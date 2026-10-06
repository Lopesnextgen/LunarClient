#!/usr/bin/env python3
"""Optionally refresh the vendored runtime copies from a local install.

The repository ships everything it needs (see libs/README.md) and nothing in
the normal build/launch flow reads from a Lunar Client or Minecraft install.
This tool exists only to *optionally* refresh those vendored copies from a
local installation; it always copies (never links), so the result can simply
be committed like the rest.

Usage:
  tools/vendor_from_install.py --plan
  tools/vendor_from_install.py --plan --lunar ~/.lunarclient
  tools/vendor_from_install.py --apply --lunar ~/.var/app/com.lunarclient.LunarClient/.lunarclient \
      --minecraft ~/.var/app/com.lunarclient.LunarClient/.minecraft
"""
import argparse
import os
import shutil
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LIBS = os.path.join(ROOT, 'libs')

LUNAR_ITEMS = [
    ('offline/multiver/lunar.jar', 'multiver-full/lunar.jar', False),
    ('offline/multiver/genesis-0.1.0-SNAPSHOT-all.jar', 'multiver-full/genesis-0.1.0-SNAPSHOT-all.jar', True),
    ('offline/multiver/common-0.1.0-SNAPSHOT-all-nomappings.jar', 'multiver-full/common-0.1.0-SNAPSHOT-all-nomappings.jar', True),
    ('offline/multiver/legacy-0.1.0-SNAPSHOT-all-nomappings.jar', 'multiver-full/legacy-0.1.0-SNAPSHOT-all-nomappings.jar', True),
    ('offline/multiver/optifine-0.1.0-SNAPSHOT-all.jar', 'multiver-full/optifine-0.1.0-SNAPSHOT-all.jar', True),
    ('offline/multiver/lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar', 'multiver-full/lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar', True),
    ('offline/multiver/forge-0.1.0-SNAPSHOT-all.jar', 'multiver-full/forge-0.1.0-SNAPSHOT-all.jar', True),
    ('offline/multiver/lunar-platform-mappings-v1_8.jar', 'multiver-full/lunar-platform-mappings-v1_8.jar', True),
    ('offline/multiver/lunar-lang.jar', 'multiver-full/lunar-lang.jar', True),
    ('offline/multiver/Forge_v1_8.jar', 'multiver-full/Forge_v1_8.jar', True),
    ('offline/multiver/OptiFine_v1_8.jar', 'multiver-full/OptiFine_v1_8.jar', True),
    ('offline/multiver/ReplayMod-v1_8-2.6.24.jar', 'multiver-full/ReplayMod-v1_8-2.6.24.jar', True),
    ('offline/multiver/natives/resources', 'natives/resources', True),
    ('ui', 'lunar-assets/ui', True),
    ('shared', 'lunar-assets/shared', True),
    ('textures', 'lunar-assets/textures', True),
]
VANILLA_ITEMS = [
    ('versions', 'vanilla/versions', True),
    ('libraries', 'vanilla/libraries', True),
    ('assets', 'vanilla/assets', True),
]


def copy_item(src, dst, only_missing, apply_):
    if only_missing and os.path.exists(dst):
        print(f'[vendor] keep    {os.path.relpath(dst, ROOT)} (exists)')
        return
    if not os.path.exists(src):
        print(f'[vendor] MISSING {src}')
        return
    print(f'[vendor] copy    {os.path.relpath(dst, ROOT)}  <-  {src}')
    if not apply_:
        return
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    if os.path.isdir(src):
        shutil.copytree(src, dst, dirs_exist_ok=True)
    else:
        shutil.copy2(src, dst)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--lunar', default=os.environ.get('LUNAR_OFFICIAL_DATA'),
                    help='path to a .lunarclient directory')
    ap.add_argument('--minecraft', default=os.environ.get('LUNAR_MC'),
                    help='path to a .minecraft directory (1.8.9 install)')
    ap.add_argument('--apply', action='store_true', help='actually copy (default: plan)')
    ap.add_argument('--plan', action='store_true', help='only show what would be copied')
    args = ap.parse_args()
    apply_ = args.apply and not args.plan
    if not args.lunar and not args.minecraft:
        ap.error('pass --lunar and/or --minecraft (or set LUNAR_OFFICIAL_DATA / LUNAR_MC)')
    if args.lunar:
        if not os.path.isdir(args.lunar):
            sys.exit(f'not a directory: {args.lunar}')
        for rel, dst, only_missing in LUNAR_ITEMS:
            copy_item(os.path.join(args.lunar, rel), os.path.join(LIBS, dst), only_missing, apply_)
    if args.minecraft:
        if not os.path.isdir(args.minecraft):
            sys.exit(f'not a directory: {args.minecraft}')
        for rel, dst, only_missing in VANILLA_ITEMS:
            copy_item(os.path.join(args.minecraft, rel), os.path.join(LIBS, dst), only_missing, apply_)
    if not apply_:
        print('[vendor] dry run; pass --apply to copy')
    return 0


if __name__ == '__main__':
    sys.exit(main())
