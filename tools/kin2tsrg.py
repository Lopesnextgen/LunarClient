#!/usr/bin/env python3
"""Convert Lunar .kin mappings to TSRG (simple, readable) and provide lookups."""
import struct
import sys


class Reader:
    def __init__(self, data):
        self.d = data
        self.p = 0

    def u1(self):
        v = self.d[self.p]
        self.p += 1
        return v

    def i4(self):
        v = struct.unpack_from('>i', self.d, self.p)[0]
        self.p += 4
        return v

    def utf(self):
        ln = struct.unpack_from('>H', self.d, self.p)[0]
        self.p += 2
        raw = self.d[self.p:self.p + ln]
        self.p += ln
        try:
            return raw.decode('utf-8')
        except UnicodeDecodeError:
            return raw.decode('utf-8', 'replace')


def parse(path):
    data = open(path, 'rb').read()
    r = Reader(data)
    magic = r.i4()
    assert magic == 99151942, hex(magic)
    ver = r.u1()
    assert ver == 1, ver
    for _ in range(r.i4()):
        r.utf()
    classes = []

    def read_class():
        obf = r.utf()
        deobf = r.utf()
        inner = [read_class() for _ in range(r.i4())]
        fields = [(r.utf(), r.utf(), r.utf()) for _ in range(r.i4())]
        methods = [(r.utf(), r.utf(), r.utf()) for _ in range(r.i4())]
        return {'obf': obf, 'deobf': deobf, 'inner': inner,
                'fields': fields, 'methods': methods}

    for _ in range(r.i4()):
        classes.append(read_class())
    return classes


def write_tsrg(classes, out):
    def emit(c, indent):
        out.write(' ' * indent + c['obf'] + ' ' + c['deobf'] + '\n')
        for f in c['fields']:
            out.write(' ' * (indent + 1) + f[0] + ' ' + f[2] + '\n')
        for m in c['methods']:
            out.write(' ' * (indent + 1) + m[0] + ' ' + m[1] + ' ' + m[2] + '\n')
        for i in c['inner']:
            emit(i, indent + 1)
    for c in classes:
        emit(c, 0)


if __name__ == '__main__':
    src = sys.argv[1]
    dst = sys.argv[2]
    cls = parse(src)
    with open(dst, 'w') as f:
        write_tsrg(cls, f)
    print(f"wrote {dst}")
