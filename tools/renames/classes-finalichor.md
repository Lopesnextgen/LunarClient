# Final ichor/forge/loader class names (dry-run map)

Cluster: `com.moonsworth.lunar.ichor.**`, `com.moonsworth.lunar.forge.**`,
`com.moonsworth.lunar.loader.**` — the junk-stem names left over after the
`classes-ichor` / `classes-ichorutil` / `classes-15` waves.

**83 rows, 1 skipped.** Map: `tools/renames/classes-finalichor.tsv`;
check: `python3 tools/apply_class_renames_aware.py --map tools/renames/classes-finalichor.tsv`
(reports `skipped=0`, dry-run — nothing in `src/` was touched).

Method: every class body was read, every reference grepped, and for the
`Owner$Inner` leftovers the deleted owner (removed in commit `266dd974`
"green-trunk WIP") was recovered from git history to pin the role.

Rules applied: role names matching the already-good neighbours
(`TransformClass`, `ProvideRemapper`, `PipelineHook`, `AccessWideningHook`,
`IchorInjector`, `ClassHierarchyFactory`, ...); no obfuscation digits or
`_N`/`$` in any new name; each new name grepped against the whole
`src/main/java` tree (0 declaration clashes).


## `com.moonsworth.lunar.ichor`

| old | new | evidence |
|---|---|---|
| `AutoCloseableIterator2$Data` | `StageClassLoaderKey` | key (IchorStage, parent ClassLoader) of the per-stage URLClassLoader cache in the deleted pipeline class: computeIfAbsent(new Data(stage, loader), -> new URLClassLoader(pipeline, stage, loader)) |
| `AutoCloseableIterator2$Data2` | `ResourceCacheKey` | key (IchorStage, resource path) of the 250-entry MixinShared WeakReference cache: field9.get(new Data2(stage, mixinshared.getResourcePath())) |
| `AutoCloseableIterator2$Data3` | `ClassTransformContext` | per-class transform context handed to every hook invoker (stage, className, ClassEntry, URLClassLoader): method1(Class) bytes, method2(byte[]) write-back, method3(Class) ClassNode, method5() ClassEntry, with the "not-early stage"/"not-late stage" guards; used by BytecodeTransformInvoker, ResourceTransformInvoker, Ichor2Handler3 and the util hook predicates |
| `ClassWriter` | `HierarchyClassWriter` | ASM ClassWriter whose getCommonSuperClass walks the ClassHierarchyFactory (MixinLegacy2Iterator) nodes and their isAssignableFrom logic, falling back to java/lang/Object |
| `Ichor2Handler` | `StubRemovalHook` | FINAL-stage hook removing every method/field carrying @Stub from the class node, i.e. stripping the stub constructors added by the stub-constructor injector |
| `Ichor2Handler22` | `ReplayModFieldWideningHook` | EXTERNAL_REMAP hook for the "com.replaymod.*" regex filter implementing FieldAccessWideningHook |
| `Ichor2Handler23` | `LegacyAccessWideningHook` | POST_REMAP hook for "net.minecraft.*\|com.mojang.*\|net.optifine.*\|[^/]+" implementing AccessWideningHook, enabled by Config.method21() ("legacy".equals(type)) so it only fires on legacy versions |
| `Ichor2Handler24` | `ReplayModMappingHook` | EXTERNAL_REMAP hook for "com.replaymod.*" implementing FieldAccessWideningHook plus the remapper-provider pipeline hook whose default @ProvideRemapper method returns the intermediary->lunar MappingSet |
| `Ichor2Handler3` | `ExternalFieldWideningHook` | EXTERNAL_REMAP hook implementing FieldAccessWideningHook for every class not under it/unimi/dsi/fastutil/ or com/moonsworth/ (external obfuscated classes) |
| `Ichor2Handler5` | `SmuggleHideStageHook` | PRE_META_MIXIN stage binding of the smuggle-hide pipeline hook (moves member annotations into the @Smuggle carrier before meta-mixins run) |
| `Ichor2Handler6` | `SmuggleRestoreStageHook` | POST_META_MIXIN stage binding of the smuggle-restore pipeline hook (puts the smuggled annotations back after meta-mixins run) |
| `Ichor2Impl` | `ForgeAccessWideningHook` | POST_OPTIFINE_PATCH hook widening "net/minecraftforge/" classes (extends the util prefix filter, implements AccessWideningHook) |
| `Ichor2Iterator$Data2` | `ClassAccessRule` | per-class access-widening rule parsed from the inflight access map: widen (make public) and forcePublic (clear permittedSubclasses) |
| `Ichor2Iterator$Data4` | `FieldAccessRule` | per-field access-widening rule: widen and mutable (clear final) |
| `Ichor2Iterator$Data5` | `MethodAccessRule` | per-method access-widening rule: widen and forcePublic (clear protected, make non-abstract) |
| `Ichor2Iterator2$1` | `StubConstructorBody` | InsnList body of a synthesized stub constructor: super() then throw new AbstractMethodError("Stub constructors should not be called.") |
| `Ichor2Iterator_2` | `SyntheticClassRenameHook` | INIT-stage hook remapping the class node in place (SimpleRemapper) and applying the synthetic-inner-class rename records (name/outerClass/outerMethod/access plus innerClasses entries); also exposes the remapper via @ProvideRemapper |
| `Ichor2Iterator2` | `StubConstructorInjector` | INIT-stage hook adding a stub <init> (descriptor derived from innerClasses) to classes that lack it, annotated @Stub("Stub Constructor") and @MixinMerged |
| `Ichor2Iterator3` | `IsRecordRewriter` | POST_REMAP hook rewriting Class.isRecord() calls and LambdaMetafactory handles to AsmUtils.isRecordLazy(Class)Z |
| `Ichor5Handler$Data` | `HandlerRegistrationTask` | MixinRegistrationTask backed by the mod mixin handler: when the stage matches it registers the handler mixin-json list and runs the handler hook-registration |
| `Ichor5Handler2` | `LoaderDescriptor` | IchorLoader carrying only an id + version (getId/getVersion) with a no-op loadIchor - a named loader descriptor |
| `Ichor6$Type` | `PlatformLoadOrder` | platform-agent ordering key (PRE_INIT, INIT, NORMAL, LATE, LAST) compared by the platform base compareTo to sort agents |
| `Ichor7$Data` | `PipelineMetrics` | per-pipeline transform metrics: per-stage/per-class timing maps, StageMemberTracker map, main-thread AtomicLong counters and the init timestamp printed by the "Debugging class transformation times" report |
| `IGlobalPropertyServiceImpl$Data` | `PropertyKey` | IPropertyKey implementation holding the property name (toString) for the IGlobalPropertyService global property service |
| `IMixinErrorImpl` | `IchorErrorHandler` | Sponge IMixinErrorHandler implementation registered by the mixin service: reports prepare/apply errors through the pipeline ErrorReporter and returns ErrorAction.NONE |
| `IMixinServiceBootstrapImpl` | `IchorServiceBootstrap` | Sponge IMixinServiceBootstrap: getName() "Ichor", getServiceClassName() "com.moonsworth.lunar.ichor.mixin.service.IchorMixinService" |
| `InheritanceProvider` | `ClassNodeInheritanceProvider` | bombe InheritanceProvider over the Ichor URLClassLoader: reads ClassNodes (loader.method6(name,true)) and builds a ClassInfo with field/method InheritanceTypes |
| `InheritanceProvider$1` | `ClassNodeClassInfo` | bombe ClassInfo.Impl subclass built from a ClassNode (name/interface/superName/interfaces plus field, field-by-name and method inheritance maps) with synchronized provideParents |
| `MixinCore$Type2` | `InnerClassKind` | kind of a synthetic inner-class rename record (LOCAL, ANONYMOUS, INNER) |
| `MixinExtra` | `MixinSourceArchive` | jar-backed mixin source set: stage + Map<class name,byte[]> + name predicate; locates a MixinClassSource by candidate names, built from a jar path with JarUtils |
| `MixinHelper6$Data31` | `ThreadClassLoaderKey` | cache key (Thread, URLClassLoader) for the per-thread remapper cache used while the mapping helper fixes inheritance entries |
| `MixinHelper6$Extension` | `InheritanceFixer` | functional interface ClassInfo fix(InheritanceProvider, String, @Nullable ClassInfo) used to repair/complete inheritance lookups for the remapper |
| `MixinInternalTask$1` | `MappingMergeFilter` | lorenz MappingSetMergerHandler filtering which method/field mappings survive the merge (skips newInstance/close/open/mode except for blaze3d, LayeredDraw$Layer and PackSelectionModel$Entry classes; keeps getBuffer$v1_16_1 when the version is above 32) |
| `MixinLegacy` | `ClassHierarchyNode` | class-hierarchy node (name, superclass, interfaces) with the isAssignableFrom/interface walk and the shared java/lang/Object root, produced by ClassHierarchyFactory and consumed by HierarchyClassWriter |
| `MixinLegacy2Iterator` | `ClasspathHierarchyResolver` | ClassHierarchyFactory resolving nodes by reading <name>.class from the URLClassLoader per stage (with deobfuscated-name fallbacks), caching results and handling java.* classes by reflection |
| `MixinMisc$Data` | `HierarchyCacheKey` | cache key (class name, URLClassLoader) for the superclass-chain mapping lookups while remapping mixin targets |
| `MixinMisc$Data2` | `HierarchyCacheEntry` | cached hierarchy lookup result: superName plus the ClassMappings collected for the annotations found on that superclass |
| `MixinMisc$Type2` | `MemberKind` | member-kind hint (ALL, FIELD, METHOD) used when resolving a mixin target member string against the class mappings |
| `MixinMisc2Handler` | `MappingSetNameMapper` | ClassNameMapper implementation over a lorenz MappingSet: remap uses computeClassMapping/getFullDeobfuscatedName, unmap uses the reverse MappingSetUtils lookup |
| `MixinMisc3$Data` | `FieldRemapTask` | in-place remap of a FieldNode (name/desc/signature/value plus annotations) driven by the node remapper |
| `MixinMisc3$Data2` | `AnnotationRemapTask` | in-place remap of an AnnotationNode (desc, values, kotlin metadata handling) |
| `MixinMisc3$Data3` | `MethodRemapTask` | in-place remap of a MethodNode: name/desc/signature/exceptions/annotations/locals/try-catch and every instruction (frames, field/method/indy/type/ldc/multianewarray) |
| `MixinMisc3$Data4` | `ModuleRemapTask` | in-place remap of a ModuleNode (name/mainClass/packages/requires/exports/opens/uses/provides) |
| `MixinMisc3$Data5` | `RecordComponentRemapTask` | in-place remap of a RecordComponentNode (name/descriptor/signature/annotations) |
| `MixinMore` | `ResourceLookup` | resource-lookup surface (getResourceAsStream/findResource) exposed by the pipeline loaders |
| `MixinMoreHandler$Data` | `MutableUrlClassLoader` | URLClassLoader subclass with a public addURL so jar URLs can be appended at runtime |
| `MixinOther` | `MixinClassPostProcessor` | post-processes a transformed mixin class: re-reads the remapped bytes through the loader, copies the structure back, drops @Shadow members with obfuscated $vN names, moves field-initialiser jumps out of <init> and deletes unreferenced lambda$ methods |
| `MixinServiceAbstractImpl` | `IchorMixinService` | Sponge MixinServiceAbstract implementation: name "Ichor", registers IchorErrorHandler, boots MixinExtras, provides the class/bytecode providers and the console logger; errors say "Loading IchorMixinService outside of an IchorPipeline?" |
| `MixinShared` | `ResourcePayload` | mutable resource payload (path + bytes) passed to the resource transform hooks, which may replace both the path and the bytes |
| `MixinSupport$1` | `MixinErrorStderrStream` | stderr PrintStream wrapper that flags a mixin failure when printf sees "Total unimplemented:" |
| `MixinSupport$2` | `MixinErrorStdoutStream` | stdout PrintStream wrapper that flags a mixin failure when print matches the mixin error patterns ("in <target>. No refMap loaded" / "mixins.<config>.json:... from mod") |
| `MixinSupport$Data` | `MixinErrorRow` | row of the .ichor/audit/mixin_error_report.csv report: ExceptionName,McClassPath,LunarClassId,ErrorMsg |
| `ClassInfoIterator` | `RemappedClassInfo` | bombe ClassInfo decorator presenting another ClassInfo with deobfuscated names through a MappingSet (getName/getSuperName/getInterfaces/getFields/getMethods) |
| `LoggerAdapterAbstractIterator` | `IchorConsoleLogger` | Sponge LoggerAdapterAbstract implementation, getType() "Ichor Console Logger", formatting "[time] [id(stage)/level]" and filtering known mixin noise |
| `RemapperIterator` | `MappingSetRemapper` | ASM Remapper over a MappingSet and InheritanceProvider: completed class mappings for field/method/record-component/invokedynamic names |
| `MixinInternal` | `MixinTransformTask` | contract for a mixin class transform step: method1(ClassNode, pipeline) decides applicability, method2(ClassNode, URLClassLoader) applies it (implemented by the mapping-fix task) |

## `com.moonsworth.lunar.ichor.mixin`

| old | new | evidence |
|---|---|---|
| `MixinHelper` | `DescriptorType` | descriptor-typed signature element (getDescriptor plus opcode/size derived from Type) implemented by the TypeSignature variants (ClassTypeSignature, PrimitiveTypeSignature, ArrayElementSignature, TypeVariableName) |
| `SignatureVisitorImpl` | `GenericSignatureParser` | ASM SignatureVisitor building TypeSignature/MethodSignature trees; the static entry points parse a method/type generic signature with an optional context |

## `com.moonsworth.lunar.ichor.util`

| old | new | evidence |
|---|---|---|
| `ClassRemapperImpl` | `PatchedClassRemapper` | ASM ClassRemapper that records the visited class name and remaps method names in that context, wrapping methods in the frame-patching method remapper (debug "PatchedMethodRemapper.remapFrameTypes") |
| `ClassVisitorImpl` | `JsrInlinerClassVisitor` | ClassVisitor wrapping every method in a JSRInlinerAdapter (inlines legacy JSR/RET bytecode) |
| `ClassWriter` | `LoaderAwareClassWriter` | ASM ClassWriter whose getCommonSuperClass asks the ichor classloader bombe InheritanceProvider and prints "LoaderAwareClassWriter.getCommonSuperClass" |
| `Ichor2Handler` | `ClassPrefixFilter` | hook predicate accepting classes whose name starts with any of a list of prefixes (e.g. net/minecraftforge/, com/replaymod/) |
| `Ichor2Handler2` | `ClassNameRegexFilter` | hook predicate matching the class name against a compiled regex, with a shared pattern cache |
| `InheritanceProvider` | `CachingInheritanceProvider` | bombe InheritanceProvider decorator caching provide(name) / provide(name, ctx) results for 5 minutes |
| `LinkedHashMapImpl` | `LruCache` | LinkedHashMap whose removeEldestEntry is bounded by the capacity passed to the constructor (the 250-entry MixinShared cache) |
| `PrintStreamImpl` | `TeePrintStream` | PrintStream mirroring println/print output into a log file (create/truncate/append options) while the wrapped stream is still System.out/System.err |

## `com.moonsworth.lunar.forge`

| old | new | evidence |
|---|---|---|
| `Ichor2Handler_2` | `ClassPatchHook` | FORGE_PATCH bytecode-transform hook applying the registered ClassPatch for the class name and logging failures through the forge logger |
| `Ichor2Iterator` | `LegacyForgeEventHook` | POST_FORGE_PATCH hook for net/minecraftforge/ classes: widens @SubscribeEvent handlers and synthesizes the legacy FML Event API (LISTENER_LIST field, setup/getListenerList/hasResult/isCancelable) |
| `Ichor2Iterator_2` | `ForgeTransformerHook` | POST_FORGE_PATCH bytecode-transform hook building and running the mod launchwrapper IClassTransformers (from IFMLLoadingPlugin.getASMTransformerClass) |
| `Ichor5Iterator$Data2` | `ForgeModRegistrationTask` | MixinRegistrationTask registering "mixins.ichor.forge.<id>.json" and "mixins.<id>_forge_<id>.json" at the MIXIN stage |
| `Ichor5Loader` | `ModMixinLoader` | abstract loader for a forge mod jar: holds the mod mixin-json list and json objects and hands them to the injector at the mod stage (extended by the replaymod forge loader) |
| `Ichor5Loader_2` | `ForgeHookLoader` | loader registering the forge stage hooks (class-patch, legacy-event and transformer hooks) |
| `MixinCore` | `ForgeModRegistrar` | registers a mod jar with FML: parses the mod class as an ASMModParser, feeds the ModDiscoverer ASM table, builds the ModContainer from mcmod.info and adds it to the Forge Loader |
| `MixinHelper` | `ForgeModMixinScanner` | loader that scans a mod jar (MANIFEST.MF MixinConfigs/TweakClass/FMLCorePlugin, mods.toml version, refmaps, @Mod main class) and exposes the mixin configs for registration |
| `MixinMisc$Data3` | `AlwaysAvailableInputStream` | FilterInputStream over a jar entry that disables mark and reports available()==Integer.MAX_VALUE so the jar copier neither blocks nor mis-sizes |

## `com.moonsworth.lunar.loader`

| old | new | evidence |
|---|---|---|
| `Ichor4Type` | `PipelineStage` | the 20 pipeline stages (PRE_INIT .. FINAL) as an enum implementing IchorStage, with the mixin-runtime flags and getApplicableStages(from, to) |
| `Ichor4Type2` | `LoaderDependency` | stage+loader enum of externally supplied libraries (FABRIC_LOADER, ADVENTURE) carrying transformer/recommended versions and maven coordinates |
| `Ichor4Type2$1` | `FabricLoaderDependency` | FABRIC_LOADER constant body: registers the Fabric Loader access-widening hook at its stage |
| `Ichor4Type2$2` | `AdventureDependency` | ADVENTURE constant body: registers nothing (no-op loadIchor) |
| `Ichor6Impl` | `LunarPlatformAgent` | platform agent for "lunar-platform": returns the loader dependencies whose stages are active in the pipeline |

## `com.moonsworth.lunar.loader.mixin`

| old | new | evidence |
|---|---|---|
| `Ichor5Loader` | `MixinConfigLoader` | loader registering a mixin json (name computed from the Config by the supplied function) at a given mixin stage through a config registration task |
| `MixinHelper` | `VersionGateEvaluator` | evaluates a version-gate annotation node (value/min/max/inverted/onReturn) against a Config: exact-version membership, range and inverted range |
| `MixinInternal3Handler` | `ConfigRegistrationTask` | abstract MixinRegistrationTask holding the Config and providing the "mixins.<id>.json" / "mixins.<id>_<suffix>.json" names |

## Skipped (genuinely undeterminable)

| class | why |
|---|---|
| `com.moonsworth.lunar.ichor.util.Annotation` | empty marker annotation (`@interface Annotation {}`, no members, no `@Target`/`@Retention`). Zero references anywhere in the tree **and** zero references in the shipped jar: scanning the constant pool of every class in `libs/lunar-libraries.jar` for `Lcom/moonsworth/lunar/ichor/util/Annotation;` returns 0 hits (the siblings `Annotation2`=`KeepName`, `Annotation3`=`IchorProcessed`, `Annotation4`=`IchorService` are all used). There is no usage, body or name to derive a role from. |

## Notable findings

- Commit `266dd974` deleted the outer owners of most `$`-named leftovers: `AutoCloseableIterator`/`AutoCloseableIterator2` (the Ichor pipeline), `Ichor2`..`Ichor7`, `MixinMisc`, `MixinMisc3`, `MixinCore`, `MixinSupport`, `MixinInternalTask`, `Ichor5Handler`/`Ichor5Handler_2`, `FieldTypeProvider2`, `ichor/URLClassLoader`, `forge/Ichor2Handler`/`Ichor2Handler2`, `loader/Ichor2Iterator`. Every `$`-row above is therefore an orphaned nested class; its role was recovered from the deleted owner (git `266dd974^`) plus surviving call sites.
- `ichor/Annotation` (the plain method-hook annotation) had already been renamed to `TransformMethod` by the concurrent wave in commit `b90c1c98` while this pass was running; it is not in the map. Its only remaining sibling, the empty `ichor/util/Annotation`, is the skipped row above.
- `MixinServiceAbstractImpl` is the concrete Ichor mixin service: it logs `"Genesis/IchorMixinService"` / `"Loading IchorMixinService outside of an IchorPipeline?"` and is what `IMixinServiceBootstrapImpl.getServiceClassName()` points at, so it becomes `IchorMixinService` / `IchorServiceBootstrap`.
- `ichor/util/ClassWriter` names *itself* in its debug output (`"LoaderAwareClassWriter.getCommonSuperClass"`), and `ichor/util/ClassRemapperImpl` names its inner remapper `"PatchedMethodRemapper"` — both used as literal evidence.
- Two names in the cluster are predicates for the same stage family and were split by role: `Ichor2Handler22` (replaymod field widening), `Ichor2Handler24` (replaymod + `@ProvideRemapper` MappingSet) and `Ichor2Handler23` (legacy-version access widening, gated by `Config.method21()` == `"legacy"`).
- The three `MixinHelper`s in the cluster are unrelated: `ichor/mixin/MixinHelper` is a descriptor-typed signature element (`getDescriptor`/`getOpcode`/`getSize`, implemented by the `*TypeSignature` classes) -> `DescriptorType`; `forge/MixinHelper` scans a Forge mod jar manifest -> `ForgeModMixinScanner`; `loader/mixin/MixinHelper` evaluates `@VersionGate` nodes against a `Config` -> `VersionGateEvaluator`.
- Five `FooN`/`FooN_M` collisions resolved by import-aware renaming only: `Ichor2Handler` exists in `ichor`, `ichor.util`, `forge` (deleted) and `framework.mixin`; duplicate simple names `MixinHelper`/`MixinShared`/`MixinLegacy`/`LinkedHashMapImpl`/`PrintStreamImpl`/`ClassWriter`/`InheritanceProvider` all exist in other packages and are left untouched.
