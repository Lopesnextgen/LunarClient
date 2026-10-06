#!/usr/bin/env python3
"""Analyze Lunar jars: package breakdown, class versions, naming mix, duplicates."""
import zipfile
import collections
import re
import sys
import os

JARS = {
    'lunar': 'lunar.jar',
    'genesis': 'genesis-0.1.0-SNAPSHOT-all.jar',
    'forge': 'forge-0.1.0-SNAPSHOT-all.jar',
    'legacy': 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar',
    'common': 'common-0.1.0-SNAPSHOT-all-nomappings.jar',
    'optifine': 'optifine-0.1.0-SNAPSHOT-all.jar',
    'replaymixins': 'lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar',
}
BASE = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                   'libs', 'multiver-full')


def main():
    all_classes = {}
    for label, fn in JARS.items():
        path = os.path.join(BASE, fn)
        if not os.path.exists(path):
            continue
        z = zipfile.ZipFile(path)
        versions = collections.Counter()
        tops = collections.Counter()
        funcs = fields = v1 = 0
        names = []
        for info in z.infolist():
            if not info.filename.endswith('.class'):
                continue
            name = info.filename
            names.append(name)
            d = z.read(info)
            if len(d) > 8:
                major = int.from_bytes(d[6:8], 'big')
                versions[major] += 1
            if b'func_' in d:
                funcs += 1
            if b'field_' in d:
                fields += 1
            if b'$v1_' in d:
                v1 += 1
            parts = name[:-6].split('/')
            tops['/'.join(parts[:2]) if len(parts) > 1 else parts[0]] += 1
        print(f"== {label} ({fn}): {len(names)} classes")
        print(f"   major versions: {dict(versions)}")
        print(f"   classes with func_: {funcs}, field_: {fields}, $v1_: {v1}")
        print(f"   top packages: {tops.most_common(8)}")
        for n in names:
            all_classes.setdefault(n, []).append(label)

    print("\n== DUPLICATES across jars ==")
    dups = {k: v for k, v in all_classes.items() if len(v) > 1}
    print(f"class paths present in >1 jar: {len(dups)}")
    combos = collections.Counter(tuple(sorted(v)) for v in dups.values())
    for c, n in combos.most_common(20):
        print(f"   {n:5d}  {c}")

    # single-letter vanilla class refs using vanilla obf names from kin
    sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
    from kin2tsrg import parse
    named = parse(os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                          'libs', 'multiver-full', 'lunar-platform-mappings-v1_8.jar'))
    obf_names = set()
    for c in named:
        obf_names.add(c['obf'])
        for i in c['inner']:
            obf_names.add(i['obf'])
    print(f"\nvanilla obf class names in kin: {len(obf_names)}")
    short = {n for n in obf_names if len(n) <= 4 and re.fullmatch(r'[a-zA-Z_$][\w$]*', n)}
    print(f"short obf names: {len(short)}")

    for label, fn in [('lunar', JARS['lunar']), ('forge', JARS['forge']), ('legacy', JARS['legacy'])]:
        z = zipfile.ZipFile(os.path.join(BASE, fn))
        hits = 0
        samples = []
        for info in z.infolist():
            if not info.filename.endswith('.class'):
                continue
            d = z.read(info)
            found = []
            for m in re.finditer(rb'[\x01\x07][\x00-\xff]{0,128}', d):
                pass
            # crude: check each obf name as class descriptor
            for n in short:
                if b'L' + n.encode() + b';' in d:
                    found.append(n)
            if found:
                hits += 1
                if len(samples) < 5:
                    samples.append((info.filename, found[:10]))
        print(f"{label}: classes referencing short vanilla-obf descriptors: {hits}")
        for s in samples:
            print('   ', s)


if __name__ == '__main__':
    main()
