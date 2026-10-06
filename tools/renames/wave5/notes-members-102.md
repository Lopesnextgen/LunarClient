# Cluster 102 — member renames (`feature.minimap.mixin`, `feature.mobsize.mixin`,
# `feature.mod` GUI framework)

Map: `tools/renames/wave5/members-102.tsv` — **203 rows** (121 methods / 82 fields)
over **26 owners**. Applied dry-run with the current `tools/apply_member_renames.py`
(17:54 revision, offset-based access rewriter): `203 rows over 26 owners`, no rows
dropped by the multi-decl guard, no missing sources.

## Validation

* Pristine tree + current applier + ECJ (`tools/ecj_check.py`):
  baseline 3357 failing files → map applied 3361 → **0 NEW failing files**.
* 37 files were touched by the access pass; no ECJ error in any touched file
  mentions a new name from this map.
* The only per-file error-count shifts were ECJ cascade re-wordings in files that
  were already failing before the map (e.g. `Storageoverlay2`, `InventorySearch`).

## What was renamed

| area | rows | notes |
|---|---|---|
| `minimap.mixin.Minimap3` + `.TerrainColumn` | 11 | height/depth/fluidState value classes; `method1` (chained) skipped, see below |
| `minimap.mixin.Minimap4` | 7 | colour-map: `update`, `getTerrainColumn`, `computeSurfaceHeight`, `isAir`, `getColors`/`setColors` |
| `mobsize.mixin.TessellatorRenderer` / `WorldRenderer` | 4 | delegate fields + builder |
| `GuiComponent` | 1 | `field1 -> renderer` only; accessors skipped (below) |
| `GuiRenderer` + `MixinHelper` (version twins) | 82 | all 18 private component fields, render-state pointers, and the safely-appliable facade methods (see map) |
| `BoxRenderer`, `ItemStackRenderer`, `KeyboardInput` | 10 | draw methods, key queues |
| `GuiEventDispatcher` | 5 | fields + dead/typed methods |
| `GuiModule` | 2 | `field1 -> activeTicks`, `method9 -> onOpen` (+3 override rows) |
| `GuiModuleManager` + `GuiRewindhandlersHandler2` (twins) | 27 | registry fields, register/update/getter methods |
| `MixinHelper2` (old WidgetStateCache) | 12 | widget-state cache API |
| `MixinHelper210/211/22/22_2/23/24/25` (old component twins) | 38 | fields + non-chained methods |

Evidence sources: declaring sources + all call sites (grep of the whole tree),
the wave-5 class plan `tools/renames/classes-modmixin.md` (component roles and the
facade API list), the in-tree "new family" twins (`GuiRenderer`/`SlotRenderer`/
`ScissorStack`/`RectRenderer`/`WidgetStateCache`…) which share method numbering and
bodies with the old `MixinHelper*` twins, the exception literals
("Called start twice before calling end!", "Calling getCurrentGuiId when not in a
render call"), and the 509 reference clients (Shoreline `ScissorStack.push/pop`,
jGui `GuiRenderer` draw API, Badlion `MiniMapChunk`).

## Deliberately skipped rows (do NOT apply without a signature-aware pass)

The current applier resolves members **by name only** and cannot rewrite these
receiver shapes, so renaming the declaration would break its call sites:

1. **Class-qualified singleton chains** — `GuiRenderer.field1.methodN(...)`,
   `MixinHelper.field1.methodN(...)`, `this.HRROORRCRHHHCCIORROORCIHOHRIHH.methodN(...)`
   (the applier's receiver regex consumes `X.field1` and typing fails).
   Consequently `field1`/`field7` singleton *fields* are also skipped
   (`GuiRenderer.field1`, `MixinHelper.field1`, `GuiModuleManager.field7`,
   `GuiRewindhandlersHandler2.field7`) — their bare/qualified uses would not follow.
2. **Chained calls** — `expr().methodN(...)`, e.g.
   `getRectRenderer().method17()`, `this.method3().method5(...)`,
   `this.method14().method2(...)`, `this.method8().method2(...)`,
   `getTerrainColumn(...).method1()`.
3. **Static/private same-name shadowing** — e.g. `Storageoverlay2.method1()`
   (static) would be renamed through the `ModuleBase.method1` ancestor row.
4. **Same-name descendant declarations** — renaming a base accessor
   (`GuiComponent.method5`) hijacks calls to a descendant's own same-named method
   (`MouseInput.method5`) whenever the descendant row is not in the same map.

### Recommended names for the skipped facade API (GuiRenderer + MixinHelper)

Safely applied rows are marked `[map]`; the rest need the applier fix (or a manual
pass) but are named here so the research is not lost.

`method1` start (literal "Called start twice…") · `[map] 2` currentState ·
`3` pushState · `4` popState · `5` clearTextFields · `[map] 6` clearGui ·
`[map] 7` removeWidget · `[map] 8` isTextFieldSelected · `[map] 9` selectTextField ·
`10` selectTextFieldWithText · `11` setTooltipsEnabled · `12` setDrawEnabled ·
`13` setScissorEnabled · `[map] 14` setSearchText · `[map] 15` setSearchTooltips ·
`16` setMouseInputEnabled · `[map] 17` setHighlightHoveredSlots · `18` pushScissor ·
`[map] 19` setScissor · `20` scale · `[map] 21` drawItemStack ·
`22` drawItemStackHovered · `[map] 24` drawCenteredText · `25/26` drawText ·
`27` mousePressed · `28` mouseReleased · `29` keyPressed · `[map] 30` drawSlotList ·
`[map] 31` drawSlotGrid (GuiRenderer) · `[map] 32` drawSlotGridFlagged ·
`33` drawSlots · `[map] 34` drawPanelNoTitle · `35` drawPanel ·
`[map] 36` drawPanelLeftAligned · `37` drawBox · `38` drawInsetBox ·
`[map] 39` drawSeparator · `40` pollMouseClick · `41` pollMouseClickButton ·
`42` isMouseOver · `43` fill · `44` getScreen · `45` drawTextField ·
`[map] 46` translateScaled · `[map] 47` beginWidget · `[map] 48` endWidget ·
`49` invalidateWidget · `[map] 50` invalidateWidget · `51` getScaleToFit ·
`52` (text-field alias of 5) · `53` drawCursorStack · `[map] 54` showTooltip ·
`[map] 55` showTooltipComponents · `56` showItemTooltip ·
`[map] 57-70,72` getCurrentGuiId / component getters · `73` isContainerScreen.

### Recommended names for `GuiComponent` accessors (twin `MixinHelper2_3` identical)

`method1` renderState · `method2` widgetStateCache · `method3` scissorStack ·
`method4` tooltipRenderer · `method5` textRenderer · `method6` rectRenderer ·
`method7` textFieldRenderer · `method8` itemStackRenderer · `method9` slotRenderer ·
`method10` boxRenderer · `method11` panelRenderer · `method12` mouseInput ·
`method13` keyboardInput · `method14` scrollAnimator · `method15` getCurrentGuiId ·
`method16` getDrawContext.
(`field1 -> renderer` **was** applied.)

### Recommended names for `GuiRenderState` fields (all skipped: always read via
`this.method1().fieldN` chains)

`field1` tooltipsEnabled · `field2` mouseInputEnabled · `field3` highlightHoveredSlots ·
`field4` scissorRect · `field5` searchText · `field6` searchTooltips ·
`field7` drawEnabled · `field8` scissorEnabled · `field9/10/11` scaleX/scaleY/scaleZ.

### Skipped chained-call members (recommended names)

`MixinHelper210.method1` fill, `MixinHelper210.method17` flush,
`MixinHelper211.method1` push, `MixinHelper211.method2` set,
`MixinHelper211.method4` contains, `MixinHelper211.method5` isOutside,
`MixinHelper211.method6` intersect, `MixinHelper211.field2` EMPTY_RECT,
`MixinHelper22.method17` isShiftDown, `MixinHelper22.method18` getPreviousKeyEvents,
`MixinHelper22_2.method1` scroll, `MixinHelper22_2.method2` withScroll,
`MixinHelper25.method1` drawRarityBackground, `MixinHelper25.method2` drawItemStack,
`MixinHelper25.method3` matchesSearch, `MixinHelper23.method1` drawSlotsSimple,
`MixinHelper23.method3` drawSlotsInteractive, `MixinHelper23.method10` setQuickCraftPending,
`MixinHelper23.method18` getQuickCraftRemaining, `MixinHelper23.method19` isQuickCrafting,
`MixinHelper2.method3` getWidget (intra-file accessor collision),
`MixinHelper2.method18` getOpenWidgetCount (chained),
`Minimap3.method1` getHeight, `TerrainColumn.method1` getHeight,
`BoxRenderer.method1` drawBox, `ItemStackRenderer.method1` drawRarityBackground,
`ItemStackRenderer.method3` matchesSearch, `MixinHelper24.method1` drawPanel.
`MixinHelper25.method17`/`ItemStackRenderer.method17` and the row set for
`MixinHelper2`/`MixinHelper210`/`MixinHelper22` that are in the map are safe and applied.
`GuiModule.method1` shouldOpen (`ModuleBase.method1`, `InventorySearchOverlay.method1`,
`TestOverlayModule.method1`), `GuiModule.method6` mouseDragged,
`GuiModule.method8` update (`Storageoverlay2.method8`), `GuiModule.method11` getScreenTitle,
`GuiModuleManager.method8` shouldRenderScreen, `GuiModuleManager.method9` getActiveModule,
`GuiModuleManager.method11` getDispatcher (same for the `GuiRewindhandlersHandler2` twin).
`GuiEventDispatcher.method1` isMouseInputConsumed, `method2` isKeyboardInputConsumed,
`method4` mouseScrolled, `method5` mouseClicked, `method6` mouseReleased,
`method7` mouseDragged, `method8` keyPressed (all only reachable via
`X.method11()` chains).

## Hierarchy notes

* **Version twins.** `GuiRenderer`/`MixinHelper`, `GuiComponent`/`MixinHelper2_3`,
  `GuiModuleManager`/`GuiRewindhandlersHandler2`, `GuiModule`/`Module_2`,
  `GuiRenderState`/`MixinHelper3`, `BoxRenderer`/`MixinHelper26`,
  `SlotRenderer`/`MixinHelper23`, `ItemStackRenderer`/`MixinHelper25`,
  `KeyboardInput`/`MixinHelper22`, `ScissorStack`/`MixinHelper211`,
  `RectRenderer`/`MixinHelper210`, `ScrollAnimator`/`MixinHelper22_2`,
  `WidgetStateCache`/`MixinHelper2`, `PanelRenderer`/`MixinHelper24` are the same
  classes for different MC versions. Names in this map are chosen to match the
  new-family twins; cluster 103 owns `MixinHelper2_3` and the old facade twins, so
  its map must use the same names listed above.
* **Cross-cluster dependency.** The old-family leaves in this cluster
  (`MixinHelper2/210/211/22/22_2/23/24/25`) extend `MixinHelper2_3` (cluster 103).
  `MixinHelper2_3.method1..16` are the same accessor set as `GuiComponent` and must
  NOT be renamed without the same hazard analysis (see 2-4 above).
* **GuiModule override family.** Renaming `GuiModule.method1/method9/method11`
  requires renaming the overrides in `ModuleBase`, `InventorySearchOverlay`,
  `TestOverlayModule`, `Storageoverlay2`, `debug.override.ModuleBase`.
  Only `method9 -> onOpen` survived this analysis; the map carries its 3 override
  rows (`Storageoverlay2.method9`, `debug.override.ModuleBase.method9`,
  `TestOverlayModule.method9`).
* `GuiModuleManager.method8/method9` and `GuiRewindhandlersHandler2.method8/method9`
  were removed from the map: the applier's multi-decl guard drops them (private
  `method8(Event*)` / `method9(Event*)` handlers share the name). They need a
  signature-aware split (`shouldRenderScreen` / `getActiveModule`).

## Inventory noise

Of the 1,072 inventory rows for these 25 files, 512 are parameters and 192 are
**method locals misclassified as fields** (`varN`/`numberN` inside method bodies,
e.g. `Minimap4 F var10`, `WorldRenderer F number5`). They are not members and are
not in the map.
