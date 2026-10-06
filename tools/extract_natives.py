#!/usr/bin/env python3
"""Extract the vendored natives zips into a runtime directory.

The per-OS native bundles Lunar ships (client jinput/lwjgl/openal/webp,
Ultralight/WebCore and the WebOSR binding) are vendored under libs/natives/,
together with the WebOSR `resources/` payload (icudt67l.dat, cacert.pem).
No Lunar Client install is needed; the launcher extracts these into run/game.

Usage: tools/extract_natives.py [--out run/game/natives] [--platform TAG]
"""
import argparse
import glob
import os
import shutil
import sys
import zipfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
NATIVES = os.path.join(ROOT, 'libs', 'natives')


def platform_tags():
    if os.name == 'nt':
        return ['windows', 'win']
    if sys.platform == 'darwin':
        return ['macos', 'osx', 'darwin', 'mac']
    return ['linux']


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--out', default=os.path.join(ROOT, 'run', 'game', 'natives'))
    ap.add_argument('--platform', default=None,
                    help='override the platform tag used to pick zips')
    args = ap.parse_args()

    tags = [args.platform] if args.platform else platform_tags()
    zips = []
    for path in sorted(glob.glob(os.path.join(NATIVES, '*.zip'))):
        base = os.path.basename(path).lower()
        if any(t in base for t in tags):
            zips.append(path)
    if not zips:
        print(f'[natives] no vendored zips for {"/".join(tags)} in {NATIVES}',
              file=sys.stderr)
        return 1
    os.makedirs(args.out, exist_ok=True)
    for path in zips:
        with zipfile.ZipFile(path) as zf:
            zf.extractall(args.out)
        print(f'[natives] extracted {os.path.relpath(path, ROOT)}')
    res = os.path.join(NATIVES, 'resources')
    if os.path.isdir(res):
        shutil.copytree(res, os.path.join(args.out, 'resources'), dirs_exist_ok=True)
        print(f'[natives] copied {os.path.relpath(res, ROOT)}')
    return 0


if __name__ == '__main__':
    sys.exit(main())
