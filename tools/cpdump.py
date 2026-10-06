#!/usr/bin/env python3
"""Dump CONSTANT_Class / Fieldref / Methodref entries from class files (minimal class parser)."""
import struct
import sys
import zipfile

TAG_UTF8 = 1
TAG_INT = 3
TAG_FLOAT = 4
TAG_LONG = 5
TAG_DOUBLE = 6
TAG_CLASS = 7
TAG_STRING = 8
TAG_FIELDREF = 9
TAG_METHODREF = 10
TAG_IFACEREF = 11
TAG_NAMEANDTYPE = 12
TAG_METHODHANDLE = 15
TAG_METHODTYPE = 16
TAG_DYNAMIC = 17
TAG_INVOKEDYNAMIC = 18
TAG_MODULE = 19
TAG_PACKAGE = 20


def parse_cp(data):
    if data[:4] != b'\xca\xfe\xba\xbe':
        raise ValueError('not a class')
    count = struct.unpack_from('>H', data, 8)[0]
    p = 10
    cp = [None] * count
    i = 1
    while i < count:
        tag = data[p]
        p += 1
        if tag == TAG_UTF8:
            ln = struct.unpack_from('>H', data, p)[0]
            cp[i] = ('utf8', data[p + 2:p + 2 + ln].decode('utf-8', 'replace'))
            p += 2 + ln
        elif tag in (TAG_INT, TAG_FLOAT):
            cp[i] = ('const', struct.unpack_from('>i', data, p)[0])
            p += 4
        elif tag in (TAG_LONG, TAG_DOUBLE):
            cp[i] = ('const', struct.unpack_from('>q', data, p)[0])
            p += 8
            i += 1
        elif tag in (TAG_CLASS, TAG_STRING, TAG_METHODTYPE, TAG_MODULE, TAG_PACKAGE):
            cp[i] = (tag, struct.unpack_from('>H', data, p)[0])
            p += 2
        elif tag in (TAG_FIELDREF, TAG_METHODREF, TAG_IFACEREF, TAG_NAMEANDTYPE, TAG_DYNAMIC, TAG_INVOKEDYNAMIC):
            cp[i] = (tag, struct.unpack_from('>HH', data, p)[0], struct.unpack_from('>H', data, p + 2)[0])
            p += 4
        elif tag == TAG_METHODHANDLE:
            cp[i] = (tag, data[p], struct.unpack_from('>H', data, p + 1)[0])
            p += 3
        else:
            raise ValueError(f'bad tag {tag} at {p}')
        i += 1
    return cp


def cp_str(cp, idx):
    e = cp[idx]
    if e is None:
        return None
    if e[0] == 'utf8':
        return e[1]
    if e[0] == TAG_CLASS:
        return cp_str(cp, e[1])
    if e[0] == TAG_STRING:
        return cp_str(cp, e[1])
    if e[0] == TAG_NAMEANDTYPE:
        return cp_str(cp, e[1]) + '.' + cp_str(cp, e[2])
    if e[0] in (TAG_FIELDREF, TAG_METHODREF, TAG_IFACEREF):
        return cp_str(cp, e[1]) + '.' + cp_str(cp, e[2])
    return str(e)


def dump(data, want):
    cp = parse_cp(data)
    out = []
    for i, e in enumerate(cp):
        if e is None:
            continue
        if e[0] in (TAG_CLASS, TAG_FIELDREF, TAG_METHODREF, TAG_IFACEREF):
            s = cp_str(cp, i)
            if want is None or want in s:
                kind = {TAG_CLASS: 'CLASS', TAG_FIELDREF: 'FIELD', TAG_METHODREF: 'METHOD',
                        TAG_IFACEREF: 'IFACE'}[e[0]]
                out.append(f'{kind} {s}')
    return out


if __name__ == '__main__':
    jar = zipfile.ZipFile(sys.argv[1])
    pat = sys.argv[2]
    limit = int(sys.argv[3]) if len(sys.argv) > 3 else 1
    shown = 0
    for info in jar.infolist():
        if not info.filename.endswith('.class'):
            continue
        if pat not in info.filename:
            continue
        data = jar.read(info)
        lines = dump(data, sys.argv[4] if len(sys.argv) > 4 else None)
        print('==== ' + info.filename)
        for l in lines[:60]:
            print('  ', l)
        shown += 1
        if shown >= limit:
            break
