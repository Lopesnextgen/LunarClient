# Cluster 40 — `com.moonsworth.lunar.bridge` (45 rows)

Source: `tools/renames/cluster-40.txt` (45 lines). **All 45 files exist**; map:
`tools/renames/wave5/classes-40.tsv` (45 rows). No `net.minecraft.*` rows, no
shaded third-party classes in this cluster. Dry run:

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-40.tsv
[aware-renames] 45 rows (0 nested); 13684 java files
[aware-renames] rows=45 skipped=0 files_touched=280 files_renamed=45 mode=dry-run
```

## What this cluster actually is

This slice of `bridge/**` is split in two very different halves:

* **36/45 are rescued duplicates.** The 2026-09-16 rescue sweep
  (`7adc91305`) restored the pre-rename copies of classes that wave-4 maps had
  already renamed (`classes-10`, `classes-bridge4`, and the `classes-finalbridge`
  nested-type pass). Both copies now compile and are referenced from different
  call sites, so the twin name is already declared and a row pointing at it
  would be skipped by the applier. Per the cluster-51 precedent
  (`classes-51.md` §2) each duplicate gets a **unique non-lazy name** here; the
  already-named twin is listed for the merge/dedupe pass below.
* **9/45 are genuinely unnamed** live bridge classes from the original
  decompile: the render-type/pipeline builders, the two `GuiGraphics`
  adapters, the Lunar render-type registry, the text-colour source, the
  output-state-shard marker, the profiler-result bridge and the item render
  state duck. These got real names in this map.

Naming references used: `classes-finalbridge.md` (already named the
`MixinHelper7_4` state enums as `LayeringMode`/`LightmapMode`/…, the
`MixinHelper4$Extension` as `TextColorTransform` and calls `MixinHelper4_6`
"RenderPipeline.Builder"), the applied twin maps, and the class bodies.

## The 9 real names

| # | old | new | evidence (short) |
|---|---|---|---|
| 1 | `MixinHelper3_9` | `OutputStateShardBridge` | marker implemented by `MainTargetRenderStateShard`/`Bridge19Task2`; `@BridgeMemberTarget` maps it to `RenderStateShard$OutputStateShard` (v6) / `rendertype/OutputTarget` (v35) |
| 2 | `MixinHelper4_2` | `TextColorSource` | `getColor()` + `method1(float)` gradient + `method14()` dynamic flag + `method3(TextColorTransform)`; `RewindhandlersExtension` extends it |
| 3 | `MixinHelper4_6` | `RenderPipelineBuilder` | abstract builder of `RenderPipelineBridge` (shader/defines/samplers/uniforms/blend/depth/vertex-format setters, `method44()` final build with "Missing …" guards); impl `legacy/wrapper/MixinHelper4`, selected via `Annotation9_2.value()` |
| 4 | `MixinHelper5` | `LegacyGuiGraphicsBridge` | `extends MixinHelper_4`, delegates to the legacy draw context `BridgeExtension_9`; fallback branch of `BridgeExtension_9.method42()` |
| 5 | `MixinHelper5_6` | `LunarRenderTypes` | static registry of `RenderTypeBridge` constants `lunar_gui_triangles`/`lunar_hologram`/`lunar_cloak`/`lunar_block_overlay_*` + `LUNAR_LEGACY_DYNAMIC_QUADS` factory (`field64`); `method1`/`method2` build the layers |
| 6 | `MixinHelper6_5` | `ModernGuiGraphicsBridge` | `@VersionGate(min=30)` `GuiGraphics` adapter extending `MixinHelper_4`, delegates to `Bridge8_2` (`bridge$fill$v1_20_0`, `bridge$submitDynamic$v1_21_6`, `bridge$blit$v1_21_6`, scissor ops) |
| 7 | `MixinHelper7_4` | `RenderTypeBuilder` | abstract `RenderType` factory: state-shard setters + `create(RenderPipelineBridge, name, bufferSize, sortOnUpload, affectsOutline, OutlineProperty)`; impls `legacy/wrapper/MixinHelper7`, `genesis/MixinHelper72` |
| 8 | `MixinHelper_11` | `ProfilerResultBridge` | `usePercentage`/`globalPercentage`/`profilerName` = `Profiler$Result` (v0) / `ResultField` (v6); returned by `MinecraftBridge.bridge$getPieChartResults()`; impls `F3PieSlice`, `F3display3` |
| 9 | `MixinHelper_14` | `ItemStackRenderStateBridge` | item render state duck (displayContext/isLeftHand/activeLayerCount/layers/isEmpty/item type+material/armorState/extractRenderStates); `BridgeExtension_4` extends it; layer type is `MixinHelper$Extension4` |

Note: `classes-finalbridge.tsv` rows that target nested types of classes in this
cluster (e.g. `MixinHelper4$Extension` → `TextColorTransform`,
`MixinHelper7$Type*` → `LayeringMode`/…) are already applied; their twins are in
the tree and are **not** re-emitted here.

## §2 Duplicate copies (36 rows)

Every row below is the same decompiled class as the listed already-named twin
(verified by member/signature diff; the only differences are local names and
already-renamed type references). The applier can apply the fresh names without
conflict; if the merge pass instead deletes the surviving twin, the **intended
final name is the twin name** (last column). Ref counts are files under
`src/main/java` referencing the simple name.

| cluster class | new name here | already-named twin | refs here / twin | final name if twin is kept |
|---|---|---|---|---|
| `MixinHelper2_9` | `DataComponentContainerBridge` | `DataComponentMapBridge` | 5 / 1 | `DataComponentMapBridge` |
| `MixinHelper3` | `ItemDataComponentTypes` | `DataComponentTypes` | 179 / 17 | `DataComponentTypes` |
| `MixinHelper30` | `EntityHeadLookPacketTranslator` | `EntityHeadLookPacketBridge` | 72 / 3 | `EntityHeadLookPacketBridge` |
| `MixinHelper31` | `SpawnPaintingPacketTranslator` | `SpawnPaintingPacketBridge` | 37 / 3 | `SpawnPaintingPacketBridge` |
| `MixinHelper3_2` | `LineSegmentSink` | `LineSink` | 95 / 1 | `LineSink` |
| `MixinHelper3_3` | `CharacterBoxingCoercion` | `CharacterCoercion` | 78 / 2 | `CharacterCoercion` |
| `MixinHelper3_4` | `MatrixTransformFactory` | `MatrixTransformProvider` | 7 / 1 | `MatrixTransformProvider` |
| `MixinHelper3_5` | `ArmorColorState` | `ArmorRenderState` | 75 / 1 | `ArmorRenderState` |
| `MixinHelper3_6` | `BakedQuadExtension` | `BakedQuadBridge` | 11 / 5 | `BakedQuadBridge` |
| `MixinHelper3_7` | `WorldBorderPacketTranslator` | `WorldBorderPacketBridge` | 15 / 2 | `WorldBorderPacketBridge` |
| `MixinHelper3_8` | `ArmorDyeColorComponent` | `DyedColorComponentBridge` | 6 / 3 | `DyedColorComponentBridge` |
| `MixinHelper4` | `TimeUpdatePacketTranslator` | `TimeUpdatePacketBridge` | 98 / 3 | `TimeUpdatePacketBridge` |
| `MixinHelper42` | `BakedModelMarker` | `BakedModelMarkerBridge` | 18 / 1 | `BakedModelMarkerBridge` |
| `MixinHelper43` | `BakedModelVariantMarker` | `BakedModelVariantBridge` | 57 / 1 | `BakedModelVariantBridge` |
| `MixinHelper4_3` | `AdventureTextCoercion` | `AdventureComponentCoercion` | 14 / 2 | `AdventureComponentCoercion` |
| `MixinHelper4_4` | `ContainerItemsComponent` | `ContainerItemsComponentBridge` | 10 / 3 | `ContainerItemsComponentBridge` |
| `MixinHelper4_5` | `BakedModelExtension` | `BakedModelBridge` | 27 / 20 | `BakedModelBridge` |
| `MixinHelper5_2` | `BlockPartFaceExtension` | `BlockPartFaceBridge` | 29 / 5 | `BlockPartFaceBridge` |
| `MixinHelper5_3` | `SpawnExperienceOrbPacketTranslator` | `SpawnExperienceOrbPacketBridge` | 17 / 3 | `SpawnExperienceOrbPacketBridge` |
| `MixinHelper5_4` | `MappedTypeCoercion` | `RemappedTypeCoercion` | 11 / 2 | `RemappedTypeCoercion` |
| `MixinHelper5_5` | `KineticWeaponTiming` | `KineticWeaponPhases` | 34 / 2 | `KineticWeaponPhases` |
| `MixinHelper6` | `PropertyMapDataComponent` | `PropertyMapComponent` | 84 / 3 | `PropertyMapComponent` |
| `MixinHelper6_2` | `SpawnPositionPacketFactory` | `SpawnPositionPacketBuilder` | 23 / 2 | `SpawnPositionPacketBuilder` |
| `MixinHelper6_3` | `RenderTypeResolver` | `RenderTypeLookup` | 25 / 7 | `RenderTypeLookup` |
| `MixinHelper6_4` | `SupertypeCoercion` | `SubtypeCoercion` | 25 / 2 | `SubtypeCoercion` |
| `MixinHelper6_6` | `ModelBuilderExtension` | `ModelBuilderBridge` | 19 / 3 | `ModelBuilderBridge` |
| `MixinHelper7` | `EntityPlayerPacketFactory` | `EntityPlayerPacketBuilder` | 143 / 3 | `EntityPlayerPacketBuilder` |
| `MixinHelper7_2` | `QuadFactoryExtension` | `BakedQuadFactoryBridge` | 19 / 4 | `BakedQuadFactoryBridge` |
| `MixinHelper7_3` | `OptionalUnwrapCoercion` | `OptionalCoercion` | 36 / 2 | `OptionalCoercion` |
| `MixinHelper7_5` | `ItemLoreComponent` | `LoreComponent` | 8 / 5 | `LoreComponent` |
| `MixinHelper8` | `NoOpWorldPacketFactory` | `NoOpWorldPacketBuilder` | 78 / 3 | `NoOpWorldPacketBuilder` |
| `MixinHelper8_2` | `TypeConversionAdapter` | `TypeConversionCoercion` | 14 / 2 | `TypeConversionCoercion` |
| `MixinHelper9` | `WorldJoinPacketFactory` | `WorldJoinPacketBuilder` | 105 / 3 | `WorldJoinPacketBuilder` |
| `MixinHelper_10` | `CompoundTagDataComponent` | `CompoundTagComponent` | 12 / 15 | `CompoundTagComponent` |
| `MixinHelper_12` | `EntityRenderStateExtension` | `EntityRenderState` | 7 / 3 | `EntityRenderState` |
| `MixinHelper_13` | `ClickableTextExtension` | `ClickableText` | 18 / 12 | `ClickableText` |

**Merge recommendation:** the duplicate copies are the *live* ones (the rescue
restored references to them tree-wide — e.g. `MixinHelper3` 179 files vs
`DataComponentTypes` 17), while the twins are the filtered/renamed lineage. A
dedupe pass should keep one copy per concept, repoint references, then delete
the other; whichever copy survives should carry the twin name above (that is
the name already used by the earlier wave and the more accurate one). Until
that pass runs, applying this map leaves the tree consistent (both families
compile, no lazy names).

## §3 Skipped rows

* None. `skipped=0` in the applier dry run; all 45 paths exist.
* No shaded third-party classes in this cluster (no Jackson/Mixin/Guava/Kotlin
  stems in `com/moonsworth/lunar/bridge`; the remaining `genesis`/`forge.lib`
  shaded families live in other clusters).

## §4 Ambiguities / caveats

* **Duplicates vs canonical names** (§2) is the main ambiguity: the map
  deliberately keeps the tree unique instead of re-declaring twin names; the
  merge pass must not apply this map a second time after deleting twins
  (names would then drift).
* `RenderPipelineBuilder` is close in meaning to `client/render/RenderPipeline`
  (Lunar's own GPU pipeline, unrelated class). No simple-name collision, but
  keep the distinction in mind when reading bridge code.
* `LegacyGuiGraphicsBridge`/`ModernGuiGraphicsBridge` name the two adapters by
  version gate; the underlying bases are `BridgeExtension_9` (legacy) and
  `Bridge8_2` (modern, `@VersionGate(min=17..)` members).
* `MixinHelper3_9` is version-mapped to two different vanilla types
  (`RenderStateShard$OutputStateShard` v6, `rendertype/OutputTarget` v35); the
  chosen name follows the older target. A future `OutputTargetBridge` rename is
  possible if the modern mapping becomes the primary one.
* `MixinHelper_11` is *not* implemented by a vanilla mixin — it is a Lunar-side
  abstraction filled by `F3PieSlice`/`F3display3`; "Bridge" is kept for family
  consistency with the profiler-result version mapping.
