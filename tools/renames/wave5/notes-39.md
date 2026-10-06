# Cluster 39 — `com.moonsworth.lunar.bridge` part 4 (45 rows)

Map: `tools/renames/wave5/classes-39.tsv` — **4 rows** (4 renamed, **41 skipped** as
restored stale-jar duplicates of already-applied names, see §2).

| | |
|---|---|
| cluster | `com.moonsworth.lunar.bridge`, files `Bridge_7..Bridge_70`, `Bridge12$Extension2`, `MixinHelper*` |
| renamed | `Bridge_67`, `MixinHelper2_5`, `MixinHelper2_6`, `MixinHelper2_8` |
| skipped | 40 same-package twins already renamed by earlier waves + nested `Bridge12$Extension2` |
| applier | all 4 rows are top-level same-package declarations; `apply_class_renames_aware.py` accepts them (no 5th `file` column needed) |

## §1 What this cluster is

Two different things share the cluster because the wave-5 inventory was generated
over the mixed tree:

* **Packet-recorder SPI** (`MixinHelper10..29`, `MixinHelper2`, `MixinHelper2_2..2_13`,
  `MixinHelper2_7`): the per-packet interfaces of Lunar's rewind/replay recorder.
  Every one of them is implemented by a `legacy/wrapper/MixinHelper*` adapter
  (built and registered by `legacy/wrapper/BridgeIterator implements Bridge_16`),
  and typed by `Bridge_16` (the factory interface, `Bridge.method59()`'s
  pre-rename return type).
* **Render/engine duck interfaces** (`Bridge_7..Bridge_70`, `Bridge12$Extension2`):
  multiversion bridges for keys, GL state, marker positions, display lists,
  vertex formats, food, etc.

## §2 The mixed-lineage problem (why 41 rows are skipped)

`libs/lunar-renamed-classes.jar` is the pre-rename snapshot that the rescue
sweeps (`7adc91305`, `837e3f59`, …) restored classes *from*; the same classes were
renamed in `src/main` by earlier applied waves and re-added under their old names
by the rescue. The tree therefore holds **two copies of the same class in the same
package** — one already carrying a real name, one still obfuscated.

Verification per pair (same procedure as `classes-59.md` §2):

* the twin file is already declared in `com/moonsworth/lunar/bridge`;
* an identifier-normalised token comparison shows the bodies differ **only** in the
  class's own name plus the lineage's type renames
  (`Bridge3_21`↔`PacketBridge`, `MixinHelper_19`↔`PacketBuilder`, `Bridge_12`↔
  `ContainerBridge`, `BridgeExtension_7`↔`NetHandlerPlayClientBridge`,
  `Bridge5Extension9`↔`GuiIngameBridge`, `Bridge4_6`↔`VertexConsumerBridge`,
  `Bridge20`↔`RenderTypeBridge`, `Annotation2`↔`VersionGate`,
  `HorsestatsType8`↔`ChatFormatting`, `MixinHelper3_3/4_3/5_4`↔
  `CharacterCoercion/OptionalCoercion/AdventureComponentCoercion`), i.e. they are
  the same class, not a different revision;
* emitting a row for them would be rejected by the applier anyway
  (`SKIP … new name already declared`).

If the main agent prefers renaming over dedupe (the classes-51 §2 route), the
exact twin names are still unusable and a family-consistent synonym set would be
needed (e.g. `<Packet>PacketFactory`, `VertexFormatAccessBridge`, …); that is not
recommended for these jar-coupled interfaces — the merge is the cleaner fix.

Per the **classes-59 §2** convention these are repair work, not naming work:
the rescued copy must be **deleted** and the remaining call sites unified onto the
named generation (or, if a copy must stay for jar unification, turned into a
named shim in the `TextureBridge extends Bridge3_4` style). Ref counts below are
import-aware: same-package bare name, `import`, or FQN (own file excluded).

### Skipped rows (41)

| # | cluster old | already-applied twin | refs old / twin |
|---|---|---|---|
| 1 | `Bridge_63` | `VertexFormatBridge` | 21 / 16 |
| 2 | `Bridge_64` | `TextureOverrideBridge` | 0 / 0 |
| 3 | `Bridge_65` | `MarkerPositionBridge` | 10 / 5 |
| 4 | `Bridge_66` | `DisplayListBridge` | 3 / 1 |
| 6 | `Bridge_68` | `GlStateQueryBridge` | 1 / 1 |
| 7 | `Bridge_69` | `EntityListBridge` | 2 / 2 |
| 8 | `Bridge_7` | `KeyEventBridge` | 11 / 10 |
| 9 | `Bridge_70` | `VertexConsumerProvider` | 0 / 0 |
| 10 | `Bridge_8` | `PoseStackBridge` | 5 / 4 |
| 11 | `Bridge_9` | `ItemFoodBridge` | 4 / 3 |
| 12 | `Bridge12$Extension2` (nested in `Bridge12.java`) | `ModernRenderSystemBridge` | 1 / 0 |
| 13 | `MixinHelper10` | `PlayerListHeaderFooterPacketBridge` | 3 / 2 |
| 14 | `MixinHelper11` | `SpawnMobPacketBridge` | 3 / 2 |
| 15 | `MixinHelper12` | `BlockBreakAnimationPacketBridge` | 3 / 2 |
| 16 | `MixinHelper13` | `ChunkUnloadPacketBuilder` | 3 / 1 |
| 17 | `MixinHelper14` | `HeldItemChangePacketBridge` | 3 / 2 |
| 18 | `MixinHelper15` | `NoOpPacketBridge` | 3 / 2 |
| 19 | `MixinHelper16` | `BossInfoPacketBridge` | 3 / 1 |
| 20 | `MixinHelper17` | `OpenWindowPacketBridge` | 3 / 2 |
| 21 | `MixinHelper18` | `PlayerAbilitiesPacketBridge` | 3 / 2 |
| 22 | `MixinHelper19` | `NoOpPacketListBridge` | 3 / 2 |
| 23 | `MixinHelper2` | `EntityItemStateBridge` | 2 / 2 |
| 24 | `MixinHelper20` | `SetExperiencePacketBridge` | 3 / 2 |
| 25 | `MixinHelper21` | `ChunkDataPacketBridge` | 3 / 2 |
| 26 | `MixinHelper22` | `NoOpNetHandlerPacketBridge` | 3 / 2 |
| 27 | `MixinHelper22_2` | `LegacyFormattingSerializer` | 0 / 1 |
| 28 | `MixinHelper23` | `EffectPacketBridge` | 3 / 2 |
| 29 | `MixinHelper24` | `SpawnObjectPacketBridge` | 3 / 2 |
| 30 | `MixinHelper25` | `WindowItemsPacketBridge` | 3 / 2 |
| 31 | `MixinHelper26` | `NoOpBatchPacketBridge` | 3 / 2 |
| 32 | `MixinHelper27` | `ServerDifficultyPacketBridge` | 3 / 2 |
| 33 | `MixinHelper28` | `EntityMovePacketBridge` | 3 / 1 |
| 34 | `MixinHelper29` | `PlayerListItemPacketBridge` | 3 / 1 |
| 35 | `MixinHelper2_10` | `BedPartTypeBridge` | 4 / 4 |
| 36 | `MixinHelper2_11` | `StyledCharSink` | 2 / 2 |
| 37 | `MixinHelper2_12` | `TextColorFunctionHolder` | 1 / 0 |
| 38 | `MixinHelper2_13` | `FoodComponentBridge` | 2 / 2 |
| 39 | `MixinHelper2_2` | `TypeCoercionPipeline` | 1 / 0 |
| 40 | `MixinHelper2_3` | `InterpolatedValueProvider` | 1 / 1 |
| 41 | `MixinHelper2_4` | `RenderTypeProvider` | 1 / 0 |
| 44 | `MixinHelper2_7` | `NoOpLegacyPacketBridge` | 3 / 2 |

### Not listed in the cluster but the same problem

`Bridge12.java` also still contains the nested duplicates `Bridge12$Extension`
(= `LegacyRenderSystemBridge`, applied) and `Bridge12$Type`
(= `GpuBufferUsage`, applied) — the inventory only flagged the digit-stemmed
`Extension2`. All three nested declarations must be removed from `Bridge12.java`
in the repair pass; the top-level `Bridge12` file itself belongs to cluster 36.

## §3 Renames (4)

| # | old | new | why |
|---|---|---|---|
| 5 | `Bridge_67` | `BridgeMethodGenerator` | per-method bytecode generator, not a factory: `Bridge2_35.method3` does `new Bridge_67(targetMethod, Bridge2_24, Config, ClassProvider, targetClassNode).method1()` for every generated bridge method and takes the resulting `InsnList`; the body is produced by running the `BytecodeEmitter` (`Bridge_15`) pipeline until one value is left on the `TypeSignature` stack; the constructor owner `Bridge2_24` is the JSON bridge-config registry; errors are wrapped as `BridgeGenerationException` ("Exception occurred during bridge gen stage"). Alternative: `BridgeGenerator` (free too, but less precise). |
| 42 | `MixinHelper2_5` | `ItemCameraTransformsBridge` | duck interface for `net.minecraft.client.renderer.block.model.ItemCameraTransforms` with the single method `bridge$applyTransform(MixinHelper2$Type5)`; `MixinHelper2$Type5` is the vanilla `TransformType` (NONE / THIRD_PERSON_LEFT/RIGHT_HAND / FIRST_PERSON_LEFT/RIGHT_HAND / HEAD / GUI / GROUND / FIXED, with `legacyIndex()`); the only implementor is `legacy/mixin/ItemCameraTransformsMixin` (`@Mixin ItemCameraTransforms`). |
| 43 | `MixinHelper2_6` | `Matrix3fBridge` | the 3x3 companion of `MixinHelper_21` (Matrix4f, cluster 40): `@Annotation` variants for `com/mojang/math/Matrix3f` (<=1.16) and `org/joml/Matrix3f` (1.17+); `bridge$setDirect/setIdentity/getTransformX/Y/Z/copy/invert/transpose/mul/multiply/scale/getColumnMajorBuffer/toJoml`; 25 consumers, notably `TessellatorMixin.bridge$normal`, `DrawBufferBridge`, `BufferBuilderBridge`, `FluentTessellatorBridge`. |
| 45 | `MixinHelper2_8` | `GameOptionsBridge` | the GameSettings (`net.minecraft.client.settings.GameSettings` on 1.8, `Options` on 1.17+) duck: ~90 accessors — keybinds (`bridge$keyBindForward/Left/Back/Right/Jump/Attack/UseItem/PickBlock/Sprint/Sneak/PlayerList/TogglePerspective`, `bridge$getKeyBindings`), `bridge$setGamma`/`bridge$setGammaOverride`, `bridge$setForceUnicode`, `bridge$isHideGui`, `bridge$getChatScale`, `bridge$getRenderDistance`/`bridge$getSimulationDistance`, `bridge$getGuiScale`, `bridge$getVSync`/`bridge$setFrameRateLimit`, `bridge$getFov`, `bridge$getParticleStatus`/`bridge$getCloudStatus`, `bridge$getTextureFiltering`, up to `bridge$setChunkSectionFadeInTime$v1_21_11`; implemented by `legacy/mixin/GameSettingsMixin` + `GameSettingsMixin3`, returned by `MinecraftBridge`. |

All 4 new names were checked against a declaration index of `src/main/java`
(zero declarations anywhere) and `src/main/resources` (zero references). No
trailing digits, no lazy stems. Note that `MixinHelper2_6`'s legacy sibling
`MixinHelper_21` (Matrix4f) should take `Matrix4fBridge` in cluster 40 so the
family stays parallel.

## §4 Ambiguities / decisions worth recording

1. **`GameSettingsBridge` is taken.** The natural name for `MixinHelper2_8` is
   already declared in `com.moonsworth.lunar.legacy.wrapper.GameSettingsBridge`
   — a two-method stub (`method1()`, `method2(int)`) renamed from `Wrapper_2` by
   the legacy.wrapper map (`377399477`), with **zero callers**. The map therefore
   uses `GameOptionsBridge`; if the repair pass deletes the dead stub, prefer
   `GameSettingsBridge` instead (single-name change, nothing else references it).
2. **Jar coupling.** The stale jar `libs/lunar-renamed-classes.jar` references all
   four renamed FQNs by name (`Bridge_67` 41 refs, `MixinHelper2_5` 5,
   `MixinHelper2_6` 15, `MixinHelper2_8` 7, counted in the jar's
   `com/moonsworth/lunar/bridge` classes). They are interfaces/classes referenced
   from jar method descriptors, i.e. the same shape as the reverted
   `Bridge_15`/`BytecodeEmitter` case. If the jar-unification policy applies,
   keep the rows applied **and** add a `TextureBridge extends Bridge3_4`-style
   named shim, or revert the row — do not leave a half-renamed hierarchy.
3. **`Bridge_16` ↔ `PacketFactory` split brain.** The rescued rewind tree
   (`client/framework/feature/rewind/**`, 133 files in `tools/work/ecj-baseline.txt`)
   calls `Bridge.method59()` and assigns the result to `Bridge_16`, while
   `Bridge.method59()`/`method60` were already renamed to `PacketFactory`; the
   mismatch is part of the current ECJ baseline (e.g.
   `framework/feature/rewind/rewindhandlersNameplateCore/RewindhandlersNameplateCoreIterator.java`).
   That, plus the skipped `MixinHelper*` rows, is one repair: unify the rescued
   rewind tree onto `PacketFactory`/`*PacketBridge` (or keep `Bridge_16` as the
   shim) and delete/redirect the `legacy/wrapper/MixinHelper*` adapters.
4. **Dead-but-harmless pair.** `Bridge_64`/`TextureOverrideBridge` and
   `Bridge_70`/`VertexConsumerProvider` have zero references on both sides; keep
   whichever copy the repair pass keeps.
5. **`Bridge12$Extension2` row syntax.** The cluster lists it as
   `Bridge12.java / Extension2`. If the main agent ever wants to rename such a
   nested declaration, the aware applier needs the owner-qualified form:
   `com.moonsworth.lunar.bridge<TAB>Bridge12$Extension2<TAB><new><TAB>…<TAB>com/moonsworth/lunar/bridge/Bridge12.java`
   (the evidence must mention `Bridge12$Extension2` so the nested-first pass
   triggers). No such row is emitted here because the twin is already applied.
