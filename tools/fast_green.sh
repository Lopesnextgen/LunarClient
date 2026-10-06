#!/usr/bin/env bash
# Fast compile/exclude loop.
#
# MCP sources are compiled once with javac into target/classes; the Lunar
# sources are then compiled with Eclipse ECJ, which reports errors for all
# files in a single pass (javac stops early and needs hundreds of rounds).
# Files that cannot compile are moved to src/reference/java; their classes
# stay available from libs/lunar-libraries.jar.
set -u
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
ECJ="${ECJ:-$HERE/bin/ecj-3.33.0.jar}"
CP="target/classes:$(cat tools/work/cp.txt):libs/lunar-libraries.jar"
OUT=/tmp/opencode/lunarout
ERR=/tmp/opencode/lunarerr.log
MAX="${MAX_ITERATIONS:-60}"
cd "$ROOT" || exit 1

if [ ! -f target/classes/net/minecraft/client/Minecraft.class ]; then
  echo "== compiling MCP =="
  find "$ROOT/src/main/java/net" -name '*.java' > /tmp/opencode/mcp-sources.txt
  rm -rf target/classes && mkdir -p target/classes
  "$JAVA_HOME/bin/javac" --release 17 -proc:none -nowarn -g:none \
      -Xmaxerrs 100000 -cp "$CP" -d target/classes @/tmp/opencode/mcp-sources.txt || exit 1
fi

find "$ROOT/src/main/java/net" -name '*.java' > /tmp/opencode/mcp-sources.txt

for i in $(seq 1 "$MAX"); do
  find "$ROOT/src/main/java/com/moonsworth" -name '*.java' > /tmp/opencode/lunar-sources.txt
  count=$(wc -l < /tmp/opencode/lunar-sources.txt)
  echo "== fast iteration $i: $count sources =="
  rm -rf "$OUT" && mkdir -p "$OUT"
  "$JAVA_HOME/bin/java" -Xmx2g -jar "$ECJ" --release 17 -proc:none -nowarn \
      -d "$OUT" -cp "$CP" @/tmp/opencode/lunar-sources.txt 2> "$ERR"
  rc=$?
  if [ $rc -eq 0 ]; then
    echo "BUILD GREEN after $i fast iterations"
    exit 0
  fi
  python3 - "$HERE" <<'PY'
import os, re, shutil, sys
here = sys.argv[1]
root = os.path.dirname(here)
main = os.path.join(root, 'src', 'main', 'java')
ref = os.path.join(root, 'src', 'reference', 'java')
pat = re.compile(r'^\d+\. ERROR in (?P<file>.+?) \(at line \d+\)')
files = []
for line in open('/tmp/opencode/lunarerr.log', encoding='utf-8', errors='replace'):
    m = pat.match(line)
    if m and m.group('file').startswith(main):
        files.append(m.group('file'))
files = sorted(set(files))
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
