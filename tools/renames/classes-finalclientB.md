# Final client-B class renames (dry run)

Scope: top-level junk-stem class declarations under `com/moonsworth/lunar/client/**`,
excluding the four packages owned by other agents (`client/replay`, `client/event`,
`client/cosmetics`, `client/render`). Discovery used the column-0 declaration grep:

```bash
grep -rE '^(public |final |abstract )*(class|interface|enum) [A-Za-z]+[0-9_$]' \
  src/main/java/com/moonsworth/lunar/client --include=*.java \
  | grep -v 'client/replay/\|client/event/\|client/cosmetics/\|client/render/'
```

126 rows. Applier dry run:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-finalclientB.tsv
[aware-renames] rows=126 skipped=0 files_touched=266 files_renamed=126 mode=dry-run
```

| package | old | new | evidence |
|---|---|---|---|
| `com.moonsworth.lunar.client.framework.feature.tiertagger` | `Tiertagger2Iterator_3` | `TierApiProvider` | abstract tier-API client base (extends Tiertagger2_2): fetches <base>tierlists gamemodes and <base>profile/<uuid> JSON and parses region/overall/points/rankings into TierPlayerProfile; subclasses supply base url + gamemode name via abstract method2/3/4 |
| `com.moonsworth.lunar.client.framework.feature.tiertagger` | `Tiertagger3_2` | `TierRanking` | per-gamemode ranking DTO built in TierApiProvider.method7: TierGameMode + current TierPlacement + optional peak placement + optional retired flag; value type of TierPlayerProfile's rankings map |
| `com.moonsworth.lunar.client.framework.feature.tiertagger` | `Tiertagger6Impl` | `MctiersMetadata` | TierMetadataRegistry singleton super("MCTiers"): reads mctiers.com fallbackGameModes_com and mctiers.io fallbackGameModes_io; log "[MCTiers] Loaded %s (.com) and %s (.io) fallback modes" |
| `com.moonsworth.lunar.client.framework.feature.tiertagger` | `Tiertagger6Impl_2` | `PvphqMetadata` | TierMetadataRegistry singleton super("PvpHQ"): reads pvphq.gameModes ladders; log "[PvpHQ] Loaded %s ladders" |
| `com.moonsworth.lunar.client.framework.feature.tiertagger.nameplate` | `Tiertagger6Impl` | `SubtiersMetadata` | TierMetadataRegistry singleton super("SubTiers"): reads subtiers fallbackGameModes; log "[SubTiers] Loaded %s fallback modes" |
| `com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers` | `Tiertagger6Iterator` | `TiertestsMetadata` | TierMetadataRegistry singleton super("TierTests"): tier tests api/gamemodes/tier endpoints plus mcTiersFormat table (nested Data) consumed by TierBadgeFormatter; log "[TierTests] Loaded %s fallback modes and %s tier format data" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing$Data` | `SkyBlockCommandBuilder` | fluent builder for SkyBlockCommand (prettyName/command/icon/anySubCommand/subCommands/userDefinedSubCommands/parent); its Lombok toString literally emits "SkyBlockCommand.SkyBlockCommandBuilder" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing2_2` | `SkyBlockChat` | static SkyBlock chat helper: Component prefix helpers, sendMessage, and "Feature %s has been toggled [ON]/[OFF]" notifications via Ref.method4().method69() |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing2_3` | `SkyBlockCommandMigrations` | runs the registered SkyBlockCommandMigrationStep suppliers over SkyBlockCommandKeybinds' persisted command tree (field2 = step count); called by SkyBlockCommandKeybinds when commands.json needs a version migration |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3_2` | `SkyBlockCommandMigrationStep` | package-private functional interface: void method1(SkyBlockCommandMigration), one migration step over the SkyBlock command model |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3_3` | `SkyBlockChatCommand` | abstract SkyBlock chat command handler: getCommand/getAliases/aliases()/isEnabledByDefault/chat types; method7 sends the line via ChatMessageQueue and method8 notifies on missing permissions |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl` | `CoinflipCommand` | SkyBlockChatCommand.getCommand() = "coinflip" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl2` | `TimeCommand` | SkyBlockChatCommand.getCommand() = "time" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl3` | `BoopCommand` | SkyBlockChatCommand.getCommand() = "boop" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl4` | `CoordsCommand` | SkyBlockChatCommand.getCommand() = "coords" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl5` | `InviteCommand` | SkyBlockChatCommand.getCommand() = "invite" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl6` | `DiceCommand` | SkyBlockChatCommand.getCommand() = "dice" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl7` | `PartyWarpCommand` | SkyBlockChatCommand.getCommand() = "warp" (alias w): sends "/p warp" (the existing mod/mixin WarpCommand is the client /warp command) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl8` | `PingCommand` | SkyBlockChatCommand.getCommand() = "ping" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl9` | `FpsCommand` | SkyBlockChatCommand.getCommand() = "fps" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl10` | `QueueInstanceCommand` | SkyBlockChatCommand.getCommand() = "queueinstance" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl11` | `AllInviteCommand` | SkyBlockChatCommand.getCommand() = "allinvite" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl12` | `EightBallCommand` | SkyBlockChatCommand.getCommand() = "eightball" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Impl13` | `TpsCommand` | SkyBlockChatCommand.getCommand() = "tps" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Iterator` | `CommandPathMigrationStep` | SkyBlockCommandMigrationStep: recursively rewrites pretty-name keys to SkyBlockCommand.key() paths in the favorites and user-defined subcommand maps |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Iterator2` | `KickCommand` | SkyBlockChatCommand.getCommand() = "kick" (alias k): resolves a party member prefix via PartyState and sends "/p kick <name>" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `Fishing3Iterator_2` | `PartyTransferCommand` | SkyBlockChatCommand.getCommand() = "partytransfer" (aliases pt/ptme) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing` | `JsonDeserializerIterator$Data` | `SkyBlockCommandConfig` | root commands.json model: final Set<SkyBlockCommand>; produced by SkyBlockCommandDeserializer and consumed by SkyBlockCommandKeybinds/SkyBlockCommandMigration |
| `com.moonsworth.lunar.client.util` | `ThreadModuleDumpThread` | `AsyncCacheLoader` | generic key->value background refresh thread (Caffeine cache + Extension.fetch loader + retry count + invalidateAll/getInfo); unreferenced in source, jar-only lazy API |
| `com.moonsworth.lunar.client.util` | `ThreadModuleDumpType` | `Direction` | compass direction enum TOP/BOTTOM/RIGHT/LEFT with getOpposite/rotate90CW/rotate90CCW/offsetX/offsetY/isVertical/getRandom/getAxis; unreferenced in source |
| `com.moonsworth.lunar.client.util` | `ThreadModuleDump73Type` | `InterpolationMode` | keyframe interpolation enum LINEAR/QUAD/CUBIC/STEP/SWING/SINE/EXP/CIRCLE/ELASTIC/BACK/SMOOTH/SMOOTHER/CATMULL_ROM carrying in/out/inout Easing curves; used by the replay timeline Fishing2Loader "interpolation" property |
| `com.moonsworth.lunar.client.util` | `ThreadModuleDumpIterator` | `EmptyIterator` | no-op Iterator<T> (hasNext=false, next=null) returned by Holograms3Iterator/HologramEntityListener when an entity subscription is empty |
| `com.moonsworth.lunar.client.util` | `Util$Data` | `ResourceUsageCounter` | AsyncResourceManager accounting counter: int count + long bytes; method1/method2 add/remove and method3 renders "N resources, <size>" |
| `com.moonsworth.lunar.client.util` | `UtilType` | `Visibility` | PUBLIC/PROTECTED/PRIVATE/PACKAGE access-level enum used by FieldSpec.Data.method4 and MethodSpec.Data.method6 in the javapoet-style spec builder |
| `com.moonsworth.lunar.client.util` | `Util` | `JavaFileWriter` | writes spec-built Java sources to disk: resolve package path segments and FileWriter the MixinHelper222 TypeSpec; also static indent/wrap helpers used by AbstractSpec/FieldSpec/MethodSpec |
| `com.moonsworth.lunar.client.util` | `Highlight3Task` | `HostedWorldJoinHandler` | joins PublicServer/HostedWorld Memory locations from the friend list: opens the hosted-world join GUI/party invite, sends the join RPC, and computes joinability colors/tooltips ("joinWorld","worldFull","worldRequiresVersion"...) |
| `com.moonsworth.lunar.client.framework.feature.mod` | `MixinHelperType` | `GuiTheme` | mod-menu colour theme enum (DEFAULT_LIGHT/DEFAULT_DARK/DARK_BLUE -> mainColor/panel/inset ARGB fields) used by GuiRenderer and InventorySearchOverlay |
| `com.moonsworth.lunar.client.framework.feature.itemcounter` | `Itemcounter` | `ItemCounterRenderer` | renders one counted inventory entry: draws the item + "count name" text (red when zero) and measures its size; instantiated per ItemCounterEntry by ItemCounterElementChildMod |
| `com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod` | `F3DataDebugTask` | `F3DataTask` | FpsDebugTask id "f3-data": walks the F3 debug screen into a text/pie-chart/chart report written to DebugArchive "f3-data.txt" |
| `com.moonsworth.lunar.client.framework.feature.screenshot` | `Gui2Iterator` | `ProgressNotification` | Notification subclass showing a 0-100 progress bar (method4 sets percent, method2 draws bar + "N%") used by screenshot upload |
| `com.moonsworth.lunar.client.framework.feature.mod.gui` | `Bridge7Iterator2` | `InventoryButtonRegistry` | per-screen registry of HighlightButton -> InventoryButtonWidget (Lotusfish5: all()/byId()); created by SkyblockInventoryButtonsMovementGui and passed to the Lotusfish screen |
| `com.moonsworth.lunar.client.framework.feature.mod.gui` | `Bridge7Iterator22` | `ScreenButtonGroup` | screen-scoped button group (Lotusfish4) wrapping a Lotusfish screen + registry: id/position/enable/disable/load-state |
| `com.moonsworth.lunar.client.framework.feature.mod.gui` | `Bridge7Iterator222` | `InventoryButtonWidget` | one inventory button (Lotusfish2): id, position/size/scale, alignment, enabled state and the static HighlightButton list; ids "inventoryButton-N" |
| `com.moonsworth.lunar.client.framework.feature.mod.gui` | `Calculator2Updater` | `SkyBlockCommandEntry` | command row in SkyBlockCommandsGui: draws "> Run Command" / "Select Sub-Command" and runs or expands the SkyBlockCommand |
| `com.moonsworth.lunar.client.framework.feature.mod.gui` | `Calculator2Updater2` | `SkyBlockSubCommandEntry` | sub-command navigation row in SkyBlockCommandsGui/SkyBlockSubCommandSelectGui (parent+child SkyBlockCommand, sub-command picker) |
| `com.moonsworth.lunar.client.framework.feature.mod.gui.mixin` | `Bridge7Impl` | `SpiritLeapGuiContainer` | Bridge7Handler subclass that wraps a GuiContainerBridge and an optional SkyblockSpiritLeapDebug (method1 = hasSpiritLeapDebug, method5 returns it) |
| `com.moonsworth.lunar.client.framework.feature.mod.gui.mixin` | `Markers3Handler` | `SpiritLeapMarkerDispatcher` | DriverViewContext that routes marker calls to the SpiritLeap GUI (SkyblockSpiritLeapDebug.method3) when the open container is a SpiritLeapGuiContainer, else falls back to the driver router |
| `com.moonsworth.lunar.client.framework.feature.quickplay.mixin` | `Bridge7Iterator2` | `QuickplayGamesScreen` | QuickPlay games-grid screen: favorites (QuickplayGameEntry) + game rows (QuickplayModeEntry), loading placeholder "loadingGames"/"chooseGame" |
| `com.moonsworth.lunar.client.framework.feature.quickplay.mixin` | `Bridge7Iterator3` | `QuickplayModesScreen` | QuickPlay mode-selection screen for one game: "< Lobby" + one QuickplayModeEntry per mode, "keyBindHint" |
| `com.moonsworth.lunar.client.framework.feature.quickplay.mixin` | `Calculator2Updater` | `QuickplayModeEntry` | clickable mode row (Lotusfish handler): sends the mode command or opens QuickplayModesScreen; draws "Select Mode"/"> Go To Lobby" |
| `com.moonsworth.lunar.client.framework.feature.quickplay.mixin` | `Calculator2Updater2` | `QuickplayGameEntry` | clickable game row: opens QuickplayModesScreen for the game or sends its lobby command (field19 = game has no modes) |
| `com.moonsworth.lunar.client.framework.feature.potioneffects` | `Potioneffects3Impl` | `GridPotionEffectRenderer` | Potioneffects3 layout strategy that lays the active effects out in rows/columns (field30 icon-only, field31 icon scale) |
| `com.moonsworth.lunar.client.framework.feature.potioneffects` | `Potioneffects3Impl2` | `CompactPotionEffectRenderer` | Potioneffects3 layout strategy that draws each effect as a compact name+duration line (mirrors horizontal alignment, field24) |
| `com.moonsworth.lunar.client.framework.feature.potioneffects` | `Potioneffects3Impl3` | `IconPotionEffectRenderer` | Potioneffects3 layout strategy drawing classic icon boxes (effect_background/effect_background_ambient sprites, 24/27/25/26 offsets) |
| `com.moonsworth.lunar.client.framework.feature.minimap` | `Minimap2_2` | `MinimapLayer` | abstract minimap layer<T>: position/size, render(method1), tooltip(method2/method3), context-menu actions(method6) and the marker payload T; base of the player/entity/waypoint layers |
| `com.moonsworth.lunar.client.framework.feature.minimap` | `Minimap2Impl` | `PlayerMinimapLayer` | MinimapLayer<Markers2> that renders the local-player marker (Markers2.method6().method5) |
| `com.moonsworth.lunar.client.framework.feature.minimap` | `Minimap2Impl2` | `EntityMinimapLayer` | MinimapLayer<BridgeExtension2_5> rendering entity icons with a vanilla->lunar mob texture map (entityhorse->horse, pigzombie->zombie_pigman, ...) |
| `com.moonsworth.lunar.client.framework.feature.minimap` | `Minimap2Impl3` | `WaypointMinimapLayer` | MinimapLayer<Waypoint> rendering user waypoints plus the waypoint context menu ("edit"/"remove" -> Client.method109().method48()) |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Handler` | `WorldEditSelectionBase` | base WorldeditSelection carrying the list of Vector3dc points (getPoints, point add/insert helpers) |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base` | `BoundedSelection` | WorldeditSelectionBase with an integer min/max bound pair set via method1(x,z) |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base2` | `VersionedSelection` | BoundedSelection-versionable base implementing WorldeditSelectionVersion: holds the selection protocol long (method2/set) |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base22` | `CuboidSelection` | final VersionedSelection for WorldeditcuiType.CUBOID (no extra state) |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base23` | `PolyhedronSelection` | final VersionedSelection for WorldeditcuiType.POLYHEDRON: List<int[]> face point lists |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base3` | `VersionedBoundedSelection` | BoundedSelection implementing WorldeditSelectionVersion (adds the protocol long); base of the polygon2d selection |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base32` | `Polygon2dSelection` | final VersionedBoundedSelection for WorldeditcuiType.POLYGON2D |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Base4` | `CylinderSelection` | final BoundedSelection for WorldeditcuiType.CYLINDER: centre Vector3d + radius/height |
| `com.moonsworth.lunar.client.framework.feature.worldeditcui` | `Worldeditcui2Impl` | `EllipsoidSelection` | final WorldEditSelectionBase for WorldeditcuiType.ELLIPSOID: two Vector3d centre points |
| `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` | `HighlightBase$Data3` | `DungeonMapResetEvent` | @TriggeredBy(DungeonMapListener); fired when the current dungeon room changes (Holograms2_5) and from DungeonMapListener.clear(); consumers reset room UI |
| `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` | `HighlightBase$Data4` | `DungeonRoomDetectedEvent` | @TriggeredBy(DungeonMapListener); fired when a new dungeon room is detected (non-null after a change); handled by DungeonSecretListener to (re)load the room secret data |
| `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` | `Rewindhandlers$Data9` | `DungeonExitEvent` | @TriggeredBy(DungeonFloorListener); fired on world change when leaving a dungeon (floor != NONE -> NONE); handled by DungeonTerminalListener.reset |
| `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` | `Rewindhandlers$Data10` | `DungeonFloorDetectedEvent` | @TriggeredBy(DungeonFloorListener); fired when the dungeon floor is first detected (NONE -> floor); payload DungeonFloor; handled by DungeonScoreListener/DungeonMapListener/DungeonTerminalListener |
| `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` | `Rewindhandlers$Data11` | `DungeonScoreUpdateEvent` | @TriggeredBy(DungeonScoreListener); payload int = computed dungeon score, fired whenever the score changes |
| `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers` | `Rewindhandlers$Data12` | `DungeonClassChangedEvent` | LunarEvent payload (DungeonPlayerTracker, DungeonClass) fired by Holograms4Updater when a teammate's dungeon class changes |
| `com.moonsworth.lunar.client.framework.mixin` | `Highlight3Handler` | `InitialScreenGuard` | EventBus listener: cancels EventInitialScreenOpen until the first EventTick arrived, reset on EventScreenChange (first-screen-open gate) |
| `com.moonsworth.lunar.client.framework.feature.nickhider` | `Highlight3Handler` | `NicknameListener` | NickHider chat/screen listener parsing "you will be nicked as ..."/"You are now nicked as <name>!"/"Your nick has been reset!" and the nickname GUI ("Enter your desired username here") |
| `com.moonsworth.lunar.client.framework.listener.mixin` | `GuiRewindhandlers6Impl` | `ServerSettingsListener` | EventBus listener applying/clearing server-scoped mod + option values (ModTraits.field4/OptionTraits.field5) on EventServerJoin/EventServerBrand/EventDisconnect |
| `com.moonsworth.lunar.client.framework.listener.mixin` | `GuiRewindhandlers6Task` | `KeybindOptionListener` | EventBus listener that toggles registered LightingExtension options on key/mouse input (method3 EventMouseButton, method4 EventKeybind, key->KEY_MOUSE mapping) |
| `com.moonsworth.lunar.client.framework.feature.mod.holograms` | `Holograms3Iterator` | `SimpleEntitySubscription` | entity subscription backed by a parent subscription + three consumer lists (add/remove/clear); returns EmptyIterator when empty |
| `com.moonsworth.lunar.client.mod.hud` | `HypixelBedwarsUpgradeDisplayChild$HRICOROOOCCOCOROCRHHCRRIRCOICO$Data` | `BedwarsUpgrade` | top-level decompiler-split inner of HypixelBedwarsUpgradeDisplayChild's upgrade registry: name/Forge flag/id/colour int[]s, method1 = isForge() |
| `com.moonsworth.lunar.client.config.option.trait` | `Lightoverlay2Extension` | `TraitHost` | TraitReader marker for a trait container; registered via ConditionalOption.method10 and used by Trait.method1 to write values |
| `com.moonsworth.lunar.client.config.option.trait` | `Lightoverlay3Extension` | `MutableTraitHost` | TraitHost + set(int,T) target of Trait.method1 (extends the jar-only Lightoverlay3/Lightoverlay2Extension2 bases) |
| `com.moonsworth.lunar.client.config.option.trait` | `Lightoverlay9Impl` | `BuilderTraitType` | TraitType subclass carrying a TraitBuilder supplier (method1()); used as ModTraits.field18/ConditionalOption.method3 trait type |
| `com.moonsworth.lunar.client.driver.core.gui` | `Gui2Task` | `ButtonProvider` | GUI button descriptor + builder (JsonProvider+Translatable; its Lombok toString is "ButtonProvider.ButtonProviderBuilder(id=...)"); used by HomeButtonBridge |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2_2` | `DungeonRoomRegistry` | static dungeon room tables: room-name -> MapRoomType, room-name -> DungeonRoomPreset (name/x/z/RoomState) and the four preset player UUIDs |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2_3` | `TrackedRoom` | mutable room state holder: index, RoomState, clear-time, display name, MapRoomType, bounds, RoomSecret set |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2_4` | `StackedPlayersTooltip` | builds the "Stacked with" / "(class)" tooltip lines for a room's player tracker list |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2_5` | `DungeonStateTracker` | the dungeon run tracker: room registry, puzzle/secret counters, map decoration parsing, room clear times and player list; fires the dungeon events |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2_6` | `RoomHistoryRenderer` | renders a cleared-room entry (RoomStateHistory) marker on the bettermap at the room position with rotation-aware size |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2$Data` | `DungeonRoomPreset` | static room preset entry: name/x/z + expected RoomState (Chambers, Blood, Supertall, ...) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms2$Data2` | `TestDungeonStateTracker` | final DungeonStateTracker subclass with a single boolean field, private ctor and F7 floor; sandbox/test instance (method39) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3_2` | `DungeonMapOverlay` | the bettermap overlay renderer: draws the dungeon map panel/rooms/players from BettermapSettings + DungeonStateTracker and hosts the MapElement list |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3_3` | `RouteManager` | loads/saves .lcroute DungeonRoutes (LunarConstants Dungeon-Routes dir), tracks the current route/section and exposes RouteRenderer |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3_4` | `RoomCoordinateTransform` | static coordinate helpers: map Vec3i/doubles through a RoomRotation into the rotated room space |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3_5` | `RoomEvent` | abstract reversible room-tracker event (apply method1 / revert method2 on a DungeonRoomTracker) with a creation timestamp; replayed by the route system |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3_6` | `MapColorCodes` | bettermap colour-code constants and the int code -> MapRoomType / int code -> RoomState lookup maps |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl` | `SetRoomCoordinatesAction` | RoomEvent applying/reverting the detected room's coordinate pair (TrackedRoom.method17+method10 for apply, method17+method10 for revert with the previous values) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl2` | `SetRoomStateAction` | RoomEvent applying/reverting a TrackedRoom RoomState |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl3` | `SetRoomTypeAction` | RoomEvent applying/reverting a TrackedRoom MapRoomType |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl4` | `AddRoomPositionAction` | RoomEvent that adds a WorldPosition to the tracker on apply and removes it on revert |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl5` | `SetRoomInstanceAction` | RoomEvent applying/reverting a detected RoomInstance on the tracker |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl6` | `RenameRoomAction` | RoomEvent applying/reverting a TrackedRoom display-name rename |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms3Impl7` | `RemoveRoomPositionAction` | RoomEvent that removes a WorldPosition on apply and re-adds it on revert (counterpart of AddRoomPositionAction) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms4_2` | `DungeonTrackable` | interface List<Component> method1() = tooltip lines; implemented by the tracked room and tracked player |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms4_3` | `RouteDataCodec` | DungeonRoute codec/upgrader: toRoute(...) throws IOException "Unknown route data version!" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms4_4` | `DungeonSplit` | one dungeon split entry (TextComponent name + regex + HudTimer + start time) stored in DungeonSplits; renders "<time>s (+/-delta)" |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms5_2` | `DungeonScorePanel` | the bettermap score panel: secrets/crypts/mimic/min-secrets/deaths/score lines plus the total/skill/exploration/time/bonus breakdown |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms5_3` | `RouteRecorder` | records a route while walking: start/stop recording, appends RouteSections from NameplateType positions, saves to RouteManager |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms6_2` | `RouteConditions` | immutable set of route requirements (etherwarp/aote/pearls/PickaxeTier) with max/isSubsetOf/setX builders; RouteRequirements reads the held item into it |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms6_3` | `MapElement` | abstract bettermap element (name, AnimatedValue, visibility) with render/shouldRender methods; base of the Score/Dungeon/Boss elements |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms6Impl` | `ScoreMapElement` | MapElement "Score": renders the dungeon score element |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms6Iterator` | `DungeonMapElement` | MapElement "Dungeon": renders the dungeon map element (MapRoomRenderer + RoomHistoryRenderer) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms6Iterator_2` | `BossMapElement` | MapElement "Boss": renders the boss floor element from BossMapFloor |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms6$Extension` | `MapRenderCallback` | @FunctionalInterface run(float,float,Markers<?>) callback used by the map elements (unreferenced in source) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms7_2` | `PuzzleTracker` | wire a detected room to its RoomPuzzle based on the PuzzleType from SkyblockDungeonPuzzles |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms8_2` | `ChatFilterRule` | chat filter rule: Pattern + replacement map or allowed-string set + priority, fed to the chat parsing pipeline |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms9_2` | `KingRelicTracker` | King relic coordinate tracker: HudTimer + the two EntityArmorStand texts/entities and their formatted components |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms10_2` | `RouteSegment` | DungeonRoute segment data: swap-on-locked RouteLinks + TextHologram list; RouteSection.method6() returns the current one |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms10$Data` | `TextHologram` | gson model of a route text hologram (text + Vector3d pos), @KeepName |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms11_2` | `RouteCompletion` | DungeonRoute completion data: swap-on-complete RouteLinks (single field of DungeonRoute) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.gui` | `Gui3$Type` | `ShopItemCategory` | item-shop entry category enum ITEM/DYE/ENCHANTMENT/ENRICHMENT/TOOL/EQUIPMENT/REFORGE/WATERING_CAN/SKIN/VACUUM/ACCESSORY/ABICASE; parsed from ItemShopPricesDeserializer "type" and held by ShopItem |
| `com.moonsworth.lunar.client.framework.feature.toggle` | `Calculator2Type` | `ToggleActionType` | ToggleSneakHudChild action enum FLYING/FLYING_BOOST/RIDING/DESCENDING/DISMOUNTING/SNEAKING|SPRINTING (held/toggled/vanilla) with Translatable settings text |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms$Data2` | `RouteSectionEntity` | top-level decompiler-split inner of the jar-only Holograms class: immutable pair (BridgeExtension entity, RouteSection); private ctor + method1/method2 accessors |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `Holograms$Type` | `MapRotation` | top-level decompiler-split inner enum of the jar-only Holograms class: map rotation NORTH(0,-1)/EAST(31,0)/SOUTH(0,31)/WEST(-1,0) with getX/getZ |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms` | `HologramsType2_2` | `DungeonClass` | dungeon class enum HEALER/ARCHER/TANK/MAGE/BERSERK with icon item, chat display name, colour and first-letter parsing ("DungeonClass" crash context) |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate` | `Holograms4Updater` | `DungeonPlayerTracker` | one dungeon teammate: tracks the nameplate (class/health regex), PlayerInfo achievements (Treasure Hunter), secrets, deaths, class and their map marker |
| `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers` | `Holograms4Iterator` | `DungeonRoomTracker` | one dungeon room instance: MapRoomType/RoomState, detected RoomInstance, room events, clear time, secret count, shape and tooltip lines |

## Skipped / deliberately not renamed

### 1. Meaningful digits (not obfuscation suffixes)

| class | package | why kept |
|---|---|---|
| `F3Chart` | `framework.feature.f3display.chart` | F3 = the vanilla debug key; the whole F3 display family (F3DebugEntry/Line/Writer/Renderer/Measurer/Info/ConditionalWriter, F3PieSlice) is named after it |
| `F3DebugEntry` | `framework.feature.f3display` | F3 debug screen entry base (see F3Chart row) |
| `F3DebugConditionalWriter` | `framework.feature.f3display` | F3 debug writer variant (see F3Chart row) |
| `F3DebugLine` | `framework.feature.f3display` | F3 debug line enum (see F3Chart row) |
| `F3DebugMeasurer` | `framework.feature.f3display` | F3 debug text measurer (see F3Chart row) |
| `F3DebugRenderer` | `framework.feature.f3display` | F3 debug renderer (see F3Chart row) |
| `F3DebugRendererWriter` | `framework.feature.f3display` | F3 debug renderer writer (see F3Chart row) |
| `F3DebugWriter` | `framework.feature.f3display` | F3 debug writer interface (see F3Chart row) |
| `F3DebugInfo` | `framework.feature.f3display` | F3 debug info holder (see F3Chart row) |
| `F3PieSlice` | `framework.feature.f3display` | F3 debug pie-chart slice (see F3Chart row) |
| `Crc32` | `audio.ogg` | standard CRC-32 class name (java.util.zip.CRC32), not an obfuscation suffix |
| `Vector2f` | `util` | math vector type name (2 floats) |
| `Vector2dPair` | `util` | math pair of 2D doubles |
| `Items2d` | `mod.render` | renders items in 2D; "2d" is the render mode, not a serial number |
| `Skins3d` | `mod.render` | renders skins in 3D; "3d" is the render mode |
| `CrosshairF5Migration` | `config.migration` | F5 = the perspective-toggle key; config migration for the crosshair F5 behaviour |
| `Vec3iDeserializer` | `feature.mod.fishing.click` | Gson deserializer for Vec3i (the "3" is part of the vector type name) |
| `CalculatorParser` | `feature.mod.fishing.click` | expression parser of the item-value calculator feature (legit feature name) |
| `Calculator` | `feature.mod.impl.calculator + mixin` | item-value calculator module (legit feature name) |
| `CalculatorType` | `feature.mod.impl.calculator.mixin` | calculator value-type option (legit feature name) |
| `SlayerQuestEvent` | `feature.mod.rewindhandlers` | Slayer = Hypixel SkyBlock Slayer quests, a real feature name |
| `SlayerQuestListener` | `feature.mod.holograms.fishing` | Slayer quest listener (real feature name) |
| `IchorStackTraceFilter` | `framework.crash` | Ichor = the mixin/transformer framework used by Lunar (com.moonsworth.lunar.ichor) |

### 2. Nested (indented) `Data`/`Type`/`Extension`/digit declarations — deferred

The discovery grep matches column-0 declarations only, so the pass above covers
top-level classes. The tree still has **320 nested** junk-name declarations inside
otherwise well-named files (mostly `private class Data` render/config holders), by area:

| area | nested junk decls |
|---|---|
| `mod/**` (hud 80, misc 45, render 35, combat 11, player 4) | 172 |
| `framework/**` (fishing/holograms 31+23, rewindhandlers 10, f3display 9, worldeditcui 9, ...) | 89 |
| `util/**` | 35 |
| `config/**` | 9 |
| `driver/**` | 8 |
| `network/**`, `profile/**`, `gui/**`, `translation/**` | 7 |

They are NOT in this map: they need a dedicated nested-class pass (the aware applier
supports them via `package / Inner / New / Owner$Inner` rows).

### 3. Jar-only junk classes with no source file — cannot rename from source

`Tiertagger2_2`, `Calculator2Handler`, `Potioneffects3`, `WorldeditSelectionVersion`,
`Tiertagger3`/`Tiertagger2`-era bases, `MixinCore8`/`MixinCore9`, `MixinHelper_4`,
`ThreadModuleDump9`, `Bridge7Iterator`/`Bridge7Handler`, `Lotusfish2`/`Lotusfish4`/`Lotusfish5`.
They resolve from the stale jars and references were kept working by the applier.

