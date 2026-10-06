#!/usr/bin/env python3
"""Move sources that javac cannot compile into src/reference/java.

Their classes remain available from libs/lunar-libraries.jar, so the rest of
the project keeps compiling. Run after a failing compile; repeat until clean.

Usage: exclude_broken_sources.py [--log path] [--apply]
"""
import argparse
import os
import re
import shutil
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.dirname(HERE)
MAIN = os.path.join(ROOT, 'src', 'main', 'java')
REFERENCE = os.path.join(ROOT, 'src', 'reference', 'java')
LIST = os.path.join(HERE, 'work', 'logs', 'excluded-sources.txt')
PAT = re.compile(r'^\[ERROR\] (?P<file>.+?\.java):\[(?:\d+)(?:,\d+)?\]')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--log', default=os.path.join(HERE, 'work', 'logs', 'maven.log'))
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()

    files = set()
    with open(args.log, encoding='utf-8', errors='replace') as f:
        for line in f:
            m = PAT.match(line.rstrip('\n'))
            if m:
                files.add(m.group('file'))
    files = sorted(files)
    mcp = [p for p in files if '/src/main/java/net/minecraft/' in p]
    if mcp:
        print('ERROR: MCP sources must not be excluded, fix these instead:')
        for p in mcp[:20]:
            print('   ', os.path.relpath(p, ROOT))
        sys.exit(2)
    print(f'files with compile errors: {len(files)}')
    for p in files[:20]:
        print('   ', os.path.relpath(p, ROOT))

    if not args.apply:
        return

    os.makedirs(os.path.dirname(LIST), exist_ok=True)
    moved = []
    for p in files:
        if not os.path.exists(p):
            continue
        rel = os.path.relpath(p, MAIN)
        dest = os.path.join(REFERENCE, rel)
        os.makedirs(os.path.dirname(dest), exist_ok=True)
        shutil.move(p, dest)
        moved.append(rel)
    with open(LIST, 'a', encoding='utf-8') as f:
        for rel in moved:
            f.write(rel + '\n')
    print(f'moved {moved.__len__()} files to src/reference/java')


if __name__ == '__main__':
    main()
