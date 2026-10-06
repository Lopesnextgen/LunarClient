# Cluster 42 — `com.moonsworth.lunar.client.util` (part 1)

45 cluster rows, all files present. **16 renamed, 29 skipped** — every skip is a
stale-jar twin of a class that already carries a real name in the same package
(identifier-normalised body similarity 0.99–1.00; the pairings were already
applied on 2026-09-14 and the placeholders were re-added later by the rescue
sweeps). Renaming a twin would be refused by the applier for top-level rows
(target simple name already declared) and would create duplicate simple names
for nested rows, so the twins belong to the repair/dedupe pass, not to a class
map — the same finding as `classes-59.md` §2.

Cluster layout: cluster-42 is `client.util#part1` (the first 45 sorted
inventory rows). The top-level rows of `ThreadModuleDump`, `ThreadModuleDump35`,
`ThreadModuleDump39`, `ThreadModuleDump91`, `MixinHelper24`/`25` and the rest of
`client.util` are in clusters 43–45.

## 1. Renames (16)

These live in *jar-only* files that have no renamed counterpart (verified:
the class-declaration and the distinctive string constants appear exactly once
in the tree; none of the new simple names is declared anywhere).

| old | file | new | role |
|---|---|---|---|
| `MixinHelper222` | `MixinHelper222.java` | `TypeSpec` | the missing central class of the SourceEmitter/JavaPoet-like generator: package, imports, modifiers, class name, extends/implements, fields, methods, nested types + private-ctor toggle; `JavaFileWriter`/`Util` hold `List<MixinHelper222>`. `AbstractSpec`/`MethodSpec`/`FieldSpec`/`AnnotationSpec` are its siblings |
| `ThreadModuleDump$Data2` | `ThreadModuleDump.java` | `ResolvedAddress` | value record of `resolveAddress` (address, module name/path/base, symbol); formatted by `formatAddress` |
| `ThreadModuleDump$Data3` | `ThreadModuleDump.java` | `LoadedModule` | `MODULEENTRY32W`-derived module record (name, exe path, base, size); range-checked by `findOwningModule` |
| `ThreadModuleDump$Data4` | `ThreadModuleDump.java` | `ThreadStartAddress` | `NtQueryInformationThread(...,9,...)` result (Win32 start address or `NTSTATUS=0x%08x`) |
| `ThreadModuleDump35$Data2` | `ThreadModuleDump35.java` | `ListSerializer` | `JsonSerializer<List<?>>` (null when empty); part of the adapter set whose Map/Set/String members are registered on the replay + fog Gson builders |
| `ThreadModuleDump35$Data3` | `ThreadModuleDump35.java` | `SetSerializer` | `JsonSerializer<Set<?>>`; `registerTypeHierarchyAdapter(Set.class, ...)` in `FogHandler2` |
| `ThreadModuleDump35$Data4` | `ThreadModuleDump35.java` | `StringSerializer` | `JsonSerializer<String>` (null when null/empty); `registerTypeAdapter(String.class, ...)` in `FogHandler2` |
| `ThreadModuleDump35$Data5` | `ThreadModuleDump35.java` | `SerializedNameExclusionStrategy` | skips fields of `@SerializedNameOnly` classes without `@SerializedName`; `setExclusionStrategies(...)` in `Rewind2_3`/`ReplayProjectManager` |
| `ThreadModuleDump35$Data6` | `ThreadModuleDump35.java` | `IntegerRangeDeserializer` | `JsonDeserializer<Range<Integer>>` for `"[a..b]"` (`"Invalid range format: "`) |
| `ThreadModuleDump39$Data2` | `ThreadModuleDump39.java` | `RefreshAccountResult` | refresh-account IPC callback result (success + refresh error enum); the sibling `Data` is the add-account result |
| `ThreadModuleDump91$Data2` | `ThreadModuleDump91.java` | `AbstractTransform` | abstract cosmetic transform: position + condition + JSON `transformType`/`values`/`condition` |
| `ThreadModuleDump91$Data3` | `ThreadModuleDump91.java` | `DynamicTransform` | per-entity position via `Function<BridgeExtension222,Vector3f>` (save writes `gui`/`player`); factory base used by `CosmeticSlot` |
| `ThreadModuleDump91$Data4` | `ThreadModuleDump91.java` | `ScaleTransform` | scale transform (`Type2.SCALE`) |
| `ThreadModuleDump91$Data5` | `ThreadModuleDump91.java` | `TranslateTransform` | translate transform (`Type2.TRANSLATE`) |
| `ThreadModuleDump91$Data6` | `ThreadModuleDump91.java` | `RotateTransform` | rotate transform (angle + axis, `Type2.ROTATE`) |
| `Wasapi$Kernel32` | `Wasapi.java` | `Kernel32Library` | JNA `StdCallLibrary` loaded from `"kernel32"` (CreateEvent/WaitForSingleObject/CloseHandle) for the WASAPI capture. `Kernel32` has a digit suffix and shadows the JNA `com.sun.jna.platform.win32.Kernel32` imported elsewhere |

## 2. Skipped: stale-jar twins (29 rows)

Byte-equivalent (identifier-normalised similarity ≥ 0.99) copies of classes
renamed by `8d81145c` (client.util), `99deb192` (clientutil2) and `8d6088f02`
(clientutil3); the placeholders were re-added by the rescue commits
(`3d38608ff`, `47dd3dd48`). They are still referenced by other rescued files
(imports placeholder/named): delete/unify in the repair pass, do not rename.

| placeholder file | already-named class | imports (placeholder/named) |
|---|---|---|
| `Annotation2.java` | `NotNullSerialized` | 0 / 0 (4 FQN sites) |
| `Annotation3.java` | `Identifier` | 9 / 3 |
| `Annotation4.java` | `TranslationEntry` | 2 / 0 |
| `Annotation5.java` | `ConstantName` | 5 / 69 |
| `Annotation6.java` | `ParameterPosition` | 6 / 0 |
| `Annotation7.java` | `SerializedNameOnly` | 32 / 32 |
| `InputStreamLoader2.java` | `RewindableInputStream` | 0 / 1 |
| `MixinHelper2.java` | `SourceEmitter` | 0 / 0 |
| `MixinHelper22.java` | `AbstractSpec` | 0 / 0 |
| `MixinHelper23.java` | `AnnotationSpec` | 1 / 0 |
| `MixinHelper23$Data2` | `AnnotationSpec$AnnotationLiteral` | — |
| `MixinHelper23$Data3` | `AnnotationSpec$AnnotationSpecBuilder` | — |
| `MixinHelper25$Data2` | `JavadocSpec$JavadocBuilder` | — |
| `MixinHelper223.java` | `MethodSpec` | 0 / 0 |
| `MixinHelper224.java` | `FieldSpec` | 1 / 0 |
| `ThreadModuleDump17$Data2` | `ColorMath$LabColor` | — |
| `ThreadModuleDump18$Data2` | `Easing$ElasticInOut` | — |
| `ThreadModuleDump18$Data3` | `Easing$ElasticOut` | — |
| `ThreadModuleDump18$Data4` | `Easing$BackInOut` | — |
| `ThreadModuleDump18$Data5` | `Easing$BackOut` | — |
| `ThreadModuleDump18$Data6` | `Easing$BackIn` | — |
| `ThreadModuleDump47$Data2` | `WeightedQuadtree$QuadTreeEntry` | — |
| `ThreadModuleDump77$Extension2` | `UncheckedFunctional$ThrowingConsumer` | — |
| `ThreadModuleDump77$Extension3` | `UncheckedFunctional$ThrowingRunnable` | — |
| `ThreadModuleDump77$Extension4` | `UncheckedFunctional$ThrowingSupplier` | — |
| `ThreadModuleDumpType5$Data2` | `ConditionState$FunctionCondition` | — |
| `ThreadModuleDumpType5$Data3` | `ConditionState$Condition` | — |
| `ThreadModuleDumpType5$Data4` | `ConditionState$ConstantCondition` | — |
| `ThreadModuleDumpType5$Data5` | `ConditionState$SupplierCondition` | — |

`ThreadModuleDump17`/`18`/`47`/`77`/`Type5` owners are themselves twins
(`ColorMath`, `Easing`, `WeightedQuadtree`, `UncheckedFunctional`,
`ConditionState`) and their top-level rows sit in cluster 43.

## 3. Application order + adjacent rows (for the main agent)

1. **Apply `classes-42.tsv` before any map that renames**
   `ThreadModuleDump35`, `ThreadModuleDump39`, `ThreadModuleDump91` or
   `ThreadModuleDump` (clusters 43–45). The nested-row applier locates the owner
   by its *current* name from the row evidence (`ThreadModuleDump35$Data2`);
   if the owner file/class was renamed first, the row falls through to the
   top-level path and degenerates into a package-wide bare `Data2` replacement.
2. Jar-only owners in adjacent clusters, suggested names (all free tree-wide
   except `ModelTransform`): `ThreadModuleDump` → `ThreadDumpUtils` /
   `NativeThreadDump` (not in any cluster — no digit); `ThreadModuleDump35` →
   `GsonSerializers`; `ThreadModuleDump39` → `LauncherAccountAuth` (also parses
   JWT payloads for EntityRenderer/ReconnectBackoff) or `AuthUtils`;
   `ThreadModuleDump91` → `CosmeticTransform`. Their non-lazy nested types
   (`Data`, `Type`, `Type2`) are not in the inventory and still need names in a
   later pass (`ThreadIdMapping`, `TransformCondition`, `TransformKind`, ...).
3. `Wasapi$Kernel32` is the only lazy name in `Wasapi.java`; `Kernel32` is the
   real Win32 library name, so `Kernel32Library` is a compromise forced by the
   no-trailing-digits rule — leave the row out if the API name is preferred.
4. `ListSerializer` has no caller in-tree (the Map/Set/String/Range members of
   the same adapter set are used); kept named for completeness of the set.
5. Unique-name checks: every new name was grepped tree-wide
   (`class|interface|enum|record|@interface <Name>`) — 0 declarations, no file
   with the same stem. No shaded-library classes in this cluster (the
   `MixinHelper2x` family is Lunar's own SourceEmitter generator, not JavaPoet
   from `libs/`; no target names matched the shaded workstream tables).
