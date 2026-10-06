#!/usr/bin/env python3
"""Regenerate the genesis -> Guava repoint map.

The genesis module embeds a minified/relocated Guava 29 plus a few other
libraries. The tree keeps two generations of many classes (an earlier rename
wave named one generation; the rescue sweep re-added the pre-rename copies).
Both are repointed at the real library dependency and deleted; references are
rewritten tree-wide by tools/repoint_external.py.

Usage: tools/make_genesis_repoint.py [--out tools/renames/wave5/genesis-repoint.tsv]
"""
import argparse
import ast
import collections
import os
import sys
import zipfile

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import pair_shaded as ps  # noqa: E402

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
GEN = os.path.join(ROOT, 'src/main/java/com/moonsworth/lunar/genesis')
JARS = [
    '/tmp/opencode/reference/guava/guava-29.0-jre.jar',
    os.path.join(ROOT, 'libs/lunar-bundled-libs.jar'),
    '/tmp/opencode/reference/jackson/jackson-databind-2.15.0.jar',
    '/tmp/opencode/reference/jackson/jackson-core-2.15.0.jar',
    '/tmp/opencode/reference/jackson/jackson-annotations-2.15.0.jar',
]

# classes that are genesis-specific (or badly paired); kept in the tree
DROP = {
    'ClientGameBootstrap', 'Genesis2', 'PreLaunchLibraryBootstrap',
    'URLClassLoader2', 'MixinHelper5$1_3', 'MixinHelper18_4',
    'MixinHelper312$Data', 'MixinHelper3122$Data4', 'MixinHelper4$Data18',
    'MixinHelperIterator2', 'Sentry',
}
# manually resolved identities
OVERRIDE = {
    'Annotation': 'com.google.common.annotations.GwtCompatible',
    'MixinHelper10_3': 'com.google.common.util.concurrent.Runnables',
    'MixinHelper222': 'com.google.common.base.JdkPattern',
    'MixinHelper5_5': 'com.google.common.cache.CacheBuilder',
    'MixinHelper4_10': 'com.google.common.cache.Cache',
    'MixinHelper24_4': 'com.google.common.cache.LoadingCache',
    'MixinHelper22': 'com.google.common.collect.Lists',
    'MixinHelper13_5': 'com.google.common.io.ByteStreams',
    'MixinHelper2_8': 'com.google.common.base.Preconditions',
    'MixinHelper_15': 'com.google.common.base.Strings',
    'CIterator23': 'com.google.common.collect.HashBasedTable',
    'AbstractCollectionIterator3': 'com.google.common.collect.ImmutableList',
    'Cache': 'com.google.common.cache.Cache',
    'Strings': 'com.google.common.base.Strings',
    'Preconditions': 'com.google.common.base.Preconditions',
}

ACC_PUBLIC = 0x0001


def class_access_flags(data):
    """access_flags of a class file (needs the constant pool walked first)."""
    cp_count = int.from_bytes(data[8:10], 'big')
    pos = 10
    i = 1
    while i < cp_count:
        tag = data[pos]
        if tag == 1:  # CONSTANT_Utf8
            length = int.from_bytes(data[pos + 1:pos + 3], 'big')
            pos += 3 + length
        elif tag in (3, 4, 9, 10, 11, 12, 18):  # 4-byte entries
            pos += 5
        elif tag in (5, 6):  # long/double take two slots
            pos += 9
            i += 1
        elif tag in (7, 8, 16, 19, 20):  # 2-byte entries
            pos += 3
        elif tag == 15:  # MethodHandle
            pos += 4
        else:
            raise ValueError(f'unknown constant pool tag {tag}')
        i += 1
    return int.from_bytes(data[pos:pos + 2], 'big')


def access_index(jars):
    idx = {}
    for jp in jars:
        with zipfile.ZipFile(jp) as z:
            for n in z.namelist():
                if not n.endswith('.class'):
                    continue
                data = z.read(n)
                if len(data) < 10:
                    continue
                idx[n[:-6].replace('/', '.')] = class_access_flags(data)
    return idx


def visible(fqn, idx):
    """True when the class and every enclosing class are public."""
    segs = fqn.split('.')
    start = next((i for i, s in enumerate(segs) if s[:1].isupper() or '$' in s), None)
    if start is None:
        return False
    chain = '$'.join(segs[start:]).split('$')
    cur = []
    for c in chain:
        cur.append(c)
        acc = idx.get('.'.join(segs[:start] + ['$'.join(cur)]))
        if acc is None or not (acc & ACC_PUBLIC):
            return False
    return True


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--out', default=os.path.join(
        ROOT, 'tools/renames/wave5/genesis-repoint.tsv'))
    args = ap.parse_args()

    classes, idf_s, idf_i = ps.load_jars(JARS)
    inv_s, inv_i = ps.build_index(classes)
    idx = access_index(JARS)

    idents = {}
    for f in os.listdir(GEN):
        if f.endswith('.java'):
            idents[f[:-5]] = ps.idents_from_source(
                open(os.path.join(GEN, f), errors='ignore').read())

    rows, tiers = [], collections.Counter()
    for f in sorted(os.listdir(GEN)):
        if not f.endswith('.java'):
            continue
        name = f[:-5]
        if name in DROP:
            continue
        if name in OVERRIDE:
            rows.append([name, OVERRIDE[name], 999.0, 999.0])
            tiers['override'] += 1
            continue
        text = open(os.path.join(GEN, f), errors='ignore').read()
        cands = ps.match(name, ps.strings_from_source(text),
                         ps.idents_from_source(text), classes, idf_s, idf_i,
                         inv_s, inv_i)
        if not cands:
            continue
        score, fqn, _ss, _si = cands[0]
        margin = score - (cands[1][0] if len(cands) > 1 else 0.0)
        if fqn.rsplit('.', 1)[-1] == name and score >= 2:
            rows.append([name, fqn, score, margin])
            tiers['exact-name'] += 1
        elif score >= 10 and margin >= 0.3 * score:
            rows.append([name, fqn, score, margin])
            tiers['scored'] += 1
        elif score >= 30 and margin >= 0.15 * score:
            rows.append([name, fqn, score, margin])
            tiers['strong'] += 1

    # merge duplicate targets: keep the best; keep another only when it is a
    # content twin (the tree keeps two generations of the same class)
    by_target = collections.defaultdict(list)
    for r in rows:
        by_target[r[1]].append(r)
    final = []
    for tgt, rs in by_target.items():
        rs.sort(key=lambda r: -r[2])
        final.append(rs[0])
        for r in rs[1:]:
            a, b = idents.get(rs[0][0], set()), idents.get(r[0], set())
            j = len(a & b) / len(a | b) if (a | b) else 1.0
            if j >= 0.6:
                final.append(r)

    # visibility filter: package-private/anonymous real classes cannot be
    # referenced from another package
    kept, nonpublic = [], []
    for r in final:
        if visible(r[1], idx):
            kept.append(r)
        else:
            nonpublic.append((r[0], r[1]))
    kept.sort(key=lambda r: r[0])

    with open(args.out, 'w') as fh:
        fh.write('old.package\tOld\tnew.fqn\tevidence\n')
        for r in kept:
            fh.write(f'com.moonsworth.lunar.genesis\t{r[0]}\t{r[1]}\t'
                     f'tier score {r[2]:.1f} margin {r[3]:.1f}\n')
    print(f'[genesis-map] tiers={dict(tiers)} kept={len(kept)} '
          f'dropped-nonpublic={len(nonpublic)} -> {args.out}')
    for o, t in nonpublic[:15]:
        print(f'   nonpublic: {o} -> {t}')


if __name__ == '__main__':
    main()
