# Cluster 01 — `com.moonsworth.lunar.bridge#part1` (45 classes)

Source revision: `tools/renames/cluster-01.txt` (45 rows, all present in
`src/main/java/com/moonsworth/lunar/bridge/`).
Map: `tools/renames/classes-bridge3.tsv` (45 rows).

This is the **bridge API bucket** of `com.moonsworth.lunar.bridge`: the
interfaces/enums that Lunar's platform code (mixins in `legacy/mixin`,
`v1_12/mixin`, the `bridgeimpl` and `legacy/wrapper` handlers) uses to talk to
the Minecraft classes of each supported version. It is not a single feature
package; each class is named by the `net.minecraft` target it adapts, or by the
role it plays in the bridge plumbing.

Reference material used: the tree sources, the implementor/handler classes
(`legacy/mixin/*`, `legacy/wrapper/*`, `bridgeimpl/mixin/MixinHelper`), the
`@Mixin` targets, and a bytecode sweep of
`tools/work/staging/lunar-all-final.jar` (a `grep -l` for the exact
`com/moonsworth/lunar/bridge/<Name>;` descriptor) to find every implementor and
to confirm which interfaces are dead in this build.

## Renames (45 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Annotation2` | `BridgeParameterTarget` | parameter-level mapping annotation (`Annotation3[] method1()`); `ParameterConversionEmitter` reads it off method params via `Type.getDescriptor(Annotation2.class)` |
| 2 | `Annotation3` | `BridgeVersionTarget` | per-version entry: `int version()` + `Annotation4[] method1()` |
| 3 | `Annotation4` | `BridgeTarget` | leaf target descriptor: `String[] value()` (`"posX"`, `{"position","x"}`) |
| 4 | `Bridge13` | `ShaderUniformsSetter` | `bridge$setShaderUniforms(Bridge6_8)`; dead in this build |
| 5 | `Bridge4` | `CoreMarkerBridge` | empty marker, no implementor/reference anywhere |
| 6 | `Bridge6` | `SharedMarkerBridge` | empty marker, no implementor/reference anywhere |
| 7 | `Bridge7` | `LightOverlayTrackerProvider` | `Bridge5_5 bridge$lightOverlayTracker()`; dead sibling of RenderChunk `Bridge9_7` |
| 8 | `BridgeExtension2` | `EntityFallingBlockBridge` | `EntityFallingBlockMixin` (@Mixin EntityFallingBlock) implements it; `bridge$getBlock()` |
| 9 | `BridgeExtension3` | `LocalPlayerBridge` | empty marker used only as `!(entity instanceof BridgeExtension3)` in `GuiRewindhandlers2` |
| 10 | `BridgeExtension_8` | `ScaledEntityRenderStateBridge` | extends `EntityRenderStateBridge`, adds `bridge$getScale`/`setScale` |
| 11 | `BridgeType_11` | `TextureFormat` | `RGBA8`/`RED8`/`DEPTH32` + `pixelSize()`; glTexImage2D internal format |
| 12 | `BridgeType_12` | `DepthTestFunction` | GL depth-test ids 512..519; implements `GlEnum` |
| 13 | `BridgeType_13` | `ClickTypeBridge` | `PICKUP`..`PICKUP_ALL` = `net.minecraft.inventory.ClickType` |
| 14 | `BridgeType_14` | `BufferUsage` | GL `*_DRAW/READ/COPY` 35040..35050 = buffer usage hint |
| 15 | `BridgeType_16` | `BufferTarget` | `STORAGE_BUFFER`/`UNIFORM_BUFFER` for compute buffer bindings |
| 16 | `BridgeType_17` | `BufferMode` | `IMMEDIATE`/`BATCHED` for `VertexConsumer.method17` |
| 17 | `BridgeType_2` | `ResourcePackFormat` | `v1_6$1_8`..`v1_17` = resource-pack format version |
| 18 | `BridgeType_3` | `AttenuationTypeBridge` | `NONE`/`LINEAR` = `ISound.AttenuationType` |
| 19 | `BridgeType_4` | `PacketDirectionBridge` | `SERVERBOUND`/`CLIENTBOUND` packet flow |
| 20 | `BridgeType_5` | `ItemTransformTypeBridge` | `ItemCameraTransforms.TransformType` |
| 21 | `BridgeType_7` | `TransparencyType` | `RenderTypeBridge.bridge$getTransparencyType()` |
| 22 | `BridgeType_9` | `PixelFormat` | `ABGR`/`ARGB` channel order of a texture image |
| 23 | `Bridge_11` | `GlFenceSyncBridge` | `glFenceSync`/`glDeleteSync`/`glClientWaitSync` impl by `BridgeHandler` |
| 24 | `Bridge_12` | `ContainerBridge` | marker; `ContainerMixin` (@Mixin Container) implements it |
| 25 | `Bridge_13` | `MixinTargetMember` | ichor target-member node walked by `PathEmitter` |
| 26 | `Bridge_14` | `EntitySkeletonBridge` | `EntitySkeletonMixin`; `bridge$isWitherSkeleton()` |
| 27 | `Bridge_15` | `BytecodeEmitter` | emitter SPI implemented by `NewInstanceEmitter`/`FieldGetEmitter`/… |
| 28 | `Bridge_16` | `PacketFactory` | `Bridge.method59()` singleton building all `*PacketBridge` packets |
| 29 | `Bridge_17` | `BlockEntityRendererBridge` | `bridge$getBlockEntityRenderer`/`bridge$shouldRender` |
| 30 | `Bridge_18` | `GuiChestBridge` | marker; `GuiChestMixin` (@Mixin GuiChest) implements it |
| 31 | `Bridge_19` | `LevelRendererVisibilityBridge` | min 39: `isVisible`/`isBlockVisible`/`getPartialTicks` |
| 32 | `Bridge_2` | `CustomBufferSourceHolder` | min 33: get/set `MultiBufferSourceBridge` |
| 33 | `Bridge_20` | `NetworkManagerBridge` | `NetworkManagerMixin` (@Mixin NetworkManager) implements it |
| 34 | `Bridge_21` | `MovementInputBridge` | `MovementInputMixin` (@Mixin MovementInput) implements it |
| 35 | `Bridge_22` | `EntityRegistryBridge` | Forge entity registry impl by `bridgeimpl/mixin/MixinHelper` |
| 36 | `Bridge_23` | `GlEnum` | `getGlId()`/`isSupported()` base of the GL enums |
| 37 | `Bridge_24` | `InventoryPlayerBridge` | = `net.minecraft.entity.player.InventoryPlayer` |
| 38 | `Bridge_25` | `MiscMarkerBridge` | empty marker, no implementor/reference anywhere |
| 39 | `Bridge_26` | `INetHandlerBridge` | `PacketMixin.bridge$handle` casts to `INetHandler` |
| 40 | `Bridge_27` | `VertexFormats` | 12 static `VertexFormatBridge` constants |
| 41 | `Bridge_28` | `BufferBuilderBridge` | vertex-buffer builder (`bridge$begin`/`color`/`endVertex`/`end`) |
| 42 | `Bridge_29` | `ComputeProgramBridge` | compute-shader program (gecko-compute) |
| 43 | `Bridge_3` | `FakeTickBridge` | `bridge$getFakeTicks`/`setFakeTicks` (rewind) |
| 44 | `Bridge_30` | `NetworkPlayerInfoBridge` | `NetworkPlayerInfoMixin` (@Mixin NetworkPlayerInfo) implements it |
| 45 | `Bridge_32` | `PropertyMapBridge` | `bridge$get(String) -> List<Bridge2_37>` item property map |

## Applier dry run, and the 5 collision rows

`python3 tools/apply_class_renames.py --map tools/renames/classes-bridge3.tsv`:

```
applied=40 skipped=5 files_touched=177 files_renamed=40 mode=dry-run
```

40 rows apply cleanly. The 5 skips are **old-name collisions across packages**,
not map errors — the current applier ignores any old name declared in more than
one package:

| skipped old name | also declared in |
|---|---|
| `Annotation2` | `client.guiRewindhandlers`, `ichor.util` |
| `Annotation3` | `annotations`, `client.guiRewindhandlers`, `ichor.util` |
| `Annotation4` | `ichor.util` |
| `BridgeExtension2` | `client.util.click` |
| `BridgeExtension3` | `client.util.click` |

These 5 need the planned package/import-aware rewriter (same follow-up as
`classes-07.md`, `classes-12.md`, `classes-15.md`). The bridge declarations are
the ones under `com.moonsworth.lunar.bridge`, and the new names are all
tree-wide unique, so a declaration-scoped pass can apply them safely.

## Caveats / follow-ups

* **Dead interfaces.** `Bridge4`, `Bridge6`, `Bridge25` are genuinely empty
  (`javap -v` shows only `java/lang/Object` as super and no members) and have
  no implementor or reference in the tree or in `lunar-all-final.jar`; they are
  named as markers because no target is recoverable from this build. `Bridge7`,
  `Bridge13`, `Bridge_2`, `Bridge_3`, `Bridge_19` likewise have no references in
  this build and are named from their members/annotations.
* **`BridgeType_12` vs the applied `DepthFunction`.** `classes-07` already named
  `BridgeType_10` → `DepthFunction`; `BridgeType_12` is a second, independent
  depth-test enum (different constant names, implements `GlEnum`), so it is
  `DepthTestFunction` to avoid a collision.
* **`BridgeExtension3`** is a marker with no shipped implementor; the
  `LocalPlayerBridge` name is inferred from the only use site (the rewind
  nameplate entity set deliberately excludes it).
* Names added by this cluster were checked against every declared type in
  `src/main/java` (`grep -rE "\b(class|interface|enum|record) NewName\b"`); none
  collided.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-bridge3.tsv
# → applied=40 skipped=5 files_touched=177 files_renamed=40 mode=dry-run
```