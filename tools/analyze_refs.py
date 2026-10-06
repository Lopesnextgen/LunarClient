#!/usr/bin/env python3
"""Analyze naming mix in Lunar jars via constant pool class refs."""
import zipfile
import collections
import os
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from cpdump import parse_cp, cp_str, TAG_CLASS

BASE = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                   'libs', 'multiver-full')
JARS = {
    'lunar': 'lunar.jar',
    'genesis': 'genesis-0.1.0-SNAPSHOT-all.jar',
    'forge': 'forge-0.1.0-SNAPSHOT-all.jar',
    'legacy': 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar',
    'optifine': 'optifine-0.1.0-SNAPSHOT-all.jar',
    'replaymixins': 'lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar',
}


def class_refs(data):
    try:
        cp = parse_cp(data)
    except Exception:
        return []
    out = []
    for e in cp:
        if e and e[0] == TAG_CLASS:
            s = cp_str(cp, e[1])
            if s:
                out.append(s)
    return out


def main():
    from kin2tsrg import parse
    named = parse(os.path.join(os.path.dirname(os.path.abspath(__file__)),
                               'work/mappings/lunar/lunar_named_b5_1.8.9.kin'))
    obf_classes = set()
    for c in named:
        for cc in [c] + c['inner']:
            obf_classes.add(cc['obf'])
    short_obf = {n for n in obf_classes if len(n) <= 3}

    for label, fn in JARS.items():
        path = os.path.join(BASE, fn)
        z = zipfile.ZipFile(path)
        stats = collections.Counter()
        samples = []
        for info in z.infolist():
            if not info.filename.endswith('.class'):
                continue
            d = z.read(info)
            refs = class_refs(d)
            stats['classes'] += 1
            for r in refs:
                if r.startswith('net/minecraft/'):
                    stats['refs_named'] += 1
                elif r in short_obf:
                    stats['refs_vanilla_obf'] += 1
                    if len(samples) < 8:
                        samples.append((info.filename, r))
                if r.startswith('com/moonsworth/'):
                    stats['refs_moonsworth'] += 1
        print(f"== {label}: {dict(stats)}")
        for s in samples:
            print('    ', s)


if __name__ == '__main__':
    main()
