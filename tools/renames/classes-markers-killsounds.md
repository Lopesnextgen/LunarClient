# Cluster 23 — `feature.markers` + `client.killsounds` + `mod.holograms` neighbours (44 classes)

Source revision: `tools/renames/cluster-23.txt` md5 `2941bf85ad0260f03dd537a65255bb71`
(44 rows: 15 `framework/feature/markers`, 15 `client/killsounds`, 14
`framework/feature/mod/holograms`).
Map: `tools/renames/classes-markers-killsounds.tsv` (md5 `6afdd79774d80ca18628d58365e394de`).

> The previous `classes-23.tsv`/`.md` pair described the `highlight.mixin.highlight`
> event-system cluster (see the note in that file); the cluster files were
> regenerated afterwards and cluster 23 is now this markers/killsounds/holograms
> tree. That old map is untouched.

## What this cluster actually is

Two of the three package names are decompiler leftovers; only the third is
literal. Evidence, not the package name, drives every row below.

### `framework.feature.markers` — the marker-HUD icon mapping (15 rows)

The package is Lunar's **marker HUD** (`com.moonsworth.lunar.client.mod.render.Markers`,
`Markers` manager, `Markers2` marker model, `Markers3` renderer, websocket
`BroadcastNewMarkerRequest`). The 15 classes here are the **registry-name ↔
marker-icon key** mapping that feeds `Markers2.Data.type_key`:

* `SIterator2` / `SIterator_2` are the two `SIterator` registries (entity and
  item). `SIterator` holds a `BiMap<String,String>` of legacy↔modern registry
  names plus a list of dynamic mappings, and logs
  `"Error while applying DynamicMapping in adaptFrom/getKeyFor"`.
* `Markers_3` is the generic mapping contract; `SExtension_2` is the
  marker-icon variant of it; `Markers4`…`Markers12`, `SExtension`, `SExtension2`
  are the concrete per-category mappings registered in
  `SIterator_2.initMappings()` (`Markers4("door")`, `Markers10("fence")`,
  `Markers11()` spawn eggs, `Markers12("minecraft:lit_furnace", …)` aliases, …).
* The mapping key `Markers3_2` is serialized as `value`/`state`
  (`Markers2_3` = variant state) inside `Markers2.Data` (`type_key`), and
  `Markers3.method10` renders the item/entity icon from it.

### `client.killsounds` — the versioned config migration system (15 rows)

**Not** the kill-sound mod. `Killsounds4` is a versioned JSON config migrator:
`field1` is an ordered list of 33 `Supplier<Killsounds3_2>` migration steps,
`field2` is the current version, and `method3(obj, json)` runs every step newer
than the config's `"version"`. It is called from the config load/save paths
(`framework/feature/rewind/highlight/LinkedHashMapImpl`, `Stopwatch`,
`SoundChanger`, `horsestats/Horsestats3`, `horsestats/mixin/Horsestats4`) as
`Killsounds4.method3(this, json)` and `json.addProperty("version", Killsounds4.field2)`.
Each `KillsoundsN` migrates one mod's option keys (Ping, BlockOutline, Skyblock,
Snaplook, Scoreboard/ItemCounter/DirectionHud/Crosshair, HEIGHT_LIMIT,
ParticleChanger, AutoTextActions, Bossbar, Stopwatch, PotionEffects). The real
kill-sound feature lives in `framework/feature/killsounds` and
`mod/combat/KillSounds.java`, which are different classes.

### `framework.feature.mod.holograms` — DynamicListener handlers + entity query (14 rows)

The base `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2`
(quarantined) is the **DynamicListener** framework: `handle(Class, Consumer)`,
`method12`, `onEnable/onDisable/isEnabled`, dependency graph, and the error
`"DynamicListener#addDependency() must be called before the listener is
initialized!"`. The 10 `GuiRewindhandlersHandler*` rows here are concrete
listeners (SkyBlock dungeon/fishing chat + event handlers). `Holograms2` /
`Holograms3` / `ParentTImpl2` / `ParentTImpl3` are the generic entity
query/subscription infrastructure (generic bound `BridgeExtension`, keyed by
`bridge$getEntityId()`) used by the nametag/highlight mods
(`SkyblockDungeonNametags`, `SkyblockGalateaMobHighlight`, `TeamCakeHighlight`, …).

## Renames (44 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Markers4` | `WoodenBlockIconMapping` | door/button/pressure_plate wood variants |
| 2 | `Markers5` | `SkullIconMapping` | `"skulls"`, `bridge$isItemSkull` |
| 3 | `Markers6` | `BannerIconMapping` | `"banners"`, `bridge$getBannerColor` |
| 4 | `Markers7` | `DyeColorIconMapping` | dye/carpet/bed/wool/…, colour↔damage BiMap |
| 5 | `Markers8` | `NetherBrickIconMapping` | `"nether_brick"`, red/block/item |
| 6 | `Markers9` | `BlockVariantIconMapping` | stone/quartz/sandstone variant lists |
| 7 | `Markers10` | `FenceIconMapping` | `"fence"`/`"fence_gate"` |
| 8 | `Markers11` | `SpawnEggIconMapping` | `"spawn_eggs"`, entityId↔name |
| 9 | `Markers12` | `LegacyItemAliasMapping` | lit/unlit registry aliases |
| 10 | `Markers_2` | `RedstoneComponentIconMapping` | `"powered"`, repeater/comparator |
| 11 | `Markers_3` | `DynamicMapping` | root mapping interface (codebase term) |
| 12 | `SExtension2` | `BoatIconMapping` | boat entity mapping |
| 13 | `SExtension_2` | `IconMapping` | marker-icon mapping interface |
| 14 | `SIterator2` | `EntityIconRegistry` | entity↔name registry |
| 15 | `SIterator_2` | `ItemIconRegistry` | item↔name registry |
| 16 | `Killsounds2` | `ConfigEntryMigration` | inserts a per-mod config entry |
| 17 | `Killsounds3` | `PotionEffectsMigration` | minimalMode→potionEffectsMode |
| 18 | `Killsounds4` | `ConfigMigrator` | versioned migration registry (33 steps) |
| 19 | `Killsounds5` | `PingMigration` | Ping nametag options |
| 20 | `Killsounds6` | `BlockOutlineMigration` | blockOverlayColor alpha |
| 21 | `Killsounds7` | `PingEntryMigration` | Ping via ConfigEntryMigration |
| 22 | `Killsounds8` | `SkyblockFloorFourMigration` | spirit bow/bear → FLOOR_FOUR |
| 23 | `Killsounds9` | `SnaplookMigration` | snaplookKey → forward/third person |
| 24 | `Killsounds10` | `HudOptionMigration` | Scoreboard/ItemCounter/DirectionHud/Crosshair |
| 25 | `Killsounds11` | `CrosshairF5Migration` | showInF5 + dotSize |
| 26 | `Killsounds12` | `HeightLimitMigration` | split HEIGHT_LIMIT_* |
| 27 | `Killsounds13` | `ParticleChangerMigration` | blood child options |
| 28 | `Killsounds14` | `AutoTextActionsMigration` | action → hideMessage/showTitleAction |
| 29 | `Killsounds15` | `BossbarColorMigration` | barColor → customBossBar |
| 30 | `Killsounds_2` | `StopwatchMigration` | MIGRATED_STOPWATCH |
| 31 | `GuiRewindhandlersHandler2` | `SackCountListener` | SkyBlock sack counter |
| 32 | `GuiRewindhandlersHandler22` | `WorldTimeListener` | `HighlightImpl10` time value |
| 33 | `GuiRewindhandlersHandler22_2` | `DungeonFloorListener` | Catacombs floor from tab list |
| 34 | `GuiRewindhandlersHandler23` | `DoubleHookListener` | "It's a Double Hook!" |
| 35 | `GuiRewindhandlersHandler23_2` | `DungeonScoreListener` | dungeon score/secret/death stats |
| 36 | `GuiRewindhandlersHandler24` | `DungeonSecretListener` | found-secret detection |
| 37 | `GuiRewindhandlersHandler25` | `DungeonTerminalListener` | F7 Goldor terminals |
| 38 | `GuiRewindhandlersHandler26` | `AlertDisplayListener` | transient alert queue renderer |
| 39 | `GuiRewindhandlersHandler2_2` | `DungeonMapListener` | feeds the dungeon map |
| 40 | `GuiRewindhandlersHandler2_3` | `HologramEntityListener` | armor-stand/entity tracking |
| 41 | `Holograms2` | `EntityQuery` | entity query/filter interface |
| 42 | `Holograms3` | `EntitySubscription` | active entity subscription |
| 43 | `ParentTImpl2` | `ClassFilteredEntityQuery` | filters by `Class<T>` |
| 44 | `ParentTImpl3` | `IterableEntityTracker` | tracker over a raw `Iterable` |

No row is a mixin (the `mixin`/`mixinCore` path segments are decompiler
scrambling, not `@Mixin` classes), so no `<Target><Purpose>Mixin` names apply.

## Applier dry run

The import-aware applier resolves every row (many old names are declared in
several packages, so the v1 token rewriter cannot be used):

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-markers-killsounds.tsv
# → [aware-renames] rows=44 skipped=0 files_touched=130 files_renamed=44 mode=dry-run
```

The v1 applier is **not** safe for this map: `Holograms2`/`Holograms3` are
declared in 10–12 packages, `GuiRewindhandlersHandler2` in 10, `Killsounds2/3/4`
in 3, `Markers_2`/`Markers_3` in 2 (`markers` vs `markers.holograms`/`markers`),
etc.:

```
python3 tools/apply_class_renames.py --map tools/renames/classes-markers-killsounds.tsv
# → applied=26 skipped=18 files_touched=76 files_renamed=26 mode=dry-run
```

**Do not run the v1 applier with `--allow-collisions`** — it would rewrite
unrelated `markers.holograms`, `fishing.holograms`, `guiRewindhandlers` and
`framework/feature/killsounds` classes. Use `apply_class_renames_aware.py`.

## Caveats / follow-ups

* **`feature.markers` vs `markers` package.** `Markers_2`/`Markers_3` are also
  declared in `com.moonsworth.lunar.client.markers` (a different, quarantined
  tree). The aware applier resolves by import; the v1 applier skips them.
* **`killsounds` is not the kill-sound mod.** The real feature is
  `framework/feature/killsounds` (`Killsounds`, `KillsoundsType`,
  `mixin/Killsounds4`) and `mod/combat/KillSounds.java`; those names must not be
  confused with this migration tree.
* **`GuiRewindhandlersHandler22`.** Named `WorldTimeListener` from
  `highlight.mixin.fishing.HighlightImpl10` (`getWorldTime()`/`method1()`) and
  its only consumer `SkyblockScathaTrackerHud` (`method5()/20/60/20` → days).
  The firing site is not in this partial tree, so the exact time semantics are
  medium confidence.
* **`Holograms*` infrastructure.** `Holograms`/`Holograms3Iterator`/
  `ParentTHandler`/`ParentTImpl` are *not* in this cluster (they are lazy but
  assigned elsewhere); the names chosen here (`EntityQuery`,
  `EntitySubscription`, `ClassFilteredEntityQuery`, `IterableEntityTracker`)
  assume the family is renamed consistently to the `Entity*` scheme.
* **`ConfigMigrator` (`Killsounds4`) is referenced tree-wide** by config
  load/save code; the aware applier rewrites those call sites, the v1 applier
  cannot (collision with `framework/feature/killsounds/mixin/Killsounds4`).
* No new name was already declared in the tree (checked with the applier's own
  declaration scan; `EntityTracker` is vanilla `net.minecraft.entity.EntityTracker`,
  which none of the new names collide with).