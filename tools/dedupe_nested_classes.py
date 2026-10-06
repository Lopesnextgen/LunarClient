#!/usr/bin/env python3
"""Quarantine flat Outer$Inner.java files that duplicate inline nested classes.

Rescues from different decompile passes mixed two styles for nested classes:

  * flat files:      class Outer$Inner { ... }   (file Outer$Inner.java)
  * inline nesting:  class Outer { class Inner { ... } }

javac rejects having both ("classes: Outer$Inner and Inner have the same binary
name"). This tool reads the last compile log, finds those pairs, and moves the
flat file to tools/rescue-quarantine so the inline declaration wins.

Usage:
  python3 tools/error_diff.py            # produce the log
  python3 tools/dedupe_nested_classes.py [--apply]
"""
import argparse
import os
import re
import shutil
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402
SRC = os.path.join(ROOT, 'src/main/java')
QUAR = os.path.join(ROOT, 'tools/rescue-quarantine')
LOG = qa_loop.LOG
PAT = re.compile(r'^(?P<file>/[^:]+\.java):\d+: error: classes: (?P<bin>[A-Za-z0-9_$]+) and ')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()
    if not os.path.exists(LOG):
        sys.exit(f'missing {LOG}; run tools/error_diff.py first')
    pairs = {}
    with open(LOG, errors='ignore') as fh:
        for line in fh:
            m = PAT.match(line)
            if not m:
                continue
            flat = os.path.join(os.path.dirname(m.group('file')), m.group('bin') + '.java')
            if os.path.exists(flat):
                pairs[flat] = m.group('file')
    for flat, outer in sorted(pairs.items()):
        rel = os.path.relpath(flat, SRC)
        action = 'quarantine' if args.apply else 'would quarantine'
        print(f'{action}: {rel}  (inline in {os.path.relpath(outer, SRC)})')
        if args.apply:
            dst = os.path.join(QUAR, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            shutil.move(flat, dst)
    print(f'{len(pairs)} duplicate nested class file(s)')
    return 0


if __name__ == '__main__':
    sys.exit(main())
