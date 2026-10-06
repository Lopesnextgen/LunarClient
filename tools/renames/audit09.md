# Audit cluster 09 — `framework.feature.{crosshair, crosshair.crosshairelytra, crosshair.mixin, debug, debug.fpsdebugmod, debug.fpsdebugmod.mixin, debug.fpsdebugmod.profilerdebugmod, debug.fpsdebugmod.profilerdebugmod.mixin}`

Slice: `tools/renames/audit-cluster-09.txt` (8 packages). Reconstructed from the
died audit-09 subagent's saved reasoning (opencode.db `ses_f5fa9de5...`, died
"Not Enough Credits" while finalizing) plus independent verification of every
row against the tree. Every new name was checked unique tree-wide.

Maps produced:

* `tools/renames/classes-audit09.tsv` — 18 class renames (1 nested `Profilerdebugmod2$Data`).
* `tools/renames/packages-audit09.tsv` — 3 package moves (empty `.mixin` buckets folded).

## Per-package verdicts

* `crosshair` — CORRECT (Crosshair/CrosshairPattern/CrosshairPresets all
  accurate, restructure-phase names).
* `crosshair.crosshairelytra` — CORRECT package. `Crosshairelytra` →
  `CrosshairShapeDrawer` (textured-shape drawing helper passed through the
  CrosshairChildMod/CrosshairElytra pipeline; medium confidence — the real
  jar's `CrosshairElytra` already lives in `mod/render`). Siblings
  `CrosshairShapeRenderer`/`CrosshairTextureRenderer` verified correct.
* `crosshair.mixin` — WRONG package (option enums, no mixins) → `crosshair`.
  `Gui2Extension` → `CrosshairMode` (SIMPLE/PRESET/CUSTOM).
* `debug` — CORRECT (`Debug` abstract module base accurate). `Gui2Extension`
  → `DebugType` (ALL/MOVEMENT_UI/ASSET_SERVER/IPC, the "debugType" option).
* `debug.fpsdebugmod` — CORRECT package (helpers; the real `FpsDebugMod` module
  is in `mod/misc`). `Fpsdebugmod` → `DebugArchive` (zip builder with
  errors.txt), `FpsdebugmodType` → `FpsDebugPhase`
  (PRE_COLLECT/PROFILER/THREAD_DUMP). Nested `Fpsdebugmod.Data` left alone
  (simple name+bytes holder, renames with its owner automatically).
* `debug.fpsdebugmod.mixin` — WRONG package (FpsDebugTasks, no mixins) →
  `fpsdebugmod`. Task names taken from their `name()` strings:
  `ThreadDumpTask`, `ProfileDataTask`, `FeatureFlagsTask`, `McSettingsTask`,
  `GpuObjectsTask`, `MiscDataTask`, `ModsEnabledTask`.
* `debug.fpsdebugmod.profilerdebugmod` — CORRECT package (soopy.dev profiler;
  `Profile*`/`StringPool` already renamed). `Profilerdebugmod` →
  `ProfilerEngine`, `Profilerdebugmod2$Data` → `ProfileUploadThrottle`
  ("ProfileUpload-Throttle" thread), `Profilerdebugmod2_2` →
  `ProfileEncoder` (gzip), `Profilerdebugmod2_3` → `ProfileUploader`.
* `debug.fpsdebugmod.profilerdebugmod.mixin` — WRONG package (samplers, no
  mixins) → `profilerdebugmod`. `Profilerdebugmod` → `ThreadProfiler`,
  `ProfilerdebugmodIterator` → `AllThreadsProfiler`.

## Uncertainties

* `CrosshairShapeDrawer` vs `CrosshairShapePainter`/`Canvas`: medium
  confidence, no ground truth in the readable tables.
* `DebugArchive` vs `DebugReport`/`DebugDump`: medium-high confidence.
