#!/usr/bin/env python3
"""Apply class rename maps to the source tree.

Input: a TSV of  package <TAB> oldName <TAB> newName [<TAB> evidence...]
(one row per top-level type; produced by the renamer subagents).

For each row this tool:
  * validates the new name is a legal type identifier
  * only applies renames whose simple name is declared in exactly one package
    (or when --allow-collisions is given, trusting the row's package)
  * rewrites every word-boundary occurrence of the old simple name in
    src/main/java (declarations, references, nested-type qualifiers)
  * renames the declaring .java file (and its Outer$Inner companions)
  * rewrites the same token in src/main/resources (mixin configs list classes
    by simple name)

Run without --apply for a dry run.

Usage:
  tools/apply_class_renames.py --map tools/renames/classes-<cluster>.tsv [--apply]
"""
import argparse
import os
import re
import subprocess
import sys
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
RES = os.path.join(ROOT, 'src/main/resources')

DECL_RE = re.compile(r'^\s*(?:public\s+|final\s+|abstract\s+)*(class|interface|enum|@interface|record)\s+([A-Za-z_$][A-Za-z0-9_$]*)', re.M)
IDENT_RE = re.compile(r'^[A-Z][A-Za-z0-9_]*$')
MAX_BYTES = 4 * 1024 * 1024


def java_files():
    out = []
    for dirpath, _dirs, files in os.walk(SRC):
        for name in files:
            if name.endswith('.java'):
                out.append(os.path.join(dirpath, name))
    return sorted(out)


def resource_files():
    out = []
    for dirpath, _dirs, files in os.walk(RES):
        for name in files:
            if name.endswith(('.json', '.txt', '.xml', '.properties', '.yml', '.yaml')):
                out.append(os.path.join(dirpath, name))
    return sorted(out)


def build_index(files):
    declarations = defaultdict(set)  # simple name -> set(package)
    owner_file = {}  # (package, simple name) -> file path
    for path in files:
        rel = os.path.relpath(path, SRC)
        pkg = os.path.dirname(rel).replace(os.sep, '.')
        try:
            text = open(path, encoding='utf-8', errors='replace').read()
        except OSError:
            continue
        for match in DECL_RE.finditer(text):
            name = match.group(2)
            declarations[name].add(pkg)
            owner_file[(pkg, name)] = path
    return declarations, owner_file


def read_map(path):
    rows = []
    for line in open(path, encoding='utf-8'):
        line = line.rstrip('\n')
        if not line or line.startswith('#') or line.startswith('package\t'):
            continue
        parts = line.split('\t')
        if len(parts) < 3:
            continue
        rows.append((parts[0].strip(), parts[1].strip(), parts[2].strip()))
    return rows


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--map', required=True)
    parser.add_argument('--apply', action='store_true')
    parser.add_argument('--allow-collisions', action='store_true')
    args = parser.parse_args()

    files = java_files()
    resources = resource_files()
    declarations, owner_file = build_index(files)
    rows = read_map(args.map)
    print(f'[class-renames] {len(rows)} rows; {len(files)} java files')

    applied = 0
    skipped = 0
    touched = set()
    renamed_files = []
    for pkg, old, new in rows:
        if not IDENT_RE.match(new):
            print(f'  SKIP illegal new name: {pkg} {old} -> {new}')
            skipped += 1
            continue
        if old == new:
            continue
        declaring = declarations.get(old, set())
        if len(declaring) > 1 and not args.allow_collisions:
            print(f'  SKIP collision: {old} declared in {sorted(declaring)}')
            skipped += 1
            continue
        if new in declarations and new != old:
            print(f'  SKIP {old} -> {new}: new name already declared in '
                  f'{sorted(declarations[new])}')
            skipped += 1
            continue
        if pkg not in declaring:
            print(f'  SKIP {old}: not declared in {pkg} (declared in {sorted(declaring) or "nowhere"})')
            skipped += 1
            continue
        pattern = re.compile(r'(?<![A-Za-z0-9_$])' + re.escape(old) + r'(?![A-Za-z0-9_$])')
        changed = 0
        for path in files:
            try:
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            repl = pattern.subn(new, text)
            if repl[1]:
                changed += repl[1]
                touched.add(path)
                if args.apply:
                    open(path, 'w', encoding='utf-8').write(repl[0])
        for path in resources:
            try:
                if os.path.getsize(path) > MAX_BYTES:
                    continue
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            repl = pattern.subn(new, text)
            if repl[1]:
                changed += repl[1]
                if args.apply:
                    open(path, 'w', encoding='utf-8').write(repl[0])
        # rename declaring files: exact stem matches only. Names containing
        # '$' are separate top-level identifiers in this tree (the old pipeline
        # flattened nested classes that way), so Bridge3 must not rename
        # Bridge3$Extension.java unless that name has its own map row.
        for path in files:
            base = os.path.basename(path)
            stem = base[:-5]
            if stem != old:
                continue
            new_stem = new
            new_path = os.path.join(os.path.dirname(path), new_stem + '.java')
            renamed_files.append((path, new_path))
            if args.apply:
                subprocess.run(['git', 'mv', '-f', path, new_path], cwd=ROOT, check=False)
        applied += 1
        print(f'  {pkg}: {old} -> {new} ({changed} token hits)')
    print(f'[class-renames] applied={applied} skipped={skipped} '
          f'files_touched={len(touched)} files_renamed={len(renamed_files)} '
          f'mode={"APPLY" if args.apply else "dry-run"}')


if __name__ == '__main__':
    main()
