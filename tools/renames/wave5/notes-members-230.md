# Cluster 230 — `com.moonsworth.lunar.client.util` `ThreadModuleDump*` members (25 owners)

Source: `tools/renames/wave5/member-cluster-230.tsv` (25 owners, 1,154 lazy rows).
Map: `tools/renames/wave5/members-230.tsv` (**315 rows** = 258 `M` + 57 `F`, 30 owners:
23 top-level + 7 nested `$` owners).
No sources were edited by this agent.

Status note: the map was already picked up and applied by the main agent
(commit `684abfadb` applied the 314-row first revision; the current 315-row
revision, which only adds the inert nested row `ThreadModuleDump82$1.field1`,
was committed in `f97820b04`). A post-apply audit of the resulting tree is at
the end of this file — it lists the sites the applier missed (bare static field
reads, nested-class name reuse, 8 KB import window) so the repair/alignment pass
can finish the job. The audit was re-checked after `f97820b04` and is unchanged.

## 1. What these classes are

`ThreadModuleDump*` are decompiler placeholders for `client.util` classes.
Most have a **real-named duplicate in the same package** (the same class rescued
twice), so the class-level campaign already named the twins while these copies
kept their placeholder names. For those, the member names below were derived
from the twin's semantics, the class-rename notes
(`tools/renames/classes-clientutil2.md`, `classes-clientutil3.md`,
`classes-finalclientB.md`), call sites, and — for overrides — the external base.

| placeholder | twin / likely real name | rows | evidence highlights |
|---|---|---|---|
| `ThreadModuleDump68` | `ClipboardUtils` | 6 | identical body incl. `"Couldn't copy image to the clipboard"` + macOS `osascript`; MCP `GuiScreen.getClipboardString/setClipboardString`; callers `Chat:453`, `SkyblockDebugGraphs:277`, `Screenshot2:88,101` |
| `ThreadModuleDump69` | `HeadTextureCache` | 6 | `download/heads/<uuid>.png` / `download/wrapped/<uuid>.png` + `/face/` `/body/front/` endpoints, `steve.png` fallback; callers `Gui2Iterator22:39`, `click/gui/Bridge7Iterator:69` |
| `ThreadModuleDump7` | `RectangleQuadtree` | 11 | quadtree (`>5` nodes → 4 children), `WeightedQuadtree` sibling keeps readable `nodes`/`max()`/`value()` |
| `ThreadModuleDump70` | `IntRectangle` | 17 | `"Invalid rectangle dimensions: left=… top=… right=… bottom=…"` names the fields; class doc lists `intersects/contains/union/intersection/scale`; callers `BoxRenderer:16` (of x,y,w,h), `ScissorStack:21` (intersection), `RectRenderer:37` (union), `MixinHelper2_2:90` (scale) |
| `ThreadModuleDump71` | `GuiResolution` | 10 | 1:1 clone of in-tree `net/minecraft/client/gui/ScaledResolution` incl. `getScaledWidth_double`/`getScaledHeight_double`/`getScaleFactor`; callers `StencilEmulator:69`, `HudDebug:28` ("MC_Scale"), `IconPotionEffectRenderer:215` |
| `ThreadModuleDump72` | `DownloadedImageCache` | 8 | `downloaded-images` dir, base64 decode, md5 filename, `MediaType.PNG`; caller `Fishing3:225` |
| `ThreadModuleDump73` | `EntityLookup` | 2 | chunk-spiral `bridge$getFirstEntity(aabb, predicate)`; entity-type-name resolver; callers `Nameplate3Iterator3:43`, `RewindHandlers3Updater:489` |
| `ThreadModuleDump73Type` | `InterpolationMode` (nested `Data`) | 2 | gson `TypeAdapter` registered at `Rewind2_3:58`; `write`/`read` are external overrides |
| `ThreadModuleDump74` | (class still unnamed; HTTP helper) | 13 | used by `Fishing:71-76,146-148` (`jsonBody`/`multipartBody`/`randomBoundary`); `Data` is record-style (`filename()` used but missing → file already in ECJ baseline) |
| `ThreadModuleDump75` | `RomanNumeralUtils` | 8 | int→roman tables/pattern, memo caches; twin identical; **unreferenced in tree** |
| `ThreadModuleDump76` | `ResourcePackUtils` | 12 | `pack.mcmeta`/`.zip` filters, `assets/lunar/` prefix check, folder icons; caller `GuiScreenResourcePacksMixin:182` |
| `ThreadModuleDump77` | `UncheckedFunctional` | 5 | throws-wrappers into `Runnable/Supplier/Consumer/Function`; callers `Nameplate.java:77`, `NameplateBase:14`; nested `Extension2` SAM → `accept` |
| `ThreadModuleDump78` | `MapRemoval` | 4 | remove/retain maps by key/value predicate; callers `Chat3:28`, `PinnedServerManager:52` |
| `ThreadModuleDump79` | `PortUtils` | 2 | `new ServerSocket(0)` free port + `Socket` probe; reference clients use `ProxyConfig.findFreePort` (CZF B13, Reality b2, ETB 0.6); caller `Bridge7Iterator22:79,179` |
| `ThreadModuleDump8` | `MatrixConversion` | 3 | `javax.vecmath` ↔ `org.joml` transpose copy |
| `ThreadModuleDump80` | `LaunchOptions` | 17 | every field named by `MainMixin.lunar$main$parse` option (`lunar$installationId`, …) + headers/log tags in `CrashReporter`, `MaliciousListFetcher`, `WebSocketClientIterator:75`; `field14` **skipped** (never assigned in-tree) |
| `ThreadModuleDump81` | `Vector2dPair` | 4 | `(Vector2d screenPos, Waypoint)` built at `Waypoints:293`, read at `Waypoints:333-334` |
| `ThreadModuleDump82` | `BlockSearch` | 5 | spiral search / facing-BFS / bounded BFS / random positions (`HologramsIterator2:314`); anonymous `AbstractIterator` must name `computeNext` |
| `ThreadModuleDump83` | `RomanNumeralParser` | 6 | regex-rewrites roman tokens in text, nested `Type` enum; callers `GuiHandler10:24`, `Holograms4Updater:210`, `Fishing5:46-47` |
| `ThreadModuleDump84` | `WeightedValue` | 4 | Dijkstra PQ entry in `SkyblockGlaciteCommissions:321-341`; `Comparable` → `compareTo` |
| `ThreadModuleDump85` | `MousePosition` | 1 | `Bridge.method20()` mouse X/Y flipped by logical height; callers `Chat5:124`, `Markers:49` |
| `ThreadModuleDump86` | `FormattingCodes` | **0** | only member is the already-real `getTextWithoutFormattingCodes`; remaining lazy rows are locals |
| `ThreadModuleDump87` | `Initializable` | 3 | `FogLoader2 implements` it and overrides `init()` (`super.init()`), so `method2` = `init` |
| `ThreadModuleDump89` | `ItemTypeLookup` | 2 | item registry lookup by name/id; caller `Rewindhandlers3:36` |
| `ThreadModuleDump9` | (JSON accessor utility, no reference twin) | 163 | 163 static gson `JsonObject`/`JsonArray` accessors; used by waypoint/TierTagger/config-migration code (`Highlight4`, `HudOptionMigration:94`, `DungeonWaypointCodec:145`, …) |

## 2. `ThreadModuleDump9` naming scheme (163 methods)

No readable twin exists (checked `lunar.jar`'s `com/lunarclient/**`, the
reference repos in `/tmp/opencode/reference`, and the 509 client sources for the
same API/strings — nothing matches). The names are therefore semantic, and the
overlapping shapes follow MCP `net/minecraft/util/JsonUtils`:

| methods | family | example call sites |
|---|---|---|
| 1–12 | `getBoolean/…/getJsonArray(obj, key, default)` (lenient `getAsX`) | `Highlight4:25-51`, `DungeonwaypointsIterator:95-97`, `WaypointStyle:84` |
| 13–24 | same names, `String... path` overloads; helper 24 `getByPath` | internal |
| 25–35 / 36–47 | `getXStrict` (requires `JsonPrimitive` + `isNumber/isString/isBoolean`), helper 47 `getByPathStrict` | internal |
| 48–58 / 59–71 | `findBoolean/findInt/findString/findJsonObject/…` returning `Optional*`; helpers 70/71 `findByPath`/`findByPathOr` | `TierApiProvider:53`, `HeldItemScaleMigration:29,41`, `Tiertagger2Iterator:166,218` |
| 72–94 | `getRequiredX` (throws `org.json.JSONException`), helper 94 `getRequiredByPath` | self-referenced as `ThreadModuleDump9::method72..82` |
| 95–99 | `mapArray` / `mapRequiredArray` / `mapList` / `mapRequiredList` | `DungeonWaypointCodec:145,159` |
| 100–105 | `findElement`, `findMapped`, `getRequiredElement`, `getRequiredMapped`, `getNullableElement`, `getNullableMapped` | — |
| 106–109 | `hasField` (MCP name), `removeIfNull`, `removeElement`, `removeMapped` | — |
| 110–120 / 121–131 / 132–142 | `takeX` (remove + `Optional`), `takeXOrNull` (remove + `@Nullable`), `takeXOrDefault` | `HudOptionMigration:94` (130) |
| 143 | `renameKey` | — |
| 144–153 / 154–163 | `getArrayX` / `getRequiredArrayX` (with default / required) | `WaypointStyle:111` (149) |

Note the deliberate `Strict` suffix: without it the lenient and strict families
have identical erased signatures (e.g. `getBoolean(JsonObject,String,boolean)`)
and would collide after renaming.

## 3. Overrides / hierarchy (base names kept)

* `ThreadModuleDump84.method1` → `compareTo` — implements `Comparable<WeightedValue<T>>`.
* `ThreadModuleDump73Type$Data.method1/2` → `write`/`read` — extends gson `TypeAdapter`.
* `ThreadModuleDump82$1.method1` → `computeNext` — extends Guava `AbstractIterator`; the missing name is why `ThreadModuleDump82.java` is in the ECJ baseline.
* `ThreadModuleDump87.method2` → `init` — `FogLoader2` already declares `@Override public void init()` / `super.init()`.
* `ThreadModuleDump77$Extension2.method1` → `accept` — SAM in the style of `Consumer` (the other nested interfaces already carry `run`/`get`/`apply`).
* `ThreadModuleDump68$Data` and `ThreadModuleDump86` already use the correct Java/MCP base names (`getTransferData`, `getTextWithoutFormattingCodes`).
* `ThreadModuleDump71` is a verbatim clone of `net/minecraft/client/gui/ScaledResolution`, so its members reuse the MCP names (`getScaledWidth_double`, `getScaleFactor`, `scaledWidthD`, `scaleFactor`, …).

## 4. Skipped / out of scope

* **594 `P` rows** (params, incl. 440 in `ThreadModuleDump9`) — the member map has no `P` kind; they belong to the param-cleanup pass.
* **196 local-variable rows misclassified as `F`** (`varN`, decompiler names; e.g. every `F` row of `ThreadModuleDump9`) and **48 call-site rows misclassified as `M`** (signature `return`) — no declarations, left alone.
* `ThreadModuleDump86` — 0 rows (only member already named).
* `ThreadModuleDump80.field14` — never assigned anywhere in source (gates the `"tournament"` nameplate in `Bridge7Iterator2:41`); left lazy rather than invented.
* 7 nested owners (`$Data`, `$Extension2`, `$Type`, `$1`) are emitted with `Outer$Nested` owner names; `apply_member_renames.owner_file()` cannot resolve them, so those rows are inert until a nested-aware pass exists (same limitation as the class-campaign nested rows).

## 5. Applier behaviour / post-apply audit (commit `684abfadb`)

Dry run (pre-apply): `314 rows over 30 owners; after multi-decl filter 294;
declarations to rename 380; accesses 22101 (dry-run)`.
(The access total is dominated by `ThreadModuleDump9.java` itself, 21230 — the
tool's `_BARE` loop counts every match of the combined old-name alternation per
old name, including identity replacements. The per-file distribution is the
useful signal: `ThreadModuleDump70` 144, `ThreadModuleDump7` 62, `Highlight4` 16,
`HighlightSerializer` 16, `Inventorymod2` 20, …)

### 5a. Rows the multi-decl filter drops by default

* Nested-class name reuse (outer + nested declare the same name): `ThreadModuleDump7` `field1,field2,method1,method2`; `ThreadModuleDump74` `field1,method1,method2`; `ThreadModuleDump72` `field1`; `ThreadModuleDump76` `field1..field4`; `ThreadModuleDump77` `method1`; `ThreadModuleDump82` `method1`.
* `ThreadModuleDump76`/`ThreadModuleDump72` fields also match their own getters' `return fieldN;` lines.
* `ThreadModuleDump9` helpers `24, 47, 70, 71, 94, 144` — internal `return methodN(...)` lines are counted as declarations. For `ThreadModuleDump9` `--allow-multi-decl` is safe (every name has exactly one declaration; the extra matches are bare self-calls).

### 5b. Verified stale references left in the tree after the apply

| file | what is inconsistent | needed repair |
|---|---|---|
| `ThreadModuleDump69.java` lines 17,22,25,31,41,44,49,59 | declarations renamed to `headTextures`/`wrappedTextures`, bodies still use bare `field1`/`field2` (static fields are never rewritten by the tool) | rename the bare uses (file was **not** in the ECJ baseline before) |
| `ThreadModuleDump83.java` lines 13,32 | bodies use `field2`/`field1`, declarations are `ROMAN_TOKEN_PATTERN`/`ROMAN_PATTERN` | rename bare uses (file was not in the baseline) |
| `ThreadModuleDump72.java` line 84 | `field2` dangling (→ `DEFAULT_TEXTURE`); `field1` row was skipped so `CACHE_DIR` was not applied | rename use + optionally apply the field1 row |
| `ThreadModuleDump75.java` lines 20,23–26,36,65 | bodies use `field1..field5`, declarations are UPPER_SNAKE | rename bare uses |
| `ThreadModuleDump84.java` line 15 | `var1.field2` → `var1.weight` | one-line fix |
| `ThreadModuleDump7.java` | outer `bounds`/`covered` used but `field1`/`field2` declarations were skipped; nested `Data` sites mixed (`this.bounds` should be `this.tree`, `this.covered` → `this.outside`, `this.bounds.field1` → `this.tree.bounds`, `this.bounds.method1/2` → `this.tree.intersects/add`) | nested-aware repair or merge with `RectangleQuadtree` |
| `ThreadModuleDump74.java` | outer `field1` skipped; nested `Data.field1/field2` reads became `this.TIMEOUT_SECONDS`/unchanged, `Data.method1/method2` declarations became `sendAsync` (should be `contentType`/`data`); missing `filename()` accessor is a pre-existing baseline error | nested-aware repair |
| `Client.java` (22 refs), `EntityRenderer4.java:258` | still use `ThreadModuleDump80.field1..field18` (`field13` etc. now `quickJoinServer`); the applier's `qualify()` only scans the first 8 KB of a file, and both imports sit at offsets 8244 / 11298 | widen the head window or patch those two files; both files are already in the ECJ baseline |

All other owners' call sites were rewritten correctly (0 remaining
`ThreadModuleDumpN.method*`/`::method*` references for the applied rows).

## 6. Uncertain / low-confidence names

* `ThreadModuleDump9` — no ground truth; scheme documented in §2. Overload names repeat across families by design (e.g. `getInt` lenient vs `…Strict` vs `findInt` vs `getRequiredInt`).
* `ThreadModuleDump71.method3` — `getScaleFactor` (vanilla `ScaledResolution` name); the earlier class note wrote `getScale`.
* `ThreadModuleDump70.method3` — `of`; `create`/`fromXYWH` equally defensible.
* `ThreadModuleDump68.method3`/`method4` — `canCopyImages`/`copyImageToClipboard` (only used by the screenshot clipboard feature); `getClipboardString`/`setClipboardString` follow MCP.
* `ThreadModuleDump73.method1` — `findFirstEntityInChunks` (chunk spiral; `findEntityInRadius` is an alternative).
* `ThreadModuleDump74` — the class itself is still unnamed (HTTP util); `Data.method1/2` inferred as record-style `contentType()`/`data()` from the missing-but-called `filename()`.
* `ThreadModuleDump76.method1` — `shouldIncludeAsset`, unreferenced in-tree.
* `ThreadModuleDump80.field13` — `quickJoinServer` (read as a connect target + `"Unable to quick join"` log, cleared afterwards; never assigned in-tree).
* `ThreadModuleDump82.method3` — `breadthFirstCount` (bounded BFS returning an accepted count).
* `ThreadModuleDump87.method1` — `initAndGet` (default self-init helper); `init` is taken by the verified `FogLoader2` override.
* `ThreadModuleDump7`, `8`, `75`, `81`, `84` are unreferenced in the tree, so their names rest on semantics only.
* `ThreadModuleDump75` and `ThreadModuleDump83` are two independent roman-numeral implementations (`RomanNumeralUtils` vs `RomanNumeralParser`); both are named distinctly to avoid a false merge.

## 7. Twin/merge hints

139 of the 315 rows (21 owners) belong to classes whose real-named twin already
exists in the same package (`ThreadModuleDump74` and `ThreadModuleDump9` have
none). If the merge pass deletes these placeholder copies, the intended
final names are the twins' (`ClipboardUtils`, `HeadTextureCache`,
`RectangleQuadtree`, `IntRectangle`, `GuiResolution`, `DownloadedImageCache`,
`EntityLookup`, `InterpolationMode`, `UncheckedFunctional`, `MapRemoval`,
`PortUtils`, `MatrixConversion`, `LaunchOptions`, `Vector2dPair`, `BlockSearch`,
`RomanNumeralParser`, `WeightedValue`, `MousePosition`, `Initializable`,
`ItemTypeLookup`, `ResourcePackUtils`); the member names above match the twins'
semantics so a merge keeps one consistent member map. `ThreadModuleDump74` and
`ThreadModuleDump9` have no twin.
