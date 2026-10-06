# Final naming pass: `bridge/**` junk-stem leftovers

Cluster: remaining junk-stem class names under `src/main/java/com/moonsworth/lunar/bridge/**`.
Dry-run map: `tools/renames/classes-finalbridge.tsv` (59 rows). Method: read each body, grep all
src/main references, and match the jar-side consumers via `javap` (NestHost/InnerClasses, Record
component names, constant-pool strings) to identify the real role before naming.

Validation: `python3 tools/apply_class_renames_aware.py --map tools/renames/classes-finalbridge.tsv`
reports **rows=59 skipped=0 files_touched=190 files_renamed=59**. A scratch copy with the map applied
at HEAD `8e829d2d` compiles to the identical failing-file set as the untouched tree (`Bridge2_35`,
`FramebufferBridge`, 6 error lines each — both pre-existing/concurrent-wave errors), so the map
introduces no new compile failures.

| # | old | new | evidence |
|---|---|---|---|
| 1 | `MixinHelper` | `ClothCloakState` | value class (ResourceLocation + 3 doubles, Lombok getters); only consumer is jar-only BridgeExtension222.bridge$getClothCloakState() (player bridge); Lunar cloth-cloak cosmetic state; zero src/main refs |
| 2 | `MixinHelper$Extension` | `ClickableTextRenderer` | @FunctionalInterface render(GuiGraphics BridgeExtension_9, PoseStack MixinHelper_4) + of(BiConsumer); nested in MixinHelper_13 (now ClickableText); Bridge2.method90(renderer,int,int) builds the label; zero src/main refs |
| 3 | `MixinHelper$Extension3` | `ClickableTextContent` | interface { Component bridge$getComponent(); }; nested in ClickableText (MixinHelper_13); Bridge2.method89(Component) produces it, callers cast to ClickableText; zero src/main refs |
| 4 | `MixinHelper$Type` | `FoilType` | enum NONE/STANDARD/SPECIAL; jar consumer MixinHelper$Extension4.bridge$foilType() (ItemStackRenderState.LayerRenderState); Mojang FoilType values |
| 5 | `MixinHelper2$Type` | `ParticleStatus` | enum ALL(1)/DECREASED(2)/MINIMAL(3) + getProtoId; consumer MixinHelper2_8.bridge$getParticleStatus() (GameSettings); Mojang ParticleStatus |
| 6 | `MixinHelper2$Type2` | `CloudStatus` | enum OFF(3)/FAST(2)/FANCY(1) + getProtoId; consumer MixinHelper2_8.bridge$getCloudStatus() (GameSettings); Mojang CloudStatus |
| 7 | `MixinHelper2$Type3` | `PrioritizeChunkUpdates` | enum NONE(1)/PLAYER_AFFECTED(2)/NEARBY(3)/UNSPECIFIED(0) + getProtoId; consumer MixinHelper2_8.bridge$getPrioritizeChunkUpdates(); Mojang PrioritizeChunkUpdates |
| 8 | `MixinHelper2$Type4` | `TextureFiltering` | enum NONE(0)/RGSS(1)/ANISOTROPIC(2) + getProtoId; consumer MixinHelper2_8.bridge$getTextureFiltering() (GameSettings); OptiFine-style texture filtering mode |
| 9 | `MixinHelper4$Extension` | `TextColorTransform` | @FunctionalInterface int transform(int); MixinHelper4_2.method3(transform) returns a derived colour source; used as the dynamic text-colour argument of GuiGraphics drawString (MixinHelper_4.method14/16/20); zero src/main refs |
| 10 | `MixinHelper7$Type` | `LayeringMode` | enum NO_LAYERING/VIEW_OFFSET_Z_LAYERING/VIEW_OFFSET_Z_LAYERING_FORWARD; builder MixinHelper7_4.method3 sets the layering state; vanilla LayeringStateShard constants |
| 11 | `MixinHelper7$Type2` | `LightingMode` | enum DEFAULT/DIFFUSE_LIGHTING; builder MixinHelper7_4.method10 setter (no-op stub on this version); vanilla diffuse-lighting state selector |
| 12 | `MixinHelper7$Type3` | `LightmapMode` | enum LIGHTMAP/NO_LIGHTMAP; builder MixinHelper7_4.method1 sets the lightmap state; vanilla LightmapStateShard constants |
| 13 | `MixinHelper7$Type4` | `ShadeMode` | enum FLAT/SMOOTH; builder MixinHelper7_4.method9 setter (no-op stub on this version); shade-model state |
| 14 | `MixinHelper7$Type5` | `TexturingMode` | enum DEFAULT_TEXTURING/GLINT_TEXTURING/ENTITY_GLINT_TEXTURING/ARMOR_ENTITY_GLINT_TEXTURING; builder MixinHelper7_4.method6 sets the texturing state; vanilla TexturingStateShard constants |
| 15 | `MixinHelper7$Type6` | `OutputTarget` | enum MAIN_TARGET/OUTLINE_TARGET/TRANSLUCENT_TARGET/PARTICLES_TARGET/WEATHER_TARGET/ITEM_ENTITY_TARGET; held by MixinHelper7$Data9 (output state) via method4/method6; vanilla RenderType output targets |
| 16 | `MixinHelper7$Type7` | `OverlayMode` | enum OVERLAY/NO_OVERLAY; builder MixinHelper7_4.method2 sets the overlay state; vanilla OverlayStateShard constants |
| 17 | `MixinHelper7$Type8` | `OutlineProperty` | enum NONE/IS_OUTLINE/AFFECTS_OUTLINE with getName() -> none/is_outline/affects_outline; passed to builder create method15; Mojang RenderType.OutlineProperty |
| 18 | `MixinHelper7$Data7` | `OutlineState` | immutable flags + ResourceLocation texture with factories for outline/affects-outline states (method3/method4) and textured states (method1/method2); builder MixinHelper7_4.method8 outline-state setter; zero src/main refs |
| 19 | `MixinHelper7$Data8` | `LineState` | float lineWidth + isDefault, defaults to 1.0F (method1) with custom-width factory method2; builder MixinHelper7_4.method7 line-state setter; vanilla LineStateShard shape; zero src/main refs |
| 20 | `Bridge$Data` | `BridgeGenerationException` | RuntimeException("Exception occurred during bridge gen stage " + stage) wrapping a BytecodeEmitter; only thrown from jar-only Bridge_67 (ichor bridge generation task); zero src/main refs |
| 21 | `Bridge$Extension` | `RenderPipelineSnippet` | read-only RenderPipeline parameter set (vertex/fragment shader, shaderDefines, samplers, uniforms ShaderUniformDeclaration, blend/depth/polygon/cull/write masks, vertexFormat+mode); MixinHelper4_6 (RenderPipeline.Builder) method42/43 withSnippet/snippet; MixinHelper_2 error string "register vanilla snippets"; zero src/main refs |
| 22 | `Bridge$Type` | `NativeImageFormat` | enum RGBA/RGB/LUMINANCE_ALPHA/LUMINANCE with component/offset flags + supportedByStb and getStbFormat(int); consumer Bridge_10.bridge$getFormat() (NativeImage); Mojang NativeImage.Format |
| 23 | `Bridge$Type2` | `IndexType` | enum BYTE(1)/SHORT(2)/INT(4) + bytes + least(int) version switch; consumer Bridge2_5.bridge$getIndexType() (IndexedMesh/MeshData); Mojang VertexFormat.IndexType |
| 24 | `Bridge10$Data` | `GlyphAtlasEntry` | record components glyphStart;glyphEnd;inBaseAtlas;index (from jar Record attribute); returned by Bridge10_2.bridge$getCodepointCharData(int) and method20(String) (font glyph lookup); zero src/main refs |
| 25 | `Bridge12$Extension` | `LegacyRenderSystemBridge` | @VersionGate(max=5) extension of Bridge12 adding matrix-stack/GL-state ops (method6, method2(int), method3(int), MatrixStackBridge method7, method8); Bridge12.method84 accessor; zero src/main refs |
| 26 | `Bridge12$Extension2` | `ModernRenderSystemBridge` | @VersionGate(min=6) extension of Bridge12 adding modern buffer ops (BufferBuilder creation, PoseStack Pose, GpuBuffer create via GlObjectBridge, vertex-format/lighting queries); Bridge12.method83 accessor; zero src/main refs |
| 27 | `Bridge12$Type` | `GpuBufferUsage` | enum COPY_DST(8)/COPY_SRC(16)/VERTEX(32)/INDEX(64)/UNIFORM(128)/STORAGE_BUFFER(1073741824) with mask field; varargs arg of Bridge12.method89(String,long,Usage...) -> GlObjectBridge (GpuBuffer creation); zero src/main refs |
| 28 | `BridgeIterator5$Data` | `NamedMethodSignature` | record components name;methodSig (from jar Record attribute); entry of BridgeIterator5's static Map<String,List<...>> used by the ichor bytecode emitter; orphan lazy class; zero src/main refs |
| 29 | `BridgeHandler` | `LineBufferBuilder` | implements BufferBuilderBridge; draws 2-vertex LINES from a FluentTessellatorBridge with per-vertex colours (method2/3/4/5/6 + end/endVertex). zero src/main refs |
| 30 | `Bridge8Handler$Type` | `FramebufferAttachmentType` | enum COLOR/DEPTH; type accessor of record Bridge8Handler(attachment), a marker for a framebuffer colour/depth texture (implements empty GpuTexture marker Bridge8_7, cf. FramebufferBridge.bridge$getColorTexture); zero src/main refs |
| 31 | `Bridge8Handler2` | `TextureManagerBridge` | extends Bridge13_3; bridge$loadTexture/bindTexture/deleteTexture/getTexture/getDynamicTextureLocation/getTextureMap over ResourceLocation+AbstractTexture; returned by MinecraftBridge.bridge$getTextureManager() and implanted by legacy/mixin/TextureManagerMixin |
| 32 | `Bridge17Extension` | `DelegatingMultiBufferSourceBridge` | extends MultiBufferSourceBridge (jar Bridge17 = BufferSource); adds bridge$setDelegate(MultiBufferSourceBridge, Function<RenderTypeBridge, VertexConsumerBridge>); zero src/main refs |
| 33 | `Bridge17Extension_2` | `BatchMultiBufferSourceBridge` | extends MultiBufferSourceBridge; batch lifecycle bridge$endBatch()/bridge$endBatch(RenderTypeBridge)/bridge$endLastBatch() (@VersionGate min=6 max=38)/isOutlineBufferSource(); returned by Bridge8_2.bridge$getBufferSource() (GuiGraphics) and Bridge15_2.bridge$bufferSource(); vanilla MultiBufferSource.BufferSource facet |
| 34 | `BridgeType` | `VertexSortingMode` | enum DISTANCE_TO_ORIGIN/ORTHOGRAPHIC_Z; MeshDataBridge.bridge$sortQuads(VertexConsumerBuilder, VertexSortingMode) and Bridge12$Extension2.method7; Mojang VertexSorting static modes |
| 35 | `BridgeType$Type` | `UniformScalarType` | enum BOOL/INT/UINT/FLOAT/DOUBLE; getScalarType() of jar-only BridgeType_15 (GLSL uniform type BOOL..MATRIX4X4/TEXEL_BUFFER) used by ShaderUniformDeclaration records; zero src/main refs |
| 36 | `BridgeType2_2` | `ConnectionProtocol` | enum HANDSHAKING/PLAY/STATUS/LOGIN/CONFIGURATION; packet send/receive factory arg (Bridge_16 method1/2, Bridge3_21.bridge$write) and PacketFactory/PacketBridge in src/main; Mojang ConnectionProtocol |
| 37 | `BridgeType2_3` | `GlBufferTarget` | enum of raw GL buffer bind targets (VERTICES 34962, INDICES 34963, PIXEL_PACK/UNPACK, COPY_READ/WRITE, UNIFORM, SHADER_STORAGE...) implementing GlEnum with USAGE_* flag constants; zero src/main refs |
| 38 | `BridgeType2_4` | `LunarItemType` | enum EMPTY/SKULL/ELYTRA/SHIELD/SWORD/PICKAXE/AXE/SHOVEL/HOE/BLOCK/ARMOR/POTION.../UNKNOWN with fromPath(String) + isAnyPotion; returned by MixinHelper_14.bridge$getLunarItemType() and used by cosmetics ItemRenderMaterial/RenderContext |
| 39 | `BridgeType2_5` | `TextureQuality` | enum LOW/FULL; async texture resource cache quality tier (AsyncResource.get(TextureQuality), AsyncResourceManager "Low Quality"/"Full Quality" buckets, CloakTextureSlicer downscale) |
| 40 | `BridgeType2_6` | `LegacyVertexFormat` | VertexFormatBridge impl carrying hasUV/hasColor; TessellatorMixin.bridge$begin(...) casts to it for the 1.7/1.8 path ("Illegal VertexFormatBridge used in 1.7"); lombok equals/hashCode |
| 41 | `BridgeType2_7` | `SoundSource` | enum MASTER/MUSIC/RECORDS/WEATHER/BLOCKS/HOSTILE/NEUTRAL/PLAYERS/AMBIENT/VOICE with lower-case name; SoundHandlerBridge.bridge$play(ResourceLocation, SoundSource, ...); Mojang SoundSource |
| 42 | `BridgeType2_8` | `BlendFactor` | enum of GL blend factors (ZERO/ONE/SRC_ALPHA/ONE_MINUS_SRC_ALPHA/SRC_COLOR/DST_*/CONSTANT_*/SRC1_*) implementing GlEnum with isSourceFactor/isDestFactor; the 4 factors of BlendFunctionBridge and RenderPipeline blend state |
| 43 | `BridgeType2_9` | `GlBlendFactor` | enum of raw GL_* blend factors with gl id + fromId(int); GlStateManagerBridge.method2/method4 blendFunc args (BridgeExtension2_4/3_4 delegate to Bridge12.method15/18) |
| 44 | `BridgeType3_2` | `LunarItemMaterial` | enum WOOD/STONE/IRON/GOLD/DIAMOND/NETHERITE/COPPER/UNKNOWN with fromRegistryName/fromMaterial (EMERALD->DIAMOND); returned by MixinHelper_14.bridge$getLunarItemMaterial() and consumed by cosmetics ItemRenderMaterial/RenderContext |
| 45 | `BridgeType3_3` | `GlMatrixMode` | enum GL_MODELVIEW(5888)/GL_PROJECTION(5889)/GL_TEXTURE(5890)/GL_COLOR(6144) with fromId(int); GlStateMutatorBridge.method8 / ShaderStateHelper / GlintColorizer matrixMode argument |
| 46 | `BridgeExtension$Type` | `UseAnim` | enum NONE/EAT/DRINK/BLOCK/BOW/SPEAR/CROSSBOW/SPYGLASS/TOOT_HORN/BRUSH/BUNDLE/TRIDENT with isConsumable(); returned by BridgeExtension_4.bridge$getItemUseAction() (ItemStack); Mojang UseAnim |
| 47 | `BridgeExtension2_2` | `EntityLivingStateBridge` | extends EntityRenderStateBridge; living-entity render state data (invisible/sleeping/bedOrientation/deathTime/hurtTime/bodyRot/scale/isBaby/inWater/inLava/glowing/headItem ItemStackRenderState/redOverlay); base of EntityLivingBridge; 11 src/main consumers (MobSize, Nametag, layer renderers) |
| 48 | `BridgeExtension2_4` | `NoOpGlStateManagerBridge` | no-op GlStateManagerBridge implementation (all methods empty/false); instantiated by RenderContextLegacyTransform.method29() and RenderContextModernTransform.method29() when no GL state manager is needed |
| 49 | `BridgeExtension2_5` | `EntityLivingBridge` | extends Bridge + EntityLivingStateBridge; living-entity bridge (health/potions/equipment/last attacker/display name/swimming/elytra/hostile); supertype of EntityMobBridge/EntityMagmaCubeBridge/EntityBatBridge/EntitySheepBridge; 58 src/main consumers |
| 50 | `BridgeExtension2_6` | `NetHandlerServerBridge` | extends INetHandlerBridge { bridge$send(PacketBridge); }; returned by EntityPlayerMPMixin.bridge$getNetHandlerServer() (= vanilla NetHandlerServer/connection); src/main legacy mixin + Skyblock helpers |
| 51 | `BridgeExtension2_7` | `ModelPlayerBridge` | extends ModelBipedBridge; player model parts (cloak/left+right sleeve/pants/jacket renderers, isSlim, isMainModel); RenderPlayerBridge.bridge$getMainModel() and LayerRenderer/HatLayer/BodyOverlay layer renderers; vanilla ModelPlayer |
| 52 | `BridgeExtension2_8` | `ContainerRepairBridge` | extends ContainerBridge; anvil/repair container (repairedItemName, input/output slots, maximumCost/materialCost, player(), detectAndSendChanges); vanilla ContainerRepair/AnvilMenu |
| 53 | `BridgeExtension2_9` | `EntityItemRenderStateProvider` | extends EntityRenderStateProvider; adds bridge$getItemStack(); used by SkyblockLilyPadHelper/SkyblockGalateaMobHighlight for items in entity render states; medium confidence (provider facet of the dropped-item entity) |
| 54 | `BridgeExtension2_10` | `EntityBatBridge` | empty extension of EntityLivingBridge implemented by legacy/mixin/EntityBatMixin; bat entity marker consumed by SkyblockDungeonBatHelper |
| 55 | `BridgeExtension22_2` | `EntitySheepBridge` | empty extension of EntityLivingBridge implemented by legacy/mixin/EntitySheepMixin; sheep entity marker consumed by SkyblockMageBeam |
| 56 | `BridgeExtension3_2` | `EntityItemExtensionBridge` | extends EntityItemStateBridge + EntityRenderStateBridge; dropped-item facet (bobOffset, bakedModel, entityId, rotationYaw, onGround); implemented by EntityItemBridge and consumed by ItemPhysics/Items2d/EventRenderEntityItem/ItemEntityRendererBridge |
| 57 | `BridgeExtension3_3` | `EntityPaintingBridge` | empty extension of the Entity bridge implemented by legacy/mixin/EntityPaintingMixin (extends EntityHanging); painting entity marker |
| 58 | `BridgeExtension3_4` | `DelegatingGlStateManagerBridge` | GlStateManagerBridge implementation delegating every call to a Bridge12 (RenderSystem/GL facade) with a boolean switch (method1); counterpart of NoOpGlStateManagerBridge; zero src/main refs |
| 59 | `BridgeExtension222$Type` | `PlayerType` | enum DUMMY_SELF/DUMMY_MANNEQUIN/MANNEQUIN/SELF/OTHER; returned by BridgeExtension222.bridge$getPlayerType() (player entity bridge); zero src/main refs |

## Skipped / not renamed

* `com.moonsworth.lunar.bridge.BridgeExtension` — **mis-moved duplicate, unsafe to rename in place.**
  The file declares `interface BridgeExtension extends MovementInputBridge` (an empty movement-input
  extension), but 203 files in `src/main` reference `BridgeExtension` meaning the jar-only *entity*
  bridge of the same FQN (`bridge$getPosX`, `bridge$isSneaking`, `bridge$getWorld`, ...). The applier
  would rewrite all 203 entity references to the movement-input name, breaking e.g.
  `InterpolatedCameraBridge` (`this.field2.method3()` / `bridge$getPosX`). It was moved here from
  `bridge.horsestats` by the moves-misc wave, shadowing the jar class. Fix in the structural/move pass
  (move it back out of `bridge` or drop it), not with a rename row.
* `com.moonsworth.lunar.bridge.Bridge18$Type` — undeterminable. Enum `BASE/ARB/EXT` whose only accessor
  is `Bridge18.method27()`; `Bridge18` has no implementors anywhere (set through `Bridge.method23`,
  never read), so the GL flavour it selects cannot be established from evidence. Left for rescue.
* `Bridge2Handler2`, `Bridge2Handler_2`, `Bridge9Handler*` — do not exist in `src/main/java`
  (jar-only leftovers of the jar-coupled bridge SPI); nothing to rename.
* `Bridge3$Extension` → already renamed by the concurrent bridge34 wave to `ChatLineExtensionBridge`;
  `Bridge4$Data` → already renamed to `SourceImage` (jar Record attribute `pixels;width;height`).
  Both were on the offender list but are gone from the tree before this map.
* `MixinTargetMember`, `BridgeMemberTarget`, `BridgeParameterTarget`, `BridgeVersionTarget` — already
  correct names from earlier waves (`MixinTargetMember` ← `Bridge_13` in classes-bridge3;
  the three annotations ← `Annotation`/`Annotation2`/`Annotation3` in classes-07/audit01). Verified
  as runtime annotations with `BridgeVersionTarget[]` members; no rows needed.

## Notable findings

* The `MixinHelper` stem is a deobfuscation alias, not a class family: the jar NestHost attributes show
  `MixinHelper$Extension{,3}` nest in `MixinHelper_13` (= `ClickableText`), `MixinHelper$Type` nests in
  `MixinHelper_14` (= `ItemStackRenderState`), `MixinHelper2$Type*` in the GameSettings/Options bridge,
  `MixinHelper4$Extension` in the dynamic text-colour source used by GuiGraphics drawString, and
  `MixinHelper7$Type*/Data*` in the jar-only RenderType-style state builder. `MixinHelper.java` itself
  is an unrelated orphan (cloth-cloak state returned by `BridgeExtension222.bridge$getClothCloakState()`).
* `MixinHelper7$Type*`/`Data7`/`Data8` mirror vanilla RenderStateShard categories 1:1 (NO_LAYERING,
  LIGHTMAP/NO_LIGHTMAP, OVERLAY/NO_OVERLAY, *_TEXTURING, line width, outline flags+texture, output
  targets, NONE/IS_OUTLINE/AFFECTS_OUTLINE), so they were named after the shard category they select.
* `Bridge$Extension` is `RenderPipeline.Snippet`: builder `MixinHelper4_6` exposes `withSnippet`-style
  `method42/43`, and the jar registry string is "Attempting to register vanilla snippets on a version
  which already defines them" (high confidence).
* `Bridge$Type` = `NativeImage.Format` (RGBA/RGB/LUMINANCE*, `getStbFormat`), `Bridge$Type2` =
  `VertexFormat.IndexType` (BYTE/SHORT/INT + `least(int)`), `BridgeType$Type` = the GLSL scalar type
  of the jar-only uniform-type enum `BridgeType_15`.
* Three GL buffer enums needed disambiguation: `BridgeType2_3` = raw GL bind targets (`GlBufferTarget`),
  `Bridge12$Type` = GpuBuffer usage flags (`GpuBufferUsage`), and the already-named `BufferUsage` =
  GL usage hints (DYNAMIC_WRITE/...). Likewise `BridgeType2_8` (`BlendFactor`, high-level) vs
  `BridgeType2_9` (`GlBlendFactor`, raw GL_* ids for GlStateManager blendFunc).
* `BridgeHandler` is a 1.8 line-mode `BufferBuilderBridge`; it had zero references (orphan) but draws
  2-vertex LINES with per-vertex colours, hence `LineBufferBuilder`.
* `Bridge8Handler` (record `attachment`) is a framebuffer colour/depth texture marker implementing the
  empty GpuTexture marker `Bridge8_7` (cf. `FramebufferBridge.bridge$getColorTexture`) — named
  `FramebufferAttachment` + `FramebufferAttachmentType` (COLOR/DEPTH).
* Concurrency note: HEAD moved while this pass was running (3b15d656 bridge34 wave, 2bdfe9c6
  ichor/forge/loader wave, 8e829d2d clientA wave), which renamed two of the other listed offenders and
  changed the compile baseline from 2 to 6 error lines. The map was re-validated (dry-run + scratch
  compile) against the tree at 8e829d2d.
