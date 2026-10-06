# Cluster 21 — `com.moonsworth.lunar.client.lighting` + `com.moonsworth.lunar.client.mod.hud` (36 classes)

Source revision: `tools/renames/cluster-21.txt` md5 `6f69f2ccce91de7a862ad62fbeb1dd05`
Map: `tools/renames/classes-lighting-modhud.tsv` md5 `b6e7b31ef39eac3e47df04e03e538bb5`
(18 top-level `lighting` types + 18 nested HUD `Data2`/`Data3`/`Type2`).

> The previous `classes-21.tsv`/`.md` pair described the
> `framework.feature.mod.fishing.click` package (a different, already-applied
> cluster). The cluster files were regenerated; cluster 21 is now this pair of
> packages and the old map remains in git history.

## What the `lighting` package actually is (the brief is wrong)

The brief guessed **"Lunar's block/item lighting engine (light level colours,
ambient darkening, `lighting*` option keys)"**. The sources contain none of
that. `com.moonsworth.lunar.client.lighting` is Lunar's **options / settings
framework** (the thing every mod uses to declare its toggles and sliders):

* the option contract `LightingExtension<T>` (its `@Annotation(... = Type.SETTING)`
  factory methods live in the `Lighting` interface) — *not in this cluster*;
* `Lighting2` is a **KeyBind** (`alt/shift/control/value`, `toString`
  `"KeyBind(...)"`);
* `LightingType2` is an option **category** (`GENERAL/PERFORMANCE/CONTROLS/
  FEATURE/REWIND`);
* `Gui2Extension2`/`Gui2Extension3` are enum options
  (`DISABLED/FORCE_ON/FORCE_OFF`, `FALSE/DEFAULT/TRUE`);
* the string `"Must have at least one option for OptionCombiner!"` names
  `LightingExtension2` as `OptionCombiner`;
* `"DevOptionBaker can only be used in a development environment!"` names
  `LightingIterator22` as `DevOptionBaker`;
* `framework/nameplate/Nameplate6.method7()` does
  `new LightingExtension23(ThreadModuleDump43Extension22::new)`, hands it to
  **every** mod's `method2(LightingExtension23)` (587 token hits across the
  tree) and calls `method12()` to get `Map<Option, LightingHandler2>` — this is
  the per-feature settings-tree root.

The package *name* is a leftover of the decompiler's package scrambling (the
real lighting/fog engine is `client.fog`, and the option-key type is
`client.lightoverlay.Lightoverlay*`). Several sibling types referenced here are
quarantined and absent from the tree (`Lighting`, `LightingExtension`,
`LightingExtension2_2`, `LightingHandler_3`, `LightingExtension4222`,
`LightingExtension443`, … at `tools/work/quarantine/src/.../lighting/`), so the
package does not compile today regardless of naming.

`com.moonsworth.lunar.client.mod.hud` really is HUD code: each outer class is a
mod and the listed `Data2`/`Data3`/`Type2` are its nested HUD element, data
model or option enum.

## Renames (36 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Gui2Extension2` | `TriState` | enum `DISABLED/FORCE_ON/FORCE_OFF` implementing `Gui2Extension`; read by holograms `GuiRewindhandlersHandler23_2` |
| 2 | `Gui2Extension3` | `BooleanOption` | enum `FALSE/DEFAULT/TRUE` implementing `Gui2Extension`; `orElse(boolean)` resolves `DEFAULT` |
| 3 | `Lighting2` | `KeyBind` | `(alt,shift,control,BridgeType_8)` + `Codec` fields alt/shift/control/value; `toString "KeyBind(...)"` |
| 4 | `Lighting3` | `JsonConfigurable` | `load(JsonObject)`/`method1(JsonObject)`/`priority()=1000`; base of `LightingExtension`, implemented by `Nameplate6` |
| 5 | `LightingExtension2` | `OptionCombiner` | delegates to an array of options; string `"Must have at least one option for OptionCombiner!"` |
| 6 | `LightingExtension22` | `SettingsTreeBuilder` | byte-identical but unused duplicate of `LightingExtension23` |
| 7 | `LightingExtension23` | `RootSettingsBuilder` | root settings-tree builder created by `Nameplate6.method7`, passed to every mod `method2` |
| 8 | `LightingExtension3` | `ConditionalOption` | delegates to an inner option, no-op while a `BooleanSupplier` is true |
| 9 | `LightingHandler2` | `SettingsNode` | `implements Lighting_4`; option + children + prerequisites + visibility supplier |
| 10 | `LightingHandler_2` | `AbstractOptionBuilder` | abstract fluent builder (B/O/T): codec/default/lang/badges/feature-link; extended by `LightingBase` |
| 11 | `LightingIterator2` | `OptionBaker` | maps `SettingsNode` → `LightingHandler` feature nodes keyed by feature id |
| 12 | `LightingIterator22` | `DevOptionBaker` | dev-only `OptionBaker`; string `"DevOptionBaker can only be used in a development environment!"` |
| 13 | `LightingType2` | `OptionCategory` | enum `GENERAL/PERFORMANCE/CONTROLS/FEATURE/REWIND` |
| 14 | `Lighting_2` | `SettingsTreeMapper` | generic `Map<Option,IN>`→`Map<Option,OUT>` mapper (method2/3/4) |
| 15 | `Lighting_3` | `SettingsRegistrant` | `void method1(RootSettingsBuilder)`; framework values that register options (`Nameplate6.method9`) |
| 16 | `Lighting_4` | `OptionTreeNode` | interface `getChildren()` + `BooleanSupplier` |
| 17 | `Lighting_5` | `SettingsBuilder` | add option/group/label (method2/6/8/10/12/15), `method18()` settings |
| 18 | `Lighting_7` | `OptionProvider` | `getId()` + build `method1()`; static `method2` flattens providers |
| 19 | `Data2` (`AudioSubtitles`) | `SubtitlesHudElement` | inner extends `MixinCore9`; draws subtitle entries with fade + `< >` arrows |
| 20 | `Data2` (`HypixelBedwarsTeamDisplayChild`) | `BedwarsTeamMember` | (name, UUID, isBot) + skin lookup + scoreboard score |
| 21 | `Data2` (`HypixelBedwarsTimersChildHud`) | `BedwarsTimersHudElement` | inner extends `MixinCore9`; draws the timer list `TreeMap<Long,Data>` |
| 22 | `Data2` (`HypixelBedwarsUpgradeDisplayChild`) | `BedwarsUpgradeRegistry` | name→`Data` map + tier maps; `method11` adds/levels upgrades |
| 23 | `Data2` (`ItemCounter`) | `HudRectangle` | x/y/width/height bounds computed from the enabled item elements |
| 24 | `Data2` (`SkyblockCropTrackerHud`) | `CropDropCounts` | `@SerializedName` cropie/squash/fermento/helianthus counters + `addDrop` |
| 25 | `Data2` (`SkyblockFarmingHud`) | `FarmingLevelProgress` | `(levelIndex, remainder)` from `method16` distributing a count over tiers |
| 26 | `Data2` (`SkyblockFishingBaitHud`) | `BaitStack` | bait item stack (item, id, display name, amount) with decrement/merge |
| 27 | `Data2` (`SkyblockHarvestFeastTrackerHud`) | `HarvestFeastHudElement` | inner extends `MixinCore8<List<HudLine>>` |
| 28 | `Data2` (`SkyblockScathaTrackerHud`) | `ScathaTrackerHudElement` | inner extends `MixinCore9Base`; builds the Scatha widget tree |
| 29 | `Data2` (`SkyblockVisitorShoppingListHud`) | `ShoppingListItem` | (item name, amount) |
| 30 | `Data2` (`SkyblockVisitorTrackerHud`) | `VisitorTrackerHudElement` | inner extends `MixinCore8<List<HudLine>>` |
| 31 | `Data2` (`Waila`) | `WailaRenderContext` | (Waila mixin, `Gui2Handler`, `Horsestats12`) |
| 32 | `Data3` (`SkyblockFarmingHud`) | `FarmingValueSample` | `(double value, long timestamp)`; averaged by `method14`, aged out after 10 s |
| 33 | `Data3` (`SkyblockScathaTrackerHud`) | `ScathaSessionStats` | rare/epic/legendary pets, streak, worm/scatha/total, sinceLastPet |
| 34 | `Type2` (`PackDisplay`) | `PackTextMode` | enum `NONE/FULL/WHITE_ONLY` implementing `Gui2Extension` |
| 35 | `Type2` (`SkyblockInvincibilityHud`) | `InvincibilityTimerUnit` | enum `SECONDS/TICKS`; id `"skyblockInvincibility"+name` |
| 36 | `Type2` (`SkyblockTickTimerHud`) | `TickTimerUnit` | enum `SECONDS/TICKS`; id `"skyblockTickTimer"+name` |

Nested rows carry the 5th TSV column with the exact relative file path, because
`Data2`/`Data3`/`Type2` are declared in many other packages (and in several HUD
files).

## Applier dry run, and the collision rows

`python3 tools/apply_class_renames.py --map tools/renames/classes-lighting-modhud.tsv`:

```
applied=14 skipped=22 files_touched=309 files_renamed=14 mode=dry-run
```

The 14 lighting rows with unique old names apply cleanly (largest:
`LightingExtension23 -> RootSettingsBuilder`, 587 hits; `Lighting_7 ->
OptionProvider`, 106 hits). The 22 skips are **old-name collisions across
packages/files**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Gui2Extension2` | 13 packages (blockoutline, crosshair, keystrokes, highlight, storageoverlay, rewindhandlers, tiertagger, …) |
| `Gui2Extension3` | 7 packages (crosshair, keystrokes, highlight, dungeonwaypoints, rewindhandlers, …) |
| `Lighting2` | `com.moonsworth.lunar.bridge.lighting` (a scoreboard entry) |
| `Lighting3` | `com.moonsworth.lunar.bridge.lighting` |
| `Data2` | 12 packages (fishing.gui, gui.nameplate, holograms.*, onesevenvisuals, highlight, markers.gui, util.chest, forge.lib, ichor.mixin, …) |
| `Data3` | 7 packages (holograms.*, guiRewindhandlers, horsestats, pkg, forge.lib, ichor.mixin) |
| `Type2` | 6 packages (fishing.gui, highlight.fishing, markers.mixin.gui, mod.render, util, forge.lib) |

**Do not run this map with `--allow-collisions`.** It would rename the same
simple name in unrelated packages (the framework HUD-anchor enums
`framework.Gui2Extension2`/`keystrokes.Gui2Extension3`, the scoreboard
`bridge.lighting.Lighting2/3`, dozens of unrelated `Data2/Data3/Type2`),
merging distinct types. These 22 rows need the planned nested/package-aware
rewriter (same follow-up as `classes-07.md`, `classes-12.md`, `classes-15.md`).

## Caveats / follow-ups

* **`LightingExtension22` vs `LightingExtension23`** are byte-identical except
  for the name. Only `LightingExtension23` is referenced (587 hits, all
  `method2(LightingExtension23)` overrides); `LightingExtension22` is dead. It
  keeps a distinct name (`SettingsTreeBuilder`) so the tree-wide uniqueness rule
  holds.
* **Name neighbours are only risky in the `lighting`/`mod.hud` packages.** New
  names (`RootSettingsBuilder`, `OptionBaker`, `DevOptionBaker`,
  `SettingsTreeBuilder`, `SettingsBuilder`, `SettingsNode`, `SettingsTreeNode`,
  …) were checked with a declaration scan: none are declared anywhere in
  `src/main/java`. Re-check after edits:
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java`.
* **`LightingExtension2` string provenance.** `OptionCombiner` is the only
  option-framework name recoverable verbatim from a source string; the rest are
  role-based because the real Lunar names are not in
  `tools/mappings-snapshot/lunar-client-names.tsv` (only `ModLighting` appears)
  nor in the `.kin` platform mappings (those cover vanilla/forge/optifine, not
  Lunar's own classes).
* **Missing sources.** `Lighting.java`, `LightingExtension.java`,
  `LightingExtension2_2.java`, `LightingHandler_3.java` and the option factory
  `LightingExtension4222/443` are quarantined under
  `tools/work/quarantine/src/.../lighting/`, not in `src/main/java`. The
  `LightingExtension2$Extension.java` / `$Extension2.java` flattened companions
  do exist. The dry-run above only rewrites tokens in the present tree.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-lighting-modhud.tsv
# → applied=14 skipped=22 files_touched=309 files_renamed=14 mode=dry-run
```