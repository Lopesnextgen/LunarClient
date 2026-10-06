# Cluster 43 — `com.moonsworth.lunar.client.util` (45 rows)

Source: `tools/renames/cluster-43.txt` (45 rows). All 45 files exist; no
`net.minecraft.*` rows, no shaded-library rows. Map: `tools/renames/wave5/classes-43.tsv`
(**3 rename rows**, 42 skipped as stale-jar duplicates — see §2).

Dry run (`tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-43.tsv --verbose`):

```
[aware-renames] 3 rows (0 nested); 13684 java files
  com.moonsworth.lunar.client.util: ThreadModuleDump16 -> ServiceEndpoints (53 hits)
  com.moonsworth.lunar.client.util: ThreadModuleDump35 -> JsonAdapters (9 hits)
  com.moonsworth.lunar.client.util: ThreadModuleDump39 -> AuthUtil (47 hits)
[aware-renames] rows=3 skipped=0 files_touched=37 files_renamed=3 mode=dry-run
```

## 1. Renames (3)

| # | old | new | evidence |
|---|---|---|---|
| 1 | `ThreadModuleDump16` | `ServiceEndpoints` | Lunar service base URLs (textures/store CDN, Styngr API, skins, thirdpartycache, authenticator `wss`, asset-server `wss`, `api.*`) with `serviceOverride<Name>` env/property overrides and a `devServices` switch between prod (`lunarclientprod.com`/`mcstats.com`) and stg (`lunarclientdev.com`/`mcstats.cloud`); callers append `/game`, `/game/screenshot/upload`, `/tier-tagger/...` (24 refs) |
| 2 | `ThreadModuleDump35` | `JsonAdapters` | container of Gson adapters: `JsonSerializer` for `Map`/`List`/`Set`/`String` that emit JSON null for empty values, an `ExclusionStrategy` that skips fields unless `@SerializedName` when the declaring class carries `Annotation7`, and a `JsonDeserializer<Range<Integer>>` for `"a..b"`; registered on `FogHandler2`'s `GsonBuilder` |
| 3 | `ThreadModuleDump39` | `AuthUtil` | launcher-IPC Microsoft auth: `AddAccountRequest`/`RefreshAccountRequest` (`lunarclient.gameipc.auth.v1`) plus JWT payload Base64 decode. The class names itself in its own log string — `Slayer.method6("Auth", "Invalid JWT in AuthUtil...")` — and is consumed by `AccountSession`/`AccountBridge`/`Gui19` |

Name availability checked tree-wide (0 declarations) and in
`libs/lunar-renamed-classes.jar` / `lunar.jar` / `genesis-*.jar` (no
`AuthUtil`/`ServiceEndpoints`/`JsonAdapters` entries). `AuthUtil` occurs once
tree-wide, inside its own log message.

## 2. Skipped: stale-jar duplicates (42)

Every skipped row is **the same class as an already-named twin in the same
package**. The twins were produced by the renamer commits `8d81145ce`
(2026-09-14, client.util part 1) and `8d6088f02` (2026-09-14, clientutil3); the
placeholder copies were **re-added** by the 2026-09-15 rescue sweep
`3d38608ff` (`git show --name-status 3d38608ff` lists each as `A`). This is the
same situation as `classes-59.md` §2 ("stale-jar duplicates ... must be
deleted/unified in the repair pass, not renamed").

Verification (two methods):

* structural signature comparison (method name/arity + field types) for all 42
  pairs: identical modulo the class's own name and references to *other*
  placeholder-generation classes (`ThreadModuleDump63`=`Ref`,
  `ThreadModuleDump70`=`IntRectangle`, `ThreadModuleDump90`=`FoodValues`, ...);
* identifier-normalised body diff (`difflib`) — the only hunks are self-name,
  already-renamed nested types (`Data2`→`QuadTreeEntry`/`LabColor`/`BackIn`,
  `JavadocElementType`, `LunarEvent`), other twins, and explicit
  constructors/Lombok noise. The five lowest-ratio pairs (`25`, `47`, `48`,
  `20`, `26`) were inspected line by line and are identical bodies.

Applying the twin name would be skipped by `apply_class_renames_aware.py`
anyway — verified with probe maps:

```
SKIP ThreadModuleDump23 -> ColorUtils: new name already declared
SKIP ThreadModuleDump48 -> LunarConstants: new name already declared
```

| placeholder | already-named twin | refs placeholder / twin | twin created by |
|---|---|---|---|
| `ThreadModuleDump2.java` | `FoodUtils` | 2 / 0 | 8d81145ce |
| `ThreadModuleDump3.java` | `ServerUtils` | 5 / 6 | 8d6088f02 |
| `ThreadModuleDump4.java` | `TickQueue` | 0 / 1 | 8d6088f02 |
| `ThreadModuleDump10.java` | `DisconnectUtils` | 3 / 2 | 8d81145ce |
| `ThreadModuleDump11.java` | `TimeFormatting` | 9 / 27 | 8d81145ce |
| `ThreadModuleDump12.java` | `RegexPatterns` | 0 / 0 | 8d81145ce |
| `ThreadModuleDump13.java` | `PacketUtil` | 4 / 5 | 8d81145ce |
| `ThreadModuleDump14.java` | `EarlyOptions` | 0 / 0 | 8d81145ce |
| `ThreadModuleDump15.java` | `LineMath` | 0 / 1 | 8d81145ce |
| `ThreadModuleDump17.java` | `ColorMath` | 1 / 1 | 8d81145ce |
| `ThreadModuleDump18.java` | `Easing` | 8 / 9 | 8d81145ce |
| `ThreadModuleDump19.java` | `ArrayUtils` | 1 / 17 | 8d81145ce |
| `ThreadModuleDump20.java` | `RunnableHolder` | 1 / 0 | 8d81145ce |
| `ThreadModuleDump21.java` | `FriendStatusUtils` | 2 / 2 | 8d81145ce |
| `ThreadModuleDump22.java` | `NpcUtils` | 7 / 15 | 8d81145ce |
| `ThreadModuleDump23.java` | `ColorUtils` | 76 / 62 | 8d6088f02 |
| `ThreadModuleDump24.java` | `ExpiringWeightedCache` | 1 / 0 | 8d6088f02 |
| `ThreadModuleDump25.java` | `BinarySearchState` | 1 / 0 | 8d6088f02 |
| `ThreadModuleDump26.java` | `MutableValue` | 0 / 1 | 8d6088f02 |
| `ThreadModuleDump27.java` | `StringCursor` | 10 / 5 | 8d6088f02 |
| `ThreadModuleDump28.java` | `FileExplorer` | 1 / 3 | 8d6088f02 |
| `ThreadModuleDump29.java` | `DesktopNotifier` | 0 / 3 | 8d6088f02 |
| `ThreadModuleDump30.java` | `KeyVersionPair` | 1 / 2 | 8d6088f02 |
| `ThreadModuleDump31.java` | `ReflectionUtils` | 1 / 1 | 8d6088f02 |
| `ThreadModuleDump32.java` | `DevFeatureList` | 0 / 0 | 8d6088f02 |
| `ThreadModuleDump33.java` | `PotionUtils` | 3 / 2 | 8d6088f02 |
| `ThreadModuleDump34.java` | `DateUtils` | 9 / 5 | 8d6088f02 |
| `ThreadModuleDump36.java` | `UuidUtils` | 6 / 4 | 8d6088f02 |
| `ThreadModuleDump37.java` | `BackgroundExecutor` | 39 / 30 | 8d6088f02 |
| `ThreadModuleDump38.java` | `FastMath` | 7 / 4 | 8d6088f02 |
| `ThreadModuleDump40.java` | `NumberUtils` | 10 / 28 | 8d6088f02 |
| `ThreadModuleDump41.java` | `RingBuffer` | 0 / 1 | 8d6088f02 |
| `ThreadModuleDump42.java` | `CheckoutUtils` | 3 / 3 | 8d6088f02 |
| `ThreadModuleDump43.java` | `ConfigRangeBuilder` | 2 / 7 | 8d6088f02 |
| `ThreadModuleDump44.java` | `TraitBuilder` | 8 / 2 | 8d6088f02 |
| `ThreadModuleDump45.java` | `HudTimer` | 7 / 18 | 8d6088f02 |
| `ThreadModuleDump46.java` | `TextUtils` | 9 / 13 | 8d6088f02 |
| `ThreadModuleDump47.java` | `WeightedQuadtree` | 1 / 1 | 8d6088f02 |
| `ThreadModuleDump48.java` | `LunarConstants` | 100 / 83 | 8d6088f02 |
| `ThreadModuleDump49.java` | `CursorManager` | 6 / 3 | 8d6088f02 |
| `MixinHelper24.java` | `NamedValue` | 52 / 2 | 8d81145ce |
| `MixinHelper25.java` | `JavadocSpec` | 122 / 4 | 8d81145ce |

* `refs` = files in `src/main/java` containing the simple name, excluding the
  declaring file.
* `MixinHelper24`/`MixinHelper25` counts are inflated: the same simple names are
  also declared in `com.moonsworth.lunar.bridge`, `client.framework.feature.mod`
  and several mixin packages (different classes, other clusters). Actual
  same-package refs are far lower.
* Both generations are live: e.g. `framework/feature/chat/Chat5` imports
  `ThreadModuleDump23` while `ChatEmojiTransformer` uses `ColorUtils`;
  `framework/feature/rewind/Fishing2Loader5` uses `ThreadModuleDump18` while
  replay code uses `Easing`. A rename therefore cannot fix the duplication —
  the repair pass must delete one copy and re-point the references
  (same repair as `classes-59` §2).
* **If the placeholder generation is kept instead of the named one, the
  intended final names are exactly the twin names in the table above.**

## 3. Nested rows owned by sibling clusters (become moot if these copies go)

18 nested rows for my files live in sibling clusters. Cluster 42 holds the
digit-renamed `Data*`: `Data2` in `MixinHelper25`, `ThreadModuleDump17`,
`ThreadModuleDump18`, `ThreadModuleDump35`, `ThreadModuleDump39`,
`ThreadModuleDump47`; `Data3..Data6` in `ThreadModuleDump18` and
`ThreadModuleDump35`. Cluster 45 holds `Type2` in `MixinHelper25`,
`ThreadModuleDump11`, `ThreadModuleDump39` and `Type3` in `MixinHelper25`. If
the repair pass deletes the placeholder copies, those rows must be dropped with
them. For the 3 renamed classes the sibling nested rows must be applied together
with this map (the outer file is renamed here):

* `ThreadModuleDump35$Data..Data6` (cluster 42) → `JsonAdapters$...`
* `ThreadModuleDump39$Data2` (cluster 42) and `$Type2` (cluster 45) → `AuthUtil$...`
* `ThreadModuleDump16` has no nested declarations.

Gap: the digit-free nested `ThreadModuleDump39$Data` / `$Type` (and `$Data` /
`$Type` of other placeholder copies in this cluster) are not listed by
`name_inventory.py`, so no cluster covers them — audit-pass follow-up if those
classes are kept.

## 4. Ambiguities / follow-ups

* `ThreadModuleDump16`: no real name leaks in the jar (obf
  `RIROICHCRROROHCCROOCCCCOCHCCRI` in `lunar.jar`; `lunar-client-names.tsv` has
  no entry) — `ServiceEndpoints` is descriptive. Alternatives considered:
  `ServiceConfig`, `LunarEndpoints`. The class also carries the fixed
  `textures.lunarclientcdn.com` / `store.lunarclient.com` constants and the
  `mcstats.com` host, so a broader `Services` would also fit.
* `ThreadModuleDump35`: no leaked name; `JsonAdapters` describes Gson
  (de)serializer plumbing only. `GsonAdapters` was rejected to keep the
  `Json*` family consistent with `JsonDateAdapter`/`ExtraCodecs`.
* `ThreadModuleDump39` → `AuthUtil`: the only textual evidence is its own log
  message, but the same pattern holds for the already-named twin
  (`PacketUtil.java` logs with the tag `"PacketUtil"`), so this is the real
  Lunar name. Kept exactly as leaked (not `AuthUtils`).
* All three renames are plain static utilities; no jar-signature coupling, so
  they are safe to apply standalone (dry run: 0 skipped).
* Provenance note for the shaded-lib workstream: `MixinHelper24`/`MixinHelper25`
  belong to the self-contained spec/codegen family (`MixinHelper*`,
  `AnnotationSpec`, `FieldSpec`, `MethodSpec`, `JavadocSpec`, `SourceEmitter`,
  `JavaFileWriter`, `AbstractSpec`, `SpecBuilder`, `TraitBuilder`, `NamedValue`)
  that **nothing outside `client.util` references** and that resembles a
  JavaPoet-style source generator (it is not stock JavaPoet: extra
  `JavadocSpec`/`TraitBuilder`/`SourceEmitter`). It looks like bundled build
  tooling rather than runtime code; worth a `match_libs.py` pass to confirm it
  is not a relocated library before those names are finalised. Wave 5 keeps the
  already-applied names (`NamedValue`, `JavadocSpec`) and skips the two
  duplicate copies here.
