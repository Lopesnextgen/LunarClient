# Wave 7 — cluster 01 (`com.moonsworth.lunar.client.util` part 1)

Scope: `tools/renames/cluster-01.txt` — 50 declarations in 33 files (33 top-level
classes plus 17 nested classes). Map: `classes-01.tsv` (50 rows).

## Headline finding: the whole cluster is the stale "rescue" copy

On 2026-09-14 commit `8d81145ce` ("apply client.util map") renamed 28 of these
classes to their final names; wave 6 (`955133506`) then moved them into
subpackages (`util.io`, `util.math`, `util.text`, `util.collection`,
`util.concurrent`, `util.game`, `util.net`, `framework.codegen`,
`config.option`, `network.friend`, `mod.render.saturation`, `translation`).
Commit `3d38608ff` ("rescue: legacy/replaymod + client leftovers") then
**re-added the pre-rename placeholder files** (`git log --diff-filter=A` shows
`A src/main/java/com/moonsworth/lunar/client/util/ThreadModuleDump18.java`,
`Annotation7.java`, `MixinHelper23.java`, ... in `3d38608ff`). Both copies are
live today: e.g. `util/io/JsonAdapters` imports the leftover `Annotation7`,
while other consumers use `Easing`/`NpcUtils`/... So every row below is a
**twin rename**: the canonical name is already taken by the renamed sibling,
and the leftover gets a distinct synonym. `docs`/tooling should treat each pair
as a merge candidate (`tools/find_duplicates.py`, `wave6/merges.tsv` format).

## Twin table (leftover -> new synonym -> canonical twin already in tree)

| old | new | canonical twin |
|---|---|---|
| Annotation2 | NonNullSerialized | util/io/NotNullSerialized |
| Annotation3 | OptionIdentifier | config/option/Identifier |
| Annotation4 | TranslationMetadata | translation/TranslationEntry |
| Annotation5 | ConstantIdentifier | config/option/ConstantName |
| Annotation6 | CoordinateParameter | config/option/ParameterPosition |
| Annotation7 | SerializedFieldsOnly | util/io/SerializedNameOnly |
| InputStreamLoader2 | ReplayableInputStream | util/io/RewindableInputStream |
| MixinHelper2 | SourceRenderer | framework/codegen/SourceEmitter |
| MixinHelper22 | AbstractSourceSpec | framework/codegen/AbstractSpec |
| MixinHelper23 | AnnotationEmitter | framework/codegen/AnnotationSpec |
| MixinHelper24 | NamedValueEntry | framework/codegen/NamedValue |
| MixinHelper25 | JavadocEmitter | framework/codegen/JavadocSpec |
| MixinHelper223 | MethodEmitter | framework/codegen/MethodSpec |
| MixinHelper224 | FieldEmitter | framework/codegen/FieldSpec |
| ThreadModuleDump2 | FoodCalculations | mod/render/saturation/FoodUtils |
| ThreadModuleDump10 | ServerDisconnector | util/net/DisconnectUtils |
| ThreadModuleDump11 | DurationFormatting | util/text/TimeFormatting |
| ThreadModuleDump12 | ValidationPatterns | util/text/RegexPatterns |
| ThreadModuleDump13 | MainThreadDispatcher | framework/PacketUtil |
| ThreadModuleDump14 | EarlyPerformanceOptions | framework/EarlyOptions |
| ThreadModuleDump15 | LineIntersectionMath | util/math/LineMath |
| ThreadModuleDump17 | ColorSpaceMath | util/math/ColorMath |
| ThreadModuleDump18 | EasingFunctions | util/math/Easing |
| ThreadModuleDump19 | ReflectionArrayUtils | util/collection/ArrayUtils |
| ThreadModuleDump20 | RunnableCallbacks | util/concurrent/RunnableHolder |
| ThreadModuleDump21 | FriendStatusJson | network/friend/FriendStatusUtils |
| ThreadModuleDump22 | NpcDetector | util/game/NpcUtils |
| ThreadModuleDump23 | ArgbColorUtils | util/math/ColorUtils |
| ThreadModuleDump24 | WeightedExpiringCache | util/collection/ExpiringWeightedCache |
| ThreadModuleDump25 | BinarySearchHistory | util/math/BinarySearchState |
| ThreadModuleDump26 | ValueCell | util/collection/MutableValue |
| ThreadModuleDump27 | TextCursor | util/text/StringCursor |
| ThreadModuleDump28 | FolderOpener | util/io/FileExplorer |

Nested rows reuse the canonical twin's nested names (nested names are scoped,
so no global collision): `AnnotationLiteral`/`AnnotationSpecBuilder`
(= AnnotationSpec's), `JavadocBuilder` (= JavadocSpec's), `LabColor`
(= ColorMath's), `ElasticInOut/ElasticOut/BackInOut/BackOut/BackIn`
(= Easing's `Data2..Data6`; cross-checked against `wave5/members-227.tsv`
which already annotated those locals as "twin Easing$ElasticInOut" etc.),
`QuadTreeEntry` (= WeightedQuadtree's), `Condition/FunctionCondition/
ConstantCondition/SupplierCondition` (= ConditionState's),
`ThrowingConsumer/ThrowingRunnable/ThrowingSupplier` (= UncheckedFunctional's).

## Application notes

* 4-column map; nested rows use the applier's `Owner$Inner` form because the
  cluster lists those declarations by bare name (`Data2`, `Extension2`) and the
  same bare names exist in many files.
* `ThreadModuleDump47`, `ThreadModuleDump77` and `ThreadModuleDumpType5`
  top-levels belong to other clusters (02/03). Apply this map **before** those
  maps (nested-first inside this map is already handled by the applier); if
  applied later the three `$` rows for those owners warn-and-skip only.
* Names verified free tree-wide with the applier's `DECL_RE` before writing
  (`FoodValues`, `ValueHolder` were rejected as already taken; renamed to
  `FoodCalculations`, `ValueCell`).
* No `net.minecraft.*` rows, no missing paths. No shaded third-party files in
  this cluster: the `framework/codegen` family is Lunar's own source generator
  (JavaPoet-style) and `ThreadModuleDump18` merely *imports* the external
  `toxi.math.MathUtils` (toxiclibs) — it is not shaded code.
* Known non-class issues seen while confirming evidence (left to member waves):
  `ThreadModuleDump77$Extension2` declares `void runnable(T)` while
  `consumer(...)` calls `var0.method1(var1)`, and `MixinHelper18`-style numeric
  members renamed by earlier passes are inconsistent in the leftover copies.
