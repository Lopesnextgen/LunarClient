#!/usr/bin/env python3
"""Rewrite decompiler-artifact `record X()` declarations into plain classes.

Classes in libs/lunar-renamed-classes.jar extend java.lang.Record (the member
remap mangled their superclass), so Vineflower emitted `record X()` for what
are plain immutable classes with explicit fields and non-canonical
constructors. The JVM does not enforce record source rules, so the jar loads,
but javac rejects the sources with "field declaration must be static".

Usage:
  tools/fix_record_decls.py FILE.java [FILE.java ...] [--apply]

Only record declarations with an empty component list are rewritten
(`(modifiers) record Name<T>()` -> `(modifiers) class Name<T>`); records with
real components are left alone.
"""
import argparse
import os
import re
import sys

RECORD = re.compile(r'\brecord(\s+[A-Za-z0-9_$]+(?:\s*<[^<>]*>)?)\s*\(\s*\)')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('files', nargs='+')
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()
    total = 0
    for path in args.files:
        text = open(path, encoding='utf-8', errors='replace').read()
        new_text, n = RECORD.subn(r'class\1', text)
        if n:
            total += n
            print(f'{path}: {n} record decl(s) -> class')
            if args.apply:
                open(path, 'w', encoding='utf-8').write(new_text)
    print(f'{total} declaration(s) {"rewritten" if args.apply else "found (check mode)"}')


if __name__ == '__main__':
    sys.exit(main())
