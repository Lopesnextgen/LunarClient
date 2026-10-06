#!/usr/bin/env bash
# Apply the restructure maps (mixin/module/remaining class renames), regenerate
# sources, rebuild until green, clean locals and empty directories, package.
set -eu
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
CP="$HERE/bin/kinremapper.jar:$HERE/bin/asm-9.7.1.jar:$HERE/bin/asm-commons-9.7.1.jar:$HERE/bin/asm-tree-9.7.1.jar"
R="$HERE/work/mappings/restructure"
MERGED="$HERE/work/mappings/restructure-merged.tsv"
cd "$ROOT"

echo "== merging restructure maps =="
python3 - "$R" "$MERGED" "$HERE/work/staging/lunar-all-final.jar" <<'PY'
import os, re, sys, zipfile, collections
src, out, jar = sys.argv[1], sys.argv[2], sys.argv[3]

def load(fn):
    p = os.path.join(src, fn)
    m = {}
    if not os.path.exists(p):
        print('missing', fn)
        return m
    for line in open(p, encoding='utf-8'):
        parts = line.rstrip('\n').split('\t')
        if len(parts) == 2 and parts[0] and parts[1]:
            m[parts[0]] = parts[1]
    print(f'{fn}: {len(m)} entries')
    return m

rem = load('remaining-renames.tsv')
rest = load('mixin-renames.tsv')
rest.update(load('module-renames.tsv'))

def simple_of(n):
    return n.rsplit('$', 1)[-1] if '$' in n else n.rsplit('/', 1)[-1]

def parent_of(n):
    return n.rsplit('$', 1)[0] if '$' in n else n.rsplit('/', 1)[0]

# combine: structure (package move) comes from the mixin/module maps, the
# clean simple name comes from the remaining-renames map
mapping = {}
for old, rem_new in rem.items():
    if old in rest and rest[old] != rem_new:
        rp, rs = parent_of(rest[old]), simple_of(rem_new)
        mapping[old] = (rp + '$' + rs) if '$' in rest[old] else (rp + '/' + rs)
    else:
        mapping[old] = rem_new
for old, rest_new in rest.items():
    mapping.setdefault(old, rest_new)

# walk the jar and give every still-obfuscated class (including inlined inner
# classes that the tree-based maps never saw) a systematic clean name
with zipfile.ZipFile(jar) as z:
    jar_names = {i.filename[:-6] for i in z.infolist() if i.filename.endswith('.class')}
gib = re.compile(r'^[CHOIR]{8,}_?$')

def resolve(name):
    if name in mapping:
        return mapping[name]
    if '$' in name:
        outer, inner = name.rsplit('$', 1)
        return resolve(outer) + '$' + inner
    return name

counters = collections.Counter()
fallback = 0
for name in sorted(jar_names):
    resolved = resolve(name)
    simple = simple_of(resolved)
    if not gib.match(simple):
        continue
    if '$' in resolved:
        outer_resolved = resolved.rsplit('$', 1)[0]
        base = simple_of(outer_resolved) + 'Part'
        counters[base] += 1
        mapping[name] = f'{outer_resolved}${base}{counters[base]}'
    else:
        pkg = resolved.rsplit('/', 1)[0]
        base = pkg.rsplit('/', 1)[-1].capitalize() + 'Type'
        counters[base] += 1
        mapping[name] = f'{pkg}/{base}{counters[base]}'
    fallback += 1
print(f'fallback renames for remaining obfuscated classes: {fallback}')

# resolve duplicate targets deterministically
used = collections.Counter(mapping.values())
final = {}
for old, new in mapping.items():
    cand = new
    i = 2
    while used[cand] > 1:
        cand = f'{new}${i}'
        i += 1
    used[new] -= 1
    used[cand] += 1
    final[old] = cand
with open(out, 'w', encoding='utf-8') as f:
    for old in sorted(final):
        f.write(f'{old}\t{final[old]}\n')
noop = sum(1 for k, v in final.items() if k == v)
print(f'merged: {len(final)} renames, {noop} no-ops')
PY

echo "== applying renames to bytecode =="
"$JAVA_HOME/bin/java" -Xmx4g -cp "$CP" KinRemapper \
    "$HERE/work/mappings" \
    "$HERE/work/staging/lunar-all-final.jar" \
    "$HERE/work/staging/lunar-all-restructured.jar" \
    "$MERGED" - -

cp -f "$HERE/work/staging/lunar-all-final.jar" "$HERE/work/staging/lunar-all-prerestructure.jar"
cp -f "$HERE/work/staging/lunar-all-restructured.jar" "$HERE/work/staging/lunar-all-final.jar"

echo "== re-decompiling =="
python3 "$HERE/deobf_pipeline.py" decompile --imports --threads 4 --heap 2g --chunk-size 1100
rm -rf "$HERE/work/staging/decompiled-cfr"
python3 "$HERE/deobf_pipeline.py" fallback
python3 "$HERE/deobf_pipeline.py" assemble
python3 "$HERE/fix_decompiled.py" --apply

echo "== compile loops =="
rm -rf "$ROOT/src/reference" "$HERE/work/logs/excluded-sources.txt"
bash "$HERE/fast_green.sh"
bash "$HERE/maven_finish.sh"

echo "== locals =="
python3 "$HERE/clean_locals.py" "$ROOT/src/main/java/com/moonsworth" --apply || true
python3 "$HERE/clean_locals.py" "$ROOT/src/reference/java" --apply || true
bash "$HERE/maven_finish.sh"

echo "== removing empty directories =="
find "$ROOT/src" -type d -empty -delete

echo "== packaging =="
bash "$HERE/build.sh" clean package
