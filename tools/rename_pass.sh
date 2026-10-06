#!/usr/bin/env bash
# Apply inferred readable class names (mixins + mods) and regenerate sources.
set -eu
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
cd "$ROOT"

. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
CP="$HERE/bin/kinremapper.jar:$HERE/bin/asm-9.7.1.jar:$HERE/bin/asm-commons-9.7.1.jar:$HERE/bin/asm-tree-9.7.1.jar"

echo "== inferring renames =="
python3 "$HERE/infer_renames.py" "$HERE/work/staging/decompiled" \
        "$HERE/work/mappings/inferred-renames.tsv"

echo "== applying renames to bytecode =="
"$JAVA_HOME/bin/java" -Xmx3g -cp "$CP" KinRemapper \
    "$HERE/work/mappings" \
    "$HERE/work/staging/lunar-all-final.jar" \
    "$HERE/work/staging/lunar-all-inferred.jar" \
    "$HERE/work/mappings/inferred-renames.tsv"

cp -f "$HERE/work/staging/lunar-all-final.jar" "$HERE/work/staging/lunar-all-final-preinf.jar"
cp -f "$HERE/work/staging/lunar-all-inferred.jar" "$HERE/work/staging/lunar-all-final.jar"

echo "== re-decompiling =="
python3 "$HERE/deobf_pipeline.py" decompile --imports --threads 4 --heap 2g --chunk-size 1100
python3 "$HERE/deobf_pipeline.py" fallback
python3 "$HERE/deobf_pipeline.py" assemble
python3 "$HERE/fix_decompiled.py" --apply

echo "== compile/exclude loop =="
rm -rf "$ROOT/src/reference" "$HERE/work/logs/excluded-sources.txt"
bash "$HERE/build_until_green.sh"
