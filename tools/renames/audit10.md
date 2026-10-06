# Audit cluster 10 — `framework.feature.{debug.optimizationdebugmod, debug.shaderdebugmod, directionhud, f3display, f3display.mixin, freelook, gui, heightlimit}`

Slice: `tools/renames/audit-cluster-10.txt` (8 packages). Reconstructed from the
died audit-10 subagent's saved reasoning (opencode.db `ses_f5fa9de2b...`, died
"Not Enough Credits" at seq 882 before writing) plus independent verification
of every row against the tree. Every new name was checked unique tree-wide.

Maps produced:

* `tools/renames/classes-audit10.tsv` — 26 class renames (4 nested `F3display$Data*`).
* `tools/renames/packages-audit10.tsv` — 2 package moves (`gui` → `overlay`, `f3display.mixin` → `f3display.chart`).

## Per-package verdicts

* `debug.optimizationdebugmod` — CORRECT package (the optimization-debug
  feature). `OptimizationdebugmodType` → `OptimizationDebugOption` (17
  toggles: FAST_TEXT, FRUSTUM_IMPROVEMENTS, ...). `OptimizationDebugRenderTarget`
  → `OptimizationDebugCategory` (ENTITY/BLOCK_ENTITY/LEVEL with
  shouldRender/shouldCancel; "RenderTarget" wrongly implies a framebuffer).
* `debug.shaderdebugmod` — CORRECT package (shader-debug/cloak feature).
  `Markers3Handler` → `ShaderCloakEditorContext` (DriverViewContext impl
  delegating to `Shaderdebugmod.method34`; sibling exporter/watcher/renderer).
  The main `Shaderdebugmod` class itself is quarantined — follow-up for the
  rescue workstream, not renamed here.
* `directionhud` — CORRECT package. `Gui2Extension` → `DirectionHudPosition`
  (ABOVE/BELOW/NONE icon placement; only live class, the Directionhud
  renderers are quarantined).
* `f3display` — CORRECT package (F3 debug feature). `Gui2Extension` →
  `F3DebugLine` (28 line ids). `F3display` → `F3DebugInfo` (static info
  utility). `F3display2` → `F3DebugRenderer` (per-frame layout). `F3display2_2`
  → `TargetInfoProvider`, `F3display_2` → `TargetInfoRegistry`,
  `F3display_3` → `F3DebugWriter` (sink interface implemented by
  `F3DataDebugTask`), `F3display3` → `F3PieSlice`, nested `F3display$Data*` →
  `F3DebugEntry`/`F3DebugRendererWriter`/`F3DebugConditionalWriter`/`F3DebugMeasurer`.
* `f3display.mixin` — WRONG package (zero `@Mixin` classes; scrambler
  artifact) → `f3display.chart`. `F3display` → `F3Chart` (abstract chart
  base), `F3displayImpl*` → `BandwidthChart`/`FpsChart`/`GpuChart`/`RamChart`/`PingChart`
  (labels verified in constructors).
* `freelook` — CORRECT package. `Gui2Extension` → `FreelookMode`
  (THIRD/FORWARD/FIRST + perspective; the mod's "mode" option).
* `gui` — WRONG package (every class is OverlayMod plumbing; the obf package
  held the readable `OverlayMod`/`OverlayMod$Type` classes) → `overlay`.
  `Gui` → `TextureAnimationCache` (UV cache lerped by `OverlayMod.method63`,
  fire-height smoothing), `GuiUpdater` → `OverlayTextureProcessor` (abstract
  base of the already-named Glass/OreTextureProcessor). `HudColorOverride`
  (earlier wave) verified correct, moves with the package.
* `heightlimit` — CORRECT package. `Heightlimit` → `HeightLimitOverride`
  (limit+displayName record from the Apollo override message).
  `HeightlimitType` → `HeightLimitVariant` (BEDWARS/BRIDGE/VANILLA/SERVER +
  featureId + filter). `HeightLimitRenderer` (earlier wave) verified correct.

## Uncertainties

* `F3display$Data2` vs `Data3` (renderer-writer vs conditional-writer) rest on
  `method12()` gating + `method8`/`method10` factory pairing; medium-high
  confidence.
* `ShaderCloakEditorContext` infers "editor" from the cloak-export/edit flow;
  medium-high confidence.
* `HeightLimitVariant` vs `HeightLimitMode`/`Preset`: `Variant` chosen, no
  ground truth in the readable tables.
