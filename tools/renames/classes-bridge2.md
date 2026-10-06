# `Bridge2*` cluster — class rename map (bridge package)

**Scope.** Every top-level `com.moonsworth.lunar.bridge.Bridge2*` file (46 files matching
`Bridge2*.java`, incl. the flattened nested names `Bridge2$Data`, `Bridge2$Extension`,
`Bridge2$Extension2`). The cluster brief said 45 files; the glob finds 46 — all were inspected.

**Result.** 45 rows, dry-run `skipped=0`, 247 files touched, 45 files renamed.
Skipped/undeterminable: `Bridge2_18` (see bottom).

Evidence sources, in order of weight:

1. implementors found in `src/main/java` / `libs/lunar-renamed-classes.jar`
   (`implements` tables + `@Mixin` targets, parsed from the class files);
2. call sites in the tree;
3. member signatures;
4. original flattening package (`tools/mappings-snapshot/normalize-renames.tsv`) and the
   naming conventions already applied to sibling markers (`LightingMarkerBridge`,
   `AuxCoreMarkerBridge`, …).

Dry run:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-bridge2.tsv
[aware-renames] 45 rows (0 nested); 6591 java files
[aware-renames] rows=45 skipped=0 files_touched=247 files_renamed=45 mode=dry-run
```

## Map

| old | new | evidence |
| --- | --- | --- |
| Bridge2_2 | ShaderDefinesBridge | `Map<String,String> bridge$values()` + `Collection<String> bridge$flags()`; returned by `Bridge$Extension.bridge$shaderDefines()` / `RenderPipelineBridge.bridge$shaderDefines()`; built by `Bridge2$Extension2.bridge$define(...)`; implemented by `legacy/wrapper/ShaderBridgeImpl` |
| Bridge2_3 | ServerStatusResponseSerializerBridge | empty marker; implemented by `SerializerMixin` + `Serializer_v1_7Mixin`, both `@Mixin(ServerStatusResponse$Serializer)`, wrapping `deserialize` to read `lcServer` |
| Bridge2_4 | EntityCreeperBridge | `EntityCreeperMixin` (`@Mixin EntityCreeper`) implements `bridge$getMaxSwell/getSwell/setSwell/setOldSwell/setPowered/setIgnited` |
| Bridge2_6 | LightingExtraMarkerBridge | empty marker, no refs; flattened from `bridge/mixinShared/mixinExtra/mixinMain` (light family, sibling `Bridge_47 = LightingMarkerBridge`) |
| Bridge2_7 | DataWatcherBridge | `void bridge$updateObject(int,Object)`; `DataWatcherMixin` (`@Mixin DataWatcher`) implements it, `EntityMixin.bridge$getDataWatcher()` returns it |
| Bridge2_8 | EntityRenderConfigurationBridge | `bridge$getEntityConfigurationName()`, `bridge$getEntity()`, `bridge$extractRenderStates(BridgeExtension)`; no refs in tree/jar — named from members |
| Bridge2_9 | AuxMarkerBridge | empty marker, no refs; flattened from top-level `bridge/mixinAux` (siblings `AuxCoreMarkerBridge`, `AuxMixinMarker`) |
| Bridge2_10 | LayerCapeBridge | `LayerCapeMixin` (`@Mixin LayerCape`) implements `bridge$render(entity, 7 floats)` → `doRenderLayer` |
| Bridge2_11 | BossInfoBridge | percent / `Optional<Component>` name / color / darkenSky / render; `legacy/wrapper/Bridge2Handler_2` implements it over `net.minecraft.client.gui.BossInfoClient` |
| Bridge2_13 | AdventureComponentBridge | `moonBridge$asBridgeComponent()` → `Bridge2_42` (legacy chat duck); `AbstractComponentMixin` (`@Mixin adventure AbstractComponent`) implements it; `TextBridge.asBridge(Component)` casts to it |
| Bridge2_14 | SamplerResourceBridge | sole method `Bridge8Extension bridge$getSamplerResource()` (texture/sampler resource); referenced only by `client/fov/Fov3$Data2` |
| Bridge2_15 | BlendFunctionBridge | 4 `BridgeType2_8` GL blend factors + static presets; returned by `Bridge$Extension.bridge$blendFunction()` / `RenderPipelineBridge.bridge$blendFunction()` |
| Bridge2_16 | AnimationFrameBridge | `AnimationFrameMixin` (`@Mixin net.minecraft.client.resources.data.AnimationFrame`) implements `bridge$hasNoTime/getFrameTime/getFrameIndex` |
| Bridge2_17 | BlockStateBridge | `BlockStateBaseMixin` (`@Mixin BlockStateBase`) implements it; `ChunkBridge.bridge$getBlockState(x,y,z)` returns it; `getBlock/getBlockShape/getInteractionShape/getCollisionShapeMaxY/getRenderShape/getBites/getStringProperties` |
| Bridge2_19 | CameraBridge | pos/yaw/pitch + `bridge$setEyeHeight`; returned by `bridge$getCamera()` on render context / GameRenderer / EntityRenderDispatcher; `Bridge2$Data` interpolates entity position into it |
| Bridge2_20 | GuiButtonBridge | `GuiButtonMixin` (`@Mixin GuiButton`) implements it; carried by `EventRenderButton` |
| Bridge2_21 | ItemsBridge | 99 `Item` getters; `Bridge2Iterator` implements them as `Items.carrot_on_a_stick`, `Items.blaze_rod`, `Items.diamond_sword`, …; `method22(String)` = `Item.getByNameOrId`, default `method18()` = `method22("golden_sword")` |
| Bridge2_22 | RenderTypeTrackerBridge | `bridge$pushTracker(Consumer<RenderTypeBridge>)` / `bridge$popTracker()`; `BridgeExtension2_11` (render context) does `instanceof Bridge2_22` then pushes/pops the tracker |
| Bridge2_23 | TessellatorBridge | `TessellatorImpl extends Tessellator implements Bridge2_23`; `method2(int)` = begin/draw mode, `method3()` = `draw()`, `bridge$isDrawing()` |
| Bridge2_25 | PlayerControllerBridge | `PlayerControllerMPMixin` (`@Mixin PlayerControllerMP`) implements attack/useItemOn/useItem/isHittingBlock/…; via `Bridge5_12.bridge$getPlayerController()` |
| Bridge2_27 | GuiContainerCreativeBridge | `GuiContainerCreativeMixin` (`@Mixin GuiContainerCreative`) implements `bridge$isInventory()` as `selectedTabIndex == 11` |
| Bridge2_28 | WindowsMouseBridge | `WindowsMouseMixin` (`@Mixin(targets = "org.lwjgl.opengl.WindowsMouse")`) implements `bridge$isGrabbed/handleMouseMoved/handleMouseMovedRelative` |
| Bridge2_29 | ItemModelMesherBridge | `ItemModelMesherMixin` (`@Mixin ItemModelMesher`) implements `bridge$getItemModel(ItemStack)` |
| Bridge2_30 | FoodStatsBridge | `FoodStatsMixin` (`@Mixin FoodStats`) implements `bridge$getFoodLevel/getSaturationLevel` |
| Bridge2_31 | BlockEntityRenderDispatcherBridge | `Bridge5_12.bridge$getBlockEntityRenderDispatcher()`; `bridge$getBlockEntityRenderer(BlockEntityBridge)` + `bridge$render(...)` |
| Bridge2_32 | DrawBufferBridge | render-context `getBuffer(renderType)` (`BridgeExtension_9.method10(RenderTypeBridge) -> Bridge2_32`); fluent vertex chain + `method17(BufferMode)` flush; adapters wrap the legacy buffer (`Bridge4_11`) and the modern `VertexConsumer` (`Bridge4_6`) |
| Bridge2_33 | PlayerInfoBridge | game profile / texture id / latency / display name / skin / `formatName()`; implemented by **both** `NetworkPlayerInfoMixin` and `GuiPlayerInfoMixin` (1.7 `GuiPlayerInfo`) |
| Bridge2_34 | JsonToNBTBridge | `CompoundTagBridge method1(String)`; `legacy/wrapper/mixin/Bridge2Handler` implements it with `JsonToNBT.getTagFromJson` |
| Bridge2_35 | BridgeClassGeneratorBridge | holds bridge interface name + `@Bridge` annotation + member targets; `method2(...)` adds the interface and generates the method bodies ("Failed to generate bridge implementation for …") |
| Bridge2_36 | CustomInjectProgramBridge | `lunar$isCustomInjectProgram()` / `lunar$markCustomInjectedProgram()`; shader family (`mixinShared/mixinSupport`) |
| Bridge2_37 | PropertyBridge | `@FunctionalInterface` `bridge$getName/getValue/getSignature`; `PropertyMapBridge.bridge$get(String) -> List<Bridge2_37>`; read for the `"textures"` skin property = authlib `Property` |
| Bridge2_39 | KeyboardBridge | `legacy/Bridge2Handler` implements it with `Keyboard.isKeyDown/getKeyName/enableRepeatEvents` + `Mouse.isButtonDown`; `BridgeType_8.KEY_*` codes |
| Bridge2_40 | InventoryBridge | `legacy/wrapper/Bridge2Handler` wraps `IInventory` (`getStackInSlot/setInventorySlotContents/getDisplayName/getInventoryStackLimit`); `ContainerChestMixin.bridge$getLowerInventory()` returns it |
| Bridge2_41 | GlStateMutatorBridge | GL state mutations: blendFunc / alphaFunc / tryBlendFuncSeparate / depthFunc / cullFace / shadeModel / enable-disable ×18 / setActiveTexture; base of `GlStateManagerBridge` and `BridgeExtension_13` |
| Bridge2_43 | EntityRenderDispatcherBridge | `Bridge5_12.bridge$getEntityRenderDispatcher()`; `RenderManagerMixin`/`2` (`@Mixin RenderManager`) implement renderPos / playerView / skinMap / `renderEntityWithPosYaw` / `renderShadow` / `getCamera` / `getRenderer` |
| Bridge2_44 | MeshDataBridge | `vertexBuffer()/indexBuffer()/sortQuads(Bridge4Extension,BridgeType)/close()`; produced by `Bridge4Extension` (BufferBuilder) `bridge$buildOrThrow()` = `MeshData` |
| Bridge2_45 | TextFieldStateBridge | mutable text input state (`selected`, `width/height`, `text`, max length, scroll offset, selection/cursor); created by `TextFieldRenderer` and passed to the GuiContainer `lunar$handleKeyEventOnState` / `lunar$handleMousePressOnState` |
| Bridge2_46 | ModelRendererBridge | `ModelRendererMixin` (`@Mixin ModelRenderer`) implements rotateAngle/rotationPoint/textureOffset/showModel + `bridge$render(float)` |
| Bridge2_47 | SoundManagerBridge | `SoundManagerMixin` (`@Mixin SoundManager`) implements `setPlayingSoundVolume/pause/resume`; returned by `Bridge3_11.bridge$getSoundEngine()` |
| Bridge2Handler2 | ComponentAdapterBridge | `Component` view over a legacy `Bridge2_42` (`children()=List.of()`, `style()=Style.empty()`); produced by `TextBridge.asAdventure` |
| Bridge2Handler_2 | DelegatingComponentBridge | `Component` delegating everything to a wrapped adventure component + width cache; produced by `TextBridge.asAdventureCached` |
| Bridge20Extension | ThreadDownloadImageDataBridge | `getFile/requestContent/setMimeType/setImageFound`; `ThreadDownloadImageDataMixin`+`2` (`@Mixin ThreadDownloadImageData`) implement it |
| Bridge2$Data | InterpolatedCameraBridge | implements `Bridge2_19` (Camera): lerps an entity's previous/current pos + yaw/pitch by partial ticks; `method1()` = view entity sleeping |
| Bridge2$Extension | RenderPipelineRegistrarBridge | `@FunctionalInterface register(RenderPipelineBridge...)`; registration hook used by the colorsaturation/fog families and `Bridge2Factory$Data` |
| Bridge2$Extension2 | ShaderDefinesBuilderBridge | builder for `Bridge2_2`: `bridge$copyFrom(...)`, `bridge$define(name[,value])`, `bridge$build()`; implemented by `Bridge2Factory$Data` |

## Skipped / undeterminable

| old | why |
| --- | --- |
| `Bridge2_18` | Only members are `float bridge$red()/bridge$green()/bridge$blue()/bridge$alpha()`. **No references at all** — not in `src/main/java`, not in `libs/lunar-renamed-classes.jar` (exact constant-pool scan: 0 referrers, 0 implementors). Its flattening package (`mixinShared/mixinInternal`) is the particle family where sibling `Bridge4_12` already exposes the same four floats plus particle type / `isFirework` (`EntityFX`), so the target is ambiguous between a colour-only particle view and an unrelated RGBA holder. Left unrenamed rather than guessed. |

## Notable naming constraints hit

* `GlStateManagerBridge` already exists (composite of `GlStateQueryBridge` + `Bridge2_41`), so the
  mutation half got `GlStateMutatorBridge`.
* `NetworkPlayerInfoBridge` already exists (cape/uuid duck), so the profile/latency/skin duck
  shared by `NetworkPlayerInfo` **and** `GuiPlayerInfo` got `PlayerInfoBridge`.
* `ShaderProgramBridge` already exists in `bridge/optifine` (`Program.getId()`), so the
  custom-injected-program marker got `CustomInjectProgramBridge`.
* `VertexConsumerProvider` already exists and `Bridge4_6`'s members are literally
  `VertexConsumer` (`bridge$vertex/color/uv/overlayCoords/normal/endVertex`), so the unified
  immediate/batched draw interface `Bridge2_32` got `DrawBufferBridge` instead of
  `VertexConsumerBridge`.
