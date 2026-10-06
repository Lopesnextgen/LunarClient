#!/usr/bin/env python3
"""Per-class package move applier (the missing half of the restructure).

Neither existing applier can split a junk-drawer package: the class applier
only renames within the same package, the package applier moves whole
prefixes. This one moves individual classes:

Map format (tab-separated, `#` comments allowed):

    old.package<TAB>OldName<TAB>new.package<TAB>NewName[<TAB>evidence[<TAB>file]]

NewName == OldName means move-only. Optional 6th column `file` scopes the
declaration (same semantics as the aware applier).

For each row it: git mv's the file, rewrites its `package` declaration, and
updates references tree-wide (FQN + import-aware bare names, same guards as
`apply_class_renames_aware.py`: no FQN-segment matches, no cross-package
collisions).

Usage:
  tools/apply_class_moves.py --map <tsv> [--apply] [--verbose]
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
PKG_RE = re.compile(r'^(\s*package\s+)([\w.$]+)(\s*;)', re.M)
IMPORT_RE = re.compile(r'^\s*import\s+(?:static\s+)?([\w.$]+?)(\.\*)?\s*;', re.M)
IDENT_RE = re.compile(r'^[A-Z][A-Za-z0-9_$]*$')
SEG_RE = re.compile(r'^[A-Za-z_][A-Za-z0-9_]*$')


def token_pattern(name):
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
            pkg_name = pkg.group(2) if pkg else ''
            files[rel] = {'path': path, 'text': text,
                          'pkg': pkg_name, 'orig_pkg': pkg_name,
                          'single': single, 'wildcard': wildcard}
    return files


def declarations(files):
    decl_packages = defaultdict(set)
    for rel, info in files.items():
        for m in DECL_RE.finditer(info['text']):
            decl_packages[m.group(2)].add(info['pkg'])
    return decl_packages


def read_map(path):
    rows = []
    for line in open(path, encoding='utf-8'):
        line = line.rstrip('\n')
        if not line or line.startswith('#') or line.startswith('package\t'):
            continue
        parts = line.split('\t')
        if len(parts) < 4:
            continue
        rows.append({'oldpkg': parts[0].strip(), 'old': parts[1].strip(),
                     'newpkg': parts[2].strip(), 'new': parts[3].strip(),
                     'evidence': parts[4] if len(parts) > 4 else '',
                     'file': parts[5].strip() if len(parts) > 5 else None})
    return rows


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', required=True)
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--verbose', action='store_true')
    args = ap.parse_args()

    files = load_files()
    decl_packages = declarations(files)
    rows = read_map(args.map)
    print(f'[class-moves] {len(rows)} rows; {len(files)} java files')

    # validate
    for r in rows:
        if not IDENT_RE.match(r['new']):
            sys.exit(f"invalid class name: {r['new']}")
        for seg in r['newpkg'].split('.'):
            if not SEG_RE.match(seg):
                sys.exit(f"invalid package segment in {r['newpkg']}")

    edits = defaultdict(int)
    fqn_map = {}    # old_fqn -> new_fqn, applied to every file in one pass
    moves = []      # (old_rel, new_rel, oldpkg, newpkg)
    skipped = 0
    used_new = set()
    for r in rows:
        oldpkg, old, newpkg, new = r['oldpkg'], r['old'], r['newpkg'], r['new']
        declaring = decl_packages.get(old, set())
        if oldpkg not in declaring:
            print(f'  SKIP {old}: not declared in {oldpkg}')
            skipped += 1
            continue
        if new in decl_packages and (newpkg in decl_packages.get(new, set())) and (old, oldpkg) != (new, newpkg):
            print(f'  SKIP {oldpkg}.{old} -> {newpkg}.{new}: target already declared')
            skipped += 1
            continue
        if (newpkg, new) in used_new:
            print(f'  SKIP {oldpkg}.{old}: target {newpkg}.{new} already used in this map')
            skipped += 1
            continue
        used_new.add((newpkg, new))
        # locate declaring file
        cand = [rel for rel, info in files.items()
                if info['pkg'] == oldpkg and os.path.basename(rel)[:-5] == old
                and (not r['file'] or rel == r['file'])]
        if not cand:
            print(f'  SKIP {oldpkg}.{old}: declaring file not found')
            skipped += 1
            continue
        old_rel = cand[0]
        new_rel = os.path.join(newpkg.replace('.', '/'), new + '.java')
        moves.append((old_rel, new_rel, oldpkg, newpkg, old, new))
        fqn_map[oldpkg + '.' + old] = newpkg + '.' + new

    # one alternation for the whole FQN pass instead of N substitutions/file
    fqn_alt = None
    if fqn_map:
        alts = '|'.join(sorted((re.escape(k) for k in fqn_map), key=len, reverse=True))
        fqn_alt = re.compile(r'(?<![A-Za-z0-9_$])(' + alts + r')(?![A-Za-z0-9_$])')

    # rewrite package declarations of moved files
    for old_rel, new_rel, oldpkg, newpkg, old, new in moves:
        info = files[old_rel]
        info['text'], n = PKG_RE.subn(lambda m: m.group(1) + newpkg + m.group(3), info['text'], count=1)
        # rename declaration + all self-refs (constructors, self types) if class renamed too.
        # ponytail: whole-file token replace; boundaries keep Fov from touching Fov_3.
        if new != old:
            info['text'] = token_pattern(old).sub(new, info['text']) if os.path.basename(old_rel)[:-5] == old else info['text']
        info['pkg'] = newpkg
        edits[old_rel] += n + (1 if new != old else 0)

    # rewrite references tree-wide (single alternation pass)
    if fqn_alt is not None:
        for rel, info in files.items():
            t = fqn_alt.sub(lambda m: fqn_map[m.group(1)], info['text'])
            if t != info['text']:
                info['text'] = t
                edits[rel] += 1

    # bare-name + import rewrites per move (import-aware, like aware applier).
    # Patterns are hoisted out of the file loop: compiling them per file made
    # this O(moves x files) regex compilations.
    move_specs = []
    for old_rel, new_rel, oldpkg, newpkg, old, new in moves:
        old_fqn, new_fqn = oldpkg + '.' + old, newpkg + '.' + new
        move_specs.append((
            old_rel, oldpkg, newpkg, old, new, old_fqn, new_fqn,
            token_pattern(old),
            re.compile(r'^(\s*import\s+(?:static\s+)?)' + re.escape(old_fqn) + r'(\s*;)', re.M),
            re.compile(r'^(\s*import\s+(?:static\s+)?)' + re.escape(oldpkg) + r'\.\*(\s*;)', re.M),
            re.compile(r'^\s*import\s+(?:static\s+)?' + re.escape(new_fqn) + r'\s*;', re.M),
        ))
    for spec in move_specs:
        (old_rel, oldpkg, newpkg, old, new, old_fqn, new_fqn,
         bare, imp_old, wild_old, imp_new) = spec
        for rel, info in files.items():
            # cheap short-circuit: most moves are absent from most files
            if old not in info['text']:
                continue
            t = info['text']
            # update single import of the moved class
            t2, n_imp = imp_old.subn(lambda m: m.group(1) + new_fqn + m.group(2), t)
            # update wildcard import of old package -> add single import? keep
            # wildcard but it no longer covers the class; add explicit import
            if wild_old.search(t2) and not imp_new.search(t2):
                pass  # handled by bare-name guard below (wildcard no longer resolves)
            t = t2
            allowed = False
            # orig_pkg: `pkg` is already rewritten for moved files by the
            # declaration pass above; use the pre-move package for resolution.
            orig_pkg = info.get('orig_pkg', info['pkg'])
            if orig_pkg in (oldpkg, newpkg):
                # same package before or after the move: the bare name is in
                # scope, but a single import of a *different* class with the
                # same simple name shadows it (e.g. gson TypeAdapter).
                shadow = info['single'].get(old)
                allowed = shadow is None or shadow == old_fqn
            else:
                imported = info['single'].get(old)
                if imported == old_fqn:
                    allowed = True
                elif imported is None and oldpkg in info['wildcard'] and new == old:
                    rivals = [w for w in info['wildcard'] if w != oldpkg and w in decl_packages.get(old, set())]
                    # after move the class is no longer in oldpkg: only rename
                    # if the file also sees the new location
                    allowed = False
            if allowed or n_imp:
                # ensure import of new location exists when bare name used
                if new == old and info['pkg'] != newpkg and not imp_new.search(t):
                    # file referenced via old single import (now rewritten) or
                    # same-package (now moved away): import already fixed above
                    pass
                t, n_bare = bare.subn(new, t)
                if t != info['text']:
                    info['text'] = t
                    edits[rel] += n_bare + n_imp
            elif n_imp:
                info['text'] = t
                edits[rel] += n_imp

    # left-behind import pass: a file moved out of oldpkg may still reference
    # siblings that stayed (often jar-only lazy classes like fov.Fov_3 with no
    # source declaration). Same-package bare refs break on move -> add imports.
    # ponytail: source decls + compile-classpath jar index; skip on ambiguity.
    import zipfile as _zf
    jar_pkgs = defaultdict(set)
    _jar = os.path.join(ROOT, 'libs', 'lunar-renamed-classes.jar')
    if os.path.exists(_jar):
        try:
            with _zf.ZipFile(_jar) as _z:
                for _n in _z.namelist():
                    if _n.endswith('.class') and '/' in _n and '$' not in _n:
                        _pkg = _n[:_n.rfind('/')].replace('/', '.')
                        _cls = _n[_n.rfind('/') + 1:-6]
                        if IDENT_RE.match(_cls):
                            jar_pkgs[_cls].add(_pkg)
        except Exception:
            pass
    moved_rels = {old_rel for old_rel, _, _, _, _, _ in moves}
    oldpkg_of = {old_rel: op for old_rel, _, op, _, _, _ in moves}
    newloc = {(op + '.' + o): (np + '.' + n) for _, _, op, np, o, n in moves}
    # Post-move locations by simple name, for classes renamed *in this map*
    # (the bare-ref pass above rewrote the token, so the left-behind scan sees
    # the new name and cannot look it up in the pre-move decl index).
    final_by_name = defaultdict(set)
    for r in rows:
        if not r['file']:
            final_by_name[r['new']].add(r['newpkg'] + '.' + r['new'])
    own_decl_re = re.compile(r'\b(?:class|interface|enum)\s+([A-Za-z_$][A-Za-z0-9_$]*)')
    for old_rel in moved_rels:
        info = files[old_rel]
        if info['pkg'] == oldpkg_of[old_rel]:
            continue
        t = info['text']
        own = set(own_decl_re.findall(t))
        added = []
        for m in set(re.findall(r'(?<![A-Za-z0-9_$.])([A-Z][A-Za-z0-9_$]*)(?![A-Za-z0-9_$])', t)):
            if m in added or m in own or info['single'].get(m):
                continue
            providers = set(decl_packages.get(m, set())) | set(jar_pkgs.get(m, set())) \
                | set(final_by_name.get(m, set()))
            if info['pkg'] in providers:
                continue  # same-package resolves
            # Prefer the file's old package: at HEAD the bare ref resolved via
            # same-package (oldpkg), so oldpkg is the proven home even when the
            # name exists in several packages (e.g. fov.Fov3 vs fov.mixin.Fov3).
            fqn = None
            if len(final_by_name.get(m, ())) == 1:
                fqn = next(iter(final_by_name[m]))
            elif oldpkg_of[old_rel] in providers:
                fqn = oldpkg_of[old_rel] + '.' + m
                fqn = newloc.get(fqn, fqn)  # follow sibling moves (fov.VertexBuilder -> emote.VertexBuilder)
            if fqn is None:
                continue
            if fqn.rsplit('.', 1)[0] == info['pkg']:
                continue  # sibling landed in the same new package
            if not re.search(r'^\s*import\s+(?:static\s+)?' + re.escape(fqn) + r'\s*;', t, re.M):
                imps = list(IMPORT_RE.finditer(t))
                if imps:
                    pos = imps[-1].end()
                    t = t[:pos] + '\nimport ' + fqn + ';' + t[pos:]
                else:
                    pm = PKG_RE.search(t)
                    t = t[:pm.end()] + '\nimport ' + fqn + ';' + t[pm.end():] if pm else t
                added.append(m)
        if added:
            info['text'] = t
            edits[old_rel] += len(added)

    # stay-put import pass: files that did NOT move but live in a package a
    # class just left lose their same-package bare refs (e.g. a bridge file in
    # `bridge.minecraft` referencing Horsestats20Extension2 moved back to
    # `bridge.horsestats`). Add the import for the departed sibling.
    departed = defaultdict(list)  # oldpkg -> [(new_fqn, new_simple_name)]
    for _old_rel, _new_rel, _oldpkg, _newpkg, _old, _new in moves:
        if _oldpkg != _newpkg:
            departed[_oldpkg].append((_newpkg + '.' + _new, _new))
    for rel, info in files.items():
        if rel in moved_rels:
            continue  # already covered above
        upserts = departed.get(info['orig_pkg'])
        if not upserts:
            continue
        t = info['text']
        own = set(own_decl_re.findall(t))
        changed = False
        for fqn, new_name in upserts:
            if new_name in own or info['single'].get(new_name):
                continue
            if not token_pattern(new_name).search(t):
                continue
            if fqn.rsplit('.', 1)[0] == info['pkg']:
                continue
            if re.search(r'^\s*import\s+(?:static\s+)?' + re.escape(fqn) + r'\s*;', t, re.M):
                continue
            imps = list(IMPORT_RE.finditer(t))
            if imps:
                pos = imps[-1].end()
                t = t[:pos] + '\nimport ' + fqn + ';' + t[pos:]
            else:
                pm = PKG_RE.search(t)
                if pm:
                    t = t[:pm.end()] + '\nimport ' + fqn + ';' + t[pm.end():]
            changed = True
        if changed:
            info['text'] = t
            edits[rel] += 1

    print(f'[class-moves] rows={len(rows)} skipped={skipped} files_moved={len(moves)} '
          f'files_touched={len(edits)} mode={"APPLY" if args.apply else "dry-run"}')
    if not args.apply:
        for a, b, _, _, _, _ in moves[:20]:
            print(f'   {a}  ->  {b}')
        return 0

    for rel, info in files.items():
        if edits.get(rel):
            open(info['path'], 'w', encoding='utf-8').write(info['text'])
    for old_rel, new_rel, _, _, _, _ in moves:
        src = os.path.join(SRC, old_rel)
        dst = os.path.join(SRC, new_rel)
        if os.path.abspath(src) == os.path.abspath(dst):
            continue  # move-only row already in place
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        subprocess.run(['git', 'mv', '-f', src, dst], cwd=ROOT, check=False)
    return 0


if __name__ == '__main__':
    sys.exit(main())
