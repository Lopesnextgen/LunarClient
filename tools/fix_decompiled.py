#!/usr/bin/env python3
"""Mechanical fixes for known Vineflower 1.12 output artifacts.

Usage: fix_decompiled.py [--root DIR] [--apply]

Fixes
-----
1. "record with explicit fields": Vineflower emits ``record X()`` plus normal
   instance fields for classes whose Record attribute lacks components.
   Rewriting the declaration to ``class X`` makes the emitted fields and
   constructor valid without changing behaviour (equals/hashCode/toString are
   already emitted explicitly by the decompiler).
2. "anonymous enum subclass": files named ``Outer$N.java`` decompiled as
   top-level ``enum`` declarations. Their bodies are already inlined in the
   outer enum source, so these files are redundant; they are removed.
"""
import argparse
import os
import re

RECORD_DECL = re.compile(
    r'^(?P<indent>[ \t]*)(?P<mods>(?:(?:public|final|abstract|sealed|non-sealed|strictfp|static|private|protected)\s+)*)'
    r'record\s+(?P<name>[\w$]+)(?P<gen><[^()]*?>)?\s*\((?P<params>[^)]*)\)',
    re.M)
INSTANCE_FIELD = re.compile(r'^\s*(?:private|protected)\s+final\s+(?!static)', re.M)
NUM_IMPORT = re.compile(r'^import\s+([\w.$]+)\.(\d+)\s*;\s*\n', re.M)
ENUM_DECL = re.compile(r'^([ \t]*(?:(?:public|final|abstract|strictfp|static)\s+)*enum\s+[\w$]+[^{]*\{)[ \t]*\n', re.M)
FIRST_MEMBER = re.compile(r'^\s*(?:[A-Z][A-Z0-9_]*\s*[(,;{]|;)')
DIAMOND_CAST = re.compile(r'\(\s*([A-Za-z_$][\w$.]*)\s*<\s*>\s*\)')


def _match_brace(text, open_idx):
    """Return index of the `}` matching `text[open_idx] == '{'`, skipping
    strings, chars and comments."""
    i = open_idx
    depth = 0
    n = len(text)
    while i < n:
        c = text[i]
        if c == '"':
            i += 1
            while i < n and text[i] != '"':
                if text[i] == '\\':
                    i += 1
                i += 1
        elif c == "'":
            i += 1
            while i < n and text[i] != "'":
                if text[i] == '\\':
                    i += 1
                i += 1
        elif c == '/' and i + 1 < n and text[i + 1] == '/':
            while i < n and text[i] != '\n':
                i += 1
        elif c == '/' and i + 1 < n and text[i + 1] == '*':
            i += 2
            while i + 1 < n and not (text[i] == '*' and text[i + 1] == '/'):
                i += 1
            i += 1
        elif c == '{':
            depth += 1
        elif c == '}':
            depth -= 1
            if depth == 0:
                return i
        i += 1
    return -1


def _enum_bodies(text):
    """Yield (body_start, body_end, enum_name) spans for every enum body."""
    for m in re.finditer(r'\benum\s+([\w$]+)[^{;]*\{', text):
        name = m.group(1)
        open_idx = text.index('{', m.start())
        end = _match_brace(text, open_idx)
        if end < 0:
            continue
        yield open_idx + 1, end, name


CONSTANT_LINE = re.compile(r'^[ \t]*([A-Za-z_$][\w$]*)\s*[(,;{]')
SKIP_LINE = re.compile(r'^\s*(?:@|//|/\*|\*)')


def _real_line_indices(lines):
    out = []
    for i, l in enumerate(lines):
        s = l.strip()
        if not s or SKIP_LINE.match(l):
            continue
        out.append(i)
    return out


def fix_empty_enums(path, apply):
    """Enum bodies must start with `;` when they have no constants, and must
    not have a stray `;` before their constants."""
    with open(path, encoding='utf-8', errors='replace') as f:
        text = f.read()
    if 'enum ' not in text:
        return 0
    edits = []
    for start, end, name in _enum_bodies(text):
        lines = text[start:end].split('\n')
        real = _real_line_indices(lines)
        if not real:
            continue
        first_i = real[0]
        first = lines[first_i].strip()

        def is_constant(idx):
            m = CONSTANT_LINE.match(lines[idx])
            if not m:
                return False
            ident = m.group(1)
            if ident == name or ident in ('public', 'private', 'protected', 'static',
                                          'final', 'abstract', 'synchronized', 'class',
                                          'interface', 'enum', 'record'):
                return False
            return True

        constants = is_constant(first_i)
        if first == ';' and len(real) > 1 and is_constant(real[1]):
            constants = True
        if constants and first == ';':
            off = start
            for i in range(first_i):
                off += len(lines[i]) + 1
            edits.append((off, off + len(lines[first_i]) + 1, ''))
        elif not constants and first != ';':
            off = start
            for i in range(first_i):
                off += len(lines[i]) + 1
            edits.append((off, off, '   ;\n'))
    for a, b, repl in sorted(edits, reverse=True):
        text = text[:a] + repl + text[b:]
    if edits and apply:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(text)
    return len(edits)


def fix_diamond_casts(path, apply):
    """Vineflower can emit `(Supplier<>)` casts which are not legal Java."""
    with open(path, encoding='utf-8', errors='replace') as f:
        text = f.read()
    if '<>' not in text:
        return 0
    new, n = DIAMOND_CAST.subn(lambda m: '(' + m.group(1) + ')', text)
    if n and apply:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(new)
    return n


def fix_numeric_anonymous(path, apply):
    """Vineflower may emit `import pkg.Outer.1;` and `new 1(...)` for
    anonymous classes. Rewrite the references to the binary name `Outer$1`."""
    with open(path, encoding='utf-8', errors='replace') as f:
        text = f.read()
    if not NUM_IMPORT.search(text):
        return 0
    mapping = {}

    def collect(m):
        fq, n = m.group(1), m.group(2)
        simple = fq.rsplit('.', 1)[-1]
        mapping[n] = simple + '$' + n
        return ''

    text, n = NUM_IMPORT.subn(collect, text)
    for num, repl in mapping.items():
        text = re.sub(r'(?<![\w.$])new\s+' + num + r'(?=\s*\()', 'new ' + repl, text)
        text = re.sub(r'(?<![\w.$])' + num + r'(?=\s+[A-Za-z_$][\w$]*\s*[=;,)\]])',
                      repl, text)
        text = re.sub(r'\(\s*' + num + r'\s*\)(?=\s*[A-Za-z_$])', '(' + repl + ')', text)
        text = re.sub(r'(?<=<)\s*' + num + r'\s*(?=>)', repl, text)
    if apply:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(text)
    return n + len(mapping)


def fix_records(path, apply):
    with open(path, encoding='utf-8', errors='replace') as f:
        text = f.read()
    if 'record ' not in text or not INSTANCE_FIELD.search(text):
        return 0
    new, n = RECORD_DECL.subn(
        lambda m: (m.group('indent') + m.group('mods') + 'class ' + m.group('name')
                   + (m.group('gen') or '')), text)
    if n and apply:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(new)
    return n


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--root', default=os.path.join(os.path.dirname(os.path.abspath(__file__)),
                                                   '..', 'src', 'main', 'java', 'com'))
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--remove-anon-enums', action='store_true')
    args = ap.parse_args()

    records = 0
    record_files = 0
    numeric = 0
    enums = 0
    diamonds = 0
    anon_enums = []
    for root, _, files in os.walk(args.root):
        for fn in files:
            if not fn.endswith('.java'):
                continue
            p = os.path.join(root, fn)
            n = fix_records(p, args.apply)
            if n:
                records += n
                record_files += 1
            numeric += fix_numeric_anonymous(p, args.apply)
            enums += fix_empty_enums(p, args.apply)
            diamonds += fix_diamond_casts(p, args.apply)
            base = fn[:-5]
            if re.search(r'\$\d+$', base):
                try:
                    head = open(p, encoding='utf-8', errors='replace').read(400)
                except OSError:
                    continue
                if re.search(r'\benum\s+\w+\$\d+\s*\{', head):
                    anon_enums.append(p)
    print(f'record declarations fixed: {records} in {record_files} files')
    print(f'numeric anonymous references fixed: {numeric}')
    print(f'empty enums fixed: {enums}')
    print(f'diamond casts fixed: {diamonds}')
    print(f'anonymous enum files found: {len(anon_enums)}')
    for p in anon_enums[:20]:
        print('   ', os.path.relpath(p, args.root))
    if args.remove_anon_enums:
        for p in anon_enums:
            os.remove(p)
        print(f'removed {len(anon_enums)} anonymous enum files')


if __name__ == '__main__':
    main()
