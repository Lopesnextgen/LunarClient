# moves-namesC — HighlightImpl / HighlightBase / Highlight / HighlightType / JsonDeserializerIterator

38 files from the assigned clusters (one assigned file, `client.util.highlight.Highlight`, was already
handled and applied by the concurrent `moves-namesA.tsv` map — see below). Package is unchanged for
every row in this map; only the class is renamed. Map: `tools/renames/moves-namesC.tsv`.

Dry-run: `python3 tools/apply_class_moves.py --map tools/renames/moves-namesC.tsv` →
`rows=38 skipped=0 files_moved=38 files_touched=456`.

Evidence sources: current sources + the pre-bucket-A compile classpath
`libs/lunar-renamed-classes.jar` (ASM scan of `<init>` call sites for firers whose mixin
source is not in the tree), plus `tools/mappings-snapshot/normalize-renames.tsv` for pre-bucket paths.

## HighlightImpl (12)

| # | old FQN | new FQN | role / evidence |
|---|---------|---------|-----------------|
| 1 | `client.event.HighlightImpl` | `client.event.CancellableEvent` | Cancellable event base (`cancel()/isCancelled()`); `LunarEventBus.method13` skips cancelled events (`LunarEventBus:333`); 60+ subclasses |
| 2 | `client.event.fishing.HighlightImpl` | `client.event.fishing.EventPotionThrow` | `(player, ItemStack)`; fired at HEAD of `ItemPotion.onItemRightClick` (`legacy/mixin/ItemPotionThrowMixin:29`); `PlayerStatTracker.method10` counts potion effects |
| 3 | `client.event.mixin.HighlightImpl` | `client.event.mixin.EventChatMessage` | Abstract mutable chat message (Component + cached plain text, `isChanged()`); `Data` fired from `GuiNewChatMessageMixin.lunar$handleChatMessage`; rewritten by `ChatFilter`/`ChatEmojiTransformer` |
| 4 | `client.event.mixin.fishing.HighlightImpl` | `client.event.mixin.fishing.EventDropItem` | `(boolean dropAll)`; fired from `EntityPlayerSP.dropOneItem/dropItem(Z)` (`legacy/EntityPlayerSPEventMixin:177`); replayed by `RewindRecorder` → `PlayerStateRecorder.method19` |
| 5 | `client.event.mixin.fishing.mixin.HighlightImpl` | `client.event.mixin.fishing.mixin.EventUseItemOnBlock` | `(blockPos, face, hitOffset, hitVec, b, b)`; fired after `PlayerControllerMP.useItemOn` (jar `MinecraftMixin2.lambda$lunar$postUseItemOnEvent`); `EtherwarpPreview.method3`; serialized by `Nameplate2Impl2.method3` via `bridge$useItemOn` |
| 6 | `client.event.mixin.gui.HighlightImpl` | `client.event.mixin.gui.EventSlotUpdate` | `(slot, oldStack, newStack)`; fired after `S2FPacketSetSlot` is applied to cursor/inventory/container (jar `NetHandlerPlayClientMixin2.lunar$onSet*`); 25 SkyBlock solvers use `getSlot()/method2()/method3()` |
| 7 | `client.event.mixin.holograms.HighlightImpl` | `client.event.mixin.holograms.EventClientShutdown` | Empty; posted by `Client.close()` (jar: `ldc HighlightImpl` + `LunarEventBus.method12`); `PersistentValuesListener` flushes `persistent.json`, `Rewind.method8` stops recording |
| 8 | `client.event.mixin.holograms.mixin.HighlightImpl` | `client.event.mixin.holograms.mixin.EventIchorHandlersLoaded` | `Map<String, Ichor5Handler_2>` built by `Client.method127(Ichor7)` from the Ichor pipeline; `FogHandler28` consumes the handler map |
| 9 | `client.event.mixin.nameplate.HighlightImpl` | `client.event.mixin.nameplate.EventRenderHudBase` | Abstract HUD-element render event (renderer, scaled resolution, `Markers`, focused flag); subclasses `EventRenderHud`/`EventRenderHudFocused`; posted per element from `EntityRenderer`/`GuiIngame` mixins; 45 HUD components implement `method3(event,w,h,focused)` |
| 10 | `client.event.mixin.rewindhandlers.HighlightImpl` | `client.event.mixin.rewindhandlers.EventKeybind` | `(GLFW key, InputAction, boolean)`; fired by jar `MinecraftMixin2.lambda$lunar$dispatchKeyPress`; consumed by Screenshot, ToggleSneak, SlotBinding, ScreenInteractionHandler, ChatImagePreview (ctrl+click) |
| 11 | `client.framework.feature.mod.rewindhandlers.HighlightImpl` | `client.framework.feature.mod.rewindhandlers.EventEquippedItemChange` | `(ItemStack, itemId, displayName)` `DynamicListenerEvent` `@TriggeredBy(EquippedItemListener)`; fired on held-item change; `SkyblockLockMouse.method3` reacts |
| 12 | `client.replay.highlight.HighlightImpl` | `client.replay.highlight.TimelineRenderJob` | `ReplayTimeline` subclass with `@SerializedName("savePath")` + target file; queued by `RewindRenderQueue` (`render_queue/*.json`), exported by `ExportSettingsPanel`, removed by `Rewindhandlers2_4` |

## HighlightBase (5)

| # | old FQN | new FQN | role / evidence |
|---|---------|---------|-----------------|
| 1 | `client.event.fishing.HighlightBase` | `client.event.fishing.EventProjectileBase` | Base of `EventProjectileSpawn/Hit/Remove` (projectile entity); `ProjectileTracker`, `FishingHookTracker`, `KillSoundTracker` |
| 2 | `client.event.mixin.fishing.HighlightBase` | `client.event.mixin.fishing.EventPlayerBlockInteractBase` | Base of `EventPlayerBlockInteract(Extended)` — `(Vec3i blockPos, BlockEntity block[, extra])` |
| 3 | `client.event.mixin.gui.HighlightBase` | `client.event.mixin.gui.EventTeleportBase` | Immutable position/rotation event (x,y,z,rotX,rotY, equals/hashCode); subclasses `EventTeleportPre/Post` consumed by `SkyblockTpMaze`, `SkyblockSmoothTeleport`, `RewindRecorder` |
| 4 | `client.event.mixin.holograms.HighlightBase` | `client.event.mixin.holograms.EventOptionsReloadBase` | Base of empty `EventOptionsReload`; `SoundChanger`, `SettingsOverrideDebug` re-apply options |
| 5 | `client.framework.feature.mod.rewindhandlers.HighlightBase` | `client.framework.feature.mod.rewindhandlers.WormScathaEventBase` | Abstract `DynamicListenerEvent` base `@TriggeredBy(WormScathaSpawnListener)` for `Data` and `WormScathaSpawnEvent(boolean)`; `SkyblockWormScathaAlert`, `SkyblockScathaTrackerHud` consume |

## Highlight (7) — 6 rows here, 1 superseded

| # | old FQN | new FQN | role / evidence |
|---|---------|---------|-----------------|
| 1 | `client.cosmetics.inactive.mixin.highlight.Highlight` | `client.cosmetics.inactive.mixin.highlight.MolangRuntime` | 502-line Molang evaluator: `ExecutionContext` + `EvaluatorImpl`, compiled `Stmt`/`Expr` cache, refreshes every `query.*` variable, `BoneQuery` inner class; built by `Holograms2Iterator`/`GeckolibDebugMod` |
| 2 | `client.event.Highlight` | `client.event.LunarEvent` | Root event type of the client: the bus keys listeners by `Class<? extends Highlight>` and only accepts `Highlight` instances (`LunarEventBus:37/42/45/191`); 230+ events extend it. Not `Event`: that simple name already exists as the nested `net.minecraft...S42PacketCombatEvent.Event` enum, and the strict uniqueness rule forbids reusing it (checked: no file imports both) |
| 3 | `client.framework.feature.mod.fishing.highlight.Highlight` | `client.framework.feature.mod.fishing.highlight.HighlightMigrationStep` | `@VersionGate(min=33)` package-private `interface { void method1(HighlightMigrationContext); }`; implemented by `HighlightHandler`, run in order by `HighlightConfigMigrations.method1` |
| 4 | `client.render.particle.highlight.Highlight` | `client.render.particle.highlight.ParticleDirection` | Abstract particle "direction" option (`"inwards"/"outwards"` or `[x,y,z]`, parsed by `Glintcolorizer2Base.method1` on key `"direction"`); writes `Glintcolorizer4_2.field30`; nested `FixedVelocity`/`RadialVelocity` |
| 5 | `client.replay.highlight.Highlight` | `client.replay.highlight.SegmentTimeline` | Generic per-track timeline `V extends Sliceable & Fishing<UUID>`: `TreeMapImpl<Range<Integer>,V>` with undo/redo (`HighlightTreeMap`), range insert/split/shift, JSON via nested `HighlightAdapter`; wrapped by `Track<T>`, used by `Rewind2_3` |
| 6 | `client.replay.rewindhandlers.highlight.Highlight` | `client.replay.rewindhandlers.highlight.MusicTrackManager` | Rewind background-music downloader from `https://rewind.lunarclientcdn.com/music/` (Constellation Convos, Cutesy Bossanova, Speed, Triumph, Wowie Zowie!), hash verification; consumed by `Rewindhandlers2Impl`/`MediaPool` |
| 7 | `client.util.highlight.Highlight` | *(already applied by namesA)* | Static atomic text writer (`.tmp` + `Files.move(ATOMIC_MOVE)` fallback), same analysis and same role name `AtomicFileWriter`, but owned and already applied by the concurrent `moves-namesA.tsv` (target `client.util.AtomicFileWriter`); omitted here to keep `skipped=0` |

## HighlightType (3)

| # | old FQN | new FQN | role / evidence |
|---|---------|---------|-----------------|
| 1 | `client.event.mixin.HighlightType` | `client.event.mixin.InputAction` | `UP, DOWN` press/release action carried by `EventKeybind` and `EventMouseButton`; consumers test `== DOWN` (CpsListener, Screenshot, ToggleSneak, ScreenInteractionHandler). Distinct from the existing `KeyInputType`/`MouseInputType` |
| 2 | `client.framework.feature.mod.highlight.HighlightType` | `client.framework.feature.mod.highlight.DungeonFloor` | Catacombs/Master floors (`NONE, E, F1–F7, M1–M7`) with secret %, time limit, boss-room pos, pretty name, boss messages; parsed from the scoreboard by `DungeonFloorListener.method4`, posted as `Rewindhandlers$Data10`, used by `DungeonWaypointManager` |
| 3 | `client.framework.feature.mod.holograms.highlight.mixin.HighlightType` | `client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType` | 60+ title regexes for the current SkyBlock screen (`COMMISSIONS`, `TERMINAL_*`, `EXPERIMENT_*`, `CHOCOLATE_*`, …) with `isTerminalGui()/isChestClaimGui()/isAuctionGui()`; resolved by `HighlightTypeListener.method5`, read by 40 mods |

## JsonDeserializerIterator (12)

All Gson `JsonDeserializer`s registered in `Module.java` / `LunarConstants` / `Glintcolorizer3_2`.

| # | old FQN | new FQN | target / data |
|---|---------|---------|---------------|
| 1 | `...fishing.JsonDeserializerIterator` | `...fishing.GlaciteTunnelsDeserializer` | `Fishing` graph from `glacite-tunnels.json`: `Fishing2` nodes keyed by UUID into a toxi `UndirectedGraph`, then `"links"` edges (`Module:64/132`) |
| 2 | `...fishing.chest.JsonDeserializerIterator` | `...fishing.chest.ImportantNpcLocationsDeserializer` | `fishing.chest.Chest` from `important-npc-locations.json`: category → `"x,y,z"` positions (`Module:80/161`); used by `SkyblockHidePlayersNearNpc` |
| 3 | `...fishing.colorsaturation.JsonDeserializerIterator` | `...fishing.colorsaturation.MinionDataDeserializer` | `Colorsaturation` from `minions.json`: `categories` name→String + `xp` name→double (`Module:84/171`); `SkyblockEnhancedMinionMenu.method8` |
| 4 | `...fishing.coordinates.JsonDeserializerIterator` | `...fishing.coordinates.MaxLevelsDeserializer` | `coordinates.mixin.Coordinates` from `max-levels.json`: `"skills"` via `CoordinatesType.of` into `Object2IntOpenHashMap` (`Module:81/146`) |
| 5 | `...fishing.gui.JsonDeserializerIterator` | `...fishing.gui.ImportantItemsDeserializer` | `ImportantItems` from `important-items.json`: dungeons/kuudra categories with weapons/items/pets sets (`Module:78/145`) |
| 6 | `...fishing.holograms.JsonDeserializerIterator` | `...fishing.holograms.DungeonSplitsDeserializer` | `DungeonSplits` from `splits.json`: per-`DungeonFloor` and per-`KuudraTier` split patterns (`Module:71/136`) |
| 7 | `...fishing.holograms.mixin.JsonDeserializerIterator` | `...fishing.holograms.mixin.KuudraWaypointsDeserializer` | `KuudraWaypoints` from `kuudra-waypoints.json`: `pearl` waypoints + `stun` positions (`Module:73/141`) |
| 8 | `...fishing.mixin.JsonDeserializerIterator` | `...fishing.mixin.ChocolateEggLocationsDeserializer` | `ChocolateEggLocations` from `hoppity-eggs.json`: area → egg id → `Data(pos, message)`; `SkyblockHoppityEggHud` (`Module:72/138`) |
| 9 | `...fishing.rewindhandlers.JsonDeserializerIterator` | `...fishing.rewindhandlers.SeaCreatureRegistryDeserializer` | `SeaCreatureRegistry` from `fishing/sea-creatures.json`: id → name/spawnMessage/`ItemRarity`/`EnumSet<RewindhandlersType>` (`Module:82/168`) |
| 10 | `...feature.tps.JsonDeserializerIterator` | `...feature.tps.TpsDeserializer` | recursive `Tps` tree with `"__values__"` string arrays; registered by `LunarConstants` |
| 11 | `...render.particle.JsonDeserializerIterator` | `...render.particle.BedrockParticleDeserializer` | abstract Gson (de)serializer for Bedrock `"particle_effect"` format 1.10.0 (`Glintcolorizer3_2`): description/curves/component BiMap; also serializes back |
| 12 | `...render.particle.nameplate.JsonDeserializerIterator` | `...render.particle.nameplate.BlockbusterParticleDeserializer` | subclass of the above adding `particle_appearance_billboard/tinting` and `blockbuster:particle_collision_*`; instantiated by `Glintcolorizer3_2.method2` |

## Notable findings

- **Several firers are not in the source tree.** For `EventSlotUpdate`, `EventUseItemOnBlock`,
  `EventKeybind`, `EventDropItem`, `EventPotionThrow`, `EventRenderHudBase` and
  `EventClientShutdown` the injecting mixins (`NetHandlerPlayClientMixin2`, `MinecraftMixin2`,
  `EntityPlayerSPMixin2`, `ItemPotionMixin2`, `EntityRendererMixin*`, `GuiIngameMixin3`) exist
  only in `libs/lunar-renamed-classes.jar`; their roles were recovered by scanning ctor call
  sites with ASM rather than guessed from the consumers.
- **`EventClientShutdown` looked unused** (empty, no source firer). The jar shows `Client.close()`
  posting it before shutting down the network and flushing modules — which matches both consumers.
- **`ConfigMigration` is already taken** by `client.config.migration.ConfigMigration`, so the
  feature-level migration step is `HighlightMigrationStep` (coherent with its siblings
  `HighlightConfigMigrations` / `HighlightMigrationContext`).
- **`event.mixin.HighlightType` (UP/DOWN) is not the same concept as the existing
  `KeyInputType`/`MouseInputType`** enums — it is the shared press/release action of the keybind
  *and* mouse-button events, hence `InputAction`.
- **Pre-existing breakage left untouched:** `render/particle/nameplate/JsonDeserializerIterator`
  (now `BlockbusterParticleDeserializer`) uses `this.HIHHCCHICRCCCRCIROROCCHCICCCOH` while its
  parent declares `field1` — a member-rename gap at HEAD, not caused by this map.
- **`client.util.highlight.Highlight` is already gone.** The concurrent `moves-namesA.tsv` map
  (junk-drawer split for `util.*`) analyzed the same class, chose the same role name
  `AtomicFileWriter`, and has already applied it (`client.util.highlight.Highlight` →
  `client.util.AtomicFileWriter`). That row is therefore omitted from this map; a duplicate row
  would make the applier print `SKIP ... not declared in ...` (`skipped=1`).
- **The root event class is `LunarEvent`, not `Event`.** `Event` is the obvious name for it, but a
  nested `enum Event` already exists in `net.minecraft.network.play.server.S42PacketCombatEvent`,
  and the naming rule forbids colliding with any existing simple name. `LunarEvent` is unique and
  pairs with the existing `LunarEventBus`; verified that no file imports both and that no source
  ever imports `S42PacketCombatEvent.Event` (the MC code always uses it qualified).
- **`fishing/JsonDeserializerIterator$Data.java` is a separate `$`-named class** (a SkyBlock
  command holder used by `SkyBlockCommandDeserializer`) that merely shares the outer-class prefix;
  the token guards correctly leave it alone. It deserves its own rename in a member/class wave.

## Verification

```
$ python3 tools/apply_class_moves.py --map tools/renames/moves-namesC.tsv
[class-moves] 38 rows; 6591 java files
[class-moves] rows=38 skipped=0 files_moved=38 files_touched=456 mode=dry-run
```

Additional check on a scratch copy with the map applied (`/tmp/opencode/repo-copy2`, built from the
current tree which already contains the other agents' applied maps):

- 38/38 files moved, declaration name matches file name in every case;
- no leftover FQN/import of any old class remains;
- no same-package residual bare references to an old name;
- no duplicate import of any new name and no new name declared in more than one package;
- the only remaining bare old names in the tree are the pre-existing classes named
  `client.fishing.highlight.Highlight` (replaymod mixins) and one `"Lunar Cosmetic Highlight"`
  display string — intentionally not touched (shadow guard).
