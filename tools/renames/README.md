# Renamer workflow (subagent handbook)

This directory holds the rename maps produced during the source-tree cleanup.
The tree was decompiled from a mixed Lunar build; an earlier pass invented
placeholder names (`Mixin12`, `Data3`, `Fog_2`, `Highlight3Iterator`, ...).
The goal is accurate, reference-grade naming: **better named than Badlion**.

## Ground rules

1. **One cluster per subagent.** Clusters are package trees listed in
   `tools/renames/inventory.tsv` (columns: package / class / file).
2. **Subagents produce maps, not code edits.** The main agent applies maps with
   `tools/apply_class_renames.py` so references stay consistent tree-wide.
3. **Evidence first.** Every row must be justified by the class source
   (fields, methods, strings, annotations), or by a reference client / the
   real Lunar names table. Put a short reason in the `evidence` column.
4. **Unique simple names.** A new simple name must be unique across the whole
   tree (or at least in the package). Map rows with colliding old names are
   skipped by the applier unless `--allow-collisions` is used.
5. **No lazy patterns.** No trailing digits, no `MixinN` prefixes, no
   `Data/Framework/Manager/Helper` + number. Mixins are named
   `<Target><Purpose>Mixin` — target first, purpose optional when the target
   has a single mixin (for example `EntityRendererFogMixin`,
   `GuiIngameKeystrokesMixin`, `ChunkLightingMixin`). This matches the
   Badlion/MCP convention of naming by the patched class.
6. **Real names win.** If `tools/mappings-snapshot/lunar-client-names.tsv`
   contains the real Lunar/Apollo name for the feature (for example the
   mod/metadata names like `ModOverlayMod`, `ModFog`), use it.

## Map format

```
package<TAB>oldSimpleName<TAB>newSimpleName<TAB>evidence
```

Written to `tools/renames/classes-<cluster>.tsv`. The applier:

* replaces word-boundary occurrences in `src/main/java` and
  `src/main/resources` (mixin configs reference classes by simple name)
* renames the declaring `.java` file (and `Outer$Inner.java` companions)

Run `tools/apply_class_renames.py --map <file>` for a dry run, add `--apply`
to write. Compile/commit after every applied map.

## Member rename maps (phase 2)

Format: `file<TAB>old<TAB>new<TAB>kind<TAB>evidence` where kind is
`method|field|local|param`.

* **Locals/params** are usually safe to rename inside the declaring file only.
* **Methods/fields** need their references updated wherever the owning type is
  used; the applier for members runs per class cluster and is verified with
  `tools/error_diff.py` (new failing files = bad rename).
* `tools/member_inventory.py` sizes the work: ~257k placeholder member tokens
  across 5,458 files (`methodN` 75k, `fieldN` 66k, `numberN` 41k, ...).
* Automate the mechanical local renames first (`tools/clean_locals.py`),
  then use subagents only for semantic method/field names on the clusters with
  real user value (framework, mods, gui).

## Reference material

| What | Where |
|---|---|
| source tree | `src/main/java` |
| runtime jars (obfuscated) | `~/.var/app/com.lunarclient.LunarClient/.lunarclient/offline/multiver/{lunar.jar,legacy-…,forge-…,genesis-…}` |
| decompiler | `java -jar tools/bin/cfr-0.153.jar <class-or-jar> --outputdir /tmp/x --jarfilter 'com/moonsworth/…'` |
| real Lunar names | `tools/mappings-snapshot/lunar-client-names.tsv` |
| obf → named maps | `tools/mappings-snapshot/{classes,members,class-renames,member-renames}.tsv` |
| Badlion reference | `~/Projects/Badlion 2.0.0-v-beta` |
| 509 reference clients | `~/Downloads/ABDM/Compressed/mc-client-sources-main/sources/` |
| mixin configs | `src/main/resources/mixins.*.json` |
