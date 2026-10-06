# Cluster 59 — `com.moonsworth.lunar.client.framework` (part 1)

45 cluster rows, all files present on disk. **22 renamed, 23 skipped** — every
skip is a stale-jar duplicate of a class that already carries its real name in
this package (see §2). The 22 real rows are in `classes-59.tsv` (with the 5th
`file` column, needed because `Framework7Extension2`, `Gui2Extension2`,
`MixinCore4` and `MixinCore_2` are also declared in other packages).

This is the **mod/feature framework core**: the abstract mod base and its
per-mod trait value types, the HUD element/component model, and the command
argument parsers. Real names were recovered where the decompiled code still
leaks them (`AbstractFeature`, `TypedHudRenderer`); everything else follows the
existing `Mod*` trait / `Hud*` element conventions established by classes-20,
audit07 and moves-namesB.

## 1. Renames (22)

| old | new | role |
|---|---|---|
| `Framework3` | `ModEnabledState` | per-feature "enabled" state/option (`ModTraits.field6`); impls `EnabledOption`, `FixedBooleanOption`, `DefaultedBooleanOption`, `CompoundBooleanOption` |
| `Framework7Extension2` | `AbstractFeature` | abstract base of every mod — **real name leak**: `DynamiclistenerDebugMod.method9` prints these as `"AbstractFeature<"` |
| `Framework7Extension22` | `FeatureToggleKeybind` | per-feature toggle keybind (`"<id>ToggleKeybind"`, `toggleKeybindName`, `modToggleEnabled/Disabled`, allowed/disallowed mod text) |
| `Framework8` | `ModDetails` | per-feature display details ("details" trait): name/description/categories/aliases/authors + `toString` that lists exactly those |
| `Framework9` | `ModSearchIndex` | per-feature search index ("index" trait): cached keyword lists + prefix matching; impl `framework.nameplate.FeatureIndex` |
| `Framework13` | `ModSupport` | per-feature support rule ("support" trait): supported flag + server + flip, built from version/module constraints; impl `framework.nameplate.KeystrokeSupport` |
| `Gui2Extension2` | `HudAnchor` | the 9-way HUD anchor enum (ids `topLeft` … `bottomRight`, `fromId`, anchor/pivot math); the axis enum next to it is `HudAlignment` |
| `MixinCore4` | `HudRenderContext` | render context passed to `HudComponent.render(x, y, ctx)`: renderer, chat/mouse state, scale, screen size, tooltip list + tooltip draw |
| `MixinCore5Handler22` | `TwoColumnHudComponent` | heading cell + two vertical `HudComponentGroup` columns, styleable via `HudComponentStyle`; unused in this build |
| `MixinCore5Handler3` | `TextHudComponent` | cached adventure-Component component with colour/scale/shadow; the text primitive used all over the HUD code |
| `MixinCore5Impl4` | `PaddedHudComponent` | wrapper + `HudComponentStyle` adding left/right/top/bottom padding |
| `MixinCore5Impl5` | `ScrollableHudComponent` | scissors-clips an oversized child and draws a 2px scrollbar (scroll event, hover, `Click4` animation) |
| `MixinCore8` | `TypedHudRenderer` | **real name leak** in its own exception: `"TypedHudRenderer doesn't know how to render object: "`; renders `String`/`Component`/`HudLine` values or lists with background/brackets/border options |
| `MixinCore9` | `HudElementBase` | abstract HUD element base: scale option, anchor, x/y/w/h, hit-test, `metadata`/`properties` JSON |
| `MixinCore9Base2` | `HudRowElement` | `@Deprecated` element rendering a `List<HudRow>` via `HudRowLayout`; two row lists switched by the enabled flag |
| `MixinCore9Base_2` | `TitledHudElement` | element with title/subject/value text colours (`skyblockTitleColor`/`skyblockSubjectColor`/`skyblockValueColor`) + padded/scrollable child; subclass `SkyblockGemstoneProfit$Data` |
| `MixinCoreImpl2` | `GreedyStringArgumentParser` | consumes the rest of the command string (`StringCursor.method5`, required) |
| `MixinCoreImpl3` | `IntegerArgumentParser` | optional `-`, digits, space-terminated, `Integer.parseInt` with rollback |
| `MixinCoreImpl4` | `StringArgumentParser` | one unquoted word (superclass of the player parser) |
| `MixinCoreImpl42` | `PlayerArgumentParser` | word parser + deduplicated online/visible player-name suggestions |
| `MixinCoreImpl5` | `DurationArgumentParser` | `<number>s\|m\|h\|d` → `java.time.Duration` + unit suggestions |
| `MixinCore_2` | `HudConditionSet` | 4-condition visibility set (width, height, brackets, border) built by `ConditionSetBuilder` (`MixinCore$Data`), consumed by `TypedHudRenderer` |

Naming notes / alternatives:

* `Framework8` is almost certainly the real `FeatureDetails` **interface** (its
  impls are `framework.nameplate.FeatureDetails`, `DynamicFeatureDetails` and
  `Nameplate5`→`FeatureDetailsImpl`). That simple name is currently held by the
  impl `framework/nameplate/FeatureDetails` (renamed from `FeatureDetailsImpl`
  by `moves-namesB`). The map uses `ModDetails` to satisfy "unique tree-wide";
  if the main agent prefers the real pairing, rename the impl back to
  `FeatureDetailsImpl` and use `FeatureDetails` for `Framework8`.
* `Framework3` cannot be `BooleanOption` — `config.option.BooleanOption` exists.
  The `Mod*` prefix matches `ModLoadState`/`ModDisplay`/`ModRestriction`.
* `Framework9` keeps the impl's `FeatureIndex` free by using `ModSearchIndex`.

## 2. Skipped: stale-jar duplicates (23)

These files are **byte-equivalent copies of already-renamed classes**, restored
by the "full-coverage sweep" (`47dd3dd4`) from `libs/lunar-renamed-classes.jar`,
which is a pre-rename snapshot. Structural check (identifier-normalised parse)
reports identical bodies for all 23; the only diffs are explicit
constructors/Lombok noise. `git log` confirms the counterpart was added by a
renamer commit (`37dd8b74`, `9fb54412`, …) while the placeholder file was
re-added by the rescue commits (`cb44ec03`, `837e3f59`, …).

**They must be deleted in the repair pass, not renamed** — a rename would be
skipped by the appliers anyway, because the target simple name is already
declared. The many files still referencing the placeholder generation (e.g.
`SkyblockChatCommands` uses `Framework2`, `Nameplate3` uses `Framework2`) also
need their references unified onto the named generation; that is repair work,
not naming.

| placeholder file | already-applied class | refs (placeholder / named) |
|---|---|---|
| `Framework2.java` | `ModCategories` | 21 / 180 |
| `Framework4.java` | `ChildModBinding` | 54 / 265 |
| `Framework5.java` | `OptionContainer` | 30 / 26 |
| `Framework6.java` | `PageState` | 7 / 5 |
| `Framework7.java` | `ModRestriction` | 5 / 5 |
| `Framework10.java` | `ModDisplay` | 11 / 3 |
| `Framework11.java` | `DynamicCondition` | 22 / 168 |
| `Framework12.java` | `PanelPosition` | 7 / 6 |
| `MixinCore2.java` | `HudSize` | 8 / 75 |
| `MixinCore3.java` | `HudLine` | 9 / 37 |
| `MixinCore5.java` | `HudComponent` | 20 / 19 |
| `MixinCore5Iterator.java` | `HudComponentGroup` | 6 / 17 |
| `MixinCore5Handler2.java` | `HudComponentDecorator` | 3 / 2 |
| `MixinCore5Handler2_2.java` | `TextureHudComponent` | 1 / 2 |
| `MixinCore5Handler2_3.java` | `ToggleHudComponent` | 1 / 1 |
| `MixinCore5Handler4.java` | `ItemStackHudComponent` | 2 / 4 |
| `MixinCore5Handler_2.java` | `HudComponentWrapper` | 8 / 5 |
| `MixinCore5Impl2.java` | `BackgroundHudComponent` | 3 / 5 |
| `MixinCore5Impl3.java` | `PlaceholderHudComponent` | 2 / 1 |
| `MixinCore6.java` | `HudComponentValue` | 9 / 6 |
| `MixinCore7.java` | `HudComponentStyle` | 4 / 2 |
| `MixinHelper2.java` | `CommandArguments` | 134 / 6 |
| `MixinHelper22.java` | `BoundArguments` | 76 / 5 |

The placeholder-side counts are inflated for `MixinHelper2`/`MixinHelper22`
(the same simple names are declared in many other packages and owned by other
clusters); the framework-package references are a minority.

Earlier evidence for the pairings (already in the ledger): classes-20 renamed
all of these and leaked names (`HudSize(...)` in `MixinCore2.toString`,
`"Not a caching HudComponentValue!"` in `MixinCore6`,
`"Component cant be null!"`, the trait keys in `Framework`) are the proof.

## 3. Hard cases / ambiguity

1. **Same simple name in other packages.** `Framework7Extension2` is declared
   in 9 packages, `Gui2Extension2` in 16, `MixinCore4` in `framework` + `legacy`,
   `MixinCore_2` in `framework` + `forge/lib`. The TSV carries the 5th `file`
   column and the map must be applied with `tools/apply_class_renames_aware.py`
   (the v1 applier refuses ambiguous names). Never use `--allow-collisions`.
2. **`HudAnchor` vs `profile.HudPosition`.** The real Lunar Apollo API type is
   `com.lunarclient.apollo.common.location.HudPosition`, but that name is taken
   in-tree by the profile-importer x/y record, so the anchor enum uses
   `HudAnchor`. Do not merge them — different shapes (enum anchor vs float pair).
3. **`TitledHudElement`** (`MixinCore9Base_2`) — the base is generic but every
   option key is skyblock-coloured (`skyblockTitleColor`, …); only
   `SkyblockGemstoneProfit$Data` extends it in this build. If more subclasses
   appear later it can be renamed to something broader.
4. **`TwoColumnHudComponent`** (`MixinCore5Handler22`) has no callers in the tree
   or in the reference jar beyond itself, so the name is derived from its layout
   (centered heading cell + two vertical columns with a padding style wrapper).
5. **Nested types** are untouched by class maps: `Framework8.Data`,
   `Framework13.Data`, `MixinCore8.Extension`, `HudComponentGroup.Data/Data2/Type`,
   `TwoColumnHudComponent` inherited nested types; a later nested/member pass can
   name them (`ModDetails.Builder`, `ModSupport.Data`, `HudValueRenderer`, …).

## 4. Adjacent classes NOT in cluster-59 (for the main agent)

These are entangled with the names above but are in no cluster (`MixinCore9Base`
and `MixinCore9Extension` are not even in `inventory.tsv`), so the main agent
must handle them:

| class | suggested name | reason |
|---|---|---|
| `MixinCore9Extension` | `HudElementRenderer` | the `hud_renderer` trait contract: `HudElement` geometry + `metadata`/`properties` JSON + `render(event, x, y, focused)`. Plain `HudElement` is taken by the geometry interface (audit07). |
| `MixinCore9Base` | `HudComponentElement` | element rendering a `HudComponent` (enabled/disabled variants, chat-aware `HudRenderContext`). |
| `Framework7Extension` | `Feature` | the mod contract (`getId`, enable/disable, option registration, translate, `toJson`); `Mod` is taken by `render.particle.Mod`, `ClientFeature`/`FeatureMod` would also work. |
| `MixinCore5Impl` | — already applied: `HideableHudComponent` (audit07) | |
| `MixinCore5Handler` | — already applied: `EmptyHudComponent` (audit07) | |
| `MixinCore5Task` | not lazy-named in inventory: interactive text component (tooltips via `HudRenderContext.method9`, click/scroll callbacks). Suggest `InteractiveHudComponent` if a pass ever touches it. |
