# Cluster 65 — `com.moonsworth.lunar.client.highlight.mixin.fishing` (wave 5)

Source: `tools/renames/cluster-65.txt` (35 rows = 25 top-level + 10 nested
`DataN`). Map: `tools/renames/wave5/classes-65.tsv` — **35 rows, 0 skipped**.
All 35 paths exist; no `net.minecraft.*` rows, no shaded third-party code.

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-65.tsv
[aware-renames] 35 rows (10 nested); 9808 java files
[aware-renames] rows=35 skipped=0 files_touched=179 files_renamed=25 mode=dry-run
```

## 1. What this package actually is

**Not mixins, not fishing.** Despite the package name, every class here is an
event-bus data class: all of them extend `client.highlight.Highlight` (the
stale-jar dispatch shim) or `client.highlight.HighlightImpl` (the cancellable
base), are constructed in a mixin hook and posted through
`Highlight2.method29().method12(...)` (= `LunarEventBus`), and are consumed by a
`handle(...)` in a HUD module/listener. `@Mixin` appears nowhere in the package;
same finding as `classes-highlightfishing.md` / wave-5 `notes-64.md`.

The tree carries **two live copies of this event family**, because the rescue
sweep `b384aca20` restored the pre-rename copies at the old path:

| copy | origin | status |
|---|---|---|
| `com.moonsworth.lunar.client.event.mixin.fishing` | renamed in place by `107715b86` (`classes-highlightmixinfishing`), then moved into `event` by `d6a452378` | canonical names (`EventTick`, `EventBlockChange`, …) |
| `com.moonsworth.lunar.client.highlight.mixin.fishing` (this cluster) | restored by `b384aca20` from the jar | still placeholders `HighlightImplN`/`DataN` — what this map names |

Both halves are load-bearing (refs below, e.g. `HighlightImpl2` 88 files /
`EventTick` 132; `HighlightBase4` 29 / `EventWorld` 74), so this is the
`classes-36/40/57` / sibling `classes-64` duplicate situation, not a byte-identical
same-package twin.

## 2. Why the canonical twin names cannot be reused

`tools/apply_class_renames_aware.py` refuses a `new` name declared anywhere in
the tree. The old canonical map is refused for every top-level row, and its
nested rows only "work" because the nested path has no duplicate guard (they
would recreate the twin's nested names a second time):

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/classes-highlightmixinfishing.tsv
  SKIP HighlightImpl2 -> EventTick: new name already declared
  ... (all 25 top-level rows) ...
[aware-renames] rows=35 skipped=25 files_touched=46 files_renamed=0 mode=dry-run
```

So the map uses **unique descriptive variant names** (same practice as wave-5
`classes-36/40/57` and `classes-64`): none of the 35 names is declared in
`src/main/java`, and none is used as a `new` name by any other `wave5/*.tsv` map
present at write time. The canonical twin is the *intended final name* once the
duplicate copies are merged/deleted (pairing table below); merge with
`tools/find_duplicates.py`, which already pairs by content.

## 3. Pairing (old → map name → canonical twin → refs old/twin)

Refs = files importing the simple name via `highlight.mixin.fishing.<Old>` /
`event.mixin.fishing.<Twin>` (own file excluded). Canonical assignment is fixed
by the renamed consumers, whose parameter names still spell the placeholder
(`EventTick highlightimpl21`, `EventBlockPlace highlightimpl81`, …), and by the
firers (e.g. raw `MinecraftEventMixin.lunar$preRunTick` fires `HighlightImpl2`
at `runTick` HEAD; the renamed `ReplayClock` fires `EventTick::new` at the same
spots; raw `ChunkMixin2.onUnload` fires `Data8`, renamed `ChunkLightingMixin`
fires `EventChunkUnload`).

| old | new (map) | canonical twin | refs | why |
|---|---|---|---|---|
| `HighlightImpl2` | `EventClientTick` | `EventTick` | 88 / 132 | `public static int field1` tick counter, fired at `runTick` HEAD, `field1%20==0` drives `EventSecond`; F3 `Fpsdebugmod4` prints the counter |
| `HighlightImpl3` | `EventTickEnd` | `EventRewindTick` | 4 / 2 | fired at `runTick` RETURN (`lunar$postRunTick`); Waila "No target" refresh, 1.7 animation, chunk reload |
| `HighlightImpl4` | `EventEverySecond` | `EventSecond` | 28 / 56 | fired only when `HighlightImpl2.field1%20==0` (= every second) |
| `HighlightImpl5` | `EventBlockBreakingProgress` | `EventBlockBreakProgress` | 5 / 6 | `(entityId, pos, progress)` + `getProgress()`; fired from `destroyBlockPartially$v1_7`/`sendBlockBreakProgress$v1_8` |
| `HighlightImpl6` | `EventRewindRefresh` | `EventRewindUpdate` | 1 / 3 | empty; rewind/debug counters (`OptimizationDebugMod`, `ProfilerDebugMod`) |
| `HighlightImpl7` | `EventEntityPos` | `EventEntityPosition` | 2 / 0 | `@Annotation2(min=8)`, single `Horsestats20Extension` position; only the `Highlight3Iterator23` handler is left |
| `HighlightImpl8` | `EventBlockPlacement` | `EventBlockPlace` | 2 / 2 | block + state + pos; crop checks in the nameplate/hologram logic |
| `HighlightImpl9` | `EventBlockModified` | `EventBlockChange` | 8 / 11 | `(pos, new state, old state)` from `ChunkMixin2.lunar$onBlockChanged$v1_7/$v1_8` (`setBlockIDWithMetadata`/`setBlockState`) |
| `HighlightImpl10` | `EventWorldTimeUpdate` | `EventWorldTime` | 3 / 2 | two longs, `getWorldTime()`; `GuiRewindhandlersHandler22` handler; `TimeChanger` cancels the twin |
| `HighlightImpl11` | `EventReplayFrame` | `EventRewindFrame` | 3 / 4 | float elapsed `(now-field37)/50.0F` (twin firer `RewindHandlers.method10/11`), rewind nameplate handlers |
| `HighlightImpl12` | `EventBlockPick` | `EventPickBlock` | 4 / 3 | empty; rewind recorder (`RewindHandlers5.method9`, renamed twin `RewindRecorder` registers `EventPickBlock`) |
| `HighlightImpl13` | `EventSoundPlay` | `EventPlaySound` | 6 / 13 | sound id/category/volume/pitch/pos; `"block.note_block.pling"` etc. |
| `HighlightImpl14` | `EventWorldRender` | `EventRenderWorld` | 1 / 0 | `Bridge14_3` level renderer + float; fired from `EntityRendererFogMixin` |
| `HighlightImpl15` | `EventParticleSpawn` | `EventSpawnParticle` | 6 / 19 | cancellable `spawnParticle$v1_7/$v1_8` payload (type/pos/velocity/colour) |
| `HighlightImpl16` | `EventExplosionSpawn` | `EventExplosion` | 0 / 2 | world + x/y/z + size (`getX/getY/getZ`) |
| `HighlightImpl17` | `EventBlockOutlineRender` | `EventRenderBlockOutline` | 1 / 2 | abstract outline event, nested modern/legacy variants (`drawSelectionBoundingBox`) |
| `HighlightImpl18` | `EventHorizonQuery` | `EventGetHorizon` | 1 / 2 | mutable double, `@ModifyReturnValue(method="getHorizon")` |
| `HighlightImpl19` | `EventWorldEffectRecord` | `EventWorldEffect` | 4 / 3 | `(entity, type, pos, data)` serialized by the rewind nameplate core |
| `HighlightImpl20` | `EventItemEntity` | `EventEntityItem` | 1 / 0 | public `ItemStackBridge` + entity; fired by `ItemBucketMixin.lunar$onItemRightClick$v1_12` |
| `HighlightImpl21` | `EventBlockUpdateNotify` | `EventBlockUpdate` | 5 / 9 | abstract `(pos, block)` + nested `Data`; consumers via `.Data` (secrets/relics) |
| `HighlightImpl22` | `EventRunDirectory` | `EventGameDirectory` | 7 / 4 | `(String, File)`; Minimap builds `new File(dir, "minimap")` |
| `HighlightImpl23` | `EventCursorPosition` | `EventMousePosition` | 4 / 4 | empty; rewind recorder writes `Nameplate2Impl7(x, y)` |
| `HighlightBase2` | `EventChunkLifecycle` | `EventChunk` | 5 / 4 | abstract base holding the Chunk bridge; subclasses load/unload |
| `HighlightBase3` | `EventRenderTickPhase` | `EventRenderTick` | 10 / 10 | abstract base holding partialTicks; subclasses start/end |
| `HighlightBase4` | `EventWorldLifecycle` | `EventWorld` | 29 / 74 | abstract base holding the World bridge; subclasses change/load |
| `Data10` | `EventBlockInteract` | `EventPlayerBlockInteractBase$EventPlayerBlockInteract` | — | nested `HighlightBase$Data10`: pos + blockEntity |
| `Data11` | `EventBlockInteractExtended` | `…$EventPlayerBlockInteractExtended` | — | nested `HighlightBase$Data11`: adds a `@Nullable` blockEntity |
| `Data8` | `EventChunkUnloaded` | `EventChunk$EventChunkUnload` | — | `ChunkMixin2` `onUnload$v1_12` RETURN |
| `Data9` | `EventChunkLoaded` | `EventChunk$EventChunkLoad` | — | `ChunkMixin2` `onLoad$v1_12`/`onChunkLoad$v1_7` TAIL |
| `Data5` | `EventRenderTickBegin` | `EventRenderTick$EventRenderTickStart` | — | `EntityRendererHandlerMixin2` after `FMLCommonHandler.onRenderTickStart` |
| `Data6` | `EventRenderTickFinish` | `EventRenderTick$EventRenderTickEnd` | — | same mixin at `onRenderTickEnd` |
| `Data3` | `EventWorldChanged` | `EventWorld$EventWorldChange` | — | `Minecraft.loadWorld` HEAD; renamed twin users carry `EventWorldChange data31` |
| `Data4` | `EventWorldLoaded` | `EventWorld$EventWorldLoad` | — | `Minecraft.loadWorld` RETURN; renamed twin users carry `EventWorldLoad data41` |
| `Data3` | `EventBlockOutlineRenderModern` | `EventRenderBlockOutline$…Modern` | — | `HighlightImpl17$Data3`, `@Annotation2(min=6)`, `Bridge5_16`+`Itemcounter_4` |
| `Data4` | `EventBlockOutlineRenderLegacy` | `EventRenderBlockOutline$…Legacy` | — | `HighlightImpl17$Data4`, `@Annotation2(max=5)`, `GlStateManager`+AABB |

Nested rows carry the owner file in the 5th column and the owner in the
evidence so the applier's nested-first path fires (dry run: `10 nested`, all
with hits, no `WARN`).

## 4. Notes / caveats

* **`EventTick`/`EventRewindTick` history.** `107715b86` renamed the
  `HighlightImpl2.java`/`HighlightImpl3.java` files to
  `EventRewindTick`/`EventTick` (swapped). The surviving consumers fix the
  semantics the other way round: `ReplayClock` fires `EventTick::new` exactly
  where raw `Rewindhandlers` fires `HighlightImpl2`, and `WailaHud.method1`
  takes `EventTick highlightimpl21` while `method3` takes
  `EventRewindTick highlightimpl31` (raw `Waila` handles `HighlightImpl2` /
  `HighlightImpl3` in those methods). The pairing table above follows the
  consumers, matching the revised (never-applied)
  `tools/renames/classes-highlightmixinfishing.tsv`.
* **`HighlightImpl12` naming.** The revised `classes-highlightmixinfishing.tsv`
  proposed `EventEntityRemove` for it, but that name belongs to the *other*
  event family (`event/fishing/EventEntityRemove`) and the tree settled on
  `EventPickBlock` (`166b668b2`), confirmed by the rewind recorder consumers
  (`RewindRecorder`/`PlayerStateRecorder` register `EventPickBlock`, raw users
  `RewindHandlers5`/`RewindhandlersNameplateCore` register `HighlightImpl12`).
* **Leftovers not in this cluster** (names are not "lazy" by the inventory
  rules, so no cluster lists them): the package-root `HighlightBase` (outer of
  the `Data10/11` rows; canonical twin `EventPlayerBlockInteractBase`),
  `HighlightImpl` (boolean payload; canonical twin `EventDropItem`) and the
  nested `HighlightImpl21$Data` (canonical twin `EventBlockUpdate$BlockUpdate`).
  Recommend a nested-name/leftovers pass, as `notes-57.md` §5 does for
  `bridge.horsestats`.
* **Merge/dedupe.** Both copies compile and are called, so the map is not
  cosmetic: deleting the `highlight.mixin.fishing` copy requires repointing 179
  files at the `event.mixin.fishing` names (the twin column). Keep the
  `event.mixin.fishing` names; `HighlightImpl15`/`HighlightBase4` in particular
  have 6/29 live users here against 19/74 on the twin side.
* **Collision scan** (at write time): none of the 35 `new` names is declared in
  `src/main/java` and none is used as a `new` name by `classes-36/37/38/39/40/…`
  or the sibling `classes-64/66/67/68/69/70` maps.
