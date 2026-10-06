#!/usr/bin/env python3
"""Produce a package rename map for the obfuscated Lunar package segments.

Reads the decompiled tree at ``tools/work/staging/decompiled/com/moonsworth/lunar``
and writes:

* ``tools/work/mappings/package-renames.tsv`` -- ``old/package/prefix<TAB>new/package/prefix``
* ``tools/work/mappings/package-renames.md``  -- evidence report

Design (heuristic, fast):

1.  Every directory under ``com/moonsworth/lunar`` that has ``*.java``
    descendants is a package.  A segment is *obfuscated* when it matches
    ``^[CHOIR]{6,}_?$`` (or is long and almost purely C/H/O/I/R).
2.  For each package the readable class names directly inside it (class files
    that were already renamed to ``*Mixin`` / module ids) are evidence.
    Packages with little direct evidence borrow readable names from their
    descendants.
3.  A local role is inferred from the class-name evidence
    (mixin/event/gui/render/config/network/util) and, failing that, from the
    dominant meaningful CamelCase word.  A package that contains exactly one
    renamed module class is named after that module (lower-case).
4.  The global name of a segment is the local name seen most often across all
    packages ending in that segment, with a curated override table for the
    big structural segments.
5.  Names are made unique per parent package and are applied recursively so
    that every full package prefix maps to a clean prefix.  Collisions with
    already-readable sibling packages (e.g. an existing ``util``) get a
    numeric suffix.
"""

from __future__ import annotations

import argparse
import collections
import os
import re
import sys

# --------------------------------------------------------------------------
# configuration
# --------------------------------------------------------------------------

DEFAULT_ROOT = "tools/work/staging/decompiled/com/moonsworth/lunar"
DEFAULT_TSV = "tools/work/mappings/package-renames.tsv"
DEFAULT_MD = "tools/work/mappings/package-renames.md"

OBF_PURE = re.compile(r"^[CHOIR]{6,}_?$")

# segments that look like obfuscation but are actually fine / structural
NOT_OBFUSCATED = set()

# curated names for key structural packages (full old package path -> name);
# these override the evidence-based inference for that one occurrence
PATH_OVERRIDES = {
    # client tree structural roots
    "client/CCROIHHHCOCHHOHORCIRHOCRROIOCI": "framework",
    "client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO": "feature",
    "client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO": "mod",
    "client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO": "impl",
    "client/RCIOICOHRIOIIRRRROCRHCIICRROHO": "feature",
    # raw com/moonsworth/lunar/<obf> roots (bridge, mixins, framework helpers)
    "CCROIHHHCOCHHOHORCIRHOCRROIOCI": "framework",
    "HORHROIOIOICIRHIOCOICHHHIHCIIO": "network",
    "HRICOROOOCCOCOROCRHHCRRIRCOICO": "bridge",
    "RHIRRICCRHHHIIHHIHHOHRCHIOORCC": "mixin",
    "IHRHHRIHICHOOICIRIOOHOICHIRHOI": "mixin2",
    "HHRROIIHRRICIIHIIHICRHHRHOHHOO": "annotations",
    "OHOOORICRHIIIIRHCICICOCHROICRC": "loader",
    "ORICHRORRORHORHOIHCRHOORCRRHOI": "files",
    "IIORCIOOIHRRRICOHIRCIHOOCCOHRO": "config",
    "ORCHOHHCOHCORRICRIHCHHRORHHCHH": "icon",
    "HIOROOCORHICHIRIHIRCCORCIIICCH": "bridgeimpl",
}

# words too generic to name a package after
STOP_WORDS = {
    "Skyblock", "Hypixel", "Lunar", "Client", "Simple", "Abstract", "Default",
    "Basic", "Custom", "Player", "Entity", "World", "Block", "Item", "Gui",
    "Hud", "Render", "Renderer", "Screen", "Overlay", "Display", "Event",
    "Handler", "Manager", "Util", "Utils", "Helper", "Config", "Settings",
    "Setting", "Option", "Options", "Mod", "Module", "Mixin", "Impl",
    "Layer", "Model", "Texture", "Shader", "Network", "Packet", "Server",
    "Child", "Children", "Base", "Generic", "Mixed", "Other", "Misc",
    "Minecraft", "Forge", "Feature", "Main", "Data", "Info", "Action",
}

ROLE_PATTERNS = [
    ("mixin", re.compile(r"Mixin|Patch$")),
    ("event", re.compile(r"Event$|Event[A-Z]|Listener|Callback")),
    ("gui", re.compile(r"Screen|Gui|Menu|Overlay|Widget|Button|Container|Tab|Hud|Display")),
    ("render", re.compile(r"Render|Shader|Texture|Model|Animation|Particle|Effect|Fog|Camera")),
    ("config", re.compile(r"Config|Setting|Option|Preference")),
    ("network", re.compile(r"Packet|Network|Socket|Connection|Websocket|Http|Api|Request|Response|Serializer|Account|Auth|Waypoint")),
    ("util", re.compile(r"Util|Helper|Manager|Cache|Loader|Registry|Factory|Provider|Service|Tracker|Controller|Generator|Builder|Adapter|Reader|Writer|Parser|Mapper|File|Stat")),
]

CAMEL = re.compile(r"[A-Z][a-z0-9]+|[A-Z]+(?![a-z])|[a-z0-9]+")


def is_obfuscated(segment: str) -> bool:
    """Return True when *segment* looks like Lunar obfuscation gibberish."""
    if segment in NOT_OBFUSCATED:
        return False
    core = segment.rstrip("_")
    if OBF_PURE.match(segment):
        return True
    if len(core) >= 7 and set(core) <= set("CHOIR"):
        return True
    # mixed-case gibberish with a very heavy C/H/O/I/R density
    if len(segment) >= 10 and not any(c.islower() for c in segment):
        ratio = sum(c in "CHOIR" for c in segment) / len(segment)
        if ratio >= 0.9:
            return True
    return False


def camel_words(name: str):
    return [w for w in CAMEL.findall(name) if len(w) > 1]


def valid_identifier(name: str) -> bool:
    if not name or not name.isidentifier() or not name.islower():
        return False
    return name not in {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch",
        "char", "class", "const", "continue", "default", "do", "double",
        "else", "enum", "extends", "final", "finally", "float", "for",
        "goto", "if", "implements", "import", "instanceof", "int",
        "interface", "long", "native", "new", "package", "private",
        "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws",
        "transient", "try", "void", "volatile", "while", "true", "false",
        "null",
    }


class PackageTree:
    def __init__(self, root: str):
        self.root = root
        self.children = collections.defaultdict(list)   # pkg -> child dir names
        self.classes = {}                               # pkg -> [class simple names]
        self.dirs = set()                               # all pkg paths (with java below)
        for dirpath, dirnames, filenames in os.walk(root):
            rel = os.path.relpath(dirpath, root).replace(os.sep, "/")
            if rel == ".":
                rel = ""
            self.dirs.add(rel)
            for d in dirnames:
                self.children[rel].append(d)
            javas = [f[:-5] for f in filenames if f.endswith(".java")]
            if javas:
                self.classes[rel] = javas

    def readable_evidence(self, pkg: str, max_depth: int = 1):
        """Readable class names directly in *pkg*, or borrowed from its direct children."""
        direct = [c for c in self.classes.get(pkg, []) if not is_obfuscated(c)]
        if direct:
            return direct, "direct"
        # borrow from immediate children only: structural parents have no classes
        names = []
        for ch in self.children.get(pkg, []):
            child = f"{pkg}/{ch}" if pkg else ch
            names += [c for c in self.classes.get(child, []) if not is_obfuscated(c)]
        if names:
            return names, "children"
        return [], "none"


# --------------------------------------------------------------------------
# local role inference
# --------------------------------------------------------------------------

class Evidence:
    def __init__(self):
        self.names = []
        self.direct = 0

    def add(self, names, direct):
        self.names += names
        self.direct += direct


def role_votes(names):
    votes = collections.Counter()
    for n in names:
        for role, rx in ROLE_PATTERNS:
            if rx.search(n):
                votes[role] += 1
                break
    return votes


def infer_local_name(tree: PackageTree, pkg: str, evidence_map: dict):
    """Infer the clean name for the last segment of *pkg* from local evidence.

    Returns ``(name, source)`` where source is ``direct`` (renamed classes in the
    package itself), ``children`` (renamed classes in direct children) or
    ``none``.
    """
    names, source = tree.readable_evidence(pkg)
    ev = Evidence()
    ev.add(names, len([c for c in tree.classes.get(pkg, []) if not is_obfuscated(c)]))
    ev.source = source
    evidence_map[pkg] = ev
    if not names:
        return None, source

    # single module class -> lower-case class name (e.g. HorseStats -> horsestats)
    if len(names) == 1:
        cand = names[0].lower()
        if cand.endswith("mixin"):
            cand = "mixin"
        if valid_identifier(cand) and len(cand) <= 20:
            return cand, source

    votes = role_votes(names)
    total = sum(votes.values())
    if total and votes:
        role, count = votes.most_common(1)[0]
        if count >= max(2, (len(names) + 1) // 2):
            return role, source

    words = collections.Counter()
    for n in names:
        for w in camel_words(n):
            if w in STOP_WORDS:
                continue
            words[w] += 1
    if words:
        best = sorted(words.items(), key=lambda kv: (-kv[1], -len(kv[0]), kv[0]))[0][0]
        return best.lower(), source

    # fall back to the most common non-stop word even if only stop words exist
    allwords = collections.Counter()
    for n in names:
        for w in camel_words(n):
            allwords[w.lower()] += 1
    if allwords:
        return allwords.most_common(1)[0][0], source
    return None, source


def build_segment_names(tree: PackageTree, path_overrides: dict, evidence_by_segment: dict,
                        debug: bool = False):
    """Compute local names and a per-segment fallback (the mode of local names)."""
    local = {}          # pkg -> local name
    pkg_evidence = {}
    for pkg in sorted(tree.dirs):
        if pkg and is_obfuscated(pkg.split("/")[-1]):
            name, source = infer_local_name(tree, pkg, pkg_evidence)
            local[pkg] = name

    seg_votes = collections.defaultdict(collections.Counter)
    seg_votes_by_root = collections.defaultdict(lambda: collections.defaultdict(collections.Counter))
    seg_examples = collections.defaultdict(list)
    seg_leaf_count = collections.Counter()
    for pkg, name in local.items():
        seg = pkg.split("/")[-1]
        root = pkg.split("/")[0] if "/" in pkg else ""
        seg_leaf_count[seg] += 1
        if name and pkg not in path_overrides:
            seg_votes[seg][name] += 1
            seg_votes_by_root[root][seg][name] += 1
        names = pkg_evidence[pkg].names
        if names:
            seg_examples[seg] += names[:6]

    seg_names = {}
    for seg, votes in seg_votes.items():
        if votes:
            seg_names[seg] = votes.most_common(1)[0][0]

    # root-aware fallback: prefer a name inferred elsewhere in the same top-level
    # subtree (client/, legacy/, forge/, ...) over the global mode
    seg_names_by_root = {}
    for root, segs in seg_votes_by_root.items():
        seg_names_by_root[root] = {seg: votes.most_common(1)[0][0] for seg, votes in segs.items() if votes}

    # evidence bundle for the report
    for seg in sorted(set(seg_votes) | set(seg_names) | {s.split('/')[-1] for s in path_overrides}):
        evidence_by_segment[seg] = {
            "name": seg_names.get(seg),
            "leaf_pkgs": seg_leaf_count.get(seg, 0),
            "votes": dict(seg_votes.get(seg, {})),
            "examples": sorted(set(seg_examples.get(seg, [])))[:12],
        }
    if debug:
        for seg in sorted(seg_leaf_count, key=lambda s: -seg_leaf_count[s]):
            base = seg_names.get(seg)
            if base is None:
                continue
            top = ", ".join(f"{k}:{v}" for k, v in seg_votes[seg].most_common(6))
            print(f"{seg}\t{base}\tleaf={seg_leaf_count[seg]}\t{top}")
    return seg_names, seg_names_by_root, local, pkg_evidence


# --------------------------------------------------------------------------
# collision-safe recursive renaming
# --------------------------------------------------------------------------

def resolve_tree(tree: PackageTree, seg_names: dict, seg_names_by_root: dict,
                 local_names: dict, evidence: dict):
    """Return old_pkg -> new_pkg for every package (recursive, collision-safe)."""
    mapping = {"": ""}
    seg_resolved = {}     # (old pkg, segment) -> final name

    def resolve(pkg: str):
        children = tree.children.get(pkg, [])
        if not children:
            return
        used = {c for c in children if not is_obfuscated(c)}
        candidates = []
        for c in children:
            if not is_obfuscated(c):
                continue
            child_pkg = f"{pkg}/{c}" if pkg else c
            if not _has_java(tree, child_pkg):
                continue
            root = child_pkg.split("/")[0] if "/" in child_pkg else ""
            fallback = (seg_names_by_root.get(root, {}).get(c)
                        or seg_names.get(c)
                        or _fallback_name(c))
            base = (PATH_OVERRIDES.get(child_pkg)
                    or (local_names.get(child_pkg) if local_names else None)
                    or fallback)
            # strength: readable classes in the subtree (used to order collisions)
            strength = 0
            frontier = [child_pkg]
            seen = set()
            for _ in range(4):
                nxt = []
                for p in frontier:
                    for d in tree.children.get(p, []):
                        cp = f"{p}/{d}" if p else d
                        if cp in seen:
                            continue
                        seen.add(cp)
                        nxt.append(cp)
                        strength += len([x for x in tree.classes.get(cp, [])
                                         if not is_obfuscated(x)])
                frontier = nxt
            candidates.append((child_pkg, c, base, strength))
        candidates.sort(key=lambda t: (-t[3], t[2], t[1]))
        for child_pkg, c, base, _ in candidates:
            final = base
            k = 2
            while final in used or not valid_identifier(final):
                final = f"{base}{k}"
                k += 1
            used.add(final)
            seg_resolved[(pkg, c)] = final
        for c in children:
            child_pkg = f"{pkg}/{c}" if pkg else c
            if not _has_java(tree, child_pkg):
                continue
            name = c if not is_obfuscated(c) else seg_resolved.get((pkg, c), c)
            mapping[child_pkg] = f"{mapping[pkg]}/{name}" if mapping[pkg] else name
            resolve(child_pkg)

    resolve("")
    return mapping


def _has_java(tree: PackageTree, pkg: str) -> bool:
    if pkg in tree.classes and tree.classes[pkg]:
        return True
    for ch in tree.children.get(pkg, []):
        if _has_java(tree, f"{pkg}/{ch}" if pkg else ch):
            return True
    return False


def _fallback_name(seg: str) -> str:
    import hashlib
    digest = hashlib.sha1(seg.encode("utf-8")).hexdigest()
    return "pkg" + str(int(digest[:4], 16) % 100).zfill(2)


# --------------------------------------------------------------------------
# main
# --------------------------------------------------------------------------

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", default=DEFAULT_ROOT)
    ap.add_argument("--tsv", default=DEFAULT_TSV)
    ap.add_argument("--md", default=DEFAULT_MD)
    ap.add_argument("--debug", action="store_true")
    args = ap.parse_args()

    tree = PackageTree(args.root)
    evidence_by_segment = {}
    seg_names, seg_names_by_root, local, pkg_evidence = build_segment_names(
        tree, PATH_OVERRIDES, evidence_by_segment, args.debug)
    mapping = resolve_tree(tree, seg_names, seg_names_by_root, local, pkg_evidence)

    fq_prefix = "com/moonsworth/lunar"
    rows = []
    for old, new in mapping.items():
        if not old or old == new:
            continue
        rows.append((f"{fq_prefix}/{old}", f"{fq_prefix}/{new}"))
    rows.sort()

    os.makedirs(os.path.dirname(args.tsv) or ".", exist_ok=True)
    with open(args.tsv, "w", encoding="utf-8") as f:
        for old, new in rows:
            f.write(f"{old}\t{new}\n")
    print(f"wrote {len(rows)} mappings to {args.tsv}")

    write_report(args.md, rows, tree, seg_names, evidence_by_segment, pkg_evidence,
                 PATH_OVERRIDES, mapping)


def write_report(path, rows, tree, seg_names, evidence_by_segment, pkg_evidence,
                 path_overrides, mapping):
    lines = []
    lines.append("# Package rename map")
    lines.append("")
    lines.append(f"Generated from the decompiled tree `{DEFAULT_ROOT}` by `tools/make_package_renames.py`.")
    lines.append(f"`package-renames.tsv` contains **{len(rows)}** full package-prefix mappings "
                 f"(old prefix -> new prefix), sorted and unique; this report explains how the")
    lines.append("names were chosen.")
    lines.append("")
    lines.append("## Method")
    lines.append("")
    lines.append("The obfuscator reused a small pool of segment names for many unrelated packages, so a")
    lines.append("single global name per segment would be wrong in most subtrees. Each obfuscated package")
    lines.append("is therefore renamed from the evidence found in that package (consistently inside its")
    lines.append("parent package):")
    lines.append("")
    lines.append("* mostly `*Mixin` classes -> `mixin`; mostly event classes -> `event`;")
    lines.append("  screens/GUI -> `gui`; renderers/textures -> `render`; config/settings -> `config`;")
    lines.append("  packets/network -> `network`; utilities/managers -> `util`")
    lines.append("* a package holding a single renamed module class -> the module name in lower case")
    lines.append("  (e.g. `HorseStats` -> `horsestats`, `Freelook` -> `freelook`)")
    lines.append("* otherwise the dominant meaningful CamelCase word of the readable class names")
    lines.append("  (brand words such as `Skyblock`/`Hypixel` are ignored)")
    lines.append("* packages without readable classes borrow the class names of their direct children;")
    lines.append("  if that also fails they fall back to the most common name for that segment in the")
    lines.append("  same top-level subtree (or elsewhere in the tree), and finally to a stable")
    lines.append("  `pkgNN` placeholder")
    lines.append("* two different obfuscated siblings may never share a name: the weaker one gets a")
    lines.append("  numeric suffix (`mixin`, `mixin2`, ...), and existing readable sibling names")
    lines.append("  (`util`, `gui`, ...) are never taken over")
    lines.append("")
    lines.append("## Curated structural renames")
    lines.append("")
    lines.append("| old package | new segment |")
    lines.append("|---|---|")
    for old, name in sorted(path_overrides.items()):
        lines.append(f"| `com/moonsworth/lunar/{old}` | `{name}` |")
    lines.append("")
    lines.append("## Segment names and evidence")
    lines.append("")
    lines.append("`fallback` is the name used when a package occurrence has no local readable classes;")
    lines.append("`votes` counts the local names inferred for occurrences ending in that segment.")
    lines.append("")
    lines.append("| obfuscated segment | fallback | packages ending in seg | local votes | examples |")
    lines.append("|---|---|---:|---|---|")
    for seg in sorted(seg_names, key=lambda s: (-evidence_by_segment.get(s, {}).get("leaf_pkgs", 0), s)):
        ev = evidence_by_segment.get(seg, {})
        top = ", ".join(f"`{k}`:{v}" for k, v in sorted(ev.get("votes", {}).items(), key=lambda kv: -kv[1])[:4])
        ex = ", ".join(ev.get("examples", [])[:4])
        lines.append(f"| `{seg}` | `{seg_names[seg]}` | {ev.get('leaf_pkgs', 0)} | {top} | {ex} |")
    lines.append("")
    lines.append("## Example resolved mappings")
    lines.append("")
    lines.append("| old package prefix | new package prefix | evidence (class count / examples) |")
    lines.append("|---|---|---|")
    shown = 0
    for seg in sorted(seg_names, key=lambda s: (-evidence_by_segment.get(s, {}).get("leaf_pkgs", 0), s)):
        examples = []
        for pkg, ev in pkg_evidence.items():
            if pkg not in mapping or pkg.split("/")[-1] != seg or not ev.names:
                continue
            examples.append((len(ev.names), pkg, ev))
        examples.sort(key=lambda t: -t[0])
        for count, pkg, ev in examples[:1]:
            ex = ", ".join(ev.names[:4])
            lines.append(f"| `com/moonsworth/lunar/{pkg}` | `{mapping[pkg]}` | {count} classes: {ex} |")
            shown += 1
    lines.append("")
    lines.append(f"({shown} examples; the full list of {len(rows)} mappings is in `package-renames.tsv`.)")
    lines.append("")
    lines.append("## Notes")
    lines.append("")
    lines.append("* The decompiled tree mixes several copies of the same client classes (flat")
    lines.append("  `client/<module>`, nested `client/framework/feature/...` and the raw `lunar/<obf>`")
    lines.append("  tree); every copy is renamed from its own local evidence.")
    lines.append("* Some raw `com/moonsworth/lunar/<obf>` packages contain no renamed classes at all, so")
    lines.append("  they fall back to the name most commonly inferred for that segment elsewhere.")
    lines.append("")
    with open(path, "w", encoding="utf-8") as f:
        f.write("\n".join(lines))
    print(f"wrote report to {path}")


if __name__ == "__main__":
    main()
