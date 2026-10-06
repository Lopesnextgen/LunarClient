# Wave 5 — member spree: replay/rewind (`members-obf1.tsv`)

## State of play (important)

The first revision of this map was already applied by the main agent in
`4227f6adb` ("member spree wave 1"), merged with `members-141.tsv` (same
cluster). The current tree has **no lazy member declarations left in the 16
scope files except the overload groups listed below**, and only two obf tokens
survive (`HORHRO…` in `RewindTimelinesListBridge`, `OIRHOO…` which belongs to
`LightingExtension4915`, not this module).

This revision of `members-obf1.tsv` (56 rows) therefore contains only what is
**still unresolved**, re-scanned against the working tree, and its names are
aligned 1:1 with `members-141.tsv` (0 conflicts) so the two maps stay merged.
Full research trail is below.

## What the module is

Two parallel copies of the same Rewind/RewindMod editor bridge:

* **A-side** `com.moonsworth.lunar.client.framework.feature.rewind.*`
  (in `libs/lunar-renamed-classes.jar`),
* **B-side** `com.moonsworth.lunar.client.replay.*` (source-only newer copy).

Both extend the static editor context (`Coordinates` / `RewindEditorContext`,
same 425-line class, same `method1..method47` numbering). All 16 files are the
JS bridges of the web UI: each implements `GuiIterator.Extension.getProvider()`
and exposes `@CallbackJS("…")` handlers.

## Evidence used (two+ per rename)

1. declaring source + all call sites (grep over `src/main/java`);
2. obf token → declaration via `tools/mappings-snapshot/member-renames.tsv`
   resolved through the hierarchy with `tools/fix_obf_tokens.Resolver`;
3. overload disambiguation from `javap -c -p -classpath libs/lunar-renamed-classes.jar`
   (`invokestatic` descriptors with argument types, e.g. the `HORHRO…` pair in
   `selectTimeline` is `setSelectedLayer(null)` + `setSelectedKeyframe(null)`);
4. `@CallbackJS("…")` literals = the JS-facing names, used verbatim
   (`cut`, `selectKeyframe`, `startPlayheadDrag`, `downloadFfmpeg`, …);
5. A/B copies cross-check (same member index, better types on the B side);
6. ReplayMod 2.6.24 (`ReplayHandler.getReplayFile/getOverlay/isQuickMode`) and
   the real-named `RewindEditorContext` types (`SelectedKeyframe`,
   `KeyframeProperty`, `ReplayTimeline`, `Track`, `PropertyGroup`,
   `EntityContextMenu`, `MediaExporter`, `ExportSettingsPanel`) for style.

Base-member identifications (already applied by wave 1, kept for the record):

| member | name | evidence |
|---|---|---|
| `method7..14` | `refreshPlaybackState / refreshTimeline / refreshProperties / refreshMediaExporter / refreshTimelinesList / refreshExportSettings / refreshEffects / refreshEntityContextMenu` | `RewindHandlers.field9..16` = `PlaybackStateProvider/RewindTimelinePanel/RewindPropertiesPanel/MediaExporter/RewindTimelinesListPanel/ExportSettingsPanel/EffectsPanel/EntityContextMenu`, each `RewindPropertyProvider.method1(handler)` |
| `method1/2/29/30` | `setSelectedLayer / setSelectedKeyframe / getSelectedLayer / getAdditionalSelectedLayers` | `selectLayer`/`selectLayers`/`cut` handlers + javap descriptors |
| `method31..34` | `set/isLinkSelectionEnabled`, `set/isMultiSelectEnabled` | set with `!shift` in `selectLayer`; gates the `"links"` JSON |
| `method35..40` | `set/getCopiedLayer`, `set/getCopiedRange`, `set/getCopiedTrack` | `Rewind4.method9` stages, `Rewind4.method10` inserts at playhead |
| `method44..47` | `set/isDragging`, `set/getScrubFrame` | `startPlayheadDrag`/`stopPlayheadDrag`, JSON `"dragging"`/`"playhead"` |
| `method4/5` | `applyFrameRemap` / `lookupRemappedFrame` | `moveKeyframes` map `option → oldFrame → newFrame` |
| `method6` | `collectLinkedLayers` | `SegmentLinkManager.method3/4`, used by cut/trim/`linkLayers` |

## What is still open (this revision of the map)

* **`method15() → getProvider()`**: wave 1 renamed `GuiIterator.Extension.method15`
  to `getProvider` on the **interface only**, so implementors still declaring
  `method15()` no longer satisfy it. The map emits the row only for
  single-declaration files; the overload files (`Chest2`,
  `RewindTimelineBridge`, `Holograms2`, `RewindEditorBridge`, which also have
  `method15(int)/method15(float)`) are intentionally excluded and need the
  signature-aware split (`getProvider` vs `deleteTrack`/`setZoom`) — the
  previous apply already mis-renamed the float overload, see post-apply fixes.
* **Overload groups still lazy** (the applier drops same-name rows; names are
  in the evidence column):
  `Chest2`/`RewindTimelineBridge` `method20()→unselectLayer` (other overload
  `moveKeyframes`), `method21()→unselectKeyframe` (other `linkLayers`),
  `Holograms2`/`RewindEditorBridge` `method15(float)→setZoom` and
  `method4(Float,Float)→updatePreviewWindow` (other `onScroll` default),
  `Highlight2_2`/`MediaExporterJsApi` `method7(UUID)→newFolder`
  (other `rename`), `RewindPropertiesBridge` `method3→setPropertyExtended`
  (other `Coordinates.findProperty`), `method20()→endPropertyUpdate`
  (other `Coordinates.getLayerRange`).
* **Coordinates/RewindEditorContext fields** (`field1..field12`) are still lazy
  in the tree: the map re-emits all 12 with the `members-141.tsv` names.
  Note the declarations are accessed *bare* inside the class, so the applier
  needs a bare-field rewrite (or file-local rename) to apply them.
* **`HORHROIOIOICIRHIOCOICHHHIHCIIO`** in
  `RewindTimelinesListBridge.selectTimeline`: a single obf name shared by
  `setSelectedLayer` and `setSelectedKeyframe`; javap proves the pair is
  `setSelectedLayer(null)` then `setSelectedKeyframe(null)`. One map row can
  only pick one target (mapped to `setSelectedLayer`); the second call needs a
  manual fix (same shape in the A copy `Click2`, already rewritten to
  `method2(null) method2(null)` by an earlier pass).
* **`OIRHOOIICOCIOOHICRRRICORIHHIHC`** (in `Rewindhandlers2_4`/`ExportSettingsJsApi`)
  resolves to `LightingExtension4915` (open folder), not the rewind hierarchy;
  another owner's map.

## Known leftovers that are not plain obf tokens

`Chest2`/`RewindTimelineBridge` still call `method30(…)` with four signatures;
the A-side jar renamed members per class without rewriting cross-class
references, so these point at `Coordinates` members that no longer exist under
that name. Correct targets (descriptor → name):

* `(LRewindIterator;)V` → `setSelectedLayer`
* `(LCoordinates4;/LSelectedKeyframe;)V` → `setSelectedKeyframe`
* `(Ljava/util/Collection;Ljava/lang/String;[I)` → `findPropertyGroup`
* `(LRewindIterator;Ljava/lang/String;Ljava/lang/String;)V` → `findProperty`

Lines: `Chest2.java` 227/231/247/249/271 (selection), 542/543/547/548,
573/574, 589/590, 601, 616, 640; same offsets in `RewindTimelineBridge.java`
(230/234/250/252/274, 545/546/550/551, 576/577, 592/593, 604, 619, 643).
The single-argument `null` calls must be matched per method against the
bytecode descriptors (`javap -c` on `libs/lunar-renamed-classes.jar`).

Interface defaults kept lazy on purpose (renaming them alone breaks
`implements`): `DriverGuiExtension.method2(KeyCode,…)` / `method4(double,double)`
overridden by `Holograms2`/`RewindEditorBridge`.

## Post-apply fixes needed in the current working tree

The wave-1/re-application pass renamed overload groups by name only, so these
three spots are wrong in the tree right now (all compile, but wrong semantics
or wrong API name):

1. `Holograms2.getProvider(float)` and `RewindEditorBridge.getProvider(float)`
   (lines ~351, under `@CallbackJS("setZoom")`) must be `setZoom`; the
   no-arg `getProvider()` is the interface override. A signature-aware pass
   must split `method15()` from `method15(float)`.
2. `Click2.selectTimeline` calls `selectTimeline(null); selectTimeline(null);`
   (lines ~28-29) — descriptor-proven `setSelectedLayer(null)` then
   `setSelectedKeyframe(null)`. In `RewindTimelinesListBridge` the same pair
   is now `setSelectedLayer(null); setSelectedLayer(null);` — the second call
   must become `setSelectedKeyframe(null)`.
3. `Chest2`/`RewindTimelineBridge` keep `method20()/method21()/method15`
   in the tree: the 0-arg `method20` is `unselectLayer`, `method20(Data2[])`
   is `moveKeyframes`, `method21()` is `unselectKeyframe`,
   `method21(boolean)` is `linkLayers`, `method15()` is `getProvider()` and
   `method15(int)` is `deleteTrack` (same for the B copy).

## Skipped / false positives

* `GuiType4` and `ReplayEnvironment`: the `SINGLEPLAYER` rows in
  `members-obf.tsv` are enum constants, the enums are already fully named.
* `Rewindhandlers2_4` line 169 (`method17(` in a constructor call) is a
  decompiler artifact, not a declaration.
* `HORHRO…` and `OIRHOO…` cases above.
