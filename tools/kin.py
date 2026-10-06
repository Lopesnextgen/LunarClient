#!/usr/bin/env python3
"""Parse Lunar's .kin mapping format (BinaryMappingsReader, big-endian).

Format:
  int32 magic 99151942
  int8  version
  int32 string-pool count (each utf8 string in a pool; ignored for lookups)
  int32 top-level class count
  per class:
    UTF obfName, UTF deobfName,
    int32 inner-class count (recursively),
    int32 field count -> (obfName, desc, deobfName, fieldType int?)
    int32 method count -> (obfName, desc, deobfName)
"""
import sys, struct, zipfile, json

class Reader:
    def __init__(self, data):
        self.d = data
        self.o = 0

    def i4(self):
        v = struct.unpack_from('>i', self.d, self.o)[0]; self.o += 4; return v

    def u1(self):
        v = self.d[self.o]; self.o += 1; return v

    def utf(self):
        n = struct.unpack_from('>H', self.d, self.o)[0]; self.o += 2
        s = self.d[self.o:self.o + n].decode('utf-8', 'replace'); self.o += n
        return s

def parse(data):
    r = Reader(data)
    magic = r.i4()
    version = r.u1()
    if magic != 99151942:
        raise ValueError(f'bad magic {magic}')
    # 1.8.9 b5 format: after version there is a string-pool count and, when it
    # is non-zero, that many entries.  Empirically the files here have 0 then
    # the class count directly; detect both.
    a = r.i4()
    if a == 0:
        classes = r.i4()
        pool = 0
    else:
        pool = a
        # entries are utf8 strings; skip them
        for _ in range(pool):
            r.utf()
        classes = r.i4()
    out = {
        'magic': magic, 'version': version, 'pool': pool, 'classes': classes,
        'classMap': {}, 'fieldMap': {}, 'methodMap': {},
    }
    for _ in range(classes):
        obf = r.utf()
        deobf = r.utf()
        out['classMap'][obf] = deobf
        fields = r.i4()
        for _ in range(fields):
            fo = r.utf(); desc = r.utf(); fd = r.utf()
            out['fieldMap'].setdefault(obf, []).append((fo, desc, fd))
        methods = r.i4()
        for _ in range(methods):
            mo = r.utf(); desc = r.utf(); md = r.utf()
            out['methodMap'].setdefault(obf, []).append((mo, desc, md))
    return out

def main():
    kin = sys.argv[1]
    if kin.endswith('.jar'):
        name = sys.argv[2]
        z = zipfile.ZipFile(kin)
        data = z.read(name)
    else:
        data = open(kin, 'rb').read()
    m = parse(data)
    print(json.dumps({k: v for k, v in m.items() if k not in ('classMap', 'fieldMap', 'methodMap')}, indent=1), file=sys.stderr)
    print('classMap entries:', len(m['classMap']), file=sys.stderr)
    print('fieldMap owners:', len(m['fieldMap']), file=sys.stderr)
    print('methodMap owners:', len(m['methodMap']), file=sys.stderr)
    # show a few useful entries
    for k in list(m['classMap'])[:5]:
        print('  ', k, '->', m['classMap'][k], file=sys.stderr)
    out = sys.argv[3] if len(sys.argv) > 3 else None
    if out:
        json.dump(m, open(out, 'w'))

if __name__ == '__main__':
    main()
