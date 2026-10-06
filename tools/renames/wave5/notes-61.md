# Cluster 61 — `com.moonsworth.lunar.replaymod.mixin` (43 rows)

Source: `tools/renames/cluster-61.txt` (43 rows). All 43 paths exist, none are
missing, and none are shaded third-party code — these are Lunar's own ReplayMod
mixins (the mixed-into ReplayMod/jGui types are third-party, the mixin classes
are not). Map: `tools/renames/wave5/classes-61.tsv`.

Dry run (`tools/apply_class_renames_aware.py --map .../classes-61.tsv`):

```
[aware-renames] 43 rows (0 nested); 9808 java files
[aware-renames] rows=43 skipped=0 files_touched=43 files_renamed=43 mode=dry-run
```

## What this package is

`com.moonsworth.lunar.replaymod.mixin` is the flattened ReplayMod-mixin tree of
Lunar's multiver build. `50dba2ea9` ("Restructure: flat mixin packages") pulled
the classes out of the per-version modules
`com.moonsworth.lunar.replaymod.forge.{v1_8,v1_12}.mixin3` (plus the v1_8
metamixin) into one package; every class that collided got a trailing digit.
`tools/mappings-snapshot/restructure/mixin-renames.tsv` rows 560–643 hold the
exact provenance used below.

The cluster contains exactly the digit-carrying files. The plain names (39
v1_12 copies) and the `*V1_8Mixin` twins are outside the cluster.

## The duplicate problem

On 2026-09-14 `57e98a2b4` renamed all 43 of these files to readable names; then
the rescue sweeps (`3d38608ff`, `d8c57ec33`) restored the pre-rename copies from
the old `src/reference` tree next to the renamed ones. Result: **every row in
this cluster is the same decompiled class as an already-declared twin** (token
sets of all 43 pairs are identical: `@Mixin` target, `@Inject`/`@Redirect`
methods and target descriptors, declared methods). Both copies are dead — each
name is referenced only by its own file; the mixin configs in
`src/main/resources/mixins.ichor.replaymod.*.json` are still the obfuscated
originals copied from
`libs/multiver-full/lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar`
(byte-identical to the jar's configs), so they do not name the Java classes.

The applier refuses a `new` name that is already declared, so the rows receive
the **genuine upstream class name** from `github.com/LunarClient/ReplayModMixins`
(clone at `/tmp/opencode/reference/ReplayModMixins`, HEAD `94acb41`). The
upstream classes themselves carry `_v1_8` / `_v1_12` markers in the declarations
and in the repo's own `mixins.ichor.replaymod.v1_8.json`; they are real names,
not digit padding (rule 6). If the merge pass instead deletes one copy, the
upstream names are the ones to keep: the twins' `V1_8Mixin` spelling is a local
stylisation invented by `57e98a2b4`, and four of those twin names are **wrong**
(see corrections below).

## Renames

`prov` = source package in the obfuscated multiver tree (`mixin-renames.tsv`
line → flat name). `twin` = already-declared duplicate from `57e98a2b4`
(`/` twin does not exist in the published repo). `upstream` = matching file in
`/tmp/opencode/reference/ReplayModMixins/<ver>/java/.../{mixin,metamixin}/`.

| old | new | prov | twin | upstream |
|---|---|---|---|---|
| `AbstractGuiButtonMixin2` | `AbstractGuiButtonMixin_v1_8` | v1_8/mixin3 L603 | `AbstractGuiButtonV1_8Mixin` | `AbstractGuiButtonMixin_v1_8` |
| `AbstractGuiDropdownMenuMixin2` | `AbstractGuiDropdownMenuMixin_v1_8` | v1_8/mixin3 L604 | `AbstractGuiDropdownMenuV1_8Mixin` | `AbstractGuiDropdownMenuMixin_v1_8` |
| `AbstractGuiElementMixin2` | `AbstractGuiElement_v1_8` | v1_8/mixin3 L605 | `AbstractGuiElementV1_8Mixin` | `AbstractGuiElement_v1_8` |
| `AbstractGuiHorizontalScrollbarMixin2` | `AbstractGuiHorizontalScrollbarMixin_v1_8` | v1_8/mixin3 L606 | `AbstractGuiHorizontalScrollbarV1_8Mixin` | `AbstractGuiHorizontalScrollbarMixin_v1_8` |
| `AbstractGuiLabelMixin2` | `AbstractGuiLabel_v1_8` | v1_8/mixin3 L607 | `AbstractGuiLabelV1_8Mixin` | `AbstractGuiLabel_v1_8` |
| `AbstractGuiOverlayMixin2` | `AbstractGuiOverlayMixin_v1_8` | v1_8/mixin3 L608 | `AbstractGuiOverlayV1_8Mixin` | `AbstractGuiOverlayMixin_v1_8` |
| `AbstractGuiPopupMixin2` | `AbstractGuiPopup_GuiPanel_v1_8` | v1_8/mixin3 L609 | `AbstractGuiPopupV1_8Mixin` | `AbstractGuiPopup_GuiPanel_v1_8` |
| `AbstractGuiScreenMixin2` | `AbstractGuiScreenMixin_v1_8` | v1_8/mixin3 L610 | `AbstractGuiScreenV1_8Mixin` | `AbstractGuiScreenMixin_v1_8` |
| `AbstractGuiSliderMixin2` | `AbstractGuiSliderMixin_v1_8` | v1_8/mixin3 L612 | `AbstractGuiSliderV1_8Mixin` | `AbstractGuiSliderMixin_v1_8` |
| `AbstractGuiTimelineMixin2` | `AbstractGuiTimelineMixin_v1_8` | v1_8/mixin3 L613 | `AbstractGuiTimelineV1_8Mixin` | `AbstractGuiTimelineMixin_v1_8` |
| `AbstractGuiTimelineTimeMixin2` | `AbstractGuiTimelineTimeMixin_v1_8` | v1_8/mixin3 L614 | `AbstractGuiTimelineTimeV1_8Mixin` | `AbstractGuiTimelineTimeMixin_v1_8` |
| `CameraEntityMixin2` | `CameraEntityMixin_v1_8` | v1_8/mixin3 L615 | `CameraEntityV1_8Mixin` | `CameraEntityMixin_v1_8` |
| `ClassicCameraControllerMixin2` | `ClassicCameraControllerMixin_v1_8` | v1_8/mixin3 L616 | `ClassicCameraControllerV1_8Mixin` | `ClassicCameraControllerMixin_v1_8` |
| `ConnectionEventHandlerMixin2` | `ConnectionEventHandlerMixin_v1_8` | v1_8/mixin3 L617 | `ConnectionEventHandlerV1_8Mixin` | `ConnectionEventHandlerMixin_v1_8` |
| `DisableFastRenderMixin2` | `DisableFastRenderMixin_v1_8` | v1_8/mixin3 L618 | `DisableFastRenderV1_8Mixin` | `DisableFastRenderMixin_v1_8` |
| `DropdownEntryMixin2` | `AbstractGuiDropdownEntryMixin_v1_8` | v1_8/mixin3 L619 | `AbstractGuiDropdownMenuEntryV1_8Mixin` | `AbstractGuiDropdownEntryMixin_v1_8` |
| `ElementMixin2` | `GuiBackgroundProcessMixin_Element_v1_8` | v1_8/mixin3 L620 | `GuiBackgroundProcessesElementV1_8Mixin` | `GuiBackgroundProcessMixin_Element_v1_8` |
| `EntityRendererHandlerMixin2` | `EntityRendererHandlerMixin_v1_8` | v1_8/mixin3 L621 | `EntityRendererHandlerV1_8Mixin` | `EntityRendererHandlerMixin_v1_8` |
| `EventHandlerMixin2` | `VanillaGuiScreen_EventHandlerMixin_v1_8` | v1_8/mixin3 L622 | `VanillaGuiScreenEventHandlerV1_8Mixin` | `VanillaGuiScreen_EventHandlerMixin_v1_8` |
| `FullBrightnessMixin2` | `FullBrightnessMixin_v1_8` | v1_8/mixin3 L623 | `FullBrightnessV1_8Mixin` | `FullBrightnessMixin_v1_8` |
| `GuiBackgroundProcessesMixin2` | `GuiBackgroundProcessMixin_v1_8` | v1_8/mixin3 L624 | `GuiBackgroundProcessesV1_8Mixin` | `GuiBackgroundProcessMixin_v1_8` |
| `GuiHandlerMixin2` | `GuiHandlerReplayMixin_v1_12` | **v1_12/mixin3 L584** | `GuiHandlerReplayMixin` | `GuiHandlerReplayMixin_v1_12` |
| `GuiHandlerMixin22` | `GuiHandlerReplayMixin_v1_8` | v1_8/mixin3 L626 | `GuiHandlerReplayV1_8Mixin` | `GuiHandlerReplayMixin_v1_8` |
| `GuiHandlerMixin3` | `GuiHandlerMixin_v1_8` | v1_8/mixin3 L625 | `GuiHandlerRecordingV1_8Mixin` | `GuiHandlerMixin_v1_8` |
| `GuiPathingMixin2` | `GuiPathingMixin$11_v1_12` | **v1_12/mixin3 L586** | `GuiPathingRealtimeTickV1_8Mixin` | `GuiPathingMixin$11_v1_12` (inner-class file) |
| `GuiPathingMixin22` | `GuiPathingMixin$11_v1_8` | v1_8/mixin3 L628 | `GuiPathingRealtimeTickMixin` | `GuiPathingMixin$11_v1_8` (inner-class file) |
| `GuiPathingMixin3` | `GuiPathingMixin_v1_8` | v1_8/mixin3 L627 | `GuiPathingKeyframeWarningV1_8Mixin` | `GuiPathingMixin_v1_8` |
| `GuiRecordingControlsMixin2` | `GuiRecordingControlsMixin_v1_8` | v1_8/mixin3 L629 | `GuiRecordingControlsV1_8Mixin` | `GuiRecordingControlsMixin_v1_8` |
| `GuiRecordingOverlayMixin2` | `GuiRecordingOverlayMixin_v1_8` | v1_8/mixin3 L630 | `GuiRecordingOverlayV1_8Mixin` | `GuiRecordingOverlayMixin_v1_8` |
| `GuiReplayListMixin2` | `GuiReplayViewerMixin_v1_8` | v1_8/mixin3 L631 | `GuiReplayListV1_8Mixin` | `GuiReplayViewerMixin_v1_8` |
| `GuiSavingReplayMixin2` | `GuiSavingReplayMixin_v1_8` | v1_8/mixin3 L632 | `GuiSavingReplayV1_8Mixin` | `GuiSavingReplayMixin_v1_8` |
| `MCVerMixin2` | `MCVerMixin_v1_8` | v1_8/mixin3 L633 | `MCVerV1_8Mixin` | `MCVerMixin_v1_8` |
| `MessageDeserializerMixin2` | `MessageDeserializerMixin_v1_8` | v1_8/mixin3 L634 | `MessageDeserializerV1_8Mixin` | `MessageDeserializerMixin_v1_8` |
| `PacketListenerMixin2` | `PacketListenerMixin_v1_8` | v1_8/mixin3 L635 | `PacketListenerV1_8Mixin` | `PacketListenerMixin_v1_8` |
| `PboOpenGlFrameCapturerMixin2` | `PboOpenGlFrameCapturerMixin_v1_8` | v1_8/mixin3 L636 | `PboOpenGlFrameCapturerV1_8Mixin` | `PboOpenGlFrameCapturerMixin_v1_8` |
| `ReplayHandlerMixin2` | `ReplayHandlerMixin_v1_8` | v1_8/mixin3 L637 | `ReplayHandlerV1_8Mixin` | `ReplayHandlerMixin_v1_8` |
| `ReplayModBackendMixin2` | `ReplayModBackendMixin_v1_8` | v1_8/mixin3 L638 | `ReplayModBackendV1_8Mixin` | `ReplayModBackendMixin_v1_8` |
| `ReplayModMixin2` | `ReplayModMixin_v1_8` | v1_8/mixin3 L639 | `ReplayModV1_8Mixin` | `ReplayModMixin_v1_8` |
| `ReplayModMixinConfigPluginMixin2` | `ReplayModMixinConfigPluginMixin_v1_8` | v1_8/mixin (metamixin) L602 | `ReplayModMixinConfigPluginV1_8Mixin` | `ReplayModMixinConfigPluginMixin_v1_8` |
| `ReplayModRecordingMixin2` | `ReplayModRecordingMixin_v1_8` | v1_8/mixin3 L640 | `ReplayModRecordingV1_8Mixin` | `ReplayModRecordingMixin_v1_8` |
| `SettingKeysMixin2` | `SettingKeysMixin_v1_8` | v1_8/mixin3 L641 | `SettingKeysV1_8Mixin` | `SettingKeysMixin_v1_8` |
| `SettingsRegistryMixin2` | `SettingsRegistryMixin_v1_8` | v1_8/mixin3 L642 | `SettingsRegistryV1_8Mixin` | `SettingsRegistryMixin_v1_8` |
| `VanillaGuiScreenMixin2` | `VanillaGuiScreenMixin_v1_8` | v1_8/mixin3 L643 | `VanillaGuiScreenV1_8Mixin` | `VanillaGuiScreenMixin_v1_8` |

## Corrections to the twin names (exposed by this cluster)

* **`GuiHandlerMixin3` / v1_8 `GuiHandlerMixin`** — twin is
  `GuiHandlerRecordingV1_8Mixin`, but no recording class exists upstream: the
  v1_8 mixin only injects `onGuiInit`. Real name `GuiHandlerMixin_v1_8`.
* **`GuiReplayListMixin2`** — mixes `GuiReplayViewer.GuiReplayList` (the tree
  dropped the outer qualifier). Twin `GuiReplayListV1_8Mixin`; real name
  `GuiReplayViewerMixin_v1_8`.
* **`GuiPathingMixin3` / v1_8 `GuiPathingMixin`** — twin
  `GuiPathingKeyframeWarningV1_8Mixin` is invented; real name
  `GuiPathingMixin_v1_8`.
* **`GuiPathingMixin22` / `GuiPathingMixin2` are the inner class
  `GuiPathingMixin$11`** (`@Mixin(targets = "…GuiPathing$10")`, `run` +
  `ListenableFuture.cancel`). Upstream ships `GuiPathingMixin$11_v1_8` /
  `GuiPathingMixin$11_v1_12` as top-level files named after the inner class;
  kept verbatim. Twin labels are **inverted**: `GuiPathingMixin22` (v1_8) got
  `GuiPathingRealtimeTickMixin` while `GuiPathingMixin2` (v1_12) got
  `GuiPathingRealtimeTickV1_8Mixin`.
* **Two rows are v1_12, not v1_8**: `GuiHandlerMixin2` (upstream
  `GuiHandlerReplayMixin_v1_12`) and `GuiPathingMixin2`. The other 41 rows are
  the v1_8 module.
* Upstream names without a `Mixin` suffix: `AbstractGuiElement_v1_8`,
  `AbstractGuiLabel_v1_8`. Singular `Process`:
  `GuiBackgroundProcessMixin_v1_8`, `GuiBackgroundProcessMixin_Element_v1_8`.
* `ElementMixin2` mixes `GuiBackgroundProcesses$Element`; `EventHandlerMixin2`
  mixes `VanillaGuiScreen$EventHandler`; `AbstractGuiPopupMixin2` mixes the
  anonymous `AbstractGuiPopup$2` (upstream `GuiPanel`).

## Evidence (two or more per class)

Every row in the TSV carries: (1) the mixin source signatures — `@Mixin` target,
`@Inject`/`@ModifyConstant`/`@Redirect` method names and target descriptors;
(2) the `mixin-renames.tsv` line pinning the obfuscated source package
(`v1_8/mixin3` vs `v1_12/mixin3`); (3) the matching published
`LunarClient/ReplayModMixins` class (file name, class declaration and mixin
config entry); (4) the already-declared twin from `57e98a2b4`.

Jar cross-check: `libs/multiver-full/lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar`
contains the v1_8 module at
`com/moonsworth/lunar/replaymod/forge/v1_8/HRICOROOOCCOCOROCRHHCRRIRCOICO/` with
the same 39-entry config as `src/main/resources/mixins.ichor.replaymod.v1_8.json`
(extracted config is byte-identical). Example decompiled with CFR: class
`HORHROIOIOICIRHIOCOICHHHIHCIIO` is `@Mixin(AbstractGuiButton)`, injects `draw`
at `HEAD` cancellably and constructs the border/background animations with the
same constants (`1076176165` / `0x20FFFFFF`, `0x45FFFFFF`) as
`AbstractGuiButtonMixin2.java` → `AbstractGuiButtonMixin_v1_8`.

## Skipped rows

**None.** All 43 paths exist and apply (`skipped=0`). Not touched because they
are outside the cluster: the 39 plain-named v1_12 siblings and the 43
`57e98a2b4` twins (duplicates to merge, not rename).

## Ambiguities / risks

* `_v1_8` / `_v1_12` are version markers required by the upstream names; they are
  not the lazy trailing digits rule 5 bans (the upstream declaration itself is
  `class AbstractGuiButtonMixin_v1_8`). If the campaign insists on the local
  `…V1_8Mixin` spelling instead, the merge pass should delete the cluster copy
  and keep the twin — an inverse rename row would collide and cannot apply.
* `GuiPathingMixin$11_*` contains `$` like the other 140 decompiled inner-class
  files in the tree; the applier handles it as a top-level declaration (dry run:
  `0 nested`).
* Nothing references either copy (each name counts 1 hit = its own file), so the
  rename has no call-site fallout; wiring the mixins happens through the
  (still obfuscated) mixin JSONs, which is a separate deobfuscation task.

## Verification

* Applier dry run: `rows=43 skipped=0 files_touched=43 files_renamed=43`
  (and the same with `--apply` on the scratch worktree).
* ECJ A/B in a scratch worktree (`/tmp/opencode/wt61`, HEAD before/after this
  map, `QA_WORK=/tmp/opencode/wt61-work`; main's checked-in baseline is stale,
  so both passes were measured in the same checkout):

```
before: 3305 failing files
after:  3305 failing files
NEW:   exactly the 7 renamed files that were already failing under their old
       names (AbstractGuiDropdownMenuMixin2, AbstractGuiLabelMixin2,
       GuiPathingMixin2, GuiPathingMixin22, MCVerMixin2,
       ReplayModBackendMixin2, SettingsRegistryMixin2)
FIXED: the same 7 old paths, nothing else
```

No new failing files beyond the rename mapping, no other moves.
