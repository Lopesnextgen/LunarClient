# Moves: fov + fog clusters (72 classes)

1. `fov/` is three domains, not FOV code: 18 conversation/chat classes (proto `conversation.v1`), 19 emote/geckolib render classes, 4 OptiFine shader helpers; the real FOV mod is `mod.render.Fov`.
2. `fog/` is a junk drawer: zero real fog code (real mod is `mod.render.Fog`); it holds an account/session trio, a handler-lifecycle trio, perf recorders, skins, blog, alerts, and stray framework types.
3. `Fog`/`FogHandler`/`AbstractMapHandler` are a generic init/close loading framework → `LoadableHandler`/`ItemSetHandler`/`ItemMapHandler` in `client.framework`.
4. `FogType` is a Mojang/Xbox account enum (handshake `MinecraftIdentity.Type`) → `AccountType`; `Lighting3Loader*` are session/profile/models → `AccountSession`/`AccountProfile`/`MinecraftProfile` in new `client.account`.
5. `fov.mixin` was already renamed in place (PhysicsPoint etc.); 6 move-only to `client.emote`, only `FovType`→`CosmeticType` (HAT/CLOAK/BODYWEAR/WINGS/GECKOLIB).
6. Renames needed beyond moves: `Fov`→`Tickable` (collides with `mod.render.Fov`, no implementers), `Highlight3Iterator`→`PerfSnapshotRecorder`, `Lighting3Extension`→`JsonFileConfig`.
7. New packages: `conversation`, `emote`, `shader`, `account`, `performance`, `texture`, `skin`, `blog`, `music`, `config`; reused existing: `framework`, `lighting`, `alert`, `inventorymod`.
8. `ClickImpl`'s parent (`fog.click.mixin.Click`) is absent from the tree and `Fov3_2`/`Fov3Task`/`Gui2Handler3` bases are likewise gone — evidence for those rows rests on JSON keys and in-file structure.
9. All 72 new simple names grep-verified unique tree-wide (`(class|interface|enum|record) Name` ≤ 1 hit); no trailing digits; map is 72×5-col, applier-ready.
10. Skipped nothing: every file in `fov/`, `fov/mixin/`, `fog/` + 7 subpackages is mapped; `AlertCard`/`FeatureFlag`/`BlogPost`/`StyngrSong` kept names (already accurate) per Deletion-over-addition.

## Evidence table

| old | new package | new name | evidence |
|---|---|---|---|
| `fov.ChatMessage` | `client.conversation` | `ChatMessage` | proto ConversationMessage/Kind + JsonProvider, keys id/content/kind/pinned/sender/sentAt |
| `fov.EmbedType` | `client.conversation` | `EmbedType` | IMAGE/LINK_PREVIEW discriminator |
| `fov.IconChangeEvent` | `client.conversation` | `IconChangeEvent` | SystemEvent "changed conversation icon to" + newIconUrl |
| `fov.ImageEmbed` | `client.conversation` | `ImageEmbed` | MessageEmbed + mimeType |
| `fov.InviteEvent` | `client.conversation` | `InviteEvent` | SystemEvent "invited" + invitees |
| `fov.LeaveEvent` | `client.conversation` | `LeaveEvent` | SystemEvent "left the conversation" + leftMember |
| `fov.LinkPreviewEmbed` | `client.conversation` | `LinkPreviewEmbed` | MessageEmbed + title/siteName/description/mimeType |
| `fov.MessageContentType` | `client.conversation` | `MessageContentType` | PLAIN_TEXT/STICKER/SYSTEM |
| `fov.MessageEmbed` | `client.conversation` | `MessageEmbed` | abstract embed, proto ConversationMessageEmbed, keys type/rawUrl/proxiedUrl/capturedAtMs |
| `fov.NameChangeEvent` | `client.conversation` | `NameChangeEvent` | "changed conversation name" + newName |
| `fov.PinnedEvent` | `client.conversation` | `PinnedEvent` | "pinned a message" + pinnedMessageId |
| `fov.PlainTextContents` | `client.conversation` | `PlainTextContents` | contents with plainText key |
| `fov.Sticker` | `client.conversation` | `Sticker` | id()/url()/name() model |
| `fov.StickerContents` | `client.conversation` | `StickerContents` | contents with id/name/sticker/url keys |
| `fov.StickerStore` | `client.conversation` | `StickerStore` | proto LunarSticker/Pack/Emoji, keys stickerPacks/emojis/maxMessageLength/participantLimit |
| `fov.SystemEvent` | `client.conversation` | `SystemEvent` | abstract event, proto SystemMessage, keys actor/type/plainText |
| `fov.SystemEventContents` | `client.conversation` | `SystemEventContents` | contents with systemEvent key |
| `fov.SystemEventType` | `client.conversation` | `SystemEventType` | PINNED/INVITE/LEAVE/NAME_CHANGE/ICON_CHANGE |
| `fov.BipedModelRenderer` | `client.emote` | `BipedModelRenderer` | single-method biped render contract |
| `fov.Emote` | `client.emote` | `Emote` | abstract emote, mchorse emoticons + emotes/icons/*.webp + expiry + EmoteGift |
| `fov.EmoteGift` | `client.emote` | `EmoteGift` | id/expiry Instant/gifted ids/EmoteGiftInfo |
| `fov.EmoteModelRenderer` | `client.emote` | `EmoteModelRenderer` | render contract implemented by Emote |
| `fov.GeckoRenderMode` | `client.emote` | `GeckoRenderMode` | RENDERED_NORMAL/RENDERED_GECKO_COMPUTE |
| `fov.GlResources` | `client.emote` | `GlResources` | GL buffer bundle for compute skinning |
| `fov.MeshPassRunner` | `client.emote` | `MeshPassRunner` | mesh-pass dispatch over VertexSink |
| `fov.ModelRenderConfig` | `client.emote` | `ModelRenderConfig` | AnimationProcessor/texture/color/renderType/pass/light + Data builder |
| `fov.ModelVertexShader` | `client.emote` | `ModelVertexShader` | #version 430 quaternion-skinning compute sources |
| `fov.PositionHistory` | `client.emote` | `PositionHistory` | 20-sample rolling position averager |
| `fov.RenderContext` | `client.emote` | `RenderContext` | world/player-model/gui optionals + molang Evaluator + type set |
| `fov.RenderContextKind` | `client.emote` | `RenderContextKind` | IN_WORLD*/IN_COSMETIC* + usesPlayer/inGui |
| `fov.RenderContextType` | `client.emote` | `RenderContextType` | IN_WORLD/IN_PLAYER_MODEL/IN_GUI/IN_FIRST_PERSON |
| `fov.RenderEntityHandle` | `client.emote` | `RenderEntityHandle` | entity-handle wrapper + flag |
| `fov.RenderPass` | `client.emote` | `RenderPass` | NORMAL/EMISSIVE/NORMAL_GLINT |
| `fov.Transform` | `client.emote` | `Transform` | scale Vector3f + Quaternionf + translation |
| `fov.TransformStack` | `client.emote` | `TransformStack` | 32-slot push/pop bone stack |
| `fov.VertexBuilder` | `client.emote` | `VertexBuilder` | abstract 8-float vertex accumulator |
| `fov.VertexSink` | `client.emote` | `VertexSink` | 8-float vertex emit contract |
| `fov.Fov` | `client.framework` | `Tickable` | tick-only, zero implementers; FOV mod is `mod.render.Fov` |
| `fov.ShaderPackHelper` | `client.shader` | `ShaderPackHelper` | ShadersBridge pack check + DFB/viewport + log-line parse |
| `fov.ShaderPass` | `client.shader` | `ShaderPass` | ShaderDefinition wrapper, implements Colorsaturation_2 |
| `fov.ShaderStateHelper` | `client.shader` | `ShaderStateHelper` | model-view/projection state + Lunar*Mat uniforms |
| `fov.ShaderUniformUpdater` | `client.shader` | `ShaderUniformUpdater` | per-ColorsaturationType2 uniform maps |
| `fov.mixin.ConditionalOutfitTree` | `client.emote` | `ConditionalOutfitTree` | proto OutfitTree/ConditionalOutfit over player UUID |
| `fov.mixin.Direction2D` | `client.emote` | `Direction2D` | already named; move-only |
| `fov.mixin.DistanceConstraint` | `client.emote` | `DistanceConstraint` | already named; move-only |
| `fov.mixin.EmoteGiftInfo` | `client.emote` | `EmoteGiftInfo` | already named; move-only |
| `fov.mixin.EquipConditionPredicate` | `client.emote` | `EquipConditionPredicate` | abstract predicate over proto EquipCondition |
| `fov.mixin.FovType` | `client.emote` | `CosmeticType` | HAT/CLOAK/BODYWEAR/WINGS/GECKOLIB, not FOV code |
| `fov.mixin.PhysicsPoint` | `client.emote` | `PhysicsPoint` | already named; move-only |
| `fog.Fog` | `client.framework` | `LoadableHandler` | init()/close(); impl by hitcolor/horsestats/inactive + RenderSubmissionManager |
| `fog.FogHandler` | `client.framework` | `ItemSetHandler` | Set<T> loading handler; old name collides with hitcolor.FogHandler |
| `fog.AbstractMapHandler` | `client.framework` | `ItemMapHandler` | Map<T,V> loading handler; extended by PingServerCache |
| `fog.Lighting3Extension` | `client.framework` | `JsonFileConfig` | file-backed JSON load/save + sentry "Savable"; no implementers |
| `fog.FogType` | `client.account` | `AccountType` | MOJANG/Xbox over handshake MinecraftIdentity.Type |
| `fog.Lighting3Loader` | `client.account` | `AccountProfile` | avatar/eligibleForMigration/hasMultipleProfiles/legacy/persistent |
| `fog.Lighting3Loader2` | `client.account` | `AccountSession` | abstract session: token/username/type/expiry + skin; used by AccountBridge |
| `fog.Lighting3Loader3` | `client.account` | `MinecraftProfile` | minecraftProfile {id,name} |
| `fog.GcMonitor` | `client.performance` | `GcMonitor` | JFR GC pauses → setGcCycles/Longest/Shortest/Avg/MedianGcMicro |
| `fog.Highlight3Iterator` | `client.performance` | `PerfSnapshotRecorder` | frame-time ring + sodium flag → RecordPerfSnapshotRequest.Builder |
| `fog.IgnoredExceptionPatterns` | `client.inventorymod` | `IgnoredExceptionPatterns` | name→Pattern registry used by ExceptionSanitizer |
| `fog.chest.StyngrSong` | `client.music` | `StyngrSong` | styngrId/name/artist/album/durationMillis; consumed by Emote |
| `fog.click.ClickImpl` | `client.texture` | `ClickImpl` | string-set collector; parent Click absent from tree |
| `fog.click.TextureProcessor` | `client.texture` | `TextureProcessor` | @FunctionalInterface over pixel Extension; used by overlay processors |
| `fog.fishing.SavedSkin` | `client.skin` | `SavedSkin` | url/skinType/name/hash/favoriteIndex |
| `fog.fishing.SkinLoadException` | `client.skin` | `SkinLoadException` | move-only |
| `fog.fishing.SkinType` | `client.skin` | `SkinType` | CLASSIC/Steve + SLIM/Alex + default texture URLs |
| `fog.gui.ModMetadata` | `client.framework` | `ModMetadata` | ModCategory sets + features.<id> language path |
| `fog.gui.OptionGroup` | `client.lighting` | `OptionGroup` | name + List<LightingExtension<?>> |
| `fog.mixin.AlertCard` | `client.alert` | `AlertCard` | id/name/text/color/icon/link/dismissible + {COUNTDOWN:} + icons/alerts/*.webp |
| `fog.nameplate.BlogPost` | `client.blog` | `BlogPost` | title/imageUrl/link + hash texture cache |
| `fog.nameplate.BlogPostDownloadTask` | `client.blog` | `BlogPostDownloadTask` | Runnable image downloader |
| `fog.rewindhandlers.FeatureFlag` | `client.config` | `FeatureFlag` | Advent/AprilFools/TurboEngine/Rewind/... + reset callbacks |
