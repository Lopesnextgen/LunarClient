#!/usr/bin/env python3
"""Parse Lunar .kin mappings and build a class/member remap table.

Usage:
  kin2names.py dump <kin-file>                    # stats + samples
  kin2names.py forge <kin> <in.jar> <out.jar>     # remap jar names->obf
  kin2names.py readable <kin> <in.jar> <out.jar>  # remap jar obf->readable
"""
import sys, struct, zipfile, io, json

class Reader:
    def __init__(self, data):
        self.d = data
        self.p = 0
    def i4(self):
        v = struct.unpack_from('>i', self.d, self.p)[0]; self.p += 4; return v
    def u1(self):
        v = self.d[self.p]; self.p += 1; return v
    def utf(self):
        n = struct.unpack_from('>H', self.d, self.p)[0]; self.p += 2
        s = self.d[self.p:self.p+n].decode('utf-8', 'replace'); self.p += n
        return s

def parse(path):
    data = open(path, 'rb').read()
    r = Reader(data)
    magic = r.i4()
    if magic != 99151942:
        raise ValueError(f'bad magic {magic!r}')
    ver = r.u1()
    # string pool
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

def main():
    cmd = sys.argv[1]
    if cmd == 'dump':
        cs = parse(sys.argv[2])
        print('top-level classes:', len(cs))
        total = 0
        def walk(c):
            nonlocal total
            total += 1
            for i in c['inner']:
                walk(i)
        for c in cs:
            walk(c)
        print('all classes:', total)
        for c in cs[:10]:
            print(' ', c['obf'], '->', c['deobf'], f"({len(c['fields'])}f {len(c['methods'])}m)")
        return

if __name__ == '__main__':
    main()
