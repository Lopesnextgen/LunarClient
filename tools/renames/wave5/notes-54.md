# Cluster 54 — `com.moonsworth.lunar.ichor` leftovers (15 rows)

Source: `tools/renames/cluster-54.txt` (15 rows, all files present).
Map: `tools/renames/wave5/classes-54.tsv` — **5 rows written, 10 rows skipped**
(12 rows unresolved in total: the 10 duplicates in §3a and the 2 nested enums
in §3b, all held for the dedupe pass).

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-54.tsv
[aware-renames] 5 rows (0 nested); 12534 java files
  com.moonsworth.lunar.ichor: MixinHelper6 -> RemapTransformInvoker (36 hits)
  com.moonsworth.lunar.ichor: MixinHelper8 -> MethodTransformInvoker (3 hits)
  com.moonsworth.lunar.ichor: MixinMisc -> MixinTargetRemapper (29 hits)
  com.moonsworth.lunar.ichor: MixinMisc3 -> ClassNodeRemapper (83 hits)
  com.moonsworth.lunar.ichor: MixinCore -> InnerClassRenameInfo (32 hits)
[aware-renames] rows=5 skipped=0 files_touched=25 files_renamed=5 mode=dry-run
```

No `net.minecraft.*` rows, no missing paths. Nothing skipped as a shaded
third-party class: everything here is Lunar's own **Ichor** pipeline (see §5).

## 1. What this cluster is

`cluster-54` is the *leftover core* of `com.moonsworth.lunar.ichor`, the
annotation-driven class-transformation / remapping pipeline behind Sponge-Mixin
("Ichor"). It is the second helping after the applied `classes-15` /
`classes-ichor` / `classes-finalichor` waves: those renamed the pipeline hooks,
the nested task classes and the outer injector interfaces, while these 15 rows
were either skipped at the time, deleted in `266dd974` ("green-trunk WIP" moved
~2.9k non-compiling files out of `src/`) and later restored by the rescue
sweeps `47dd3dd48` / `7adc91305`, or extracted as top-level files by the
nested-type pass and then restored *inside* their owners by the same sweeps.

Consequence: **10 of the 15 rows are duplicate declarations of classes that
already carry an applied name in this package**, and both generations are
referenced from different (mixed) files in the tree. Those rows are §3 — the
applier would refuse them (`new name already declared`), and renaming them to
fresh names would create a third identity for the same concept. They belong to
the repair/dedupe pass, exactly like `classes-59.md` §2 and `classes-42.md` §2.

## 2. Renamed (5)

| # | old | new | evidence (short) |
|---|---|---|---|
| 1 | `MixinHelper6` | `RemapTransformInvoker` | `@ProvideRemapper` (Annotation8) hook invoker, registered by `AutoCloseableIterator`: reflects the annotated method for a `MappingSet`/`Data30`/`ClassNameMapper` (message `"RemapNectarHandler(" + method + ") didn't return a MappingSet!"`), validates it (`"MappingSet for " + ... + " has 0 top level class mappings."`) and caches an `InheritanceRemapper` per (Thread, URLClassLoader); on every class it runs `MixinMisc3.method1` (tree remap) and, when the hook flag is set, `MixinMisc.method1` (mixin-target remap). Forge remapper providers `forge/Ichor2Handler` / `Ichor2Handler2` return `MixinHelper6.Data30`. |
| 2 | `MixinHelper8` | `MethodTransformInvoker` | `@TransformMethod` (Annotation) invoker: `extends MixinHelper<Annotation>`; loops `ClassNode.methods` and reflectively invokes the hook with `(ClassNode, MethodNode)` when the annotation regex `value()` matches the method name; registered in `AutoCloseableIterator` for `Annotation`. Naming follows the applied invoker family (`ResourceTransformInvoker`, `BytecodeTransformInvoker`). |
| 3 | `MixinMisc` | `MixinTargetRemapper` | Remaps a mixin `ClassNode` to its `@Mixin` target's deobfuscated names: entry `method1(pipeline, loader, ClassNode, MappingSet, InheritanceRemapper)` collects the mixin annotation node (`method3`/`method4`, descriptors `"Lorg/spongepowered/asm/mixin/"` + `"Lcom/llamalad7/mixinextras/injector/"`), resolves the `@Mixin` value(s) (`method8`), walks the target hierarchy with a 10s cache (`method5`, `method7`, `method10`) and rewrites `@Shadow`/`@Overwrite` members, `@Accessor`/`@Invoker` names and annotation values (`method11`-`method16`). Called from `MixinHelper6` and, via `method3`, from `ichor.util.AsmUtils` / `FatalIchorError6`. |
| 4 | `MixinMisc3` | `ClassNodeRemapper` | In-place ASM **tree-API** class remapper: static `method1(pipeline, ClassNode, Remapper)` times `run()`/`remap()` (per-class ms into `PipelineMetrics`) and rewrites name/signature/superName/interfaces/annotations, then every field, method, instruction (`FrameNode`/`FieldInsnNode`/`MethodInsnNode`/`InvokeDynamicInsnNode`/`TypeInsnNode`/`LdcInsnNode`/`MultiANewArrayInsnNode`), inner class, module and record-component node through the `Remapper`; entry points also as `method2(ClassNode, MethodNode, Remapper)` / `method3(ClassNode, Remapper)`. Used by `SyntheticClassRenameHook`, `client/framework/transform/LwjglRelocationTransform` and `client/lotusfish/Ichor2Impl`; tree counterpart of the applied `ichor/util/PatchedClassRemapper`. |
| 5 | `MixinCore` | `InnerClassRenameInfo` | Metadata of one synthetic inner class: fields name / outerName / outerMethod / outerMethodDesc / innerName / access / kind, with `method1()` == `outerName + "$" + innerName` and fluent setters. Parsed from the 6-column `.nest` lines in `FieldTypeProvider2.method1` (`Config.method45() + ".nest"`, kind = INNER unless the inner-name token starts with a digit: a pure number -> ANONYMOUS, otherwise -> LOCAL) and applied by `SyntheticClassRenameHook.method2` (sets `node.name`/`outerClass`/`outerMethod`/`outerMethodDesc`/`access`, or synthesizes an `InnerClassNode`) and by `Ichor2Iterator_2`. |

Name alternatives (if the lead prefers a different convention):
`RemapTransformInvoker` could be `ProvideRemapperInvoker` (systematic with
`ResourceTransformInvoker`); `MixinTargetRemapper` could be `MixinRemapper`;
`InnerClassRenameInfo` could be `SyntheticInnerClassInfo` (pairs with
`SyntheticClassRenameHook`). All five chosen names were grepped tree-wide:
0 declarations and 0 `.java` stems.

## 3. Skipped rows (10) + held nested enums (2)

### 3a. Stale/rescue twins of already-applied classes (10)

Each placeholder file is the pre-rename copy of a class already renamed by
`classes-15`/`classes-ichor` (`3a9598529`) or `classes-finalichor`
(`2bdfe9c62`); the rescue sweeps restored the placeholders, and today **both
generations are referenced from different files** (the tree mixes
obf-member and renamed-member files), so this is a merge, not a rename.
Ref counts below are **tree-wide import-aware** users of the simple name (own
file excluded), so they include the cross-package callers.

| cluster row | already-applied name | placeholder-side users | already-named-side users |
|---|---|---|---|
| `MixinHelper7` | `ResourceTransformInvoker` | 1 — `AutoCloseableIterator` (registers the `@TransformResource` invoker) | 0 (dead) |
| `MixinHelper_2` | `AccessWideningHook` | 2 — `Ichor2Impl`, `Ichor2Handler23` | 2 — `LegacyAccessWideningHook`, `ForgeAccessWideningHook` |
| `MixinHelper_3` | `SmuggleHideHook` | 1 — `Ichor2Handler5` (PRE_META_MIXIN stage hook) | 1 — `SmuggleHideStageHook` |
| `MixinInternal2` | `IchorInjector` | 8 — `URLClassLoader`, `Ichor5Handler$Data`, `Ichor5Handler_2`, `MixinInternal3`, `client/lotusfish/Ichor5Iterator`, `forge/Ichor5Iterator`, `framework/Ichor5Handler`, `loader/mixin/Ichor5Loader` | 8 — `MixinRegistrationTask`, `HandlerRegistrationTask`, `URLClassLoader`, `AutoCloseableIterator2`, `forge/ForgeModRegistrationTask`, `framework/OptifineMixinHandler`, `loader/mixin/MixinConfigLoader`, `replaymod/forge/Ichor5Loader` |
| `MixinInternal3` | `MixinRegistrationTask` | 4 — `URLClassLoader`, `Ichor5Handler$Data`, `AutoCloseableIterator2`, `loader/mixin/MixinInternal3Handler` | 2 — `HandlerRegistrationTask`, `loader/mixin/ConfigRegistrationTask` |
| `MixinLegacy2` | `ClassHierarchyFactory` | 2 — `MixinLegacy2Iterator`, `ClassWriter` | 2 — `HierarchyClassWriter`, `ClasspathHierarchyResolver` |
| `MixinMisc2` | `ClassNameMapper` | 6 — `AutoCloseableIterator`, `MixinMisc2Handler`, `Ichor2Iterator_2`, `FieldTypeProvider2`, `AutoCloseableIterator2`, `MixinHelper6` | 2 — `SyntheticClassRenameHook`, `MappingSetNameMapper` |
| `MixinMisc4` | `MappingSetUtils` | 10 — `ClassInfoIterator`, `MixinMisc5`, `MixinMisc2Handler`, `RemapperIterator2`, `MixinMisc`, `bridge/Bridge2_24`, `forge/Ichor2Handler2_2`, `forge/Ichor2Iterator2`, `forge/Ichor2Iterator3`, `forge/Ichor5Iterator` | 5 — `InheritanceMapProvider`, `InheritanceRemapper`, `MappingSetNameMapper`, `RemappedClassInfo`, `bridge/Bridge6_6` |
| `MixinMisc5` | `InheritanceMapProvider` | 2 — `Ichor7`, `Ichor5Handler` | 0 (dead) |
| `RemapperIterator2` | `InheritanceRemapper` | 6 — `MixinHelper6`, `MixinMisc`, `Ichor2Handler4`, `MixinInternalTask`, `client/lotusfish/Ichor5Iterator`, `forge/Ichor6Impl` | 0 (dead, and broken: `InheritanceRemapper` reads `this.RIHROHCIRORRICORIORCRIOIOORRII` / `this.HCICRHCHCHHCICOCORCCOHHHOOCHIH`, which `MappingSetRemapper` does not declare) |

**Recommendation for the dedupe pass:** keep the generation the live call
sites use (the placeholder side for `MixinHelper7`, `MixinMisc5`,
`RemapperIterator2` and mostly for the rest), delete the other, then rename the
survivor to the *applied* name — the same rule as `classes-40.md` §2
("whichever copy survives should carry the twin name"). Ready-to-copy map rows
for after that dedupe (they will apply once the duplicate declaration is gone):

```
com.moonsworth.lunar.ichor	MixinHelper7	ResourceTransformInvoker	rescued duplicate of the applied @TransformResource invoker; registered in AutoCloseableIterator and referenced by MixinHelper7.method2(MixinShared,URLClassLoader)
com.moonsworth.lunar.ichor	MixinHelper_2	AccessWideningHook	rescued duplicate of the applied @PipelineHook widening interface (Ichor2Impl/Ichor2Handler23 use it)
com.moonsworth.lunar.ichor	MixinHelper_3	SmuggleHideHook	rescued duplicate of the applied smuggle-hide @PipelineHook (Ichor2Handler5)
com.moonsworth.lunar.ichor	MixinInternal2	IchorInjector	rescued duplicate of the applied per-stage injector SPI (URLClassLoader, Ichor5Handler$Data)
com.moonsworth.lunar.ichor	MixinInternal3	MixinRegistrationTask	rescued duplicate of the applied per-stage registration callback (URLClassLoader, Ichor5Handler$Data)
com.moonsworth.lunar.ichor	MixinLegacy2	ClassHierarchyFactory	rescued duplicate of the applied class-hierarchy node factory (MixinLegacy2Iterator, ClassWriter)
com.moonsworth.lunar.ichor	MixinMisc2	ClassNameMapper	rescued duplicate of the applied remap/unmap SPI (AutoCloseableIterator, FieldTypeProvider2)
com.moonsworth.lunar.ichor	MixinMisc4	MappingSetUtils	rescued duplicate of the applied Lorenz MappingSet toolbox (ClassInfoIterator, RemapperIterator2)
com.moonsworth.lunar.ichor	MixinMisc5	InheritanceMapProvider	rescued duplicate of the applied raw/mapped superclass-map provider (Ichor7, Ichor5Handler)
com.moonsworth.lunar.ichor	RemapperIterator2	InheritanceRemapper	rescued duplicate of the applied inheritance-completing MappingSetRemapper (MixinHelper6, MixinMisc)
```

Do **not** apply these before the duplicates are resolved: the top-level
applier skips rows whose target simple name is already declared.

### 3b. Nested enums held for the dedupe pass (2)

| cluster row | intended name | why held |
|---|---|---|
| `MixinCore.java` `Type2` | `InnerClassKind` | The applied `classes-finalichor` row (`MixinCore$Type2` -> `InnerClassKind`) was carried out when the enum lived in its own extracted file; the rescue then put `Type2` back inside `MixinCore`. The top-level `InnerClassKind.java` is currently dead (0 refs), the nested `Type2` is live (`FieldTypeProvider2`). Renaming the nested enum now would create a duplicate simple name until the top-level orphan is deleted, so it is held. After the dedupe: `MixinCore.Type2 -> InnerClassKind` (then `InnerClassRenameInfo.Type2` once §2 row 5 is applied). |
| `MixinMisc.java` `Type2` | `MemberKind` | Same story for `MixinMisc$Type2 -> MemberKind`; the nested enum is the live member-kind hint used by `MixinMisc.method13`-`method16` (ALL / FIELD / METHOD for `@Accessor` vs `@Invoker` vs other members), the top-level `MemberKind.java` is dead. Rename after deleting the orphan: `MixinMisc.Type2 -> MemberKind` (then `MixinTargetRemapper.Type2` after §2 row 3). |

Both intended names are exactly the ones in the applied `classes-finalichor`
map, so no new naming decision is needed — only the orphan top-level files
`src/main/java/com/moonsworth/lunar/ichor/InnerClassKind.java` and
`MemberKind.java` have to go first (or be repointed to a nested
`<Owner>.InnerClassKind`/`<Owner>.MemberKind`).

## 4. Ambiguities / caveats

* **Merge direction.** Except for `MixinHelper7`/`MixinMisc5`/`RemapperIterator2`
  the already-applied twin is *also* referenced, so a simple "delete the
  placeholder" would break the renamed generation's callers; the dedupe pass has
  to pick a survivor per pair and repoint the other side's references.
* **Name alternatives** for the five renames are listed in §2; none of them
  collides tree-wide.
* `MixinMisc3` is a heavily modified fork of ASM's `ClassRemapper` and
  `RemapperIterator2` of Lorenz's `LorenzRemapper`, but both are Ichor pipeline
  classes (Ichor-specific metrics, mixin hooks, `ichor.debug.remapper` flag),
  not untouched shaded library code, so they stay in the rename scope; the
  shaded-library workstream does not need to own them.
* No package moves were made (wave-5 rule): all five stay in
  `com.moonsworth.lunar.ichor`.

## 5. Method / evidence sources used

* class bodies (`MixinHelper6/8`, `MixinMisc`, `MixinMisc3`, `MixinCore`,
  `FieldTypeProvider2`, `SyntheticClassRenameHook`, `AutoCloseableIterator`,
  `URLClassLoader`) and their call sites in the tree;
* prior applied maps and notes: `tools/renames/classes-15.{tsv,md}`,
  `classes-ichor.tsv`, `classes-finalichor.{tsv,md}`, `classes-59.md` §2,
  `classes-42.md` §2, `classes-40.md` §2 (dedupe convention);
* `tools/mappings-snapshot/restructure/remaining-renames.tsv` and the
  `genesis-0.1.0-SNAPSHOT-all.jar` CFR decompile (confirms the class bodies and
  the kept string `"RemapNectarHandler("`), plus the reference sources in
  `/tmp/opencode/reference/` (`Lorenz/LorenzRemapper`, `Mixin`, `Bombe`) for the
  fork relationships.
