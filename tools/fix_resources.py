#!/usr/bin/env python3
"""Rewrite Lunar resource files (mixin configs, service files) so they reference
the final renamed classes in libs/lunar-libraries.jar.

Handles:
  * mixin configs: "package" path + simple class names in "mixins"/"client"/
    "server" arrays (resolved independently, then grouped by final package)
  * META-INF/services files: filename is the interface, contents are impls
  * generic JSON/lang: plain dotted class references

Usage: fix_resources.py [--apply] [--dry-run]
"""
import os, re, sys, json, zipfile, shutil
from importlib.machinery import SourceFileLoader

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.join(PROJECT, 'tools'))
mrm = SourceFileLoader('make_resource_map', os.path.join(PROJECT, 'tools', 'make_resource_map.py')).load_module()
chain = mrm.RenameChain()

LIBS_JAR = os.path.join(PROJECT, 'libs', 'lunar-libraries.jar')
RES = os.path.join(PROJECT, 'src', 'main', 'resources')
z = zipfile.ZipFile(LIBS_JAR)
FINAL = set(n[:-6] for n in z.namelist() if n.endswith('.class'))


def resolve(name):
    return chain.resolve(name)


def resolve_simple(pkg_path, simple):
    """Try to resolve pkg/simple as a unit, then via split."""
    full = resolve(pkg_path + '/' + simple)
    if full in FINAL:
        return full
    np, nl = chain.resolve_split(pkg_path, simple)
    cand = np + '/' + nl
    if cand in FINAL:
        return cand
    return None


def _resolve_class_path(path):
    """Resolve a full class path through the rename chain, trying progressively
    shorter prefixes (since a path segment may be a class, not just a package)."""
    path = path.replace('.', '/')
    parts = path.split('/')
    for i in range(len(parts), 0, -1):
        pref = '/'.join(parts[:i])
        r = chain.resolve(pref)
        if r in FINAL:
            return r
    return None


def _resolve_entry(pkg_path, entry):
    """Resolve a single mixin entry (may be an inner-class chain 'A$B')."""
    entry = entry.replace('.', '$')
    # 1. full path
    full = _resolve_class_path(pkg_path + '/' + entry)
    if full is not None:
        return full
    # 2. simple-name resolution against the chain
    simple = entry
    for m in (chain.clash, chain.restruct, chain.norm):
        v = m.get(simple)
        if v and '/' not in v:
            simple = v
            break
    else:
        # try the underscore clash variant
        v = chain.clash.get(entry + '_')
        if v:
            simple = v
    # derive the resolved package from the package prefix
    parts = pkg_path.split('/')
    new_pkg = pkg_path
    for i in range(len(parts), 0, -1):
        pref = '/'.join(parts[:i])
        r = chain.resolve(pref)
        cand = r + ('/' + '/'.join(parts[i:]) if i < len(parts) else '')
        if any(n.startswith(cand + '/') for n in FINAL):
            new_pkg = cand
            break
    cand = new_pkg + '/' + simple
    if cand in FINAL:
        return cand
    if pkg_path + '/' + entry in FINAL:
        return pkg_path + '/' + entry
    return None


def fix_mixin_config(path, data):
    """Return (new_data, changed, problems)."""
    problems = []
    changed = False
    pkg = data.get('package')
    if not pkg:
        return data, False, problems

    pkg_path = pkg.replace('.', '/')

    # Normalize dotted inner-class notation in the package path
    # (e.g. com.foo.Bar.Baz$Qux means package com.foo and class Bar$Baz$Qux).
    resolved = {}
    for key in ('mixins', 'client', 'server'):
        lst = data.get(key)
        if not isinstance(lst, list):
            continue
        for entry in lst:
            if not isinstance(entry, str):
                continue
            r = _resolve_entry(pkg_path, entry)
            if r is not None:
                resolved[(key, entry)] = r
            else:
                problems.append((key, entry))

    if not resolved:
        return data, False, problems

    new_pkg = next(iter(resolved.values())).rsplit('/', 1)[0]

    for key in ('mixins', 'client', 'server'):
        lst = data.get(key)
        if not isinstance(lst, list):
            continue
        out = []
        for entry in lst:
            if not isinstance(entry, str):
                out.append(entry)
                continue
            r = resolved.get((key, entry))
            if r is None:
                out.append(entry)
                continue
            if r.startswith(new_pkg + '/'):
                out.append(r[len(new_pkg) + 1:])
            else:
                out.append(r)
        if out != lst:
            data[key] = out
            changed = True
    if new_pkg != pkg_path:
        data['package'] = new_pkg.replace('/', '.')
        changed = True
    return data, changed, problems


def main():
    apply = '--apply' in sys.argv
    total_files = 0
    changed_files = 0
    rename_ops = []
    for root, dirs, files in os.walk(RES):
        for fn in files:
            p = os.path.join(root, fn)
            rel = os.path.relpath(p, RES)
            if fn.endswith('.json') and fn.startswith('mixins.'):
                try:
                    data = json.load(open(p, encoding='utf-8'))
                except Exception as e:
                    print('skip (bad json)', rel, e)
                    continue
                total_files += 1
                new_data, changed, problems = fix_mixin_config(p, data)
                if changed:
                    changed_files += 1
                    if apply:
                        json.dump(new_data, open(p, 'w', encoding='utf-8'), indent=2)
                    else:
                        print('would update', rel)
                        if data.get('package') != new_data.get('package'):
                            print('   package:', data.get('package'), '->', new_data.get('package'))
                        for key in ('mixins', 'client', 'server'):
                            if data.get(key) != new_data.get(key):
                                for a, b in zip(data.get(key, []), new_data.get(key, [])):
                                    if a != b:
                                        print(f'   {key}: {a} -> {b}')
                for key, entry in problems:
                    print(f'   PROBLEM {rel} {key}: {entry} unresolved')
            elif 'services' in root and fn != 'Provider':
                total_files += 1
                # service file name is a class
                name = fn.replace('.', '/')
                target = resolve(name)
                if target in FINAL:
                    newname = target.replace('/', '.')
                    # contents may also reference moonsworth classes
                    txt = open(p, encoding='utf-8', errors='replace').read()
                    newtxt = txt
                    for m in re.finditer(r'com[./]moonsworth(?:[./][A-Za-z0-9_$]+)+', txt):
                        raw = m.group(0)
                        t = resolve(raw.replace('.', '/'))
                        if t in FINAL:
                            newtxt = newtxt.replace(raw, t.replace('/', '.'))
                    if newname != fn or newtxt != txt:
                        changed_files += 1
                        rename_ops.append((p, os.path.join(root, newname)))
                        if apply:
                            open(p, 'w', encoding='utf-8').write(newtxt)
                            if newname != fn:
                                os.rename(p, os.path.join(root, newname))
                        else:
                            print('would rename', rel, '->', newname)
    if apply:
        for old, new in rename_ops:
            print('renamed', os.path.relpath(old, RES), '->', os.path.relpath(new, RES))
    print(f'mixin configs: {total_files}, changed: {changed_files}')


if __name__ == '__main__':
    main()
