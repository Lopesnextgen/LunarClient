#!/usr/bin/env bash
# Finish the Maven/javac build by moving the few files javac rejects
# (ECJ accepts slightly more code than javac) into src/reference/java.
set -u
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
if [ -d "$HOME/.sdkman/candidates/maven/current/bin" ]; then
  export PATH="$HOME/.sdkman/candidates/maven/current/bin:$PATH"
fi
export MAVEN_OPTS="${MAVEN_OPTS:--Xmx1g}"
LOG="$HERE/work/logs/maven.log"
MAX="${MAX_ITERATIONS:-20}"
cd "$ROOT" || exit 1

for i in $(seq 1 "$MAX"); do
  echo "== maven iteration $i =="
  mvn -B -q -Dmaven.test.skip=true compile > "$LOG" 2>&1
  rc=$?
  if [ $rc -eq 0 ]; then
    echo "MAVEN GREEN after $i iterations"
    exit 0
  fi
  python3 - "$HERE" <<'PY'
import os, re, shutil, sys
here = sys.argv[1]
root = os.path.dirname(here)
main = os.path.join(root, 'src', 'main', 'java')
ref = os.path.join(root, 'src', 'reference', 'java')
pat = re.compile(r'^\[ERROR\] (?P<file>/[^:]+\.java):\[')
files = set()
for line in open(os.path.join(here, 'work', 'logs', 'maven.log'), encoding='utf-8', errors='replace'):
    m = pat.match(line)
    if m and m.group('file').startswith(main):
        files.add(m.group('file'))
files = sorted(files)
if not files:
    print('no file errors recovered; stopping')
    sys.exit(3)
for p in files:
    rel = os.path.relpath(p, main)
    dest = os.path.join(ref, rel)
    os.makedirs(os.path.dirname(dest), exist_ok=True)
    shutil.move(p, dest)
with open(os.path.join(here, 'work', 'logs', 'excluded-sources.txt'), 'a') as f:
    for p in files:
        f.write(os.path.relpath(p, main) + '\n')
print(f'moved {len(files)} files to src/reference/java')
PY
  rc=$?
  if [ $rc -ne 0 ]; then echo "stopping"; exit 1; fi
done
echo "gave up after $MAX iterations"
exit 1
