# Cluster 227 — member renames (`com.moonsworth.lunar.client.util`, utilities)

Map: `tools/renames/wave5/members-227.tsv` (216 rows: 101 methods, 115 fields,
over 24 top-level owners + 9 nested owners). All names lowerCamelCase; constants
UPPER_SNAKE; no `main`/`equals`/`hashCode`/`toString` touches.

Verification run against the *pre-application* tree (241-row draft) with
`python3 tools/apply_member_renames.py --map tools/renames/wave5/members-227.tsv`:

```
[members] 241 rows over 31 owners
[members] after multi-decl filter: 218 rows
  !! no source for ...ShaderPreprocessor$Data / ThreadModuleDump$Data /
     $LoadedModule / $ResolvedAddress / $ThreadStartAddress /
     ThreadModuleDump17$Data, $Data2 / ThreadModuleDump18$Data2, $Data4
[members] declarations to rename: 190
[members] accesses to rename: 732 (dry-run)
```

After that check, **25 field rows were removed** from the map: they rename a
field whose body is also read *bare* inside its declaring class
(`field2.matcher(...)`, `field2.set(...)`, `return field3;`). `apply_member_renames.py`
renames declarations plus `this.`/`super.`/receiver-qualified accesses only — it
has no bare-field pass — so those rows renamed the declaration and left the
reads dangling (compile errors). They are listed in §6; drop them in if a
bare-field pass lands. The 216-row map has **0** remaining fields with bare use
(checked per owner file, including nested classes) and no `import static` /
reflection-by-name usage of the renamed members.

## 1. Twin pairs (critical context)

Every ThreadModuleDumpN owner here is a byte-equivalent "stale-jar twin" of an
already-named class (or vice versa) — `wave5/notes-43.md` §2 lists the pairs.
The map only targets cluster-227 owners; **the named twin must receive the same
rows** (or the repair pass must delete one copy) for the two generations to
converge:

| cluster-227 owner | named twin (`notes-43`) | evidence in map |
|---|---|---|
| `RomanNumeralParser` | `ThreadModuleDump83` | regex fields, getFromChar enum |
| `RomanNumeralUtils` | `ThreadModuleDump75` | cache maps + VALUES/NUMERALS |
| `ScreenProjection` | `ThreadModuleDump64` | matrices, worldToScreen/screenToRay |
| `ShaderPreprocessor` (+`$Data`) | `ThreadModuleDump94` | LUNAR-SHADER-DEFINITION-MARKER |
| `TextSanitizer` | `ThreadModuleDump58` | isAllowedCharacter/sanitize |
| `TextUtils` | `ThreadModuleDump46` | wrapText/player-name codec |
| `StringCursor` | `ThreadModuleDump27` | Brigadier StringReader API |
| `ServerUtils` | `ThreadModuleDump3` | scoreboard + brand detection |
| `RunnableHolder` | `ThreadModuleDump20` | onOpen/onClose hooks |
| `ThreadModuleDump2` | `FoodUtils` | canEat/saturation/REGEN_EXHAUSTION |
| `ThreadModuleDump10` | `DisconnectUtils` | disconnect world + integrated server |
| `ThreadModuleDump11` (+`Type2`) | `TimeFormatting` (`TimeFormat`) | duration formats |
| `ThreadModuleDump12` | `RegexPatterns` | name-validation regex literals |
| `ThreadModuleDump13` | `PacketUtil` | "PacketUtil" log tag |
| `ThreadModuleDump14` | `EarlyOptions` | performance.json noErrorContext |
| `ThreadModuleDump15` | `LineMath` | slope/intercept/intersection |
| `ThreadModuleDump17` (+`$Data`,`$Data2`) | `ColorMath` (`$LabColor`) | CIE Lab/LCH constants |
| `ThreadModuleDump18` (+`$Data2`,`$Data4`) | `Easing` (`$ElasticInOut`…) | easing curves + averages |
| `ThreadModuleDump19` | `ArrayUtils` | arrayEquals/arrayToString |

`ThreadModuleDump` (JNA thread dumper), `SupplierExtension`, `SpecBuilder` and
`SourceEmitter` have no twin.

## 2. Skipped / not in the map

* **Stale owner** `ThreadModuleDump16` (54 inventory rows): file was renamed
  `ServiceEndpoints` by `wave5/classes-43.tsv`; the rows are moot.
* **`SourceEmitter.method1(int)`** (+ its param row): the family
  (`MixinHelper2` twin, `JavadocSpec`, `FieldSpec`, `MethodSpec`, `TypeSpec`,
  `NamedValue`, `AnnotationSpec` + nested `AnnotationLiteral`, `MixinHelper23`)
  spans sibling clusters 222–226. Renaming the interface alone would break the
  implementors; the applier's multi-decl guard would drop the two duplicate
  declarations (`AnnotationSpec`/`MixinHelper23` each declare `method1` twice),
  so the family cannot be renamed safely from this cluster. Suggested name for
  whoever owns the family: **`emit(int indent)`** (JavaPoet `TypeSpec.emit`).
* **All `P` rows (323)** and the ~408 `F` rows that are really locals
  (`varN`, `numberN`, `valueN`, `textN`, `flagN`, `numberN`) are out of scope:
  `apply_member_renames.py` only renames declarations/accesses per owner, and
  the locals are handled by `clean_locals.py`.
* 25 genuine field rows whose bodies also read the field bare are listed in §6
  (removed from the map until a bare-field pass exists).
* `ThreadModuleDump17`/`18` anonymous-class overrides and the nested-class
  members are covered only through their owners (see §3).

## 3. Application caveats

1. **Nested owners are not resolvable yet.** `apply_member_renames.py`'s
   `owner_file()` cannot map `…ThreadModuleDump17$Data` to the declaring file
   (the nested classes are declared inline, not as `Outer$Inner.java`), and
   `qualify()` returns `ThreadModuleDump17/Data` (slash) for receiver types, so
   both declaration and access passes skip those rows. They are written with the
   `$` fqn so a nested-aware pass can consume them later; today they are inert
   (with a "no source" warning).
   Affected: `ShaderPreprocessor$Data` (16), `ThreadModuleDump$Data` (5),
   `ThreadModuleDump$ResolvedAddress` (5), `$LoadedModule` (3),
   `$ThreadStartAddress` (2), `ThreadModuleDump17$Data` (6), `$Data2` (6),
   `ThreadModuleDump18$Data2` (4), `$Data4` (1).
2. **SIG-SENSITIVE rows** (same old name is used by unrelated nested members in
   one file): `ThreadModuleDump17` `field1..field3`, `method1..method5`;
   `ThreadModuleDump18` `method1..method6`; `ShaderPreprocessor` `field1`,
   `method1..method5`; `ScreenProjection` `method2` (its recursive call reads
   like a second declaration); `ThreadModuleDump2` `method3`
   (`return method3(var0)`).
   The default applier drops all of these (multi-decl guard) — that is the safe
   outcome. **Do not blanket-apply this map with `--allow-multi-decl`**: for
   `ThreadModuleDump17` it would rename the nested `Data`/`Data2` getters to the
   outer colour-conversion names. A signature-aware pass is needed to apply just
   the intended declaration.
   For `ThreadModuleDump18` the opposite holds: every `method1/2/3/4/5`
   declaration is an override of the same easing operation, so a
   signature-aware/whole-family pass renaming them all to
   `map`/`map`/`interpolate`/`interpolate`/`average` is correct.
3. **Multi-decl false positives are enough for the others**: rows kept after the
   filter are applied by the current tool (the pre-application verification run
   counted 190 declarations and ~732 access sites, e.g. `ThreadModuleDump73Type`
   consumes the `ThreadModuleDump18` easing singletons and gets the renamed field
   accesses). Field rows that would leave bare reads dangling are in §6.

## 4. Evidence used per family

* declaring source + every in-tree call site (grep; paths are in the evidence
  column: `EnchantedBookItemValueParser`, `RabbitLevel`, `DungeonPlayerTracker`,
  `Saturation`, `Saturation3`, `Waypoints`, `SelectionHighlightHandler`,
  `ShaderStateHelper`, `CosmeticHighlightShader`, `PlayerStatTracker`, `Tab`,
  `ServerBrandWatcher`, `Chest`, `CommandParser`/`LiteralCommandNode`,
  `FriendChatMessage`, `ExtraCodecs`, `DebugType`, `Fishing3`,
  `SkyblockTpMaze`, `Bridge7Iterator`, `ApolloPacketUtils`, `GuiIterator`, …).
* 509 reference clients for the same feature/naming: Echo / Lithium / Star /
  Tenacity `RomanNumeralUtils`, `venus/com/mojang/brigadier/StringReader`
  (StringCursor API), Echo/Tenacity `CaseUtils.toCamelCase`, Zyth
  `Strings.capitalizeFirstLetter`, Flap/Dog/Lirium easing tables, OptiFine
  `net/optifine/util/ArrayUtils` (checked; different methods, used only to
  confirm the class purpose).
* Lunar open source (`/tmp/opencode/reference/`): no direct match for these
  utilities (Apollo/Atlas/Mercury/Bombe/Lorenz have no `SupplierExtension`,
  `ScreenProjection` or `ColorMath`); the JavaPoet-like `SpecBuilder` family is
  Lunar's own generator, so the map uses JavaPoet's public builder vocabulary
  (`addJavadoc(s)`, `addAnnotation(s)`, `javadocs()`, `annotations()`).
* Twin bodies in the same package (`FoodUtils`, `Easing`, `ColorMath`, …) were
  used to cross-check member roles and the `Easing.Data/ElasticInOut/Back*`
  nested mapping.

## 5. Notable naming choices

* `DungeonPlayerTracker`/`GuiHandler` parse Hypixel tier numerals → `romanToInt`
  (matches Echo/Lithium/Star `RomanNumeralUtils`); the text rewriter is
  `replaceRomanNumerals`.
* `Easing.method5()` is the **mean** of the curve over [0,1] (verified: t² →
  1/3, t³ → 1/4, 1-cos(tπ/2) → 1-2/π, back-in → 0.108202, …), hence
  `average` / `field34 averageValue` / `method6 getAverageValue`.
* `FoodUtils` constants are vanilla `FoodStats` units: 6.0 exhaustion per heal,
  4.0 per saturation point; `countHeals` is a derived heal simulation (no
  in-tree caller).
* `ThreadModuleDump12` patterns are the generator's identifier validators
  (constant / lower-camel-qualified / UpperCamel), not resource paths.

## 6. Removed rows — blocked on a bare-field-access pass

`apply_member_renames.py` renames field declarations and `recv.field` sites but
not unqualified reads inside the declaring class. These rows are correct but
were removed to keep the map gate-clean; re-add them together with (or after)
the bare references are rewritten (`old` -> `new` inside the owner file):

```
com.moonsworth.lunar.client.util.RomanNumeralParser	F	field1	STRICT_NUMERAL_PATTERN	anchored roman-numeral regex; bare read field1.matcher in method2
com.moonsworth.lunar.client.util.RomanNumeralParser	F	field2	NUMERAL_PATTERN	search regex; bare read field2.matcher in replaceRomanNumerals
com.moonsworth.lunar.client.util.RomanNumeralUtils	F	field1	ROMAN_CACHE	int->numeral memo map; bare field1.computeIfAbsent
com.moonsworth.lunar.client.util.RomanNumeralUtils	F	field2	VALUE_CACHE	numeral->int memo map; bare field2.computeIfAbsent
com.moonsworth.lunar.client.util.RomanNumeralUtils	F	field3	NUMERAL_PATTERN	validation regex; bare field3.matcher
com.moonsworth.lunar.client.util.RomanNumeralUtils	F	field4	VALUES	int[] 1000..1; bare field4.length/index accesses
com.moonsworth.lunar.client.util.RomanNumeralUtils	F	field5	NUMERALS	String[] "M","CM",...; bare field5[index2]
com.moonsworth.lunar.client.util.ScreenProjection	F	field2	modelViewMatrix	field2.set/invert/transform inside update/project
com.moonsworth.lunar.client.util.ScreenProjection	F	field3	worldProjectionMatrix	field3.set/invert/transform inside update/project
com.moonsworth.lunar.client.util.ScreenProjection	F	field4	inverseModelViewMatrix	field4.transform in screenToRay
com.moonsworth.lunar.client.util.ScreenProjection	F	field5	inverseWorldProjectionMatrix	field5.transform in screenToRay
com.moonsworth.lunar.client.util.ScreenProjection	F	field6	modelViewArray	FloatBuffer.wrap(field6) + field2.set(field6)
com.moonsworth.lunar.client.util.ScreenProjection	F	field7	projectionArray	FloatBuffer.wrap(field7) + field3.set(field7)
com.moonsworth.lunar.client.util.ScreenProjection	F	field8	modelViewBuffer	biconsumer6.accept(field8, field9)
com.moonsworth.lunar.client.util.ScreenProjection	F	field9	projectionBuffer	biconsumer6.accept(field8, field9)
com.moonsworth.lunar.client.util.ScreenProjection	F	field10	scaledWidth	field10 = ...getScaledWidth(); arithmetic use
com.moonsworth.lunar.client.util.ScreenProjection	F	field11	scaledHeight	field11 = ...getScaledHeight(); arithmetic use
com.moonsworth.lunar.client.util.ScreenProjection	F	field12	cameraX	field12 = value0; subtracted in worldToScreen
com.moonsworth.lunar.client.util.ScreenProjection	F	field13	cameraY	field13 = value2
com.moonsworth.lunar.client.util.ScreenProjection	F	field14	cameraZ	field14 = value4
com.moonsworth.lunar.client.util.ServerUtils	F	field1	BRAND_PATTERN	bare field1.matcher(text1) in isClientBrand
com.moonsworth.lunar.client.util.ThreadModuleDump2	F	field1	REGEN_EXHAUSTION	bare uses in countHeals (Math.min(var1, field1), ...)
com.moonsworth.lunar.client.util.ThreadModuleDump2	F	field2	EXHAUSTION_PER_SATURATION	bare uses in countHeals (while (var2 > field2), ...)
com.moonsworth.lunar.client.util.ThreadModuleDump14	F	field1	initialized	bare !field1 / field1 = true in isNoErrorContextEnabled/initialize
com.moonsworth.lunar.client.util.ThreadModuleDump14	F	field2	noErrorContext	bare field2 = var3.getAsBoolean() in initialize
```

Fields in the map that *do* keep their receiver (`this.field`, `Owner.field`,
`ScreenProjection.field1`, `RunnableHolder.field1`, `Easing.field28` in
`ThreadModuleDump73Type`, ...) are safe and were applied.

## 7. Already-applied state (for the main agent)

The first 241-row revision was applied to the working tree before this fix
(`git status`: ~80 modified files, e.g. `RomanNumeralParser` now declares
`STRICT_NUMERAL_PATTERN`/`NUMERAL_PATTERN` while `field2.matcher(...)` still
reads `field2`; `ScreenProjection` declares `modelViewMatrix` while the body
still uses `field2`/`field6`). Those files need either

* `git checkout -- <the affected util/chat/... files>` and re-apply the
  corrected 216-row map (recommended), or
* a bare-field sweep on the owner files for the rows in §6.

Files with dangling bare reads from the first revision: `RomanNumeralParser`,
`RomanNumeralUtils`, `ScreenProjection`, `ServerUtils`, `ThreadModuleDump2`,
`ThreadModuleDump14` (all under `client/util`).

