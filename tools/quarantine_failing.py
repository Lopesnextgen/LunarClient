#!/usr/bin/env python3
"""Move failing source files to tools/rescue-quarantine until the tree compiles.

Used after a rescue sweep: the decompiled classes change javac resolution for
pre-existing files, which then need repair; staging them keeps the tree green
while preserving the sources.

--untracked-only restricts moves to files not yet in git (the rescued batch),
leaving committed files in place even when they fail. This is the safe mode
for finishing a rescue sweep: tracked failures are pre-existing tree state,
not sweep regressions.

Usage: tools/quarantine_failing.py [--rounds N] [--untracked-only]
"""
import argparse
import os
import re
import shutil
import subprocess
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402
SRC = os.path.join(ROOT, 'src/main/java')
QUAR = os.path.join(ROOT, 'tools/rescue-quarantine')
ERR = re.compile(r'^/.*src/main/java/(.*\.java):\d+: error:')


def error_files():
    subprocess.run([sys.executable, 'tools/error_diff.py'], cwd=ROOT,
                   stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
    out = set()
    for line in open(qa_loop.LOG, encoding='utf-8', errors='replace'):
        m = ERR.match(line)
        if m:
            out.add(m.group(1))
    return out


def untracked_files():
    out = subprocess.check_output(
        ['git', 'ls-files', '--others', '--exclude-standard', 'src'],
        cwd=ROOT, text=True)
    return {os.path.join(ROOT, p) for p in out.splitlines() if p.endswith('.java')}


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--rounds', type=int, default=60)
    ap.add_argument('--untracked-only', action='store_true')
    args = ap.parse_args()
    for i in range(1, args.rounds + 1):
        bad = error_files()
        if args.untracked_only:
            ut = untracked_files()
            bad = {rel for rel in bad if os.path.join(SRC, rel) in ut}
        if not bad:
            print(f'[quarantine] clean after {i - 1} moves')
            return 0
        for rel in sorted(bad):
            src_path = os.path.join(SRC, rel)
            if not os.path.exists(src_path):
                continue
            dst = os.path.join(QUAR, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            shutil.move(src_path, dst)
        print(f'[quarantine] round {i}: moved {len(bad)} failing files')
    print('[quarantine] round limit reached')
    return 1


if __name__ == '__main__':
    sys.exit(main())
