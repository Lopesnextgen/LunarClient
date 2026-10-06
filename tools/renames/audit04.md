# Audit cluster 04 — `client.{click.fishing, click.mixin, click.rewindhandlers, coordinates, coordinates.mixin, feature, fishing, fishing.gui.mixin}`

Slice: `tools/renames/audit-cluster-04.txt` (8 packages). Reconstructed from the
died audit-04 subagent's saved reasoning (opencode.db `ses_f5fa9de68...`, died
"Not Enough Credits" while adapting to concurrent tree changes) plus
independent verification of every row against the CURRENT tree (a sibling map
had already renamed `Coordinates2/3/5` mid-audit — those rows are omitted here).

Maps produced:

* `tools/renames/classes-audit04.tsv` — 15 class renames (all applied).
* `tools/renames/packages-audit04.tsv` — 7 package moves applied
  (an 8th, `feature` → `cosmetic`, was applied then **reverted**, see below).

## Per-package verdicts

* `click.fishing` → `client.loading`: `Fishing` → `LoadingStage`
  (FogHandler registers `instanceof Fishing` with the loading screen).
* `click.mixin` → `client.gui`: `Bridge7Iterator` →
  `CompetitiveDisconnectScreen` (confirm dialog
  "gui.apollo.competitiveDisconnect").
* `click.rewindhandlers` → `client.font`: `Rewindhandlers` →
  `CachedTextMesh`, `GlyphTexture` → `TextRenderPass` (GL list + texture),
  `Object2ObjectOpenHashMapImpl` → `TextRenderCache` (string/color/shadow key).
* `coordinates` → `client.hostedworld`: `Coordinates` → `HostedWorldHost`
  (uuid+username), `Gui2Handler` → `HostedWorldPlayer`
  (username/uuid/lastOnline/isOnline, JsonProvider).
* `coordinates.mixin` → `client.hostedworld`: `Coordinates` →
  `HostedWorldEntryRenderer` (join icons + per-UUID cache),
  `CoordinatesThread` → `HostedWorldRefreshThread`.
* `feature`: `Module` → `Cosmetic`, `ModuleType` → `CosmeticSlot`,
  `ModuleType3_2` → `BodywearCosmetic` (BODY/BODYWEAR). Package stays
  `feature` for now (see revert note).
* `fishing` → `client.external`: `Fishing` → `ExternalLinkRegistry`
  ("[Ichor/External Link]" log), `Nameplate` → `ModMenuCompat`
  (terraformersmc ModMenu reflection).
* `fishing.gui.mixin` → `client.external.gui`: `Fishing2Extension` →
  `GuiExternalLink` (version-gated ExternalLink hook).

## feature→cosmetic revert (documented, not dropped)

The `feature` → `cosmetic` move was applied, then reverted: `BodywearCosmetic
extends ModuleType3`, and `ModuleType3` (abstract cosmetic base) is quarantined
(same-package jar phantom under the old path). Rescuing it requires translating
its refs to post-rename names (`Cosmetic`, `CosmeticSlot`, renamed `Fov`/`ModuleType2`
targets) plus fixing its 3 ledger errors — quarantine-rescue workstream job.
Redo the package move together with that rescue. Class renames were kept.
