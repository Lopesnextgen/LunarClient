# Wave 7, cluster 05 — `com.moonsworth.lunar.bridge` leftovers

**Map:** `classes-05.tsv` — 49 rows, all `package = com.moonsworth.lunar.bridge`.
**Dry run:** `python3 tools/apply_class_renames_aware.py --map tools/renames/wave7/classes-05.tsv`
→ `49 rows (1 nested); skipped=0; files_touched=656; files_renamed=48` (dry-run, no `--apply`).

## What this cluster is

Every class here is a **rescue-bundle copy of a concept that already exists in the
tree under a real name**. The provenance column of
`tools/mappings-snapshot/normalize-renames.tsv` shows the origin: the decompile
kept Lunar's multiver *mixin bundles* as folders
(`bridge/mixinMain`, `mixinAlpha`, `mixinAux`, `mixinInternal/mixinMisc/mixin`,
`mixinShared/mixinExtra/mixin`, `mixinSupport/mixin`, …) and a normalisation pass
flattened them into `com.moonsworth.lunar.bridge`, suffixing simple names with
`_N`. That is why the same packet/keybinding/food/matrix duck exists twice.

Consequences for this wave:

* No row can reuse the canonical twin's simple name (rule 4, unique tree-wide).
  Names therefore mirror the twin with a distinguishing role word
  (`…PacketTranslator` for the `mixinInternal/mixinMisc` packet family, following
  the already-applied `EntityHeadLookPacketTranslator` /
  `TimeUpdatePacketTranslator` convention from wave 5).
* The twin is named in the evidence of every row so the merge wave can fold the
  copies without re-researching them (table below).
* Nothing was skipped: no missing paths, no `net.minecraft.*` rows, no shaded
  third-party code in this cluster. (`forge/lib/…` and `genesis/…` copies of the
  same `MixinHelperNN` names exist in the normalise table but those blobs were
  already deleted in earlier waves.)

## The internal packet family (`MixinHelper10…29`, `MixinHelper2_7`, `MixinHelper_19`)

All from the `mixinInternal/mixinMisc/mixin` bundle. `MixinHelper_19` is the base
(exact twin of `PacketBuilder`), implemented by `legacy/wrapper/AbstractRewindPacketBuilder`;
each `MixinHelperNN` is one packet translator, implemented by a
`legacy/wrapper/*PacketFactory*` class and registered in
`legacy/wrapper/BridgeIterator` (which implements `PacketFactoryBridge` and is
installed by `legacy/Legacy2`). The parallel canonical family
(`legacy/wrapper/*PacketBuilder` + `bridge/*PacketBridge` + `bridge/PacketFactory`)
exists and is still consumed by `client/replay/recording/*`, so this is a genuine
two-family duplication, not dead code.

| old | new | canonical twin (merge target) |
|---|---|---|
| `MixinHelper10` | PlayerListHeaderFooterPacketTranslator | PlayerListHeaderFooterPacketBridge |
| `MixinHelper11` | SpawnMobPacketTranslator | SpawnMobPacketBridge |
| `MixinHelper12` | BlockBreakAnimationPacketTranslator | BlockBreakAnimationPacketBridge |
| `MixinHelper13` | ChunkUnloadPacketTranslator | ChunkUnloadPacketBuilder (interface) |
| `MixinHelper14` | HeldItemChangePacketTranslator | HeldItemChangePacketBridge |
| `MixinHelper15` | NoOpPacketTranslator | NoOpPacketBridge |
| `MixinHelper16` | BossInfoPacketTranslator | BossInfoPacketBridge |
| `MixinHelper17` | OpenWindowPacketTranslator | OpenWindowPacketBridge |
| `MixinHelper18` | PlayerAbilitiesPacketTranslator | PlayerAbilitiesPacketBridge |
| `MixinHelper19` | NoOpPacketListTranslator | NoOpPacketListBridge |
| `MixinHelper20` | SetExperiencePacketTranslator | SetExperiencePacketBridge |
| `MixinHelper21` | ChunkDataPacketTranslator | ChunkDataPacketBridge |
| `MixinHelper22` | NoOpNetHandlerTranslator | NoOpNetHandlerPacketBridge |
| `MixinHelper23` | EffectPacketTranslator | EffectPacketBridge |
| `MixinHelper24` | SpawnObjectPacketTranslator | SpawnObjectPacketBridge |
| `MixinHelper25` | WindowItemsPacketTranslator | WindowItemsPacketBridge |
| `MixinHelper26` | NoOpSnapshotPacketTranslator | NoOpBatchPacketBridge |
| `MixinHelper27` | ServerDifficultyPacketTranslator | ServerDifficultyPacketBridge |
| `MixinHelper28` | EntityMovePacketTranslator | EntityMovePacketBridge |
| `MixinHelper29` | PlayerListItemPacketTranslator | PlayerListItemPacketBridge |
| `MixinHelper_19` | PacketTranslator | PacketBuilder (exact member-for-member) |
| `MixinHelper2_7` | NoOpLegacyPacketTranslator | NoOpLegacyPacketBridge |

## Exact/near-exact twins outside the packet family

| old | new | canonical twin | notes |
|---|---|---|---|
| `Bridge_70` | VertexConsumerSourceBridge | VertexConsumerProvider | `Bridge4_6` == `VertexConsumerBridge`; both sides unreferenced except the twin's own file |
| `Bridge_8` | PoseTransformBridge | PoseStackBridge | member-for-member identical; live via `AbstractRenderContext`, `Bridge5$Data`, render-item events |
| `Bridge_9` | FoodPropertiesBridge | ItemFoodBridge | `@Annotation2(max=25)` vs `@VersionGate(max=25)` |
| `MixinHelper2` | ItemEntityRenderStateBridge | EntityItemStateBridge | renderSeed/renderCount/getItemState |
| `MixinHelper2_11` | StyledCodePointSink | StyledCharSink | only the parameter names differ |
| `MixinHelper2_12` | DynamicTextColorHolder | TextColorFunctionHolder | only the private ctor differs; **the copy is the live one** (ModernGuiGraphicsBridge), the twin has no refs |
| `MixinHelper2_2` | TypeCoercionChain | TypeCoercionPipeline | only the functor type differs (`MixinHelper_16` vs `TypeCoercion`) |
| `MixinHelper2_3` | InterpolatedValueAccessor | InterpolatedValueProvider | `@Annotation2(min=17)` vs `@VersionGate(min=17)` |
| `MixinHelper_15` | LegacyKeyBindingBridge | KeyBindingBridge | 1.7 variant implemented by `KeyBinding_v1_7Mixin`; twin uses `KeyBindingEntry` instead of `KeyBindingClashEntry` |
| `MixinHelper_16` | TypeCoercionFunctor | TypeCoercion | member-for-member identical |
| `MixinHelper_18` | TransformationBridge | Transformation | `@Annotation2(min=6)` vs `@VersionGate(min=6)` |
| `MixinHelper22_2` | AdventureFormattingSerializer | LegacyFormattingSerializer | only the formatting enum differs (AdventureChatFormatting vs ChatFormatting) |
| `MixinHelper_3` | BlockParticleSpawner | ParticleSpawner | only `Vector3iBridge` vs `Vec3iBridge` |
| `MixinHelper_6` | PlayerRendererBridge | RenderPlayerBridge | `BridgeExtension2_7` vs `ModelPlayerBridge` model type |
| `MixinHelper_9` | ItemDataComponentType | DataComponentType | member-for-member identical |
| `Extension2` (nested in `RenderSystemBridge`) | ModernRenderSystemExtension | top-level ModernRenderSystemBridge | **the nested interface is the live one** (`RenderSystemBridge.method83()` casts to it); the top-level twin has **zero references** |

## Unique roles (no duplicate to merge)

* `MixinHelper_2` → **VanillaRenderPipelines**: static registry of the vanilla
  `core/…` render pipelines (15 `Bridge$Extension` + 60 `Bridge_45` = 
  `RenderPipelineBridge` constants), registered from `legacy/Legacy2` and read 62×
  by `LunarRenderTypes`; `LunarRenderTypes` is the Lunar-side counterpart,
  not a duplicate.
* `MixinHelper_4` → **AbstractGuiGraphicsBridge**: abstract base of
  `LegacyGuiGraphicsBridge`/`ModernGuiGraphicsBridge`; the only class in the
  cluster that is a real supertype rather than a copy.
* `MixinHelper_21` → **Matrix4fBridge**: the multiversion 4×4 matrix duck
  (`@Annotation` maps 1.8 `util/Matrix4f` → 1.21.6 `org/joml/Matrix4f`); sibling of
  the existing `Matrix3fBridge` (wave 5 renamed `MixinHelper2_6` to it).
* `MixinHelper2_10` → **BedPartBridge** (`bridge$isFoot()`, `BlockBed.PART`).
* `MixinHelper2_4` → **LineWidthRenderTypeResolver**: float key is the line width
  (`MixinHelper7$Data8` field is literally `lineWidth`); twin of the
  `ResourceLocation`-keyed `RenderTypeResolver`.
* `MixinHelper2_13` → **FoodDataComponent**: min-26 food item component
  (`givesBadEffect`), sits next to the `Bridge_9`/`ItemFoodBridge` pre-26 path.
* `MixinHelper_7` → **PackSelectionEntryBridge** and `MixinHelper_17` →
  **ModelBakeryMarker**: annotation-only ducks, no live references.
* `MixinHelper_5` → **InternalMixinCoreMarker** and `MixinHelper_20` →
  **AuxBundleMarker**: empty mixin-bundle markers, no implementors/references
  (peers of the existing `AuxMarkerBridge`, `AuxCoreMarkerBridge`,
  `InternalCoreMixinMarker`, `SharedMixinMarker`).

## Caveats / follow-ups

1. **Nested row.** `Extension2` is a nested interface of `RenderSystemBridge`.
   The evidence starts with `RenderSystemBridge.Extension2`, which is how
   `apply_class_renames_aware.py` detects nested rows; the dry run reports
   `1 nested` and scopes the declaration edit to `RenderSystemBridge.java`
   (an unrelated `Extension2` exists in `client/util/ThreadModuleDump77.java` and
   was **not** touched in the dry run). Do not strip the owner prefix from that
   evidence line.
2. **Merge candidates.** After this map is applied, `wave6`-style merge rows can
   fold the copies listed above; the ones that are outright dead
   (`ModernRenderSystemBridge` and its sibling `LegacyRenderSystemBridge`,
   `VertexConsumerProvider`, `TextColorFunctionHolder`) should probably be
   deleted/redirected in favour of the live bundle copies. The
   packet family merge is the big one (`BridgeIterator`/`*PacketFactory` vs
   `PacketFactory`/`*PacketBuilder`) — both sides still have consumers
   (`rewindhandlers*` vs `replay/recording`), so that merge needs the same
   decision the wave-6 replay merge made, not a blind rename.
3. Names were checked tree-wide (`src/main/java`, including `net/minecraft`):
   no collisions, no trailing digits.
