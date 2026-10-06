# Cluster 49 — `com.moonsworth.lunar.legacy.wrapper` (45 rows)

Map: `classes-49.tsv` (45 rows, 0 skipped). Dry-run with
`tools/apply_class_renames_aware.py`: 45 rows (16 nested), 0 SKIP, 0 WARN,
29 files renamed, 38 files touched.

## What this package is

Lunar's **legacy platform module** (the 1.7 / 1.8 / 1.12 compatibility layer),
in two halves:

* **Bridge implementations** (`*BridgeImpl` style): blocks registry, tool
  material tiers, render pipeline, entity registry, enchantments, item-entity
  renderer, nametag renderer, framebuffer wrappers, render-view proxy, MP3
  codec, Apollo server-links screen.
* **Packet builders** for the cross-version translator `BridgeIterator`
  (`Bridge_16`): each class wraps a `net.minecraft.network.play.server.*`
  packet and is registered by class in `BridgeIterator.field1`, with
  `MC_VERSION` branches for 1.7 (`<= 0`), 1.8 (`== 1`) and 1.12 (`else`).

## Duplicate / lineage situation (important)

The tree merges two extractions ("main" and "reference", the latter rescued
from quarantine — see `Prompt.md` §0.6). 24 of these 45 classes are **rescued
duplicates** of classes that were already renamed by the `legacy.wrapper` map
(classes-15, commit `377399477`). In every case:

* the **twin (family A) is dead code** — zero references outside its own file
  (`BlocksBridgeImpl`, `EntityHeadLookPacketBuilder`, …);
* the **rescued copy (family B, this cluster) is the live one** — referenced by
  `legacy/Legacy2`, `legacy/wrapper/BridgeIterator` and the `legacy/mixin`
  mixins.

Naming follows the classes-50 convention for this package: the rescued copy
gets a **variant name** so the map applies without collisions —
`<X>PacketFactory` for the packet twins, `…BridgeAdapter`, `…Proxy`,
`…Codec` for the rest. The twins were left alone.

If a later dedupe pass wants one name per concept, delete the dead family-A
twins first and re-run this map with the twin names:

`BlocksBridgeImpl`, `ToolMaterialBridgeImpl`, `ShaderBridgeImpl`,
`EntityListBridgeImpl`, `ExternalTextureFramebuffer`, `NoopFramebuffer`,
`RenderViewEntityAdapter`, `StaticMp3Codec`, `JoinGamePacketBuilder`,
`BlockBreakAnimPacketBuilder`, `ChunkDataPacketBuilder`,
`HeldItemChangePacketBuilder`, `OpenWindowPacketBuilder`,
`PlayerAbilitiesPacketBuilder`, `SetExperiencePacketBuilder`,
`SpawnExperienceOrbPacketBuilder`, `SpawnMobPacketBuilder`,
`SpawnPlayerPacketBuilder`, `TabListHeaderFooterPacketBuilder`,
`UnsupportedNetPacketBuilder`, `UnsupportedPacketBuilder`,
`UnsupportedPacketListBuilder`, `UnsupportedStatePacketBuilder`,
`UnsupportedWorldPacketBuilder`, and `Wrapper`/`FaceEdgeMasks`.

## Unique (no family-A twin) — named independently

`BridgeHandler_2` (item renderer), `BridgeHandler_6` (enchantments),
`BridgeIterator_2` (nametag renderer), `GuiScreenImpl2` (server-links screen),
`MixinHelper2` (player list), `MixinHelper16` (chunk unload),
`MixinHelper19` (boss info), and the 14 `MixinHelper7$Data22…Data35` render
state shards.

## Rows deliberately skipped

None — every path exists, there are no `net.minecraft.*` rows and no shaded
third-party classes in this cluster.

## Ambiguities / decisions

* **`Wrapper$Data2` → `CubeEdgeMasks`**: the constants are 13 ints
  (`17,18,33,34,5,6,9,10,20,36,24,40,63`) = adjacent-face pairs of the six face
  bits in `Wrapper$Data` (`1,2,4,8,16,32,63`); the already-renamed twin nested
  class is `FaceEdgeMasks$EdgeMasks`. The simple name `EdgeMasks` was left to
  the twin to honour the tree-wide uniqueness rule (a bare `EdgeMasks` would be
  declared twice); rename to `EdgeMasks` after the `Wrapper`/`FaceEdgeMasks`
  dedupe if exact parallel naming is wanted. Note the outer `Wrapper` class and
  `Wrapper$Data` are **not** in any cluster (no trailing digit), so the outer
  keeps its lazy name for now.
* **`MixinHelper16` → `ChunkUnloadPacketFactory`**: its bridge counterpart is
  already named `com.moonsworth.lunar.bridge.ChunkUnloadPacketBuilder` (an
  interface, despite the "Builder" suffix), so that simple name is taken.
* **`TiersBridgeAdapter` / `RenderPipelineBridgeAdapter`** intentionally differ
  from the twin names (`ToolMaterialBridgeImpl`, `ShaderBridgeImpl`): those
  impls implement `bridge.TiersBridge` and `bridge.RenderPipelineBridge`, so
  the accurate interface name was used for the variant.
* **Ordering vs classes-50**: the 14 `MixinHelper7$Data22…Data35` rows are
  scoped by evidence `MixinHelper7$…`. classes-50 renames the owner to
  `LegacyRenderTypeFactory`; if that map is applied first, either apply
  classes-49 before classes-50, or re-point the evidence to
  `LegacyRenderTypeFactory$DataNN`.
* **Packet factory vs builder**: `MixinHelper2` / `MixinHelper16` /
  `MixinHelper19` have no family-A twin, but only `PlayerListItemPacketBuilder`
  and `BossInfoPacketBuilder` were free — the `PacketFactory` suffix is
  reserved here for the rescued duplicates (same as classes-50).
