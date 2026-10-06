#!/usr/bin/env bash
# Final verification for the deobf project.
set -u
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
if [ -d "$HOME/.sdkman/candidates/maven/current/bin" ]; then
  export PATH="$HOME/.sdkman/candidates/maven/current/bin:$PATH"
fi
LOG="$HERE/work/logs/verify.log"
mkdir -p "$HERE/work/logs"

cd "$ROOT" || exit 1
echo "== source stats =="
echo "net/minecraft sources : $(find src/main/java/net -name '*.java' | wc -l)"
echo "com/moonsworth sources: $(find src/main/java/com/moonsworth -name '*.java' 2>/dev/null | wc -l)"
echo "reference sources     : $(find src/reference -name '*.java' 2>/dev/null | wc -l)"
echo "libs                  : $(ls libs/*.jar 2>/dev/null | wc -l) jars, $(du -sh libs 2>/dev/null | cut -f1)"
echo "resources             : $(du -sh src/main/resources 2>/dev/null | cut -f1)"

echo "== maven package =="
mvn -B -Dmaven.test.skip=true clean package > "$LOG" 2>&1
rc=$?
echo "maven exit=$rc"
tail -3 "$LOG"
if [ $rc -ne 0 ]; then
  python3 "$HERE/analyze_compile_errors.py" "$LOG" 20
  exit $rc
fi

echo "== artifact =="
ls -la target/*.jar 2>/dev/null
FAT=$(ls target/*jar-with-dependencies.jar 2>/dev/null | head -1)
if [ -n "$FAT" ]; then
  echo "fat jar size: $(du -h "$FAT" | cut -f1)"
  echo "lunar assets entries: $(unzip -l "$FAT" | grep -c 'assets/lunar')"
  echo "moonsworth classes  : $(unzip -l "$FAT" | grep -c 'com/moonsworth/.*\.class')"
  echo "net/minecraft classes: $(unzip -l "$FAT" | grep -c 'net/minecraft/.*\.class')"
  echo "bundled kotlin      : $(unzip -l "$FAT" | grep -c 'kotlin/.*\.class')"
  echo "bundled protobuf    : $(unzip -l "$FAT" | grep -c 'com/google/protobuf/.*\.class')"
  echo "bundled lunarclient : $(unzip -l "$FAT" | grep -c 'com/lunarclient/.*\.class')"
fi

echo "== renamer / rescue status =="
echo "quarantined sources : $(find src/reference -name '*.java' 2>/dev/null | wc -l)"
echo "digit-named classes : $(find src/main/java/com/moonsworth -name '*[0-9].java' | wc -l)"
echo "ThreadModuleDump    : $(find src/main/java -name 'ThreadModuleDump*.java' | wc -l)"
echo "obfuscated filenames: $(find src/main/java -name '*.java' | grep -cE '/[A-Z]{14,}(\$|\.java)')"
echo "obfuscated mentions : $(grep -rlE '[A-Z]{14,}' src/main/java/com/moonsworth --include='*.java' 2>/dev/null | wc -l)"
echo "empty dirs          : $(find src/main/java -type d -empty | wc -l)"
