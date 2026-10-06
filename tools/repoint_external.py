#!/usr/bin/env python3
"""Delete shaded source copies and repoint every reference to the real library.

The tree contains flattened, decompiled copies of third-party libraries
(genesis/Guava, the root Jackson block, forge/lib, ...). Once the real library
is a build dependency (Maven), the copies can be deleted; this tool rewrites
all references to the real FQNs first so the tree still compiles.

Map format (tab-separated, `#` comments allowed):

    old.package<TAB>OldName<TAB>real.fqn.NewName<TAB>evidence

For every row the declaring file (old.package.OldName) is deleted, then:
* FQN occurrences `old.package.OldName` -> `real.fqn.NewName`
  (nested `OldName$Inner` is handled too)
* `import old.package.OldName;` -> `import real.fqn.NewName;`
* bare-name occurrences in files that imported it, lived in the old package,
  or wildcard-imported it -> `NewName`, guarded against same-name collisions

Dry-run by default. --apply rewrites files and `git rm`s the deleted ones.

Usage:
  tools/repoint_external.py --map <tsv> [--apply] [--verbose]
"""
import argparse
import os
import re
import subprocess
import sys
from collections import defaultdict

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from apply_class_moves import load_files  # noqa: E402

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, 'src/main/java')
IDENT_RE = re.compile(r'^[A-Za-z_$][A-Za-z0-9_$]*$')
BARE_TOKEN = re.compile(r'(?<![A-Za-z0-9_$.])([A-Za-z_$][A-Za-z0-9_$]*)')


def token_pattern(name):
    """Whole-token pattern; `$` is treated as an identifier char."""
    return re.compile(r'(?<![A-Za-z0-9_$.])' + re.escape(name) + r'(?![A-Za-z0-9_$])')


def read_map(path):
    rows = []
    for line in open(path, encoding='utf-8'):
        line = line.rstrip('\n')
        if not line or line.startswith('#') or line.startswith('old.package\t'):
            continue
        parts = line.split('\t')
        if len(parts) < 3:
            continue
        rows.append({'oldpkg': parts[0].strip(), 'old': parts[1].strip(),
                     'newfqn': parts[2].strip(),
                     'evidence': parts[3] if len(parts) > 3 else ''})
    return rows


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', required=True)
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--verbose', action='store_true')
    args = ap.parse_args()

    files = load_files()
    rows = read_map(args.map)
    print(f'[repoint] {len(rows)} rows; {len(files)} java files')

    prepared = []
    for r in rows:
        if not IDENT_RE.match(r['old']) or '.' not in r['newfqn']:
            sys.exit(f"invalid row: {r}")
        new_simple = r['newfqn'].rsplit('.', 1)[-1]
        if not IDENT_RE.match(new_simple):
            sys.exit(f"invalid target class name: {r['newfqn']}")
        old_fqn = r['oldpkg'] + '.' + r['old']
        cand = [rel for rel, info in files.items()
                if info['pkg'] == r['oldpkg'] and os.path.basename(rel)[:-5] == r['old']]
        if not cand:
            print(f"  SKIP {old_fqn}: declaring file not found")
            continue
        prepared.append({'rel': cand[0], 'oldpkg': r['oldpkg'], 'old': r['old'],
                         'old_fqn': old_fqn, 'newfqn': r['newfqn'],
                         'new_simple': new_simple})
    deletes_set = {p['rel'] for p in prepared}

    # combined FQN pattern: one pass per file
    fqn_map = {p['old_fqn']: p['newfqn'] for p in prepared}
    fqn_alt = None
    if fqn_map:
        alts = '|'.join(sorted((re.escape(k) for k in fqn_map), key=len, reverse=True))
        fqn_alt = re.compile(r'(?<![A-Za-z0-9_$.])(' + alts + r')(?![A-Za-z0-9_])')

    by_name = defaultdict(list)
    for p in prepared:
        by_name[p['old']].append(p)

    edits = defaultdict(int)
    skipped_bare = []
    for rel, info in files.items():
        if rel in deletes_set:
            continue
        t = orig = info['text']

        if fqn_alt is not None:
            t = fqn_alt.sub(lambda m: fqn_map[m.group(1)], t)

        # bare-name pass over the identifiers actually present in the file
        tokens = set(BARE_TOKEN.findall(t))
        for token in tokens:
            for p in by_name.get(token, ()):
                old, new_simple, new_fqn = p['old'], p['new_simple'], p['newfqn']
                new_pkg = new_fqn.rsplit('.', 1)[0]
                n_imp = info['single'].get(old) == p['old_fqn']
                same_pkg = info['pkg'] == p['oldpkg']
                saw_wild = p['oldpkg'] in info['wildcard']
                # a different class with the same simple name shadows the old
                # package's member: the bare token is NOT the deleted class
                shadow_old = info['single'].get(old)
                if shadow_old is not None and shadow_old != p['old_fqn']:
                    continue
                if new_simple == old:
                    # Same simple name, new package. The FQN pass already fixed
                    # explicit imports/FQNs. Only same-package resolution
                    # definitely needs the new import; wildcard-only references
                    # are ambiguous, so leave them (they surface as errors if
                    # they really were the deleted class).
                    if same_pkg and new_pkg != info['pkg'] and not re.search(
                            r'^\s*import\s+(?:static\s+)?' + re.escape(new_fqn) + r'\s*;', t, re.M):
                        imps = list(re.finditer(r'^\s*import\s+[^;]+;', t, re.M))
                        pos = imps[-1].end() if imps else None
                        if pos is None:
                            pm = re.search(r'^\s*package\s+[\w.]+\s*;', t, re.M)
                            pos = pm.end() if pm else 0
                        t = t[:pos] + '\nimport ' + new_fqn + ';' + t[pos:]
                        edits[rel] += 1
                    continue
                if not (n_imp or same_pkg or saw_wild):
                    continue
                shadow = info['single'].get(new_simple)
                if shadow is not None and shadow != new_fqn:
                    skipped_bare.append((rel, p['old_fqn'],
                                         f'{new_simple} already imported from {shadow}'))
                    continue
                if re.search(r'\b(?:class|interface|enum|record)\s+' +
                             re.escape(new_simple) + r'\b', t):
                    skipped_bare.append((rel, p['old_fqn'],
                                         f'declares its own {new_simple}'))
                    continue
                new_pkg = new_fqn.rsplit('.', 1)[0]
                if new_pkg != info['pkg'] and not re.search(
                        r'^\s*import\s+(?:static\s+)?' + re.escape(new_fqn) + r'\s*;', t, re.M):
                    imps = list(re.finditer(r'^\s*import\s+[^;]+;', t, re.M))
                    pos = imps[-1].end() if imps else None
                    if pos is None:
                        pm = re.search(r'^\s*package\s+[\w.]+\s*;', t, re.M)
                        pos = pm.end() if pm else 0
                    t = t[:pos] + '\nimport ' + new_fqn + ';' + t[pos:]
                    edits[rel] += 1
                t, n_bare = token_pattern(old).subn(new_simple, t)
                edits[rel] += n_bare

        if t != orig:
            info['text'] = t

    changed = {rel: info for rel, info in files.items() if edits[rel]}
    print(f'[repoint] deletes: {len(deletes_set)}; files rewritten: {len(changed)}; '
          f'edit actions: {sum(edits.values())}')
    if skipped_bare:
        print(f'[repoint] {len(skipped_bare)} bare-name rewrites skipped (collisions):')
        for rel, fqn, why in skipped_bare[:25]:
            print(f'   {rel}: {fqn} -- {why}')
    if args.verbose:
        for rel in sorted(changed):
            print(f'   {rel}: {edits[rel]}')

    if not args.apply:
        print('[repoint] dry run; pass --apply to write')
        return 0

    for rel, info in changed.items():
        with open(info['path'], 'w', encoding='utf-8') as fh:
            fh.write(info['text'])
    if deletes_set:
        subprocess.run(['git', 'rm', '-q', '--ignore-unmatch'] +
                       [os.path.join(SRC, d) for d in sorted(deletes_set)], check=False)
    print(f'[repoint] applied: {len(changed)} files rewritten, {len(deletes_set)} deleted')
    return 0


if __name__ == '__main__':
    sys.exit(main())
