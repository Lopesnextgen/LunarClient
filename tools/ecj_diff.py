#!/usr/bin/env python3
"""ECJ gate: compile the tree and diff failing files against a baseline.

While the tree is partial, the rename batches are gated with "no NEW failing
files". `error_diff.py` does this with javac, but javac aborts on this tree
(see Prompt.md 0.7); ECJ reports every failing file in one pass (~25s), so it
is the measurement oracle. This tool runs the same compile as `ecj_check.py`
and diffs the failing-file set against `tools/work/ecj-baseline.txt`.

Usage:
  tools/ecj_diff.py                 # compile, print NEW/ FIXED vs baseline
  tools/ecj_diff.py --save-baseline # adopt the current compile as the baseline

Exit status: 0 when there are no NEW failing files, 1 otherwise.
"""
import argparse
import os
import subprocess
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402

ROOT = qa_loop.ROOT
BASELINE = os.path.join(ROOT, 'tools/work/ecj-baseline.txt')


def current_failing():
    """Run ecj_check.py and return the set of src-relative failing files."""
    out = os.path.join(qa_loop.WORK, 'ecj-errors.txt')
    subprocess.run([sys.executable, os.path.join(ROOT, 'tools/ecj_check.py')],
                   check=False)
    with open(out, errors='ignore') as fh:
        return {line.strip() for line in fh if line.strip()}


def read_set(path):
    if not os.path.exists(path):
        return set()
    with open(path, errors='ignore') as fh:
        return {line.strip() for line in fh if line.strip()}


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--save-baseline', action='store_true')
    args = ap.parse_args()

    now = current_failing()
    print(f'[ecj-diff] failing files: {len(now)}')

    if args.save_baseline or not os.path.exists(BASELINE):
        os.makedirs(os.path.dirname(BASELINE), exist_ok=True)
        with open(BASELINE, 'w') as fh:
            for p in sorted(now):
                fh.write(p + '\n')
        print(f'[ecj-diff] baseline saved ({len(now)} files) -> {BASELINE}')
        return 0

    base = read_set(BASELINE)
    new = sorted(now - base)
    fixed = sorted(base - now)

    if fixed:
        print(f'[ecj-diff] FIXED ({len(fixed)}):')
        for p in fixed[:25]:
            print(f'  - {p}')
    if new:
        print(f'[ecj-diff] NEW ({len(new)}):')
        for p in new:
            print(f'  + {p}')
        return 1
    print('[ecj-diff] no NEW failing files')
    return 0


if __name__ == '__main__':
    sys.exit(main())
