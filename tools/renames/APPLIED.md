# Applied rename batches (ledger)

Every batch is compiled-checked with `tools/error_diff.py` ("no NEW failing
files" against the baseline; the tree itself is partial — see Prompt.md §0.5)
and committed. Maps are kept next to this file; the batch commit hashes are in
`git log`.

| Batch | Commit | Rows | Highlights |
|---|---|---|---|
| bridge#part1 (`classes-07`) | `add80a65` | 40 | ResourcePackBridge, MultiBufferSourceBridge, FrustumBridge, ShaderStageType, NewInstanceEmitter, InvocationEmitter |
| legacy.mixin#part1 | `add80a65` | 48 | ChunkLightingMixin, RenderGlobalEntityOutlineMixin, EntityRendererFasterLoadingMixin, BlockXrayMixin, EntityPlayerGameProfileMixin |
| highlight `classes-23` | `57e98a2b` | 30 | package is actually the EVENT SYSTEM: EventRenderEntityBase, LunarEventBus, EventRenderTooltip, EventPreAttackEntity, ... |
| highlight `classes-26` | `57e98a2b` | 25 | ApolloPacketEvent, EventRenderTabListEntry, EventKeyInput, EventRenderSlot, ... |
| replaymod.mixin (`classes-16`) | `57e98a2b` | 43 | `<Target><Purpose>V1_8Mixin` flavour names from ReplayMod ground truth |
| client.util (`classes-12`) | `8d81145c` | 45 | SourceEmitter/MethodSpec/FieldSpec (JavaPoet-like generator), Easing + ElasticInOut/BackIn etc., NotNullSerialized, Identifier |
| bridge#part2 (`classes-09`) | `8a891edd` | 38 | RenderPipelineBridge, BlocksBridge, NameTagRendererBridge, packet bridges |
| bridge#part3 (`classes-10`) | `8a891edd` | 43 | 22 `<Packet>PacketBridge` translators, BakedModelBridge, EntityRenderStateBridge, NoOp*PacketBridge |
| legacy.wrapper (`classes-15`) | `37739947` | 45 | RewindPacketBuilder family, LegacyWorldBorder, AncientDummyPlayer, AudioStreamCache |
| fishing.click (`classes-21`) | `e466d6ba` | 25 | accuracy fix: it is a **SkyBlock utility grab-bag**, not a clicker — IslandUtils, CalculatorParser, TextComponentFactory, SkyblockCalendar, EntityFinder, WidgetFactory |
| framework core (`classes-20`) | `9fb54412` | 25 | ModCategories, OptionContainer, HudComponent + group/decorators, HudSize, HudComponentValue, CommandParser, BoundArguments, ClientCommand |
| molang JIT (`classes-19`) | `9fb54412` | 25 | accuracy fix: the `fps` package is Lunar's **Molang JIT compiler** — MolangClassDefiner, VariablesMap, MolangClassBuilder, MolangBuiltinFunctions + 17 nested AST cache tokens |
| BetterMap/dungeons (`classes-bettermap`) | `5ab34e03`, `de0d2810` | 40 | accuracy fix: it is the **SkyBlock dungeon BetterMap + secret routes** stack — DungeonRoute, RouteRenderer, RouteTracker, BettermapSettings, WaterBoardSolutions (+ nested WaterBoardBlock/Material), MapRoomType, RoomTemplateDetector |
| bridge.horsestats (`classes-bridgehorsestats`) | `3a959852` | 25 | accuracy fix: **not horse stats** — a generic bridge bucket: Vec3iBridge, EnumFacingBridge, DamageSourceBridge, ChatComponentFactoryBridge, KeyBindingEntry, ... |
| ichor (`classes-ichor`) | `3a959852` | 32 | Ichor mixin/remap pipeline: TransformClass/Field/Resource/Method, ProvideRemapper, PipelineHook, IchorOptions/Stage/Loader, MixinRegistrationTask, AccessWideningHook |
| **wave 2 (2026-09-14)** | | | |
| genesis Guava (`classes-genesis`) | `227148b6` | 792 | the `genesis` module embeds a **minified/relocated Guava 29.0-jre**; matched every class to its upstream identity with an ASM hierarchy+coverage matcher and renamed to real Guava names (HashMultiset, AbstractIterator, SerializedForm, ...). 14 rows skipped (duplicate new names). |
| forge/lib deletion | `a15bdc9d` | 1102 files | deleted shaded third-party `com/moonsworth/lunar/forge/lib` (Guava + Mixin); excluded from the compile loop, classes remain in `libs/` |
| client.util#part2 (`classes-clientutil2`) | `99deb192` | 40 | ProtoConverter, MathUtils, ClipboardUtils, HeadTextureCache, RectangleQuadtree, IntRectangle, GuiResolution, EntityLookup, RomanNumeralParser, ... |
| legacy.mixin leftovers (`classes-legacymixin`) | `11e4b815` | 24 | ChunkLightingMixin, EntityRendererFasterLoadingMixin, RendererLivingEntityGlowMixin, TimerRewindMixin, ScoreboardAccessorMixin, ... |
| markers + killsounds (`classes-markers-killsounds`) | `f1eb72fb` | 44 | accuracy fix: `killsounds` is a **versioned config-migration system** (ConfigMigrator + 15 migrations); `markers` is the marker-icon registry mapping; holograms neighbours are DynamicListeners |
| rewindhandlers (`classes-rewindhandlers`) | `1184dbfb` | 24 | accuracy fix: the package is the **SkyBlock event-bus events** (DungeonEvents, TerminalEvents, CommissionEvent, SlayerQuestEvent, LocationEvent, KuudraEvent, RoomSecretEvent, ...) |
| keystrokes (`classes-keystrokes`) | `b169e903` | 45 | KeystrokeKey/KeystrokeRenderer + 16 animation renderers; the `fishing.gui` half is the SkyBlock item registry + shop-price stack (SkyblockItemRegistry, ItemShopPrices, ...) |
| lighting + mod.hud (`classes-lighting-modhud`) | `9d16c3cc` | 36 | accuracy fix: `client.lighting` is the **options/settings framework** (OptionCombiner, SettingsBuilder, OptionTreeNode, KeyBind, ...), not a lighting engine |
| mod.fishing (`classes-modfishing`) | `b0c29117` | 40 | accuracy fix: the SkyBlock utility grab-bag (commissions, chat commands, fairy souls) + Rewind replay audio/export layer + packet recorder |
| holograms + bridge (`classes-holograms-bridge`) | `758db25a` | 40 | SkyBlock DynamicListeners (SlayerQuestListener, SkyblockProfileCache, ...), generic MCP bridges, and the GeckoLib/Bedrock model format (GeoModelSerializer, ModelCube, ...) |

**Wave 2 tooling fixes** (all committed): aware applier now handles `$`-named
top-level files (`63958c66`), rewrites the `Owner` token when only `Owner.Nested`
is imported (`77b4b8d0`), and scopes a 5th-column `file` to the declaration only
so references in other files still update (`2cc4b6fa`).

| **wave 3 (2026-09-14)** | | | |
| genesis2 (`classes-genesis2`) | `6436df47` | 33 | remaining Guava leftovers (`Uninterruptibles`, `Platform`×3, package-info classes) + `Genesis4` → `Sentry` (Lunar's Sentry bootstrap) |
| bridge3 (`classes-bridge3`) | (see `git log`) | 45 | bridge targets (EntityFallingBlock, Container, NetworkManager, …), enums (TextureFormat, BufferUsage, …), annotations |
| client.util3 (`classes-clientutil3`) | (see `git log`) | 45 | `Ref` (5218 refs), `FastMath`, `RingBuffer`, `BackgroundExecutor` + util toolbox |
| highlight.fishing (`classes-highlightfishing`) | (see `git log`) | 35 | accuracy fix: the package is a **generic event-bus data bucket** — `Event<Purpose>` (EventAttack, EventFog, …) |
| highlight.mixin.fishing (`classes-highlightmixinfishing`) | (see `git log`) | 35 | same event system, fishing category (`EventTick`, `EventBlockChange`, …) |
| modmixin (`classes-modmixin`) | (see `git log`) | 41 | route-command suite (`RouteCommand`, `Route<Verb>Subcommand`) + custom GUI framework (`GuiComponent`, `GuiRenderState`, …) |
| rewindgui (`classes-rewindgui`) | (see `git log`) | 36 | ReplayMod timeline/project model + packet recorders (KeyframeRecorder, PacketRecorder, …) |
| forgemixin (`classes-forgemixin`) | (see `git log`) | 33 | Forge v1_12 mixin twins, openapi-generator client, GeckoLib cosmetic renderer |
| fov (`classes-fov`) | (see `git log`) | 44 | accuracy fix: **not FOV** — cosmetics/emote support (PhysicsPoint, EmoteGiftInfo) + ArmorStatus HUD + neighbours |

Additional tooling fix: `2cc4b6fa` (above). Two follow-ups: `HighlightImpl12`
(cluster 05) skipped — `EventEntityRemove` already declared; and `Genesis4` →
`Sentry` needed two `io.sentry.Sentry` call sites qualified with the FQN.

| **wave 4 (2026-09-14)** | | | |
| modmixin (`classes-modmixin`) | `ff111718` | 41 | route-command suite (`RouteCommand`, `Route<Verb>Subcommand`) + custom GUI framework (`GuiComponent`, `GuiRenderState`, …) |
| client.util3 (`classes-clientutil3`) | `8d6088f0` | 45 | `Ref` (5218 refs), `FastMath`, `RingBuffer`, `BackgroundExecutor` + util toolbox |
| fov (`classes-fov`) | `198b266b` | 44 | accuracy fix: **not FOV** — cosmetics/emote support (PhysicsPoint, EmoteGiftInfo) + ArmorStatus HUD + neighbours |
| forgemixin (`classes-forgemixin`) | `bf3b5e79` | 33 | Forge v1_12 mixin twins, openapi-generator client, GeckoLib cosmetic renderer |
| highlight.fishing (`classes-highlightfishing`) | `49423fad` | 35 | accuracy fix: generic **event-bus data bucket** — `Event<Purpose>` (EventAttack, EventFog, …) |
| highlight.mixin.fishing (`classes-highlightmixinfishing`) | `107715b8` | 35 | same event system, fishing category (`EventTick`, `EventBlockChange`, …; 1 row skipped, name already declared) |
| rewindgui (`classes-rewindgui`) | `be615dc3` | 36 | ReplayMod timeline/project model + packet recorders (KeyframeRecorder, PacketRecorder, …) |
| bridge3 (`classes-bridge3`) | `72c44452` | 45 | bridge targets (EntityFallingBlock, Container, NetworkManager, …), enums, annotations |
| genesis2 (`classes-genesis2`) | `6436df47` | 33 | remaining Guava leftovers + `Genesis4` → `Sentry` (Lunar's Sentry bootstrap; 2 call sites FQN-qualified) |
| networkgui (`classes-networkgui`) | `1d4af883` | 36 | accuracy fix: `network` is a **generated OpenAPI HTTP client** (`BaseGameEvent`, `GameEventApi`, …); `guiRewindhandlers` is the DynamicListener framework (`CpsListener`, `TabListListener`, …) |
| modmisc (`classes-modmisc`) | `0ba599eb` | 39 | SkyBlock mods (ArrowFrame, ProfitEntry, …), Rewind replay audio, dungeon map items, chat-message listeners, OptiFine bridge |
| client.tps fix | `087a41cb` | 4 + pkg | accuracy fix: `client.tps` was the **render pipeline**, not TPS → `client.render` (`RenderStage`, `RenderSubmission`, `RenderSubmissionManager`, `RenderStageException`) |
| markersholograms (`classes-markersholograms`) | `021d5a02` | 41 | markers emote mixins, ray-trace lib (`RayBuilder`, `FixedRay`), Ogg reader, Forge/Ichor/ReplayMod glue |
| utilgui (`classes-utilgui`) | `f7d5becb` | 41 | copy-on-write list library, fluent list builder, Mojang launcher models, localisation, cosmetics identity |
| ichorutil (`classes-ichorutil`) | `62b5319b` | 40 | Ichor toolbox (`IchorLogger`, `AsmUtils`, …), `markers` = WebOSR **Driver** UI (`Driver*`), nested render events |
| clickstruct (`classes-clickstruct`) | `f92dce29` | 32 | accuracy fix: `util.click` is the render/GPU toolchain (`RenderContextModernTransform`, …); vanilla structure names left canonical |
| clientmixins (`classes-clientmixins`) | `5c81fb75` | 38 | client/ichor mixins, Ichor generic-signature parser, Rewind core, cosmetic task system |
| modhighlight (`classes-modhighlight`) | `57a5e1a3` | 42 | SkyBlock data enums, Griffin-burrow locator, WAILA components, Emoticons/morph renderer, profile importer, trait system |
| waypoints (`classes-waypoints`) | `64b20ae7` | 6 | accuracy fixes: `colorsaturation` = Emoticons/BOBJ renderer, `waypoints` = game-IPC websocket client; 1 false positive skipped |
| bridge4 (`classes-bridge4`) | `740bb7f2` | 35 | ichor plumbing in `bridge` (coercion functors, packet-builder SPI, data-component values) |
| nameplate (`classes-nameplate`) | `4a7db932` | 39 | accuracy fix: `client.nameplate` = Apollo Transfer/Ping; colour/animation system; kin binary-mappings format |
| inventorysearch (`classes-inventorysearch`) | `166b668b` | 45 | inventory-search overlay + neighbours; `EventPickBlock` accuracy fix; raffle-task system |
| debugprofiler (`classes-debugprofiler`) | `9891dfa4` | 45 | soopy.dev frame profiler (`Profile`, `ProfileCallNode`, …), SkyBlock item registry, commands GUI, undo/redo |
| minimap (`classes-minimap`) | `a28ee98d` | 45 | minimap engine data helpers (`MapCoord`, `TerrainColumn`, `ChunkColorMap`), Rewind registries/packets |
| excavation (`classes-excavation`) | `36ae9fcd` | 44 | Fossil Excavator solver, Diana burrow helper, Bettermap/PvpInfo HUDs, GeckoLib cosmetics |
| markersgui (`classes-markersgui`) | `c446f88c` | 45 | accuracy fix: `glintcolorizer` = vendored **McLib + Blockbuster Bedrock particle engine**; markers = WebOSR Driver `@CallbackJS` providers |
| markersnameplate (`classes-markersnameplate`) | `33112130` | 45 | LComponent GUI framework callbacks (`Component*Callback`), texture/shader layer, Hoppity's Hunt, dungeon waypoints, 3D Skin Layers port |
| lightingnameplate (`classes-lightingnameplate`) | `cb9d9e6d` | 38 | options-framework trait layer, Apollo safety prompts, hosted-world hosting, Feather profile importer |
| audit12 (`classes-audit12` + package moves) | `5adda09f`, `80d90836` | 34 + 4 pkgs | first **full-package audit** batch: keystrokes/killsounds/markers/knockbacktrainer fixes; empty `.mixin` buckets folded into feature roots (package applier proven end-to-end) |
| highlightmixgui (`classes-highlightmixgui`) | `531c6f7d` | 26 | network/screen/container event category (`EventScoreboardUpdate`, `EventTabListUpdate`, `EventTitle`, `EventServerPing`, …) |
| audit01 (`classes-audit01` + package moves) | `a95ff5ee` | 13 + 5 pkgs | bridge truth fixes: `fog` = potion bridges, `glintcolorizer` = IntegratedServer, `hitbox` = statistics file, `hitcolor` = tile entities; packages moved to `bridge.potion/server/resourcepack/stats/tileentity` |
| audit07 (`classes-audit07` + package moves) | (this wave) | 23 + 4 pkgs | framework truth: `Framework` → `ModTraits`, `MixinCore` → `CommandArgumentParser`, `MixinHelper` → `CommandExecutor`, `MixinNameplate*` → command nodes, `Module/Handler` → HUD element geometry; `fps.mixin` → `molang.ast`, armorstatus `.mixin`/`.nameplate*` folded into `armorstatus[.hud]` |
| audit08 (`classes-audit08` + package moves) | (this wave) | 21 + 2 pkgs | attack-indicator provider family, `blockoutline` enum swap (`BlockOutlineMode` ↔ `BlockOverlayMode`, two-pass apply), chat/cooldowns/coordinates fixes; empty `.mixin` buckets folded into feature roots |
| audit10 (`classes-audit10` + package moves) | `5c777e6a` | 26 + 2 pkgs | F3-debug truth (`F3DebugLine/Info/Renderer/Writer`, `F3Chart` + metric charts), `gui` = OverlayMod plumbing → `overlay`, `FreelookMode`, `DirectionHudPosition`, `HeightLimitOverride/Variant`, `TextureAnimationCache`, `OverlayTextureProcessor` |
| audit09 (`classes-audit09` + package moves) | (this wave) | 18 + 3 pkgs | crosshair/debug truth: `DebugType`, `CrosshairMode`, `CrosshairShapeDrawer`, `DebugArchive`, `FpsDebugPhase`, task names from `name()` strings, soopy.dev profiler (`ProfilerEngine`, `ProfileEncoder/Uploader`); empty `.mixin` buckets folded |
| audit02 (`classes-audit02` + package moves) | (this wave) | 47 applied + 3 pkgs (3 rows reverted, see below) | Apollo layer at `client` root (19 `<Id>ApolloHandler`, `ApolloApprovedServer` from toString, `LunarLogger`, `ApolloTypeRegistry`, `RenderScope`); `lighting` → scoreboard, `slayer` → optifine, `horsestats.mixin` → particle; `DifficultyBridge`, `BiomeCategory`, `WorldInfoBridge`, `HoverEventBridge`, ... |
| audit03 (`classes-audit03` + package moves) | (this wave) | 20 + 4 pkgs (1 pkg move reverted, see below) | override/intercept truth (`OverrideSource`, `SettingOverride/Intercept`), prompt system (`PromptAction`, `RunCommandPrompt`, `ActivePrompt`), translation truth (`CachedReplacement` from exception string, `SharedInfo`, `ClientLanguage`), notifications (`Notification/Type`, `AnchorFunction`, `EmptyScreen`) |
| audit04 (`classes-audit04` + package moves) | (this wave) | 15 + 7 pkgs (1 pkg move reverted, see below) | loading truth (`LoadingStage`), `CompetitiveDisconnectScreen`, font cache (`CachedTextMesh`/`TextRenderPass`/`TextRenderCache`), hostedworld (`HostedWorldHost/Player`, entry renderer), cosmetics (`Cosmetic/Slot`, `BodywearCosmetic`), external links (`ExternalLinkRegistry`, `ModMenuCompat`) |
| audit05 (`classes-audit05`) | `cdb1ab4c` | 9, no moves | external link flavors (`MainMenu/Recording/Hologram/TurboExternalLink`), `StyngrSong`, `TextureProcessor`, skins (`SavedSkin`, `SkinType`, `SkinLoadException`) |
| audit11 (`classes-audit11`) | `d8013d62` | 4, no moves | `BedwarsBedModelFactory` (colored-bed models), `InventorySlotUtils`, `BlockVariantAliases`, `DamagedItemEntry` |
| audit06 (`classes-audit06` + package moves) | (this wave) | 71 + 2 pkgs | chat/emote/render sort of the `fov` bucket (`ChatMessage`, `MessageEmbed`, `SystemEvent` family, `Emote`, `TransformStack`, ...), Molang JIT truth (`MolangBuiltin` + 30 `MolangMath<Op>` from the registry, `MolangVariablePath`); `fps` → `molang`, outfit conditions merged into `fov.mixin`; alerts (`AlertCard`) + blog posts (`BlogPost`) |

| **wave 5 (2026-09-15)** | | | |
| misclazy (`classes-misclazy`) | (this wave) | 14 (6 nested) | Molang function-provider family (`MolangCheckBiome/Category`, `MolangCheckSnowyBiome`, `MolangLightLevel`, `MolangSmoothQuery`, `MolangAnimationState`), armorstatus enums (`DurabilityPosition`, `HotbarPosition`), render-mod nested types (`EntityMarkerType`, `PlayerMarkerType`, `WishingCompassVerdict`, `SpiritLeapDefaultView`, `StoragePreviewRule`, `VanillaModelCacheKey`) |
| coordsnameplate (`classes-coordsnameplate`) | (this wave) | 12 (3 nested) | `rewindhandlers/coordinates` = timeline-editing state (`ExportSettingsSection`, `SelectedKeyframe`, `TimelineBridgePayloads` + keyframe-move DTOs); `rewindhandlersNameplate` = **FFmpeg export pipeline** (`FFmpegDownloader`, `FFmpegRenderer`, `VideoEncoder`, `VideoCodec`, `EncoderProbe`, `LoopbackAudioRecorder`) |
| tiertagger (`classes-tiertagger`) | (this wave) | 7 | `TierLookupHandler`, `TierBadgeFormatter`, `TierPlayerProfile`, `TierPlacement`, `TierMetadataRegistry`, `TierGameMode`, `TierProviderData` |
| rewindhighlight (`classes-rewindhighlight`) | (this wave) | 5 (1 nested) | replay-timeline model: `MediaPool`, `ReplayTimeline`, `SegmentLinkManager`, `ThumbnailManager`, `HighlightAdapter` (`Highlight$Data`) |
| inactive (`classes-inactive`) | (this wave) | 9 | hitbox stat-HUD row layout (`TextHudRow`, `SpacerHudRow`, `HorizontalHudRow`, `ItemIconHudRow`, `HudRowAlignment`); GeckoLib cosmetic engine (`CosmeticDefinitionMapper`, `AnimationBuilderDeserializer`, `OffsetDateTimeDeserializer`); `WanderPositionResolver` |

| **wave 6 (2026-09-15)** | | | |
| junk-drawer drain (`moves-misc`) | (this wave) | 301 moves + 3 deferred | per-class moves: `calculator` → `translation`, `click` → `gui`/`notification`, `feature` → `cosmetics`, `framework/feature/pkg` → `cosmetics.skin`, `client.pkg` → `server`, `keystrokes` → `server`, `killsounds` → `config.migration`, `lighting` → `option`, `markers` → `driver` (core/gui/holograms/mixin…), `lotusfish` → `transform` + `audio.ogg`, `horsestats` → `profile[.importer]`; `BridgeExtension` stays in `bridge.horsestats` (per precedent). `Horsestats20Extension2`/`HorsestatsType$Type{3}` renames deferred again (stale-jar coupling). All 22 empty left-behind dirs removed. `error_diff`: no change vs baseline. |

| **bucket pass (2026-09-15)** | | | |
| buckets A (`pkgs-bucketsA`) | (this wave) | 3 pkgs / 242 files | `client.highlight` → `client.event` (it *is* the event bus); `client.guiRewindhandlers` → `client.framework.listener` (dynamic-listener framework, not GUI); `client.lightoverlay` → `client.option.trait` (TraitType/trait registry of the options framework, **not** the light-overlay feature). Deleted 4 `$VF` decompile-failure stubs (`genesis/MixinHelper{13_2,15,262,_3}.java`) — comment-only files, classes resolve from the jar. |
| buckets B (`pkgs-bucketsB`) | (this wave) | 26 pkgs / 691 files | root buckets: `config/{option,override}`, `cosmetics/{emote,inactive,holograms,molang}`, `render/{texture,shader,font,particle}`, `network/{server,ipc,mumble,hostedworld}`, `chat`, `account/skin`, `framework/{loading,transform}`, `audio/music`, `gui/{blog,external,prompt,notification}`, `util/{memory,performance}`, `replay` (was `framework/feature/rewind`). |
| buckets C1 (`moves-bucketsC1`) | (this wave) | 30 classes | wrong-folder/name fixes: `nameplate` → `network/transfer` + `framework/build` (`LunarBuildData`); `hitcolor.FogHandler` → `framework.combat.PlayerCombatState`; `hitbox` → `framework.hud` (`HudRowLayout` + rows); `inventorymod` → `framework.crash` (Sentry `CrashReporter`, `IchorStackTraceFilter`, ...); `itemcounter.Itemcounter` → `network.safety.MaliciousListFetcher`; `freelook.Highlight3Handler` → `network.server.PluginMessageHandler`; `itemtracker.TypeAdapter` → `util.JsonDateAdapter`; `itemphysics/mobsize/heightlimit/knockbacktrainer` stubs folded into `framework.feature.*`. |
| buckets D (`moves-bucketsD`) | (this wave) | 11 classes | `client.mixin` keeps only real mixins: websocket classes → `network/websocket` (`AssetServerClient`, `ConnectionState`, `ReconnectBackoff`, `WebSocketClientHandler`, `WebSocketRpcChannel`), `YggdrasilAuthServiceFactory` → `account`, `FriendChatMessage` → `chat`, `EntityRendererType` → `network.friend.FriendStatus`; `client.rewindhandlers` → `network/apollo` + `chat` + `util.ItemTagUtils`. |
| bridge names (`moves-bridgenames` + `pkgs-bridgenames` + `moves-bridgefix`) | (this wave) | 2 class moves + 2 pkg moves + 4 reverted | `bridge.itemcounter` → `bridge.world` (chunk/world/biome/map bridges, not item counters), `bridge.horsestats` → `bridge.minecraft` (generic vanilla/MCP bridge bucket); `Itemcounter$Type2` → `AxisDirection`, `Itemcounter4_2` → `WorldDirectoryBridge`. `Horsestats20Extension2`, `HorsestatsType$Type{,3}`, `Vec3iBridge` stay in `bridge.horsestats` (jar signature coupling — the third failed attempt, see audit02). |

| **naming wave (2026-09-15)** | | | |
| namesA (`moves-namesA`) | `e920d1d7` | 54 classes | `util.click` → `render.*` (GPU toolchain: `RenderPipeline`, `PipelinePass`, `SkinRenderCache`…), `util.chest` → `util.raytrace` (`Ray`/`RaySegment`/`Raycaster`/`RaycastContext`), `util.colorsaturation` → `cosmetics.emote` (BOBJ mesh), `util.alert` → `render.{texture,shader}` + `cosmetics` (`PersistentTexture`, `ShaderDefinition`, `CloakTextureSlicer`…), `util.highlight` → `util` file helpers, `util.nameplate` → `ExtraCodecs`, `util.rewindhandlers` → `render.color`, `util.{gui,mixin,holograms}` → `util.collection`. |
| bridge56/bridge2/namesB/C/D | `13ee78fa` | 272 renames | 57+44 bridge interfaces (`MinecraftBridge`, `ItemBridge`, `BlockStateBridge`, `PlayerControllerBridge`, `EntityRenderDispatcherBridge`…); 86 mass-named `Nameplate*`/`Gui2Extension`/`GuiExtension` classes; 38 `Highlight*`/`JsonDeserializerIterator` (event classes + deserializers); 46 `Holograms`/`Rewindhandlers`/`Gui`/`GuiIterator`/`Gui2Loader` (`DungeonRoom`, `ReplayClock`, `ItemValueResponse`, `TimelineElementRegistry`…). `Bridge2_35` reverted (jar coupling). |
| namesE (final wave) | `b90c1c98` | 77 classes | particle engine leftovers (`RandomFunction` avoiding `java.util.Random`), `Waila`/`WailaRow`, `MinimapMap`/`BoneTransform`, `GlaciteTunnelGraph`, `NpcLocations`, `MinionXpData`, `SkillLevelCalculator`, `Vec3iDeserializer`, `RequiresDynamicListener`, `GuiRenderer`, `InventoryButtonsEditor`, `QuickplayOverlay`, … |
| options/settings core (`classes-51`) | `69c557fa9` | 45 | `Lighting*` was the **mis-homed settings/options framework**: Abstract*Option + Integer/Boolean/Double/Long/Short/Float/Byte options, Toggle/Color/Label/Sound/Text/Button/Set/MultiSelect options, keybind options + slider/keybind/character builders; 8 rescued duplicates flagged for dedupe |
| options builders (`classes-52`) | `69c557fa9` | 13 | MapOption, DropdownOption, MultiNumberOption, NamedDropdownOption, CyclingDropdownOption, EnumOption, TriStateOption, DynamicDropdownOption, ListOption, AutoTextHotkeyOption, CategorizedSettingsBuilder, SettingsParent, ByteRangeOptionBuilder |
| framework core (`classes-59`) | `69c557fa9` | 22 | AbstractFeature, ModEnabledState/ModDetails/ModSearchIndex/ModSupport, FeatureToggleKeybind, HudElementBase/HudRowElement/TitledHudElement/HudRenderContext/HudConditionSet, Text/Padded/Scrollable/TwoColumn Hud components, HudAnchor, GreedyString/String/Integer/Player/Duration argument parsers; 23 stale-jar duplicates flagged for dedupe |

**Restructure pass** (requested by the user, after naming): tracked in
`tools/renames/PLAN-restructure.md` — one home per subsystem, split the flat
`client.util` root, rescue jar-only leftovers so the deferred bridge renames
can land, and keep the launch path working batch by batch.

**Mover shadow guard (`apply_class_moves.py`, this wave):** the same-package bare rewrite
now checks single-import shadows, so a moved `TypeAdapter` landing in `client.util` no
longer rewrites `com.google.gson.TypeAdapter` references. `error_diff` clean after each
bucket batch.

**Package-applier left-behind imports (`apply_package_renames.py`, this wave):** a moved
file that references a class left behind in the old package (jar-only stubs like
`Lightoverlay6`, `GuiRewindhandlersHandler2`) now gets an explicit import of the old
FQN, mirroring the class mover. Without it every package move failed on bare refs to
jar-only siblings.

**Mover root-cause fix (`apply_class_moves.py`, this wave):** the bare-name
pass used `info['pkg']` *after* the declaration pass had already rewritten it
to the new package, so (a) files moved in the same batch lost their
same-package sibling rewrites (`Horsestats$Data` stayed instead of becoming
`ProfileData`), and (b) bare names from the *old* package could be resolved
against a colliding row from another old package (`Nameplate` in
`lighting.nameplate.mixin` got rewritten to `OptionTraits` from
`lighting.nameplate` instead of `NumberRule`). Now uses `orig_pkg`, and the
left-behind import pass skips declarations owned by the moved file itself
(killed the bogus self-imports like `...markers.mixin.gui.CachedJsonProvider`)
and resolves names renamed in the same map via a post-move index. Same-path
`git mv` rows are skipped instead of printing fatal noise.

**Audit02 deferral:** 3 rows (`BlockPosBridge`, `DirectionAxis`, `AxisPlane`)
were applied then reverted — stale-jar signature coupling (`Bridge2.method4`
is jar-only and returns jar-type `Horsestats20Extension2`). Re-apply after
`Bridge2` rescue. Same fix class as the 2 baseline failures. |

**Package-applier fix** (`43c10cdc`, re-applied this wave): the applier used to
rewrite *every* `old.package` prefix reference, which corrupted imports of
classes that were never in the tree (quarantined, resolving via the stale
`lunar-renamed-classes.jar`) — e.g. `attackindicator.mixin.Attackindicator`.
It now rewrites references **only for classes that actually moved**. Found via
6 NEW failing files after the audit07/08 moves; fixed by the applier change
plus rescuing 3 quarantined files
(`armorstatus/mixin/Gui2Extension{,2,3}` → `armorstatus/`, single obfuscated
method resolves via the stale jar exactly like the already-compiling
`DurabilityDisplayMode`).

**Restructure phase tooling** (`8368dc67`): `tools/package_inventory.py`
walks all 371 packages into 47 audit clusters (fixes the lazy-inventory blind
spot); `tools/apply_package_renames.py` is the package-move applier.

Known follow-ups: collision rows deferred in `classes-07.md` /
`classes-15.md`; `Highlight`/`HighlightImpl` base-class renames (`Event`,
`CancellableEvent`) are listed in `classes-23.md`/`classes-26.md`. Remaining
genesis matcher inputs live in `/tmp/opencode/genesis/` (regenerable).

**fov/fog junk-drawer split** (`95caac5e`, map `tools/renames/moves-fovfog.tsv`,
notes `moves-fovfog.md`): 72 per-class moves via new
`tools/apply_class_moves.py` — `client.fov` (chat/conversation + emote/gecko +
shader under an FOV-mod name) and `client.fog` (account/profile + framework
handlers + misc) split into `conversation`, `emote`, `shader`, `account`,
`framework`, `alert`, `blog`, `config`, `inventorymod`, `lighting`,
`performance`, `texture`, `skin`, `music`. Two mover bugs found via 22 NEW
failing files and fixed forward + in the tool: (1) declaration rename used
`count=1`, leaving constructors at old names (8 files); the mover now
whole-file self-renames and updates `info['pkg']`; (2) moved files kept
bare refs to siblings that stayed behind (often jar-only lazy classes like
`fov.Fov_3`/`Fov3_2`/`Fov3` in `lunar-renamed-classes.jar`) — the mover now
adds `oldpkg` imports, following sibling moves for the new home
(`fov.VertexBuilder` -> `emote.VertexBuilder`). `error_diff`: no change vs
baseline (2 pre-existing bridge files).

**mods renamer batch 1** (`41f828e1f`, `tools/renames/classes-mods001.tsv`, 32 rows, 134
files touched): removed the mechanical `Child`/`ChildMod`/`Mod`/`Manager`
suffixes from the `client.mod` feature tree and named each class after the
feature it implements. Feature ids are untouched — they are Lunar's persisted
lang/config keys (including the `_CHILD`/`_CHILD_HUD` ones). Highlights:
CoordinatesChildHudMod→CoordinatesHudEntry, F3ModuleChildMod→F3DisplayModule,
ItemCounterElementChildMod→ItemCounterElement, StopwatchChildMod→StopwatchCounter,
TimerChildMod→StopwatchTimer, CrosshairChildMod→CrosshairStyle,
HeightLimitChildMod→HeightLimitProfile, ParticleChildMod→ParticleStyle,
ParticleChangerBloodChild→ParticleChangerBlood, SaturationHudChild→SaturationHud,
ArmorstatusBarsChild→ArmorstatusBars, ArmorstatusProtectionChild→ArmorstatusProtection,
TotemAnimationOverlayChild→OverlayTotemAnimation, MarkerData→ApolloMarkerData,
ServerBorderManager→ServerBorderRegistry, StaffModsManager→StaffModsSettings,
HypixelMod→HypixelMods, InventoryMod→InventoryMods, KillSoundChildMod→KillSoundEntry,
Replaymod→ReplayMod, ReplaymodRecordingIndicatorChild→ReplayModRecordingIndicator,
PvpInfo{Health,Melee,Projectile}Child→PvpInfo{Health,Melee,Projectile}Stats,
TotemCounterHudChild→TotemCounterHud, ToggleSneakHudChild→ToggleSneakHud,
HypixelBedwars{Stats,ResourceCounter,UpgradeDisplay,TeamDisplay}Child and
HypixelBedwarsTimersChildHud→HypixelBedwars{Stats,ResourceCounter,UpgradeDisplay,
TeamDisplay,Timers}, RewindRecordingIndicatorChild→RewindRecordingIndicator.
Maven error-set diff vs the pre-batch tree: 54 vs 54 failing files, identical
sets — no new failures. Non-compiled stale twins still present (see batch 2):
`Coordinates`, `Waila`, `MinimapMod`, `Rewind`+`RewindHandlers2..5`,
`FoodValues`/`FoodUtils`, `HypixelBedwarsUpgradeDisplay$…$Data`.

**mods renamer batch 2** (`500383e84`, `tools/renames/classes-mods002.tsv`, 7 rows, 34 files
touched): last mechanical suffixes outside the stale twins —
ChatCommandAliasesChild→ChatCommandAliases,
{Direct,OffHeap}MemoryDebugChildMod→{Direct,OffHeap}MemoryDebug,
MemoryDebugChildMod→MemoryDebug, FragmentDebugChildMod→FragmentDebug,
TurboGroupRebuildsChildHudMod→TurboGroupRebuilds, WaypointManager→WaypointStore
(it is the persisted waypoint store, not a manager). Maven error-set diff again
54 vs 54 with no new failures.
