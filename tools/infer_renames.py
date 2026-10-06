#!/usr/bin/env python3
"""Infer readable class names from decompiled Lunar sources.

High-confidence rules only:

1. Mixins: a class annotated with ``@Mixin(Target.class)`` (or
   ``@Mixin(targets = "a.b.Target")``) is renamed ``TargetMixin``. This is the
   naming convention used by the wider modding ecosystem.
2. Mods/modules: a class implementing a ``String getName()`` / ``getId()``
   method that returns an ALL_CAPS constant is renamed after that constant in
   PascalCase (e.g. ``ATTACK_INDICATOR`` -> ``AttackIndicator``).

Usage: infer_renames.py <decompiled-root> <out.tsv> [--apply-map]
"""
import argparse
import os
import re
import sys

GIBBERISH = re.compile(r'^[CHOIR]{8,}_?$')
MIXIN_DECL = re.compile(
    r'@Mixin\(\s*(?P<args>[^)]*)\)\s*'
    r'(?:@[\w.$]+(?:\([^)]*\))?\s*)*'
    r'(?:public\s+|protected\s+|private\s+|final\s+|abstract\s+|static\s+)*'
    r'class\s+(?P<name>[\w$]+)',
    re.S)
TARGET_CLASS = re.compile(r'([\w.$]+)\.class')
TARGET_STRING = re.compile(r'targets\s*=\s*\{?\s*"([\w.$]+)"')
NAME_METHOD = re.compile(
    r'public\s+String\s+(?:getName|getId|getModId|getModuleName)\(\)\s*\{\s*'
    r'return\s+"([^"]+)"\s*;', re.S)
ALL_CAPS = re.compile(r'^[A-Z][A-Z0-9_]{2,}$')


def pascal(cid):
    return ''.join(p.capitalize() for p in cid.split('_') if p)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('root')
    ap.add_argument('out')
    ap.add_argument('--limit', type=int, default=0)
    args = ap.parse_args()

    existing = set()
    for dirpath, _, files in os.walk(args.root):
        for f in files:
            if f.endswith('.java'):
                existing.add(os.path.join(dirpath, f))

    fqcn_of = {}
    for path in existing:
        rel = os.path.relpath(path, args.root)
        fqcn_of[rel[:-5].replace(os.sep, '/')] = path

    renames = {}
    mixin_count = 0
    mod_count = 0

    for fqcn, path in fqcn_of.items():
        simple = fqcn.rsplit('/', 1)[-1]
        if '$' in simple:
            continue  # inner classes follow their outer's rename
        try:
            text = open(path, encoding='utf-8', errors='replace').read()
        except OSError:
            continue
        pkg = fqcn.rsplit('/', 1)[0] if '/' in fqcn else ''

        # rule 1: mixins
        m = MIXIN_DECL.search(text)
        if m and GIBBERISH.match(simple) or (m and simple.endswith('_')):
            target = None
            tm = TARGET_CLASS.search(m.group('args'))
            if tm:
                target = tm.group(1)
            else:
                tm = TARGET_STRING.search(m.group('args'))
                if tm:
                    target = tm.group(1)
            if target:
                tail = target.rsplit('.', 1)[-1]
                if '$' in tail:
                    outer, inner = tail.rsplit('$', 1)
                    # anonymous targets (Outer$2) are named after the outer
                    base = inner if not inner.isdigit() else outer
                else:
                    base = tail
                base = re.sub(r'[^\w$]', '', base) or 'Anonymous'
                new_simple = base + 'Mixin'
                if not re.match(r'^[A-Za-z_$]', new_simple):
                    new_simple = 'Mixin' + new_simple
                if new_simple != simple:
                    renames[fqcn] = (pkg + '/' if pkg else '') + new_simple
                    mixin_count += 1
                    continue

        # rule 2: mods / modules
        nm = NAME_METHOD.search(text)
        if nm and ALL_CAPS.match(nm.group(1)):
            new_simple = pascal(nm.group(1))
            if new_simple != simple:
                renames[fqcn] = (pkg + '/' if pkg else '') + new_simple
                mod_count += 1

    # resolve collisions
    used = set(fqcn_of)
    final = {}
    for old, new in renames.items():
        candidate = new
        i = 2
        while candidate in used and candidate != old:
            candidate = f'{new}{i}'
            i += 1
        used.add(candidate)
        final[old] = candidate

    # include inner classes of renamed outers
    expanded = dict(final)
    for old in final:
        for fqcn in fqcn_of:
            if fqcn.startswith(old + '$'):
                expanded[fqcn] = final[old] + fqcn[len(old):]

    with open(args.out, 'w', encoding='utf-8') as f:
        for old in sorted(expanded):
            f.write(f'{old}\t{expanded[old]}\n')
    print(f'mixins renamed: {mixin_count}')
    print(f'mods/modules renamed: {mod_count}')
    print(f'total class renames (incl. inners): {len(expanded)} -> {args.out}')


if __name__ == '__main__':
    main()
