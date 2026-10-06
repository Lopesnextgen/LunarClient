#!/usr/bin/env python3
"""Apply member (method/field) rename maps to declarations and call sites.

Map format (headerless TSV, 5 columns):

    owner.fqn <TAB> M|F <TAB> oldName <TAB> newName <TAB> evidence

The tool renames declarations (methods/fields) in the owner files and every
access whose receiver type resolves to the owner or one of its ancestors.

Receiver-type resolution is source-only: a hierarchy index is built by parsing
`class X extends Y implements Z` headers once, so no javap/JVM is ever spawned
(fix_obf_tokens-style ancestor walks were a javap-per-lookup bottleneck).
The access pass is parallel, pre-filtered with a probe regex, and resumable
(already-renamed sites no longer match).

Usage:
  tools/apply_member_renames.py --map tools/renames/members-options.tsv [--apply] [--jobs 10]
"""
import argparse
import multiprocessing
import os
import re
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import fix_obf_tokens as fot  # noqa: E402

MEMBER_DECL = (r'^(?P<pre>\s*(?:(?:@\w+(?:\([^)]*\))?'
               r'|(?:public|protected|private|static|final|abstract|default|synchronized|native|strictfp)'
               r'|[\w$.<>\[\],?])+\s+)+)'
               r'(?P<name>%s)(?P<suf>\s*\()')
FIELD_DECL = (r'^(?P<pre>\s*(?:(?:@\w+(?:\([^)]*\))?'
              r'|(?:public|protected|private|static|final|volatile|transient)'
              r'|[\w$.<>\[\],?])+\s+)+)'
              r'(?P<name>%s)(?P<suf>\s*(?:=|;))')
TYPE_TOKEN = r'[\w$.<>\[\],?]+'
FIELD_SCAN = re.compile(r'\b(' + TYPE_TOKEN + r')\s+([A-Za-z_$][\w$]*)\s*(?:=|;)')
HEADER = re.compile(r'\b(?:class|interface|enum)\s+(?P<name>[\w$]+)')
EXTENDS = re.compile(r'\bextends\s+([\w$.]+)')
IMPLEMENTS = re.compile(r'\bimplements\s+([\w$.,\s]+)')

# Globals inherited by forked workers (or rebuilt in each worker).
_RES = None
_PROBE = None
_ACCESS = None
_METHOD_REF = None
_CAST_ACCESS = None
_BARE = None


class MemResolver:
    """Source-only resolver: class index, hierarchy and the member rename maps."""

    def __init__(self, maps):
        self.src_of = {}
        src = fot.SRC
        for dp, _dirs, fs in os.walk(src):
            for f in fs:
                if f.endswith('.java'):
                    rel = os.path.relpath(os.path.join(dp, f), src)
                    self.src_of[rel[:-5].replace(os.sep, '/')] = os.path.join(dp, f)
        self.existing = set(self.src_of)
        self.by_owner = {}
        self.old_names = set()
        for path in maps:
            for line in open(path, encoding='utf-8', errors='replace'):
                if not line.strip() or line.startswith('#'):
                    continue
                parts = line.rstrip('\n').split('\t')
                if len(parts) < 4:
                    continue
                owner, kind, old, new = parts[:4]
                self.by_owner.setdefault(owner.replace('.', '/'), {})[old] = (kind, new)
                self.old_names.add(old)
        self._build_hierarchy()

    # -- hierarchy (source-only; no javap) -----------------------------------
    def qualify(self, simple, context_fqcn, head=None):
        if simple in ('Object',):
            return None
        if '.' in simple:
            return simple.replace('.', '/')
        pkg = context_fqcn.rsplit('/', 1)[0]
        if not head:
            src = self.src_of.get(context_fqcn)
            head = open(src, errors='replace').read(4096) if src else ''
        if pkg + '/' + simple in self.existing:
            return pkg + '/' + simple
        for m in fot.IMPORT.finditer(head):
            if m.group(1).split('.')[-1] == simple:
                return m.group(1).replace('.', '/')
        if os.path.exists(os.path.join(fot.SRC, pkg, simple + '.java')):
            return pkg + '/' + simple
        return None

    def _build_hierarchy(self):
        parents = {}
        for fqcn, path in self.src_of.items():
            try:
                text = open(path, encoding='utf-8', errors='replace').read(8192)
            except OSError:
                continue
            stem = os.path.basename(path)[:-5]
            ps = self._parse_parents(text, fqcn, stem)
            if ps:
                parents[fqcn] = ps
        children = {}
        for child, ps in parents.items():
            for p in ps:
                children.setdefault(p, []).append(child)
        # owner_chain[class] = owners that are ancestors of class
        self.owner_chain = {}
        for owner in self.by_owner:
            stack = list(children.get(owner, []))
            seen = set()
            while stack:
                c = stack.pop()
                if c in seen:
                    continue
                seen.add(c)
                self.owner_chain.setdefault(c, []).append(owner)
                stack += children.get(c, [])

    def _parse_parents(self, text, fqcn, stem):
        m = HEADER.search(text)
        if not m:
            return []
        name = m.group('name')
        # only trust the declaration that names this file's class
        if name != stem and name != stem.split('$')[0]:
            return []
        i = m.end()
        while i < len(text) and text[i] in ' \t':
            i += 1
        # skip the class's own type parameters (generic bounds are NOT parents)
        if i < len(text) and text[i] == '<':
            depth = 0
            while i < len(text):
                c = text[i]
                if c == '<':
                    depth += 1
                elif c == '>':
                    depth -= 1
                    if depth == 0:
                        i += 1
                        break
                i += 1
        j = text.find('{', i)
        rest = text[i:j] if j != -1 else text[i:i + 500]
        names = []
        ext = EXTENDS.search(rest)
        if ext:
            names.append(ext.group(1))
        imp = IMPLEMENTS.search(rest)
        if imp:
            names += [p.strip() for p in imp.group(1).split(',')]
        resolved = []
        for n in names:
            n = re.sub(r'<.*>', '', n).strip()
            q = self.qualify(n, fqcn, text[:4096])
            if q:
                resolved.append(q)
        return resolved

    def lookup_member(self, owner, name):
        if owner is None:
            return None
        hit = self.by_owner.get(owner, {}).get(name)
        if hit:
            return hit
        for anc in self.owner_chain.get(owner, ()):
            hit = self.by_owner.get(anc, {}).get(name)
            if hit:
                return hit
        return None


def owner_file(res, owner):
    """Source file declaring this owner (handles nested owners)."""
    src = res.src_of.get(owner)
    if src:
        return src
    parts = owner.split('/')
    for i in range(1, len(parts)):
        cand = '/'.join(parts[:len(parts) - i]) + '$' + '$'.join(parts[len(parts) - i:])
        if cand in res.src_of:
            return res.src_of[cand]
    for i in range(1, len(parts)):
        outer = '/'.join(parts[:-i])
        if outer in res.src_of:
            return res.src_of[outer]
    return None


def drop_multi_decl_rows(res, verbose=True):
    """Remove rows whose member is declared more than once in the owner file.

    Renaming `method1` -> `section` when the file also declares another
    `method1` overload would rename both applications and all call sites,
    breaking overload resolution (the SPLIT cases in the research notes).
    Those need a signature-aware pass; skipping keeps the tree compiling.
    """
    dropped = []
    for owner in list(res.by_owner):
        src = owner_file(res, owner)
        if not src or not os.path.exists(src):
            continue
        text = open(src, encoding='utf-8', errors='replace').read()
        for old in list(res.by_owner[owner]):
            kind, new = res.by_owner[owner][old]
            pattern = re.compile((MEMBER_DECL if kind == 'M' else FIELD_DECL) % re.escape(old))
            n = sum(1 for line in text.split('\n') if pattern.match(line))
            if n != 1:
                del res.by_owner[owner][old]
                dropped.append((owner, old, new, n))
    if verbose:
        for owner, old, new, n in dropped:
            print(f'  skip {owner}.{old} ({n} declarations) -> would have been {new}')
    return dropped


def rename_declarations(res, apply, verbose=False):
    total = 0
    for owner, members in sorted(res.by_owner.items()):
        src = owner_file(res, owner)
        if not src or not os.path.exists(src):
            print(f'  !! no source for {owner}')
            continue
        text = open(src, encoding='utf-8', errors='replace').read()
        changed = False
        for old, (kind, new) in sorted(members.items()):
            pattern = re.compile((MEMBER_DECL if kind == 'M' else FIELD_DECL) % re.escape(old))
            lines = text.split('\n')
            n = 0
            for i, line in enumerate(lines):
                m = pattern.match(line)
                if m:
                    lines[i] = line[:m.start('name')] + new + line[m.end('name'):]
                    n += 1
            text = '\n'.join(lines)
            text, k = re.subn(r'\b(this|super)\.' + re.escape(old) + r'\b', r'\1.' + new, text)
            n += k
            if n:
                changed = True
                if verbose:
                    print(f'  decl {owner}.{old} -> {new} ({n})')
            total += n
        if apply and changed:
            open(src, 'w', encoding='utf-8').write(text)
    return total


def _file_type_maps(text):
    """name -> [(position, type)] so a receiver resolves to the declaration in
    scope (decompiled varN names are reused with different types per method)."""
    decls = {}

    def add(pos, name, typ):
        typ = re.sub(r'<.*>', '', typ).strip('[]? ')
        if typ:
            decls.setdefault(name, []).append((pos, typ))

    for m in fot.LOCAL_DECL.finditer(text):
        add(m.start(2), m.group(2), m.group(1))
    for m in FIELD_SCAN.finditer(text):
        add(m.start(2), m.group(2), m.group(1))
    for v in decls.values():
        v.sort()
    return decls


def _type_at(decls, name, pos):
    lst = decls.get(name)
    if not lst:
        return None
    import bisect
    i = bisect.bisect_left(lst, (pos,)) - 1
    if i < 0:
        return None
    return lst[i][1]


def access_worker(item):
    fqcn, path = item
    res = _RES
    try:
        text = open(path, encoding='utf-8', errors='replace').read()
    except OSError:
        return path, 0, None
    if not _PROBE.search(text):
        return path, 0, None
    head = text[:8192]
    pkg = re.search(r'^package\s+([\w.]+)\s*;', text, re.M)
    if not pkg:
        return path, 0, None
    self_fqcn = pkg.group(1).replace('.', '/') + '/' + os.path.basename(path)[:-5]
    decls = _file_type_maps(text)
    class_cache = {}

    def owner_for(recv, pos):
        if recv in ('this', 'super'):
            return self_fqcn
        base = recv
        if recv.startswith(('this.', 'super.')):
            base = recv.split('.', 1)[1].split('.')[0]
        elif base and base[0].isupper() and '.' not in base:
            cached = class_cache.get(base)
            if cached is None:
                cached = res.qualify(base, self_fqcn, head) or ''
                class_cache[base] = cached
            if cached:
                return cached
        t = _type_at(decls, base, pos)
        if t:
            return res.qualify(t, self_fqcn, head)
        return None

    hits = []
    for regex in (_ACCESS, _METHOD_REF):
        for m in regex.finditer(text):
            recv, name = m.group('recv'), m.group('name')
            hit = res.lookup_member(owner_for(recv, m.start('recv')), name)
            if hit:
                hits.append((m.start(), m.end(),
                             recv + m.group(0)[len(recv):-len(name)] + hit[1]))
    for m in _CAST_ACCESS.finditer(text):
        cast_type = res.qualify(m.group('t'), self_fqcn, head)
        hit = res.lookup_member(cast_type, m.group('name'))
        if hit:
            hits.append((m.start('name'), m.end('name'), hit[1]))
    n = len(hits)
    # apply by offset, longest/latest first: plain text.replace() would clobber
    # prefixes (`method1` inside `method10` -> `percentage0`)
    for s, e, new_s in sorted(hits, key=lambda t: -t[0]):
        if text[s:e] != new_s:
            text = text[:s] + new_s + text[e:]
    if self_fqcn in res.by_owner:
        for old in sorted(res.by_owner[self_fqcn], key=len, reverse=True):
            kind, new = res.by_owner[self_fqcn][old]
            if kind != 'M':
                continue
            text, k = _BARE.subn(lambda m: new + '(' if m.group('name') == old else m.group(0), text)
            n += k
    return path, n, text if n else None


def _build_patterns(res):
    global _PROBE, _ACCESS, _METHOD_REF, _CAST_ACCESS, _BARE
    name_re = '|'.join(re.escape(n) for n in sorted(res.old_names, key=len, reverse=True))
    _PROBE = re.compile(name_re)
    _ACCESS = re.compile(r'(?P<recv>[A-Za-z_$][\w$.]*|this|super)\.(?P<name>' + name_re + r')\b')
    _METHOD_REF = re.compile(r'(?P<recv>[A-Za-z_$][\w$.]*|this|super)::(?P<name>' + name_re + r')\b')
    _CAST_ACCESS = re.compile(r'\(\(\s*(?P<t>[\w$.]+)\s*\)(?P<inner>[^();]{1,60}(?:\([^()]*\))?[^();]{0,60})\)\.'
                              r'(?P<name>' + name_re + r')\b')
    _BARE = re.compile(r'(?<![\w.$])(?P<name>' + name_re + r')\s*\(')


def _worker_init(map_paths):
    global _RES
    _RES = MemResolver(map_paths)
    _build_patterns(_RES)


def rename_accesses(res, apply, map_paths, jobs=10, verbose=False):
    global _RES
    _build_patterns(res)
    _RES = res
    items = [(fqcn, path) for fqcn, path in sorted(res.src_of.items()) if os.path.exists(path)]
    if jobs > 1:
        pool = multiprocessing.Pool(jobs, initializer=_worker_init, initargs=(map_paths,))
        try:
            results = pool.map(access_worker, items, chunksize=16)
        finally:
            pool.close()
            pool.join()
    else:
        results = [access_worker(it) for it in items]
    total = sum(n for _p, n, _t in results)
    if apply:
        for path, n, text in results:
            if text is not None:
                open(path, 'w', encoding='utf-8').write(text)
    if verbose:
        for path, n, _t in results:
            if n:
                print(f'  {n:5d}  {os.path.relpath(path, fot.ROOT)}')
    return total


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', action='append', required=True)
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--jobs', type=int, default=10)
    ap.add_argument('--allow-multi-decl', action='store_true',
                    help='also rename members declared more than once (default: skip them)')
    ap.add_argument('--verbose', action='store_true')
    args = ap.parse_args()

    res = MemResolver(args.map)
    print(f'[members] {sum(len(m) for m in res.by_owner.values())} rows over '
          f'{len(res.by_owner)} owners from {len(args.map)} map(s)')
    if not args.allow_multi_decl:
        drop_multi_decl_rows(res, verbose=args.verbose)
        print(f'[members] after multi-decl filter: '
              f'{sum(len(m) for m in res.by_owner.values())} rows')
    d = rename_declarations(res, args.apply, args.verbose)
    print(f'[members] declarations {"renamed" if args.apply else "to rename"}: {d}')
    a = rename_accesses(res, args.apply, args.map, args.jobs, args.verbose)
    print(f'[members] accesses {"renamed" if args.apply else "to rename"}: {a} '
          f'({"APPLY" if args.apply else "dry-run"})')
    return 0


if __name__ == '__main__':
    sys.exit(main())
