# Final naming pass - client cluster A (dry-run map)

Map: `tools/renames/classes-finalclientA.tsv` (185 rows, 4 columns).
Scope: `com.moonsworth.lunar.client.{replay,event,cosmetics,render}/**`.

## Result

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/classes-finalclientA.tsv
[aware-renames] 185 rows (44 nested); 6591 java files
[aware-renames] rows=185 skipped=0 files_touched=469 files_renamed=141 mode=dry-run
```

`skipped=0`, no `WARN ... no references found`, no `SKIP` rows.
Nothing was applied to `src/` and nothing was committed.

## What was already named (not in this map)

A concurrent wave (`b90c1c98 renamer: final names wave E`) had already drained part of
`render/particle`: `Glintcolorizer2Base*` -> `BedrockComponentAppearance*`/`SSFunction`/`Cos`/
`Abs`/`Exp`/`Min`, `Glintcolorizer2Impl*` -> `BedrockComponentParticleLifetime`/`KillPlane`,
`highlight/Glintcolorizer2Base*` -> `BedrockComponentShapeBase/EntityAABB/Sphere/Point`,
`holograms/Glintcolorizer2Base*` -> `BedrockComponentRate*`, `nameplate/Glintcolorizer*` ->
`BedrockComponentLocalSpace/Initialization` + `Nameplate*Component`,
`rewindhandlers/Glintcolorizer*` -> `BedrockComponentMotion*`, plus the McLib math helpers
(`SinDegrees`, `CosDegrees`, `Lerp`, `HermiteBlend`, `LerpRotate`, `RandomFunction`, ...).
This map covers what is still junk after that wave.

## Method

1. Re-inventoried every declaration in the 4 subtrees (including nested classes) with a
   junk filter (`digit`, `$`, bare `Data`/`Type`/`Extension`, `Glintcolorizer*`).
2. Read each class body + all call sites; where the code is a port of an open-source
   library the upstream identity was recovered and used verbatim:
   * `render/particle` is **mchorse Blockbuster** (`BedrockScheme`, `BedrockEmitter`,
     `BedrockParticle`, `BedrockComponent*`, `Tint.Solid/Gradient`, ...) with the
     expression evaluator from **mchorse McLib** (`IValue`, `Constant`, `Variable`, `Group`,
     `Wrapper`, `Negate`, `Negative`, `Operator`, `Ternary`, `Operation`, `MathFunction`,
     `NNFunction`/`NSFunction`/`SNFunction`, `MolangParser`, `MolangException`, ...).
   * `replay/**` is Lunar's Rewind replay engine; names are the domain roles of the
     classes (packets, property tree, project manager, JS APIs).
3. Verified every new name is declared nowhere else in `src/main/java` (7034 declared
   simple names checked) and that no two rows use the same new name.


## Rows

| package | old | new | evidence |
|---|---|---|---|
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader` | `KeyframeProperty` | Abstract animated property (@SerializedName values TreeMap<Integer,Fishing2Loader.Data<V>> + showInTimeline + in/out easing + Sliceable copy); base of the bool/number/color/list/HUD-position property loaders |
| `com.moonsworth.lunar.client.replay` | `Data` | `Keyframe` | Fishing2Loader.Data<V>: @SerializedName interpolation/in/out + value; the keyframe entry stored per frame in the property TreeMap |
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader2` | `BooleanProperty` | extends KeyframeProperty<LightingExtension<T>,T>: method10 returns the end value unchanged (step/boolean interpolation) |
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader3` | `NumberProperty` | extends KeyframeProperty; interpolation delegates to NumberInterpolator |
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader32` | `HudPositionProperty` | extends NumberProperty<Float>: binds a HUD mod (MixinCore9Extension) and rewrites its x/y as percent of the scaled resolution (horizontal + vertical toggles) |
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader4` | `ColorProperty` | extends KeyframeProperty<LightingExtension4222,Fishing2Loader4.Data>: interpolates ARGB/ColorAnimation keyframes |
| `com.moonsworth.lunar.client.replay` | `Data` | `ColorValue` | Fishing2Loader4.Data: ARGB int + loop flag + int + ColorAnimation per color keyframe |
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader5` | `ListProperty` | extends KeyframeProperty<...,List<T>>: clones the list and interpolates element-wise via NumberInterpolator |
| `com.moonsworth.lunar.client.replay` | `Fishing2Loader52` | `TransformProperty` | extends ListProperty<Double>: 3 coords + shortest-angle rotation interpolation via AngleUtils |
| `com.moonsworth.lunar.client.replay` | `Rewind2_2` | `ReplayCompression` | static synchronized zlib deflate/inflate helpers used by ReplayHandler and ReplayFileReader for the ZLIB packet-stream codec |
| `com.moonsworth.lunar.client.replay` | `Rewind2_3` | `ReplayProjectManager` | owns the project Gson (registers TrackCollection/MediaPool/property adapters), the project file/dir, timeline list, MediaPool and ThumbnailManager; built by RewindHandlers and reached via RewindHandlers.method40() |
| `com.moonsworth.lunar.client.replay` | `Rewind3_3` | `TimelineSelection` | value object (RewindHandlers, ReplayTimeline, UndoRedoManager, Track, Entry<Range,RewindIterator>) passed to ReplayAction in ReplayKeybindHandler/RewindEditorBridge |
| `com.moonsworth.lunar.client.replay` | `Rewind4_2` | `ReplayMetadata` | @SerializedName minecraftVersion/minecraftProtocolVersion/externalMods DTO parsed by RewindListProvider when scanning saved replays |
| `com.moonsworth.lunar.client.replay` | `RewindIterator2_2` | `TimelineSegment` | abstract RewindIterator base with @SerializedName contentStart; parent of AudioSegment and GameplaySegment |
| `com.moonsworth.lunar.client.replay.gui` | `Gui2$Data` | `TrackCollectionDeserializer` | Gson JsonDeserializer<TrackCollection> reading the effects/gameplay/audios arrays through TrackRegistry types |
| `com.moonsworth.lunar.client.replay.highlight` | `Highlight$Data` | `MediaPoolAdapter` | TypeAdapter<MediaPool> writing the media/folders/parents/names maps keyed by UUID with rewind-relative paths |
| `com.moonsworth.lunar.client.replay.highlight` | `Highlight2_2` | `ThumbnailRequest` | (GameplaySegment, Range<Integer>, UUID, frame) queued in ThumbnailManager to render a timeline thumbnail |
| `com.moonsworth.lunar.client.replay.highlight` | `Data` | `PropertyMapAdapter` | LinkedHashMapImpl.Data: TypeAdapter serialising the property map (enabled/extended/childProperties/keyframes) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Fishing2Iterator` | `PropertyGroup` | abstract rewind property-tree node: child PropertyGroups + KeyframeProperty values with type/enabled/extended flags |
| `com.moonsworth.lunar.client.replay.nameplate` | `Type` | `NodeType` | Fishing2Iterator.Type NONE/KEYFRAMES/CHILD_PROPERTIES selects which of the node's maps is serialised |
| `com.moonsworth.lunar.client.replay.nameplate` | `Fishing2Iterator2` | `ModPropertyGroup` | PropertyGroup bound to a Framework7Extension: toggles the mod and supplies its child groups/properties |
| `com.moonsworth.lunar.client.replay.nameplate` | `Fishing2Iterator3` | `AnimatedPropertyGroup` | PropertyGroup with an explicit child-type list, type string and KeyframeProperty supplier (timeline element template) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Impl_2` | `SettingValuePacket` | ReplayPacket: feature id + key + SettingValueType + typed value; replays a rewind setting override |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Impl$Type` | `SettingValueType` | enum STRING/INTEGER/FLOAT/DOUBLE/LONG/BOOLEAN selecting the SettingValuePacket payload encoding |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Iterator` | `BeamPacket` | ReplayPacket: Map<String,Beam> (Apollo beam id/colour/block location) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Iterator2` | `WaypointPacket` | ReplayPacket: Set<Waypoint> (name/pos/dimension/island/visibility flags) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Iterator3` | `WorldBorderPacket` | ReplayPacket: Map<String,WorldBorderExtensionBridge> centre/size/damage/cancel flags |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Iterator4` | `ClickHandlerPacket` | ReplayPacket: Map<UUID,LinkedList<ClickHandler2>>; replay replaces the click-handler manager contents |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Iterator5` | `ModuleConfigPacket` | ReplayPacket: module id + JsonObject; replay resets the module options and calls load(json) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Iterator6` | `ServerHologramPacket` | ReplayPacket: Map<String,Serverholograms> (text/pos/flags/components) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate2Task` | `ButtonStatePacket` | ReplayPacket: module id + option id + pressed/value booleans; replays a toggle-button state |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate3Iterator` | `EntityOverrideGroup` | abstract PropertyGroup registered as the entityOverrides timeline element; applies per-entity option overrides |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate3Iterator2` | `PlayerOverrideGroup` | EntityOverrideGroup keyed by player UUID (registered as the player element; label from the profile name) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Nameplate3Iterator3` | `EntityIdOverrideGroup` | EntityOverrideGroup keyed by numeric entity id (registered as the entity element; label from EntityLookup) |
| `com.moonsworth.lunar.client.replay.nameplate` | `Type` | `DisconnectMode` | DisconnectPacket.Type single value DISCONNECT driving EventDisconnect.method2(true) on replay |
| `com.moonsworth.lunar.client.replay.nameplate` | `Type` | `OverrideMode` | ServerSettingOverridePacket.Type FORCE_ENABLED/FORCE_DISABLED/NONE -> Boolean applied as a SettingIntercept from OverrideSource.SERVER |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.gui` | `Nameplate2Impl7` | `CursorPositionPacket` | ReplayPacket: x/y replayed into GuiScreenContext cursor coordinates |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl7` | `StopUsingItemPacket` | ReplayPacket: empty payload; replay calls bridge$stopUsingItem() |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl8` | `SwingHandPacket` | ReplayPacket: hand int replayed through bridge$swingHand |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl9` | `ItemSwapPacket` | ReplayPacket: tick replayed into itemSwapTicker (MC>=35) |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl10` | `HeldItemPacket` | ReplayPacket: slot; replay swaps the current equipped item index (inverse packet reads it back) |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl11` | `MovementInputPacket` | ReplayPacket: moveForward/moveStrafe/sneaking/jumping replayed onto MovementInputBridge |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl12` | `LookPacket` | ReplayPacket: yaw/pitch replayed into LocalPlayerContext (inverse reads them back) |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl13` | `DropItemPacket` | ReplayPacket: boolean replayed through bridge$drop |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl14` | `StartUsingItemPacket` | ReplayPacket: slot; replay calls bridge$startUsingItem |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl15` | `PlayerAnimationPacket` | ReplayPacket: int stored on LocalPlayerContext (write-only field in this tree; likely swing/hurt ticks) - medium confidence |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl16` | `RelativePositionPacket` | ReplayPacket: 3 shorts /4096 + onGround applied entity-relative (EntityPositionApplier) |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.holograms` | `Nameplate2Impl17` | `PositionPacket` | ReplayPacket: absolute x/y/z + onGround (EntityPositionApplier) |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.rewindhandlers` | `Nameplate2Iterator` | `ServerPackPacket` | ReplayPacket: server resource-pack indices resolved to pack files, replacing the pack list |
| `com.moonsworth.lunar.client.replay.nameplate.mixin.rewindhandlers` | `Nameplate2Iterator2` | `ClientPackPacket` | ReplayPacket: client resource-pack names resolved under resourcepacks/ |
| `com.moonsworth.lunar.client.replay.rewindhandlers` | `Rewindhandlers2Impl` | `MediaAudioStream` | AudioStream<AudioInputStream> for a media:// file (MediaPool + MusicTrackManager), with OpenAL playback and waveform rendering |
| `com.moonsworth.lunar.client.replay.rewindhandlers` | `Rewindhandlers2_2` | `InputTimelineJsApi` | @CallbackJS goToTick driver extension; its RewindPropertyProvider is InputTimelinePanel |
| `com.moonsworth.lunar.client.replay.rewindhandlers` | `Rewindhandlers2_3` | `EffectsJsApi` | @CallbackJS updateBoundingBoxes driver extension; its RewindPropertyProvider is EffectsPanel |
| `com.moonsworth.lunar.client.replay.rewindhandlers` | `Rewindhandlers2_4` | `ExportSettingsJsApi` | @CallbackJS updatePropertyValue/resetPropertyValue driver extension; its RewindPropertyProvider is ExportSettingsPanel |
| `com.moonsworth.lunar.client.replay.rewindhandlers` | `Rewindhandlers2_5` | `HighlightOptionWatcher` | watches a LightingExtension option and re-applies the HUD highlight (Horsestats20Extension) when the value changes |
| `com.moonsworth.lunar.client.replay.rewindhandlers.highlight` | `Highlight2_2` | `MediaExporterJsApi` | @CallbackJS onClick driver extension for clip/media selection; its RewindPropertyProvider is MediaExporter |
| `com.moonsworth.lunar.client.replay.rewindhandlers.holograms` | `Markers3Iterator` | `ReplayViewContext` | DriverViewContext rendering the rewind framebuffer/alert overlay and markers during playback |
| `com.moonsworth.lunar.client.replay.rewindhandlers.nameplate` | `Nameplate2$Data4` | `SelectedElement` | holder (RewindHandlers, RewindIterator, SelectionHighlightHandler, String); zero references in this partial tree - medium confidence |
| `com.moonsworth.lunar.client.replay.rewindhandlers.coordinates` | `Data` | `KeyframeTarget` | TimelineBridgePayloads.Data: @SerializedName layerId/keyframeType/keyframeId/frame bridge callback payload |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Handler` | `ReplayDriverHandler` | owns the WebOSR driver surface (1920x1080) and MarkersImpl for replay; handles mouse/key events |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl` | `ReplaySoundHandler` | EventPlaySound -> OpenAL listener volume from soundsOption/soundsVolume; pauses with the replay |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl2` | `SelectionHighlightHandler` | raycasts under the cursor each frame to track the hovered entity/block, reports the highlight colour, mouse-button 2 toggles selection |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl3` | `EntityOverrideRenderer` | EventRenderEntity/EventRenderNameTag: hide/skin/name overrides per entity with a profile-texture cache |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl4` | `HudVisibilityHandler` | gates crosshair/chat/titles/scoreboard/bossbar/hotbar rendering from the uiRendering options |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl5` | `ScreenRenderHandler` | EventScreenChange/EventRenderContainerSlotPre/EventResolutionChange; swaps screens and draws the custom cursor |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl6` | `WorldRenderHandler` | renderBlocks/Clouds/Entities/Players/Sky/Particles + chromaKeying options for the exported world render |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl7` | `ZoomKeyHandler` | drives the zoom option/keybind (fixed player camera) during replay |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Impl8` | `ExportTargetHandler` | allocates the export FramebufferBridge, binds the render target and restores screens/GL state around frames |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Iterator` | `ResourcePackHandler` | applies the server/client pack options plus registered/override pack lists |
| `com.moonsworth.lunar.client.replay.rewindhandlers.rewindhandlersCore` | `RewindHandlers3Updater` | `CameraUpdateHandler` | cameraMode/follow/positionRotationFreecam/fov options applied to the RewindCameraController |
| `com.moonsworth.lunar.client.replay.mixin.nameplate` | `Data` | `EndOfCentralDirectory` | ZipEntryLocator.Data: EOCD record (entry count, central-directory offset/size) with the 0xFFFFFFFF/0xFFFF Zip64 sentinel check |
| `com.moonsworth.lunar.client.event` | `Data` | `ItemRender` | EventRenderContainerSlot.Data: the concrete slot-render subtype handled by the container-slot mods |
| `com.moonsworth.lunar.client.event` | `Data` | `TooltipRender` | EventRenderTooltip.Data: x/y/scale + updateCache, the tooltip render state handled by ScrollableTooltips |
| `com.moonsworth.lunar.client.event` | `Type` | `EventPhase` | LunarEventBus.Type PRE/POST/BOTH selecting which side of a wrapped event pair is dispatched |
| `com.moonsworth.lunar.client.event` | `Type` | `Outcome` | ResultEvent.Type ALLOW/DENY/DEFAULT |
| `com.moonsworth.lunar.client.event.fishing` | `Data` | `Interact` | EventEntityInteract.Data: concrete subclass fired/handled by SkyblockArrowAlign |
| `com.moonsworth.lunar.client.event.fishing` | `Data` | `FogRender` | EventFog.Data: colour/range/density payload applied through Bridge fog setters |
| `com.moonsworth.lunar.client.event.fishing` | `Type` | `FogField` | EventFog.Type DENSITY/START/END selects which fog value EventFog.Data applies |
| `com.moonsworth.lunar.client.event.fishing` | `Type` | `SleepStatus` | EventPlayerSleep.Type OK/NOT_POSSIBLE_HERE/... mirrors vanilla EntityPlayer.EnumStatus |
| `com.moonsworth.lunar.client.event.fishing` | `Type` | `Phase` | EventPreRenderPlayer.Type TEST/MONITOR controls the early (cancellable) vs late (monitor-only) dispatch |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl2_2` | `EventItemDrop` | cancellable event carrying BridgeExtension_4 (item stack); SkyblockProtectItem and ItemDropProtection cancel it to block drops |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl3_2` | `EventPluginChannelRegister` | empty event fired by PluginMessageHandler after a lunar/apollo register message; WorldeditCui replies with the WECUI v\|3 handshake |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl4_2` | `EventPlayerJoinWorld` | event carrying Bridge5_11 (player); PlayerCosmeticsSubscription adds the player and NickHider reads the game profile |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl5_2` | `EventPreAttackEntity` | toString literally "EventPreAttackEntity(player=..., target=..., distance=..., hitPos=...)"; fired by the attack mixin |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl6_2` | `EventEntitySpawn` | cancellable event (entity + Itemcounter6 world); hologram/slayer listeners inspect the spawned armor stand |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl7_2` | `EventRenderEntityModel` | cancellable event fired by RendererLivingEntityGlowMixin around renderModel; cancelling skips the model render |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl9_2` | `EventPlayerLivingUpdate` | empty event fired at the head of EntityPlayerSP.onLivingUpdate; ToggleSneak uses it to sync sneak/sprint/fly key state |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl10_2` | `EventEndermanTeleport` | cancellable x/y/z + float event fired by EntityEndermanMixin; SkyblockEndermanSlayer cancels it per island |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl11_2` | `EventPlayerKnockback` | event carrying Bridge5Extension_5 (local player); KnockbackListener marks a knockback when it matches the local player |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl12_2` | `EventInventoryUpdate` | empty event fired from the crafting/inventory mixins; cache-holding HUD mods invalidate on it |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl13_2` | `EventBlockHighlight` | abstract cancellable event: BlockPos + world/entity + block state + double; base of the block-highlight payload variants |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl13$Data` | `EventBlockHighlightWidth` | variant carrying a single float outline width/alpha |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl13$Data2` | `EventBlockHighlightRotation` | variant carrying yaw/pitch/roll for the outline orientation |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl13$Data3` | `EventBlockHighlightColor` | variant carrying red/green/blue for the outline colour |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl13$Data4` | `EventBlockHighlightAlpha` | variant carrying a single float (same shape as Width; distinguished by consumer) - medium confidence |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl13$Data5` | `EventBlockHighlightLayer` | variant carrying an int layer + farPlaneDistance for the outline render pass |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl14_2` | `EventPlayerState` | abstract cancellable base of the player-state events (EventPlayerSleep extends it) |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl14$Data` | `EventItemPickup` | player + EntityItemBridge payload (item pickup by a player) |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl14$Data2` | `EventGameModeChange` | two GameTypeBridge values (previous -> new game mode) |
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl15_2` | `EventOtherPlayerDamage` | fired by EntityOtherPlayerMP.attackEntityFrom (victim, DamageSourceBridge, amount); cancelling refuses the hit |
| `com.moonsworth.lunar.client.event.mixin` | `Data` | `TypedChatMessage` | EventChatMessage.Data: Component + int message type; used by the kill-message/chat parsers (EventTypedMessage sibling is unused) |
| `com.moonsworth.lunar.client.event.mixin` | `Data` | `CommandInput` | EventCommand.Data: raw command string + prefix + valid flag marked by the command framework |
| `com.moonsworth.lunar.client.event.mixin.fishing` | `Data` | `BlockUpdate` | EventBlockUpdate.Data: concrete subclass fired for world block updates |
| `com.moonsworth.lunar.client.event.mixin.gui` | `Type` | `TitleSource` | EventTitle.Type SERVER/APOLLO identifies who sent the title |
| `com.moonsworth.lunar.client.event.mixin.highlight` | `Data` | `FovInput` | EventFovModifier.Data: the input/base FOV-modifier variant (Pre counterpart is EventFovModifierPost) |
| `com.moonsworth.lunar.client.event.mixin.highlight` | `Data` | `ItemStackSize` | EventRenderItemStackSize.Data: renderer + stack label + x/y of the stack-size overlay |
| `com.moonsworth.lunar.client.event.mixin.highlight` | `Type` | `TransformPhase` | EventRenderGlintTransform.Type BEFORE_TRANSFORMS/AFTER_TRANSFORMS selects the glint transform stage |
| `com.moonsworth.lunar.client.event.mixin.highlight` | `Type` | `GlintTarget` | EventRenderItemGlint.Type EQUIPPED_ARMOR/ITEM/GUI selects what is being glinted |
| `com.moonsworth.lunar.client.event.mixin.nameplate` | `Data` | `Focused` | EventRenderHud.Data: focused-HUD subclass of EventRenderHudFocused |
| `com.moonsworth.lunar.client.cosmetics.emote` | `Data` | `Builder` | ModelRenderConfig.Data: fluent builder (model/animation processor/texture/config flags/colour) returned by the static factory |
| `com.moonsworth.lunar.client.cosmetics.holograms` | `Data` | `MorphEntry` | MorphTracker.Data: cached morph id + index per tracked model |
| `com.moonsworth.lunar.client.cosmetics.holograms` | `Gui2Handler` | `EmoteGiftProvider` | JsonProvider emitting emoteId/slotId/jamId (+ expireTime/grantedAt from EmoteGift) |
| `com.moonsworth.lunar.client.cosmetics.holograms` | `Holograms8Handler` | `BlockMorphRenderer` | MorphRenderer for block morphs (Block/Meta NBT -> block state, pose + transform) |
| `com.moonsworth.lunar.client.cosmetics.inactive` | `Type` | `PathfinderType` | Inactive.Type GROUND/FLYING selects the companion pathfinder mode |
| `com.moonsworth.lunar.client.cosmetics.inactive` | `Data` | `MolangResourceLoader` | MolangResourceProvider.Data: MixinHelper102_4 that parses the geckolib molang resource pack entries into providers |
| `com.moonsworth.lunar.client.cosmetics.inactive.mixin` | `FogIterator` | `GeckolibCosmeticManager` | LoadableHandler managing geckolib cosmetic models: loads cosmetics/functions.molang + constants.molang, model/animation caches, crash tag literally "GeckolibCosmeticManager" |
| `com.moonsworth.lunar.client.cosmetics.inactive.mixin` | `Gui2Impl` | `CompanionCosmetic` | JSON definition (attributes/default_transition_ticks/default_anim/states) building the "companion" animation controller |
| `com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui` | `Extension` | `AnimationTest` | AnimationControllerImpl.Extension: PlayState test(AnimationEventImpl) controller callback |
| `com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin` | `Gui2Impl` | `IdleMoveController` | AnimationControllerDefinition choosing idle_anim vs move_anim from the entity's movement speed |
| `com.moonsworth.lunar.client.cosmetics.inactive.mixin.holograms` | `Holograms2Iterator` | `EmoteModel` | abstract BedrockAnimatedModel/IAnimatableModel implementation with an AnimationProcessor, render context and tick offset |
| `com.moonsworth.lunar.client.cosmetics.inactive.mixin.holograms` | `Data` | `RuntimeKey` | Holograms2Iterator.Data: (entity UUID, tick) key for the cached MolangRuntime |
| `com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers` | `Rewindhandlers2_2` | `BedrockGeometry` | Bedrock geometry body: List<IBoneSerializer> bones + ModelDescription, name lookup recursing the bone tree |
| `com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers` | `Rewindhandlers3_2` | `BedrockGeometryFile` | @Annotation27 format_version + minecraft:geometry array, the parsed geometry file root |
| `com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers` | `Rewindhandlers4_2` | `BedrockBone` | @Annotation27 bind_pose_rotation/cubes/debug/parent/pivot/rotation/poly_mesh/texture_mesh/name bone entry |
| `com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers` | `RewindhandlersType` | `PolyMeshType` | enum QUAD_LIST/TRI_LIST serialised as quad_list/tri_list ("Cannot deserialize PolysEnum") for the bone poly_mesh |
| `com.moonsworth.lunar.client.cosmetics.molang` | `Data` | `GeneratedClassLoader` | MolangClassDefiner.Data: ClassLoader defining the JIT-generated molang classes |
| `com.moonsworth.lunar.client.cosmetics.molang` | `Data` | `ChildScope` | MolangScope.Data: child scope delegating unresolved symbols to its parent |
| `com.moonsworth.lunar.client.cosmetics.molang` | `Data` | `MolangSigWildcard` | MolangJitSignature.Data: empty signature node (matches any expression) |
| `com.moonsworth.lunar.client.cosmetics.molang` | `Extension` | `MolangSigNode` | MolangJitSignature.Extension: marker interface of the JIT signature AST nodes (MolangSigGroup/If/FunctionCall/...) |
| `com.moonsworth.lunar.client.cosmetics.skin` | `Data` | `Face` | CustomizableCube.Data: face vertex position (Vector3f) + uv pair |
| `com.moonsworth.lunar.client.cosmetics.skin` | `Data` | `Origin` | SolidPixelWrapper.Data: x/y/z origin triple of the solid pixel box |
| `com.moonsworth.lunar.client.cosmetics.skin` | `Type` | `CubeAxis` | CubeDirection.Type X/Y/Z axis selector |
| `com.moonsworth.lunar.client.render` | `Data` | `DeferredDraw` | RenderSubmission.Data: holds the ModelRenderConfig + copied pose and replays draw() through the render pipeline |
| `com.moonsworth.lunar.client.render.font` | `Data` | `CacheKey` | TextRenderCache.Data: (string, colour, shadow) cache key with equals/hashCode |
| `com.moonsworth.lunar.client.render.texture` | `Extension` | `TextureProvider` | TextureProcessor.Extension: resource-pack texture provider (name/meta/dimensions/process) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer` | `IComponentBase` | upstream mchorse Blockbuster IComponentBase: marker with getSortingIndex returning 0, implemented by every particle component interface |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2_3` | `BedrockComponentBase` | upstream Blockbuster BedrockComponentBase: fromJson/toJson/canBeEmpty base of every Bedrock component |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2_4` | `IValue` | upstream McLib mclib.math.IValue: isNumber/set(double\|String)/get/booleanValue/stringValue |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2_2` | `JsonPrettyPrinter` | upstream McLib JsonUtils.jsonToPretty: Gson JsonWriter with 4-space indent |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler` | `MathFunction` | upstream McLib mclib.math.functions.Function: args + required-argument count + result IValue |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler2` | `Constant` | upstream McLib mclib.math.Constant: holds a double or string IValue |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler22` | `Variable` | upstream McLib mclib.math.Variable extends Constant with a name used by the parser's variable map |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler_2` | `Group` | upstream McLib mclib.math.Group: wraps an IValue in parentheses in toString |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler4` | `ValueWrapper` | upstream McLib mclib.math.Wrapper (name taken by legacy.wrapper.Wrapper): abstract single-value wrapper |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler42` | `Negative` | upstream McLib mclib.math.Negative: unary minus (-value) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler43` | `Negate` | upstream McLib mclib.math.Negate: logical not (!value) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler5` | `Operator` | upstream McLib mclib.math.Operator: Operation + left/right IValues |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Handler3` | `Ternary` | upstream McLib mclib.math.Ternary: condition ? ifTrue : ifFalse |
| `com.moonsworth.lunar.client.render.particle` | `GlintcolorizerType` | `Operation` | upstream McLib mclib.math.Operation enum: + - * / % ^ && \|\| < <= >= > == != with signs and precedence |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base_2` | `NSFunction` | upstream McLib NSFunction: number args validated, string result (isNumber false, result=stringValue) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base2_3` | `SNFunction` | upstream McLib SNFunction: string args validated, number result (isNumber true, result=doubleValue) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base4_2` | `NNFunction` | upstream McLib NNFunction: abstract number function, throws "'+name+' cannot receive string arguments!" |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base4_3` | `Floor` | mclib.math.functions.rounding.Floor (Math.floor) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base42_2` | `Max` | mclib.math.functions.limit.Max |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base42_3` | `Ceil` | mclib.math.functions.rounding.Ceil (Math.ceil) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base43_2` | `Clamp` | mclib.math.functions.limit.Clamp (ClampUtils.clamp) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base43_3` | `Round` | mclib.math.functions.rounding.Round |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base44_2` | `Trunc` | mclib.math.functions.rounding.Trunc (negative -> ceil else floor) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base44_3` | `Ln` | mclib.math.functions.classic.Ln (Math.log) |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base45` | `Pow` | mclib.math.functions.classic.Pow |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base46` | `Sin` | mclib.math.functions.trig.Sin |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base47` | `Sqrt` | mclib.math.functions.classic.Sqrt |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base48` | `Mod` | mclib.math.functions.classic.Mod |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base_3` | `BedrockComponentLifetime` | upstream Blockbuster abstract lifetime component base: active_time Molang (default 10), sorting index -10 |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base2_2` | `BedrockComponentLifetimeLooping` | upstream Blockbuster LifetimeLooping: sleep_time + playing state restart |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base3_2` | `BedrockComponentLifetimeOnce` | upstream Blockbuster LifetimeOnce: emitter lifetime = active_time*20 then stop |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Base4_4` | `BedrockComponentLifetimeExpression` | upstream Blockbuster LifetimeExpression: activation_expression/expiration_expression start/stop |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer2Impl_2` | `BedrockComponentAppearanceLighting` | upstream Blockbuster AppearanceLighting: emitter lit=false on init, canBeEmpty true |
| `com.moonsworth.lunar.client.render.particle` | `GlintcolorizerExtension` | `IComponentEmitterInitialize` | upstream Blockbuster IComponentEmitterInitialize: apply(emitter) hook distinct from the per-tick IComponentEmitterUpdate |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer3_2` | `BedrockScheme` | upstream Blockbuster BedrockScheme: identifier/material/textures/curves + component lists, fromJson/toJson |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer3_3` | `AngleMath` | wrapDegrees(float/double/int) into [-180,180) helpers |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer4_2` | `BedrockParticle` | upstream Blockbuster BedrockParticle: per-particle position/velocity/rotation/matrix/lifetime runtime state |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer5_2` | `BedrockEmitter` | upstream Blockbuster BedrockEmitter: emitter transform/variables/particle list, variable.particle_* bindings (McLib/Blockbuster maths) |
| `com.moonsworth.lunar.client.render.particle` | `GlintcolorizerImpl` | `MolangAssignment` | upstream McLib MolangAssignment: "variable = expression" assignment node |
| `com.moonsworth.lunar.client.render.particle` | `GlintcolorizerIterator` | `MolangMultiStatement` | upstream McLib MolangMultiStatement: ';'-joined list of expressions |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer$Data` | `TintSolid` | upstream Blockbuster Tint.Solid (decompiled as the top-level Glintcolorizer$Data): 4 Molang channels r/g/b/a |
| `com.moonsworth.lunar.client.render.particle` | `Glintcolorizer$Data2` | `TintGradient` | upstream Blockbuster Tint.Gradient (top-level Glintcolorizer$Data2): stops + interpolant + range |
| `com.moonsworth.lunar.client.render.particle` | `Data` | `ColorStop` | Glintcolorizer$Data2.Data: (float position, solid colour) gradient stop |
| `com.moonsworth.lunar.client.render.particle.highlight` | `Glintcolorizer2Base32` | `BedrockComponentShapeDisc` | upstream Blockbuster ShapeDisc: radius + plane_normal vector distribution |
| `com.moonsworth.lunar.client.render.particle.highlight` | `Glintcolorizer2Base5` | `BedrockComponentShapeBox` | upstream Blockbuster ShapeBox: half_dimensions offset/surface distribution |
| `com.moonsworth.lunar.client.render.particle.mixin` | `Glintcolorizer3Iterator` | `MolangParser` | upstream McLib MolangParser extending MathBuilder: variable map, function registration, parse/parseExpression/parseJson |
| `com.moonsworth.lunar.client.render.particle.mixin` | `GlintcolorizerException` | `MolangException` | upstream McLib MolangException: checked parse error |
| `com.moonsworth.lunar.client.render.particle.nameplate` | `Bridge8Extension3` | `GifTexture` | GIF texture implementation of the bridge interface Bridge8Extension3: frame list + duration/index and the gif frame ticker |
| `com.moonsworth.lunar.client.render.particle.nameplate` | `Data` | `GifFrame` | Bridge8Extension3.Data: frame delay + byte buffer |
| `com.moonsworth.lunar.client.render.particle.rewindhandlers` | `Glintcolorizer2Iterator` | `BedrockComponentMotionCollision` | upstream Blockbuster MotionCollision: enabled/collision_drag/coefficient_of_restitution/collision_radius/expire_on_contact |
## Skipped

Only one class in scope could not be named from evidence:

| package | class | why |
|---|---|---|
| `com.moonsworth.lunar.client.event.fishing` | `HighlightImpl8_2` | cancellable event holding a `Bridge5_11` (player) with **zero references** anywhere in the tree and no firing site (the recorder/mixin that fires it is not in this partial tree). Sibling `HighlightImpl4_2` (same payload) was named `EventPlayerJoinWorld` from its consumers; `8_2` has none, so any name would be invention. |

## Not junk (deliberately left alone)

| class | reason |
|---|---|
| `cosmetics/emote/Direction2D` | "2D" is a dimension suffix (`LEFT/UP/RIGHT/DOWN`), not an obfuscation index. |
| `cosmetics/molang/MolangMathAtan2` | real function name (`Math.atan2`). |
| `replay/mixin/nameplate/ZipEntryLocator.Zip64EndOfCentralDirectory`, `Zip64ExtraField` | ZIP64 format names, not obfuscation. |

## Out of scope (named in the brief but not in the 4 subtrees)

These live under `client/framework/feature/mod/fishing/**` and belong to another
cluster; this map does not touch them:

* `framework/feature/mod/fishing/highlight/Highlight{Handler,ConfigMigrations,MigrationContext,Serializer,Button,MigrationStep}.java`
* `framework/feature/mod/fishing/click/JsonDeserializerImpl.java`, `framework/feature/mod/fishing/JsonDeserializerIterator$Data.java`,
  `framework/feature/mod/fishing/nameplate/JsonDeserializerImpl.java`,
  `framework/feature/mod/impl/chest/dungeon/dungeonwaypoints/JsonDeserializerImpl.java`
* `framework/feature/mod/fishing/holograms/HologramsHandler.java`, `framework/feature/mod/holograms/HologramsHandler.java`,
  `framework/feature/mod/debug/HologramsHandler.java`, `framework/feature/mod/rewindhandlers/Rewindhandlers$Data{9..12}.java`

## Confidence notes

* High confidence for everything derived from call sites, JSON keys (`@SerializedName`/`@Annotation27`), crash tags,
  or upstream Blockbuster/McLib sources.
* Medium confidence (marked in the evidence column): `PlayerAnimationPacket` (write-only int on `LocalPlayerContext`),
  `SelectedElement` (zero refs), `EventBlockHighlightAlpha` (shape-identical to `EventBlockHighlightWidth`).
