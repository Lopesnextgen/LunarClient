#!/usr/bin/env python3
"""Build a jar-class -> current-source-class map from the rename ledgers.

Reads every tools/renames/*.tsv (class-rename and move maps) and emits
`source<TAB>jar` rows for classes whose FQN changed during the rename waves.
Usage: tools/build_jar_map.py [--out FILE]
"""
import argparse
import os

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MAPS = os.path.join(ROOT, 'tools/renames')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--out', default='/tmp/opencode/jar-to-source.tsv')
    args = ap.parse_args()

    rows = {}
    pkg_moves = []
    for f in sorted(os.listdir(MAPS)):
        if not f.endswith('.tsv'):
            continue
        for line in open(os.path.join(MAPS, f), encoding='utf-8', errors='replace'):
            if not line.strip() or line.startswith('#'):
                continue
            p = line.rstrip('\n').split('\t')
            if p[0].startswith('com.') and len(p) == 2 and p[1].startswith('com.'):
                pkg_moves.append((p[0], p[1]))
    pkg_moves.sort(key=lambda r: -len(r[0]))

    def move_src(fqn):
        for old, new in pkg_moves:
            if fqn == old or fqn.startswith(old + '.'):
                return new + fqn[len(old):]
        return fqn

    for f in sorted(os.listdir(MAPS)):
        if not f.endswith('.tsv'):
            continue
        if not (f.startswith('classes-') or f.startswith('moves-')):
            continue  # skip inventories/clusters/members
        for line in open(os.path.join(MAPS, f), encoding='utf-8', errors='replace'):
            if not line.strip() or line.startswith('#'):
                continue
            p = line.rstrip('\n').split('\t')
            if len(p) < 4 or not p[0].startswith('com.'):
                continue
            if p[2].startswith('com.'):        # move map: oldpkg old newpkg new
                jar, src = p[0] + '.' + p[1], p[2] + '.' + p[3]
            else:                              # rename map: package old new
                jar, src = p[0] + '.' + p[1], p[0] + '.' + p[2]
            rows[move_src(src)] = jar
    with open(args.out, 'w', encoding='utf-8') as out:
        for src, jar in sorted(rows.items()):
            out.write(f'{src}\t{jar}\n')
    print(f'[jar-map] wrote {len(rows)} source->jar class mappings to {args.out}')


if __name__ == '__main__':
    main()
