# members-settings.tsv — settings / registration API (research notes)

Scope: `com.moonsworth.lunar.client.config.option.RootSettingsBuilder` and its
supertypes (`lighting.CategorizedSettingsBuilder` → `lighting.Lighting_5`),
the implementation class `lighting.LightingExtension2_2`, and the section
handle the registration lambdas receive (`lighting.SettingsParent` / its
concrete subclasses `RootSettingsBuilder.Data`, `SettingsTreeBuilder.Data`,
`RootSettingsAssembler.Data`, `SettingsTreeAssembler.Data`). The ModDetails
builder rows at the end are adjacent scope (the `method20()` example) — drop
them if another batch owns `client/framework/ModDetails`.

## Ground truth used

* `libs/multiver-full/lunar.jar` contains the **original** classes; they were
  decompiled with CFR and cross-checked with `javap`, so every "obf" token in
  the evidence column is the *real* member name in the shipped jar:
  * original interface `.../HHCCIRH.../IRCIIHH.../CRRRICCR...` =
    `CategorizedSettingsBuilder` (section/category API + `buildMap`),
  * original `.../IRCIIHH.../RCIRRO...` = `LightingExtension2_2` (impl),
  * original `.../IRCIIHH.../RRCRRC...` = `SettingsParent` (section handle),
  * original `.../IRCIIHH.../RIOOCH...` = `Lighting_5` (SettingsBuilder),
  * original `.../IRCIIHH.../RCIRRO...` (the class that carries
    "Category icons must be of type 'solid'") = the concrete section builder.
* Call-site verification: the original bytecode of `AttackIndicator` and
  `MinimapMod` was decompiled and compared with the tree call sites.
  `attackIndicatorPlaySoundCustom`/`minimapOpenSaveFolder` unique strings were
  used to locate the original classes.

## Vocabulary (canonical names)

| concept | canonical name | declared by |
|---|---|---|
| builder-level labelled/typed category | `section(...)` | `CategorizedSettingsBuilder.method1/2/3/4/5` |
| section divider | `separator()` | `LightingExtension2_2.method13()` |
| text label node | `label(String)` | `Lighting_5.method15`, `LightingExtension2_2.method12(String)` |
| nested group under an option | `group(option, consumer)` | `Lighting_5.method2/3/4/5`, `LightingExtension2_2.method5/6`, `SettingsParent.method7/8` |
| add options (as a new section at the head) | `addOptions(options...)` | `Lighting_5.method6/7`, `LightingExtension2_2.method8`, `SettingsParent.method9` |
| append options to the current section | `addSectionOptions(options...)` | `SettingsParent.method2(LE...)` (alias `ICRHORII...`) |
| insert a section before/after an option | `addOptionsBefore/After` | `Lighting_5.method8/9`, `method10/11`, `LightingExtension2_2.method11/12` |
| append a section at the tail | `appendOptions(options...)` | `Lighting_5.method12/13/14`, `SettingsParent.method13` |
| option visibility / hide condition | `visibleWhen(BS)` / `hiddenWhen(BS)` | `ThreadModuleDump43Extension.method3/4`, `SettingsSectionBuilder.method3/4` |
| section without title | `flat(boolean)` | `ThreadModuleDump43Extension.method5`, `SettingsSectionBuilder.method5` |
| version/module gates | `showOnlyOnVersion`, `showExceptOnVersion`, `showOnlyWithModule`, `showExceptWithModule` | `SettingsSectionImpl` / `ThreadModuleDump43Extension22` |
| remove option tree-wide | `removeOption(LE)` | `CategorizedSettingsBuilder.method6`, `LightingExtension2_2.method6` |
| tree build | `buildMap()` | `CategorizedSettingsBuilder.method12`, `LightingExtension2_2.method12()` |
| baker factory | `createBaker(String, boolean)` | `OptionBakerFactory.method1`, `Lighting2_2.method1` |
| registrant entry point | `registerOptions(builder)` | `SettingsRegistrant.method1` (config.option), `Lighting_3.method1` (lighting) |
| ModDetails fluent builder | `details()`, `categories(...)`, `aliases(...)`, `originalAuthors(...)`, `instance(...)` | `ModDetails` / `ModDetails.Data` |

## The `arg1x` receiver

* `RootSettingsBuilder.method7(RewindhandlersType, Consumer)` at the call sites
  is the tree's **placeholder for the RewindhandlersType overload** of
  `section(...)`; in the original jar that is the *default* interface method
  `RCIRRO... (RewindhandlersType, Consumer)` = current
  `CategorizedSettingsBuilder.method4` (the tree's own declaration is named
  `method4`). The callback parameter type is `Parent`, and for
  `RootSettingsBuilder` that is `RootSettingsBuilder.Data`, which
  `extends com.moonsworth.lunar.client.lighting.SettingsParent`. So the class
  the lambdas actually receive is **`lighting.SettingsParent`** (the twin
  `config.option` package has no `SettingsParent` class; only
  `SettingsParentFactory`).
* `SettingsParent` declares the whole `arg1x` surface used by mods:
  `addOptions` (`method9`), `addSectionOptions` (`method2`),
  `group` (`method7`, the `HORHROIOIOICIRHIOCOICHHHIHCIIO` call sites),
  plus `visibleWhen`/`hiddenWhen`/`flat` forwarders.

## Call-site alias tokens (still obfuscated in the tree)

The mod classes were decompiled from a *different* jar revision than the API
classes, so several call-site tokens never got normalised. Verified mappings:

| token | receiver | maps to | evidence |
|---|---|---|---|
| `HORHROIOIOICIRHIOCOICHHHIHCIIO` | builder (`var1.HORH(RewindhandlersType, ...)`) | `section(RewindhandlersType, Consumer)` | TypedHudRenderer:330/365, Coordinates, FogLoader2 |
| `HORHROIOIOICIRHIOCOICHHHIHCIIO` | section handle (`arg1x.HORH(option, ...)`) | `group(option, consumer)` | MinimapMod `borderOptions`, PvpInfoStatHud, TypedHudRenderer |
| `ICRHORIIHOHROHOHOCOOHOOCOORRHO` | section handle (`arg1x.ICRH(LE[])` / `(OptionProvider[])`) | `addSectionOptions(LE...)` / `addOptions` | 87 files (MinimapMod, CoordinatesBiomeChildMod, SkyblockVisitorBazaarHelper) |
| `OHOOORICRHIIIIRHCICICOCHROICRC` | builder | `section(String, Consumer)` | PvpInfoProjectileChild.method3, ArmorStatusElementChildMod |
| `method7` | builder | ambiguous: `(RewindhandlersType, Consumer)` → `section`; `(option, Consumer)` → `group` | AttackIndicator:123 vs PvpInfoStatHud:81 / Hitbox:93 |
| `method2` on the handle | section handle | `addSectionOptions(LE...)` (the `BooleanSupplier` overload is `visibleWhen`) | SkyblockInventoryButtons:216; SettingsParent.method2(LE...)/(BS) both exist |
| `ROOOCICROROOHCIRRHHHCRCOROOHHH` | section handle | unresolved — returns a section builder (`arg1x.ROOOC...().method3(...)`, `...RHIHII...`) | Armorstatus:206, PotionEffects:268, Lighting3Loader:105 |
| `IIOHHROCRCCRCRIHCOHIHRHCOOIHRR(int)` | section result | unresolved version gate, most likely `showOnlyOnVersion(int...)` (`method6(int...)`) | Fog:158/165, PvpInfoProjectileChild:50 |
| `CIOHHCORHRCCRICCCORIHCRHCCCRRR` | section result | `hiddenWhen` (only in the original bytecode of MinimapMod, 0 tree hits) | CFR of original MinimapMod |

## Application hazards (read before applying)

1. `tools/apply_member_renames.py` renames **declarations by name only** inside
   the owner file, and `MemResolver` keeps one row per `(owner, oldName)` —
   *the last row wins*. The `SPLIT:` notes mark owners whose file declares two
   different members under the same placeholder name. These need a
   signature-aware pass (or manual splitting), otherwise the wrong overload is
   renamed:
   * `LightingExtension2_2`: `method1`, `method5`, `method6`, `method7`,
     `method11`, `method12` (see rows).
   * `SettingsParent`: `method2`, `method13`.
   * `SettingsSectionImpl` / `ThreadModuleDump43Extension22`: `method4`,
     `method5`, `method6`.
   * `ModDetails.Data` vs `ModDetails`: safe (different owners).
2. Access-side renaming (`receiver.old`) only fires when the receiver type can
   be inferred by `fix_obf_tokens.local_type`/`field_type`/`qualify`.
   **Lambda parameters (`arg1x -> ...`, `arg1xx -> ...`) cannot be resolved**,
   so all `arg1x.HORH(...)` / `arg1x.ICRH(...)` style call sites need a
   dedicated pass (the rows are still required for declaration renaming and
   for typed receivers).
3. `method7(option, Consumer)` and `method7(RewindhandlersType, Consumer)`
   share one receiver type at several call sites; a name-only renamer cannot
   tell them apart. Split by first-argument type.
4. Rows whose owner ends in `$Data` (`ModDetails$Data`) address a **nested**
   class: `apply_member_renames.py` only resolves owners to `<TopLevel>.java`
   files, so it reports `!! no source for ...` and skips them. They are kept
   for a signature-aware/manual pass; do not merge them onto the top-level
   owner (the getter names would collide).
5. The section mutator `SettingsSectionBuilder.method1(LightingExtension...)`
   is **unsatisfied in the tree**: both section impls lost the mutator to the
   `method1()` list getter (name collision after normalisation). The builder's
   `group`/`addSection` code still calls `node.method1(option)`. A repair pass
   must restore the mutator (suggested names: `addOptions`, getters:
   `getOptions`/`getChildren`) before applying the `addOptions` rows to the
   section impls.
6. Two parallel hierarchies exist and must be kept name-identical:
   * mod-facing: `config.option.RootSettingsBuilder` / `SettingsTreeBuilder`
     (extend `lighting.LightingExtension2_2`), consumed by mods via
     `void method2(RootSettingsBuilder)`.
   * framework-facing: `lighting.RootSettingsAssembler` /
     `SettingsTreeAssembler`, built by `Nameplate6.method7()` and passed to
     `Framework7Extension.method2(RootSettingsAssembler)` / `Lighting_3`.
   The mods' `method2(RootSettingsBuilder)` overrides do **not** implement
   `Framework7Extension.method2(RootSettingsAssembler)` today, so registration
   is broken for either set of classes; the parent will probably merge the
   twins. Rows are provided for both so names stay coherent.
6. `config.option.SettingsBuilder` and `SettingsSectionBuilder` are duplicates
   of `lighting.Lighting_5` and the section contract from a second jar. Their
   rows use the same names; no in-tree implementors exist.

## Deliberately not renamed / left ambiguous

* `Lighting_5.method17()` / `method21()` (and `SettingsParent.method19/20`
  forwarders) — original semantics not recovered (probably "current node" /
  "root node"); no call sites in the mod cluster.
* `LightingExtension2_2.method22/23/24` (internal prune/build helpers).
* `ROOOCICROROOHCIRRHHHCRCOROOHHH` (section handle) and
  `IIOHHROCRCCRCRIHCOHIHRHCOOIHRR` (section builder) alias tokens — most
  likely `SettingsParent.method20()` and `showOnlyOnVersion(int...)`, but not
  proven from the bytecode; do not guess-rename.
* `Framework7Extension.method2(RootSettingsAssembler)` / the 180+ mod
  `method2(RootSettingsBuilder)` overrides — registration entry point, but
  out of scope (would touch every mod); suggested name if the parent wants it:
  `registerOptions`.
