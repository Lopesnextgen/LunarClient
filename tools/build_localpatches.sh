#!/usr/bin/env bash
# ============================================================================
# Builds libs/lunar-localpatches.jar, the first jar on the game classpath.
#
# Contents:
#   lunarBuildData.txt   production=false (dev build mode; ungates
#                        Singleplayer/Multiplayer/Discover and enables the
#                        serviceOverride* system properties)
#   ProfileData          stub for a class missing from this runtime + hook that
#                        starts the alt manager's asset warm-up
#   altmanager/*         LocalAccounts, AccountOps, MicrosoftAuth helpers
#   patched accounts UI service (built with ASM by AccountsPatcher, because
#                        the runtime jar has a class/package name clash that
#                        javac cannot compile against)
#
# The game's class loader picks the first match, so these resources/classes
# override lunar.jar's copies.
# ============================================================================
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"

. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
JAVAC="$JAVA_HOME/bin/javac"
JAVA="$JAVA_HOME/bin/java"
JAR="$JAVA_HOME/bin/jar"
LUNAR_JAR="${LUNAR_JAR:-$ROOT/libs/multiver-full/lunar.jar}"
ASM_JAR="$ROOT/libs/asm-9.7.1-all.jar"

OUT="$ROOT/target/localpatches"
CLS="$ROOT/target/localpatches-classes"
TOOLS="$ROOT/target/localpatches-tools"
JAR_OUT="$ROOT/libs/lunar-localpatches.jar"

rm -rf "$OUT" "$CLS" "$TOOLS"
mkdir -p "$OUT" "$CLS" "$TOOLS"

# 1. resources (classpath-first overrides of jar resources)
cp tools/localpatches/resources/lunarBuildData.txt "$OUT/"

# 2. compile local patch helpers (pure Java + Gson from lunar.jar)
find tools/localpatches/src -name '*.java' > "$ROOT/target/localpatches-sources.txt"
"$JAVAC" -nowarn -proc:none -g:none --release 17 \
    -d "$CLS" -classpath "$LUNAR_JAR" @"$ROOT/target/localpatches-sources.txt"

# 3. bytecode-patch the account service into the same output dir
"$JAVAC" -nowarn -proc:none -g:none --release 17 \
    -d "$TOOLS" -classpath "$ASM_JAR" tools/localpatches/tools/AccountsPatcher.java
"$JAVA" -cp "$TOOLS:$ASM_JAR" AccountsPatcher "$LUNAR_JAR" "$CLS"

# 4. package
cp -r "$CLS/." "$OUT/"
"$JAR" cf "$JAR_OUT" -C "$OUT" .
echo "[localpatches] built $JAR_OUT"
