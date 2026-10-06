# Cluster 16 — `client.util.gui` + `client.util.mixin` + neighbours (44 rows)

Source revision: `tools/renames/cluster-16.txt` md5 `79fba5e4c90c0e67b9688c23ef3f6120`
(44 rows: 41 renames + 3 already-correct no-ops).
Map: `tools/renames/classes-utilgui.tsv` md5 `32ce423cacaa39760fbfd222366e0ad3`
(41 rows, 2 of them nested).

## What this cluster actually is

The cluster name is only the first two packages; the 44 rows are a cross-tree
slice of **small utility/model classes the decompiler gave lazy `…2` names**.
Despite the "gui" package name, the two headline packages are collection
helpers, not GUI code:

* **`client.util.gui`** is a tiny copy-on-write list library:
  `ListExtension` (base interface, out of cluster), `ListExtension2`
  (immutable view interface), `ListExtension22` (its implementation) and
  `ListExtension3` (the actual `CopyOnWriteIfNeededArrayList`, named in its own
  exception string). `client.util.gui.Gui` is a read-only collection-method
  interface (out of cluster).
* **`client.util.mixin`** is a fluent **list builder** (`MixinHelper` base,
  `MixinHelper2` API, `MixinHelper22` implementation + lazy `Data2`), unrelated
  to the many real mixin-helper classes that share the `MixinHelper` placeholder
  name elsewhere in the tree.

The rest are neighbours from every corner of the tree: the Mojang launcher
JSON models (`files.mixin`), the potion bridge (`bridge.fog`), the localisation
system (`client.calculator`), cosmetics (`client.feature`, `fov`), the
Hypixel SkyBlock HUD helpers (`crosshair`, `markers`, `lotusfish`,
`holograms.highlight`) and a few legacy wrappers.

Readable ground truth used: `toString()` methods (`AssetIndex`,
`VersionInfo`, `VersionManifest`, `CosmeticSettings`), exception strings
(`CopyOnWriteIfNeededArrayList`, `ExternalLink`), `@SerializedName`, option ids
and the runtime decompiles/quarantine copies
(`tools/work/quarantine/src/.../fov/mixin/rewindhandlers/Gui2Iterator.java`,
`.../fog/gui/FogHandler2.java`, `.../framework/Framework8.java`).

## Renames (41 rows)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | `client.util.gui` | `ListExtension2` | `ImmutableListView` | immutable List view; every mutator throws `"List is immutable!"`; `method1(List)` wraps in `ListExtension22` |
| 2 | `client.util.gui` | `ListExtension22` | `ImmutableListViewImpl` | only impl of `ListExtension2`; delegates reads, re-wraps `subList`, immutable iterators |
| 3 | `client.util.gui` | `ListExtension3` | `CopyOnWriteIfNeededArrayList` | own error string `"CopyOnWriteIfNeededArrayList doesn't support subList()!"`; clones only when mutated during a view |
| 4 | `client.util.mixin` | `MixinHelper2` | `ListBuilder` | fluent list-builder interface; `method2(T...) -> method3(Collection)`, `@Contract("_->this")` |
| 5 | `client.util.mixin` | `MixinHelper22` | `ListBuilderImpl` | `addAll`/`removeAll`/`removeIf`/`build`/`unmodifiableList` + static factories |
| 6 | `client.util.mixin` | `Data2` (nested) | `LazyListBuilder` | `MixinHelper22$Data2`: lazily creates the list from a `Supplier<L>`; `build()` null until touched |
| 7 | `files.mixin` | `Files2` | `AssetIndex` | `toString "AssetIndex(id=, sha1=, url=)"`; `assetIndex` field of `ResolvedVersion` |
| 8 | `files.mixin` | `Files3` | `VersionInfo` | `toString "VersionInfo{…}"`; `parent` chain, `method2() id>=26` |
| 9 | `files.mixin` | `Files4` | `VersionManifest` | `toString "VersionManifest(latest=, versions=)"`; `launchermeta…version_manifest.json` |
| 10 | `legacy.wrapper.util` | `OpenALNative2` | `OpenALSupport` | empty holder for a static boolean availability flag (sibling `OpenALNative` is the JNA interface) |
| 11 | `legacy.wrapper.util` | `OpenALNative3` | `BlockPathEvaluator` | static pathfindability check per `PathType` LAND/WATER/AIR; called by `Bridge2Handler.bridge$isPathfindable` |
| 12 | `legacy.wrapper.util` | `OpenALNative4` | `GuavaFunctionAdapter` | adapts Guava `Function` to `java.util.function.Function` |
| 13 | `mixin.mixin` | `BiomeGenBaseMixin2` | `BiomeGenBaseBridgeMixin` | `@Mixin(BiomeGenBase) implements BiomeBridge`; 1.8 twin of legacy `BiomeGenBaseMixin` |
| 14 | `mixin.mixin` | `ChunkMixin2` | `ChunkBiomeMixin` | `@Mixin(Chunk) implements Itemcounter2`; adds `bridge$getBiome` |
| 15 | `mixin.mixin` | `WorldMixin2` | `WorldBiomeMixin` | `@Mixin(value=World, priority=201) implements Itemcounter6`; adds `bridge$getBiome` |
| 16 | `bridge.fog` | `Fog2` | `PotionBridge` | status-effect type bridge: `isBadEffect`/`hasStatusIcon`/`getStatusIconIndex`/`getID` |
| 17 | `bridge.fog` | `Fog3` | `PotionRegistryBridge` | potion-type registry (getters return `Fog2`), `method10` creates a `Fog` effect, name/item lookups |
| 18 | `client.calculator` | `Calculator2` | `Translatable` | `getLanguagePath()` + `method1(key,args)`; implemented by `Calculator2Handler`/`FeatureDetailsImpl`/`Calculator2Type` |
| 19 | `client.calculator` | `CalculatorType2` | `TranslationVariable` | `id`/`dynamic`/`replacement` variable registry consumed by `TranslationFormatter` |
| 20 | `client.calculator.mixin` | `CalculatorHandler2` | `StringArrayTranslation` | pre-rendered `String[]` translation returned when no `Supplier` args |
| 21 | `client.calculator.mixin` | `CalculatorHandler3` | `ConstantTranslation` | single constant `String` translation |
| 22 | `client.feature` | `Module2` | `CosmeticSettings` | `toString "CosmeticSettings(…)"`; keys `clothCloak`/`flipShoulder`/`showOverChestplate`/… |
| 23 | `client.feature` | `ModuleType2` | `CosmeticCategory` | enum `HATS`/`BODYWEAR` → `cosmetics/indexes/<name>.json` |
| 24 | `client.fishing` | `Fishing2` | `ExternalLink` | registry error `"Unable to find ExternalLink interface for class: …"` |
| 25 | `client.fishing` | `Fishing_2` | `FancyMenuCompat` | probes `de.keksuccino.fancymenu.FancyMenu` |
| 26 | `client.fog.gui` | `Gui2` | `OptionGroup` | named group of `LightingExtension` options (`name` + `options()`) |
| 27 | `client.fog.gui` | `Gui3` | `ModMetadata` | `mod_metadata.json` entry: aliases/categories/authors + language path |
| 28 | `fov.mixin.rewindhandlers` | `Rewindhandlers2` | `ConditionalOutfitTree` | conditional outfits + default outfit; (de)serialised to proto `OutfitTree` |
| 29 | `fov.mixin.rewindhandlers` | `Data5` (nested) | `ConditionalOutfitEntry` | `Rewindhandlers2$Data5`: (equip condition, cosmetic outfit) pair |
| 30 | `…attackindicator.mixin` | `Attackindicator2` | `AttackIndicatorProvider` | provider interface: vanilla/active/progress/icon/sound; impls `Attackindicator2Handler*` |
| 31 | `…attackindicator.mixin` | `Attackindicator3` | `AttackIndicatorState` | immutable snapshot of a provider for one player |
| 32 | `…crosshair` | `Crosshair2` | `CrosshairPattern` | boolean-grid model with `LCCH` string (de)serialisation |
| 33 | `…crosshair` | `Crosshair3` | `CrosshairPresets` | built-in `LCCH` presets + `field17 Set<Crosshair2>` similarity match |
| 34 | `…crosshair.crosshairelytra` | `Crosshairelytra2` | `CrosshairShapeRenderer` | renders CROSS/CIRCLE/ARROW/… from `crosshairShape`/thickness/width/height/gap options |
| 35 | `…crosshair.crosshairelytra` | `Crosshairelytra3` | `CrosshairTextureRenderer` | renders the custom crosshair as texture (+ `_outline`) |
| 36 | `…markers.mixin` | `Markers2` | `MarkerSource` | local marker source: teams, recipient UUIDs, source validation, serialised `Markers` |
| 37 | `…markers.mixin` | `Markers3` | `MarkerDetectionFunction` | `@FunctionalInterface check(Bridge2_33, GameProfile, Markers)` |
| 38 | `…lotusfish` | `Lotusfish2` | `PlayerListEntryParser` | regex parses Hypixel player-list entries (`[lvl] [rank] name …`) |
| 39 | `…lotusfish` | `Lotusfish3` | `ScoreboardSection` | contiguous scoreboard lines of one tab widget |
| 40 | `…holograms.highlight` | `GuiRewindhandlersHandler22` | `HighlightTypeListener` | matches current screen title against `HighlightType` patterns |
| 41 | `…holograms.highlight` | `GuiRewindhandlersHandler23` | `StorageOverlayListener` | maintains the cached `Storageoverlay` list for Storage/Backpack/Ender-Chest |

Nested rows 6 and 29 carry the owner in the `evidence` column (`MixinHelper22$Data2`,
`Rewindhandlers2$Data5`) and the owner file in the 5th `file` column, per the
`classes-rewindhandlers.tsv` convention.

## Already-correct rows (not in the map)

Three cluster rows are **not** lazy placeholders and were left untouched; the
aware applier skips `old == new` anyway:

| row | why it is already correct |
|---|---|
| `net/minecraft/util/MessageDeserializer2` | real MCP 1.8.9 name (the varint frame decoder); present verbatim in Badlion and the 509 reference clients |
| `net/minecraft/util/MessageSerializer2` | real MCP 1.8.9 name (the varint frame encoder) |
| `net/minecraft/util/Vec3` | real MCP 1.8.9 name (the double-precision vector) |

## Collision notes

* `Fishing2`, `Gui2`, `Gui3`, `Markers2`, `Markers3`, `Calculator2`,
  `CalculatorHandler2`, `Module2`, `Data2`, `Data5` are placeholder names
  declared in many packages. The map is meant for `tools/apply_class_renames_aware.py`
  (import-aware), **not** the simple `apply_class_renames.py`; several of these
  rows would be skipped or corrupted by a global token replace.
* `client.util.gui.ListExtension2` and `ListExtension22` differ only by a
  trailing digit, so the map must be applied with the aware applier's
  word-boundary token pattern (the simple applier's `\b` also happens to be
  safe here, but the aware one is the supported path).
* `markers.mixin.Markers2`/`Markers3` have no importers in `src/main/java`
  (their consumer `markers.mixin.Markers` lives in quarantine); only the
  declaring files and same-package `Gui2Extension` are rewritten.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-utilgui.tsv
# → [aware-renames] 41 rows (2 nested); 6594 java files
# → [aware-renames] rows=41 skipped=0 files_touched=137 files_renamed=39 mode=dry-run
```

`skipped=0`: every row resolved a declaring package and a fresh new name
(no `new name already declared` and no `new name already used in this map`).
The 39 `files_renamed` are the 39 top-level declarations; the 2 nested rows
rename in-place inside their owner files.