#!/usr/bin/env python3
"""Map original Lunar class/package references (as found in mixin configs and
service files) to the final renamed names in this project.

Outputs a JSON mapping on stdout (or --out file).

Resolution order:
  1. rename-chain over the full path (classes.tsv, class-renames.tsv,
     restructure/*, normalize-renames.tsv)
  2. rename-chain on parent path + clash/leaf fallback
  3. verification against the final class set (lunar-libraries.jar)
"""
import os, re, sys, json, zipfile, argparse

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SNAP = os.path.join(PROJECT, 'tools', 'mappings-snapshot')
LIBS_JAR = os.path.join(PROJECT, 'libs', 'lunar-libraries.jar')

def load(p):
    d = {}
    if not os.path.isfile(p):
        return d
    for line in open(p, encoding='utf-8'):
        line = line.rstrip('\n')
        if not line or line.startswith('#'):
            continue
        parts = line.split('\t')
        if len(parts) >= 2 and parts[0]:
            d[parts[0]] = parts[1]
    return d

class RenameChain:
    def __init__(self):
        self.classes = load(os.path.join(SNAP, 'classes.tsv'))
        self.clash = load(os.path.join(SNAP, 'class-renames.tsv'))
        self.pkg = load(os.path.join(SNAP, 'package-renames.tsv'))
        self.inferred = load(os.path.join(SNAP, 'inferred-renames.tsv'))
        self.restruct = {}
        for f in ('mixin-renames.tsv', 'module-renames.tsv', 'remaining-renames.tsv'):
            self.restruct.update(load(os.path.join(SNAP, 'restructure', f)))
        self.norm = load(os.path.join(SNAP, 'normalize-renames.tsv'))

    def apply_pkg(self, name):
        parts = name.split('/')
        for i in range(len(parts) - 1, 0, -1):
            pref = '/'.join(parts[:i])
            if pref in self.pkg:
                return self.pkg[pref] + '/' + '/'.join(parts[i:])
        return name

    def resolve(self, name):
        """Full-path resolution."""
        cur = name
        for m in (self.classes, self.clash, self.inferred):
            n = m.get(cur)
            if n:
                cur = n
        cur = self.apply_pkg(cur)
        for m in (self.restruct, self.norm):
            n = m.get(cur)
            if n:
                cur = n
        return cur

    def resolve_split(self, pkg_path, simple):
        """Resolve a package + simple name pair independently."""
        parts = pkg_path.split('/')
        # walk from longest package prefix that appears as a key to shortest
        new_pkg = pkg_path
        for i in range(len(parts), 0, -1):
            pref = '/'.join(parts[:i])
            if pref in self.classes:
                new_pkg = self.classes[pref] + ('/' + '/'.join(parts[i:]) if i < len(parts) else '')
                break
        new_simple = simple
        for m in (self.clash, self.restruct, self.norm):
            if simple in m:
                v = m[simple]
                if '/' not in v:
                    new_simple = v
                    break
        return new_pkg, new_simple

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--out')
    ap.add_argument('--check', action='store_true')
    args = ap.parse_args()
    chain = RenameChain()
    print(f'loaded: classes={len(chain.classes)} clash={len(chain.clash)} '
          f'pkg={len(chain.pkg)} restruct={len(chain.restruct)} norm={len(chain.norm)}',
          file=sys.stderr)

    z = zipfile.ZipFile(LIBS_JAR)
    final = set(n[:-6] for n in z.namelist() if n.endswith('.class'))

    res_root = os.path.join(PROJECT, 'src', 'main', 'resources')
    ref_re = re.compile(r'com[./]moonsworth(?:[./][A-Za-z0-9_$]+)+')
    found = {}
    unresolved = []
    for root, dirs, files in os.walk(res_root):
        for fn in files:
            p = os.path.join(root, fn)
            if not (fn.endswith('.json') or 'services' in root or fn == 'Provider'):
                continue
            try:
                txt = open(p, encoding='utf-8', errors='replace').read()
            except Exception:
                continue
            for m in ref_re.finditer(txt):
                raw = m.group(0)
                name = raw.replace('.', '/')
                target = chain.resolve(name)
                if target in final:
                    found[name] = target
                else:
                    # try split approach using parent package + leaf
                    if '/' in name:
                        parent, leaf = name.rsplit('/', 1)
                        np, nl = chain.resolve_split(parent, leaf)
                        cand = np + '/' + nl
                        if cand in final:
                            found[name] = cand
                        else:
                            unresolved.append(name)
                    else:
                        unresolved.append(name)
    found = {k: v for k, v in found.items() if k != v}
    unresolved = sorted(set(unresolved))
    print(f'resolved: {len(found)}  unresolved: {len(unresolved)}', file=sys.stderr)
    for u in unresolved:
        print('  UNRESOLVED', u, file=sys.stderr)
    with open(args.out or '/dev/stdout', 'w', encoding='utf-8') as f:
        json.dump(found, f, indent=1, sort_keys=True)

if __name__ == '__main__':
    main()
