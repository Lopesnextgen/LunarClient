# Cluster 51 — `com.moonsworth.lunar.client.lighting` (45 rows)

Source: `tools/renames/cluster-51.txt` (45 lines). **All 45 files exist**; nothing
skipped as missing. Map: `tools/renames/classes-51.tsv`.

Dry run (`tools/apply_class_renames_aware.py --map tools/renames/classes-51.tsv`):

```
[aware-renames] 45 rows (4 nested); 13684 java files
[aware-renames] rows=45 skipped=0 files_touched=623 files_renamed=41 mode=dry-run
```

The 4 nested rows apply too (`--verbose`):

| nested old | new | refs fixed |
|---|---|---|
| `Gui2Extension$Data2` | `EnumOptionCodec` | 1 file |
| `Lighting$Data2` | `SimpleOptionBuilder` | 3 files |
| `LightingExtension443$Data2` | `ToggleOptionBuilder` | 265 files |
| `LightingExtension4913$Data2` | `KeybindOptionBuilder` | 3 files |

## What this package actually is

Not lighting: it is the **options/settings framework** (the "Lighting" name is
only the first mod that used it — the real lighting mod is
`client/mod/render/Lighting` with id `LIGHTING`). The package was already
drained into `client/config/option` by `moves-misc`/`bucketsB`; what remains
here is the un-renamed core plus the copies the 2026-09-16 rescue sweep
restored. Class graph confirmed from source:

* `LightingExtension` (interface) extends `Lighting`, `OptionProvider`,
  `JsonConfigurable`, traits. Not in this cluster (name does not end in a
  digit, so `name_inventory.py` never listed it).
* `AbstractOption` (`LightingExtension4`, this cluster) → typed options
  (`AbstractIntegerOption`…`AbstractLongOption`, `AbstractValueOption`) →
  concrete options (`ToggleOption`, `ColorOption`, `LabelOption`, `TextOption`,
  `SoundOption`, `MultiSelectOption`, `ButtonOption`, `SetOption`, keybinds…).
* Builders: `AbstractOptionBuilder` (`LightingHandler_2`, already named) →
  `LongSliderOptionBuilder`/`ShortSliderOptionBuilder`/`FloatSliderOptionBuilder`
  (`Lighting4/5/6`), `CharacterOptionBuilder` (`LightingBase2`),
  `BooleanOptionBuilder` (`LightingBase_2`) → per-option `Data*` builders.
* `OptionFactory` is the static entry point (`Lighting`, 42 static builder
  methods) — **not in this cluster** (no trailing digit); see §3.

## Renames

| # | old | new | evidence (short) |
|---|---|---|---|
| 1 | `LightingExtension4` | `AbstractOption` | base of every option: id + codec + traits, JSON load/save/parse (`"Option Load/Save/Parse"`), Apollo broadcast, clone |
| 2 | `LightingExtension49` | `AbstractValueOption` | generic option storing value + default for `T` (getValue/getDefaultValue/method3/method4) |
| 3 | `LightingExtension42` | `AbstractIntegerOption` | abstract `Codec<Integer>` option with stored int + default |
| 4 | `LightingExtension422` | `IntegerOption` | concrete int option, clamps into the Nameplate range, integer renderer (`Calculator2Iterator317`); `Lighting.method4` |
| 5 | `LightingExtension4222` | `ColorOption` | chroma/hex/hue/saturation/brightness/alpha JSON, `MarkersType.COLOR_PICKER`; `Lighting.method8` |
| 6 | `LightingExtension43` | `AbstractByteOption` | abstract `Codec<Byte>` option |
| 7 | `LightingExtension432` | `ByteOption` | concrete byte option, clamps + slider |
| 8 | `LightingExtension44` | `AbstractBooleanOption` | abstract `Codec<Boolean>` option |
| 9 | `LightingExtension442` | `CategoryOption` | boolean group: Data uses `MarkersType.CATEGORY`, `method1(...)` nests children, reset/isDefault cascade |
| 10 | `LightingExtension443` | `ToggleOption` | checkbox (`MarkersType.CHECKBOX`), debug wrapper `": update("`/`": silentUpdate("`/`": silentUpdateNoSave("`; `Lighting.method7` |
| 11 | `LightingExtension45` | `AbstractDoubleOption` | abstract `Codec<Double>` option |
| 12 | `LightingExtension452` | `DoubleOption` | clamps/rounds, `"%.2f"`; `Lighting.method1` |
| 13 | `LightingExtension46` | `AbstractShortOption` | abstract `Codec<Short>` option |
| 14 | `LightingExtension462` | `ShortOption` | clamps to min/max slider; `Lighting.method5` |
| 15 | `LightingExtension47` | `AbstractFloatOption` | abstract `Codec<Float>` option |
| 16 | `LightingExtension472` | `FloatOption` | clamps/rounds, `"%.2f"`; `Lighting.method2` |
| 17 | `LightingExtension48` | `AbstractLongOption` | abstract `Codec<Long>` option |
| 18 | `LightingExtension482` | `LongOption` | clamps to min/max slider; `Lighting.method3` |
| 19 | `LightingExtension4910` | `LabelOption` | `SETTING_LABELS`, `getLanguagePath()+".labels"`, name supplier, CATEGORY with children |
| 20 | `LightingExtension4911` | `SoundOption` | dropdown sound id (`"none"`, `"file:"`), child `volume` integer option, `"choices"` JSON |
| 21 | `LightingExtension4912` | `MultiSelectOption` | `Set<String>` + available choices list + change Consumer, `MarkersType.MODIFIABLE_LIST` ("elements", waypoint room select) |
| 22 | `LightingExtension49122` | `ItemSelectOption` | item-registry multi-select (static id→`Itemcounter_2` map from `Bridge.method28().method99()`, potion variants) |
| 23 | `LightingExtension4913` | `AbstractKeybindOption` | abstract keybind (`MarkersType.KEYBIND`, isKeyDown/getKey, clashesWith, `.Data` `"clashesWith"` JSON) |
| 24 | `LightingExtension49132` | `SimpleKeybindOption` | single key code + Lunar key binding registration + `"ignoreClashing"` (tabKeybind, customKey) |
| 25 | `LightingExtension49133` | `ModifierKeybindOption` | key + shift/ctrl/alt, `"CTRL + K"` toString, JSON shift/control/alt (mod hotkeys) |
| 26 | `LightingExtension4914` | `ButtonOption` | `SETTING_BUTTONS`, `MarkersType.BUTTON`, Runnable + width, `".buttons"` |
| 27 | `LightingExtension4915` | `TextOption` | `MarkersType.TEXT`, isEditing, max length, key/char filters, edit/change consumers |
| 28 | `LightingExtension4916` | `SetOption` | generic `Set<V>` option with add/remove that save on change |
| 29 | `Lighting4` | `LongSliderOptionBuilder` | abstract Long builder, `NumberRangeBuilder`, `MarkersType.SLIDER`, `"Min cannot be more than Max!"` |
| 30 | `Lighting5` | `ShortSliderOptionBuilder` | abstract Short builder, same pattern |
| 31 | `Lighting6` | `FloatSliderOptionBuilder` | abstract Float builder, `SteppedNumberRangeBuilder` (+ decimal places) |
| 32 | `LightingBase2` | `CharacterOptionBuilder` | abstract `Character` builder (default `'\u0000'`, fluent `method2(char)`); no subclass in the tree |
| 33 | `LightingBase_2` | `BooleanOptionBuilder` | abstract Boolean builder: `MarkersType.CHECKBOX`, `Codec` boolean, fluent `method4(boolean)` |
| 34 | `Gui2Extension2` | `OverrideTriState` | `DISABLED/FORCE_ON/FORCE_OFF("disabled"/"forceOn"/"forceOff")` — see §2 |
| 35 | `Gui2Extension3` | `DefaultedBoolean` | `FALSE/DEFAULT/TRUE` + `orElse(fallback)` — see §2 |
| 36 | `Lighting2` | `KeyCombo` | `(alt, shift, control, key)` binding, `toString "KeyBind(alt=…)"` — see §2 |
| 37 | `Lighting3` | `JsonPersistable` | `load(JsonObject)/method1(JsonObject)/priority()=1000` — see §2 |
| 38 | `LightingExtension2` | `CompositeOption` | composite over an option array, `"Must have at least one option for OptionCombiner!"` — see §2 |
| 39 | `LightingExtension22` | `SettingsTreeAssembler` | settings-tree builder (extends `LightingExtension2_2`, implements `OptionBakerFactory`); byte-identical to 23 — see §2 |
| 40 | `LightingExtension23` | `RootSettingsAssembler` | the used root settings-tree builder + `OptionBakerFactory` — see §2 |
| 41 | `LightingExtension3` | `GuardedOption` | option wrapper no-op'd by a `BooleanSupplier` guard — see §2 |
| 42 | `Gui2Extension$Data2` | `EnumOptionCodec` | enum codec: extends `Gui2Extension$Data`, adds `String id → E` resolver |
| 43 | `Lighting$Data2` | `SimpleOptionBuilder` | plain T builder returned by `Lighting.method40-42` |
| 44 | `LightingExtension443$Data2` | `ToggleOptionBuilder` | checkbox builder for `ToggleOption`, optional `BooleanSupplier` alert |
| 45 | `LightingExtension4913$Data2` | `KeybindOptionBuilder` | abstract keybind builder for `AbstractKeybindOption` subclasses |

## §2 Duplicate copies (ambiguous rows)

Eight classes are **the same class as an already-named `client.config.option`
class**; the rescue sweep restored the pre-move copies under their old names, so
both now compile and are referenced from different call sites. Applying the
exact twin name would be skipped by `apply_class_renames_aware.py` ("new name
already declared"), so these rows keep the tree unique and non-lazy instead. The
real fix for these is a **merge** (delete one copy, repoint refs), not a rename —
the table lists the twin and its reference count for that decision.

| cluster class | new name here | already-named twin (`client.config.option`) | refs here / refs twin (FQN grep) |
|---|---|---|---|
| `Gui2Extension2` | `OverrideTriState` | `TriState` | 1 / 2 |
| `Gui2Extension3` | `DefaultedBoolean` | `BooleanOption` | 1 / 0 |
| `Lighting2` | `KeyCombo` | `KeyBind` | 15 / 10 |
| `Lighting3` | `JsonPersistable` | `JsonConfigurable` | 17 / 12 |
| `LightingExtension2` | `CompositeOption` | `OptionCombiner` | 1 / 1 |
| `LightingExtension22` | `SettingsTreeAssembler` | `SettingsTreeBuilder` | 7 / 0 |
| `LightingExtension23` | `RootSettingsAssembler` | `RootSettingsBuilder` | 73 / 281 |
| `LightingExtension3` | `GuardedOption` | `ConditionalOption` | 1 / 1 |

Notes:

* Token-level diffs (identifier sets, source length) confirm each pair is the
  same decompiled class; only local names and already-renamed type references
  differ.
* `LightingExtension22`/`23` are byte-identical siblings. Both are live in the
  current tree: `22` is instantiated by `fog.FogLoader2.method12()` and its
  subclasses, `23` by `framework/nameplate/Nameplate6.method7` (73 FQN refs).
  Their `config.option` twins are `SettingsTreeBuilder` (0 FQN refs) and
  `RootSettingsBuilder` (281 FQN refs) respectively.
* If the merge pass runs before this map, delete the `client.lighting` copies
  in favour of `config.option` and drop rows 34–41; if it runs after, the two
  variants keep working unchanged.
* If a merge is preferred and the twin is deleted first, the intended final
  names are the twin names (`TriState`, `BooleanOption`, `KeyBind`,
  `JsonConfigurable`, `OptionCombiner`, `SettingsTreeBuilder`,
  `RootSettingsBuilder`, `ConditionalOption`).

## §3 Deliberately not renamed (not in this cluster)

These are lazy-adjacent names in the same package that `name_inventory.py`
does not classify as lazy (no trailing digit) and therefore **no cluster is
expected to cover** (the sibling cluster 52 has picked up a few of them);
listed here so the audit pass does not lose them. The Lighting mod
itself is `client/mod/render/Lighting`, so a rename of the factory to
`OptionFactory`/`Options` does not clash.

| name | what it is | suggested rename |
|---|---|---|
| `Lighting` | options entry point: 42 static builder factories used ~3,000× | `OptionFactory` (or `Options`) |
| `LightingExtension` | the option interface (`get/set/load/save/render`, extends `Lighting`) | `Option` |
| `Gui2Extension` | interface implemented by enum option values (`id/icon/description/provide`) | `OptionEnumValue` |
| `Lighting_2`/`Lighting_3`/`Lighting_4`/`Lighting_5`/`Lighting_7` | `SettingsTreeMapper`, `SettingsRegistrant`, `OptionTreeNode`, `SettingsBuilder`, `OptionProvider` | already mapped by `lighting-modhud`; only the files are back under old names after the rescue |
| `LightingHandler_2`, `LightingHandler`, `LightingHandler2`, `LightingHandler_3` | `AbstractOptionBuilder`, `BakedOptionNode`, `SettingsNode`, settings node impl | ditto |
| `LightingBase`, `LightingException`, `LightingType`, `LightingType2`, `LightingIterator*`, `Lighting2_2`, `LightingExtension2_2` | `DefaultedOptionBuilder`, `OptionException`, `OptionFlag`, `OptionCategory`, `PruningOptionBaker`/`OptionBaker`/`DevOptionBaker`, `OptionBakerFactory`, settings-container impl | ditto |
| `Lighting$Extension`, `Lighting$Extension2` | `NumberRangeBuilder`, `SteppedNumberRangeBuilder` | ditto |
| `Lighting3_2`, `Lighting2_3`, `Lighting_6` | Integer/Double/Byte slider builders (siblings of this cluster's `Lighting4/5/6`) | `IntegerSliderOptionBuilder`, `DoubleSliderOptionBuilder`, `ByteSliderOptionBuilder` |
| `LightingExtension492`…`499`, `4952`, `4972` | map/list/enum/number-list options | already mapped by the sibling cluster 52 (`MapOption`, `DropdownOption`, `EnumOption`, …) |

The `lighting-modhud` ledger already named many of these; the rescue restored
the decompiled originals next to the moved/renamed copies, so they will show up
as duplicates in the same way as §2 when their clusters are processed. The
`client.lighting` package as a whole is really `client.config.option`; a package
move/merge (not a rename) is the right follow-up.
