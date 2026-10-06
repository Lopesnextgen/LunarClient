#!/usr/bin/env python3
"""Resolve leftover obfuscated member tokens in decompiled sources.

The restructure pass renamed class/member names across the tree, but many call
sites still reference obfuscated member names (e.g.
`this.OHRIHIRHCRRHORCHCOOCHRCHOOHICR()`, `TOKEN(...)`, `field.TOKEN`). For
classes resolved from libs/lunar-renamed-classes.jar these stop compiling.

The tool resolves each token against the rename tables
(tools/mappings-snapshot/member-renames.tsv) through the receiver's type and
its ancestor chain, then rewrites it.

Usage:
  tools/fix_obf_tokens.py --check [--tree DIR | FILE...]
  tools/fix_obf_tokens.py --apply [--tree DIR | FILE...]
  tools/fix_obf_tokens.py --report unresolved.txt ...
"""
import argparse
import collections
import os
import re
import subprocess
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import qa_loop  # noqa: E402
SNAP = os.path.join(ROOT, 'tools/mappings-snapshot')
SRC = os.path.join(ROOT, 'src/main/java')
JAVAP = os.environ.get('QA_JAVAP') or qa_loop.java_bin('javap')

TOKEN = r'[A-Z][A-Z]{15,}'
TOKEN_RE = re.compile(r'\b(' + TOKEN + r')\b')
# receiver.TOKEN( / (this|super|ident|Class).TOKEN
MEMBER_ACCESS = re.compile(r'(?P<recv>[A-Za-z_$][\w$.]*|this|super)\.(?P<tok>' + TOKEN + r')\b(?P<call>\s*\()?')
CLASS_DECL = re.compile(r'\b(?:class|interface|enum)\s+([\w$]+)(?:\s+extends\s+([\w$.]+))?')
IMPORT = re.compile(r'^import\s+(?:static\s+)?([\w$.]+)\s*;', re.M)
LOCAL_DECL = re.compile(
    r'(?:^|[;{(,]\s*)(?:final\s+)?([A-Z][\w$.<>\[\]]*)\s+([a-zA-Z_$][\w$]*)\s*(?=[=,;)]|\s*\))')


def load_tsv(path, keycol=0, valcol=1):
    out = {}
    if not os.path.exists(path):
        return out
    with open(path, encoding='utf-8', errors='replace') as fh:
        for line in fh:
            if not line.strip() or line.startswith('#'):
                continue
            parts = line.rstrip('\n').split('\t')
            if len(parts) > max(keycol, valcol):
                out[parts[keycol]] = parts[valcol]
    return out


class Resolver:
    def __init__(self):
        self.classes = load_tsv(os.path.join(SNAP, 'classes.tsv'))
        self.clash = load_tsv(os.path.join(SNAP, 'class-renames.tsv'))
        self.pkg = load_tsv(os.path.join(SNAP, 'package-renames.tsv'))
        self.inferred = load_tsv(os.path.join(SNAP, 'inferred-renames.tsv'))
        self.restruct = {}
        for f in ('mixin-renames.tsv', 'module-renames.tsv', 'remaining-renames.tsv'):
            self.restruct.update(load_tsv(os.path.join(SNAP, 'restructure', f)))
        self.norm = load_tsv(os.path.join(SNAP, 'normalize-renames.tsv'))

        self.member_by_new = collections.defaultdict(lambda: collections.defaultdict(list))
        self._load_members(os.path.join(SNAP, 'member-renames.tsv'))
        self._load_members(os.path.join(ROOT, 'tools/work/mappings/member-renames.tsv'))

        self._ancestor_cache = {}
        self._parent_cache = {}
        self.src_of = {}
        for dp, _dirs, files in os.walk(SRC):
            for f in files:
                if f.endswith('.java'):
                    rel = os.path.relpath(os.path.join(dp, f), SRC)
                    self.src_of[rel[:-5].replace(os.sep, '/')] = os.path.join(dp, f)
        self.existing = set(self.src_of)

    def _load_members(self, path):
        if not os.path.exists(path):
            return
        with open(path, encoding='utf-8', errors='replace') as fh:
            for line in fh:
                parts = line.rstrip('\n').split('\t')
                if len(parts) < 5:
                    continue
                owner, kind, token, desc, new = parts[:5]
                self.member_by_new[self.resolve(owner)][token].append((kind, desc, new))

    def apply_pkg(self, name):
        parts = name.split('/')
        for i in range(len(parts) - 1, 0, -1):
            pref = '/'.join(parts[:i])
            if pref in self.pkg:
                return self.pkg[pref] + '/' + '/'.join(parts[i:])
        return name

    def resolve(self, name):
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

    def ancestors(self, fqcn, depth=10):
        if fqcn in self._ancestor_cache:
            return list(self._ancestor_cache[fqcn])
        out, seen, cur = [], [], fqcn
        for _ in range(depth):
            if cur in seen:
                break
            seen.append(cur)
            parent = None
            src = self.src_of.get(cur)
            if src and os.path.exists(src):
                with open(src, encoding='utf-8', errors='replace') as fh:
                    head = fh.read(4096)
                m = CLASS_DECL.search(head)
                if m and m.group(2):
                    parent = self.qualify(m.group(2), cur, head)
            if parent is None:
                parent = self.jar_parent(cur)
            if parent is None:
                break
            out.append(parent)
            cur = parent
        self._ancestor_cache[fqcn] = list(out)
        return list(out)

    def jar_parent(self, fqcn):
        if fqcn in self._parent_cache:
            return self._parent_cache[fqcn]
        parent = None
        # lunar-renamed-classes.jar is the vendored reference jar; everything
        # else (protocol API, third-party bundles) lives in libs/ as well.
        for jar in ('libs/lunar-renamed-classes.jar', 'libs/multiver-full/lunar.jar'):
            p = subprocess.run([JAVAP, '-classpath', os.path.join(ROOT, jar), fqcn],
                               stdout=subprocess.PIPE, stderr=subprocess.DEVNULL, text=True)
            m = re.search(r'class\s+\S+\s+extends\s+([\w.]+)', p.stdout)
            if m:
                parent = m.group(1).replace('.', '/')
                break
        self._parent_cache[fqcn] = parent
        return parent

    def qualify(self, simple, context_fqcn, head=None):
        if simple in ('Object',):
            return 'java/lang/Object'
        if '.' in simple:
            return simple.replace('.', '/')
        pkg = context_fqcn.rsplit('/', 1)[0]
        if not head:
            src = self.src_of.get(context_fqcn)
            head = open(src, errors='replace').read(4096) if src else ''
        if pkg + '/' + simple in self.existing:
            return pkg + '/' + simple
        for m in IMPORT.finditer(head):
            if m.group(1).split('.')[-1] == simple:
                return m.group(1).replace('.', '/')
        if os.path.exists(os.path.join(SRC, pkg, simple + '.java')):
            return pkg + '/' + simple
        return None

    def lookup(self, owner_fqcn, token):
        """Return (kind, name) or (None, candidates) if ambiguous/unresolved."""
        chain = [owner_fqcn] + self.ancestors(owner_fqcn)
        found = []
        for cls in chain:
            entries = self.member_by_new.get(cls, {}).get(token, [])
            names = {(k, n) for k, d, n in entries}
            if names:
                found = list(names)
                break
        if not found:
            return None, []
        if len(found) == 1:
            return found[0], []
        return None, [n for _k, n in found]

    def field_type(self, text, field):
        m = re.search(r'([\w$.<>\[\]]+)\s+' + re.escape(field) + r'\s*(?:[=;)]|\s*\))', text)
        if not m:
            return None
        t = re.sub(r'<.*>', '', m.group(1)).strip('[]? ')
        return t or None

    def local_type(self, text, var):
        best = None
        for m in LOCAL_DECL.finditer(text):
            if m.group(2) == var:
                best = m.group(1)
        if best:
            best = re.sub(r'<.*>', '', best).strip('[]? ')
        return best


def fix_file(res, path, apply=False, verbose=False):
    text = open(path, encoding='utf-8', errors='replace').read()
    pkg = re.search(r'^package\s+([\w.]+)\s*;', text, re.M)
    if not pkg:
        return 0, []
    fqcn = pkg.group(1).replace('.', '/') + '/' + os.path.basename(path)[:-5]
    head = text[:8192]

    def owner_for(recv):
        if recv in ('this', 'super'):
            return fqcn
        # this.field / super.field -> type of that field
        base = recv
        if recv.startswith(('this.', 'super.')):
            base = recv.split('.', 1)[1].split('.')[0]
        # imported/known class?
        if base and base[0].isupper() and '.' not in base:
            q = res.qualify(base, fqcn, head)
            if q:
                return q
        t = res.field_type(text, base) or res.local_type(text, base)
        if t:
            return res.qualify(t, fqcn, head)
        return None

    results = []
    for m in MEMBER_ACCESS.finditer(text):
        recv, token = m.group('recv'), m.group('tok')
        owner = owner_for(recv)
        if owner is None:
            results.append((token, recv, None, 'owner?'))
            continue
        (found, candidates) = res.lookup(owner, token)
        if found:
            kind, name = found
        else:
            kind, name = None, None
        if name:
            results.append((token, recv, name, kind))
        elif candidates:
            results.append((token, recv, None, 'ambiguous:' + '/'.join(sorted(set(candidates)))))
        else:
            results.append((token, recv, None, 'unresolved'))

    fixed = 0
    for token, recv, name, info in results:
        if not name:
            continue
        pattern = re.compile(r'(\b' + re.escape(recv) + r'\.)' + re.escape(token) + r'\b')
        text, n = pattern.subn(r'\g<1>' + name, text)
        fixed += n
    if apply and fixed:
        open(path, 'w', encoding='utf-8').write(text)
    return fixed, results


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--check', action='store_true')
    ap.add_argument('--tree')
    ap.add_argument('--report')
    ap.add_argument('files', nargs='*')
    args = ap.parse_args()
    res = Resolver()
    files = list(args.files)
    if args.tree:
        base = args.tree if os.path.isabs(args.tree) else os.path.join(ROOT, args.tree)
        for dp, _d, fs in os.walk(base):
            if 'forge/lib' in dp:
                continue
            for f in fs:
                if f.endswith('.java') and TOKEN_RE.search(open(os.path.join(dp, f), errors='ignore').read()):
                    files.append(os.path.join(dp, f))
    total = 0
    unresolved = []
    ambiguous = 0
    for f in files:
        n, results = fix_file(res, f, apply=args.apply)
        total += n
        if n:
            print(f'{f}: {n} fixed')
        for token, recv, name, info in results:
            if name is None:
                if info.startswith('ambiguous'):
                    ambiguous += 1
                rel = os.path.relpath(f, os.path.join(ROOT, 'src/main/java'))
                unresolved.append(f'{rel}\t{recv}\t{token}\t{info}')
    print('total fixed:', total, 'unresolved sites:', len(unresolved), 'ambiguous:', ambiguous)
    if args.report:
        with open(args.report, 'w') as fh:
            fh.write('\n'.join(unresolved) + '\n')


if __name__ == '__main__':
    main()
