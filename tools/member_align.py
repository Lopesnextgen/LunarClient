#!/usr/bin/env python3
"""Generate supplementary member-rename rows for hierarchy-alignment breaks.

Applying member maps can leave a hierarchy inconsistent when the map renamed
an abstract/base member but not every implementor, or when an access site's
receiver could not be resolved. ECJ reports the survivors; this tool turns
those errors into extra map rows for `apply_member_renames.py`:

* "The type Impl must implement the inherited abstract method Base.newName()"
    -> old = reverse map lookup (Base: newName -> old); if Impl declares `old`,
       emit Impl M old newName.
* "The method old() is undefined for the type Receiver"
    -> if Receiver has a map row old -> new, emit Receiver M old new to force
       the access rewrite.

Usage:
  tools/member_align.py --map <applied-map> [--map ...] [--out tools/renames/wave5/members-align.tsv]
"""
import argparse
import os
import re
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import apply_member_renames as amr  # noqa: E402

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
LOG = os.path.join(os.environ.get('QA_WORK', '/tmp/opencode'), 'ecj.log')
MUST = re.compile(r'The type (\w+) must implement the inherited abstract method ([\w.$]+)\.(\w+)\(')
UNDEF = re.compile(r'The method (\w+)\([^)]*\) (?:is undefined for the type|in the type) ([\w.$]+)')
NOTAPP = re.compile(r'The method ([\w$]+)\([^)]*\) in the type ([\w.$<>, ]+) is not applicable')


def read_maps(paths):
    rows = []
    for p in paths:
        for line in open(p, encoding='utf-8'):
            line = line.rstrip('\n')
            if not line or line.startswith('#'):
                continue
            parts = line.split('\t')
            if len(parts) < 4:
                continue
            rows.append({'owner': parts[0], 'kind': parts[1], 'old': parts[2],
                         'new': parts[3]})
    return rows


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', action='append', required=True)
    ap.add_argument('--out', default=os.path.join(ROOT, 'tools/renames/wave5/members-align.tsv'))
    args = ap.parse_args()

    rows = read_maps(args.map)
    res = amr.MemResolver(args.map)
    reverse = {}  # owner -> {new: old}
    for r in rows:
        reverse.setdefault(r['owner'], {}).setdefault(r['new'], r['old'])

    text = open(LOG, errors='ignore').read()
    out_rows = []
    seen = set()

    # declared method names per file (cheap scan)
    decls = {}
    for dp, _dirs, fs in os.walk(SRC):
        for f in fs:
            if not f.endswith('.java'):
                continue
            p = os.path.join(dp, f)
            t = open(p, errors='ignore').read()
            decls[os.path.relpath(p, SRC)] = set(
                re.findall(r'\b([A-Za-z_$][\w$]*)\s*\(', t))

    for m in MUST.finditer(text):
        impl, base, new = m.group(1), m.group(2), m.group(3)
        # ECJ prints nested owners with '.', maps use '$'; also the contract
        # may live on the outer class (GuiIterator.Extension -> GuiIterator)
        candidates = [base, base.replace('.', '$')]
        parts = base.split('.')
        for i in range(len(parts) - 1, 0, -1):
            candidates.append('.'.join(parts[:i]))
            candidates.append('$'.join(parts[:i]))
        old = None
        for cand in candidates:
            # ECJ prints simple/nested names; match map owners by suffix
            hits = [o for o in reverse
                    if o == cand or o.endswith('.' + cand) or o.endswith('$' + cand)]
            for o in hits:
                old = reverse[o].get(new)
                if old:
                    break
            if old:
                break
        if not old:
            continue
        base_simples = {p for p in base.replace('$', '.').split('.')}
        # locate the implementor by simple name
        for rel, names in decls.items():
            if os.path.basename(rel)[:-5] != impl:
                continue
            if old not in names:
                continue
            header = open(os.path.join(SRC, rel), errors='ignore').read(4096)
            if not any(s in header for s in base_simples if len(s) > 3):
                continue
            fqn = rel[:-5].replace('/', '.')
            key = (fqn, old, new)
            if key in seen:
                continue
            seen.add(key)
            out_rows.append((fqn, 'M', old, new,
                             f'must-implement alignment: {base}.{new} renamed from {old}'))
    for m in UNDEF.finditer(text):
        old, recv = m.group(1), m.group(2).split('<')[0].strip()
        # receiver may be simple or FQN
        owner = None
        for o in reverse:
            if o == recv or o.endswith('.' + recv):
                owner = o
                break
        if not owner:
            continue
        new = None
        for o, mp in reverse.items():
            if o == owner and old in {v for v in mp.values()}:
                pass
        for r in rows:
            if r['owner'] == owner and r['old'] == old:
                new = r['new']
                break
        if not new:
            continue
        key = (owner, old, new)
        if key in seen:
            continue
        seen.add(key)
        out_rows.append((owner, 'M', old, new,
                         f'access alignment: {old} renamed but a receiver on {recv} was not rewritten'))
    for m in NOTAPP.finditer(text):
        old, recv = m.group(1), m.group(2).split('<')[0].strip()
        owner = None
        for o in reverse:
            if o == recv or o.endswith('.' + recv):
                owner = o
                break
        if not owner:
            continue
        new = next((r['new'] for r in rows if r['owner'] == owner and r['old'] == old), None)
        if not new:
            continue
        key = (owner, old, new)
        if key in seen:
            continue
        seen.add(key)
        out_rows.append((owner, 'M', old, new,
                         f'overload alignment: {old} on {recv} renamed to {new}'))

    with open(args.out, 'w') as fh:
        fh.write('# owner.fqn\tM|F\told\tnew\tevidence\n')
        for r in out_rows:
            fh.write('\t'.join(r) + '\n')
    print(f'[member-align] {len(out_rows)} supplementary rows -> {args.out}')


if __name__ == '__main__':
    sys.exit(main())
