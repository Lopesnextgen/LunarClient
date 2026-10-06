#!/usr/bin/env python3
"""Split the lazy-name inventory into subagent-sized clusters.

Groups rows by package, caps each cluster at --size classes (default 50) and
writes tools/renames/cluster-<id>.txt (file list) plus an index
tools/renames/clusters.tsv.

Usage: tools/make_clusters.py [--size 50]
"""
import argparse
import os
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
INV = os.path.join(ROOT, 'tools/renames/inventory.tsv')
OUTDIR = os.path.join(ROOT, 'tools/renames')

# packages that are shaded third-party code and should be deleted, not renamed
SKIP_PREFIXES = (
    'com.moonsworth.lunar.forge.lib',
)


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--size', type=int, default=50)
    args = parser.parse_args()

    by_package = defaultdict(list)
    for line in open(INV, encoding='utf-8'):
        if line.startswith('package\t') or not line.strip():
            continue
        pkg, cls, path = line.rstrip('\n').split('\t')
        if any(pkg.startswith(p) for p in SKIP_PREFIXES):
            continue
        by_package[pkg].append((cls, path))

    # keep packages together; order by size descending so big features get
    # their own cluster first
    ordered = sorted(by_package.items(), key=lambda kv: (-len(kv[1]), kv[0]))

    clusters = []
    current = []
    current_pkgs = []
    for pkg, rows in ordered:
        if len(rows) > args.size:
            # split a single huge package into chunks of `size`
            for i in range(0, len(rows), args.size):
                chunk = rows[i:i + args.size]
                clusters.append((pkg + (f'#part{i // args.size + 1}' if len(rows) > args.size else ''), chunk))
            continue
        if current and len(current) + len(rows) > args.size:
            clusters.append(('+'.join(current_pkgs), current))
            current, current_pkgs = [], []
        current.extend(rows)
        current_pkgs.append(pkg)
    if current:
        clusters.append(('+'.join(current_pkgs), current))

    index = open(os.path.join(OUTDIR, 'clusters.tsv'), 'w', encoding='utf-8')
    index.write('cluster\tpackage\tclass\tfile\n')
    for number, (name, rows) in enumerate(clusters, start=1):
        cid = f'{number:02d}'
        with open(os.path.join(OUTDIR, f'cluster-{cid}.txt'), 'w', encoding='utf-8') as out:
            for cls, path in rows:
                out.write(f'{path}\t{cls}\n')
        for cls, path in rows:
            index.write(f'{cid}\t{name}\t{cls}\t{path}\n')
        print(f'cluster-{cid}: {len(rows):3d} classes  {name[:90]}')
    index.close()
    print(f'[clusters] wrote {len(clusters)} clusters to tools/renames/')


if __name__ == '__main__':
    main()
