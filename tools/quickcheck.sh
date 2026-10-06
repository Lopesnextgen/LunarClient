#!/usr/bin/env bash
# Fast compile check for a batch of sources.
# Uses target/classes (previous successful build) + libraries on the classpath,
# so only the given files need compiling.
#
# Usage: tools/quickcheck.sh <file.java> ...
#        tools/quickcheck.sh --list <listfile>
set -u
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
JAVAC="$JAVA_HOME/bin/javac"
CP="target/classes:libs/lunar-libraries.jar:libs/multiver-full/genesis-0.1.0-SNAPSHOT-all.jar:libs/multiver-full/legacy-0.1.0-SNAPSHOT-all-nomappings.jar:libs/multiver-full/forge-0.1.0-SNAPSHOT-all.jar:libs/multiver-full/common-0.1.0-SNAPSHOT-all-nomappings.jar:libs/multiver-full/optifine-0.1.0-SNAPSHOT-all.jar:libs/multiver-full/lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar"

if [ "${1:-}" = "--list" ]; then
  shift
  mapfile -t FILES < "$1"
else
  FILES=("$@")
fi
[ ${#FILES[@]} -eq 0 ] && { echo "no files"; exit 0; }

mkdir -p /tmp/opencode/javac-out
"$JAVAC" -nowarn -proc:none -g:none -d /tmp/opencode/javac-out \
  -cp "$CP" "${FILES[@]}" 2>&1 | head -400
exit ${PIPESTATUS[0]}
