#!/usr/bin/env python3
"""Rescue jar-only classes into the source tree.

Decompiles the requested packages out of the stale reference jar with
Vineflower and copies the results into src/main/java.  Files whose FQN
already exists in source are skipped (the source version is kept).

Usage:
  tools/rescue_classes.py com/moonsworth/lunar/client/framework \
                          com/moonsworth/lunar/client/lighting \
                          [--jar libs/lunar-renamed-classes.jar] [--apply]

Without --apply it only reports what would be added.
"""
import argparse
import os
import shutil
import subprocess
import sys
import tempfile
import zipfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
VINEFLOWER = os.path.join(ROOT, 'tools/bin/vineflower-1.12.0.jar')
WORK = os.path.join(os.environ.get('QA_WORK') or tempfile.gettempdir(), 'opencode', 'rescue')


def renamed_fqns():
    """FQNs that already have a renamed source twin (skip them)."""
    old = set()
    maps_dir = os.path.join(ROOT, 'tools/renames')
    for f in os.listdir(maps_dir):
        if not f.endswith('.tsv'):
            continue
        for line in open(os.path.join(maps_dir, f), encoding='utf-8', errors='replace'):
            if not line.strip() or line.startswith('#'):
                continue
            p = line.rstrip('\n').split('\t')
            if len(p) >= 3 and p[0].startswith('com.'):
                old.add(p[0] + '.' + p[1])
    return old


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('packages', nargs='+', help='internal package prefixes')
    ap.add_argument('--jar', default=os.path.join(ROOT, 'libs/lunar-renamed-classes.jar'))
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--no-subpackages', action='store_true',
                    help='only classes directly in the given packages')
    ap.add_argument('--include-renamed-twins', action='store_true',
                    help='also decompile classes that already have a renamed source twin '
                         '(needed when the outer class is referenced by other rescued classes)')
    ap.add_argument('--remap-member-tsv', default=None,
                    help='apply a member-rename table to the extracted bytecode before '
                         'decompiling (KinRemapper; keeps rescued code consistent with the '
                         'member names already used in src/)')
    args = ap.parse_args()

    shutil.rmtree(WORK, ignore_errors=True)
    extracted = os.path.join(WORK, 'extract')
    out = os.path.join(WORK, 'out')
    os.makedirs(extracted, exist_ok=True)

    z = zipfile.ZipFile(args.jar)
    old_names = renamed_fqns()
    n_extracted = 0
    skipped_renamed = 0
    for name in z.namelist():
        if not name.endswith('.class'):
            continue
        for pkg in args.packages:
            if name.startswith(pkg + '/'):
                if args.no_subpackages and '/' in name[len(pkg) + 1:-6]:
                    break
                fqn = name[:-6].replace('/', '.')
                if fqn in old_names and not args.include_renamed_twins:
                    skipped_renamed += 1
                    break
                path = os.path.join(extracted, name)
                os.makedirs(os.path.dirname(path), exist_ok=True)
                open(path, 'wb').write(z.read(name))
                n_extracted += 1
                break
    print(f'[rescue] extracted {n_extracted} classes '
          f'(skipped {skipped_renamed} that already have renamed source twins)')

    if args.remap_member_tsv:
        # zip the extracted classes, apply the member table with KinRemapper,
        # and decompile the renamed bytecode instead of the raw classes
        in_jar = os.path.join(WORK, 'in.jar')
        out_jar = os.path.join(WORK, 'remapped.jar')
        subprocess.run(['jar', 'cf', in_jar, '-C', extracted, '.'], check=True)
        cp = ':'.join(os.path.join(ROOT, 'tools/bin', j) for j in (
            'kinremapper.jar', 'asm-9.7.1.jar', 'asm-commons-9.7.1.jar', 'asm-tree-9.7.1.jar'))
        empty = os.path.join(WORK, 'empty-mappings')
        os.makedirs(empty, exist_ok=True)
        subprocess.run(['java', '-cp', cp, 'KinRemapper', empty, in_jar, out_jar,
                        '-', os.path.abspath(args.remap_member_tsv)],
                       check=True)
        src_dir = out_jar
    else:
        src_dir = extracted

    subprocess.run(['java', '-jar', VINEFLOWER, '-dgs=1', src_dir, out],
                   check=True, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
    produced = []
    for dp, _dirs, fs in os.walk(out):
        for f in fs:
            if f.endswith('.java'):
                produced.append(os.path.join(dp, f))
    print(f'[rescue] decompiled {len(produced)} java files')

    added = skipped = 0
    for path in produced:
        rel = os.path.relpath(path, out)
        dst = os.path.join(SRC, rel)
        if os.path.exists(dst):
            skipped += 1
            continue
        added += 1
        if args.apply:
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            shutil.copy2(path, dst)
    print(f'[rescue] {"added" if args.apply else "would add"} {added}, skipped {skipped} existing')
    if not args.apply and added:
        for path in produced:
            rel = os.path.relpath(path, out)
            if not os.path.exists(os.path.join(SRC, rel)):
                print('   ', rel)


if __name__ == '__main__':
    sys.exit(main())
