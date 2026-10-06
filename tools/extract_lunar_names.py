#!/usr/bin/env python3
"""Extract the readable `com/lunarclient/**` classes that the shipped lunar.jar
contains (Apollo framework, mod definitions, options, events, protobuf, dfu).

These are Lunar's own names — ground truth for renaming the obfuscated
`com/moonsworth` counterparts.

Usage: extract_lunar_names.py [jar ...] > tools/mappings-snapshot/lunar-client-names.tsv
"""
import sys
import zipfile

DEFAULT_JARS = [
    os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                 'libs', 'multiver-full', 'lunar.jar'),
]

PROTO_HINTS = ('/v1/', '/v2/', '/v3/', '/v4/')


def main():
    jars = sys.argv[1:] or DEFAULT_JARS
    seen = {}
    for jar in jars:
        try:
            z = zipfile.ZipFile(jar)
        except OSError as exc:
            print(f'# skip {jar}: {exc}', file=sys.stderr)
            continue
        for name in z.namelist():
            if not name.startswith('com/lunarclient/') or not name.endswith('.class'):
                continue
            if '$' in name:
                continue
            fqcn = name[:-6].replace('/', '.')
            if fqcn in seen:
                continue
            kind = 'protobuf' if any(h in name for h in PROTO_HINTS) else 'readable'
            seen[fqcn] = (kind, jar)
    print('# fqcn\tkind\tsource_jar')
    for fqcn, (kind, jar) in sorted(seen.items()):
        print(f'{fqcn}\t{kind}\t{jar.rsplit("/", 1)[-1]}')


if __name__ == '__main__':
    main()
