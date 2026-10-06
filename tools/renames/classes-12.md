# Cluster 12 — `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` (40 classes)

Source revision: `tools/renames/cluster-12.txt` md5 `df8a12325ef2169d81bf814ee44cbd0a`
(40 rows: 38 top-level types + the nested `Holograms6.Data2` / `Holograms6.Type2`).
Map: `tools/renames/classes-12.tsv` (md5 `32e1962111f6bdfcd822e34de7e49bdc`).

> Note: the previous `classes-12.tsv`/`.md` pair described the `client.util`
> cluster that was already applied (`8d81145c`, see `APPLIED.md`). The cluster
> files were regenerated afterwards and cluster 12 is now this package; the
> old map remains in git history.

## What this cluster actually is

The package name is a leftover of the decompiler's package scrambling: **this is
not a fishing feature**. These classes are Lunar's Hypixel SkyBlock dungeon stack:

* **BetterMap** — the dungeon minimap HUD (`BETTERMAP_PRIMARY`,
  `BETTERMAP_SECONDARY`; option keys `mapStyle`, `mapTickStyle`,
  `mapPlayerStyle`, `mapScoreInfo`, `currentRoomInfo`, …; textures
  `skyblock/dungeon/map_icon/*.png`, `skyblock/dungeon/boss_minimap/*.png`).
* **SkyblockDungeonRoutes** — the secret-route loader/recorder/renderer
  (`secretRoutes*` option keys, `.lcroute` files under `Dungeon-Routes/`,
  `routes.json`), plus the spirit-leap map overlay.
* dungeon data files loaded by `com/moonsworth/lunar/client/framework/feature/mod/Module.java`:
  `splits.json` (dungeon timer), `dungeon/quiz-key.json` (Quiz puzzle),
  `dungeon/waterboard-solutions.json` (Water Board solver).
* the websocket proto glue that syncs map/room detection between party members
  (`DungeonUpdatePush`, `DungeonRoomType`, `DungeonRoomRotation`).

Provenance is recoverable from `tools/mappings-snapshot/normalize-renames.tsv`;
the current files were flattened from three original jar subtrees:

| current file(s) | original jar subtree |
|---|---|
| `Holograms2`…`Holograms11`, `HologramsType*`, `JsonDeserializerIterator*` | `…/fishing/holograms/mixinCore/*` |
| `Holograms2`…`Holograms12`, `Holograms_7`…`Holograms_11` | `…/mixinCore/mixin/*`, `…/mixin/mixinCore/*`, `…/mixin/mixinExtra/*` |
| `Holograms_2`…`Holograms_6`, `Gui2Extension*` | `…/mixinCore/mixinNameplate/*` |

Readable ground truth used: `mod/render/SkyblockDungeonRoutes.java`,
`BettermapPrimary.java`, `BettermapSecondary.java`, `SpiritLeapOverlay.java`,
`mod/misc/WaterRoomSolutionGenerator.java`, `mod/misc/SkyblockCollectedSecret.java`,
`framework/feature/mod/Module.java`, and the `secretRoutes*` / `BetterMap*`
option keys in `BettermapSettings` / `SkyblockDungeonRoutes`.

## Renames (40 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Holograms2` | `DungeonRoute` | route model: name/author, sections, requirements, `.lcroute` (de)serialization |
| 2 | `Holograms3` | `MapRoomRenderer` | draws room tick icon (`MapTickIcon`), name/secrets labels, outline/hitbox on BetterMap |
| 3 | `Holograms4` | `RoomPuzzle` | per-room puzzle name + room status, `"???"` → ADJACENT |
| 4 | `Holograms5` | `SpiritLeapMap` | map overlay drawn while Spirit Leap held (`Fishing("spirit-leap-map")`) |
| 5 | `Holograms6` | `WaterBoardSolutions` | `dungeon/waterboard-solutions.json` model (wool/ore block layout) |
| 6 | `Holograms7` | `RouteSection` | section of a route: waypoints + tagged points, active-section tracking |
| 7 | `Holograms8` | `RouteFileLoader` | loads `routes.json` zip + `Dungeon-Routes/**/*.lcroute` |
| 8 | `Holograms9` | `RouteRenderer` | DEPTH/ESP route rendering (boxes, labels, lines, particles) |
| 9 | `Holograms10` | `QuizKey` | `dungeon/quiz-key.json` questions/answers for the Quiz room |
| 10 | `Holograms11` | `DungeonSplits` | `splits.json` catacombs/kuudra timer splits |
| 11 | `Holograms12` | `RouteLink` | `routeName:sectionIndex` reference used by swapOnComplete/swapOnLocked |
| 12 | `HologramsType2` | `MapTickIcon` | `map_icon/*.png` tick textures per room state |
| 13 | `HologramsType3` | `RoomShape` | `1x1`…`2x2`/`L`/`boss` room template shape |
| 14 | `HologramsType4` | `KingRelic` | F7 king relics: `*_KING_RELIC` items, relic locations |
| 15 | `HologramsType5` | `DungeonRoomType` | SPAWN/CLEAR/MINIBOSS/PUZZLE/… ↔ websocket `DungeonRoomType` |
| 16 | `HologramsType6` | `SecretType` | BAT/CHEST/ITEM_DROP/ESSENCE/FAIRY_SOUL secret kinds |
| 17 | `HologramsType7` | `MapGridShape` | map grid layouts 4x4…6x6 detected from map colours |
| 18 | `HologramsType8` | `PuzzleType` | puzzle solver kinds (ICE_FILL, THREE_WEIRDOS, QUIZ, …) |
| 19 | `HologramsType9` | `ScoreGrade` | S+/S/A/B/C/D score grades |
| 20 | `HologramsType_2` | `BossMapFloor` | boss minimap floor textures F1…F7_x |
| 21 | `HologramsType_3` | `RoomRotation` | EAST/SOUTH/WEST/NORTH ↔ websocket `DungeonRoomRotation` |
| 22 | `Holograms_2` | `DungeonUpdateAction` | outgoing `DungeonUpdate` + apply to map; implemented by `holograms/HologramsType22..26` |
| 23 | `Holograms_3` | `DungeonSync` | applies incoming `DungeonUpdatePush`, broadcasts own updates |
| 24 | `Holograms_4` | `RoomDetection` | room hash/rotation detection + rotation coordinate transforms |
| 25 | `Holograms_5` | `RoomStateChange` | (from,to,timestamp) room-state timeline event |
| 26 | `Holograms_6` | `DungeonStats` | map data + run stats (rooms, secrets, crypts, score, deaths) interface |
| 27 | `Holograms_7` | `RoomSecret` | room-template secret: type/pos/entrance/levers/superboom/redstone key |
| 28 | `Holograms_8` | `RouteRequirements` | cached DUNGEONBREAKER requirement check for BREAK_BLOCK sections |
| 29 | `Holograms_9` | `BettermapSettings` | per-HUD BetterMap option/colour/scale holder (~50 options) |
| 30 | `Holograms_10` | `RouteTracker` | hooks render/tick/sound/interaction events; draws route and records waypoints |
| 31 | `Holograms_11` | `ParticleTrail` | particle trail between two points (FLAME route path) |
| 32 | `Gui2Extension2` | `MapTickStyle` | option `MapTickStyle`/`SecondaryTickStyle`/`PuzzleTickStyle` |
| 33 | `Gui2Extension3` | `PlayerNameDisplay` | option `PlayerNames`: NEVER/LEAP_HELD/ALWAYS |
| 34 | `Gui2Extension4` | `RoomInfoPosition` | option `CurrentRoomInfo`: OFF/LEFT/RIGHT |
| 35 | `Gui2Extension5` | `MapScoreStyle` | option `MapScoreInfo`: NONE/SIMPLIFIED/LEGAL_MAP |
| 36 | `Gui2Extension6` | `MapStyle` | option `MapStyle`: LEGAL_MAP/HYPIXEL/CUSTOM |
| 37 | `Gui2Extension7` | `MapPlayerStyle` | option `MapPlayerStyle`: OFF/ICONS/SELF_ICON/HEADS |
| 38 | `JsonDeserializerIterator2` | `QuizKeyDeserializer` | `JsonDeserializer<QuizKey>` for `quiz-key.json` |
| 39 | `Data2` (nested) | `WaterBoardBlock` | nested `WaterBoardSolutions.Data2`: offset + material + FLIP/UP/DOWN |
| 40 | `Type2` (nested) | `WaterBoardMaterial` | nested `WaterBoardSolutions.Type2`: COAL/GOLD/…/WATER block |

Out-of-cluster companion (not listed in `cluster-12.txt`, so not in the map):
plain `HologramsType.java` (PRIMARY `"primary"` / SECONDARY `"secondary"` /
SPIRIT_LEAP `"spiritLeap"`, consumed by `BettermapPrimary`,
`BettermapSecondary`, `SpiritLeapOverlay` and `BettermapSettings`) is best named
`BettermapProfile`; whoever gets that file should claim it.

## Applier dry run, and the collision rows

`python3 tools/apply_class_renames.py --map tools/renames/classes-12.tsv`:

```
applied=25 skipped=15 files_touched=95 files_renamed=25 mode=dry-run
```

25 rows are unambiguous and apply with the simple tree-wide rewriter. The 15
skips are **old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Holograms2` | `fishing.holograms.holograms`, `.mixin`, `mod.holograms`, `rewind.holograms`, `client.holograms`, `markers.holograms`, … (11 packages) |
| `Holograms3`, `Holograms4`, `Holograms5`, `Holograms6`, `Holograms7`, `Holograms8` | `framework.feature.rewind.holograms`, `client.holograms`, `markers.mixin.holograms`, … |
| `HologramsType2` | `fishing.holograms.holograms`, `markers.mixin.holograms.mixin` |
| `Holograms_2` | `markers.holograms` |
| `Gui2Extension2`, `Gui2Extension3`, `Gui2Extension4` | 14 / 8 / 4 packages (armorstatus, crosshair, keystrokes, lighting, …) |
| `JsonDeserializerIterator2` | `framework.feature.mod.fishing` (a different deserializer) |
| `Data2`, `Type2` | 15 / 7 packages; also the nested-class rows |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
simple name in unrelated `client.holograms`, `markers.holograms`,
`fog.holograms`, armorstatus/crosshair `Gui2Extension*` classes, merging
distinct types. The 15 collision rows need the planned import/package-aware
rewriter (same follow-up as `classes-07.md`, `classes-15.md`, `classes-23.md`).

## Caveats / follow-ups

* **Nested rows 39–40** (`Holograms6.Data2`, `Holograms6.Type2`) are real nested
  types inside `WaterBoardSolutions.java`. The applier matches declarations by
  simple name and both names are declared in many other packages, so they are
  skipped by design (and its `Outer$Inner` handling does not apply to nested
  types left in place). They need a nested-aware pass that renames inside the
  file, together with the sibling nested `Data` and `Data2.Type` enums.
* **Stale `fog.holograms` references.** 76 references to the quarantined
  `com.moonsworth.lunar.client.fog.holograms.Holograms*` classes still exist
  (e.g. `client/mod/hud/Tab.java`, `client/mod/misc/RewindHandlers.java`,
  `ShaderDebugMod.java`, `client/fog/Lighting3Loader2.java`). Applying the
  applied rows rewrites those dangling tokens too (`Holograms9/10/11/12` name
  collisions are *not* detected because those classes are absent from the tree —
  they live in `tools/work/quarantine/src/…`). The affected files already cannot
  compile because of the missing imports, so this creates no new failures, but
  the quarantine restore should be aware of it.
* Names added by this cluster (`DungeonRoomType`, `RoomRotation`, `RoomShape`,
  `MapPlayerStyle`, `BettermapSettings`, …) were checked with the applier's own
  declaration scan; no row was skipped for "new name already declared". Manual
  re-check:
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java`.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-12.tsv
# → applied=25 skipped=15 files_touched=95 files_renamed=25 mode=dry-run
```
