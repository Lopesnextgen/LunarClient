#!/usr/bin/env python3
"""Export combined mapping tables from .kin files + MCP 1.8.9 CSVs.

Outputs (tools/work/mappings/):
  classes.tsv      obfClass <TAB> namedClass        (non-identity only)
  members.tsv      owner <TAB> F|M <TAB> name <TAB> desc <TAB> target
  srg-methods.tsv  srg <TAB> mcp
  srg-fields.tsv   srg <TAB> mcp
"""
import os
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)
from kin2tsrg import parse  # noqa: E402

MAPPINGS = os.path.join(HERE, 'work', 'mappings')
MCP = os.environ.get('MCP_MAPPINGS_DIR',
                     os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                                  'tools', 'mappings-snapshot'))

KIN_FILES = [
    'lunar/lunar_named_b5_1.8.9.kin',
    'v1_8_inflight_vanilla.kin',
    'v1_8_inflight_optifine.kin',
    'v1_8_inflight_forge.kin',
    'v1_8_inflight_optiforge.kin',
]


def main():
    classes = {}
    members = {}

    def collect(cls_list, prefix=''):
        for c in cls_list:
            obf = prefix + c['obf']
            named = prefix + c['deobf']
            if obf != named:
                classes.setdefault(obf, named)
            for (fname, desc, fdeobf) in c['fields']:
                members.setdefault((named, 'F', fname, desc), fdeobf)
            for (mname, desc, mdeobf) in c['methods']:
                members.setdefault((named, 'M', mname, desc), mdeobf)
            collect(c['inner'], obf + '$')

    for kin in KIN_FILES:
        path = os.path.join(MAPPINGS, kin)
        collect(parse(path))
        print(f'parsed {kin}')

    with open(os.path.join(MAPPINGS, 'classes.tsv'), 'w', encoding='utf-8') as f:
        for obf in sorted(classes):
            f.write(f'{obf}\t{classes[obf]}\n')

    with open(os.path.join(MAPPINGS, 'members.tsv'), 'w', encoding='utf-8') as f:
        for (owner, kind, name, desc), target in sorted(members.items()):
            f.write(f'{owner}\t{kind}\t{name}\t{desc}\t{target}\n')

    # MCP SRG -> MCP name tables
    def load_csv(name, out):
        src = os.path.join(MCP, name)
        n = 0
        with open(src, encoding='utf-8') as fi, open(os.path.join(MAPPINGS, out), 'w', encoding='utf-8') as fo:
            header = fi.readline()
            for line in fi:
                parts = line.rstrip('\n').split(',')
                if len(parts) >= 2 and parts[0] and parts[1]:
                    fo.write(f'{parts[0]}\t{parts[1]}\n')
                    n += 1
        print(f'{out}: {n} entries')

    load_csv('methods.csv', 'srg-methods.tsv')
    load_csv('fields.csv', 'srg-fields.tsv')
    print(f'classes.tsv: {len(classes)} entries')
    print(f'members.tsv: {len(members)} entries')


if __name__ == '__main__':
    main()
