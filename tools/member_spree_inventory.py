#!/usr/bin/env python3
"""Inventory the placeholder/obfuscated member names for the member spree.

Outputs (all under tools/renames/wave5/):
* members-obf.tsv    owner, kind, name, signature, file, count   (all-caps tokens)
* members-lazy.tsv   owner, kind, name, signature, file          (methodN/fieldN/...)
* member-clusters.tsv cluster, owner, file, n_lazy, sample

Usage: tools/member_spree_inventory.py [--size 25]
"""
import argparse
import collections
import os
import re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
OUT = os.path.join(ROOT, 'tools/renames/wave5')

DECL = re.compile(r'\b(?:class|interface|enum|record|@interface)\s+([A-Za-z_$][A-Za-z0-9_$]*)')
METHOD = re.compile(
    r'^\s*(?:@\w+(?:\([^)]*\))?\s*)*'
    r'(?:(?:public|protected|private|static|final|abstract|synchronized|native|default|strictfp)\s+)*'
    r'(?:<[^>]+>\s+)?'
    r'([\w$.<>\[\],?\s]+?)\s+'
    r'([A-Za-z_$][\w$]*)\s*\(([^)]*)\)')
FIELD = re.compile(
    r'^\s*(?:@\w+(?:\([^)]*\))?\s*)*'
    r'(?:(?:public|protected|private|static|final|volatile|transient)\s+)*'
    r'([\w$.<>\[\],?\s]+?)\s+'
    r'([A-Za-z_$][\w$]*)\s*(?:=\s*[^;]+)?;')
OBF = re.compile(r'^[A-Z]{12,}$')
LAZY = re.compile(r'^(method|field|arg|var|value|number|flag|obj|text|it|this|data|type|entry|item|key|name|result|input|output)\d+$')
PARAM = re.compile(r'([\w$.<>\[\],?]+)\s+([A-Za-z_$][\w$]*)\s*(?:,|$)')


def owner_of(path):
    text = open(path, errors='ignore').read()
    m = DECL.search(text)
    return m.group(1) if m else os.path.basename(path)[:-5]


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--size', type=int, default=25)
    args = ap.parse_args()

    obf = collections.defaultdict(lambda: {'count': 0, 'files': set(), 'sig': ''})
    lazy = []
    files = []
    for dp, _dirs, fs in os.walk(SRC):
        for f in fs:
            if not f.endswith('.java'):
                continue
            p = os.path.join(dp, f)
            rel = os.path.relpath(p, SRC)
            owner = owner_of(p)
            lazy_count = 0
            for line in open(p, errors='ignore'):
                line = line.rstrip('\n')
                m = METHOD.match(line)
                if m:
                    name = m.group(2)
                    if OBF.match(name):
                        key = (owner, 'M', name)
                        obf[key]['count'] += 1
                        obf[key]['files'].add(rel)
                        obf[key]['sig'] = m.group(1).strip()
                    elif LAZY.match(name):
                        lazy.append((owner, 'M', name, m.group(1).strip(), rel))
                        lazy_count += 1
                    for pm in PARAM.finditer(m.group(3)):
                        if LAZY.match(pm.group(2)) or OBF.match(pm.group(2)):
                            lazy.append((owner, 'P', pm.group(2), pm.group(1).strip(), rel))
                            lazy_count += 1
                    continue
                m = FIELD.match(line)
                if m:
                    name = m.group(2)
                    if OBF.match(name):
                        key = (owner, 'F', name)
                        obf[key]['count'] += 1
                        obf[key]['files'].add(rel)
                        obf[key]['sig'] = m.group(1).strip()
                    elif LAZY.match(name):
                        lazy.append((owner, 'F', name, m.group(1).strip(), rel))
                        lazy_count += 1
            if lazy_count or any(k[0] == owner for k in obf):
                files.append((owner, rel, lazy_count))

    with open(os.path.join(OUT, 'members-obf.tsv'), 'w') as fh:
        fh.write('owner\tkind\tname\tsignature\tcount\tfiles\n')
        for (owner, kind, name), v in sorted(obf.items()):
            fh.write(f"{owner}\t{kind}\t{name}\t{v['sig']}\t{v['count']}\t"
                     f"{','.join(sorted(v['files'])[:4])}\n")
    with open(os.path.join(OUT, 'members-lazy.tsv'), 'w') as fh:
        fh.write('owner\tkind\tname\tsignature\tfile\n')
        for row in sorted(lazy):
            fh.write('\t'.join(row) + '\n')

    # clusters: group owners by top package, cap --size owners per cluster
    def fam(owner_file):
        rel = owner_file[1]
        parts = rel.split('/')
        return '/'.join(parts[:6])
    files.sort(key=lambda t: t[1])
    by_fam = collections.OrderedDict()
    for owner, rel, n in files:
        by_fam.setdefault(fam((owner, rel)), []).append((owner, rel, n))
    idx = open(os.path.join(OUT, 'member-clusters.tsv'), 'w')
    idx.write('cluster\towner\tfile\tn_lazy\n')
    cid = 0
    cur = 0
    for _fam, members in by_fam.items():
        for owner, rel, n in members:
            if cur % args.size == 0:
                cid += 1
            idx.write(f'{cid:03d}\t{owner}\t{rel}\t{n}\n')
            cur += 1
    idx.close()
    print(f'[member-inventory] obf tokens: {len(obf)}; lazy member rows: {len(lazy)}; '
          f'owners: {len(files)}; clusters: {cid}')
    print(f'[member-inventory] -> {OUT}/members-obf.tsv, members-lazy.tsv, member-clusters.tsv')


if __name__ == '__main__':
    main()
