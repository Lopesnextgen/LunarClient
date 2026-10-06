#!/usr/bin/env python3
"""Match source classes against reference jars by string-literal fingerprint.

The old restructure flattened shaded third-party packages (Jackson, Mixin,
Guava, ...) into `com.moonsworth.lunar` and gave the classes lazy names
(`MixinHelper3_11`, `SerializableImpl_8`). Obfuscation cannot touch string
literals, so every class can be re-identified by matching its literals against
the real-named library jar.

Usage:
  tools/match_libs.py --src <dir-or-file> [--src ...] \
      --jar <jar> [--jar ...] [--min-score 2.5] [--out tools/renames/shaded-matches.tsv]

Output (TSV): src-relpath | old-simple | ref-jar-fqn | score | coverage | shared-strings
Run with --suggest to also print the destination package for a shaded tree:
  com.moonsworth.lunar.shaded.<ref-package>
"""
import argparse
import math
import os
import re
import sys
import zipfile
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

ASCII_RUN = re.compile(rb'[\x20-\x7e]{8,}')
STR_LIT = re.compile(r'"((?:[^"\\]|\\.)*)"')
JUNK = re.compile(r'^(\(|\)|L[a-zA-Z/]+;|\[+|.<|>\.|<init>|<clinit>|[()VJ\[ZBCSIJFD]+)$')
ESCAPES = {'n': '\n', 't': '\t', 'r': '\r', '"': '"', '\\': '\\', "'": "'",
           'b': '\b', 'f': '\f'}


def unescape(s):
    out, i = [], 0
    while i < len(s):
        c = s[i]
        if c == '\\' and i + 1 < len(s):
            n = s[i + 1]
            if n == 'u' and i + 5 < len(s):
                try:
                    out.append(chr(int(s[i + 2:i + 6], 16)))
                    i += 6
                    continue
                except ValueError:
                    pass
            out.append(ESCAPES.get(n, n))
            i += 2
            continue
        out.append(c)
        i += 1
    return ''.join(out)


LAZY_ID = re.compile(r'^(method|field|arg|var|value|number|flag|obj|text|it|this)\d+$')
IDENT = re.compile(r'\b([A-Za-z_$][A-Za-z0-9_$]{2,})\b')


def members_from_source(text):
    """Non-lazy identifiers: enum constants, fields, methods that survived."""
    out = set()
    for m in IDENT.finditer(text):
        n = m.group(1)
        if LAZY_ID.match(n) or n in ('class', 'interface', 'enum', 'record',
                                     'return', 'super', 'this', 'new', 'import',
                                     'package', 'static', 'final', 'public',
                                     'private', 'protected', 'abstract',
                                     'boolean', 'byte', 'char', 'short', 'int',
                                     'long', 'float', 'double', 'void', 'var'):
            continue
        if n.islower() or '_' in n or n.isupper() or len(n) > 4:
            out.add(n)
    return out


def members_from_class(data):
    """Identifier-like tokens from a class file's constant pool + code."""
    return {m.group(1) for m in IDENT.finditer(data.decode('latin1'))}


def strings_from_class(data):
    """Distinctive string literals from a class file."""
    out = set()
    for raw in ASCII_RUN.findall(data):
        s = raw.decode('ascii', 'ignore')
        if s.startswith(('java/', 'Ljava/', '(', '[')) or JUNK.match(s):
            continue
        # constant-pool strings in class files are NOT quoted; keep those with
        # a space or punctuation that marks human text
        if len(s) >= 10 and (' ' in s or ':' in s or '.' in s or '-' in s):
            out.add(s)
    return out


def strings_from_source(text):
    out = set()
    for m in STR_LIT.finditer(text):
        s = unescape(m.group(1))
        if len(s) >= 10 and (' ' in s or ':' in s or '.' in s or '-' in s):
            out.add(s)
    return out


def load_jars(jar_paths):
    """Return {fqn: set(strings)}, plus idf weights over jars."""
    classes = {}
    for jp in jar_paths:
        with zipfile.ZipFile(jp) as z:
            for name in z.namelist():
                if not name.endswith('.class') or '$' in name:
                    continue
                fqn = name[:-6].replace('/', '.')
                data = z.read(name)
                strs = strings_from_class(data)
                if strs:
                    classes[fqn] = strs
    df = defaultdict(int)
    for strs in classes.values():
        for s in strs:
            df[s] += 1
    n = max(1, len(classes))
    idf = {s: math.log(1 + n / c) for s, c in df.items()}
    return classes, idf


def build_index(classes):
    """Inverted index: literal -> classes containing it."""
    inv = defaultdict(list)
    for fqn, strs in classes.items():
        for s in strs:
            inv[s].append(fqn)
    return inv


def match(target_strings, classes, inv, idf):
    scores = defaultdict(float)
    shared_map = defaultdict(set)
    for s in target_strings:
        w = idf.get(s)
        if w is None:
            continue
        for fqn in inv[s]:
            scores[fqn] += w
            shared_map[fqn].add(s)
    best = []
    for fqn, score in scores.items():
        shared = shared_map[fqn]
        coverage = len(shared) / max(1, len(classes[fqn]))
        best.append((score, coverage, len(shared), fqn, shared))
    best.sort(reverse=True)
    return best


def declared_name(text):
    m = re.search(r'\b(?:class|interface|enum|record|@interface)\s+([A-Za-z_$][A-Za-z0-9_$]*)', text)
    return m.group(1) if m else None


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--src', action='append', required=True,
                    help='source dir or .java file (repeatable)')
    ap.add_argument('--jar', action='append', required=True,
                    help='reference jar (repeatable)')
    ap.add_argument('--min-score', type=float, default=2.5)
    ap.add_argument('--min-shared', type=int, default=2)
    ap.add_argument('--out', default=os.path.join(ROOT, 'tools/renames/shaded-matches.tsv'))
    ap.add_argument('--suggest', action='store_true')
    args = ap.parse_args()

    targets = []
    for src in args.src:
        if any(ch in src for ch in '*?['):
            import glob as _glob
            targets.extend(p for p in _glob.glob(src) if p.endswith('.java'))
        elif os.path.isfile(src):
            targets.append(src)
        else:
            for dirpath, _dirs, names in os.walk(src):
                for name in names:
                    if name.endswith('.java'):
                        targets.append(os.path.join(dirpath, name))
    print(f'[match] {len(targets)} target files, loading {len(args.jar)} jars ...')
    classes, idf = load_jars(args.jar)
    inv = build_index(classes)
    print(f'[match] {len(classes)} reference classes, {len(idf)} distinct strings')

    rows, matched = [], 0
    for t in sorted(targets):
        text = open(t, encoding='utf-8', errors='replace').read()
        ts = strings_from_source(text)
        if not ts:
            continue
        best = match(ts, classes, inv, idf)
        if not best:
            continue
        score, cov, nshared, fqn, shared = best[0]
        margin = score - (best[1][0] if len(best) > 1 else 0)
        if score < args.min_score or nshared < args.min_shared or margin < 0.5 * score:
            continue
        matched += 1
        old = declared_name(text) or os.path.basename(t)[:-5]
        rel = os.path.relpath(t, ROOT)
        sample = '; '.join(sorted(shared)[:3])[:220].replace('\t', ' ')
        rows.append((rel, old, fqn, f'{score:.2f}', f'{cov:.2f}', str(nshared), sample))

    with open(args.out, 'w') as fh:
        fh.write('src\told\tfqn\tscore\tcov\tshared\tsample\n')
        for r in rows:
            fh.write('\t'.join(r) + '\n')
    print(f'[match] {matched} confident matches -> {args.out}')
    if args.suggest:
        for rel, old, fqn, score, cov, nshared, _s in rows[:40]:
            pkg = '.'.join(fqn.split('.')[:-1])
            print(f'  {old:35s} -> com.moonsworth.lunar.shaded.{pkg}.{fqn.split(".")[-1]}  ({score})')


if __name__ == '__main__':
    sys.exit(main())
