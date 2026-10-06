#!/usr/bin/env python3
"""Create 1.8.9-filtered mixin configs (clean rewrite).

We only keep mixins whose targets and members verifiably exist in
target/classes.  Output is written as the versioned configs the loader
requests for 1.8.9: mixins.<module>_v1_8.json.
"""
import os, sys, json, zipfile, re, struct

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LIBS = os.path.join(ROOT, 'libs', 'lunar-libraries.jar')
LEGACY = os.path.join(ROOT, 'libs', 'multiver-full', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar')
RES = os.path.join(ROOT, 'src', 'main', 'resources')
CLASSES = os.path.join(ROOT, 'target', 'classes')

sys.path.insert(0, os.path.join(ROOT, 'tools'))
from importlib.machinery import SourceFileLoader
chain = SourceFileLoader('mrm', os.path.join(ROOT, 'tools', 'make_resource_map.py')).load_module().RenameChain()

ZLIBS = zipfile.ZipFile(LIBS)
ALL = set(ZLIBS.namelist())
EXISTING = set()
MEMBERS = {}
for base, dirs, files in os.walk(os.path.join(CLASSES, 'net', 'minecraft')):
    for f in files:
        if not f.endswith('.class'):
            continue
        rel = os.path.relpath(os.path.join(base, f), CLASSES)
        cls = rel[:-6]
        EXISTING.add(cls)
        try:
            data = open(os.path.join(base, f), 'rb').read()
        except Exception:
            continue
        strings = set(re.findall(rb'[A-Za-z_$][A-Za-z0-9_$]{0,80}', data))
        MEMBERS[cls] = set(s.decode('utf-8', 'replace') for s in strings)

SUFFIX = re.compile(r'\$v1_(?:7|8|12)')

def resolved_class(pkg_path, m):
    full = pkg_path + '/' + m.replace('.', '$')
    r = chain.resolve(full)
    return r if r + '.class' in ALL else None

def compatible(cls):
    data = ZLIBS.read(cls + '.class')
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

def main():
    apply = '--apply' in sys.argv
    z = zipfile.ZipFile(LEGACY)
    for name in sorted(z.namelist()):
        if not (name.startswith('mixins.') and name.endswith('_combined.json')):
            continue
        data = json.loads(z.read(name))
        pkg_path = data.get('package', '').replace('.', '/')
        kept, dropped = [], []
        for m in data.get('mixins', []):
            r = resolved_class(pkg_path, m)
            if r and compatible(r):
                kept.append(m)
            else:
                dropped.append(m)
        if not kept:
            print(f'{name}: 0 kept / {len(dropped)} dropped')
            continue
        stem = name[len('mixins.'):-len('_combined.json')]
        out_name = f'mixins.{stem}_v1_8.json'
        out = dict(data)
        out['mixins'] = kept
        out['required'] = False
        inj = out.setdefault('injectors', {})
        inj['defaultRequire'] = 0
        print(f'{name} -> {out_name}: keep {len(kept)} drop {len(dropped)}')
        if apply:
            json.dump(out, open(os.path.join(RES, out_name), 'w'), indent=2)

if __name__ == '__main__':
    main()
