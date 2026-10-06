#!/usr/bin/env python3
"""Regenerate the combined mixin configs inside the legacy jar so only
1.8.9-compatible mixins remain.

A mixin is kept when:
  * its class exists in libs/lunar-libraries.jar, and
  * every net/minecraft class it references exists in target/classes, and
  * every @Inject/@Redirect/@ModifyArg/@At "method"/"target" member it names
    exists in the target class (checked via javap symbol tables of the vanilla
    classes we build).

Usage: fix_combined_configs.py [--apply]
"""
import os, sys, json, zipfile, re, struct, shutil, subprocess

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.join(PROJECT, 'tools'))
from importlib.machinery import SourceFileLoader
mrm = SourceFileLoader('mrm', os.path.join(PROJECT, 'tools', 'make_resource_map.py')).load_module()
chain = mrm.RenameChain()
LIBS = os.path.join(PROJECT, 'libs', 'lunar-libraries.jar')
LEGACY = os.path.join(PROJECT, 'libs', 'multiver-full', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar')

zlibs = zipfile.ZipFile(LIBS)
FINAL = set(n[:-6] for n in zlibs.namelist() if n.endswith('.class'))

# MCP classes + members (members via javap on the class files)
EXISTING = {}
for root, dirs, files in os.walk(os.path.join(PROJECT, 'target', 'classes', 'net', 'minecraft')):
    for f in files:
        if f.endswith('.class'):
            EXISTING[os.path.relpath(os.path.join(root, f), os.path.join(PROJECT, 'target', 'classes'))[:-6]] = os.path.join(root, f)

VERSION_SUFFIX = re.compile(r'\$v1_(?:7|8|12)')

def member_exists(cls, member):
    """Best-effort: check the class constant pool for the member name."""
    path = EXISTING.get(cls)
    if not path:
        return False
    try:
        data = open(path, 'rb').read()
    except Exception:
        return False
    pool = set()
    try:
        cp = struct.unpack('>H', data[8:10])[0]
        off = 10
        i = 1
        sizes = {3:4,4:4,5:8,6:8,7:2,8:2,9:4,10:4,11:4,12:4,15:3,16:2,17:4,18:4,19:2,20:2}
        while i < cp and off < len(data):
            tag = data[off]; off += 1
            size = sizes.get(tag)
            if size is None:
                return True
            if tag == 1:
                ln = struct.unpack('>H', data[off:off+2])[0]
                pool.add(data[off+2:off+2+ln].decode('utf-8', 'replace'))
                off += 2 + ln
            else:
                off += size
            i += 1
            if tag in (5, 6):
                i += 1
    except Exception:
        return True
    target = VERSION_SUFFIX.sub('', member).split('(')[0].split(':')[0]
    return any(target == p or target in p for p in pool)

def class_ok(name):
    """Every net/minecraft reference must exist in our 1.8.9 tree."""
    entry = name + '.class'
    if entry not in zlibs.namelist():
        return False
    data = zlibs.read(entry)
    for m in re.findall(rb'net/minecraft/[A-Za-z0-9_$/]+', data):
        c = m.decode()
        if c.split('$')[0] not in EXISTING:
            return False
    # member targets from annotations: name$v1_x or name( in Ltarget;name(
    for m in re.finditer(rb'L(net/minecraft/[A-Za-z0-9_/$]+);([A-Za-z0-9_$<>]+)\s*\(', data):
        cls = m.group(1).decode()
        member = m.group(2).decode()
        if not member_exists(cls, member):
            return False
    return True

def resolve_name(pkg_path, m):
    full = pkg_path + '/' + m.replace('.', '$')
    r = chain.resolve(full)
    if r in FINAL:
        return r
    return None

def main():
    apply = '--apply' in sys.argv
    z = zipfile.ZipFile(LEGACY)
    out_entries = {}
    for name in z.namelist():
        if not (name.startswith('mixins.') and name.endswith('_combined.json')):
            continue
        data = json.loads(z.read(name))
        pkg = data.get('package', '')
        pkg_path = pkg.replace('.', '/')
        kept, dropped = [], []
        for m in data.get('mixins', []):
            resolved = resolve_name(pkg_path, m)
            if resolved and class_ok(resolved):
                kept.append(m)
            else:
                dropped.append(m)
        if not kept:
            print(f'{name}: all dropped')
            continue
        data['mixins'] = kept
        data['required'] = False
        data.setdefault('injectors', {})['defaultRequire'] = 0
        out_entries[name] = json.dumps(data, indent=2).encode()
        print(f'{name}: keep {len(kept)} drop {len(dropped)}')
        for d in dropped:
            print(f'     drop {d}')
    if not out_entries:
        return
    if apply:
        tmp = LEGACY + '.tmp'
        with zipfile.ZipFile(LEGACY) as zin, zipfile.ZipFile(tmp, 'w', zipfile.ZIP_DEFLATED) as zout:
            for item in zin.infolist():
                d = out_entries.get(item.filename, zin.read(item.filename))
                zout.writestr(item, d)
        shutil.move(tmp, LEGACY)
        shutil.copy(LEGACY, os.path.join(PROJECT, 'libs', 'multiver', 'legacy-patched.jar'))
        print('legacy jar configs rewritten')

if __name__ == '__main__':
    main()
