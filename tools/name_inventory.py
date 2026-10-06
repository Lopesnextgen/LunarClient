#!/usr/bin/env python3
"""Inventory of lazy/placeholder class names in the source tree.

Groups class basenames that match the placeholder patterns the old pipeline
left behind (MixinN, DataN, FrameworkN, GuiN, object-oriented junk, trailing
digits, ...) by package, so the renamer work can be split into clusters.

Usage: tools/name_inventory.py [--out tools/renames/inventory.tsv]
"""
import argparse
import os
import re
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')

# class Foo / class Foo123 / class FooMixin2 etc.
CLASS_RE = re.compile(r'\b(?:class|interface|enum)\s+([A-Za-z_$][A-Za-z0-9_$]*)')

# A basename is "lazy" when it ends in digits, or is a *MixinN / *HandlerN /
# known placeholder stem + digits.
LAZY_RE = re.compile(
    r'^('
    r'[A-Za-z_]*\d+'
    r')$'
)
STEMS = ('Mixin', 'Data', 'Framework', 'Gui', 'Handler', 'Manager', 'Helper',
         'Object', 'Holder', 'Wrapper', 'Util', 'Impl', 'Type', 'Entry',
         'Iterator', 'Loader', 'List', 'Map', 'Set', 'Factory', 'Builder')

NESTED_RE = re.compile(r'\$')


def is_lazy(name: str) -> bool:
    if NESTED_RE.search(name):
        return False
    if re.match(r'^[A-Za-z_]+[0-9]+$', name):
        return True
    for stem in STEMS:
        if name.startswith(stem) and name != stem and re.search(r'\d+$', name):
            return True
    return False


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--out', default=os.path.join(ROOT, 'tools/renames/inventory.tsv'))
    args = parser.parse_args()

    by_package = defaultdict(list)
    simple_to_packages = defaultdict(set)
    for dirpath, _dirs, files in os.walk(SRC):
        for f in files:
            if not f.endswith('.java'):
                continue
            path = os.path.join(dirpath, f)
            rel = os.path.relpath(path, SRC)
            pkg = os.path.dirname(rel).replace(os.sep, '.')
            try:
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            for match in CLASS_RE.finditer(text):
                name = match.group(1)
                if is_lazy(name):
                    by_package[pkg].append((name, rel))
                    simple_to_packages[name].add(pkg)

    os.makedirs(os.path.dirname(args.out), exist_ok=True)
    total = 0
    with open(args.out, 'w', encoding='utf-8') as out:
        out.write('package\tclass\tfile\n')
        for pkg in sorted(by_package):
            for name, rel in sorted(set(by_package[pkg])):
                out.write(f'{pkg}\t{name}\t{rel}\n')
                total += 1
    print(f'[inventory] {total} lazy class declarations across {len(by_package)} packages')
    print(f'[inventory] wrote {args.out}')

    # name collisions across packages make global token replacement unsafe
    collisions = {n: pkgs for n, pkgs in simple_to_packages.items() if len(pkgs) > 1}
    print(f'[inventory] simple names used in >1 package: {len(collisions)}')


if __name__ == '__main__':
    main()
