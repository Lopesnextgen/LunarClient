#!/usr/bin/env python3
"""Rescue packages from the stale jar, quarantining classes that do not compile.

Process:
  1. decompile the requested packages into src/ (tools/rescue_classes.py)
  2. run tools/error_diff.py and move any *rescued* file with errors to
     tools/work/rescue-quarantine/ (kept out of compilation, tracked for later)
  3. repeat until the batch is green, then print the summary

Usage: tools/rescue_with_quarantine.py <pkg>... [--no-subpackages] [--include-renamed-twins]
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


def rescued_files():
    out = subprocess.check_output(
        ['git', 'ls-files', '--others', '--exclude-standard', 'src'], cwd=ROOT, text=True)
    return {os.path.join(ROOT, p) for p in out.splitlines() if p.endswith('.java')}


def error_files():
    subprocess.run([sys.executable, 'tools/error_diff.py'], cwd=ROOT,
                   stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
    out = set()
    for line in open(qa_loop.LOG, encoding='utf-8', errors='replace'):
        m = ERR.match(line)
        if m:
            rel = m.group(1).split('src/main/java/')[-1]
            out.add(rel)
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('packages', nargs='+')
    ap.add_argument('--no-subpackages', action='store_true')
    ap.add_argument('--include-renamed-twins', action='store_true')
    args = ap.parse_args()

    cmd = [sys.executable, 'tools/rescue_classes.py'] + args.packages + ['--apply']
    if args.no_subpackages:
        cmd.append('--no-subpackages')
    if args.include_renamed_twins:
        cmd.append('--include-renamed-twins')
    subprocess.run(cmd, cwd=ROOT, check=True)
    rescued = rescued_files()
    print(f'[quarantine] {len(rescued)} rescued files added')

    os.makedirs(QUAR, exist_ok=True)
    moved = 0
    for _ in range(12):
        errs = error_files()
        bad = [os.path.relpath(f, SRC) for f in rescued if os.path.relpath(f, SRC) in errs]
        if not bad:
            break
        for rel in bad:
            src_path = os.path.join(SRC, rel)
            if not os.path.exists(src_path):
                continue
            dst = os.path.join(QUAR, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            shutil.move(src_path, dst)
            moved += 1
        rescued = {f for f in rescued if os.path.relpath(f, SRC) not in set(bad)}
        print(f'[quarantine] moved {len(bad)} failing files (total {moved})')
    errs = error_files()
    kept = [f for f in rescued if os.path.relpath(f, SRC) not in errs]
    print(f'[quarantine] kept {len(kept)} rescued files, quarantined {moved}')
    print(f'[quarantine] remaining error files: {len(errs)} -> {sorted(errs)[:8]}')


if __name__ == '__main__':
    sys.exit(main())
