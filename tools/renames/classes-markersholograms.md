# Cluster 18 — `markers.mixin.holograms.mixin` + `rewind…` grab-bag (45 rows)

Source revision: `tools/renames/cluster-18.txt` md5 `7d4deeb2a8cc017f5a6fa9e38983e6dd`
(45 rows). Map: `tools/renames/classes-markersholograms.tsv` md5
`3ad27dcadebe6fc30c3c929adc08c4c8` (41 rows; 4 rows are already-correct names, see
[below](#rows-deliberately-not-in-the-map)).

This cluster is a **cross-package grab-bag**, not one package. It mixes the
markers/holograms emote mixins, the root `client.rewindhandlers` helpers, the
`util.chest` ray-trace library, the `util.lotusfish` Ogg reader, the
`util.nameplate` DataFixerUpper codec helpers, the Forge class-patch glue, the
Ichor API interfaces, the ReplayMod accessor mixins, and a handful of
`framework.feature.*` HUD/mod classes. Each was named from its own source.

## What the notable groups actually are

* **`client.markers.mixin.holograms.mixin`** — the **emote/cosmetic hologram**
  model. `Holograms2Iterator` (out of cluster) is the hologram entity with a
  `skin` enum and a `skinOverride` model; the two rows here are exactly those
  two payloads.
* **`client.rewindhandlers`** (root package) — despite the name, **not**
  rewind events (that is the *other* `framework.feature.mod.rewindhandlers`
  package). `Rewindhandlers2` is the clickable chat/button action handler
  (open-URL / run-command prompts, `ButtonClientAction`), `Rewindhandlers3` is
  the Apollo packet decode helper used by the `Highlight3Iterator*` Apollo
  modules.
* **`client.util.chest`** — a **ray-trace library** (the package name is a
  decompiler artefact). `Chest` is the ray context, `SImpl` the trace config
  factory, `SExtension` the ray, `SBase`/`SBase2` its implementations; the
  exception strings literally say "RayContext" / "RayPointSupplier". Used by
  `RaycastDebug`, `DungeonWaypointRaycast`, `Waila`, `EtherwarpPreview`, ….
* **`client.util.lotusfish`** — an **Ogg container** reader (`"OggS"`,
  `LittleEndianDataInputStream`, CRC-32 via `Lotusfish`).
* **`client.util.nameplate`** — `Nameplate` is DataFixerUpper's
  **`ExtraCodecs`**; the two nested rows are its `TagOrElementLocation` and
  `StrictUnboundedMapCodec`.
* **`com.moonsworth.lunar.forge`** — Lunar's binary **class-patch** glue around
  the shaded Mixin (`Bootstrap2.patch`).
* **`com.moonsworth.lunar.ichor.api`** — the Ichor pipeline/class-loader
  contracts.
* **`replaymod.forge.v1_8/v1_12.mixin`** — ReplayMod **accessor mixin
  interfaces** (`<Target>Accessor`, with the 1.8 twin suffixed `V1_8`), the
  same convention as the `classes-16` ReplayMod batch.

## Renames (41 rows)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | `…markers.mixin.holograms.mixin` | `Holograms2` | `SkinOverride` | skin hash/url/type for the hologram `skinOverride` field |
| 2 | `…markers.mixin.holograms.mixin` | `HologramsType2` | `HologramSkin` | `skin` enum SELF/ASTRONAUT/ASTRONAUT_DARK/WHITE → `skins/*.png` |
| 3 | `…client.rewindhandlers` | `Rewindhandlers2` | `ChatActionHandler` | open-URL / run-command / `ButtonClientAction` chat handler |
| 4 | `…client.rewindhandlers` | `Rewindhandlers3` | `ApolloPacketUtils` | `ItemStackIcon`→item, `ApolloComponent.fromJson`, URL/string helpers |
| 5 | `…client.util.chest` | `Data2` *(nested)* | `RayBuilder` | `SExtension$Data2` fluent ray-trace builder |
| 6 | `…client.util.chest` | `SBase2` | `FixedRay` | abstract ray with two fixed endpoints |
| 7 | `…client.util.chest.mixin` | `ChestHandler2` | `BlockRaycastContext` | boolean ray context for BLOCK traces |
| 8 | `…client.util.chest.mixin` | `ChestHandler3` | `EntityRaycastContext` | boolean+floats ray context for ENTITY traces |
| 9 | `…client.util.lotusfish.mixin` | `Lotusfish2` | `OggPage` | Ogg page model (`"OggS"`, granule, CRC, segments) |
| 10 | `…client.util.lotusfish.mixin` | `Lotusfish3` | `OggPageReader` | reads/syncs/seeks Ogg pages |
| 11 | `…client.util.nameplate` | `Data10` *(nested)* | `TagOrElementLocation` | `Nameplate$Data10` ResourceLocation + tag flag |
| 12 | `…client.util.nameplate` | `Data11` *(nested)* | `StrictUnboundedMapCodec` | `Nameplate$Data11` strict map codec |
| 13 | `com.moonsworth.lunar.forge` | `MixinMisc2` | `ClassPatch` | binary class patch ("Binpatch src -> dst") |
| 14 | `com.moonsworth.lunar.forge` | `MixinMisc3` | `ClassPatchRegistry` | rename maps + patched-byte cache |
| 15 | `…ichor.api` | `IchorAPI2` | `IchorClassLoader` | class-loader contract exposing the Ichor7 pipeline |
| 16 | `…ichor.api` | `IchorAPI3` | `IchorExclusionFilter` | `isExcluded(String)` exclusion contract |
| 17 | `…replaymod.forge.v1_12.mixin` | `MixinHelper2` | `ConnectionEventHandlerAccessor` | `guiControls` accessor (v1.12) |
| 18 | `…replaymod.forge.v1_12.mixin` | `MixinHelper3` | `PacketListenerAccessor` | `outputPath` accessor (v1.12) |
| 19 | `…replaymod.forge.v1_8.mixin` | `MixinHelper2` | `ConnectionEventHandlerV1_8Accessor` | 1.8 twin |
| 20 | `…replaymod.forge.v1_8.mixin` | `MixinHelper3` | `PacketListenerV1_8Accessor` | 1.8 twin |
| 21 | `…lunar.annotations` | `Annotation3` | `SourceMarker` | SOURCE marker annotation (dead in this tree) |
| 22 | `…bridge.glintcolorizer.mixin` | `Glintcolorizer2` | `PackFiltersBridge` | `bridge$getPackFilters`/`setPackFilters` |
| 23 | `…bridge.hitbox` | `Hitbox2` | `StatBaseBridge` | `@Mixin(StatBase)` marker, stat cast type |
| 24 | `…bridge.hitcolor` | `Hitcolor2` | `TileEntityChestBridge` | `bridge$isVisuallyOpen()` → `lidAngle != 0` |
| 25 | `…bridge.horsestats.mixin` | `HorsestatsType2` | `ParticleType` | ~130 vanilla particle resource locations |
| 26 | `…bridge.mixin` | `MixinHelper2` | `ClickEventBridge` | `@Mixin(ClickEvent)` `bridge$getValue()` |
| 27 | `…client` | `Gui2Handler2` | `Badge` | cosmetic badge metadata (EquipBadgeRequest) |
| 28 | `…client.alert.mixin` | `Alert2` | `AlertCondition` | criteria/alert engine interface |
| 29 | `…client.click.fishing` | `Fishing2` | `LoadableResource` | loadable registry entry (`isLoaded`/`getName`) |
| 30 | `…client.click.rewindhandlers` | `Rewindhandlers2` | `GlyphTexture` | cached text/glyph texture pass |
| 31 | `…client.fog.rewindhandlers` | `RewindhandlersType2` | `FeatureFlag` | client feature-flag enum |
| 32 | `…client.framework` | `Data2` *(nested)* | `Spacer` | `HudComponentGroup$Data2` flexible spacer |
| 33 | `…client.framework.feature` | `Module2` | `ModOptionOverrides` | per-mod option override map for rewind |
| 34 | `…armorstatus.mixin` | `Gui2Extension4` | `DurabilityDisplayMode` | VALUE/PERCENT/NONE |
| 35 | `…armorstatus.nameplate.mixin` | `Framework7Extension2` | `ArmorStatusElementChildMod` | name leaked in its own exception string |
| 36 | `…blockoutline` | `Gui2Extension2` | `BlockOutlineMode` | STATIC/RAINBOW/BLEND/INVERTED/DARKEN |
| 37 | `…cooldowns.mixin` | `CooldownsImpl2` | `IconCooldown` | renders an Apollo Icon |
| 38 | `…coordinates` | `CoordinatesChildHudModImpl2` | `CoordinatesDirectionChildMod` | cardinalDirection/directionAffect options |
| 39 | `…debug.optimizationdebugmod` | `OptimizationdebugmodType2` | `OptimizationDebugRenderTarget` | ENTITY/BLOCK_ENTITY/LEVEL |
| 40 | `…hypixelbedwars.mixin` | `Hypixelbedwars2` | `BedwarsTeamColorMapper` | team colour name → 0..7 index |
| 41 | `…inventorymod.mixin` | `GuiRewindhandlersHandler2` | `InventoryScreenPreserver` | keeps container screens open |

## Rows deliberately not in the map

Four listed rows already carry their **real upstream names** and are no-ops, so
they are omitted from the `.tsv` (the applier skips `old == new` anyway):

| row | why |
|---|---|
| `com/moonsworth/lunar/genesis/FarmHashFingerprint64.java` | already renamed by the `classes-genesis` Guava batch (`MixinHelper5242 → FarmHashFingerprint64`); real Guava `com.google.common.hash.FarmHashFingerprint64` |
| `com/moonsworth/lunar/genesis/Utf8.java` | already renamed by `classes-genesis` (`MixinHelper9_2 → Utf8`); real Guava `com.google.common.base.Utf8` |
| `net/minecraft/client/model/ModelSheep1.java` | genuine MCP 1.8.9 name (present in the reference clients) |
| `net/minecraft/client/model/ModelSheep2.java` | genuine MCP 1.8.9 name (the sheep wool overlay model) |

The cluster generator flagged these only because of the trailing `64`/`1`/`2`.

## Applier dry run

The **aware** applier (import/package resolution, nested handling) applies the
whole map:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-markersholograms.tsv
# → [aware-renames] 41 rows (4 nested); 6594 java files
#   rows=41 skipped=0 files_touched=139 files_renamed=37 mode=dry-run
```

The **naive** applier (`apply_class_renames.py`) only handles globally-unique
simple names and therefore skips 22 rows whose old name is declared in several
packages — `MixinHelper2`/`MixinHelper3`, `Annotation3`, `Glintcolorizer2`,
`Hitbox2`, `Alert2`, `Fishing2`, `Rewindhandlers2`, `Data2`, `Module2`,
`Gui2Extension4`, `Framework7Extension2`, `Gui2Extension2`,
`GuiRewindhandlersHandler2`, …:

```
python3 tools/apply_class_renames.py --map tools/renames/classes-markersholograms.tsv
# → applied=19 skipped=22 files_touched=93 files_renamed=17 mode=dry-run
```

**Do not run this map with `--allow-collisions`** on the naive applier: the old
names above are shared with unrelated classes (`client.hitbox.Hitbox2`,
`client.glintcolorizer.Glintcolorizer2`, `client.util.alert.Alert2`,
`client.feature.Module2`, the crosshair/keystrokes `Gui2Extension*` /
`Framework7Extension2`, …). Use the aware applier.

## Caveats / follow-ups

* **Nested rows 5/11/12/32** use the `Owner$Old` evidence + 5th `file` column
  form; the aware applier's nested branch handles them (`SExtension$Data2`,
  `Nameplate$Data10`, `Nameplate$Data11`, `HudComponentGroup$Data2`).
* **`client.rewindhandlers` and `client.click.*` are misnamed packages.** The
  root `client.rewindhandlers` is the Apollo/chat-action helper package, not
  rewind events; `client.click.rewindhandlers` is a cached text/glyph renderer.
  The package renames are out of scope here.
* **`client.util.chest` naming is only half done.** This map renames the nested
  builder, `SBase2` and the two contexts, but the outer interface (`SExtension`
  → a ray), `SBase`, `SImpl`, `Chest` and `ChestHandler` are not in this
  cluster. Whoever gets them should align to `Ray`/`FixedRay`/`BlockRaycastContext`/
  `EntityRaycastContext`.
* **`Annotation3` (`SourceMarker`)** is dead in this partial tree (nothing
  imports `com.moonsworth.lunar.annotations.*`); the name is inferred from its
  retention/target only. Re-check if the missing `annotations.Annotation2`/
  `Annotation4` companions reappear.
* **`GlyphTexture`** (`client.click.rewindhandlers.Rewindhandlers2`) is also
  unreferenced; it is named from its GL texture lifecycle (`bind`/`delete`) and
  the `(string,color,shadow)` cache that aggregates it.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-markersholograms.tsv
# → rows=41 skipped=0 files_touched=139 files_renamed=37 mode=dry-run
```