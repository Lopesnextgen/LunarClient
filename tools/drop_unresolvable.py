#!/usr/bin/env python3
"""Drop mixins whose targets/members cannot exist in this runtime.

Some mixins target readable Forge members (`ClientCommandHandler.executeCommand`)
while the runtime Forge jars use obfuscated names.  Those mixins can never
apply here, so we remove them from the generated 1.8.9 configs.

Usage: drop_unresolvable.py [--apply]
"""
import os, sys, json, zipfile, re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LIBS = os.path.join(ROOT, 'libs', 'lunar-libraries.jar')
RES = os.path.join(ROOT, 'src', 'main', 'resources')
CLASSES = os.path.join(ROOT, 'target', 'classes')

ZLIBS = zipfile.ZipFile(LIBS)
HAS_CLASS = set(ZLIBS.namelist())
SRG = re.compile(rb'^[a-z]{1,3}$')

def unresolved_forge_member(data):
    """Return True when the class references readable Forge members that the
    runtime (obfuscated) cannot provide."""
    for m in re.finditer(rb'L(net/minecraftforge/[A-Za-z0-9_$/]+);([A-Za-z_][A-Za-z0-9_]*)\s*\(', data):
        cls = m.group(1).decode()
        member = m.group(2).decode()
        entry = cls + '.class'
        if entry not in HAS_CLASS:
            continue
        try:
            runtime = ZLIBS.read(entry)
        except Exception:
            continue
        if member.encode() not in runtime:
            return True
    return False

def main():
    apply = '--apply' in sys.argv
    for fn in sorted(os.listdir(RES)):
        if not (fn.startswith('mixins.') and fn.endswith('_v1_8.json')):
            continue
        p = os.path.join(RES, fn)
        data = json.load(open(p))
        pkg = data.get('package', '').replace('.', '/')
        kept, dropped = [], []
        for m in data.get('mixins', []):
            cls = pkg + '/' + m.replace('.', '$')
            entry = cls + '.class'
            if entry not in HAS_CLASS:
                dropped.append(m)
                continue
            d = ZLIBS.read(entry)
            if unresolved_forge_member(d):
                dropped.append(m)
            else:
                kept.append(m)
        if dropped:
            data['mixins'] = kept
            print(f'{fn}: keep {len(kept)} drop {len(dropped)} ({dropped[:3]}...)')
            if apply:
                json.dump(data, open(p, 'w'), indent=2)
                combined = p.replace('_v1_8.json', '_combined.json')
                if os.path.exists(combined):
                    json.dump(data, open(combined, 'w'), indent=2)

if __name__ == '__main__':
    main()
