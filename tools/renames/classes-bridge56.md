# Bridge5* / Bridge6* cluster (`com.moonsworth.lunar.bridge`)

Naming pass over the 44 top-level `Bridge5*` and 19 `Bridge6*` classes.
Each name mirrors an existing, already-renamed class in the same package
(`Vec3iBridge`, `ContainerBridge`, `EntityRendererBridge`, `GuiChestBridge`, ...).

Dry-run: `python3 tools/apply_class_renames_aware.py --map tools/renames/classes-bridge56.tsv`
→ **rows=57, skipped=0, files_touched=326, files_renamed=57 (0 conflicts)**.

## Renames

| old | new | evidence |
|---|---|---|
| `Bridge5$Data` | `PoseStackDataBridge` | implements `PoseStackBridge` by delegating `translate`/`scale`/`rotateDegrees`; compiled as nested `Data` of the PoseStack bridge (`InnerClasses Data of Bridge5_16`, `NestHost Bridge5_16`) |
| `Bridge5_2` | `NbtIoBridge` | single member `method1(InputStream) = CompoundTagBridge` (NBT read); `legacy/wrapper/mixin/Bridge5Handler` delegates to `CompressedStreamTools.readCompressed` |
| `Bridge5_3` | `AlcBridge` | OpenAL ALC facade: `alcCaptureOpenDevice`/`alcCaptureStart`/`alcGetInteger`/`alcGetString`/`alcCreateContext`/`alcLoopbackOpenDeviceSOFT`/`alcRenderSamplesSOFT`; `legacy/wrapper/Bridge5Handler` implements it over JNA `OpenALNative` |
| `Bridge5_4` | `ItemMapBridge` | `bridge$getMapData(ItemStack,World)` + `bridge$getMapId`; `ItemMapMixin @Mixin(ItemMap)` implements it via `getMapData` |
| `Bridge5_5` | `LightOverlayTrackerBridge` | relative block coords packed as `(x&15)<<8 \| (y&15)<<4 \| z&15` into `ShortArrayList`/`ByteArrayList`; returned by `LightOverlayTrackerProvider.bridge$lightOverlayTracker()`; implemented by `WorldRendererMixin`/`RenderChunkMixin` |
| `Bridge5_6` | `EnumPlayerModelPartsBridge` | `bridge$getMask()`/`bridge$getId()` over vanilla `partMask`/`partName`; `EnumPlayerModelPartsMixin @Mixin(EnumPlayerModelParts)` |
| `Bridge5_7` | `TextureUtilBridge` | `glGenTextures`/`uploadTextureImage`/`deleteTexture`; `legacy/wrapper/Bridge5Handler_2` implements it over `net.minecraft.client.renderer.texture.TextureUtil` |
| `Bridge5_10` | `TypeConversionUtilBridge` | static widening coercions `float<->double`, `int->long/float`, `int[]->float[]`; `TypeConversionCoercion` looks it up and complains "Both %s and %s in **TypeConversionUtil** share the signature %s" |
| `Bridge5_12` | `MinecraftBridge` | Minecraft client facade (~160 members): `bridge$getPlayer`/`bridge$getWorld`/`bridge$getTextureManager`/`bridge$getSessionService`/`bridge$displayScreen`/`bridge$joinWorld`; `Ref.method3()` returns it |
| `Bridge5_14` | `MixinTargetVersionBridge` | `version()` + `Bridge6_6[]` mixin-member resolvers, one entry per game version; `Bridge4_2.method3(Config)` picks the matching entry for the current `Config` |
| `Bridge5_15` | `GuiBridge` | root of the 1.8 `Gui` hierarchy: `GuiScreen` (`Bridge5Extension6`), `GuiIngame` (`Bridge5Extension9`), `GuiNewChat` (`Bridge5Extension4`) and `GuiPlayerTabOverlay` (`Bridge5Extension`) all extend it; default `method1() = Optional<String>` screen title (inference from hierarchy, see notes) |
| `Bridge5_19` | `RenderItemBridge` | `bridge$renderItemAndEffectIntoGUI`/`bridge$renderItem`/`bridge$renderModel`/`bridge$getItemModelShaper`/`bridge$setZLevel`; `RenderItemBridgeMixin @Mixin(RenderItem)` implements it |
| `Bridge5_20` | `EntityAgeableBridge` | `bridge$isBaby()` over vanilla `isChild()`; `EntityAgeableMixin @Mixin(EntityAgeable)` |
| `Bridge5Extension` | `GuiPlayerTabOverlayBridge` | `bridge$getHeader()`/`bridge$getFooter()`, also returned by `bridge$getTabList()`; `GuiPlayerTabOverlayMixin @Mixin(GuiPlayerTabOverlay)` implements it |
| `Bridge5Extension10` | `GuiScreenResourcePacksBridge` | `bridge$handlePackSwapList()` + title "Resource Packs"; `GuiScreenResourcePacksMixin extends GuiScreen` implements it |
| `Bridge5Extension2_3` | `GuiControlsBridge` | title "Controls"; `GuiControlsMixin @Mixin(GuiControls)` implements it |
| `Bridge5Extension_2` | `ServerLinksScreenBridge` | sole member default `method1()` returns `Optional.of("Server Links")` (driver screen label) |
| `Bridge5Extension3` | `EntityMagmaCubeBridge` | `bridge$getSize()` over `getSlimeSize()`; `EntityMagmaCubeMixin @Mixin(EntityMagmaCube)` implements it |
| `Bridge5Extension3_2` | `GuiEditSignBridge` | `bridge$getEditLine()`/`bridge$getLine(int)` + title "Edit Sign"; `GuiEditSignMixin @Mixin(GuiEditSign)` |
| `Bridge5Extension_3` | `GuiContainerBridge` | `bridge$inventorySlots`/`bridge$title`/`bridge$getSlotAtPosition`/`bridge$clickSlot` + title "GuiContainer"; `GuiContainerMixin extends GuiContainer` implements it |
| `Bridge5Extension4` | `GuiNewChatBridge` | `bridge$addMessage`/`bridge$clearChatMessages`/`bridge$getChatOpen`/`bridge$rescaleChat` + title "New Chat"; `GuiNewChatMessageMixin @Mixin(GuiNewChat)` |
| `Bridge5Extension42` | `EntityWitherSkeletonBridge` | `EntityWitherSkeletonMixin` implements it (`@VersionGate(min=5)`); paired with the PigZombie variant for Inferno Demonlord mob tracking in `SkyblockBlazeSlayer` |
| `Bridge5Extension43` | `EntityPigZombieBridge` | `EntityPigZombieMixin @Mixin(EntityPigZombie)` implements it |
| `Bridge5Extension4_2` | `EntityMobBridge` | `EntityMobMixin @Mixin(EntityMob)` implements it, supplying `bridge$isHostile()` from `EntityPigZombie.angerLevel` |
| `Bridge5Extension5` | `GuiAchievementBridge` | title "Achievement"; `GuiAchievementMixin @Mixin(GuiAchievement)` implements it (achievement toast) |
| `Bridge5Extension6` | `GuiScreenBridge` | `bridge$drawScreen`/`bridge$setWorldAndResolution`/`bridge$mouseClicked`/`bridge$keyTyped`/`bridge$isShiftKeyDown`; `GuiScreenMixin2 @Mixin(GuiScreen)` implements it |
| `Bridge5Extension62` | `GuiScreenWrapperBridge` | extends the GuiScreen bridge and exposes `method2() = Bridge7_8` wrapped driver gui; `legacy/wrapper/GuiScreenImpl extends GuiScreen` wraps a `Bridge7_8`, `Ref.method31` unwraps it |
| `Bridge5Extension63` | `GuiLanguageBridge` | title "Language"; `GuiLanguageMixin @Mixin(GuiLanguage)` implements it |
| `Bridge5Extension64` | `GuiOptionsBridge` | title "Options"; `GuiOptionsMixin @Mixin(GuiOptions)` implements it |
| `Bridge5Extension65` | `GuiMultiplayerBridge` | title "Server Selector"; `GuiMultiplayerMixin @Mixin(GuiMultiplayer)` implements it |
| `Bridge5Extension662` | `GuiConfirmOpenLinkBridge` | title "Confirm Open Link", extends the GuiYesNo bridge; `GuiConfirmOpenLinkMixin @Mixin(GuiConfirmOpenLink)` |
| `Bridge5Extension66` | `GuiYesNoBridge` | title "Yes No" + `bridge$getYesNoCallback()`/`bridge$getParentButtonClickedId()`; `GuiYesNoMixin @Mixin(GuiYesNo)` |
| `Bridge5Extension67` | `GuiSelectWorldBridge` | title "Select World"; `GuiSelectWorldMixin @Mixin(GuiSelectWorld)` implements it |
| `Bridge5Extension68` | `GuiErrorScreenBridge` | title "Alert"; `GuiErrorScreenMixin @Mixin(GuiErrorScreen)` implements it |
| `Bridge5Extension69` | `GuiConnectingBridge` | `bridge$getClientPacketListener()`; `GuiConnectingMixin @Mixin(GuiConnecting)` implements it |
| `Bridge5Extension7` | `GuiAchievementsBridge` | title "Achievements"; `GuiAchievementsMixin @Mixin(GuiAchievements)` implements it |
| `Bridge5Extension8` | `GuiScreenBookBridge` | `bridge$getPageCount`/`bridge$getCurrentPage`/`bridge$getPageContents` + title "Book"; `GuiScreenBookMixin @Mixin(GuiScreenBook)` |
| `Bridge5Extension9` | `GuiIngameBridge` | `bridge$renderGameOverlay`/`bridge$showCrosshair`/`bridge$getChatGUI`/`bridge$getTabList`/title API; `GuiIngameMixin @Mixin(GuiIngame)` implements it |
| `Bridge5Extension610` | `GuiMainMenuBridge` | title "Main Menu"; `GuiMainMenuMixin @Mixin(GuiMainMenu)` implements it |
| `Bridge5Extension611` | `GuiIngameMenuBridge` | title "Pause Screen"; `GuiIngameMenuMixin @Mixin(GuiIngameMenu)` implements it |
| `Bridge6_2` | `RenderBlocksBridge` | `legacy/wrapper/Bridge6Handler` implements it holding a `net.minecraft.client.renderer.RenderBlocks`; `method1(pose, Block, meta, packedLight)` renders one block, `method2()` flushes |
| `Bridge6_3` | `EntityLightningBoltBridge` | `bridge$setAddedByWeatherChanger(boolean)` forcing `lightningState=3` in `onUpdate`; `EntityLightningBoltMixin @Mixin(EntityLightningBolt)` |
| `Bridge6_4` | `ItemBridge` | item property API: `bridge$isItemPotion/Skull/Block/sword/bow/...`, `bridge$hasEffect(ItemStack)`, `bridge$getRegistryName()`, `bridge$getBlockFromItem()`; `ItemMixin @Mixin(Item)` implements it |
| `Bridge6_5` | `ServerStatusResponseBridge` | `getLunarServer`/`setLunarServer` over a `@Unique lunar$lunarServer` field; `ServerStatusResponseMixin @Mixin(ServerStatusResponse)` |
| `Bridge6_6` | `MixinTargetMemberResolverBridge` | holds `String[]` member paths and resolves each through mapping sets to `MixinTargetMember`, following return-type chains for nested paths; used by the ichor mixin generation (`Bridge4_2`/`Bridge2_35`) |
| `Bridge6_7` | `MapItemRendererBridge` | `bridge$renderMap`/`bridge$getMapTexture(MapDataBridge,int)`; `EntityRendererMixin` exposes its `mapItemRenderer` field through it |
| `Bridge6_8` | `ShaderManagerBridge` | `bridge$getShaderUniform(String)`/`bridge$getProgram()`/`bridge$apply()` (=`useShader`)/`bridge$clear()` (=`endShader`)/`bridge$bindSampler`; `ShaderManagerMixin @Mixin(ShaderManager)` implements it and the Shader mixin returns `getShaderManager()` as it |
| `Bridge6_9` | `DecodedTextureDataBridge` | lombok value class self-named "DecodedTextureData" in `toString`: clamp/blur/animationMetadata/lunarMetadata/width/height/originalHeight/PixelFormat/data/name; consumed by the `Bridge7_3` texture loader |
| `Bridge6Extension_2` | `ItemHoeBridge` | `ItemHoeMixin @Mixin(ItemHoe)` implements it |
| `Bridge6Extension2` | `ItemPotionBridge` | adds `bridge$getEffects(ItemStack)` to the Item bridge; cast on `bridge$getItem()` results in `PotionCounter`/`PotionUtils` for potion stacks |
| `Bridge6Extension3$Type` | `ArmorMaterialBridge` | armor material enum LEATHER/CHAIN/IRON/GOLD/DIAMOND/TURTLE/NETHERITE/ARMADILLO/TURTLE_SCUTE/COPPER with `fromMaterial`+`getMaterial`; returned by the ItemArmor bridge's `bridge$getArmorMaterial()` |
| `Bridge6Extension4` | `ItemAxeBridge` | `ItemAxeMixin @Mixin(ItemAxe)` implements it |
| `Bridge6Extension5` | `ItemSpadeBridge` | `ItemSpadeMixin @Mixin(ItemSpade)` implements it |
| `Bridge6Extension6` | `ItemBowBridge` | `ItemBowMixin @Mixin(ItemBow)` implements it |
| `Bridge6Extension7` | `ItemPickaxeBridge` | `ItemPickaxeMixin @Mixin(ItemPickaxe)` implements it |
| `Bridge6Extension8` | `ItemBlockBridge` | `ItemBlockMixin @Mixin(ItemBlock)` implements it; `bridge$getBlockFromItem()` consumers are `ShulkerPreview` and the attack indicator |
| `Bridge6Extension9` | `ItemSwordBridge` | `ItemSwordMixin @Mixin(ItemSword)` implements it |

## Undeterminable (left out)

| class | why |
|---|---|
| `Bridge5_8` | two default methods `bridge$readPayload(Bridge7_9)` / `bridge$writePayload(Bridge7_9)` (`Bridge7_9` = PacketBuffer, see `PacketBufferMixin extends ByteBuf`), but **zero implementors, callers or subinterfaces in the whole jar** — cannot tell which packet/payload class of Lunar's it is. |
| `Bridge5_9` | empty marker interface, zero references in source or compiled jar. |
| `Bridge5_17` | empty marker interface; only use is `currentScreen instanceof Bridge2_27 \|\| instanceof Bridge5_17` in `ScrollableTooltipsController.method3()` (`Bridge2_27` = `GuiContainerCreative` marker, `@Mixin GuiContainerCreative`). No implementor anywhere in the jar, so the specific screen cannot be pinned down. |
| `Bridge5_21` | empty marker interface, zero references in source or compiled jar. |
| `Bridge6_11` | empty marker interface, zero references in source or compiled jar. |
| `Bridge6_13` | empty marker interface, zero references in source or compiled jar. |

## Notable findings / inference flags

* **`Bridge5_15` → `GuiBridge` is an inference from the type hierarchy, not from a direct implementor.**
  Its extenders map 1:1 onto the 1.8 `net.minecraft.client.gui.Gui` subclasses
  (`GuiScreen`, `GuiIngame`, `GuiNewChat`, `GuiPlayerTabOverlay`), which is the only
  vanilla class all of them share; the optional-title `method1()` is never invoked in
  the current tree (dead API surface), so it cannot corroborate the name directly.
  Everything else is backed by an explicit `@Mixin`/implementor or a named string
  found in bytecode.
* The `Bridge5`/`Bridge6` simple names are **not** pipeline noise: the pre-normalization
  jar literally contained `bridge/mixinCore/Bridge5`, `bridge/mixinShared/Bridge5`,
  `bridge/mixinSupport/Bridge5`, ... (`tools/mappings-snapshot/normalize-renames.tsv`),
  i.e. Lunar's own generated bridge classes carry these names. No readable original
  names exist for them in `lunar-client-names.tsv`, so every name here is derived
  from method surface + implementors.
* Related classes owned by other clusters were used as evidence but *not* renamed here:
  `Bridge5_16` = PoseStack (pushPose/popPose/last/copy), `Bridge3_10` = `PoseStack.Pose`
  (pose/normal/copy), `Bridge7_9` = PacketBuffer, `Bridge7_8` = wrapped driver GuiScreen,
  `Bridge3_23` = Block, `Bridge6Extension3` = ItemArmor, `Bridge5Extension_4`/`Bridge5_13`,
  `Bridge5_11`/`Bridge6_10` = EntityLivingBase/EntityPlayer.
* `Bridge6_2` is the 1.8-only `RenderBlocks` facade (impl holds a `RenderBlocks` static
  field); later versions route block rendering through the same interface, so the
  1.8 class name was chosen in line with existing `GuiChestBridge`/`EntityAgeableBridge`.
* `Bridge6_9` names itself: its generated `toString()` emits `"DecodedTextureData(" ...`.
