#!/usr/bin/env python3
"""Resolve broken net/minecraft references (from FixRefs scan) to the closest
existing class in target/classes or in the original Lunar jars.

Reads:  broken-refs.tsv  (owner<TAB>DESC|MIXIN<TAB>missing/internal-name)
Writes: fixrefs-map.tsv   (missing<TAB>replacement)
"""
import os, sys, zipfile, collections

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
REF = sys.argv[1] if len(sys.argv) > 1 else '/tmp/opencode/broken-refs.tsv'
OUT = sys.argv[2] if len(sys.argv) > 2 else '/tmp/opencode/fixrefs-map.tsv'

# existing classes
existing = set()
for root, dirs, files in os.walk(os.path.join(PROJECT, 'target/classes/net/minecraft')):
    for f in files:
        if f.endswith('.class'):
            existing.add(os.path.relpath(os.path.join(root, f), os.path.join(PROJECT, 'target/classes'))[:-6])

# candidate aliases by simple name
by_simple = collections.defaultdict(set)
for c in existing:
    by_simple[c.rsplit('/', 1)[-1].split('$')[0]].add(c)

# for double-obfuscated/lunar names, also index the ORIGINAL jars' names
orig_candidates = collections.defaultdict(set)
for jar in [os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                         'libs', 'multiver-full', 'lunar.jar')]:
    if not os.path.isfile(jar):
        continue
    z = zipfile.ZipFile(jar)
    for n in z.namelist():
        if n.endswith('.class') and n.startswith('net/minecraft'):
            orig_candidates[n[:-6].rsplit('/', 1)[-1].split('$')[0]].add(n[:-6])

missing = []
for line in open(REF, encoding='utf-8'):
    parts = line.rstrip('\n').split('\t')
    if len(parts) < 3:
        continue
    missing.append(parts[2])

out = {}
unresolved = []
for m in sorted(set(missing)):
    simple = m.rsplit('/', 1)[-1]
    base = simple.split('$')[0]
    # strip trailing version markers like _v1_7 / _v1_12
    stripped = base
    for marker in ('_v1_7', '_v1_8', '_v1_12'):
        if stripped.endswith(marker):
            stripped = stripped[:-len(marker)]
            break
    # try full path with markers stripped
    cands = []
    if stripped != base:
        stripped_path = m.rsplit('/', 1)[0] + '/' + stripped
        if stripped_path in existing:
            cands.append(stripped_path)
    for cand_simple in (base, stripped):
        # exact path with same package + simple
        same_pkg = m.rsplit('/', 1)[0] + '/' + cand_simple
        if same_pkg in existing:
            cands.append(same_pkg)
        for c in by_simple.get(cand_simple, ()):
            cands.append(c)
    if cands:
        out[m] = sorted(set(cands))[0]
    else:
        # try normalized simple-name match anywhere
        want = stripped.lower().replace('_', '')
        for c in existing:
            if c.rsplit('/', 1)[-1].lower().replace('_', '') == want.lower():
                out[m] = c
                break
        else:
            unresolved.append(m)

print(f'resolved {len(out)} of {len(set(missing))}', file=sys.stderr)
print('unresolved:', unresolved, file=sys.stderr)
with open(OUT, 'w', encoding='utf-8') as f:
    for k in sorted(out):
        f.write(f'{k}\t{out[k]}\n')
print('wrote', OUT, file=sys.stderr)
