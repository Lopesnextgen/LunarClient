#!/usr/bin/env python3
"""Repair dangling obfuscated member references in rescued sources.

The old member-rename pass renamed declarations but missed many call sites, so
the jar (and therefore freshly decompiled classes) calls members that no longer
exist. For every obfuscated member reference (field or method) in a rescued
class we look up the owner in the jar, find the unique member with the same
descriptor, and rewrite the source token to that name.

Usage:
  tools/fix_dangling_members.py FILE.java [FILE.java ...] [--apply]
"""
import argparse
import os
import re
import struct
import zipfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
JAR = os.path.join(ROOT, 'libs/lunar-renamed-classes.jar')
TOKEN = re.compile(r'^[A-Z][A-Z0-9_$]{10,}$')
# Member calls are almost always `expr.TOKEN(...)`, so a match must be allowed
# right after a dot; only identifier characters (and $) block a match.
CALL_TOKEN = re.compile(r'(?<![A-Za-z0-9_$])([A-Z][A-Z0-9_$]{10,})(?![A-Za-z0-9_$])')


def parse_class(data):
    """returns (cp, tags, offset_after_cp) or None"""
    if len(data) < 10 or data[:4] != b'\xca\xfe\xba\xbe':
        return None
    cp_count = struct.unpack_from('>H', data, 8)[0]
    off = 10
    cp = [None] * cp_count
    tags = [0] * cp_count
    i = 1
    while i < cp_count:
        tag = data[off]
        off += 1
        if tag == 1:
            ln = struct.unpack_from('>H', data, off)[0]
            off += 2
            cp[i] = data[off:off + ln].decode('utf-8', 'replace')
            off += ln
        elif tag in (3, 4):
            off += 4
        elif tag in (5, 6):
            off += 8
            i += 1
        elif tag in (7, 8, 16, 19, 20):
            cp[i] = struct.unpack_from('>H', data, off)[0]
            off += 2
        elif tag in (9, 10, 11, 12, 17, 18):
            cp[i] = struct.unpack_from('>HH', data, off)
            off += 4
        elif tag == 15:
            off += 3
        else:
            return None
        tags[i] = tag
        i += 1
    return cp, tags, off


class JarIndex:
    def __init__(self, jar):
        self.z = zipfile.ZipFile(jar)
        self._members = {}
        self._supers = {}

    def _read(self, internal):
        try:
            return self.z.read(internal + '.class')
        except KeyError:
            return None

    def members(self, internal):
        if internal in self._members:
            return self._members[internal]
        out = {}
        data = self._read(internal)
        parsed = parse_class(data) if data else None
        if parsed:
            cp, _tags, off = parsed
            _acc, _this, _super = struct.unpack_from('>HHH', data, off)
            off += 6
            ifaces = struct.unpack_from('>H', data, off)[0]
            off += 2 + 2 * ifaces
            for _ in range(2):  # fields then methods
                count = struct.unpack_from('>H', data, off)[0]
                off += 2
                for _ in range(count):
                    _f, n, d, attrs = struct.unpack_from('>HHHH', data, off)
                    off += 8
                    name = cp[n] if n and n < len(cp) else None
                    desc = cp[d] if d and d < len(cp) else None
                    if name:
                        out[name] = desc
                    for _a in range(attrs):
                        _ai, ln = struct.unpack_from('>HI', data, off)
                        off += 6 + ln
        self._members[internal] = out
        return out

    def supers(self, internal):
        if internal in self._supers:
            return self._supers[internal]
        out = []
        data = self._read(internal)
        parsed = parse_class(data) if data else None
        if parsed:
            cp, _tags, off = parsed
            _acc, _this, super_c = struct.unpack_from('>HHH', data, off)
            if super_c and cp[super_c]:
                out.append(cp[cp[super_c]])
            ifaces = struct.unpack_from('>H', data, off + 6)[0]
            pos = off + 8
            for _ in range(ifaces):
                idx = struct.unpack_from('>H', data, pos)[0]
                pos += 2
                if cp[idx]:
                    out.append(cp[cp[idx]])
        self._supers[internal] = out
        return out

    def resolve(self, internal, desc, seen=None):
        """unique non-token member with this descriptor, searching supers"""
        if seen is None:
            seen = set()
        if internal in seen:
            return None
        seen.add(internal)
        mem = self.members(internal)
        cands = [n for n, d in mem.items() if d == desc and not TOKEN.match(n)]
        if len(cands) == 1:
            return cands[0]
        if len(cands) > 1:
            return None
        for s in self.supers(internal):
            got = self.resolve(s, desc, seen)
            if got:
                return got
        return None

    def class_refs(self, internal):
        """[(owner_name, token, desc)] member references declared in the cp"""
        data = self._read(internal)
        parsed = parse_class(data) if data else None
        if not parsed:
            return []
        cp, tags, _off = parsed
        out = []
        for i, tag in enumerate(tags):
            if tag in (9, 10, 11) and cp[i]:  # Fieldref / Methodref / InterfaceMethodref
                cls_idx, nat_idx = cp[i]
                if not (cls_idx and nat_idx and cls_idx < len(cp) and nat_idx < len(cp)):
                    continue
                owner_idx = cp[cls_idx]
                owner = cp[owner_idx] if isinstance(owner_idx, int) and owner_idx < len(cp) else None
                nat = cp[nat_idx]
                if not owner or not isinstance(nat, tuple):
                    continue
                name_i, desc_i = nat
                name = cp[name_i] if name_i < len(cp) else None
                desc = cp[desc_i] if desc_i < len(cp) else None
                if name and desc and TOKEN.match(name):
                    out.append((owner, name, desc))
        return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('files', nargs='+')
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()

    jar = JarIndex(JAR)
    total_fixed = 0
    for path in args.files:
        rel = os.path.relpath(os.path.abspath(path), os.path.join(ROOT, 'src/main/java'))[:-5]
        internal = rel.replace(os.sep, '/')
        refs = jar.class_refs(internal)
        if not refs:
            continue
        mapping = {}
        for owner, name, desc in refs:
            new = jar.resolve(owner, desc)
            if new:
                mapping.setdefault(name, set()).add(new)
        usable = {k: next(iter(v)) for k, v in mapping.items() if len(v) == 1}
        # injectivity: two different tokens must not collapse to the same name
        claims = {}
        for tok, new in usable.items():
            claims.setdefault(new, []).append(tok)
        bad = {new for new, toks in claims.items() if len(toks) > 1}
        usable = {k: v for k, v in usable.items() if v not in bad}
        if not usable:
            continue
        text = open(path, encoding='utf-8', errors='replace').read()
        hits = [0]

        def repl(m):
            tok = m.group(1)
            if tok in usable:
                hits[0] += 1
                return usable[tok]
            return tok

        new_text = CALL_TOKEN.sub(repl, text)
        if hits[0]:
            total_fixed += hits[0]
            print(f'{path}: {hits[0]} fixed ({", ".join(f"{k}->{v}" for k, v in list(usable.items())[:3])})')
            if args.apply:
                open(path, 'w', encoding='utf-8').write(new_text)
    print(f'total fixed: {total_fixed} in {"APPLY" if args.apply else "check"} mode')


if __name__ == '__main__':
    main()
