# Member cluster 191 — `com.moonsworth.lunar.client.mod.hud` (Skyblock/Stopwatch/Tab/Waila HUDs)

## Summary

Map: `tools/renames/wave5/members-191.tsv` — **422 rows** (319 F, 103 M) over all
25 cluster owners. Dry run of `tools/apply_member_renames.py --map ... --verbose`
reports **0 multi-declaration skips** and 2,507 access renames.

Naming sources used for evidence:

* option ids in the field initialisers (`OptionFactory.method7("showHud")`, …) —
  used verbatim lowerCamelCase for option fields; `UPPER_SNAKE` for static finals
  (patterns, textures, message sets, `DecimalFormat`s only when `static final`).
* `@SerializedName` on `SkyblockVisitorTrackerHud.Data` gives the canonical names
  for 13 persisted counters (`rarities`, `accepted`, `denied`, `gardenXP`, …); Gson
  keys are unaffected by field renames.
* `this.handle(EventX.class, this::methodN)` + body → handler names
  (`onTick`, `onChatMessage`, `onWorldChange`, `onSkillXpUpdate`, `onTabListUpdate`,
  `onSlotUpdate`, `onRenderBossBar`, `onTerminalPhase`, `onStormEnraged`, `onSecond`).
* Apollo bridge `Highlight3Iterator24` fixes the stopwatch/timer semantic pairs:
  `StopwatchChildMod.method15/16/17` = start/stop/reset, `TimerChildMod.method16/17/19`
  = start(resume)/stop/reset; `StopwatchMigration` + `Killsounds_2` show
  `Stopwatch.field11`/`method15`/`method16` = migrated flag/setter/getter.
* HUD framework shape in `TypedHudRenderer`: `Data.method15()` = size,
  `Data.method2/method7(boolean)` = value function, `method4(boolean)` = update.
* reference clients/open source directories were searched for the same features;
  the Skyblock HUDs, Stopwatch and Tab are Lunar-specific (Apollo-only mods), so the
  naming comes from the runtime flow above, not from a reference client.

Cross-file call sites that this map renames (verified by the applier dry run):

| consumer | renamed members |
|---|---|
| `GuiPlayerTabOverlayTabMixin`, `GuiPlayerTabOverlayMixin2`, `GuiIngameHudMixin` | `Tab` getters `getHidePing`/`getDisplayPlayerHead`/`isLunarIconsOnRight`, `renderLeftIcon`, `renderPingNumber`, `shouldHidePing` |
| `ToggleActionType`, `Calculator2Type` | `ToggleSneakHudChild.field12/13/14` → `sprintingText`/`sneakingText`/`flyingText` |
| `WailaHandler`, `WailaHandler3`, `StringWailaComponent` | `WailaHud.method16/17` → `getTextColor`/`getTextShadow` |
| `Highlight3Iterator24` | `Stopwatch.addStopwatch/removeTimer`, `StopwatchChildMod.stop/reset`, `TimerChildMod.start/stop/reset`, `applyRemoteSettings` |
| `StopwatchMigration`, `Killsounds_2` | `Stopwatch.isMigrated`/`setMigrated` — **will not be rewritten by the current access pass**, see gap #2 |

No member in this cluster is looked up reflectively / by string (no `Class.getDeclared*`
or config-driven member names found). All option ids live in `OptionFactory`
initialisers, independent of the Java names.

## Skipped / deferred rows

### 1. Overloaded placeholder names (9 rows dropped by the applier's multi-decl guard)

| owner | old | proposed name | why nothing applied |
|---|---|---|---|
| SkyblockVisitorTrackerHud | method1 | onChatMessage | outer chat handler + nested `Data.method1` (recordOffer) |
| SkyblockVisitorTrackerHud | method13 | saveVisitorData | outer JSON save + nested `Data.method13` (getOvergrownGrass) |
| SkyblockVisitorTrackerHud | method14 | createPreviewData | outer static preview factory + nested `Data.method14` (getDedicationFour) |
| Waila | method1 | updateTarget | outer tick handler + `Data2.method1` (components getter) |
| Waila | method3 | renderNoTarget | outer no-target hook + `Data.method3` (render override) + `Data2.method3` |
| Waila | method4 | getBreakTimeString | outer helper + `Data.method4` (update override) |
| WailaHud | method1 | updateTarget | same shape as Waila |
| WailaHud | method3 | renderNoTarget | same shape as Waila |
| WailaHud | method4 | getBreakTimeString | same shape as Waila |

### 2. Outer/nested field-name collisions (12 rows deferred, VisitorTrackerHud)

`SkyblockVisitorTrackerHud` declares `field8..field13` **twice**: once on the mod class
and once on the nested `Data`. Both declarations match the applier's field pattern, so
neither can be renamed until a scope-aware pass exists. Intended names:

* outer: `field8 profileIdListener`, `field9 GSON`, `field10 SAVE_FILE`,
  `field11 OFFER_PATTERN`, `field12 DROP_PATTERN`, `field13 NUMBER_FORMAT`
* `Data`: `field8 greenBandana`, `field9 overgrownGrass`, `field10 dedicationFour`,
  `field11 musicRune`, `field12 copperDye`, `field13 spaceHelmet`

### 3. Methods whose placeholder number is also an `AbstractFeature` registration call (4 rows, unsafe with this applier)

The private handler and the constructor's inherited registration call share the same
`methodN`, so renaming the declaration would also rename `this.methodN(ModTraits, …)`:

| owner | old | proposed name |
|---|---|---|
| SkyblockSkillGlobeHud | method1 | onSkillXpUpdate |
| SkyblockSkillProgressBarHud | method1 | onSkillXpUpdate |
| SkyblockVisitorHud | method1 | onTabListUpdate |
| SkyblockTerminalStatus | method3 | onTerminalPhase |

These become renamable once `AbstractFeature.method1(Trait,Object)` /
`method3(Trait,Object)` are renamed family-wide (framework cluster) or the access pass
becomes signature-aware.

### 4. Overrides of still-lazy Lunar framework members (family renames belong to the framework clusters)

* every HUD's `method2(RootSettingsBuilder|RootSettingsAssembler)` / `method45` path →
  `registerOptions` (`AbstractFeature.method2`, 407 subclasses in tree).
* nested `Data` overrides in `TypedHudRenderer`/`HudElementBase`:
  `method15()` → `getSize`, `method2/method7(boolean)` → `getValue`,
  `method4(boolean)` → `updateValue`, `method16()` → `getDefaultAlignment`,
  `method23()` → `isTextOptionHidden`, `method17/19/20/22` → `shouldRenderBackground`/
  `shouldRenderBrackets`/`shouldUseStaticWidth`/`shouldUseStaticHeight`,
  `method5()` → `getConditionSet`, `method3(EventRenderHudBase,…)` → `render`.
  Renaming only the subclass would silently break the override, so these stay lazy.
* `Data.method30()` (Visitors/Whisper trackers) overrides an obfuscated-name framework
  member (`HHRRRCCCHIOCOCRHHHRIHHCCRHORRI`); leave to the obf map.
* `HudElementBase.method1(JsonObject)`/`load` saves: `load` is already named; the
  writer side (`method1(JsonObject)`) is an interface member of `JsonPersistable`.

### 5. Other blocked/unclear methods

* `Stopwatch.method5/method8/method13/method14` — the numbers collide with
  `AbstractFeature.method5()` (initialize), `method6(String,T)` (I18n lookup),
  `method8(String,Object[])` (I18n) and the JSON save/load overloads, so no row.
  Semantically they are `addTimer`, `removeStopwatch`, `clearTimers`, `tick`.
* `StopwatchChildMod.method15` (start) and `TimerChildMod.method15` (stop) — the same
  name is used by the anonymous `TypedHudRenderer.method15()` size override in the
  same file (multi-decl guard).
* `StopwatchChildMod.method12/13/14`, `TimerChildMod.method20/21/22` — static
  factories collide with inherited/other declarations; intended `fromConfig`/`create`.
* `ToggleSneakHudChild.method5(...)` — overloaded with the `HudConditionSet method5()`
  framework override; intended `formatActionTextWithPartition`.
* `Tab.method5(...)` (static logo renderer) — collides with the constructor's
  `this.method5(ModTraits…)` registration call; skipped.
* local variables that `members-lazy.tsv` lists as `F` rows (`text2`, `number8`,
  `value5`, …) were ignored — only real class fields are mapped.

## Hierarchy notes

* All 25 owners extend `com.moonsworth.lunar.client.framework.AbstractFeature`
  (`SkyblockVitalityHud` via `Framework7Extension2`); nested `Data` classes extend
  `TypedHudRenderer`/`HudElementBase`. None of the renamed methods override an
  external Minecraft/Java/Forge method, so no external `@Override` names were needed.
* `Tab.method34/35/36/37` and `Waila/WailaHud.method15/16/17/19` are Lombok
  `@Generated` getters; names follow the field they return (`getHidePing`, …).

## Applier gaps observed while validating (for the main agent)

1. **Nested receiver types are not resolved** (`Waila.Data2`, `SkyblockVisitorTrackerHud.Data`,
   …): `qualify("X.Data")` yields `X/Data` which is not a key in `by_owner`. Access
   renames of nested-class methods from outer code (`data0.method18(…)`,
   `data2.method7()`) are therefore missed. Rows for nested-class **methods** were
   excluded from this map for that reason; nested-class **fields** are safe because
   the declaration pass rewrites `this.fieldN` (and `Outer.this.fieldN` as a substring).
2. **`instanceof` pattern declarations are not indexed**: `StopwatchMigration` L15 and
   `Killsounds_2` L12 use `obj instanceof Stopwatch stopwatch4` then
   `stopwatch4.method16()`. The access pass never touches these two files, so applying
   `Stopwatch.method16 → isMigrated` needs either an `instanceof T name` rule in
   `_file_type_maps` or the two rows dropped. (I kept the rows: the rename is correct,
   the resolver just needs 3 lines.)
3. **`Outer.this.field` accesses** are handled by the declaration pass via the
   `\b(this|super)\.` substring rule (verified: `Waila.this.field30` →
   `Waila.this.horizontalSpacing`), but not by the access pass — fine for this map.
4. Overload/multi-decl guard: see deferred sections 1–3.
