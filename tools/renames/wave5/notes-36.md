# Cluster 36 — `com.moonsworth.lunar.bridge` (45 rows)

Source: `tools/renames/cluster-36.txt` (45 rows). **All 45 files exist**, none
skipped as missing and none are shaded-library code (this is Lunar's own
multi-version bridge API). Map: `tools/renames/wave5/classes-36.tsv`.

Dry run (`tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-36.tsv`):

```
[aware-renames] rows=45 skipped=0 files_touched=909 files_renamed=45 mode=dry-run
```

## What this package is

The bridge API Lunar's platform/mixin code uses to talk to each supported
Minecraft version: one interface per vanilla class/facet (`ItemStack`,
`RenderType`, `IResource`, `OpenGlHelper`, entity renderers, ...), a static
registry (`Bridge`, real name `BridgeManager` — see below) and the per-version
implementation (this cluster's `Bridge2`). Two kinds of classes dominate:

* real bridges implemented by version mixins/handlers (`bridge$get...` members),
* marker interfaces annotated with `@Annotation` version maps
  (`Annotation*` -> `net/minecraft/...` targets),
* plus the jar plumbing (`Annotation2/3/4` mapping annotations, `Bridge3`
  parameter slots, `Bridge4/6/7` mixin-family markers).

## Method

Every row has at least two evidence sources: the class source
(superclass/interfaces/members/string literals/annotations), the
`tools/mappings-snapshot/restructure/*` tables and the earlier bridge maps
(`classes-07`, `classes-bridge2/3/34`, `classes-finalbridge`). In addition the
real-named Lunar open source was used for external confirmation — the
`ReplayModMixins` repo calls the real API
(`BridgeManager.getRenderSystem()`, `BridgeManager.getOptiFine()`,
`BridgeManager.getMinecraftClient()`), which pins `Bridge12` = RenderSystem.

## Renames

`dup of` = an already-declared class in this tree that is the **same decompiled
class** (rescued copy vs renamer copy); those rows get a unique variant name so
the map applies (the applier skips a `new` name that is already declared).
`refs` = files referencing the name (own file excluded), measured with the
applier's import-aware resolution.

| old | new | dup of | refs old/twin | evidence (short) |
|---|---|---|---|---|
| `Annotation2` | `BridgeParameterMapping` | `BridgeParameterTarget` | 2/1 | param-level mapping annotation (`BridgeVersionTarget[] method1()`) read by `ParameterConversionEmitter` |
| `Annotation3` | `BridgeVersionMapping` | `BridgeVersionTarget` | 50/2 | per-version entry: `int version()` + target descriptors |
| `Annotation4` | `BridgeTargetMapping` | `BridgeTarget` | 49/1 | leaf descriptor strings (`net/minecraft/...`, `posX`, ...) |
| `AutoCloseableExtension2` | `AwtImageBridge` | `BufferedImageBridge` | 0/1 | wraps `java.awt.image.BufferedImage`, alpha-pixel checks |
| `AutoCloseableExtension3` | `DirectBufferImageBridge` | `DirectImageBridge` | 1/1 | direct native-order RGBA `ByteBuffer` (glGetTexImage readback) |
| `Bridge10` | `BlockFrostedIceBridge` | — | 1* | marker mapped to `BlockFrostedIce` (v5) / `FrostedIceBlock` (v6) |
| `Bridge11` | `LivingEntityRendererBridge` | `RendererLivingEntityBridge` | 1/3 | `canRenderName` hooks for `RendererLivingEntity` |
| `Bridge12` | `RenderSystemBridge` | — | 39/- | GL/RenderSystem facade; `Bridge.method42()` = real `BridgeManager.getRenderSystem()` |
| `Bridge13` | `ShaderUniformBinder` | `ShaderUniformsSetter` | 0/0 | single member `bridge$setShaderUniforms(ShaderManagerBridge)` |
| `Bridge14` | `IResourcePackBridge` | `ResourcePackBridge` | 13/14 | 1.8 `IResourcePack` facet (stream, pack name/desc/image) |
| `Bridge15` | `IResourceBridge` | `ResourceBridge` | 23/12 | 1.8 `IResource` facet (stream, metadata, pack name) |
| `Bridge16` | `BufferSourceDecorator` | `BufferSourceWrapper` | 1/0 | one-method decorator `unwrap() -> MultiBufferSourceBridge` |
| `Bridge17` | `BatchingBufferSourceBridge` | `MultiBufferSourceBridge` | 21/10 | `getBuffer(RenderType)` + sorted/unbatchable batch ordering |
| `Bridge18` | `OpenGlHelperBridge` | — | 7/- | framebuffer/lightmap caps, OpenGL30/33/44 + ARB flags, `glGetStringi`; `legacy/wrapper/Bridge18Handler` delegates to `OpenGlHelper` |
| `Bridge19` | `RenderStateLifecycleBridge` | `RenderStateShardBridge` | 5/3 | `bridge$setupState`/`bridge$clearState` render-state shards |
| `Bridge2` | `BridgeImplementation` | — | 16/- | version implementation (`enable()` + all vanilla factories); `Bridge.method1` logs "Setting Bridge Implementation to"; `Legacy2 implements Bridge2` |
| `Bridge20` | `RenderLayerBridge` | `RenderTypeBridge` | 83/27 | 1.8 `RenderLayer` facet (name/format/pipeline/uniforms/outline) |
| `Bridge21` | `CullingFrustumBridge` | `FrustumBridge` | 1/2 | `getPosition` + `isBoundingBoxInFrustum(AABB)` |
| `Bridge3` | `MethodParameter` | — (prior name reverted) | 22/- | bytecode-emitter parameter slot (`VarInsnNode` load, index, annotations) |
| `Bridge4` | `CoreMixinMarker` | `CoreMarkerBridge` | 1*/0 | empty mixin-family marker |
| `Bridge5` | `AdventureTextBridge` | `TextBridge` | 96/129 | Adventure `Component` text utilities |
| `Bridge6` | `SharedMixinMarker` | `SharedMarkerBridge` | 0/0 | empty mixin-family marker |
| `Bridge7` | `LightOverlayTrackerHolder` | `LightOverlayTrackerProvider` | 0/0 | `bridge$lightOverlayTracker()` accessor |
| `Bridge8` | `BlockTripWireMarker` | `BlockTripWireBridge` | 0/1 | marker of `legacy/mixin/BlockTripWireMixin` |
| `Bridge9` | `ServerListEntryMarker` | `ServerListEntryNormalBridge` | 0/1 | marker of `legacy/mixin/ServerListEntryNormalMixin` |
| `BridgeExtension2` | `FallingBlockEntityBridge` | `EntityFallingBlockBridge` | 0/3 | `bridge$getBlock()` on the falling-block entity |
| `BridgeExtension22` | `EntityArrowBridge` | — | 5/- | projectile marker mapped to `EntityArrow`/`AbstractArrow` |
| `BridgeExtension222` | `EntityPlayerBridge` | — | 88/- | client player bridge (skin layers, player type, cosmetics, emotes) |
| `BridgeExtension23` | `EntityFishHookBridge` | — | 6/- | projectile marker mapped to `EntityFishHook`/`FishingHook` |
| `BridgeExtension3` | `LocalPlayerMarker` | `LocalPlayerBridge` | 1/1 | empty marker used as an entity filter in rewind mixins |
| `BridgeExtension4` | `EntityTNTPrimedBridge` | — | 2/- | TNT bridge, `EntityTNTPrimed` (v0) / `PrimedTnt` (v6), fuse members |
| `BridgeExtension5` | `ItemFrameEntityBridge` | `EntityItemFrameBridge` | 4/4 | item frame (stack, rotation, map packet, hanging pos) |
| `BridgeExtension52` | `ItemEntityBridge` | `EntityItemBridge` | 12/12 | dropped item (stack, render count/seed, baked model) |
| `BridgeExtension522` | `EntityEnderPearlBridge` | — | 2/- | projectile marker mapped to `EntityEnderPearl`/`ThrownEnderpearl` |
| `BridgeExtension_10` | `BipedModelBridge` | `ModelBipedBridge` | 10/7 | biped model parts + `setSneak` |
| `BridgeExtension_11` | `PrimedTntBridge` | `SulfurCubeTntBridge` | 0/1 | min 39 TNT-family entity (fuse/max fuse); low confidence, see below |
| `BridgeExtension_12` | `EntityRenderStateHolder` | `EntityRenderStateProvider` | 1/1 | min 16/17 cached entity render state accessor |
| `BridgeExtension_13` | `GlStateControlBridge` | `GlStateManagerBridge` | 5/4 | empty GL-state facet (mutator + query) |
| `BridgeExtension_2` | `ArmorStandBridge` | `EntityArmorStandBridge` | 12/28 | armor-stand equipment slots + marker flag |
| `BridgeExtension_3` | `FireworkRocketBridge` | `EntityFireworkRocketBridge` | 0/2 | elytra boost rocket (`getAttachedToEntity`) |
| `BridgeExtension_4` | `ItemStackBridge` | — | 468/- | item stack (name/lore/enchants/model data/durability/tooltip/NBT/components) |
| `BridgeExtension_5` | `ChestContainerBridge` | `ContainerChestBridge` | 0/1 | chest container (`getLowerInventory`) |
| `BridgeExtension_6` | `GlObjectHandleBridge` | `GlHandleBridge` | 0/0 | functional GL handle (`lunar$getHandle`, `create(int)`) |
| `BridgeExtension_7` | `ClientPacketListenerBridge` | `NetHandlerPlayClientBridge` | 29/18 | client packet handler (queue, player info, quit, brand) |
| `BridgeExtension_8` | `ScalableRenderStateBridge` | `ScaledEntityRenderStateBridge` | 0/0 | render state + `getScale`/`setScale` |

`*` `Bridge10`/`Bridge4` have no real references: the counted hit is a
qualified reference to their unrelated `Data` companion classes (see below).

## Skipped rows

**None.** All 45 paths exist and every row applies cleanly (`skipped=0`).

## Duplicates: this is a merge problem, not a naming problem

34 of the 45 rows are the **same decompiled class as an already-named class** —
the 2026-09-15 rescue sweep restored the pre-rename copies next to the renamed
ones, and both are live (referenced from different call sites). Because the
applier refuses an already-declared `new` name, those rows received unique
variant names so the tree stays unique and non-lazy. The real fix is a merge
(delete one copy, repoint refs); if the merge deletes the renamer twin first,
the intended final names are the twin names (column `dup of`) — inverse rows are
not needed, the merge tool can map them.

Interesting asymmetries (merge should keep the *more referenced* name):

* `Bridge20`/`RenderLayerBridge` **83** vs `RenderTypeBridge` 27,
  `Bridge17` 21 vs 10, `Bridge15` 23 vs 12,
  `BridgeExtension_7` 29 vs 18 — the rescued copy is the one most code uses.
* `Bridge5` 96 vs `TextBridge` **129**, `BridgeExtension_2` 12 vs
  `EntityArmorStandBridge` **28** — the twin is the one most code uses.
* `Bridge13`, `Bridge4`, `Bridge6`, `Bridge7`, `Bridge8`, `Bridge9`,
  `BridgeExtension2`, `BridgeExtension3`, `BridgeExtension_5`,
  `BridgeExtension_6`, `BridgeExtension_8`, `BridgeExtension_3`,
  `BridgeExtension_11`, `AutoCloseableExtension2` are dead on one or both
  sides (0 refs) — drop them during the merge.

Two decompiler stem collisions worth recording (the jar re-used a class-name
stem for unrelated `$Data` classes):

* `Bridge4$Data` (= `SourceImage`, texture-atlas sprite source bitmap) is the
  nested `Data` of `Bridge4_8` (BakedModel), **not** of the `Bridge4` marker.
  `Bridge4_8.java` writes `Bridge4.Bridge4$Data`, which is already unresolvable
  (file is in the ECJ baseline); after row `Bridge4` it reads
  `CoreMixinMarker.Bridge4$Data` — the merge/fix pass should repoint it to
  `SourceImage`.
* `Bridge10$Data` (= `GlyphAtlasEntry`) belongs to `Bridge10_2` (font renderer),
  **not** to the `Bridge10` marker; same story (`BlockFrostedIceBridge.Bridge10$Data`).

## Ambiguities / low confidence

* `BridgeExtension_11` -> `PrimedTntBridge`: no `@Annotation` target on the
  class; the only consumer (`TntCountdown`) uses it as the MC >= 39 TNT with
  fuse, and the twin was named `SulfurCubeTntBridge` from the
  `"sulfur-cube-tnt-"` map key. The 1.8 sibling is `EntityTNTPrimedBridge`;
  if the upstream entity is really a sulfur-cube TNT variant, rename both to
  the same entity name in the merge pass.
* `Bridge2` -> `BridgeImplementation`: name comes from the runtime string
  `"[Bridge] Setting Bridge Implementation to "`. The real API class set through
  `BridgeManager` cannot be observed from the public repos (alternative
  considered: `PlatformBridge`).
* `Bridge18` -> `OpenGlHelperBridge`: the interface mixes framebuffer support,
  lightmap units and GL capability flags; `legacy/wrapper/Bridge18Handler`
  delegates every member to `net.minecraft.client.renderer.OpenGlHelper`, which
  makes the MCP name the best fit.
* `Bridge10`/`Bridge13`/`Bridge4`/`Bridge6`/`BridgeExtension_6`/`BridgeExtension_8`
  have no live implementors; their names rest on the annotation target
  (`Bridge10`) or a single member (`Bridge13`, `BridgeExtension_8`).

## Deliberately not renamed (not in this cluster)

* `com.moonsworth.lunar.bridge.Bridge` (no trailing digit, so no cluster lists
  it) is the **real Lunar `BridgeManager`**: the ReplayModMixins open source
  imports `com.moonsworth.lunar.bridge.BridgeManager` and calls
  `getRenderSystem()` / `getOptiFine()` / `getMinecraftClient()`, matching
  `Bridge.method42()` / `method5()` / `method9()`. The same reference also
  confirms `Bridge12` = RenderSystem (row above).
* `Bridge13_2` (`BlockFire`/`BaseFireBlock` marker), `Bridge11_2` (resource
  manager), `Bridge12_2/3/4`, `Bridge2*` family and the rest of the 240 bridge
  placeholders are clusters 37–41 / the other wave maps.
* `Annotation` (the outer version-map annotation) has no digit and is not in a
  cluster; note that its obfuscated **member** names (`OIRHIC...`,
  `IHCHII...`) are still unmapped, which is why `Bridge10` and friends are in
  the ECJ baseline (member-rename work, not class names).

## Verification

Applied to a scratch copy of `HEAD` (`/tmp/opencode/wt36`) with the file renames
performed, then compiled with the ECJ oracle:

```
before: 4413 failing files (untouched copy)
after:  4413 failing files
NEW:    exactly the 5 files that are themselves renamed and were already
        failing under their old names (BlockFrostedIceBridge/EntityArrowBridge/
        EntityEnderPearlBridge/EntityFishHookBridge/EntityTNTPrimedBridge,
        formerly Bridge10/BridgeExtension22/BridgeExtension522/
        BridgeExtension23/BridgeExtension4)
FIXED:  none (same 5 paths, no semantic change)
```

No new failures, no moved failures outside the renamed declaration files.
The 22-file difference between this 4413 set and the checked-in
`tools/work/ecj-baseline.txt` (4391) is present in the untouched copy as well
(classpath/`target` drift) and is unrelated to this map.
