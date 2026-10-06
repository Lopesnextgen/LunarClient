#!/usr/bin/env python3
"""Compile the full source tree and quarantine files that fail.

The green-trunk strategy: files that do not compile are moved to
tools/work/quarantine/ (gitignored working area; every file stays in git
history and in the ledger) so the trunk always compiles. Quarantined files
are rescued in batches later.

Usage:
  tools/qa_loop.py [--compile | --dry-run] [--max-rounds N]

Compiles with the same semantics as the Maven build:
  * javac 21, --release 17, -proc:none, -g:none, -Xmaxerrs 100000
  * no -sourcepath (deliberately: Maven's auto sourcepath causes javac to
    implicitly compile held-out sources and emit bogus duplicate-class errors)
  * classpath = target/classes + Maven dependencies + bundled Lunar jars

javac crash workaround (2026-09-16): javac 21/22 dies with internal errors
(Attr NPE, Annotate/TransTypes asserts) on broken trees, aborting the compile
and truncating the error list ("printing javac parameters to: javac.<ts>.args").
The compile uses -XDshould-stop.ifError=FLOW: attribution and flow run for all
files, erasure/codegen are skipped (where javac aborts). GENERATE is unsafe
here, and the default INIT policy stops after the first error phase, which
hides later files' errors (false green). Files in tools/qa-crash-excludes.txt
are held out of the QA compile and reported as failing; regenerate the list
with tools/find_crash_triggers.py if javac starts aborting again. The crash is
order-dependent: after adding/removing tree files the trigger set can shift,
so always re-check the log tail for "printing javac parameters".
"""
import argparse
import collections
import os
import re
import shutil
import subprocess
import sys
import tempfile
import time

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# Cross-platform scratch/work area (override with QA_WORK). Defaults to
# <system tempdir>/opencode, which is /tmp/opencode on Linux.
WORK = os.environ.get('QA_WORK') or os.path.join(tempfile.gettempdir(), 'opencode')
def java_bin(name):
    """Locate a JDK tool without hardcoding user paths.

    Order: $JAVA_HOME -> ~/.sdkman/candidates/java/{current,*} -> PATH.
    """
    jh = os.environ.get('JAVA_HOME')
    cands = []
    if jh:
        cands.append(os.path.join(jh, 'bin', name))
    sdk = os.path.join(os.path.expanduser('~'), '.sdkman', 'candidates', 'java')
    for pref in ('21.0.12-amzn', '17.0.20.fx-zulu', '17.0.19.fx-zulu', 'current'):
        cands.append(os.path.join(sdk, pref, 'bin', name))
    if os.path.isdir(sdk):
        for d in sorted(os.listdir(sdk)):
            cands.append(os.path.join(sdk, d, 'bin', name))
    found = shutil.which(name)
    if found:
        cands.append(found)
    for c in cands:
        if os.path.exists(c):
            return c
    return name


JAVAC = os.environ.get('QA_JAVAC') or java_bin('javac')
CPFILE = os.path.join(WORK, 'cp.txt')
OUT = os.path.join(WORK, 'qa-classes')
QUARANTINE = os.path.join(ROOT, 'tools/work/quarantine')
LEDGER = os.path.join(QUARANTINE, 'ledger.tsv')
LOG = os.path.join(WORK, 'qa-javac.log')
CRASH_EXCLUDES_FILE = os.path.join(ROOT, 'tools/qa-crash-excludes.txt')

EXCLUDE_DIRS = ('com/moonsworth/lunar/forge/lib/',)
EXCLUDE_FILES = (
    'com/moonsworth/lunar/OHOOORICRHIIIIRHCICICOCHROICRC/HRICOROOOCCOCOROCRHHCRRIRCOICO$1.java',
    'com/moonsworth/lunar/OHOOORICRHIIIIRHCICICOCHROICRC/HRICOROOOCCOCOROCRHHCRRIRCOICO$2.java',
)


def crash_excludes():
    """src-relative paths held out because javac aborts while attributing them."""
    if not os.path.exists(CRASH_EXCLUDES_FILE):
        return []
    out = []
    with open(CRASH_EXCLUDES_FILE, errors='ignore') as fh:
        for line in fh:
            line = line.strip()
            if line and not line.startswith('#'):
                out.append(line)
    return out

ERR = re.compile(r'^(?P<file>/[^:]+\.java):(?P<line>\d+): error: (?P<msg>.*)$')
SYNTAX_HINTS = ('expected', 'illegal start', 'not a statement', 'reached end of file',
                'unclosed', 'malformed')
PKG_ERR = re.compile(r'^package ([\w.]+) does not exist$')


def source_files():
    out = []
    src = os.path.join(ROOT, 'src/main/java')
    skip = set(EXCLUDE_FILES) | set(crash_excludes())
    for dirpath, _dirs, files in os.walk(src):
        for f in files:
            if not f.endswith('.java'):
                continue
            rel = os.path.relpath(os.path.join(dirpath, f), src)
            if any(rel.startswith(d) for d in EXCLUDE_DIRS):
                continue
            if rel in skip:
                continue
            out.append(os.path.join(dirpath, f))
    return sorted(out)


def classpath():
    """Javac/ECJ classpath: jar-free by default (set QA_LUNAR_JAR=1 for A/B)."""
    cp = [OUT, os.path.join(ROOT, 'target/classes')]
    cp += [e for e in open(CPFILE).read().strip().split(':')
           if not e.endswith(('lunar-renamed-classes.jar', 'lunar-libraries.jar'))]
    if os.environ.get('QA_LUNAR_JAR'):
        cp.append(os.path.join(ROOT, 'libs/lunar-renamed-classes.jar'))
    return cp


def compile_tree(files):
    if not os.path.exists(CPFILE):
        sys.exit(f'missing {CPFILE}; run: mvn dependency:build-classpath -Dmdep.outputFile={CPFILE}')
    cp = classpath()
    shutil.rmtree(OUT, ignore_errors=True)
    os.makedirs(OUT, exist_ok=True)
    argfile = os.path.join(OUT, 'sources.txt')
    with open(argfile, 'w', encoding='utf-8') as fh:
        for p in files:
            fh.write(('"%s"\n' % p) if ' ' in p else (p + '\n'))
    # -XDshould-stop.ifError=FLOW: javac's default (INIT) stops compiling right
    # after the first phase that reported an error, so a single parse error can
    # hide every later file's errors (false green). FLOW runs attribution and
    # flow analysis for all files, then stops before erasure/codegen (where
    # javac 21/22 abort on this tree; GENERATE is unsafe).
    stop = os.environ.get('QA_JAVAC_STOP_POLICY', 'FLOW')
    extra = os.environ.get('QA_JAVAC_EXTRA', '').split()
    cmd = [JAVAC, '-J-Xmx8g', '-J-Xss16m', '-J-XX:MaxMetaspaceSize=2g'] + extra + [
           '-nowarn', '-proc:none', '-g:none', '--release', '17',
           '-Xmaxerrs', '100000', f'-XDshould-stop.ifError={stop}',
           '-d', OUT, '-classpath', ':'.join(cp), '@' + argfile]
    start = time.time()
    with open(LOG, 'w') as log:
        p = subprocess.run(cmd, stdout=log, stderr=subprocess.STDOUT)
    took = time.time() - start
    return p.returncode, took


def parse_errors():
    """Return {file: [(line, msg, symbol|None)]} from the last log."""
    errs = collections.OrderedDict()
    cur = None
    with open(LOG, errors='ignore') as fh:
        for line in fh:
            m = ERR.match(line)
            if m:
                cur = errs.setdefault(m.group('file'), [])
                cur.append([int(m.group('line')), m.group('msg'), None])
                continue
            if cur is not None and line.strip().startswith('symbol:'):
                cur[-1][2] = line.split('symbol:')[-1].strip()
    return errs


def crash_excluded_error_files():
    """Absolute paths of crash-excluded files that are still in src/main/java."""
    src = os.path.join(ROOT, 'src/main/java')
    out = []
    for rel in crash_excludes():
        p = os.path.join(src, rel)
        if os.path.exists(p):
            out.append(p)
    return out


def classify(msgs):
    first = msgs[0][1]
    if any(h in first for h in SYNTAX_HINTS):
        return 'syntax'
    if PKG_ERR.match(first):
        return 'package'
    if 'cannot find symbol' in first:
        return 'symbol'
    if 'duplicate class' in first or 'already defined' in first:
        return 'duplicate'
    if 'is not public' in first or 'private access' in first or 'protected access' in first:
        return 'visibility'
    return 'other'


def ledger_has(rel):
    if not os.path.exists(LEDGER):
        return False
    with open(LEDGER, errors='ignore') as fh:
        for line in fh:
            if line.split('\t', 1)[0] == rel:
                return True
    return False


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--max-rounds', type=int, default=6)
    ap.add_argument('--dry-run', action='store_true')
    args = ap.parse_args()

    os.makedirs(QUARANTINE, exist_ok=True)
    for rnd in range(1, args.max_rounds + 1):
        files = source_files()
        print(f'== round {rnd}: compiling {len(files)} files ==')
        code, took = compile_tree(files)
        errs = parse_errors()
        err_files = {f for f, m in errs.items() if m}
        print(f'   exit={code} in {took:.1f}s, error files={len(err_files)}, '
              f'error lines={sum(len(m) for m in errs.values())}')
        if not err_files:
            print('GREEN')
            return 0
        if args.dry_run:
            for f in sorted(err_files)[:50]:
                rel = os.path.relpath(f, os.path.join(ROOT, 'src/main/java'))
                msgs = errs[f]
                print(f'   {classify(msgs):9s} {len(msgs):4d}  {rel}  |  {msgs[0][1][:70]}')
            return 0

        moved = 0
        with open(LEDGER, 'a') as ledger:
            if os.path.getsize(LEDGER) == 0 if os.path.exists(LEDGER) else True:
                ledger.write('file\tcategory\tn_errors\tsymbols\tfirst_error\n')
            for f in sorted(err_files):
                src_java = os.path.join(ROOT, 'src/main/java')
                rel = os.path.relpath(f, src_java)
                dest = os.path.join(QUARANTINE, 'src', rel)
                os.makedirs(os.path.dirname(dest), exist_ok=True)
                if os.path.exists(f):
                    shutil.move(f, dest)
                    moved += 1
                msgs = errs[f]
                syms = sorted({m[2] for m in msgs if m[2]})
                ledger.write(f"{rel}\t{classify(msgs)}\t{len(msgs)}\t{','.join(syms)}\t{msgs[0][1][:120]}\n")
        print(f'   quarantined {moved} files -> {QUARANTINE}')
    return 1


if __name__ == '__main__':
    sys.exit(main())
