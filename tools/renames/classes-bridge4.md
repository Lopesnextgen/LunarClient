# Cluster 01 (bridge#part4) — `com.moonsworth.lunar.bridge` (35 classes)

Source revision: `tools/renames/cluster-01.txt` md5 `1451662097c8232c482dda693eae50c7`
(35 rows, all present in `src/main/java/com/moonsworth/lunar/bridge/`).
Map: `tools/renames/classes-bridge4.tsv` md5 `7212175f8d090df6d59548faa41e75b7`.

This is the **remainder of the bridge API bucket** after `classes-bridge3`
(part1), `classes-bridgehorsestats`, `classes-holograms-bridge` and the
`classes-07`/`09`/`10` batches. Unlike those, this cluster is *not* mostly
per-version MCP adapters: 24 of the 35 classes are **ichor/mixin plumbing and
Lunar's per-version packet-builder SPI** that the decompiler flattened into
`bridge`, and 11 are genuine bridge interfaces/values.

Three coherent sub-families turned up (confirmed by
`tools/work/mappings/normalize-renames.tsv`, which shows the decompiler's
original package grouping):

| original package | what it is | classes in this cluster |
|---|---|---|
| `bridge/mixinMore/mixin` | **type-coercion functors** (ichor's ASM type bridge) | `MixinHelper_16` (the SPI) + `MixinHelper5_4`, `MixinHelper6_4`, `MixinHelper7_3`, `MixinHelper8_2` |
| `bridge/mixinInternal/mixinMisc/mixin` | **rewind packet-builder SPI** | `MixinHelper_19` (base) + `MixinHelper7`, `MixinHelper6_2`, `MixinHelper8`, `MixinHelper9`, `MixinHelper13` |
| `bridge/mixinAlpha/mixin` | **item data-component values** | `MixinHelper_10`, `MixinHelper6`, `MixinHelper7_5`, `MixinHelper5_5` |

The already-named neighbours `TypeCoercionPipeline.java`, `CharacterCoercion.java`,
`AdventureComponentCoercion.java` (jar `MixinHelper2_2` / `MixinHelper3_3` /
`MixinHelper4_3`) fix the naming convention for the coercion family, and
`RewindPacketBuilder.java` + the concrete `legacy/wrapper/*PacketBuilder.java`
classes fix it for the packet family.

Reference material used: the tree sources, the `@Mixin` targets and implementors
(`legacy/mixin/*`, `legacy/wrapper/*`), `PacketFactory.java`, `DataComponentTypes.java`,
a bytecode sweep of `tools/work/staging/lunar-all-final.jar` (exact-boundary grep
for `com/moonsworth/lunar/bridge/<Name>` to find every implementor/reference),
and CFR decompiles of the implementors that are absent from the partial tree
(`legacy/wrapper/MixinHelper{9,10,11,12,16}`, `legacy/wrapper/MixinHelper`,
`legacy/mixin/BuilderMixin`, `client/util/click/Click2Base`).

## Renames (35 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Bridge_43` | `AuxCoreMarkerBridge` | empty marker, no implementor/reference; orig `mixinAux/mixinCore` |
| 2 | `Bridge_47` | `LightingMarkerBridge` | empty marker; orig `mixinShared/mixinExtra/mixinMain` = light-overlay family |
| 3 | `Bridge_48` | `SizeExtendableBridge` | `bridge$extendSize(int)`; dead; orig `mixinBeta` |
| 4 | `Bridge_49` | `InputConstants` | constants 1/3/22/24; orig `mixinGeneral` = input family (key enum `BridgeType_8`, key handler `Bridge2_39`) |
| 5 | `Bridge_51` | `SoundMarkerBridge` | empty marker; orig `mixinShared/mixinAlt` = sound family (`Bridge3_11` SoundHandler) |
| 6 | `Bridge_70` | `VertexConsumerProvider` | `@VersionGate(6,38) Bridge4_6 bridge$first()`; AnimationMesh outline pass |
| 7 | `MixinHelper13` | `ChunkUnloadPacketBuilder` | packet SPI; impl gates on `SPacketUnloadChunk` |
| 8 | `MixinHelper42` | `BakedModelMarkerBridge` | empty `BakedModelBridge` sub-interface; dead |
| 9 | `MixinHelper43` | `BakedModelVariantBridge` | empty `BakedModelBridge` sub-interface; dead |
| 10 | `MixinHelper5_4` | `RemappedTypeCoercion` | remaps class name via `MappingSet` + `checkcast` |
| 11 | `MixinHelper5_5` | `KineticWeaponPhases` | 4 ints returned by `bridge$getKineticWeaponPhases()` |
| 12 | `MixinHelper6` | `PropertyMapComponent` | `@FunctionalInterface PropertyMapBridge bridge$getPropertyMap()` data component |
| 13 | `MixinHelper6_2` | `SpawnPositionPacketBuilder` | builds `S05PacketSpawnPosition` from the World bridge |
| 14 | `MixinHelper6_3` | `RenderTypeLookup` | `@FunctionalInterface RenderTypeBridge get(ResourceLocation)` |
| 15 | `MixinHelper6_4` | `SubtypeCoercion` | returns the supertype of two types (`FatalIchorError6` hierarchy) |
| 16 | `MixinHelper6_6` | `ModelBuilderBridge` | `SimpleBakedModel.Builder` mixin (`BuilderMixin`) |
| 17 | `MixinHelper7` | `EntityPlayerPacketBuilder` | builds `S0CPacketSpawnPlayer` (+ inverse destroy) from `Bridge6_10` |
| 18 | `MixinHelper7_2` | `BakedQuadFactoryBridge` | `FaceBakery.makeBakedQuad` (`FaceBakeryMixin`) |
| 19 | `MixinHelper7_3` | `OptionalCoercion` | `Optional.orElse` / `ofNullable` unwrap/wrap |
| 20 | `MixinHelper7_5` | `LoreComponent` | `@FunctionalInterface List<Bridge3_25> bridge$getLines()` lore data component |
| 21 | `MixinHelper8` | `NoOpWorldPacketBuilder` | World→null fallback (`UnsupportedWorldPacketBuilder`) |
| 22 | `MixinHelper8_2` | `TypeConversionCoercion` | delegates to `TypeConversionUtil` static conversions |
| 23 | `MixinHelper9` | `WorldJoinPacketBuilder` | builds `S01PacketJoinGame` from World + `EntityPlayerSP` |
| 24 | `MixinHelper_10` | `CompoundTagComponent` | `CompoundTagBridge bridge$getData()` data component |
| 25 | `MixinHelper_12` | `EntityRenderState` | `bridge$getRenderState()`; holds an `InterpolatedValueProvider<Transformation>` |
| 26 | `MixinHelper_13` | `ClickableText` | `bridge$getHeight/getWidth(FontRenderer)`; `Click2Base`/`TextComponentFactory` |
| 27 | `MixinHelper_15` | `KeyBindingBridge` | `KeyBindingMixin` (`@Mixin KeyBinding`) |
| 28 | `MixinHelper_16` | `TypeCoercion` | the coercion-functor SPI (`method1`/`method2`) |
| 29 | `MixinHelper_18` | `Transformation` | render-state matrix/translation/rotation/scale |
| 30 | `MixinHelper_19` | `PacketBuilder` | base rewind packet-builder SPI |
| 31 | `MixinHelper_20` | `AuxMixinMarker` | empty marker; orig `mixinAux/mixin` |
| 32 | `MixinHelper_3` | `ParticleSpawner` | CRIT + SNOW_SHOVEL particle spawns (`legacy/wrapper/MixinHelper`) |
| 33 | `MixinHelper_5` | `InternalCoreMixinMarker` | empty marker; orig `mixinInternal/mixin/mixinCore` |
| 34 | `MixinHelper_6` | `RenderPlayerBridge` | player renderer (`RendererLivingEntity` mixins) |
| 35 | `MixinHelper_9` | `DataComponentType` | generic `T bridge$get(ItemStack)`; element of `DataComponentTypes` |

No nested types in this cluster, so no 5th `file` column is needed.

## Which names came from where

* **Real Lunar naming conventions already in the tree** pinned most of the
  coercion and packet classes: the coercion SPI is named after its
  `<X>Coercion` implementations, and the packet sub-interfaces after the
  concrete `<Packet>PacketBuilder` classes they are implemented by. Two names
  had to differ from the concrete class to stay unique tree-wide:
  `MixinHelper7` → `EntityPlayerPacketBuilder` (not `SpawnPlayerPacketBuilder`)
  and `MixinHelper9` → `WorldJoinPacketBuilder` (not `JoinGamePacketBuilder`).
* **`PacketBuilder`** is the base SPI (`method1()` = packet class,
  `method2()` = connection-state id, `method3/4()` = class match,
  `method5/6()` = packet decoration). `RewindPacketBuilder` implements it.
* **`TypeCoercion`** is the `MixinHelper3 method1(param,target,InsnList)` /
  `method2(target,param,InsnList)` functor driven by `TypeCoercionPipeline`.
* **`BakedQuadFactoryBridge`** / **`ModelBuilderBridge`** are the two facets
  returned by `horsestats/FaceBakeryBridge.method1()/method2()`.

## Applier status

`python3 tools/apply_class_renames.py --map tools/renames/classes-bridge4.tsv`
now reports `applied=0 skipped=35` because the orchestrator applied this map
tree-wide while it was being written (the bridge files were rewritten at the
same time; a concurrent `apply_class_renames_aware.py --map
classes-excavation.tsv` run was also in flight). Verification against the
applied tree:

* all 35 `new` `.java` files exist;
* zero remaining old tokens for any of the 35 old names;
* `classes-excavation.tsv` has no row overlapping this package, so the two
  applies cannot have collided.

## Caveats

* The six `Bridge_*` rows are the weak spot. Five are dead (`no implementor,
  no reference` in tree or `lunar-all-final.jar`), so their names are inferred
  from the decompiler's original package family rather than from behaviour:
  `AuxCoreMarkerBridge` / `AuxMixinMarker` / `InternalCoreMixinMarker` are
  package-derived markers (same convention as the applied `CoreMarkerBridge`,
  `SharedMarkerBridge`, `MiscMarkerBridge`), while `LightingMarkerBridge`,
  `SoundMarkerBridge` and `InputConstants` are named from their family's
  siblings (light overlay / sound engine / key-mouse input). If a future sweep
  finds the real upstream names in an older Lunar jar, these six are the rows
  to revisit.
* `MixinHelper42`/`MixinHelper43` are indistinguishable empty
  `BakedModelBridge` sub-interfaces with no refs; the `Marker`/`Variant`
  suffixes are placeholders for "one of the two dead IBakedModel duck
  interfaces", not a behavioural distinction.
* `Bridge_70.bridge$first()` semantics are inferred from the only caller
  (`mchorse.emoticons…AnimationMesh.renderModern`, which unwraps a `Bridge4_6`
  to it for the outline pass); `VertexConsumerProvider` reflects "a vertex
  consumer that yields its first delegate", not a confirmed upstream name.