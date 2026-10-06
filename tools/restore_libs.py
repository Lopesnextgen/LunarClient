#!/usr/bin/env python3
"""Restore third-party classes that the old pipeline dropped from
libs/lunar-libraries.jar, taking them from the original Lunar jars.

Also restores the corresponding META-INF/services entries and other resources
that are absent.

Usage: restore_libs.py [--apply]
"""
import os, sys, zipfile, shutil

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LIBS = os.path.join(PROJECT, 'libs', 'lunar-libraries.jar')
MV = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                  'libs', 'multiver-full')
SOURCES = [
    'lunar.jar',
    'genesis-0.1.0-SNAPSHOT-all.jar',
    'forge-0.1.0-SNAPSHOT-all.jar',
    'legacy-0.1.0-SNAPSHOT-all-nomappings.jar',
    'common-0.1.0-SNAPSHOT-all-nomappings.jar',
    'optifine-0.1.0-SNAPSHOT-all.jar',
    'lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar',
    'lunar-lang.jar',
    'Forge_v1_8.jar',
    'OptiFine_v1_8.jar',
    'ReplayMod-v1_8-2.6.24.jar',
]

def main():
    apply = '--apply' in sys.argv
    libs = zipfile.ZipFile(LIBS)
    have = set(libs.namelist())
    additions = {}
    for name in SOURCES:
        p = os.path.join(MV, name)
        if not os.path.isfile(p):
            continue
        try:
            z = zipfile.ZipFile(p)
        except Exception:
            continue
        n = 0
        for entry in z.namelist():
            if entry in have or entry in additions:
                continue
            # never let the old com/moonsworth bytecode shadow ours
            if entry.startswith('com/moonsworth/'):
                continue
            additions[entry] = z.read(entry)
            n += 1
        print(f'{name}: +{n}')
    print('total additions:', len(additions))
    if not apply:
        return
    tmp = LIBS + '.tmp'
    with zipfile.ZipFile(tmp, 'w', zipfile.ZIP_DEFLATED) as out:
        for item in libs.infolist():
            out.writestr(item, libs.read(item.filename))
        for name, data in additions.items():
            info = zipfile.ZipInfo(name)
            info.compress_type = zipfile.ZIP_DEFLATED
            out.writestr(info, data)
    libs.close()
    shutil.move(tmp, LIBS)
    print('restored', len(additions), 'entries into', LIBS)

if __name__ == '__main__':
    main()
