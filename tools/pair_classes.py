#!/usr/bin/env python3
"""Pair jar classes with their (possibly renamed/relocated) source files.

Class renames made path-based pairing impossible. Obfuscation does not touch
string constants, so classes are matched by their distinctive string literals
(sentences, resource paths, json keys). Output: source-fqn<TAB>jar-fqn.

Usage: tools/pair_classes.py [--jar ...] [--src ...] [--out ...]
"""
import argparse
import os
import re
import struct
import sys
import zipfile
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
JAR = os.path.join(ROOT, 'libs/lunar-renamed-classes.jar')

UTF8_CP = re.compile(rb'[\x20-\x7e]{8,}')
STR_LIT = re.compile(r'"((?:[^"\\]|\\.){8,})"')
KEEP = re.compile(r'[ /\\:]')
JUNK = re.compile(r'^[\x00-\x1f]*$|^\(|\)V$|^Ljava|^Lcom/moonsworth$|^\$')


def keep(lit):
    if not lit or len(lit) < 10 or JUNK.search(lit):
        return False
    if not KEEP.search(lit):
        return False
    return True


def jar_strings(path):
    """Extract candidate string literals from a class file's constant pool."""
    data = open(path, 'rb').read()
    if len(data) < 10 or data[:4] != b'\xca\xfe\xba\xbe':
        return set()
    cp_count = struct.unpack_from('>H', data, 8)[0]
    off = 10
    out = set()
    i = 1
    while i < cp_count:
        tag = data[off]
        off += 1
        if tag == 1:  # Utf8
            ln = struct.unpack_from('>H', data, off)[0]
            off += 2
            raw = data[off:off + ln]
            off += ln
            try:
                text = raw.decode('utf-8')
            except UnicodeDecodeError:
                continue
            if keep(text):
                out.add(text)
        elif tag in (3, 4):
            off += 4
        elif tag in (5, 6):
            off += 8
            i += 1
        elif tag in (7, 8, 16, 19, 20):
            off += 2
        elif tag in (9, 10, 11, 12, 17, 18):
            off += 4
        elif tag == 15:
            off += 3
        else:
            # unknown tag: give up on this class
            return set()
        i += 1
    return out


def source_strings(text):
    out = set()
    for m in STR_LIT.finditer(text):
        lit = m.group(1)
        lit = lit.replace('\\n', '\n').replace('\\t', '\t').replace('\\"', '"').replace('\\\\', '\\')
        if keep(lit):
            out.add(lit)
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--jar', default=JAR)
    ap.add_argument('--src', default=SRC)
    ap.add_argument('--out', default='/tmp/opencode/jar-source-pairs.tsv')
    args = ap.parse_args()

    # jar side
    z = zipfile.ZipFile(args.jar)
    jar_lits = {}
    tmp = '/tmp/opencode/pair-classes'
    os.makedirs(tmp, exist_ok=True)
    for name in z.namelist():
        if not name.startswith('com/moonsworth/') or not name.endswith('.class') or '$' in name:
            continue
        p = os.path.join(tmp, name.replace('/', '_'))
        open(p, 'wb').write(z.read(name))
        lits = jar_strings(p)
        if lits:
            jar_lits[name[:-6]] = lits
    print(f'[pair] jar classes with distinctive literals: {len(jar_lits)}', file=sys.stderr)

    # source side
    src_lits = {}
    for dp, _dirs, fs in os.walk(args.src):
        for f in fs:
            if not f.endswith('.java'):
                continue
            path = os.path.join(dp, f)
            rel = os.path.relpath(path, args.src)[:-5].replace(os.sep, '/')
            lits = source_strings(open(path, encoding='utf-8', errors='replace').read())
            if lits:
                src_lits[rel] = lits
    print(f'[pair] source files with distinctive literals: {len(src_lits)}', file=sys.stderr)

    df = defaultdict(int)
    for lits in jar_lits.values():
        for lit in lits:
            df[lit] += 1

    def score(a, b):
        shared = a & b
        return sum(1.0 / df[lit] for lit in shared)

    pairs = defaultdict(list)
    for sl, slits in src_lits.items():
        best = []
        for jl, jlits in jar_lits.items():
            if not slits & jlits:
                continue
            best.append((score(slits, jlits), jl))
        if not best:
            continue
        best.sort(reverse=True)
        if len(best) > 1 and best[1][0] >= best[0][0] * 0.85:
            continue  # ambiguous
        pairs[sl] = best[0][1]

    written = 0
    with open(args.out, 'w', encoding='utf-8') as out:
        for sl, jl in sorted(pairs.items()):
            if sl.replace('/', '.') == jl.replace('/', '.'):
                continue
            out.write(f'{sl.replace("/", ".")}\t{jl.replace("/", ".")}\n')
            written += 1
    print(f'[pair] wrote {written} non-identity pairs to {args.out}')


if __name__ == '__main__':
    main()
