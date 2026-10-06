#!/usr/bin/env python3
"""Filter the combined mixin configs to 1.8.9-compatible mixins.

The mixin names in the original configs are in the pre-restructure namespace.
We resolve each through the rename chain to find its class in
libs/lunar-libraries.jar, then verify with a Java helper whether its injection
targets exist in target/classes.

Usage: filter_mixins_189.py [--apply]
"""
import os, sys, json, zipfile, re, subprocess, shutil, tempfile

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


def _java_bin():
    """Locate java without hardcoding user paths (JAVA_HOME -> PATH -> sdkman)."""
    jh = os.environ.get('JAVA_HOME')
    if jh and os.path.exists(os.path.join(jh, 'bin', 'java')):
        return os.path.join(jh, 'bin', 'java')
    found = shutil.which('java')
    if found:
        return found
    fallback = os.path.join(os.path.expanduser('~'), '.sdkman', 'candidates', 'java', 'current', 'bin', 'java')
    return fallback if os.path.exists(fallback) else 'java'


sys.path.insert(0, os.path.join(PROJECT, 'tools'))
from importlib.machinery import SourceFileLoader
mrm = SourceFileLoader('mrm', os.path.join(PROJECT, 'tools', 'make_resource_map.py')).load_module()
chain = mrm.RenameChain()

LIBS = os.path.join(PROJECT, 'libs', 'lunar-renamed-classes.jar')
LEGACY = os.path.join(PROJECT, 'libs', 'multiver-full', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar')
RES = os.path.join(PROJECT, 'src', 'main', 'resources')
_M2 = os.environ.get('M2_REPO') or os.path.join(os.path.expanduser('~'), '.m2', 'repository')
GSON = os.path.join(_M2, 'com/google/code/gson/gson/2.2.4/gson-2.2.4.jar')
ASMCP = os.pathsep.join([
    os.path.join(PROJECT, 'tools', 'bin'),
    os.path.join(PROJECT, 'tools', 'bin', 'asm-9.7.1.jar'),
    os.path.join(PROJECT, 'tools', 'bin', 'asm-tree-9.7.1.jar'),
    GSON,
])

FINAL = set(n[:-6] for n in zipfile.ZipFile(LIBS).namelist() if n.endswith('.class'))

def resolve(pkg_path, m):
    full = pkg_path + '/' + m.replace('.', '$')
    r = chain.resolve(full)
    return r if r in FINAL else None

def main():
    apply = '--apply' in sys.argv
    z = zipfile.ZipFile(LEGACY)
    changed = 0
    for name in z.namelist():
        if not (name.startswith('mixins.') and name.endswith('_combined.json')):
            continue
        data = json.loads(z.read(name))
        pkg_path = data.get('package', '').replace('.', '/')
        resolved = []
        for m in data.get('mixins', []):
            r = resolve(pkg_path, m)
            if r:
                resolved.append((m, r))
        # Ask the Java filter which resolved classes are 1.8.9-compatible.
        # We write a temp config whose mixins are already in final namespace so the
        # Java tool can load them from lunar-libraries.jar directly.
        if not resolved:
            continue
        tmpdir = tempfile.mkdtemp()
        cfg = dict(data)
        cfg['package'] = ''  # names are absolute in `resolved`
        cfg['mixins'] = [r for _, r in resolved]
        tmp_in = os.path.join(tmpdir, 'in.json')
        tmp_out = os.path.join(tmpdir, 'out.json')
        json.dump(cfg, open(tmp_in, 'w'))
        r = subprocess.run([
            _java_bin(),
            '-cp', ASMCP, 'MixinFilter', LIBS, tmp_in, tmp_out,
            os.path.join(PROJECT, 'target', 'classes')
        ], capture_output=True, text=True, cwd=PROJECT)
        if not os.path.exists(tmp_out):
            print(f'{name}: filter failed: {r.stderr[-300:]}')
            continue
        filtered = json.load(open(tmp_out))
        kept_final = set(filtered.get('mixins', []))
        kept = [m for m, rf in resolved if rf in kept_final]
        dropped = [m for m in data.get('mixins', []) if m not in kept]
        if not kept:
            print(f'{name}: all dropped')
            continue
        data['mixins'] = kept
        data['required'] = False
        data.setdefault('injectors', {})['defaultRequire'] = 0
        print(f'{name}: keep {len(kept)} drop {len(dropped)}')
        if apply:
            json.dump(data, open(os.path.join(RES, name), 'w'), indent=2)
            changed += 1
        shutil.rmtree(tmpdir, ignore_errors=True)
    print('written:', changed)

if __name__ == '__main__':
    main()
