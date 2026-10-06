# Cluster 12 — `…debug.fpsdebugmod.profilerdebugmod` + neighbours (45 classes)

Source revision: `tools/renames/cluster-12.txt` md5 `83b3ad3d09bd3c5d16a9d6ae679de37b`
(45 rows across 9 packages: 42 top-level types + the nested `Gui$Data3`,
`Nameplate2$Data2`, `Nameplate2$Data3`).
Map: `tools/renames/classes-debugprofiler.tsv` (md5 `df1a97c369f14da9212aca1e8233cd53`).

## What this cluster actually is

The package name is the leftover of the decompiler's package scrambling. The
cluster is the **debug/profiler HUD stack** plus a ring of unrelated
neighbours that happened to sort next to it:

* **`…debug.fpsdebugmod.profilerdebugmod` — the frame profiler.** `Profilerdebugmod`
  (not in this map) is the profiler manager: it installs a
  `GarbageCollectionNotificationInfo` listener, measures frame pauses
  (`nextFrame()`, `"Game Pause Detected!, Dumping Stack!"`) and emits
  `Data`/`Type` (UNKNOWN/GC/POLL_EVENTS) events. The five mapped classes are
  the serialized profile: a `Profile` segment, its `ProfileMetadata` header,
  the `ProfileCallNode` call tree, a `ProfileFlag` boolean holder and the
  `StringPool` used by the `.lcprofile` writer. `ProfilerDebugMod` writes
  `data-N.lcprofile` files under `lunar-profiler/` and (when
  `Profilerdebugmod2_3.shouldProfile()`, currently hard-coded `false`) uploads
  them to `http://soopy.dev:25561/uploadProfile`.
* **`…mod.fishing.gui.mixin` — the SkyBlock item registry.** Despite the name
  this is not a mixin and not a GUI: `ImportantItems`/`ImportantItemCategory`
  model `important-items.json` (dungeons/kuudra × weapons/items/pets),
  `VanillaItemRegistry` models `skyblock-vanilla-items.json`, `LegacyItemRegistry`
  is the 1.8→1.13 item-id table and `ItemRarity` is the Hypixel rarity enum.
* **`…mod.gui` — the SkyBlock commands GUI stack** (`SkyBlockCommandsGui`,
  `SkyBlockCommandOptionsGui`, `SkyBlockSubCommandEditorGui`,
  `SkyBlockSubCommandSelectGui`) plus the inventory-buttons movement screen
  button (`Gui$Data3`).
* **`…rewind.highlight.nameplate` — the undo/redo transaction collections**
  used by the rewind highlight model (`UndoRedoManager` + batch/command,
  `TransactionalSet`, `HighlightTreeMap`).
* **`…rewind.rewindhandlers.nameplate` — the ReplayMod-style replay context
  objects.** The `toString()` of each class gives the exact real names:
  `GuiScreenContext`, `LocalPlayerContext`, `ReplayContext` (whose
  `toString` also names the `RewindingContext` field) and the entity context
  menu.
* **`…guiRewindhandlers.mixin` — event-bus handlers** (the package is a
  scrambled grab-bag): own-projectile tracker, screen/GUI behaviour handler,
  HUD-timer ticker, player-cosmetics subscription manager and the player stat
  tracker.
* **`…highlight.mixin.fishing.mixin` / `…highlight.mixin.rewindhandlers` —
  two more event classes** (the `highlight` event system, `Event<Purpose>`
  convention). The `fishing.mixin` set is the recorded/replayed player
  actions plus render/map events; the `rewindhandlers` set is the input
  events (mouse button/scroll/wheel, perspective) used by the mods and the
  rewind camera.
* **`…killsounds.mixin` — the `optionsLC.txt` legacy-options migration
  system**, not kill sounds (the sibling top-level `killsounds` package is the
  *mod-config* migrator, already applied in `classes-markers-killsounds`).

Readable ground truth used: the `toString()` strings of `ReplayContext` /
`GuiScreenContext` / `LocalPlayerContext` / `LayerPropertiesContext`, the
`ProfilerDebugMod` driver, `Module.java` (`important-items.json`,
`skyblock-vanilla-items.json`), `SkyblockVanillaItemModels`, `SkyblockItemUtil`,
`ScrollableTooltips`, `Zoom`, `Freelook`, `DungeonMapListener`,
`RouteTracker.method7`, `EventRegistrationsHandler`, `Nameplate2Impl2/5`,
`PlayerStateRecorder` and `RewindHandlers3Updater`.

## Renames (45 rows)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | `…profilerdebugmod` | `Profilerdebugmod2` | `Profile` | one captured profile segment (start delay, dirty flag, pause events, root call tree) |
| 2 | `…profilerdebugmod` | `Profilerdebugmod3` | `ProfileMetadata` | branch/hash/mcVersion/uuid header (`create()`) |
| 3 | `…profilerdebugmod` | `Profilerdebugmod4` | `ProfileCallNode` | call-tree node: class/method/line/time + children |
| 4 | `…profilerdebugmod` | `Profilerdebugmod_2` | `ProfileFlag` | `@Annotation2` immutable boolean holder (`shouldProfile`) |
| 5 | `…profilerdebugmod` | `Profilerdebugmod_3` | `StringPool` | profile string table (collect → write → index) |
| 6 | `…fishing.gui.mixin` | `Gui2` | `ImportantItemCategory` | weapons/items/pets set of `important-items.json` |
| 7 | `…fishing.gui.mixin` | `Gui4` | `VanillaItemRegistry` | `skyblock-vanilla-items.json` model |
| 8 | `…fishing.gui.mixin` | `Gui5` | `ImportantItems` | dungeons+kuudra categories of `important-items.json` |
| 9 | `…fishing.gui.mixin` | `GuiType2` | `LegacyItemRegistry` | 1.8→1.13 id/data→name mapping (`idToNewName`) |
| 10 | `…fishing.gui.mixin` | `GuiType3` | `ItemRarity` | ADMIN…NONE rarity enum with colours |
| 11 | `…mod.gui` | `Data3` (nested) | `AddInventoryButton` | `Gui$Data3`: the "addNewInventoryButton" button |
| 12 | `…mod.gui` | `Gui2` | `SkyBlockCommandOptionsGui` | per-command options (keybind/favorite) |
| 13 | `…mod.gui` | `Gui3` | `SkyBlockCommandsGui` | main commands list (`.skyBlockCommands`) |
| 14 | `…mod.gui` | `Gui4` | `SkyBlockSubCommandEditorGui` | user-defined sub-command editor ("addCommand") |
| 15 | `…mod.gui` | `Gui_2` | `SkyBlockSubCommandSelectGui` | sub-command chooser ("chooseCommand") |
| 16 | `…highlight.nameplate` | `Data2` (nested) | `UndoRedoBatch` | `Nameplate2$Data2`: batch of undo/redo commands |
| 17 | `…highlight.nameplate` | `Data3` (nested) | `UndoRedoCommand` | `Nameplate2$Data3`: one undo/redo command |
| 18 | `…highlight.nameplate` | `Nameplate2` | `UndoRedoManager` | undo/redo with beginBatch/endBatch, cap 50 |
| 19 | `…highlight.nameplate` | `Nameplate3` | `TransactionalSet` | set counterpart of the transactional map |
| 20 | `…highlight.nameplate` | `TreeMapImpl2` | `HighlightTreeMap` | `TreeMapImpl<Range<Integer>,V>` keeping the Highlight UUID index |
| 21 | `…rewindhandlers.nameplate` | `Nameplate2` | `GuiScreenContext` | `toString` "GuiScreenContext(...)" |
| 22 | `…rewindhandlers.nameplate` | `Nameplate3` | `LocalPlayerContext` | `toString` "LocalPlayerContext(...)" |
| 23 | `…rewindhandlers.nameplate` | `Nameplate4` | `ReplayContext` | `toString` "ReplayContext(...)" |
| 24 | `…rewindhandlers.nameplate` | `Nameplate5` | `RewindingContext` | bounded action queue; named `rewindingContext` in `ReplayContext.toString` |
| 25 | `…rewindhandlers.nameplate` | `Nameplate_2` | `EntityContextMenu` | rewind entity menu ("showContextMenu"/"nametag"/"skin") |
| 26 | `guiRewindhandlers.mixin` | `GuiRewindhandlers2` | `ProjectileTracker` | tracks the local player's projectiles for hit sounds |
| 27 | `guiRewindhandlers.mixin` | `GuiRewindhandlers3` | `ScreenInteractionHandler` | screen/keybind/crosshair/chat-scale bundle |
| 28 | `guiRewindhandlers.mixin` | `GuiRewindhandlers4` | `HudTimerTicker` | ticks the `WeakHashMap<HudTimer>` on render tick |
| 29 | `guiRewindhandlers.mixin` | `GuiRewindhandlers5` | `PlayerCosmeticsSubscription` | subscribe/unsubscribe cosmetics+radio per player |
| 30 | `guiRewindhandlers.mixin` | `GuiRewindhandlers6` | `PlayerStatTracker` | increments FogLoader stat counters from gameplay events |
| 31 | `highlight.mixin.fishing.mixin` | `HighlightImpl2` | `EventUseItem` | recorded/replayed `useItem(slot)` action |
| 32 | `highlight.mixin.fishing.mixin` | `HighlightImpl3` | `EventEntityItemSpawn` | item+entity event consumed by `RouteTracker.method7` |
| 33 | `highlight.mixin.fishing.mixin` | `HighlightImpl4` | `EventRenderEntityItem` | fired at HEAD of `RenderEntityItem.doRender`; `Items2d` cancels it |
| 34 | `highlight.mixin.fishing.mixin` | `HighlightImpl5` | `EventRenderEntityItemLegacy` | `@VersionGate(max=5)` legacy render-item variant |
| 35 | `highlight.mixin.fishing.mixin` | `HighlightImpl6` | `EventMapUpdate` | map id + MapData + decorations; `DungeonMapListener` |
| 36 | `highlight.mixin.rewindhandlers` | `HighlightImpl2` | `EventInitialScreenOpen` | empty; `Highlight3Handler` cancels it before the first tick after a screen change |
| 37 | `highlight.mixin.rewindhandlers` | `HighlightImpl3` | `EventMouseButton` | mouse button + DOWN/UP (`fromMouseButton`) |
| 38 | `highlight.mixin.rewindhandlers` | `HighlightImpl4` | `EventMouseScroll` | scroll delta; tooltips + replay camera distance |
| 39 | `highlight.mixin.rewindhandlers` | `HighlightImpl5` | `EventPerspectiveChange` | third-person view cycle (0→1→2); `Freelook` cancels |
| 40 | `highlight.mixin.rewindhandlers` | `HighlightImpl6` | `EventMouseWheel` | wheel delta; variable zoom + `InputReplayTimer.handleScroll` |
| 41 | `killsounds.mixin` | `Killsounds2` | `OptionAccessor` | typed `process(key, default)` over optionsLC.txt |
| 42 | `killsounds.mixin` | `Killsounds3` | `OptionMigration` | migration-step interface (applies/migrate) |
| 43 | `killsounds.mixin` | `Killsounds4` | `KeyCodeOptionMigration` | maxFps/chatOpacity + numeric key → name |
| 44 | `killsounds.mixin` | `Killsounds5` | `FovOptionMigration` | fov −1..1 ↔ 30..110 normalisation |
| 45 | `killsounds.mixin` | `Killsounds6` | `KeyNameOptionMigration` | chatOpacity + key name → numeric code |

## Applier dry run, and the collision rows

`python3 tools/apply_class_renames_aware.py --map tools/renames/classes-debugprofiler.tsv`:

```
[aware-renames] 45 rows (3 nested); 6594 java files
[aware-renames] rows=45 skipped=0 files_touched=209 files_renamed=42 mode=dry-run
```

All 45 rows resolve with the import-aware applier (the 3 nested rows rename
in place, so 42 files are renamed). The v1 global applier would skip 26 rows
because most old names are declared in several packages:

| skipped old name | also declared in |
|---|---|
| `Gui2`, `Gui4`, `Gui5`, `Gui3` | `inactive.mixin.gui`, `markers.mixin.gui`, … |
| `Nameplate2`/`3`/`4`/`5`/`_2` | `mod.gui.nameplate`, `lighting.nameplate`, `markers.nameplate.mixin`, `glintcolorizer.nameplate`, … |
| `HighlightImpl2`…`6` | `highlight.mixin.gui`, `highlight.mixin.holograms`, `highlight.mixin.nameplate`, … |
| `Data2`/`Data3` | 31 / 14 packages (and the nested rows) |

**Do not run this map with the v1 applier or `--allow-collisions`.** The
import-aware applier is required so that, for example, the `Gui2` in
`fishing.gui.mixin` is not confused with the `Gui2` in `mod.gui` or the
`markers.mixin.gui` one.

## Caveats / follow-ups

* **Row 36 (`EventInitialScreenOpen`) is the weakest name.** The event is
  empty and its only handler (`framework/mixin/Highlight3Handler`) simply
  cancels it while no `EventTick` has fired since the last `EventScreenChange`.
  No firer survives in this partial tree, so the name is inferred from the
  guard semantics; a later pass with the full tree should confirm it.
* **`Profilerdebugmod_2` (`ProfileFlag`)** is unreferenced in the tree (it came
  from the `mixinExtra` profiler subtree); the name is from its single
  `shouldProfile` field.
* **Out-of-cluster companions** (not listed in `cluster-12.txt`, so not in the
  map): `Profilerdebugmod` (the profiler manager), `Profilerdebugmod2_2`
  (`.lcprofile` encoder), `Profilerdebugmod2_3` (soopy.dev uploader),
  `Profilerdebugmod2$Data` (Flow throttle subscription), `mixin/Profilerdebugmod`
  + `mixin/ProfilerdebugmodIterator` (sampler thread), `GuiType` (potion-effect
  enum, same `fishing.gui.mixin` package), `Killsounds` (the optionsLC.txt
  manager) and `Killsounds6Impl`. Whoever gets those files should claim them
  consistently (`ProfileSerializer`, `ProfileUploader`, `ProfilerSampler`, …).
* Names added by this cluster were checked with the applier's declaration scan;
  no row was skipped for "new name already declared" and none of the 45 new
  simple names is declared anywhere else in `src/main/java`.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-debugprofiler.tsv
# pre-apply dry run → rows=45 skipped=0 files_touched=209 files_renamed=42 mode=dry-run
```

The map was subsequently applied to `src/main/java` by the main agent (all 45
rows resolved, 42 files renamed; the 3 nested rows were renamed in place).
Re-running the applier now reports `skipped=45` because the old simple names no
longer exist in the target packages — that is expected after an apply.