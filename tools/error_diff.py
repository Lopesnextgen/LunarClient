#!/usr/bin/env python3
"""Compile the tree and diff the error files against a stored baseline.

The tree is currently partial (see Prompt.md fragmentation note), so a full
green compile is not possible. This tool answers the question that matters for
rename batches: did the edit introduce any NEW failing file?

Usage:
  tools/error_diff.py [--save-baseline]

Baseline file: tools/work/compile-baseline.txt (one src-relative path per line)
"""
import argparse
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402

ROOT = qa_loop.ROOT
BASELINE = os.path.join(ROOT, 'tools/work/compile-baseline.txt')


def current_error_files():
    files = qa_loop.source_files()
    code, took = qa_loop.compile_tree(files)
    errs = qa_loop.parse_errors()
    # Crash-excluded files are held out of the compile (javac aborts on them)
    # but are broken, so they must count as failing for the baseline diff.
    all_errs = set(errs) | set(qa_loop.crash_excluded_error_files())
    rel = {os.path.relpath(f, os.path.join(ROOT, 'src/main/java')) for f in all_errs}
    return code, took, rel


def read_set(path):
    if not os.path.exists(path):
        return set()
    return {line.strip() for line in open(path) if line.strip()}


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--save-baseline', action='store_true')
    args = parser.parse_args()

    code, took, rel = current_error_files()
    print(f'[error-diff] exit={code} in {took:.1f}s, error files={len(rel)}')

    if args.save_baseline or not os.path.exists(BASELINE):
        os.makedirs(os.path.dirname(BASELINE), exist_ok=True)
        with open(BASELINE, 'w') as out:
            for path in sorted(rel):
                out.write(path + '\n')
        print(f'[error-diff] baseline written ({len(rel)} files) -> {BASELINE}')
        return 0

    baseline = read_set(BASELINE)
    new = sorted(rel - baseline)
    fixed = sorted(baseline - rel)
    if new:
        print(f'[error-diff] NEW failing files ({len(new)}):')
        for path in new[:40]:
            print('   +', path)
    if fixed:
        print(f'[error-diff] files fixed since baseline ({len(fixed)}):')
        for path in fixed[:40]:
            print('   -', path)
    if not new and not fixed:
        print('[error-diff] no change vs baseline')
    return 1 if new else 0


if __name__ == '__main__':
    sys.exit(main())
