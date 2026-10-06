# Audit cluster 07 — `client.fps.mixin` … `framework.feature.attackindicator`

Source slice: `tools/renames/audit-cluster-07.txt` (8 packages, all contain
`.java` files). Output maps: `classes-audit07.tsv` (23 rows),
`packages-audit07.tsv` (4 rows).

This is an **accuracy audit**, not a lazy-name pass. The classes below already
had non-lazy-looking names (or were left behind by the earlier waves); reading
their bodies shows the names describe the wrong subsystem or the wrong role.
No sources were edited.

## What each package actually is

| package | real subsystem | verdict |
|---|---|---|
| `client.fps.mixin` | Molang AST (the `fps` package is the Molang JIT, classes-19) | package + class both wrong |
| `client.framework` | Lunar's mod/feature framework: HUD component model, command tree, mod traits | package name fine; 14 leftover/`Mixin*` names wrong |
| `client.framework.feature` | framework support leftovers (HUD geometry, staff-xray trait, option overrides) | `Module*`/`Staffxray` wrong |
| `...feature.animations.customhelditems` | leftover nested `Type` of the CustomHeldItems module | class wrong |
| `...feature.armorstatus.mixin` | armor-status config enums (no mixins) | package + `ArmorstatusType` wrong |
| `...feature.armorstatus.nameplate` | armor-status HUD element/renderers (no nameplates) | package + `Nameplate` wrong |
| `...feature.armorstatus.nameplate.mixin` | armor-slot child mod + its HUD (no mixins/nameplates) | package + `Nameplate` wrong |
| `...feature.attackindicator` | attack-indicator render mode enum | package fine; `Gui2Extension` wrong |

## The framework package is the command + HUD framework

The `mixinNameplate`/`mixinCore` provenance in
`tools/work/mappings/normalize-renames.tsv` and the code in
`framework/nameplate/{CommandCompleter,Nameplate3_2,Nameplate3Handler}.java`
show that the `MixinNameplate*` family is a **command tree**, not nameplates:

* `MixinNameplate` — abstract node (children + executor + `matches(StringCursor,BoundArguments)`)
* `MixinNameplateImpl` — literal node (name)
* `MixinNameplateIterator` — argument node (parser + suggestion provider)
* `MixinCore` — argument parser base (`MixinCoreImpl/2/3/4/42` = Double/String/Integer/player-name)
* `MixinHelper` — `@FunctionalInterface execute(CommandArguments)` executor
* `Nameplate2_2`/`Nameplate3_2` (other cluster) — suggestion provider/builder

The `MixinCore5*` classes are HUD components, and `MixinCore$Data` is the
builder of the 4-condition set `MixinCore_2` (a different original package,
`mixinCore/MixinCore`), so it is named independently.

## Class renames (23)

See `classes-audit07.tsv`. Highlights:

| old | new | why |
|---|---|---|
| `Framework` | `ModTraits` | static registry of `TraitType` keys ("options","display","events",…) |
| `Framework10Extension` | `ModLifecycle` | per-mod enable/disable + listener/command registration (key "events") |
| `FrameworkType` | `ModLoadState` | CONSTRUCTOR…COMPLETE load lifecycle |
| `AlertExtension` | `ModChildren` | the "children" trait, not an alert |
| `Calculator2Handler` | `ModCategory` | category values "new"/"hud"/"server"/… |
| `Gui2Extension` | `HudAlignment` | TOP/BOTTOM/MIDDLE/LEFT/RIGHT anchor |
| `MixinNameplate`/`Impl`/`Iterator` | `CommandNode`/`LiteralCommandNode`/`ArgumentCommandNode` | command tree |
| `MixinCore` | `CommandArgumentParser` | argument parser base |
| `MixinCore$Data` | `ConditionSetBuilder` | builder of `MixinCore_2` condition set |
| `MixinCore5Handler`/`MixinCore5Impl` | `EmptyHudComponent`/`HideableHudComponent` | HUD components |
| `MixinHelper` | `CommandExecutor` | command callback |
| `Module`/`ModuleHandler` | `HudElement`/`HudElementBounds` | HUD geometry, not a mod |
| `Staffxray` | `StaffXrayState` | value of the "staff_mod" trait |
| `EvaluatableImpl` | `ConstantEvaluatable` | Molang AST constant |
| `CustomhelditemsType` | `HeldItemHand` | LEFT/RIGHT hand enum |
| `ArmorstatusType` | `ArmorStatusSlot` | equipment-slot enum |
| `Nameplate` (×2) | `ArmorStatusElement` / `ArmorStatusElementHud` | armor-status element / its HUD |
| `Gui2Extension` (attackindicator) | `AttackIndicatorMode` | VANILLA…PROGRESS mode |

## Package renames (4)

See `packages-audit07.tsv`.

* `client.fps.mixin` → `client.molang.ast` (the parent `client.fps` should become
  `client.molang`; that rename is owned by cluster 06/19).
* `framework.feature.armorstatus.mixin` → `framework.feature.armorstatus`.
* `framework.feature.armorstatus.nameplate` → `framework.feature.armorstatus.hud`.
* `framework.feature.armorstatus.nameplate.mixin` → `framework.feature.armorstatus.hud` (merge).

## Collision / safety checks

* All 23 new simple names were checked with
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java` — 0 hits.
* `Nameplate` is declared in two of the audited packages; the aware applier is
  package-scoped, so the two rows are safe.
* `MixinCore`, `AlertExtension`, `Gui2Extension`, `Nameplate` etc. collide with
  other packages tree-wide; use `tools/apply_class_renames_aware.py` (never
  `--allow-collisions`). The `file` column is populated for every row.
* Renaming `Framework` is safe with the aware applier: its bare token pattern
  `(?<![A-Za-z0-9_$.])Framework(?![A-Za-z0-9_$])` still matches `Framework.fieldN`
  (the lookahead only blocks identifier chars) and does not match
  `Framework7Extension`/`Framework10Extension`.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-audit07.tsv
# → [aware-renames] 23 rows (1 nested); 6594 java files
#   rows=23 skipped=0 files_touched=427 files_renamed=23 mode=dry-run

python3 tools/apply_package_renames.py --map tools/renames/packages-audit07.tsv
# → [pkg-renames] rows=4 files_moved=10 files_touched=20 mode=dry-run
```

Both dry-runs are clean. **Apply the class map before the package map**: the two
`Nameplate` classes (armorstatus `.nameplate` and `.nameplate.mixin`) both move
to `...armorstatus.hud` and must first be renamed to `ArmorStatusElement` /
`ArmorStatusElementHud` so the directories can merge without a filename clash.

## Follow-ups for neighbouring clusters

* `MixinCore_2` (quarantined, `framework`) should become `ConditionSet` to pair
  with `ConditionSetBuilder`; `MixinCoreImpl*` should become `<Type>ArgumentParser`.
* `framework.nameplate.Nameplate2_2`/`Nameplate3_2`/`Nameplate3Handler` are the
  command suggestion provider/builder (`SuggestionProvider`/`SuggestionsBuilder`).
* `armorstatus.nameplate.mixin.ArmorStatusElementChildMod` is fine as-is.
* `framework.feature` itself was left alone: it is the feature-framework root and
  renaming it would cascade into every `framework.feature.*` subpackage.