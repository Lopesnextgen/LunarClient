# members-188.tsv — `client.mod.hud` member renames (cluster 188)

Scope: the 25 owner classes of `tools/renames/wave5/member-cluster-188.tsv`
(all in `com/moonsworth/lunar/client/mod/hud`, from `Coordinates` to `Memory`).

## Numbers

| metric | value |
|---|---|
| owners | 25 (24 outer + the decompiler-split `HypixelBedwarsUpgradeDisplayChild$HRICOROOOCCOCOROCRHHCRRIRCOICO$Data`) |
| lazy M/F entries in `members-lazy.tsv` for those owners | 951 (281 M, 670 F) |
| rows written | **559** (197 M, 362 F) |
| dropped by the default multi-decl filter | 69 (all marked `SPLIT:` in evidence) |
| clean rows applied by default | 490 |
| F entries that are really CFR locals (`varN`, `flagN`, `textN`, `numberN`, `valueN`, `dataN`, `typeN`, …) | 308 — deliberately skipped (no `local` kind in the map format) |
| unused/ambiguous fields left lazy | 2 (`Keystrokes.field9/field10`) |
| framework hooks left lazy | 8 (`MixinCore9Extension.method30/31` overrides) |

Evidence per row: the option-id string passed to `OptionFactory/Lighting`
(e.g. `OptionFactory.method7("showWhileTyping")`), cross-checked against the
real Lunar Apollo mod definitions in `/tmp/opencode/reference/Apollo`
(`api/src/main/java/com/lunarclient/apollo/mods/impl/ModCoordinates.java` etc.,
which carry the exact node paths and defaults), plus call sites and the
`members-settings.md` vocabulary (`registerOptions`, `createDetails`, …).

## Convention highlights

* `registerOptions` — every `method2(RootSettingsBuilder|RootSettingsAssembler)`
  (members-settings.md: "registrant entry point"; `SettingsRegistrant.method1`
  and `Lighting_3.method1` map to the same name).
* `createDetails` — `method20()` returning `ModDetails` (override of
  `AbstractFeature.method20`, cluster 079).
* `getChildMods` — `method9()` returning `List<Framework7Extension>`.
* `create` — static `method6/method7/method8(...)` factories whose own
  exception/`getId` strings say "must be created using X.create()!"
  (`ItemCounterElementChildMod`, `CoordinatesChildHudMod`, `F3ModuleChildMod`
  — note `F3ModuleChildMod` really has two `create` overloads).
* `save` — `method1(JsonObject)` (the `JsonPersistable` twin of `load`;
  cluster 176 owns the interface).
* Field names are the option keys in camelCase (`showWhileTyping`,
  `decimalCoordinates`, `keyFadeDelay`, `itemCounterGrouped`, …); static final
  values use UPPER_SNAKE (`HypixelTps.DEFAULT_TPS_TEXT`,
  `HypixelBedwarsResourceCounterChild.EXECUTOR/IRON_ITEM/…`,
  `HypixelBedwarsTeamDisplayChild.BedwarsTeamMember.DEFAULT_SKIN`).

## Framework family (base classes are in other member clusters — keep names in sync)

These rows rename only the *overrides in this cluster*; the base declarations
are owned by clusters 079 (`AbstractFeature`), 080 (`Framework7Extension`,
`HudElementBase`), 082 (`MixinCore9Extension`), 083 (`TypedHudRenderer`,
`HudRowElement`, `HudRowLayout`). The main agent must apply the same names to
the base owners (or drop both sides) or the overrides silently stop overriding:

| base member | name used here | overrides in this cluster |
|---|---|---|
| `AbstractFeature.method2(RootSettingsBuilder)` | `registerOptions` | every mod (`Cps`, `Fps`, `DayCounter`, `Coordinates*`, `Keystrokes`, `ItemCounter`, `F3*`, `*Child`, `Memory`, …) |
| `AbstractFeature.method20()` | `createDetails` | `HudDebug`, `ItemCounter`, `ItemCounterElementChildMod`, `CustomKeystrokeKey`, `F3Display`, `F3ModuleChildMod`, `HypixelBedwars*` |
| `AbstractFeature.method9()` | `getChildMods` | `Coordinates`, `CoordinatesHud`, `ItemCounter`, `Keystrokes` |
| `AbstractFeature.method3(boolean)` | `setEnabled` | `ItemCounterElementChildMod`, `F3Display`, `F3ModuleChildMod` |
| `AbstractFeature.method1(JsonObject)` | `save` | `F3Display`, `HypixelBedwarsUpgradeDisplayChild` |
| `AbstractFeature.method4()` | `reset` | `Keystrokes`, `ItemCounter`, `F3Display`, `HypixelBedwarsUpgradeDisplayChild` |
| `MixinCore9Extension.method3(HighlightImpl/EventRenderHudBase, x, y, partial)` | `render` | all `Data`/element inner classes |
| `MixinCore9Extension.method4(boolean)` | `shouldRender` | all `Data`/element inner classes |
| `MixinCore9Extension.method16(float,float)` | `setSize` (call sites only, not renamed) | `Coordinates`, `DirectionHud`, `CustomKeystrokeKey.updateSize` |
| `TypedHudRenderer.method15()` | `getSize` | `Cps`, `DayCounter`, `DebugTimerHud`, `Memory`, `HypixelTps`, `CoordinatesChildHudMod` |
| `TypedHudRenderer.method7(boolean)` (named `method2(boolean)`/`method6(boolean)` in several files) | `getText` | `Cps`, `DayCounter`, `DebugTimerHud`, `HypixelTps`, `Memory`, `CoordinatesChildHudMod` |
| `TypedHudRenderer.method5()` | `getConditions` | `DayCounter`, `Memory`, `CoordinatesChildHudMod` |
| `TypedHudRenderer.method18(renderer,x,y,partial,afterText)` | `renderDecoration` | `Cps` |
| `TypedHudRenderer.method17(renderer,self,text,x,y,shadow,bracket,after)` | `renderText` | `Memory` |
| `TypedHudRenderer.method3(ColorOption.Data)` | `configureTextColor` | `Memory` |
| `TypedHudRenderer.method1(ToggleOption.ToggleOptionBuilder)` | `configureBackground` | `CoordinatesChildHudMod` (+ `Data`) |
| `TypedHudRenderer.method2(ToggleOption.ToggleOptionBuilder)` | `configureTextShadow` | `CoordinatesChildHudMod.Data` |
| `HudElementBase.method18()` | `resetPosition` | `Keystrokes.Data`, `F3ModuleChildMod.Data` |
| `HudElementBase.method27(HudAnchor)` | `setAnchor` | `F3ModuleChildMod.Data` |
| `HudRowElement.method2/Method4/Method5` | `renderRows` / `shouldRender` / `buildRows` | anonymous elements in `HypixelBedwarsResourceCounterChild`, `HypixelBedwarsStatsChild` |

Because the tree is a mix of jar revisions, the *slot* is what matters, not the
current placeholder number: e.g. `TypedHudRenderer` declares the text provider
as `method7(boolean)` while `Cps.Data` declares the matching override as
`method2(boolean)`; both are mapped to `getText` in their own cluster.

## Deliberately skipped / uncertain

* `MixinCore9Extension.method30()` / `method31()` overrides
  (`Coordinates.Data.method30`, `CoordinatesChildHudMod.Data.method30`,
  `DirectionHud.Data.method31`, `InventoryHud.Data.method31`,
  `Keystrokes.Data.method31`, `F3ModuleChildMod.Data.method30/31`): the defaults
  are never called anywhere in the tree (only the private static
  `HudElementBase.method20` compares `method31()` against a boolean), so the
  purpose could not be pinned down. Cluster 082 owns the declarations.
* `MixinCore9Extension.method29/32/33` and `HudElementBase` geometry hooks
  (`method13/14/15/16/17/19/20/22/25/26/27`) are not declared in this cluster.
* `Keystrokes.field9` / `field10` — two `public static final float = 0.0F`
  with no reader in the class (dead constants, probably old padding).
* `HypixelBedwarsTeamDisplayChild` `type6`/`type7` are locals of the Tick
  handler (`Type type6 = field9.get()`), not members.
* `HypixelBedwarsUpgradeDisplayChild$…$Data` (→ `BedwarsUpgrade` in
  `tools/renames/classes-*.tsv`, not applied yet): the owner string in the map
  uses the **current on-disk** name; remap the owner if the class rename lands
  first.
* `CoordinatesHud` looks like a **stale duplicate** of `Coordinates`:
  `ModsSettings` instantiates `mod.hud.Coordinates`, and only the dead
  `CoordinatesBiomeChildMod`/`CoordinatesDirectionChildMod` reference
  `CoordinatesHud`. Rows are provided for both (same names), but dedupe before
  applying.

## Applier hazards

* 69 rows are multi-declaration `SPLIT:` rows (outer member + inner
  `Data`/anonymous member share one placeholder). `apply_member_renames.py`
  drops them by default; with `--allow-multi-decl` only the primary semantic
  listed first in the evidence is correct, the other declaration in the same
  file would get the same name.
* `FIELD_DECL` in the applier also matches `return field4;` style lines
  (`HypixelBedwarsTeamDisplayChild` `DEFAULT_SKIN` is counted twice because of
  `return field4;`), so a few otherwise-clean field rows are dropped too.
* No renamed member is read by reflection or by string name. The settings
  system addresses options by *option id* (the quoted strings), which are
  independent of member names; `getId()` strings are real and untouched.
* `F3ModuleChildMod` has two option keys used twice each ("showProperties" and
  "showTags" for TARGET_BLOCK vs TARGET_FLUID); the fields were named
  `showBlockProperties/showBlockTags` and `showFluidProperties/showFluidTags`
  to keep them distinct.
