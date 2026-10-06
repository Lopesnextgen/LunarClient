# Cluster 47 — legacy/mixin leftovers (`legacy.mixin#part2`)

Wave 5, 2026-09-16. Scope: 45 rows, all in `com.moonsworth.lunar.legacy.mixin`
(the "second legacy mixin source set", `legacy/mixin2` before the flatten wave).

## Summary

| Outcome | Count |
|---|---|
| renamed (rows in `classes-47.tsv`) | 14 |
| skipped — superseded duplicate (canonical twin already in tree) | 31 |
| net.minecraft.* | 0 |
| shaded third-party | 0 |

15 classes still carry a `MixinN` placeholder and were not touched by any earlier
wave; the other 31 were re-added into the tree by `47dd3dd48` ("full-coverage
sweep + class restore", jar twin of the renamed class) although an earlier wave
had already renamed the very same class. The project keeps jar-1:1 twins for
coverage; the applied map must not create a second live copy, so these rows are
skipped rather than renamed.

## Skipped rows — superseded duplicates

Each of these files has the identical `@Mixin` target plus identical
`@Inject/@Redirect/@WrapOperation` signatures to the named file listed to the
right (verified by comparing annotation target signatures; the named copy is the
one the earlier waves produced, e.g. `classes-legacymixin.tsv`). Renaming them
again would produce two mixins for one real Lunar class.

| old (kept as-is) | canonical file already in tree |
|---|---|
| InventoryPlayerMixin2 | InventoryPlayerEventMixin |
| ItemMixin2 | ItemGlintMixin |
| ItemPotionMixin2 | ItemPotionThrowMixin |
| ItemStackMixin3 | ItemStackUpdateMixin |
| KeyBindingMixin2 | KeyBindingModMixin |
| LayerArmorBaseMixin2 | LayerArmorBaseHitColorMixin |
| IChatComponentMixin2 | IChatComponentBridgeMixin |
| MinecraftMixin3 | MinecraftIntegrationTestMixin |
| MinecraftMixin4 | MinecraftOptifineWrapperMixin |
| ModelBakeryMixin2 | ModelBakeryOptifineMixin |
| ModelBipedMixin2 | ModelBipedBridgeMixin |
| ModelPlayerMixin2 | ModelPlayerLayerMixin |
| ModelRendererMixin2 | ModelRendererAttachMixin |
| PotionEffectMixin2 | PotionEffectNullFixMixin |
| RenderEntityItemMixin2 | RenderEntityItemPhysicsMixin |
| RenderGlobalMixin2 | RenderGlobalEntityOutlineMixin |
| RenderGlobalMixin3 | RenderGlobalFlawlessMixin |
| RenderItemMixin3 | RenderItemBridgeMixin |
| RenderPlayerMixin2 | RenderPlayerEventMixin |
| RenderPlayerMixin3 | RenderPlayer3dSkinsMixin |
| RendererLivingEntityMixin2 | RendererLivingEntityGlowMixin |
| RendererLivingEntityMixin3 | RendererLivingEntityHitColorMixin |
| ResourcePackRepositoryMixin2 | ResourcePackRepositoryServerPackMixin |
| ScoreboardMixin2 | ScoreboardNameTagMixin |
| ScoreboardMixin3 | ScoreboardAccessorMixin |
| ServerListEntryNormalMixin2 | ServerListEntryNormalPingMixin |
| ServerListEntryNormalMixin3 | ServerListEntryNormalAccessorMixin |
| ServerPingerMixin2 | ServerPingerLunarServerMixin |
| ShaderGroupMixin2 | ShaderGroupRewindMixin |
| ShaderMixin2 | ShaderCustomResourceMixin |
| SkinManagerMixin2 | SkinManagerSkinLoadingMixin |

Recommendation: delete the placeholder copies (or leave them to
`tools/match_libs.py`-style dedupe) instead of re-applying class renames.
`ModelRendererMixin2`/`ModelRendererMixin3` are an OptiFine pair: the named twin
`ModelRendererAttachMixin` is the `absent=optifine` half, so only `Mixin3`
(OptiFine half) is genuinely unclaimed and got a name.

## Ambiguities / notes

* `NetworkManagerMixin2` is the v1_8 half (`@Annotation2(min=1,max=1)`, target
  `NetworkManager`), while `NetworkManager_v1_12Mixin2`/`_v1_7Mixin2` exist for
  the split version classes; it is not a duplicate of either. Named
  `NetworkManagerRewindMixin` (v1_8 implied by target); a version-qualified
  variant can be adopted later if the v1_12/v1_7 siblings get canonical names.
* `RendererLivingEntityMixin22` ("22") is the 1.7-only sibling of
  `RendererLivingEntityGlowMixin` (1.8+/1.12); named with the `V1_7` qualifier
  already used by `RenderPlayer_v1_7Mixin`/`FMLCommonHandlerV1_12Mixin` style.
* `ServerPingerMixin3` is misnamed as shipped: it targets
  `net.minecraft.client.multiplayer.GuiConnecting$1` (the connect thread), not
  ServerPinger. Name follows the real target (`GuiConnectingConnectMixin`); the
  existing `GuiConnectingMixin`/`GuiConnectingMixin2`/`GuiConnectingDisconnectMixin`
  are different patches on the outer class.
* `IChatComponentMixin2` implements the empty bridge marker
  `bridge.horsestats.Horsestats5`; the canonical `IChatComponentBridgeMixin`
  implements `bridge.minecraft.IChatComponentMarker`. Both are 1.8 markers, the
  rest of the class is identical, so it was treated as a superseded duplicate.
* Evidence sources: class source (targets, injections, Lunar mod accessors
  `FogLoader3`/`Client` getters, `client.event.*` classes matched against the
  `HighlightImpl*` aliases), `tools/mappings-snapshot/restructure/mixin-renames.tsv`,
  `tools/renames/classes-legacymixin.tsv` and tree-wide grep for simple-name
  uniqueness (all 14 new names occur nowhere else in `src/`).
* No shaded third-party classes in this cluster — all 45 are Lunar legacy mixins.
