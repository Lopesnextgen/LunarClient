# Cluster 05 — `com.moonsworth.lunar.ichor.util` + `highlight.mixin.highlight` + `com.moonsworth.lunar.client.markers` (40 classes)

Source revision: `tools/renames/cluster-05.txt` md5 `b5c5e22e75b85c84accb8a3817b3c48a`
(40 rows: 16 `ichor.util` types, 10 nested highlight event types + 2 top-level
events, 12 `markers` types incl. 2 nested).
Map: `tools/renames/classes-ichorutil.tsv` md5 `9bd4dbf847611ee7f33e526d4e40c9a8`.
Applied by the main agent as commit `62b5319b` (`renamer: apply ichorutil map`).

## What this cluster actually is

Three unrelated packages that the clusterer merged, plus two nested leftovers
from earlier highlight batches:

* **`com.moonsworth.lunar.ichor.util`** — the small support toolbox of **Ichor**,
  the ASM source-transformation framework Lunar embeds (relocated from
  `org.cadixdev.ichor`, together with the lorenz/bombe mappings libraries).
  It holds Ichor's logger, annotation-node helpers, the central ASM helper, the
  jar/stream helpers, the per-class `ClassEntry` work item, the stage member
  tracker and three framework annotations. **The placeholder prefix
  `FatalIchorError` is a decompiler artifact and has nothing to do with errors.**
* **`com.moonsworth.lunar.client.highlight.mixin.highlight`** — the core of the
  client **event system** (`LunarEventBus`, `EventBusAccess`, `ResultEvent`,
  the `EventRender*` families). The 10 nested `DataN`/`DataM` rows here are the
  concrete event subclasses that the earlier `classes-23`/`classes-26`
  batches proposed names for but could not apply (the applier at the time
  skipped nested declarations). `HighlightImpl23`/`HighlightImpl25` are two
  `ResultEvent` gates whose mixin owners live in `legacy`/`v1_8`/`v1_12`.
* **`com.moonsworth.lunar.client.markers`** — **not** the marker-icon registry
  (that is `framework.feature.markers`, already renamed in
  `classes-markers-killsounds`). It is Lunar's **WebOSR “Driver”** UI system:
  browser-driven overlays and routes (`DriverOverlay`, `DriverRoute`), the
  provider GUI-extension interface, the JS `@CallbackJS` bridge and the
  component/context model. The real names leak in the generated
  `toString()`s (`"DriverOverlay.DriverOverlayBuilder0"`,
  `"DriverRoute.DriverRouteBuilder0"`).

## Renames (40 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Annotation2` | `KeepName` | no-member CLASS marker on Ichor's own annotations (VersionGate/Smuggle/Implements/Interface/MixinCondition) and on name-sensitive members/classes (Ref.MC_VERSION/hasModule, IchorAPI2.loadClass, FatalIchorError6.isRecordLazy, Gson-serialized RoomSecret/Storageoverlay fields) |
| 2 | `Annotation3` | `IchorProcessed` | records `stage` + `time` (String method1(), long method2()); added to a ClassNode when `ichor.markClasses=true` (AutoCloseableIterator2.method6 -> FatalIchorError6.method47) |
| 3 | `Annotation4` | `IchorService` | TYPE marker (ANNOTATION_TYPE/TYPE/RECORD_COMPONENT) on the global service/entry classes IchorAPI, OpenALNative, FlawlessFrames |
| 4 | `FatalIchorError10` | `ClassWriterSymbolTable` | reflection wrapper over `ClassWriter.symbolTable.entries` (index/tag/owner/name/value/data); nested `Data` is the entry wrapper |
| 5 | `FatalIchorError11` | `ErrorReporter` | dedup Throwable collector (method1 subscribe / method2 report); returned by Ichor7.method38 and used by IMixinErrorImpl |
| 6 | `FatalIchorError12` | `DebugStack` | ThreadLocal context stack, ASCII-boxed dump (method2); unused in this partial tree |
| 7 | `FatalIchorError13` | `StageMemberTracker` | records the fields/methods each Ichor stage adds; method2/3 find the introducing stage; nested `Data` = (stage, fields[], methods[]) |
| 8 | `FatalIchorError14` | `ClassBytes` | immutable (className, byte[]) result of the jar-rewrite BiFunction (FatalIchorError9.method1/3) |
| 9 | `FatalIchorError2` | `TriFunction` | `@FunctionalInterface R apply(A,B,C)`; hook-factory type of AutoCloseableIterator.method1 |
| 10 | `FatalIchorError3` | `ResourceProvider` | `InputStream method1(String)`; implemented by Ichor's URLClassLoader and used by Ichor7 for classpath resources |
| 11 | `FatalIchorError4` | `AnnotationUtils` | ASM annotation helper (getDesc, get/set Visible/Invisible, getValue/setValue, enum packing) — Sponge `Annotations` analogue |
| 12 | `FatalIchorError5` | `IchorLogger` | info/warn/fatal + throwing(), optional log file, `Type{FATAL…TRACE}`; `field1` is the global "Ichor" logger |
| 13 | `FatalIchorError6` | `AsmUtils` | central ASM helper: ClassNode copy, access widening, name/super/interface extraction, hierarchy walk, inner classes, byte->ClassNode, annotation get/add/remove, ClassWriter, constant extraction, boxing |
| 14 | `FatalIchorError7` | `IoUtils` | stream copy / toByteArray; used by MappingSetUtils |
| 15 | `FatalIchorError8` | `ClassEntry` | lazily-parsed class holder (byte[] or ClassNode) with getClassNode/getClassBytes/parse |
| 16 | `FatalIchorError9` | `JarUtils` | jar read/write: rewrite applying a (name,bytes)->ClassBytes fn, jar-in-jar recursion, TreeMap->File, zip class listing, validity check |
| 17 | `Data10` (nested) | `EventRenderHotbarPost` | `EventRenderInventoryScreen$Data10`: post hotbar-render event (hotbar position); SlotLocking/ItemDropProtection/HotbarKeyOverlay/SkyblockProtectItem |
| 18 | `Data11` (nested) | `EventRenderHotbarItems` | `EventRenderInventoryScreen$Data11`: hotbar items pre-render event; SkyblockRarityBackground |
| 19 | `Data12` (nested) | `EventRenderHotbarPre` | `EventRenderInventoryScreen$Data12`: hotbar pre-render event (wrapEvents pre) |
| 20 | `Data13` (nested) | `EventRenderBipedModel` | `EventRenderModel$Data13`: biped model event with BodyPart allow/deny sets; ModelBipedMixin -> Skins3d/FogIterator |
| 21 | `Data14` (nested) | `EventRenderModelPlayer` | `EventRenderModel$Data14`: player-model variant (no BodyPart sets); ModelPlayerMixin (`EventRenderPlayerModel` was taken by `highlight.fishing`) |
| 22 | `Data2` (nested) | `EventFovModifierPost` | `EventFovModifier$Data2`: post FOV stage; Zoom |
| 23 | `Data2` (nested) | `EventRenderEntityLabelValues` | `EventRenderEntityLabel$Data2`: value-table record variant |
| 24 | `Data3` (nested) | `EventRenderEntityLabelLines` | `EventRenderEntityLabel$Data3`: Supplier<List<Component>> lines API; Nametag/Tiertagger/RewindHandlers3Impl6 |
| 25 | `Data5` (nested) | `EventRenderItemColorCancel` | `EventRenderItemColor$Data5`: empty nested cancellable event; no references in this partial tree |
| 26 | `Data8` (nested) | `EventRenderEntity` | `EventRenderEntityBase$Data8`: concrete entity event (BridgeExtension entity); entity-HUD mods |
| 27 | `HighlightImpl23` | `EventRenderChat` | cancellable ResultEvent around `GuiNewChat.drawChat(int)` (update counter); legacy/v1_8/v1_12 GuiIngame mixins |
| 28 | `HighlightImpl25` | `EventRenderHotbar` | empty cancellable ResultEvent at HEAD of the hotbar render (`renderTooltip$v1_8`/`renderHotbar$v1_12`); GuiIngameMixin3 |
| 29 | `Data2` (nested) | `DriverOverlayRegistrar` | `Markers2Handler$Data2`: registering builder for `DriverOverlay` (toString leak) |
| 30 | `Data2` (nested) | `DriverRouteRegistrar` | `Markers2Handler2$Data2`: registering builder for `DriverRoute` (toString leak) |
| 31 | `GuiExtension_2` | `DriverGuiExtension` | provider interface implemented by every WebOSR driver GUI handler (~49) |
| 32 | `Markers2` | `DriverElement` | two-boolean interface implemented by DriverOverlay/DriverRoute |
| 33 | `Markers3` | `DriverViewContext` | per-route render/input context (stored as Markers2Handler2.field34) |
| 34 | `Markers4` | `DriverBridge` | WebOSR `@CallbackJS` bridge (setScreen/requestPayload/setActiveRoute/… + provider registry) |
| 35 | `Markers5` | `DriverSettingExtension` | abstract base exposing Lunar settings to driver GUIs; extended by Gui9 |
| 36 | `MarkersImpl_2` | `VanillaHomeContext` | extends `Markers_3`, adds `"vanillaHome"` to the driver JSON context |
| 37 | `MarkersType_2` | `WebOsrNative` | native library set enum ULTRALIGHT/COMMON (`loadWebOSR` -> System.loadLibrary) |
| 38 | `MarkersType_3` | `DriverScreen` | vanilla screen enum NULL/SINGLEPLAYER/…/HOSTED_WORLD_SETTINGS + initScreen/requiresAuth |
| 39 | `Markers_2` | `DriverComponent` | abstract driver UI component (x/y/w/h, children, 9 callback hooks) |
| 40 | `Markers_3` | `DriverContext` | driver JSON context provider (isMainMenu/streaming/creative); extended by VanillaHomeContext/MarkersImpl |

The 12 nested rows carry a 5th `file` column and name their owner as
`Owner$Old` in the evidence, as required by the import-aware applier.

## Applier dry run and collisions

`python3 tools/apply_class_renames_aware.py --map tools/renames/classes-ichorutil.tsv`:

```
[aware-renames] 40 rows (12 nested); 6594 java files
[aware-renames] rows=40 skipped=0 files_touched=244 files_renamed=28 mode=dry-run
```

All 40 rows resolve; 0 skipped. The v1 applier would have skipped most of this
map: `Annotation2/3/4`, `Markers2/3/4/5`, `Markers_2/_3`, `MarkersType_2/_3`,
`MarkersImpl_2`, `GuiExtension_2` and every `DataN` are declared in several
packages tree-wide. The import/`file`-aware applier resolves each reference
from the owning package, so unrelated `markers`/`annotations`/`highlight`
classes are untouched.

## Caveats / follow-ups

* **Framework annotation roles** are inferred, not confirmed: `KeepName`
  (`Annotation2`), `IchorProcessed` (`Annotation3`) and `IchorService`
  (`Annotation4`) have **no reader in this partial tree** (the runtime reads
  them through relocated/lost code). `Annotation3`'s role is unambiguous
  (stage/time marker); `Annotation2`'s spread across API members *and*
  Gson-serialized fields fits a "keep this name" marker; `Annotation4` is a
  type-only marker on IchorAPI/OpenALNative/FlawlessFrames.
* **Nested leftovers**: the highlight rows are the exact nested rows
  `classes-23.tsv` proposed (`Highlight$Data8` -> EventRenderEntity,
  `HighlightBase$Data13/14`, `HighlightImpl20$Data2`,
  `HighlightImpl5$Data2/3`). `HighlightImpl20$Data` (the `Pre` stage) was **not**
  in `cluster-05.txt` so it is deliberately left unrenamed (plain `Data`, no
  trailing digit, is not flagged by the clusterer).
* `EventRenderPlayerModel` had to be avoided (already used by
  `highlight.fishing`), hence `EventRenderModelPlayer`.
* `EventRenderItemColorCancel` (row 25) is a guess: the class is an empty
  cancellable nested event with **no reference anywhere** in the partial tree.
* `DriverBridge`/`Markers4` likewise has no in-tree reference; the name comes
  from its `@CallbackJS` surface and the Driver system it belongs to.
