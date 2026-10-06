#!/usr/bin/env python3
"""Repair quarantined rescued classes and move them back into source.

Each round:
  1. copy a slice of tools/rescue-quarantine/ back into src/main/java
  2. apply the member/annotation token fixers to those files
  3. compile: keep the files that pass, move the rest back to quarantine

Usage: tools/repair_quarantine.py [--rounds N] [--slice K]
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
ERR = re.compile(r'^(/.*\.java):\d+: error:')


def quarantine_files():
    out = []
    for dp, _dirs, fs in os.walk(QUAR):
        for f in fs:
            if f.endswith('.java'):
                out.append(os.path.relpath(os.path.join(dp, f), QUAR))
    return sorted(out)


def error_files():
    subprocess.run([sys.executable, 'tools/error_diff.py'], cwd=ROOT,
                   stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
    out = set()
    for line in open(qa_loop.LOG, encoding='utf-8', errors='replace'):
        m = ERR.match(line)
        if m:
            out.add(m.group(1).split('src/main/java/')[-1])
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--rounds', type=int, default=8)
    ap.add_argument('--slice', type=int, default=60)
    args = ap.parse_args()

    total_kept = 0
    for round_no in range(1, args.rounds + 1):
        files = quarantine_files()
        if not files:
            print('[repair] quarantine empty')
            break
        batch = files[:args.slice]
        for rel in batch:
            dst = os.path.join(SRC, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            shutil.copy2(os.path.join(QUAR, rel), dst)
        paths = [os.path.join(SRC, rel) for rel in batch]
        subprocess.run([sys.executable, 'tools/fix_dangling_members.py', *paths, '--apply'],
                       cwd=ROOT, stdout=subprocess.DEVNULL)
        subprocess.run([sys.executable, 'tools/fix_annotation_members.py', *paths, '--apply'],
                       cwd=ROOT, stdout=subprocess.DEVNULL)
        errs = error_files()
        bad = [rel for rel in batch if rel in errs]
        for rel in bad:
            dst = os.path.join(QUAR, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            shutil.move(os.path.join(SRC, rel), dst)
        kept = len(batch) - len(bad)
        total_kept += kept
        print(f'[repair] round {round_no}: kept {kept}, re-quarantined {len(bad)} '
              f'(remaining quarantine {len(quarantine_files())})')
        if not bad:
            continue
    print(f'[repair] total kept: {total_kept}')


if __name__ == '__main__':
    sys.exit(main())
