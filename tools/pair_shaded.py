#!/usr/bin/env python3
"""Pair shaded source classes with their real-named library twin.

The old restructure flattened shaded third-party packages (Jackson, Guava,
Mixin, ...) into the tree and gave classes lazy names. A class can be
re-identified by its contents:

* string literals  — obfuscation cannot touch them
* member identifiers — Lunar's member tables kept many real names
  (`checkArgument`, `WRITE_DATES_AS_TIMESTAMPS`, ...) even in shaded code

Both fingerprints are IDF-weighted against the reference jar, so `getClass`
style tokens do not drown the signal. Exact simple-name matches get a bonus.

Usage:
  tools/pair_shaded.py --src <dir-or-glob> --jar <jar> [--jar ...] \
      [--out tools/renames/shaded-pairs.tsv] [--min-score 3]

Output: src | src-class | ref-fqn | score | name | shared-strings | shared-members
"""
import argparse
import glob as _glob
import math
import os
import re
import sys
import zipfile
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

ASCII_RUN = re.compile(rb'[\x20-\x7e]{8,}')
STR_LIT = re.compile(r'"((?:[^"\\]|\\.)*)"')
IDENT = re.compile(r'\b([A-Za-z_$][A-Za-z0-9_$]{2,})\b')
JUNK_STR = re.compile(r'^(\(|\)|L[a-zA-Z/]+;|\[+|<[^>]*>|[()VJ\[ZBCSIJFD]+)$')
LAZY_ID = re.compile(r'^(method|field|arg|var|value|number|flag|obj|text|it|this)\d+$')
KEYWORDS = {'class', 'interface', 'enum', 'record', 'return', 'super', 'this',
            'new', 'import', 'package', 'static', 'final', 'public', 'private',
            'protected', 'abstract', 'boolean', 'byte', 'char', 'short', 'int',
            'long', 'float', 'double', 'void', 'extends', 'implements',
            'throws', 'instanceof', 'catch', 'finally', 'try', 'null', 'true',
            'false', 'else', 'while', 'for', 'switch', 'case', 'default',
            'break', 'continue', 'synchronized', 'volatile', 'transient',
            'native', 'strictfp', 'assert', 'throw', 'do', 'if'}
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


def strings_from_source(text):
    out = set()
    for m in STR_LIT.finditer(text):
        s = unescape(m.group(1))
        if len(s) >= 10 and (' ' in s or ':' in s or '.' in s or '-' in s):
            out.add(s)
    return out


def strings_from_class(data):
    out = set()
    for raw in ASCII_RUN.findall(data):
        s = raw.decode('ascii', 'ignore')
        if s.startswith(('java/', 'Ljava/', '(', '[')) or JUNK_STR.match(s):
            continue
        if len(s) >= 10 and (' ' in s or ':' in s or '.' in s or '-' in s):
            out.add(s)
    return out


def idents_from_source(text):
    out = set()
    for m in IDENT.finditer(text):
        n = m.group(1)
        if n in KEYWORDS or LAZY_ID.match(n):
            continue
        out.add(n)
    return out


def idents_from_class(data):
    return set(IDENT.findall(data.decode('latin1')))


def load_jars(jar_paths, include_nested=True):
    """Return {fqn: (strings, idents)} and two IDF maps."""
    classes = {}
    for jp in jar_paths:
        with zipfile.ZipFile(jp) as z:
            for name in z.namelist():
                if not name.endswith('.class'):
                    continue
                if not include_nested and '$' in name:
                    continue
                fqn = name[:-6].replace('/', '.')
                data = z.read(name)
                classes[fqn] = (strings_from_class(data), idents_from_class(data))
    df_s, df_i = defaultdict(int), defaultdict(int)
    for strs, ids in classes.values():
        for s in strs:
            df_s[s] += 1
        for i in ids:
            df_i[i] += 1
    n = max(1, len(classes))
    idf_s = {s: math.log(1 + n / c) for s, c in df_s.items()}
    idf_i = {i: 0.5 * math.log(1 + n / c) for i, c in df_i.items()}
    return classes, idf_s, idf_i


def build_index(classes):
    inv_s, inv_i = defaultdict(list), defaultdict(list)
    for fqn, (strs, ids) in classes.items():
        for s in strs:
            inv_s[s].append(fqn)
        for i in ids:
            inv_i[i].append(fqn)
    return inv_s, inv_i


def match(name, strs, ids, classes, idf_s, idf_i, inv_s, inv_i):
    scores = defaultdict(float)
    shared_s, shared_i = defaultdict(set), defaultdict(set)
    for s in strs:
        w = idf_s.get(s)
        if w is None:
            continue
        for fqn in inv_s[s]:
            scores[fqn] += w
            shared_s[fqn].add(s)
    for i in ids:
        w = idf_i.get(i)
        if w is None:
            continue
        for fqn in inv_i[i]:
            scores[fqn] += w
            shared_i[fqn].add(i)
    out = []
    for fqn, score in scores.items():
        if fqn.split('.')[-1] == name:
            score += 5.0
        out.append((score, fqn, shared_s[fqn], shared_i[fqn]))
    out.sort(reverse=True, key=lambda t: t[0])
    return out


def declared_name(text):
    m = re.search(r'\b(?:class|interface|enum|record|@interface)\s+([A-Za-z_$][A-Za-z0-9_$]*)', text)
    return m.group(1) if m else None


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--src', action='append', required=True)
    ap.add_argument('--jar', action='append', required=True)
    ap.add_argument('--out', default=os.path.join(ROOT, 'tools/renames/shaded-pairs.tsv'))
    ap.add_argument('--min-score', type=float, default=3.0)
    ap.add_argument('--min-shared', type=int, default=1)
    ap.add_argument('--no-nested', action='store_true')
    args = ap.parse_args()

    targets = []
    for src in args.src:
        if any(ch in src for ch in '*?['):
            targets.extend(p for p in _glob.glob(src) if p.endswith('.java'))
        elif os.path.isfile(src):
            targets.append(src)
        else:
            for dirpath, _dirs, names in os.walk(src):
                for n in names:
                    if n.endswith('.java'):
                        targets.append(os.path.join(dirpath, n))
    print(f'[pair] {len(targets)} targets, loading {len(args.jar)} jars ...')
    classes, idf_s, idf_i = load_jars(args.jar, include_nested=not args.no_nested)
    inv_s, inv_i = build_index(classes)
    print(f'[pair] {len(classes)} reference classes')

    rows = []
    for t in sorted(targets):
        text = open(t, encoding='utf-8', errors='replace').read()
        name = declared_name(text) or os.path.basename(t)[:-5]
        strs, ids = strings_from_source(text), idents_from_source(text)
        if not strs and not ids:
            continue
        best = match(name, strs, ids, classes, idf_s, idf_i, inv_s, inv_i)
        if not best:
            continue
        score, fqn, ss, si = best[0]
        margin = score - (best[1][0] if len(best) > 1 else 0)
        if score < args.min_score or len(ss) + len(si) < args.min_shared:
            continue
        if len(best) > 1 and margin < 0.25 * score:
            continue
        rel = os.path.relpath(t, ROOT)
        rows.append((rel, name, fqn, f'{score:.2f}', f'{margin:.2f}',
                     str(len(ss)), str(len(si)),
                     '; '.join(sorted(ss)[:2] + sorted(si)[:3])[:200].replace('\t', ' ')))

    with open(args.out, 'w') as fh:
        fh.write('src\tsrc_class\tref_fqn\tscore\tmargin\tstrings\tmembers\tsample\n')
        for r in rows:
            fh.write('\t'.join(r) + '\n')
    print(f'[pair] {len(rows)} confident pairs -> {args.out}')


if __name__ == '__main__':
    sys.exit(main())
