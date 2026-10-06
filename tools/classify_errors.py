#!/usr/bin/env python3
"""Classify raw javac errors per source file.

Usage: classify_errors.py <javac.log> <project_root> > per-file.tsv

Categories (first matching wins for `category`):
  syntax     - parser errors (';' expected, illegal start, etc.)
  package    - "package X does not exist"
  symbol     - "cannot find symbol"
  visibility - not public/private/protected access
  duplicate  - duplicate class / already defined
  other      - anything else

Also emits `symbols` = distinct missing simple names, and `pkgs` = distinct
missing packages.
"""
import collections
import os
import re
import sys

ERR = re.compile(r'^(?P<file>/[^:]+\.java):(?P<line>\d+): error: (?P<msg>.*)$')
SYNTAX_HINTS = ("expected", "illegal start", "not a statement", "reached end of file",
                "unclosed", "malformed")


def main():
    log, root = sys.argv[1], sys.argv[2]
    root = os.path.abspath(root) + '/'
    files = collections.OrderedDict()
    cur = None
    with open(log, encoding='utf-8', errors='replace') as fh:
        for line in fh:
            m = ERR.match(line)
            if m:
                cur = files.setdefault(m.group('file'), {'msgs': [], 'symbols': set(), 'pkgs': set()})
                cur['msgs'].append(m.group('msg'))
                continue
            if cur is None:
                continue
            s = line.strip()
            if s.startswith('symbol:'):
                cur['symbols'].add(s.split('symbol:')[-1].strip())
            elif s.startswith('location:'):
                pass
            elif s.startswith('package ') and 'does not exist' in s:
                cur['pkgs'].add(s.split()[1])
    print('file\tcategory\tn\tpkgs\tsymbols\tfirst')
    for path, d in files.items():
        msgs = d['msgs']
        first = msgs[0]
        if any(h in first for h in SYNTAX_HINTS) or re.search(r"case, default", first):
            cat = 'syntax'
        elif 'does not exist' in first and first.startswith('package '):
            cat = 'package'
        elif 'cannot find symbol' in first:
            cat = 'symbol'
        elif 'duplicate class' in first or 'already defined' in first:
            cat = 'duplicate'
        elif 'is not public' in first or ' has private access' in first or 'protected access' in first:
            cat = 'visibility'
        else:
            cat = 'other'
        rel = path[len(root):] if path.startswith(root) else path
        print(f"{rel}\t{cat}\t{len(msgs)}\t{','.join(sorted(d['pkgs']))}\t{','.join(sorted(d['symbols']))}\t{first[:120]}")


if __name__ == '__main__':
    main()
