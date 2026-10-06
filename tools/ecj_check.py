#!/usr/bin/env python3
"""Compile the tree with Eclipse ECJ and report every failing file in one pass.

javac aborts with internal asserts on the broken constructs this tree
contains, so its error list is truncated and its failing-file set moves
between runs. ECJ reports all errors in a single pass; use it as the oracle
while the tree is being repaired, then javac (tools/error_diff.py) as the
final gate once the crash triggers are gone.

Usage: tools/ecj_check.py [--out-dir DIR]
"""
import argparse
import os
import re
import subprocess
import sys
import time

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402

ECJ = os.path.join(qa_loop.ROOT, 'tools/bin/ecj-3.33.0.jar')
LOG = os.path.join(qa_loop.WORK, 'ecj.log')
ERR = re.compile(r'ERROR in (/[^\s]+\.java)')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--out-dir', default=os.path.join(qa_loop.WORK, 'ecj-out'))
    args = ap.parse_args()

    files = qa_loop.source_files()
    argfile = os.path.join(qa_loop.WORK, 'ecj-sources.txt')
    with open(argfile, 'w', encoding='utf-8') as fh:
        for p in files:
            fh.write(p + '\n')
    cmd = ['java', '-Xmx6g', '-jar', ECJ, '--release', '17', '-proc:none',
           '-nowarn', '-d', args.out_dir, '-cp', ':'.join(qa_loop.classpath()),
           '@' + argfile]
    print(f'[ecj] compiling {len(files)} files ...')
    t = time.time()
    with open(LOG, 'w') as log:
        p = subprocess.run(cmd, stdout=log, stderr=subprocess.STDOUT)
    errs = set()
    with open(LOG, errors='ignore') as fh:
        for line in fh:
            m = ERR.search(line)
            if m:
                errs.add(os.path.relpath(m.group(1), os.path.join(qa_loop.ROOT, 'src/main/java')))
    print(f'[ecj] exit={p.returncode} in {time.time() - t:.1f}s, error files={len(errs)}')
    errors_file = os.path.join(qa_loop.WORK, 'ecj-errors.txt')
    with open(errors_file, 'w') as fh:
        for e in sorted(errs):
            fh.write(e + '\n')
    print(f'[ecj] failing list -> {errors_file}')
    return 0 if p.returncode == 0 else 1


if __name__ == '__main__':
    sys.exit(main())
