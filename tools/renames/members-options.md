# `client.lighting` options-framework member map (`members-options.tsv`)

Scope: the options/settings framework in `com.moonsworth.lunar.client.lighting`
(the class-renamed "lighting" package): the `Lighting` factory, the option
classes, their `Data`/`ToggleOptionBuilder` builders and the shared bases
(`LightingExtension` = option interface, `AbstractOption`, `LightingHandler_2` =
`AbstractOptionBuilder`, the slider/boolean/character/keybind builders).

224 rows. Apply with:

```
tools/apply_member_renames.py --map tools/renames/members-options.tsv          # dry-run
tools/apply_member_renames.py --map tools/renames/members-options.tsv --apply
tools/error_diff.py                                                            # no NEW failures
```

Owner keys are the *current* FQNs (`Lighting`, `LightingHandler_2`, …), the
names that still exist in `src/`. Class renames for this package (clusters
51/52) are already applied, so the applier resolves receivers through the
renamed hierarchy.

---

## 1. Family conventions

### 1.1 `Lighting` — static factories (38 rows)

`key -> builder`, named after what they build. Where the factory has a
with-default overload, both rows map to the same new name (normal Java
overloading; the applier maps per-owner old-name, so `method9/10`, `20/22`,
`23/24`, `25/26`, `29/30/31`, `33/34/35`, `36/37/38`, `40/41/42` all share
their family name).

| factory | new name | returns |
|---|---|---|
| `method2` | `floatOption` | `FloatOption.Data` (slider/range) |
| `method5`/`method6` | `shortOption`/`byteOption` | `ShortOption.Data`/`ByteOption.Data` |
| `method7` | `toggle` | `ToggleOption.ToggleOptionBuilder` (1576 sites) |
| `method8` | `color` | `ColorOption.Data` (540 sites) |
| `method9`/`method10` | `enumOption` | `EnumOption.Data<T>` |
| `method11` | `triState` | `TriStateOption.Data` |
| `method12`…`method19` | `text`, `sound`, `button`, `label`, `autoTextHotkey`, `simpleKeybind`, `modifierKeybind`, `dynamicDropdown` | per-option `Data` |
| `method20`/`method22` | `multiNumber` | `MultiNumberOption.Data<T>` |
| `method23`/`method24` | `dropdown` | `DropdownOption.Data<T>` |
| `method25`/`method26` | `namedDropdown` | `NamedDropdownOption.Data<T>` |
| `method27` | `multiSelect` | `MultiSelectOption.Data` |
| `method28` | `itemSelect` | `ItemSelectOption.Data` |
| `method29`–`method32` | `list`, `listWithRenderer` | `ListOption.Data` |
| `method33`–`method35` | `set` | `SetOption.Data` |
| `method36`–`method38` | `map` | `MapOption.Data` |
| `method39` | `empty` | `Lighting.Data` (UNKNOWN marker, null value) |
| `method40`–`method42` | `simpleOption` | `Lighting.SimpleOptionBuilder<T>` |

**Deliberately not renamed here:** `method1` (`doubleOption`), `method3`
(`longOption`), `method4` (`integerOption`) and `method21` (`multiNumber`
list-default). Reason: `LightingExtension extends Lighting`, so for a receiver
statically typed `LightingExtension` the applier walks into the factory
interface. Those four numbers are the ones used by option-interface call sites
on `LightingExtension`-typed receivers (`field1.method4(...)`,
`var1.method1(...)`, `lightingextension2.method3(...)`, `.method21(...)`), and
renaming the factory would rewrite those calls to the factory names. See §3.

### 1.2 Builder base `LightingHandler_2` (34 rows)

Fields: `id`, `markerType`, `hideFromApi`, `nameSupplier`, `description`,
`icon`, `noCodec`, `jsonProvider`, `visibleWhen`, `featureLink`, `badges`,
`hiddenByDefault`, `customComponent`.
Methods:

| old | new | note |
|---|---|---|
| `method9` | `customComponent` | no-arg hook + `BiFunction` setter |
| `method12` | `description` | |
| `method13`/`method14` | `displayName` | constant + supplier overloads |
| `method15`/`method16` | `icon`/`markerType` | two different `MarkersType`s |
| `method17` | `visibleWhen` | `BooleanSupplier` |
| `method18`/`method19` | `feature`/`featureLink` | |
| `method20`/`method21` | `codec`/`unencoded` | set/clear the codec |
| `method22`/`method23` | `hideFromApi` | boolean + no-arg |
| `method24`/`method25` | `shown`/`hidden` | hidden-by-default flag |
| `method26`/`method27`/`method28` | `setFlags`/`addFlags`/`advanced` | `LightingType` badges |
| `method29` | `resolveDefaults` | fills codec/provider/visibility defaults |
| `method30` | `configure` | copies builder state onto the built option (overridden by all 6 slider builders — rows included) |
| `method31` | `build` | the abstract build; also declared by `Lighting.Data`/`Lighting.SimpleOptionBuilder`. ~3.3k `.method31()` call sites tree-wide. |

**Skipped on purpose:** `method2`/`method3`/`method4`/`method5`/`method6`/
`method7`/`method8`/`method10`/`method11` hooks. The applier is *name-only*
(no signature matching), so renaming an abstract hook also renames every
same-named overload/override in the hierarchy:
`LightingBase.method10()` vs `LightingHandler_2.method10(Function)` vs the
option-side `method10(T)`; `X.Data.method11()` factories vs
`method11(Consumer)`; `ToggleOptionBuilder.method2(BooleanSupplier)` vs the
`method2()` marker hook. Slider builders get their `method4/5/6/7/8` fluent
setters renamed instead (below), which is where the call-site value is.

### 1.3 Builder subclasses

* **Slider builders** (`FloatSliderOptionBuilder`, `Lighting2_3` = double,
  `Lighting3_2` = integer, `Long/ShortSliderOptionBuilder`,
  `ByteRangeOptionBuilder`): `method4 -> defaultValue`,
  `method5 -> forceRange`, `range` (`method7`/`method8`, incl. the deprecated
  boxed aliases), `decimalPlaces` (`method6` on the float/double builders), and
  field names `defaultValue`/`min`/`max`/`decimalPlaces`/`forceMin`/`forceMax`.
* **`BooleanOptionBuilder`**: `field14 -> defaultValue`, `method4 -> defaultValue`.
* **`CharacterOptionBuilder`**: `field14 -> defaultValue`, `method2(char) -> defaultValue`.
* **`ToggleOptionBuilder`**: `method2(BooleanSupplier) -> alert`, `field15 -> alert`.
* **`X.Data` option factory hooks** (all 27 declaring classes): `method11`/
  `method12`/`method13`/`method19 -> createOption` (the protected hook that
  constructs the concrete option; called by `build()`).
* **`ColorOption.Data`** (18 rows): own builder API shadowing the inherited
  slider names — `method4/5/6 -> chroma/chromaSpeed/chromaType`,
  `method7..method13 -> defaultValue` overloads (TextColor/`HorsestatsType8`/
  `java.awt.Color`/rgb/rgba/float), `method14 -> withChroma`,
  `method15 -> withoutChroma`, `method16 -> forceOpaque`,
  `method17 -> chroma`, `method18 -> allowAlpha`, `method19 -> createOption`,
  `field17 -> chroma`, `field18 -> allowAlpha`.
* **Keybind Data**: `AutoTextHotkeyOption.Data` (`method3 -> index`,
  `method4 -> defaultKey`), `SimpleKeybindOption.Data`
  (`method2 -> ignoreClashing` from the JSON key, `method11 -> respectClashing`,
  `method6/field16/method12 -> showInControls`; evidence: `Legacy2.method12`
  adds the key to `gameSettings.keyBindings` when the flag is true).
* **`LabelOption.Data`**: `method12 -> hidden` (it calls `super.method25()`,
  the hidden-by-default flag; `LightingExtension2_2` uses
  `Lighting.method15(...).method12().method31()` to create hidden section
  labels). Without this row the call sites would be rewritten to the inherited
  `description()` and stop compiling.

### 1.4 Option side (safe subset)

* `LightingExtension.method20` + every declaring override
  (`AbstractOption`, `CompositeOption`, `GuardedOption`,
  `config.option.ConditionalOption`, `config.option.OptionCombiner`,
  `ListOption`) -> `copy`.
* `method25 -> createRenderer` on `AbstractOption` + all 25 concrete options +
  `config.option.CrosshairDrawOption` (the protected hook called by
  `method18(Calculator2Handler)`; no overload conflicts).

**Skipped on the option side:** `get`/`getValue`/`setValue` are already real;
`method3/4/5/…/10` are blocked because `AbstractOption` implements the
Lightoverlay trait interface with overloads `method1..method10(Lightoverlay9,…)`
in the *same class* (one new name per old name would break those `@Override`s).
`method12/13/14/19` are blocked by `ColorOption`/`AbstractKeybindOption`
same-name members; `method16` by a private/public overload pair.

---

## 2. Notes for the applier / reviewer

1. **Overload groups share one new name** (applier constraint): same owner+old
   can only have one mapping. `displayName`, `hideFromApi`, `range`,
   `defaultValue`, `createOption` therefore cover 2–7 overloads each. Java
   overloading keeps this compile-correct.
2. **The map is owner-scoped.** `field1` etc. exist in dozens of lighting
   classes; only accesses whose receiver resolves to `LightingHandler_2` (or a
   subclass in its chain) are rewritten.
3. **`config.option` twins are not covered** (`AbstractOptionBuilder`,
   `DefaultedOptionBuilder`, `ConditionalOption`, `OptionCombiner`,
   `CrosshairDrawOption`). `method20 -> copy`/`method25 -> createRenderer` rows
   for the three twins are the minimum needed to keep them compiling after the
   interface/hierarchy renames; for the builder twins apply the same names as
   §1.2 to `config.option.AbstractOptionBuilder`/`DefaultedOptionBuilder` when
   that package gets its own member batch (the two copies should not diverge).
4. **`Lighting` factory leak to `LightingExtension`-typed receivers.** ~90 call
   sites tree-wide call `.methodN(...)` on a variable declared
   `LightingExtension<?>`; because the interface `extends Lighting`, the
   applier will rewrite those calls to the factory name (e.g.
   `EnabledOption.setEnabled`: `field1.method10(flag)` -> `field1.enumOption(flag)`).
   The four factory rows that mostly caused this were dropped (§1.1). For the
   rest, run `error_diff` after applying; if new failures appear, either add
   shadow rows for `LightingExtension` (requires renaming the 5 direct
   implementers) or patch those ~90 sites by hand. The affected names are
   concentrated in `framework/nameplate/*`, `config/option/OptionJsonProvider`,
   keystroke mods and `MixinHelper_3`.
5. **`X.Data` factory-number chaos.** The same hook is `method11`, `method12`,
   `method13` or `method19` depending on the file — hence one uniform
   `createOption` name.
6. **Stale `Option.Hud` references.** `mod/combat/AttackIndicator.java` lines
   61/68/73/83/88/93 reference `ColorOption.Hud`/`FloatOption.Hud`; the member
   batch `0ce30215` accidentally rewrote `LightingExtension4222.Data`/
   `LightingExtension472.Data` while renaming the local `AttackIndicator.Data`.
   Those nested types are `ColorOption.Data`/`FloatOption.Data` today — revert
   the six qualifiers (or point them at the builder names in §4).

## 3. `config.option` twin suggestions (not in this map)

`config.option.{AbstractOptionBuilder, DefaultedOptionBuilder}` mirror
`LightingHandler_2`/`LightingBase` member-for-member. When that package is
processed, reuse §1.2 names verbatim, plus:

* `DefaultedOptionBuilder.method10() -> defaultValue()` (hook),
  `method2(T) -> defaultValue(T)` — the `LightingBase` equivalent was not
  renamed here because of the `LightingHandler_2.method10(Function)` clash;
  a signature-aware pass can do it cleanly for both copies.
* `ConditionalOption`/`OptionCombiner` already get `method20 -> copy` here.

## 4. Nested type names (class-rename territory, NOT in `members-options.tsv`)

The nested `Data` classes are builders; recommend a class-rename batch:

* `Lighting$Data` -> `EmptyOptionBuilder` (builds an anonymous
  `AbstractOption<Void>`, `MarkersType.UNKNOWN`).
* `Lighting$Data2` is already `SimpleOptionBuilder`.
* `<Option>$Data` -> `<Option>Builder` for every option (`ColorOption$Data` ->
  `ColorOptionBuilder`, `FloatOption$Data` -> `FloatOptionBuilder`,
  `ToggleOption$Data2` -> `ToggleOptionBuilder` already applied,
  `AbstractKeybindOption$Data` -> `KeybindOptionData`, …). Keep
  `ToggleOptionBuilder` (already applied) and
  `AbstractKeybindOption$KeybindOptionBuilder`.
* `Gui2Extension$Data2` -> `EnumOptionCodec` (already applied).

## 5. Known weak guesses

* `SimpleKeybindOption.Data.showInControls` (field16) — inferred from
  `Legacy2.method12(name,key,category,flag)` adding the binding to
  `gameSettings.keyBindings` only when the flag is set.
* `ColorOption.Data.chroma/allowAlpha` naming — inferred from
  `Calculator2Iterator39` (chroma widgets gated by `option.method9()`, alpha
  slider/force-opaque by `option.method10()`).
* `KeybindOptionBuilder.method4/5` (`unique`/`ignoreClashing` flags) **not**
  renamed: `ModifierKeybindOption.Data`/`SimpleKeybindOption.Data` both declare
  their own `method4()` JSON-provider override, so a rename would poison those
  `@Override`s.
* `ModifierKeybindOption.Data` `method3/5/11` (extra ctor flag / default-key
  shortcut) left as placeholders — `field16` semantics could not be pinned from
  the source alone.
