#!/usr/bin/env python3
"""Pair jar classes with renamed source files by method-descriptor fingerprints.

Renaming changes names, not descriptors: the multiset of argument/return shapes
of a class's methods survives class and member renames. Source-side descriptors
are approximated (simple type names, generics stripped) and normalised against
the jar's JVM descriptors ($ -> . for nested types, FQN -> simple name).

Usage: tools/pair_descriptors.py [--jar ...] [--src ...] [--out ...]
"""
import argparse
import os
import re
import struct
import sys
import zipfile
from collections import Counter, defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
JAR = os.path.join(ROOT, 'libs/lunar-renamed-classes.jar')

METHOD_CP = re.compile(r'\b([A-Za-z_$][\w$]*)\s*\(')
SRC_METHOD = re.compile(
    r'(?:public|private|protected|static|final|abstract|default|synchronized|native|\s)+'  # modifiers
    r'([\w<>\[\],.?\s$]+?)\s+(\w+)\s*\(([^)]*)\)', re.S)

PRIM = {'void': 'V', 'boolean': 'Z', 'byte': 'B', 'char': 'C', 'short': 'S',
        'int': 'I', 'long': 'J', 'float': 'F', 'double': 'D'}


def norm(t):
    """source type -> rough descriptor token"""
    t = re.sub(r'<[^>]*>', '', t).strip()
    t = t.replace('...', '[]').replace('?', '')
    dims = t.count('[]')
    t = t.replace('[]', '').strip()
    base = PRIM.get(t)
    if base is None:
        base = 'L' + t.split('.')[-1]
    return base + '[' * dims


def src_fingerprint(text):
    fps = Counter()
    for m in SRC_METHOD.finditer(text):
        ret, name, params = m.group(1), m.group(2), m.group(3)
        if ret.strip() in ('return', 'new', 'class', 'interface', 'enum'):
            continue
        parts = []
        for p in params.split(','):
            p = p.strip()
            if not p:
                continue
            p = re.sub(r'^(?:final\s+)+', '', p)
            if ' ' in p:
                p = p.split(' ', 1)[0]
            parts.append(norm(p))
        fps[norm(ret.strip()) + '(' + ','.join(parts) + ')'] += 1
    return fps


def jar_fingerprint(data):
    cp_count = struct.unpack_from('>H', data, 8)[0]
    off = 10
    cp = [None] * cp_count
    i = 1
    while i < cp_count:
        tag = data[off]
        off += 1
        if tag == 1:
            ln = struct.unpack_from('>H', data, off)[0]
            off += 2
            cp[i] = data[off:off + ln].decode('utf-8', 'replace')
            off += ln
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
            return None
        i += 1
    off += 6
    ifaces = struct.unpack_from('>H', data, off)[0]
    off += 2 + 2 * ifaces
    fps = Counter()
    off += 2  # field count
    # skip fields
    count = struct.unpack_from('>H', data, off - 2)[0]
    for _ in range(count):
        _f, _n, _d, attrs = struct.unpack_from('>HHHH', data, off)
        off += 8
        for _a in range(attrs):
            _ai, ln = struct.unpack_from('>HI', data, off)
            off += 6 + ln
    methods = struct.unpack_from('>H', data, off)[0]
    off += 2
    for _ in range(methods):
        _f, _n, d, attrs = struct.unpack_from('>HHHH', data, off)
        off += 8
        desc = cp[d] if d and d < len(cp) else ''
        for _a in range(attrs):
            _ai, ln = struct.unpack_from('>HI', data, off)
            off += 6 + ln
        if not desc:
            continue
        ret, _, args = desc.partition(')')
        ret = ret.lstrip('(')
        def conv(t):
            t = t.replace('/', '.')
            arr = ''
            while t.endswith('['):
                arr += '[]'
                t = t[:-1]
            if t.startswith('L'):
                t = t[1:].replace('$', '.')
            return t + arr
        fps[conv(ret) + '(' + ','.join(conv(p) for p in re.findall(r'\[*(?:[VZBCSIJFD]|L[^;]+;)', args)) + ')'] += 1
    return fps


def similarity(a, b):
    inter = sum((a & b).values())
    total = sum(a.values()) + sum(b.values())
    return 2.0 * inter / total if total else 0.0


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--jar', default=JAR)
    ap.add_argument('--src', default=SRC)
    ap.add_argument('--out', default='/tmp/opencode/jar-source-pairs.tsv')
    args = ap.parse_args()

    z = zipfile.ZipFile(args.jar)
    jar = {}
    for n in z.namelist():
        if n.startswith('com/moonsworth/') and n.endswith('.class') and '$' not in n:
            fp = jar_fingerprint(z.read(n))
            if fp:
                jar[n[:-6]] = fp
    src = {}
    for dp, _dirs, fs in os.walk(args.src):
        for f in fs:
            if not f.endswith('.java'):
                continue
            rel = os.path.relpath(os.path.join(dp, f), args.src)[:-5].replace(os.sep, '/')
            src[rel] = src_fingerprint(open(os.path.join(dp, f), encoding='utf-8', errors='replace').read())
    print(f'[pair-desc] jar {len(jar)} classes, src {len(src)} files', file=sys.stderr)

    # bucket by fingerprint size to keep it tractable
    by_size = defaultdict(list)
    for jn, jfp in jar.items():
        by_size[sum(jfp.values())].append(jn)

    pairs = {}
    used = set()
    for sn, sfp in src.items():
        n = sum(sfp.values())
        best = []
        for size in range(max(0, n - 3), n + 4):
            for jn in by_size.get(size, ()):
                s = similarity(sfp, jar[jn])
                if s >= 0.55:
                    best.append((s, jn))
        best.sort(reverse=True)
        if not best:
            continue
        if len(best) > 1 and best[1][0] >= best[0][0] - 0.002:
            continue
        if best[0][0] >= 0.75:
            pairs[sn] = best[0][1]
    with open(args.out, 'w', encoding='utf-8') as out:
        n = 0
        for sn, jn in sorted(pairs.items()):
            if sn == jn:
                continue
            out.write(f'{sn.replace("/", ".")}\t{jn.replace("/", ".")}\n')
            n += 1
    print(f'[pair-desc] wrote {n} non-identity pairs to {args.out}')


if __name__ == '__main__':
    main()
