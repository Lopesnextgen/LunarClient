# Wave 6 — `replay` / `driver` / leftovers structure audit

Map: `tools/renames/wave6/moves-replay.tsv` (applied by `tools/apply_class_moves.py`).

Dry run: `rows=427 skipped=0 files_moved=427 files_touched=619` — every row applies cleanly, no target collision, no missing source.

Scope: `client/replay/**` (245), `client/guiRewindhandlers/**` (37), `client/driver/**` (104), `client/replaymod/**` + `com/moonsworth/lunar/replaymod/**` (143), `client/feature/**` (8), `client/pkg/**` (6), `client/rewindhandlers/**` (3) = **546 classes**.

## What the evidence says (and where the brief was wrong)

1. **`replay` is the Rewind replay editor/engine** — confirmed: `client/replay/**` is the renamed twin of `client/framework/feature/rewind/**` (ReplayMod-style project → timeline → export/GUI pipeline; see `classes-rewindgui`, `classes-rewindhighlight`, `classes-coordsnameplate`, `classes-modfishing`, `classes-clientmixins`).  The map splits it into `project/ timeline/ network/ recording/ audio/ export/ render/ gui/`.
2. **`guiRewindhandlers` is NOT replay.**  It is the **dynamic-listener framework** (`GuiRewindhandlers` = ref-counted listener interface, `GuiRewindhandlersHandler2` = listener base, `Annotation3` = `@TriggeredBy`, plus CPS/biome/scoreboard/tab-list listeners; see `classes-networkgui`, `classes-fov`, `classes-debugprofiler`, `classes-minimap`).  Task `d6a452378` ("bucket batch A") already moved+renamed most of it into `client/framework/listener/**`; rescue batch 7 then re-added the pre-move originals.  Only three classes have **no** twin in `framework/listener` and are mapped there: `GuiRewindhandlersHandler2 → DynamicListener`, `holograms/GuiRewindhandlers2Extension → TrackedValue`, `…2Extension2 → TrackedValueImpl`.
3. **`driver` is the WebOSR/Ultralight driver stack** — confirmed (`DriverBridge`, `WebOsrNative`, `UltralightConfigFactory`, the `@CallbackJS` screen providers; see `classes-ichorutil`, `classes-markersgui`, `classes-lightingnameplate`).  The junk sub-package names it inherited (`core/gui/mixin`, `core/highlight`, `core/holograms`, `core/nameplate`, `core/rewindhandlers`, `nameplate/mixin`) are flattened originals, so the map re-homes by role: `bridge/` (JS/JSON providers), `component/` (LComponent callbacks), `hologram/` (marker + cosmetic preview holograms), `waypoint/` (waypoints/minimap).
4. **`feature`, `pkg`, `rewindhandlers` are leftovers of already-finished moves** — `feature/* → client/cosmetics/*`, `pkg/* → client/network/server/*`, `rewindhandlers/* → client/util|client/chat|client/network/apollo`; all three packages are stale re-rescues (see the deletion list).
5. **`replaymod` is Lunar's ReplayMod integration layer**: 124 `@Mixin` classes that patch the bundled `com.replaymod.*`/jGui classes plus the Ichor mixin-loader glue for them.  They move under the replay subsystem as `client/replay/replaymod/**` (the mixin target is a third-party jar class, so this is as close to "next to the target" as source can get).

## Headline finding: rescue batch 7 re-created whole already-moved packages

`git show --name-status 7adc91305` (rescue batch 7, *after* `d6a452378` and `811b292d4`) re-added files that earlier waves had already moved+renamed.  The tree therefore holds **two live copies** of several subsystems; the map classifies the canonical copy, and the stale copies are listed below for deletion (they are NOT in the map — the applier refuses a target that is already declared, and `New==Old` would leave duplicate names in one package).

| stale package (delete) | canonical package | note |
|---|---|---|
| `client/framework/feature/rewind/**` (258 files, old names) | `client/replay/**` (245) | the whole Rewind engine; both trees are referenced (304 vs 261 files) |
| `client/guiRewindhandlers/**` (34 of 37) | `client/framework/listener/**` | bucket batch A moved+renamed; batch 7 re-added |
| `client/feature/**` (5 of 8) | `client/cosmetics/**` | `Module*` → `Cosmetic*` |
| `client/pkg/**` (6) | `client/network/server/**` | `Pkg*` → `ServerAddress*` |
| `client/rewindhandlers/**` (3) | `client/util` `client/chat` `client/network/apollo` | `ItemTagUtils` / `ChatActionHandler` / `ApolloPacketUtils` |
| `client/markers/mixin/gui/mixin/*Bridge` (5) | `client/driver/bridge/**` | out of scope for this map, but same class of duplicate (Cosmetics/Sprays/Emotes/BlogPosts/BugReport bridge) |

The stale copies are *not* byte-identical: they still reference the pre-move packages (`client.highlight.*`, `framework.feature.rewind.*`, …), which is why the tree still compiles with both.  Deleting them requires repointing the ~200 referring files to the canonical names — do it package by package with `error_diff` after each, exactly like the naming waves.

## New layout produced by the map (427 rows, 21 renames)

| destination | classes | holds |
|---|---|---|
| `com.moonsworth.lunar.client.replay.project` | 13 | project file model + persistence + replay-archive IO + compression |
| `com.moonsworth.lunar.client.replay.timeline` | 57 | timeline document, tracks, segments, keyframe properties, property tree, undo/redo, media pool, thumbnails, registries |
| `com.moonsworth.lunar.client.replay.network` | 54 | the ReplayPacket protocol + registry + ByteBuf codec |
| `com.moonsworth.lunar.client.replay.recording` | 36 | recorder capture tasks, recorder event adapters, replay data handler/clock |
| `com.moonsworth.lunar.client.replay.audio` | 10 | OpenAL audio streams, waveform, music tracks, audio options |
| `com.moonsworth.lunar.client.replay.export` | 16 | ffmpeg pipeline (renderer/downloader/encoder probe), video + export option enums, export settings providers |
| `com.moonsworth.lunar.client.replay.render` | 23 | frame-buffer pools/capture + replay camera + render/GUI event handlers + view context |
| `com.moonsworth.lunar.client.replay.gui` | 33 | editor WebOSR providers/panels/JS bridges, selections, captured replay contexts |
| `com.moonsworth.lunar.client.replay.replaymod/**` | 96 | ReplayMod integration mixins + Ichor loader glue (from `com.moonsworth.lunar.replaymod`) |
| `com.moonsworth.lunar.client.driver` | 6 | WebOSR driver core (framework types, nativess, registries, contexts, component model) |
| `com.moonsworth.lunar.client.driver.bridge` | 45 | WebOSR JS bridge providers, JSON/data providers, store models |
| `com.moonsworth.lunar.client.driver.component` | 9 | LComponent model + input callbacks |
| `com.moonsworth.lunar.client.driver.hologram` | 16 | marker/cosmetic-preview hologram model + renderers |
| `com.moonsworth.lunar.client.driver.waypoint` | 7 | waypoint + minimap driver contexts and JS APIs |
| `com.moonsworth.lunar.client.cosmetics` | 3 | the 3 cosmetics leftovers with no twin yet |
| `com.moonsworth.lunar.client.framework.listener` | 3 | the 3 listener-framework classes with no twin yet |

Classes already in the right place produce no row: 21 driver root classes (`DriverBridge`, `DriverComponent`, `WebOsrNative`, `DriverScreen`, …), and `replay/gui/{AlertImpl,GuiType,ProgressListener}`.

## Renames in this map (medium-confidence names flagged)

| old | new | reason |
|---|---|---|
| `com.moonsworth.lunar.client.replay.RewindImpl` | `ImageSegment` | type()=="image" (sibling of `TextSegment`) |
| `com.moonsworth.lunar.client.replay.gui.GuiImpl` | `EffectTrack` | `Track` subtype with type()=="effect" |
| `com.moonsworth.lunar.client.replay.highlight.LinkedHashMapImpl` | `PropertyMap` | `String→PropertyGroup` layer index (the generic base keeps `LinkedHashMapImpl`) |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.RewindhandlersNameplateCore` | `RecorderConstants` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.RewindhandlersNameplateCoreHandler` | `ContainerCapture` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator` | `EntityPairingCapture` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.RewindhandlersNameplateCoreType` | `RecorderState` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCore` | `RecorderEventListener` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl` | `RecorderEventAdapter` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreIterator` | `SettingRecorder` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplate.RewindhandlersNameplate` | `ExportSettings` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplate.mixin.RewindhandlersNameplate` | `FrameBufferPool` | evidence in the map row |
| `com.moonsworth.lunar.client.replay.rewindhandlersNameplate.mixin.RewindhandlersNameplateImpl` | `QueuedFrameBufferPool` | evidence in the map row |
| `com.moonsworth.lunar.client.feature.ModuleType3` | `AbstractCosmetic` | evidence in the map row |
| `com.moonsworth.lunar.client.feature.Module_2` | `CosmeticModelRenderer` | evidence in the map row |
| `com.moonsworth.lunar.client.feature.mixin.MixinHelper` | `CosmeticLayerExtension` | evidence in the map row |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2` | `DynamicListener` | evidence in the map row |
| `com.moonsworth.lunar.client.guiRewindhandlers.holograms.GuiRewindhandlers2Extension` | `TrackedValue` | evidence in the map row |
| `com.moonsworth.lunar.client.guiRewindhandlers.holograms.GuiRewindhandlers2Extension2` | `TrackedValueImpl` | evidence in the map row |
| `com.moonsworth.lunar.replaymod.forge.v1_12.mixin.MixinHelper` | `ReplayHandlerAccessor` | evidence in the map row |
| `com.moonsworth.lunar.replaymod.forge.v1_8.mixin.MixinHelper` | `ReplayHandlerAccessor` | evidence in the map row |

## Stale duplicates in scope — delete list (95 files)

Each row: stale file → surviving class (already in the canonical package).  These files were re-added by rescue batch 7 (or are pre-rename flavour copies); they are deliberately not in the map.  Verify by `error_diff` after each package.

For the ReplayMod flavour copies there is a third rescue per target, so the table lists the sibling classes of the same mixin target; exactly one of them must survive (check the target class + the active mixin config, then delete the others).

### `com.moonsworth.lunar.client`

| stale class | canonical survivor / sibling rescues |
|---|---|
| `com.moonsworth.lunar.client.feature.Module` | `com.moonsworth.lunar.client.cosmetics.Cosmetic` |
| `com.moonsworth.lunar.client.feature.Module2` | `com.moonsworth.lunar.client.cosmetics.CosmeticSettings` |
| `com.moonsworth.lunar.client.feature.ModuleType` | `com.moonsworth.lunar.client.cosmetics.CosmeticSlot` |
| `com.moonsworth.lunar.client.feature.ModuleType2` | `com.moonsworth.lunar.client.cosmetics.CosmeticCategory` |
| `com.moonsworth.lunar.client.feature.ModuleType3_2` | `com.moonsworth.lunar.client.cosmetics.BodywearCosmetic` |
| `com.moonsworth.lunar.client.guiRewindhandlers.Annotation` | `com.moonsworth.lunar.client.framework.listener.RequiresDynamicListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.Annotation2` | `com.moonsworth.lunar.client.framework.listener.DynamicListenerIsEnabled` |
| `com.moonsworth.lunar.client.guiRewindhandlers.Annotation3` | `com.moonsworth.lunar.client.framework.listener.TriggeredBy` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers` | `com.moonsworth.lunar.client.framework.listener.GuiRewindhandlers` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers2` | `com.moonsworth.lunar.client.framework.listener.DependencyValue` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers3` | `com.moonsworth.lunar.client.framework.listener.DependencyTracker` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler` | `com.moonsworth.lunar.client.framework.listener.GuiRewindhandlersHandler` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler22` | `com.moonsworth.lunar.client.framework.listener.CpsListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23` | `com.moonsworth.lunar.client.framework.listener.HypixelLocationListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24` | `com.moonsworth.lunar.client.framework.listener.TabListListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25` | `com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler26` | `com.moonsworth.lunar.client.framework.listener.PersistentValuesListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler27` | `com.moonsworth.lunar.client.framework.listener.BiomeListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28` | `com.moonsworth.lunar.client.framework.listener.ScoreboardListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler29` | `com.moonsworth.lunar.client.framework.listener.KnockbackListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2_2` | `com.moonsworth.lunar.client.framework.listener.ScreenTitleListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler3` | `com.moonsworth.lunar.client.framework.listener.RefCountedListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers_2` | `com.moonsworth.lunar.client.framework.listener.LambdaFactory` |
| `com.moonsworth.lunar.client.pkg.Pkg` | `com.moonsworth.lunar.client.network.server.ResolvedServerAddress` |
| `com.moonsworth.lunar.client.pkg.Pkg2` | `com.moonsworth.lunar.client.network.server.ServerBlocklist` |
| `com.moonsworth.lunar.client.pkg.Pkg3` | `com.moonsworth.lunar.client.network.server.UnresolvedServerAddress` |
| `com.moonsworth.lunar.client.pkg.Pkg4` | `com.moonsworth.lunar.client.network.server.ServerAddressResolver` |
| `com.moonsworth.lunar.client.pkg.Pkg5` | `com.moonsworth.lunar.client.network.server.ServerSrvLookup` |
| `com.moonsworth.lunar.client.pkg.Pkg6` | `com.moonsworth.lunar.client.network.server.ServerAddressPipeline` |
| `com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers` | `com.moonsworth.lunar.client.util.ItemTagUtils` |
| `com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers2` | `com.moonsworth.lunar.client.chat.ChatActionHandler` |
| `com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3` | `com.moonsworth.lunar.client.network.apollo.ApolloPacketUtils` |

### `com.moonsworth.lunar.client.guiRewindhandlers`

| stale class | canonical survivor / sibling rescues |
|---|---|
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers` | `com.moonsworth.lunar.client.framework.listener.mixin.GuiRewindhandlers` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers2` | `com.moonsworth.lunar.client.framework.listener.mixin.ProjectileTracker` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers3` | `com.moonsworth.lunar.client.framework.listener.mixin.ScreenInteractionHandler` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers4` | `com.moonsworth.lunar.client.framework.listener.mixin.HudTimerTicker` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers5` | `com.moonsworth.lunar.client.framework.listener.mixin.PlayerCosmeticsSubscription` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6` | `com.moonsworth.lunar.client.framework.listener.mixin.PlayerStatTracker` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6Impl` | `com.moonsworth.lunar.client.framework.listener.mixin.ServerSettingsListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6Task` | `com.moonsworth.lunar.client.framework.listener.mixin.KeybindOptionListener` |
| `com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate` | `com.moonsworth.lunar.client.framework.listener.nameplate.EventSubscriptionRegistry` |
| `com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2` | `com.moonsworth.lunar.client.framework.listener.nameplate.DynamicListenerEvent` |
| `com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate3` | `com.moonsworth.lunar.client.framework.listener.nameplate.ThreadedEvent` |
| `com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.GuiRewindhandlersHandler2` | `com.moonsworth.lunar.client.framework.listener.rewindhandlers.HypixelPartyTracker` |
| `com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.GuiRewindhandlersHandler22` | `com.moonsworth.lunar.client.framework.listener.rewindhandlers.TpsTracker` |
| `com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers` | `com.moonsworth.lunar.client.framework.listener.rewindhandlers.HypixelLocation` |
| `com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2` | `com.moonsworth.lunar.client.framework.listener.rewindhandlers.LocrawResponse` |
| `com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers3` | `com.moonsworth.lunar.client.framework.listener.rewindhandlers.PartyState` |

### `com.moonsworth.lunar.replaymod.forge.v1_12`

| stale class | canonical survivor / sibling rescues |
|---|---|
| `com.moonsworth.lunar.replaymod.forge.v1_12.mixin.MixinHelper2` | `com.moonsworth.lunar.replaymod.forge.v1_12.mixin.ConnectionEventHandlerAccessor` |
| `com.moonsworth.lunar.replaymod.forge.v1_12.mixin.MixinHelper3` | `com.moonsworth.lunar.replaymod.forge.v1_12.mixin.PacketListenerAccessor` |

### `com.moonsworth.lunar.replaymod.forge.v1_8`

| stale class | canonical survivor / sibling rescues |
|---|---|
| `com.moonsworth.lunar.replaymod.forge.v1_8.mixin.MixinHelper2` | `com.moonsworth.lunar.replaymod.forge.v1_8.mixin.ConnectionEventHandlerV1_8Accessor` |
| `com.moonsworth.lunar.replaymod.forge.v1_8.mixin.MixinHelper3` | `com.moonsworth.lunar.replaymod.forge.v1_8.mixin.PacketListenerV1_8Accessor` |

### `com.moonsworth.lunar.replaymod.mixin`

| stale class | canonical survivor / sibling rescues |
|---|---|
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiButtonMixin_v1_8` | ``AbstractGuiButtonMixin`, `AbstractGuiButtonV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiDropdownEntryMixin_v1_8` | ``AbstractGuiDropdownMenuEntryV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiDropdownMenuMixin_v1_8` | ``AbstractGuiDropdownMenuMixin`, `AbstractGuiDropdownMenuV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiElement_v1_8` | ``AbstractGuiElementMixin`, `AbstractGuiElementV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiHorizontalScrollbarMixin_v1_8` | ``AbstractGuiHorizontalScrollbarMixin`, `AbstractGuiHorizontalScrollbarV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiLabel_v1_8` | ``AbstractGuiLabelMixin`, `AbstractGuiLabelV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiOverlayMixin_v1_8` | ``AbstractGuiOverlayMixin`, `AbstractGuiOverlayV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiPopup_GuiPanel_v1_8` | ``AbstractGuiPopupV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiScreenMixin_v1_8` | ``AbstractGuiScreenMixin`, `AbstractGuiScreenV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiSliderMixin_v1_8` | ``AbstractGuiSliderMixin`, `AbstractGuiSliderV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiTimelineMixin_v1_8` | ``AbstractGuiTimelineMixin`, `AbstractGuiTimelineV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.AbstractGuiTimelineTimeMixin_v1_8` | ``AbstractGuiTimelineTimeMixin`, `AbstractGuiTimelineTimeV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.CameraEntityMixin_v1_8` | ``CameraEntityMixin`, `CameraEntityV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ClassicCameraControllerMixin_v1_8` | ``ClassicCameraControllerMixin`, `ClassicCameraControllerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ConnectionEventHandlerMixin_v1_8` | ``ConnectionEventHandlerMixin`, `ConnectionEventHandlerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.DisableFastRenderMixin_v1_8` | ``DisableFastRenderMixin`, `DisableFastRenderV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.EntityRendererHandlerMixin_v1_8` | ``EntityRendererHandlerMixin`, `EntityRendererHandlerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.FullBrightnessMixin_v1_8` | ``FullBrightnessMixin`, `FullBrightnessV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiBackgroundProcessMixin_Element_v1_8` | ``GuiBackgroundProcessesElementV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiBackgroundProcessMixin_v1_8` | ``GuiBackgroundProcessesV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiHandlerMixin_v1_8` | ``GuiHandlerMixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiHandlerReplayMixin_v1_12` | ``GuiHandlerReplayMixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiHandlerReplayMixin_v1_8` | ``GuiHandlerReplayV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiPathingMixin$11_v1_12` | ``GuiPathingRealtimeTickMixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiPathingMixin$11_v1_8` | ``GuiPathingRealtimeTickV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiPathingMixin_v1_8` | ``GuiPathingMixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiRecordingControlsMixin_v1_8` | ``GuiRecordingControlsMixin`, `GuiRecordingControlsV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiRecordingOverlayMixin_v1_8` | ``GuiRecordingOverlayMixin`, `GuiRecordingOverlayV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiReplayViewerMixin_v1_8` | ``GuiReplayListV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.GuiSavingReplayMixin_v1_8` | ``GuiSavingReplayMixin`, `GuiSavingReplayV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.MCVerMixin_v1_8` | ``MCVerMixin`, `MCVerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.MessageDeserializerMixin_v1_8` | ``MessageDeserializerMixin`, `MessageDeserializerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.PacketListenerMixin_v1_8` | ``PacketListenerMixin`, `PacketListenerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.PboOpenGlFrameCapturerMixin_v1_8` | ``PboOpenGlFrameCapturerMixin`, `PboOpenGlFrameCapturerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ReplayHandlerMixin_v1_8` | ``ReplayHandlerMixin`, `ReplayHandlerV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ReplayModBackendMixin_v1_8` | ``ReplayModBackendMixin`, `ReplayModBackendV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ReplayModMixinConfigPluginMixin_v1_8` | ``ReplayModMixinConfigPluginMixin`, `ReplayModMixinConfigPluginV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ReplayModMixin_v1_8` | ``ReplayModMixin`, `ReplayModV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.ReplayModRecordingMixin_v1_8` | ``ReplayModRecordingMixin`, `ReplayModRecordingV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.SettingKeysMixin_v1_8` | ``SettingKeysMixin`, `SettingKeysV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.SettingsRegistryMixin_v1_8` | ``SettingsRegistryMixin`, `SettingsRegistryV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.VanillaGuiScreenMixin_v1_8` | ``VanillaGuiScreenMixin`, `VanillaGuiScreenV1_8Mixin`` |
| `com.moonsworth.lunar.replaymod.mixin.VanillaGuiScreen_EventHandlerMixin_v1_8` | `—` |

## Follow-ups / uncertainties

- **`framework/feature/rewind` must go.**  It is the largest duplicate tree (258 files) and the oldest references; deleting it is the single biggest win of this subsystem.  The class-level correspondence already exists in the naming ledgers (`classes-finalclientA`, `classes-rewindgui`, `classes-rewindhighlight`, `classes-coordsnameplate`, `classes-modfishing`, `classes-clientmixins`, `classes-inventorysearch`); each old `framework.feature.rewind.X` has a renamed twin under `client.replay.*`.  Until then `client/replay/**` moves are safe (independent FQNs), but `RewindHandlers` (the mod) still imports one class from the old tree (`framework.feature.rewind.mixin.Rewind2`), and `client/replay/mixin/nameplate/*` classes are still referenced by old-tree files.
- **`replaymod` mixin triples.**  Many targets exist as `<X>Mixin` + `<X>Mixin_v1_8` + `<X>V1_8Mixin` (three rescues of the same mixin; e.g. `AbstractGuiButton`, `MCVer`, `GuiPathingRealtimeTick`).  The map moves all of them into `client/replay/replaymod/mixin/`; only one per target may stay.  Resolve by checking which simple name the active mixin config resolves to (`mixins.ichor.replaymod.v1_8.json` currently carries the *obfuscated* names, so the names can only be validated in the VM).
- `client/replay/mixin/**` was a misnomer (no mixins: `InputStreamLoader`, `RewindFileReader`, `ZipEntryLocator`, …).  Those moved to `replay/project`; the freed `replay/mixin` name was not reused — the ReplayMod mixins live in `replay/replaymod/mixin` to keep the third-party target boundary visible.
- **Driver holograms**: `driver/hologram/**` renders the marker/cosmetic-preview holograms and talks to `client.markers.*`/`client.cosmetics.emote`.  If a later pass distinguishes "in-world markers" from "WebOSR cosmetics preview", this package is the pivot; it was kept under `driver/` because `HologramRenderer`/`CosmeticPreviewBridge` are wired into the driver overlay registry.
- `driver/core/highlight/{StorePrice,StorePriceEntry}` are store-screen JSON models; they were folded into `driver/bridge` rather than a 2-class package (min-5-class rule).
- `client/markers/mixin/gui/mixin/*Bridge` (5 files) are driver bridges that wave5 left in `markers`; they belong in `client/driver/bridge/` but are outside this map's scope.
- Two `RewindhandlersNameplateCore` classes and two `…Iterator` classes collided; they are different classes (packet constants vs recorder event base) and got distinct names.

