# Cluster 63 — `com.moonsworth.lunar.client.highlight.mixin.highlight` (39 rows)

Wave 5, 2026-09-16. Source: `tools/renames/cluster-63.txt` (39 rows: 29
top-level types + 10 nested `DataN`). All 39 paths existed; no `net.minecraft.*`
rows; no shaded third-party code. Map: `tools/renames/wave5/classes-63.tsv`
(**39 rows written, 0 skipped**).

Applier dry run (captured before the map was applied):

```
[aware-renames] 39 rows (10 nested); 9808 java files
[aware-renames] rows=39 skipped=0 files_touched=87 files_renamed=29 mode=dry-run
```

The main agent applied this map to the working tree during the session
(uncommitted at time of writing); spot-checks confirm the references were
rewritten (`legacy/mixin/GuiIngameHudMixin` now imports/uses
`InventoryScreenRenderEvent.HotbarPreEvent`/`.HotbarPostEvent`,
`BossBarRenderEvent`, `ChatRenderEvent`, `ArmorRenderEvent`, ...).

## 1. What this package actually is

Despite the `highlight` name, this is **not** a block/entity highlight feature
and **not** a mixin package: it is the *render/hotbar branch of Lunar's client
event system*. Every class extends the event base from
`com.moonsworth.lunar.client.highlight` (`Highlight` = non-cancellable,
`HighlightImpl` = cancellable, `OutcomeEvent` = ALLOW/DENY result) and is
constructed by `ClientEventBus.method12(Class, Supplier)` inside a mixin, then
consumed by mods via `handle(Class, Consumer)`. The same finding as
`classes-23.md`, `classes-64.md`, `classes-66.md`.

## 2. Why the canonical names are not the map names

The canonical names for this exact package were already produced by earlier
waves and are **declared in the tree**:

| earlier map | applied | content |
|---|---|---|
| `tools/renames/classes-23.tsv` + `classes-23.md` | `57e98a2b4` | 30 `Highlight*` → `Event*` renames (EventRenderItemGlint, EventRenderBossBar, EventCameraOffset, EventSetupTerrain, ...) |
| `tools/renames/classes-ichorutil.tsv` | `62b5319b6` | the remaining rows + nested stages (`EventRenderChat`, `EventRenderHotbar`, `EventRenderInventoryScreen$EventRenderHotbarPost/Items/Pre`, `EventRenderEntityLabel$*`, `EventRenderItemColorCancel`, `EventRenderModelPlayer`, ...) |
| `d6a452378` | package move | the renamed files were moved to `com.moonsworth.lunar.client.event.mixin.highlight` |

The 2026-09-15/16 rescue sweeps (`b384aca20`, `47dd3dd48`, `7adc91305`)
re-added the **pre-rename copies** under the old placeholder names — that is
what this cluster inventories. Verification per pair: identical constructor
signatures, field sets, method bodies and string sets (the pre-rename copy
differs only in local names and in already-renamed type references), plus the
git rename provenance `57e98a2b4`/`62b5319b6` above.

`apply_class_renames_aware.py` refuses a `new` name that is declared anywhere,
so the canonical names cannot be emitted as rows. Per the `classes-40` §2 /
`classes-51` §2 / `classes-64` / `classes-66` practice the rescued copies get
**unique variant names**: canonical `EventXxxYyy` → variant `XxxYyyEvent`
(“Event” moved to the end), so the inverse map variant→canonical is mechanical.
The canonical twin is recorded in the map's evidence column and below.

## 3. Pairing (old → map name → canonical twin → refs old/twin)

Top-level rows (refs = import-aware file counts, own file excluded; counted
before the apply):

| old | new (map) | canonical twin | refs |
|---|---|---|---|
| `HighlightBase2` | `InventoryScreenRenderEvent` | `EventRenderInventoryScreen` | 2 / 7 |
| `HighlightImpl2` | `VanillaHudRenderEvent` | `EventRenderVanillaHud` | 3 / 3 |
| `HighlightImpl3` | `ItemGlintRenderEvent` | `EventRenderItemGlint` | 8 / 6 |
| `HighlightImpl4` | `CameraOffsetEvent` | `EventCameraOffset` | 1 / 1 |
| `HighlightImpl5` | `EntityLabelRenderEvent` | `EventRenderEntityLabel` | 2 / 3 |
| `HighlightImpl6` | `GlintTransformEvent` | `EventRenderGlintTransform` | 8 / 1 |
| `HighlightImpl7` | `SetupTerrainEvent` | `EventSetupTerrain` | 2 / 1 |
| `HighlightImpl8` | `ChunkReloadEvent` | `EventChunkReload` | 3 / 1 |
| `HighlightImpl9` | `ItemClumpEvent` | `EventRenderItemClump` | 1 / 2 |
| `HighlightImpl10` | `BossBarRenderEvent` | `EventRenderBossBar` | 4 / 3 |
| `HighlightImpl11` | `NameTagRenderEvent` | `EventRenderNameTag` | 5 / 14 |
| `HighlightImpl12` | `ItemStackSizeRenderEvent` | `EventRenderItemStackSize` | 2 / 6 |
| `HighlightImpl13` | `HologramUpdateEvent` | `EventHologramUpdate` | 3 / 1 |
| `HighlightImpl14` | `AlertUpdateEvent` | `EventAlertUpdate` | 7 / 2 |
| `HighlightImpl15` | `ItemColorRenderEvent` | `EventRenderItemColor` | 3 / 1 |
| `HighlightImpl16` | `ItemRotationRenderEvent` | `EventRenderItemRotation` | 1 / 3 |
| `HighlightImpl17` | `EntityOffsetRenderEvent` | `EventRenderEntityOffset` | 2 / 0 |
| `HighlightImpl18` | `GroundItemTransformEvent` | `EventGroundItemTransform` | 3 / 4 |
| `HighlightImpl19` | `GuardianRenderEvent` | `EventRenderGuardian` | 0 / 1 |
| `HighlightImpl20` | `FovModifierEvent` | `EventFovModifier` | 3 / 4 |
| `HighlightImpl21` | `FovRenderEvent` | `EventFovRender` | 3 / 1 |
| `HighlightImpl22` | `CrosshairRenderEvent` | `EventRenderCrosshair` | 5 / 5 |
| `HighlightImpl23` | `ChatRenderEvent` | `EventRenderChat` | 3 / 0 |
| `HighlightImpl24` | `EntitiesRenderEvent` | `EventRenderEntities` | 4 / 3 |
| `HighlightImpl25` | `HotbarRenderEvent` | `EventRenderHotbar` | 1 / 0 |
| `HighlightImpl26` | `ParticleRenderEvent` | `EventRenderParticle` | 2 / 2 |
| `HighlightImpl27` | `PostProcessEvent` | `EventPostProcess` | 1 / 3 |
| `HighlightImpl28` | `ArmorRenderEvent` | `EventRenderArmor` | 3 / 3 |
| `HighlightImpl29` | `PlayerRenderEvent` | `EventRenderPlayer` | 3 / 1 |

Nested rows (5th `file` column scopes the owner; `Owner$Old` in the evidence so
the applier's nested-first detection fires — dry run showed `(10 nested)`):

| old (owner) | new | canonical twin (owner) | refs old |
|---|---|---|---|
| `Data8` (`Highlight$Data8`) | `EntityRenderEvent` | `EventRenderEntityBase$EventRenderEntity` | 3 |
| `Data13` (`HighlightBase$Data13`) | `BipedModelRenderEvent` | `EventRenderModel$EventRenderBipedModel` | 2 |
| `Data14` (`HighlightBase$Data14`) | `ModelPlayerRenderEvent` | `EventRenderModel$EventRenderModelPlayer` | 1 |
| `Data10` (`HighlightBase2$Data10`) | `HotbarPostEvent` | `EventRenderInventoryScreen$EventRenderHotbarPost` | 2 |
| `Data11` (`HighlightBase2$Data11`) | `HotbarItemsEvent` | `EventRenderInventoryScreen$EventRenderHotbarItems` | 1 |
| `Data12` (`HighlightBase2$Data12`) | `HotbarPreEvent` | `EventRenderInventoryScreen$EventRenderHotbarPre` | 1 |
| `Data2` (`HighlightImpl5$Data2`) | `EntityLabelValuesEvent` | `EventRenderEntityLabel$EventRenderEntityLabelValues` | 0 |
| `Data3` (`HighlightImpl5$Data3`) | `EntityLabelLinesEvent` | `EventRenderEntityLabel$EventRenderEntityLabelLines` | 2 |
| `Data5` (`HighlightImpl15$Data5`) | `ItemColorCancelEvent` | `EventRenderItemColor$EventRenderItemColorCancel` | 0 |
| `Data2` (`HighlightImpl20$Data2`) | `FovModifierPostEvent` | `EventFovModifier$EventFovModifierPost` | 1 |

## 4. Decisions / caveats

* **Variant vs dedupe.** Per the `classes-40`/`classes-51`/`classes-64`/
  `classes-66` wave-5 practice the rescued duplicates get unique variant names
  so the tree stays greppable and the applier accepts the map. If the main
  agent prefers the `classes-59`/`notes-42`/`notes-46` route (delete the zombie
  copies and re-point the mixins at `client.event.mixin.highlight`), **drop all
  39 rows** and delete the `highlight/mixin/highlight` copies — the intended
  final names are the twin column above.
* **Both copies are load-bearing.** The canonical and the leftover copies are
  referenced from different call sites (table above): e.g. the legacy mixins
  (`legacy/mixin/GuiIngameHudMixin`, `GuiIngameForgeMixin` v1_8/v1_12,
  `legacy/mixin/RenderItemEventMixin`, `EntityRendererFogMixin`) fire the
  leftovers, while mods (`Bossbar`, `SkyblockHideArmor`, `Skins3d`,
  `SkyblockHideFood`, `GlintColorizer`) consume the canonical twins. A merge
  must re-point those imports, not just delete files.
* **Unreferenced leftovers:** `GuardianRenderEvent` (was `HighlightImpl19`,
  twin `EventRenderGuardian`, which the renamed `legacy/mixin/RenderGuardianMixin`
  already fires) and `ItemColorCancelEvent`/`EntityLabelValuesEvent` have 0
  references in this tree copy.
* **Not in the cluster (digit-free names, leave alone):** the package still
  declares `Highlight` (twin `EventRenderEntityBase`), `HighlightBase`
  (`EventRenderModel`) and `HighlightImpl` (`EventRenderDroppedItem`); the
  inventory classifier only lists digit-suffixed lazy names. Nested stages not
  listed in cluster-63 also remain placeholder-named: `HighlightImpl20$Data`
  (twin `EventFovModifier$FovInput`), `HighlightImpl12$Data` (twin
  `EventRenderItemStackSize$ItemStackSize`), `HighlightImpl3$Type` (twin
  `EventRenderItemGlint$GlintTarget`), `HighlightImpl6$Type` (twin
  `EventRenderGlintTransform$TransformPhase`).
* **Name style.** The variants follow the sibling event-leftover clusters
  (`classes-66` for `com.moonsworth.lunar.client.highlight`, `classes-64` for
  `highlight.fishing`): semantically descriptive and unique rather than
  `...Legacy`-suffixed, so they read naturally at call sites. Every new simple
  name was verified undeclared tree-wide before the apply (no trailing digits,
  no two rows sharing a name).
* **Applied-state note.** The map was applied to the working tree during this
  session while the subagent was finishing (the apply removed the old files, so
  a later re-run of the applier reports the rows as `not declared` — expected).

## 5. Evidence used

* class sources (constructor signatures, field types, injected-method bodies,
  enum constants `GlintTarget{EQUIPPED_ARMOR,ITEM,GUI}`,
  `TransformPhase{BEFORE_TRANSFORMS,AFTER_TRANSFORMS}`);
* firing sites (e.g. `EntityRendererFogMixin.lunar$offsetCamera` for
  `CameraOffsetEvent`, `RenderGlobalMixin2.lunar$onChunkReload` for
  `ChunkReloadEvent`, `RenderItemEventMixin.lunar$renderGuiIemOverlay` for
  `ItemStackSizeRenderEvent`) and consumers
  (`CustomHeldItems`, `TierTagger`, `ParticleChanger`, `LightOverlay`,
  `RewindHandlers3Updater`, `DungeonWaypoints`, ...);
* the already-applied canonical maps `tools/renames/classes-23.tsv`,
  `tools/renames/classes-ichorutil.tsv` and the git rename provenance in
  `57e98a2b4` / `62b5319b6` / `d6a452378` / `b384aca20`;
* structural diff of each pair against `com.moonsworth.lunar.client.event.mixin.highlight`.
