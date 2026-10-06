#!/usr/bin/env python3
"""Repair prefix-clobbered identifiers left by the old member applier.

The pre-2026-09-16 applier rewrote accesses with `text.replace(match, new)`.
Because `oldShort` can be a prefix of `oldLong` (`method2` / `method20`), the
replacement leaked into the longer identifier:

    this.method20()  ->  this.getConfig0()      (method2 -> getConfig)

This tool reverses the damage. For every map row `oldShort -> newShort`, any
token `newShort + digits` in the tree is examined:

* if the map also maps `oldShort + digits` (oldLong), the token becomes the
  mapped target of oldLong;
* otherwise the intended token was the unmapped lazy `oldLong`, so it is
  restored verbatim.

Tokens that are themselves map targets, or declared anywhere in the tree, are
left alone. Ambiguous cases (several short stems matching) are reported and
skipped unless --force.

Usage:
  tools/repair_prefix_clobber.py --map <map> [--map ...] [--apply] [--force]
"""
import argparse
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
IDENT = re.compile(r'[A-Za-z_$][\w$]*')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', action='append', required=True)
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--force', action='store_true')
    args = ap.parse_args()

    old_to_new, new_to_olds = {}, {}
    for p in args.map:
        for line in open(p, encoding='utf-8'):
            line = line.rstrip('\n')
            if not line or line.startswith('#'):
                continue
            parts = line.split('\t')
            if len(parts) < 4:
                continue
            old, new = parts[2], parts[3]
            old_to_new.setdefault(old, set()).add(new)
            new_to_olds.setdefault(new, set()).add(old)

    new_names = set(new_to_olds)

    files = {}
    declared = set()
    for dp, _dirs, fs in os.walk(SRC):
        for f in fs:
            if not f.endswith('.java'):
                continue
            p = os.path.join(dp, f)
            text = open(p, errors='ignore').read()
            files[p] = text
            declared.update(re.findall(r'\b(?:class|interface|enum|record)\s+(\w+)', text))

    # candidate tokens: newShort + digits, not a map target, not a class name
    cand = {}
    for p, text in files.items():
        for m in re.finditer(r'(?<![\w$.])([A-Za-z_$][\w$]*?)(\d+)(?![\w$])', text):
            stem, suffix, tok = m.group(1), m.group(2), m.group(0)[1:-len(m.group(2))]
            tok = m.group(1) + m.group(2)
            if stem not in new_to_olds:
                continue
            if tok in new_names or tok in declared:
                continue
            targets = set()
            for old_short in new_to_olds[stem]:
                old_long = old_short + suffix
                if old_long in old_to_new:
                    if len(old_to_new[old_long]) == 1:
                        targets.add(next(iter(old_to_new[old_long])))
                else:
                    targets.add(old_long)
            if len(targets) == 1:
                cand.setdefault(tok, set()).add(next(iter(targets)))
            elif targets:
                cand.setdefault(tok, set()).update(targets)

    repairs = []
    ambiguous = 0
    for tok, targets in sorted(cand.items()):
        if len(targets) == 1 and (args.force or next(iter(targets)) != tok):
            repairs.append((tok, next(iter(targets))))
        else:
            ambiguous += 1
    print(f'[prefix-repair] candidate tokens: {len(cand)}; repairs: {len(repairs)}; '
          f'ambiguous/self: {ambiguous}')
    for tok, target in repairs[:15]:
        print(f'   {tok} -> {target}')

    fixed = files_hit = 0
    for p, text in files.items():
        orig = text
        for tok, target in repairs:
            rx = re.compile(r'(?<![\w$.])' + re.escape(tok) + r'(?![\w$])')
            text, k = rx.subn(target, text)
            fixed += k
        if text != orig:
            files_hit += 1
            if args.apply:
                with open(p, 'w') as fh:
                    fh.write(text)
    print(f'[prefix-repair] {fixed} tokens in {files_hit} files; '
          f'mode={"APPLY" if args.apply else "dry-run"}')


if __name__ == '__main__':
    sys.exit(main())
