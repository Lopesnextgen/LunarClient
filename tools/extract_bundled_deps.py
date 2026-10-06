#!/usr/bin/env python3
"""Extract the third-party classes bundled inside the Lunar reference jar.

libs/lunar-renamed-classes.jar repackages Lunar's runtime dependencies (Mixin,
kyori adventure, protobuf, kotlin, jctools, ...) next to the Lunar classes.
The Lunar code (com/moonsworth/**, com/lunarclient/**) now lives in
src/main/java, so the build only needs the third-party slice. Keeping it in a
separate jar makes the "no Lunar classes from the reference jar" state
auditable; each package can later be replaced by its Maven coordinate.

Usage:
  tools/extract_bundled_deps.py [--apply] [--out libs/lunar-bundled-libs.jar]
  tools/extract_bundled_deps.py --only-prefix com/lunarclient \
      --out libs/lunarclient-protocol.jar [--apply]
"""
import argparse
import os
import sys
import zipfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DEFAULT_JAR = os.path.join(ROOT, 'libs/lunar-renamed-classes.jar')
SKIP_PREFIXES = ('com/moonsworth/', 'com/lunarclient/')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--jar', default=DEFAULT_JAR)
    ap.add_argument('--out', default=os.path.join(ROOT, 'libs/lunar-bundled-libs.jar'))
    ap.add_argument('--only-prefix', default=None,
                    help='extract only entries under this prefix (e.g. com/lunarclient) '
                         'instead of the third-party slice')
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()

    src = zipfile.ZipFile(args.jar)
    keep, skip = [], 0
    for n in src.namelist():
        if n.endswith('/'):
            continue
        if args.only_prefix:
            if n.startswith(args.only_prefix + '/'):
                keep.append(n)
            continue
        if any(n.startswith(p) for p in SKIP_PREFIXES):
            skip += 1
            continue
        keep.append(n)
    print(f'included entries: {len(keep)} | lunar entries skipped: {skip}')
    if not args.apply:
        print('dry run; pass --apply to write', args.out)
        return 0
    with zipfile.ZipFile(args.out, 'w', zipfile.ZIP_DEFLATED) as out:
        for n in keep:
            out.writestr(n, src.read(n))
    print('wrote', args.out, os.path.getsize(args.out), 'bytes')
    return 0


if __name__ == '__main__':
    sys.exit(main())
