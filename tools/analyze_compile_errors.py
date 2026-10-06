#!/usr/bin/env python3
"""Summarise javac/Maven compile errors from a build log.

Usage: analyze_compile_errors.py <maven.log> [limit]
"""
import collections
import os
import re
import sys

PAT = re.compile(r'^\[ERROR\] (?P<file>.+?\.java):\[(?P<line>\d+),(?P<col>\d+)\] (?P<msg>.*)$')


def main():
    path = sys.argv[1]
    limit = int(sys.argv[2]) if len(sys.argv) > 2 else 40
    errors = collections.Counter()
    by_file = collections.Counter()
    by_pkg = collections.Counter()
    missing = collections.Counter()
    rows = []
    with open(path, encoding='utf-8', errors='replace') as f:
        for line in f:
            m = PAT.match(line.rstrip('\n'))
            if not m:
                continue
            msg = m.group('msg')
            msg_norm = re.sub(r"'[^']*'", "'X'", msg)
            errors[msg_norm] += 1
            by_file[m.group('file')] += 1
            rel = m.group('file').split('src/main/java/')[-1]
            pkg = '/'.join(rel.split('/')[:-1])
            by_pkg[pkg] += 1
            rows.append((m.group('file'), m.group('line'), msg))
            for sym in re.findall(r'symbol:\s+(?:class|interface|variable|method)\s+(\S+)', msg):
                missing[sym] += 1
    print(f'total errors: {len(rows)}')
    print('\n== error kinds ==')
    for msg, n in errors.most_common(limit):
        print(f'  {n:6d}  {msg}')
    print('\n== top packages ==')
    for pkg, n in by_pkg.most_common(limit):
        print(f'  {n:6d}  {pkg}')
    print('\n== top files ==')
    for f, n in by_file.most_common(limit):
        print(f'  {n:6d}  {f}')
    print('\n== most common missing symbols ==')
    for sym, n in missing.most_common(limit):
        print(f'  {n:6d}  {sym}')


if __name__ == '__main__':
    main()
