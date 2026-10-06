#!/usr/bin/env bash
# Final normalization:
#   * real mixin classes -> <module>/mixin/<SimpleName> (flat)
#   * classes in fake "mixin*" packages -> package with all mixin* segments removed
#   * shaded library trees (forge/lib) -> src/reference/java
#   * rebuild, clean locals, remove empty dirs, package
set -eu
HERE="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(dirname "$HERE")"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
CP="$HERE/bin/kinremapper.jar:$HERE/bin/asm-9.7.1.jar:$HERE/bin/asm-commons-9.7.1.jar:$HERE/bin/asm-tree-9.7.1.jar"
JAR="$HERE/work/staging/lunar-all-final.jar"
MAP="$HERE/work/mappings/normalize-renames.tsv"
cd "$ROOT"

echo "== building normalization map =="
python3 - "$HERE" "$JAR" "$MAP" <<'PY'
import collections, os, re, sys, zipfile
here, jar, out = sys.argv[1], sys.argv[2], sys.argv[3]

real_mixins = set()
p = os.path.join(here, 'work', 'mappings', 'restructure', 'mixin-renames.tsv')
if os.path.exists(p):
    for line in open(p, encoding='utf-8'):
        parts = line.rstrip('\n').split('\t')
        if len(parts) == 2:
            real_mixins.add(parts[1])
            if '$' not in parts[1]:
                real_mixins.add(parts[1])

with zipfile.ZipFile(jar) as z:
    names = {i.filename[:-6] for i in z.infolist() if i.filename.endswith('.class')}

seg_re = re.compile(r'^mixin', re.I)

def module_of(name):
    parts = name.split('/')
    # com/moonsworth/lunar/<module>/...
    if len(parts) > 3 and parts[:3] == ['com', 'moonsworth', 'lunar']:
        mod = parts[3]
        return 'mixin' if seg_re.match(mod) else mod
    return None

def module_root(mod):
    return f'com/moonsworth/lunar/{mod}/mixin'

mapping = {}
for name in names:
    if not name.startswith('com/moonsworth/'):
        continue
    simple = name.rsplit('/', 1)[-1]
    simple_last = simple.rsplit('$', 1)[-1]
    pkg_segs = name.rsplit('/', 1)[0].split('/')
    has_fake = any(seg_re.match(s) and s != 'mixin' for s in pkg_segs)
    is_real = name in real_mixins or simple_last.endswith('Mixin')
    if is_real:
        mod = module_of(name)
        if mod is None:
            continue
        target = f'{module_root(mod)}/{simple}'
        # already canonical?
        if name.rsplit('/', 1)[0] == module_root(mod):
            continue
        mapping[name] = target
    elif has_fake:
        cleaned = [s for s in pkg_segs if not seg_re.match(s)]
        target = ('/'.join(cleaned) + '/' + simple) if cleaned else simple
        if target != name:
            mapping[name] = target

# expand inner classes explicitly so prefix propagation cannot create collisions
for old in list(mapping):
    target = mapping[old]
    for n in names:
        if n.startswith(old + '$') and n not in mapping:
            mapping[n] = target + n[len(old):]

# dedupe targets against each other AND against classes that keep their name
remaining = collections.Counter(n for n in names if n not in mapping)
used = collections.Counter(mapping.values())
used.update(remaining)
final = {}
for old, new in mapping.items():
    if used[new] == 1:
        cand = new
        used[new] = 0
    else:
        i = 2
        while used.get(f'{new}_{i}', 0) > 0:
            i += 1
        cand = f'{new}_{i}'
        used[new] -= 1
        used[cand] = 1
    final[old] = cand
with open(out, 'w', encoding='utf-8') as f:
    for old in sorted(final):
        f.write(f'{old}\t{final[old]}\n')
print(f'normalization renames: {len(final)}')
PY

echo "== applying =="
"$JAVA_HOME/bin/java" -Xmx4g -cp "$CP" KinRemapper "$HERE/work/mappings" \
    "$JAR" "$HERE/work/staging/lunar-all-normalized.jar" "$MAP" - -
cp -f "$JAR" "$HERE/work/staging/lunar-all-prenormalize.jar"
cp -f "$HERE/work/staging/lunar-all-normalized.jar" "$JAR"

echo "== re-decompiling =="
python3 "$HERE/deobf_pipeline.py" decompile --imports --threads 4 --heap 2g --chunk-size 1100
rm -rf "$HERE/work/staging/decompiled-cfr"
python3 "$HERE/deobf_pipeline.py" fallback
python3 "$HERE/deobf_pipeline.py" assemble
python3 "$HERE/fix_decompiled.py" --apply

echo "== moving shaded library trees to reference =="
python3 - <<'PY'
import os, shutil
main = 'src/main/java/com/moonsworth'
ref = 'src/reference/java/com/moonsworth'
for lib in ['lunar/forge/lib']:
    src = os.path.join(main, lib)
    if os.path.exists(src):
        dst = os.path.join(ref, lib)
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        if os.path.exists(dst):
            shutil.rmtree(dst)
        shutil.move(src, dst)
        print('moved', src, '->', dst)
PY

echo "== compile loops =="
rm -rf "$ROOT/src/reference/java/net" 2>/dev/null || true
bash "$HERE/fast_green.sh"
bash "$HERE/maven_finish.sh"

echo "== locals =="
python3 "$HERE/clean_locals.py" "$ROOT/src/main/java/com/moonsworth" --apply || true
python3 "$HERE/clean_locals.py" "$ROOT/src/reference/java" --apply || true
bash "$HERE/maven_finish.sh"

echo "== empty dirs =="
find "$ROOT/src" -type d -empty -delete

echo "== packaging =="
bash "$HERE/build.sh" clean package
