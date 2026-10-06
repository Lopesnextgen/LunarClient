# Cluster 57 — `com.moonsworth.lunar.bridge.horsestats` (bridge bucket)

## Summary

The package name is a first-pass misnomer. It is a generic vanilla/MCP **bridge
bucket** (the wave-2 `classes-bridgehorsestats` map already documented "not
horse stats"); an earlier pass renamed 25 of its classes and moved them to
`bridge.minecraft`, the 2026-09-16 rescue then restored jar twins for every
class, so the tree now carries **two generations** of the same bridge types:

* `bridge/minecraft/<RealName>.java` — the already-renamed canonical copies,
  referenced by the canonical files (`Bridge.method16/47/57/70`,
  `legacy/wrapper/ClipboardBridgeImpl`, `EnumFacingMixin`, …).
* `bridge/horsestats/HorsestatsN.java` — the restored twins this cluster names.

Map: **43 rows** written (0 applier skips in dry run, 40 files renamed,
977 files touched).
Rows = 41 of the 45 cluster rows + 2 out-of-inventory rows added because they
are the owners of listed nested rows and are equally lazy: `HorsestatsHandler`
(→ `MissResult`, base class of `Data`/`Data2`/`Data3`) and the nested
`HorsestatsHandler$Data` (→ `BlockPositionHitResult`, sits between the listed
`Data2`/`Data3` rows).
The cluster contained no `net.minecraft.*` rows and no shaded third-party code,
so there was nothing to skip for those two categories.

Highlights:

* Raytrace result family (`HorsestatsHandler` + `Handler2…8` + nested
  `Data/Data2/Data3`) named with the **real Apollo names**
  (`lunar-client-names.tsv`: `…packetenrichment.raytrace.{RayTraceResult,
  MissResult, BlockHitResult, EntityHitResult, Direction}`): MISS result
  `MissResult`, block hits `BlockPositionHitResult`/`BlockHitResult`/
  `DirectedBlockHitResult`, plus Lunar-only `Biome/Chunk/Spray/ClientEntity/
  Light/CosmeticHitResult`; evidence from `client/util/raytrace/Raycaster`
  (`Type.BLOCK/ENTITY/CHUNK/COSMETIC/SPRAY/CLIENT_ENTITY`) and consumers
  (Waila, Markers/MarkerManager, RaycastDebug, chest `SImpl`).
* Vanilla bridges never renamed before now get canonical names from their
  `@Annotation` version tables: `CryptManagerBridge`, `ItemTransformsBridge`,
  `ItemTransformVec3fBridge`, `AxisAlignedBBBridge`, `ResourceLocationBridge`,
  `Vec3Bridge`, `TimerBridge`, `MathHelperBridge`.

## Skipped rows (4)

| old | why |
|---|---|
| `Horsestats9` | `float method1(BridgeExtension2_5, BridgeExtension2_5)` (EntityLiving pair), zero references in tree/jar/staging, no `@Annotation`. Naming it would be invention. |
| `Horsestats17` | empty 103-byte marker interface, no members, zero references, no metadata. |
| `Horsestats24` | same as 17. |
| `Horsestats27` | same as 17. |

These four were already investigated and skipped by two earlier maps
(`classes-16.md` §Skipped and `classes-lightingnameplate.md` §Skipped, same
conclusion: "unresolvable, dead … renaming would be invention"). This cluster
follows that decision; if the main agent wants them renamed anyway, they need a
deliberate "Unknown/Dead" convention, not per-class guesses.

## Ambiguities / notes

1. **Duplicates of already-renamed `bridge.minecraft` twins.** All rows whose
   evidence says "rescued duplicate of …" collide semantically with an existing
   class. The applier guard rejects a rename onto an existing declaration, so
   the twins got distinct names. Recommended follow-up: merge via
   `tools/find_duplicates.py` (it already pairs `Horsestats19`↔`DamageSourceBridge`,
   `Horsestats22`↔`EnumChatFormattingBridge`, `Horsestats25`↔`EnumFacingBridge`)
   and delete the `bridge/horsestats` copy, keeping the `bridge.minecraft` name.
   Pairs: 2↔ModelRotationBridge, 4↔GuiResourcePackListBridge, 5↔IChatComponentMarker,
   7↔FaceBakeryBridge, 10↔LanguageBridge, 11↔ImageConverterBridge,
   13↔ChatComponentFactoryBridge, 16↔TexturedBoxBridge, 18↔KeyBindingEntry,
   19↔DamageSourceBridge, 20↔Vec3iBridge (same package!),
   21↔MovingObjectPositionBridge, 22↔EnumChatFormattingBridge,
   23↔ClipboardBridge, 25↔EnumFacingBridge, 26↔GameProfilePropertyMerger,
   29↔LunarSymbols, Type2↔EntityEquipmentSlotBridge, Type3↔EnumFacingValue,
   Type4↔MovingObjectTypeBridge, Type5↔KeyBindingSource, Type6↔DyeColorBridge,
   Type7↔ClickEventActionBridge, Type8↔ChatFormatting.
2. **`Horsestats20` ↔ same-package `Vec3iBridge`** is jar-coupled (see
   `APPLIED.md` audit02 / `moves-bridgefix.tsv`): `Bridge2.method4` returns the
   `bridge.horsestats` type, so `Vec3iBridge` had to stay behind. Named
   `Vector3iBridge`; when the jar coupling is resolved, merge into `Vec3iBridge`
   and keep `BlockPosBridge` for `Horsestats20Extension2` (still deferred).
3. **Raytrace interface name assumption.** `Horsestats_3` (the generic
   self-typed result interface) is in cluster 58, not here. I assumed its
   subagent will take `RayTraceResult`; I therefore named the base/empty
   implementation `MissResult` (Apollo vocabulary). If cluster 58 picks
   `MissResult` for the interface instead, one of the two maps will be skipped
   by the applier's duplicate guard — reconcile before applying both.
4. **`HorsestatsType7` is damaged**: the rescue decompile dropped
   `RUN_COMMAND`, so the enum reads `OPEN_FILE, OPEN_FILE;`. The canonical twin
   `bridge.minecraft.ClickEventActionBridge` has both constants. Merge/repair
   (not done here — subagents do not edit sources).
5. **Leftovers not covered by any cluster** (names are not "lazy" by
   `tools/name_inventory.py` rules, so no cluster picked them up):
   * same package: `Horsestats` (vanilla **Session** bridge, `bridge$getPlayerID/
     getUsername/getToken/getProfile` — recommend `SessionBridge`, free),
     `Horsestats$Type` (twin `bridge/minecraft/RaytraceType`),
     `Horsestats19Extension` (EntityDamageSource — recommend
     `EntityDamageSourceBridge`, free), `Horsestats20Extension`
     (**SectionPos** bridge, `@Annotation v6 net/minecraft/core/SectionPos` —
     recommend `SectionPosBridge`, free), `Horsestats20Extension2` (BlockPos,
     jar-coupling deferral, planned `BlockPosBridge`), `BridgeExtension`
     (stays per precedent), and the nested `$` set
     (`HorsestatsHandler2…8$Data`, `HorsestatsHandler7$Type`,
     `Horsestats8$Data`, `Horsestats12$Data`, `Horsestats15$Data`,
     `Horsestats16$Extension`, `Horsestats20$Extension`,
     `HorsestatsType$Extension` (twin `bridge/minecraft/AxisCoordinateChooser`),
     `HorsestatsType$Type`, `$Type2`, `$Type3`, `Horsestats26$1`).
   * cluster 58: `Horsestats_2`, `Horsestats_3`, `Horsestats_4`,
     `HorsestatsType_2`; cluster 102: `bridge/horsestats/mixin/*`.
   Recommend a nested-name + leftovers pass after this wave.
6. **Not this package:** `bridge/horsestats/mixin/*` is the particle engine
   (audit02 → `bridge.particle`) and lives in cluster 102. The unrelated
   `client.horsestats` (profile model, cluster 90) and `client.horsestats.mixin`
   (cluster 97) packages are a different subsystem — despite the shared
   placeholder stem.
7. **Cross-map collision resolved:** cluster 39 (another subagent) claims
   `ItemCameraTransformsBridge` for `com.moonsworth.lunar.bridge.MixinHelper2_5`
   (the vanilla-class duck with `bridge$applyTransform`). `Horsestats6` is the
   *display data model* of the same vanilla class family, so it was named
   `ItemTransformsBridge` (modern vanilla holder name) instead. No other name in
   this map collides with the maps present in `wave5/` at write time.
