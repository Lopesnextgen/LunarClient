#!/usr/bin/env python3
"""Derive merge rows (stale twin -> canonical class) from the wave maps.

Wave-6 rows whose target class already exists in the tree were SKIPped by
`apply_class_moves.py`; those are rescue duplicates of a canonical class. A
merge deletes the twin and re-points every reference at the canonical FQN,
which is exactly what `tools/repoint_external.py` does.

Usage:
  tools/make_merge_map.py --map tools/renames/wave6/moves-*.tsv \
      [--map tools/renames/wave5/classes-*.tsv ...] \
      --out tools/renames/wave6/merges.tsv
"""
import argparse
import glob
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
DECL = re.compile(r'\b(?:class|interface|enum|record|@interface)\s+([A-Za-z_$][A-Za-z0-9_$]*)')
PKG = re.compile(r'^\s*package\s+([\w.]+)\s*;', re.M)


def declared_index():
    """(pkg, class) -> relative file path, for every declaration in the tree."""
    idx = {}
    for dp, _dirs, fs in os.walk(SRC):
        for f in fs:
            if not f.endswith('.java'):
                continue
            p = os.path.join(dp, f)
            rel = os.path.relpath(p, SRC)
            text = open(p, errors='ignore').read()
            m = PKG.search(text)
            pkg = m.group(1) if m else ''
            for name in DECL.findall(text):
                idx.setdefault((pkg, name), rel)
    return idx


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', action='append', required=True)
    ap.add_argument('--out', default=os.path.join(ROOT, 'tools/renames/wave6/merges.tsv'))
    args = ap.parse_args()

    paths = []
    for pattern in args.map:
        paths.extend(glob.glob(pattern))
    idx = declared_index()
    rows, seen = [], set()
    for p in sorted(paths):
        for line in open(p, encoding='utf-8'):
            line = line.rstrip('\n')
            if not line or line.startswith('#') or line.startswith('old.package'):
                continue
            parts = line.split('\t')
            if len(parts) < 4:
                continue
            oldpkg, old, newpkg, new = parts[0], parts[1], parts[2], parts[3]
            ev = parts[4] if len(parts) > 4 else ''
            target = idx.get((newpkg, new))
            oldfile = idx.get((oldpkg, old))
            if not oldfile or not target or oldfile == target:
                continue
            if (oldpkg, old) == (newpkg, new):
                continue
            key = (oldpkg, old)
            if key in seen:
                continue
            seen.add(key)
            rows.append((oldpkg, old, newpkg + '.' + new,
                         f'merge: stale rescue twin; canonical {newpkg}.{new} already exists '
                         f'-- {ev[:120]}'))
    with open(args.out, 'w') as fh:
        fh.write('# old.package<TAB>Old<TAB>canonical.fqn<TAB>evidence\n')
        for r in rows:
            fh.write('\t'.join(r) + '\n')
    print(f'[merge-map] {len(rows)} merge rows -> {args.out}')


if __name__ == '__main__':
    sys.exit(main())
