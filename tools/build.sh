#!/usr/bin/env bash
# Build helper: compiles MavenMCP-1.8.9 with the project toolchain and
# summarises compile errors.
#
#   tools/build.sh [maven args...]
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
LOG="${LOG:-$HERE/work/logs/maven.log}"
mkdir -p "$(dirname "$LOG")"

cd "$ROOT" || exit 1
mvn -B -Dmaven.test.skip=true "$@" > "$LOG" 2>&1
rc=$?
echo "maven exit=$rc (log: $LOG)"
tail -3 "$LOG"
if [ $rc -ne 0 ]; then
  python3 "$HERE/analyze_compile_errors.py" "$LOG" 30
fi
exit $rc
