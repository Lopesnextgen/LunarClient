#!/usr/bin/env python3
"""Inventory of lazy member names in the source tree.

Counts method/field/parameter placeholder tokens per class so member renaming
can be split into subagent clusters (one class or feature at a time).

Usage: tools/member_inventory.py [--out tools/renames/members.tsv]
"""
import argparse
import os
import re
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')

CLASS_RE = re.compile(r'^\s*(?:public\s+|final\s+|abstract\s+)*(class|interface|enum|@interface|record)\s+([A-Za-z_$][A-Za-z0-9_$]*)', re.M)
# placeholder member tokens the old pipeline produced
MEMBER_RE = re.compile(r'\b(method|field|arg|number|text|string|obj|object|var|value|flag|bl|n|n2|n3|i|j|k)\d+\b')


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--out', default=os.path.join(ROOT, 'tools/renames/members.tsv'))
    args = parser.parse_args()

    rows = []
    total_tokens = 0
    per_token = defaultdict(int)
    for dirpath, _dirs, files in os.walk(SRC):
        for name in files:
            if not name.endswith('.java'):
                continue
            path = os.path.join(dirpath, name)
            rel = os.path.relpath(path, SRC)
            try:
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            match = CLASS_RE.search(text)
            cls = match.group(2) if match else name[:-5]
            tokens = MEMBER_RE.findall(text)
            if not tokens:
                continue
            total_tokens += len(tokens)
            for token in tokens:
                per_token[token] += 1
            rows.append((len(tokens), rel, cls, ','.join(sorted(set(tokens)))))

    rows.sort(reverse=True)
    os.makedirs(os.path.dirname(args.out), exist_ok=True)
    with open(args.out, 'w', encoding='utf-8') as out:
        out.write('count\tfile\tclass\ttokens\n')
        for count, rel, cls, tokens in rows:
            out.write(f'{count}\t{rel}\t{cls}\t{tokens}\n')
    print(f'[members] {len(rows)} files, {total_tokens} placeholder member tokens')
    print(f'[members] wrote {args.out}')
    print('[members] top tokens:',
          ', '.join(f'{k}={v}' for k, v in sorted(per_token.items(), key=lambda kv: -kv[1])[:12]))


if __name__ == '__main__':
    main()
