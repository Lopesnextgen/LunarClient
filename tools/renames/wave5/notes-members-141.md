# Member names — cluster 141 (`framework.feature.rewind.rewindhandlers`)

Owner/file index: `tools/renames/wave5/member-cluster-141.tsv` (25 owners).
Lazy-member rows: `tools/renames/wave5/members-lazy.tsv` filtered by owner **and** file
(1 120 rows, of which 716 are locals `varN`/params, 298 `methodN`, 106 `fieldN`).
Map: **`tools/renames/wave5/members-141.tsv`** — 279 rows (24 owners).

## What this cluster is

`rewindhandlers` is the **Rewind replay editor** (Lunar's ReplayMod integration), not a
"handlers" package:

| old class | role | moved-tree twin (`com.moonsworth.lunar.client.replay.*`) |
|---|---|---|
| `Rewindhandlers` | replay **clock**: speed/freeze options, skip budget, partial tick | `rewindhandlers.ReplayClock` |
| `Rewindhandlers2` / `2Impl` / `2Impl2` | OpenAL audio streams (media-file, Opus/UUID) + Gson adapter | `AudioStream` / `MediaAudioStream` / (no twin yet) |
| `GuiIterator` | `.rewind`/`project.json` list provider for the web UI | `rewindhandlers.RewindListProvider` |
| `Rewindhandlers2_2/2_3/2_4/2_5` | JS APIs (`goToTick`, `updateBoundingBoxes`, export API) + option watcher | `InputTimelineJsApi` / `EffectsJsApi` / `ExportSettingsJsApi` / `HighlightOptionWatcher` |
| `Rewindhandlers_2/_3/_5/_6` | web-UI JSON panels (input timeline, export settings, effects) + file callbacks | `InputTimelinePanel` / `ExportSettingsPanel` / `EffectsPanel` / `RewindFileCallbacks` |
| `Rewindhandlers_4` | per-entity option override registry | `EntityOptionOverrides` |
| `Chest`/`Chest2`, `Click`/`Click2`, `Colorsaturation`/`Colorsaturation2` | timeline/properties JSON providers (`*Panel`) + the `@CallbackJS` bridges (`*Bridge`) | `chest.RewindTimelinePanel`/`RewindTimelineBridge`, `click.RewindTimelinesListPanel`/`RewindTimelinesListBridge`, `colorsaturation.RewindPropertiesPanel`/`RewindPropertiesBridge` |
| `Coordinates` | global editor interaction state (selection, copied layer, zoom, scrub) | `coordinates.RewindEditorContext` |
| `Coordinates2/3/4`, `Gui2Extension3/4` | export-settings section + bridge payload DTOs; `AudioSampleRate`, `RenderRange` enums | `ExportSettingsSection`, `TimelineBridgePayloads`, `SelectedKeyframe`, `AudioSampleRate`, `RenderRange` |

## Method and evidence

* declaring sources (`src/main/java/.../rewindhandlers/**`) + all call sites
  (`mod/misc/RewindHandlers.java`, `RewindHandlers4.java`, `Rewind4.java`,
  `highlight/Highlight_3.java`, `markers/Markers4.java`, `Client.java`, `legacy/mixin/TimerRewindMixin`);
* the **moved tree** `com.moonsworth.lunar.client.replay.*` — same code, real class names and a
  partially applied member rename (class bucket pass 2026-09-15). Names there are copied verbatim
  where they exist (`refresh`, `getProvider` family, `setSelectedLayer`, `canPropagateEdit`,
  `applyLayerMove`, `beginUndoBatch`, `endUndoBatch`, `editTimelineName`, all `@CallbackJS` names …).
  Members of the twin classes were not invented: `RewindEditorContext` alone confirmed 45 of my names;
* `tools/mappings-snapshot/member-renames.tsv` (obf → lazy name) to recover the obfuscated call-site
  aliases and their true targets;
* JSON keys / annotations in the sources (`"selectedLayer"`, `"inputTicks"`, `"renderInFrame"`,
  `"downloadProgress"`, `@CallbackJS("...")`) and ReplayMod 2.6.24 (`RenderSettings`: `videoWidth`,
  `framesPerSecond`, `bitRate`, `ProgressListener`-style export callbacks);
* class-identity docs: `classes-modfishing.md` (rows 12–21), `classes-rewindgui.md`,
  `classes-coordsnameplate` (in `APPLIED.md`), `classes-clientmixins.md`.

## State when this map was written (2026-09-16 ~18:00)

The wave-1 member spree (`4227f6adb`) plus a run of an earlier revision of this map already
renamed most of the cluster. `python3 tools/apply_member_renames.py --map …/members-141.tsv` now
reports **1 row after the multi-decl filter** (only the `fileCache` correction still has exactly one
declaration — every already-applied row has 0, every blocked row has 2) and **1 742 accesses**
left to rewrite (stale call sites in `Chest2`, `Colorsaturation2`, `Rewindhandlers2_4`, `Click2`,
`Rewindhandlers_3`, `Rewindhandlers2Impl*`, `markers/mixin/gui/GuiIterator`), so re-applying is
still worth it.

* 264 rows: declaration already carries the mapped name (no-op on re-apply).
* 6 rows: **alignment corrections** — the spree applied an earlier name; these re-key on the
  *current* name so the tree matches the moved tree:
  `Chest2.isLinkedWithSelection→canPropagateEdit`, `Chest2.moveLayerInternal→applyLayerMove`,
  `Colorsaturation2.beginBatch→beginUndoBatch`, `Colorsaturation2.endBatch→endUndoBatch`,
  `Click2.renameTimeline→editTimelineName`, `Rewindhandlers2Impl.fileCache→musicTrackManager`.
* 14 rows: **pending but blocked** — the 12 `Coordinates` fields, `Rewindhandlers2.field1`
  (genuinely declared twice: the constant + the nested `AudioStreamAdapter.field1`) and the
  `fileCache` correction (which the *unpatched* applier keeps, see findings).
* 20 rows are extras that were never in `members-lazy.tsv` (multi-line initializers such as the
  option fields of `Rewindhandlers_3`/`Colorsaturation`, the anonymous `ProgressListener` field,
  `GuiIterator.field7`, and the two base-owner family rows).

## Naming highlights

* **Clock** (`ReplayClock`): `MILLIS_PER_TICK` (50), `originTime`, `lastRealTime`, `rewindOffset`,
  `tickStartTime`, `remainingForwardMs`, `remainingRewindMs`, `skipDurationMs`, `stepMsPerTick`,
  `skipFinished`, `rewindDirection`, `tickStepped`, `forwardDirection`, `timeAdvanced`,
  `speedOption`/`freezeOption` (the `"speed"`/`"freeze"` options); methods `skip(ms)`, `tick(handlers)`,
  `isSkipping`, `isBusy`, `isReversing`, `isPlayable`, `getPlaybackTime`, `getProjectedTime`,
  `getSkipProgress` (the `"progress"` bar), `commitPendingTime`, `markTickStart`, `hasPendingRewind`,
  `isTickStepped`. Evidence: `RewindHandlers.java` (`field20.methodN` call sites), `RewindHandlers4`
  render gate, `PlaybackStateProvider`/`Holograms` JSON, `TimerRewindMixin` (`getTime()*1e6`).
* **Audio streams**: `BUFFER_COUNT`, `sourceId`, `bufferIds`, `uri`, `mediaId`, `rootDirectory`,
  `input`, `sampleFormat`, `timeAnchor`, `playbackRate`, `decodeBuffer`/`sampleBuffer`/`scaledBuffer`,
  `waveformSamples`, `waveformRenderer`, `alive`; `openInput`, `readSampleFormat`, `readSampleRate`,
  `readFrameSize`, `computeHash`, `loadWaveform`, `computeWaveform`, `decodeFrame`, `skipInput`,
  `queueBuffer`, `setVolume`, `setPlaybackRate`, `getPlaybackRate`, `getFramesPerSecond`,
  `updatePlayback`, `advanceTime`, `checkAlError`, `copy`, `setAlive`/`isAlive`.
  `MediaAudioStream`: `mediaPool`, `musicTrackManager` (twin type `MusicTrackManager`),
  `audioFormat`, `mediaFile`, `loadDuration`. Opus stream: `fileReader`, `audioMetadata`,
  `decoderHandle`, `createDecoder` (blocked, see below).
* **Family rename** used everywhere the interface declares it: `refresh(RewindHandlers)` for the
  provider entry point (base `coordinates.GuiIterator.method1`) and `getProvider()` for the
  `markers.mixin.gui.GuiIterator$Extension` override.
* **`@CallbackJS` methods keep the annotation value as their Java name** (`cut`, `addTrack`,
  `addLayer`, `moveLayer(s)`, `trimLayer`, `selectKeyframe(s)`, `moveKeyframe`, `linkLayers`,
  `startLayersSelection`, `updatePropertyValue`, `renderTimeline`, `exportProject`, …) — the string is
  the JS-side contract, the Java name is free (see "Reflection" below).
* **`Coordinates`** (editor state): `selectedLayer`, `additionalSelectedLayers`,
  `linkSelectionEnabled`, `multiSelectEnabled`, `copiedLayer`/`copiedRange`/`copiedTrack`
  (the duplicate/insert pipeline), `selectedKeyframe`, `additionalSelectedKeyframes`, `zoom`,
  `dragging`, `scrubFrame`; `refresh*` methods map 1:1 onto the `RewindHandlers` provider fields
  (field9 PlaybackStateProvider … field16 EntityContextMenu), e.g. `refreshTimeline` =
  `RewindTimelinePanel`, `refreshProperties` = `RewindPropertiesPanel`, `refreshTimelinesList`
  = `RewindTimelinesListPanel`.

## Skipped / uncertain (27 `members-lazy` keys + extra)

**a) Ambiguous multi-declaration — a name-only map cannot split them** (all left untouched):

| owner | old | intended names (per overload) |
|---|---|---|
| `Chest` | `method1` | `toJsonArray` (`Collection<UUID>`→JsonArray) / `refresh(RewindHandlers)` |
| `Chest2` | `method15` | `getProvider()` / `deleteTrack(int)` (`@CallbackJS("deleteTrack")`) |
| `Chest2` | `method20` | `unselectLayer()` / `moveKeyframes(Coordinates3$Data2[])` |
| `Chest2` | `method21` | `unselectKeyframe()` / `linkLayers(boolean)` |
| `Rewindhandlers2Impl` | `method8` | `computeHash()` (override) / `readFrames(AudioInputStream)` (override of base `skipInput`) |
| `Rewindhandlers2Impl` | `method9` | `openInput()` (override of base `openInput`) / `decodeFrame(AudioInputStream,ByteBuffer,ShortBuffer)` |
| `Rewindhandlers2Impl2` | `method9` | `createDecoder()` / `skipInput(DataInputStream)` |
| `Rewindhandlers_3` | `method1`,`method2`,`method3`,`method4` | class member `refresh(RewindHandlers)` plus the anonymous `ProgressListener` overrides (`method1`=onProgress, `method2`=onComplete→**already renamed by the spree**, `method3`=onError, `method4`=private `pushUpdate`). Renaming them would break the `@Override` chain (`GUI3`/`ProgressListener` methods are still lazy) |

**b) Rows that exist only because the owner file contains an *inherited* call** (not declared in the
owner — handled by the declaring owner's row): `Chest2.method18/method30/method41`,
`Click2.method17/method18`, `Colorsaturation2.method41`, `Rewindhandlers2_4.method17`.

**c) Nested-class members — the applier's `owner_file()` cannot resolve `Outer$Inner`** (it only
tries `…/Outer$Inner` and `…/Outer`, and the declaration pass then sees 2–3 same-name
declarations, so the multi-decl guard drops them). Names are therefore documented here, not in the
map (`Coordinates3` = `TimelineBridgePayloads`):

| nested owner | old → new |
|---|---|
| `Coordinates3$Data` | `field1→layerId`, `field2→keyframeType`, `field3→keyframeId` (`frame` was already named) |
| `Coordinates3$Data2` | `field1→layerId`, `field2→parentType`, `field3→keyframeType`, `field4→keyframeId`, `field5→currentFrame`, `field6→newFrame` |
| `Coordinates3$Data3` | `field1→layerId`, `field2→trackId` (`frame` already named) |
| `Coordinates3$Data4` | `field1→property`, `field2→frame`, `field3→value`, `method1→getProperty`, `method2→getFrame`, `method3→getValue` |
| `Rewindhandlers2$Data4` | `field1→projectManager`, `method1→write`, `method2→read` (Gson adapter, old class map row: `AudioStreamAdapter`) |

**d) `GuiIterator` (rewindhandlers) `method2`** has two overloads (`refresh()` and the private
`sorter(JsonArray)`); one row renames both to `refresh` — fine on apply, but a signature-aware pass
should give the private one `sortEntries`.

## Base owners included in the map (cross-cluster contracts)

| owner | member | new | why |
|---|---|---|---|
| `…rewindhandlers.coordinates.GuiIterator` | `method1` | `refresh` | abstract provider entry point; **already applied** to the base and to `Rewindhandlers_2/_5`, `Click`, `Colorsaturation`; `Chest`, `Rewindhandlers_3`, `Chest2`, `Colorsaturation2` still need it or their `@Override` breaks |
| `com.moonsworth.lunar.client.markers.mixin.gui.GuiIterator` | `method15` | `getProvider` | `Extension.method15()` contract implemented by `Rewindhandlers2_2/2_3/2_4`, `Rewindhandlers_6`, `Click2`, `Colorsaturation2`, `Chest2`; that owner belongs to cluster 182 — **coordinate before applying** or the `implements …Extension` family breaks |

## Applier findings (actionable)

1. **Multi-decl guard false positives.** `MEMBER_DECL`/`FIELD_DECL` treat a statement that starts
   with `return` as a declaration (`return field1;`, `return method16().method35();`), so
   `drop_multi_decl_rows` deletes the 12 `Coordinates.fieldN` rows (and would delete many more
   tree-wide; it also deletes every already-applied row because they now count 0 declarations).
   Proven fix (tested on a copy in `/tmp`, not applied to the repo): prefix both patterns with
   `r'^(?!\s*(?:return|throw|else|case|do|yield|assert|new|break|continue)\b)'`.
   With that one-liner the dry run goes from **1 → 13 rows surviving** (the 12 field rows plus the
   `fileCache` correction) and 15 declarations are renamed.
   Still blocked after the fix: `Rewindhandlers2.field1` (real double declaration) — needs a
   nested-aware exception.
2. **`n` in the access report is inflated by duplicate matches** (e.g. `Chest2` reports 1002 while the
   real diff is 7 lines); per-file counts should de-duplicate `(old,new)` pairs before `str.replace`.
3. **Nested owners** are unreachable (see c) — `owner_file()` should fall back to extracting the
   nested class body from the outer file.
4. **`--allow-multi-decl` must not be used for this map**: rows for `Rewindhandlers_3.method1..4`
   and the `Rewindhandlers2Impl*` `method8/9` pairs were deliberately dropped (see a).

## Call-site damage found in this cluster (needs a fix pass, my map cannot express it)

An earlier per-file obfuscated-alias pass collapsed **every inherited `Coordinates` static call in
`Chest2` to `method30(...)`** (and the same damage exists in the moved twin
`chest/RewindTimelineBridge.java`, so it is upstream, not from this map). The subsequent renames
made it worse where the class's own member has the same lazy name:

| file:line | current text | should be |
|---|---|---|
| `chest/Chest2.java:51` | `selectKeyframe()` / `selectKeyframes()` | `Coordinates.getRewind()` / `Coordinates.getRewindHandlers()` |
| `chest/Chest2.java:61,96,119,243,278,316,368,400,503,545,563,628,673` | `moveKeyframe()` (no args) | `Coordinates.getTimeline()` |
| `chest/Chest2.java:227,247,590` | `method30(rewindIterator)` / `method30(selectedKeyframe)` | `Coordinates.setSelectedLayer(…)` / `Coordinates.setSelectedKeyframe(…)` |
| `chest/Chest2.java:231,249,271,548,589,601` | `method30(null)` | `Coordinates.setSelectedLayer(null)` / `setSelectedKeyframe(null)` (ambiguous, inspect each site) |
| `chest/Chest2.java:542,573` | `method30(collection, path, 0)` | `Coordinates.findPropertyByPath(…)` |
| `chest/Chest2.java:543,574,640` | `method30(rewindIterator, path, id)` | `Coordinates.findProperty(…)` |
| `chest/Chest2.java:663` | `addTrack(var5)` (HashMap!) | `Coordinates.applyFrameRemap(var5)` |
| `chest/Chest2.java:670,691,696` | `unselectKeyframe(boolean)` — the `@CallbackJS("linkLayers")` method renamed by the spree | `linkLayers(boolean)` |
| `colorsaturation/Colorsaturation2.java:66` | `setPropertyExtended(null)` (1 arg for a 2-arg method) | `Coordinates.setSelectedKeyframe(null)` (same wrong call exists in the moved twin, so it is upstream) |
| `click/Click2.java:28,29,94,95,101,102` | `selectTimeline(null)` (self-recursive, was `Coordinates.method2(null)`) | `Coordinates.setSelectedKeyframe(null)` / `setSelectedLayer(null)` (moved twin uses `setSelectedLayer(null)`) |

## Obfuscated alias → target table (recovered for the still-stale sites)

`tools/mappings-snapshot/member-renames.tsv` rows for the pre-rename `Coordinates` class
(identified via `method20(UUID)→Range`) give: `HORHROIOIOICIRHIOCOICHHHIHCIIO`→`method1`,
`RCIOICOHRIOIIRRRROCRHCIICRROHO`→`method2`, `HHRROIIHRRICIIHIIHCRHHRHOHHOO`→`method3`/`method28`,
`ORICHRORRORHORHOIHCRHOORCRRHOI`→`method4`, `CCHIHOHCICHCRCCCHRIIOHHIICCCIO`→`method6`,
`RCHICOCIRRHROICHHRCHHRCOHIIIIO`→`method7`, `RORCORIHORORRCCCCCRRICIORROIHR`→`method8`,
`HHCCRRIIOCOROOHRCCCCOHRCHIOROC`→`method9`, `CORRHIRRRHORHRICOCCHIRHRHRHHOH`→`method10`,
`CHHRCORROHCHHHCOICORHRRIIHIOIC`→`method11`, `ICOIIIRCHRIRIRCCCCRROICORHIIRI`→`method12`,
`ICIHOOOICHIHHOHCHCCCHRRHRHRCIH`→`method14`, `RIOOHRCHIRCIICRIRCOCHIRHHCOIRO`→`method15`,
`RRRCROOHOHICIIIOHHIHOIHCHIOCOH`→`method16`, `COOHOCCHHRHOICOCCIROCRRHRIIROI`→`method17`,
`RCHIIRIICIRORCOHIICROHOCCOIIOI`→`method18`, `HCHIOCOCRHRHORCHIOHRRIHCOOHCOI`→`method19`,
`RRCIOHHRCRCCOCCIOROHORCRROHCOC`→`method20`, `HRRCROICHIIROIHRCOIHRRHCCRIIRH`→`method25`,
`OICRROHCCIRICRIHCOIRCOORHRHRHC`→`method26`, `IROHICIOOHIRCOCHOOCROHROIIRRIC`→`method27`,
`CRRRRHHOOHHCOOOOHOIHCRRIHHHHOO`→`method29`, `CIROCIIRIRCRCICHCIHOHCRIRHCHOO`→`method30`,
`RCROHHIIORHICHRRIOIRIRROOORIRC`→`method31`, `RRORCHOOHHORHCIRIOICOHCOOHOIHO`→`method32`,
`COHRIRHRORRROHOIHROICCCHRCRHIR`→`method34`, `HRICOROOOCCOCOROCRHHCRRIRCOICO`→`method35`,
`RCHOCRRHCICIHOCOIRCRHIHHROCOCO`→`method38`, `OIRHIRHIOIOIOIHOHCHOICRHOIRHIO`→`method41`,
`CRRCHCCHOCOOCROHROIORCOCRORROR`→`method42`, `CRHCHHIIOCRRHIROHCCRHRICCIHCHO`→`method44`,
`OOICRIIHIIIIHCOHCOIRORCICOHCHH`→`method45`, `HHIRRCHCHIIHIOHICHOOOHIRHRRCCR`→`method46`,
`HHCIHORHHCIIHHICIRCRHCHOCIICRO`→`method47`.
Other files: `IICIIOHRCHCIOIIHROHHHOCRHIIIHR`→`AudioStream.setEof` (Rewindhandlers2Impl/Impl2 — the
declaration already uses `setEof`, call sites should be updated); the `HORHIRROCIOIICIOHCOCCOOHIRCCRI`
/ `HIRIHCROOIRIORCCOIRRCRHOHCCRRO` / `OIRHOOIICOCIOOHICRRRICORIHHIHC` / `OOOIROIIOCOOHICRIRHHHRROHHHHIO`
/ `OCRRICRIORICCCRHIOHORCICIHHICO` calls in `Rewindhandlers_3`/`_5`/`Colorsaturation` belong to the
**options framework** (`LightingExtension` trait/change-listener/min-max builder) owned by clusters
51/52/59 and are not part of this cluster.

## Reflection / string contracts (rename-safe, but worth knowing)

* `@CallbackJS("name")` is a runtime annotation read by the WebOSR JS bridge — JS calls the string,
  not the Java name, so all `@CallbackJS` methods were named after their annotation value.
* `Markers4` registers every provider with `Files6_2.method1(Class, Function)` (class literals +
  reflection over `@Annotation`-tagged methods) and the map keys `"rewindTimeline"`,
  `"rewindProperties"`, `"rewindExport"`, `"rewindsList"`, `"rewindDev"`, `"rewindPacks"` etc. are
  JS ids, not member names.
* `markers.mixin.gui.Annotation.value()` and the JSON keys passed to `this.method3("key", …)`
  (`"inputTicks"`, `"selectedLayer"`, `"downloadProgress"`, `"renderInFrame"`, …) are the real wire
  contract; the Java names chosen here follow them.
* i18n keys (`Client.method109().method67().method2("rewind", …)`) are unaffected by member renames.

## Verification performed

```
python3 /tmp/opencode/m141/check_state.py     # 279 rows: 264 applied, 14 pending, 1 no-op (field18), 6 corrections
python3 tools/apply_member_renames.py --map tools/renames/wave5/members-141.tsv      # dry run
python3 /tmp/opencode/m141/patched_applier.py --map tools/renames/wave5/members-141.tsv  # guard fix proved
```
No source file was edited by this task.
