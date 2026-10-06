#!/usr/bin/env bash
# ============================================================================
# Builds libs/lunar-altmanager.jar, the built-in Forge mod that adds the
# cracked alt manager (RSHIFT) to the local Lunar runtime.
#
# The mod is compiled against the workspace's MCP sources (target/classes)
# plus the Forge 1.8.9 compile jar.
# ============================================================================
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"

. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
JAVAC="$JAVA_HOME/bin/javac"
JAR="$JAVA_HOME/bin/jar"
MAVEN="$(lunar_maven_bin)"

OUT="$ROOT/target/altmanager-classes"
JAR_OUT="$ROOT/libs/lunar-altmanager.jar"
CP_FILE="/tmp/opencode/cp.txt"

rm -rf "$OUT"
mkdir -p "$OUT"

# 1. Make sure the MCP game classes are compiled - the mod compiles against
#    them (MCP names, like the rest of the workspace).
if [ ! -d "$ROOT/target/classes/net/minecraft" ]; then
  echo "[altmod] compiling MCP classes..."
  find src/main/java/net/minecraft -name '*.java' > /tmp/opencode/mcp-files.txt
  mkdir -p "$ROOT/target/classes"
  deps="$CP_FILE"
  if [ ! -f "$deps" ]; then
    "$MAVEN" -q dependency:build-classpath -Dmdep.outputFile="$deps" -o >/dev/null 2>&1 || \
      "$MAVEN" -q dependency:build-classpath -Dmdep.outputFile="$deps" >/dev/null
  fi
  "$JAVAC" -nowarn -proc:none -g:none --release 8 -d "$ROOT/target/classes" \
    -classpath "$(cat "$deps")" @/tmp/opencode/mcp-files.txt
fi

# 2. Maven classpath (dependencies) for annotations/gson/lwjgl.
if [ ! -f "$CP_FILE" ]; then
  "$MAVEN" -q dependency:build-classpath -Dmdep.outputFile="$CP_FILE" >/dev/null
fi
DEPS="$(cat "$CP_FILE")"

# 3. Compile the mod.
find src/main/java/com/moonsworth/lunar/altmanager -name '*.java' > /tmp/opencode/altmod-files.txt
"$JAVAC" -nowarn -proc:none -g:none --release 17 -d "$OUT" \
  -classpath "$ROOT/target/classes:$ROOT/libs/forge-1.8.9-compile.jar:$DEPS" \
  @/tmp/opencode/altmod-files.txt

# 4. Package with mod metadata.
mkdir -p "$(dirname "$JAR_OUT")"
"$JAR" cf "$JAR_OUT" -C "$OUT" . -C "$ROOT/tools/altmanager-resources" mcmod.info
echo "[altmod] built $JAR_OUT"
