# Cluster 24 — `mod.holograms.fishing` + `bridge.itemcounter` + `inactive.rewindhandlers` (40 classes)

Source revision: `tools/renames/cluster-24.txt` (40 rows).
Map: `tools/renames/classes-holograms-bridge.tsv`.

The cluster file mixes **three unrelated packages** that the earlier passes had
left with placeholder names. The task blurb described only the first one and
called it the "fishing holograms/entities stack"; the source shows that is only
half right (see below). Every row below is justified from the class body
(fields, methods, `@Annotation27` JSON keys, string literals, `@Mixin`
targets, supertypes), not from the package name.

## What the three packages actually are

### 1. `com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing` (14 rows)

Not hologram rendering — these are **Hypixel SkyBlock event listeners**. Each
class extends `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2`,
which is the client's `DynamicListener` base (its own error string is
`"DynamicListener#addDependency() must be called ..."`): a dependency-ordered
listener that registers `handle(EventClass, Consumer)` callbacks and has
`onEnable`/`onDisable`/`isEnabled`. The classes are wired to the SkyBlock HUDs
and mods in `client/mod/hud/Skyblock*` and `client/mod/misc/Skyblock*`, and the
events they fire carry `@Annotation3(GuiRewindhandlersHandlerN.class)`.

| listener | role | fired event (consumer) |
|---|---|---|
| `GuiRewindhandlersHandler2` | Slayer quest tracker | `HighlightBase3.Data/Data2/Data3` |
| `GuiRewindhandlersHandler22` | SkyBlock profile cache | `Rewindhandlers.Data14` |
| `GuiRewindhandlersHandler23` | sidebar → `Lotusfish3` section parser | — |
| `GuiRewindhandlersHandler24` | action-bar health/defense/mana/overflow | — |
| `GuiRewindhandlersHandler25` | Dwarven Mines commissions | `HighlightBase2.Data/Data2/Data3` |
| `GuiRewindhandlersHandler26` | skill XP/level tracker | `HighlightImpl4` |
| `GuiRewindhandlersHandler27` | fishing-hook / sea-creature tracker | — |
| `GuiRewindhandlersHandler28` | SkyBlock time/weather (☀/☽/☔/⚡) | `HighlightType5` |
| `GuiRewindhandlersHandler29` | equipped-item tracker | `HighlightImpl` |
| `GuiRewindhandlersHandler210` | sidebar location (⏣/ф) | `HighlightType6` |
| `GuiRewindhandlersHandler211` | election mayor/perks | — |
| `GuiRewindhandlersHandler212` | `Profile ID:` parser | `Rewindhandlers.Data13/Data15` |
| `GuiRewindhandlersHandler213` | gift armor-stand tracker | — |

Only `GuiRewindhandlersHandler27` (fishing) and `GuiRewindhandlersHandler2`
(slayer mobs) touch entities/"sea creatures"; the rest are stat/scoreboard
listeners. So the package name `holograms.fishing` is a leftover, not a
description.

### 2. `com.moonsworth.lunar.bridge.itemcounter` (13 rows)

A generic **MCP bridge bucket** (same idea as `bridge.horsestats`): one
obfuscated package that wraps a grab-bag of vanilla types. Provenance is in
`tools/mappings-snapshot/normalize-renames.tsv` (`mixinAlt`/`mixinCore`/
`mixinExtra`/`mixinMisc`/`mixinMore` subpackages were flattened into it).

Confirmed targets (from method signatures / `@Mixin` classes):

| class | target | evidence |
|---|---|---|
| `Itemcounter4` | `WorldSettings` | `legacy/mixin/WorldSettingsMixin @Mixin(WorldSettings)`; `Bridge5_12.bridge$joinWorld(...,Itemcounter4,...)` |
| `Itemcounter8` | `LevelData` | Lombok `@ToString` emits `"LevelData(path=...)"` |
| `ItemcounterType2` | `GameType` | SURVIVAL/CREATIVE/ADVENTURE/SPECTATOR |
| `ItemcounterType_2` | biome registry | OCEAN…SULFUR_CAVES enum, `fromBiomeBridge(Itemcounter_3)` |
| `ItemcounterType_3` | `PathType` | LAND/WATER/AIR, `Bridge2_17.bridge$isPathfindable` |
| `ItemcounterType_4` | `BlockRenderType` | INVISIBLE/ENTITYBLOCK_ANIMATED/MODEL, `bridge$getRenderShape()` |
| `Itemcounter_2` | `MapDecoration` | x/y/rot + `getDecorationType` (`Itemcounter$Type` GREEN/BLUE) |
| `Itemcounter_3` | `Biome` | `BiomeGenBaseMixin @Mixin(BiomeGenBase)` |
| `Itemcounter_5` | equippable/equipment-layer component | `bridge$layerAssetId(boolean,ItemStack)`, `bridge$isDecal()`; `DataComponentTypes.field37` |

The five **empty marker interfaces** (`Itemcounter2`, `Itemcounter3`,
`Itemcounter5`, `Itemcounter7` and, in the same family, `Itemcounter4`) are
duck interfaces for classes that are only passed around opaquely. `Itemcounter4`
is implemented by `WorldSettingsMixin`; the others have **no implementer in any
runtime jar** (checked bytecode-level across `lunar.jar`, `legacy`, `forge`,
`genesis`, `common`), so their target is inferred from the sibling grouping:
`Itemcounter2` with the Chunk bridge, `Itemcounter3` with WorldChunkManager,
`Itemcounter5` with WorldInfo, `Itemcounter7` standalone. Treat those four
names as **medium confidence**.

### 3. `com.moonsworth.lunar.client.inactive.rewindhandlers` (13 rows)

Not "rewind handlers" — this is the **GeckoLib / Bedrock model format**
(Blockbench `.geo.json` + `.animation.json`). Proof: every class is annotated
with `@Annotation27("<json key>")` using the Bedrock schema (`inflate`,
`mirror`, `origin`, `pivot`, `size`, `uv`, `uv_size`, `bones`, `cape`,
`description`, `format_version`, `minecraft:geometry`, `texture_meshes`,
`poly_mesh`, `visible_bounds_*`, `animationArmsDown`, …), and the sibling
`IBoneSerializer` (not in this cluster) literally
`implements software.bernie.geckolib3.core.processor.IBone`. The package is
`inactive`, i.e. dead code kept for the model loader.

## Renames (40 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `GuiRewindhandlersHandler2` | `SlayerQuestListener` | Slayer quest chat/sidebar tracker; fires `HighlightBase3.Data/Data2/Data3` |
| 2 | `GuiRewindhandlersHandler22` | `SkyblockProfileCache` | `SkyBlockProfileUtil.getProfileSync` → `Profile`/`Member`, fires `Rewindhandlers.Data14` |
| 3 | `GuiRewindhandlersHandler23` | `SkyblockScoreboardParser` | sidebar → `Map<String,Lotusfish3>` sections |
| 4 | `GuiRewindhandlersHandler24` | `ActionBarStatsListener` | parses `[❤][❈][✎][ʬ][♨]` action bar |
| 5 | `GuiRewindhandlersHandler25` | `CommissionListener` | Dwarven Mines commissions; fires `HighlightBase2.*` |
| 6 | `GuiRewindhandlersHandler26` | `SkillXpListener` | skill XP/level; `skyblock_skill_levels.json`; fires `HighlightImpl4` |
| 7 | `GuiRewindhandlersHandler27` | `FishingHookTracker` | hook/sea-creature tracking, `HorsestatsType2.FISHING/SMOKE` |
| 8 | `GuiRewindhandlersHandler28` | `SkyblockTimeListener` | sidebar time+icon → `HighlightType5` |
| 9 | `GuiRewindhandlersHandler29` | `EquippedItemListener` | equipped tool id/name; fires `HighlightImpl` |
| 10 | `GuiRewindhandlersHandler210` | `SkyblockLocationListener` | sidebar location → `HighlightType6` |
| 11 | `GuiRewindhandlersHandler211` | `SkyblockElectionListener` | `SkyBlockElectionUtil.getElectionSync` mayor/perks |
| 12 | `GuiRewindhandlersHandler212` | `ProfileIdListener` | `Profile ID: <uuid>`; fires `Rewindhandlers.Data13/Data15` |
| 13 | `GuiRewindhandlersHandler213` | `GiftTracker` | gift armor stands (`WHITE_GIFT`/`From:`/`To:`) |
| 14 | `Data2` (nested) | `Trail` | nested `GuiRewindhandlersHandler27$Data2`; entity trail + angle state |
| 15 | `Itemcounter2` | `IBlockAccessBridge` | empty marker (medium confidence) |
| 16 | `Itemcounter3` | `ChunkProviderBridge` | empty marker (medium confidence) |
| 17 | `Itemcounter4` | `WorldSettingsBridge` | `WorldSettingsMixin @Mixin(WorldSettings)` |
| 18 | `Itemcounter5` | `WorldProviderBridge` | empty marker (medium confidence) |
| 19 | `Itemcounter7` | `TileEntityBridge` | empty marker (medium confidence) |
| 20 | `Itemcounter8` | `LevelData` | `@ToString` → `"LevelData(path=...)"` |
| 21 | `ItemcounterType2` | `GameTypeBridge` | SURVIVAL/CREATIVE/ADVENTURE/SPECTATOR |
| 22 | `ItemcounterType_2` | `Biome` | Lunar biome registry enum |
| 23 | `ItemcounterType_3` | `PathTypeBridge` | LAND/WATER/AIR |
| 24 | `ItemcounterType_4` | `BlockRenderTypeBridge` | INVISIBLE/ENTITYBLOCK_ANIMATED/MODEL |
| 25 | `Itemcounter_2` | `MapDecorationBridge` | x/y/rot + decoration type |
| 26 | `Itemcounter_3` | `BiomeBridge` | `BiomeGenBaseMixin` |
| 27 | `Itemcounter_5` | `EquippableComponentBridge` | `layerAssetId`/`isDecal` data component |
| 28 | `Rewindhandlers2` | `GeoModelSerializer` | Gson loader for Bedrock geometry |
| 29 | `Rewindhandlers3` | `ModelQuad` | face: 4 vertices + normal + facing |
| 30 | `Rewindhandlers4` | `ModelVertex` | position + u/v |
| 31 | `Rewindhandlers5` | `ModelCube` | `cubes` entry (origin/size/pivot/…/uv) |
| 32 | `Rewindhandlers6` | `ModelDescription` | `identifier`/`texture_width`/`visible_bounds_*` |
| 33 | `Rewindhandlers7` | `FaceTexture` | `material_instance`/`uv`/`uv_size` |
| 34 | `Rewindhandlers8` | `ModelFaceTextures` | six faces down/east/north/south/up/west |
| 35 | `Rewindhandlers10` | `ModelTransform` | `ignore_inherited_scale`/`offset`/`rotation` |
| 36 | `Rewindhandlers13` | `BedrockModel` | root: `bones`/`cape`/`description` |
| 37 | `Rewindhandlers14` | `TextureMesh` | `local_pivot`/`position`/`rotation`/`scale`/`texture` |
| 38 | `RewindhandlersType2` | `FormatVersion` | `1.12.0`/`1.14.0`/`1.21.20` |
| 39 | `Rewindhandlers_2` | `CubeMesh` | built cube: 6 quads from a `ModelCube` |
| 40 | `Rewindhandlers_3` | `PolyMesh` | `normalized_uvs`/`normals`/`polys`/`positions`/`uvs` |

## Applier dry run, and the collision rows

```
python3 tools/apply_class_renames.py --map tools/renames/classes-holograms-bridge.tsv
# → applied=21 skipped=19 files_touched=70 files_renamed=21 mode=dry-run
```

21 rows apply with the simple tree-wide rewriter. The 19 skips are
**old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `GuiRewindhandlersHandler2` | `framework.feature.mod`, `…mod.holograms.{gui,mixin,nameplate,rewindhandlers}`, `inventorymod.mixin`, `guiRewindhandlers.rewindhandlers`, `util.alert` (9 packages) |
| `GuiRewindhandlersHandler22`…`29` | `…mod.holograms.{highlight,mixin}`, `guiRewindhandlers`, `guiRewindhandlers.rewindhandlers` |
| `Data2` (nested) | 15+ packages (and the new name must be written inside the file, not tree-wide) |
| `Itemcounter2`, `Itemcounter3`, `Itemcounter4`, `Itemcounter_2` | `bridge.itemcounter.mixin`, `client.framework.feature.itemcounter` |
| `Rewindhandlers2`, `Rewindhandlers3`, `RewindhandlersType2`, `Rewindhandlers_2`, `Rewindhandlers_3` | `client.click.rewindhandlers`, `client.fov.mixin.rewindhandlers`, `client.rewindhandlers`, `framework.feature.rewind.rewindhandlers[.mixin]`, `framework.feature.mod.fishing.rewindhandlers`, `inactive.mixin.rewindhandlers.mixin`, `guiRewindhandlers.rewindhandlers`, `fog.rewindhandlers` |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
simple names in the unrelated `guiRewindhandlers`, `framework.feature.rewind`
and `client.framework.feature.itemcounter` packages, merging distinct types.
The collision rows need the planned import/package-aware rewriter (same
follow-up as `classes-07.md`, `classes-12.md`, `classes-15.md`, `classes-23.md`).

## Caveats / follow-ups

* **Nested row 14** (`GuiRewindhandlersHandler27$Data2` → `Trail`) is a real
  nested type. The applier matches declarations by simple name and `Data2` is
  declared in 15+ packages, so it is skipped by design; the 5th TSV column
  carries the exact file path for the nested-aware pass.
* **Medium-confidence rows 15/16/18/19** (`Itemcounter2/3/5/7`) are empty
  marker interfaces with no implementer in any runtime jar. Names follow the
  sibling grouping in the bucket; verify against a full (non-partial) tree if
  one becomes available.
* **Row 22** reuses the simple name `Biome`; no `Biome` type is declared in the
  tree (1.8 uses `BiomeGenBase`), so the applier accepts it, but the applier's
  word-boundary rewrite of `ItemcounterType_2` must not be confused with the
  dangling `net.minecraft.world.biome.Biome` references.
* **Row 27** (`Itemcounter_5`) is a modern data component; the exact vanilla
  component (equippable vs. equipment asset/model) could not be pinned down
  from the partial tree, hence the component-flavoured name.
* The Bedrock-model package (`inactive.rewindhandlers`) still has several
  **unlisted** siblings with placeholder names (`Rewindhandlers2_2`,
  `Rewindhandlers3_2`, `Rewindhandlers4_2`, `RewindhandlersException`,
  `RewindhandlersIterator`, `RewindhandlersType`, `Rewindhandlers`,
  `IBoneSerializer`); they belong to a separate cluster but should be named
  consistently with rows 28–40 (`GeoModelInstance`, `GeometryFile`,
  `ModelBone`, `ModelParseException`, `GeoModelFactory`, `PolyMeshType`,
  `ModelFactory`).

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-holograms-bridge.tsv
# → applied=21 skipped=19 files_touched=70 files_renamed=21 mode=dry-run
```

New names checked against the tree's declaration scan (`grep -rE
"\b(class|interface|enum|record) NewName\b" src/main/java`) — all unique.