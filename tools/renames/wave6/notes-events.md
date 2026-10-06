# wave6 events/markers/inactive — notes

Audit scope: `client/highlight/**` (197), `client/event/**` (194), `client/markers/**` (128),
`client/inactive/**` (77) and the marker/event leftovers elsewhere (see §7). Map:
`tools/renames/wave6/moves-events.tsv` — 468 rows (69 event re-buckets, 194 highlight
merges, 128 markers, 77 inactive). Generated 2026-09-16, read-only audit.

## 1. What each package actually is

* **`client/highlight`** — the client **event system**, rescued a second time from the stale
  jar after bucket-A had already renamed+moved its canonical classes to `client/event`.
  129 classes were re-renamed in place by the wave5 maps 63/64/65/66/70 (variant names such
  as `BossBarRenderEvent`); the other 68 still carry raw rescue names (`HighlightImpl*`,
  `HighlightImpl13$Data*`). Every class is a duplicate of a canonical `event/**` class
  **except** the three jar-coupled bases listed in §7.
* **`client/event`** — the canonical event system: 7 bus types at the top level plus payload
  events. `event/fishing` is a junk-named jar bucket (generic gameplay/render payloads), so
  its feature payloads are re-grouped by subject in §3.
* **`client/markers`** — a third-rescue copy of the WebOSR driver stack (the 104 classes that
  `moves-misc` already moved to `client/driver/**`) plus the abstract SkyBlock marker model.
  All but `Markers` belong to `client/driver/**` (§5).
* **`client/inactive`** — not a "disabled features" package: it is the **live GeckoLib /
  Bedrock model + animation + Molang engine** (the raw names are the second rescue
  generation; live code imports these copies). The older renamed generation sits in
  `client/cosmetics/inactive/**` and is relocated to `cosmetics/{gecko,molang,emote}` by the
  cosmetics map. This map moves the live copies to the same final packages under the
  canonical names (§6).

## 2. Map composition

| source package | rows | destination |
|---|---|---|
| `client/event` + `client/event/fishing` payloads + `event/mixin` enums | 69 | `client/event/{combat,entity,player,render,screen,input,options,resourcepack,network}/` |
| `client/highlight/**` | 194 | the canonical twin package under `client/event/**` (unique variant names kept) |
| `client/markers/**` | 128 | `client/driver/**` (127 rows) + `client/framework/feature/markers` (the marker model) |
| `client/inactive/**` | 77 | `client/cosmetics/{gecko,molang,emote}/` (same final layout as `moves-cosmetics`) |

Applier validation: 468 rows, all source classes exist, no duplicate destination
`(package, name)`, and no destination simple name is already declared in its target package
(so no silent skips), except the deliberate coordination cases in §8.

## 3. Event layout

Bus types stay at `client/event/` top level: `LunarEvent`, `CancellableEvent`, `ResultEvent`,
`LunarEventBus`, `DebuggingEventBus`, `EventListener`, `EventBusAccess`. Feature payloads:

* `client/event/combat/` — `EventAttack`, `EventCriticalHit`, `EventEnchantmentCriticalHit`, `EventEndermanTeleport`, `EventOtherPlayerDamage`, `EventPlayerKnockback`, `EventPlayerReceiveDamage`, `EventPotionThrow`, `EventPreAttackEntity`, `EventProjectileBase`, `EventTotemPop`
* `client/event/entity/` — `EventEntityChunkBase`, `EventEntityCollisionBoxes`, `EventEntityHealthUpdate`, `EventEntityHurtAnimation`, `EventEntityInteract`, `EventEntityJoinWorld`, `EventEntityMovement`, `EventEntityRemove`, `EventEntitySpawn`, `EventEntityStatus`, `EventLivingEntityBase`, `EventPlayerDeath`
* `client/event/player/` — `EventGameModeChange`, `EventInventoryUpdate`, `EventItemDrop`, `EventItemPickup`, `EventItemUse`, `EventItemUseFinish`, `EventLocalPlayerDeath`, `EventPlayerJoinWorld`, `EventPlayerLivingUpdate`, `EventPlayerRemove`, `EventPlayerSleep`, `EventPlayerState`
* `client/event/render/` — `EventBlockHighlight`, `EventEyeHeight`, `EventFog`, `EventPreRenderPlayer`, `EventRenderButton`, `EventRenderContainerSlot`, `EventRenderEntityModel`, `EventRenderHologram`, `EventRenderItemDurability`, `EventRenderPlayerModel`, `EventRenderPlayerStats`, `EventRenderScale`, `EventRenderScreenItem`, `EventRenderScreenOverlay`, `EventRenderSlot`, `EventRenderTabListEntry`, `EventRenderTooltip`
* `client/event/screen/` — `EventResolutionChange`, `EventScreenChange`, `EventScreenClose`, `EventScreenOpen`, `EventScreenUpdate`
* `client/event/input/` — `EventKeyInput`, `EventMarkerInput`, `EventMouseMove`, `InputAction`, `KeyInputType`, `MouseInputType`
* `client/event/options/` — `ApolloOptionUpdateEvent`, `EventOptionsSaved`
* `client/event/resourcepack/` — `EventResourcePackUpdate`, `EventResourcesReload`
* `client/event/network/` — `EventPluginChannelRegister`

The one raw leftover `event/fishing/HighlightImpl8_2` is renamed `PlayerJoinWorldEvent` and
grouped with the player payloads (duplicate of `EventPlayerJoinWorld`). Mixin-holder
payloads stay under `client/event/mixin/**` (task target). Follow-up: the
subcategory names `mixin/fishing` and `mixin/highlight` are jar-inherited; rename them to
`mixin/world` / `mixin/render` once the `highlight` package is gone (deferred here to keep
the map conservative).

## 4. Highlight → event merge table

Each row moves the stale copy next to its canonical twin under a unique variant name
(wave5 names where already applied; canonical names where the twin name was free).
**Intended merge**: re-point the importers named in the row evidence at the canonical twin
and delete the variant. Compact table (`variant = canonical twin`):

```
ButtonRenderEvent                  = com.moonsworth.lunar.client.event.EventRenderButton
ClientEventBus                     = event.LunarEventBus
ContainerSlotRenderEvent           = com.moonsworth.lunar.client.event.EventRenderContainerSlot
EventRegistrar                     = event.EventBusAccess
ClientEventBusDebug                = event.DebuggingEventBus
HologramRenderEvent                = event.EventRenderHologram
OptionUpdateEvent                  = event.ApolloOptionUpdateEvent
ScreenItemRenderEvent              = event.EventRenderScreenItem
ItemDurabilityRenderEvent          = com.moonsworth.lunar.client.event.EventRenderItemDurability
KeyInputEvent                      = com.moonsworth.lunar.client.event.EventKeyInput
ListenerRegistration               = event.EventListener
MarkerInputEvent                   = com.moonsworth.lunar.client.event.EventMarkerInput
OptionsSavedEvent                  = com.moonsworth.lunar.client.event.EventOptionsSaved
OutcomeEvent                       = event.ResultEvent
PlayerStatsRenderEvent             = com.moonsworth.lunar.client.event.EventRenderPlayerStats
RenderScaleEvent                   = com.moonsworth.lunar.client.event.EventRenderScale
ResolutionChangeEvent              = com.moonsworth.lunar.client.event.EventResolutionChange
ResourcePackUpdateEvent            = com.moonsworth.lunar.client.event.EventResourcePackUpdate
ResourcesReloadEvent               = com.moonsworth.lunar.client.event.EventResourcesReload
ScreenChangeEvent                  = com.moonsworth.lunar.client.event.EventScreenChange
ScreenCloseEvent                   = com.moonsworth.lunar.client.event.EventScreenClose
ScreenInitEvent                    = com.moonsworth.lunar.client.event.EventRenderScreenOverlay
ScreenOpenEvent                    = com.moonsworth.lunar.client.event.EventScreenOpen
ScreenUpdateEvent                  = com.moonsworth.lunar.client.event.EventScreenUpdate
SlotRenderEvent                    = com.moonsworth.lunar.client.event.EventRenderSlot
TabListEntryRenderEvent            = com.moonsworth.lunar.client.event.EventRenderTabListEntry
TooltipRenderEvent                 = com.moonsworth.lunar.client.event.EventRenderTooltip
EventCameraEyeHeight               = com.moonsworth.lunar.client.event.fishing.EventEyeHeight
EventChunkBase                     = com.moonsworth.lunar.client.event.fishing.EventEntityChunkBase
EventCollisionBoxes                = com.moonsworth.lunar.client.event.fishing.EventEntityCollisionBoxes
EventEnchantCriticalHit            = com.moonsworth.lunar.client.event.fishing.EventEnchantmentCriticalHit
EventEntityHealthChange            = com.moonsworth.lunar.client.event.fishing.EventEntityHealthUpdate
EventEntityMove                    = com.moonsworth.lunar.client.event.fishing.EventEntityMovement
EventEntityRemoval                 = com.moonsworth.lunar.client.event.fishing.EventEntityRemove
EventEntityStatusUpdate            = com.moonsworth.lunar.client.event.fishing.EventEntityStatus
EventEntityWorldJoin               = com.moonsworth.lunar.client.event.fishing.EventEntityJoinWorld
EventFogSetup                      = com.moonsworth.lunar.client.event.fishing.EventFog
EventHurtAnimation                 = com.moonsworth.lunar.client.event.fishing.EventEntityHurtAnimation
EventInteractEntity                = com.moonsworth.lunar.client.event.fishing.EventEntityInteract
EventItemRightClick                = com.moonsworth.lunar.client.event.fishing.EventItemUse
EventLivingBase                    = com.moonsworth.lunar.client.event.fishing.EventLivingEntityBase
EventLocalDeath                    = com.moonsworth.lunar.client.event.fishing.EventLocalPlayerDeath
EventMeleeCriticalHit              = com.moonsworth.lunar.client.event.fishing.EventCriticalHit
EventMouseDelta                    = com.moonsworth.lunar.client.event.fishing.EventMouseMove
EventMouseOverAttack               = com.moonsworth.lunar.client.event.fishing.EventAttack
EventPlayerDamaged                 = com.moonsworth.lunar.client.event.fishing.EventPlayerReceiveDamage
EventPlayerDied                    = com.moonsworth.lunar.client.event.fishing.EventPlayerDeath
EventPlayerModelRender             = com.moonsworth.lunar.client.event.fishing.EventRenderPlayerModel
EventPlayerPreRender               = com.moonsworth.lunar.client.event.fishing.EventPreRenderPlayer
EventPlayerRemoval                 = com.moonsworth.lunar.client.event.fishing.EventPlayerRemove
EventSleepAttempt                  = com.moonsworth.lunar.client.event.fishing.EventPlayerSleep
EventTotemActivation               = com.moonsworth.lunar.client.event.fishing.EventTotemPop
EventUseItemFinish                 = com.moonsworth.lunar.client.event.fishing.EventItemUseFinish
ProjectileBaseEvent                = event.EventProjectileBase
PotionThrowEvent                   = event.EventPotionThrow
EndermanTeleportEvent              = event.EventEndermanTeleport
PlayerKnockbackEvent               = event.EventPlayerKnockback
InventoryUpdateEvent               = event.EventInventoryUpdate
BlockHighlightAlphaEvent           = event.EventBlockHighlightAlpha
BlockHighlightRotationEvent        = event.EventBlockHighlightRotation
BlockHighlightColorEvent           = event.EventBlockHighlightColor
BlockHighlightWidthEvent           = event.EventBlockHighlightWidth
BlockHighlightLayerEvent           = event.EventBlockHighlightLayer
BlockHighlightRenderEvent          = event.EventBlockHighlight
ItemPickupEvent                    = event.EventItemPickup
GameModeChangeEvent                = event.EventGameModeChange
PlayerStateEvent                   = event.EventPlayerState
OtherPlayerDamageEvent             = event.EventOtherPlayerDamage
ItemDropEvent                      = event.EventItemDrop
PlayerLivingUpdateEvent            = event.EventPlayerLivingUpdate
PlayerJoinWorldEventLegacy         = event.EventPlayerJoinWorld
PreAttackEntityEvent               = event.EventPreAttackEntity
EntitySpawnEvent                   = event.EventEntitySpawn
RenderEntityModelEvent             = event.EventRenderEntityModel
PlayerJoinWorldLegacyEvent         = event.fishing.EventPlayerJoinWorld
PluginChannelRegisterEvent         = event.EventPluginChannelRegister
EventChatMessageLegacy             = event.mixin.EventChatMessage
EventComponentMessageLegacy        = event.mixin.EventComponentMessage
EventChatSendLegacy                = event.mixin.EventChatSend
EventCommandLegacy                 = event.mixin.EventCommand
EventNameplateExtensionLegacy      = event.mixin.EventNameplateExtension
EventTabCompleteLegacy             = event.mixin.EventTabComplete
EventCommandRegisterLegacy         = event.mixin.EventCommandRegister
InputActionLegacy                  = event.mixin.InputAction
KeyInputTypeLegacy                 = event.mixin.KeyInputType
MouseInputTypeLegacy               = event.mixin.MouseInputType
EventBlockBreakingProgress         = com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress
EventBlockModified                 = com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange
EventBlockOutlineRender            = com.moonsworth.lunar.client.event.mixin.fishing.EventRenderBlockOutline
EventBlockPick                     = com.moonsworth.lunar.client.event.mixin.fishing.EventPickBlock
EventBlockPlacement                = com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPlace
EventBlockUpdateNotify             = com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate
EventChunkLifecycle                = com.moonsworth.lunar.client.event.mixin.fishing.EventChunk
EventClientTick                    = com.moonsworth.lunar.client.event.mixin.fishing.EventTick
EventCursorPosition                = com.moonsworth.lunar.client.event.mixin.fishing.EventMousePosition
EventEntityPos                     = com.moonsworth.lunar.client.event.mixin.fishing.EventEntityPosition
EventEverySecond                   = com.moonsworth.lunar.client.event.mixin.fishing.EventSecond
EventExplosionSpawn                = com.moonsworth.lunar.client.event.mixin.fishing.EventExplosion
EventHorizonQuery                  = com.moonsworth.lunar.client.event.mixin.fishing.EventGetHorizon
EventItemEntity                    = com.moonsworth.lunar.client.event.mixin.fishing.EventEntityItem
EventParticleSpawn                 = com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle
EventRenderTickPhase               = com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick
EventReplayFrame                   = com.moonsworth.lunar.client.event.mixin.fishing.EventRewindFrame
EventRewindRefresh                 = com.moonsworth.lunar.client.event.mixin.fishing.EventRewindUpdate
EventRunDirectory                  = com.moonsworth.lunar.client.event.mixin.fishing.EventGameDirectory
EventSoundPlay                     = com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound
EventTickEnd                       = com.moonsworth.lunar.client.event.mixin.fishing.EventRewindTick
EventWorldEffectRecord             = com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffect
EventWorldLifecycle                = com.moonsworth.lunar.client.event.mixin.fishing.EventWorld
EventWorldRender                   = com.moonsworth.lunar.client.event.mixin.fishing.EventRenderWorld
EventWorldTimeUpdate               = com.moonsworth.lunar.client.event.mixin.fishing.EventWorldTime
PlayerBlockInteractBaseEvent       = event.mixin.fishing.EventPlayerBlockInteractBase
RewindFrameEvent                   = event.mixin.fishing.EventRewindFrame
EventUseItemOnBlockLegacy          = event.mixin.fishing.mixin.EventUseItemOnBlock
EventUseItemLegacy                 = com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItem
EventEntityItemSpawnLegacy         = com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventEntityItemSpawn
RenderEntityItemEvent              = event.mixin.fishing.mixin.EventRenderEntityItem
RenderEntityItemLegacyEvent        = event.mixin.fishing.mixin.EventRenderEntityItemLegacy
EventMapUpdateLegacy               = com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventMapUpdate
BossBarUpdateEvent                 = com.moonsworth.lunar.client.event.mixin.gui.EventBossBarUpdate
BridgePayload                      = event.mixin.gui.EventBridgePayload
DisconnectEvent                    = com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect
DisconnectReasonEvent              = com.moonsworth.lunar.client.event.mixin.gui.EventDisconnectReason
GuiScreenEvent                     = com.moonsworth.lunar.client.event.mixin.gui.EventGuiScreen
LocationChangeEvent                = com.moonsworth.lunar.client.event.mixin.gui.EventLocationChange
NetworkEncryptionEvent             = com.moonsworth.lunar.client.event.mixin.gui.EventNetworkEncryption
PacketEvent                        = com.moonsworth.lunar.client.event.mixin.gui.EventPacket
PlayerListEntryEvent               = com.moonsworth.lunar.client.event.mixin.gui.EventPlayerListEntry
PluginMessageEvent                 = com.moonsworth.lunar.client.event.mixin.gui.EventPluginMessage
ScoreboardUpdateEvent              = com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate
ScreenActionEvent                  = com.moonsworth.lunar.client.event.mixin.gui.EventScreenAction
ServerBrandEvent                   = com.moonsworth.lunar.client.event.mixin.gui.EventServerBrand
ServerChangeEvent                  = com.moonsworth.lunar.client.event.mixin.gui.EventServerChange
ServerJoinEvent                    = com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin
ServerPingEvent                    = com.moonsworth.lunar.client.event.mixin.gui.EventServerPing
ServerResourcePackRemoveEvent      = com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackRemove
ServerResourcePackUpdateEvent      = com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackUpdate
ServerTickEvent                    = com.moonsworth.lunar.client.event.mixin.gui.EventServerTick
SignUpdateEvent                    = com.moonsworth.lunar.client.event.mixin.gui.EventSignUpdate
SkinLoadedEvent                    = None
SlotUpdateEvent                    = event.mixin.gui.EventSlotUpdate
TabListUpdateEvent                 = com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate
TeleportEvent                      = event.mixin.gui.EventTeleportBase
TitleEvent                         = com.moonsworth.lunar.client.event.mixin.gui.EventTitle
WorldEditSelectionEvent            = com.moonsworth.lunar.client.event.mixin.gui.EventWorldEditSelection
AlertUpdateEvent                   = com.moonsworth.lunar.client.event.mixin.highlight.EventAlertUpdate
ArmorRenderEvent                   = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderArmor
BossBarRenderEvent                 = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderBossBar
CameraOffsetEvent                  = com.moonsworth.lunar.client.event.mixin.highlight.EventCameraOffset
ChatRenderEvent                    = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderChat
ChunkReloadEvent                   = com.moonsworth.lunar.client.event.mixin.highlight.EventChunkReload
CrosshairRenderEvent               = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderCrosshair
EntitiesRenderEvent                = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntities
EntityLabelRenderEvent             = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityLabel
EntityOffsetRenderEvent            = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityOffset
FovModifierEvent                   = com.moonsworth.lunar.client.event.mixin.highlight.EventFovModifier
FovRenderEvent                     = com.moonsworth.lunar.client.event.mixin.highlight.EventFovRender
GlintTransformEvent                = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderGlintTransform
GroundItemTransformEvent           = com.moonsworth.lunar.client.event.mixin.highlight.EventGroundItemTransform
GuardianRenderEvent                = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderGuardian
EntityRenderBaseEvent              = event.mixin.highlight.EventRenderEntityBase
ModelRenderEvent                   = event.mixin.highlight.EventRenderModel
DroppedItemRenderEvent             = event.mixin.highlight.EventRenderDroppedItem
HologramUpdateEvent                = com.moonsworth.lunar.client.event.mixin.highlight.EventHologramUpdate
HotbarRenderEvent                  = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderHotbar
InventoryScreenRenderEvent         = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen
ItemClumpEvent                     = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemClump
ItemColorRenderEvent               = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemColor
ItemGlintRenderEvent               = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint
ItemRotationRenderEvent            = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemRotation
ItemStackSizeRenderEvent           = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize
NameTagRenderEvent                 = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag
ParticleRenderEvent                = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderParticle
PlayerRenderEvent                  = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderPlayer
PostProcessEvent                   = com.moonsworth.lunar.client.event.mixin.highlight.EventPostProcess
SetupTerrainEvent                  = com.moonsworth.lunar.client.event.mixin.highlight.EventSetupTerrain
VanillaHudRenderEvent              = com.moonsworth.lunar.client.event.mixin.highlight.EventRenderVanillaHud
OptionsReloadBaseEvent             = event.mixin.holograms.EventOptionsReloadBase
ClientShutdownEvent                = event.mixin.holograms.EventClientShutdown
EventAssetServerConnectedLegacy    = com.moonsworth.lunar.client.event.mixin.holograms.EventAssetServerConnected
EventWebSocketReadyLegacy          = com.moonsworth.lunar.client.event.mixin.holograms.EventWebSocketReady
EventFeatureToggleLegacy           = com.moonsworth.lunar.client.event.mixin.holograms.EventFeatureToggle
IchorHandlersLoadedEvent           = event.mixin.holograms.mixin.EventIchorHandlersLoaded
HudBaseRenderEvent                 = event.mixin.nameplate.EventRenderHudBase
HudRenderLegacyEvent               = None
EventRenderTabListLegacy           = com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderTabList
HudRenderLegacyEventAlt            = None
EventRenderHudLegacy               = com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHud
KeybindEvent                       = event.mixin.rewindhandlers.EventKeybind
EventInitialScreenOpenLegacy       = com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventInitialScreenOpen
EventMouseButtonLegacy             = com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton
EventMouseScrollLegacy             = com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScroll
EventPerspectiveChangeLegacy       = com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventPerspectiveChange
EventMouseWheelLegacy              = com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheel
```

Where the canonical twin prints as `None` the rename never landed anywhere
(`SkinLoadedEvent`, `highlight.mixin.nameplate.HighlightImpl2/4`); the moved copy becomes
the canonical class and there is nothing to dedupe.

## 5. Markers classification

* 127 rows go to `client/driver/**`. All but the move-only ones are `...Legacy` variants
  of an existing driver class (`Markers4` → `driver.DriverBridgeLegacy`, `Gui10` →
  `driver.core.gui.mixin.CosmeticsBridgeLegacy`, `Holograms2` → `driver.holograms.HoverModelLegacy`).
  Merge = re-point importers and delete; the driver copy named in each row is canonical.
* Move-only rows (`BrowserHandler`, `FileSystemIterator`, `LoggerHandler`, `driver.gui.Gui`/
  `GuiExtension`, `driver.holograms.ColorsaturationExtension`/`Holograms`/`HologramsIterator2`,
  `driver.core.gui[.mixin]` and `driver.core.holograms[.mixin]` classes): the driver copy is
  missing from the tree (quarantined during `moves-misc`), so these rescued classes are the
  only copies and move as-is.
* `markers.Markers` → `framework/feature/markers.MarkerModel` — the abstract marker-icon
  model (nested `Data3`/`Data4`/`Data6`) consumed by `event.EventMarkerInput`
  (`Markers.Data4`) and by the highlight/hologram features. The same-named `Markers` there is
  the SkyBlock marker manager, so the model gets a unique name. When the mods batch later
  moves the SkyBlock markers feature to `client/mod/render/markers/`, `MarkerModel` should
  follow (same simple names as `Markers`/`MarkerData` would then collide otherwise).

## 6. Inactive classification (live GeckoLib engine)

All 77 rows target the final packages of the cosmetics layout: **63 → `cosmetics/gecko/`,
12 → `cosmetics/molang/`, 2 → `cosmetics/emote/`** (`EmoteModel`, `MolangResourceModel`).
This is the **second-rescue, live generation**: live code (`Holograms3Handler`,
`fog/holograms`, mod features) imports these copies. The older renamed generation in
`cosmetics/inactive/**` is relocated to the same packages by `moves-cosmetics`; where that
copy survives, this map uses a `...Legacy` variant name and the evidence names the twin to
merge with. Where the old copy is already marked for deletion (the 14 excluded in
notes-cosmetics) the live copy takes the canonical name directly:

* `rewindhandlers/RewindhandlersNN` → `BedrockBone`, `BedrockGeometry(File)`, `BedrockModel`,
  `ModelCube`, `ModelVertex`, `ModelQuad`, `PolyMesh`, `CubeMesh`, `TextureMesh`,
  `ModelDescription`, `FormatVersion`, `GeoModelSerializer`, `BoneHierarchySerializer`.
* `mixin/fishing/*` → the live task implementations `InactiveTask`, `FishingHandler`,
  `AbstractTask`, `AbstractTimedTask`, `AttachToOwnerTask`, `LookAtOwnerTask`,
  `LookAtTargetTask`, `LookAtBlockTask`, `MoveToOwnerTask`, `TeleportToOwnerTask`,
  `CombinedTask` (the renamed copies the cosmetics map excludes are dead).
* `mixin/highlight[.mixin]/*` → `MolangRuntime`, `QueryDefaults`, `QueryTransition`,
  `Molang*` builtins, `EvaluatorIterator`, `FunctionImpl` (molang package).
* `mixin/nameplate`, `mixin/gui[.mixin]`, `mixin/colorsaturation[.mixin]`, `mixin/holograms[.mixin]`,
  root `Inactive*`/`InactiveType*`/`GuiHandler` → gecko model/animation engine (`...Legacy`
  where the cosmetics map already moved the same name).

## 7. Classes deliberately left in place / delegated

* `client.highlight.Highlight` — event base bound to the stale jar
  (`Highlight3Iterator_3.handle(Class<T extends Highlight>, Consumer<T>)`); `LunarEvent` still
  extends it. Move only after the jar remap/drop step.
* `client.highlight.HighlightImpl` — cancellable base of all rescued copies (same coupling).
* `client.highlight.HighlightImpl_3` — Apollo packet event; documented coupling to
  `Highlight3Iterator_3.method3` (`tools/renames/moves-highlightimpl3.tsv`).
* Root Apollo leftovers (`client/Highlight3Iterator*`, `Gui2Handler*`, `ApolloTypeRegistry`,
  ...) are already covered by `tools/renames/wave6/moves-misc.tsv` → `client/network/apollo`,
  `client/account`; not duplicated here.
* `framework/listener/nameplate.DynamicListenerEvent` + `ThreadedEvent` → `client/event/` are
  already covered by `tools/renames/wave6/moves-framework.tsv`; not duplicated here.
* The whole `event/mixin/**` tree stays put (mixin-holder events), including
  `event/mixin/gui.HighlightIterator`.

## 8. Cross-map coordination / apply order

* **`moves-cosmetics.tsv`**: its rows relocate the *older* `cosmetics/inactive/**` generation
  into `cosmetics/{gecko,molang,emote}`. This map moves the *live* `client/inactive/**`
  generation into the same packages under the same names. Apply `moves-events.tsv` first (or
  delete the superseded stale copies) or the inactive rows will skip; the merge is "keep the
  live body, take the canonical name, drop the stale copy). Under the status-table order
  (cosmetics before events) the ~56 twin rows skip in the mover by design; the twin/dedupe
  pass then keeps the live `client/inactive` body and deletes the relocated stale copy.
* **`moves-mods.tsv`** moves `mod.render.Markers` → `client/mod/render/markers/`; if that
  feature later absorbs `framework/feature/markers/**`, move `MarkerModel` with it.
* No other wave6 map claims a class owned by this map (checked against moves-misc/
  moves-framework/moves-mods/moves-cosmetics/moves-render/moves-replay/moves-util).

## 9. Apply / verify

1. `python3 tools/apply_class_moves.py --map tools/renames/wave6/moves-events.tsv` (dry-run first).
2. `python3 tools/error_diff.py` — expect no NEW failing files. The highlight copies still
   call pre-rescue type names through the stale jar (that is how they compile today); the
   applier rewrites references to the moved classes only.
3. Dedupe pass: for every row whose evidence names a twin, re-point importers at the twin
   and delete the variant (`...Legacy` / `XxxRenderEvent` names are greppable).
4. Follow-ups: rename `event/mixin/fishing` + `event/mixin/highlight` (§3); drop the three
   jar-coupled `client/highlight` bases once the reference jar is remapped (§7).
