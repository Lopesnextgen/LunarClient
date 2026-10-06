#!/usr/bin/env python3
"""Repair stale annotation element names in rescued sources.

The old member-rename pass renamed annotation members (`@interface Annotation4
{ Type method1(); String method2(); boolean method3(); }`) but left the element
names in annotation usages (`@Annotation4(OOHROIO... = Type.MOD_INFO, ...)`).
This resolves each element name to the annotation's declared member by the
value's type and rewrites the usage.

Usage: tools/fix_annotation_members.py FILE.java [FILE.java ...] [--apply]
"""
import argparse
import os
import re
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from fix_dangling_members import JarIndex  # noqa: E402

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
JAR = os.path.join(ROOT, 'libs/lunar-renamed-classes.jar')

TOKEN = re.compile(r'^[A-Z][A-Z0-9_$]{10,}$')
IMPORT = re.compile(r'^\s*import\s+(?:static\s+)?([\w.$]+)\s*;', re.M)
ANNOTATION = re.compile(r'@([A-Za-z_$][\w$]*)\s*\(')
PAIR = re.compile(r'([A-Za-z_$][\w$]*)\s*=\s*')


def descriptor_of(value):
    v = value.strip()
    if v.startswith('"'):
        return 'Ljava/lang/String;'
    if v in ('true', 'false'):
        return 'Z'
    if re.match(r'^[A-Za-z_$][\w$]*\.class$', v):
        return 'Ljava/lang/Class;'
    if re.match(r'^[A-Za-z_$][\w$]*\.[A-Za-z_$][\w$]*$', v):
        return 'LENUM;'
    if re.match(r'^-?\d+[lL]$', v):
        return 'J'
    if re.match(r'^-?\d+[fF]$', v):
        return 'F'
    if re.match(r'^-?\d+(\.\d+)?[dD]?$', v):
        return 'D' if '.' in v else 'I'
    if v.startswith('{'):
        return '['
    return None


def match_member(members, desc):
    if desc is None:
        return None
    cands = []
    for name, d in members.items():
        if not d or TOKEN.match(name):
            continue
        if desc == 'LENUM;':
            if d.startswith('L') and d.count(';') == 1 and 'String' not in d and 'Class' not in d:
                cands.append(name)
        elif desc == '[':
            if d.startswith('['):
                cands.append(name)
        elif d == desc:
            cands.append(name)
    return cands[0] if len(cands) == 1 else None


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('files', nargs='+')
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()

    jar = JarIndex(JAR)
    total = 0
    for path in args.files:
        text = open(path, encoding='utf-8', errors='replace').read()
        imports = {m.group(1).rsplit('.', 1)[-1]: m.group(1) for m in IMPORT.finditer(text)}
        pm = re.search(r'^\s*package\s+([\w.]+)\s*;', text, re.M)
        package = pm.group(1) if pm else ''
        edits = []  # (start, end, new_body)
        for m in ANNOTATION.finditer(text):
            ann = m.group(1)
            fqn = imports.get(ann) or (package + '.' + ann if package else ann)
            internal = fqn.replace('.', '/')
            members = jar.members(internal)
            if not members:
                continue
            i = m.end()
            depth = 1
            while i < len(text) and depth:
                if text[i] == '(':
                    depth += 1
                elif text[i] == ')':
                    depth -= 1
                i += 1
            body = text[m.end():i - 1]
            new_body = body
            fixed = 0
            for seg in re.split(r',\s*(?=[A-Za-z_$][\w$]*\s*=)', body):
                pair = PAIR.match(seg.strip())
                if not pair:
                    continue
                name = pair.group(1)
                if not TOKEN.match(name):
                    continue
                value = seg.strip()[pair.end():]
                target = match_member(members, descriptor_of(value))
                if target:
                    new_body = re.sub(r'(?<![A-Za-z0-9_$.])' + re.escape(name) + r'(?![A-Za-z0-9_$])',
                                      target, new_body, count=1)
                    fixed += 1
            if fixed:
                edits.append((m.end(), i - 1, new_body))
                total += fixed
        if edits:
            for start, end, body in reversed(edits):
                text = text[:start] + body + text[end:]
            print(f'{path}: {len(edits)} annotation usages fixed')
            if args.apply:
                open(path, 'w', encoding='utf-8').write(text)
    print(f'total fixed: {total} in {"APPLY" if args.apply else "check"} mode')


if __name__ == '__main__':
    main()
