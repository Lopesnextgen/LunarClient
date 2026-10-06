# Cluster 09 — `com.moonsworth.lunar.client.util#part2` (40 classes)

Source revision: `tools/renames/cluster-09.txt` (40 rows).
Map: `tools/renames/classes-clientutil2.tsv` (40 rows).

## What this package actually is

`com.moonsworth.lunar.client.util` is Lunar's general-purpose utility package.
The `ThreadModuleDump*` names are decompiler placeholders: the original jar had
these classes under the same package with meaningless obfuscated names
(`CHOHRRHCHHCHCOOCICOCORRHRIICIH`, `OIOHHHHCICORIHOHOHIRHRROOORRCO`, …), as shown
by `tools/mappings-snapshot/restructure/remaining-renames.tsv`
(lines 4398–4623). Unlike the flattened feature packages, **nothing here was
moved between subtrees** — every file already lived in `client/util`, so the
provenance tables only confirm the package, not a feature. There is therefore no
"real Lunar name" to recover from `lunar-client-names.tsv` (that table only
covers the readable `com.lunarclient.*` Apollo jar); the names below are derived
from the class bodies and their call sites.

The 40 rows fall into a few families:

* **math / geometry** — `MathUtils`, `IntRectangle`, `DoubleRectangle`,
  `RectangleQuadtree`, `MatrixConversion`, `CubeGeometry`, `Corner`.
* **text / parsing** — `RomanNumeralUtils`, `RomanNumeralParser`,
  `FormattingCodes`, `JavadocTag`, `JavadocElementType`.
* **IO / platform** — `ClipboardUtils`, `DownloadedImageCache`,
  `HeadTextureCache`, `ResourcePackUtils`, `PortUtils`, `OperatingSystem`.
* **client plumbing** — `GuiResolution`, `LaunchOptions`, `MousePosition`,
  `EntityLookup`, `ItemTypeLookup`, `EntityTypeLookup`, `DirtyFlag`,
  `ShaderPreprocessor`, `ConditionState`, `Flag`, `TimeFormat`, `FoodValues`,
  `Vector2dPair`, `WeightedValue`, `FilteringIterator`, `MapRemoval`,
  `UncheckedFunctional`, `Initializable`, `NameTagUpdateListener`,
  `AsyncResource`, `AsyncResourceManager`, `ProtoConverter`, `BlockSearch`.

## Per-class reasoning

| # | old | new | evidence |
|---|-----|-----|----------|
| 1 | `ThreadModuleDump66` | `ProtoConverter` | static `Uuid`/`Timestamp`/`Instant`/`Duration`/`Vector3f`/`Vector2i`/`Vector3i`/`Struct`<->`JsonObject`/`Direction` conversions |
| 2 | `ThreadModuleDump67` | `MathUtils` | `clamp` (byte/short/int/long/float/double), `ceil`/`floor`, `lerp`, `wrapDegrees`, `distSq`, `absLess` |
| 3 | `ThreadModuleDump68` | `ClipboardUtils` | AWT clipboard read/write string + image, macOS `osascript` fallback, `Slayer.warn("Couldn't copy image to the clipboard")` |
| 4 | `ThreadModuleDump69` | `HeadTextureCache` | `download/heads/<uuid>.png` and `download/wrapped/<uuid>.png`, `steve.png` fallback, texture load/delete |
| 5 | `ThreadModuleDump7` | `RectangleQuadtree` | holds `IntRectangle` nodes, splits into 4 children when `>5` nodes, culls fully-contained rects; nested `Data` is the culling index |
| 6 | `ThreadModuleDump70` | `IntRectangle` | int `left/top/right/bottom`, `"Invalid rectangle dimensions: left=…"`, `intersects`/`contains`/`union`/`intersection`/`scale` |
| 7 | `ThreadModuleDump71` | `GuiResolution` | `getWidth/Height`, `getScaledWidth/Height`, `getScale`, `bridge$unicode`, `bridge$getGuiScale` — MCP `ScaledResolution` clone |
| 8 | `ThreadModuleDump72` | `DownloadedImageCache` | `downloaded-images` dir, base64 decode, md5 filename, `MediaType.PNG`, texture load |
| 9 | `ThreadModuleDump73` | `EntityLookup` | `method1` scans blocks in radius for first entity matching a predicate; `method2` resolves entity type name by id |
| 10 | `ThreadModuleDump75` | `RomanNumeralUtils` | int->roman (`M`,`CM`,`D`…), roman->int, validation regex |
| 11 | `ThreadModuleDump76` | `ResourcePackUtils` | `pack.mcmeta` / `.zip` filters, `assets/lunar/` path check, `back-folder-64x64.png` / `folder-64x64.png` |
| 12 | `ThreadModuleDump77` | `UncheckedFunctional` | wraps `ThrowingRunnable`/`ThrowingSupplier`/`ThrowingConsumer`/`Extension` into `Runnable`/`Supplier`/`Consumer`/`Function` |
| 13 | `ThreadModuleDump78` | `MapRemoval` | `Map` entry removal by key/value predicate (and negated) |
| 14 | `ThreadModuleDump79` | `PortUtils` | `new ServerSocket(0)` free-port lookup (fallback 25564) and `localhost:<port>` probe |
| 15 | `ThreadModuleDump8` | `MatrixConversion` | `javax.vecmath.Matrix4f/Matrix3f` <-> `org.joml.Matrix4f/Matrix3f` |
| 16 | `ThreadModuleDump80` | `LaunchOptions` | static holder set from `MainMixin` launch args: launcher version, installation id, overwolf muid, sentry trace, modrinth/curseforge ids, launch id, canary token, ipc port, ui/jit/webosr/textures dirs, language |
| 17 | `ThreadModuleDump81` | `Vector2dPair` | generic `(Vector2d, T)` pair; used in `Waypoints` as `(screenPos, GuiHandler2)` |
| 18 | `ThreadModuleDump82` | `BlockSearch` | spiral search, BFS over `HorsestatsType_2` neighbours, bounded BFS, random positions in a box |
| 19 | `ThreadModuleDump83` | `RomanNumeralParser` | regex-replaces roman numerals inside text; `Type` enum I/V/X/L/C/D/M; fishing tier parsing |
| 20 | `ThreadModuleDump84` | `WeightedValue` | `Comparable` `(T value, float cost)`; Dijkstra priority queue in `SkyblockGlaciteCommissions` |
| 21 | `ThreadModuleDump85` | `MousePosition` | returns a `markers.Data6` point from `Bridge.method20().getX()/getY()` adjusted by logical height |
| 22 | `ThreadModuleDump86` | `FormattingCodes` | `getTextWithoutFormattingCodes` (§ code stripper), used by `EnumChatFormattingOptimizationMixin` |
| 23 | `ThreadModuleDump87` | `Initializable` | `void init()` + `static <S> S create(Supplier<S>)` + default self-init |
| 24 | `ThreadModuleDump88` | `NameTagUpdateListener` | single method `lunar$onNameTagUpdate()`, implemented by player entities |
| 25 | `ThreadModuleDump89` | `ItemTypeLookup` | `Bridge2_21` registry lookup by name/id returning `Bridge6_4` (item) -> `BridgeExtension_4`; used for cooldown items |
| 26 | `ThreadModuleDump90` | `FoodValues` | `(int hunger, float saturation)` built by `FoodUtils` |
| 27 | `ThreadModuleDump92` | `CubeGeometry` | cube vertex/UV arrays, index buffer, `lerp`, UV atlas coords, 24 cube corners |
| 28 | `ThreadModuleDump93` | `DirtyFlag` | static `dirty`; `mark()` then on `HighlightImpl2` event calls `ThreadModuleDump63.method28` (keybind/lighting refresh) |
| 29 | `ThreadModuleDump94` | `ShaderPreprocessor` | `LUNAR-SHADER-DEFINITION-MARKER`, `#version 120/330/450`, uniforms/`LUNAR_IN`/`LUNAR_OUT`/`fragColor`; nested `Data` = shader definition |
| 30 | `ThreadModuleDump95` | `DoubleRectangle` | double `x/y/width/height`, `left/top/right/bottom`, `intersects`/`contains` |
| 31 | `ThreadModuleDumpIterator2` | `FilteringIterator` | `Iterator` that skips elements failing a `Predicate` |
| 32 | `ThreadModuleDumpType2` | `OperatingSystem` | `MACOS/LINUX/WINDOWS/SOLARIS/UNKNOWN`, wayland + apple-silicon detection |
| 33 | `ThreadModuleDumpType3` | `Flag` | singleton enum `INSTANCE`, value type for `is_global`/`hide_from_api` nameplate attributes |
| 34 | `ThreadModuleDumpType4` | `Corner` | `TOP_LEFT/TOP_RIGHT/BOTTOM_LEFT/BOTTOM_RIGHT`, `getOpposite`, `rotate90CW/CCW`, `offsetX/offsetY` |
| 35 | `ThreadModuleDumpType5` | `ConditionState` | `ANY/TRUE/FALSE` + `Condition`/`ConstantCondition`/`SupplierCondition`/`FunctionCondition` |
| 36 | `Type2` (nested) | `JavadocTag` | `JavadocSpec$Type2`: DEPRECATED/SEE/PARAM/RETURN/… with valid element types |
| 37 | `Type2` (nested) | `TimeFormat` | `TimeFormatting$Type2`: `Gui2Extension` enum delegating to `TimeFormatting.Type` |
| 38 | `Type3` (nested) | `JavadocElementType` | `JavadocSpec$Type3`: CLASS/INTERFACE/ENUM/FIELD/METHOD |
| 39 | `Util2` | `AsyncResource` | async texture resource interface: `ExecutorService` (`lunar-async-resource`), `get`, `Optional`, `load`, `unload` |
| 40 | `Util_2` | `AsyncResourceManager` | `[LC Async Resources]` manager: resident-size tracking, low/full quality, downgrade on memory pressure |

## Ground truth used

* `tools/mappings-snapshot/restructure/remaining-renames.tsv` (lines 4398–4623)
  and `tools/work/mappings/restructure-merged.tsv` (lines 4936–5002) — obf name
  -> placeholder, confirming every file was already in `client/util` (no
  flattening) and giving the original obfuscated identifiers.
* `tools/mappings-snapshot/lunar-client-names.tsv` — no entries for these
  legacy `com.moonsworth.lunar` classes (only `com.lunarclient.*`), so no real
  name recovery was possible.
* Call sites: `MainMixin` (launch args -> `LaunchOptions`), `Waypoints`
  (`Vector2dPair`), `SkyblockGlaciteCommissions` (`WeightedValue`),
  `SkyblockTpMaze` (`Corner`), `FoodUtils` (`FoodValues`),
  `EnumChatFormattingOptimizationMixin` (`FormattingCodes`), fishing
  `GuiHandler*` (`RomanNumeralParser`), `Cooldowns` (`ItemTypeLookup`),
  `Horsestats16Task` (`CubeGeometry`), `Nameplate`/`LightingHandler`
  (`Flag`, `DirtyFlag`), `MixinCore$Data` (`ConditionState`).
* Reference clients (Badlion 2.0.0-v-beta, 509-client sources) were searched for
  `hide_from_api`/`is_global` and similar; no matching named utility existed.

## Name uniqueness

All 40 new simple names were checked against the tree-wide declaration set
(`grep -rE "\b(class|interface|enum|record)\s+NAME\b" src/main/java`): no
collisions. `ScaledResolution` and `FoodStats` already exist (vanilla
`net.minecraft.client.gui.ScaledResolution`, `net.minecraft.util.FoodStats`), so
the Lunar clones were named `GuiResolution` and `FoodValues` instead.

## Uncertain names

* `ThreadModuleDumpType3` -> `Flag` (#33). It is a bare singleton enum
  (`INSTANCE`) used only as a boolean-ish value for `is_global` /
  `hide_from_api` nameplate attributes. `Flag` / `Unit` / `Marker` are all
  defensible; `Flag` was chosen for readability. Low risk.
* `ThreadModuleDump89` -> `ItemTypeLookup` (#25). `Bridge6_4` is clearly an
  item (`bridge$isItemPotion`, `bridge$isItemSkull`, …) and call sites pass
  item names (`golden_sword`, `ender_pearl`), but the exact Lunar name is
  unknown; `ItemLookup` is an equivalent alternative.
* `ThreadModuleDump72` -> `DownloadedImageCache` (#8) vs `DownloadedImages`.
  It both decodes base64 icons and downloads/caches images; the cache aspect
  dominates.
* `ThreadModuleDump82` -> `BlockSearch` (#18). It has no call sites in the tree
  (possibly reflection/dead code); the name reflects the three search methods.
* `ThreadModuleDump75` -> `RomanNumeralUtils` (#10) is unused in-tree and
  overlaps conceptually with `RomanNumeralParser` (#19, used by fishing). Both
  kept distinct because they are separate classes.