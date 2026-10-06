# Wave 6 — misc/client-structure audit (loose root, network, config, leftovers)

Scope audited (per the wave-6 README + coordinator's brief):

* `client/*.java` loose files (78)
* `client/network/**` (35)
* `client/config/**` (114, settings framework + option + override + migration)
* leftovers: `alert`, `animations`, `coordinates`, `feature`, `mixin`,
  `pkg`, `lightoverlay`, `lighting`, `gui`, `guiRewindhandlers`,
  `calculator`, `chest`, `fishing`, `freelook`, `heightlimit`, `hitbox`,
  `hitcolor`, `holograms`, `horsestats`, `inventorymod`, `itemcounter`,
  `itemphysics`, `itemtracker`, `keystrokes`, `killsounds`,
  `knockbacktrainer`, `lotusfish`, `memory`, `mobsize`, `mumble`,
  `nameplate`, `profile`, `rewindhandlers`, `tps`, `translation`,
  `waypoints`
* alt-manager-adjacent: `client/account/**`; `com.moonsworth.lunar.altmanager`
  itself is outside `client/**` and untouched.

Deliverable: `moves-misc.tsv` — **62 rows**, validated against the current tree
(every `Old` resolves, every destination is free, no duplicate destinations,
and zero old/destination overlap with the other wave-6 maps that appeared while
this audit ran). This wave's client tree is full of **rescued stale twins**:
the rescue batches re-added classes under their old FQNs that already exist
(renamed) at their canonical location. Those cannot be "moved" (the applier
would skip them as `target already declared`), so they are catalogued below as
**dedupe rows** (old FQN → canonical FQN). Moves only cover classes that are
genuinely misplaced and have no canonical twin.

## Cross-map reconciliation (checked after the other maps landed)

| subsystem | who owns the moves | note |
|---|---|---|
| `client/gui/**` → `client/ui/**` (29) | `moves-cosmetics.tsv` | identical destinations; dropped from this map |
| `client/translation/**` → `client/chat/translation/**` (8) | `moves-cosmetics.tsv` | identical destinations; dropped |
| `client/lighting/**` → `client/config/option/**` (80 + `RewindhandlersType`) | `moves-render.tsv` | identical destinations (theirs names `RewindhandlersType` → `SettingsPage` too); dropped |
| `client/lightoverlay/**` → `client/config/option/trait/**` (18) | `moves-render.tsv` | same canonical names for `TraitContainer`/`TraitMutator`/`TraitRegistry`; theirs names the other two `MutableTraitContainer` + `DebugTraitContainer` (better evidence: `[DEBUG_TRAIT]` logging) — this map defers to theirs |

Everything below (`moves-misc.tsv`) is disjoint from the framework/mods/render/
cosmetics/replay/util maps; a scripted cross-map check reports 0 shared `Old`
and 0 shared destinations.

## Moves at a glance

| destination | rows | what |
|---|---|---|
| `client/network/apollo` | 43 | 19 named + 14 unnamed Apollo module handlers, `ApolloModuleHandler` base + listener entry, `OptionsImpl`, `ApolloTypeRegistry`, `ApolloModuleManager` (was `FogHandler2`), `ApolloModSettingsBridge` (was `MixinHelper_3`), `ApolloButtonRenderer` (was `MixinHelper`) + button model |
| `client/chest` → `client/render` | 4 | GL info/buffer helpers + NanoSVG renderer (render junk drawer) |
| `client/` root → misc | 15 | `HorizontalAlignment` + `NotificationLink` → ui, `Autotexthotkey` → network/websocket (`PromotionMessageHandler`), `Badge`/`OwnedBadge` → account, `EventHandlerEntry` → event, `CosmeticRenderFilter` + `RenderScope` → cosmetics, `MarkerData` + builder → mod/render/markers, `LunarLogger`/`Slayer` → util, `Client` → framework, `MaliciousListFetcher` → network/server, `AlertExtension` → config/override |

Renames carried in the map (all destinations were empty, so no collisions):

* `Highlight3Iterator_3` → `ApolloModuleHandler`; `Highlight3Iterator$Data2` →
  `ApolloModuleHandler$ListenerEntry`; `Highlight3Iterator_2` →
  `ModSettingApolloHandler`; the 14 handlers with no named twin get their
  module name from `super("<id>", ...)`: `Glint`, `StaffMod`, `Hologram`,
  `Border`, `Chat`, `Limb`, `Nametag`, `ServerLink`, `Waypoint`, `Stopwatch`,
  `Cosmetic`, `Inventory`, `Team`, `Marker` + `ApolloHandler`.
* `FogHandler2` (root) → `ApolloModuleManager` (registers every handler, holds
  module state, returned by `Client.framework().method84()`).
* `MixinHelper` → `ApolloButtonRenderer`; `MixinHelper_3` →
  `ApolloModSettingsBridge`; `MixinHelper_2` → `MarkerData`;
  `MixinHelper$Data22` → `MarkerData$Builder`.
* `MixinExtra` → `CosmeticRenderFilter`; `Autotexthotkey` →
  `PromotionMessageHandler`; `Gui2Handler` → `OwnedBadge`.
* `chest.Chest{2,3,4}` → `render.{GlBufferFunctions,GlScratchBuffers,SvgTextureRenderer}`,
  `chest.Chest` → `render.OpenGlInfo`.

## Why these destinations

* **Apollo handlers** (`client/network/apollo`). Every root `*ApolloHandler` /
  `Highlight3Iterator` / `Highlight3Iterator_2` is an `ApolloModuleHandler`
  subclass whose `super("<module id>", "<Title>")` names the Apollo module it
  serves; `FogHandler2` instantiates them all (`method9(...)`). `OptionsImpl`
  implements the Apollo options store, `ApolloTypeRegistry` builds the
  protobuf `TypeRegistry`, `ApolloPacketUtils` already lives here. PLAN lists
  `apollo` under `network/`.
* **`chest` → `render`.** `Chest` (OpenGL version dump), `Chest2`
  (GL45C/ARB direct-state buffer functions), `Chest3` (pooled scratch
  IntBuffers), `Chest4` (NanoSVG/NanoVG texture renderer). No twins exist in
  `client/render`; the render bucket is their only sensible home.
* **`Client` → `framework`.** Main singleton/bootstrap (handshake, websocket,
  client managers); the target table puts bootstrap in `framework/`.
* **`lighting` / `lightoverlay` / `gui` / `translation`** are handled exactly
  as described in this audit but by `moves-render.tsv` / `moves-cosmetics.tsv`
  (see the reconciliation table); this map only keeps `alert.AlertExtension`
  → `config/override` from the options-framework leftovers.
* **`network/safety.MaliciousListFetcher` → `network/server`.** Fetches the
  `/game/safety` malicious server/URL lists; `safety/` is a 1-class package
  and PLAN's network list has no `safety`.
* **`config/**` in place.** `option/`, `option/trait/`, `override/` and the
  43 `migration/` classes are already in the target bucket shape; the only
  non-config class found there is `override/AlertCard` (alert-feature card) —
  left for the feature auditor, since moving feature models out of config is
  the mods auditor's call (see deferred).

## Dedupe table (stale/rescued twins — delete the left column, keep the right)

All of these are **not** in the moves map: the canonical class already exists,
so the applier would skip a move and leave the twin behind. They need the
dedupe pass (delete + repoint references). `~` marks near-identical (fuzzy
similarity or identical constants), `=` marks exact/behavioural duplicate.

### root loose files → root Apollo handlers / canonical
| old | canonical (delete old) |
|---|---|
| `client.MixinRewindhandlers` | `network.apollo.ApolloTypeRegistry` = (diff is only the class name + ctor) |
| `client.MixinExtraType` | `cosmetics.RenderScope` = (same 7 scope constants + map) |
| `client.Slayer` | `util.LunarLogger` = (identical `[LC]` log4j facade) |
| `client.Gui2Handler2` | `account.Badge` = (same id/name/description/resource/resourceUi/animated JSON) |
| `client.Highlight3Iterator` | `client.TntCountdownApolloHandler` (tnt_countdown) |
| `Highlight3Iterator3/5/6/7/9/10/12/13/14/18/20/21/23/25/27/29/30/31` | the same-module `*ApolloHandler` (server_rule, saturation, beam, rich_presence, tebex, auto_text_hotkey, entity, pay_now, title, notification, glow, combat, height_limit, cooldown, colored_fire, vignette, nick_hider, packet_enrichment) |

### `client/alert` → `config/override` / `config/option`
| old | canonical |
|---|---|
| `alert.Alert` | `config.override.ChildContainer` = (same `getChildren/method1/2/3`) |
| `alert.mixin.Alert` | `config.override.SettingOverride` = (same value/predicate/override state) |
| `alert.mixin.Alert2` | `config.override.SettingIntercept` = (same 6 signatures) |
| `alert.mixin.Alert2Handler` | `config.override.InterceptHandler` = |
| `alert.mixin.AlertType` | `config.override.OverrideSource` = (SERVER/CLIENT_INTERNAL/CLIENT_REMOTE/CLIENT_CRITERIA/CLIENT_OVERRIDE + isClient) |
| (`alert.AlertExtension` is **moved** to `config/override` — shared base of `config.option.AlertExtension` and `framework.AlertExtension`) |

### `client/animations` → `gui`/`ui` prompt framework
| old | canonical |
|---|---|
| `animations.Animations` | `gui.prompt.ActivePrompt` = (same nullable `PromptAction` holder) |
| `animations.mixin.Animations` | `gui.prompt.PromptAction` = (same abstract `method1(boolean)/method2()/method3()/method4()`) |
| `animations.mixin.AnimationsImpl{,2..6}` | the `gui.prompt` action impls (`BlockedActionPrompt`, `MaliciousServerPrompt`, `MaliciousUrlPrompt`, `OpenUrlPrompt`, `RunCommandPrompt`, + generic yes/no) ~ (`saveChoice/rememberChoice` strings) |

### `client/calculator` → `translation`
| old | canonical |
|---|---|
| `calculator.Calculator` | `translation.SharedTranslations` ~0.91 |
| `calculator.CalculatorType` | `translation.ClientLanguage` =1.0 (locales nl_NL/fr_FR/…) |
| `calculator.Calculator2` | `translation.Translatable` (getLanguagePath + delegating `method1`) |
| `calculator.CalculatorType2` | `translation.TranslationVariable` (id/dynamic/replacement values()) |
| `calculator.mixin.Calculator` | `translation.CachedReplacement` ~0.71 |
| `calculator.mixin.CalculatorHandler{,2,3}` | `translation.{ConstantReplacement,StringArrayReplacement,SupplierReplacement}` (verify each) |

### `client/coordinates` → `network/hostedworld`
| old | canonical |
|---|---|
| `coordinates.Coordinates` | `network.hostedworld.HostedWorldPlayer` (uuid+username model) |
| `coordinates.Coordinates2` | `network.hostedworld.HostedWorldSettingsHolder` (wraps settings) |
| `coordinates.Coordinates3` | `network.hostedworld.HostedWorldRelay` ~0.87 |
| `coordinates.Coordinates4` | `network.hostedworld.HostedWorldHost` (verify) |
| `coordinates.Coordinates5` | `network.hostedworld.LocalHostedWorldSettings` ~0.86 |
| `coordinates.FogIterator` | `network.hostedworld.HostedWorldSettingsHolder` (publish/settings; verify) |
| `coordinates.Gui2Handler` | `network.hostedworld.HostedWorldPlayer` ~0.69 |
| `coordinates.mixin.Coordinates` | `network.hostedworld.HostedWorldEntryRenderer` ~0.85 |
| `coordinates.mixin.CoordinatesThread` | `network.hostedworld.HostedWorldRefreshThread` |

### `client/feature` → `cosmetics`
| old | canonical |
|---|---|
| `feature.Module` | `cosmetics.Cosmetic` ~0.82 |
| `feature.Module2` | `cosmetics.CosmeticSettings` ~0.96 (clothCloak/hatHeightOffset/…) |
| `feature.ModuleType2` | `cosmetics.CosmeticCategory` =1.0 (`lunar/cosmetics/indexes/*.json`) |
| `feature.ModuleType` | `cosmetics.emote.CosmeticType` (verify) |
| `feature.ModuleType3` / `ModuleType3_2` | cosmetics model loader (`cosmetics/models/%s/%s/%s`, `.obj/.webp`) — find canonical |
| `feature.Module_2` | util Gson factory (`null,null,_,_,_,_->fail`) — find canonical |
| `feature.mixin.MixinHelper` | cosmetics skin-layer mixin (`head|low_body`) — find canonical |

### `client/fishing`, `holograms`, `horsestats`, `inventorymod`, `keystrokes`, `killsounds`, `lotusfish`, `memory`, `nameplate`, `pkg`, `rewindhandlers`, `tps`, `waypoints`
| old | canonical |
|---|---|
| `fishing.Fishing` | `gui.external.ExternalLinkRegistry` ~0.79 (target `ui/external` after the cosmetics map) |
| `fishing.Fishing2` | external-link variant (`[Ichor/External Link]`) — verify |
| `fishing.Fishing2Extension` (×5 packages: `fishing.mixin`, `.holograms`, `.highlight`, `.gui.mixin`, `.rewindhandlers`) | one marker interface duplicated 5× |
| `holograms.Holograms3` | `cosmetics.holograms.MorphTimeline` ~0.78 |
| `holograms.Holograms6` | `cosmetics.holograms.EmoteAnimator` ~0.65 |
| `holograms.Gui2Handler` | `cosmetics.holograms.EmoteGiftProvider` ~0.60 |
| `holograms.Holograms{,2,4,5,7,8,8Handler,Type}` | `cosmetics.holograms.*` (verify) |
| `horsestats.Horsestats` | `profile.ModProfile` |
| `horsestats.Horsestats$Data` | `profile.ProfileData` |
| `horsestats.Horsestats$Data2` | `profile.HudPosition` =1.0 |
| `horsestats.Horsestats2_2` | `profile.ProfileColor` ~0.83 |
| `horsestats.Horsestats2` | `profile.importer.ExternalProfileLocator` ~0.82 |
| `horsestats.Horsestats3` | `profile.importer.BadlionProfileConverter` ~0.87 |
| `horsestats.Horsestats4` | `profile.importer.BadlionProfileImporter` (data.json/info.json) |
| `horsestats.Horsestats5` | `profile.importer.FeatherProfileImporter` (verify) |
| `horsestats.Horsestats_2` | `profile.importer.BadlionProfileConfig` ~0.81 |
| `horsestats.FogIterator` | `profile.ModProfileManager` |
| `horsestats.mixin.Horsestats{,2,3,4,5}` | `profile.importer.{FeatherProfileConfig,FeatherProfileConverter,FeatherConvertedProfile,…}` (verify) |
| `inventorymod.Inventorymod3` | `framework.crash.ExceptionSanitizer` ~0.82 |
| `inventorymod.JsonSerializer` | `framework.crash.ThrowableJsonSerializer` ~0.77 |
| `inventorymod.Inventorymod{,2,Error}` | `framework.crash.{CrashReporter,IchorStackTraceFilter}` (buckets C) |
| `keystrokes.Keystrokes` | `network.server.PluginChannelRegistry` ~0.78 |
| `keystrokes.Keystrokes3` | `network.server.ServerIconEntry` =1.0 |
| `keystrokes.Highlight3Iterator` | `network.server.ServerBrandWatcher` (EventServerChange/Brand) |
| `keystrokes.Keystrokes2` | `network.server.PinnedServer` (verify) |
| `keystrokes.KeystrokesType` | `network.server` server-type enum (verify) |
| `killsounds.Killsounds` | `config.migration.ConfigIdResolver` =1.0 |
| `killsounds.Killsounds15Impl{,2..10}`, `15Iterator{,2..6}`, `2_2`, `3_2`, `4_2`, `5`, `6..15`, `Handler`, `_2` | the `config.migration.*` twins (`CosmeticIdMigration`, `OverlayEnabledMigration`, `TimeChangerMigration`, …; all fuzzy 0.73–1.0) |
| `killsounds.mixin.Killsounds` | `config.migration.VanillaOptionsFile` ~0.91 |
| `killsounds.mixin.Killsounds6Impl` | `config.migration.TypeCoercionMigration` ~0.90 |
| `killsounds.mixin.{Killsounds2..6}` | `config.migration.{OptionMigration,FovOptionMigration,KeyCodeOptionMigration,KeyNameOptionMigration,MigratedOptionReader}` (verify each) |
| `lotusfish.Ichor2Impl` | `framework.transform.LwjglRelocationTransform` ~0.71 |
| `lotusfish.{Ichor2Handler,Ichor2Handler2,Ichor2Handler22,Ichor2Iterator,Ichor2Iterator2,Ichor5Iterator,Ichor6Impl,FunctionProvider,MixinInternalTask,Lotusfish}` | `framework.transform.*` / `ichor` transform twins (verify) |
| `memory.Memory` | `util.memory.Memory` |
| `memory.MemoryTask` | `util.memory.MemoryTask` ~0.76 |
| `nameplate.Nameplate` | `framework.build.LunarBuildData` ~0.89 |
| `nameplate.Nameplate2` | `network.transfer.PingServerData` ~0.64 (`, timedOut=|, endTime=|serverDataBridge=`) |
| `nameplate.Nameplate_2` | `network.transfer.TransferSrvResolver` ~0.66 (`mc_transfer_accept_from`) |
| `nameplate.Highlight3Iterator` | `network.transfer.TransferHandler` ~0.67 (`transfer`) |
| `nameplate.FogHandler2` | dead stub (UUID map, `MC_VERSION == 0` no-op handlers) — delete |
| `pkg.Pkg2` | `network.server.ServerBlocklist` ~0.91 |
| `pkg.Pkg3` | `network.server.UnresolvedServerAddress` ~0.80 |
| `pkg.Pkg5` | `network.server.ServerSrvLookup` ~0.77 |
| `pkg.Pkg`, `pkg.Pkg4`, `pkg.Pkg6` | `network.server.{ResolvedServerAddress,ServerAddressResolver,ServerAddressPipeline}` (verify) |
| `rewindhandlers.Rewindhandlers` | `util.ItemTagUtils` (`lunar` compound tag helpers) |
| `rewindhandlers.Rewindhandlers2` | `chat.ChatActionHandler` (chat-link/browser open, same code) |
| `rewindhandlers.Rewindhandlers3` | `network.apollo.ApolloPacketUtils` ~0.76 (`Slayer` vs `LunarLogger`, `ChestType` vs `NotificationType`) |
| `tps.Tps` | `render.RenderSubmission` |
| `tps.TpsType` | `render.RenderStage` |
| `tps.TpsException` | `render.RenderStageException` |
| `tps.FogIterator` | `render.RenderSubmissionManager` |
| `waypoints.RpcChannelImpl` | `network.ipc.RpcChannelImpl` ~0.74 |
| `waypoints.WebSocketClientIterator` | `network.ipc.WebSocketClientIterator` |

### `client/lightoverlay` (the rest)
Canonical names below live in `config/option/trait`; the moves map
(`moves-render.tsv`) already contains all of these rows with the same
destinations, so this table is documentation only:

| old | canonical |
|---|---|
| `lightoverlay.Lightoverlay` | `trait.Lightoverlay` = (same registry interface; rename the survivor to `TraitRegistry`/`TraitRegistryImpl` later) |
| `lightoverlay.Lightoverlay2` | `trait.TraitReader` ~0.83 |
| `lightoverlay.Lightoverlay2Extension` | `trait.TraitHost` (same `method2()` container + stream helpers) |
| `lightoverlay.Lightoverlay3Extension` | `trait.MutableTraitHost` (same set/listener/method3 contract) |
| `lightoverlay.Lightoverlay4` | `trait.TraitSnapshot` ~0.92 |
| `lightoverlay.Lightoverlay5` | `trait.TraitListener` |
| `lightoverlay.Lightoverlay7` | `trait.TraitDebugFormatter` |
| `lightoverlay.Lightoverlay8` | `trait.Trait` |
| `lightoverlay.Lightoverlay9` | `trait.TraitType` |
| `lightoverlay.Lightoverlay9Impl` | `trait.BuilderTraitType` |
| `lightoverlay.LightoverlayException` | `trait.LightoverlayException` ~0.86 |
| `lightoverlay.LightoverlayHandler` | `trait.LightoverlayHandler` |
| `lightoverlay.LightoverlayType` | `trait.LightoverlayType` |
| `lightoverlay.Lightoverlay3Extension2` | `trait.MutableTraitContainer` (moves-render) |
| `lightoverlay.Lightoverlay3Extension22` | `trait.DebugTraitContainer` (moves-render; logs `[DEBUG_TRAIT]`) |

### `client/lighting` subpackages → `config/option` (moves in `moves-render.tsv`)
`lighting/nameplate/**` (17) is a whole stale copy of `config/option` classes:

| old | canonical |
|---|---|
| `lighting.nameplate.Nameplate` | `config.option.OptionTraits` (same 15 trait keys in the same order) |
| `lighting.nameplate.Nameplate2` | `config.option.OptionUpdateListeners` (verify) |
| `lighting.nameplate.Nameplate2Task` | `config.option.ListenerSet` ~0.60 |
| `lighting.nameplate.Nameplate3` | `config.option.OptionFeatureLink` (verify) |
| `lighting.nameplate.Nameplate3Handler` | `config.option.FeatureLinkRef` ~0.85 |
| `lighting.nameplate.Nameplate4` | `config.option.OptionDisplay` ~0.71 |
| `lighting.nameplate.Nameplate4Task` | `config.option.DisplaySpec` ~0.60 |
| `lighting.nameplate.GuiExtension` | `config.option.SettingsNode` (verify) |
| `lighting.nameplate.GuiExtension2` | `config.option.OptionJsonProvider` ~0.63 |
| `lighting.nameplate.AlertExtension` | `config.option.AlertExtension` ~0.55 |
| `lighting.nameplate.AlertExtension2` | `config.option.OptionChildren` =1.0 |
| `lighting.nameplate.Alert2Handler` | `config.override.InterceptHandler` ~0.71 |
| `lighting.nameplate.ThreadModuleDump43Extension` | `config.option.SettingsSectionBuilder` ~0.60 |
| `lighting.nameplate.ThreadModuleDump43Extension2` | `config.option.SettingsSection` (verify) |
| `lighting.nameplate.ThreadModuleDump43Extension22` | `config.option.SettingsSectionImpl` ~0.82 |
| `lighting.nameplate.mixin.Nameplate` | `config.option.NumberRule` (verify) |
| `lighting.nameplate.mixin.NameplateHandler` | `config.option.AbstractNumberRule` ~0.85 |
| `lighting.nameplate.mixin.NameplateImpl{,2..6}` | `config.option.{Double,Float,Long,Short,Integer,Byte}NumberRange` ~0.85 |
| `lighting.mixin.LightingExtension44` | `config.option.CrosshairDrawOption` =1.0 |
| `lighting.OptionSection` | `config.option.OptionCategory` = (GENERAL/PERFORMANCE/CONTROLS/FEATURE/REWIND) |

### `lighting` root: parallel-generation candidates (do NOT delete blindly)
`DefaultedBoolean` ~ `config.option.BooleanOption` (false/default/true) and
`OverrideTriState` ~ `config.option.TriState` (disabled/forceOn/forceOff) are
constants-identical; the rest look like a parallel generation of the settings
tree (`OptionTreeMapper`/`SettingsTreeMapper`, `OptionHierarchyNode`/
`OptionTreeNode`, `OptionGraphNode`/`SettingsNode`, `OptionTreePruner`/
`PruningOptionBaker`, `CompositeOption`/`OptionCombiner`). Both sides are
referenced, so the merged `config/option` will hold both until a behavioural
dedupe confirms which generation wins. `moves-render.tsv` keeps them all.

### `client/mixin` strays
| old | canonical |
|---|---|
| `mixin.EntityRenderer2` | `account.YggdrasilAuthServiceFactory` ~0.86 |
| `mixin.EntityRenderer3` | `network.websocket.ReconnectBackoff` ~0.91 |
| `mixin.EntityRenderer4` | `network.websocket.AssetServerClient` ~0.70 |
| `mixin.EntityRenderer5` | chat line model (`Memory`, `HH:mm:ss`) — verify against `chat.ChatMessage` |
| `mixin.EntityRenderer6` | friend-request model (`uuid/username/rank/badge/timeSent`) — verify against `network.friend` / `chat.FriendChatMessage` |
| `mixin.EntityRendererType` | `network.friend.FriendStatus` =1.0 |
| `mixin.EntityRendererType2` | `network.websocket.ConnectionState` (READY/"disconnected"/"ready") |
| `mixin.RpcChannelImpl` | `network.websocket.WebSocketRpcChannel` ~0.74 |
| `mixin.WebSocketClientHandler` | `network.websocket.WebSocketClientHandler` ~0.81 |
| `mixin.EntityRenderer` | auth token refresher (`AuthUtil` + `exp`), only referenced by `AssetServerClient`; dedupe/verify then move to `account` |

The ~25 real vanilla mixins in `client/mixin` (`EntityRenderer{,Overlay,Reach}Mixin*`,
`TextureMap*Mixin*`, `Gui*Mixin`, `RenderGlobalMixin`, `KeyBindingMixin`, …) stay
put per the target (`client/mixin/` = shared mixins); no rows.

### `client/guiRewindhandlers` → `framework/listener` (buckets-A destination)
| old | canonical |
|---|---|
| `guiRewindhandlers.GuiRewindhandlers_2` | `framework.listener.LambdaFactory` =1.0 |
| `guiRewindhandlers.GuiRewindhandlers3` | `framework.listener.DependencyTracker` ~0.65 |
| `guiRewindhandlers.rewindhandlers.Rewindhandlers` | `framework.listener.rewindhandlers.LocrawResponse` =1.0 |
| `guiRewindhandlers.rewindhandlers.Rewindhandlers2` | `framework.listener.rewindhandlers.HypixelLocation` ~0.66 |
| `guiRewindhandlers.rewindhandlers.Rewindhandlers3` | `framework.listener.rewindhandlers.PartyState` =1.0 |
| remaining `GuiRewindhandlersHandler*`/`mixin/*`/`nameplate/*`/`holograms/*` | the `framework.listener.*` dynamic-listener classes (verify one-by-one) |

## Deferred / needs verification

* **Dedupe execution order.** The dedupe table assumes the canonical class is
  authoritative. Where the canonical is itself junk-named (`trait.Lightoverlay`,
  `trait.LightoverlayHandler`, `trait.LightoverlayType`, `trait.LightoverlayException`),
  a later rename pass should fix the survivor (suggested: `TraitRegistry`,
  `TraitRegistryImpl`, `TraitCondition` (enum), `TraitException`).
* **`gui.IllegalStateException`** is mapped by `moves-cosmetics.tsv` to
  `ui.IllegalStateException` (move-only). It shadows `java.lang.IllegalStateException`;
  suggest renaming to `GuiStateException` in a naming pass.
* **`feature.ModuleType3`/`ModuleType3_2`/`Module_2`/`ModuleType` and
  `feature.mixin.MixinHelper`** need a cosmetics-side check; they are cosmetic
  model-loader / skin-cube code but no exact twin was confirmed.
* **`fishing`, `holograms`, `lotusfish`, `killsounds.mixin`, `horsestats.mixin`**
  have partial matches only; every row above marked "(verify)" must be
  diff-checked before deleting the twin.
* **`config/override/AlertCard`** (`icons/alerts/`, COUNTDOWN regex) is an
  alerts-feature card living in the config/override bucket; the alerts feature
  owner should claim it.
* **`animations/mixin.AnimationsImpl{,2..6}`** map 1:1 onto the named
  `gui.prompt` impls but the pairing is fuzzy — confirm before deleting.
* `client/highlight` is the event bus (events auditor), `click`/`fog`/`fov`/
  `fps`/`glintcolorizer`/`inactive`/`markers` are the junk-drawer auditor's
  (junkA) big packages, `driver` is the WebOSR subsystem, `framework`/`util`/
  `event`/`replay`/`render`/`cosmetics`/`mod` are other auditors' packages —
  all intentionally **not** touched here, except as move destinations above.

## Validation

* 62/62 rows pass the existence/collision scan (old class declared in the old
  package, destination file/name free, no duplicate rows).
* Cross-map check after the other wave-6 maps appeared: 0 shared `Old` and 0
  shared destination `New` between `moves-misc.tsv` and
  `moves-{framework,mods,render,cosmetics,replay,util}.tsv`. The four
  overlapping subsystems (gui, translation, lighting, lightoverlay) were
  dropped from this map and are documented in the reconciliation table.
* Recommended apply order: `apply_class_moves.py --map tools/renames/wave6/moves-misc.tsv`
  (dry-run first), then `error_diff`; the dedupe table is a separate follow-up
  batch (delete + reference repoint), not appliable by the class mover.
