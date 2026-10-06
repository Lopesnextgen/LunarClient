# Cluster 05 — `com.moonsworth.lunar.client.highlight.mixin.fishing` (35 rows)

Source revision: `tools/renames/cluster-05.txt` md5 `f3c5225fbeee2e30a3849e75b3443dbf`
(35 rows: 25 top-level types + 10 nested `DataN` rows).
Map: `tools/renames/classes-highlightmixinfishing.tsv` md5 `532ad113c627a1f4f16cfa74a00af8c4`.

## What this cluster actually is

**Not mixins.** Despite the `mixin` segment in the package name, every class here
extends the event base `Highlight`/`HighlightImpl` and is constructed inside a
mixin hook that posts it through `LunarEventBus.method29().method12(...)`, then
consumed by a `handle(...)` in a HUD module / listener. `@Mixin` appears nowhere
in the package (grep: no matches). This matches the sibling maps
`classes-23.md` (reporting *"names below do not use the mixin convention — these
are plain event data classes"*) and `classes-26.md` (`Highlight` = event base,
`HighlightImpl` = cancellable event). So the countdown for this cluster is named
`Event<Purpose>` per the codebase's own `EventRenderTooltip` / `EventKeyInput` /
`EventPreAttackEntity` constants.

Two independent event sets exist under the same names:

| package | origin |
|---|---|
| `…highlight.fishing` | flattened from `…highlight/mixin/fishing/mixinCore/*` (`normalize-renames.tsv`) |
| `…highlight.mixin.fishing` (this cluster) | the original `…highlight/mixin/fishing/<obf>` subtree (`restructure/remaining-renames.tsv` maps e.g. `CORRCOHCRHOHHOIHOIOICORROHOOOO` → `HighlightImpl2`, `ICRHORIIHOHROHOHOCOOHOOCOORRHO` → `HighlightBase`) |

The two are **different classes with the same placeholder numbers** (diffing
`HighlightImpl2`/`4`/`5`/`7`/`14`/`20`/`21`/`Base` shows different fields and
owners), so names cannot be shared between them. The cluster's firers are the
`legacy/mixin/WorldEventMixin`, `ChunkLightingMixin`, `ChunkProviderClientMixin`,
`ExplosionMixin`, `RenderGlobalEntityOutlineMixin`, `replaymod/mixin/EntityRendererHandlerMixin`
and the rewind recorder; the `highlight.fishing` set is fired by
`EntityLivingBaseEventMixin`/`EntityPlayerSPEventMixin`/`RenderPlayerEventMixin`
and friends.

`Highlight` and `HighlightImpl` (the unnumbered top-level files) are already
in the tree but **not listed in `cluster-05.txt`** and are not renamed here; the
nested rows reference their owners.

## Renames (35 rows)

| # | old | new | conf | evidence (short) |
|---|-----|-----|------|------------------|
| 1 | `HighlightImpl2` | `EventTick` | high | global tick: `static int field1` counter, `Debug.EVERY_TICK`, fired every tick (`Rewindhandlers`, `EntityRenderer4.bootstrap`), 125 importers |
| 2 | `HighlightImpl3` | `EventRewindTick` | med | empty event fired once per iteration of `RewindHandlers.method17` playback loop; `method9` drains the nameplate queue; `Waila` clears its overlay |
| 3 | `HighlightImpl4` | `EventSecond` | high | fired only after `now-field4 >= 1000L` (`GuiRewindhandlers3`); `Debug.EVERY_SECOND`/`EVERY_15_SECONDS`/`EVERY_30_SECONDS` |
| 4 | `HighlightImpl5` | `EventBlockBreakProgress` | high | fired on `destroyBlockPartially`/`sendBlockBreakProgress` with (entityId,pos,progress); `getProgress()` |
| 5 | `HighlightImpl6` | `EventRewindUpdate` | low | handled by `RewindHandlers.method6` (advances the playback loop); counted by `OptimizationDebugMod`/`ProfilerDebugMod` |
| 6 | `HighlightImpl7` | `EventEntityPosition` | low | `@VersionGate(min=8)`, single `Horsestats20Extension` position; no firer/user left in tree |
| 7 | `HighlightImpl8` | `EventBlockPlace` | med | block + state + pos; nameplate checks crop blocks for `HighlightType3`, `RouteTracker.method5` uses the pos |
| 8 | `HighlightImpl9` | `EventBlockChange` | high | fired on `Chunk.setBlockIDWithMetadata`/`setBlockState` with (pos,new,old) |
| 9 | `HighlightImpl10` | `EventWorldTime` | med | two longs (`getWorldTime()`); `TimeChanger.method7` cancels it, `WorldTimeListener` stores it |
| 10 | `HighlightImpl11` | `EventRewindFrame` | med | per-frame rewind event with float elapsed `(now-field37)/50.0F`; `RewindHandlers3Updater`, `Minimap` |
| 11 | `HighlightImpl12` | `EventEntityRemove` | low | recorded by the rewind nameplate pipeline (`Nameplate2Impl6`) |
| 12 | `HighlightImpl13` | `EventPlaySound` | high | sound id/category/volume/pitch/pos; handlers match `block.note_block.pling`, `entity.guardian.death`, `entity.bat.death` |
| 13 | `HighlightImpl14` | `EventRenderWorld` | low | holds `Bridge14_3` (=RenderGlobal) + float; no firer/user left in tree |
| 14 | `HighlightImpl15` | `EventSpawnParticle` | high | fired by `WorldEventMixin` in `spawnParticle$v1_7/$v1_8`; cancellable, ~18 handlers |
| 15 | `HighlightImpl16` | `EventExplosion` | high | fired by `ExplosionMixin` on `Explosion.doExplosionB` with (world,x,y,z,size) |
| 16 | `HighlightImpl17` | `EventRenderBlockOutline` | high | fired by `RenderGlobalEntityOutlineMixin.lunar$drawSelectionBoundingBox`; `BlockOutline` cancels and draws its outline |
| 17 | `HighlightImpl18` | `EventGetHorizon` | high | fired by `WorldEventMixin.lunar$getHorizon` with mutable double `value`; `TimeChanger.method8` overrides |
| 18 | `HighlightImpl19` | `EventWorldEffect` | low | rewind nameplate effect (type/data) serialized in `RewindhandlersNameplateCoreImpl8.method11` |
| 19 | `HighlightImpl20` | `EventEntityItem` | low | holds an `ItemStack` + entity; no firer/user left in tree |
| 20 | `HighlightImpl21` | `EventBlockUpdate` | med | pos + block, consumed via `HighlightImpl21.Data` by chest/relic/hotspot trackers |
| 21 | `HighlightImpl22` | `EventGameDirectory` | med | `(String,File)`; `Minimap3` builds `new File(method2(),"minimap")`, `Minimap2` stores `method2().toPath()` |
| 22 | `HighlightImpl23` | `EventMousePosition` | med | fired by `RewindHandlers5.method13`, recorded as `Nameplate2Impl7(x,y)` |
| 23 | `HighlightBase2` | `EventChunk` | med | base of `Data8`/`Data9`, holds `Itemcounter2` (=Chunk) |
| 24 | `HighlightBase2$Data10` → `Data10` | `EventPlayerBlockInteract` | low | nested `HighlightBase$Data10`: (pos, player); no users |
| 25 | `HighlightBase$Data11` → `Data11` | `EventPlayerBlockInteractExtended` | low | nested `HighlightBase$Data11`: adds nullable player |
| 26 | `HighlightBase2$Data8` → `Data8` | `EventChunkUnload` | high | fired on `Chunk.onUnload` |
| 27 | `HighlightBase2$Data9` → `Data9` | `EventChunkLoad` | high | fired on `Chunk.onLoad`/`ChunkProviderClient.loadChunk` |
| 28 | `HighlightBase3` | `EventRenderTick` | high | abstract base holding partialTicks, parent of `Data5`/`Data6` |
| 29 | `HighlightBase3$Data5` → `Data5` | `EventRenderTickStart` | high | fired at `FMLCommonHandler.onRenderTickStart`; `Debug.EVERY_FRAME` |
| 30 | `HighlightBase3$Data6` → `Data6` | `EventRenderTickEnd` | high | fired at `FMLCommonHandler.onRenderTickEnd` |
| 31 | `HighlightBase4` | `EventWorld` | med | abstract base holding `Itemcounter6` (=World) |
| 32 | `HighlightBase4$Data3` → `Data3` | `EventWorldChange` | med | reset hook for ~70 HUD modules on world/dimension change |
| 33 | `HighlightBase4$Data4` → `Data4` | `EventWorldLoad` | med | world load variant (`Minimap` sets the dimension) |
| 34 | `HighlightImpl17$Data3` → `Data3` | `EventRenderBlockOutlineModern` | med | `@VersionGate(min=6)` block-outline selection variant |
| 35 | `HighlightImpl17$Data4` → `Data4` | `EventRenderBlockOutlineLegacy` | med | `@VersionGate(max=5)` variant fired by `lunar$drawSelectionBoundingBox` |

Object-type decoding used as evidence: `Itemcounter2` = `Chunk` (from
`ChunkLightingMixin`/`ChunkProviderClientMixin`), `Itemcounter6` = `World`
(`bridge$setWorldTime`), `Bridge14_3` = `RenderGlobal`/level renderer,
`HitcolorExtension` = player (`WeakHashMap<HitcolorExtension,GameProfile>` in
`framework.feature.pkg.Pkg4`), `Horsestats20Extension(2)` = a 3-D position,
`BridgeExtension_4` = `ItemStack`, `Bridge6_10` = entity/player.

## Dry runs

Import-aware applier (the one that must be used):

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-highlightmixinfishing.tsv
# → [aware-renames] 35 rows (10 nested); 6594 java files
#   rows=35 skipped=0 files_touched=264 files_renamed=25 mode=dry-run
```

The v1 word-boundary applier cannot apply this map and **must not be run with
`--allow-collisions`**:

```
python3 tools/apply_class_renames.py --map tools/renames/classes-highlightmixinfishing.tsv
# → [class-renames] applied=1 skipped=34 files_touched=75 files_renamed=1
```

Every `HighlightImplN`/`HighlightBaseN`/`DataN` old name is declared in many
other packages; en masse replacement would merge unrelated `highlight.fishing`,
`highlight.mixin.gui`, `holograms` and `rewindhandlers` classes. The aware
applier resolves each reference from the file's imports and scopes the nested
rows by their `Owner$Inner` owner from the evidence column.

## Caveats / follow-ups

* **Low-confidence rows** (unused in this partial tree, named from payload only):
  `EventRewindUpdate` (Impl6), `EventEntityPosition` (Impl7),
  `EventEntityRemove` (Impl12), `EventRenderWorld` (Impl14), `EventWorldEffect`
  (Impl19), `EventEntityItem` (Impl20), and the two `HighlightBase.Data10/11`
  events. Their firers/consumers live in quarantined sources; verify against the
  runtime jar before trusting.
* `HighlightImpl3`/`11`/`12`/`19`/`23` are rewind-recording events with a lot of
  empty pipeline indirection (`RewindhandlersNameplateCore` no-op methods); the
  `EventRewind*` names are best-effort.
* `EventGetHorizon` (Impl18) and `EventRenderBlockOutline` (Impl17) are also
  produced by the `highlight.mixin.highlight` map's neighbours; no name clashes
  (checked: no declaration of any new name exists tree-wide).
* `HighlightImpl10` is cancelled by `TimeChanger` but its firer is absent here;
  `EventWorldTime` follows the `getWorldTime()` accessor.
* `EventRenderBlockOutlineModern`/`Legacy` encode the `@VersionGate` split; the
  codebase forbids *trailing digits*, so `V1_8`-style suffixes were avoided.