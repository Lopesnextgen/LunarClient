# Remaining class + package restructure

Generated from `tools/work/staging/decompiled` (5,740 files / the full remapped jar has 12,406 `com/moonsworth` classes) by `remaining-renames` pass.

## Scope

- Every class under `com/moonsworth/**` whose materialised simple name (top-level *or* any `$` segment) still matches `^[CHOIR]{8,}_?\d*$` was assigned a clean name.
- Classes already renamed by `mixin-renames.tsv` / `module-renames.tsv` are **not** renamed again, with one exception: **270 override entries** are included for classes whose entry in those maps still keeps an obfuscated inner segment (see "Merge precedence" below).
- `net/minecraft`, `org/**`, `com/google/**`, `kotlin/**`, `com/lunarclient/**` are untouched.
- Inner classes are renamed together with their outer class (`Outer$HORHRO...` -> `Outer$Data`).

## Naming rules used

1. `main`/`premain` entry points -> `Bootstrap` / `Launcher`.
2. `@Mixin(X.class)` -> `XMixin` (covers the leftovers the parallel mixin pass could not name).
3. readable supertype -> `<Supertype><Role>` where Role is inferred from the class body (`Renderer`, `Handler`, `Loader`, `Factory`, `Serializer`, `Impl`, `Base`, ...); when the supertype already ends in a role word the class is numbered instead (`MixinHelper2`), so no `ImplImpl` chains are produced. Interfaces get `<Supertype>Extension`, enums `<Supertype>Type`, records `<Supertype>Record`/`Data`, annotations `<Supertype>Annotation`.
4. otherwise a systematic `<PackageRole><index>` name (`Framework12`, `Horsestats3`, `MixinHelper17`, `Annotation4`, ...), using the containing package role.

## Package cleaning

- **646** packages were renamed (including descendants of renamed parents).
  - digit dropped where the base sibling is free: **9** (`mixin3` -> `mixin`, `pkg39` -> `pkg`, `gui2` -> `gui`, ...).
  - digit replaced by a readable suffix when the base sibling exists: **399** (`mixin16` -> `mixinShared`, `mixin11` -> `mixinExtra`, `rewindhandlers2` -> `rewindhandlersCore`, ...). The suffix is evidence based when the subtree contains readable class names, otherwise a readable fallback word (`Core`, `Extra`, `Misc`, `Shared`, ...).
  - duplicated segments collapsed: **73** (`.../mixin/mixin` -> `.../mixin`, `.../mixinShared/mixinShared` -> `.../mixinShared`).
- single-class packages whose new class name equals the package name were flattened into the parent: **21** (`bridge/freelook/Freelook` -> `bridge/Freelook`, `ichor/mixinOther/MixinOther` -> `ichor/MixinOther`, ...); candidates whose target name was already taken were skipped.
- no class name keeps the `_` clash suffix any more (the previous `X_` names are all renamed).

## Stats

| item | count |
|---|---:|
| `remaining-renames.tsv` entries | 9190 |
| top-level class renames | 5689 |
| nested class renames (obfuscated `$` segment) | 2469 |
| entries that only rename an inner class of a renamed outer | 1032 |
| override entries taking precedence over the parallel maps | 270 |
| entries skipped when merged in the current `apply_restructure.sh` order | 270 |
| parallel `mixin-renames.tsv` entries | 690 (still obfuscated target after pass: 20) |
| parallel `module-renames.tsv` entries | 764 (still obfuscated target after pass: 250) |

## Duplicate-target validation

Targets were checked twice:

1. within `remaining-renames.tsv`: **0 duplicate targets** (must be zero).
2. against the targets of `mixin-renames.tsv` + `module-renames.tsv`: **0 collisions** (must be zero).

Class/package clashes (`class FQCN == package FQCN`, illegal in Java source): **0** meaning every new name was also checked against the final package-path set.

## Merge precedence (important)

`270` of the entries are also present in `module-renames.tsv` (250) or `mixin-renames.tsv` (20). Those parallel entries keep the obfuscated class name (e.g. `ActionBar$HORHROIOIOICIRHIOCOICHHHIHCIIO`); the remaining pass maps them to the final name (`ActionBar$Data`). `apply_restructure.sh` merges with *first file wins* (`mixin, module, remaining`), so those entries are skipped and **270 obfuscated names survive**. Give the remaining map precedence, i.e. in `tools/apply_restructure.sh` use

```python
files = ['remaining-renames.tsv', 'mixin-renames.tsv', 'module-renames.tsv']
```

With that order the merged map has 10,374 entries and remapping the jar with `KinRemapper` leaves **zero** obfuscated class names (see verification below).

## Verification (bytecode level)

The merged map (remaining first) was applied to `lunar-all-final.jar` with the real `KinRemapper` and the resulting class list inspected:

| check | result |
|---|---|
| classes in jar in / out | 32,174 / 32,174 |
| `com/moonsworth` classes in / out | 12,406 / 12,406 |
| obfuscated (`[CHOIR]{8,}`) class names left | **0** |
| duplicate class entries | 0 |
| numeric package segments left under `com/moonsworth` | 0 |
| duplicated consecutive package segments left | 0 |
| flattenable single-class packages left (`class == package`) | 0 |
| class == package clashes | 0 |
| missing `com/moonsworth` class references | 0 |
| same checks with the *current* merge order (`mixin, module, remaining`) | 270 obfuscated names survive, everything else identical |

## 15 examples

| # | role | old internal name | new internal name |
|---:|---|---|---|
| 1 | digit dropped where the base sibling is free | `com/moonsworth/lunar/client/framework/feature/gui2/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/client/framework/feature/gui/Gui` |
| 2 | digit replaced by a readable suffix | `com/moonsworth/lunar/bridge/horsestats/mixin2/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/bridge/horsestats/mixinCore/HorsestatsType` |
| 3 | duplicated segments collapsed | `com/moonsworth/lunar/client/click/click/HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/client/click/Bridge7Iterator` |
| 4 | annotation type in the annotations package | `com/moonsworth/lunar/annotations/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/annotations/Annotation` |
| 5 | top-level network class (package role) | `com/moonsworth/lunar/network/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/network/Network` |
| 6 | mixin left over by the parallel mixin pass (now named after its @Mixin target) | `com/moonsworth/lunar/legacy/mixin/mixin/mixin2/mixin/RCIOICOHRIOIIRRRROCRHCIICRROHO` | `com/moonsworth/lunar/legacy/mixin/IBakedModelMixin` |
| 7 | module inner left obfuscated by module-renames (override entry) | `com/moonsworth/lunar/client/framework/feature/actionbar/ActionBar$HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/client/mod/hud/ActionBar$Data` |
| 8 | class implementing an obfuscated bridge interface | `com/moonsworth/lunar/network/mixin/CCROIHHHCOCHHOHORCIRHOCRROIOCI` | `com/moonsworth/lunar/network/mixin/ResponseBodyImpl` |
| 9 | class extending a renamed base class | `com/moonsworth/lunar/legacy/mixin/HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/legacy/mixin/StatFileWriter` |
| 10 | enum in a bridge package (supertype rule -> Type) | `com/moonsworth/lunar/bridge/horsestats/HRORICORIHHHRICRIRCIIOHCRIRRHI` | `com/moonsworth/lunar/bridge/horsestats/HorsestatsType` |
| 11 | record nested in a renamed module | `com/moonsworth/lunar/client/framework/feature/tntcountdown/TntCountdown$HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/client/mod/misc/TntCountdown$Data` |
| 12 | inner enum of a module | `com/moonsworth/lunar/client/framework/feature/totemcounter/TotemCounterHudChild$HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/client/mod/hud/TotemCounterHudChild$Type` |
| 13 | class/package clash `_` suffix removed (old name ended with `_`) | `com/moonsworth/lunar/network/HORHROIOIOICIRHIOCOICHHHIHCIIO_` | `com/moonsworth/lunar/network/Network2` |
| 14 | interface with a readable supertype (-> Extension) | `com/moonsworth/lunar/bridge/hitcolor/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/bridge/hitcolor/HitcolorExtension` |
| 15 | anonymous inner class follows its renamed outer | `com/moonsworth/lunar/bridge/horsestats/HCHHRHHCRIIORRRICOOCCOCHIRRRRR$1` | `com/moonsworth/lunar/bridge/horsestats/Horsestats6$1` |

## Reference clients inspected

Class/package conventions were cross-checked against the Badlion 2.0.0 source tree and five large reference clients (`Venus`, `VegaLine`, `Novoline b112321`, `Sigma Nextgen`, `Ellant`): `events/event/*Event`, `Event`/`EventType`, `util/*Util`, `*Handler`, `*Worker`, `*Manager`, `config/*Config`, `modules/<category>/<Name>`, `features/impl/<Name>`, mixins named `<Target>Mixin`. The systematic names produced here (`Framework12`, `MixinHelper7`, `Holograms4`, `Annotation3`, ...) follow the same role vocabulary.

