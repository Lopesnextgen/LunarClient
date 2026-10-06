#!/usr/bin/env bash
# Builds libs/fake-launcher.jar: local stand-ins for Lunar's launcher IPC
# (FakeLauncher) and for the Authenticator + AssetServer websockets
# (FakeBackend). All are compiled against the installed lunar.jar because they
# speak its protobuf protocols.
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
LUNAR_JAR="${LUNAR_JAR:-$ROOT/libs/multiver-full/lunar.jar}"
OUT="$ROOT/target/fake-launcher-classes"
rm -rf "$OUT" && mkdir -p "$OUT"
"$JAVA_HOME/bin/javac" -nowarn -proc:none -g:none --release 17 -d "$OUT" -classpath "$LUNAR_JAR" \
    tools/fake-launcher/FakeLauncher.java tools/fake-launcher/FakeBackend.java
"$JAVA_HOME/bin/jar" cfe "$ROOT/libs/fake-launcher.jar" FakeLauncher -C "$OUT" .
echo "[fakelauncher] built $ROOT/libs/fake-launcher.jar"
