# Audit cluster 05 — `client.{fishing.highlight, fishing.holograms, fishing.mixin, fishing.rewindhandlers, fog, fog.chest, fog.click, fog.fishing}`

Slice: `tools/renames/audit-cluster-05.txt` (8 packages). Note: the top-level
`client.fishing` package itself was already renamed to `client.external` by
audit04 (so the four `Fishing2Extension` files below live under `external/`
now); the `fog` top package was verified correct (loadable/telemetry
framework) by inspection.

Maps produced:

* `tools/renames/classes-audit05.tsv` — 9 class renames (all applied, gated green).
* No package moves — every package in the slice is already correctly placed.

## Per-package verdicts

* `external.mixin` — CORRECT package. `Fishing2Extension` →
  `MainMenuExternalLink` (main-menu button hook via ExternalLinkRegistry).
* `external.highlight` — CORRECT package. `Fishing2Extension` →
  `RecordingExternalLink` (recording state + icon; Replaymod consumers).
* `external.holograms` — CORRECT package. `Fishing2Extension` →
  `HologramExternalLink` (empty marker; medium confidence).
* `external.rewindhandlers` — CORRECT package. `Fishing2Extension` →
  `TurboExternalLink` (turbo renderer state callbacks).
* `fog.chest` — CORRECT package (Styngr music). `Chest` → `StyngrSong`
  (id/styngrId/name/image/song/artist/album/lyrics).
* `fog.click` — CORRECT package (texture processing). `Click` →
  `TextureProcessor` (implemented by the Overlay texture processors).
* `fog.fishing` — CORRECT package (skin loading). `Gui2Handler` →
  `SavedSkin`, `Gui2Type` → `SkinType` (CLASSIC/SLIM), `FishingException` →
  `SkinLoadException` (skin/profile fetch failures).
* `fog` top — CORRECT, no rows.
