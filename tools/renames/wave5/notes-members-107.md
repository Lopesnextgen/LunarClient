# Members — member-cluster-107 (`fishing.chest` + `fishing.click`)

Cluster: `tools/renames/wave5/member-cluster-107.tsv` (25 owners, 1,626 lazy
inventory rows). Map: `tools/renames/wave5/members-107.tsv` — **496 rows**
(275 methods, 221 fields) over 47 owner FQNs. No sources were edited by this
pass.

## What this cluster is

A duplicated 1.8 SkyBlock fishing/rendering toolkit. Most owners come in pairs:
one fully renamed class from the merge and one leftover `ClickN`/`JsonDeserializerIterator`
copy that still had lazy member names. Names were assigned so the copies read
the same; see the twin table below.

| ClickN copy | real-named twin | twin cluster | vocab |
|---|---|---|---|
| `Click` (62 methods) | `WorldRenderUtils` | 108 | world render helpers; `Click` got the names listed in the map, the 108 twin should reuse them per method number (`drawBlockOutline`, `drawBoxWires`, `drawBoxFilled`, `drawLine`, `drawString`, …) |
| `Click2` | `CalculatorParser` | 107 | identical bodies — same names in both |
| `Click3` | `IslandUtils` | 108 | `hasIsland` vs twin `isOnIsland` (divergence) |
| `Click4` | `AnimatedValue` | 107 | identical bodies — same names in both |
| `Click5` | `CircleFitter` | 107 | identical bodies — same names in both |
| `Click6` | `MiddleClickGuiRegistry` | 108 | `findSlots/registerStartsWith/registerEndsWith` vs twin `find/registerPrefix/registerSuffix` (divergence) |
| `Click7` | `LineFitter` | 108 | `createSegment` vs twin `createLineSegment` (divergence) |
| `Click8` | `RollingWindow` | 108 | `next` vs twin `advance` (divergence) |
| `Click9` | `ParticleSample` | 108 | `getPosition/getWeight/getTimestamp`; twin applied `position/y/timestamp` — `y` is wrong, field is the sample weight used by the RANSAC fit |
| `Click10` | `VoidgloomBoss` | 108 | `entity/nameEntity/anchorEntity/shields/spawnedEntities/...` vs twin `boss/nameTag/marker/skulls/endermen/...` |
| `Click11` | `SkyblockCalendar` | 108 | `SKYBLOCK_EPOCH/getCurrentTimeSeconds/getYearSeconds/getPeriodSeconds` vs twin `FIRST_YEAR_START/now/getElapsedYearSeconds/getElapsedSeconds` |
| `Click12` | `TextComponentFactory` | 108 | `getStyle/getProgressColor/createItemLine/createClickableText` vs twin `styleOf/colorForRatio/itemLine/clickable` |
| `Click13` | `FontGlyphs` | 108 | I used code-point names `GLYPH_E000…`; twin has real icon names (`GLYPH_HASH`, `GLYPH_ASTERISK`, …, `GLYPH_ANGOLA`) — reconcile, see below |
| `Click14` | `EntityFinder` | 108 | **same** (`findNearest` ×3, `distanceSquared`) |
| `Click15` | `AverageInterval` | 107 | identical bodies — same name in both |
| `Click16` | `FaceMerger` | 108 | **same** (`mergeEdges`/`mergeFaces`; nested `Data`/`BlockBox`/`EdgeKey`/`BlockFace`/`FaceKey`/`MaskedMap` layout) |
| `Click17` | `WidgetFactory` | 108 | `withPadding/withBackground/createItemComponent/createLoreLines` vs twin `withBackground ×4/createItem/createLore` |

Evidence per row: declaring source (line-checked against the pre-rename tree),
the consumer call sites listed in the evidence column, and the twin class when
the body is identical. Reference clients (`Documentation/references/mc-client-sources`)
were searched for `CircleFitter`/`CalculatorParser`/`SkyBlockMiddleClickGuis`/
`1560275700` and do not contain these Lunar-only classes, so no external naming
could be copied.

## Skipped / not in the map

* **1,132 inventory rows are not members** — 687 constructor/parameter rows
  (`kind P`), 379 method-local declarations (`varN`, `dataN`, `textN`, …) and
  66 `methodN` hits that are calls (`return methodX(...)` lines). The map is
  declaration-only (`M`/`F`); locals/params are handled by
  `tools/clean_locals.py`, not by member maps.
* **Anonymous Guava loader** `method1(String)` inside `CalculatorParser` and
  `Click2` overrides `com.google.common.cache.CacheLoader.load(String)`; it is
  an anonymous class (no owner FQN) and shares the name with the outer
  `method1`, so it was left out. A signature-aware pass should rename it to
  `load`.
* **`Click16.Data5.v0` / `v1`** are non-lazy placeholder names but form the
  same min/max key layout as the rest of the face key; they were mapped to
  `min2`/`max2` (2 extra rows over the lazy inventory).
* **`Click13` code-point names** — the 91 private-use glyphs were unreferenced
  and have no semantic source in the decompiled tree. `GLYPH_E000`-style names
  are a mechanical stopgap; the cluster-108 twin `FontGlyphs` now carries real
  names for the same code points (same order, verified). Reconcile by renaming
  `Click13.fieldN` to `FontGlyphs.fieldN`'s name, e.g.
  `field1 GLYPH_E000 → GLYPH_HASH`, `field2 GLYPH_E001 → GLYPH_ASTERISK`,
  `field11 GLYPH_E00A → GLYPH_EIGHT`, `field47 GLYPH_E050 → GLYPH_COLOMBIA`.
* **`Click3.enabled / isEnabled / setEnabled`** (was `field1/method7/method8`)
  is a low-confidence guess: the static flag and its `@Generated` accessors are
  unused tree-wide, so only the shape (getter/setter pair) is evidenced.
* Overload families intentionally share the target name (`Click.drawLine`,
  `drawString`, `drawBoxWires`, `drawBlockOutline`, `drawComponentCentered`,
  `Click14.findNearest`, `Click7.fitLine`, `Click12.createItemLine`,
  `Click12.Data.title/value/titleColor/valueColor`, …); parameter lists were
  checked to stay distinct.
* `Click12.Data` and `Click16.Data2` use a field and a fluent/getter method
  with the same name (`title`, `value`, `minX`, …) — legal Java and matches
  the pre-existing `minX()`/`maxX()` getters.

## Overrides / hierarchy

* `ImportantNpcLocationsDeserializer.method1` and
  `JsonDeserializerIterator.method1` implement Gson
  `JsonDeserializer<T>.deserialize(JsonElement, Type, JsonDeserializationContext)`;
  both keep the base name `deserialize` (`ImportantNpcLocationsDeserializer` is
  registered in `Module.java:80`).
* `CalculatorParser`/`Click2` `ExpressionNode.method3` (abstract) and its
  `Data`/`NumberNode` implementations form one override family → `evaluate`
  everywhere. `Data.getPriority()` was already named.
* `AnimatedValue`/`Click4` are self-contained (no external overrides); the
  lazy numbering has a gap (`method1-4`, `method6`) which is preserved.
* Enum constants (`AnimatedValue.Type`, `Click10.Type`, `Click16.Type`, …) were
  already real and were not touched.

## Post-apply audit (important)

The map was picked up and applied by the parent while this pass was still
running (commit `684abfadb`, *member maps 107/108/191/230/232*). The current
applier (`tools/apply_member_renames.py`) scope is **file-wide per old name**,
and its access pass resolves `this.member` to the file's top-level class — so
any file that has several nested classes re-using the same lazy names is
inconsistent in the working tree (state checked 17:55, after the commit).
Observed damage:

| file | symptom |
|---|---|
| `CalculatorParser.java`, `Click2.java` | static fields 5–8 were renamed but bare references were not (`field7.format(…)` vs new `SCIENTIFIC_FORMAT`, `field5.matcher(…)`, `field8.format(…)`); every nested declaration got the top-level names from the same file (`Data.method3→calculate`, `ExpressionNode.method1/2→formatNumber/calculateCached`, `NumberNode.method3→calculate`, `Token.method1/2→formatNumber/calculateCached`, anonymous `CacheLoader.method1→formatNumber`) while calls (`data28.method3()`, `data41.method1()`, `field1.method2()`, `data9.field1`) were left old — the file no longer resolves. |
| `CircleFitter.java`, `Click5.java` | nested `Data`/`CircleCandidate` declarations and accesses got the outer names/fields: `this.RADIUS_EPSILON = value1` (was `field1`), `data211.findCircles()` ×2 for `method1`/`method2`, `data211.getPosition()`/`getY()` for `method1`/`method2`, and `data211.method3()`/`ListUtils.getPosition(...)` calls left behind. |
| `Click16.java` | same pattern: `this.COORDINATE_SCALE = var1` in `Data`, `Data.method1-7` renamed to `mergeEdges/mergeFaces/isExposedEdge/addEdgeBox/addFaceBox/addEdge/addFace`, `Data4.method9` left as outer name; external `Click16.Data2.method1(...)` call sites renamed to `fromArray` correctly but internal state is mixed. |
| `Click12.java` | nested `Data` builder methods got the outer names (`getStyle/builder/getProgressColor/createItemLine/createClickableText` instead of `title/value/titleColor/...`). |
| `Click6.java`, `Click10.java` | nested `Data` methods got outer names: `Click6.Data.method1→findSlots` (want `getSlots`); `Click10.Data.method1-6→update/getEntity/getNameEntity/getAnchorEntity/getState/getShields` (want `getEntity/getPosition/getTimestamp/setEntity/setPosition/setTimestamp`). The `Click10`/`Click6` outer members themselves renamed correctly; `Click6.field1-2` and `Click10.field1-3` stay lazy because of the multi-decl filter. |
| cluster-108 twins | same damage (`VoidgloomBoss.Data.update()/getBoss()/getNameTag()`, `MiddleClickGuiRegistry.Data.find()`). |

**Recommendation:** revert the member application for the files above (or all
of them) and re-apply with an owner/class-scoped declaration pass (scope the
regex to the class body, and resolve nested owners with their full FQN
including the outer path). 79 rows are still lazy after the current apply, all
fields, precisely because the multi-decl filter dropped them:
`CalculatorParser.field1-4`, `CalculatorParser.Data.field1-4`,
`CalculatorParser.NumberNode.field1`, `CalculatorParser.Token.field1-2`,
`Click2.field1-4`, `Click2.Data.field1-4`, `Click2.Data3.field1`,
`Click2.Data4.field1-2`, `CircleFitter.field1`, `CircleFitter.Data.field1-3`,
`CircleFitter.CircleCandidate.field1-2`, `Click5.field1`, `Click5.Data.field1-3`,
`Click5.Data2.field1-2`, `Click6.field1-2`, `Click6.Data.field1-2`,
`Click10.field1-3`, `Click10.Data.field1-3`, `Click16.field1`,
`Click16.Data.field1-7`, `Click16.Data2.field1-7`, `Click16.Data3.field1-7`,
`Click16.Data4.field1-6`, `Click16.Data5.field1-4`, `Click16.Data6.field1`,
`Click16.Data6.Data.field1`, `Click3.field1`.

Files that applied cleanly (no nested classes sharing lazy names): `Chest`,
`NpcLocations`, `ImportantNpcLocationsDeserializer`, `JsonDeserializerIterator`,
`AnimatedValue`, `Click4`, `AverageInterval`, `Click15`, `Click`,
`Click7`, `Click8`, `Click9`, `Click11`, `Click13`, `Click14`, `Click17`,
`Click3` (only `field1` left lazy, no mangling).

## Twin reconciliation (optional, applies after the above)

Rename my applied names to the cluster-108 vocabulary where the twin exists, or
fix the twin to mine. My suggestion: adopt the twin names except
`ParticleSample` (keep `weight` — `getY` mislabels the RANSAC weight) and
`WidgetFactory` (`withBackground ×4` is legal overloading but loses the
padding/background distinction; `withPadding ×2` is clearer).

| owner | applied (mine) | twin (cluster 108) |
|---|---|---|
| `Click3` | `hasIsland`, `updateCoordinates` | `isOnIsland`, `playSound` |
| `Click6` | `findSlots`, `resolveSlots`, `registerStartsWith`, `registerEndsWith`, `startsWithMatchers`, `endsWithMatchers` | `find`, `load`, `registerPrefix`, `registerSuffix`, `prefixMatches`, `suffixMatches` |
| `Click7` | `createSegment` | `createLineSegment` |
| `Click8` | `next` | `advance` |
| `Click10` | `entity/nameEntity/anchorEntity`, `state`, `shields`, `spawnedEntities`, `radiationTimer`, `glyphData`, `lastBlockPos`, `getEntity/getNameEntity/getAnchorEntity/getState/getShields/getSpawnedEntities/getRadiationTimer/getGlyphData/getLastBlockPos` | `boss/nameTag/marker`, `type`, `skulls`, `endermen`, `beaconTimer`, `beaconData`, `beaconBlockPos`, `getBoss/getNameTag/getMarker/getType/getSkulls/getEndermen/getBeaconTimer/getBeaconData/getBeaconBlockPos` |
| `Click11` | `SKYBLOCK_EPOCH`, `YEAR_DURATION_SECONDS`, `DAY_DURATION_SECONDS`, `getCurrentTimeSeconds`, `getYearSeconds`, `getDaySeconds`, `getPeriodSeconds` | `FIRST_YEAR_START`, `YEAR_SECONDS`, `DAY_SECONDS`, `now`, `getElapsedYearSeconds`, `getElapsedDaySeconds`, `getElapsedSeconds` |
| `Click12` | `getStyle`, `getProgressColor`, `createItemLine`, `createClickableText` | `styleOf`, `colorForRatio`, `itemLine`, `clickable` |
| `Click13` | `GLYPH_E000…GLYPH_E068` | `FontGlyphs.GLYPH_*` (same code point, same order) |
| `Click17` | `withPadding ×2`, `createItemComponent`, `createLoreLines` | `withBackground ×4`, `createItem`, `createLore` |

`Click`, `Click2`, `Click4`, `Click5`, `Click14`, `Click15`, `Click16` already
agree with their twins (`Click2`/`Click4`/`Click5`/`Click15` are in this
cluster, so both sides are mine; `Click14`/`Click16` were independently
identical).
