# Mixin class restructure map

`mixin-renames.tsv` moves every `@Mixin` class in the Lunar client into one flat
`mixin` package per module, removing the current `mixin2`/`mixin3`/`*/mixin/mixin`
nests. Format: two tab-separated columns, `oldFQCN<TAB>newFQCN` (internal names),
sorted by old name, unique, no header. 690 rows.

## Method

* Discovery: an ASM scan of `tools/work/staging/lunar-all-final.jar` (the bytecode
  that will actually be remapped) selected every class with a class-level
  `Lorg/spongepowered/asm/mixin/Mixin;` annotation or a simple name ending in
  `Mixin`. The decompiled tree `tools/work/staging/decompiled` was scanned too as
  a cross-check: it contains 472 of the 668 classes, and all of its annotated
  classes are in the bytecode set. The remaining **196** mixins are not in the
  current tree (it is the merged `src/main` + `src/reference` subset) but do exist
  in the jar, so they are included; otherwise the re-decompiled tree would keep
  their nests. Per module: legacy 165, v1_8 17, client 10, mixin2 4.
* Module root (from the package, per task rules):
  `client|forge|legacy|genesis|ichor|replaymod|v1_7|v1_8|v1_12|optifine|common`
  are module roots (`<root>/mixin`); every other first-level component under
  `com/moonsworth/lunar/` (or `com/moonsworth/`) is kept as its module root.
  `legacy/optifine/**` and `v1_7/optifine/**` therefore fold under their
  first-level module (`legacy/mixin`, `v1_7/mixin`).
* Core module: the top-level plugin packages `com/moonsworth/lunar/mixin`,
  `mixin2`, `mixin3` are not modules of their own, so their mixins go to
  `com/moonsworth/lunar/mixin/<Simple>` (17 classes). This is the reading that
  actually removes the `mixin2`/`mixin/mixin` nests; the literal alternative
  (treating `mixin2` as a module) would leave `lunar/mixin2/mixin` in place.
* Collisions: candidates are processed in sorted-old-FQCN order; a target that is
  already taken (by another renamed class, by an inner class derived from one, or
  by a class that stays) gets `2`, `3`, `4`, ... appended. Existing jar names are
  reserved so a moved class can never land on a class that does not move.
  162 of 666 outers were disambiguated (133× `2`, 27× `3`, 2× `4`).
* Inner classes: every `$` class of a renamed outer in the jar is emitted
  explicitly (`A/B/C$D -> A/B/mixin/C$D`); 24 rows over 22 outers.
* No-ops: 2 classes are already directly in their target `mixin` package and are
  omitted from the TSV (rule 3):
  `com/moonsworth/lunar/legacy/mixin/HHRROIIHRRICIIHIIHICRHHRHOHHOO_`,
  `com/moonsworth/lunar/legacy/mixin/HORHROIOIOICIRHIOCOICHHHIHCIIO_`.
* 33 discovered classes carry `@Mixin` but do not end in `Mixin`
  (e.g. `EventBusMixin2`, the obfuscated `HORH..._` names); they are included.

## Counts

| item | count |
|---|---:|
| `@Mixin`/`*Mixin` classes discovered in bytecode | 668 |
| ... found in the decompiled tree | 472 |
| ... recovered from bytecode only (absent from tree) | 196 |
| no-op classes (omitted) | 2 |
| renamed outer classes | 666 |
| inner-class rows | 24 |
| **TSV rows** | **690** |

| target package | outers | suffix-disambiguated |
|---|---:|---:|
| `com/moonsworth/lunar/mixin` (core) | 17 | 3 |
| `com/moonsworth/lunar/client/mixin` | 24 | 5 |
| `com/moonsworth/lunar/forge/mixin` | 32 | 12 |
| `com/moonsworth/lunar/legacy/mixin` | 464 | 101 |
| `com/moonsworth/lunar/replaymod/mixin` | 82 | 41 |
| `com/moonsworth/lunar/v1_12/mixin` | 12 | 0 |
| `com/moonsworth/lunar/v1_7/mixin` | 13 | 0 |
| `com/moonsworth/lunar/v1_8/mixin` | 22 | 0 |

## Examples

| before | after |
|---|---|
| `com/moonsworth/lunar/mixin/mixin/mixin3/NetworkManagerMixin` | `com/moonsworth/lunar/mixin/NetworkManagerMixin` |
| `com/moonsworth/lunar/mixin2/mixin/mixin/WorldMixin` | `com/moonsworth/lunar/mixin/WorldMixin2` |
| `com/moonsworth/lunar/client/menublur/mixin/mixin/mixin/mixin/GuiButtonRecipeMixin` | `com/moonsworth/lunar/client/mixin/GuiButtonRecipeMixin` |
| `com/moonsworth/lunar/client/minimap/mixin/mixin/mixin/EntityRendererMixin` | `com/moonsworth/lunar/client/mixin/EntityRendererMixin2` |
| `com/moonsworth/lunar/forge/mixin/mixin/ModelLoaderMixin` | `com/moonsworth/lunar/forge/mixin/ModelLoaderMixin` |
| `com/moonsworth/lunar/forge/mixin2/mixin/ModelLoaderMixin` | `com/moonsworth/lunar/forge/mixin/ModelLoaderMixin2` |
| `com/moonsworth/lunar/forge/mixin2/mixin/EventBusMixin2` | `com/moonsworth/lunar/forge/mixin/EventBusMixin22` |
| `com/moonsworth/lunar/legacy/mixin/mixin/mixin4/EntityPlayerSPMixin` | `com/moonsworth/lunar/legacy/mixin/EntityPlayerSPMixin` |
| `com/moonsworth/lunar/legacy/mixin2/mixin/mixin/mixin19/StatFileWriterMixin$2` | `com/moonsworth/lunar/legacy/mixin/StatFileWriterMixin$2` |
| `com/moonsworth/lunar/legacy/mixin2/mixin/mixin/mixin/EntityRendererMixin$1` | `com/moonsworth/lunar/legacy/mixin/EntityRendererMixin2$1` |
| `com/moonsworth/lunar/legacy/mixin2/mixin/mixin/mixin5/WindowsDisplayMixin$HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/legacy/mixin/WindowsDisplayMixin$HORHROIOIOICIRHIOCOICHHHIHCIIO` |
| `com/moonsworth/lunar/legacy/optifine/mixin/MinecraftMixin` | `com/moonsworth/lunar/legacy/mixin/MinecraftMixin4` |
| `com/moonsworth/lunar/replaymod/forge/v1_12/mixin3/AbstractGuiScreenMixin` | `com/moonsworth/lunar/replaymod/mixin/AbstractGuiScreenMixin` |
| `com/moonsworth/lunar/replaymod/forge/v1_8/mixin3/AbstractGuiScreenMixin` | `com/moonsworth/lunar/replaymod/mixin/AbstractGuiScreenMixin2` |
| `com/moonsworth/lunar/v1_12/forge/mixin/C00HandshakeMixin` | `com/moonsworth/lunar/v1_12/mixin/C00HandshakeMixin` |
| `com/moonsworth/lunar/v1_7/optifine/mixin/MinecraftMixin` | `com/moonsworth/lunar/v1_7/mixin/MinecraftMixin` |
| `com/moonsworth/lunar/v1_8/forge/mixin/C00HandshakeMixin` | `com/moonsworth/lunar/v1_8/mixin/C00HandshakeMixin` |

## Validation

Both the generator and an independent validator pass:

* 690 rows, 690 distinct old names, 690 distinct target names.
* every old name exists in `lunar-all-final.jar`.
* no target equals a class that stays where it is (all jar names are reserved
  except those vacated by the rename itself).
* final class set is bijective: 32,174 classes in, 32,174 unique names out.
* every target is inside `<moduleRoot>/mixin/`.
* every `$` row follows its renamed outer (`Outer$D -> NewOuter$D`) and every
  inner row's outer has its own row.
* file is byte-order sorted by old name, two columns, LF, no header.

## Notes for the merge step

`tools/apply_restructure.sh` loads `mixin-renames.tsv` first, so if
`module-renames.tsv` / `remaining-renames.tsv` map other classes into the same
`mixin` packages, the merge step will suffix those later targets (it keeps the
first occurrence, i.e. this map). Verified against the sibling
`module-renames.tsv`: it shares neither old names nor target names with this map
(0 overlap at the time of writing). Keep this file's targets collision-free if
it is regenerated.
