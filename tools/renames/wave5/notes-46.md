# Cluster 46 — `com.moonsworth.lunar.legacy.mixin` (45 rows)

Map: `tools/renames/wave5/classes-46.tsv` (12 rows). No sources were edited.

## Summary

| | count |
|---|---|
| renamed (rows written) | 12 |
| skipped — rescue-restored duplicates of already-renamed classes | 33 |
| skipped — missing paths / `net.minecraft.*` / shaded libs | 0 |

All 45 rows still exist in `src/main/java`. Every skipped row is a **zombie**:
the file was renamed to a descriptive name by an earlier batch
(`add80a65b` legacy.mixin#part1, `11e4b815e` legacymixin leftovers), then the
pre-rename copy was re-added by the rescue sweep `3d38608ff`
("legacy/replaymod + client leftovers"). The tree therefore contains the same
mixin twice: once under its good name and once under its old placeholder name.
Renaming the zombie would create two names for one class, so it is skipped and
flagged for deletion instead.

Duplicate proof (all 33 pairs, script-checked, not heuristic):
identical `@Mixin` target, identical injected-method set, identical `@At
target` set, identical injection-annotation kinds, and token-shape similarity
0.89–1.00 (the small delta is the default constructor `public X() {}` that the
renaming pass injects). Git history additionally shows
`R089..R099 old.java -> new.java` for each pair in the batches above.

## Rows written (12)

These are the classes whose pre-rename copy was restored by `47dd3dd48`
(full-coverage sweep) and which have **no** named twin anywhere in the tree.
Each new simple name was checked with a word-boundary grep over
`src/main/java` (0 hits).

| old | new | one-line rationale |
|---|---|---|
| `AbstractClientPlayerMixin2` | `AbstractClientPlayerFovMixin` | FOV-override event on `getFovModifier` + skin-available event + `isSpectator` overwrite |
| `BlockDoublePlantMixin2` | `BlockDoublePlantHideFoliageMixin` | hides double plants / tall grass while OverlayMod `hideFoliage` is on |
| `EntityMixin2` | `EntityHooksMixin` | multi-feature Entity hooks (rewind, glow, nametag, xray/lighting, cache) |
| `EntityRendererMixin2` | `EntityRendererFogMixin` | 28 injections, fog/atmosphere event family dominant; plus rewind/freelook/rain |
| `FontRendererMixin3` | `FontRendererCacheMixin` | overwrites `renderStringAtPos` with a string render cache + OptiFine custom-font reader |
| `GameSettingsMixin3` | `GameSettingsOptifineMixin` | OptiFine zoom key/`ofRenderRegions`, reloading and rewind water-opacity hooks |
| `GuiChatMixin2` | `GuiChatEventsMixin` | chat submit/click/hover events, chat alpha, Apollo chat packets, URL guard |
| `GuiContainerCreativeMixin2` | `GuiContainerCreativeSlotClickMixin` | slot-click event wrapper for the creative inventory |
| `GuiContainerMixin2` | `GuiContainerSlotEventsMixin` | slot click/draw/tooltip events + InventoryMod double-click + OverlayMod tint |
| `GuiIngameMixin2` | `GuiIngameBridgeMixin` | full `GuiIngameBridge` implementation (title/overlay/chat/tab, 1.7+1.8) |
| `GuiIngameMixin3` | `GuiIngameHudMixin` | HUD-wide overlay hooks: custom tab list, bars, hotbar tint, boss/potion/scoreboard |
| `GuiScreenMixin2` | `GuiScreenEventsMixin` | mouse/keyboard event wrapping, button tint, menu blur, tooltips, sendChatMessage |

Evidence sources used: the class sources themselves, the already-named twins
of these classes where they exist (e.g. `EntityLivingBaseEventMixin` for the
event names, `OverlayMod` field `field19="hideFoliage"` for the foliage mixin),
`tools/mappings-snapshot/restructure/*.tsv` for provenance, and
`git log -M`/`git log --follow` for the rename/restore history.

## Skipped rows (33) — duplicates, recommend deletion

Each row: zombie (current file) → existing named twin that already owns the
name. All were renamed in `add80a65b` (31 pairs) or `11e4b815e`
(`ChunkMixin2`, `EntityRendererMixin3`) and all were restored in `3d38608ff`.

| zombie | existing renamed twin | renamed in |
|---|---|---|
| `AbstractClientPlayerMixin3` | `AbstractClientPlayerCapeMixin` | add80a65b |
| `AbstractTextureMixin2` | `AbstractTextureHandleMixin` | add80a65b |
| `AbstractTextureMixin3` | `AbstractTextureMultiTexMixin` | add80a65b |
| `AxisAlignedBBMixin2` | `AxisAlignedBBBoundsMixin` | add80a65b |
| `BlockMixin2` | `BlockXrayMixin` | add80a65b |
| `BlockModelRendererMixin2` | `BlockModelRendererEmissiveMixin` | add80a65b |
| `ChunkMixin2` | `ChunkLightingMixin` | 11e4b815e |
| `ComponentTransformableExtension2` | `TranslatableComponentTransformMixin` | add80a65b |
| `CrashReportMixin2` | `CrashReportStackTraceMixin` | add80a65b |
| `EffectRendererMixin2` | `EffectRendererParticleChangerMixin` | add80a65b |
| `EntityArmorStandMixin2` | `EntityArmorStandRewindMixin` | add80a65b |
| `EntityLivingBaseMixin2` | `EntityLivingBaseEventMixin` | add80a65b |
| `EntityPlayerMixin2` | `EntityPlayerNameplateMixin` | add80a65b |
| `EntityPlayerMixin3` | `EntityPlayerGameProfileMixin` | add80a65b |
| `EntityPlayerSPMixin2` | `EntityPlayerSPEventMixin` | add80a65b |
| `EntityRendererMixin3` | `EntityRendererFasterLoadingMixin` | 11e4b815e |
| `EnumChatFormattingMixin2` | `EnumChatFormattingOptimizationMixin` | add80a65b |
| `FontRendererMixin2` | `FontRendererOverlayMixin` | add80a65b |
| `GameSettingsMixin2` | `GameSettingsDefaultsMixin` | add80a65b |
| `GuiButtonMixin2` | `GuiButtonOverlayMixin` | add80a65b |
| `GuiChatMixin3` | `GuiChatPacketEnrichmentMixin` | add80a65b |
| `GuiConfirmOpenLinkMixin2` | `GuiConfirmOpenLinkLayoutMixin` | add80a65b |
| `GuiConnectingMixin2` | `GuiConnectingDisconnectMixin` | add80a65b |
| `GuiEditSignMixin2` | `GuiEditSignFinishMixin` | add80a65b |
| `GuiErrorScreenMixin2` | `GuiErrorScreenLayoutMixin` | add80a65b |
| `GuiIngameMixin22` | `GuiIngameVignetteMixin` | add80a65b |
| `GuiMainMenuMixin2` | `GuiMainMenuLinkMixin` | add80a65b |
| `GuiMultiplayerMixin2` | `GuiMultiplayerBlocklistMixin` | add80a65b |
| `GuiNewChatMixin2` | `GuiNewChatMessageMixin` | add80a65b |
| `GuiPlayerTabOverlayMixin2` | `GuiPlayerTabOverlayTabMixin` | add80a65b |
| `GuiScreenMixin3` | `GuiScreenMenuBlurMixin` | add80a65b |
| `GuiSlotMixin2` | `GuiSlotBackgroundMixin` | add80a65b |
| `GuiYesNoMixin2` | `GuiYesNoChoiceMixin` | add80a65b |

**Action instead of rename:** delete the 33 zombie files (or run a
`find_duplicates`-style sweep), keeping the named twin. The skipped rows are
deliberately *not* in the map: mapping them to the twin name would make
`apply_class_renames_aware.py` refuse them as duplicate declarations
(correctly), and inventing a second name would leave the tree with two names
for one class.

## Ambiguities / caveats

* `AbstractClientPlayerMixin2` (`AbstractClientPlayerFovMixin`): the event it
  posts on `getFovModifier` is still placeholder-named
  (`client.highlight.mixin.highlight.HighlightImpl21`, a single `float`), and
  no consumer for it is in the tree, so the name is derived from the hook, not
  from a consuming mod.
* `EntityMixin2` (`EntityHooksMixin`) is genuinely multi-purpose (rewind,
  Apollo glow, nametags, Lighting/Xray, per-entity `Click4` cache); no single
  feature dominates, so the generic "Hooks" purpose is intentional.
* `FontRendererMixin3` came from the `legacy/optifine/mixin` staging path and
  uses OptiFine `FontUtils`, but its feature is the Lunar custom-font/cache
  renderer, so it is **not** given an `Optifine` qualifier.
  `GameSettingsMixin3` is the one that genuinely shadows OptiFine settings, so
  it follows the existing `ModelBakeryOptifineMixin` /
  `MinecraftOptifineWrapperMixin` convention.
* `GuiIngameMixin2` implements the placeholder bridge `Bridge5Extension9`,
  which is itself a duplicate of the named `GuiIngameBridge` (same interface,
  zombie twin). The name describes the role, not the dangling interface.
* Same zombie pattern is very likely present in clusters 47/48: their old
  names (`StatFileWriter2/3`, `TextureMapMixin3`, `TimerMixin2`,
  `WorldMixin3/4`, `MinecraftMixin2/3/4`, ...) already appear as `old` in the
  applied `tools/renames/classes-legacymixin.tsv`, whose `new` names are in
  the tree. A tree-wide zombie sweep is worth doing before clusters 47-48 are
  renamed.
* Renaming is name-only; packages stay as-is per wave-5 rules.
