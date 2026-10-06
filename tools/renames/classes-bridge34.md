# cluster bridge34 — the last unnamed `Bridge3*` / `Bridge4*` interfaces

62 top-level classes in `com.moonsworth.lunar.bridge`: 40 `Bridge3*` + 22 `Bridge4*`
(all except `Bridge3_3`, `Bridge3_9`, `Bridge3_15`, `Bridge3_16`, `Bridge3Extension_4`,
`Bridge4_16`, `Bridge4_18`, `Bridge4_19`, left unnamed — see bottom). 54 rows in
`classes-bridge34.tsv`; dry-run `skipped=0`, `files_touched=322`, `files_renamed=54`.

## Method

* Read each interface body, then cross-checked every token in `src/main/java`.
* `libs/lunar-renamed-classes.jar` is the pre-restructure Lunar client jar and still
  carries readable mixin class names (`SoundHandlerMixin`, `FramebufferMixin`, ...).
  Scanning its `implements`/`extends` headers (`javap -p`) gave the target class
  directly; `javap -v` constant-pool dumps gave the `@Mixin(...)` targets.
* For unpacked wrappers (`legacy/wrapper/*`, `icon/mixin/*`) `javap -c` bodies named
  the delegated MC member (`RenderHelper.enableStandardItemLighting`,
  `EffectRenderer.addEffect(EntityFX)`, `EnumPlayerModelParts.*`, ...).
* Naming follows the already-renamed siblings in the package
  (`EntityRendererBridge`, `BufferBuilderBridge`, `ShaderInstanceBridge`, ...).
* Zero new-name collisions with any declaration in `src/main/java`.

## Evidence table (54)

| old | new | target / discriminating methods |
|---|---|---|
| `Bridge3$Extension` | `ChatLineExtensionBridge` | nested `Extension` of the ChatLine bridge (same original file as `Bridge3_13`), empty, unreferenced |
| `Bridge3Extension` | `AnimationMetadataSectionBridge` | `@Mixin(AnimationMetadataSection)`; `bridge$getFrameCount/FrameTime/FrameIndex/hasTime`, driven by `SpriteAnimationBridge` |
| `Bridge3Extension2_2` | `EmissiveMetadataSectionBridge` | custom `IMetadataSection` built from `.mcmeta` `"emissive"`/`"emissiveAnimated"` (wrapper `Bridge3Extension2`) |
| `Bridge3Extension3_2` | `TextureMetadataSectionBridge` | `@Mixin(TextureMetadataSection)`; `bridge$getTextureBlur/getTextureClamp` |
| `Bridge3Extension_2` | `ServerboundCustomPayloadPacketBridge` | `@Mixin(C17PacketCustomPayload)`, empty packet marker; 1.8 client→server plugin message |
| `Bridge3Extension_3` | `ModelHumanoidHeadBridge` | extends `Bridge3_29`; `bridge$showHat(boolean)`; `@Mixin(ModelHumanoidHead)`, used by `SkullSkinLayerRenderer` |
| `Bridge3Extension_5` | `BlockButtonBridge` | `@Mixin(BlockButton)`; `bridge$isPowered(BlockStateBridge)` = `POWERED` property |
| `Bridge3Extension_6` | `ClientboundCustomPayloadPacketBridge` | `@Mixin(S3FPacketCustomPayload)`; `bridge$id()` + `bridge$getBufferData()`; `EventPluginMessage` |
| `Bridge3Extension_7` | `AsyncTextureBridge` | extends `Bridge3_4`; `method4()`/`method13()`/`method3(boolean)` = async texture-resource state (impl `Util2Handler`) |
| `Bridge3_11` | `SoundHandlerBridge` | `@Mixin(SoundHandler)`; `bridge$playSound/play/playLunarMusic/playMp3FromURL/getSoundEngine` |
| `Bridge3_12` | `EntityEndermanMarkerBridge` | empty, `@Mixin(EntityEnderman)`; `instanceof` in `SkyblockEndermanSlayer` |
| `Bridge3_13` | `ChatLineBridge` | `@Mixin(ChatLine)`; `bridge$getLunarID/setLunarID/isAddedByApollo` |
| `Bridge3_14` | `IMetadataSectionBridge` | `@Mixin(IMetadataSection)`; base of the three metadata bridges |
| `Bridge3_17` | `GameRendererBridge` | `@Mixin(EntityRenderer)`; `bridge$loadPostEffectShader/getShaderGroup/enableLightmap/getMapItemRenderer/getCamera` (`EntityRendererBridge` already = modern min-6 duck) |
| `Bridge3_18` | `SlotBridge` | `@Mixin(Slot)`; `bridge$getItemStack/getIndex/getNumber/getInventory/getXDisplayPosition/getYDisplayPosition` |
| `Bridge3_19` | `ServerDataBridge` | `@Mixin(ServerData)`; `bridge$serverIP/getServerName/getPopulationInfo/getPingCallback/isPinned/disableResourcePack` |
| `Bridge3_2` | `GuiScreenHorseInventoryBridge` | `@Mixin(GuiScreenHorseInventory)`; sole method `bridge$getHorse()`; `HorseStats` |
| `Bridge3_20` | `EntityFXMarkerBridge` | empty marker, the `ParticleEngineBridge.bridge$addEffect(EntityFX)` parameter; impl casts to `EntityFX`/`EntityFXBridge` |
| `Bridge3_21` | `PacketBridge` | `@Mixin(Packet)`; `bridge$read/write/handle/getPacketType`; supertype of all `*PacketBridge` |
| `Bridge3_22` | `CrashReportBridge` | `@Mixin(CrashReport)`; `bridge$getTitle/getCause`; `CrashReporter`/`ThrowableJsonSerializer` |
| `Bridge3_24` | `FramebufferBridge` | `@Mixin(Framebuffer)`; `bridge$createBindFramebuffer/blitToScreen/getColorTexture/present`; blur/screenshot/stencil |
| `Bridge3_25` | `ChatComponentStyleBridge` | `@Mixin(ChatComponentStyle)`; `moonBridge$asAdventureComponent()` |
| `Bridge3_26` | `LayerHeldItemBridge` | empty marker, `@Mixin(LayerHeldItem)` |
| `Bridge3_27` | `MouseHelperBridge` | global `Bridge.method20()`; `Mouse.isButtonDown/getX/getY`, `Mouse.event_dx/dy/dwheel` setters, native `Cursor` create/set/clear |
| `Bridge3_29` | `ModelSkeletonHeadBridge` | empty marker, `@Mixin(ModelSkeletonHead)`; base of `Bridge3Extension_3` |
| `Bridge3_30` | `OldServerPingerBridge` | `@Mixin(OldServerPinger)`; `bridge$ping(ServerDataBridge)/bridge$tick()` |
| `Bridge3_32` | `PlayerCapabilitiesBridge` | `@Mixin(PlayerCapabilities)`; `isFlying/isCreativeMode/getFlySpeed/getWalkSpeed/isAllowFlying` |
| `Bridge3_33` | `DisplayBridge` | `@Mixin(targets = org.lwjgl.opengl.{Linux,MacOSX,Windows}Display)`; `bridge$getDisplayHandle/getWindowHandle/toggleRawInput` |
| `Bridge3_34` | `TileEntityBeaconRendererBridge` | `@Mixin(TileEntityBeaconRenderer)`; `bridge$renderBeacon(..., BeamSegmentBridge, ...)` |
| `Bridge3_35` | `RenderHelperBridge` | global `Bridge.method14()`; `RenderHelper.enable/disable...ItemLighting` (method1-3), hovering text (method4-5), main-menu skybox/panorama (method6-7) |
| `Bridge3_4` | `TextureBridge` | texture lifecycle duck: `method1(IResourceManager, handle)` load, `method22()` delete; `BaseTexture`/`PersistentTexture`/`AbstractTextureImpl` |
| `Bridge3_5` | `BridgeMethodTarget` | `Bridge4_2` mapping + ASM `MethodNode`; `Bridge2_35` emits each bridge implementation from it |
| `Bridge3_6` | `NBTTagListBridge` | `@Mixin(NBTTagList)`; `bridge$getString/getCompoundAt/size` |
| `Bridge3_7` | `ShaderUniformDeclaration` | `name()` + `BridgeType_15` (joml type); element of `RenderPipelineBridge.bridge$uniforms()` |
| `Bridge3_8` | `EntityWitherSkullMarkerBridge` | empty marker, `@Mixin(EntityWitherSkull)` |
| `Bridge4$Data` | `SourceImage` | `int[]` pixels + width/height; `TextureAtlasSpriteMixin.bridge$getSourceImage/setSourceImage` |
| `Bridge4Extension_2` | `EntityHorseVariantBridge` | extends `Bridge4_23`; `bridge$getVariant()`; `@Mixin(EntityHorse)`; `Hitbox` |
| `Bridge4_10` | `BlockDoublePlantBridge` | `@Mixin(BlockDoublePlant)`; `bridge$isSunflower/isRoseBush(BlockStateBridge)` |
| `Bridge4_11` | `FluentTessellatorBridge` | fluent API mixed into `Tessellator`: `bridge$begin(mode, format)/pos/color/uv/lightmap/endVertex/end`; `TessellatorBridge` was already taken by `Bridge2_23` |
| `Bridge4_12` | `EntityFXBridge` | `@Mixin(EntityFX)`; `bridge$getRed/Green/Blue/Alpha`, `bridge$setParticleType/getParticleType/isFirework` |
| `Bridge4_13` | `SubtitleBridge` | `@Mixin(GuiSubtitleOverlay$Subtitle)`; `bridge$getText/getAliveTime/getLocation`; `AudioSubtitles` |
| `Bridge4_14` | `SpecialModelRendererBridge` | `bridge$render(T, displayContext, MatrixStack, MultiBufferSourceBridge, light, overlay, foil)` + `bridge$extractArgument(ItemStack)` = `SpecialModelRenderer`; returned by `MixinHelper$Extension4.bridge$specialRenderer()` |
| `Bridge4_15` | `GuiRecipeBookBridge` | `@Mixin` incl. `GuiRecipeBook`; `bridge$isRecipeBookVisible/isWidthTooNarrow` |
| `Bridge4_17` | `Vec3Factory` | static `method1(double,double,double)` → `Horsestats15.method2` (Vec3 bridge create); no callers |
| `Bridge4_20` | `ChatStyleBridge` | `@Mixin(ChatStyle)`; `moonBridge$asAdventureStyle()` |
| `Bridge4_21` | `EntityModelBridge` | Lunar wing `ModelBase` impl (`legacy/wrapper/ModelBase`); `method1(matrixStack, scale, partialTicks, texture)` render, `method4(texture)` = `Model.renderType`; vanilla marker already `ModelBaseBridge` |
| `Bridge4_22` | `ProtocolInfoBridge` | empty marker; `NetworkManagerBridge.bridge$get{Client,Server}boundProtocolInfo()` (`VersionGate min=22`) |
| `Bridge4_23` | `AbstractHorseBridge` | `@Mixin(AbstractHorse)`; `bridge$getJumpHeight/getJumpHeightRaw/getSpeed/getSpeedRaw/hasChest` |
| `Bridge4_24` | `PlayerModelPartsBridge` | 7 methods → `EnumPlayerModelParts.{CAPE,JACKET,LEFT_SLEEVE,RIGHT_SLEEVE,LEFT_PANTS_LEG,RIGHT_PANTS_LEG,HAT}`; impl `Bridge4Handler_2` |
| `Bridge4_4` | `ServerListBridge` | `@Mixin(ServerList)`; `bridge$load/save/add(ServerDataBridge)/containsUnpinnedAddress/canSwapServers` |
| `Bridge4_5` | `ToolMaterialBridge` | `@Mixin(Item$ToolMaterial)`; `TiersBridge.method1..method7` return the tiers as it |
| `Bridge4_6` | `VertexConsumerBridge` | modern `VertexConsumer`: `bridge$vertex/color/uv/overlayCoords/uv2/normal/endVertex` + 14-arg overload (`NO_OVERLAY = 655360`); `VertexConsumerProvider.bridge$first()` |
| `Bridge4_7` | `ShaderBridge` | `@Mixin(Shader)`; `bridge$getShaderUniforms()` = `ShaderManagerBridge` (`Bridge6_8`); `ShaderGroupMixin` |
| `Bridge4_9` | `LegacyLayerRendererBridge` | implemented by `legacy/wrapper/Bridge4Handler`, which also implements `LayerRenderer<EntityLivingBase>` and delegates to the richer `LayerRendererBridge`; `method1` = `doRenderLayer`, `method2` = MultiBufferSource path |

## Left unnamed (8)

| class | why undeterminable |
|---|---|
| `Bridge3_3` | empty marker; only occurrence is `RenderTypeBridge.bridge$setupVirtualShaderUniforms(Bridge3_3)`; no implementor, caller or mixin anywhere in the tree, client jar, legacy/forge/genesis jars. The name would be a guess between a modern `ShaderInstance`/virtual-uniform holder. |
| `Bridge3_9` | empty marker from `bridge/mixinShared/mixinExtra/mixinMain` (light-overlay family); no implementor and no reference in any jar. Same for `Bridge4_19` below. |
| `Bridge3_15` | empty marker from `bridge/mixinAlpha/Bridge3`; javap shows 0 methods, 0 annotations, no implementor or usage. |
| `Bridge3_16` | empty marker from `bridge/mixinShared/mixinExtra/mixinCommon/Bridge3`; no implementor or usage. |
| `Bridge3Extension_4` | empty packet marker extending `Bridge3_21`; `RawNetworkPacket.method3` skips dispatching a packet that is `instanceof` it, but no class implements it in the tree or the jar (the mixin was dropped), so the packet type cannot be pinned. |
| `Bridge4_16` | `@VersionGate(min = 26) bridge$setEventBypass(boolean)`; the method string occurs nowhere in the tree or in any jar, no implementor/reference. |
| `Bridge4_18` | constants-only class (`field1..field14` = 0..12, 99); no annotations, no implementors, no references, no strings — the 14 values do not identify a unique MC enum. |
| `Bridge4_19` | empty marker, same `mixinShared/mixinExtra/mixinMain` (light-overlay) family as `Bridge3_9`; no implementor or reference. |
