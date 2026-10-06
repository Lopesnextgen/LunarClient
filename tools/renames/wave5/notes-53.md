# Cluster 53 — `com.moonsworth.lunar.ichor` (45 rows)

Source: `tools/renames/cluster-53.txt` (45 rows = the first 45 names of the
ichor inventory, cut after `MixinHelper5`; the rest is cluster 54). **All 45
paths exist**; map: `tools/renames/wave5/classes-53.tsv` — **12 rows written,
33 rows skipped** (every skip is a rescued duplicate of a class that already
carries a name in the same package).

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-53.tsv
[aware-renames] 12 rows (0 nested); 12493 java files
  com.moonsworth.lunar.ichor: AutoCloseableIterator2 -> IchorTransformer (116 hits)
  com.moonsworth.lunar.ichor: FieldTypeProvider2 -> NestClassNameMapper (13 hits)
  com.moonsworth.lunar.ichor: Ichor2 -> IchorInjection (66 hits)
  com.moonsworth.lunar.ichor: Ichor6 -> IchorModule (38 hits)
  com.moonsworth.lunar.ichor: Ichor7 -> IchorPipeline (210 hits)
  com.moonsworth.lunar.ichor: MixinHelper2 -> LunarMappingProviderHook (4 hits)
  com.moonsworth.lunar.ichor: MixinHelper3 -> AnnotationTransformInvoker (3 hits)
  com.moonsworth.lunar.ichor: MixinHelper4 -> ClassTransformInvoker (3 hits)
  com.moonsworth.lunar.ichor: MixinHelper5 -> FieldTransformInvoker (3 hits)
  com.moonsworth.lunar.ichor: Ichor2Handler4$$Data2 -> LegacyRemapProvider (2 files)
  com.moonsworth.lunar.ichor: Ichor2Iterator$$Data3 -> AccessWideningRules (1 files)
  com.moonsworth.lunar.ichor: MixinHelper6$$Data30 -> RemapTransformResult (6 files)
[aware-renames] rows=12 skipped=0 files_touched=140 files_renamed=9 mode=dry-run
```

No `net.minecraft.*` rows. Nothing skipped as shaded third-party: `ichor/**`
is Lunar's own **Ichor** transformation/remapping pipeline (it *consumes*
Sponge Mixin, it does not embed it); the shaded Mixin fork lives in
`org.spongepowered.asm.**` and the relocated Guava in `genesis`.

## 1. What this cluster is

`com.moonsworth.lunar.ichor` is the annotation-driven class-transformation and
remapping framework behind Lunar's Mixin integration ("Ichor"). This slice is
the *core vocabulary*: the hook annotations, the pipeline/stage/module
interfaces, the invokers that dispatch the annotations to hook methods, and the
nested keys/tasks of the pipeline.

The tree is a merge of two decompile generations (the pre-rename copy restored
by the rescue sweeps `47dd3dd48` / `7adc91305`, and the copy that carries the
names applied by `classes-15` / `classes-ichor` (`3a9598529`) /
`classes-finalichor` (`2bdfe9c62`)), and **both generations are live**: e.g.
`Annotation2` is imported by 412 files while its applied twin `VersionGate` is
imported by 349. A rename of the placeholder side to the twin name would be
refused by the applier ("new name already declared"), so §3 lists them for the
**repair/dedupe pass** — identical finding to `classes-54.md` §3a,
`classes-59.md` §2 and `classes-42.md` §2.

Naming references used: the real names leaked in the framework's own log
strings (`IchorPipeline`, `IchorModule(s)`, `IchorInjector#transform`,
`IchorContainer.inject`, "isn't an instance of an Ichor injection!",
`IchorClassLoader`, `IchorClassInfo`), `META-INF/services`, the applied maps
(`classes-ichor`, `classes-finalichor`, `classes-54`), and the applied sibling
name family (`ResourceTransformInvoker`, `BytecodeTransformInvoker`,
`MethodTransformInvoker`, `RemapTransformInvoker`, `MixinTargetRemapper`,
`ClassNodeRemapper`).

## 2. Renamed (12)

| # | old | new | evidence (short) |
|---|---|---|---|
| 1 | `AutoCloseableIterator2` | `IchorTransformer` | the pipeline's class transformer (`IchorPipeline.field6`): one `IchorClassLoader` per stage, loads the per-stage Mixin runtime ("Loading Mixin runtime for ", "Going to default mixins stage for "), drives every stage's injector (`transformClassNode`, log `"IchorInjector#transform: On stage ..."`), writes/dumps bytes, maps obf<->deobf names across stages (`method11`-`method16`), collects the stage `MappingSet`s. Its real name is `IchorInjector` (`URLClassLoader`: "isn't in the supplied IchorInjector's pipeline") — the name is currently held by the rescued `MixinInternal2` twin, see §4.1 |
| 2 | `FieldTypeProvider2` | `NestClassNameMapper` | `ClassNameMapper` (MixinMisc2) over the `<Config.method45()>.nest` table: `method4(Config)` loads it, `method1` parses the 6-column rows into `InnerClassRenameInfo` (inner token starting with a digit -> ANONYMOUS, else LOCAL), `remap`/`unmap` + `method2(FieldType)`/`method3(MethodDescriptor)` walk the `outer$inner` chain; consumers `FieldTypeProvider` and `SyntheticClassRenameHook` |
| 3 | `Ichor2` | `IchorInjection` | the interface of one registered injection: `default boolean method1(ClassTransformContext)` applicability + `IchorStage[] method2()` stages; wrapped by `IchorContainer` and keyed in `IchorPipeline.field8`; `method26` error **"isn't an instance of an Ichor injection!"**; implementations `LineNumberIchor`, `LVTCleanupIchor`, `ParameterAnnotationIchor` and every `Ichor2Handler*` / `Ichor2Iterator` / `Ichor2Impl` |
| 4 | `Ichor6` | `IchorModule` | `ServiceLoader.load(Ichor6.class)` (`META-INF/services/com.moonsworth.lunar.ichor.ORCHOHHCOHCORRICRIHCHHRORHHCHH`); pipeline logs "found IchorModule" / "Couldn't find any IchorModules on the classpath!"; `Client.addAllIchorModules`; abstract id + `List<IchorLoader>` + `Type` load order (PRE_INIT..LAST); subclass logs "RuntimeForgeIchorModule.loadFMLLoadingPluginsImpl" |
| 5 | `Ichor7` | `IchorPipeline` | the pipeline: logger `new FatalIchorError5("IchorPipeline")`, "Done creating IchorPipeline in ...ms", "IchorPipeline can't find class in ..."; holds the `IchorStage` list, `IchorOptions`, `IchorModule` map, `IchorInjection`->`IchorContainer` map, MappingSets, `PipelineMetrics`; returned by `IchorAPI.getPipeline` |
| 6 | `MixinHelper2` | `LunarMappingProviderHook` | `@PipelineHook` interface whose `@ProvideRemapper` default `method1(URLClassLoader)` loads the intermediary->lunar MappingSet (`Files.Data2.field12`, error "Can't find mappings intermediary2lunar_${mcVer}.kin for " + Config id) with flags method1/2/3/5; implemented at EXTERNAL_REMAP by `Ichor2Handler2` and `Ichor2Handler24` (applied twin `ReplayModMappingHook`) |
| 7 | `MixinHelper3` | `AnnotationTransformInvoker` | invoker for `@TransformAnnotation` (`Annotation4`; `IchorContainer` registers `MixinHelper3::new` for `Annotation4`): `method1` loops `ClassNode.visibleAnnotations` and invokes the hook with (value() regex, annotation.desc, [ClassNode, AnnotationNode]) |
| 8 | `MixinHelper4` | `ClassTransformInvoker` | invoker for `@TransformClass` (`Annotation7`; registered `MixinHelper4::new` for `Annotation7`): `method1` fetches the ClassNode and invokes the hook with (ClassNode) or (ClassNode, IchorClassLoader) by parameter count |
| 9 | `MixinHelper5` | `FieldTransformInvoker` | invoker for `@TransformField` (`Annotation11`; registered `MixinHelper5::new` for `Annotation11`): `method1` loops `ClassNode.fields` and invokes the hook per field |
| 10 | `Ichor2Handler4$Data2` | `LegacyRemapProvider` | nested `Ichor2Handler4$Data2` (`Ichor2Handler4.java`): `@ProvideRemapper method2(URLClassLoader)` -> `RemapTransformResult` via `method1` (Lunar MappingSet from `Files.Data2.field4`); only instantiated by `Ichor5Handler` when `Config.method19()` ("modern") is **false**, i.e. the non-modern variant that does not set the mixin-target-remap flag of sibling `Ichor2Handler4$Data` |
| 11 | `Ichor2Iterator$Data3` | `AccessWideningRules` | nested `Ichor2Iterator$Data3` (`Ichor2Iterator.java`): the parsed access-widener tables — `Map<String,ClassAccessRule> field1`, `Map<String,MethodAccessRule> field2`, `Map<String,FieldAccessRule> fields`; filled by `Ichor2Iterator.method7` from the "class accessible/extendable", "method ...", "field ... mutable" lines |
| 12 | `MixinHelper6$Data30` | `RemapTransformResult` | nested `MixinHelper6$Data30` (`MixinHelper6.java`): return value of a `@ProvideRemapper` hook = MappingSet + `Function<String,Boolean>` class filter + InheritanceFixer; consumed by `RemapTransformInvoker.method1` (`field8` = filter, `method2(mappings)`, fixer for `InheritanceProvider2`), built by `Ichor2Handler4$Data/$Data2`, `Ichor2Impl2` and the forge providers |

Body-similarity of every renamed class to its nearest sibling was checked
(`difflib` on identifier-normalised bodies) to make sure `MixinHelper3/4/5`
were paired with the right annotation: `IchorContainer`'s constructor is the
ground truth pair list (`Annotation7`->MixinHelper4, `Annotation11`->MixinHelper5,
`Annotation8`->MixinHelper6, `Annotation4`->MixinHelper3,
`Annotation10`->MixinHelper7, `Annotation5`->MixinHelper2_3).

## 3. Skipped rows (33) — stale/rescue twins of already-applied classes

Every row below is the pre-rename copy of a class already renamed by
`classes-15` / `classes-ichor` (`3a9598529`) or `classes-finalichor`
(`2bdfe9c62`); the rescue sweeps restored the placeholders and today **both
generations are referenced** (e.g. `Annotation2` 412 users vs `VersionGate`
349, `Ichor4` 57 vs `IchorStage` 38, `AutoCloseableIterator2$Data3` 20 vs
`ClassTransformContext` 6). This is a merge, not a rename: renaming the
placeholder to the twin name is refused by the applier, and inventing a third
name for the same concept would leave the tree worse off. Same policy as
`classes-54.md` §3a / `classes-42.md` §2 / `classes-59.md` §2.

Ref counts are tree-wide users of the simple name (import/FQN/same-package),
including the declaring file; nested rows count `Owner.Inner`/`Owner$Inner`
users.

| cluster row | already-applied twin | placeholder / twin users |
|---|---|---|
| `Annotation10` | `TransformResource` | 5 / 3 |
| `Annotation11` | `TransformField` | 7 / 4 |
| `Annotation12` | `Interface` | 2 / 3 |
| `Annotation13` | `Stub` | 4 / 5 |
| `Annotation2` | `VersionGate` | 412 / 349 |
| `Annotation3` | `PipelineHook` | 9 / 6 |
| `Annotation4` | `TransformAnnotation` | 3 / 1 |
| `Annotation5` | `TransformBytecode` | 6 / 4 |
| `Annotation6` | `Smuggle` | 3 / 3 |
| `Annotation7` | `TransformClass` | 18 / 12 |
| `Annotation8` | `ProvideRemapper` | 15 / 2 |
| `Annotation9` | `Implements` | 2 / 1 |
| `Annotation_2` | `MixinCondition` | 12 / 17 |
| `Ichor3` | `IchorOptions` | 5 / 1 |
| `Ichor4` | `IchorStage` | 57 / 38 |
| `Ichor5` | `IchorLoader` | 21 / 9 |
| `InheritanceProvider2` | `RemappingInheritanceProvider` | 2 / 1 |
| `MixinExtra2` | `MixinClassSource` | 5 / 3 |
| `MixinHelper2_2` | `SmuggleRestoreHook` | 2 / 2 |
| `MixinHelper2_3` | `BytecodeTransformInvoker` | 2 / 1 |
| `MixinHelper3_2` | `FieldAccessWideningHook` | 5 / 4 |
| `MixinHelper4_2` | `ExternalAccessWideningHook` | 1 / 1 |
| `AutoCloseableIterator2$Data2` | `ResourceCacheKey` | 1 / 1 |
| `AutoCloseableIterator2$Data3` | `ClassTransformContext` | 20 / 6 |
| `Ichor2Iterator$Data2` | `ClassAccessRule` | 1 / 1 |
| `Ichor2Iterator$Data4` | `FieldAccessRule` | 1 / 1 |
| `Ichor2Iterator$Data5` | `MethodAccessRule` | 1 / 1 |
| `MixinMisc$Data2` | `HierarchyCacheEntry` | 1 / 1 |
| `MixinMisc3$Data2` | `AnnotationRemapTask` | 1 / 1 |
| `MixinMisc3$Data3` | `MethodRemapTask` | 1 / 1 |
| `MixinMisc3$Data4` | `ModuleRemapTask` | 1 / 1 |
| `MixinMisc3$Data5` | `RecordComponentRemapTask` | 1 / 1 |
| `MixinHelper6$Data31` | `ThreadClassLoaderKey` | 1 / 1 |

Body similarity (identifier-normalised, difflib) of each pair is 0.79-1.00
for the annotations and 0.42-0.96 for the classes; the low outliers
(`ExternalAccessWideningHook` 0.42, `RemappingInheritanceProvider` 0.44) are
the standalone-`$`-file vs inline-nested form of the same class — the twin was
extracted to a top-level file with the outer instance passed as a constructor
argument (e.g. `AnnotationRemapTask(MixinMisc3, AnnotationNode)`), while the
rescued owner keeps the nested form (`MixinMisc3.this`). Member sets and call
sites are identical.

### Ready-to-copy rows for after the dedupe pass

Delete one copy per concept, repoint the refs, then apply these (they will
apply once the duplicate declaration is gone; whichever copy survives should
carry the twin name — same rule as `classes-40.md` §2):

```
com.moonsworth.lunar.ichor	Annotation10	TransformResource	rescued duplicate of the applied @TransformResource annotation; resource-path regex hook, registered by IchorContainer for Annotation10
com.moonsworth.lunar.ichor	Annotation11	TransformField	rescued duplicate of the applied @TransformField annotation; field hook, registered by IchorContainer for Annotation11
com.moonsworth.lunar.ichor	Annotation12	Interface	rescued duplicate of the applied @Interface annotation; Class value + VersionGate available(), element of @Implements
com.moonsworth.lunar.ichor	Annotation13	Stub	rescued duplicate of the applied @Stub annotation; marks the synthesized stub <init>, removed at FINAL by StubRemovalHook
com.moonsworth.lunar.ichor	Annotation2	VersionGate	rescued duplicate of the applied @VersionGate annotation (value/min/max/inverted/onReturn); read by VersionGateEvaluator
com.moonsworth.lunar.ichor	Annotation3	PipelineHook	rescued duplicate of the applied @PipelineHook hook-interface marker; IchorContainer walks @PipelineHook interfaces for annotated hook methods
com.moonsworth.lunar.ichor	Annotation4	TransformAnnotation	rescued duplicate of the applied @TransformAnnotation annotation; class-annotation hook, invoker AnnotationTransformInvoker
com.moonsworth.lunar.ichor	Annotation5	TransformBytecode	rescued duplicate of the applied @TransformBytecode annotation; byte[] replacement hook, invoker BytecodeTransformInvoker
com.moonsworth.lunar.ichor	Annotation6	Smuggle	rescued duplicate of the applied @Smuggle annotation carrier (smuggle()/remove()/shadow()); read by SmuggleHideHook and SmuggleRestoreHook
com.moonsworth.lunar.ichor	Annotation7	TransformClass	rescued duplicate of the applied @TransformClass annotation; ClassNode hook, invoker ClassTransformInvoker
com.moonsworth.lunar.ichor	Annotation8	ProvideRemapper	rescued duplicate of the applied @ProvideRemapper annotation (6 flags + optional); invoker RemapTransformInvoker
com.moonsworth.lunar.ichor	Annotation9	Implements	rescued duplicate of the applied @Implements annotation; Interface[] value, Sponge @Implements analogue
com.moonsworth.lunar.ichor	Annotation_2	MixinCondition	rescued duplicate of the applied @MixinCondition annotation (present/absent/available); version+feature gating of legacy wrappers and mixins
com.moonsworth.lunar.ichor	Ichor3	IchorOptions	rescued duplicate of the applied pipeline options (classpathDir/overridesDir/extra mixin sets/properties); IchorPipeline.method34()
com.moonsworth.lunar.ichor	Ichor4	IchorStage	rescued duplicate of the applied stage descriptor interface; implemented by PipelineStage (loader/Ichor4Type) and LoaderDependency
com.moonsworth.lunar.ichor	Ichor5	IchorLoader	rescued duplicate of the applied functional interface void loadIchor(IchorTransformer); formals in IchorModule/IchorPipeline/MiscDataTask
com.moonsworth.lunar.ichor	InheritanceProvider2	RemappingInheritanceProvider	rescued duplicate of the applied bombe InheritanceProvider that retries ClassMapping.getFullDeobfuscatedName() and wraps the result in RemappedClassInfo
com.moonsworth.lunar.ichor	MixinExtra2	MixinClassSource	rescued duplicate of the applied located-mixin-class holder (stage/className/bytes) returned by IchorOptions.method1(Collection)
com.moonsworth.lunar.ichor	MixinHelper2_2	SmuggleRestoreHook	rescued duplicate of the applied POST_META_MIXIN smuggle-restore hook (SmuggleRestoreStageHook binds it)
com.moonsworth.lunar.ichor	MixinHelper2_3	BytecodeTransformInvoker	rescued duplicate of the applied @TransformBytecode invoker; registered by IchorContainer for Annotation5
com.moonsworth.lunar.ichor	MixinHelper3_2	FieldAccessWideningHook	rescued duplicate of the applied @TransformField widening default used by ReplayModFieldWideningHook/ExternalFieldWideningHook
com.moonsworth.lunar.ichor	MixinHelper4_2	ExternalAccessWideningHook	rescued duplicate of the applied widening hook for classes whose superclass is obfuscated or net/minecraft, com/mojang, net/optifine
com.moonsworth.lunar.ichor	AutoCloseableIterator2$Data2	ResourceCacheKey	rescued duplicate of the applied (IchorStage, resource path) weak-cache key in IchorTransformer
com.moonsworth.lunar.ichor	AutoCloseableIterator2$Data3	ClassTransformContext	rescued duplicate of the applied per-class transform context passed to every hook invoker
com.moonsworth.lunar.ichor	Ichor2Iterator$Data2	ClassAccessRule	rescued duplicate of the applied per-class access rule (widen/forcePublic) of AccessWideningRules
com.moonsworth.lunar.ichor	Ichor2Iterator$Data4	FieldAccessRule	rescued duplicate of the applied per-field access rule (widen/mutable)
com.moonsworth.lunar.ichor	Ichor2Iterator$Data5	MethodAccessRule	rescued duplicate of the applied per-method access rule (widen/forcePublic)
com.moonsworth.lunar.ichor	MixinMisc$Data2	HierarchyCacheEntry	rescued duplicate of the applied cached hierarchy lookup (superName + collected ClassMappings) in MixinTargetRemapper
com.moonsworth.lunar.ichor	MixinMisc3$Data2	AnnotationRemapTask	rescued duplicate of the applied AnnotationNode remap task of ClassNodeRemapper
com.moonsworth.lunar.ichor	MixinMisc3$Data3	MethodRemapTask	rescued duplicate of the applied MethodNode remap task (name/desc/signature/exceptions/annotations/locals/instructions)
com.moonsworth.lunar.ichor	MixinMisc3$Data4	ModuleRemapTask	rescued duplicate of the applied ModuleNode remap task
com.moonsworth.lunar.ichor	MixinMisc3$Data5	RecordComponentRemapTask	rescued duplicate of the applied RecordComponentNode remap task
com.moonsworth.lunar.ichor	MixinHelper6$Data31	ThreadClassLoaderKey	rescued duplicate of the applied (Thread, IchorClassLoader) cache key of RemapTransformInvoker
```

For the nested rows the merge is small: the owners `Ichor2Iterator`,
`MixinMisc`, `MixinMisc3`, `MixinHelper6` and `AutoCloseableIterator2` use
their inline `Data*` classes themselves and the standalone twins (except
`ClassTransformContext`) are internal-only, so deleting either side and
renaming the survivor is mechanical. Among the top-level skipped rows
`MixinHelper4_2` is referenced by nothing at all (only `ExternalAccessWideningHook`
is used), so it can simply be deleted.

## 4. Ambiguities / caveats

1. **`IchorInjector` is held by the wrong class.** `AutoCloseableIterator2` is
   the real `IchorInjector` (it emits `"IchorInjector#transform: On stage "`
   and `URLClassLoader` says the stage "isn't in the supplied IchorInjector's
   pipeline"). The name is currently declared by `MixinInternal2`, which is
   actually Sponge's **`MixinProxy`** SPI: it is implemented by
   `org.spongepowered.asm.mixin.transformer.MixinProxyImpl` (checked in
   `libs/multiver-full/genesis-*.jar`), set via
   `IchorMixinService.method19(new MixinProxyImpl(loader))` and guarded by the
   messages `"MixinProxy is null"`. Cluster 54 skipped `MixinInternal2` as a
   duplicate of `IchorInjector`. Recommended sequence for the repair pass:
   delete the `MixinInternal2` twin in favour of `IchorInjector` **after**
   renaming that interface to `MixinProxy` (cluster-54 row, already skipped),
   then rename `IchorTransformer` -> `IchorInjector`.
2. **`IchorModule` vs the applied `PlatformLoadOrder` / `LunarPlatformAgent`.**
   `Ichor6` is called *IchorModule* by its own logs and
   `Client.addAllIchorModules`, while `classes-finalichor` named its nested
   load-order enum `PlatformLoadOrder` and `loader/Ichor6Impl`
   `LunarPlatformAgent` (guesses from the "lunar-platform" id). The string
   evidence is stronger; the loader-side names should eventually be aligned
   (`Ichor6$Type` -> module load order, `Ichor6Impl` -> `LunarPlatformModule`).
   Not changed here (other clusters / other packages).
3. **Owners that no cluster covers** (their names have no trailing digit, so
   `name_inventory.py` skipped them). Suggested real names, all with 0
   declarations tree-wide:
   * `AutoCloseableIterator` -> `IchorContainer` (log
     `"IchorContainer.inject: Failed to inject into ClassNode "`, field2 is the
     `IchorInjection`).
   * `Ichor2Iterator` -> `AccessWideningMapHook` (ACCESS_WIDEN injection that
     parses `/lunar/minecraft.accesswidener`, applies `ClassAccessRule`/
     `FieldAccessRule`/`MethodAccessRule`; recorded around `Ichor2Iterator$Data3`).
   * `Ichor2Handler4` -> `InitialRemapHook` (INITIAL_REMAP, loads the Lunar
     MappingSet, registers `Ichor5Handler`, writes `.ichor/lunar_mappings.xsrg`
     when `RemapperIterator2.DEBUG`; its nested `Data`/`Data2` providers are
     named in §2/§3).
   * `MixinInternal2` -> `MixinProxy` (see §4.1).
4. **`Ichor3` (`IchorOptions`) naming.** Its twin was named from the *pipeline
   options* role; the placeholder copy is also used as `IchorOptions` by
   `IchorPipeline` — consistent, no action needed beyond the merge.
5. `MixinHelper2` could be seen as part of the widening family (`Ichor2Handler22/24`
   implement it together with `FieldAccessWideningHook`), but its body only
   provides the intermediary->lunar MappingSet, so it is named
   `LunarMappingProviderHook` rather than a widening hook.
