# Cluster 04 — `com.moonsworth.lunar.client.highlight.fishing` (35 rows)

Source revision: `tools/renames/cluster-04.txt` md5 `6fc05e556504b840980249711ca8446c`
(35 rows: 26 top-level types + 9 nested `Outer$Inner` types).
Map: `tools/renames/classes-highlightfishing.tsv` md5 `3f8b39773a1947f946cdf0265a8559ed`.

## What this package actually is

Like the rest of the `highlight` tree (see `classes-23.md` / `classes-26.md`) the
package name is a **decompiler artifact, not a feature**. The `highlight` tree is
Lunar's Forge-style **event bus + event types**; `highlight/fishing` is one
flattened bucket of plain event data classes. The word "fishing" comes from the
obfuscated class `HHRROIIHRRICIIHIIHICRHHRHOHHOO` (renamed `Fishing` elsewhere),
which named the original package — the classes themselves are **generic
client events** (health, damage, item use, entity spawn/remove, fog, mouse, …).

Provenance from `tools/work/mappings/normalize-renames.tsv`: this package is the
merge of two jar subtrees, which is why every event exists twice (plain name and
`_2` suffix):

| current file(s) | original jar subtree |
|---|---|
| plain `HighlightImpl2`…`HighlightImpl23`, `HighlightImpl_2`, `HighlightBase*` | `…/highlight/mixin/fishing/mixinCore/` and `…/mixinCore/mixin/` |

The `_2` duplicates (`HighlightImpl2_2`, `HighlightImpl10_2`, …) belong to the
same subtrees but are **not in this cluster** and were left alone. The plain
`HighlightImpl`/`HighlightBase` owners are likewise out of scope; only the 35
listed lazy classes were renamed.

## Naming convention

Events are named `Event<Purpose>` to match the already-applied sibling packages
(`highlight.mixin.highlight` → `EventRenderTooltip`, `EventPreAttackEntity`,
`EventSetupTerrain`, …). The codebase's own `toString()` in `HighlightImpl5_2`
literally prints `"EventPreAttackEntity(...)"`, confirming the convention.
Nested payload enums get a plain noun (`FogKind`).

## Renames (35 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `HighlightImpl2` | `EventAttack` | fired at HEAD of `Minecraft.clickMouse()` with `objectMouseOver`; Killsounds reads the MOP and registers a MELEE kill |
| 2 | `HighlightImpl3` | `EventEntityHealthUpdate` | fired on `EntityLivingBase.setHealth` with (entity, old, new); GuiRewindhandlers6 diffs them into damage/heal |
| 3 | `HighlightImpl4` | `EventEnchantmentCriticalHit` | fired at HEAD of `EntityPlayerSP.onEnchantmentCritical`, cancellable |
| 4 | `HighlightImpl5` | `EventEyeHeight` | fired in `EntityRenderer.orientCamera`; 1.7-animation mods rewrite the sneak eye height |
| 5 | `HighlightImpl6` | `EventLocalPlayerDeath` | empty event fired in `EntityPlayerSP.onLivingUpdate` when the local player is dead; clears totem counter / drops a death waypoint |
| 6 | `HighlightImpl7` | `EventEntityCollisionBoxes` | fired at RETURN of `World.getCollisionBoxes`; ServerBorders appends border boxes to the returned list |
| 7 | `HighlightImpl8` | `EventCriticalHit` | fired at HEAD of `EntityPlayerSP.onCriticalHit`, cancellable |
| 8 | `HighlightImpl9` | `EventPreRenderPlayer` | fired at HEAD of `RenderPlayer.doRender` via static factory (TEST/MONITOR stages), cancellable |
| 9 | `HighlightImpl10` | `EventPlayerReceiveDamage` | fired after `attackEntityFrom` with (player, DamageSourceBridge); hit counter + knockback trainer |
| 10 | `HighlightImpl11` | `EventEntityStatus` | fired at TAIL of `handleEntityStatus` with (entity, S19 status byte); Shields/Combo/ReachDisplay read codes 2/29/30 |
| 11 | `HighlightImpl12` | `EventEntityRemove` | fired at TAIL of `World.removeEntity` for non-player, non-projectile entities |
| 12 | `HighlightImpl13` | `EventRenderPlayerModel` | fired in `RenderPlayer.renderModel` with (player, matrix, model, partialTicks) |
| 13 | `HighlightImpl14` | `EventMouseMove` | cancellable mouse-delta event (dx/dy); Freelook and SkyblockLockMouse consume it |
| 14 | `HighlightImpl142` | `EventPlayerSleep` | (player, BlockPos, EnumStatus OK/NOT_POSSIBLE_HERE/…/NOT_SAFE) bed-sleep attempt |
| 15 | `HighlightImpl15` | `EventPlayerRemove` | `World.removeEntity` for players; evicts the player from fog/skin/tracker caches |
| 16 | `HighlightImpl16` | `EventFog` | cancellable `EntityRenderer.setupFog` event carrying a `FogKind` + stage |
| 17 | `HighlightImpl17` | `EventEntityHurtAnimation` | fired after `performHurtAnimation` with (entity, yaw) |
| 18 | `HighlightImpl18` | `EventItemUse` | fired at HEAD of `ItemStack.useItemRightClick` with (player, itemStack) |
| 19 | `HighlightImpl19` | `EventEntityInteract` | abstract right-click-entity event fired at HEAD of `PlayerControllerMP.interactWithEntity` |
| 20 | `HighlightImpl20` | `EventEntityJoinWorld` | cancellable `World.spawnEntity`/`loadEntities`/`joinEntityInSurroundings` event with (entity, world) |
| 21 | `HighlightImpl21` | `EventEntityMovement` | fired at TAIL of `handleEntityMovement` (S14PacketEntity) with (entity, onGround) |
| 22 | `HighlightImpl22` | `EventTotemPop` | totem activation (displayItemActivation + entity-status totem particle) |
| 23 | `HighlightImpl23` | `EventItemUseFinish` | fired at HEAD of `EntityPlayer.onItemUseFinish` with (player, itemInUse) |
| 24 | `HighlightImpl_2` | `EventPlayerDeath` | cancellable `EntityPlayerMP.onDeath` event |
| 25 | `HighlightBase2` | `EventLivingEntityBase` | abstract base carrying a `BridgeExtension2_5` (EntityLivingBase) |
| 26 | `HighlightBase3` | `EventEntityChunkBase` | abstract base carrying a `BridgeExtension` (Entity) |
| 27 | `HighlightImpl16$Data2` | `EventFogColor` | nested fog colour event (FogKind, density, RGB); Fog.method1 remaps it |
| 28 | `HighlightImpl16$Data3` | `EventFogRange` | nested fog start/end event; Fog.method3 rewrites start/end on 1.12+ |
| 29 | `HighlightImpl16$Type2` | `FogKind` | nested fog kinds LAVA/WATER/POWDER_SNOW/RENDER_DISTANCE/BLINDNESS/DARKNESS/DIMENSION/BOSS/ATMOSPHERIC |
| 30 | `HighlightBase2$Data6` | `EventLivingEntity` | nested plain entity-only variant of the living-entity base |
| 31 | `HighlightBase2$Data7` | `EventEntityScale` | nested entity scale event; fired in `preRenderCallback`, consumed by MobSize |
| 32 | `HighlightBase3$Data4` | `EventEntityChunk` | nested entity-enters-chunk event (entity + chunkX + chunkZ) |
| 33 | `HighlightBase$Data7` | `EventProjectileSpawn` | nested projectile spawn (projectile + thrower); Killsounds/FishingHookTracker |
| 34 | `HighlightBase$Data8` | `EventProjectileHit` | nested projectile hit (projectile + hit entity); EntityArrowMixin |
| 35 | `HighlightBase$Data9` | `EventProjectileRemove` | nested projectile removal (projectile only); `World.removeEntity` |

## Nested rows

Rows 27–35 are real nested types inside their owner files
(`HighlightImpl16.Data2/Data3/Type2`, `HighlightBase2.Data6/Data7`,
`HighlightBase3.Data4`, `HighlightBase.Data7/Data8/Data9`). Each map row carries
the owner in the evidence as `Owner$Old` and the owner file as the 5th column,
which the aware applier uses to scope the rename. The sibling nested types
`HighlightImpl16.Data`/`Type`, `HighlightImpl19.Data`, `HighlightImpl14$Data*`
and `HighlightImpl13$Data*` are **not in this cluster** and were left as-is.

## Applier dry run

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-highlightfishing.tsv
[aware-renames] 35 rows (9 nested); 6594 java files
[aware-renames] rows=35 skipped=0 files_touched=95 files_renamed=26 mode=dry-run
```

All 35 rows apply with the import-aware applier: 0 skipped, 0 warnings. The
tree-wide v1 applier would skip most of them (every `HighlightImplN` simple name
is declared in several `highlight.*` packages), so **use the aware applier** and
do **not** pass `--allow-collisions`.

## Caveats / follow-ups

* `HighlightBase` (the projectile base, rows 33–35) is **not** in the cluster, so
  it keeps its lazy name while its nested classes are renamed. The same is true
  for the `_2` duplicates and the sibling `highlight/mixin/fishing` package.
* Several firers live only in `tools/work/quarantine/src` (`MinecraftMixin2`,
  `ItemStackMixin2`, `NetHandlerPlayClientMixin(2)`, `EntityArrowMixin`,
  `PlayerControllerMPMixin2`, `EntityRendererMixin2`); the in-tree mixins carry
  the handlers. Evidence for those rows is taken from the quarantined sources.
* `HighlightImpl142` and `HighlightImpl19` have no in-tree consumers (only the
  quarantined firers / a `Data` inner class), so their names are inferred from
  payload + firer hook, not from a handler.