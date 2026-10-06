#!/usr/bin/env python3
"""Apply package rename / move maps (the missing half of the restructure).

The existing renamer only moved *classes*; there was no applier for moving a
whole package (e.g. `com.moonsworth.lunar.client.tps` -> `.client.render`),
which is why wrongly-named packages survived every wave.

Map format (tab-separated, `#` comments allowed):

    old.package.prefix<TAB>new.package.prefix[<TAB>evidence]

Prefix semantics: every package equal to `old` or starting with `old + "."`
is moved to `new` + the suffix.  Files are `git mv`-ed, their `package`
declaration rewritten, and all fully-qualified references tree-wide updated.

Usage:
  tools/apply_package_renames.py --map <tsv> [--apply] [--verbose]
"""
import argparse
import os
import re
import subprocess
import sys
import zipfile
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
RES = os.path.join(ROOT, 'src/main/resources')

PKG_RE = re.compile(r'^(\s*package\s+)([\w.]+)(\s*;)', re.M)
SEG_RE = re.compile(r'^[A-Za-z_][A-Za-z0-9_]*$')
IMPORT_RE = re.compile(r'^\s*import\s+(?:static\s+)?([\w.$]+?)(\.\*)?\s*;', re.M)
DECL_RE = re.compile(r'\b(?:class|interface|enum)\s+([A-Za-z_$][A-Za-z0-9_$]*)')
IDENT_RE = re.compile(r'^[A-Z][A-Za-z0-9_$]*$')


def token_pattern(name):
    return re.compile(r'(?<![A-Za-z0-9_$.])' + re.escape(name) + r'(?![A-Za-z0-9_$])')


def read_map(path):
    rows = []
    for line in open(path, encoding='utf-8'):
        line = line.rstrip('\n')
        if not line or line.startswith('#') or line.startswith('package\t'):
            continue
        parts = line.split('\t')
        if len(parts) < 2:
            continue
        old, new = parts[0].strip(), parts[1].strip()
        if old and new and old != new:
            rows.append((old, new, parts[2] if len(parts) > 2 else ''))
    return rows


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', required=True)
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--verbose', action='store_true')
    args = ap.parse_args()

    rows = read_map(args.map)
    # longest prefix first so nested mappings apply correctly
    rows.sort(key=lambda r: -len(r[0]))

    for old, new, _ev in rows:
        for seg in new.split('.'):
            if not SEG_RE.match(seg):
                sys.exit(f'invalid package segment in "{new}"')

    def remap(pkg):
        for old, new, _ev in rows:
            if pkg == old:
                return new
            if pkg.startswith(old + '.'):
                return new + pkg[len(old):]
        return None

    # 1. collect files per package (from the package declaration)
    files = {}
    for dirpath, _dirs, names in os.walk(SRC):
        for n in names:
            if not n.endswith('.java'):
                continue
            path = os.path.join(dirpath, n)
            rel = os.path.relpath(path, SRC)
            try:
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            m = PKG_RE.search(text)
            if not m:
                continue
            files[rel] = {'path': path, 'text': text, 'pkg': m.group(2),
                          'orig_pkg': m.group(2),
                          'single': {f.rsplit('.', 1)[-1]: f for f, star in IMPORT_RE.findall(text)
                                     if not star}}

    moves = []          # (old_rel, new_rel)
    edits = defaultdict(int)
    for rel, info in files.items():
        new_pkg = remap(info['pkg'])
        if not new_pkg or new_pkg == info['pkg']:
            continue
        info['text'], n = PKG_RE.subn(lambda m: m.group(1) + new_pkg + m.group(3), info['text'], count=1)
        new_dir = new_pkg.replace('.', '/')
        new_rel = os.path.join(new_dir, os.path.basename(rel))
        moves.append((rel, new_rel))
        edits[rel] += n

    # 2. rewrite fully-qualified references tree-wide -- but ONLY for classes
    # that actually moved. A prefix-wide rewrite corrupts references to
    # classes that were never in the tree (quarantined, resolving via the
    # stale libs jar): e.g. moving `a.mixin` -> `a` must not rewrite
    # `a.mixin.Foo` when Foo.java was never moved (Foo lives in quarantine).
    moved = []  # (old_fqn, new_fqn)
    for old_rel, new_rel in moves:
        old_stem = os.path.basename(old_rel)[:-len('.java')]
        new_stem = os.path.basename(new_rel)[:-len('.java')]
        old_pkg = os.path.dirname(old_rel).replace(os.sep, '.')
        new_pkg = os.path.dirname(new_rel).replace(os.sep, '.')
        moved.append((old_pkg + '.' + old_stem, new_pkg + '.' + new_stem))
    if moves:
        pats = [(re.compile(r'(?<![\w$.])' + re.escape(o) + r'(?![\w])'), n)
                for o, n in moved]
        for rel, info in files.items():
            t = info['text']
            for pat, new_fqn in pats:
                t = pat.sub(new_fqn, t)
            if t != info['text']:
                info['text'] = t
                edits[rel] += 1

    # left-behind import pass: a moved file may still reference classes that
    # stayed in the old package as jar-only stubs (e.g. Lightoverlay6) or that
    # moved to a third package. Same-package bare refs break on move -> add an
    # explicit import for the proven old-package location.
    moved_owner = {}
    for old_rel, new_rel in moves:
        old_fqn = os.path.dirname(old_rel).replace(os.sep, '.') + '.' + os.path.basename(old_rel)[:-5]
        new_fqn = os.path.dirname(new_rel).replace(os.sep, '.') + '.' + os.path.basename(new_rel)[:-5]
        moved_owner[old_fqn] = new_fqn
    final_pkgs = defaultdict(set)
    for rel, info in files.items():
        pkg = remap(info['orig_pkg']) or info['orig_pkg']
        for name in set(DECL_RE.findall(info['text'])):
            final_pkgs[name].add(pkg)
    jar_pkgs = defaultdict(set)
    _jar = os.path.join(ROOT, 'libs', 'lunar-renamed-classes.jar')
    if os.path.exists(_jar):
        try:
            with zipfile.ZipFile(_jar) as _z:
                for _n in _z.namelist():
                    if _n.endswith('.class') and '/' in _n and '$' not in _n:
                        _cls = _n[_n.rfind('/') + 1:-6]
                        if IDENT_RE.match(_cls):
                            jar_pkgs[_cls].add(_n[:_n.rfind('/')].replace('/', '.'))
        except Exception:
            pass
    for old_rel, new_rel in moves:
        info = files[old_rel]
        oldpkg = info['orig_pkg']
        newpkg = os.path.dirname(new_rel).replace(os.sep, '.')
        t = info['text']
        own = set(DECL_RE.findall(t))
        added = []
        for m in set(re.findall(r'(?<![A-Za-z0-9_$.])([A-Z][A-Za-z0-9_$]*)(?![A-Za-z0-9_$])', t)):
            if m in added or m in own or info['single'].get(m):
                continue
            providers = set(final_pkgs.get(m, set())) | set(jar_pkgs.get(m, set()))
            if newpkg in providers:
                continue  # same-package resolves
            old_fqn = oldpkg + '.' + m
            fqn = None
            if old_fqn in moved_owner:
                fqn = moved_owner[old_fqn]
            elif oldpkg in providers:
                fqn = old_fqn  # jar-only sibling stays in the old package
            if fqn is None or fqn.rsplit('.', 1)[0] == newpkg:
                continue
            if not re.search(r'^\s*import\s+(?:static\s+)?' + re.escape(fqn) + r'\s*;', t, re.M):
                imps = list(IMPORT_RE.finditer(t))
                if imps:
                    pos = imps[-1].end()
                    t = t[:pos] + '\nimport ' + fqn + ';' + t[pos:]
                else:
                    pm = PKG_RE.search(t)
                    if pm:
                        t = t[:pm.end()] + '\nimport ' + fqn + ';' + t[pm.end():]
                added.append(m)
        if added:
            info['text'] = t
            edits[old_rel] += len(added)

    n_moved = len(moves)
    print(f'[pkg-renames] rows={len(rows)} files_moved={n_moved} '
          f'files_touched={len(edits)} mode={"APPLY" if args.apply else "dry-run"}')

    if not args.apply:
        for a, b in moves[:20]:
            print(f'   {a}  ->  {b}')
        return 0

    for rel, info in files.items():
        if edits.get(rel):
            open(info['path'], 'w', encoding='utf-8').write(info['text'])
    for old_rel, new_rel in moves:
        src = os.path.join(SRC, old_rel)
        dst = os.path.join(SRC, new_rel)
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        subprocess.run(['git', 'mv', '-f', src, dst], cwd=ROOT, check=False)
    return 0


if __name__ == '__main__':
    sys.exit(main())
