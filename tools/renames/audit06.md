# Audit cluster 06 — `client.{fog.gui, fog.mixin, fog.nameplate, fog.rewindhandlers, fov, fov.mixin, fov.mixin.rewindhandlers, fps}`

Slice: `tools/renames/audit-cluster-06.txt` (8 packages). A previous subagent
died mid-research here; this is a from-scratch audit of EVERY class in the
slice against the current tree (71 renames + 2 package moves, gated green).

Maps produced:

* `tools/renames/classes-audit06.tsv` — 71 class renames (all applied).
* `tools/renames/packages-audit06.tsv` — 2 package moves (`fps` → `molang`,
  `fov.mixin.rewindhandlers` → `fov.mixin`).

## Per-package verdicts

* `fog.gui` — CORRECT (options/mod-metadata models; `ModMetadata` parses
  `mod_metadata.json`, `OptionGroup` is a named option list; both Gson models,
  no live constructors — kept).
* `fog.mixin` — CORRECT package (alerts). `Gui2Handler` → `AlertCard`
  (id/name/text/color/icon/link/dismissible + `{COUNTDOWN}` templating).
* `fog.nameplate` — CORRECT package (blog posts; `GameBlogPostInteractionEvent`
  confirms the subsystem). `Gui2Handler` → `BlogPost`,
  `NameplateTask` → `BlogPostDownloadTask`.
* `fog.rewindhandlers` — CORRECT (`FeatureFlag` verified accurate, kept).
* `fov` — scrambler bucket mixing THREE subsystems (package split deferred as
  cross-cluster work): conversation/chat (`ChatMessage`, `MessageEmbed`,
  `ImageEmbed`/`LinkPreviewEmbed`, `SystemEvent` + 5 event types,
  `PlainText`/`Sticker`/`SystemEvent` contents, `Sticker`, `MessageContentType`,
  `EmbedType`, `SystemEventType`), emotes (`EmoteGift`, `Emote`,
  `StickerStore`), render (`TransformStack`, `Transform`, `GlResources`,
  `VertexBuilder`/`VertexSink`, `MeshPassRunner`, `ShaderUniformUpdater`,
  `PositionHistory`, `ShaderPass`, `RenderContextKind`,
  `GeckoRenderMode`). Already-correct names kept (`BipedModelRenderer`,
  `EmoteModelRenderer`, `ModelRenderConfig`, `RenderContext`, `RenderPass`,
  `ShaderPackHelper`, ...).
* `fov.mixin` — CORRECT (physics/outfit support; earlier wave's
  `PhysicsPoint`/`DistanceConstraint`/`Direction2D`/`EmoteGiftInfo` verified,
  kept).
* `fov.mixin.rewindhandlers` — WRONG segment (outfit conditions, not rewind) →
  merged into `fov.mixin`. `Rewindhandlers` → `EquipConditionPredicate`;
  `ConditionalOutfitTree` verified correct.
* `fps` — WRONG package (Molang JIT compiler, not frames-per-second) →
  `client.molang` (the real FPS mod is `mod.hud.Fps`). `Fps` →
  `MolangVariablePath`, `Fps6Handler` → `ConstantExpression`,
  `Fps7Extension` → `MolangBuiltin` (`MolangFunction` is taken by the
  quarantined parsed-function class), `Fps7Handler` → `MolangVariable`,
  `Fps7Extension17` → `MolangCustomFunction`, and all 30 remaining
  `Fps7ExtensionN` → `MolangMath<Op>` with ground truth from the
  `MolangBuiltinFunctions` registry (`math.min` → `MolangMathMin`, ...).

## Fixes applied during gating (same stale-jar class as audit02/03)

* `Emote` clashed with the `mchorse...Emote` import — import dropped, two uses
  FQN-qualified.
* `MolangBuiltin`/`ConstantExpression`/`MolangCustomFunction` reference
  jar-phantom `fps.Fps9`/`fps.Fps6` (same-package resolution broke on the
  `fps` → `molang` move) — explicit old-path imports added.
* `ConditionalOutfitTree` references quarantined same-package `Gui2Iterator`
  (jar-only under the old path) — explicit old-path import added.

## Uncertainties / deferred

* `VertexBuilder`/`VertexSink`/`MeshPassRunner`/`GlResources`/`ShaderPass` rest
  on quarantined consumers; medium confidence, flagged in evidence.
* `fov` package split (chat vs emotes vs render) and `fog.mixin`/`fog.nameplate`
  placement left for the restructure phase; `Fov` (`tick()`) left alone as too
  generic to rename safely.
