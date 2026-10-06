# Misc-lazy cluster — function providers, armor enums, render-mod nested types (14 rows)

Map: `tools/renames/classes-misclazy.tsv`. No source edited. All 14 new names
verified FREE in `src/main/java` and absent from every `tools/renames/*.tsv`.
Nested rows carry the owner file in the 5th column for the aware applier
(`tools/apply_class_renames_aware.py`), same convention as `classes-minimap.tsv`.

## Renames

### `inactive.mixin.highlight.mixin` — Molang function-provider family (6 rows)

`FunctionImpl` (neighbour) is NOT renamed: it is the generic user-defined
Molang `Function` (body `Evaluatable` + arg list, stored in
`FogIterator.field5 Map<FunctionDefinition, FunctionImpl>`). The numbered
siblings are each one builtin, named by their registration in
`Holograms2Iterator.method9` / `method7`:

| old | new | provides |
|-----|-----|----------|
| `FunctionImpl2` | `MolangCheckBiome` | `lunar.check_for_biome` (biome legacy-id match) |
| `FunctionImpl3` | `MolangCheckBiomeCategory` | `lunar.check_for_<category>` (per-`BiomeCategory`) |
| `FunctionImpl4` | `MolangCheckSnowyBiome` | `lunar.check_for_snowy_biome` |
| `FunctionImpl5` | `MolangLightLevel` | `lunar.get_light_level` |
| `FunctionImpl6` | `MolangSmoothQuery` | `<query>_smooth[_easing]` via `QueryTransition` |
| `FunctionImpl7` | `MolangAnimationState` | animation-state id equality vs `Inactive3` current/previous `AnimationStateConfig`; **unreferenced** (no `new` site in tree) |

### `framework.feature.armorstatus` — option enums (2 rows)

Neighbour `Gui2Extension` (`listMode` VERTICAL/HORIZONTAL) NOT renamed. Keys
from `Armorstatus` fields:

| old | new | option key |
|-----|-----|-----------|
| `Gui2Extension2` | `DurabilityPosition` | `durabilityPosition` (TOP/BOTTOM/RIGHT/LEFT) |
| `Gui2Extension3` | `HotbarPosition` | `hotbarPosition` (RIGHT/LEFT, default LEFT) |

### `mod.render` — nested types named from owner usage (6 rows)

| owner | old | new | usage |
|-------|-----|-----|-------|
| `Minimap` | `Type2` | `EntityMarkerType` | `entityMarkerType` (default HEAD) |
| `Minimap` | `Type3` | `PlayerMarkerType` | `playerMarkerType` (default TRIANGLE) |
| `SkyblockWishingCompass` | `Type2` | `WishingCompassVerdict` | `method14()` compass-use verdict |
| `SpiritLeapOverlay` | `Type2` | `SpiritLeapDefaultView` | `spiritLeapMenuDefaultView` (WHEEL/MAP) |
| `SkyblockStorageHoverPreview` | `Data2` | `StoragePreviewRule` | `field25` preview-rule list |
| `SkyblockVanillaItemModels` | `Data2` | `VanillaModelCacheKey` | `field11` substitution-cache key |

## Verify-only verdicts (NOT renamed, excluded from the .tsv)

* `client/molang/MolangMathAtan2.java` — CORRECT: `call` returns
  `Math.atan2(value0, value2)`; trailing 2 is the function name, matches the
  `MolangMath*` family convention.
* `com.moonsworth.lunar.genesis.FarmHashFingerprint64.java` — CORRECT: vendored
  Guava (`com.google.common.hash.FarmHashFingerprint64`: `Hashing.farmHashFingerprint64()`,
  64-bit fingerprint, `shiftMix`/`hashLength*` impl).
* `com.moonsworth.lunar.genesis.Utf8.java` — CORRECT: vendored Guava
  (`com.google.common.base.Utf8`: `encodedLength`/`encodedLengthGeneral`, `@Beta @GwtCompatible`).
* `net/minecraft/**` numeric suffixes — all CORRECT canonical MCP/vanilla 1.8.9
  names, none lazy:
  * `ModelSheep1` (wool layer model) / `ModelSheep2` (body model) — vanilla split.
  * `EntityAIWatchClosest2` extends `EntityAIWatchClosest` with mutex bits 3 — vanilla.
  * `MessageDeserializer2` / `MessageSerializer2` — vanilla Netty pipeline framing handlers.
  * `WorldGenGlowStone1` / `WorldGenTaiga1` (+ `...2` siblings) — vanilla generators.
  * `Corridor2-5`, `Crossing2/3` — inner classes of `StructureNetherBridgePieces` (vanilla).
  * `Field1/2`, `House1/2/3` — inner classes of `StructureVillagePieces` (vanilla).
  * `Stairs2` extends `Stairs` — inner class of `StructureStrongholdPieces` (vanilla).
  * `Vec3` / `Vec3i` — canonical vanilla classes, not suffixes at all.
  * Genuinely wrong: **none**.

## Caveats

* `FunctionImpl7`/`MolangAnimationState` has no instantiation site; its owner
  type `Inactive3` is not in the tree (only `Inactive3$Data2`/`$Data5`
  companions), so the current-vs-previous reading of `method45`/`method47`
  follows `Gui2Impl` usage, not a direct read of `Inactive3`.
* Rows kept to the three in-scope packages only; the `moves-misc.tsv` batch is untouched.
