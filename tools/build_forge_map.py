#!/usr/bin/env python3
"""Build a Forge member map (readable -> runtime) for the shipped jars.

Forge jars here have readable class names but short obfuscated member names
('a', 'b', ...).  Forge's SRG database maps Mojang names to SRG names; the
shipped jar preserves the same member order/descriptors.  We pair by
descriptor within each class using the SRG member tables where available.

Because the exact SRG pairing info is incomplete in this workspace, we fall
back to pairing readable members found in the *MCP mixin sources* with runtime
members by descriptor when both sides are unambiguous.

Output: JSON {"classes": {cls: {readableMember: runtimeMember}}}
"""
import sys, json, zipfile, struct, collections, re, os

PROJECT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

def parse(data):
    try:
        cp = struct.unpack('>H', data[8:10])[0]
    except Exception:
        return [], []
    off, i = 10, 1
    utf = {}
    sizes = {3:4,4:4,5:8,6:8,7:2,8:2,9:4,10:4,11:4,12:4,15:3,16:2,17:4,18:4,19:2,20:2}
    while i < cp and off < len(data):
        tag = data[off]; off += 1
        size = sizes.get(tag)
        if size is None:
            return [], []
        if tag == 1:
            ln = struct.unpack('>H', data[off:off+2])[0]
            utf[i] = data[off+2:off+2+ln].decode('utf-8', 'replace')
            off += 2 + ln
        else:
            off += size
        i += 1
        if tag in (5, 6):
            i += 1
    try:
        off += 6
        ic = struct.unpack('>H', data[off:off+2])[0]; off += 2 + 2*ic
        fields = []
        fc = struct.unpack('>H', data[off:off+2])[0]; off += 2
        for _ in range(fc):
            fa, nm, ds = struct.unpack('>HHH', data[off:off+6]); off += 6
            ac = struct.unpack('>H', data[off:off+2])[0]; off += 2
            for _ in range(ac):
                al = struct.unpack('>I', data[off:off+4])[0]; off += 4 + al
            fields.append((utf.get(nm, ''), utf.get(ds, ''), fa))
        methods = []
        mc = struct.unpack('>H', data[off:off+2])[0]; off += 2
        for _ in range(mc):
            ma, nm, ds = struct.unpack('>HHH', data[off:off+6]); off += 6
            ac = struct.unpack('>H', data[off:off+2])[0]; off += 2
            for _ in range(ac):
                al = struct.unpack('>I', data[off:off+4])[0]; off += 4 + al
            methods.append((utf.get(nm, ''), utf.get(ds, ''), ma))
    except Exception:
        return [], []
    return fields, methods

# readable method names actually referenced in the mixin annotations
readable = collections.defaultdict(set)
zlib = zipfile.ZipFile(PROJECT + '/libs/lunar-libraries.jar')
for n in zlib.namelist():
    if not n.endswith('.class') or not n.startswith('com/moonsworth'):
        continue
    d = zlib.read(n)
    for m in re.finditer(rb'L(net/minecraftforge/[A-Za-z0-9_$/]+);([A-Za-z_][A-Za-z0-9_]*)\s*\(', d):
        readable[m.group(1).decode()].add(m.group(2).decode())
print('forge classes referenced by mixins:', len(readable), file=sys.stderr)

# runtime members
out = {}
for jar in ['libs/multiver-full/Forge_v1_8.jar',
            'libs/multiver-full/OptiFine_v1_8.jar']:
    z = zipfile.ZipFile(PROJECT + '/' + jar)
    for n in z.namelist():
        if not n.endswith('.class'):
            continue
        cls = n[:-6]
        if cls not in readable:
            continue
        try:
            fields, methods = parse(z.read(n))
        except Exception:
            continue
        want = readable[cls]
        # descriptor-index runtime members
        by_desc = collections.defaultdict(list)
        for name, desc, acc in methods:
            by_desc[desc].append(name)
        entry = {}
        for name in want:
            # a readable name that exists verbatim is already runtime
            if any(m[0] == name for m in methods):
                continue
            # find descriptor from the MCP class when available
            mcp = PROJECT + '/target/classes/' + cls + '.class'
            pair = None
            if os.path.exists(mcp):
                mcp_fields, mcp_methods = parse(open(mcp, 'rb').read())
                for mn, md, ma in mcp_methods:
                    if mn == name and md in by_desc and len(by_desc[md]) == 1:
                        pair = by_desc[md][0]
                        break
                if pair is None:
                    for fn, fd, fa in mcp_fields:
                        if fn == name and fd in by_desc and len(by_desc[fd]) == 1:
                            pair = by_desc[fd][0]
                            break
            if pair:
                entry[name] = pair
        if entry:
            out[cls] = entry
print('classes with mappings:', len(out), file=sys.stderr)
json.dump({'classes': out}, open(sys.argv[1], 'w'))
