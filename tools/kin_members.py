#!/usr/bin/env python3
"""Build runtime<->readable member maps from Lunar .kin files.

For each class:
  * method entries: (runtimeName, desc, readableName)
  * field entries:  (runtimeName, desc, readableName)
Readable names may carry Lunar version suffixes ($v1_8 etc.); we index both
the raw readable name and the suffix-stripped name.

Output: JSON with {classes, readable2runtime{cls: {readable: runtime}},
runtime2readable{cls: {runtime: readable}}}
"""
import sys, struct, json

class Reader:
    def __init__(self, data):
        self.d = data; self.p = 0
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
    assert r.i4() == 99151942
    r.u1()
    for _ in range(r.i4()):
        r.utf()
    out = []
    def read_class():
        obf = r.utf(); deobf = r.utf()
        inner = [read_class() for _ in range(r.i4())]
        fields = [(r.utf(), r.utf(), r.utf()) for _ in range(r.i4())]
        methods = [(r.utf(), r.utf(), r.utf()) for _ in range(r.i4())]
        return (obf, deobf, inner, fields, methods)
    for _ in range(r.i4()):
        out.append(read_class())
    return out

SUFFIXES = ('$v1_7', '$v1_8', '$v1_12')
def strip(n):
    for s in SUFFIXES:
        if n.endswith(s):
            return n[:-len(s)]
    return n

def main():
    kin_path, out_path = sys.argv[1], sys.argv[2]
    cs = parse(kin_path)
    r2r = {}   # readable name -> runtime name (global by simple+desc)
    classes = {}
    def walk(c, indent=''):
        obf, deobf, inner, fields, methods = c
        # class name mapping: deobf (readable) -> obf
        rc = {}
        rr = {}
        for ro, desc, rn in fields:
            rc[rn] = ro
            rc[strip(rn)] = ro
            rr[ro] = strip(rn)
        for ro, desc, rn in methods:
            rc[rn] = ro
            rc[strip(rn)] = ro
            rr[ro] = strip(rn)
        classes[deobf] = {'obf': obf, 'members': rc}
        for i in inner:
            walk(i, indent + '  ')
    for c in cs:
        walk(c)
    print(f'classes with member maps: {len(classes)}', file=sys.stderr)
    # sample
    for k in list(classes)[:3]:
        print(' ', k, '->', classes[k]['obf'], len(classes[k]['members']), file=sys.stderr)
    json.dump(classes, open(out_path, 'w'))

if __name__ == '__main__':
    main()
