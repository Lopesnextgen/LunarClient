# Audit cluster 11 — `framework.feature.{hypixelbedwars(+.bedwars,+.mixin), inventorymod(+.mixin,+.slot.inventorysearch), itemcounter(+.mixin)}`

Slice: `tools/renames/audit-cluster-11.txt` (8 packages). A previous subagent
died mid-research here; this reconstruction verifies every row against the
CURRENT tree.

Maps produced:

* `tools/renames/classes-audit11.tsv` — 4 class renames (all applied, gated green).
* No package moves — every package in the slice is accurately placed.

## Per-package verdicts

* `hypixelbedwars` (+`.bedwars`, +`.mixin`) — CORRECT packages. `Bedwars` /
  `BedwarsUpdater` / `BedwarsTeamColorMapper` / `TitlesHandler` verified
  correct (BedWars stats + trap-title colors). `Hypixelbedwars` →
  `BedwarsBedModelFactory` (colored-bed baked-model factory).
* `inventorymod` (+`.mixin`, +`.slot.inventorysearch`) — CORRECT packages.
  `Inventorymod` → `InventorySlotUtils` (static slot-index helpers).
  `InventoryScreenPreserver` / `InventorySearchOverlay` verified correct.
* `itemcounter` (+`.mixin`) — CORRECT packages. `ItemcounterImpl` →
  `DamagedItemEntry` ("Impl" misleads: it extends `ItemCounterEntry`, sets
  item damage). Mixin `Itemcounter` → `BlockVariantAliases` (stone/dirt/
  leaves/log variant table). `ItemCounterEntry` + potion entries verified
  correct.

## Cross-slice follow-up (not touched)

`client/inventorymod/` (`CrashReporter`, `ExceptionSanitizer`,
`InventorymodError`, `Inventorymod`, `JsonSerializer`) is the Sentry crash
reporter hiding under an inventory name — different slice (audit inventory
covers it), flagged for its owner.
