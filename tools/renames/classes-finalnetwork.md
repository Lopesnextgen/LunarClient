# Final naming pass - `network`, `legacy`, `framework`, `mixin`, `files`

Dry-run map: `tools/renames/classes-finalnetwork.tsv` (apply with `tools/apply_class_renames_aware.py --map tools/renames/classes-finalnetwork.tsv`).

**Result:** `rows=117 skipped=0 files_touched=169 files_renamed=112` (dry run, no source edits).

Every `old` name is declared in the listed package, every `new` name is unique across all of `src/main/java`
(`decl_packages` check, re-verified after the concurrent `bridge`/`client` waves) and unique within the map.
Nested rows were verified to be declared inside the owner file.

## Renames

| package | old | new | evidence |
|---|---|---|---|
| `com.moonsworth.lunar.network` | `MixinHelper` | `GameRewindExportResolution` | generated model: width/height BigDecimal; toString "class GameRewindExportResolution"; held as encoder/resolution by GameRewindProjectExportEventData |
| `com.moonsworth.lunar.network` | `MixinHelper$Data` | `GameRewindExportResolutionTypeAdapterFactory` | Gson TypeAdapterFactory whose create() checks MixinHelper.class (GameRewindExportResolution) |
| `com.moonsworth.lunar.network` | `MixinHelper3$Data` | `GameRewindExportAudioTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper3 (toString "class GameRewindExportAudio") |
| `com.moonsworth.lunar.network` | `MixinHelper4$Data` | `GameFailedParseEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameFailedParseEvent |
| `com.moonsworth.lunar.network` | `MixinHelper5$Data` | `GameRewindRecordingLocationTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper5 (toString "class GameRewindRecordingLocation") |
| `com.moonsworth.lunar.network` | `MixinHelper7$Data` | `BaseGameEventGeoLocationTypeAdapterFactory` | TypeAdapterFactory whose create() checks BaseGameEventGeoLocation |
| `com.moonsworth.lunar.network` | `MixinHelper8$Data` | `GameRewindEditorTimelineTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameRewindEditorTimeline |
| `com.moonsworth.lunar.network` | `MixinHelper9$Data` | `BaseGameEventInboundLocationTypeAdapterFactory` | TypeAdapterFactory whose create() checks BaseGameEventInboundLocation |
| `com.moonsworth.lunar.network` | `MixinHelper10$Data` | `BaseGameEventLocationTypeAdapterFactory` | TypeAdapterFactory whose create() checks BaseGameEventLocation |
| `com.moonsworth.lunar.network` | `MixinHelper11$Data` | `GameEventBatchPostRequestTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameEventBatchPostRequest |
| `com.moonsworth.lunar.network` | `MixinHelper12$Data` | `BaseGameEventLocationHostedWorldTypeAdapterFactory` | TypeAdapterFactory whose create() checks BaseGameEventLocationHostedWorld |
| `com.moonsworth.lunar.network` | `MixinHelper13$Data` | `BaseGameEventLocationPublicServerTypeAdapterFactory` | TypeAdapterFactory whose create() checks BaseGameEventLocationPublicServer |
| `com.moonsworth.lunar.network` | `MixinHelper14$Data` | `BaseGameEventInstalledModsInnerTypeAdapterFactory` | TypeAdapterFactory whose create() checks BaseGameEventInstalledModsInner |
| `com.moonsworth.lunar.network` | `MixinHelper15$Data` | `GameFailedParseEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameFailedParseEventData |
| `com.moonsworth.lunar.network` | `MixinHelper22$Data` | `GameRewindProjectExportEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameRewindProjectExportEventData |
| `com.moonsworth.lunar.network` | `MixinHelper23$Data` | `GameRewindLayerAddEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameRewindLayerAddEventData |
| `com.moonsworth.lunar.network` | `MixinHelper24$Data` | `GamePromotionInteractionEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper24 (toString "class GamePromotionInteractionEventData") |
| `com.moonsworth.lunar.network` | `MixinHelper25$Data` | `GameBlogPostInteractionEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper25 (toString "class GameBlogPostInteractionEventData") |
| `com.moonsworth.lunar.network` | `MixinHelper26$Data` | `GameRewindRecordingEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks GameRewindRecordingEventData |
| `com.moonsworth.lunar.network` | `MixinHelper27$Data` | `GameRewindEditorSessionEventDataTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper27 (toString "class GameRewindEditorSessionEventData") |
| `com.moonsworth.lunar.network` | `MixinHelper62$Data` | `GameBatchEventTypeAdapterFactory` | anyOf adapter for GameBatchEvent; delegates to the six NetworkIterator* event adapters + GameFailedParseEvent |
| `com.moonsworth.lunar.network` | `MixinHelper63$Data` | `GameRewindLayerTypeAdapterFactory` | oneOf adapter for GameRewindLayer; delegates GameRewindLayerAudio/Gameplay/Effect (MixinHelper163/162/164) |
| `com.moonsworth.lunar.network` | `MixinHelper162$Data` | `GameRewindLayerGameplayTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper162 (toString "class GameRewindLayerGameplay") |
| `com.moonsworth.lunar.network` | `MixinHelper163$Data` | `GameRewindLayerAudioTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper163 (toString "class GameRewindLayerAudio") |
| `com.moonsworth.lunar.network` | `MixinHelper164$Data` | `GameRewindLayerEffectTypeAdapterFactory` | TypeAdapterFactory whose create() checks MixinHelper164 (toString "class GameRewindLayerEffect") |
| `com.moonsworth.lunar.network` | `NetworkIterator$Data` | `GameBlogPostInteractionEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks NetworkIterator (toString "class GameBlogPostInteractionEvent") |
| `com.moonsworth.lunar.network` | `NetworkIterator2$Data` | `GameRewindLayerAddEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks NetworkIterator2 (toString "class GameRewindLayerAddEvent") |
| `com.moonsworth.lunar.network` | `NetworkIterator3$Data` | `GameRewindProjectExportEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks NetworkIterator3 (toString "class GameRewindProjectExportEvent") |
| `com.moonsworth.lunar.network` | `NetworkIterator4$Data` | `GamePromotionInteractionEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks NetworkIterator4 (toString "class GamePromotionInteractionEvent") |
| `com.moonsworth.lunar.network` | `NetworkIterator5$Data` | `GameRewindEditorSessionEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks NetworkIterator5 (toString "class GameRewindEditorSessionEvent") |
| `com.moonsworth.lunar.network` | `NetworkIterator6$Data` | `GameRewindRecordingEventTypeAdapterFactory` | TypeAdapterFactory whose create() checks NetworkIterator6 (toString "class GameRewindRecordingEvent") |
| `com.moonsworth.lunar.network` | `Network2$Data` | `AnalyticsBatchEntry` | Network2 analytics batcher queue entry: GameBatchEvent + retry counter, requeued while count<2 after a failed batch send |
| `com.moonsworth.lunar.network` | `Network2$Type` | `AnalyticsEnvironment` | PRODUCTION(0)/DEVELOPMENT(1) + serverIndex passed to the ApiClient; Network2 runs on "lunar-analytics-service-thread" |
| `com.moonsworth.lunar.network` | `Network` | `AnalyticsEvent` | interface {BaseGameEvent method1()}; implemented by the anyOf event models (jar NetworkIterator*), cast in Network2.method5 |
| `com.moonsworth.lunar.network.mixin` | `MixinHelper` | `ApiClient` | openapi-generator okhttp-gson ApiClient: basePath/hostIndex/auth/okhttp client + Gson builder; held by GameEventApi/Configuration |
| `com.moonsworth.lunar.network.mixin` | `MixinHelperException` | `ApiException` | openapi-generator ApiException: code + response headers/body; getMessage prints Message/HTTP response code/body/headers |
| `com.moonsworth.lunar.network.mixin` | `InterceptorImpl` | `GzipRequestInterceptor` | okhttp Interceptor that gzips request bodies and sets Content-Encoding: gzip |
| `com.moonsworth.lunar.network.mixin` | `RequestBodyImpl` | `ProgressRequestBody` | openapi-generator ProgressRequestBody: wraps a RequestBody and forwards write progress to an ApiCallback |
| `com.moonsworth.lunar.network.mixin` | `ResponseBodyImpl` | `ProgressResponseBody` | openapi-generator ProgressResponseBody: wraps a ResponseBody and forwards read progress to an ApiCallback |
| `com.moonsworth.lunar.files` | `Files` | `MappingsGson` | static Gson used by VersionInfo/VersionManifest to parse mapping metadata; outer class of the mapping-artifact registry |
| `com.moonsworth.lunar.files` | `Files$Data2` | `MappingArtifacts` | static catalog of mapping artifacts (mojang manifest, lunar kin, optifine csrg, forge, fabric, parchment); referenced by framework Ichor6Impl |
| `com.moonsworth.lunar.files` | `Files$Data4` | `NameDescription` | record with components name;desc (unreferenced helper) |
| `com.moonsworth.lunar.files` | `Files2_2` | `ArtifactData` | record dataSource;data: mapping-artifact descriptor + resolved bytes; returned by the mapping providers |
| `com.moonsworth.lunar.files` | `Files3_2` | `MappingDownloader` | HTTP client: retrying download (7 tries, 180s timeout), zip-integrity check, multi-URL predicate download |
| `com.moonsworth.lunar.files` | `Files4$Data` | `MappingResolution` | result of the provider resolve step: either resolved artifact list or the missing-artifact set |
| `com.moonsworth.lunar.files` | `Files4$Type` | `LogLevel` | FATAL/ERROR/WARN/INFO/DEBUG/TRACE levels of the mapping logger (jar Files4) |
| `com.moonsworth.lunar.files` | `Files4Base` | `DelegatingMappingProvider` | abstract provider base delegating resolve/write to a wrapped Files4_2 |
| `com.moonsworth.lunar.files` | `Files4Base2` | `FileMappingProvider` | disk-cache provider; validates srg/xsrg/csrg/tsrg with lorenz readers and .kin with the kin MappingFormat |
| `com.moonsworth.lunar.files` | `Files4Base3` | `RegistryMappingProvider` | provider that consults the Files7 mapping registry cache before the wrapped provider |
| `com.moonsworth.lunar.files` | `Files4Base4` | `ClasspathMappingProvider` | loads mapping artifacts from the classloader resources |
| `com.moonsworth.lunar.files` | `Files4Impl` | `RemoteMappingProvider` | downloads artifacts from the descriptor URL with SoftReference cache and mx.offline flag |
| `com.moonsworth.lunar.files` | `Files5$1` | `KinMappingFormat` | lorenz MappingFormat reading/writing BinaryMappingsReader/Writer with standard extension "kin" |
| `com.moonsworth.lunar.files` | `Files5$Extension` | `PlaceholderResolver` | supplies Collection<VersionPlaceholder> and substitutes ${key} placeholders in artifact file names |
| `com.moonsworth.lunar.files` | `Files5_2` | `MappingFormats` | holder of the kin MappingFormat instance used by FileMappingProvider |
| `com.moonsworth.lunar.files` | `Files6_2` | `ValuePair` | generic (first, second) pair with static of(); used across client features |
| `com.moonsworth.lunar.files` | `FilesException` | `MappingException` | checked exception thrown by the mapping downloader |
| `com.moonsworth.lunar.files` | `FilesImpl` | `MappingBundle` | mapping-source bundle: namespace + artifact dependency set; getId "freeze/"+hash; built by MappingArtifacts |
| `com.moonsworth.lunar.files` | `FilesImpl$1` | `PredefinedMappingBundle` | anonymous subclass of MappingBundle preset with a fixed artifact array |
| `com.moonsworth.lunar.files` | `InheritanceProvider$Data` | `ClassInfoImpl` | org.cadixdev.bombe InheritanceProvider.ClassInfo implementation: name/interface/superName/interfaces/fields/methods |
| `com.moonsworth.lunar.files.mixin` | `Files` | `ResolvedVersion` | toString "ResolvedVersion(...)": id/javaVersion/assetIndex/mainClass/type/time/releaseTime/downloads |
| `com.moonsworth.lunar.files.mixin` | `Data` | `DownloadInfo` | nested Files.Data -> ResolvedVersion.DownloadInfo (sha1 + url) of the downloads map |
| `com.moonsworth.lunar.framework` | `FrameworkType` | `MappingNamespace` | enum NONE/NOTCH/SRG/PATCH with resource-path filters; field of framework.mixin.Ichor2Iterator (optifine patch) |
| `com.moonsworth.lunar.framework` | `Ichor5Handler$Data2` | `OptifineMixinHandler` | extends loader MixinInternal3Handler; registers optifine/optifine_combined/optifine_<version> mixins at the Mixin stage |
| `com.moonsworth.lunar.framework.mixin` | `Ichor2Handler` | `NotchResourceTransformer` | PRE_OPTIFINE_PATCH Ichor2: for net/optifine or net/minecraftforge classes injects the bytes of the "notch/<path>" resource |
| `com.moonsworth.lunar.mixin` | `ChannelInitializerImpl` | `NetworkChannelInitializer` | netty ChannelInitializer wrapping a delegate handler; preserves IP_TOS and forces TCP_NODELAY (used by mixin.NetworkManagerMixin) |
| `com.moonsworth.lunar.mixin` | `LegacyImpl` | `MultiBridgeV1_7` | extends legacy MultiBridge; getMinecraftVersion() returns Config.field1 ("v1_7") |
| `com.moonsworth.lunar.mixin` | `Wrapper` | `DummyPlayerFactoryV1_7` | extends AncientDummyPlayer; getMinecraftVersion() = Config.field1 (v1_7); createDummyPlayer -> EntityClientPlayerMPImpl |
| `com.moonsworth.lunar.legacy` | `Bridge2Handler` | `KeyboardBridgeImpl` | implements bridge.KeyboardBridge via LWJGL Keyboard: keycode table, enableRepeatEvents, modifier bitmask |
| `com.moonsworth.lunar.legacy` | `Bridge2Handler$1` | `KeyboardBridgeImpl$1` | javac synthetic switch-map for the BridgeType_8 switch in KeyboardBridgeImpl.method6 |
| `com.moonsworth.lunar.legacy` | `Bridge3Handler` | `MouseBridgeImpl` | implements bridge.Bridge3_27: Mouse.isButtonDown/getX/getY, cursor build, sets Mouse.event_dx/dy/dwheel via MethodHandles |
| `com.moonsworth.lunar.legacy` | `Legacy` | `MultiBridge` | abstract service (getMinecraftVersion + ServiceLoader); logs "Creating MultiBridgeLegacy instance" |
| `com.moonsworth.lunar.legacy` | `Legacy2$1` | `RunnableErrorScreen` | anonymous GuiErrorScreen whose actionPerformed runs an injected Runnable |
| `com.moonsworth.lunar.legacy` | `Legacy2$2` | `NoopChannelInitializer` | anonymous netty ChannelInitializer with an empty initChannel |
| `com.moonsworth.lunar.legacy` | `MixinCore` | `ConnectingScreenLock` | static boolean guard: GuiConnectingDisconnectMixin.lunar$blockDisconnectButton cancels the Disconnect button (id 0) while connecting |
| `com.moonsworth.lunar.legacy` | `MixinHelper` | `ServerListDragBridge` | interface lunar$armServerDrag(int,int,int) cast onto GuiMultiplayer.serverListSelector by GuiMultiplayerBlocklistMixin |
| `com.moonsworth.lunar.legacy` | `MixinMisc$Type` | `DesktopPlatform` | enum LINUX/SOLARIS/WINDOWS/OSX/UNKNOWN + telemetryName + openUrl (xdg-open/rundll32/open) |
| `com.moonsworth.lunar.legacy` | `MixinMisc$Type$1` | `DesktopPlatform$1` | WINDOWS constant body: getOpenUrlArguments -> rundll32 url.dll,FileProtocolHandler |
| `com.moonsworth.lunar.legacy` | `MixinMisc$Type$2` | `DesktopPlatform$2` | OSX constant body: getOpenUrlArguments -> open <url> |
| `com.moonsworth.lunar.legacy.mixin` | `BridgeHandler` | `KeyEventBridgeImpl` | implements bridge.KeyEventBridge: char/code/modifiers, isAlphabetic, code==1 |
| `com.moonsworth.lunar.legacy.mixin` | `C01PacketChatMessageMixin2` | `C01PacketChatMessageChatLengthMixin` | @Mixin(C01PacketChatMessage) @Redirect substring(II) clamps to Chat.method24 / ServerRuleModule.MAX_CHAT_LENGTH |
| `com.moonsworth.lunar.legacy.mixin` | `ComponentTransformableExtension` | `TextComponentTransformableMixin` | @Mixin(TextComponent) interface extends ComponentTransformable, delegates renderSelf to AdventureMixins.renderSelfText |
| `com.moonsworth.lunar.legacy.mixin` | `StatFileWriter` | `KeyResourceLocationMixin` | @Mixin(adventure Key) @Overwrite static key(String,String) -> new ResourceLocation |
| `com.moonsworth.lunar.legacy.mixin` | `Data` | `RawMouseInput` | nested WindowsDisplayMixin.Data: native struct for nGetRawMouseCoords (boolean absolute + int x/y) |
| `com.moonsworth.lunar.legacy.wrapper` | `BridgeHandler` | `GlFenceSyncBridgeImpl` | implements bridge.GlFenceSyncBridge via GL32.glFenceSync/glDeleteSync/glClientWaitSync |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge2Handler` | `InventoryBridgeImpl` | implements bridge.InventoryBridge over net.minecraft IInventory (getStackInSlot/displayName/stackLimit) |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge4Handler` | `LayerRendererBridgeAdapter` | adapts a bridge LayerRendererBridge to vanilla LayerRenderer (doRenderLayer/shouldCombineTextures) |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge4Handler_2` | `PlayerModelPartsBridgeImpl` | implements bridge.Bridge4_24 returning EnumPlayerModelParts (CAPE/JACKET/sleeves/legs/HAT) |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge5Handler` | `AlcBridgeImpl` | implements bridge.AlcBridge: ALC capture/loopback device + context handling via ALC10/ALC11 and OpenALNative |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge5Handler_2` | `TextureUtilBridgeImpl` | implements bridge.TextureUtilBridge via TextureUtil.glGenTextures/uploadTextureImage/deleteTexture |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge7Handler` | `TextureDataBridgeImpl` | implements bridge.Bridge7_3: upload DecodedTextureDataBridge (ARGB) and glGetTexImage into a DirectImage |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge8Handler` | `EntityFactoryBridgeImpl` | implements bridge.Bridge8_6 bridge$create(World-ish) -> Entity from an injected factory function |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge19Handler` | `ShaderRenderStateShard` | implements bridge.RenderStateShardBridge wrapping a vanilla Shader (texture-unit save/restore); CompositeRenderType looks it up for shader uniforms |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge19Task` | `RunnableRenderStateShard` | implements bridge.RenderStateShardBridge with setup/clear Runnables (alpha-test/glint state shards) |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge19Task2` | `MainTargetRenderStateShard` | RunnableRenderStateShard subclass replacing the main render target: new Bridge19Task2(() -> overrideMainRenderTarget(...), ...) |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge20Iterator` | `CompositeRenderType` | implements bridge.RenderTypeBridge: named RenderPipelineBridge + ordered RenderStateShardBridge list (vanilla 1.17 CompositeRenderType shape) |
| `com.moonsworth.lunar.legacy.wrapper` | `Bridge2Iterator` | `ItemsBridgeImpl` | implements bridge.ItemsBridge: returns ItemBridge constants from net.minecraft.init.Items |
| `com.moonsworth.lunar.legacy.wrapper` | `Horsestats23Handler` | `ClipboardBridgeImpl` | implements bridge.minecraft.ClipboardBridge via client.util.ClipboardUtils |
| `com.moonsworth.lunar.legacy.wrapper` | `PrintStreamImpl` | `LoggerPrintStream` | PrintStream subclass redirecting print/println/printf to a log4j logger with a domain prefix |
| `com.moonsworth.lunar.legacy.wrapper` | `Wrapper$Data3` | `TextureBatch` | vertex batch descriptor (first;count;texture) used by the nametag renderer to draw text/icons |
| `com.moonsworth.lunar.legacy.wrapper` | `Wrapper$Data6` | `DummyNetworkManager` | dummy NetworkManager with EnumPacketDirection.SERVERBOUND returned by DummyNetworkManagerFactory for MC >= 1.8 |
| `com.moonsworth.lunar.legacy.wrapper` | `Wrapper$Type` | `BorderTransition` | enum GROWING/SHRINKING/STATIONARY with packed colours; returned by LegacyWorldBorder.method4() from old/new size |
| `com.moonsworth.lunar.legacy.wrapper` | `Wrapper2$Data` | `BakedQuadBuilder` | FaceBakery-backed BakedQuad builder (shader-dependent vertex stride 56/28) for generated item models |
| `com.moonsworth.lunar.legacy.wrapper` | `Wrapper2$Type` | `BakedQuadFace` | enum DOWN/UP/WEST/EAST mapping EnumFacing for item-model quad generation |
| `com.moonsworth.lunar.legacy.wrapper` | `Data` | `FaceMasks` | nested FaceEdgeMasks.Data: 6 face bitmasks 1..32 plus COMPLETE=63 |
| `com.moonsworth.lunar.legacy.wrapper` | `Line` | `EdgeMasks` | nested FaceEdgeMasks.Line: 12 edge bitmasks 17..40 plus COMPLETE=63 |
| `com.moonsworth.lunar.legacy.wrapper.mixin` | `Bridge2Handler` | `JsonToNbtBridgeImpl` | implements bridge.JsonToNBTBridge via JsonToNBT.getTagFromJson (v1_7 fallback) |
| `com.moonsworth.lunar.legacy.wrapper.mixin` | `Bridge5Handler` | `NbtIoBridgeImpl` | implements bridge.NbtIoBridge via CompressedStreamTools.readCompressed |
| `com.moonsworth.lunar.legacy.wrapper.util` | `Bridge2Handler` | `BlockStateBridgeV1_7` | implements bridge.BlockStateBridge for @VersionGate(max=0): Block collision/render shape/isSolid/isFluid |
| `com.moonsworth.lunar.legacy.wrapper.util` | `Bridge3Extension2` | `EmissiveMetadataSection` | IMetadataSection holding the "emissive" texture + "emissiveAnimated" flag parsed by the emissive metadata serializer |
| `com.moonsworth.lunar.legacy.wrapper.util` | `IMetadataSectionSerializer` | `EmissiveMetadataSectionSerializer` | vanilla IMetadataSectionSerializer<Bridge3Extension2> for section "lunar": reads emissive/emissiveAnimated into the emissive section |
| `com.moonsworth.lunar.legacy.wrapper.util` | `Horsestats13Handler` | `ChatComponentFactoryBridgeImpl` | implements bridge.minecraft.ChatComponentFactoryBridge: getFormattedText + new ChatComponentTranslation |
| `com.moonsworth.lunar.legacy.wrapper.util` | `Horsestats16Task` | `TexturedBoxBridgeImpl` | implements bridge.minecraft.TexturedBoxBridge; cube geometry with GL display-list cache for textured boxes |
| `com.moonsworth.lunar.legacy.wrapper.util` | `Extension` | `VertexSink` | nested Horsestats16Task.Extension: @FunctionalInterface vertex(x,y,z,u,v,r,g,b) sink used by method4 |
| `com.moonsworth.lunar.legacy.optifine.wrapper` | `Slayer3Handler` | `ShadersBridgeImpl` | implements bridge.optifine.ShadersBridge: shadow pass, shader pack, program push/pop, dfb target, glowing entities |
| `com.moonsworth.lunar.legacy.optifine.wrapper` | `Slayer3Renderer` | `CustomItemsBridgeImpl` | implements bridge.optifine.CustomItemsBridge: isUseGlint/renderCustomEffect/getCustomItemModel |
| `com.moonsworth.lunar.legacy.optifine.wrapper` | `Slayer3Renderer2` | `ConnectedTexturesBridgeImpl` | implements bridge.optifine.ConnectedTexturesBridge returning ConnectedTextures tile/block property tables |
| `com.moonsworth.lunar.legacy.optifine.wrapper` | `Slayer3Renderer222` | `CustomColorsBridgeImpl` | implements bridge.optifine.CustomColorsBridge: getDurabilityColor/getTextColor |

## Skipped

| package | old | reason |
|---|---|---|
| `com.moonsworth.lunar.files` | `Files$Extension` | 4-method interface (method1/method2/getNewName/method3) with zero references in src/main/java and zero real readers in the runtime jar (constant-pool matches were InnerClasses attributes of unrelated classes). Role undeterminable; left for the structural pass. |

## Notable findings

- **`network` is a generated openapi-generator okhttp-gson client** (`network/mixin` = invoker package). The
  leftover `MixinHelper*$Data` files are the generated `CustomTypeAdapterFactory` classes of the anyOf/oneOf
  models: each `create()` names its model class, and the real model names come from the runtime jar `toString`
  strings (`GameRewindExportResolution`, `GameRewindLayerAudio/Effect/Gameplay`, the six `Game*Event` types,
  `GameRewindRecordingLocation`, `GameRewindExportAudio`, ...). Named `<Model>TypeAdapterFactory`.
- **`network/mixin` canonical names** come straight from the generator's templates: `MixinHelper` -> `ApiClient`,
  `MixinHelperException` -> `ApiException`, `InterceptorImpl` -> `GzipRequestInterceptor`,
  `RequestBodyImpl`/`ResponseBodyImpl` -> `ProgressRequestBody`/`ProgressResponseBody`.
- **`network.Network`** is only implemented by the six anyOf event models (jar `NetworkIterator*`) and cast by the
  jar-only `Network2` analytics batcher -> `AnalyticsEvent`; `Network2$Type` is the PRODUCTION/DEVELOPMENT server
  index env -> `AnalyticsEnvironment`, `Network2$Data` is the queue entry with the retry counter -> `AnalyticsBatchEntry`.
- **`files` is Lunar's `mx` mapping registry** (Lorenz + Bombe): download/cache/dependency resolution of
  `*.kin`/srg/csrg/tsrg/parchment mapping artifacts for the Ichor remap pipeline. Classes were named
  `Mapping*`/`RemoteMappingProvider` (URL + soft cache), `FileMappingProvider` (disk cache + reader validation),
  `RegistryMappingProvider` (MRegistry cache), `ClasspathMappingProvider`, `DelegatingMappingProvider`.
- **`files/mixin/Files` names itself**: its `toString` is `ResolvedVersion(...)` and the nested `Data` string is
  `ResolvedVersion.DownloadInfo(sha1, url)` -> renamed to exactly those names. `VersionInfo`, `VersionManifest`,
  `AssetIndex` already match their own `toString` and were left alone.
- **`legacy.wrapper` is the legacy render pipeline** (RenderStateShard/RenderType/BakedQuad + packet builders):
  `Bridge19Handler`/`Bridge19Task`/`Bridge19Task2` -> `ShaderRenderStateShard`/`RunnableRenderStateShard`/
  `MainTargetRenderStateShard` (the latter is built with `overrideMainRenderTarget` in `Legacy2`),
  `Bridge20Iterator` -> `CompositeRenderType`; the rest are `*BridgeImpl` for their already-named bridge interfaces.
- **Version signal:** `com.moonsworth.lunar.mixin.LegacyImpl`/`Wrapper` return `Config.field1`, which
  `Config`'s static init maps to `v1_7` (`field2` = `v1_8`), so they become `MultiBridgeV1_7`/`DummyPlayerFactoryV1_7`.
  The abstract service logs `Creating MultiBridgeLegacy instance` -> `Legacy` renamed to `MultiBridge`.
- **`legacy/optifine/wrapper`** classes are thin OptiFine 1.8+ bridge impls (`ShadersBridge`, `CustomItemsBridge`,
  `ConnectedTexturesBridge`, `CustomColorsBridge`); their `v1_7`/`v1_8` siblings live outside this cluster and keep
  their current names.
- Synthetic `$N` classes follow their renamed owner (`Bridge2Handler$1` -> `KeyboardBridgeImpl$1`; the javac
  switch-map of the enum switch) or get a role name when the `$N` body is a real class
  (`Legacy2$1` -> `RunnableErrorScreen`, enum constant bodies -> `DesktopPlatform$1`/`$2`).
- Bare nested names were resolved with the applier's nested-row mode: `FaceEdgeMasks.Data`/`Line` ->
  `FaceMasks`/`EdgeMasks` (6 face + 12 edge bitmasks), `WindowsDisplayMixin.Data` -> `RawMouseInput` (native
  struct for `nGetRawMouseCoords`), `Horsestats16Task.Extension` -> `VertexSink` (functional vertex sink).
- `legacy.wrapper.util.IMetadataSectionSerializer` is a *class* whose name shadowed the vanilla interface it
  implements -> `EmissiveMetadataSectionSerializer`; its section model `Bridge3Extension2` -> `EmissiveMetadataSection`.

## Notes

- The map is append-compatible with the aware applier: 5 rows are nested-class renames which need the owner in
  their evidence, 112 rows rename a top-level/`$`-flat file.
- Nothing in `src/main/java` was modified; the only files written are this report and the TSV.
- Mixin configs in `src/main/resources` are still obfuscated and are regenerated later, so no resource edits
  were needed for the renamed mixins.

## Left as-is (deliberate, not junk)
- `legacy.wrapper.{LegacyWorldBorder,LegacyServerBorder,LegacyHoverEventSerializer}`: "Legacy" is the actual role (MC-version adapters), names are descriptive.
- `legacy.wrapper.{StaticMp3Codec,NetworkPlayerInfoImpl}`, `legacy.*Impl` classes: real format/impl names, not obfuscation stems.
- `files.{VersionPlaceholder,MappingNormalizer}`, `files.mixin.{VersionInfo,VersionManifest,AssetIndex}`: already accurate (two confirmed by their own `toString`).
- `legacy.optifine.wrapper.IImageBufferLoader`: descriptive for the cape `IImageBuffer` implementation.
