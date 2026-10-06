# Cluster 08 — `mod.misc` + fishing-holograms neighbours (39 classes)

Source revision: `tools/renames/cluster-08.txt` md5 `7068f4ada7aa4bbf89803cecfbc6540e`
(39 rows: 27 top-level types + 12 nested types).
Map: `tools/renames/classes-modmisc.tsv` md5 `691fc78257926dedfeb6801dfd362b08`.

The cluster is a grab-bag of four unrelated areas that happened to be adjacent in
the inventory: the `client.mod.misc` SkyBlock mods + Rewind replay components,
the `fishing.holograms.holograms` dungeon-map subpackage, the
`holograms.rewindhandlers` chat parser, the misnamed `bridge.slayer` OptiFine
bridge, and the `framework.feature.minimap` storage/provider layer.

## What this cluster actually is

* **`client.mod.misc` SkyBlock mods** — `SkyblockArrowAlign` (arrow-align puzzle
  solver over item frames), `SkyblockChestProfit` (dungeon chest profit HUD),
  `SkyblockTrophyFishExchangeRate` (magmafish/lotus HUD),
  `SkyblockForagingBeaconSolver` (Galatea/Torrhus "Match the Beat" beacon solver).
* **`client.mod.misc` Rewind replay components** — `RewindHandlers` is the main
  replay handler; `RewindHandlers2` the audio manager, `RewindHandlers3` the
  abstract sub-handler base, `RewindHandlers4` the render queue/video encoder and
  `RewindHandlers5` the recorder.
* **`fishing.holograms.holograms`** — the dungeon-map model layer: a held
  "Magical Map" item snapshot, a placed room instance, the room-state enum and the
  five outgoing `DungeonUpdate` action implementations (the interface
  `DungeonUpdateAction` lives in the parent `fishing.holograms` package and was
  named by `classes-12`).
* **`holograms.rewindhandlers`** — `Rewindhandlers` parses Hypixel chat lines into
  typed `Extension` messages; `GuiRewindhandlersHandler2` is the tick listener
  that feeds the current chat line into that parser and re-fires it on the bus.
* **`bridge.slayer`** — despite the package name this is **not Slayer quests**:
  it is Lunar's OptiFine integration bridge (`net.optifine.Config`,
  `net.optifine.shaders.Shaders`, `CustomItems`, `CustomColors`,
  `ConnectedTextures`, `shaders.Program`). Confirmed by the implementors in
  `com/moonsworth/lunar/{legacy,v1_7,v1_8}/optifine/wrapper/*` and the
  `MinecraftOptifineWrapperMixin` log line `"[Bridge] Using OptiFine wrapper"`.
* **`framework.feature.minimap`** — the minimap storage/provider layer used by the
  `mod.render.Minimap` HUD: region PNG cache, region `.dat` file store, the update
  task queue, and the marker providers for entities and waypoints.

Ground truth used: `Gui2Extension` (fishing) maps the chat types to
`ISLAND_VISITOR`/`ALL`/`PARTY`/`GUILD`/`GUILD_OFFICER`/`DM_RECEIVE`/`DM_SEND`/`COOP`;
the OptiFine reference clients in
`~/Downloads/ABDM/Compressed/mc-client-sources-main/sources/`
(`net/optifine/{Config,CustomItems,CustomColors,ConnectedTextures,ConnectedProperties}.java`,
`net/optifine/shaders/{Shaders,Program}.java`); and the runtime wrapper sources in
`tools/work/quarantine/src/com/moonsworth/lunar/**/optifine/wrapper/`.

## Renames (39 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Data2` (nested `SkyblockArrowAlign`) | `ArrowFrame` | tracked item-frame arrow panel: frame + expected `Direction2D` + match counter |
| 2 | `Data2` (nested `SkyblockChestProfit`) | `ProfitEntry` | per-chest-type cost/value/alreadyOpened + item→value map; profit = value−cost |
| 3 | `Data2` (nested `SkyblockTrophyFishExchangeRate`) | `ExchangeRateHud` | `MixinCore8<List<HudLine>>` HUD for magmafish/lotus counts |
| 4 | `RewindHandlers2` | `RewindAudioManager` | set of playing `AudioStream`s: add/contains/cleanup/reload/setTime |
| 5 | `RewindHandlers3` | `RewindHandler` | abstract replay sub-handler base; holds `ValueHolder<Nameplate4>` |
| 6 | `RewindHandlers4` | `RewindRenderQueue` | `render_queue/*.json`, FFMPEG, frameCount, opens rendered video |
| 7 | `RewindHandlers5` | `RewindRecorder` | recorder list (Keyframe/Packet/…), captures, tick, RECORDING/PAUSED/STOPPED |
| 8 | `Type2` (nested `SkyblockForagingBeaconSolver`) | `BeaconPitch` | LOW/NORMAL/HIGH note pitch |
| 9 | `Type3` (nested `SkyblockForagingBeaconSolver`) | `BeaconSpeed` | ONE..FIVE beat speeds (55…15 ticks) |
| 10 | `Holograms2` | `DungeonMapItem` | held "Magical Map": `Itemcounter2_3` map data + map id |
| 11 | `Holograms3` | `RoomInstance` | placed room: `RoomTemplate` + hash + origin + `RoomRotation` |
| 12 | `HologramsType2` | `RoomState` | FAILED/ADJACENT/OPENED/CLEARED/COMPLETED + `asColor()` |
| 13 | `HologramsType22` | `EarlyInDoorAction` | `DungeonUpdateAction`: proto `EarlyInDoor` (type + 2 positions) |
| 14 | `HologramsType23` | `RoomSecretsAction` | `DungeonUpdateAction`: proto `RoomSecrets` (current/max) |
| 15 | `HologramsType24` | `EarlyInRoomAction` | `DungeonUpdateAction`: proto `EarlyInRoom` (components + type) |
| 16 | `HologramsType25` | `RoomDetectionAction` | `DungeonUpdateAction`: proto `RoomDetection` (hash/origin/rotation) |
| 17 | `HologramsType26` | `EarlyInUnknownAction` | `DungeonUpdateAction`: proto `EarlyInUnknown` (position) |
| 18 | `Data2` (nested `Rewindhandlers`) | `CoopChatMessage` | `"Co-op > …"` (Gui2Extension `COOP`) |
| 19 | `Data3` (nested `Rewindhandlers`) | `PartyChatMessage` | `"Party > …"` (`PARTY`) |
| 20 | `Data4` (nested `Rewindhandlers`) | `DirectMessageReceived` | `"From …"` (`DM_RECEIVE`) |
| 21 | `Data5` (nested `Rewindhandlers`) | `GuildChatMessage` | `"Guild > …"` (`GUILD`) |
| 22 | `Data6` (nested `Rewindhandlers`) | `GuildOfficerChatMessage` | `"Officer > …"` (`GUILD_OFFICER`) |
| 23 | `Data7` (nested `Rewindhandlers`) | `IslandVisitorChatMessage` | chat with `[✌]` emblem (`ISLAND_VISITOR`) |
| 24 | `Data8` (nested `Rewindhandlers`) | `DirectMessageSent` | `"To …"` (`DM_SEND`) |
| 25 | `GuiRewindhandlersHandler2` | `ChatMessageListener` | parses current chat line → `HighlightImpl5` on the bus |
| 26 | `Slayer2` | `OptifineBridge` | OptiFine facade (`Slayer3Renderer22`): config/shaders/items/CTM/colors |
| 27 | `Slayer3` | `ShadersBridge` | `net.optifine.shaders.Shaders` (`Slayer3Handler`) |
| 28 | `Slayer4` | `OptifineConfigBridge` | `net.optifine.Config` (`Slayer3Renderer2222`) |
| 29 | `Slayer5` | `CustomColorsBridge` | `net.optifine.CustomColors` (`Slayer3Impl`) |
| 30 | `Slayer6` | `CustomItemsBridge` | `net.optifine.CustomItems` (`Wrapper2`) |
| 31 | `Slayer7` | `ShaderProgramBridge` | `net.optifine.shaders.Program` (`ProgramMixin`) |
| 32 | `Slayer8` | `ConnectedTexturesBridge` | `net.optifine.ConnectedTextures` (`Slayer3Impl22`) |
| 33 | `Minimap2` | `MinimapImageCache` | 512×512 region PNG read/write + Guava cache |
| 34 | `Minimap3` | `MinimapRegionFile` | static region `.dat` reader/writer (deflated 32×32 chunks) |
| 35 | `Minimap4` | `MinimapConstants` | constants 16/4/32/5 |
| 36 | `Minimap5` | `MinimapUpdateTask` | `Type SINGLE_CHUNK/FULL_MAP` + `Data(pos, colors)` |
| 37 | `Minimap_2` | `MinimapMarkerProvider` | abstract `method1(player,radius,center) -> List<Minimap2_2<T>>` |
| 38 | `MinimapImpl2` | `MinimapPlayerProvider` | `Minimap_2<BridgeExtension2_5>`: nearby players |
| 39 | `MinimapImpl3` | `MinimapWaypointProvider` | `Minimap_2<GuiHandler2>`: visible waypoints |

## Applier dry run

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-modmisc.tsv
# → [aware-renames] 39 rows (12 nested); 6594 java files
# → rows=39 skipped=0 files_touched=131 files_renamed=27 mode=dry-run
```

Use the **aware** applier (`tools/apply_class_renames_aware.py`), not the v1
`apply_class_renames.py`: the old names `Holograms2`, `Holograms3`,
`HologramsType2`, `Minimap2`, `Minimap3`, `Minimap4` are each declared in two or
more packages, so the v1 global rewriter would corrupt the unrelated parent/mixin
copies. The aware applier resolves each reference by import/package.

## Caveats / follow-ups

* **Nested rows** (12): the aware applier detects them from the evidence
  (`Owner$Old`) and renames the declaration inside the owner file plus every
  qualified reference (`Owner.Inner` / `Owner$Inner`). The 5th `file` column is
  informational for these rows (it is only consulted for top-level rows).
* **`bridge.slayer` is misnamed.** The package really is Lunar's OptiFine
  integration bridge. The sibling `Slayer` (→ best named `ConnectedPropertiesBridge`)
  and `SlayerType` (→ `ConnectedTextureMethod`) are *not* in this cluster; whoever
  owns them should follow the OptiFine naming. `client.Slayer` is unrelated — it is
  a logging helper (`Slayer.method8("Minimap", …)`).
* **Out-of-cluster companions** in the same packages (not listed in
  `cluster-08.txt`, so not in the map): `fishing.holograms.holograms.Holograms`
  (the room template — best named `RoomTemplate`), `…holograms.HologramsType`
  (room shape name enum), `framework.feature.minimap.Minimap` (the texture
  manager), `mod.misc.RewindHandlers` (the main replay handler), and the
  `framework.feature.minimap.mixin.Minimap*` classes.
* The nested `Rewindhandlers.Data` (plain `ALL` chat) is deliberately left alone;
  only `Data2`–`Data8` are in the cluster.
* Names added here were checked with a tree-wide declaration scan; no new name is
  already declared anywhere (`ArrowFrame`, `RoomState`, `ShadersBridge`,
  `MinimapMarkerProvider`, … all unique).