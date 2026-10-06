#!/usr/bin/env python3
"""Full-package audit inventory (the lazy-name inventory's blind spot).

`name_inventory.py` only lists classes whose *name* looks like a placeholder
(trailing digits, MixinN/DataN/...).  A package full of plausible-but-wrong
names (e.g. `client.tps` = the render pipeline, `TpsType` = a render stage) is
invisible to it and never reaches a rename subagent.

This tool walks every package under `src/main/java/com/moonsworth/lunar` and
groups it into subsystem-sized *audit clusters* so subagents can review the
whole tree, not only the lazy subset.

Outputs:
  tools/renames/package-inventory.tsv   package, n_classes, n_lazy, sample
  tools/renames/audit-clusters.tsv      cluster, package, n_classes, n_lazy
  tools/renames/audit-cluster-NN.txt    one package per line for the subagent

Usage: tools/package_inventory.py [--size 8] [--root com/moonsworth/lunar]
"""
import argparse
import os
import re
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')

CLASS_RE = re.compile(r'\b(?:class|interface|enum|record|@interface)\s+([A-Za-z_$][A-Za-z0-9_$]*)')
STEMS = ('Mixin', 'Data', 'Framework', 'Gui', 'Handler', 'Manager', 'Helper',
         'Object', 'Holder', 'Wrapper', 'Util', 'Impl', 'Type', 'Entry',
         'Iterator', 'Loader', 'List', 'Map', 'Set', 'Factory', 'Builder')


def is_lazy(name):
    if '$' in name:
        return False
    if re.match(r'^[A-Za-z_]+[0-9]+$', name):
        return True
    for stem in STEMS:
        if name.startswith(stem) and name != stem and re.search(r'\d+$', name):
            return True
    return False


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--size', type=int, default=8, help='max packages per audit cluster')
    ap.add_argument('--root', default='com/moonsworth/lunar')
    args = ap.parse_args()

    root = os.path.join(SRC, args.root)
    pkgs = {}
    for dirpath, _dirs, files in os.walk(root):
        java = [f for f in files if f.endswith('.java')]
        if not java:
            continue
        rel = os.path.relpath(dirpath, SRC).replace(os.sep, '.')
        names = []
        lazy = 0
        for f in java:
            try:
                text = open(os.path.join(dirpath, f), encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            found = CLASS_RE.findall(text)
            names.extend(found)
            lazy += sum(1 for n in found if is_lazy(n))
        pkgs[rel] = {
            'n': len(java),
            'lazy': lazy,
            'sample': sorted({n for n in names if not n.startswith('$')})[:6],
        }

    # group: keep families together (parent package prefix), cap per cluster
    def family(p):
        segs = p.split('.')
        # com.moonsworth.lunar.<a>.<b>... -> family is first 4-5 segments
        return '.'.join(segs[:5])

    by_family = defaultdict(list)
    for p in sorted(pkgs):
        by_family[family(p)].append(p)

    os.makedirs(os.path.join(ROOT, 'tools/renames'), exist_ok=True)
    with open(os.path.join(ROOT, 'tools/renames/package-inventory.tsv'), 'w') as out:
        out.write('package\tn_classes\tn_lazy\tsample\n')
        for p in sorted(pkgs):
            info = pkgs[p]
            out.write(f"{p}\t{info['n']}\t{info['lazy']}\t{','.join(info['sample'])}\n")

    clusters = []
    current = []
    for fam, members in sorted(by_family.items()):
        for p in members:
            current.append(p)
            if len(current) >= args.size:
                clusters.append(current)
                current = []
    if current:
        clusters.append(current)

    idx = open(os.path.join(ROOT, 'tools/renames/audit-clusters.tsv'), 'w')
    idx.write('cluster\tpackage\tn_classes\tn_lazy\n')
    for i, members in enumerate(clusters, 1):
        cid = f'{i:02d}'
        with open(os.path.join(ROOT, f'tools/renames/audit-cluster-{cid}.txt'), 'w') as fh:
            for p in members:
                fh.write(p + '\n')
        for p in members:
            idx.write(f"{cid}\t{p}\t{pkgs[p]['n']}\t{pkgs[p]['lazy']}\n")
    idx.close()

    total_classes = sum(v['n'] for v in pkgs.values())
    total_lazy = sum(v['lazy'] for v in pkgs.values())
    print(f'[pkg-inventory] {len(pkgs)} packages, {total_classes} classes, '
          f'{total_lazy} lazy-name declarations')
    print(f'[pkg-inventory] wrote {len(clusters)} audit clusters (size<= {args.size})')
    # a few suspect examples: packages whose name shares no word with their classes
    print('examples with 0 lazy but >=3 classes (previously invisible):')
    shown = 0
    for p, v in sorted(pkgs.items()):
        if v['lazy'] == 0 and v['n'] >= 3:
            print(f"   {v['n']:3d} classes  {p}   {', '.join(v['sample'][:3])}")
            shown += 1
            if shown >= 12:
                break


if __name__ == '__main__':
    main()
