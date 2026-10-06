# Cluster 51 — `com.moonsworth.lunar.client.lighting` (wave 5)

Map: `tools/renames/wave5/classes-51.tsv` — **26 rows, 0 skipped**.

Dry run (`tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-51.tsv`):

```
[aware-renames] 26 rows (0 nested); 13684 java files
[aware-renames] rows=26 skipped=0 files_touched=766 files_renamed=26 mode=dry-run
```

## 1. The handed cluster file is stale

`tools/renames/cluster-51.txt` was generated at 15:07 from the pre-apply
inventory, then its own subagent map (`tools/renames/classes-51.tsv`) was
**applied in commit `69c557fa9`** ("clusters 51/52/59", 80 renames). Every one
of the 45 handed rows therefore names a file/class that no longer exists:

* 43 rows: file renamed to the classes-51/52 new name (e.g.
  `LightingExtension443.java → ToggleOption.java`,
  `LightingExtension4.java → AbstractOption.java`, `Lighting2.java → KeyCombo.java`,
  `Lighting_6.java → ByteRangeOptionBuilder.java` — see `classes-51.tsv` /
  `classes-52.tsv`).
* 2 rows are paths that still exist but whose target class is gone:
  `Gui2Extension.java` (`Gui2Extension$Data2 → EnumOptionCodec`) and
  `Lighting.java` (`Lighting$Data2 → SimpleOptionBuilder`).

**Skipped rows: all 45** (stale duplicates of an applied map). Nothing was
skipped as shaded third-party code and nothing went to `net.minecraft.*`.

Root cause worth fixing in the wave-5 generator: `tools/make_clusters.py` reads
`tools/renames/inventory.tsv`; the inventory was regenerated at 15:07 but the
cluster files are only refreshed when the generator is re-run *after* applies.
A fresh run of `tools/name_inventory.py` today yields 4,383 lazy declarations
and only **10** in `client.lighting` — the 10 that `classes-52.md` §3 explicitly
skipped because the canonical names were already taken by `config.option`.

## 2. What this map actually renames

The package is Lunar's internal options/settings framework (the real lighting
engine is `client.mod.render.Lighting` + `client.lightoverlay`). It now contains
two co-existing worlds: the canonical renamed classes in
`client/config/option` (from the earlier move/rename passes) and the
**rescued jar-faithful copies** still sitting in `client.lighting` under their
decompile placeholders. Wave-5 sibling `classes-40.tsv` renames rescued bridge
duplicates the same way, so this map keeps that practice: the duplicates get
unique, descriptive variant names (the canonical twin name is already declared,
which the applier rejects).

| # | old | new | group |
|---|---|---|---|
| 1 | `Lighting` | `OptionFactory` | core (not a duplicate) |
| 2 | `LightingExtension` | `ClientOption` | core (not a duplicate) |
| 3 | `Gui2Extension` | `OptionEnumValue` | core (not a duplicate) |
| 4 | `Lighting$Extension` | `NumberRangeConfigurator` | range contract dup of `NumberRangeBuilder` |
| 5 | `Lighting$Extension2` | `SteppedNumberRangeConfigurator` | dup of `SteppedNumberRangeBuilder` |
| 6 | `LightingHandler_2` | `OptionBuilderBase` | dup of `AbstractOptionBuilder` |
| 7 | `LightingBase` | `DefaultValueBuilder` | dup of `DefaultedOptionBuilder` |
| 8 | `Lighting2_3` | `DoubleRangeOptionBuilder` | Double slider builder |
| 9 | `Lighting3_2` | `IntegerRangeOptionBuilder` | Integer slider builder |
| 10 | `LightingException` | `OptionConfigException` | dup of `OptionException` |
| 11 | `LightingType` | `AdvancedOptionFlag` | dup of `OptionFlag` |
| 12 | `LightingType2` | `OptionSection` | dup of `OptionCategory` |
| 13 | `Lighting_2` | `OptionTreeMapper` | dup of `SettingsTreeMapper` |
| 14 | `Lighting_3` | `OptionRegistrant` | dup of `SettingsRegistrant` |
| 15 | `Lighting_4` | `OptionHierarchyNode` | dup of `OptionTreeNode` |
| 16 | `Lighting_5` | `SettingsComposer` | dup of `SettingsBuilder` |
| 17 | `Lighting_7` | `OptionSupplier` | dup of `OptionProvider` |
| 18 | `Lighting2_2` | `OptionBakerProvider` | dup of `OptionBakerFactory` |
| 19 | `LightingIterator` | `OptionTreePruner` | dup of `PruningOptionBaker` |
| 20 | `LightingIterator2` | `FeatureOptionBaker` | dup of `OptionBaker` |
| 21 | `LightingIterator22` | `DevelopmentOptionBaker` | dup of `DevOptionBaker` |
| 22 | `LightingHandler` | `ResolvedOptionNode` | dup of `BakedOptionNode` |
| 23 | `LightingHandler2` | `OptionGraphNode` | dup of `SettingsNode` |
| 24 | `LightingExtension2_2` | `SettingsTreeContainer` | concrete `CategorizedSettingsBuilder` |
| 25 | `LightingExtension2$Extension` | `SettingsGroupCreator` | dup of `SettingsGroupFactory` |
| 26 | `LightingExtension2$Extension2` | `SettingsParentCreator` | dup of `SettingsParentFactory` |

Rows 4/5/25/26 are declared as separate top-level files named `Owner$Inner`
(the applier detects `$` names declared at top level and renames the files);
rows 13–23 are the 10 fresh-lazy duplicates + `LightingIterator`.

## 3. Evidence used

* Source structure: superclass/interfaces, member names, string literals
  (`"Min cannot be more than Max!"`, `"DevOptionBaker can only be used in a
  development environment!"`, `"Value must be set!"`), annotations
  (`@Annotation(Type.SETTING)`, `@MustBeInvokedByOverriders`), MarkersType use.
* Duplicate identity: token-multiset comparison against the already-applied
  `client.config.option` twins (jac 0.41–0.85; printed in the `evidence`
  column); `classes-51.md` §2/§3, `classes-52.md` §3/§4.
* Usage: import-aware reference counts (`Lighting` 323 importers,
  `LightingExtension` 487, `Gui2Extension` 43, `Lighting_7` 19); instantiation
  sites (`SettingsTreeContainer.method12`, `OptionBakerProvider.method2`).

## 4. Ambiguities / decisions worth recording

* **Rename vs dedupe.** `classes-52.md` §3 recommended a structural dedupe for
  the 10 twin pairs instead of a rename (both copies are load-bearing today).
  If the main agent prefers a merge pass, drop rows 4–7, 10–23, 25–26 from the
  map and keep only 1–3, 8–9, 24 (the classes with no `config.option` twin).
  Otherwise this map makes the package differentiable for a later dedupe.
* **`ClientOption`, not `Option`.** `classes-51.md` §3 suggested `Option`, but
  `MixinHelper_3.java` and `Highlight3Iterator_2.java` already import
  `com.lunarclient.apollo.option.Option` and use it; the applier would have
  produced a duplicate import and a new ECJ failure. `ClientOption` is unused
  tree-wide.
* **Range vs Slider family.** Applied sibling names are inconsistent:
  `Long/Short/FloatSliderOptionBuilder` (classes-51) vs `ByteRangeOptionBuilder`
  (classes-52). Rows 8–9 follow the `classes-52.md` §4 recommendation
  (`DoubleRangeOptionBuilder`/`IntegerRangeOptionBuilder`). A follow-up pass may
  want to harmonise the family; no non-placeholder class is renamed here.
* **`LightingType.java` is broken** (decompiled enum declares `ADVANCED` twice);
  it is one of the pre-existing non-compiling files. The rename is token-safe
  but does not repair it.
* **`LightingExtension2_2` has no `config.option` twin** (closest is
  `SettingsSectionImpl`, jac 0.16), so `SettingsTreeContainer` is a new
  canonical name; it is the base class of the applied
  `RootSettingsAssembler`/`SettingsTreeAssembler`.
* **Overlap with a regenerated cluster 52.** A fresh wave-5 regeneration will
  count rows 13–23 as `client.lighting` lazies and could hand them to a
  cluster-52 subagent. If this map is applied, mark them done to avoid a second
  map racing for the same old names.

## 5. Validation

* All 26 new names: `grep -rE "(class|interface|enum|record) <Name>"` → 0
  declarations; identifier scan → 0 occurrences tree-wide (so no member/local
  shadowing), `src/main/resources` → 0 references.
* `old` names declared exactly once each in the package; all 26 rows resolve.
* Dry run: 26 rows, 0 skipped, 26 file renames, 766 files touched
  (dominated by `Lighting`/`LightingExtension` references).
* No mixins/no `net.minecraft.*`/no shaded library rows in this cluster.
