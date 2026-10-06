#!/usr/bin/env python3
"""Rename lazy/decompiler parameter names to readable type-based names.

The decompiler's parameters are often `<lowered-type><number>` (e.g.
`highlightimpl141`, `gui2extension1`, `lightingextension231`) or the primitive
stems (`var1`, `value2`, `number3`, `text4`, `flag5`). This tool renames them
inside the declaring method only, using the declared type:

    EventMouseMove highlightimpl141 -> event
    SnaplookPerspective gui2extension1 -> perspective
    RootSettingsBuilder lightingextension231 -> settings
    boolean flag2 -> flag
    int number2 -> value
    List<Foo> list1 -> list
    Foo[] items0 -> items

Uniqueness is per method: if the derived name is taken, a numeric suffix is
appended (`event2`). Strings/comments are masked before rewriting, so literals
are never touched. Locals are out of scope (see tools/clean_locals.py).

Usage:
  tools/clean_params.py [--root src/main/java] [--apply] [--check] [--limit N]
"""
import argparse
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DEFAULT_ROOT = os.path.join(ROOT, 'src/main/java')

PRIMITIVES = {
    'boolean': 'flag', 'byte': 'value', 'short': 'value', 'char': 'character',
    'int': 'value', 'long': 'value', 'float': 'value', 'double': 'value',
}
CONTAINERS = [
    ('Event', 'event'), ('Perspective', 'perspective'), ('Settings', 'settings'),
    ('Builder', 'builder'), ('Option', 'option'), ('Packet', 'packet'),
    ('Data', 'data'), ('Handler', 'handler'), ('Listener', 'listener'),
    ('Manager', 'manager'), ('Renderer', 'renderer'), ('Provider', 'provider'),
    ('Factory', 'factory'), ('Registry', 'registry'), ('Module', 'module'),
    ('Feature', 'feature'), ('Entity', 'entity'), ('Player', 'player'),
    ('World', 'world'), ('Config', 'config'), ('State', 'state'),
    ('Map', 'map'), ('Set', 'set'), ('List', 'list'), ('Collection', 'list'),
    ('Iterable', 'iterable'), ('Iterator', 'iterator'), ('Optional', 'optional'),
    ('Queue', 'queue'), ('Deque', 'deque'), ('Stream', 'stream'),
    ('Predicate', 'predicate'), ('Consumer', 'consumer'), ('Supplier', 'supplier'),
    ('Function', 'function'), ('Runnable', 'runnable'),
    ('Class', 'type'), ('Thread', 'thread'), ('String', 'text'),
    ('CharSequence', 'text'),
]
JAVA_KEYWORDS = {
    'abstract','assert','boolean','break','byte','case','catch','char','class','const',
    'continue','default','do','double','else','enum','extends','final','finally',
    'float','for','goto','if','implements','import','instanceof','int','interface',
    'long','native','new','package','private','protected','public','return','short',
    'static','strictfp','super','switch','synchronized','this','throw','throws',
    'transient','try','void','volatile','while','true','false','null',
}

LAZY = re.compile(
    r'^(?:var|arg|value|number|text|flag|data|obj|object|type|field|entry|item|key|input|output|result'
    r'|list|map|set|iterator|thread|event|perspective|settings'
    r'|[a-z][a-z0-9]*?(?:impl|extension|base|handler|wrapper|iterator|loader|adapter|provider|builder|state|data|type|entry|list|map|set))\d+$')
METHOD_DECL = re.compile(
    r'(?P<indent>^[ \t]*)'
    r'(?P<mods>(?:(?:public|protected|private|static|final|abstract|synchronized|native|default|strictfp)\s+)*)'
    r'(?:<[^>{};]+>\s+)?'
    r'(?P<ret>[\w$.<>\[\],?\s]+?)\s+'
    r'(?P<name>[A-Za-z_$][\w$]*)\s*\((?P<params>[^)]*)\)\s*'
    r'(?:throws\s+[\w$.,\s]+)?\{', re.M)
PARAM = re.compile(r'(?:final\s+)?([\w$.<>\[\],?\s]+?)\s+([A-Za-z_$][\w$]*)\s*(?:,|$)')


def mask(text):
    """Replace string/char literals and comments with spaces (same offsets)."""
    out = list(text)
    i, n = 0, len(text)
    while i < n:
        c = text[i]
        if c == '"':
            j = i + 1
            while j < n and text[j] != '"':
                j += 2 if text[j] == '\\' else 1
            for k in range(i + 1, min(j, n)):
                out[k] = ' '
            i = j + 1
        elif c == "'":
            j = i + 1
            while j < n and text[j] != "'":
                j += 2 if text[j] == '\\' else 1
            for k in range(i + 1, min(j, n)):
                out[k] = ' '
            i = j + 1
        elif c == '/' and i + 1 < n and text[i + 1] == '/':
            j = text.find('\n', i)
            j = n if j < 0 else j
            for k in range(i, j):
                out[k] = ' '
            i = j
        elif c == '/' and i + 1 < n and text[i + 1] == '*':
            j = text.find('*/', i + 2)
            j = n if j < 0 else j + 2
            for k in range(i, j):
                out[k] = ' '
            i = j
        else:
            i += 1
    return ''.join(out)


def type_name(t):
    t = t.strip()
    t = re.sub(r'<.*>', '', t).strip()
    t = t.replace('...', '[]')
    if t.endswith('[]'):
        return 'items'
    simple = t.split('.')[-1].split('$')[-1]
    for key, name in CONTAINERS:
        if key in simple:
            return name
    if simple in PRIMITIVES:
        return PRIMITIVES[simple]
    name = simple
    for suffix in ('Impl', 'Extension', 'Provider', 'Adapter', 'Wrapper', 'Base'):
        if name.endswith(suffix) and len(name) > len(suffix) + 2:
            name = name[:-len(suffix)]
    if not name:
        return 'value'
    out = name[0].lower() + name[1:]
    if out in JAVA_KEYWORDS:
        out += 'Value'
    return out


def split_params(s):
    """Split a parameter list on top-level commas (<> and [] aware)."""
    out, cur, depth = [], [], 0
    for ch in s:
        if ch in '<([':
            depth += 1
        elif ch in '>)]':
            depth -= 1
        if ch == ',' and depth == 0:
            out.append(''.join(cur))
            cur = []
        else:
            cur.append(ch)
    if cur:
        out.append(''.join(cur))
    return [p.strip() for p in out if p.strip()]


TOKEN = re.compile(r'[A-Za-z_$][\w$]*|\d+|[^\sA-Za-z0-9_$]+', re.S)


def token_types(text):
    return TOKEN.findall(mask(text))


def validate(before, after, renames):
    """Structural check: non-identifier token streams must be identical and
    identifier changes limited to the planned renames."""
    tb, ta = token_types(before), token_types(after)
    if len(tb) != len(ta):
        return 'token count changed'
    for i, (a, b) in enumerate(zip(tb, ta)):
        if a == b:
            continue
        if re.match(r'^[A-Za-z_$][\w$]*$', a) and a in renames:
            if b not in renames[a]:
                return f'unexpected rename {a} -> {b}'
            continue
        return f'token changed: {a!r} -> {b!r}'
    return None


def body_end(text, start):
    depth, i, n = 0, start, len(text)
    while i < n:
        c = text[i]
        if c == '{':
            depth += 1
        elif c == '}':
            depth -= 1
            if depth == 0:
                return i
        i += 1
    return n


def process(text):
    masked = mask(text)
    # First pass: collect methods + lazy params, no edits yet.
    methods = []  # (sig_start, brace, end, params)
    for m in METHOD_DECL.finditer(masked):
        params_raw = m.group('params')
        if not params_raw.strip() or params_raw.count('(') != params_raw.count(')'):
            continue
        params = []
        for p in split_params(params_raw):
            pm = re.match(r'(?:final\s+)?([\w$.<>\[\],?\s]+?)\s+([A-Za-z_$][\w$]*)$', p)
            if pm and LAZY.match(pm.group(2)):
                params.append((pm.group(1).strip(), pm.group(2)))
        if not params:
            continue
        brace = masked.find('{', m.end() - 1)
        end = body_end(masked, brace)
        methods.append((m.start(), brace, end, params))

    # Skip a method whose signature starts inside a previously accepted span
    # (the regex can match the same declaration twice at different offsets).
    methods.sort(key=lambda m: m[0])
    pruned, last_end = [], -1
    for sig_start, brace, end, params in methods:
        if sig_start < last_end:
            continue
        pruned.append((sig_start, brace, end, params))
        last_end = end
    methods = pruned

    # A param is eligible when its name is declared exactly once in the file
    # and every occurrence lies inside that method (no nested-scope shadowing).
    decl_count = {}
    for _s, _b, _e, params in methods:
        for _t, name in params:
            decl_count[name] = decl_count.get(name, 0) + 1

    eligible = {}  # method index -> [(ptype, pname, occ)]
    for idx, (sig_start, _brace, end, params) in enumerate(methods):
        for ptype, pname in params:
            if decl_count.get(pname, 0) != 1:
                continue
            occ = [o for o in re.finditer(
                r'(?<![A-Za-z0-9_$.])' + re.escape(pname) + r'(?![A-Za-z0-9_$])', masked)]
            if any(not (sig_start <= o.start() < end) for o in occ):
                continue
            eligible.setdefault(idx, []).append((ptype, pname, occ))

    edits, renames = [], {}
    for idx, (sig_start, brace, end, params) in enumerate(methods):
        picks = eligible.get(idx, [])
        if not picks:
            continue
        renamed_old = {pname for _t, pname, _o in picks}
        used = set(re.findall(r'\b[A-Za-z_$][\w$]*\b', masked[brace:end]))
        used -= renamed_old                                  # old names become free
        used |= {n for _t, n in params if n not in renamed_old}  # kept names stay taken
        for ptype, pname, occ in picks:
            base = type_name(ptype)
            new = base
            i = 2
            while new in used:
                new = base + str(i)
                i += 1
            used.add(new)
            renames.setdefault(pname, set()).add(new)
            for o in occ:
                edits.append((o.start(), o.end(), new))
    if not edits:
        return None
    out = list(text)
    for s, e, new in sorted(edits, key=lambda t: -t[0]):
        out[s:e] = list(new)
    new_text = ''.join(out)
    err = validate(text, new_text, renames)
    if err:
        return ('INVALID: ' + err)
    return new_text


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--root', default=DEFAULT_ROOT)
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--check', action='store_true')
    ap.add_argument('--limit', type=int, default=0)
    ap.add_argument('--exclude-list', default=None,
                    help='file with src-relative paths to skip (e.g. the ECJ failing set)')
    ap.add_argument('--verbose', action='store_true')
    args = ap.parse_args()

    excluded = set()
    if args.exclude_list and os.path.exists(args.exclude_list):
        with open(args.exclude_list) as fh:
            excluded = {line.strip() for line in fh if line.strip()}
    root_rel = os.path.abspath(args.root)

    changed = files = 0
    invalid = skipped_excluded = 0
    for dp, _dirs, fs in os.walk(args.root):
        for f in sorted(fs):
            if not f.endswith('.java'):
                continue
            p = os.path.join(dp, f)
            if excluded:
                rel = os.path.relpath(os.path.abspath(p), root_rel).replace(os.sep, '/')
                if rel in excluded:
                    skipped_excluded += 1
                    continue
            text = open(p, errors='ignore').read()
            new = process(text)
            if new is None or new == text:
                continue
            if new.startswith('INVALID: '):
                invalid += 1
                if args.verbose:
                    print(f'  SKIP {os.path.relpath(p, args.root)}: {new[9:]}')
                continue
            files += 1
            n = sum(1 for a, b in zip(text.split('\n'), new.split('\n')) if a != b)
            changed += n
            if args.verbose:
                print(f'  {os.path.relpath(p, args.root)}: {n} lines')
            if args.apply:
                with open(p, 'w') as fh:
                    fh.write(new)
            if args.limit and files >= args.limit:
                break
    print(f'[clean-params] files changed: {files}; lines changed: {changed}; '
          f'skipped-invalid: {invalid}; mode={"APPLY" if args.apply else "dry-run"}')
    if args.check and files and not args.apply:
        print('  (run with --apply to write)')


if __name__ == '__main__':
    sys.exit(main())
