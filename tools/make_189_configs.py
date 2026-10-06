#!/usr/bin/env python3
"""Generate the 1.8.9 version-specific mixin configs.

For every `_combined` config we:
  1. resolve each listed mixin through the rename chain,
  2. verify the resolved class exists in libs/lunar-libraries.jar,
  3. verify every net/minecraft class/member it references exists in
     target/classes (members read from class files directly),
  4. write `mixins.<stem>_<version>.json` for v1_8 (and keep combined only if
     fully resolvable).

Usage: make_189_configs.py [--apply]
"""
import os, sys, json, zipfile, re, struct, collections

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.join(PROJECT, 'tools'))
from importlib.machinery import SourceFileLoader
mrm = SourceFileLoader('mrm', os.path.join(PROJECT, 'tools', 'make_resource_map.py')).load_module()
chain = mrm.RenameChain()

LIBS = os.path.join(PROJECT, 'libs', 'lunar-libraries.jar')
LEGACY = os.path.join(PROJECT, 'libs', 'multiver-full', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar')
RES = os.path.join(PROJECT, 'src', 'main', 'resources')
ROOT = os.path.join(PROJECT, 'target', 'classes')

FINAL = set(n[:-6] for n in zipfile.ZipFile(LIBS).namelist() if n.endswith('.class'))
SUFFIX = re.compile(r'\$v1_(?:7|8|12)')

# members of every net/minecraft class in target/classes
MEMBERS = {}
EXISTING = set()
for base, dirs, files in os.walk(os.path.join(ROOT, 'net', 'minecraft')):
    for f in files:
        if not f.endswith('.class'):
            continue
        rel = os.path.relpath(os.path.join(base, f), ROOT)[:-6]
        EXISTING.add(rel)
        try:
            data = open(os.path.join(base, f), 'rb').read()
        except Exception:
            continue
        names = set()
        try:
            cp = struct.unpack('>H', data[8:10])[0]
            off = 10
            i = 1
            sizes = {3:4,4:4,5:8,6:8,7:2,8:2,9:4,10:4,11:4,12:4,15:3,16:2,17:4,18:4,19:2,20:2}
            utf = {}
            while i < cp and off < len(data):
                tag = data[off]; off += 1
                size = sizes.get(tag)
                if size is None:
                    break
                if tag == 1:
                    ln = struct.unpack('>H', data[off:off+2])[0]
                    utf[i] = data[off+2:off+2+ln].decode('utf-8', 'replace')
                    off += 2 + ln
                else:
                    off += size
                i += 1
                if tag in (5, 6):
                    i += 1
            # method/field names appear as UTF entries directly
            for v in utf.values():
                if re.match(r'^[A-Za-z_$][A-Za-z0-9_$]*$', v):
                    names.add(v)
        except Exception:
            pass
        MEMBERS[rel] = names

def class_ok(cls):
    entry = cls + '.class'
    if entry not in FINAL:
        return False
    data = zipfile.ZipFile(LIBS).read(entry)
    for m in re.findall(rb'L?(net/minecraft/[A-Za-z0-9_$/]+);', data):
        c = m.decode()
        if c.split('$')[0] not in EXISTING:
            return False
    for m in re.finditer(rb'L(net/minecraft/[A-Za-z0-9_$/]+);([A-Za-z0-9_$<>]+)', data):
        c = m.group(1).decode().split('$')[0]
        name = SUFFIX.sub('', m.group(2).decode())
        if c in MEMBERS and name not in MEMBERS[c]:
            return False
    return True

def resolve(pkg_path, m):
    full = pkg_path + '/' + m.replace('.', '$')
    r = chain.resolve(full)
    if r in FINAL:
        return r
    return None

def main():
    apply = '--apply' in sys.argv
    z = zipfile.ZipFile(LEGACY)
    written = 0
    for name in sorted(z.namelist()):
        if not (name.startswith('mixins.') and name.endswith('_combined.json')):
            continue
        data = json.loads(z.read(name))
        pkg_path = data.get('package', '').replace('.', '/')
        kept, dropped = [], []
        for m in data.get('mixins', []):
            r = resolve(pkg_path, m)
            if r and class_ok(r):
                kept.append(m)
            else:
                dropped.append(m)
        if not kept:
            print(f'{name}: all {len(dropped)} dropped')
            continue
        # version-specific config name: mixins.legacy_optifine_combined.json ->
        # mixins.legacy_optifine_v1_8.json (only if the stem ends _combined)
        stem = name[len('mixins.'):-len('_combined.json')]
        target = f'mixins.{stem}_v1_8.json'
        out = dict(data)
        out['mixins'] = kept
        out['required'] = False
        out.setdefault('injectors', {})['defaultRequire'] = 0
        print(f'{name} -> {target}: keep {len(kept)} drop {len(dropped)}')
        if apply:
            json.dump(out, open(os.path.join(RES, target), 'w'), indent=2)
            written += 1
    print('written:', written)

if __name__ == '__main__':
    main()
