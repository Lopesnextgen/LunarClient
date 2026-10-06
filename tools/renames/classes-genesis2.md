# Cluster 06 — `com.moonsworth.lunar.genesis` leftovers (33 of 35 classes)

Source revision: `tools/renames/cluster-06.txt` md5 `d9382e792a554686af9d742ac97e34b6`
(35 rows, all present in `src/main/java`).
Map: `tools/renames/classes-genesis2.tsv` (md5 `b0854a3de4f586d81cb379011a503a23`).

This is the residue of the `genesis` module: a minified/relocated copy of
**Guava 29.0-jre** (plus a handful of Lunar bootstrap classes). The
already-applied `classes-genesis.tsv` matched 792 classes to their upstream
Guava identities; these 33 are the ones the coverage matcher could not resolve
(identical fingerprints, or the canonical simple name was already taken by a
sibling Guava class after the packages were flattened into one).

Two rows are **no-ops and intentionally omitted**: `FarmHashFingerprint64` and
`Utf8` are already the correct Guava names.

## How the leftovers were identified

The tree was flattened to a single package, so class identity was recovered
from the **obfuscated package prefix** recorded in
`/tmp/opencode/genesis/obf_to_tree.tsv` (e.g.
`…/genesis/mixin/mixin/mixin2/mixin13/…`), cross-checked against
`/tmp/opencode/genesis/matched3.tsv` and the Guava 29.0-jre jar
(`javap -p` on the candidate). The obf `mixinN` bucket is dominated by one
upstream Guava package:

| obf bucket | Guava package | obf bucket | Guava package |
|---|---|---|---|
| `mixin2` | `base` | `mixin10` | `math` |
| `mixin3` | `primitives` | `mixin11` | `eventbus` |
| `mixin4` | `annotations` | `mixin12` | `net` |
| `mixin5` | `cache` | `mixin13` | `graph` |
| `mixin6` | `html` | `mixin14` | `hash` |
| `mixin7` | `collect` | `mixin15` | `escape` |
| `mixin8` | `util/concurrent` | `mixin16` | `reflect` |
| `mixin` | `io` | `mixin9` | `xml` |

## The 15 `package-info` classes

Fifteen rows are empty `ACC_SYNTHETIC` interfaces carrying
`@CheckReturnValue` and/or `@ParametersAreNonnullByDefault` — i.e. the
relocated Guava `package-info` classes. Guava 29.0-jre has exactly fifteen
(`base cache collect escape eventbus graph hash html io math net primitives
reflect util/concurrent xml`), and the annotation sets line up exactly: only
`cache` and `net` carry `@ParametersAreNonnullByDefault` alone, and only
`math`/`primitives` declare it *before* `@CheckReturnValue` (the other
thirteen put `@CheckReturnValue` first) — which independently confirms the
`mixin5`/`mixin12` and `mixin10`/`mixin3` assignments. Because a flat package
cannot hold fifteen `package-info.java` files, each is named
`<Package>PackageInfo`.

## Renames (33 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `MixinHelper23_3` | `BasePackageInfo` | `com/google/common/base/package-info` (obf `mixin2`) |
| 2 | `MixinHelper_13` | `CachePackageInfo` | `com/google/common/cache/package-info`, only `@ParametersAreNonnullByDefault` (obf `mixin5`) |
| 3 | `MixinHelper12_5` | `CollectPackageInfo` | `com/google/common/collect/package-info` (obf `mixin7`) |
| 4 | `MixinHelper6_4` | `ConcurrentPackageInfo` | `com/google/common/util/concurrent/package-info` (obf `mixin8`) |
| 5 | `MixinHelper_6` | `EscapePackageInfo` | `com/google/common/escape/package-info` (obf `mixin15`) |
| 6 | `MixinHelper5_4` | `EventbusPackageInfo` | `com/google/common/eventbus/package-info` (obf `mixin11`) |
| 7 | `MixinHelper10_7` | `GraphPackageInfo` | `com/google/common/graph/package-info` (obf `mixin13`) |
| 8 | `MixinHelper7` | `HashPackageInfo` | `com/google/common/hash/package-info` (obf `mixin14`) |
| 9 | `MixinHelper_5` | `HtmlPackageInfo` | `com/google/common/html/package-info` (obf `mixin6`, next to `HtmlEscapers`) |
| 10 | `MixinHelper17_3` | `IoPackageInfo` | `com/google/common/io/package-info` (obf `mixin`) |
| 11 | `MixinHelper3_3` | `MathPackageInfo` | `com/google/common/math/package-info`, `@Parameters` first (obf `mixin10`) |
| 12 | `MixinHelper3_9` | `NetPackageInfo` | `com/google/common/net/package-info`, only `@ParametersAreNonnullByDefault` (obf `mixin12`) |
| 13 | `MixinHelper2_7` | `PrimitivesPackageInfo` | `com/google/common/primitives/package-info`, `@Parameters` first (obf `mixin3`) |
| 14 | `MixinHelper4_6` | `ReflectPackageInfo` | `com/google/common/reflect/package-info` (obf `mixin16`) |
| 15 | `MixinHelper_2` | `XmlPackageInfo` | `com/google/common/xml/package-info` (obf `mixin9`, next to `com.google.common.xml.Xml`) |
| 16 | `MixinHelper22_3` | `ConcurrentUninterruptibles` | `util/concurrent/Uninterruptibles` JRE impl (`await/join/get/take/put/sleep/tryAcquireUninterruptibly`) |
| 17 | `MixinHelper28` | `InternalFutures` | `util/concurrent/internal/InternalFutures` (`static tryInternalFastPathGetFailure(InternalFutureFailureAccess)`) |
| 18 | `MixinHelper29_2` | `InternalFutureFailureAccess` | `util/concurrent/internal/InternalFutureFailureAccess` (abstract `tryInternalFastPathGetFailure`) |
| 19 | `MixinHelper25_3` | `ConcurrentPlatform` | `util/concurrent/Platform` (`isInstanceOfThrowableClass`) |
| 20 | `MixinHelper31633_2` | `ConcurrentForwardingBlockingDeque` | `util/concurrent/ForwardingBlockingDeque` |
| 21 | `MixinHelper6222` | `MutableGraph` | `graph/MutableGraph` (`addNode/putEdge/removeNode/removeEdge`) |
| 22 | `MixinHelper63` | `GraphNetwork` | `graph/Network` (`nodes/edges/asGraph/degree/edgesConnecting…`) |
| 23 | `MixinHelper8_2` | `CollectPlatform` | `collect/Platform` (`newHashMapWithExpectedSize` … `checkGwtRpcEnabled`) |
| 24 | `MixinHelper9_7` | `PrimitivesPlatform` | `primitives/Platform` (`checkGwtRpcEnabled`) |
| 25 | `MixinHelperIterator3` | `UnmodifiableIterator` | `collect/UnmodifiableIterator` (final `remove()` throws) |
| 26 | `MixinHelperIterator34` | `UnmodifiableListIterator` | `collect/UnmodifiableListIterator` (final `add`/`set` throw) |
| 27 | `MixinHelperType2_2` | `RecursiveDeleteOption` | `io/RecursiveDeleteOption` (enum `ALLOW_INSECURE`) |
| 28 | `MixinHelperType_2` | `FileWriteMode` | `io/FileWriteMode` (enum `APPEND`) |
| 29 | `MixinHelperType_3` | `BoundType` | `collect/BoundType` (`OPEN(false)/CLOSED(true)`, `forBoolean`, `flip`) |
| 30 | `MixinHelper_12` | `HashLongAddable` | `hash/LongAddable` (`increment/add(long)/sum`) |
| 31 | `SerializableIterator5` | `ImmutableBiMapFauxverideShim` | `collect/ImmutableBiMapFauxverideShim` (two deprecated `toImmutableMap` shims) |
| 32 | `SerializableIterator_2` | `MathStats` | `math/Stats` (`count/mean/sum/populationVariance/min/max/meanOf/toByteArray`) |
| 33 | `Genesis4` | `Sentry` | Lunar genesis Sentry initializer (`genesis-mappings.tiny` → `genesis/Sentry`; quarantine `Genesis.java` calls `Genesis4.init()/setUser()`) |

## Why some names are qualified

The tree is flattened, so several distinct upstream Guava classes share a
simple name. The already-applied `classes-genesis.tsv` gave the canonical name
to the first occurrence and skipped the rest (14 rows), which is why these
rows exist:

| canonical name | already held by | leftover renamed to |
|---|---|---|
| `Platform` | the empty stub from `MixinHelper34` | `CollectPlatform`, `PrimitivesPlatform`, `ConcurrentPlatform` |
| `Uninterruptibles` | the empty GWT stub from `MixinHelper38` | `ConcurrentUninterruptibles` |
| `ForwardingBlockingDeque` | the `collect` variant (`MixinHelper316332`) | `ConcurrentForwardingBlockingDeque` |
| `LongAddable` | the `cache` variant (`MixinHelper9_4`) | `HashLongAddable` |
| `Network` | `com.moonsworth.lunar.network.Network` | `GraphNetwork` |
| `Stats` | `net.minecraft.client.gui.achievement.GuiStats.Stats` | `MathStats` |

The `Platform`/`Uninterruptibles` stubs are themselves matcher mis-assignments
(empty classes in the `collect` obf bucket that were labelled `util/concurrent`
by the fingerprint matcher). They are **not** in this cluster, so the real
implementations get the package-qualified names above.

## Applier notes

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-genesis2.tsv
# → rows=33 skipped=0 files_touched=85 files_renamed=33 mode=dry-run
```

Use the **aware** applier: ten of the old names are also declared elsewhere
(`MixinHelper8_2`, `MixinHelper_12`, `MixinHelper_13`, `MixinHelper5_4`,
`MixinHelper6_4`, `MixinHelper7`, `MixinHelper_2`, `MixinHelper_5`,
`MixinHelper_6` in `com.moonsworth.lunar.bridge`/`legacy`/`network`/`ichor.mixin`,
and `MixinHelper63` in `com.moonsworth.lunar.network`). The v1 applier would
skip them or, with `--allow-collisions`, corrupt the unrelated classes.

## Caveats / follow-ups

* **`Genesis4` → `Sentry`**: the file currently does `import io.sentry.Sentry;`
  and calls `Sentry.init(...)`. After the rename the file declares
  `com.moonsworth.lunar.genesis.Sentry`, so the single-type import must be
  dropped and the SDK references fully qualified (`io.sentry.Sentry…`) — an
  import fix the applier does not perform. If that is undesirable, name it
  `GenesisSentry` instead.
* The `PackageInfo` names are derived, not upstream: Guava's real simple name
  for these declarations is `package-info`, which cannot exist fifteen times in
  one flattened package.
* `MixinHelper22_3` is the real `Uninterruptibles`; the empty
  `Uninterruptibles.java` already in the tree is a stub (see table above) and
  should eventually be renamed/removed.