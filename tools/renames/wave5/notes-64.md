# Cluster 64 — `com.moonsworth.lunar.client.highlight.fishing` (wave 5)

Source: `tools/renames/cluster-64.txt` (35 rows: 26 top-level + 9 nested).
Map: `tools/renames/wave5/classes-64.tsv` — **35 rows, 0 skipped**.

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-64.tsv
[aware-renames] 35 rows (9 nested); 9808 java files
[aware-renames] rows=35 skipped=0 files_touched=79 files_renamed=26 mode=dry-run
```

All 35 paths exist, no `net.minecraft.*` rows, no shaded third-party code.
Nothing skipped as missing.

## 1. What this package actually is

Despite the name, this is **not fishing**: it is the jar-faithful copy of Lunar's
**event-bus data classes** (same finding as `classes-23.md` / `classes-26.md` /
`classes-highlightfishing.md`). Every class extends `client.highlight.Highlight`
(the stale-jar dispatch base) or `client.highlight.HighlightImpl` (cancellable);
the only string in the package confirms the convention (`HighlightImpl5_2`
prints `"EventPreAttackEntity(player=...)"`).

The tree now holds **two live copies of this event family**:

| copy | origin | status |
|---|---|---|
| `com.moonsworth.lunar.client.event.fishing` | renamed by wave 3 (`classes-highlightfishing`, in place) + final-names wave `8e829d2d9` (`classes-finalclientA`) | canonical names (`EventAttack`, `EventFog`, …) |
| `com.moonsworth.lunar.client.highlight.fishing` (this cluster) | restored 2026-09-15 by the rescue sweep `b384aca20` from `libs/lunar-renamed-classes.jar` (snapshot mtime 2026-09-13 15:03, contains only `highlight/fishing/HighlightImpl*`) | still placeholders — what this map names |

Both copies are referenced from different call sites (import-aware counts below):
the twins are **load-bearing**, so this is the `classes-40` / `classes-51` /
`notes-51` duplicate situation, not the `notes-42` "byte-identical same-package
twin" one.

## 2. Why the canonical names are not usable in this map

The applier refuses a `new` name that is declared anywhere in the tree. Running
the old canonical map against the current tree shows the collision directly:

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/classes-highlightfishing.tsv
  SKIP HighlightImpl2 -> EventAttack: new name already declared
  ... (26x) ...
[aware-renames] rows=35 skipped=26 files_touched=20 files_renamed=0 mode=dry-run
```

All 26 top-level canonical names and all 9 nested ones (`EventFogColor`,
`EventFogRange`, `FogKind`, `EventLivingEntity`, `EventEntityScale`,
`EventEntityChunk`, `EventProjectileSpawn/Hit/Remove`) are declared in
`event/fishing`, so the map below uses **unique descriptive variant names**
(35/35 verified by a full declaration scan of `src/main/java`; none is declared
anywhere, no two rows share a name and none ends in a digit). The last column of
`classes-64.tsv` keeps the canonical name as the "intended final name" note.

## 3. Pairing (old → map name → canonical twin → refs old/twin)

Top-level rows (firing/consumer evidence from the class bodies and the applied
wave-3 map; refs = import-aware file counts, own file excluded):

| old | new (map) | canonical twin | refs |
|---|---|---|---|
| `HighlightImpl2` | `EventMouseOverAttack` | `EventAttack` | 4 / 4 |
| `HighlightImpl3` | `EventEntityHealthChange` | `EventEntityHealthUpdate` | 3 / 3 |
| `HighlightImpl4` | `EventEnchantCriticalHit` | `EventEnchantmentCriticalHit` | 2 / 3 |
| `HighlightImpl5` | `EventCameraEyeHeight` | `EventEyeHeight` | 2 / 4 |
| `HighlightImpl6` | `EventLocalDeath` | `EventLocalPlayerDeath` | 3 / 5 |
| `HighlightImpl7` | `EventCollisionBoxes` | `EventEntityCollisionBoxes` | 2 / 3 |
| `HighlightImpl8` | `EventMeleeCriticalHit` | `EventCriticalHit` | 2 / 3 |
| `HighlightImpl9` | `EventPlayerPreRender` | `EventPreRenderPlayer` | 4 / 3 |
| `HighlightImpl10` | `EventPlayerDamaged` | `EventPlayerReceiveDamage` | 4 / 4 |
| `HighlightImpl11` | `EventEntityStatusUpdate` | `EventEntityStatus` | 3 / 5 |
| `HighlightImpl12` | `EventEntityRemoval` | `EventEntityRemove` | 10 / 20 |
| `HighlightImpl13` | `EventPlayerModelRender` | `EventRenderPlayerModel` | 4 / 2 |
| `HighlightImpl14` | `EventMouseDelta` | `EventMouseMove` | 5 / 6 |
| `HighlightImpl142` | `EventSleepAttempt` | `EventPlayerSleep` | 1 / 1 |
| `HighlightImpl15` | `EventPlayerRemoval` | `EventPlayerRemove` | 13 / 10 |
| `HighlightImpl16` | `EventFogSetup` | `EventFog` | 7 / 4 |
| `HighlightImpl17` | `EventHurtAnimation` | `EventEntityHurtAnimation` | 3 / 3 |
| `HighlightImpl18` | `EventItemRightClick` | `EventItemUse` | 4 / 6 |
| `HighlightImpl19` | `EventInteractEntity` | `EventEntityInteract` | 2 / 2 |
| `HighlightImpl20` | `EventEntityWorldJoin` | `EventEntityJoinWorld` | 6 / 9 |
| `HighlightImpl21` | `EventEntityMove` | `EventEntityMovement` | 3 / 2 |
| `HighlightImpl22` | `EventTotemActivation` | `EventTotemPop` | 3 / 2 |
| `HighlightImpl23` | `EventUseItemFinish` | `EventItemUseFinish` | 4 / 4 |
| `HighlightImpl_2` | `EventPlayerDied` | `EventPlayerDeath` | 3 / 2 |
| `HighlightBase2` | `EventLivingBase` | `EventLivingEntityBase` | 4 / 4 |
| `HighlightBase3` | `EventChunkBase` | `EventEntityChunkBase` | 2 / 2 |

Nested rows (5th `file` column scopes the owner; owner in the evidence so the
applier's nested-first detection fires — verified `(9 nested)` in the dry run):

| old (owner) | new | canonical twin (owner) |
|---|---|---|
| `Data2` (`HighlightImpl16$Data2`) | `EventFogTint` | `EventFog$EventFogColor` |
| `Data3` (`HighlightImpl16$Data3`) | `EventFogDistance` | `EventFog$EventFogRange` |
| `Type2` (`HighlightImpl16$Type2`) | `FogSource` | `EventFog$FogKind` |
| `Data6` (`HighlightBase2$Data6`) | `EventLiving` | `EventLivingEntityBase$EventLivingEntity` |
| `Data7` (`HighlightBase2$Data7`) | `EventEntityScaling` | `EventLivingEntityBase$EventEntityScale` |
| `Data4` (`HighlightBase3$Data4`) | `EventEntityChunkEnter` | `EventEntityChunkBase$EventEntityChunk` |
| `Data7` (`HighlightBase$Data7`) | `EventProjectileLaunch` | `EventProjectileBase$EventProjectileSpawn` |
| `Data8` (`HighlightBase$Data8`) | `EventProjectileImpact` | `EventProjectileBase$EventProjectileHit` |
| `Data9` (`HighlightBase$Data9`) | `EventProjectileRemoval` | `EventProjectileBase$EventProjectileRemove` |

Sibling evidence: `HighlightImpl5` has `eyeHeight` +
`method1(float)` + `isModified()` and `HighlightImpl6` is empty (both bodies are
identical to `EventEyeHeight`/`EventLocalPlayerDeath`); `HighlightImpl16`'s
`Type2` enum is exactly `EventFog.FogKind`; `HighlightBase$Data7`'s single
`@Nullable` field is `EventProjectileSpawn`'s nullable thrower.

## 4. Decisions / caveats

* **Variant vs dedupe.** Per the `classes-40`/`classes-51` wave-5 practice the
  rescued duplicates get unique variant names so the tree stays greppable and the
  applier accepts the map. If the main agent prefers the `classes-59`/`notes-42`
  route, **drop all 35 rows** and delete the `highlight/fishing` copies (the
  intended final names are the twin column above).
* **Name choices forced by collisions:** `EventUseItem` is already declared by
  `client/event/mixin/fishing/mixin/EventUseItem` → `EventItemRightClick`;
  `EventRenderScale` is taken by `client/event/EventRenderScale` (the MobSize
  consumer is `MobSize`, hence `EventEntityScaling`); `FogType` is taken by the
  account-type enum → `FogSource`.
* **Don't dedupe blindly:** the canonical `EventLivingEntityBase` (and its
  `EventEntityScale`) still reference the dead placeholder bridge
  `CHCICRCHHRCCHRRRCHHHCIRIHOICIC`, while the `highlight/fishing` copy compiles
  with `BridgeExtension2_5`/`EntityLivingBridge`. A merge must take the fixed
  copy, not simply keep `event/fishing`.
* **Out of cluster (leave alone):** the owner `HighlightBase` (canonical
  `EventProjectileBase`), the `_2` siblings (`HighlightImpl2_2`… — canonical
  names already live in `event/fishing` as `EventItemDrop`, `EventPreAttackEntity`,
  …), and the nested types not listed in the cluster
  (`HighlightImpl16$Data/Type`, `HighlightImpl19$Data`,
  `HighlightImpl13$Data*`, `HighlightImpl14$Data*`).
* Refs above are live: e.g. `HighlightImpl15` has 13 reference sites vs the
  twin's 10, `HighlightImpl12` 10 vs 20 — both copies are compiled and called, so
  the map is not cosmetic-only.
