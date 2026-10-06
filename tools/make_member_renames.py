#!/usr/bin/env python3
"""Build a method/field rename map for the obfuscated Lunar Client jar.

Parses the compiled jar (exact owners/descriptors and method bodies) and
optionally the decompiled source tree (body-heuristic fallback) and emits a
headerless TSV:

    <ownerInternalName>\t<M|F>\t<obfName>\t<descriptor>\t<newName>

Naming rules (high confidence only):
  * simple getter  : no args, body ``return <field>;`` -> ``get<Field>``
  * boolean getter : same, boolean return type         -> ``is<Field>``
  * simple setter  : one arg, body ``<field> = <arg>;`` -> ``set<Field>``
  * everything readable is kept untouched (constructors, ``toString``,
    ``equals``, ``hashCode``, ``clone``, ``main``, bridge$/lambda$/$ names,
    ALL_CAPS constants, short readable names such as ``x``/``id``/``mc``)
  * obfuscated leftovers get stable ``method1``, ``method2``, ... and
    ``field1``, ``field2``, ... numbered per class in class-file (= source)
    order.
  * override groups are unified: a method that overrides/implements another
    method in scope gets one shared new name; methods overriding anything
    outside the scope (Minecraft, shaded libs, JDK) are left alone.

Getter/setter bodies are detected from exact bytecode shapes
(``aload_0; getfield f; xreturn`` / ``aload_0; *load_1; putfield f; return``),
with a decompiled-source regex fallback for anything the parser misses.

Usage:
    make_member_renames.py [--jar JAR] [--root DECOMPILED_DIR]
                           [--tsv OUT.tsv] [--md OUT.md] [--limit N]

Only stdlib is used; a small class-file parser reads exact descriptors.
"""
import argparse
import hashlib
import io
import os
import re
import struct
import sys
import zipfile
from collections import defaultdict, Counter

HERE = os.path.dirname(os.path.abspath(__file__))
DEFAULT_JAR = os.path.join(HERE, 'work', 'staging', 'lunar-all-final.jar')
DEFAULT_ROOT = os.path.join(HERE, 'work', 'staging', 'decompiled')
DEFAULT_TSV = os.path.join(HERE, 'work', 'mappings', 'member-renames.tsv')
DEFAULT_MD = os.path.join(HERE, 'work', 'mappings', 'member-renames.md')

SCOPE_PREFIX = 'com/moonsworth/'
GIBBERISH_RE = re.compile(r'^[CHOIR]{8,}$')
IDENT_RE = re.compile(r'^[A-Za-z_$][A-Za-z0-9_$]*$')
READABLE_M_RE = re.compile(r'^[a-z][a-zA-Z0-9_]{2,}$')
ALL_CAPS_RE = re.compile(r'^[A-Z][A-Z0-9_]*$')
SHORT_KEEP = frozenset('xyz')

ACC_PUBLIC = 0x0001
ACC_PRIVATE = 0x0002
ACC_PROTECTED = 0x0004
ACC_STATIC = 0x0008

KEYWORDS = frozenset('''
abstract assert boolean break byte case catch char class const continue default
do double else enum extends final finally float for goto if implements import
instanceof int interface long native new package private protected public return
short static strictfp super switch synchronized this throw throws transient try
void volatile while true false null
'''.split())

# ---------------------------------------------------------------------------
# class file parser
# ---------------------------------------------------------------------------

_TAG_UTF8 = 1
_TAG_INT = 3
_TAG_FLOAT = 4
_TAG_LONG = 5
_TAG_DOUBLE = 6
_TAG_CLASS = 7
_TAG_STRING = 8
_TAG_FIELDREF = 9
_TAG_METHODREF = 10
_TAG_IFACEREF = 11
_TAG_NAMEANDTYPE = 12
_TAG_METHODHANDLE = 15
_TAG_METHODTYPE = 16
_TAG_DYNAMIC = 17
_TAG_INVOKEDYNAMIC = 18
_TAG_MODULE = 19
_TAG_PACKAGE = 20
_TWO_BYTE_REFS = (_TAG_CLASS, _TAG_STRING, _TAG_METHODTYPE, _TAG_MODULE, _TAG_PACKAGE)
_FOUR_BYTE_REFS = (_TAG_FIELDREF, _TAG_METHODREF, _TAG_IFACEREF,
                   _TAG_NAMEANDTYPE, _TAG_DYNAMIC, _TAG_INVOKEDYNAMIC)


def _parse_cp(data):
    count = struct.unpack_from('>H', data, 8)[0]
    p = 10
    cp = [None] * count
    i = 1
    while i < count:
        tag = data[p]
        p += 1
        if tag == _TAG_UTF8:
            ln = struct.unpack_from('>H', data, p)[0]
            cp[i] = ('utf8', data[p + 2:p + 2 + ln].decode('utf-8', 'replace'))
            p += 2 + ln
        elif tag in (_TAG_INT, _TAG_FLOAT):
            cp[i] = ('const', 0)
            p += 4
        elif tag in (_TAG_LONG, _TAG_DOUBLE):
            cp[i] = ('const', 0)
            p += 8
            i += 1
        elif tag in _TWO_BYTE_REFS:
            cp[i] = (tag, struct.unpack_from('>H', data, p)[0])
            p += 2
        elif tag in _FOUR_BYTE_REFS:
            a, b = struct.unpack_from('>HH', data, p)
            cp[i] = (tag, a, b)
            p += 4
        elif tag == _TAG_METHODHANDLE:
            cp[i] = (tag, data[p], struct.unpack_from('>H', data, p + 1)[0])
            p += 3
        else:
            raise ValueError(f'unknown constant-pool tag {tag}')
        i += 1
    return cp, p


class Cls:
    __slots__ = ('name', 'super', 'ifaces', 'fields', 'methods', 'accesses',
                 'scope', 'obf_ratio', 'eligible_m', 'method_index')

    def __init__(self, name, sup, ifaces, fields, methods, accesses=None):
        self.name = name
        self.super = sup
        self.ifaces = ifaces
        self.fields = fields
        self.methods = methods
        self.accesses = accesses or {}
        self.scope = name.startswith(SCOPE_PREFIX)
        self.obf_ratio = 0.0
        self.eligible_m = {}
        self.method_index = {}


def parse_class(data):
    cp, p = _parse_cp(data)

    def u(idx):
        e = cp[idx]
        if e is None:
            return None
        if e[0] == 'utf8':
            return e[1]
        if e[0] in (_TAG_CLASS, _TAG_MODULE, _TAG_PACKAGE):
            return u(e[1])
        return None

    access, this_i, super_i = struct.unpack_from('>HHH', data, p)
    p += 6
    n_ifaces = struct.unpack_from('>H', data, p)[0]
    p += 2
    ifaces = []
    for _ in range(n_ifaces):
        ifaces.append(u(struct.unpack_from('>H', data, p)[0]))
        p += 2

    def skip_attrs(p):
        n = struct.unpack_from('>H', data, p)[0]
        p += 2
        for _ in range(n):
            ln = struct.unpack_from('>I', data, p + 2)[0]
            p += 6 + ln
        return p

    fields = []
    n_fields = struct.unpack_from('>H', data, p)[0]
    p += 2
    for _ in range(n_fields):
        a, n, d = struct.unpack_from('>HHH', data, p)
        p += 6
        fields.append((a, u(n), u(d)))
        p = skip_attrs(p)

    methods = []
    accesses = {}
    n_methods = struct.unpack_from('>H', data, p)[0]
    p += 2
    for _ in range(n_methods):
        a, n, d = struct.unpack_from('>HHH', data, p)
        p += 6
        n_str, d_str = u(n), u(d)
        methods.append((a, n_str, d_str))

        n_attrs = struct.unpack_from('>H', data, p)[0]
        p += 2
        code = None
        for _ in range(n_attrs):
            an_i = struct.unpack_from('>H', data, p)[0]
            ln = struct.unpack_from('>I', data, p + 2)[0]
            body = p + 6
            if u(an_i) == 'Code':
                code_len = struct.unpack_from('>I', data, body + 4)[0]
                code = data[body + 8:body + 8 + code_len]
            p = body + ln
        if code is not None and d_str is not None:
            hit = _detect_simple_access(code)
            if hit:
                ref = _resolve_fieldref(cp, u, hit[1])
                if ref and _access_matches(hit, ref[2], d_str):
                    accesses[(n_str, d_str)] = (hit[0],) + ref

    return u(this_i), u(super_i), [i for i in ifaces if i], fields, methods, accesses


_RETURN_OPS = {0xAC: 'BCISZ', 0xAD: 'J', 0xAE: 'F', 0xAF: 'D', 0xB0: 'L['}
# *load_0 / *load_1: iload/l load/f load/dload/aload
_LOAD0 = frozenset((0x1A, 0x1E, 0x22, 0x26, 0x2A))
_LOAD1 = frozenset((0x1B, 0x1F, 0x23, 0x27, 0x2B))


def _detect_simple_access(code):
    """Exact bytecode shapes of a trivial getter/setter.

    getter: aload_0; getfield f; xreturn  /  getstatic f; xreturn
    setter: aload_0; *load_1; putfield f; return  /  *load_0; putstatic f; return
    """
    n = len(code)
    if n == 5 and code[0] == 0x2A and code[1] == 0xB4 and code[4] in _RETURN_OPS:
        return ('get', (code[2] << 8) | code[3], code[4])
    if n == 4 and code[0] == 0xB2 and code[3] in _RETURN_OPS:
        return ('getstatic', (code[1] << 8) | code[2], code[3])
    if n == 6 and code[0] == 0x2A and code[1] in _LOAD1 and code[2] == 0xB5 and code[5] == 0xB1:
        return ('set', (code[3] << 8) | code[4], 0)
    if n == 5 and code[0] in _LOAD0 and code[1] == 0xB3 and code[4] == 0xB1:
        return ('setstatic', (code[2] << 8) | code[3], 0)
    return None


def _access_matches(hit, field_desc, method_desc):
    kind, _idx, op = hit
    if kind in ('get', 'getstatic'):
        allowed = _RETURN_OPS.get(op, '')
        if field_desc.startswith('['):
            return allowed == 'L['
        if field_desc.startswith('L'):
            return allowed == 'L['
        return len(field_desc) == 1 and field_desc in allowed
    params = param_key(method_desc)
    return params == '(%s)' % field_desc and method_desc.endswith(')V')


def _resolve_fieldref(cp, u, idx):
    if idx <= 0 or idx >= len(cp):
        return None
    e = cp[idx]
    if e is None or e[0] != _TAG_FIELDREF:
        return None
    owner = u(e[1])
    nat = cp[e[2]]
    if owner is None or nat is None or nat[0] != _TAG_NAMEANDTYPE:
        return None
    name, desc = u(nat[1]), u(nat[2])
    if name is None or desc is None:
        return None
    return owner, name, desc


def parse_jar(path, blob=None):
    classes = {}
    if blob is None:
        with open(path, 'rb') as f:
            blob = f.read()
    with zipfile.ZipFile(io.BytesIO(blob)) as z:
        for info in z.infolist():
            fn = info.filename
            if not fn.endswith('.class') or fn.startswith('META-INF/'):
                continue
            if fn.endswith('module-info.class') or fn == 'module-info.class':
                continue
            try:
                name, sup, ifaces, fields, methods, accesses = parse_class(z.read(info))
            except Exception:
                continue
            if not name:
                name = fn[:-6]
            classes[name] = Cls(name, sup, ifaces, fields, methods, accesses)
    return classes


# ---------------------------------------------------------------------------
# descriptor helpers
# ---------------------------------------------------------------------------

_PRIM_SIMPLE = {'B': 'byte', 'C': 'char', 'D': 'double', 'F': 'float',
                'I': 'int', 'J': 'long', 'S': 'short', 'Z': 'boolean'}


def param_key(desc):
    """'(IJLjava/lang/String;)V' -> '(IJLjava/lang/String;)'."""
    return desc[:desc.index(')') + 1]


def return_desc(desc):
    return desc[desc.index(')') + 1:]


def desc_params(desc):
    out, i, n = [], 1, len(desc)
    while i < n and desc[i] != ')':
        arrays = 0
        while desc[i] == '[':
            arrays += 1
            i += 1
        c = desc[i]
        if c in _PRIM_SIMPLE:
            out.append(_PRIM_SIMPLE[c])
            i += 1
        elif c == 'L':
            j = desc.index(';', i)
            out.append(desc[i + 1:j])
            i = j + 1
        else:
            break
        if arrays:
            out[-1] += '[]' * arrays
    return out


def jvm_simple(t):
    t = t.rstrip('[]')
    if t in _PRIM_SIMPLE.values():
        return t
    return t.rsplit('/', 1)[-1].rsplit('$', 1)[-1]


# ---------------------------------------------------------------------------
# source scanner
# ---------------------------------------------------------------------------

def sanitize(text):
    """Blank out comments and string/char literals, keeping offsets stable."""
    out = list(text)
    i, n = 0, len(text)
    while i < n:
        c = text[i]
        if c == '/' and i + 1 < n and text[i + 1] == '/':
            j = text.find('\n', i)
            if j < 0:
                j = n
            for k in range(i, j):
                out[k] = ' '
            i = j
        elif c == '/' and i + 1 < n and text[i + 1] == '*':
            j = text.find('*/', i + 2)
            j = n if j < 0 else j + 2
            for k in range(i, j):
                if text[k] != '\n':
                    out[k] = ' '
            i = j
        elif c == '"':
            j = i + 1
            while j < n:
                if text[j] == '\\':
                    j += 2
                    continue
                if text[j] == '"':
                    j += 1
                    break
                j += 1
            for k in range(i, j):
                if text[k] not in '\n\r':
                    out[k] = ' '
            i = j
        elif c == "'":
            j = i + 1
            while j < n:
                if text[j] == '\\':
                    j += 2
                    continue
                if text[j] == "'":
                    j += 1
                    break
                j += 1
            for k in range(i, j):
                if text[k] not in '\n\r':
                    out[k] = ' '
            i = j
        else:
            i += 1
    return ''.join(out)


def find_matching(s, i, op, cl):
    depth = 0
    for j in range(i, len(s)):
        c = s[j]
        if c == op:
            depth += 1
        elif c == cl:
            depth -= 1
            if depth == 0:
                return j
    return -1


CLASS_DECL_RE = re.compile(r'\b(?:class|interface|enum|record)\s+([A-Za-z_$][\w$]*)')
METHOD_DECL_RE = re.compile(
    r'(?:(?:public|protected|private|static|final|abstract|synchronized|native|'
    r'strictfp|default)\s+)*'
    r'(?:<[^<>]*>\s*)?'
    r'([\w$.\[\]<>?, ]+?)\s+'
    r'([A-Za-z_$][\w$]*)\s*\(([^()]*)\)\s*'
    r'(?:throws\s+[\w$., <>\[\]]+\s*)?$',
    re.S)


def strip_annotations(h):
    """Remove leading ``@Anno(...)`` groups; return None on malformed input."""
    i = 0
    while True:
        m = re.compile(r'\s*@[A-Za-z_$][\w$.]*').match(h, i)
        if not m:
            break
        j = m.end()
        if j < len(h) and h[j] == '(':
            e = find_matching(h, j, '(', ')')
            if e < 0:
                return None
            j = e + 1
        i = j
    return h[i:]


def scan_members(body):
    """Return [(name, params_text, body_text)] for direct members of a class body."""
    out = []
    i, n = 0, len(body)
    seg = 0
    pdepth = 0
    while i < n:
        c = body[i]
        if c == '(':
            pdepth += 1
        elif c == ')':
            if pdepth:
                pdepth -= 1
        elif c == '{' and pdepth == 0:
            end = find_matching(body, i, '{', '}')
            header = body[seg:i]
            h = strip_annotations(header)
            if h and '->' not in h and not re.search(r'\bnew\b', h):
                m = METHOD_DECL_RE.search(h)
                if m and m.group(2) != 'if' and m.group(2) != 'for' \
                        and m.group(2) != 'while' and m.group(2) != 'switch' \
                        and m.group(2) != 'catch' and m.group(2) != 'synchronized':
                    out.append((m.group(2), m.group(3), body[i + 1:end if end >= 0 else n]))
            if end < 0:
                break
            i = end + 1
            seg = i
            continue
        elif c == ';' and pdepth == 0:
            seg = i + 1
        i += 1
    return out


def scan_source(text, expected_simple):
    s = sanitize(text)
    decl = None
    for cand in CLASS_DECL_RE.finditer(s):
        if cand.group(1) == expected_simple:
            decl = cand
            break
    if decl is None:
        decl = CLASS_DECL_RE.search(s)
    if decl is None:
        return []
    i, n, depth = decl.end(), len(s), 0
    body_start = -1
    while i < n:
        c = s[i]
        if c in '(<':
            depth += 1
        elif c in ')>':
            if depth:
                depth -= 1
        elif c == '{' and depth == 0:
            body_start = i
            break
        elif c == ';':
            break
        i += 1
    if body_start < 0:
        return []
    body_end = find_matching(s, body_start, '{', '}')
    if body_end < 0:
        body_end = n
    return scan_members(s[body_start + 1:body_end])


def split_params(s):
    parts, depth, cur = [], 0, ''
    for ch in s:
        if ch in '<([':
            depth += 1
        elif ch in '>)]':
            if depth:
                depth -= 1
        if ch == ',' and depth == 0:
            parts.append(cur)
            cur = ''
        else:
            cur += ch
    if cur.strip():
        parts.append(cur)
    return parts


def param_simple(p):
    p = re.sub(r'@[\w$.]+(?:\([^()]*\))?', ' ', p)
    p = p.replace('final ', ' ').replace('...', '[]')
    p = re.sub(r'<[^<>]*>', '', p)
    toks = re.findall(r'[A-Za-z_$][\w$]*', p)
    if not toks:
        return ''
    # "Type name" -> the type is the token before the variable name
    if len(toks) >= 2 and toks[-1][0].islower():
        return toks[-2]
    return toks[-1]


def param_name(p):
    p = re.sub(r'@[\w$.]+(?:\([^()]*\))?', ' ', p)
    p = p.replace('...', '[]')
    toks = re.findall(r'[A-Za-z_$][\w$]*', p)
    return toks[-1] if toks else ''


def param_match(src_param, jvm_param):
    return param_simple(src_param) == jvm_simple(jvm_param)


# ---------------------------------------------------------------------------
# naming helpers
# ---------------------------------------------------------------------------

def pascal(name):
    parts = [p for p in name.split('_') if p]
    out = ''.join((p[0].upper() + p[1:]) for p in parts)
    return out or 'Value'


def bump(name):
    m = re.match(r'^(.*?)(\d+)$', name)
    if m:
        return m.group(1) + str(int(m.group(2)) + 1)
    return name + '2'


def valid_name(name):
    return bool(IDENT_RE.match(name)) and name not in KEYWORDS


def obf_pattern(name):
    """Name string that could plausibly be an obfuscated member."""
    if GIBBERISH_RE.match(name):
        return True
    if len(name) == 1 and name.isalpha() and name not in SHORT_KEEP:
        return True
    return False


def keep_reason(kind, name):
    if name.startswith('<'):
        return 'special'
    if kind == 'M' and name == 'main':
        return 'special'
    if '$' in name:
        return 'dollar'
    if READABLE_M_RE.match(name):
        return 'readable'
    if ALL_CAPS_RE.match(name):
        return 'constant'
    if len(name) <= 2:
        return 'short-readable'
    return 'readable'


# ---------------------------------------------------------------------------
# main
# ---------------------------------------------------------------------------

def main():
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument('--jar', default=DEFAULT_JAR)
    ap.add_argument('--root', default=DEFAULT_ROOT)
    ap.add_argument('--tsv', default=DEFAULT_TSV)
    ap.add_argument('--md', default=DEFAULT_MD)
    ap.add_argument('--class-map', default=None,
                    help='TSV source-fqn<TAB>jar-fqn for renamed classes (tools/pair_classes.py)')
    ap.add_argument('--limit', type=int, default=0, help='debug: limit scope classes')
    args = ap.parse_args()

    print('[1/6] parsing jar ...', file=sys.stderr)
    with open(args.jar, 'rb') as f:
        jar_blob = f.read()
    jar_hash = hashlib.sha256(jar_blob).hexdigest()
    classes = parse_jar(args.jar, jar_blob)
    scope = sorted(n for n, c in classes.items() if c.scope)
    if args.limit:
        scope = scope[:args.limit]
    print(f'      {len(classes)} classes parsed, {len(scope)} in scope', file=sys.stderr)

    # per-class obfuscation ratio and eligible members (class-file order)
    for cname in scope:
        cl = classes[cname]
        total = len(cl.fields) + len(cl.methods)
        gib = sum(1 for _, n, _ in cl.fields if GIBBERISH_RE.match(n))
        gib += sum(1 for _, n, _ in cl.methods if GIBBERISH_RE.match(n))
        cl.obf_ratio = gib / total if total else 0.0

    def eligible_m(cl, name):
        if name.startswith('<') or name == 'main' or '$' in name:
            return False
        if GIBBERISH_RE.match(name):
            return True
        if len(name) == 1 and name.isalpha() and name not in SHORT_KEEP:
            return cl.obf_ratio >= 0.5
        return False

    def eligible_f(cl, name):
        if '$' in name:
            return False
        if GIBBERISH_RE.match(name):
            return True
        if len(name) == 1 and name.isalpha() and name not in SHORT_KEEP:
            return cl.obf_ratio >= 0.5
        return False

    for cname in scope:
        cl = classes[cname]
        idx = 0
        for a, n, d in cl.methods:
            if eligible_m(cl, n):
                idx += 1
                cl.method_index[(n, d)] = idx
                cl.eligible_m[(n, d)] = True

    # ------------------------------------------------------------------
    print('[2/6] scanning decompiled sources ...', file=sys.stderr)
    src = {}
    tree_files = 0
    unmatched_files = []
    class_map = {}
    if args.class_map:
        for line in open(args.class_map, encoding='utf-8', errors='replace'):
            if not line.strip():
                continue
            a, _, b = line.rstrip('\n').partition('\t')
            if a and b:
                class_map[a.replace('.', '/')] = b.replace('.', '/')
        print(f'      class map: {len(class_map)} jar<->source pairs', file=sys.stderr)
    for dirpath, _, files in os.walk(args.root):
        for f in files:
            if not f.endswith('.java'):
                continue
            tree_files += 1
            path = os.path.join(dirpath, f)
            owner = os.path.relpath(path, args.root)[:-5].replace(os.sep, '/')
            try:
                text = open(path, encoding='utf-8', errors='replace').read()
            except OSError:
                continue
            simple = owner.rsplit('/', 1)[-1]
            methods = scan_source(text, simple)
            jar_owner = class_map.get(owner, owner)
            if jar_owner not in classes:
                unmatched_files.append(owner)
            d = {}
            for mname, mparams, mbody in methods:
                d.setdefault(mname, []).append((mparams, mbody))
            src[jar_owner] = d
    print(f'      {tree_files} java files scanned, {len(src)} owners', file=sys.stderr)

    # ------------------------------------------------------------------
    print('[3/6] building hierarchy indexes ...', file=sys.stderr)
    anc_cache = {}

    def ancestors(name):
        cached = anc_cache.get(name)
        if cached is not None:
            return cached
        out = set()
        stack = []
        cl = classes.get(name)
        if cl:
            if cl.super:
                stack.append(cl.super)
            stack.extend(cl.ifaces)
        while stack:
            a = stack.pop()
            if a in out or a not in classes:
                continue
            out.add(a)
            ca = classes[a]
            if ca.super:
                stack.append(ca.super)
            stack.extend(ca.ifaces)
        anc_cache[name] = out
        return out

    depth_cache = {}

    def depth(name, seen=None):
        d = depth_cache.get(name)
        if d is not None:
            return d
        if seen is None:
            seen = frozenset()
        if name in seen:
            return 0
        seen = seen | {name}
        cl = classes.get(name)
        best = 0
        if cl:
            parents = ([cl.super] if cl.super else []) + cl.ifaces
            for p in parents:
                if p in classes:
                    best = max(best, 1 + depth(p, seen))
        depth_cache[name] = best
        return best

    scope_set = set(scope)
    scope_sorted = sorted(scope, key=lambda c: (depth(c), c))

    input_methods = defaultdict(dict)
    kept_by_sig = defaultdict(set)
    field_name_owners = defaultdict(set)
    for cname, cl in classes.items():
        for a, n, d in cl.methods:
            input_methods[cname][(n, d)] = a
        for a, n, d in cl.fields:
            field_name_owners[n].add(cname)

    for cname, cl in classes.items():
        for a, n, d in cl.methods:
            if cname.startswith(SCOPE_PREFIX) and eligible_m(cl, n):
                continue
            kept_by_sig[(n, param_key(d))].add(cname)

    reserved_methods = {}

    def reserved_m(cname):
        r = reserved_methods.get(cname)
        if r is not None:
            return r
        r = set()
        own = classes.get(cname)
        if own:
            r.update((n, param_key(d)) for _, n, d in own.methods)
        for a in ancestors(cname):
            r.update((n, param_key(d)) for _, n, d in classes[a].methods)
        reserved_methods[cname] = r
        return r

    reserved_fields = {}

    def reserved_f(cname):
        r = reserved_fields.get(cname)
        if r is not None:
            return r
        r = set()
        own = classes.get(cname)
        if own:
            r.update(n for _, n, _ in own.fields)
        for a in ancestors(cname):
            r.update(n for _, n, _ in classes[a].fields)
        reserved_fields[cname] = r
        return r

    # ------------------------------------------------------------------
    print('[4/6] grouping overrides ...', file=sys.stderr)
    sig_owners = defaultdict(list)
    for cname in scope:
        cl = classes[cname]
        for a, n, d in cl.methods:
            if (a & ACC_PRIVATE) or (a & ACC_STATIC):
                continue
            if obf_pattern(n):
                sig_owners[(n, d)].append(cname)

    group_members = {}
    group_of = {}
    groups_total = 0
    for (n, d), owners in sig_owners.items():
        parent = {o: o for o in owners}

        def find(x):
            while parent[x] != x:
                parent[x] = parent[parent[x]]
                x = parent[x]
            return x

        for o in owners:
            for a in ancestors(o):
                if a in parent:
                    ro, ra = find(o), find(a)
                    if ro != ra:
                        parent[ro] = ra
        members = defaultdict(set)
        for o in owners:
            members[find(o)].add(o)
        for root, ms in members.items():
            groups_total += 1
            group_members[(n, d, root)] = ms
            for o in ms:
                group_of[(n, d, o)] = root

    print(f'      {groups_total} override groups', file=sys.stderr)

    # ------------------------------------------------------------------
    print('[5/6] assigning names ...', file=sys.stderr)

    def find_src_body(cname, name, desc):
        owner_src = src.get(cname)
        if not owner_src:
            return None
        entries = owner_src.get(name)
        if not entries:
            return None
        want = desc_params(desc)
        for params_text, body in entries:
            sp = split_params(params_text)
            if len(sp) != len(want):
                continue
            if all(param_match(sp[i], want[i]) for i in range(len(want))):
                return params_text, body
        return None

    def lookup_field(cname, fname):
        """Return the class that declares field ``fname`` (self or an ancestor)."""
        cl = classes.get(cname)
        if cl and any(n == fname for _, n, _ in cl.fields):
            return cname
        for a in ancestors(cname):
            if any(n == fname for _, n, _ in classes[a].fields):
                return a
        return None

    def semantic_candidate(cname, name, desc):
        access = classes[cname].accesses.get((name, desc))
        if access:
            kind, fowner, fname, fdesc = access
            decl = fowner if fowner in classes else lookup_field(cname, fname)
            if decl is None:
                return None
            if eligible_f(classes[decl], fname):
                return None
            if kind in ('get', 'getstatic'):
                if desc_params(desc):
                    return None
                if return_desc(desc) == 'Z':
                    if re.match(r'^is[A-Z]', fname):
                        return fname
                    return 'is' + pascal(fname)
                return 'get' + pascal(fname)
            if kind in ('set', 'setstatic'):
                if len(desc_params(desc)) != 1:
                    return None
                return 'set' + pascal(fname)
            return None

        found = find_src_body(cname, name, desc)
        if not found:
            return None
        params_text, body = found
        b = ' '.join(body.split())
        args = desc_params(desc)

        if not args:
            m = re.fullmatch(r'return (?:this\.)?([A-Za-z_$][\w$]*);', b)
            if not m:
                return None
            fname = m.group(1)
            decl = lookup_field(cname, fname)
            if decl is None:
                return None
            if eligible_f(classes[decl], fname):
                return None
            if return_desc(desc) == 'Z':
                if re.match(r'^is[A-Z]', fname):
                    return fname
                return 'is' + pascal(fname)
            return 'get' + pascal(fname)

        if len(args) == 1:
            m = re.fullmatch(
                r'(?:this\.)?([A-Za-z_$][\w$]*)\s*=\s*([A-Za-z_$][\w$]*);', b)
            if not m:
                return None
            fname, arg_name = m.group(1), m.group(2)
            sp = split_params(params_text)
            if len(sp) != 1 or param_name(sp[0]) != arg_name:
                return None
            decl = lookup_field(cname, fname)
            if decl is None:
                return None
            if eligible_f(classes[decl], fname):
                return None
            return 'set' + pascal(fname)
        return None

    def is_descendant_or_self(cname, of):
        return cname == of or of in ancestors(cname)

    own_assigned = defaultdict(set)

    def method_name_free(cand, params, owners):
        key = (cand, params)
        for c in owners:
            if key in reserved_m(c) or key in own_assigned[c]:
                return False
            for a in ancestors(c):
                if key in own_assigned[a]:
                    return False
        for d in kept_by_sig.get(key, ()):
            for c in owners:
                if is_descendant_or_self(d, c):
                    return False
        return True

    def allocate_method(cand, params, owners):
        if not valid_name(cand):
            cand = 'method' + cand[1:] if cand[:1].isdigit() else 'method1'
        while not method_name_free(cand, params, owners):
            cand = bump(cand)
        return cand

    group_name = {}
    group_kept = Counter()
    own_names = {}
    semantic_hits = Counter()

    for cname in scope_sorted:
        cl = classes[cname]
        for a, n, d in cl.methods:
            if not cl.eligible_m.get((n, d)):
                continue
            params = param_key(d)
            if (a & ACC_PRIVATE) or (a & ACC_STATIC):
                sem = semantic_candidate(cname, n, d)
                cand = allocate_method(sem or f'method{cl.method_index[(n, d)]}',
                                       params, [cname])
                own_names[(cname, n, d)] = cand
                own_assigned[cname].add((cand, params))
                if sem:
                    semantic_hits['method_%s' % ('private' if a & ACC_PRIVATE else 'static')] += 1
                continue
            root = group_of.get((n, d, cname))
            if root is None:
                continue
            gkey = (n, d, root)
            if gkey in group_kept or gkey in group_name:
                continue
            members = group_members[gkey]

            conflict = False
            for m in members:
                if not classes[m].eligible_m.get((n, d)):
                    conflict = True
                    break
                for anc in ancestors(m):
                    if anc in scope_set:
                        continue
                    ma = input_methods[anc].get((n, d))
                    if ma is not None and not (ma & ACC_PRIVATE) and not (ma & ACC_STATIC):
                        conflict = True
                        break
                if conflict:
                    break
            if conflict:
                group_kept[gkey] = 1
                continue

            sem = None
            cands = set()
            for m in sorted(members, key=lambda x: (depth(x), x)):
                sc = semantic_candidate(m, n, d)
                if sc:
                    cands.add(sc)
            if len(cands) == 1:
                sem = cands.pop()
            cand = allocate_method(sem or f'method{cl.method_index[(n, d)]}',
                                   params, sorted(members))
            group_name[gkey] = cand
            for m in members:
                own_assigned[m].add((cand, params))
            if sem:
                semantic_hits['method_group'] += 1
            else:
                semantic_hits['method_fallback_group'] += 1

    # fields
    own_fields_assigned = defaultdict(set)
    field_names = {}

    def field_name_free(cname, cand):
        if cand in reserved_f(cname) or cand in own_fields_assigned[cname]:
            return False
        for a in ancestors(cname):
            if cand in own_fields_assigned[a]:
                return False
        for d in field_name_owners.get(cand, ()):
            if is_descendant_or_self(d, cname):
                return False
        return True

    for cname in scope_sorted:
        cl = classes[cname]
        n = 0
        for a, fn, fd in cl.fields:
            if not eligible_f(cl, fn):
                continue
            n += 1
            cand = f'field{n}'
            while not valid_name(cand) or not field_name_free(cname, cand):
                cand = bump(cand)
            field_names[(cname, fn)] = cand
            own_fields_assigned[cname].add(cand)

    # ------------------------------------------------------------------
    # Repair pass: a generated name must not accidentally override/implement
    # an unrelated method in an ancestor or descendant (groups can span
    # branches of the hierarchy, so ancestor-only checks are not enough).
    def method_final(owner, n, d):
        if not classes[owner].eligible_m.get((n, d)):
            return None
        a = input_methods[owner][(n, d)]
        if (a & ACC_PRIVATE) or (a & ACC_STATIC):
            return own_names.get((owner, n, d))
        root = group_of.get((n, d, owner))
        return group_name.get((n, d, root)) if root is not None else None

    def bump_decl(owner, n, d):
        params = param_key(d)
        a = input_methods[owner][(n, d)]
        if (a & ACC_PRIVATE) or (a & ACC_STATIC):
            old = own_names[(owner, n, d)]
            cand = allocate_method(bump(old), params, [owner])
            own_names[(owner, n, d)] = cand
            own_assigned[owner].discard((old, params))
            own_assigned[owner].add((cand, params))
        else:
            root = group_of[(n, d, owner)]
            gkey = (n, d, root)
            old = group_name[gkey]
            members = group_members[gkey]
            cand = allocate_method(bump(old), params, sorted(members))
            group_name[gkey] = cand
            for m in members:
                own_assigned[m].discard((old, params))
                own_assigned[m].add((cand, params))

    for _round in range(50):
        finals = {}
        for owner in scope:
            dmap = {}
            for a, n, d in classes[owner].methods:
                f = method_final(owner, n, d)
                if f is None:
                    f = n
                dmap.setdefault((f, param_key(d)), []).append((n, d))
            finals[owner] = dmap
        to_bump = set()
        for owner in scope:
            own_map = finals[owner]
            for anc in ancestors(owner):
                anc_map = finals.get(anc)
                if not anc_map:
                    continue
                for key, own_decls in own_map.items():
                    anc_decls = anc_map.get(key)
                    if not anc_decls:
                        continue
                    for n_o, d_o in own_decls:
                        a_o = input_methods[owner][(n_o, d_o)]
                        if (a_o & ACC_PRIVATE) or (a_o & ACC_STATIC):
                            continue
                        for n_a, d_a in anc_decls:
                            if (n_o, d_o) == (n_a, d_a):
                                continue
                            a_a = input_methods[anc][(n_a, d_a)]
                            if (a_a & ACC_PRIVATE) or (a_a & ACC_STATIC):
                                continue
                            if method_final(owner, n_o, d_o) is not None:
                                to_bump.add((owner, n_o, d_o))
                            elif method_final(anc, n_a, d_a) is not None:
                                to_bump.add((anc, n_a, d_a))
        if not to_bump:
            break
        for owner, n, d in sorted(to_bump):
            bump_decl(owner, n, d)
    else:
        print('WARNING: override repair did not converge', file=sys.stderr)

    # ------------------------------------------------------------------
    print('[6/6] writing outputs ...', file=sys.stderr)
    rows = []
    stats = Counter()
    examples = defaultdict(list)
    emitted_decl = set()

    for cname in scope:
        cl = classes[cname]
        for a, n, d in cl.methods:
            if not cl.eligible_m.get((n, d)):
                continue
            if (a & ACC_PRIVATE) or (a & ACC_STATIC):
                new = own_names.get((cname, n, d))
            else:
                root = group_of.get((n, d, cname))
                new = group_name.get((n, d, root)) if root is not None else None
            if not new or new == n:
                if not ((a & ACC_PRIVATE) or (a & ACC_STATIC)):
                    root = group_of.get((n, d, cname))
                    if root is not None and group_kept.get((n, d, root)):
                        stats['method_kept_external'] += 1
                    else:
                        stats['method_dropped'] += 1
                else:
                    stats['method_dropped'] += 1
                continue
            decl = (cname, 'M', n, d)
            if decl in emitted_decl:
                continue
            emitted_decl.add(decl)
            rows.append((cname, 'M', n, d, new))
            stats['method_renamed'] += 1
            if GIBBERISH_RE.match(n):
                stats['method_from_gibberish'] += 1
            else:
                stats['method_from_short'] += 1
            if new.startswith('get'):
                cat = 'getter'
            elif new.startswith('is'):
                cat = 'is-getter'
            elif new.startswith('set'):
                cat = 'setter'
            else:
                cat = 'fallback'
            stats['method_cat_' + cat] += 1
            if len(examples[cat]) < 8:
                examples[cat].append(f'{cname}.{n}{d} -> {new}')

        for a, fn, fd in cl.fields:
            if not eligible_f(cl, fn):
                continue
            new = field_names.get((cname, fn))
            if not new:
                continue
            rows.append((cname, 'F', fn, fd, new))
            stats['field_renamed'] += 1
            if GIBBERISH_RE.match(fn):
                stats['field_from_gibberish'] += 1
            else:
                stats['field_from_short'] += 1
            if len(examples['field']) < 8:
                examples['field'].append(f'{cname}.{fn} {fd} -> {new}')

    # kept stats
    for cname in scope:
        cl = classes[cname]
        for a, n, d in cl.methods:
            if not cl.eligible_m.get((n, d)):
                stats['method_kept_' + keep_reason('M', n)] += 1
        for a, n, d in cl.fields:
            if not eligible_f(cl, n):
                stats['field_kept_' + keep_reason('F', n)] += 1

    rows.sort(key=lambda r: (r[0], r[1], r[2], r[3]))

    # ---- validation -------------------------------------------------
    problems = []
    seen = set()
    per_class_new = defaultdict(set)
    for owner, kind, old, desc, new in rows:
        if not valid_name(new):
            problems.append(f'invalid new name {new!r} for {owner}.{old}{desc}')
        if kind == 'M':
            key = (owner, 'M', new, param_key(desc))
        else:
            key = (owner, 'F', new)
        if key in seen:
            problems.append(f'duplicate new member {owner}.{new}{desc}')
        seen.add(key)
        per_class_new[owner].add((kind, new, param_key(desc) if kind == 'M' else ''))

    for owner, kind, old, desc, new in rows:
        if kind == 'M':
            if (new, param_key(desc)) in reserved_m(owner):
                problems.append(f'collides with kept/inherited member {owner}.{new}')
        else:
            if new in reserved_f(owner):
                problems.append(f'collides with kept/inherited field {owner}.{new}')

    os.makedirs(os.path.dirname(os.path.abspath(args.tsv)), exist_ok=True)
    with open(args.tsv, 'w', encoding='utf-8') as f:
        for owner, kind, old, desc, new in rows:
            f.write(f'{owner}\t{kind}\t{old}\t{desc}\t{new}\n')

    classes_touched = len({r[0] for r in rows})
    lines = []
    add = lines.append
    add('# Member rename map - stats')
    add('')
    add(f'Generated by `tools/make_member_renames.py` from `{os.path.relpath(args.jar, HERE)}` '
        f'and `{os.path.relpath(args.root, HERE)}`.')
    add('')
    add(f'- source jar sha256: `{jar_hash}`')
    add(f'- mapping rows written: {len(rows)}')
    add(f'- replay: `python3 tools/make_member_renames.py --jar '
        f'{os.path.relpath(args.jar, os.path.dirname(HERE))} --root '
        f'{os.path.relpath(args.root, os.path.dirname(HERE))}`')
    add('')
    add('## Scope')
    add('')
    add(f'- classes parsed from jar: {len(classes)}')
    add(f'- classes in rename scope (`{SCOPE_PREFIX}**`): {len(scope)}')
    add(f'- decompiled `.java` files scanned: {tree_files}')
    add(f'- source files without a matching jar class: {len(unmatched_files)}')
    add(f'- classes with at least one rename: {classes_touched}')
    add(f'- override groups considered: {groups_total}')
    add('')
    add('## Renamed members')
    add('')
    add(f'- methods renamed: {stats["method_renamed"]} '
        f'(from gibberish: {stats["method_from_gibberish"]}, '
        f'from short names: {stats["method_from_short"]})')
    add(f'  - simple getters: {stats["method_cat_getter"]}')
    add(f'  - boolean getters (`is...`): {stats["method_cat_is-getter"]}')
    add(f'  - simple setters: {stats["method_cat_setter"]}')
    add(f'  - systematic fallbacks: {stats["method_cat_fallback"]}')
    add(f'- fields renamed: {stats["field_renamed"]} '
        f'(from gibberish: {stats["field_from_gibberish"]}, '
        f'from short names: {stats["field_from_short"]})')
    add(f'- override/implementation groups named semantically: {semantic_hits["method_group"]}')
    add(f'- override/implementation groups named systematically: {semantic_hits["method_fallback_group"]}')
    add(f'- override groups left untouched (external override): {sum(group_kept.values())}')
    add(f'- private/static methods named semantically: '
        f'{semantic_hits["method_private"] + semantic_hits["method_static"]}')
    add('')
    add('## Kept members')
    add('')
    add('| kind | reason | count |')
    add('| --- | --- | --- |')
    for prefix, label in (('method_kept_', 'methods'), ('field_kept_', 'fields')):
        for reason in ('readable', 'constant', 'short-readable', 'special', 'dollar'):
            cnt = stats[prefix + reason]
            if cnt:
                add(f'| {label} | {reason} | {cnt} |')
    add(f'| methods | override of external method (left untouched) | {stats["method_kept_external"]} |')
    if group_kept:
        add(f'| methods | override groups left untouched | {sum(group_kept.values())} groups |')
    add('')
    add('## Examples')
    add('')
    for cat, label in (('getter', 'Getters'), ('is-getter', 'Boolean getters'),
                       ('setter', 'Setters'), ('fallback', 'Systematic fallbacks'),
                       ('field', 'Fields')):
        if examples[cat]:
            add(f'### {label}')
            add('')
            for ex in examples[cat]:
                add(f'- `{ex}`')
            add('')
    add('## Validation')
    add('')
    if problems:
        add(f'{len(problems)} problem(s) found:')
        add('')
        for p in problems[:50]:
            add(f'- {p}')
    else:
        add('- 0 invalid names')
        add('- 0 keyword conflicts')
        add('- 0 duplicate name+descriptor within a class')
        add('- 0 collisions with kept/inherited members')
    add('')
    add('## Notes')
    add('')
    add('- Descriptors are exact, taken from the compiled class files.')
    add('- Trivial getters/setters are recognised from exact bytecode shapes')
    add('  (`aload_0; getfield f; xreturn`, `aload_0; *load_1; putfield f; return`);')
    add('  a decompiled-source regex fallback covers anything the parser misses.')
    add('- Methods overriding anything outside `com/moonsworth` (Minecraft, shaded')
    add('  libraries, JDK) are deliberately left with their original names so the')
    add('  override relationship survives the bytecode rename and re-decompilation.')
    add('- Override/implementation groups inside the scope share one generated name.')
    add('- Generated names are checked against kept members, inherited members and')
    add('  unrelated methods in ancestor/descendant classes so no accidental')
    add('  override/implementation is introduced.')
    add('- `x`, `y`, `z` and short readable identifiers (`id`, `mc`, `cb`, ...) are kept.')
    add('- The decompiled tree is only a body-heuristic fallback: a source file is')
    add('  used when its owner exists in the parsed jar and the member lookup')
    add('  matches; files from a different namespace are ignored.')
    add('- Owners and descriptors refer to the namespace of the source jar above;')
    add('  apply this map to that exact jar revision (the sha256 is recorded).')

    with open(args.md, 'w', encoding='utf-8') as f:
        f.write('\n'.join(lines) + '\n')

    print(f'wrote {len(rows)} renames to {args.tsv}', file=sys.stderr)
    print(f'wrote report to {args.md}', file=sys.stderr)
    if problems:
        print(f'WARNING: {len(problems)} validation problems', file=sys.stderr)
        for p in problems[:10]:
            print('  ' + p, file=sys.stderr)
    return 0


if __name__ == '__main__':
    sys.exit(main())
