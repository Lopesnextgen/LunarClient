#!/usr/bin/env python3
"""Find javac crash triggers and add them to tools/qa-crash-excludes.txt.

javac 21/22 aborts with an internal error (Attr.isBooleanOrNumeric NPE or
TransTypes assertion) while attributing broken conditionals in the fragmented
tree. The abort truncates the error list, which silently corrupts QA runs
(looks "green" far too early). This tool finds the offending files by
compiling via qa_loop and, for every abnormal termination, appending the last
failing file in the log to the exclusion list. Repeat until javac completes.

Usage: tools/find_crash_triggers.py [--max-rounds N]
"""
import argparse
import os
import re
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402

ERR = re.compile(r'^(?P<file>/[^:]+\.java):\d+: error:')
CHECK = re.compile(r'^\[checking ([^\]]+)\]$')
CRASH = 'printing javac parameters to:'
SRC = os.path.join(qa_loop.ROOT, 'src/main/java')


def candidates():
    """Candidate source files in likelihood order.

    javac prints "[checking <class>]" while it processes each class, so the
    last ones before the abort are the best guess at the class whose
    attribution/flow triggered the crash. Fall back to the last error files
    for aborts that happen outside class processing.
    """
    checks, errs = [], []
    with open(qa_loop.LOG, errors='ignore') as fh:
        for line in fh:
            m = CHECK.match(line)
            if m:
                checks.append(m.group(1))
                continue
            m = ERR.match(line)
            if m and (not errs or errs[-1] != m.group('file')):
                errs.append(m.group('file'))
    out = []
    for cls in reversed(checks):
        p = os.path.join(SRC, cls.replace('.', '/') + '.java')
        if os.path.exists(p):
            out.append(p)
    out += [f for f in reversed(errs) if os.path.exists(f)]
    return out


def append_exclude(rel):
    with open(qa_loop.CRASH_EXCLUDES_FILE, 'a') as fh:
        fh.write(rel + '\n')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--max-rounds', type=int, default=40)
    args = ap.parse_args()

    os.environ.setdefault('QA_JAVAC_EXTRA', '-verbose')
    for rnd in range(1, args.max_rounds + 1):
        excludes = qa_loop.crash_excludes()
        files = qa_loop.source_files()
        code, took = qa_loop.compile_tree(files)
        with open(qa_loop.LOG, errors='ignore') as fh:
            log = fh.read()
        crashed = code == 4 or CRASH in log
        print(f'[triggers] round {rnd}: compiled {len(files)} files, exit={code} '
              f'in {took:.1f}s, crashed={crashed}')
        if not crashed:
            print('[triggers] no crash - exclusion list is sufficient')
            return 0
        cand = None
        for f in candidates():
            rel = os.path.relpath(f, SRC)
            if rel not in excludes and os.path.exists(f):
                cand = rel
                break
        if cand is None:
            print('[triggers] no candidate left - inspect the log manually')
            return 1
        append_exclude(cand)
        print(f'[triggers] added {cand} to {os.path.relpath(qa_loop.CRASH_EXCLUDES_FILE, qa_loop.ROOT)}')
    print('[triggers] cap reached')
    return 1


if __name__ == '__main__':
    sys.exit(main())
