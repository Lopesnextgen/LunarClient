# moves-namesD — mass-named class renames (batch D names)

Owner: namesD subagent. Map: `tools/renames/moves-namesD.tsv` (46 rows).
Applied by `tools/apply_class_moves.py --map tools/renames/moves-namesD.tsv`.
Dry run: `rows=46 skipped=0 files_moved=46 files_touched=194` (dry run, no src writes).

Every old class was one of the bulk-rename duplicates listed in `/tmp/opencode/massnames.txt`;
names below are role-derived from the class body, its call sites, its supertypes and, where
available, the runtime jar (`libs/lunar-renamed-classes.jar`, javap) and JSON/serialized keys.

Two rows from the original 48 were dropped because the concurrent `namesA` batch
(`e920d1d7 renamer: name the util.* junk drawers`) already fixed them:
`util.rewindhandlers.Rewindhandlers` → `render.color.ColorMutator` and
`util.gui.Gui` → `util.collection.ReadOnlyCollection`. They are intentionally not
re-renamed here.

## Holograms + HologramsType

| # | Old FQN | New name | Package | Evidence |
|---|---------|----------|---------|----------|
| 1 | `com.moonsworth.lunar.client.cosmetics.holograms.Holograms` | **EmoteMorphAnimator** | same | Facade over MorphTracker + EmoteAnimator: update(EmoteController, entity) / render(entity, matrix, bone, partialTick) / isActive; no supertype and zero source refs (legacy emote+morph model driver) |
| 2 | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.holograms.Holograms` | **MolangResourceModel** | same | GeckoLib model extending Holograms2Iterator; implements mixin.holograms.Holograms; three MolangResourceProvider textures (primary, secondary, third defaults to lunar:animations/empty_anim.json), getModelName()=primary; cast target in AnimationControllerDefinition.method2 |
| 3 | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.holograms.mixin.Holograms` | **ModelTextureProvider** | same | Interface Horsestats14 method1(E, Evaluator): resolves the model texture/resource for an animatable given the molang evaluator; implemented by Holograms2Iterator + MolangResourceModel |
| 4 | `com.moonsworth.lunar.client.driver.core.holograms.Holograms` | **HologramModelBridge** | same | @CallbackJS bridge for hologram models: addModel/updateModel/removeModel/setHighlightingEnabled/forceHighlightCosmetic/selectCosmetic/playEmote/stopEmote/setHoveredModel; owned by HologramRenderer.method14() |
| 5 | `com.moonsworth.lunar.client.driver.core.holograms.mixin.Holograms` | **HologramEntry** | same | JSON model entry @SerializedName id/name/type/metadata; elements of EmoteHologram "cosmetics" array (method22), looked up by id() |
| 6 | `com.moonsworth.lunar.client.driver.holograms.Holograms` | **CosmeticHighlightRenderer** | same | Offscreen framebuffer + "Lunar Cosmetic Highlight" depth shader compositor for the selected cosmetic model; tracks hovered/selected HoverModel and sends model:hover/model:select; owned by HologramRenderer.method13() |
| 7 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms` | **DungeonRoom** | same | @KeepName room definition loaded from dungeon/rooms.json: hashes/ids/communityName/blcName/blcRotOffset/roofHeight/secretCount/secrets/MapRoomType/RoomShape; referenced by Module, Route* subcommands, RoomInstance |
| 8 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin.Holograms` | **KuudraPearlWaypoint** | same | Kuudra "pearl" entry parsed by JsonDeserializerIterator from pos/waypoints/second arrays; KuudraWaypoints maps pearl name -> this; SkyblockKuudraWaypoints draws the pearl trajectory from method2/method3 |
| 9 | `com.moonsworth.lunar.client.framework.feature.mod.holograms.Holograms` | **EntityChangeListener** | same | Package-private entity add/remove/update + clear sink implemented by ParentTHandler/ParentTImpl/ClassFilteredEntityQuery/IterableEntityTracker |
| 10 | `com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.Holograms` | **BurrowTrailPredictor** | same | Collects trail particle points and fits a bezier curve to predict the burrow block (field5 Vector3i + timestamp); used by BurrowTrailLocator.method5/method3 |
| 11 | `com.moonsworth.lunar.client.replay.holograms.Holograms` | **TimelineElementRegistry** | same | Registers replay-editor timeline element factories by type ("camera","speed","cameraShake","cameraFov","sounds","decode","text","image","audio","packs","shader","chromaKeying","worldRendering","uiRendering","entityOverrides") plus mod-provided elements; sibling of SegmentRegistry/TrackRegistry |
| 12 | `com.moonsworth.lunar.client.replay.rewindhandlers.holograms.Holograms` | **PlaybackStateProvider** | same | GuiIterator feeding the rewind editor playhead/paused/dragging/ruleOfThirds/quarters/previewMode/rendering/quickView/panelX/panelY/progress properties |
| 13 | `com.moonsworth.lunar.client.cosmetics.holograms.HologramsType` | **MorphRenderType** | same | PARTICLE/BLOCK tag returned by MorphRenderer.method11(); Nameplate buckets morph renderers by it to order the two passes |
| 14 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType` | **BettermapVariant** | same | PRIMARY/SECONDARY/SPIRIT_LEAP with optionPrefix ("primary"/"secondary"/"spiritLeap"); selects the BettermapSettings option prefix and per-HUD defaults (BettermapPrimary/Secondary, SpiritLeapOverlay) |
| 15 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType` | **DetectedRoomShape** | same | Runtime room-shape result of Holograms4Iterator.method12 (1x1..2x2/L/1x3/1x4) plus display labels Trap/Puzzle/General/???; used by RoomDetectionAction to detect single-cell rooms; distinct from rooms.json RoomShape |

## Rewindhandlers

| # | Old FQN | New name | Package | Evidence |
|---|---------|----------|---------|----------|
| 16 | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.rewindhandlers.mixin.Rewindhandlers` | **BoneHierarchyNode** | same | Bedrock bone tree node: children HashMap<String, node> + raw bone Rewindhandlers4_2; built/consumed by BoneHierarchyBuilder |
| 17 | `com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers.Rewindhandlers` | **BoneHierarchySerializer** | same | Interface building the animatable skeleton (Rewindhandlers2_2) from a BoneHierarchyBuilder and one node into an IBoneSerializer; implemented by the name-keyed registry RewindhandlersIterator |
| 18 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.Rewindhandlers` | **SeaCreature** | same | Sea creature definition (key id, name, spawnMessage, ItemRarity, Set<RewindhandlersType> conditions); held/mapped by SeaCreatureRegistry, looked up by DoubleHookListener + SkyblockSeaCreatureMessages |
| 19 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers` | **RoomStateHistory** | same | Per-room MapRoomType + WorldPosition bounds + List<RoomStateChange> with timestamp seek (method4/method5) used by the dungeon map (Holograms2_5) and RoomTemplateDetector |
| 20 | `com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.Rewindhandlers` | **ChatMessageParser** | same | Parses Hypixel/Skyblock chat lines into its nested Extension types (IslandVisitorChatMessage, Data, PartyChatMessage, GuildChatMessage, GuildOfficerChatMessage, DirectMessageReceived/Sent, CoopChatMessage); entry point Rewindhandlers.method1 called by ChatMessageListener; nested type registry used by fishing/Gui2Extension chat channels |
| 21 | `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers` | **SkyblockProfileEvents** | same | Container for the profile events SkyblockProfileIdEvent / SkyblockProfileLoadEvent / SkyblockProfileChangeEvent consumed by ProfileIdListener/SkyblockProfileCache/SkillXpListener/SackCountListener |
| 22 | `com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.mixin.Rewindhandlers` | **EventCropPlaced** | same | CropType event fired by CropTracker on EventBlockPlace when a crop is planted; consumed by SkyblockFarmingHud |
| 23 | `com.moonsworth.lunar.client.framework.listener.rewindhandlers.Rewindhandlers` | **LocrawResponse** | same | Gson model of the /locraw JSON (server/gametype/mode/map/lobbyname) plus the static empty default; converted into HypixelLocation by HypixelLocationListener |
| 24 | `com.moonsworth.lunar.client.replay.rewindhandlers.Rewindhandlers` | **ReplayClock** | same | Replay playback clock: speed / freeze options, simulated time, partialTick, pause/step/skip and progress; RewindHandlers.method41(); TimerRewindMixin uses getTime()*1_000_000 as the system time while replaying |
| 25 | `com.moonsworth.lunar.client.replay.rewindhandlers.mixin.Rewindhandlers` | **RewindCameraController** | same | Abstract per-frame camera controller (method1(partialTicks) + EventMouseScroll/EventMouseMove/EventRewindFrame hooks) driven by RewindHandlers3Updater; subclasses RewindhandlersImpl (free camera) and CameraRewindHandler (fixed to player) |

## Gui

| # | Old FQN | New name | Package | Evidence |
|---|---------|----------|---------|----------|
| 26 | `com.moonsworth.lunar.client.cosmetics.inactive.gui.Gui` | **AnimationSelector** | same | Interface @Nullable GuiHandler method1(RenderContext): picks the animation to play for the current context; implemented by GuiHandler (always plays itself) and ConditionalAnimationSelector (molang conditions) |
| 27 | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.Gui` | **AnimationDefinition** | same | Gson animation model: "name", "states" (GuiImpl[]), "default_transition_ticks" (10); used as the "default_anim"/"animation" field in Gui2Impl and AnimationStateConfig |
| 28 | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.mixin.Gui` | **LayeredAnimationController** | same | AnimationControllerDefinition registered as "layered": builds one AnimationControllerImpl("controller"+i) per AnimationBuilder in "anims" and plays them via method2 |
| 29 | `com.moonsworth.lunar.client.driver.core.gui.Gui` | **DriverDataProvider** | same | Interface JsonElement method128(): data half of DriverGuiExtension (JsonProvider provides the other half); defaults supplied by markers.mixin.gui.GuiIterator.Extension |
| 30 | `com.moonsworth.lunar.client.driver.core.gui.mixin.Gui` | **MarkersBridge** | same | DriverGuiExtension registered as route "markers"; @CallbackJS("mark") marks the clicked position as a Markers2.Type and closes the screen |
| 31 | `com.moonsworth.lunar.client.driver.gui.Gui` | **ModsBridge** | same | DriverGuiExtension registered as route "mods"; method15() returns the mods list GUI from FogLoader3.method12() |
| 32 | `com.moonsworth.lunar.client.event.mixin.gui.Gui` | **EventBridgePayload** | same | Unused event-package data holder: no supertype, single BridgeExtension field + getter, zero references in the tree and in the runtime jars; payload-derived best-effort name (low confidence) |
| 33 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui` | **ItemValueResponse** | same | Item value lookup result: success/value/Type(NO_OBTAINER\|OBTAINER_FAILED) with Builder (nested Data); toString literally "ItemValueResponse.ItemValueResponseBuilder(...)"; returned by all ItemValueParser implementations |
| 34 | `com.moonsworth.lunar.client.framework.feature.mod.gui.Gui` | **SkyblockInventoryButtonsMovementGui** | same | Drag/place editor screen for the Skyblock inventory buttons (lang path ".skyblockInventoryButtonsMovement", keybind mouse1 hold addButtonsToRegion etc.); displayed by SkyblockInventoryButtons.method226 |
| 35 | `com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.Gui` | **DungeonWaypointHudSettings** | same | HUD settings panel (MixinCore9Base) for dungeon waypoints: preset/render mode/box mode/when/through-walls/offset/size/colors, lang keys dungeonWaypointHud* |
| 36 | `com.moonsworth.lunar.client.replay.gui.Gui` | **RewindPaths** | same | Static rewind directory/path constants: .record, .packs, rewinds/<version>, .thumbnails, projects, videos, screenshots, ffmpeg path and the 2^16 ZSTD threshold; referenced by all replay record/read/export code |
| 37 | `com.moonsworth.lunar.client.replay.rewindhandlers.Gui` | **RewindPackListProvider** | same | DriverGuiExtension registered as route "rewindPacks": serialises the resourcepacks folder tree plus built-in packs into {name,path,type} JSON for the rewind editor |

## GuiIterator

| # | Old FQN | New name | Package | Evidence |
|---|---------|----------|---------|----------|
| 38 | `com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.GuiIterator` | **KuudraKeyItemValueParser** | same | ItemValueParser matching "^([A-Za-z]+ )?Kuudra Key$" and summing the per-tier key value (Basic/Hot/...) with the Barbarian/Mage faction bonus from the skyblock profile |
| 39 | `com.moonsworth.lunar.client.replay.rewindhandlers.GuiIterator` | **RewindListProvider** | same | Async rewind library scanner ("lunar-rewind-list-provider-thread"): walks rewinds/projects/record dirs, parses project.json and .rewind metadata into the entries JSON (minecraftVersion/duration/compatible/type/mods/thumbnails/locations/date); currently unreferenced |
| 40 | `com.moonsworth.lunar.client.replay.rewindhandlers.coordinates.GuiIterator` | **RewindPropertyProvider** | same | Abstract base of the rewind-editor property providers (Chest, Click, Colorsaturation, MediaExporter, EntityContextMenu, ExportSettingsPanel, EffectsPanel, InputTimelinePanel, holograms) adding abstract method1(RewindHandlers) and self-registration into the provider list |
| 41 | `com.moonsworth.lunar.client.replay.rewindhandlers.fishing.GuiIterator` | **CreateProjectBridge** | same | DriverGuiExtension registered as route "rewindCreateProject": video resolution/orientation/framerate and audio type options, @CallbackJS init/updateValue/validate/rename, method2() builds the RewindhandlersNameplate recording settings |

## Gui2Loader

| # | Old FQN | New name | Package | Evidence |
|---|---------|----------|---------|----------|
| 42 | `com.moonsworth.lunar.client.framework.feature.waypoints.Gui2Loader` | **BadlionWaypointImporter** | same | WaypointImporter for Badlion: reads BLClient-Mod-Profiles zips (data.json) and decodes BLCWP: base64/compressed waypoint blobs |
| 43 | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader` | **WaypointImporter** | same | Abstract waypoint importer base (implements JsonProvider): method2() source entries, method3()/method4() decode to Waypoint, shared parse helpers and provide() name/icon; decoders list used by WaypointImportJsApi |
| 44 | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin.holograms.Gui2Loader` | **XaeroWaypointImporter** | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin` | WaypointImporter for Xaero's Minimap: walks xaero/minimap/<world>/dim%N/waypoints.txt |
| 45 | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin.nameplate.Gui2Loader` | **SkytilsWaypointImporter** | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin` | WaypointImporter for Skytils: decompresses the <Skytils-Waypoint-Data>(V1) payload and reads categories/waypoints JSON |
| 46 | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin.rewindhandlers.Gui2Loader` | **VoxelMapWaypointImporter** | `com.moonsworth.lunar.client.framework.feature.waypoints.mixin` | WaypointImporter for VoxelMap: parses voxelmap/*.points name/x/y/z/dimensions lines and applies red/green/blue/alpha colours |

## Verification

1. `python3 tools/apply_class_moves.py --map tools/renames/moves-namesD.tsv` →
   `rows=46 skipped=0 files_moved=46 files_touched=194` (dry run, no src writes).
2. Name uniqueness: every new simple name was checked against every top-level and nested
   declaration in `src/main/java` (0 collisions) and against the class names in
   `libs/lunar-renamed-classes.jar` (0 collisions).
3. Move safety: the three mover rows live in single-file junk packages
   (`waypoints.mixin.holograms|nameplate|rewindhandlers`) and have zero references, so no
   unmoved same-package file can lose its import.
4. Scratch simulation: the map was applied with `--apply` to a copy of `src` under
   `/tmp/opencode/applysim1` (git moves replayed by hand). Result: 0 old FQNs left, 0 old
   files left, all new files declare the right package/class, and 0 same-package files
   still reference an old name. Every file that previously imported an old FQN now imports
   the new one.

## Judgement calls / low-confidence rows

* `event.mixin.gui.Gui` → `EventBridgePayload`: the class has no supertype, a single
  `BridgeExtension` field plus getter, and zero references in source or the runtime jars.
  Only the shape is known, so the name states the shape (an event-package bridge payload)
  rather than guessing a firer. Rename it again if a firer is ever recovered.
* `replay.rewindhandlers.GuiIterator` → `RewindListProvider`: unreferenced, but the worker
  thread is literally named `lunar-rewind-list-provider-thread` and it scans `.rewind` /
  `project.json` entries, so the role is unambiguous from the body.
* `cosmetics.inactive.mixin.holograms.Holograms` → `MolangResourceModel` and its interface
  `…mixin.Holograms` → `ModelTextureProvider`: the model extends the GeckoLib
  `Holograms2Iterator`, and each `method1/2/3(entity, Evaluator)` delegates to one of three
  `MolangResourceProvider`s returning a resource location; the primary provider also backs
  `getModelName()`.
* `framework.feature.mod.holograms.Holograms` → `EntityChangeListener`: the interface is the
  add/remove/update/clear sink implemented by `ParentTHandler` and its query classes.
* `framework.feature.mod.gui.mixin.Gui` → `DungeonWaypointHudSettings`: its only consumer was
  the (since moved/renamed) dungeon-waypoints mod; the panel is identified by its
  `dungeonWaypointHud*` language keys and the `DungeonWaypoints` methods it calls.

## Deliberately not renamed/moved

* Sibling junk-named classes in the same packages are other batches' scope (e.g.
  `Holograms2*`/`Holograms3Impl*` in `…fishing.holograms`, `Rewindhandlers2_*` in
  `replay.rewindhandlers`, `Bridge7Iterator*` in `framework.feature.mod.gui`).
* The 43 same-package rows were kept in their current packages: those packages are the real
  subsystem homes (`driver.holograms`, `driver.core.holograms`, `replay.holograms`,
  `framework.*`, `cosmetics.*`) and moving a single class out would split the subsystem
  while its siblings stay behind.
* The only package moves are the three junk `waypoints.mixin.<topic>` importers, because those
  packages each contain exactly that one file and the package names describe an unrelated
  feature.
