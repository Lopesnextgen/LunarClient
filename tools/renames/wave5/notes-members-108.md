# Cluster 108 — member renames (`fishing.click`, `fishing.colorsaturation`, `fishing.coordinates`)

Map: **`tools/renames/wave5/members-108.tsv` — 398 rows over 34 owners.**

Owners are the *current* FQCNs (wave-5 class renames already applied). Nested
classes are keyed with `$` (`FaceMerger$Data`, `TextComponentFactory$Data`,
`VoidgloomBoss$Data`, `MiddleClickGuiRegistry$Data`, …).

The handed cluster (`member-cluster-108.tsv`) counts **1,051 lazy declarations**;
627 of them are type `M`/`F` and 424 are locals/params (`kind = P`,
`valueN`/`numberN`/`flagN`) or decompiler locals mis-typed as `F`
(`value7`, `number42`, `type0`, `var4`, …). Locals/params are out of scope for
this map — they belong to `tools/clean_locals.py`. Of the 627 M/F rows the map
names all but three (`IslandUtils.field1`, `method7`, `method8`, see below); the
remaining lazy tokens outside the map are the 121 decompiler locals that the
inventory mis-typed as `F`/`M` (out of scope).

## Evidence highlights

* **`SkyblockRenderDebugUtilities` is a name oracle.** The debug mod calls the
  `WorldRenderUtils` helpers and labels each one with a string that is the
  original method name: `drawBoxAtCoordinate`, `drawFancyBox`, `drawFilledBox`,
  `highlightBlockAt`, `getAABB`, `drawBoxAt`, `highlightBlockFace`,
  `renderDebugLine`, `renderLines`, `renderLinesJoml`, `renderLine`,
  `renderLines (thick)`, `drawLine`, `drawLineFromCamera`, `draw3DCircle`,
  `drawFilled3DCircle`, `draw3DCylinder`, `drawBeaconBeam`, `fadingBoxBeam`.
  Those names were copied verbatim into the map (labels line up one-to-one with
  the calls).
* **`FontGlyphs` (91 constants).** Every PUA code point in the class is mapped in
  `src/main/resources/assets/lunar/emoji/shortcodes.json`; names are the emoji
  shortcodes uppercased with a `GLYPH_` prefix (`\ue000` → `GLYPH_HASH`,
  `\ue018` → `GLYPH_ID`, …). The class has **no references in the tree** and is
  duplicated by the stale `Click13.java`, so this rename is cosmetic. If the
  glyphs are really Hypixel resource-pack icons rather than emoji, the names are
  the best proxy available offline — see "uncertain" below.
* **`VoidgloomBoss`** is a Lombok `@Data`-style class: the real `getOwner()`
  /`setOwner()` survived, the rest are `methodN`. Field meaning was recovered from
  `SkyblockEndermanSlayer` (nameplate regex `field10`, skull/beacon armour-stand
  handlers, block-change beacon pos, `EntityFinder` distance check).
* **`Coordinates`/`SkillLevelCalculator`** read `max-levels.json`
  (`Module.method3("max-levels.json", MaxLevels.class)`) and `skill-xp.json`
  (`"runecrafting"`/`"social"`/`"skill"`, `"catacombs"`, `"pet100"`, `"pet200"`).
* **`TextComponentFactory$Data`** is the "- label: value" builder used by ~20
  HUDs; method roles follow the call sites (`method2` label, `method4` value,
  `method5/7` colors, `method12` showLabel, `build` already named).
* **`MiddleClickGuiRegistry`** matches container titles by exact/prefix/suffix/
  contains into middle-click slot sets; `CrashReporter` tag
  `"SkyBlockMiddleClickGuis"`.
* **`LineFitter`/`FaceMerger`/`EntityFinder`** are RANSAC / coincident-quad /
  nearest-entity helpers: `FaceMerger` quantizes at 1/10000, `MaskedMap` ORs
  side bitmasks and drops internal masks 3/12/5/10/15.

## @Override / external names

* `click.JsonDeserializerImpl`, `click.Vec3iDeserializer`,
  `colorsaturation.JsonDeserializerIterator`, `colorsaturation.MinionDataDeserializer`,
  `coordinates.JsonDeserializerIterator` → `method1 = deserialize`
  (`com.google.gson.JsonDeserializer` external override).
* No `main`/`equals`/`hashCode`/`toString`/Lombok accessor was touched;
  `getOwner`/`setOwner` in `VoidgloomBoss` were already real.

## Skipped / uncertain

| What | Why |
|---|---|
| `IslandUtils.field1` + `method7`/`method8` (boolean getter/setter) | zero in-tree callers, no semantic evidence; left untouched |
| `FontGlyphs` names | based on `emoji/shortcodes.json`, not on Lunar source; class is dead code (see below) |
| `VoidgloomBoss.field2` = `nameTag`, `field3` = `marker` | structural evidence is strong (custom name parsed from `field2`, companion used only for proximity) but the original names may differ (`bossNameTag`/`skullStand`) |
| `FaceMerger$BlockBox`/`$FaceKey` `plane` component | which axis `field2` encodes depends on the face `Type`; kept axis-neutral |
| `SkyblockCalendar.field1` | named `FIRST_YEAR_START` (1560275700 = SkyBlock year 1 epoch); not verifiable against a Lunar symbol |
| locals/params (`kind = P`, mis-typed `F`) | out of scope for this map |

## Duplicates worth reporting to the class-rename owners

1. **`click/WorldRenderUtils.java` is a near-copy of `click/Click.java`**
   (cluster 107, 545 lazy members). Both declare `method1..method62` with the
   same bodies (only renamed type references differ). `members-107.tsv` (written
   after this map started) names the twin differently for part of the API. The
   names here keep the `SkyblockRenderDebugUtilities` labels where they exist;
   the main divergences to reconcile are:

   | old | this map (WorldRenderUtils) | `members-107.tsv` (Click) |
   |---|---|---|
   | method1-3 | `drawBoxAtCoordinate` | `drawBlockOutline` |
   | method4 | `drawFancyBox` | `drawBlockOutline` |
   | method5 | `drawFancyBox` | `drawBlockHighlight` |
   | method6/7 | `drawFilledBox` (quads via `method26`) | `drawBlockOutlineBatched` / `drawBoxWires` |
   | method9 | `drawString` | `drawStringCentered` |
   | method13 | `applyBillboardTransform` | `beginTextTransform` |
   | method14 | `highlightBlockAt` | `drawBlockHighlightAt` |
   | method15/16 | `renderDebugLine` | `drawLine` |
   | method17/18 | `renderLines` / `renderLinesJoml` | `drawLines` / `drawFishingLines` |
   | method22 | `addLineVertices` | `drawDashedLine` |
   | method50 | `drawLine` | `drawLineWithArrow` |
   | method51 | `drawLine` | `drawLineImpl` |
   | method53 | `drawLineFromCamera` | `drawLookLine` |
   | method54 | `draw3DCylinder` | `drawCylinderWalls` |
   | method55 | `createCirclePoints` | `circlePoints` |
   | method56 | `getAABB` | `getBlockAABB` |
   | method57 | `fadingBoxBeam` | `drawBlockOutlineFade` |
   | method59 | `drawBeaconBeam` | `drawBeaconBeamInternal` |
   | method60 | `prepareComponentScale` | `pushHudTransform` |
   | method62 | `renderTooltip` | `layoutTooltip` |

   Note that `Click.method6/7` use the same `method26` quad-fill helper as
   `method5`, so `drawFilledBox` is the accurate reading; the debug harness
   explicitly labels method28/29/54/57/58/59 as `draw3DCircle` /
   `drawFilled3DCircle` / `draw3DCylinder` / `fadingBoxBeam` /
   `drawBeaconBeam`.
2. **`click/Click13.java` is a stale duplicate of `click/FontGlyphs.java`**
   (same 91 char constants, old class name, no references). One of them should
   be dropped by the class wave. `members-107.tsv` renamed the `Click13` copy to
   `GLYPH_E000…` (code-point names) while this map names the `FontGlyphs` copy
   `GLYPH_HASH…` (emoji-shortcode names); converge on one scheme when the
   duplicate is removed.
3. **`coordinates.Coordinates` vs `coordinates.SkillLevelCalculator`** are
   old/new copies of the same skill-level math (differ only in the clamp helper
   and `GuiType3` vs `ItemRarity`); both got the same member names for
   consistency.

## Applier notes (state at hand-off)

The map was applied into the working tree on 2026-09-16 ~17:45 (before commit
`4227f6adb`); the tree currently shows **281 of 398 rows applied**.
Unapplied (117 rows):

* all nested-class owners — `FaceMerger$Data/$BlockBox/$EdgeKey/$BlockFace/$FaceKey/$MaskedMap/$MaskedMap$Data`,
  `MiddleClickGuiRegistry$Data`, `TextComponentFactory$Data`,
  `VoidgloomBoss$Data` (the applier's `owner_file()` cannot resolve
  `Outer$Inner`, so it reports `!! no source for …` and skips the group);
* outer members whose lazy name is also declared in a nested class and which
  the file-wide multi-decl guard therefore drops: `FaceMerger.field1`,
  `MiddleClickGuiRegistry.field1`/`field2`, `VoidgloomBoss.field1`/`field2`/`field3`.

### ⚠ Collision damage to fix (class-aware pass required)

Where an outer class and its nested class share a lazy name, the applied run
renamed *references* with the outer class's new name while the nested/outer
declarations stayed behind (or vice versa). Current broken spots:

| file | current (wrong) | intended |
|---|---|---|
| `TextComponentFactory$Data` | `styleOf(TextComponent)`, `builder(String)`, `colorForRatio(TextComponent)`, `itemLine(String)`, `itemLine(TextColor)`, `clickable(int)`, `clickable(TextColor)` | `label`, `label`, `value`, `value`, `labelColor`, `labelColor`, `valueColor` |
| `TextComponentFactory$Data` | `field1..field6` unchanged | `label`, `value`, `labelColor`, `valueColor`, `separatorColor`, `showLabel` |
| `MiddleClickGuiRegistry` | outer `field1`/`field2` declarations kept, references renamed to `EMPTY_SLOTS`/`exactMatches` | rename the declarations too |
| `MiddleClickGuiRegistry$Data` | `this.EMPTY_SLOTS = text1; this.exactMatches = ...` (assigns outer static final / final fields) | `this.name = ...; this.slots = ...`; getter `getSlots` |
| `VoidgloomBoss` | `this.boss/nameTag/marker` references, `field1..field3` declarations kept | complete the field rename |
| `VoidgloomBoss$Data` | methods renamed with the outer names (`update`, `getBoss`, `getNameTag`, `getMarker`, `getType`, `getSkulls`), refs to `this.boss/nameTag/marker` | `getBeacon`, `getPos`, `getTimestamp`, `setBeacon`, `setPos`, `setTimestamp`; fields `beacon`/`pos`/`timestamp` |

Do **not** run this map with `--allow-multi-decl`: the declaration pass would
then rewrite every `this.<name>` in the file (outer + nested classes) to one
new name, which is what produced the table above.

### Tooling observations (still valid)

* `owner_file()` needs a `$`-aware fallback to the outer file **and**
  per-declaring-class declaration counting.
* `MEMBER_DECL`/`FIELD_DECL` also match lines like `return method2(` and
  `this.method1(` (`return`/`this.` parse as type tokens), so several
  strictly-unique rows are counted as multi-declaration:
  `EntityFinder.method2/3`, `IslandUtils.method2`, `LineFitter.method2`,
  `SkyblockCalendar.method5/7`, `WidgetFactory.method2/4`, `MiddleClickGuiRegistry.field2`.
* `FIELD_DECL` misses fields whose type contains a space
  (`Map<String, String> field1;`): `Colorsaturation`/`MinionXpData` fields and
  `MiddleClickGuiRegistry.field6`.
* The lazy inventory missed four `WorldRenderUtils` methods that still exist in
  the source (`method20`, `method39`, `method48`, `method49`); they are included
  in this map.
