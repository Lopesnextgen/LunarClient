# Cluster 10 notes — `com.moonsworth.lunar.bridge` (45 classes)

All 45 paths in `cluster-10.txt` exist in `src/main/java`; none were skipped.
43 classes got a new name (`classes-10.tsv`); `MixinHelper42` and
`MixinHelper43` are deliberately left unnamed (see "Needs follow-up").

## What this cluster actually is

These are bridge/API interfaces of Lunar's MoonBridge (`com.moonsworth.lunar.bridge`),
not mixin classes. The 45 lazy `MixinHelper*` names came from the restructure
pipeline (`remaining-renames.tsv`); they cover three different roles:

* **packet translators (22)** — one interface per Minecraft clientbound packet,
  paired with an implementation in `com.moonsworth.lunar.legacy.wrapper` that
  extends `MixinHelper_2` with `super(<PacketClass>.class, list)` and overrides
  a `Bridge3_21 methodN(...)` factory. `Bridge_16` exposes every one of them as
  a getter (`method5()`..`method34()`), and
  `client/framework/feature/rewind/rewindhandlersNameplateCore/*` consumes them.
* **duck/extension interfaces (17)** — `bridge$...` methods injected into a
  vanilla class by a `@Mixin`, or plumbing interfaces of the ichor framework.
* **ichor coercion machinery (4)** — `TypeCoercionPipeline` and the
  `CharacterCoercion` / `AdventureComponentCoercion` functors plus the
  `TypeCoercionPipeline` functor list.

Naming convention used: `<McTarget>PacketBridge` for packet translators,
`<McTarget>Bridge` for duck bridges, descriptive names elsewhere (matches
`classes-07.tsv`, e.g. `EntityItemBridge`, `BakedQuadBridge`, `RenderTypeBridge`).

## Packet translators (packet proven by the wrapper implementation)

| new name | old | packet (wrapper evidence) |
|---|---|---|
| `HeldItemChangePacketBridge` | MixinHelper14 | S09PacketHeldItemChange (`legacy/wrapper/MixinHelper17`) |
| `PlayerAbilitiesPacketBridge` | MixinHelper18 | S39PacketPlayerAbilities (`.../MixinHelper21`) |
| `SetExperiencePacketBridge` | MixinHelper20 | S1FPacketSetExperience (`.../MixinHelper23`) |
| `ChunkDataPacketBridge` | MixinHelper21 | S26PacketMapChunkBulk / S21PacketChunkData (`.../MixinHelper24`) |
| `OpenWindowPacketBridge` | MixinHelper17 | S2DPacketOpenWindow (`.../MixinHelper20`) |
| `TimeUpdatePacketBridge` | MixinHelper4 | S03PacketTimeUpdate (`.../MixinHelper7_2`) |
| `WorldBorderPacketBridge` | MixinHelper3_7 | S44PacketWorldBorder (`quarantine .../MixinHelper6`) |
| `EntityHeadLookPacketBridge` | MixinHelper30 | S19PacketEntityHeadLook (`.../MixinHelper3`) |
| `EntityMovePacketBridge` | MixinHelper28 | S14PacketEntity incl. S15/S17 inverse move (`quarantine .../MixinHelper31`) |
| `EffectPacketBridge` | MixinHelper23 | S28PacketEffect (`.../MixinHelper26`) |
| `ServerDifficultyPacketBridge` | MixinHelper27 | S41PacketServerDifficulty (`.../MixinHelper30`) |
| `WindowItemsPacketBridge` | MixinHelper25 | S30PacketWindowItems (`.../MixinHelper28`) |
| `SpawnObjectPacketBridge` | MixinHelper24 | S0EPacketSpawnObject -> S13PacketDestroyEntities (`.../MixinHelper27`) |
| `SpawnPaintingPacketBridge` | MixinHelper31 | S10PacketSpawnPainting -> S13PacketDestroyEntities (`.../MixinHelper4_3`) |
| `SpawnExperienceOrbPacketBridge` | MixinHelper5_3 | S11PacketSpawnExperienceOrb -> S13PacketDestroyEntities (`.../MixinHelper8`) |
| `BossInfoPacketBridge` | MixinHelper16 | SPacketUpdateBossInfo (`quarantine .../MixinHelper19`) |
| `PlayerListItemPacketBridge` | MixinHelper29 | S38PacketPlayerListItem (`quarantine .../MixinHelper2`) |
| `NoOpPacketBridge` | MixinHelper15 | impl super(null), returns null; no packet class in this build |
| `NoOpPacketListBridge` | MixinHelper19 | impl super(null), returns `List.of()` |
| `NoOpBatchPacketBridge` | MixinHelper26 | impl super(null), returns null |
| `NoOpNetHandlerPacketBridge` | MixinHelper22 | impl super(null), `method1(NetHandlerPlayClientBridge)` returns null |
| `NoOpLegacyPacketBridge` | MixinHelper2_7 | impl super(null), returns null; used on both <=1.8 and >=1.19 paths |

The five `NoOp*` rows are honest: the shipped wrappers have `super(null)` and
return `null`/`List.of()` (verified with `javap` on the legacy jar), and no
class anywhere in the multiver jars implements those interfaces with a packet
class. Their real packet targets are not recoverable from `lunar.jar`,
`legacy-…jar`, `genesis-…jar`, `forge-…jar` or `common-…jar` (constant-pool
scan for the interface internal names finds only the wrappers + rewind
consumers). They are flagged low-confidence below.

## Duck / plumbing interfaces

| new name | old | evidence |
|---|---|---|
| `EntityItemStateBridge` | MixinHelper2 | `bridge$getItemState()` (item render state `MixinHelper_14`) + `bridge$renderSeed()/renderCount()`; same surface as quarantined `EntityItemMixin`; stored by `EventRenderItemClump`, used by `ItemPhysics` |
| `BedPartTypeBridge` | MixinHelper2_10 | `@Mixin(BlockBed.EnumPartType)` `EnumPartTypeMixin.bridge$isFoot()`; `Hypixelbedwars`, `Bridge2Handler.bridge$getBedPartValue` |
| `StyledCharSink` | MixinHelper2_11 | `boolean accept(int index, Style style, int codePoint)` (Adventure `FormattedCharSink` analogue); implemented by `NickhiderImpl` + `LegacyFormattingSerializer` |
| `TextColorFunctionHolder` | MixinHelper2_12 | static set/clear/get of `Function<Float,Integer>`; used as a temporary text-colour override around `drawString` (quarantined `MixinHelper6_5.method14`) |
| `FoodComponentBridge` | MixinHelper2_13 | food data-component payload, `bridge$givesBadEffect()`; `DataComponentTypes.field22`, read by `FoodUtils` |
| `TypeCoercionPipeline` | MixinHelper2_2 | chains `MixinHelper_16` functors for params/returns, error text `"Failed to coerce param type %s to %s. Functors: %s"` |
| `InterpolatedValueProvider` | MixinHelper2_3 | `@Annotation2(min=17)` `T bridge$get(float)`; returned by `MixinHelper_12.bridge$getTransformation()` |
| `RenderTypeProvider` | MixinHelper2_4 | `@FunctionalInterface RenderTypeBridge get(float)`; registry `MixinHelper5_6` fields 41/42 (lunar_lines / lunar_lines_esp by width) |
| `DataComponentMapBridge` | MixinHelper2_9 | `Set<MixinHelper_9<?>> bridge$keySet()` + `boolean bridge$has(MixinHelper_9<?>)` |
| `DataComponentTypes` | MixinHelper3 | static registry of 57 `MixinHelper_9` component keys; read by 16 client files via `bridge$getDataComponent` |
| `LineSink` | MixinHelper3_2 | `accept(x1,y1,x2,y2)`; `MixinHelper_4.method32(width,color,sink)` draws a line through the sink |
| `CharacterCoercion` | MixinHelper3_3 | `MixinHelper_16` boxing/unboxing `Character` <-> `char` |
| `MatrixTransformProvider` | MixinHelper3_4 | two factories returning the 4x4 matrix bridge (`MixinHelper_21`) |
| `ArmorRenderState` | MixinHelper3_5 | value object `(hasColor, colour, resourcePath)` from `ItemStackMixin.bridge$getArmorState()`; checked by `Fov11` |
| `BakedQuadBridge` | MixinHelper3_6 | marker on `BakedQuad` (`BakedQuadMixin`); quad factories return/accept it |
| `DyedColorComponentBridge` | MixinHelper3_8 | data component `int bridge$getRgb()` (`DataComponentTypes.field26`); `SkyblockArmorDyeColor` |
| `AdventureComponentCoercion` | MixinHelper4_3 | `MixinHelper_16` functor Component <-> legacy chat (`TextBridge.asLegacyString/asAdventure`) |
| `ContainerItemsComponentBridge` | MixinHelper4_4 | data component `List<BridgeExtension_4> bridge$items()` (`DataComponentTypes.field32/53`); `ShulkerPreview` |
| `BakedModelBridge` | MixinHelper4_5 | `IBakedModel` duck implemented by `IBakedModelMixin`/`SimpleBakedModelMixin`/`WeightedBakedModelMixin`/`BuiltInModelMixin` |
| `LegacyFormattingSerializer` | MixinHelper22_2 | class implementing `StyledCharSink`, serialises Adventure `Style` + code points to legacy `§` string |
| `BlockPartFaceBridge` | MixinHelper5_2 | marker on `BlockPartFace` (`BlockPartFaceMixin`), used by FaceBakery/model bridges |

## Needs follow-up (2 classes, deliberately unnameable)

| class | why not named |
|---|---|
| `MixinHelper42` | `public interface MixinHelper42 extends MixinHelper4_5 {}` — empty BakedModel sub-interface. No members, no annotations, no implementors/usages in `src/main/java`, in the quarantine tree, in resources, or in any multiver jar (scanned constant pools of all 14 jars for its interface internal name: only self-reference). Nothing distinguishes it from `MixinHelper43`; a name like `BakedModelMarkerBridge` would be invention, not evidence. |
| `MixinHelper43` | same as above; if evidence appears later, likely `<Something>BakedModelBridge` for a specific model type (`SimpleBakedModel`/`WeightedBakedModel`/`MultipartBakedModel`/`BuiltInModel` all already implement `BakedModelBridge` directly in this build). |

## Applier warning — do NOT run `apply_class_renames.py` on this map

`tools/apply_class_renames.py` is unsafe for this cluster for two reasons:

1. **39 of 45 old names are declared in more than one package** (bridge + genesis
   + forge/lib + ichor + client + legacy/wrapper …). The tool skips those rows
   (`SKIP collision`), and with `--allow-collisions` it would substitute the
   simple name tree-wide — e.g. `MixinHelper2` is declared in 16 packages,
   `MixinHelper3` in 14, `MixinHelper4` in 8.
2. **Most of the old names that are declared only in
   `com.moonsworth.lunar.bridge` are still not safe for a global token
   rewrite.** The simple applier reports `applied=7 skipped=36` for this map
   (`MixinHelper19`, `MixinHelper2_10`, `MixinHelper2_13`, `MixinHelper3_5`,
   `MixinHelper3_6`, `MixinHelper4_4`, `MixinHelper4_5`); of those seven only
   `MixinHelper2_10` has no foreign standalone tokens. The other six have
   same-named, *different* types in `genesis/` that a global rewrite would
   mis-rename (declarations of those genesis types were renamed or quarantined
   by other clusters, so the references are already dangling):
   * `MixinHelper19` -> `genesis/MixinHelper19$1_2.java`, `genesis/MixinHelper5$3.java`
   * `MixinHelper2_13` -> `genesis/MixinHelper5242.java`
   * `MixinHelper3_5` -> `genesis/MixinHelper39$Data5.java`, `genesis/AbstractCollectionIterator3322.java`, `genesis/AbstractCollectionIterator5$Data.java`, `genesis/AbstractCollectionIterator53.java`, `genesis/CIterator3$Data2.java`, `genesis/MixinHelper19$Data26.java`, `genesis/MixinHelper39$Data2.java`
   * `MixinHelper3_6` -> `genesis/MixinHelper3$Data38$Data.java`
   * `MixinHelper4_4` -> `genesis/MixinHelper2_4.java`
   * `MixinHelper4_5` -> `genesis/MixinHelper4$Data14.java`

Recommended application: a **package-scoped rename** with these three rewrite
sets for each row:
* the declaring file `src/main/java/com/moonsworth/lunar/bridge/<Old>.java`;
* every file under `src/main/java/com/moonsworth/lunar/bridge/` that mentions the
  token unqualified (in-package references — almost all packet rows are
  referenced only by `bridge/Bridge_16.java`, `MixinHelper3` by 22 files,
  `BakedModelBridge` by 7, `EntityItemStateBridge` by 6);
* the external files that import or fully-qualify the bridge type, listed below
  (grep/`import com.moonsworth.lunar.bridge.<Old>`); these were resolved against
  the tree as of this writing.

External qualified references (package `com.moonsworth.lunar.bridge.<Old>`):

* `MixinHelper14` -> `legacy/wrapper/MixinHelper17.java`
* `MixinHelper15` -> `legacy/wrapper/MixinHelper18.java`
* `MixinHelper17` -> `legacy/wrapper/MixinHelper20.java`
* `MixinHelper18` -> `legacy/wrapper/MixinHelper21.java`
* `MixinHelper19` -> `legacy/wrapper/MixinHelper22.java`
* `MixinHelper2` -> `client/highlight/mixin/highlight/EventRenderItemClump.java`
* `MixinHelper20` -> `legacy/wrapper/MixinHelper23.java`
* `MixinHelper21` -> `legacy/wrapper/MixinHelper24.java`
* `MixinHelper22` -> `legacy/wrapper/MixinHelper25.java`
* `MixinHelper22_2` -> `client/framework/feature/nickhider/NickhiderImpl.java`
* `MixinHelper23` -> `legacy/wrapper/MixinHelper26.java`
* `MixinHelper24` -> `legacy/wrapper/MixinHelper27.java`
* `MixinHelper25` -> `legacy/wrapper/MixinHelper28.java`
* `MixinHelper26` -> `legacy/wrapper/MixinHelper29.java`
* `MixinHelper27` -> `legacy/wrapper/MixinHelper30.java`
* `MixinHelper2_10` -> `client/framework/feature/hypixelbedwars/mixin/Hypixelbedwars.java`, `legacy/mixin/EnumPartTypeMixin.java`, `legacy/wrapper/util/Bridge2Handler.java`
* `MixinHelper2_11` -> `client/framework/feature/nickhider/NickhiderImpl.java`
* `MixinHelper2_13` -> `client/util/FoodUtils.java`
* `MixinHelper2_7` -> `legacy/wrapper/MixinHelper5.java`
* `MixinHelper3` -> 16 client files (`Gui3`, `Holograms3_3`, `SkyblockFarmingHud`, `SkyblockPickonimbusDurability`, `SkyblockQuiverHud`, `SkyblockCreationDate`, `SkyblockDungeonQuality`, `SkyblockRarityBackground`, `SkyblockSmoothTeleport`, `EtherwarpPreview`, `ShulkerPreview`, `SkyblockArmorDyeColor`, `SkyblockHighlightTrashDungeonItems`, `TiersAsStackSize`, `Rewindhandlers`, `FoodUtils`)
* `MixinHelper30` -> `legacy/wrapper/MixinHelper3.java`
* `MixinHelper31` -> `legacy/wrapper/MixinHelper4_3.java`
* `MixinHelper3_6` -> `legacy/mixin/BakedQuadMixin.java`, `legacy/mixin/FaceBakeryMixin.java`
* `MixinHelper3_8` -> `client/mod/render/SkyblockArmorDyeColor.java`
* `MixinHelper4` -> `legacy/wrapper/MixinHelper7_2.java`
* `MixinHelper4_4` -> `client/mod/render/ShulkerPreview.java`
* `MixinHelper4_5` -> `Hypixelbedwars`, `EventRenderItemGlint`, `BlockRendererDispatcherMixin`, `BuiltInModelMixin`, `IBakedModelMixin`, `ItemModelMesherMixin`, `RenderItemMixin3`, `SimpleBakedModelMixin`, `WeightedBakedModelMixin`, `legacy/optifine/wrapper/Slayer3Renderer`, `v1_7/optifine/wrapper/Slayer6Renderer`, `v1_8/optifine/wrapper/Wrapper2`
* `MixinHelper5_2` -> `legacy/mixin/BlockPartFaceMixin.java`, `legacy/mixin/FaceBakeryMixin.java`
* `MixinHelper5_3` -> `legacy/wrapper/MixinHelper8.java`

Rows with **no external qualified references** (only `bridge/` in-package refs +
the quarantined wrapper that implements them): `MixinHelper16`, `MixinHelper28`,
`MixinHelper29`, `MixinHelper2_12`, `MixinHelper2_2`, `MixinHelper2_3`,
`MixinHelper2_4`, `MixinHelper2_9`, `MixinHelper3_2`, `MixinHelper3_3`,
`MixinHelper3_4`, `MixinHelper3_5`, `MixinHelper3_7`, `MixinHelper4_3`.

Quarantine caveat: the only implementors of `MixinHelper16`, `MixinHelper28`,
`MixinHelper29` (and users of `MixinHelper3_2` / `MixinHelper3_5` /
`MixinHelper2_12` / `MixinHelper2_13` in some versions) currently live under
`tools/work/quarantine/src/...` (`legacy/wrapper/MixinHelper19|31|2.java`,
`bridge/MixinHelper_4.java`, `bridge/MixinHelper5.java`,
`bridge/MixinHelper6_5.java`, `bridge/MixinHelper5_6.java`,
`legacy/mixin/ItemStackMixin.java`). They were not part of the rename scope,
so whoever un-quarantines them must apply the same names there.

## Collisions / dependency notes

* New simple names verified tree-wide (`class|interface|enum|record NewName`) —
  no hits, no duplicates inside the map, no overlap with `classes-07.tsv` /
  `classes-15.tsv`.
* The names of the classes this cluster *uses* belong to other clusters and may
  still change: `MixinHelper_19` (packet descriptor base), `Bridge3_21`
  (packet interface), `Bridge_12` (container bridge), `Bridge5Extension_5`
  (client player bridge), `MixinHelper_14` (item render state),
  `BridgeExtension_4` (ItemStack bridge), `MixinHelper_9` (data component key),
  `MixinHelper2_5` (ItemCameraTransforms duck, used by `BakedModelBridge`),
  `Bridge4_8` (TextureAtlasSprite), `Bridge2_42` (legacy Component bridge).
  Keep these consistent when their clusters land.
* `Bridge_16.java` (other cluster) references 30 of the renamed packet
  interfaces by simple name; it is the main in-package call site.

## Low-confidence rows

* The five `NoOp*PacketBridge` rows describe the *shipped* behaviour (null
  conversions), not the packets they were originally written for. If the real
  Lunar packet set is recovered later (e.g. a 1.19+ source jar), those five
  should be retargeted to their packet names. They are the only rows whose
  Minecraft target is unknown.
* `MatrixTransformProvider` (`MixinHelper3_4`) has no implementors/usages in any
  jar; named from its two matrix-returning signatures only.
* `TextColorFunctionHolder` (`MixinHelper2_12`) is only consumed by the
  quarantined `MixinHelper6_5`; name follows its only use (temporary text-colour
  function around drawString).
* `EntityItemStateBridge` (`MixinHelper2`) is the 1.8/1.7 module's
  EntityItem duck interface; the already-applied `EntityItemBridge`
  (`BridgeExtension52`) is the parallel interface from the legacy module.
