#!/usr/bin/env python3
"""Import-aware class rename applier (v2).

The v1 applier only handles globally-unique simple names. Many clusters
(e.g. the misnamed `highlight` event-system package) have the same simple name
(`HighlightImpl17`) declared in many packages, so a global token replace would
corrupt unrelated classes.

This tool resolves each simple-name reference from the file's package and
imports:

  * same package                              -> bare name
  * `import pkg.Old;`                         -> bare name + `pkg.Old` FQN
  * `import pkg.*;` and pkg is the only
    wildcard-imported package declaring Old   -> bare name + FQN
  * `pkg.Old` FQN anywhere                    -> FQN

Rows may carry an optional 5th column `file` to scope a declaration rename to a
specific relative file (for nested/duplicate declarations in one package).

Usage:
  tools/apply_class_renames_aware.py --map <tsv> [--apply] [--verbose]
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
PKG_RE = re.compile(r'^\s*package\s+([\w.$]+)\s*;', re.M)
IMPORT_RE = re.compile(r'^\s*import\s+(?:static\s+)?([\w.$]+?)(\.\*)?\s*;', re.M)
IDENT_RE = re.compile(r'^[A-Z][A-Za-z0-9_$]*$')


def token_pattern(name):
    # bare identifiers: never preceded by a dot (that would be a qualified
    # segment handled by the FQN pattern) or another identifier char
    return re.compile(r'(?<![A-Za-z0-9_$.])' + re.escape(name) + r'(?![A-Za-z0-9_$])')


def load_files():
    files = {}
    for dirpath, _dirs, names in os.walk(SRC):
        for name in names:
            if not name.endswith('.java'):
                continue
            path = os.path.join(dirpath, name)
            rel = os.path.relpath(path, SRC)
            try:
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            pkg = PKG_RE.search(text)
            imports = IMPORT_RE.findall(text)
            single = {}
            wildcard = []
            for fqn, star in imports:
                if star:
                    wildcard.append(fqn)
                else:
                    single[fqn.rsplit('.', 1)[-1]] = fqn
            files[rel] = {
                'path': path,
                'text': text,
                'pkg': pkg.group(1) if pkg else '',
                'single': single,
                'wildcard': wildcard,
            }
    return files


def declarations(files):
    decl_packages = defaultdict(set)
    for rel, info in files.items():
        for match in DECL_RE.finditer(info['text']):
            decl_packages[match.group(2)].add(info['pkg'])
    return decl_packages


def read_map(path):
    rows = []
    for line in open(path, encoding='utf-8'):
        line = line.rstrip('\n')
        if not line or line.startswith('#') or line.startswith('package\t'):
            continue
        parts = line.split('\t')
        if len(parts) < 3:
            continue
        row = {
            'package': parts[0].strip(),
            'old': parts[1].strip(),
            'new': parts[2].strip(),
            'evidence': parts[3] if len(parts) > 3 else '',
            'file': parts[4].strip() if len(parts) > 4 else None,
        }
        rows.append(row)
    return rows


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--map', required=True)
    parser.add_argument('--apply', action='store_true')
    parser.add_argument('--verbose', action='store_true')
    args = parser.parse_args()

    files = load_files()
    decl_packages = declarations(files)
    rows = read_map(args.map)
    # nested rows first: renaming an owner class rewrites qualified
    # Owner.Nested references and would destroy the nested row's pattern
    nested_first = []
    normal = []
    for row in rows:
        nested = re.search(
            r'(?<![\w$])[A-Za-z_$][\w$]*[.$]' + re.escape(row['old']) + r'(?![\w$])',
            row['evidence'])
        (nested_first if nested else normal).append(row)
    rows = nested_first + normal
    print(f'[aware-renames] {len(rows)} rows ({len(nested_first)} nested); {len(files)} java files')

    edits = defaultdict(int)   # rel -> count
    renames = []
    skipped = 0
    applied_via_nested = []
    used_new_names = set()
    for row in rows:
        pkg, old, new = row['package'], row['old'], row['new']
        if not IDENT_RE.match(new) or old == new:
            skipped += 1
            continue
        # Nested-class row. Two forms: old may be the full Owner$Inner name,
        # or evidence names the owner as Owner$Old.  Exception: the decompiled
        # tree often stores nested classes as *separate top-level files* named
        # `Owner$Inner.java` declaring `class Owner$Inner`.  Those must be
        # handled by the normal top-level path (file rename + token replace).
        top_level_dollar = False
        if '$' in old:
            cand = os.path.join(pkg.replace('.', '/'), old + '.java')
            if cand in files and re.search(
                    r'\b(?:class|interface|enum|record|@interface)\s+'
                    + re.escape(old) + r'\b', files[cand]['text']):
                top_level_dollar = True
        if '$' in old and not top_level_dollar:
            owner, inner = old.rsplit('$', 1)
        else:
            nested = re.search(r'(?<![\w$])([A-Za-z_$][\w$]*)[.$]' + re.escape(old) + r'(?![\w$])',
                               row['evidence'])
            owner = nested.group(1) if nested else None
            inner = old
        owner_rel = os.path.join(pkg.replace('.', '/'), owner + '.java') if owner else None
        if owner and not top_level_dollar and owner_rel in files and re.search(
                r'\b(?:class|interface|enum|record|@interface)\s+' + re.escape(inner) + r'\b',
                files[owner_rel]['text']):
            owner_pattern = token_pattern(inner)
            owner_fqn = re.compile(
                r'(?<![A-Za-z0-9_$])' + re.escape(owner)
                + r'((?:\$|\.)' + re.escape(inner) + r')(?![A-Za-z0-9_$])')
            owner_single = pkg + '.' + owner + '.' + inner
            hit = 0
            for rel, info in files.items():
                text = info['text']
                local = owner_fqn.subn(lambda m: owner + ('$' if m.group(1).startswith('$') else '.')
                                       + new, text)[0]
                if rel == owner_rel or info['single'].get(inner) == owner_single:
                    local = owner_pattern.subn(new, local)[0]
                if local != text:
                    info['text'] = local
                    hit += 1
                    edits[rel] += 1
            if hit:
                applied_via_nested.append((pkg, owner + '$' + inner, new, hit))
                if args.verbose:
                    print(f'  {pkg}: {owner}$${inner} -> {new} ({hit} files)')
            else:
                print(f'  WARN nested {pkg} {owner}$${inner}: no references found')
            continue
        declaring = decl_packages.get(old, set())
        if pkg not in declaring:
            print(f'  SKIP {old}: not declared in {pkg}')
            skipped += 1
            continue
        if new in decl_packages and new not in (old,):
            print(f'  SKIP {old} -> {new}: new name already declared')
            skipped += 1
            continue
        if new in used_new_names:
            print(f'  SKIP {old} -> {new}: new name already used in this map')
            skipped += 1
            continue
        used_new_names.add(new)
        fqn = pkg + '.' + old
        new_fqn = pkg + '.' + new
        bare = token_pattern(old)
        fqn_pat = re.compile(r'(?<![A-Za-z0-9_$])' + re.escape(fqn) + r'(?![A-Za-z0-9_$])')

        touched_here = 0
        # A 5th-column `file` scopes the *declaration* rename.  When the row
        # renames a top-level declaration (file stem == old), references in
        # other files must still be updated, so only skip other files for
        # nested/duplicate-declaration rows.
        owner_row = bool(row['file']) and os.path.basename(row['file'])[:-5] == old
        for rel, info in files.items():
            if row['file'] and rel != row['file'] and not owner_row:
                continue
            text = info['text']
            local = text

            # FQN occurrences always rename for the declaring package
            local, n_fqn = fqn_pat.subn(new_fqn, local)

            # bare-name resolution
            allowed = False
            if row['file'] and rel == row['file']:
                allowed = True  # explicitly scoped declaration
            elif info['pkg'] == pkg and os.path.basename(rel)[:-5] == old:
                allowed = True  # the declaring top-level file always renames
            elif info['pkg'] == pkg:
                # a single-type import of another package's Old shadows us
                shadow = info['single'].get(old)
                allowed = shadow is None or shadow == fqn
            else:
                imported = info['single'].get(old)
                if imported == fqn:
                    allowed = True
                elif imported is None and any(v.startswith(fqn + '.')
                                             for v in info['single'].values()):
                    # file imports a nested class of Old (pkg.Old.Nested); the
                    # owner token must still be rewritten
                    allowed = True
                elif imported is None and pkg in info['wildcard']:
                    # ambiguous if another wildcard package also declares Old
                    rivals = [w for w in info['wildcard']
                              if w != pkg and w in decl_packages.get(old, set())]
                    allowed = not rivals
            if allowed:
                local, n_bare = bare.subn(new, local)
            else:
                n_bare = 0

            if local != text:
                edits[rel] += n_bare + n_fqn
                info['text'] = local
                touched_here += n_bare + n_fqn
        if not touched_here:
            print(f'  WARN {pkg} {old}: no references found')
        # declaration file rename (exact stem)
        for rel, info in files.items():
            if row['file'] and rel != row['file']:
                continue
            stem = os.path.basename(rel)[:-5]
            if stem == old and info['pkg'] == pkg:
                renames.append((rel, os.path.join(os.path.dirname(rel), new + '.java')))
        if args.verbose:
            print(f'  {pkg}: {old} -> {new} ({touched_here} hits)')

    if args.apply:
        for rel, info in files.items():
            if rel in edits and edits[rel]:
                open(info['path'], 'w', encoding='utf-8').write(info['text'])
        seen = set()
        for old_rel, new_rel in renames:
            if old_rel in seen:
                continue
            seen.add(old_rel)
            src = os.path.join(SRC, old_rel)
            dst = os.path.join(SRC, new_rel)
            subprocess.run(['git', 'mv', '-f', src, dst], cwd=ROOT, check=False)
    print(f'[aware-renames] rows={len(rows)} skipped={skipped} '
          f'files_touched={len(edits)} files_renamed={len(renames)} '
          f'mode={"APPLY" if args.apply else "dry-run"}')


if __name__ == '__main__':
    main()
