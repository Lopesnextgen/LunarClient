#!/usr/bin/env bash
# Compile a subset of sources against the project classpath and report errors.
# Usage: tools/checkfile.sh <file.java> [more.java ...]
#        tools/checkfile.sh --list <listfile>
set -u
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
JAVAC="$JAVA_HOME/bin/javac"
CP_FILE="tools/work/cp-fix.txt"

if [ ! -f "$CP_FILE" ]; then
  if [ -d "$HOME/.sdkman/candidates/maven/current/bin" ]; then
  export PATH="$HOME/.sdkman/candidates/maven/current/bin:$PATH"
fi
  mvn -q -Dmaven.test.skip=true dependency:build-classpath \
      -Dmdep.outputFile="$CP_FILE" -Dmdep.includeScope=runtime >/dev/null 2>&1
fi
CP="$(cat "$CP_FILE" 2>/dev/null):libs/lunar-libraries.jar"

if [ "${1:-}" = "--list" ]; then
  shift
  FILES=()
  while IFS= read -r line; do
    [ -n "$line" ] && FILES+=("$line")
  done < "$1"
else
  FILES=("$@")
fi

mkdir -p /tmp/opencode/javac-out
"$JAVAC" -nowarn -proc:none -g:none -d /tmp/opencode/javac-out \
  -cp "$CP" "${FILES[@]}" 2>&1 | head -200
