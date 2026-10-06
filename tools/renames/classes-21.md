# cluster-21 notes — `com.moonsworth.lunar.client.framework.feature.mod.fishing.click`

## What this package actually is

**Not** an auto-fish clicker. The cluster brief guessed "auto-fish click automation,
click scheduling, delay randomization, GUI settings", but the sources contain none of
that: no click scheduler, no random delay, no fish state machine. The `ClickN` names are
an artefact of the restructure pass naming rule ("otherwise `<PackageRole><index>`, using
the containing package role") — every class in a `click` package got a `Click` stem.

What the 17 top-level classes really are: **shared SkyBlock utilities** that happen to live
in this package and are imported by dozens of unrelated mods (HUDs, holograms, slayers,
fishing overlays, GUIs). This is visible from the import graph: `Click3` alone is imported
by ~100 mods (553 token hits), `Click12` by ~40 HUDs/holograms, `Click17` by the settings
GUI and debug HUDs. There is no fishing-specific logic in the top-level classes except the
circle/line fitters used by the fishing/healing overlays (and those are used by other mods
too). A repo-wide grep for `autofish|autoreel|auto.?click` finds nothing — if a real
auto-fish feature exists it is not in this cluster/tree.

## Names in `classes-21.tsv` (25 rows)

| old | new | what it is | confidence |
|---|---|---|---|
| `Click2` | `CalculatorParser` | `1k/1m/1b/t/s/e` + operator expression parser/formatter (`DecimalFormat "#,##0.#"`, parse cache, `expr = value` component) | high |
| `Click2$Data2` | `ExpressionNode` | private abstract AST base (shunting-yard build + abstract eval) | high |
| `Click2$Data3` | `NumberNode` | number-literal node wrapping a `Token` | high |
| `Click2$Data4` | `Token` | lexer token (`String` + `NUMBER/GROUP/OPERATION` + `Double`) | high |
| `Click3` | `IslandUtils` | current island type / on-island predicate / powder + mining island flags / sneaking eye height / send-coords | high |
| `Click4` | `AnimatedValue` | eased double (LINEAR/SIN_IN/SIN_OUT/SIN_IN_OUT/EASE_IN) over `getSystemTime()` | high |
| `Click5` | `CircleFitter` | RANSAC circle fit (`Circle.from3Points`, radius/inlier thresholds) | high |
| `Click5$Data2` | `CircleCandidate` | private candidate (score, `Circle`, inlier indices) | high |
| `Click6` | `MiddleClickGuiRegistry` | display-name pattern → slot-id set registry for SkyBlock middle-click GUIs; logger tag `"SkyBlockMiddleClickGuis"`, JSON parsed in `SkyblockMiddleClickGui` | high |
| `Click7` | `LineFitter` | RANSAC 3D line fit returning a `util.Fishing` segment; used by `SkyblockMageBeam` | high |
| `Click8` | `RollingWindow` | rolling N+1 collections, rotate-and-return-oldest; `SkyblockKeyGuardianTimer` per-tick sets | high |
| `Click9` | `ParticleSample` | particle sample Vec2D x/z + y + timestamp, input to the fitters | high |
| `Click10` | `VoidgloomBoss` | per-owner Voidgloom Seraph state (owner, beacon/entity refs, SHIELD/RADIATION/NONE from nametag `extraData`, nukekubi + yang-glyph sets, glyph timer) | high |
| `Click11` | `SkyblockCalendar` | SkyBlock year/month/season clock (year 446400 s, month 1200 s, seasons from `HighlightType4`) | high |
| `Click12` | `TextComponentFactory` | component helpers (ratio color, `"- label xN"`, hover line, nested fluent `name: value` builder) | high |
| `Click13` | `FontGlyphs` | private-use icon codepoint constants U+E000..U+E087 (chars inlined in hologram strings: `\ue010`, `\ue003`, ...) | high |
| `Click14` | `EntityFinder` | nearest matching entity (NPC/player predicate, +2 Y offset distance) | high |
| `Click15` | `AverageInterval` | mean gap of last ≤8 floats (sample timing) | high |
| `Click16` | `FaceMerger` | removes coincident/internal edges+faces of axis-aligned boxes via quantized keys + bitmask; no users in tree | high |
| `Click16$Data2` | `BlockBox` | input AABB (min/max + block id) | high |
| `Click16$Data3` | `EdgeKey` | canonical quantized edge key (6 longs + id) | high |
| `Click16$Data4` | `BlockFace` | surviving face (axis, plane, bounds, id, orientation) with quad-outline render | high |
| `Click16$Data5` | `FaceKey` | quantized face key (axis + plane + bounds + id) | high |
| `Click16$Data6` | `MaskedMap` | generic map OR-accumulating a bitmask per key, filtered by mask predicate | high |
| `Click17` | `WidgetFactory` | GUI widget factory (padded/clickable `MixinCore5` wrappers, item widget, tooltip lines) | high |

All 25 targets verified unique (no existing declaration tree-wide).

## Applying (important)

* Name collisions on the **old** side are heavy: `Click2/3/4/5/6/8/9/10/11/12` also exist in
  `...rewind.rewindhandlers.click` and/or `...client.util.click`; `Data2` is declared in 45
  packages and `Data6` in 9. Only `Click7/13/14/15/16/17` are globally unique.
* Use `tools/apply_class_renames_aware.py` (import-aware). It handles the 9 nested rows
  because their evidence starts with `Owner$Old` (e.g. `nested Click16$Data2: ...`):
  it rewrites `Owner.Old` qualifiers tree-wide and the bare `Old` token only inside
  `Owner.java`. Dry-run: **25 rows, 0 skipped, 241 files touched, 16 files renamed**.
* **Do not** use `--allow-collisions` with `tools/apply_class_renames.py` (v1): replacing bare
  `Data2` tree-wide would corrupt 45 packages. v1 without the flag only applies the 6 unique
  names (`Click7/13/14/15/16/17`).
* No `src/main/resources` file references any of these names, so the resource pass is a no-op.

## Left over / not in the map

* `Click` (top-level render helper: boxes, beams, lines, billboard labels) and
  `JsonDeserializerImpl` (Gson adapter for `Horsestats20`) keep digit-free names and are not
  part of cluster-21 — a separate cluster should own them (`Click` is a general
  `WorldRenderUtils`-style class used by Calculator/Burrow/holograms/rewindhandlers).
* Digit-free nested placeholders still worth renaming later (not in cluster-21):
  `Click2$Data` → `OperationNode` (has left/right + `getPriority()`),
  `Click5$Data` → `FittedCircle`, `Click10$Data` → glyph/entity hit record,
  `Click12$Data` → `TextComponentBuilder`, `Click16$Data` → `Edge`,
  `Click16$Data6$Data` → masked value holder.
* No auto-fish/auto-reel/click-automation class exists anywhere in the tree
  (`grep -ri 'autofish|autoreel|autoclick'` → 0 hits), so the cluster brief's suggested
  names (`AutoFishClicker`, `ClickScheduler`, ...) were deliberately **not** used.
