# Cluster 66 — `com.moonsworth.lunar.client.highlight` (33 rows)

Wave 5, 2026-09-16. Source: `tools/renames/cluster-66.txt` (33 rows: 24
top-level types + 9 nested `DataN`). All 33 paths exist; no `net.minecraft.*`
rows, no shaded third-party code. Map: `tools/renames/wave5/classes-66.tsv`
(**32 rows written, 1 skipped** — see §3).

Applier dry run:

```
[aware-renames] 32 rows (9 nested); 9808 java files
[aware-renames] rows=32 skipped=0 files_touched=242 files_renamed=23 mode=dry-run
```

No `SKIP`/`WARN` lines; every nested row resolved its owner declaration
(`Highlight2$$Data2 -> EventListenerChange`, `HighlightBase2$$Data5 ->
ScreenInitPostEvent`, ...). No new simple name is declared anywhere in
`src` (word-boundary grep = 0 hits for all 32).

## 1. What this package is

`com.moonsworth.lunar.client.highlight` is **the client event system — the
event bus and every event/payload class**. It is the package that earlier
rename waves already processed:

* `classes-23` / `classes-26` (applied `57e98a2b`) renamed the event classes
  in place to `LunarEventBus`, `EventBusAccess`, `EventListener`,
  `EventRender*`, `EventKeyInput`, `EventMarkerInput`, `ApolloOptionUpdateEvent`,
  `ResultEvent`, ...
* `classes-rewindgui` (applied `be615dc3`) supplied the nested stages
  (`EventBusOperation`, `EventRenderTooltipPre`,
  `EventRenderContainerSlotPre/Post/AfterItems`, `EventScreenInitPre/Post`,
  `EventRenderHologramItem/Text`) and the three member-less events
  (`EventOptionsSaved`, `EventResourcesReload`, `EventRenderPlayerStats`).
* `moves-bucketsA` (`d6a452378`, `pkgs-bucketsA.tsv` row 2) **moved the whole
  package to `com.moonsworth.lunar.client.event`**, so the canonical names
  today live in `client.event` (`ApolloPacketEvent` is the one exception, §3).

The 2026-09-15/16 rescue sweeps (`b384aca20`, `47dd3dd48`) then
re-added the *pre-rename* copies from `libs/lunar-renamed-classes.jar` under
the old placeholder names, which is what this cluster inventories. The tree
therefore holds two live copies of each class, referenced from different call
sites:

| example pair | ref files (bare/import/FQN) |
|---|---|
| `highlight.Highlight2` vs `event.LunarEventBus` | 146 / 108 |
| `highlight.HighlightImpl17` vs `event.EventRenderTooltip` | 11 / 25 |
| `highlight.HighlightImpl3` vs `event.EventResourcePackUpdate` | 5 / 4 |

**Jar provenance** (`tools/mappings-snapshot/restructure/remaining-renames.tsv`
lines 3411-3420) and the six `libs/multiver-full` jars agree: the obfuscated
runtime has exactly one copy of each event class (`HRICOROOOCCOCOROCRHHCRRIRCOICO`
= `Highlight2`/`LunarEventBus`, `OHROCHICOIOICHOCRROORRCIIICIHO` = `HighlightImpl2`
/ `ResultEvent`, ...); the highlight copies are the jar-1:1 twins restored for
coverage, not a second revision.

## 2. Renames (32 rows)

Every row is the *same decompiled class* as its already-named `client.event`
twin. Verification per pair (scripted, not heuristic):

* identical sorted string-literal set,
* identical sorted method-name set (placeholder names `methodN` survive in both
  lineages),
* identical field count and constructor signatures (spot-checked on the source
  pairs whose constructors differ in renamed parameter types),
* for the member-less events the firer/consumer sets match exactly
  (`GameSettingsMixin` fires `HighlightImpl8` at `saveOptions` TAIL while the
  event side has `EventOptionsSaved`; `FogIterator.field8` consumes
  `HighlightImpl18` = `EventResourcesReload`; `SkyblockBitsShopHelper` /
  `SkyblockSkymartHelper` / `GuiRewindhandlersHandler25` consume
  `HighlightBase2.Data5` = `EventScreenInitPost`, ...).

Because `apply_class_renames_aware.py` refuses a `new` name that is already
declared (the `client.event` twin), the canonical names cannot be emitted as
rows. Per the cluster-40 §2 / cluster-51 §2 precedent each duplicate gets a
**unique, non-lazy variant name**; the twin and the intended final name are
recorded below. If the merge deletes the `client.event` twin instead, the
intended final names are the canonical column (an inverse map
variant→canonical is all that is needed).

`dup of` = canonical `client.event` class. `refs h/e` = files referencing the
highlight copy / the event twin (applier-style resolution: same-package bare,
single import, or FQN; own file excluded).

| # | old | new (variant) | dup of (final name if merge keeps this copy) | refs h/e | evidence (short) |
|---|---|---|---|---|---|
| 1 | `Highlight2` | `ClientEventBus` | `LunarEventBus` | 146/108 | the event bus: Class→`EventListener[]` registry, priority sort, PRE/POST/BOTH `Type.wrapEvents`, ReentrantLock, `"EventBus [...]"` diagnostics, `DebuggingEventBus` subclass on `-Ddebug` |
| 2 | `Highlight3` | `EventRegistrar` | `EventBusAccess` | 54/22 | interface with default `handle(Class,Consumer)` / `handle(Class,Runnable,int)` delegating to `Highlight2.method29()`; implemented by every handler |
| 3 | `Highlight4` | `ListenerRegistration` | `EventListener` | 4/4 | listener entry: `int priority` + `Consumer<T>`, sorted descending in the bus |
| 4 | `HighlightBase2` | `ScreenInitEvent` | `EventRenderScreenOverlay` | 7/16 | abstract overlay event carrying only the screen (`Bridge5Extension6`); `Data4`/`Data5` = pre/post `GuiScreen.setWorldAndResolution` |
| 5 | `HighlightImpl2` | `OutcomeEvent` | `ResultEvent` | 10/8 | gating base with `Type{ALLOW,DENY,DEFAULT}` + `method1()/method2(Type)` |
| 6 | `HighlightImpl3` | `ResourcePackUpdateEvent` | `EventResourcePackUpdate` | 5/4 | holds `IResourcePackBridge`; `PackDisplay` refreshes the selected-pack thumbnail |
| 7 | `HighlightImpl4` | `TabListEntryRenderEvent` | `EventRenderTabListEntry` | 1/2 | (`Bridge_30` NetworkPlayerInfo + `Component`) fired in `GuiPlayerTabOverlayTabMixin`; TotemCounter edits the name |
| 8 | `HighlightImpl5` | `SlotRenderEvent` | `EventRenderSlot` | 3/21 | (screen + font + slotId + int + mutable `ContainerClickType`) with `setSlotId`/`setItem`/`isModified`; ~20 Skyblock container solvers |
| 9 | `HighlightImpl6` | `ButtonRenderEvent` | `EventRenderButton` | 1/0 | (screen + `Bridge2_20` GuiButton) GUI button render event |
| 10 | `HighlightImpl7` | `ScreenChangeEvent` | `EventScreenChange` | 17/15 | current-screen event (getter only); root-package users `GuiRewindhandlersHandler22` (priority 190) / Nickhider `Highlight3Handler` mirror `HighlightTypeListener` / `NicknameListener` |
| 11 | `HighlightImpl8` | `OptionsSavedEvent` | `EventOptionsSaved` | 1/0 | empty event posted at `GameSettings.saveOptions` TAIL (`GameSettingsMixin.java:532`, `method12(HighlightImpl8.class, HighlightImpl8::new)`) |
| 12 | `HighlightImpl9` | `ScreenOpenEvent` | `EventScreenOpen` | 10/19 | screen + nameplate text pair with getters/setters; Zoom resets zoom, HUD solvers clear state |
| 13 | `HighlightImpl10` | `ResolutionChangeEvent` | `EventResolutionChange` | 4/1 | (`getScaledWidth`/`getScaledHeight`) scaled-resolution pair; Bossbar / SkyblockWitherHealthHud reposition widgets |
| 14 | `HighlightImpl11` | `ScreenCloseEvent` | `EventScreenClose` | 1/2 | carries the closing screen (`Bridge7_8`); fired in `GuiScreenImpl.keyTyped` on ESC, `GuiRewindhandlers3` cancels it |
| 15 | `HighlightImpl12` | `ItemDurabilityRenderEvent` | `EventRenderItemDurability` | 1/2 | (ItemStack + double damage) `@VersionGate(min=26)`; SkyblockPickonimbusDurability replaces the readout |
| 16 | `HighlightImpl13` | `KeyInputEvent` | `EventKeyInput` | 4/5 | (`KeyCode` + char + keyCode + modifiers + `HighlightType2{PRESS,RELEASE,CHAR}`) |
| 17 | `HighlightImpl14` | `MarkerInputEvent` | `EventMarkerInput` | 10/10 | (screen + `Markers.Data4` + int + `HighlightType3{CLICK,RELEASE,DRAG,SCROLL}` + timestamp + coords); waypoint/marker mouse interaction |
| 18 | `HighlightImpl15` | `ContainerSlotRenderEvent` | `EventRenderContainerSlot` | 13/32 | abstract (marker + scale + screen + render context + `MixinHelper_4`); `Data`/`Data2` fired by `GuiContainerSlotEventsMixin`, `Data4`/`Data3` wrap `GuiScreen.drawScreen` in `EntityRendererFogMixin` |
| 19 | `HighlightImpl16` | `ScreenUpdateEvent` | `EventScreenUpdate` | 4/4 | mutable screen event (getter+setter); Title/ServerAddress/ToggleSneak/PvpInfo handlers |
| 20 | `HighlightImpl17` | `TooltipRenderEvent` | `EventRenderTooltip` | 11/25 | (screen + x/y + optional ItemStack) tooltip event; twin named after the leaked `"EventRenderTooltip.Pre"` string; ScrollableTooltips |
| 21 | `HighlightImpl18` | `ResourcesReloadEvent` | `EventResourcesReload` | 2/0 | empty event at `Minecraft.refreshResources` TAIL (`MinecraftEventMixin.java:921`); fog `FogIterator.field8` drops its texture cache |
| 22 | `HighlightImpl19` | `PlayerStatsRenderEvent` | `EventRenderPlayerStats` | 4/0 | extends `highlight.mixin.nameplate.HighlightImpl` + `boolean foodRendered`; fired at `GuiIngame.renderPlayerStats` TAIL (`GuiIngameHudMixin`); Saturation overlay |
| 23 | `HighlightImpl_2` | `RenderScaleEvent` | `EventRenderScale` | 4/2 | single float scale (`getScale`/`setScale`); Nameplate4 / GuiScale read it |
| 24 | `Highlight2$Data2` | `EventListenerChange` | `EventBusOperation` (`LunarEventBus$Data2`) | — | queued (un)register op: register flag + Class + Consumer + priority, drained at dispatch depth 0 |
| 25 | `HighlightBase$Data5` | `HologramItemRenderEvent` | `EventRenderHologramItem` | — | item hologram variant storing `ItemStackBridge`, draws via `Bridge7Iterator.method127` |
| 26 | `HighlightBase$Data6` | `HologramTextRenderEvent` | `EventRenderHologramText` | — | `TextComponent` hologram variant via `Bridge7Iterator.method128..131` |
| 27 | `HighlightBase2$Data4` | `ScreenInitPreEvent` | `EventScreenInitPre` | — | pre `setWorldAndResolution` (pairs with `Data5`, documented on #28) |
| 28 | `HighlightBase2$Data5` | `ScreenInitPostEvent` | `EventScreenInitPost` | — | post `setWorldAndResolution`; same consumers as the twin (`SkyblockBitsShopHelper.method1`, `SkyblockSkymartHelper.method1`, `GuiRewindhandlersHandler25`) |
| 29 | `HighlightImpl15$Data2` | `ContainerSlotAfterItemsEvent` | `EventRenderContainerSlotAfterItems` | — | fired by `GuiContainerSlotEventsMixin.lunar$renderGuiAfterItems` at `drawGuiContainerForegroundLayer` |
| 30 | `HighlightImpl15$Data3` | `ContainerSlotPostEvent` | `EventRenderContainerSlotPost` | — | post half of the `drawScreen` `method16(Data4 PRE, Data3 POST)` wrapper |
| 31 | `HighlightImpl15$Data4` | `ContainerSlotPreEvent` | `EventRenderContainerSlotPre` | — | pre half of the same wrapper; OverlayMod / rewind recorders |
| 32 | `HighlightImpl17$Data2` | `TooltipPreRenderEvent` | `EventRenderTooltipPre` | — | collects tooltip lines + max width, logs `"EventRenderTooltip.Pre"` when the font is null |

## 3. Skipped row (1)

| row | why skipped |
|---|---|
| `HighlightImpl_3` | **Jar-coupled name, not a zombie.** `dbfca2d11` deliberately renamed+**moved** `client.event.ApolloPacketEvent` back to `client.highlight.HighlightImpl_3` (`classes-apolloevent-revert.tsv`: the stale jar's `Highlight3Iterator_3.method3(HighlightImpl_3)` is overridden by ~30 Apollo handlers, so the parameter type FQN must match). The file *is* the surviving class — it is the `event`-side copy, moved back to `highlight`; renaming it would re-break the jar coupling. It has no duplicate to dedupe: `client.event.ApolloPacketEvent` no longer exists. |

## 4. Cluster rows not present / adjacent leftovers (not renamed here)

The inventory classifier only lists digit-suffixed lazy names, so these event
classes are outside cluster 66 and stay untouched:

* `highlight/Highlight.java` — the compatibility base shim the stale-jar event
  dispatch is bound to; `event/LunarEvent.java` extends it by design (its
  javadoc says "drop this shim once the event API is rebuilt from source").
* `highlight/HighlightImpl.java` (= `CancellableEvent`), `Highlight2Iterator.java`
  (= `DebuggingEventBus`) — both have named `client.event` twins and are in the
  ECJ baseline (`tools/work/ecj-baseline.txt`), so they belong to the dedupe
  repair pass, not to a naming map (they are also outside `cluster-66.txt`).
* `HighlightImpl2_2` (= `ApolloOptionUpdateEvent`) and `HighlightImpl2_3`
  (= `EventRenderScreenItem`) exist on disk but `name_inventory.py`'s
  `^[A-Za-z_]+[0-9]+$` regex does not classify `Impl2_2` as lazy, so they are in
  no cluster. They are duplicates all the same (verified by the same structural
  comparison) and should be picked up by the dedupe pass or a follow-up row:
  `HighlightImpl2_2 → ApolloOptionUpdateEvent`,
  `HighlightImpl2_3 → EventRenderScreenItem`.
* The two non-digit nested payloads `HighlightImpl15$Data` (twin
  `EventRenderContainerSlot$ItemRender`) and `HighlightImpl17$Data` (twin
  `EventRenderTooltipPost`) are likewise outside the cluster (plain `Data` is
  not classified lazy).

## 5. Merge recommendation

The canonical name set (`client.event`) is the one to keep: the package name is
right (bucket A moved it there deliberately) and the majority of twins are more
referenced than the rescued copies (`EventRenderSlot` 21/3,
`EventRenderContainerSlot` 32/13, `EventRenderTooltip` 25/11,
`EventRenderHologram` 22/2, `EventScreenOpen` 19/10, ...). The exceptions are
`Highlight2`/`Highlight3`/`HighlightImpl7`/`HighlightImpl10`, where the rescued
copy has more refs.

* If the dedupe runs **before** this map: delete the `highlight` copies, repoint
  their call sites to `client.event`, and drop rows 1-32; nothing else changes.
* If it runs **after**: the variant names keep both lineages compiling in the
  meantime; translate variant→canonical with the table in §2 when unifying
  (the event twins can then be deleted, or the highlight copies renamed to the
  canonical names).
* Do **not** apply rows 1-32 a second time as canonical names without deleting
  the `client.event` twin first — the applier will (correctly) skip them.

No other caveats: all rows are name-only, package stays
`com.moonsworth.lunar.client.highlight`, and the current ECJ baseline is
unchanged in kind (`Highlight2.java`, `Highlight2Iterator.java`,
`HighlightImpl17.java` were already failing before this map; the rename only
rewrites identifiers).
