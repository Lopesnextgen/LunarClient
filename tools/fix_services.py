#!/usr/bin/env python3
"""Patch META-INF/services files in the multiver module jars so they reference
the final class names.

Usage: fix_services.py [--apply]
"""
import os, sys, zipfile, shutil, re

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, os.path.join(PROJECT, 'tools'))
from importlib.machinery import SourceFileLoader
chain = SourceFileLoader('mrmS', os.path.join(PROJECT, 'tools', 'make_resource_map.py')).load_module().RenameChain()

LIBS = zipfile.ZipFile(os.path.join(PROJECT, 'libs', 'lunar-libraries.jar'))
FINAL = set(n[:-6] for n in LIBS.namelist() if n.endswith('.class'))

TARGETS = [
    os.path.join(PROJECT, 'libs', 'multiver-full', 'legacy-0.1.0-SNAPSHOT-all-nomappings.jar'),
    os.path.join(PROJECT, 'libs', 'multiver-full', 'genesis-0.1.0-SNAPSHOT-all.jar'),
    os.path.join(PROJECT, 'libs', 'multiver-full', 'forge-0.1.0-SNAPSHOT-all.jar'),
    os.path.join(PROJECT, 'libs', 'multiver-full', 'optifine-0.1.0-SNAPSHOT-all.jar'),
    os.path.join(PROJECT, 'libs', 'multiver-full', 'common-0.1.0-SNAPSHOT-all-nomappings.jar'),
]

def resolve_dotted(name):
    r = chain.resolve(name.replace('.', '/'))
    if r in FINAL:
        return r.replace('/', '.')
    return None

def main():
    apply = '--apply' in sys.argv
    for jar in TARGETS:
        if not os.path.isfile(jar):
            continue
        z = zipfile.ZipFile(jar)
        changes = {}
        for entry in z.namelist():
            if not entry.startswith('META-INF/services/'):
                continue
            base = entry[len('META-INF/services/'):]
            if not base or base == 'Provider':
                continue
            new_base = resolve_dotted(base) or base
            text = z.read(entry).decode('utf-8', 'replace')
            lines = text.splitlines()
            new_lines = []
            for line in lines:
                s = line.strip()
                if not s or s.startswith('#'):
                    new_lines.append(line)
                    continue
                r = resolve_dotted(s)
                new_lines.append(r or line)
            new_text = '\n'.join(new_lines)
            if not new_text.endswith('\n'):
                new_text += '\n'
            if new_base != base or new_text != text:
                changes[entry] = new_text
                changes['__rename__' + entry] = new_base
        if not changes:
            print(os.path.basename(jar), ': no changes')
            continue
        print(os.path.basename(jar), ':', len([k for k in changes if not k.startswith('__rename__')]), 'service files')
        for k in list(changes):
            if k.startswith('__rename__'):
                old = k[len('__rename__'):]
                print('   ', old[len('META-INF/services/'):], '->', changes[k])
        if apply:
            tmp = jar + '.tmp'
            with zipfile.ZipFile(jar) as zin, zipfile.ZipFile(tmp, 'w', zipfile.ZIP_DEFLATED) as out:
                for item in zin.infolist():
                    if item.filename in changes:
                        newname = changes['__rename__' + item.filename]
                        out.writestr(newname, changes[item.filename])
                    elif item.filename.startswith('META-INF/services/') and \
                            ('__rename__' + item.filename) in changes:
                        # entry already rewritten above
                        continue
                    else:
                        out.writestr(item, zin.read(item.filename))
            shutil.move(tmp, jar)

if __name__ == '__main__':
    main()
