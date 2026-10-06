# Cluster 52 — `com.moonsworth.lunar.client.lighting` part 2 (23 classes)

Map: `tools/renames/classes-52.tsv` — **13 rows**, all top-level classes whose file
stem equals the declared type (no nested/`$` rows).

Result: **13 renamed, 10 skipped** (the 10 are rescued duplicate copies of types
already renamed by the applied `classes-lighting-modhud` batch — see §3, no rows
emitted for them on purpose).

## 1. What this half of the package is

`com.moonsworth.lunar.client.lighting` is Lunar's **internal options/settings
framework** (the real lighting engine lives in `client.fog` / `client.lightoverlay`),
as established by `classes-lighting-modhud` (cluster 21) and the wave-6 move pass
that relocated the already-named classes to `client.config.option`. Cluster 52
contains the **option-type definitions** (the classes every mod instantiates for
its settings), plus three structural plumbing classes. Cluster 51 holds the rest of
the framework (`LightingExtension`, `LightingExtension4`/`42..48`, the `49x`
siblings, `Lighting2/3`, `LightingBase*`, `LightingHandler/Iterator/Type`).

Naming conventions used here, aligned with the *already applied* framework names
(`SettingsBuilder`, `SettingsNode`, `OptionBaker`, `OptionProvider`,
`AbstractOptionBuilder`, `DefaultedOptionBuilder`, `ConditionalOption`,
`OptionCombiner`, `OptionCategory`, `OptionFlag`, `KeyBind`):

* concrete option/value definitions → `<Thing>Option`
  (`MapOption`, `DropdownOption`, `EnumOption`, `ListOption`, …);
* fluent builders → `<Thing>OptionBuilder`;
* settings-tree plumbing → `Settings*` (`SettingsParent`,
  `CategorizedSettingsBuilder`).

The public Apollo option API shipped in `libs` uses the same vocabulary
(`com.lunarclient.apollo.option.Option`, `SimpleOption`, `NumberOption`,
`EnumOption`, `ListOption`, `OptionBuilder`); where the concept is identical the
name was matched (`EnumOption`, `ListOption`). None of those names is declared in
`src/`, so there is no collision.

## 2. Renames (13)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `LightingExtension492` | `MapOption` | `Map<K,V,M>` value; `contains`/`put`/`remove` delegate to the map and re-serialise; `Data` throws `"Value must be set!"`; used for `"lunarPlusColor"` (`Int2IntMap`, `fog/holograms/FogLoader23`) |
| 2 | `LightingExtension493` | `DropdownOption` | T-valued option + `List<T>` choices; JSON `"choices"` from `toString()`, `"Options must be set!"`; `MarkersType.DROPDOWN`; used for `"soundFileName"` (`KillSoundChildMod`) |
| 3 | `LightingExtension494` | `MultiNumberOption` | `List<T extends Number>` with per-component `"names"` and `"integer"` flag, `"%.2f"` formatting; `MarkersType.MULTI_NUMBER`; replay keyframe vector property (`replay/ListProperty`, `TransformProperty`, `ExportSettingsPanel`) |
| 4 | `LightingExtension495` | `NamedDropdownOption` | dropdown whose choices carry `Function<T,String>` display names; JSON choices `{id,name,description}`; base of tiertagger `TierSourceOption` (`TierGameMode::niceName`) |
| 5 | `LightingExtension4952` | `CyclingDropdownOption` | abstract `NamedDropdownOption` with runtime `setOptions`/`getOptions` and cycle-to-next `method8` (`indexOf+1 % size`), driven by `TierTagger`'s cycle button (`field13.method8()`); base of `TierSourceOption`/`TierSourceModeOption` |
| 6 | `LightingExtension496` | `AutoTextHotkeyOption` | String value + paired `KeyBind` child option (`reset()` clears it to `KEY_NONE`); id `"autoHotkey"+index`, lang key `autoHotkey` (en_US `"Key $0"`), `MarkersType.KEYBIND`; built only by `mod/misc/AutoTextHotkey` via `Lighting.method16`; the config migration renames its JSON `"text"`→`"value"` |
| 7 | `LightingExtension497` | `EnumOption` | radio over `Enum<T> & Gui2Extension`: `"choices"` from each constant's `provide()` + `"hideRecommended"`; `MarkersType.RADIO`; factories `Lighting.method9/10`; the option used for every feature enum implementing `Gui2Extension` |
| 8 | `LightingExtension4972` | `TriStateOption` | `EnumOption` over the `BooleanOption` enum (FALSE/DEFAULT/TRUE): `orElse(boolean)` resolves DEFAULT; renderer `Calculator2Iterator314` draws a 3-segment off/default/on control; used for `"textShadow"` (`mod/hud/ActionBar`) |
| 9 | `LightingExtension498` | `DynamicDropdownOption` | choices computed on demand by `Callable<List<String>>` (+ optional display-name function); `"Provider must be set!"`; ids `"font"`, `"shaderPack"`, `"micInput"`, `"dungeonWaypointPreset"` |
| 10 | `LightingExtension499` | `ListOption` | editable `List<T>`: `add()`/`remove()`/`contains()` toggle + per-element `Codec`; `MarkersType.MODIFIABLE_LIST`; ids `"overridePacks"`, `"bossPriorityList"`, `"ignoreSlots"`, `"serverCommandPermissionWhitelist/Blacklist"` |
| 11 | `LightingExtension_2` | `CategorizedSettingsBuilder` | settings-tree builder contract; `extends SettingsBuilder` + labelled/icon categories (`method1/2/3/4/5`, `@Subst("generalOptions")`), wiring (`method6/7/9`) and build → `Map<Option,SettingsNode>` (`method12`); static prune helpers `method13-15` used by `Nameplate6`/`FeatureOptionContainer` |
| 12 | `LightingHandler_3` | `SettingsParent` | fluent section handle produced by `SettingsParentFactory` (`LightingExtension2$Extension2`) and handed to mod callbacks; forwards builder calls to the inner builder / section calls to the section and returns `Parent`/`Setting`; it is the `Parent` bound of `LightingExtension2_2` and the superclass of `SettingsTreeBuilder.Data`/`RootSettingsBuilder.Data` |
| 13 | `Lighting_6` | `ByteRangeOptionBuilder` | abstract byte slider builder: `extends AbstractOptionBuilder`, `Codec.BYTE`, `MarkersType.SLIDER`, min/max + forceMin/forceMax, attaches the `ByteNumberRange` rule via `NameplateImpl6.method1`, `"Min cannot be more than Max!"`; `Data` of `LightingExtension432` extends it |

## 3. Skipped (10) — already-named rescued duplicates

These files are **not new classes**: each is a second decompile of a type that
cluster 21 already renamed and the wave-6 move pass relocated to
`client.config.option`. The rescue sweeps (`cb44ec03` … `d8c57ec3`) re-added the
old-named copies because renamed-world call sites and jar-faithful call sites
coexist in the tree. Bodies are identical modulo renamed types/members/locals
(checked pairwise with a normalising diff; e.g. `LightingHandler2` vs
`SettingsNode` differs only in local names and in renamed type references such as
`Lighting_4`→`OptionTreeNode`). Emitting rows would re-use names that are already
declared, which the applier rejects (`new name already declared`), so they are
listed here only.

| old (still in `lighting/`) | already applied as (`config/option/`) | refs old / new (files, excl. own) |
|---|---|---|
| `LightingHandler2` | `SettingsNode` | 5 / 3 |
| `LightingHandler_2` | `AbstractOptionBuilder` | 11 / 1 |
| `LightingIterator2` | `OptionBaker` | 3 / 3 |
| `LightingIterator22` | `DevOptionBaker` | 2 / 3 |
| `LightingType2` | `OptionCategory` | 8 / 4 |
| `Lighting_2` | `SettingsTreeMapper` | 2 / 2 |
| `Lighting_3` | `SettingsRegistrant` | 3 / 1 |
| `Lighting_4` | `OptionTreeNode` | 5 / 4 |
| `Lighting_5` | `SettingsBuilder` | 7 / 3 |
| `Lighting_7` | `OptionProvider` | 24 / 38 |

**Recommended fix (structural, not a rename):** this is the same class of problem
as `bridge.BridgeExtension` in `classes-finalbridge.md` ("mis-moved duplicate,
unsafe to rename in place"). For each pair pick a survivor and delete the other,
then point the remaining call sites at the survivor. Both worlds are load-bearing
today (see the ref counts), so the dedupe pass should settle per pair — the
renamed copy carries the fully renamed members and 38 refs for
`OptionProvider`; the rescued copy is the jar-faithful body the config.option
classes still inherit from (e.g. `RootSettingsBuilder extends LightingExtension2_2`,
which references `Lighting_3`, `Lighting_5`, `Lighting_7`, `Lighting_6` and
`LightingHandler_3`).

## 4. Ambiguous rows / decisions worth recording

* **`TriStateOption` (4972)** is an `EnumOption` over the
  `BooleanOption` enum (FALSE/DEFAULT/TRUE). The name `TriState` is already taken
  by the unrelated `Gui2Extension2` enum (DISABLED/FORCE_ON/FORCE_OFF, a
  force-override setting), so the option and the enum do not share a stem.
  Alternatives considered: `DefaultableBooleanOption`, `SegmentedBooleanOption`.
* **`ListOption` (499)** could also be read as "multi-select". The widget is a
  MODIFIABLE_LIST editor (add/remove entries, not checkboxes), so the plain
  `ListOption` was preferred; `MultiSelectOption` is the fallback if the family
  wants the widget word.
* **Dropdown family:** 493 = `DropdownOption` (plain choices), 495 =
  `NamedDropdownOption` (display names), 4952 = `CyclingDropdownOption`
  (mutable + cycle helper), 498 = `DynamicDropdownOption` (provider-backed).
  Cluster-51's `LightingExtension4911` (a `"none"`/`"file:"` string dropdown) is
  a *different* dropdown and should take a distinct name (suggest `FileOption`).
* **`LightingExtension4952` old-name collision:** the name is declared in *two*
  packages (`lighting` and `framework/feature/tiertagger/mixin`, a rescued stale
  copy of `TierSourceOption`). The v1 applier skips colliding old names; use the
  import-aware `tools/apply_class_renames_aware.py`, which resolves the FQN
  `com.moonsworth.lunar.client.lighting.LightingExtension4952` in the
  `TierSourceOption`/`TierSourceModeOption`/tiertagger subclass headers and
  leaves the package-local tiertagger declaration alone. **Do not** use v1 with
  `--allow-collisions` here: its global token replace would also rename the
  different tiertagger class and its file.
* **`LightingExtension_2`** — the interface name `SettingsTreeBuilder` was
  rejected: it is already declared in `config.option` (applied rename of
  `LightingExtension22`). `CategorizedSettingsBuilder` reflects its defining
  feature over `SettingsBuilder` (labelled/icon categories).
* **`LightingHandler_3`** — `SettingsParent` is chosen to match the existing
  `SettingsParentFactory` in `config.option`, the factory that produces it.
* **`ByteRangeOptionBuilder` (Lighting_6)** — cluster-51 owns the sibling
  per-primitive builders; suggested family so the two halves stay coherent:
  `IntegerRangeOptionBuilder` (`Lighting3_2`), `LongRangeOptionBuilder`
  (`Lighting4`), `ShortRangeOptionBuilder` (`Lighting5`), `FloatRangeOptionBuilder`
  (`Lighting6`), `DoubleRangeOptionBuilder` (`Lighting2_3`; the Double/Float ones
  implement `SteppedNumberRangeBuilder`, the others `NumberRangeBuilder`).
* Suggested sibling names for cluster-51's option definitions (same family, same
  files not touched here): `4910 CategoryOption`, `4911 FileOption`,
  `4912 StringSetOption`, `4913 AbstractKeybindOption`, `49132 KeyCodeOption`,
  `49133 KeyBindOption`, `4914 ButtonOption`, `4915 TextOption`,
  `4916 SetOption`.

## 5. Validation

* All 13 new names were checked against a declaration index of `src/main/java`
  (zero declarations) and against `src/main/resources` (zero class references;
  `EnumOption` only appears inside translation keys such as
  `enchantCompressionEnumOption`).
* No trailing digits, no `DataN`/`TypeN`/`MixinN` leftovers; no mixin targets in
  this cluster.
* `LightingExtension4952` is the only row that needs the aware applier (old-name
  collision, see above); all other rows are single-package declarations, so the
  v1 applier also accepts them.
