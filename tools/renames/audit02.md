# Audit cluster 02 — `bridge.{horsestats, horsestats.mixin, itemcounter, itemcounter.mixin, lighting, mixin, slayer}` + `client` root

Slice: `tools/renames/audit-cluster-02.txt` (8 packages). Reconstructed from the
died audit-02 subagent's saved reasoning (opencode.db `ses_f5fa9de77...`, died
"Not Enough Credits") plus independent verification of every row against the
tree. Every applied new name was checked unique tree-wide; colliding old names
use the 5th `file` column.

Maps produced:

* `tools/renames/classes-audit02.tsv` — 50 class renames (47 applied, 3 reverted, see below).
* `tools/renames/packages-audit02.tsv` — 3 package moves (`lighting` → `scoreboard`, `slayer` → `optifine`, `horsestats.mixin` → `particle`).

## Per-package verdicts

* `client` root — the Apollo protocol layer dumped at the package root:
  19 `Highlight3Iterator*` → `<Id>ApolloHandler` (module id from the `super("id","Name")`
  call, all verified), `MixinExtra` → `ApolloApprovedServer` (toString literally
  says so), `MixinExtraType` → `RenderScope`, `MixinRewindhandlers` →
  `ApolloTypeRegistry` (protobuf TypeRegistry builder), `Slayer` →
  `LunarLogger` (Log4j "Lunar Client" facade), `MixinHelper$Data23` →
  `ApolloButtonContent`, `MixinHelper$Type3` → `ApolloButtonShape`,
  `Highlight3Iterator$Data2` → `EventHandlerEntry`, `Highlight3Iterator19$Data`
  → `NotificationLink`, `Highlight3Iterator28$Type` → `HorizontalAlignment`
  (last three orphans, medium confidence). `Badge` verified correct, kept.
* `bridge.lighting` — WRONG package (scoreboard bridges) → `bridge.scoreboard`.
  `Lighting` → `ScoreboardObjectiveBridge` (bridge$getScoreboard + display name).
* `bridge.slayer` — WRONG package (OptiFine bridges) → `bridge.optifine`.
  `Slayer` → `ConnectedProperties`, `SlayerType` → `ConnectedTextureMethod`
  (CTM values). The two `ConnectedPropertiesMixin` files needed their `@Mixin`
  target FQN-qualified (`net.optifine.ConnectedProperties` vs the bridge
  interface) after the rename.
* `bridge.horsestats.mixin` — WRONG package (particle system) →
  `bridge.particle`. `Horsestats` → `ParticleData`, `HorsestatsHandler` →
  `SimpleParticleData`, `HorsestatsType` → `LegacyParticleType`.
* `bridge.horsestats` — generic MCP bridge bucket (earlier wave's verdict
  stands; package kept). `Horsestats20Extension2` → `BlockPosBridge`,
  `Horsestats$Type` → `RaytraceType`, `HorsestatsType$Type/$Type2/$Type3/$Extension`
  → `DirectionAxis`/`DirectionAxisDirection`/`AxisPlane`/`AxisCoordinateChooser`.
* `bridge.itemcounter` — generic world bridge bucket (package kept).
  `ItemcounterType` → `DifficultyBridge`, `Type2_2` → `BiomeCategory`,
  `Type2_3` → `PathNodeType`, `2_3` → `MapDataBridge`, `5_2` →
  `WorldInfoBridge`, `3_3` → `WorldChunkManagerBridge`, `4_3` →
  `WorldBorderBridge`, `$Type` → `MapDecorationType`, `$Extension` →
  `BoxEdgeConsumer`.
* `bridge.mixin` — CORRECT package (mixin accessors). `MixinHelper` →
  `HoverEventBridge` (getValue():IChatComponent = HoverEvent.getValue; pair of
  the correct ClickEventBridge).

## Deferred (documented, not dropped)

* 3 rows **applied then reverted**: `Horsestats20Extension2` → `BlockPosBridge`,
  `HorsestatsType$Type` → `DirectionAxis`, `HorsestatsType$Type3` → `AxisPlane`.
  Cause: stale-jar signature coupling — `Bridge2.method4` (jar-only class)
  returns jar-type `Horsestats20Extension2`, so the renamed bodies became
  incompatible. Same hazard class as the 2 baseline failures. Re-apply after
  `Bridge2` is rescued into the tree. The TSV rows are kept as the record.
* Left out (unresolvable, zero consumers): `Horsestats9/17/24/27` (empty
  markers), `bridge.horsestats.HorsestatsType` (SHOW_TEXT orphan),
  `itemcounter.Itemcounter/2_2/3_2/4_2/$Type2`.
* `bridge.horsestats` / `bridge.itemcounter` package renames deferred (grab-bags;
  earlier wave's choice stands).
