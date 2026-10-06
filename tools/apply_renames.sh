#!/usr/bin/env bash
# Apply package/member rename maps to the Lunar jar, regenerate sources,
# clean local variable names, and rebuild until green.
set -eu
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
CP="$HERE/bin/kinremapper.jar:$HERE/bin/asm-9.7.1.jar:$HERE/bin/asm-commons-9.7.1.jar:$HERE/bin/asm-tree-9.7.1.jar"
cd "$ROOT"

PKG="$HERE/work/mappings/package-renames.tsv"
MEM="$HERE/work/mappings/member-renames.tsv"
[ -f "$PKG" ] || { echo "missing $PKG"; exit 1; }
[ -f "$MEM" ] || { echo "missing $MEM"; exit 1; }

echo "== applying package + member renames =="
"$JAVA_HOME/bin/java" -Xmx4g -cp "$CP" KinRemapper \
    "$HERE/work/mappings" \
    "$HERE/work/staging/lunar-all-final.jar" \
    "$HERE/work/staging/lunar-all-named.jar" \
    - "$MEM" "$PKG"

cp -f "$HERE/work/staging/lunar-all-final.jar" "$HERE/work/staging/lunar-all-prenamed.jar"
cp -f "$HERE/work/staging/lunar-all-named.jar" "$HERE/work/staging/lunar-all-final.jar"

echo "== re-decompiling =="
python3 "$HERE/deobf_pipeline.py" decompile --imports --threads 4 --heap 2g --chunk-size 1100
rm -rf "$HERE/work/staging/decompiled-cfr"
python3 "$HERE/deobf_pipeline.py" fallback
python3 "$HERE/deobf_pipeline.py" assemble
python3 "$HERE/fix_decompiled.py" --apply

echo "== compile loop =="
rm -rf "$ROOT/src/reference" "$HERE/work/logs/excluded-sources.txt"
bash "$HERE/fast_green.sh"
bash "$HERE/maven_finish.sh"

echo "== cleaning local variable names =="
if [ -f "$HERE/clean_locals.py" ]; then
    python3 "$HERE/clean_locals.py" --root "$ROOT/src/main/java/com/moonsworth" --apply || true
    python3 "$HERE/clean_locals.py" --root "$ROOT/src/reference/java" --apply || true
    bash "$HERE/maven_finish.sh"
fi

echo "== verifying with maven =="
bash "$HERE/build.sh" clean package || bash "$HERE/verify.sh"
