# wave6 — client/util structure audit notes

Scope: `src/main/java/com/moonsworth/lunar/client/util/**` — 355 files
(268 top-level + 87 across 16 subpackages). Output: `moves-util.tsv`,
154 move rows (all move-only, New == Old) plus this notes file.

## Counts

| bucket | files | target |
|---|---|---|
| move rows | 154 | see map |
| left in place (duplicates) | 179 | dedupe pass |
| already in final shape | 22 | util/collection (9), util/raytrace (9), util/memory (2), util/performance (2) |

Destination clusters (rows): framework 25 (root 9, codegen 11, hud 3, crash 1,
listener 1), config 10 (root 1, option 9), util/math 19, util/io 17,
util/collection 16, util/text 10, util/concurrent 10, util/game 7, util/net 6,
ui 6 (root 5 + notification 1), render 10 (root 3, texture 5, shader 1, color 1),
network 4 (ipc/friend/apollo/hostedworld), translation 3, audio 3 (root 2,
ogg 1), cosmetics 2, account 1, driver 1, mixin 1, mod/render/saturation 2,
util/raytrace 1.

## Why half the tree is deliberately left

The tree carries **two parallel generations** of the same classes:

* **(a) established/named copies** — the results of `moves-namesA`,
  `moves-misc` and the options/framework class maps (183 files: they have the
  real names and correct packages);
* **(b) rescue batch 6 copies** (commit `3d38608ff`, 172 files under `util/`)
  that re-decompiled classes already renamed+placed in (a): `ThreadModuleDump*`,
  `Annotation*`, `MixinHelper*`, `Util*`, and the *entire* leftover of
  `util/{alert,chest,click,colorsaturation,gui,highlight,holograms,lotusfish,
  mixin,nameplate,rewindhandlers}`.

This audit moves (a) — the copies with real names — and leaves (b) for the
dedupe pass: moving a stale duplicate into the target package next to its
already-placed twin (usually under a placeholder name) would only relocate the
mess. Twin map for the 97 `ThreadModuleDump*` files left behind:

```
T2 FoodUtils            T44 TraitBuilder        T70 IntRectangle
T3 ServerUtils          T45 HudTimer            T71 GuiResolution
T4 TickQueue            T46 TextUtils           T72 DownloadedImageCache
T5 UnorderedPair        T47 WeightedQuadtree    T73 EntityLookup
T6 ValueHolder          T48 LunarConstants      T73Type InterpolationMode
T7 RectangleQuadtree    T49 CursorManager       T75 RomanNumeralUtils
T8 MatrixConversion     T50 OpaqueTextureFix    T76 ResourcePackUtils
T10 DisconnectUtils     T51 MarkerPredicate     T77 UncheckedFunctional
T11 TimeFormatting      T52 CompressionUtils    T78 MapRemoval
T12 RegexPatterns       T53 ListUtils           T79 PortUtils
T13 PacketUtil          T54 DummyPlayer         T80 LaunchOptions
T14 EarlyOptions        T55 MimeTypeUtils       T81 Vector2dPair
T15 LineMath            T56 TranslationFormatter T82 BlockSearch
T17 ColorMath           T57 KeyBindingOrder     T83 RomanNumeralParser
T18 Easing              T58 TextSanitizer       T84 WeightedValue
T19 ArrayUtils          T59 ImageUtils          T85 MousePosition
T20 RunnableHolder      T60 Vector2f            T86 FormattingCodes
T21 FriendStatusUtils   T61 BrowserUtils        T87 Initializable
T22 NpcUtils            T62 GuiClipState        T88 NameTagUpdateListener
T23 ColorUtils          T63 Ref                 T89 ItemTypeLookup
T24 ExpiringWeightedCache T64 ScreenProjection  T90 FoodValues
T25 BinarySearchState   T65 CollectionUtils     T92 CubeGeometry
T26 MutableValue        T66 ProtoConverter      T93 DirtyFlag
T27 StringCursor        T67 MathUtils           T94 ShaderPreprocessor
T28 FileExplorer        T68 ClipboardUtils      T95 DoubleRectangle
T29 DesktopNotifier     T69 HeadTextureCache    TIterator EmptyIterator
T30 KeyVersionPair                              TIterator2 FilteringIterator
T31 ReflectionUtils                             TThread AsyncCacheLoader
T32 DevFeatureList                              TType Direction
T33 PotionUtils                                 TType2 OperatingSystem
T34 DateUtils                                   TType3 Flag
T36 UuidUtils                                   TType4 Corner
T37 BackgroundExecutor                          TType5 ConditionState
T38 FastMath
T40 NumberUtils
T41 RingBuffer
T42 CheckoutUtils
T43 ConfigRangeBuilder
```

Named rescue duplicates left (23): `Annotation{,2,3,4,5,6,7}` (= TranslationKey,
NotNullSerialized, Identifier, TranslationEntry, ConstantName, ParameterPosition,
SerializedNameOnly), `Coordinates` (= LunarSoundPlayer), `Highlight3Task`
(= HostedWorldJoinHandler), `InputStreamLoader2` (= RewindableInputStream),
`MixinHelper{,2,22,23,24,25,223,224}` (= SpecBuilder/SourceEmitter/AbstractSpec/
AnnotationSpec/NamedValue/JavadocSpec/MethodSpec/FieldSpec), `Util`
(= JavaFileWriter), `Util$Data` (= ResourceUsageCounter), `Util2`
(= AsyncResource), `Util_2` (= AsyncResourceManager), `UtilType` (= Visibility).

Subpackage leftovers left (59), all twins of already-placed classes:
* `alert/` (9): Alert, Alert2, Alert3, Alert5, Alert5Extension, Alert5Handler,
  Alert5Impl, Alert6, GuiRewindhandlersHandler2.
* `chest/` (9): Chest, SBase, SBase2, SExtension, SImpl, mixin/{Chest,
  ChestHandler, ChestHandler2, ChestHandler3}. Note: `util/chest/mixin` is not
  a real mixin folder — those interfaces are raytrace contexts, not mixins.
* `click/` (14): BridgeExtension2/3, Click, Click2, Click3, Click4, Click4Impl,
  Click5, Click6, Click8, Click9, Click10, Click11, Click12.
* `colorsaturation/` (4): Colorsaturation, Colorsaturation2,
  ColorsaturationHandler, ColorsaturationTask.
* `gui/` (5): Gui, ListExtension, ListExtension2, ListExtension22,
  ListExtension3 (= util/collection classes).
* `highlight/` (3): CloseableImpl, FilenameFilter, Highlight (= DirectoryWatcher,
  FileExtensionFilter, AtomicFileWriter).
* `holograms/` (1): MapImpl (= CachedValuesMap).
* `lotusfish/mixin` (4): Lotusfish, Lotusfish2, Lotusfish3, LotusfishException
  (= audio.ogg Crc32, OggPage, OggPageReader, LotusfishException).
* `mixin/` (3): MixinHelper, MixinHelper2, MixinHelper22 (= collection
  ElementAdder/ListBuilder/ListBuilderImpl; package name is misleading).
* `nameplate/` (1): Nameplate (= ExtraCodecs).
* `rewindhandlers/` (6): Gui2Extension, Rewindhandlers,
  RewindhandlersExtension2/3/32/4 (= render.color ColorAnimation, ColorMutator,
  SolidColor, AnimatedColor, AnimatedColorImpl, MutableColor).

## Kept as-is (no rows)

* `util/collection` (9) and `util/raytrace` (9) are already the destinations of
  `moves-namesA`; no package change needed.
* `util/memory` (Memory, MemoryTask) and `util/performance` (GcMonitor,
  PerfSnapshotRecorder) are the named buckets created by `buckets B`; left
  untouched. Caveat: `util/memory/*` now duplicates the older `client/memory/*`
  (same simple names) — a move map cannot merge them (name collision), so this
  is a dedupe-pass item.
* `Fishing` and `LineSegment` are identical live classes (misnamed twin) — both
  moved to `util/math`; merge in the naming pass.

## Judgement calls / assumptions

* **`client/ui`** is used per the wave6 README + scope brief. The current tree
  still has `client/gui` (screens/blog/external/notification/prompt); the
  gui-owner map must converge on `client/ui`, otherwise the 6 UI rows
  (`CursorManager`, `GuiClipState`, `GuiResolution`, `MousePosition`,
  `Click2Base`, `DesktopNotifier`) sit in a separate top-level package.
* Two extra buckets beyond the requested six: **`util/game`** (7 vanilla-world
  helpers: entity/block/NPC/potion/item lookups) and **`framework/codegen`**
  (11 JavaPoet-style spec emitters for the Apollo mod generator that writes
  `apollo/.../Mod*.java`). Both are coherent families with >=5 classes; without
  them those classes would have to stay in `util/` root.
* `Ref`, `LunarConstants`, `LaunchOptions`, `EarlyOptions`, `OperatingSystem`,
  `DevFeatureList`, `Initializable`, `Flag`, `PacketUtil`, `ThreadModuleDump`
  are client infrastructure, not utilities -> `client/framework*` (`util` must
  shrink to real utilities).
* Three unique placeholder-named classes were moved rather than stranded
  (`ThreadModuleDump9` -> util/io, `ThreadModuleDump74` -> util/net,
  `ThreadModuleDump91` -> cosmetics); they have no named twin and need the
  naming pass.
* `Alert4` and `Util2Handler` are two live async-texture loaders (probably twin
  generations); both moved to `render/texture`.
* `NameTagUpdateListener` is a mixin duck interface -> `client/mixin`.
* `MarkerPredicate` follows the markers feature (already drained to `driver`).
* All 154 source files exist; no target simple-name collision and no duplicate
  destination was found (checked programmatically against the current tree).
