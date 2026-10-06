# Cluster 14 — mixed bucket: `client.nameplate`, `client.util.rewindhandlers`, `lunar.files`, vanilla worldgen, bridge buckets, notifications, telemetry, crosshair/fpsdebug/heightlimit/killsounds

Source revision: `tools/renames/cluster-14.txt` md5 `1c32c2d140115a892fd00b082f09ddd8`
(43 rows across 13 packages).
Map: `tools/renames/classes-nameplate.tsv` (md5 `bc779372c417694333427d9e38166c67`, 39 rename rows + 4 kept).

> The cluster is a **mixed bucket** produced by the inventory's trailing-digit
> heuristic, not a single feature. Every package is named by role below. All
> paths in `cluster-14.txt` exist; the four `net.minecraft.world.gen.feature`
> rows are **kept** (they are already the canonical MCP names, rule 6).

## What this cluster actually is

| package | real identity |
|---|---|
| `client.nameplate` | the **Apollo Transfer/Ping** implementation (`TransferRequest`/`PingRequest` protobufs), plus the `lunarBuildData.txt` build-info reader. The package name is a decompiler artifact (`mixinRewindhandlers/nameplate`). |
| `client.util.rewindhandlers` | a **colour/animation** system: a colour sink (`Rewindhandlers`), colour sources (`RewindhandlersExtension*`) and the `Gui2Extension` WAVE/SHIFT animation enum. Used by BetterMap/HUD text ("chroma text"). |
| `lunar.files` | two unrelated systems merged by the decompiler: the **launcher version manifest** (`ResolvedVersion`/`VersionInfo`, asset index, placeholders) and the **lorenz binary "kin" mappings format** (`BinaryMappingsReader/Writer`). |
| `net.minecraft.world.gen.feature` | vanilla MCP classes (`WorldGenGlowStone1/2`, `WorldGenTaiga1/2`). |
| `bridge.itemcounter.mixin` | duck interfaces for the world/chunk bridge bucket; `Itemcounter2` is the `Chunk` bridge. |
| `bridge.lighting` | the **scoreboard** bridge bucket (`ScoreObjective`/`Score`/`ScorePlayerTeam`/`Scoreboard`). |
| `client.click.chest` | the **toast/notification** system (`ChestType` = info/success/warning/error). |
| `client.fog` | a **telemetry/utility** grab-bag: ignored-exception patterns, JFR GC monitor, generic loaders. |
| `framework.feature.crosshair.mixin` | crosshair option enums (grid size / scale / shape). |
| `framework.feature.debug.fpsdebugmod` (+`.mixin`) | the **FPS debug report** system (`FpsDebugMod`): tasks that assemble a zip of diagnostics. |
| `framework.feature.heightlimit` | the HeightLimit block-scan + mesh renderer. |
| `framework.feature.killsounds.mixin` | the kill-sound **filter/parser** stack. |

Ground truth used: class sources (fields/strings/`toString`), the `@Mixin`
implementors (`ChunkMixin`, `ScoreMixin`, `ScorePlayerTeamMixin`,
`ScoreObjectiveMixin`, `WorldClientMixin`), `tools/mappings-snapshot/classes.tsv`
(obf→MCP: `aox`=WorldGenGlowStone2, `apd`=WorldGenGlowStone1, `apk`=WorldGenTaiga1,
`aps`=WorldGenTaiga2), `tools/work/mappings/normalize-renames.tsv` (provenance:
`mixinMore/Files2`→`files/Files2`, `mixinCore/Files5`→`files/Files5`,
`mixinExtra/Files`→`files/Files_4`, `mixinMore/Files`→`files/Files_5`), the
`restructure/module-renames.tsv` module names (`FpsDebugMod`, `KillSounds`,
`HeightLimit`, `Crosshair`, `ItemCounter`, `RewindHandlers`), and a `javap`
sweep of the runtime `lunar.jar` (the three empty `Itemcounter*` markers).

## Renames (39 rows)

| # | old (owner) | new | evidence (short) |
|---|---|---|---|
| 1 | `FogHandler2` | `PingServerCache` | `Map<String,UUID>` cache, cleared on `HighlightImpl10`; handles `ApolloPacketEvent` |
| 2 | `Nameplate2` | `PingServerData` | `toString()` = `PingServerData(completed,timedOut,endTime,serverDataBridge)` |
| 3 | `Nameplate_2` | `TransferSrvResolver` | DNS TXT `mc_transfer_accept_from` + wildcard host match for transfers |
| 4 | `Nameplate$Type2` | `BuildDataKey` | NESTED: `lunarBuildData.txt` keys BRANCH/GIT_HASH/…/UI_GIT_HASH |
| 5 | `RewindhandlersExtension2` | `SolidColor` | constant ARGB int (`method1`/`getColor` return `field1`) |
| 6 | `RewindhandlersExtension3` | `AnimatedColor` | colour interface + `method11` factory and chroma `method12()` |
| 7 | `RewindhandlersExtension32` | `AnimatedColorImpl` | wraps a `Gui2Extension` (WAVE/SHIFT) animation + colour + speed |
| 8 | `RewindhandlersExtension4` | `MutableColor` | extends `Rewindhandlers`+`…Extension`; default RGBA/HSB setters |
| 9 | `Files2` | `BinaryMappingsFormat` | binary "kin" format constants: magic 99151942, version 1, ext "kin" |
| 10 | `Files5` | `VersionPlaceholder` | (mcVer/mcId/mcpVer/parchmentVer/…) key/value; `${key}` substitution |
| 11 | `Files_4` | `MappingNormalizer` | lorenz `MappingSet` copy/de-dup/re-root/normalize helpers |
| 12 | `Files_5` | `FileHashUtils` | zip validity + SHA-1 + CRC32 + hex |
| 13 | `Itemcounter2` | `ChunkBridge` | `ChunkMixin` implements it (`bridge$getWorld/getBlockState/getSkyLight/…`) |
| 14 | `Itemcounter3` | `BlockAccessBridge` | empty marker, no implementor/reference (world/chunk bucket) |
| 15 | `Itemcounter4` | `WorldBridge` | empty marker, no implementor/reference (world/chunk bucket) |
| 16 | `Lighting2` | `ScoreBridge` | `ScoreMixin` implements it |
| 17 | `Lighting3` | `ScorePlayerTeamBridge` | `ScorePlayerTeamMixin` implements it |
| 18 | `Lighting4` | `ScoreboardBridge` | `bridge$getPlayersTeam/getSortedScores/…`; returned by `getScoreboard()` |
| 19 | `Gui2Iterator22` | `FriendNotification` | toast `"friendsHint"`, friend uuid + user + chat flag |
| 20 | `Gui2Iterator222` | `FriendNotificationLong` | `FriendNotification` with `durationMs * 1.5` |
| 21 | `Gui2Iterator23` | `HostedWorldNotification` | toast `"hostedWorldHint"`, `ChestType.SUCCESS` icon |
| 22 | `Fog2` | `IgnoredExceptionPatterns` | name→`Pattern` registry; `Inventorymod3.method1` filters stack traces |
| 23 | `FogHandler2` | `AbstractMapHandler` | abstract `Fog` handler backed by `Map<T,V>` (`method3` factory) |
| 24 | `Fog_2` | `GcMonitor` | JFR `jdk.GarbageCollection` → `RecordPerfSnapshotRequest` GC stats |
| 25 | `Gui2Extension2` | `CrosshairGridSize` | SMALL/MEDIUM/BIG/HUGE (`crosshairGridSmall`…, 7/15/31/63) |
| 26 | `Gui2Extension3` | `CrosshairScale` | SMALL/NORMAL/LARGE/AUTO (1..4) |
| 27 | `Gui2Extension4` | `CrosshairShape` | CROSS/CIRCLE/ARROW/TRIANGLE/SQUARE/DOT/CIRCLE_DOT/X |
| 28 | `Fpsdebugmod$Data2` | `FpsDebugError` | NESTED: (section, Exception) → `errors.txt` in the report zip |
| 29 | `Fpsdebugmod2` | `FpsDebugTask` | name/enabled/interval/phase/`Future<Fpsdebugmod>` |
| 30 | `Fpsdebugmod3` | `FpsDebugCollector` | runs tasks per phase and merges the archives |
| 31 | `Fpsdebugmod2` (mixin) | `EventBusDebugTask` | `"events"` → `events.txt` |
| 32 | `Fpsdebugmod3` (mixin) | `DynamicListenerDebugTask` | `"dynamic-listeners"` → `dynamic-listeners.txt` |
| 33 | `Fpsdebugmod4` (mixin) | `F3DataDebugTask` | `"f3-data"` → `f3-data.txt` |
| 34 | `Heightlimit2$Data2` | `HeightScanChunk` | NESTED: cached `Itemcounter2` chunk + reusable `BlockPos` for the scan |
| 35 | `Heightlimit2$Data3` | `HeightScanResult` | NESTED: x/y/z IntLists + face ByteList + limit + world |
| 36 | `Heightlimit2` | `HeightLimitRenderer` | async block scan + immediate-mode barrier/darken mesh |
| 37 | `Killsounds2` | `KillEvent` | (MELEE/ARROW/ROD/THROWABLE, `Instant`) |
| 38 | `Killsounds3` | `KillSoundFilter` | name/patterns/`<target>`/HYPIXEL/OTHER/ALL/gameTypes |
| 39 | `Killsounds4` | `KillMessageParser` | `parseTargetName(String, HighlightImpl.Data)` |

### Kept (not renamed)

| old | why |
|---|---|
| `WorldGenGlowStone1`, `WorldGenGlowStone2`, `WorldGenTaiga1`, `WorldGenTaiga2` | already the canonical **MCP 1.8.9** names (`classes.tsv`: `apd`/`aox`/`apk`/`aps`); rule 6 "real names win". The trailing digit is part of the upstream name, not a placeholder. Verified against the 509 reference clients (same filenames/contents). |

## Applier dry runs

```
python3 tools/apply_class_renames.py --map tools/renames/classes-nameplate.tsv
# → applied=21 skipped=18 files_touched=68 files_renamed=21 mode=dry-run

python3 tools/apply_class_renames_aware.py --map tools/renames/classes-nameplate.tsv
# → rows=39 skipped=0 files_touched=96 files_renamed=35 mode=dry-run
```

The v1 simple-name rewriter applies 21 rows and skips 18 as **old-name
collisions across packages** (not map errors):

| skipped old | also declared in |
|---|---|
| `FogHandler2` | `client.fog`, `client.nameplate` |
| `Nameplate2` | 11 `…nameplate` packages |
| `Nameplate_2` | 4 `…nameplate` packages |
| `Type2` | `markers.mixin.gui`, `mod.render` |
| `Gui2Extension2` | 8 packages (highlight/crosshair/tiertagger/…) |
| `Gui2Extension3` | 3 packages |
| `Data2` | 3 packages (+ nested rows) |
| `Data3` | 3 packages (+ nested rows) |
| `Fpsdebugmod2`/`Fpsdebugmod3` | `fpsdebugmod`, `fpsdebugmod.mixin` |
| `Heightlimit2` | `framework.feature.heightlimit`, `client.heightlimit` |
| `Killsounds2/3/4` | `framework.feature.killsounds.mixin`, `client.killsounds.mixin` |

**Do not run this map with `--allow-collisions`.** The aware applier handles all
39 rows (0 skipped), including the four nested rows via the 5th `file` column.
The main agent should apply with `tools/apply_class_renames_aware.py`.

## Caveats / follow-ups

* **`nameplate` package is not nameplates.** It is the Apollo Transfer/Ping
  module. `Highlight3Iterator` (also in the package, but **not** in this cluster)
  is the actual `TransferHandler`; whoever gets it should claim it. `Nameplate`
  (the `lunarBuildData.txt` reader, outer class) is likewise not in the cluster;
  only its nested `Type2` enum is mapped here.
* **`FogHandler2` (`nameplate`)** has no reference anywhere in the tree, so its
  exact key/value meaning is inferred from the package (server address → request
  UUID). Rename if a caller is restored.
* **`Itemcounter3`/`Itemcounter4`** are empty marker interfaces with no
  implementor or reference in this build (`javap` on `lunar.jar` confirms they
  declare nothing). They are named after the world/chunk bridge bucket; if the
  quarantined/restored mixins reveal their targets, adjust.
* **`Files2`** is the binary-mappings *constants* class (from
  `mixinMore/Files2`), but `ResolvedVersion` (`files/mixin/Files.java`) uses the
  simple name as its `@SerializedName("assetIndex")` field type — a decompiler
  cross-merge. Renaming to `BinaryMappingsFormat` makes that field type
  semantically wrong but does not add a new compile failure (the tree is partial).
* **Nested rows** (4, 28, 34, 35) need the aware applier; v1 cannot scope them.
* New names were checked tree-wide with
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java`; none is
  declared elsewhere.
