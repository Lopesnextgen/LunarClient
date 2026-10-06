# Cluster 10 — mixed package grab-bag (45 rows)

Source: `tools/renames/cluster-10.txt`. Map: `tools/renames/classes-markersnameplate.tsv`.
Cluster header from `clusters.tsv`:

```
com.moonsworth.lunar.client.markers.nameplate.mixin
com.moonsworth.lunar.client.util.alert
com.moonsworth.lunar.legacy
com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin
com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin
com.moonsworth.lunar.client.framework.feature.pkg
com.moonsworth.lunar.client.framework.feature.pkg.mixin
```

This is seven unrelated packages; the lazy-class inventory bucketed them together.
Three of them are outright mis-named by the decompiler, so the `evidence`
column records the accuracy fixes:

## What the packages actually are

### 1. `markers.nameplate.mixin` — the LComponent GUI framework callbacks
The package name is a leftover; the callback owner (the LComponent base, renamed
`DriverComponent` by the parallel `markers` batch) serialises itself as
`LComponent.LComponentBuilder(screen=…, onTick=…, onClose=…, onFramebufferSize=…,
onWindowSize=…, onWindowFocus=…, onKey=…, onChar=…, onCursorPos=…, onMouseButton=…,
onScroll=…, onDrop=…)`. The eight `@FunctionalInterface` files in `mixin/` are the
typed slots of that builder, dispatched by `Highlight3Iterator` (the screen). They are
**not** mixins and have nothing to do with nameplates, so they are named
`Component<Event>Callback` (the `Component` prefix refers to the LComponent base).
(`Nameplate`/onChar is not in this cluster.)

### 2. `util.alert` — **not** the toast framework, it is the texture/shader layer
* `Alert` (not in cluster) is a texture-residency timer (60s/15s decay) used by `AsyncResourceManager`.
* `Alert2` slices/reescales cloak (cape) textures for the legacy `SimpleTextureMixin`.
* `Alert3` resolves and loads a cosmetic texture.
* `Alert5` is the animated-texture interface (`SpriteAnimationBridge`) consumed by `HologramsIterator`.
* `Alert6` is a shader definition consumed by `ShaderPreprocessor`, `ShaderStateHelper` and `FovIterator`.
* `GuiRewindhandlersHandler2` is the OptiFine **connected-textures** (CTM) listener.

### 3. `legacy` — loose MCP mixin support classes
`HostedWorldListEntry` (server-list entry), `MatrixStackHolder`, the
`ModelRendererAttachable` interface, the first-person item/hand render helpers and
the `BufferedImageTexture` / `ToolMaterialHolder` bridge shims.

### 4. `fishing.mixin` — Hoppity's Hunt + Crystal Hollows metal detector
The `mod.fishing` package is a SkyBlock grab-bag (see `APPLIED.md`); this sub-package
holds the Chocolate Factory / Hoppity rabbit models (`ChocolateEggLocations`,
`RabbitCollectionStore`, `RabbitCollection`, `RabbitLevel`, `ChocolateRabbit`) and the
`MetalDetectorTreasureType` enum.

### 5. `dungeonwaypoints.mixin` — BetterMap dungeon waypoints
The `DungeonWaypoint*` models (floor/room containers, style, preset) and the two
`Gui2ExtensionN` enums for render mode and visibility.

### 6. `feature.pkg` + `feature.pkg.mixin` — **3D Skin Layers** (tr7zw)
A vendored port of the *3D Skin Layers* mod. Provenance is recovered from the
`toString` string constants in the runtime jar: `"SolidPixelWrapper.Position"`,
`"SolidPixelWrapper.Dimensions"`, `"SolidPixelWrapper.UV"`,
`"SolidPixelWrapper.VoxelPosition"`, `"BodyLayerFeatureRenderer.Layer"` and
`"pollFuture SkinDataCache"`. Real upstream names are used where they survive in
those strings/tables; the rest are named by role:
* root: `SkinDataCache`, `SkinLayerRenderer`, `SkinLayerCache`, `SkinLayerFactory`,
  `SkullSkinLayerRenderer`, `LegacySkinLayerRenderer`.
* mixin: `CustomizableCube` (real upstream name) and `SolidPixelWrapper` (real
  upstream name), with nested `VoxelDimensions`/`TextureUV`/`VoxelPosition`/
  `Polygon`. The siblings `Pkg3_2` (verified `BodyLayerFeatureRenderer` from its
  nested `Layer` toString) and `Pkg2_2` are outside this cluster.

## Renames (45 rows)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | `…markers.nameplate.mixin` | `Nameplate2` | `ComponentFocusCallback` | LComponent `onWindowFocus` slot (`DriverComponent.field10` → `method15(boolean)`) |
| 2 | `…markers.nameplate.mixin` | `Nameplate3` | `ComponentDropCallback` | LComponent `onDrop` slot (`field16` → `method21(List<Path>)`) |
| 3 | `…markers.nameplate.mixin` | `Nameplate4` | `ComponentKeyCallback` | LComponent `onKey` slot (`field11` → `method16`) |
| 4 | `…markers.nameplate.mixin` | `Nameplate5` | `ComponentCursorCallback` | LComponent `onCursorPos` slot (`field13` → `method18`) |
| 5 | `…markers.nameplate.mixin` | `Nameplate6` | `ComponentScrollCallback` | LComponent `onScroll` slot (`field15` → `method20`) |
| 6 | `…markers.nameplate.mixin` | `Nameplate7` | `ComponentResizeCallback` | LComponent `onFramebufferSize`/`onWindowSize` slots (`field8`/`field9`) |
| 7 | `…markers.nameplate.mixin` | `Nameplate8` | `ComponentMouseButtonCallback` | LComponent `onMouseButton` slot (`field14` → `method19`) |
| 8 | `…util.alert` | `Alert2` | `CloakTextureSlicer` | cloak texture slice/scale for `SimpleTextureMixin.lunar$sliceCloakTexture_*` |
| 9 | `…util.alert` | `Alert3` | `CosmeticTextureLoader` | resolves + loads a cosmetic's texture identifier |
| 10 | `…util.alert` | `Alert5` | `AnimatedTexture` | `method7()` = `SpriteAnimationBridge`, `method12()` = restart (used in `HologramsIterator`) |
| 11 | `…util.alert` | `Alert6` | `ShaderDefinition` | shader def with `LunarModelViewMat`/etc. uniforms (`ShaderPreprocessor`, `ShaderStateHelper`, `FovIterator`) |
| 12 | `…util.alert` | `Data5` | `TileVariants` | `GuiRewindhandlersHandler2$Data5` OptiFine tile-name sets |
| 13 | `…util.alert` | `Data6` | `ConnectedTexture` | `GuiRewindhandlersHandler2$Data6` tile→(SlayerType,ctmIndex) |
| 14 | `…util.alert` | `GuiRewindhandlersHandler2` | `ConnectedTexturesListener` | OptiFine CTM DynamicListener |
| 15 | `…legacy` | `GuiListExtendedImpl2` | `HostedWorldListEntry` | `IGuiListEntry` for a `HostedWorld` in `GuiMultiplayerBlocklistMixin` |
| 16 | `…legacy` | `MixinCore2` | `MatrixStackHolder` | static `MatrixStackBridge` holder |
| 17 | `…legacy` | `MixinCore4` | `ModelRendererAttachable` | `lunar$attach(ModelRenderer)` implemented by `ModelRendererAttachMixin` |
| 18 | `…legacy` | `MixinHelper2` | `FirstPersonItemRenderer` | static held-item/first-person render helper |
| 19 | `…legacy` | `MixinHelper3` | `FirstPersonHandTransform` | first-person hand GL transform |
| 20 | `…legacy` | `MixinHelper4` | `BufferedImageTexture` | `Click.Extension` backed by a `BufferedImage` |
| 21 | `…legacy` | `MixinHelper_2` | `ToolMaterialHolder` | `lunar$getMaterial(): Bridge4_5` implemented by `ItemToolMixin` |
| 22 | `…fishing.mixin` | `Fishing2` | `ChocolateEggLocations` | Hoppity's Hunt egg locations (`" Egg <msg>!"`) |
| 23 | `…fishing.mixin` | `Fishing3` | `RabbitCollectionStore` | loads/saves `skyblock_rabbit_collection.json` |
| 24 | `…fishing.mixin` | `Fishing4` | `RabbitCollection` | rabbit→entry map + per-rarity summary lines |
| 25 | `…fishing.mixin` | `Fishing5` | `RabbitLevel` | parsed rabbit level (arabic/roman) |
| 26 | `…fishing.mixin` | `Fishing7` | `ChocolateRabbit` | Chocolate Factory shop rabbit (cost/rate) |
| 27 | `…fishing.mixin` | `FishingType2` | `MetalDetectorTreasureType` | GOLD/DIAMOND/EMERALD/LAPIS scanner tiers |
| 28 | `…dungeonwaypoints.mixin` | `Dungeonwaypoints2` | `DungeonFloorWaypoints` | floor + waypoint list, `floor-<n>.json` |
| 29 | `…dungeonwaypoints.mixin` | `Dungeonwaypoints3` | `DungeonRoomWaypoints` | roomId/communityName + waypoints, `<room>.json` |
| 30 | `…dungeonwaypoints.mixin` | `Dungeonwaypoints4` | `WaypointStyle` | render mode/when/colours/offset/size |
| 31 | `…dungeonwaypoints.mixin` | `Dungeonwaypoints5` | `WaypointPreset` | named preset (style + boxMode) |
| 32 | `…dungeonwaypoints.mixin` | `Gui2Extension2` | `WaypointRenderMode` | WIREFRAME/FILL/BOTH |
| 33 | `…dungeonwaypoints.mixin` | `Gui2Extension3` | `WaypointVisibility` | CLEAR/SECRETS/BOTH |
| 34 | `…feature.pkg` | `Pkg2` | `SkinDataCache` | generic skin-texture cache ("pollFuture SkinDataCache") |
| 35 | `…feature.pkg` | `Pkg3` | `SkinLayerRenderer` | static arm/body 3D skin-layer renderer |
| 36 | `…feature.pkg` | `Pkg4` | `SkinLayerCache` | per-player model cache + GameProfile maps |
| 37 | `…feature.pkg` | `Pkg5` | `SkinLayerFactory` | builds head/body model arrays |
| 38 | `…feature.pkg` | `Pkg6` | `SkullSkinLayerRenderer` | 3D head/skull layer rendering |
| 39 | `…feature.pkg` | `Pkg_2` | `LegacySkinLayerRenderer` | `VersionGate(max=5)` dispatcher used by `ModelBipedImpl` |
| 40 | `…feature.pkg.mixin` | `Data2` | `Polygon` | `CustomizableCube$Data2` face |
| 41 | `…feature.pkg.mixin` | `Data2` | `VoxelDimensions` | `SolidPixelWrapper$Data2` (toString `SolidPixelWrapper.Dimensions`) |
| 42 | `…feature.pkg.mixin` | `Data3` | `TextureUV` | `SolidPixelWrapper$Data3` (toString `SolidPixelWrapper.UV`) |
| 43 | `…feature.pkg.mixin` | `Data4` | `VoxelPosition` | `SolidPixelWrapper$Data4` (toString `SolidPixelWrapper.VoxelPosition`) |
| 44 | `…feature.pkg.mixin` | `Pkg2` | `CustomizableCube` | per-direction cube model |
| 45 | `…feature.pkg.mixin` | `Pkg4` | `SolidPixelWrapper` | texture→voxel builder |

## Notes / deferred

* All 45 new simple names are unique tree-wide (checked against every `*.java`
  basename and every identifier occurrence). `VoxelDimensions` and `TextureUV`
  deliberately avoid the upstream `Dimensions` (protobuf class) and `UV`
  (`VertexFormatElement.EnumUsage.UV`) collisions.
* `Nameplate`/onChar, `Pkg`/`ModelBuilder`, `PkgType`/`Direction`, `Pkg2$Data`/
  `Vertex`, `Pkg4$Data`/`Position`, `Alert`/`Alert5Handler`/`Alert5Extension`/
  `Alert5Impl`, `Pkg2_2`, `Pkg3_2`, `Pkg3$*` are outside this cluster and are
  left to their own batches.
* The parallel `markers` batch renamed the LComponent base `Markers_2` →
  `DriverComponent` (and `MarkersType_3` → `DriverScreen`, `Markers2` →
  `DriverElement`, …) shortly before this map was applied. The callbacks keep the
  short `Component<Event>Callback` form (the base class is a `DriverComponent`,
  i.e. an LComponent) — this is the naming that shipped in the applied tree, so
  the map matches the tree. The applier keys on the `old` simple names, so the
  map remains valid regardless of the base-class name.
* `GuiRewindhandlersHandler2` (util.alert) shares its simple name with
  `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2`
  (its superclass). The map keys on `package + old name`, so the applier only
  touches the `util.alert` copy.
