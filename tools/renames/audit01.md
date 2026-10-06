# Audit cluster 01 — `com.moonsworth.lunar.{altmanager, annotations, bridge, bridge.fog, bridge.glintcolorizer, bridge.glintcolorizer.mixin, bridge.hitbox, bridge.hitcolor}`

Slice: `tools/renames/audit-cluster-01.txt` (8 packages). This pass looks for
**plausible-but-wrong** names, not lazy placeholders (`BridgeN`, `MixinHelperN`,
`DataN`, `$`-flattened nested types) which the earlier waves own.

Maps produced:

* `tools/renames/classes-audit01.tsv` — 13 class renames.
* `tools/renames/packages-audit01.tsv` — 5 package moves.

Provenance used throughout: the first-pass `tools/work/staging/decompiled` tree,
`tools/mappings-snapshot/restructure/remaining-renames.tsv` (which shows the
obfuscated package segment each class came from), the implementors in
`legacy/mixin` / `legacy/wrapper`, and the mixin/`@Mixin` targets.

---

## `com.moonsworth.lunar.altmanager` (7 classes) — CORRECT

This is a small, hand-written alt-manager mod, not decompiled Lunar code:
`AltManagerMod` (`@Mod(modid="lunaraltmanager")`), `AltStore`
(`lunar/altmanager.json`), `AltAccount`, `GuiAltManager` (+ nested `AltList`),
`GuiDeviceLogin`, `MicrosoftAuth` (Microsoft/Xbox device-code + refresh-token
flow, AuthMe client list), `SessionSwitcher` (reflective `Minecraft.session`
swap). Every name matches its behaviour; the package name is accurate.

## `com.moonsworth.lunar.annotations` (3 classes) — BROKEN / mostly lazy

Only `Annotation`, `Annotation4$Type2` and `SourceMarker` remain on disk;
`Annotation2`, `Annotation4` and `Annotation4$Type` are in
`tools/work/quarantine/src` (they referenced the flattened `Annotation4.Type`
and no longer compile). The set is a **source-retention thread-affinity
annotation family**: `Annotation` is the repeatable container
(`Annotation2[] method1()`), `Annotation2` is `@Repeatable(Annotation.class)`
and carries `Annotation4$Type` (`THREAD_MAIN`, message
`"$NAME$ must be called from the main thread"`), `Annotation3` → `SourceMarker`
(already renamed).

* `Annotation` → `ThreadRestrictions` (container). **Low confidence** because
  the package is half-quarantined; a full rename should be done together with
  the quarantine restore.
* `Annotation4$Type2` is a lazy `$`+digit name → out of scope (lazy).

## `com.moonsworth.lunar.bridge` (486 classes) — bridge API bucket

This is Lunar's multiversion bridge API (interfaces/enums implemented by the
`legacy/mixin` duck mixins and the `legacy/wrapper` platform classes). The
earlier `classes-bridge3/4`, `classes-bridgehorsestats`, `classes-07/09/10`
batches already named ~190 of them. The remaining lazy `BridgeN*` /
`MixinHelperN*` / `$`-flattened names are out of scope.

Plausible-but-wrong names found (all collide with a class of the same simple
name elsewhere, or are named after a lazy base):

* `Annotation` → `BridgeMemberTarget` — the RUNTIME member-level mapping
  annotation (`value()` + `BridgeVersionTarget[]`), sibling of the applied
  `BridgeParameterTarget`/`BridgeVersionTarget`/`BridgeTarget`. Its simple name
  also collides with `annotations.Annotation`.
* `MExtension` → `LayerRendererBridge` — the `net.minecraft…layers.LayerRenderer`
  duck (implemented by the cosmetic layers `Pkg2_2`/`Pkg3_2`, wrapped by
  `Bridge4Handler implements LayerRenderer`).
* `SExtension` → `RendererLivingEntityLayerBridge` — the `RendererLivingEntity`
  layer/render-name duck implemented by `RendererLivingEntityMixin`. Kept
  distinct from the existing `bridge.RendererLivingEntityBridge`, which is the
  brightness/`canRenderName` wrapper (`RendererLivingEntityImpl`).
* `Heightlimit` → `ViewFrustumBridge` — sole member
  `List<Bridge9_7> bridge$getRenderChunks()` (Bridge9_7 = RenderChunk), i.e. the
  `ViewFrustum` chunk-list holder; `LightOverlay` reads it through
  `Bridge.method7()`. Name came from the obfuscated `heightlimit` segment.
* `Itemcounter4Extension` → `WorldBorderExtensionBridge` — the world-border
  bridge implemented by `WorldBorderImpl`/`LegacyServerBorder`; named after the
  lazy `bridge.itemcounter.Itemcounter4_3`.
* `Itemcounter6Extension` → `WorldBridgeExtension` — the `World` bridge
  extension (scoreboard/weather/light/time/map data); named after the lazy
  `bridge.itemcounter.Itemcounter6`.
* `Fov`, `Freelook` — **uncertain, not renamed.** Both are empty marker
  interfaces named after obfuscated package segments; they have **no
  implementors or references** in the current tree (the `implements Fov` hit is
  `client.fov.Fov`, a different type). Their real role cannot be recovered from
  the partial tree.
* `Fps` exists only as a stale `bridge/Fps.class` in `lunar-all-final.jar`; no
  `bridge/Fps.java` in `src` (already dropped).

Everything else sampled (`FramebufferBuilder`, `ResourceBridge`,
`BufferSourceWrapper`, `DisplayListBridge`, `LightTextureBridge`,
`DirectImageBridge`, `MarkerPositionBridge`, `TiersBridge`,
`ProtectionEnchantmentsBridge`, `SulfurCubeTntBridge`, `ShadeModel`,
`DepthFunction`, `PixelFormat`, `TextureFormat`, `LayerRenderer`-adjacent
classes, the `*PacketBridge`/`*PacketBuilder` family, …) matches its behaviour
and is left alone.

## `com.moonsworth.lunar.bridge.fog` (3 classes) — WRONG PACKAGE + one wrong name

Obfuscated segment `fog`; it is **not** the fog feature. Contents are the
**potion** bridges:

* `Fog` → `PotionEffectBridge` — implemented by `PotionEffectMixin`
  (`@Mixin PotionEffect`); `bridge$getPotionID/getPotion/getDuration/
  getMaxDuration/getAmplifier/getEffectName/getIsAmbient/getColor/
  renderEffectIcon`.
* `PotionBridge`, `PotionRegistryBridge` are already correct.
* package `bridge.fog` → `bridge.potion`.

## `com.moonsworth.lunar.bridge.glintcolorizer` (1) + `.mixin` (2) — WRONG PACKAGE

Obfuscated segment `glintcolorizer`; no glint/colour code here.

* `bridge.glintcolorizer.Glintcolorizer` → `IntegratedServerBridge` —
  `Bridge5_12.bridge$getIntegratedServer()` returns it; members are the
  integrated-server LAN/cheats/difficulty/world-directory API.
  Package → `bridge.server`.
* `bridge.glintcolorizer.mixin.Glintcolorizer` → `ResourcePackParentBridge`
  (`bridge$getParentDirPath()/bridge$setParentDir(Path)`), sibling
  `PackFiltersBridge` (`bridge$getPackFilters/setPackFilters`).
  Package → `bridge.resourcepack`. Both are dead in this partial tree, so the
  target (`AbstractResourcePack`/`FileResourcePack` vs `ResourcePackRepository`)
  is inferred from the member names — medium confidence.

## `com.moonsworth.lunar.bridge.hitbox` (2) — WRONG PACKAGE + one wrong name

Obfuscated segment `hitbox`; it is the **statistics** subsystem.

* `Hitbox` → `StatisticsFileBridge` — implemented by `StatFileWriterMixin`
  (`@Mixin StatFileWriter`) and `StatisticsFileMixin` (`@Mixin StatisticsFile`);
  `bridge$setValueFromPacket/increment/recordStat`.
* `StatBaseBridge` is correct.
* package `bridge.hitbox` → `bridge.stats`.

## `com.moonsworth.lunar.bridge.hitcolor` (3) — WRONG PACKAGE + two wrong names

Obfuscated segment `hitcolor`; it is the **tile-entity** bridge bucket.

* `Hitcolor` → `BeamSegmentBridge` — implemented by `BeamSegmentMixin`
  (`@Mixin TileEntityBeacon.BeamSegment`); used as `List<Hitcolor>` in
  `bridge$renderBeacon`.
* `HitcolorExtension` → `BlockEntityBridge` — implemented by `TileEntityMixin`
  (`@Mixin TileEntity`), extends `TurboBlockBridge`; the block-entity handle
  passed to `BlockEntityRendererBridge`. (Named `BlockEntityBridge` because
  `bridge.itemcounter.TileEntityBridge` already exists.)
* `TileEntityChestBridge` is correct.
* package `bridge.hitcolor` → `bridge.tileentity`.

---

## Verification

All 13 new simple names were checked for tree-wide uniqueness with
`grep -rE "(class|interface|enum|record|@interface) <Name>\b" src/main/java`
(0 hits each), and none of the 5 target packages exists yet. No sources were
edited; the maps are for `tools/apply_class_renames.py` (package moves need the
package-aware applier).