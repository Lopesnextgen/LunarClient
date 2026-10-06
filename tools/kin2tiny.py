#!/usr/bin/env python3
"""Convert Lunar Client .kin mappings to Tiny v2 mappings.

Usage: kin2tiny.py <out.tiny> <in1.kin> <in2.kin> ...
"""
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
        v = int.from_bytes(self.d[self.p:self.p + 4], 'big', signed=True)
        self.p += 4
        return v

    def utf(self):
        ln = int.from_bytes(self.d[self.p:self.p + 2], 'big')
        self.p += 2
        raw = self.d[self.p:self.p + ln]
        self.p += ln
        return raw.decode('utf-8', 'replace')


def parse_kin(path):
    data = open(path, 'rb').read()
    r = Reader(data)
    magic = r.i4()
    if magic != 99151942:
        raise ValueError(f'{path}: bad magic {magic}')
    ver = r.u1()
    if ver != 1:
        raise ValueError(f'{path}: bad version {ver}')
    for _ in range(r.i4()):
        r.utf()

    def read_class():
        obf = r.utf()
        deobf = r.utf()
        inner = [read_class() for _ in range(r.i4())]
        fields = [(r.utf(), r.utf(), r.utf()) for _ in range(r.i4())]
        methods = [(r.utf(), r.utf(), r.utf()) for _ in range(r.i4())]
        return {'obf': obf, 'deobf': deobf, 'inner': inner,
                'fields': fields, 'methods': methods}

    return [read_class() for _ in range(r.i4())]


class Mapping:
    """Collects class/field/method mappings, keyed for fast output."""

    def __init__(self):
        self.classes = {}          # obf class -> named class
        self.fields = {}           # obf class -> {obf field: (desc, named)}
        self.methods = {}          # obf class -> {(obf method, desc): named}
        self.conflicts = 0

    def add_class(self, obf, named):
        old = self.classes.get(obf)
        if old is not None and old != named:
            self.conflicts += 1
            return
        self.classes[obf] = named

    def add_field(self, cls, obf, desc, named):
        d = self.fields.setdefault(cls, {})
        old = d.get(obf)
        if old is not None and old != (desc, named):
            self.conflicts += 1
            return
        d[obf] = (desc, named)

    def add_method(self, cls, obf, desc, named):
        d = self.methods.setdefault(cls, {})
        old = d.get((obf, desc))
        if old is not None and old != named:
            self.conflicts += 1
            return
        d[(obf, desc)] = named


def collect(classes, mapping, prefix=''):
    for c in classes:
        obf_full = prefix + c['obf']
        named_full = prefix + c['deobf']
        mapping.add_class(obf_full, named_full)
        for (fname, desc, fdeobf) in c['fields']:
            mapping.add_field(obf_full, fname, desc, fdeobf)
        for (mname, desc, mdeobf) in c['methods']:
            mapping.add_method(obf_full, mname, desc, mdeobf)
        collect(c['inner'], mapping, obf_full + '$')


def main():
    out = sys.argv[1]
    mapping = Mapping()
    for path in sys.argv[2:]:
        collect(parse_kin(path), mapping)

    nf = sum(len(v) for v in mapping.fields.values())
    nm = sum(len(v) for v in mapping.methods.values())
    with open(out, 'w', encoding='utf-8') as f:
        f.write('tiny\t2\t0\tobf\tnamed\n')
        for obf in sorted(mapping.classes):
            f.write(f'c\t{obf}\t{mapping.classes[obf]}\n')
            for fname in sorted(mapping.fields.get(obf, {})):
                desc, named = mapping.fields[obf][fname]
                f.write(f'\tf\t{desc}\t{fname}\t{named}\n')
            for key in sorted(mapping.methods.get(obf, {})):
                mname, desc = key
                f.write(f'\tm\t{desc}\t{mname}\t{mapping.methods[obf][key]}\n')
    print(f'wrote {out}: {len(mapping.classes)} classes, {nf} fields, '
          f'{nm} methods, {mapping.conflicts} conflicts')


if __name__ == '__main__':
    main()
