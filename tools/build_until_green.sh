#!/usr/bin/env bash
# Compile, move failing sources to src/reference/java, repeat until green.
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
MAX="${MAX_ITERATIONS:-400}"

cd "$ROOT" || exit 1
for i in $(seq 1 "$MAX"); do
  echo "=== iteration $i: compiling ==="
  mvn -B -Dmaven.test.skip=true clean compile > "$LOG" 2>&1
  rc=$?
  if [ $rc -eq 0 ]; then
    echo "BUILD GREEN after $i iterations"
    exit 0
  fi
  n=$(grep -c "COMPILATION ERROR" "$LOG" || true)
  echo "compile failed (rc=$rc); analysing $LOG"
  python3 "$HERE/analyze_compile_errors.py" "$LOG" 8 | head -20
  moved_before=$(wc -l < "$HERE/work/logs/excluded-sources.txt" 2>/dev/null || echo 0)
  python3 "$HERE/exclude_broken_sources.py" --apply | tail -2
  moved_after=$(wc -l < "$HERE/work/logs/excluded-sources.txt" 2>/dev/null || echo 0)
  if [ "$moved_before" = "$moved_after" ]; then
    echo "no new files to exclude; stopping"
    exit 1
  fi
done
echo "gave up after $MAX iterations"
exit 1
