#!/usr/bin/env python3
"""Generate 1.8.9-only mixin configs.

For each module/version we take the original combined config list, resolve each
mixin class name through the rename chain, keep only those that exist in
libs/lunar-libraries.jar, and write a version-specific config.

We also drop mixins whose member targets reference classes that do not exist in
the 1.8.9 tree (detected by scanning the class bytes for net/minecraft
references not present in target/classes).

Usage: gen_189_mixins.py [--apply]
"""
import os, sys, json, zipfile, re, collections, shutil

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.join(PROJECT, 'tools'))
from importlib.machinery import SourceFileLoader
mrm = SourceFileLoader('mrm', os.path.join(PROJECT, 'tools', 'make_resource_map.py')).load_module()
chain = mrm.RenameChain()

LIBS = os.path.join(PROJECT, 'libs', 'lunar-libraries.jar')
LEGACY = os.path.join(PROJECT, 'libs', 'multiver-full', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar')
RES = os.path.join(PROJECT, 'src', 'main', 'resources')

FINAL = set(n[:-6] for n in zipfile.ZipFile(LIBS).namelist() if n.endswith('.class'))
EXISTING_MC = set()
for root, dirs, files in os.walk(os.path.join(PROJECT, 'target', 'classes', 'net', 'minecraft')):
    for f in files:
        if f.endswith('.class'):
            EXISTING_MC.add(os.path.relpath(os.path.join(root, f), os.path.join(PROJECT, 'target', 'classes'))[:-6])

def class_mc_refs(name):
    """Return net/minecraft refs mentioned in the class bytes."""
    try:
        z = zipfile.ZipFile(LIBS)
        if name not in z.namelist():
            return []
        d = z.read(name)
    except Exception:
        return []
    return sorted(set(m.decode() for m in re.findall(rb'net/minecraft/[A-Za-z0-9_$/]+', d)))

def is_189_ok(name):
    refs = class_mc_refs(name + '.class')
    for r in refs:
        if r.split('$')[0] not in EXISTING_MC:
            return False
    return True

apply = '--apply' in sys.argv
z = zipfile.ZipFile(LEGACY)
generated = 0
for cfgname in z.namelist():
    if not (cfgname.startswith('mixins.') and cfgname.endswith('_combined.json')):
        continue
    data = json.loads(z.read(cfgname))
    pkg = data.get('package', '')
    pkg_path = pkg.replace('.', '/')
    base = cfgname[:-len('_combined.json')]
    out = dict(data)
    kept = []
    dropped = []
    for m in data.get('mixins', []):
        full = pkg_path + '/' + m.replace('.', '$')
        resolved = chain.resolve(full)
        if resolved in FINAL and is_189_ok(resolved):
            kept.append(resolved.rsplit('/', 1)[1] if '/' in resolved else resolved)
            # keep package relative when possible
        else:
            dropped.append(m)
    if not kept:
        continue
    # derive package from the first kept class resolution
    first = chain.resolve(pkg_path + '/' + data['mixins'][0].replace('.', '$'))
    # fall back: keep original package if it resolves; else map through chain
    newpkg = pkg
    out['mixins'] = kept
    out['required'] = False
    out.setdefault('injectors', {})['defaultRequire'] = 0
    target = base + '.json'
    print(f'{cfgname} -> {target}: keep {len(kept)} drop {len(dropped)}')
    if apply:
        json.dump(out, open(os.path.join(RES, target), 'w'), indent=2)
    generated += 1
print('generated:', generated)
