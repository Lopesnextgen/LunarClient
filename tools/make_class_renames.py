#!/usr/bin/env python3
"""Find classes whose FQCN is also a package prefix and emit rename targets.

Java source cannot express a class and a package with the same fully qualified
name, so such classes are renamed by appending `_` before decompilation.

Usage: make_class_renames.py <jar> <out.tsv>
"""
import sys
import zipfile


def main():
    jar_path, out_path = sys.argv[1], sys.argv[2]
    with zipfile.ZipFile(jar_path) as z:
        names = {i.filename[:-6] for i in z.infolist() if i.filename.endswith('.class')}
    packages = set()
    for n in names:
        parts = n.split('/')
        for i in range(1, len(parts)):
            packages.add('/'.join(parts[:i]))
    clash = sorted(names & packages)
    renames = {}
    for c in clash:
        t = c + '_'
        while t in names or t in renames.values():
            t += '_'
        renames[c] = t
        # inner classes of a renamed class must be renamed with it, otherwise
        # the InnerClasses attributes and javac disagree about the outer name
        for n in names:
            if n.startswith(c + '$'):
                cand = t + n[len(c):]
                while cand in names or cand in renames.values():
                    cand += '_'
                renames[n] = cand
    with open(out_path, 'w', encoding='utf-8') as f:
        for old, new in renames.items():
            f.write(f'{old}\t{new}\n')
    print(f'class/package clashes: {len(clash)} -> {out_path}')


if __name__ == '__main__':
    main()
