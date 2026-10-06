# Cluster 16 (regenerated) — `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` (24 classes)

Map: `tools/renames/classes-rewindhandlers.tsv` (24 rows).
Source: `tools/renames/cluster-16.txt` (24 rows; all paths exist).

> **The cluster number 16 was reused.** `tools/renames/classes-16.tsv`/`.md`
> already describe the *old* cluster 16 (`com.moonsworth.lunar.bridge.horsestats`)
> and are left untouched. This map is written under the descriptive name
> `classes-rewindhandlers.*`, like `classes-bettermap.tsv` / `classes-ichor.tsv`.

## What this cluster actually is

The package name is a decompiler artifact. These are **not** ReplayMod rewind
handlers: they are **Lunar event-bus events** for Hypixel SkyBlock features.

The whole tree's event system is in `com.moonsworth.lunar.client.highlight`:

* `Highlight` = base **Event**; `HighlightImpl` = cancellable event base.
* `LunarEventBus` (its own name is real) registers listeners per event class;
  `method11` reads `@Annotation3` and calls `GuiRewindhandlersHandler2.method1/3`
  (enable/disable) — the annotation is a **`@TriggeredBy`** marker.
* `Nameplate2` (`com.moonsworth.lunar.client.guiRewindhandlers.nameplate`) is the
  **`DynamicListenerEvent`** marker: the bus throws
  `"DynamicListenerEvent must be annotated with @TriggeredBy"` when a class
  implements it without the annotation. So every class in this cluster is a
  dynamic event, and its `@Annotation3(SomeHandler.class)` names the handler
  that produces/enables it.

Each event was identified from its fields plus the handler that fires it and the
mods/HUDs that consume it (see the `evidence` column). The concrete SkyBlock
subsystems are: SkyBlock profile id/data, Crystal Hollows Worm/Scatha, Dwarven
& Glacite commissions, Slayer quests, Hypixel `/locraw` location, Kuudra tier,
F7 Goldor terminals, dungeon room changes, dungeon secrets, sea-creature
catches and skill XP.

## Renames (24 rows)

| # | old (owner) | new | subsystem |
|---|---|---|---|
| 1 | `Rewindhandlers$Data13` | `SkyblockProfileIdEvent` | profile id from `/profileid` |
| 2 | `Rewindhandlers$Data14` | `SkyblockProfileLoadEvent` | profile object fetched |
| 3 | `Rewindhandlers$Data15` | `SkyblockProfileChangeEvent` | profile switched (old,new id) |
| 4 | `HighlightBase$Data2` | `WormScathaSpawnEvent` | Worm/Scatha spawn (bool isScatha) |
| 5 | `HighlightBase2$Data2` | `CommissionStartEvent` | commission appears |
| 6 | `HighlightBase3$Data2` | `SlayerQuestFailedEvent` | slayer quest failed |
| 7 | `HighlightBase4$Data2` | `LocationEnterEvent` | joined a SkyBlock location |
| 8 | `HighlightBase5$Data2` | `KuudraEnterEvent` | Kuudra tier detected |
| 9 | `Rewindhandlers2$Data2` | `TerminalPhaseEvent` | Goldor terminal phase |
| 10 | `Rewindhandlers3$Data2` | `RoomSecretCollectedEvent` | dungeon secret found |
| 11 | `HighlightBase2$Data3` | `CommissionRemoveEvent` | commission removed |
| 12 | `HighlightBase3$Data3` | `SlayerBossSpawnEvent` | slayer boss spawned |
| 13 | `Rewindhandlers2$Data3` | `TerminalActivateEvent` | terminal/lever/device/gate |
| 14 | `HighlightBase2` | `CommissionEvent` | base (complete/start/remove) |
| 15 | `HighlightBase3` | `SlayerQuestEvent` | base (complete/failed/spawn) |
| 16 | `HighlightBase4` | `LocationEvent` | base (enter/leave) |
| 17 | `HighlightBase5` | `KuudraEvent` | base (enter/leave) |
| 18 | `HighlightBase_2` | `DungeonRoomEvent` | base of `HighlightBase$Data3/Data4` |
| 19 | `HighlightImpl2` | `GameDataLoadEvent` | `Module` finished loading SkyBlock data |
| 20 | `HighlightImpl3` | `SeaCreatureCatchEvent` | fishing catch |
| 21 | `HighlightImpl4` | `SkillXpUpdateEvent` | skill XP gain |
| 22 | `Rewindhandlers2` | `TerminalEvents` | namespace of the F7 terminal events |
| 23 | `Rewindhandlers3` | `RoomSecretEvent` | base secret event |
| 24 | `Rewindhandlers_2` | `DungeonEvents` | namespace of dungeon floor/score events |

## How the identities were established

* **Firing sites** (the handler named by `@Annotation3`):
  `holograms.fishing.GuiRewindhandlersHandler212` (`/profileid`),
  `...Handler22` (profile fetch), `...Handler25` (commissions + F7 terminals),
  `...Handler26` (skill XP), `...Handler29` (held item),
  `holograms.gui.GuiRewindhandlersHandler2` (Worm/Scatha),
  `holograms.fishing.GuiRewindhandlersHandler2` (slayer),
  `holograms.mixin.GuiRewindhandlersHandler2` (Kuudra),
  `holograms.GuiRewindhandlersHandler2_2`/`23_2`/`22_2` (dungeon floor/score),
  `holograms.GuiRewindhandlersHandler24` (room secrets),
  `guiRewindhandlers.GuiRewindhandlersHandler23` (`/locraw` location),
  `framework.feature.mod.Module` (data load).
* **Consumers**: `SkyblockScathaTrackerHud`, `SkyblockWormScathaAlert`,
  `SkyblockGlaciteCommissions`, `SkyblockBossTimer`, `SkyblockBlazeSlayer`,
  `SkyblockCollectedSecret`, `SkyblockTerminalSplits`, `SkyblockTerminalSolvers`,
  `SkyblockTerminalStatus`, `SkyblockFishingInfoHud`, and the skill HUDs.
* **Enums carried by the events**: `Rewindhandlers3$Data$Type`
  (SUPERBOOM/LEVER/REDSTONE_KEY_PICKUP/REDSTONE_KEY_PLACED/LOCKED_CHEST/
  ROOM_COMPLETED), `Rewindhandlers2$Data3$Type` (TERMINAL/DEVICE/LEVER/GATE),
  `Gui2Extension3` (Hub…Kuudra…NONE), `CoordinatesType2`
  (ACTION_BAR/SKILLS_MENU/TAB_LIST), `HighlightType` (TERMINAL_*).
* **Provenance**: `normalize-renames.tsv` shows the `mixinCore/*` origin of
  `HighlightBase_2`, `Rewindhandlers_2`, `Rewindhandlers2`, `Rewindhandlers3`;
  `remaining-renames.tsv` gives the obf names
  (`OHROCHICOIOICHOCRROORRCIIICIHO` = `Rewindhandlers`, etc.). `javap` on
  `Rewindhandlers_2.class` shows it is the **nest host** of
  `Rewindhandlers$Data9..12`, which is what makes row 24 a namespace rather
  than an event.

## Out-of-cluster siblings (not in `cluster-16.txt`, so not in the map)

* `Rewindhandlers` (holder of `Data13/14/15`) → best named
  `SkyblockProfileEvents`; its nested `Data` (not listed) is the profile
  object event.
* `HighlightBase` (holder of `Data`/`Data2`) → `WormScathaEvent`; nested `Data`
  is the "You hear the sound of something approaching…" warning.
* `HighlightImpl` (held item: `BridgeExtension_4` item + id + name) →
  `HeldItemChangeEvent`.
* The flattened `$`-files `HighlightBase$Data3`/`Data4`,
  `Rewindhandlers$Data9..12` are separate top-level declarations in this tree
  and are not in the cluster; they are referenced only in the evidence column.
* The `mixin/Rewindhandlers.java` sibling in the same package is a different
  class (Kuudra crop helper) and is not in the cluster.

## Applier dry run and collisions

`python3 tools/apply_class_renames.py --map tools/renames/classes-rewindhandlers.tsv`:

```
applied=2 skipped=22 files_touched=5 files_renamed=2 mode=dry-run
```

Only `HighlightBase5` and `HighlightBase_2` are unique simple names tree-wide
and apply with the v1 rewriter. The other 22 rows are **old-name collisions**
(the same placeholder name is declared in many packages), not map errors:

| old | also declared in (count) |
|---|---|
| `Data2` | 12 packages (fishing.gui, gui.nameplate, holograms.fishing, mod.hud, …) |
| `Data3` | 7 packages (holograms.mixin, guiRewindhandlers, horsestats, …) |
| `Data13/14/15` | not indexed by v1 (declared as `public static class` inside `Rewindhandlers.java`) |
| `HighlightBase2` | `highlight.fishing`, `highlight.mixin.fishing`, `highlight.mixin.gui` |
| `HighlightBase3` | `highlight.fishing`, `highlight.mixin.fishing` |
| `HighlightBase4` | `highlight.mixin.fishing` |
| `HighlightImpl2/3/4` | 8/9/8 packages (`highlight.mixin.*`, `highlight.fishing`, …) |
| `Rewindhandlers2` | 10 packages (`click.rewindhandlers`, `fov.mixin.rewindhandlers`, `fishing.rewindhandlers`, …) |
| `Rewindhandlers3` | `guiRewindhandlers.rewindhandlers`, `inactive.rewindhandlers`, `rewindhandlers` |
| `Rewindhandlers_2` | `framework.feature.rewind.rewindhandlers`, `inactive.rewindhandlers` |

**Do not run this map with `--allow-collisions`.** A global word-boundary
rewrite would merge this cluster's events into the unrelated
`highlight.mixin.*`, `guiRewindhandlers`, `fishing.rewindhandlers`, … classes.
The 22 rows need the planned import/package-aware rewriter (same follow-up as
`classes-07.md`, `classes-12.md`, `classes-15.md`, `classes-23.md`), using the
5th `file` column to disambiguate the nested `Data2`/`Data3` rows.

Every new name was checked with the tree-wide declaration scan
(`grep -rE "\b(class|interface|enum|record)\s+NewName\b" src/main/java`) and is
declared nowhere else.

## Caveats / uncertainties

* **Nested rows are not applied by v1.** `Data13/14/15` and the `Data2`/`Data3`
  rows are nested types; v1 only renames top-level declarations and its
  `Outer$Inner` handling does not cover types left in place. They need the
  nested-aware pass.
* `HighlightBase4` is named around Hypixel `/locraw` **locations**
  (`Gui2Extension3` = Hub…Kuudra…NONE). If the project prefers "island"
  terminology, `IslandEvent`/`IslandEnterEvent` are the alternative; I chose
  `Location*` because `Gui2Extension3` also covers Dungeon/Kuudra/Mineshaft.
* `HighlightBase_2`'s two nested events (`HighlightBase$Data3`/`Data4`) are the
  room-change/room-enter pair; the base is named `DungeonRoomEvent`. If the
  nested pair is later renamed, keep the base consistent.
* `Rewindhandlers_2` (`DungeonEvents`) and `Rewindhandlers2` (`TerminalEvents`)
  are namespace containers, not base classes (their nested events extend
  `Highlight`/`HighlightImpl`). `Rewindhandlers3`/`HighlightBase*` **are**
  abstract bases (their nested events extend them), hence the singular
  `…Event` names.
* `HighlightImpl2` (`GameDataLoadEvent`) is fired exactly once, by `Module`
  after all SkyBlock JSON is loaded; if it turns out to be reused as a generic
  reload signal, `GameDataReloadEvent` would fit better.