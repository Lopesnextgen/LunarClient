#!/usr/bin/env python3
"""Lunar Client 1.8.9 deobfuscation pipeline.

Stages
------
  mappings   Convert .kin mapping files into a combined Tiny v2 file.
  extract    Extract + deduplicate all Lunar jars into one merged jar.
  remap      Apply Minecraft mappings to the merged jar with TinyRemapper.
  decompile  Decompile remapped com/moonsworth classes with Vineflower.
  fallback   Decompile classes Vineflower skipped with CFR.
  assemble   Copy decompiled sources, libraries and assets into the project.
  report     Print pipeline statistics.

All heavy intermediates live under tools/work/.
"""
import argparse
import collections
import hashlib
import json
import os
import shutil
import subprocess
import sys
import time
import zipfile

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.dirname(HERE)
WORK = os.path.join(HERE, 'work')
STAGING = os.path.join(WORK, 'staging')
LOGS = os.path.join(WORK, 'logs')
MAPPINGS = os.path.join(WORK, 'mappings')

LUNAR_BASE = os.path.join(ROOT, 'libs', 'multiver-full')

# priority: lower = preferred when duplicate class files differ
JARS = collections.OrderedDict([
    ('lunar', 'lunar.jar'),
    ('genesis', 'genesis-0.1.0-SNAPSHOT-all.jar'),
    ('forge', 'forge-0.1.0-SNAPSHOT-all.jar'),
    ('legacy', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar'),
    ('optifine-module', 'optifine-0.1.0-SNAPSHOT-all.jar'),
    ('common', 'common-0.1.0-SNAPSHOT-all-nomappings.jar'),
    ('replaymixins', 'lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar'),
])

KIN_FILES = [
    'v1_8_inflight_vanilla.kin',
    'v1_8_inflight_optifine.kin',
    'v1_8_inflight_forge.kin',
    'v1_8_inflight_optiforge.kin',
    'lunar/lunar_named_b5_1.8.9.kin',
]

JAVA = os.environ.get('JAVA', 'java')
TINY_REMAPPER = os.path.join(HERE, 'bin', 'tiny-remapper.jar')
VINEFLOWER = os.path.join(HERE, 'bin', 'vineflower-1.12.0.jar')
CFR = os.path.join(HERE, 'bin', 'cfr-0.153.jar')

SOURCE_PREFIXES = ('com/moonsworth/',)


def log(msg):
    print(f'[{time.strftime("%H:%M:%S")}] {msg}', flush=True)


def is_source_class(fqcn):
    return fqcn.startswith(SOURCE_PREFIXES)


def sha1(data):
    return hashlib.sha1(data).hexdigest()


def is_obf_entry(name):
    """True for obfuscated single-letter class/package entries in MC jars."""
    if not name.endswith('.class'):
        return False
    top = name.split('/')[0]
    if len(top) <= 3 and top == top.lower() and not top.startswith('net'):
        return True
    # nested obf packages like ady$FlowerEntry.class or aee$1.class
    if '$' in top and len(top.split('$')[0]) <= 3 and top.split('$')[0].islower():
        return True
    return False


def make_filtered(src, dst, exclude):
    if not os.path.exists(src):
        return
    n = 0
    with zipfile.ZipFile(src) as z, zipfile.ZipFile(dst, 'w', zipfile.ZIP_DEFLATED) as o:
        for info in z.infolist():
            name = info.filename
            if name.endswith('/') or exclude(name):
                continue
            o.writestr(name, z.read(info))
            n += 1
    log(f'wrote {dst} ({n} entries)')


# --------------------------------------------------------------------------
# mappings
# --------------------------------------------------------------------------

def stage_mappings(args):
    passes = [
        ('mappings-vanilla.tiny', [
            'lunar/lunar_named_b5_1.8.9.kin',
            'v1_8_inflight_vanilla.kin',
            'v1_8_inflight_optifine.kin',
        ]),
        ('mappings-forge.tiny', [
            'v1_8_inflight_forge.kin',
            'v1_8_inflight_optiforge.kin',
        ]),
    ]
    for out_name, kin_names in passes:
        out = os.path.join(MAPPINGS, out_name)
        kin = [os.path.join(MAPPINGS, k) for k in kin_names]
        for k in kin:
            if not os.path.exists(k):
                raise SystemExit(f'missing kin file: {k}')
        cmd = [sys.executable, os.path.join(HERE, 'kin2tiny.py'), out] + kin
        log('running ' + ' '.join(cmd))
        subprocess.check_call(cmd)


# --------------------------------------------------------------------------
# extract
# --------------------------------------------------------------------------

def stage_extract(args):
    sys.path.insert(0, HERE)
    export = os.path.join(STAGING, 'jar-resources')
    classes_dir = os.path.join(STAGING, 'classes')
    if os.path.exists(STAGING) and args.force:
        shutil.rmtree(STAGING)
    os.makedirs(STAGING, exist_ok=True)
    os.makedirs(LOGS, exist_ok=True)

    seen = {}          # fqcn -> (priority, jar, sha, path)
    conflicts = []
    stats = collections.Counter()
    resources = collections.Counter()

    merged_path = os.path.join(STAGING, 'lunar-all.jar')
    with zipfile.ZipFile(merged_path, 'w', zipfile.ZIP_STORED) as merged:
        for priority, (label, fn) in enumerate(JARS.items()):
            path = os.path.join(LUNAR_BASE, fn)
            if not os.path.exists(path):
                log(f'WARN missing jar {path}')
                continue
            with zipfile.ZipFile(path) as z:
                for info in z.infolist():
                    name = info.filename
                    if name.endswith('/'):
                        continue
                    if name.endswith('.class'):
                        fqcn = name[:-6]
                        data = z.read(info)
                        h = sha1(data)
                        prev = seen.get(fqcn)
                        if prev is not None:
                            if prev[2] == h:
                                stats['dup_identical'] += 1
                            else:
                                conflicts.append((fqcn, prev[1], label))
                                stats['dup_conflict'] += 1
                                if priority < prev[0]:
                                    merged.writestr(name, data)
                                    seen[fqcn] = (priority, label, h, name)
                            continue
                        seen[fqcn] = (priority, label, h, name)
                        merged.writestr(name, data)
                        stats['classes'] += 1
                        stats['from_' + label] += 1
                    else:
                        resources[label] += 1
                        if name.startswith('META-INF/services/') or name.endswith('.json'):
                            dest = os.path.join(export, label, name)
                            os.makedirs(os.path.dirname(dest), exist_ok=True)
                            with open(dest, 'wb') as f:
                                f.write(z.read(info))
            log(f'extracted {label}: {fn}')

    report = {
        'classes_total': stats['classes'],
        'per_jar': {k: v for k, v in stats.items() if k.startswith('from_')},
        'dup_identical': stats['dup_identical'],
        'dup_conflict': stats['dup_conflict'],
        'conflicts': conflicts[:200],
        'resources': dict(resources),
    }
    with open(os.path.join(LOGS, 'extract-report.json'), 'w') as f:
        json.dump(report, f, indent=2)
    with open(os.path.join(LOGS, 'origin.json'), 'w') as f:
        json.dump({fqcn: v[1] for fqcn, v in seen.items()}, f)
    log(f'extract: {stats["classes"]} unique classes, '
        f'{stats["dup_identical"]} identical dups, {stats["dup_conflict"]} conflicting dups')
    log(f'merged jar: {merged_path} ({os.path.getsize(merged_path)/1e6:.1f} MB)')


# --------------------------------------------------------------------------
# remap
# --------------------------------------------------------------------------

def stage_remap(args):
    src = os.path.join(STAGING, 'lunar-all.jar')
    pass1 = os.path.join(STAGING, 'lunar-all-remapped.jar')
    final = os.path.join(STAGING, 'lunar-all-final.jar')
    cp = os.pathsep.join([
        os.path.join(HERE, 'bin', 'kinremapper.jar'),
        os.path.join(HERE, 'bin', 'asm-9.7.1.jar'),
        os.path.join(HERE, 'bin', 'asm-commons-9.7.1.jar'),
        os.path.join(HERE, 'bin', 'asm-tree-9.7.1.jar'),
    ])

    def run(inp, outp, extra=None):
        cmd = [JAVA, '-Xmx3g', '-cp', cp, 'KinRemapper', MAPPINGS, inp, outp]
        if extra:
            cmd.append(extra)
        with open(os.path.join(LOGS, 'remap.log'), 'a') as f:
            f.write('$ ' + ' '.join(cmd) + '\n')
            r = subprocess.run(cmd, stdout=f, stderr=subprocess.STDOUT)
        if r.returncode != 0:
            log('KinRemapper failed; see tools/work/logs/remap.log')
            sys.exit(r.returncode)
        with open(os.path.join(LOGS, 'remap.log')) as f:
            for line in f:
                if line.startswith('loaded:') or line.startswith('remapped in'):
                    log(line.strip())

    log('remap pass 1: Minecraft mappings')
    run(src, pass1)
    renames = os.path.join(MAPPINGS, 'class-renames.tsv')
    subprocess.check_call([sys.executable, os.path.join(HERE, 'make_class_renames.py'),
                           pass1, renames])
    log('remap pass 2: class/package clash renames')
    run(pass1, final, renames)
    with zipfile.ZipFile(final) as z:
        log(f'final remapped jar: {final} ({len(z.infolist())} entries, '
            f'{os.path.getsize(final)/1e6:.1f} MB)')


def split_remapped(out_dir=None, force=False, jar=None):
    """Split the remapped merged jar into source classes and lib classes."""
    src = jar or os.path.join(STAGING, 'lunar-all-final.jar')
    base = out_dir or os.path.join(STAGING, 'remapped')
    if force and os.path.exists(base):
        shutil.rmtree(base)
    src_dir = os.path.join(base, 'source')
    lib_dir = os.path.join(base, 'libs')
    os.makedirs(src_dir, exist_ok=True)
    os.makedirs(lib_dir, exist_ok=True)
    stats = collections.Counter()
    with zipfile.ZipFile(src) as z:
        for info in z.infolist():
            name = info.filename
            if not name.endswith('.class'):
                continue
            fqcn = name[:-6]
            if is_source_class(fqcn):
                dest = os.path.join(src_dir, name)
                stats['source'] += 1
            else:
                dest = os.path.join(lib_dir, name)
                stats['libs'] += 1
            os.makedirs(os.path.dirname(dest), exist_ok=True)
            with open(dest, 'wb') as f:
                f.write(z.read(info))
    log(f'split remapped: {stats["source"]} source classes, {stats["libs"]} lib classes')


# --------------------------------------------------------------------------
# decompile
# --------------------------------------------------------------------------

def stage_decompile(args):
    split_remapped(force=True, jar=args.jar)
    src = os.path.join(STAGING, 'remapped', 'source')
    dst = os.path.join(STAGING, 'decompiled')
    if os.path.exists(dst):
        shutil.rmtree(dst)
    os.makedirs(dst, exist_ok=True)

    # Group classes by package prefix, then greedily pack into balanced chunks.
    groups = collections.defaultdict(list)
    for root, _, files in os.walk(src):
        for f in files:
            if not f.endswith('.class'):
                continue
            p = os.path.join(root, f)
            rel = os.path.relpath(p, src)
            key = os.sep.join(rel.split(os.sep)[:5])
            groups[key].append(p)
    total = sum(len(v) for v in groups.values())
    target = max(600, min(1500, args.chunk_size))
    nchunks = max(1, min(24, (total + target - 1) // target))
    chunks = [[] for _ in range(nchunks)]
    for g in sorted(groups.values(), key=len, reverse=True):
        min(chunks, key=len).extend(g)
    log(f'chunking {total} source classes into {nchunks} chunks '
        f'({", ".join(str(len(c)) for c in chunks)})')

    chunk_root = os.path.join(STAGING, 'chunks')
    if os.path.exists(chunk_root):
        shutil.rmtree(chunk_root)
    for idx, chunk in enumerate(chunks):
        if not chunk:
            continue
        cdir = os.path.join(chunk_root, f'chunk-{idx:02d}')
        for p in chunk:
            rel = os.path.relpath(p, src)
            dest = os.path.join(cdir, rel)
            os.makedirs(os.path.dirname(dest), exist_ok=True)
            shutil.copy2(p, dest)
        cmd = [JAVA, f'-Xmx{args.heap}', '-jar', VINEFLOWER,
               '--thread-count=' + str(args.threads),
               '-dgs=1', '-jrt=1', '-hdc=0']
        if not args.imports:
            cmd.append('--remove-imports=true')
        cmd += [cdir, dst]
        logstart = time.strftime('%H:%M:%S')
        with open(os.path.join(LOGS, f'vineflower-{idx:02d}.log'), 'w') as f:
            r = subprocess.run(cmd, stdout=f, stderr=subprocess.STDOUT)
        made = sum(1 for _, _, fs in os.walk(dst) for f in fs if f.endswith('.java'))
        log(f'chunk {idx + 1}/{nchunks}: {len(chunk)} classes, rc={r.returncode}, '
            f'total java files={made}')
    javas = sum(1 for _, _, fs in os.walk(dst) for f in fs if f.endswith('.java'))
    log(f'vineflower done, produced {javas} java files')


def stage_fallback(args):
    src = os.path.join(STAGING, 'remapped', 'source')
    dst = os.path.join(STAGING, 'decompiled')
    classes = []
    for root, _, files in os.walk(src):
        for f in files:
            if f.endswith('.class'):
                classes.append(os.path.join(root, f))
    missing = []
    for cls in classes:
        rel = os.path.relpath(cls, src)[:-6]      # package/Outer$Inner
        # a class is covered if its own file exists, or any enclosing class file
        # exists (Vineflower inlines anonymous/member classes into the outer
        # file). Outers may carry a trailing `_` from the clash-rename pass.
        candidate = rel
        covered = False
        while True:
            if os.path.exists(os.path.join(dst, candidate + '.java')) or \
                    os.path.exists(os.path.join(dst, candidate + '_.java')):
                covered = True
                break
            if '$' not in candidate:
                break
            candidate = candidate.rsplit('$', 1)[0]
        if not covered:
            missing.append(cls)
    if not missing:
        log('all classes covered by Vineflower output; no CFR fallback needed')
        return
    log(f'{len(missing)} classes missing from vineflower output; running CFR fallback')
    out = os.path.join(STAGING, 'decompiled-cfr')
    os.makedirs(out, exist_ok=True)
    failed = 0
    for i, cls in enumerate(missing):
        rel = os.path.relpath(cls, src)
        dest = os.path.join(out, os.path.dirname(rel))
        os.makedirs(dest, exist_ok=True)
        cmd = [JAVA, '-Xmx1g', '-jar', CFR, cls, '--outputdir', dest]
        r = subprocess.run(cmd, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
        if r.returncode != 0:
            failed += 1
    cfr_ok = sum(1 for _, _, fs in os.walk(out) for f in fs if f.endswith('.java'))
    log(f'CFR produced {cfr_ok} files ({failed} failures)')
    json.dump([os.path.relpath(c, src) for c in missing],
              open(os.path.join(LOGS, 'vineflower-missing.json'), 'w'), indent=2)


# --------------------------------------------------------------------------
# assemble
# --------------------------------------------------------------------------

def stage_assemble(args):
    java_out = os.path.join(ROOT, 'src', 'main', 'java')
    libs_out = os.path.join(ROOT, 'libs')
    os.makedirs(libs_out, exist_ok=True)

    # 1. decompiled sources
    moons = os.path.join(java_out, 'com', 'moonsworth')
    if os.path.exists(moons):
        shutil.rmtree(moons)
    for base in ['decompiled', 'decompiled-cfr']:
        src = os.path.join(STAGING, base)
        if not os.path.exists(src):
            continue
        n = 0
        for root, _, files in os.walk(src):
            for f in files:
                if not f.endswith('.java'):
                    continue
                rel = os.path.relpath(os.path.join(root, f), src)
                dest = os.path.join(java_out, rel)
                os.makedirs(os.path.dirname(dest), exist_ok=True)
                shutil.copy2(os.path.join(root, f), dest)
                n += 1
        log(f'copied {n} sources from {base}')

    # 2. libraries jar: every remapped Lunar class (including the decompiled
    #    sources themselves, so any source excluded from compilation can still
    #    be resolved from the classpath).
    all_jar = os.path.join(STAGING, 'lunar-all-final.jar')
    lib_jar = os.path.join(libs_out, 'lunar-libraries.jar')
    n = 0
    with zipfile.ZipFile(all_jar) as z, \
            zipfile.ZipFile(lib_jar, 'w', zipfile.ZIP_DEFLATED, compresslevel=6) as o:
        for info in z.infolist():
            if info.filename.endswith('.class'):
                o.writestr(info, z.read(info))
                n += 1
    log(f'wrote {lib_jar} ({n} classes, {os.path.getsize(lib_jar)/1e6:.1f} MB)')

    # 3. extra libraries shipped with the client
    extra = {
        'ReplayMod-v1_8-2.6.24.jar': 'ReplayMod-v1_8-2.6.24.jar',
        'OptiFine_v1_8.jar': 'OptiFine_v1_8.jar',
        'Forge_v1_8.jar': 'Forge_v1_8.jar',
    }
    for src_name, dst_name in extra.items():
        s = os.path.join(LUNAR_BASE, src_name)
        if os.path.exists(s):
            shutil.copy2(s, os.path.join(libs_out, dst_name))
            log(f'copied lib {dst_name}')

    # 3b. filtered compile-time variants without obfuscated net/minecraft classes
    make_filtered(os.path.join(LUNAR_BASE, 'Forge_v1_8.jar'),
                  os.path.join(libs_out, 'forge-1.8.9-compile.jar'),
                  lambda n: n.startswith('net/minecraft/') or is_obf_entry(n))
    make_filtered(os.path.join(LUNAR_BASE, 'OptiFine_v1_8.jar'),
                  os.path.join(libs_out, 'optifine-1.8.9-compile.jar'),
                  lambda n: n.startswith('net/minecraft/') or is_obf_entry(n))

    # 4. assets / resources
    res_out = os.path.join(ROOT, 'src', 'main', 'resources')
    natives_out = os.path.join(libs_out, 'natives')
    for native_zip in ['client-natives-linux-x86-v1_8.zip', 'ul-natives-x86-linux.zip',
                       'webosr-natives-x86-linux.zip']:
        s = os.path.join(LUNAR_BASE, native_zip)
        if os.path.exists(s):
            os.makedirs(natives_out, exist_ok=True)
            shutil.copy2(s, os.path.join(natives_out, native_zip))
            log(f'copied native {native_zip}')
    lunar_tex = os.path.join(ROOT, 'libs', 'lunar-assets', 'textures', 'assets', 'lunar')
    dst_lunar = os.path.join(res_out, 'assets', 'lunar')
    if os.path.exists(lunar_tex):
        log('copying Lunar assets ...')
        for root, _, files in os.walk(lunar_tex):
            for f in files:
                rel = os.path.relpath(os.path.join(root, f), lunar_tex)
                dest = os.path.join(dst_lunar, rel)
                os.makedirs(os.path.dirname(dest), exist_ok=True)
                shutil.copy2(os.path.join(root, f), dest)
        log(f'Lunar assets copied to {dst_lunar}')

    # lunar-lang.jar -> resources/assets/minecraft/lang additions
    lang_jar = os.path.join(LUNAR_BASE, 'lunar-lang.jar')
    if os.path.exists(lang_jar):
        with zipfile.ZipFile(lang_jar) as z:
            for info in z.infolist():
                if info.filename.endswith('/'):
                    continue
                dest = os.path.join(res_out, info.filename)
                os.makedirs(os.path.dirname(dest), exist_ok=True)
                with open(dest, 'wb') as f:
                    f.write(z.read(info))
        log('lunar-lang.jar resources copied')

    # bridge config and service files
    res_export = os.path.join(STAGING, 'jar-resources')
    if os.path.exists(res_export):
        for label in os.listdir(res_export):
            base = os.path.join(res_export, label)
            for root, _, files in os.walk(base):
                for f in files:
                    rel = os.path.relpath(os.path.join(root, f), base)
                    dest = os.path.join(res_out, rel)
                    os.makedirs(os.path.dirname(dest), exist_ok=True)
                    shutil.copy2(os.path.join(root, f), dest)
        log('service/json resources copied')


# --------------------------------------------------------------------------

def main():
    ap = argparse.ArgumentParser(description=__doc__)
    sub = ap.add_subparsers(dest='stage', required=True)

    p = sub.add_parser('mappings')
    p.set_defaults(func=stage_mappings)

    p = sub.add_parser('extract')
    p.add_argument('--force', action='store_true')
    p.set_defaults(func=stage_extract)

    p = sub.add_parser('remap')
    p.set_defaults(func=stage_remap)

    p = sub.add_parser('decompile')
    p.add_argument('--threads', type=int, default=4)
    p.add_argument('--heap', default='2g')
    p.add_argument('--chunk-size', type=int, default=1200)
    p.add_argument('--jar', default=None, help='remapped source jar to decompile')
    p.add_argument('--imports', action='store_true',
                   help='keep import statements (prettier, but ambiguous for '
                        'same-named obfuscated classes)')
    p.set_defaults(func=stage_decompile)

    p = sub.add_parser('fallback')
    p.set_defaults(func=stage_fallback)

    p = sub.add_parser('assemble')
    p.set_defaults(func=stage_assemble)

    args = ap.parse_args()
    os.makedirs(LOGS, exist_ok=True)
    args.func(args)


if __name__ == '__main__':
    main()
