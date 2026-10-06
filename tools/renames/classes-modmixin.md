# Cluster 09 — `com.moonsworth.lunar.client.framework.feature.mod.mixin` + neighbours (41 classes)

Source revision: `tools/renames/cluster-09.txt` md5 `7b4aac759e1b94fd5f9ee3cfdd6b806f`
(41 rows: 21 in `...feature.mod.mixin`, 20 in `...feature.mod`).
Map: `tools/renames/classes-modmixin.tsv` (md5 `86d9360353fc85a6df98dda1cc717fbf`).

> **Accuracy fix.** Despite the placeholder names, **none of these classes is a
> mixin**. The cluster is two unrelated things that the decompiler flattened
> into adjacent packages:
>
> 1. `...feature.mod.mixin` — the **`/route` command** suite for the SkyBlock
>    dungeon secret-route feature: a subcommand interface, a `/route`
>    dispatcher, 16 subcommand handlers, and two standalone commands
>    (`/warp`, `/sendcoords`).
> 2. `...feature.mod` — Lunar's **custom immediate-mode GUI framework**
>    (renderer facade + theme + per-subsystem renderers/input/state), plus the
>    GUI **module** (screen/overlay) plumbing.
>
> So the `<Target><Purpose>Mixin` rule does not apply here; every row is named
> by its actual role.

## Provenance

`tools/mappings-snapshot/normalize-renames.tsv` and
`tools/mappings-snapshot/restructure/remaining-renames.tsv` recover the
original jar subtrees:

| current package | original jar subtree |
|---|---|
| `…feature.mod.mixin` (route commands) | `…/feature/mod/mixin/mixin/mixin/*` |
| `…feature.mod` (GUI framework) | `…/feature/mod/mixinCore/mixin/{,mixinCore,mixinCore/mixin,mixinExtra}/*` |
| `…feature.mod` (`Module2`, `ModuleBase2`, `Module_2`, `GuiRewindhandlersHandler2`) | `…/feature/mod/mixinCore/*` |

The route commands are wired from
`com/moonsworth/lunar/client/mod/render/SkyblockDungeonRoutes.java` (imports
`MixinHelper5`) and the command registry inside `MixinHelper5` itself. The GUI
framework is the renderer behind the mod-menu / inventory screens: it is driven
by `SkyblockDungeonRoutes`, `InventoryMod`, `SlotLocking`,
`SkyblockProtectItem`, `SkyblockRarityBackground` and the
`EventRenderItemStackSize` / `EventRenderItemDurability` events.

## Renames (41 rows)

### Route command suite — `com.moonsworth.lunar.client.framework.feature.mod.mixin`

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `MixinHelper2` | `RouteSubcommand` | interface: `execute(String[])` + `tab-complete(String[])`; implemented by every `/route` subcommand |
| 2 | `MixinHelper5` | `RouteCommand` | `ClientCommand "/route"`; `Map<String,RouteSubcommand>` registry (help/save/start/clone/cancel/reset/info/show/delete/select/deselect/section/list/meta/sectionmeta/whitelist/blacklist) |
| 3 | `MixinHelper3` | `WarpCommand` | `ClientCommand "warp"`; sends `/warp <dest>`, autocompletes server warp list, SkyBlock-island gated |
| 4 | `MixinHelper4` | `SendCoordsCommand` | `ClientCommand "sendcoords"`; broadcasts `x/y/z` to all/party/guild/officer/coop |
| 5 | `MixinHelper6` | `RouteResetSubcommand` | `/route reset`: resets current-room progress |
| 6 | `MixinHelper7` | `RouteCancelSubcommand` | `/route cancel`: cancels recording (`recordingCancelled`) |
| 7 | `MixinHelper8` | `RouteSectionMetaSubcommand` | `/route sectionmeta`: swaponlocked + text-hologram add/remove |
| 8 | `MixinHelper9` | `RouteBlacklistSubcommand` | `/route blacklist`: list/add/remove/clear (`roomBlackList`) |
| 9 | `MixinHelper10` | `RouteCloneSubcommand` | `/route clone`: clones selected route (`successfullyClonedRoute`) |
| 10 | `MixinHelper1010` | `RouteListSubcommand` | `/route list`: routes in room, `[B]`/`[H]` tags (`routesInCurrentRoom`) |
| 11 | `MixinHelper1011` | `RouteDeselectSubcommand` | `/route deselect` (`routeDeselected`) |
| 12 | `MixinHelper1012` | `RouteSelectSubcommand` | `/route select <name>` / `route:section` (`selectedRoute`) |
| 13 | `MixinHelper1013` | `RouteWhitelistSubcommand` | `/route whitelist`: list/add/remove/clear (`roomWhitelist`) |
| 14 | `MixinHelper102` | `RouteMetaSubcommand` | `/route meta`: hidden + swaponcomplete (`metaForCurrent`) |
| 15 | `MixinHelper103` | `RouteDeleteSubcommand` | `/route delete <name>` with swap-dependency warnings (`routeDeleteSuccess`) |
| 16 | `MixinHelper104` | `RouteShowSubcommand` | `/route show [name]` (`showingAllRoutesInCurrentRoom`) |
| 17 | `MixinHelper105` | `RouteStartSubcommand` | `/route start`: start/reset recording (`freeToStartRecording`) |
| 18 | `MixinHelper106` | `RouteSaveSubcommand` | `/route save <name>` (`successfullySaved`) |
| 19 | `MixinHelper107` | `RouteHelpSubcommand` | `/route help` for meta/sectionmeta/whitelist/blacklist |
| 20 | `MixinHelper108` | `RouteSectionSubcommand` | `/route section prev\|next\|<index>` (`currentRouteSection`) |
| 21 | `MixinHelper109` | `RouteInfoSubcommand` | `/route info <name>` (`foundRoute`) |

### GUI framework — `com.moonsworth.lunar.client.framework.feature.mod`

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 22 | `GuiRewindhandlersHandler2` | `GuiModuleManager` | registers screen/overlay modules, tracks active screen (`field12`) and open overlays (`field11`) on `EventScreenUpdate` |
| 23 | `MixinHelper2` | `WidgetStateCache` | `Map<guiId, Map<widgetId, WidgetState>>`, hover/scale animation, dirty flags; begin/end widget |
| 24 | `MixinHelper210` | `RectRenderer` | filled-rect batching; `method1(x,y,w,h,color)`, flush (`method17`) with dirty `IntRectangle` |
| 25 | `MixinHelper211` | `ScissorStack` | clip-rect stack; push/intersect, `glScissor` via `Bridge7Iterator.method113`, contains/outside |
| 26 | `MixinHelper22` | `KeyboardInput` | per-GUI key queue; `method2` queues `KeyEventBridge`, `method18` returns last frame |
| 27 | `MixinHelper22_2` | `ScrollAnimator` | per-rect float scroll offset (`ThreadModuleDump47.Data`) applied as z-translation |
| 28 | `MixinHelper23` | `SlotRenderer` | 9-wide slot grid, quick-craft drag, shift-click, slot-lock/protect overlays |
| 29 | `MixinHelper24` | `PanelRenderer` | titled window panel using `GuiTheme` border/background colours |
| 30 | `MixinHelper25` | `ItemStackRenderer` | item + durability + stack size + rarity background + search highlight; fires `EventRenderItemStackSize` |
| 31 | `MixinHelper26` | `BoxRenderer` | panel box, inset box, separator line from `GuiTheme` |
| 32 | `MixinHelper27` | `ScaledTextRenderer` | scaled text draw, `getStringWidth`, fit-scale helper |
| 33 | `MixinHelper28` | `TextFieldRenderer` | text input: placeholder, cursor blink, selection, key handling |
| 34 | `MixinHelper2_2` | `MouseInput` | press/release + deferred `List<MouseClick>` polled per rect |
| 35 | `MixinHelper2_3` | `GuiComponent` | abstract base of every GUI subsystem; holds `GuiRenderer`, siblings, theme; `start()`/`end()` |
| 36 | `MixinHelper3` | `GuiRenderState` | pushed/popped render state: flags, clip rect, search text, x/y/z scale |
| 37 | `MixinHelper_2` | `MouseClick` | completed click record (x, y, button) with `contains(rect)` |
| 38 | `MixinHelper_3` | `WidgetState` | per-widget state (id/pos/size/scale/dirty) with hover/update/delete |
| 39 | `Module2` | `GuiEventDispatcher` | dispatches render/scroll/click/drag/key to active module or all overlays |
| 40 | `ModuleBase2` | `OverlayModule` | concrete module rendered on top of the current screen |
| 41 | `Module_2` | `GuiModule` | abstract module base: render/scroll/click/drag/key + `onClose` |

## Out-of-cluster companions (not listed, so not in the map)

* **`…feature.mod.MixinHelper`** is the GUI framework's renderer facade —
  singleton, theme holder, `start`/`end`, push/pop, and the public draw API
  (`drawRect`, `drawText`, `drawItemStack`, `beginWidget`, `scissor`,
  `textField`, `slotGrid`, `panel`, `tooltip`, `translate`). Best named
  `GuiRenderer` (or `LunarGui`). It is the class that owns all 13 components
  above; whoever gets that file should claim it.
* **`…feature.mod.MixinHelperType`** is the colour theme enum
  (`DEFAULT_LIGHT`/`DEFAULT_DARK`/`DARK_BLUE` with `panelBackground`,
  `insetBackground`, `panelBorder*`, `insetBorder*`, `searchNoMatchOverlay`) →
  `GuiTheme`. Claimed by `GuiComponent.getTheme()`.
* **`…feature.mod.Module`** is unrelated to this GUI framework (the JSON game
  data loader) and **`…feature.mod.ModuleBase`** is the abstract
  `ScreenModule` base (`shouldOpen(CurrentScreen)`), sibling of `OverlayModule`.
  Neither is in cluster-09; the names `GuiModule` / `OverlayModule` here leave
  `ModuleBase` free to become `ScreenModule`.
* **Quarantined `…feature.mod.mixin.MixinHelper`** (in
  `tools/work/quarantine/src/…/mod/mixin/MixinHelper.java`) is the route
  commands' **translation/formatting helper** (`method1(key, args…)` →
  `Client.method109().method67().method2("features.SKYBLOCK.dungeonRoutes", …)`,
  plus route/room hover components). The route command files call
  `MixinHelper.method1(...)` with no import, so those references resolve to
  this missing same-package class, **not** to the GUI `MixinHelper`. It is a
  different class that needs its own name (e.g. `DungeonRouteMessages`) when
  quarantine is restored.

## Applier dry run, and the collision rows

```
python3 tools/apply_class_renames.py --map tools/renames/classes-modmixin.tsv
# → applied=18 skipped=23 files_touched=57 files_renamed=18 mode=dry-run
```

The simple rewriter skips 23 rows because the old simple names are declared in
several packages (`MixinHelper2`, `MixinHelper3`, `MixinHelper22/23/26/28`,
`MixinHelper_2/_3`, `Module2`, `ModuleBase2`, …). **Do not run this map with
`--allow-collisions`**: it would rewrite unrelated `bridge`/`network`/`genesis`/
`legacy` `MixinHelper*` classes and the `client.feature`/`inventorymod`
`Module*` classes.

The package-aware applier resolves them all:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-modmixin.tsv
# → rows=41 skipped=0 files_touched=55 files_renamed=41 mode=dry-run
```

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-modmixin.tsv
# → rows=41 skipped=0 files_touched=55 files_renamed=41 mode=dry-run
```

New simple names were checked for tree-wide uniqueness
(`grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java` and the
1451 already-claimed names in `classes-*.tsv`): no collisions.