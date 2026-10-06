#!/usr/bin/env python3
"""Find duplicate class files in the source tree.

Two classes are "duplicates" when their bodies are identical after
normalising the package declaration, the declared type name, and comments.
Catches both accidental byte-for-byte twins (same class copied into two
packages) and same-body/different-name twins left by the deobf pipeline.

Usage:
  tools/find_duplicates.py [--min-lines N] [--json]

Output: one block per group, most-duplicated first.
"""
import argparse
import hashlib
import json
import os
import re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')

PKG_RE = re.compile(r'^\s*package\s+[\w.]+\s*;', re.M)
IMPORT_RE = re.compile(r'^\s*import\s+.*?;\s*$', re.M)
DECL_RE = re.compile(r'\b(class|interface|enum|record)\s+([A-Za-z_$][A-Za-z0-9_$]*)')
BLOCK_COMMENT_RE = re.compile(r'/\*.*?\*/', re.S)
LINE_COMMENT_RE = re.compile(r'//[^\n]*')
WS_RE = re.compile(r'\s+')

# strip package/imports/comments and blank the declared type name so a
# same-body/same-name file in another package hashes the same
def normalize(text):
    text = BLOCK_COMMENT_RE.sub('', text)
    text = LINE_COMMENT_RE.sub('', text)
    text = PKG_RE.sub('', text)
    text = IMPORT_RE.sub('', text)

    def blank(m):
        return m.group(1) + ' CLASS'

    text = DECL_RE.sub(blank, text)
    return WS_RE.sub(' ', text).strip()


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--min-lines', type=int, default=5,
                    help='ignore files shorter than this (default 5)')
    ap.add_argument('--json', action='store_true')
    args = ap.parse_args()

    groups = {}
    for dirpath, _dirs, names in os.walk(SRC):
        for name in names:
            if not name.endswith('.java'):
                continue
            path = os.path.join(dirpath, name)
            rel = os.path.relpath(path, SRC)
            text = open(path, encoding='utf-8', errors='replace').read()
            if text.count('\n') + 1 < args.min_lines:
                continue
            digest = hashlib.sha1(normalize(text).encode()).hexdigest()
            groups.setdefault(digest, []).append(rel)

    dupes = sorted((v for v in groups.values() if len(v) > 1),
                   key=lambda v: (-len(v), v[0]))
    if args.json:
        print(json.dumps(dupes, indent=2))
        return
    total = sum(len(g) for g in dupes)
    print(f'[duplicates] {len(dupes)} groups, {total} files')
    for group in dupes:
        print(f'--- {len(group)} copies')
        for rel in sorted(group):
            print(f'    {rel}')


if __name__ == '__main__':
    main()
