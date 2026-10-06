# Cluster 15 — `com.moonsworth.lunar.ichor` (32 classes)

All 32 paths in `cluster-15.txt` exist in `src/main/java`; nothing skipped as
missing. Map: `classes-15.tsv` (package / old / new / evidence).

This file replaces the previous `classes-15.*` content (the `legacy.wrapper`
map, applied in commit `37739947` — that map is preserved in git history and
`APPLIED.md`; cluster-15.txt now inventories the Ichor top-level package).

## What this package is

`com.moonsworth.lunar.ichor` is Lunar's **Ichor**: a Sponge-Mixin service plus
a multi-stage, remapping/version-aware class-transformation pipeline used by
Genesis/Lunar. Evidence from the runtime jars:

* `META-INF/services/com.moonsworth.lunar.ichor.<obf>` in `lunar.jar`,
  `genesis-…-all.jar`, `common-…-nomappings.jar` and the ReplayMod mixin jar
  registers the Ichor *module* implementations (the ServiceLoader interface is
  the one the pipeline logs as `IchorModule`).
* `IMixinServiceBootstrapImpl.getServiceClassName()` returns the real
  `com.moonsworth.lunar.ichor.mixin.service.IchorMixinService`; the
  `genesis` jar strings contain `IchorPipeline`, `IchorClassLoader`,
  `IchorClassInfo`, `IchorContainer.inject`, `IchorInjector#transform`,
  `"found IchorModule "`, `"Found … IchorLoaders."`,
  `"Going to default mixins stage for "`, `"Must provide at least one
  InjectStage"`, `"Trying to get ClassNode at an early stage"`.
* `MixinServiceAbstractImpl` implements `org.spongepowered.asm.service.MixinServiceAbstract`
  and initializes MixinExtras; the annotations under this package are Ichor's
  own hook vocabulary, *not* Sponge annotations (Sponge's `@Mixin`, `@Shadow`,
  `@Inject`, `@Implements` are referenced separately).

### Provenance used

Each row was matched to its obfuscated class through
`tools/work/mappings/normalize-renames.tsv` (pre-flatten path → current name)
and `tools/mappings-snapshot/restructure/remaining-renames.tsv`
(obf FQCN → restructured path). The runtime class files in
`genesis-0.1.0-SNAPSHOT-all.jar` / `lunar-libraries.jar` were decompiled with
CFR to cross-check *which* annotation each hook invoker binds (the runtime
build has different randomized obf names, so annotations were paired by member
structure, e.g. `smuggle`/`remove`/`shadow`, `min`/`max`/`inverted`/`onReturn`,
`Annotation12[] value()`), and to read the retained strings above.

## Naming scheme

The 13 `Annotation*` rows are the hooks/annotations of the pipeline. The
runtime invoker classes show one annotation per hook kind, invoked through a
generic `MixinHelper<A>` base (dropped from this partial tree):

| tree old | hook kind (from invoker / call sites) | new |
|---|---|---|
| `Annotation` (not in this cluster) | per-method transform `(ClassNode, MethodNode)` | `TransformMethod` (follow-up) |
| `Annotation4` | per-annotation transform (loops visible annotations) | `TransformAnnotation` |
| `Annotation5` | raw bytecode transform returning `byte[]` | `TransformBytecode` |
| `Annotation7` | per-class transform `(ClassNode[, URLClassLoader])` | `TransformClass` |
| `Annotation10` | resource/`MixinShared` transform by path regex | `TransformResource` |
| `Annotation11` | per-field transform `(ClassNode, FieldNode)` | `TransformField` |
| `Annotation8` | remapper provider (returns `MixinMisc2`) | `ProvideRemapper` |
| `Annotation3` | marker on hook-provider interfaces | `PipelineHook` |
| `Annotation6` | annotation-smuggling carrier | `Smuggle` |
| `Annotation13` | generated-stub marker (value `"Stub Constructor"`) | `Stub` |
| `Annotation9` + `Annotation12` | `@Implements` + element `@Interface` (Class + available version) | `Implements` + `Interface` |
| `Annotation2` | MC version range gate (value/min/max/inverted/onReturn) | `VersionGate` |
| `Annotation_2` | present/absent feature + version condition on mixins | `MixinCondition` |

`MixinHelper*` rows are of two kinds and are named accordingly:

* **invokers** of one hook annotation (subclasses of the dropped generic
  `MixinHelper<A>`): `MixinHelper2_3` → `BytecodeTransformInvoker`,
  `MixinHelper7` → `ResourceTransformInvoker`.
* **hook-provider interfaces** marked `@PipelineHook` contributing default
  hook methods: `MixinHelper_2`/`MixinHelper3_2`/`MixinHelper4_2` are the
  access-widening hooks (class/method/field, field-only for non-`@Shadow`
  fields, and only for external/obfuscated parents), `MixinHelper_3` hides a
  member's annotations inside `@Smuggle` at `PRE_META_MIXIN` and
  `MixinHelper2_2` restores them at `POST_META_MIXIN`.

Pipeline/module/mapping rows follow the strings recovered from the runtime:

* `Ichor3` → `IchorOptions` (pipeline paths, extra mixin sources, dump list,
  property map).
* `Ichor4` → `IchorStage` (name/mixin-runtime/bytes flags; implemented by the
  `Ichor4Type` phase enum `PRE_INIT…FINAL` and the external loader enum).
* `Ichor5` → `IchorLoader` (`loadIchor`; logged as “IchorLoaders”).
* `MixinInternal2` → `IchorInjector` (`transformClassNode`, `registerMixins`,
  `setMixinDecorations`, `gotoDefaultPhase`, `audit`, …; the pipeline log is
  literally `IchorInjector#transform: On stage`).
* `MixinInternal3` → `MixinRegistrationTask` (one callback per stage; the
  implementors call `IchorInjector.registerMixins`).
* mapping stack: `InheritanceProvider2` → `RemappingInheritanceProvider`,
  `MixinMisc2` → `ClassNameMapper`, `MixinMisc4` → `MappingSetUtils`,
  `MixinMisc5` → `InheritanceMapProvider`, `RemapperIterator2` →
  `InheritanceRemapper`, `MixinLegacy2` → `ClassHierarchyFactory`,
  `MixinExtra2` → `MixinClassSource`.

`ClassInfoIterator` (not in this cluster) already prints
`"IchorClassInfo{name='…"`, and the top-level `ClassWriter` is named
`LoaderAwareClassWriter` by a runtime string — useful cross-checks for the
clusters owning those rows.

## Applier notes (important)

Dry run with `tools/apply_class_renames.py --map tools/renames/classes-15.tsv`
(observed: `32 rows; applied=16 skipped=16 files_touched=67 files_renamed=16`,
no `--allow-collisions`):

* **16 rows apply cleanly** (old simple name declared only here):
  `Annotation5`–`Annotation13`, `Ichor3`–`Ichor5`, `InheritanceProvider2`,
  `MixinInternal2`, `MixinInternal3`, `RemapperIterator2`.
* **16 rows are skipped as old-name collisions** (the simple name is declared
  in other packages too — `forge/lib` bundled libraries, `genesis`, `bridge`,
  `client/**`): `Annotation2`, `Annotation3`, `Annotation4`, `Annotation_2`,
  `MixinExtra2`, `MixinHelper2_2`, `MixinHelper2_3`, `MixinHelper3_2`,
  `MixinHelper4_2`, `MixinHelper7`, `MixinHelper_2`, `MixinHelper_3`,
  `MixinLegacy2`, `MixinMisc2`, `MixinMisc4`, `MixinMisc5`.

**Do not apply with `--allow-collisions`** for the 16 collision rows: the
applier substitutes the simple name tree-wide and would rewrite unrelated
`genesis`/`forge/lib`/`client` classes. They need either (a) the sibling
packages renamed first, or (b) a package-scoped rewrite of
`src/main/java/com/moonsworth/lunar/ichor/**` plus the explicit importers
(`framework/mixin/Ichor2Handler`, `forge/Ichor2Handler_2`,
`forge/Ichor2Iterator_2`, `client/lotusfish/Ichor2*`, `replaymod/forge/ichor/Ichor2Handler2`).
All 16 new names are free tree-wide and do not collide with the `new` names
already proposed in `classes-07/09/10/12/16/23/26.tsv`.
