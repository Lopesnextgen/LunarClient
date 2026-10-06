# Cluster 06 — `com.moonsworth.lunar.legacy.mixin` (50 rows)

Map: `tools/renames/wave7/classes-06.tsv`. **0 in-place renames, 50 merge rows.**
No sources were edited.

## Summary

| | count |
|---|---|
| rows in the cluster | 50 |
| in-place renames | 0 |
| stale rescue twins (merge into an already-named canonical class) | 50 |
| missing paths / `net.minecraft.*` / shaded third-party | 0 |

All 50 paths in `tools/renames/cluster-06.txt` exist, none is `net.minecraft.*`, and
none is shaded third-party code. (`ComponentTransformableExtension2` imports the
Adventure transform API `com.lunarclient.adventure.transform.*`, but the class itself
is Lunar's `TranslatableComponent` mixin.)

Every class in this cluster is a **zombie**: the same mixin already exists in the
same package under a descriptive name, and the placeholder-named file is a copy
restored by the rescue sweep. Renaming it would create two names for one class
(and both appliers refuse such rows), so the map points each zombie at its
canonical class and the action is **merge + delete**, not rename.

## Proof (per class)

* **git rename history** — 48 rows: `add80a65b` ("renamer: apply bridge#part1 +
  legacy.mixin#part1") renamed `Old.java` -> `Twin.java`; 2 rows: `11e4b815e`
  ("renamer: apply legacymixin map"). The placeholder copy itself had entered
  `src/main` in the 2026-09-13 restructure/migration commits (`50dba2ea9`,
  `335cea10d`), and after the rename it was re-added unchanged by the rescue sweep
  `3d38608ff` ("rescue: legacy/replaymod + client leftovers"): `git log
  --full-history` shows `D` in add80a65b/11e4b815e then `A` in 3d38608ff for all 50
  paths. 33 of the 50 pairs were already documented in
  `tools/renames/wave5/notes-46.md` §"Skipped rows"; this map adds the remaining 17
  (`IChatComponentMixin2` … `RenderGlobalMixin3`).
* **structural identity (script-checked)** — for every pair: identical `@Mixin`
  target; identical set of annotation string constants (all `method=`/`target=`
  descriptors, `@At`, `@Slice`, `@Local`/`@Share`); identical unique member set
  (`lunar$`/`apollo$`/`impl$`/`bridge$`/`ext$` names). The only delta is the
  annotation-name generation (`@Annotation2` -> `@VersionGate`, `@Annotation_2` ->
  `@MixinCondition`, plus `@Override` in a few twins).
* **applier behaviour** — `tools/apply_class_renames.py` and
  `tools/apply_class_renames_aware.py` both print `SKIP <old> -> <new>: new name
  already declared` for these rows and change nothing; the rows are kept in the map
  as machine-readable merge documentation.

Two rows are also skipped earlier because the **old simple name is declared in two
packages** and is a genuinely different mixin there (not a twin):

* `ChunkMixin2` also in `com/moonsworth/lunar/mixin/mixin/ChunkMixin2.java` — a biome
  bridge (`Itemcounter2`, `bridge$getBiome`) unrelated to lighting.
* `EntityRendererMixin3` also in `com/moonsworth/lunar/client/mixin/EntityRendererMixin3.java`
  — lightmap init + `GuiIngame.renderGameOverlay` redirect (HUD/Framebuffer capture),
  unrelated to faster loading.

## Pair table

| # | delete (stale twin) | canonical (keep) | `@Mixin` target | renamed by | what it does |
|---|---|---|---|---|---|
| 1 | `AbstractClientPlayerMixin3` | `AbstractClientPlayerCapeMixin` | `AbstractClientPlayer` | `add80a65b` | cape (locationOfCape, OptiFine cape fallback) |
| 2 | `AbstractTextureMixin2` | `AbstractTextureHandleMixin` | `AbstractTexture` | `add80a65b` | GL texture handle bridge (lunar$getHandle) |
| 3 | `AbstractTextureMixin3` | `AbstractTextureMultiTexMixin` | `AbstractTexture` | `add80a65b` | OptiFine MultiTex base handling |
| 4 | `AxisAlignedBBMixin2` | `AxisAlignedBBBoundsMixin` | `AxisAlignedBB` | `add80a65b` | AABB minY/maxY accessors |
| 5 | `BlockMixin2` | `BlockXrayMixin` | `Block` | `add80a65b` | StaffXray block sides/light/brightness |
| 6 | `BlockModelRendererMixin2` | `BlockModelRendererEmissiveMixin` | `BlockModelRenderer` | `add80a65b` | emissive sprite null guard |
| 7 | `ChunkMixin2` | `ChunkLightingMixin` | `Chunk` | `11e4b815e` | lighting (ModLighting) + chunk/block events |
| 8 | `ComponentTransformableExtension2` | `TranslatableComponentTransformMixin` | `TranslatableComponent` | `add80a65b` | Adventure TranslatableComponent transform |
| 9 | `CrashReportMixin2` | `CrashReportStackTraceMixin` | `CrashReport` | `add80a65b` | crash cause -> Inventorymod stack trace |
| 10 | `EffectRendererMixin2` | `EffectRendererParticleChangerMixin` | `EffectRenderer` | `add80a65b` | ParticleChanger spawn/registry + render/rewind |
| 11 | `EntityArmorStandMixin2` | `EntityArmorStandRewindMixin` | `EntityArmorStand` | `add80a65b` | rewind onUpdate skip |
| 12 | `EntityLivingBaseMixin2` | `EntityLivingBaseEventMixin` | `EntityLivingBase` | `add80a65b` | health/hurt/use-item events + rewind + swing speed |
| 13 | `EntityPlayerMixin2` | `EntityPlayerNameplateMixin` | `EntityPlayer` | `add80a65b` | use-item + reduced-debug notifier |
| 14 | `EntityPlayerMixin3` | `EntityPlayerGameProfileMixin` | `EntityPlayer` | `add80a65b` | gameProfile accessor |
| 15 | `EntityPlayerSPMixin2` | `EntityPlayerSPEventMixin` | `EntityPlayerSP` | `add80a65b` | local combat/chat events + anti-portal-traps + rewind |
| 16 | `EntityRendererMixin3` | `EntityRendererFasterLoadingMixin` | `EntityRenderer` | `11e4b815e` | fasterLoading 100->1 |
| 17 | `EnumChatFormattingMixin2` | `EnumChatFormattingOptimizationMixin` | `EnumChatFormatting` | `add80a65b` | getTextWithoutFormattingCodes overwrite |
| 18 | `FontRendererMixin2` | `FontRendererOverlayMixin` | `FontRenderer` | `add80a65b` | overlay GUI text colour |
| 19 | `GameSettingsMixin2` | `GameSettingsDefaultsMixin` | `GameSettings` | `add80a65b` | VBO default / model parts / rewind save block |
| 20 | `GuiButtonMixin2` | `GuiButtonOverlayMixin` | `GuiButton` | `add80a65b` | overlay button tint |
| 21 | `GuiChatMixin3` | `GuiChatPacketEnrichmentMixin` | `GuiChat` | `add80a65b` | Apollo chat open/close packets |
| 22 | `GuiConfirmOpenLinkMixin2` | `GuiConfirmOpenLinkLayoutMixin` | `GuiConfirmOpenLink` | `add80a65b` | button centring |
| 23 | `GuiConnectingMixin2` | `GuiConnectingDisconnectMixin` | `GuiConnecting` | `add80a65b` | disconnect on join + block button |
| 24 | `GuiEditSignMixin2` | `GuiEditSignFinishMixin` | `GuiEditSign` | `add80a65b` | SignUpdateEvent lines |
| 25 | `GuiErrorScreenMixin2` | `GuiErrorScreenLayoutMixin` | `GuiErrorScreen` | `add80a65b` | multiline error layout |
| 26 | `GuiIngameMixin22` | `GuiIngameVignetteMixin` | `GuiIngame` | `add80a65b` | Apollo vignette overlay |
| 27 | `GuiMainMenuMixin2` | `GuiMainMenuLinkMixin` | `GuiMainMenu` | `add80a65b` | open-url redirect + splash colour |
| 28 | `GuiMultiplayerMixin2` | `GuiMultiplayerBlocklistMixin` | `GuiMultiplayer` | `add80a65b` | blocklist/malicious-server + hosted-world scan |
| 29 | `GuiNewChatMixin2` | `GuiNewChatMessageMixin` | `GuiNewChat` | `add80a65b` | chat message events/ids + chat heads + peek + rewind |
| 30 | `GuiPlayerTabOverlayMixin2` | `GuiPlayerTabOverlayTabMixin` | `GuiPlayerTabOverlay` | `add80a65b` | Tab mod names/logo/colours + rewind head |
| 31 | `GuiScreenMixin3` | `GuiScreenMenuBlurMixin` | `GuiScreen` | `add80a65b` | MenuBlur OptiFine screens |
| 32 | `GuiSlotMixin2` | `GuiSlotBackgroundMixin` | `GuiSlot` | `add80a65b` | slot background suppression |
| 33 | `GuiYesNoMixin2` | `GuiYesNoChoiceMixin` | `GuiYesNo` | `add80a65b` | PromptAction checkboxes/layout |
| 34 | `IChatComponentMixin2` | `IChatComponentBridgeMixin` | `IChatComponent` | `add80a65b` | ChatComponentMarker bridge |
| 35 | `InventoryPlayerMixin2` | `InventoryPlayerEventMixin` | `InventoryPlayer` | `add80a65b` | InventoryUpdateEvent |
| 36 | `ItemMixin2` | `ItemGlintMixin` | `Item` | `add80a65b` | rewind glint override |
| 37 | `ItemPotionMixin2` | `ItemPotionThrowMixin` | `ItemPotion` | `add80a65b` | PotionThrowEvent |
| 38 | `ItemStackMixin3` | `ItemStackUpdateMixin` | `ItemStack` | `add80a65b` | null item update guard |
| 39 | `KeyBindingMixin2` | `KeyBindingModMixin` | `KeyBinding` | `add80a65b` | mod keybind filtering |
| 40 | `LayerArmorBaseMixin2` | `LayerArmorBaseHitColorMixin` | `LayerArmorBase` | `add80a65b` | HitColor shouldCombineTextures |
| 41 | `MinecraftMixin3` | `MinecraftIntegrationTestMixin` | `Minecraft` | `add80a65b` | integration-test early exit |
| 42 | `MinecraftMixin4` | `MinecraftOptifineWrapperMixin` | `Minecraft` | `add80a65b` | OptiFine renderer wrapper registration |
| 43 | `ModelBakeryMixin2` | `ModelBakeryOptifineMixin` | `ModelBakery` | `add80a65b` | OptiFine CIT model paths |
| 44 | `ModelBipedMixin2` | `ModelBipedBridgeMixin` | `ModelBiped` | `add80a65b` | ModelBiped bridge members |
| 45 | `ModelPlayerMixin2` | `ModelPlayerLayerMixin` | `ModelPlayer` | `add80a65b` | player wear-layer attach |
| 46 | `ModelRendererMixin2` | `ModelRendererAttachMixin` | `ModelRenderer` | `add80a65b` | attached model parts |
| 47 | `PotionEffectMixin2` | `PotionEffectNullFixMixin` | `PotionEffect` | `add80a65b` | null Potion.isReady guard |
| 48 | `RenderEntityItemMixin2` | `RenderEntityItemPhysicsMixin` | `RenderEntityItem` | `add80a65b` | ground-item transform event + offset |
| 49 | `RenderGlobalMixin2` | `RenderGlobalEntityOutlineMixin` | `RenderGlobal` | `add80a65b` | entity outlines + sky/beam/block events + rewind |
| 50 | `RenderGlobalMixin3` | `RenderGlobalFlawlessMixin` | `RenderGlobal` | `add80a65b` | FlawlessFrames chunks + sky distance + outline depth |

## Ready-to-use merge map (`tools/repoint_external.py`)

`repoint_external.py --map <file> --apply` deletes each zombie and rewrites every
FQN/import/bare reference to the canonical class (same package, so `newfqn` is
`com.moonsworth.lunar.legacy.mixin.<Canonical>`). It is path/FQN-based, so the two
name-collision rows above are still handled correctly for the `legacy/mixin` copy.

Caveat: do **not** feed `classes-06.tsv` to `tools/make_merge_map.py` — despite its
docstring mentioning `wave5/classes-*.tsv`, that parser expects
`old.package <TAB> Old <TAB> new.package <TAB> New`, so a class map's 4th column
(evidence) would be read as the destination name. Use the fenced map with
`repoint_external.py`, or point `make_merge_map.py` at the row structure above.

```
# old.package<TAB>Old<TAB>canonical.fqn<TAB>evidence
com.moonsworth.lunar.legacy.mixin	AbstractClientPlayerMixin3	com.moonsworth.lunar.legacy.mixin.AbstractClientPlayerCapeMixin	merge: stale rescue twin; canonical AbstractClientPlayerCapeMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	AbstractTextureMixin2	com.moonsworth.lunar.legacy.mixin.AbstractTextureHandleMixin	merge: stale rescue twin; canonical AbstractTextureHandleMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	AbstractTextureMixin3	com.moonsworth.lunar.legacy.mixin.AbstractTextureMultiTexMixin	merge: stale rescue twin; canonical AbstractTextureMultiTexMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	AxisAlignedBBMixin2	com.moonsworth.lunar.legacy.mixin.AxisAlignedBBBoundsMixin	merge: stale rescue twin; canonical AxisAlignedBBBoundsMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	BlockMixin2	com.moonsworth.lunar.legacy.mixin.BlockXrayMixin	merge: stale rescue twin; canonical BlockXrayMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	BlockModelRendererMixin2	com.moonsworth.lunar.legacy.mixin.BlockModelRendererEmissiveMixin	merge: stale rescue twin; canonical BlockModelRendererEmissiveMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ChunkMixin2	com.moonsworth.lunar.legacy.mixin.ChunkLightingMixin	merge: stale rescue twin; canonical ChunkLightingMixin already exists (11e4b815e)
com.moonsworth.lunar.legacy.mixin	ComponentTransformableExtension2	com.moonsworth.lunar.legacy.mixin.TranslatableComponentTransformMixin	merge: stale rescue twin; canonical TranslatableComponentTransformMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	CrashReportMixin2	com.moonsworth.lunar.legacy.mixin.CrashReportStackTraceMixin	merge: stale rescue twin; canonical CrashReportStackTraceMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EffectRendererMixin2	com.moonsworth.lunar.legacy.mixin.EffectRendererParticleChangerMixin	merge: stale rescue twin; canonical EffectRendererParticleChangerMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EntityArmorStandMixin2	com.moonsworth.lunar.legacy.mixin.EntityArmorStandRewindMixin	merge: stale rescue twin; canonical EntityArmorStandRewindMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EntityLivingBaseMixin2	com.moonsworth.lunar.legacy.mixin.EntityLivingBaseEventMixin	merge: stale rescue twin; canonical EntityLivingBaseEventMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EntityPlayerMixin2	com.moonsworth.lunar.legacy.mixin.EntityPlayerNameplateMixin	merge: stale rescue twin; canonical EntityPlayerNameplateMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EntityPlayerMixin3	com.moonsworth.lunar.legacy.mixin.EntityPlayerGameProfileMixin	merge: stale rescue twin; canonical EntityPlayerGameProfileMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EntityPlayerSPMixin2	com.moonsworth.lunar.legacy.mixin.EntityPlayerSPEventMixin	merge: stale rescue twin; canonical EntityPlayerSPEventMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	EntityRendererMixin3	com.moonsworth.lunar.legacy.mixin.EntityRendererFasterLoadingMixin	merge: stale rescue twin; canonical EntityRendererFasterLoadingMixin already exists (11e4b815e)
com.moonsworth.lunar.legacy.mixin	EnumChatFormattingMixin2	com.moonsworth.lunar.legacy.mixin.EnumChatFormattingOptimizationMixin	merge: stale rescue twin; canonical EnumChatFormattingOptimizationMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	FontRendererMixin2	com.moonsworth.lunar.legacy.mixin.FontRendererOverlayMixin	merge: stale rescue twin; canonical FontRendererOverlayMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GameSettingsMixin2	com.moonsworth.lunar.legacy.mixin.GameSettingsDefaultsMixin	merge: stale rescue twin; canonical GameSettingsDefaultsMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiButtonMixin2	com.moonsworth.lunar.legacy.mixin.GuiButtonOverlayMixin	merge: stale rescue twin; canonical GuiButtonOverlayMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiChatMixin3	com.moonsworth.lunar.legacy.mixin.GuiChatPacketEnrichmentMixin	merge: stale rescue twin; canonical GuiChatPacketEnrichmentMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiConfirmOpenLinkMixin2	com.moonsworth.lunar.legacy.mixin.GuiConfirmOpenLinkLayoutMixin	merge: stale rescue twin; canonical GuiConfirmOpenLinkLayoutMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiConnectingMixin2	com.moonsworth.lunar.legacy.mixin.GuiConnectingDisconnectMixin	merge: stale rescue twin; canonical GuiConnectingDisconnectMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiEditSignMixin2	com.moonsworth.lunar.legacy.mixin.GuiEditSignFinishMixin	merge: stale rescue twin; canonical GuiEditSignFinishMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiErrorScreenMixin2	com.moonsworth.lunar.legacy.mixin.GuiErrorScreenLayoutMixin	merge: stale rescue twin; canonical GuiErrorScreenLayoutMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiIngameMixin22	com.moonsworth.lunar.legacy.mixin.GuiIngameVignetteMixin	merge: stale rescue twin; canonical GuiIngameVignetteMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiMainMenuMixin2	com.moonsworth.lunar.legacy.mixin.GuiMainMenuLinkMixin	merge: stale rescue twin; canonical GuiMainMenuLinkMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiMultiplayerMixin2	com.moonsworth.lunar.legacy.mixin.GuiMultiplayerBlocklistMixin	merge: stale rescue twin; canonical GuiMultiplayerBlocklistMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiNewChatMixin2	com.moonsworth.lunar.legacy.mixin.GuiNewChatMessageMixin	merge: stale rescue twin; canonical GuiNewChatMessageMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiPlayerTabOverlayMixin2	com.moonsworth.lunar.legacy.mixin.GuiPlayerTabOverlayTabMixin	merge: stale rescue twin; canonical GuiPlayerTabOverlayTabMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiScreenMixin3	com.moonsworth.lunar.legacy.mixin.GuiScreenMenuBlurMixin	merge: stale rescue twin; canonical GuiScreenMenuBlurMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiSlotMixin2	com.moonsworth.lunar.legacy.mixin.GuiSlotBackgroundMixin	merge: stale rescue twin; canonical GuiSlotBackgroundMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	GuiYesNoMixin2	com.moonsworth.lunar.legacy.mixin.GuiYesNoChoiceMixin	merge: stale rescue twin; canonical GuiYesNoChoiceMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	IChatComponentMixin2	com.moonsworth.lunar.legacy.mixin.IChatComponentBridgeMixin	merge: stale rescue twin; canonical IChatComponentBridgeMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	InventoryPlayerMixin2	com.moonsworth.lunar.legacy.mixin.InventoryPlayerEventMixin	merge: stale rescue twin; canonical InventoryPlayerEventMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ItemMixin2	com.moonsworth.lunar.legacy.mixin.ItemGlintMixin	merge: stale rescue twin; canonical ItemGlintMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ItemPotionMixin2	com.moonsworth.lunar.legacy.mixin.ItemPotionThrowMixin	merge: stale rescue twin; canonical ItemPotionThrowMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ItemStackMixin3	com.moonsworth.lunar.legacy.mixin.ItemStackUpdateMixin	merge: stale rescue twin; canonical ItemStackUpdateMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	KeyBindingMixin2	com.moonsworth.lunar.legacy.mixin.KeyBindingModMixin	merge: stale rescue twin; canonical KeyBindingModMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	LayerArmorBaseMixin2	com.moonsworth.lunar.legacy.mixin.LayerArmorBaseHitColorMixin	merge: stale rescue twin; canonical LayerArmorBaseHitColorMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	MinecraftMixin3	com.moonsworth.lunar.legacy.mixin.MinecraftIntegrationTestMixin	merge: stale rescue twin; canonical MinecraftIntegrationTestMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	MinecraftMixin4	com.moonsworth.lunar.legacy.mixin.MinecraftOptifineWrapperMixin	merge: stale rescue twin; canonical MinecraftOptifineWrapperMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ModelBakeryMixin2	com.moonsworth.lunar.legacy.mixin.ModelBakeryOptifineMixin	merge: stale rescue twin; canonical ModelBakeryOptifineMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ModelBipedMixin2	com.moonsworth.lunar.legacy.mixin.ModelBipedBridgeMixin	merge: stale rescue twin; canonical ModelBipedBridgeMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ModelPlayerMixin2	com.moonsworth.lunar.legacy.mixin.ModelPlayerLayerMixin	merge: stale rescue twin; canonical ModelPlayerLayerMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	ModelRendererMixin2	com.moonsworth.lunar.legacy.mixin.ModelRendererAttachMixin	merge: stale rescue twin; canonical ModelRendererAttachMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	PotionEffectMixin2	com.moonsworth.lunar.legacy.mixin.PotionEffectNullFixMixin	merge: stale rescue twin; canonical PotionEffectNullFixMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	RenderEntityItemMixin2	com.moonsworth.lunar.legacy.mixin.RenderEntityItemPhysicsMixin	merge: stale rescue twin; canonical RenderEntityItemPhysicsMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	RenderGlobalMixin2	com.moonsworth.lunar.legacy.mixin.RenderGlobalEntityOutlineMixin	merge: stale rescue twin; canonical RenderGlobalEntityOutlineMixin already exists (add80a65b)
com.moonsworth.lunar.legacy.mixin	RenderGlobalMixin3	com.moonsworth.lunar.legacy.mixin.RenderGlobalFlawlessMixin	merge: stale rescue twin; canonical RenderGlobalFlawlessMixin already exists (add80a65b)
```

## References that must be repointed (handled by the map above)

* `com/moonsworth/lunar/legacy/mixin/RenderManagerMixin.java:462-463` — casts
  `((AxisAlignedBBMixin2)var1)` to call `bridge$setMaxY`/`bridge$setMinY`;
  `AxisAlignedBBBoundsMixin` exposes the same accessors.
* `com/moonsworth/lunar/legacy/wrapper/EntityPlayerSPImpl.java:8,119` and
  `com/moonsworth/lunar/mixin/EntityClientPlayerMPImpl.java:6,75` — import + cast
  `EntityPlayerMixin3` to call `bridge$setGameProfile`;
  `EntityPlayerGameProfileMixin` exposes the same accessor.

