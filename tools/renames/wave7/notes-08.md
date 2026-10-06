# Wave 7 — `com.moonsworth.lunar.ichor` leftovers (cluster-08, 45 rows)

Source: `tools/renames/cluster-08.txt` (45 rows). **All 45 paths exist**, no
`net.minecraft.*` rows, no shaded third-party code: `ichor` is Lunar's own
annotation-driven class-transformation / remapping pipeline. It *consumes*
Sponge Mixin (`org.spongepowered.asm.**` stays a shaded dependency) and Lorenz/
Bombe (`org.cadixdev.**`), it does not embed them.

Map: `tools/renames/wave7/classes-08.tsv` (45 renames, 0 skips).

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave7/classes-08.tsv
[aware-renames] 45 rows (0 nested); 9392 java files
[aware-renames] rows=45 skipped=0 files_touched=516 files_renamed=32 mode=dry-run
```

`files_renamed=32` = the 13 annotation files + the 19 top-level class files;
the 13 nested rows rename declarations inside their owners (owner files are not
renamed), and every row found references (no `WARN ... no references found`).

## 1. What this cluster is

`cluster-08` is the *rescue generation* of the ichor core: every one of the 45
placeholders is the pre-rename copy of a class that already carries an applied
name in the same package (the earlier `classes-15` / `classes-bridge*` /
`classes-finalichor` maps renamed one copy, the rescue sweeps `47dd3dd48` /
`7adc91305` restored the other). Both generations are referenced from different
files, so this is a **merge of two decompile generations**, not dead code: e.g.
`Annotation2` has 316 import-aware users vs 343 for its applied twin
`VersionGate`, and `Ichor4` 49 vs `IchorStage` 37.

Because the applier refuses to hand an already-declared simple name to a second
declaration (`SKIP ... new name already declared`), every row here follows the
`classes-36` / `classes-annotations` convention: **the rescued copy takes a
fresh, descriptive synonym** (or the real name when the placeholder is the only
carrier of it), and the applied twin keeps its name. §3 lists the per-pair merge
intel the dedupe pass needs.

Two real names were recovered from the framework's own string literals, so the
placeholders that carry them were named accordingly (ground rule 6, real names
win):

* `Annotation9` -> **`DynamicImplements`** — `MixinInternalTask.java:319/360`:
  `"Invalid @DynamicImplements annotation attached to "` /
  `"make sure your declaration is of the form @DynamicImplements({@Interface(...), ...})"`.
* `Annotation2` -> **`Available`** — the same literal shows the member form
  `available = @Available(...)`; `@Available` is exactly the annotation whose
  members (`value`/`min`/`max`/`inverted`/`onReturn`) are evaluated by
  `loader/mixin/VersionGateEvaluator.method1`. The applied `VersionGate` was a
  wave-15 guess; after dedupe the survivor should be called `Available` (see §3).

## 2. Renames (45)

`twin` = the applied class this row duplicates; `users` are import-aware
tree-wide users of the simple name (own file excluded).

| # | old | new | twin (users) | evidence (short) |
|---|---|---|---|---|
| 1 | `Annotation2` | `Available` | `VersionGate` (316/343) | version availability `@interface` (`value`/`min`/`max`/`inverted`/`onReturn`); real name in the `MixinInternalTask` error literal |
| 2 | `Annotation3` | `PipelineHookMarker` | `PipelineHook` (8/5) | empty TYPE marker; `AutoCloseableIterator:62` selects hook roots via `getAnnotation(...)` |
| 3 | `Annotation4` | `TransformAnnotations` | `TransformAnnotation` (2/0) | class-annotation hook annotation, registered with `AnnotationTransformInvoker` |
| 4 | `Annotation5` | `TransformClassBytes` | `TransformBytecode` (5/3) | byte[] replacement hook, registered with `MixinHelper2_3` |
| 5 | `Annotation6` | `SmuggledMember` | `Smuggle` (2/2) | `smuggle()`/`remove()`/`shadow()`; read by the hide/restore hooks |
| 6 | `Annotation7` | `TransformClassNode` | `TransformClass` (14/11) | `ClassNode` hook, registered with `ClassTransformInvoker` |
| 7 | `Annotation8` | `ProvideMappings` | `ProvideRemapper` (14/1) | 6 boolean flags + `optional`, registered with `RemapTransformInvoker` |
| 8 | `Annotation9` | `DynamicImplements` | `Implements` (1/0) | interface-adding annotation; real name in the `MixinInternalTask` error literal |
| 9 | `Annotation10` | `TransformResources` | `TransformResource` (4/2) | resource-path regex hook, registered with `MixinHelper7` |
| 10 | `Annotation11` | `TransformFields` | `TransformField` (6/3) | field hook, registered with `FieldTransformInvoker` |
| 11 | `Annotation12` | `ImplementedInterface` | `Interface` (1/2) | element of `Annotation9` (`Class<?> value` + `available`) |
| 12 | `Annotation13` | `StubMarker` | `Stub` (3/4) | marks the synthesized stub `<init>` ("Stub Constructor") |
| 13 | `Annotation_2` | `MixinConditions` | `MixinCondition` (11/16) | `present()`/`absent()`/`available()` mixin gate |
| 14 | `ClassNodeRemapper$Data2` | `AnnotationNodeRemapTask` | `AnnotationRemapTask` | annotation remap task (desc + kotlin Metadata values) |
| 15 | `ClassNodeRemapper$Data3` | `MethodNodeRemapTask` | `MethodRemapTask` | method remap task (name/desc/signature/instructions/locals) |
| 16 | `ClassNodeRemapper$Data4` | `ModuleNodeRemapTask` | `ModuleRemapTask` | module remap task |
| 17 | `ClassNodeRemapper$Data5` | `RecordComponentNodeRemapTask` | `RecordComponentRemapTask` | record-component remap task |
| 18 | `Ichor2Iterator$Data2` | `ClassAccessSpec` | `ClassAccessRule` | per-class access-widener rule (widen/forcePublic) |
| 19 | `Ichor2Iterator$Data4` | `FieldAccessSpec` | `FieldAccessRule` | per-field rule (widen/mutable) |
| 20 | `Ichor2Iterator$Data5` | `MethodAccessSpec` | `MethodAccessRule` | per-method rule (widen/forcePublic) |
| 21 | `IchorTransformer$Data2` | `ResourceCacheEntry` | `ResourceCacheKey` | (stage, className) key of the loaded-mixin-resource cache |
| 22 | `IchorTransformer$Data3` | `IchorTransformContext` | `ClassTransformContext` (20/6) | per-class context handed to every hook invoker |
| 23 | `MixinTargetRemapper$Data2` | `HierarchyCacheValue` | `HierarchyCacheEntry` | (superName, List<ClassMapping>) of the 10s cache |
| 24 | `MixinTargetRemapper$Type2` | `MemberSelector` | `MemberKind` | ALL/FIELD/METHOD member filter |
| 25 | `InnerClassRenameInfo$Type2` | `InnerClassType` | `InnerClassKind` | LOCAL/ANONYMOUS/INNER of the `.nest` parser |
| 26 | `RemapTransformInvoker$Data31` | `ThreadLoaderCacheKey` | `ThreadClassLoaderKey` | (Thread, URLClassLoader) cache key |
| 27 | `Ichor3` | `IchorPipelineOptions` | `IchorOptions` (2/0) | pipeline options (classpath/overrides/dump lists/properties) |
| 28 | `Ichor4` | `IchorStageDescriptor` | `IchorStage` (49/37) | stage descriptor (`name`/`hasMixinRuntime`/...); implemented by `Ichor4Type` |
| 29 | `Ichor5` | `IchorStageLoader` | `IchorLoader` (18/8) | `@FunctionalInterface void loadIchor(IchorTransformer)` |
| 30 | `InheritanceProvider2` | `FallbackInheritanceProvider` | `RemappingInheritanceProvider` (1/0) | retries via `getFullDeobfuscatedName()`, wraps in `ClassInfoIterator.lazy()` |
| 31 | `MixinExtra2` | `MixinClassEntry` | `MixinClassSource` (4/2) | (stage, className, bytes) located mixin class |
| 32 | `MixinHelper2_2` | `SmuggleRestoreProcessor` | `SmuggleRestoreHook` (1/1) | POST_META_MIXIN smuggle-restore hook (`Ichor2Handler6`) |
| 33 | `MixinHelper2_3` | `ClassBytesTransformInvoker` | `BytecodeTransformInvoker` (1/0) | `@TransformBytecode` invoker, registered for `Annotation5` |
| 34 | `MixinHelper3_2` | `FieldAccessWidener` | `FieldAccessWideningHook` (4/3) | `@TransformField` widening default (`Ichor2Handler2/22/24/3`) |
| 35 | `MixinHelper4_2` | `ExternalAccessWidener` | `ExternalAccessWideningHook` (0/0) | widens members of obfuscated/`net.minecraft` superclasses |
| 36 | `MixinHelper7` | `ResourceHookInvoker` | `ResourceTransformInvoker` (1/0) | `@TransformResource` invoker, registered for `Annotation10` |
| 37 | `MixinHelper_2` | `ClassAccessWidener` | `AccessWideningHook` (2/2) | class/member widening hooks driven by the config flag |
| 38 | `MixinHelper_3` | `SmuggleHideProcessor` | `SmuggleHideHook` (1/1) | PRE_META_MIXIN smuggle-hide hook (`Ichor2Handler5`) |
| 39 | `MixinInternal2` | `IchorMixinProxy` | `IchorInjector` (8/8) | MixinProxy SPI stored by `URLClassLoader.method19`, fed `MixinProxyImpl` |
| 40 | `MixinInternal3` | `MixinStageRegistrar` | `MixinRegistrationTask` (4/2) | per-stage `(stage, injector, loader)` registration callback |
| 41 | `MixinLegacy2` | `HierarchyNodeProvider` | `ClassHierarchyFactory` (2/2) | `@FunctionalInterface ... provide(String)` for the hierarchy |
| 42 | `MixinMisc2` | `ClassNameResolver` | `ClassNameMapper` (6/2) | `remap`/`unmap` SPI |
| 43 | `MixinMisc4` | `MappingSetTools` | `MappingSetUtils` (10/5) | static Lorenz toolbox (reverse/remap/copy/jar-remap) |
| 44 | `MixinMisc5` | `SuperclassMapProvider` | `InheritanceMapProvider` (2/0) | raw→mapped superclass maps filled from class entries |
| 45 | `RemapperIterator2` | `InheritanceMappingResolver` | `InheritanceRemapper` (6/0) | `MappingSetRemapper` completing mappings from inheritance |

All 45 new simple names were grepped before writing: 0 declarations and 0
`.java`/`.cfg`/`.json` stems tree-wide (script over `src/main/java` +
`src/main/resources`), and the applier reports 0 collisions / 0 skips.

## 3. Dedupe intel (one twin per row)

The dedupe rule from `classes-40.md` §2 / `classes-54.md` §3a applies: keep the
copy the live call sites use, delete the other, then apply the canonical row
(`old -> twin`) from the "after-dedupe" list. Direction for each pair:

* **Keep the rescued copy, delete the applied twin** (applied twin is dead /
  lower-use): `Annotation4`->`TransformAnnotation` (0 users),
  `Annotation9`->`Implements` (0), `Ichor3`->`IchorOptions` (0),
  `InheritanceProvider2`->`RemappingInheritanceProvider` (0),
  `MixinHelper2_3`->`BytecodeTransformInvoker` (0),
  `MixinHelper7`->`ResourceTransformInvoker` (0),
  `MixinMisc5`->`InheritanceMapProvider` (0),
  `RemapperIterator2`->`InheritanceRemapper` (0; the twin still reads the
  undeclared raw fields `RIHROHCIRORRICORIORCRIOIOORRII` /
  `HCICRHCHCHHCICOCORCCOHHHOOCHIH`, so it cannot compile),
  `MixinHelper4_2`->`ExternalAccessWideningHook` (both 0: delete one).
* **For the annotations prefer the real/attested names**: keep `Available` and
  `DynamicImplements`, delete `VersionGate` / `Implements` (the two literals in
  `MixinInternalTask` are the only surviving real names of the annotation set).
* **Keep the applied twin, delete the rescued copy** (applied side carries
  `@KeepName` or more users): `Annotation_2`->`MixinCondition` (16 vs 11),
  `Annotation13`->`Stub` (4 vs 3), `Annotation3`->`PipelineHook` (the five hook
  interfaces are the applied side), `Annotation12`->`Interface`.
* **Mixed generations, both live** — these need a member-level check before the
  merge (the placeholder side has obfuscated `methodN` members, the applied side
  partially renamed ones): `Annotation2`/`VersionGate`, `Annotation5`/
  `TransformBytecode`, `Annotation6`/`Smuggle`, `Annotation7`/`TransformClass`,
  `Annotation8`/`ProvideRemapper`, `Annotation10`/`TransformResource`,
  `Annotation11`/`TransformField`, `Ichor4`/`IchorStage`, `Ichor5`/`IchorLoader`,
  `MixinExtra2`/`MixinClassSource`, `MixinHelper2_2`/`SmuggleRestoreHook`,
  `MixinHelper3_2`/`FieldAccessWideningHook`, `MixinHelper_2`/`AccessWideningHook`,
  `MixinHelper_3`/`SmuggleHideHook`, `MixinInternal2`/`IchorInjector` (8/8),
  `MixinInternal3`/`MixinRegistrationTask`, `MixinLegacy2`/`ClassHierarchyFactory`,
  `MixinMisc2`/`ClassNameMapper`, `MixinMisc4`/`MappingSetUtils`.
* **Nested twins**: the owner files use their inline `Data*`/`Type2` copies and
  the extracted top-level twins are only used from one or two files, so the
  merge is mechanical (delete one declaration, rename the survivor). Two nested
  classes are load-bearing: `IchorTransformer$Data3` (20 files) vs
  `ClassTransformContext` (6) and `InnerClassRenameInfo$Type2` (2 files).

Ready-to-copy canonical rows for after the duplicates are resolved (they are
refused today because the target name is still declared):

```
com.moonsworth.lunar.ichor	Annotation2	Available	rescued copy; delete VersionGate first (real name from MixinInternalTask literal)
com.moonsworth.lunar.ichor	Annotation3	PipelineHook	rescued copy of the applied hook-root marker
com.moonsworth.lunar.ichor	Annotation4	TransformAnnotation	rescued copy of the applied class-annotation hook
com.moonsworth.lunar.ichor	Annotation5	TransformBytecode	rescued copy of the applied bytecode hook
com.moonsworth.lunar.ichor	Annotation6	Smuggle	rescued copy of the applied smuggle carrier
com.moonsworth.lunar.ichor	Annotation7	TransformClass	rescued copy of the applied ClassNode hook
com.moonsworth.lunar.ichor	Annotation8	ProvideRemapper	rescued copy of the applied remapper-provider hook
com.moonsworth.lunar.ichor	Annotation9	DynamicImplements	rescued copy; delete the dead Implements first
com.moonsworth.lunar.ichor	Annotation10	TransformResource	rescued copy of the applied resource hook
com.moonsworth.lunar.ichor	Annotation11	TransformField	rescued copy of the applied field hook
com.moonsworth.lunar.ichor	Annotation12	Interface	rescued copy of the applied @Interface element
com.moonsworth.lunar.ichor	Annotation13	Stub	rescued copy of the applied stub marker
com.moonsworth.lunar.ichor	Annotation_2	MixinCondition	rescued copy of the applied mixin gate
com.moonsworth.lunar.ichor	ClassNodeRemapper$Data2	AnnotationRemapTask	rescued nested twin of the extracted task
com.moonsworth.lunar.ichor	ClassNodeRemapper$Data3	MethodRemapTask	rescued nested twin of the extracted task
com.moonsworth.lunar.ichor	ClassNodeRemapper$Data4	ModuleRemapTask	rescued nested twin of the extracted task
com.moonsworth.lunar.ichor	ClassNodeRemapper$Data5	RecordComponentRemapTask	rescued nested twin of the extracted task
com.moonsworth.lunar.ichor	Ichor2Iterator$Data2	ClassAccessRule	rescued nested twin of the rule
com.moonsworth.lunar.ichor	Ichor2Iterator$Data4	FieldAccessRule	rescued nested twin of the rule
com.moonsworth.lunar.ichor	Ichor2Iterator$Data5	MethodAccessRule	rescued nested twin of the rule
com.moonsworth.lunar.ichor	IchorTransformer$Data2	ResourceCacheKey	rescued nested twin of the key
com.moonsworth.lunar.ichor	IchorTransformer$Data3	ClassTransformContext	rescued nested twin of the context
com.moonsworth.lunar.ichor	MixinTargetRemapper$Data2	HierarchyCacheEntry	rescued nested twin of the cache entry
com.moonsworth.lunar.ichor	MixinTargetRemapper$Type2	MemberKind	rescued nested twin of the member enum
com.moonsworth.lunar.ichor	InnerClassRenameInfo$Type2	InnerClassKind	rescued nested twin of the kind enum
com.moonsworth.lunar.ichor	RemapTransformInvoker$Data31	ThreadClassLoaderKey	rescued nested twin of the cache key
com.moonsworth.lunar.ichor	Ichor3	IchorOptions	rescued copy of the pipeline options
com.moonsworth.lunar.ichor	Ichor4	IchorStage	rescued copy of the stage descriptor
com.moonsworth.lunar.ichor	Ichor5	IchorLoader	rescued copy of the stage loader
com.moonsworth.lunar.ichor	InheritanceProvider2	RemappingInheritanceProvider	rescued copy of the fallback inheritance provider
com.moonsworth.lunar.ichor	MixinExtra2	MixinClassSource	rescued copy of the located mixin class
com.moonsworth.lunar.ichor	MixinHelper2_2	SmuggleRestoreHook	rescued copy of the smuggle-restore hook
com.moonsworth.lunar.ichor	MixinHelper2_3	BytecodeTransformInvoker	rescued copy of the bytecode invoker
com.moonsworth.lunar.ichor	MixinHelper3_2	FieldAccessWideningHook	rescued copy of the field widening hook
com.moonsworth.lunar.ichor	MixinHelper4_2	ExternalAccessWideningHook	rescued copy of the external widening hook
com.moonsworth.lunar.ichor	MixinHelper7	ResourceTransformInvoker	rescued copy of the resource invoker
com.moonsworth.lunar.ichor	MixinHelper_2	AccessWideningHook	rescued copy of the widening hook
com.moonsworth.lunar.ichor	MixinHelper_3	SmuggleHideHook	rescued copy of the smuggle-hide hook
com.moonsworth.lunar.ichor	MixinInternal2	IchorInjector	rescued copy of the MixinProxy SPI (real upstream name: MixinProxy)
com.moonsworth.lunar.ichor	MixinInternal3	MixinRegistrationTask	rescued copy of the stage registration callback
com.moonsworth.lunar.ichor	MixinLegacy2	ClassHierarchyFactory	rescued copy of the hierarchy node factory
com.moonsworth.lunar.ichor	MixinMisc2	ClassNameMapper	rescued copy of the remap/unmap SPI
com.moonsworth.lunar.ichor	MixinMisc4	MappingSetUtils	rescued copy of the Lorenz toolbox
com.moonsworth.lunar.ichor	MixinMisc5	InheritanceMapProvider	rescued copy of the superclass-map provider
com.moonsworth.lunar.ichor	RemapperIterator2	InheritanceRemapper	rescued copy of the inheritance-completing remapper
```

## 4. Caveats / follow-ups outside this cluster

1. **`MixinProxy` is the upstream real name of `IchorInjector`/`MixinInternal2`**
   (`URLClassLoader.method19(MixinInternal2)` is fed
   `org.spongepowered.asm.mixin.transformer.MixinProxyImpl` from
   `IchorMixinService.init`). `MixinProxy` is currently not a declaration in the
   tree (it lives in the shaded Mixin jar), so a later wave may rename the
   surviving interface to `MixinProxy` and keep `MixinProxyImpl` in sync.
2. **Names suggested in `wave5/notes-53.md` §4.3 are still pending** for the
   owners that have no trailing digit and therefore no cluster row:
   `AutoCloseableIterator` -> `IchorContainer`, `Ichor2Iterator`
   -> `AccessWideningMapHook`, `Ichor2Handler4` -> `InitialRemapHook`.
3. **`RemapperIterator` (restored placeholder)** still exists next to
   `MappingSetRemapper` (its applied twin) and next to this cluster's
   `RemapperIterator2`; it is out of cluster-08 only because its name has no
   trailing digit, but it is the same duplicate pattern.
4. `MixinHelper4_2`/`ExternalAccessWideningHook` are both dead; naming the
   placeholder keeps the pair distinguishable until the dedupe deletes one.
5. No member renames were attempted here; the placeholder generation keeps
   obfuscated `methodN`/`fieldN` members, so the dedupe merge should be done
   with `tools/member_align.py` after the class merge, as usual.
