# Cluster 15 — forge mixins + OpenAPI client + vanilla structure + `client.fov` (45 classes)

Source revision: `tools/renames/cluster-15.txt` md5 `e458e4769ba59a60401aec92c487c935`
(45 rows = 12 `com.moonsworth.lunar.forge.mixin` + 12 `net.minecraft.world.gen.structure`
inner classes + 11 `com.moonsworth.lunar.network.mixin` + 10 `com.moonsworth.lunar.client.fov`).
Map: `tools/renames/classes-forgemixin.tsv` md5 `ac18420f475f1058d1b23ed0c6e3a79e`.

> Note: this is **not** the `legacy.wrapper` cluster (`classes-15.tsv`, commit
> `37739947`, see `APPLIED.md`). The cluster files were regenerated afterwards;
> cluster 15 is now this mixed set. The old map remains in git history. This
> map deliberately writes to a *new* file name (`classes-forgemixin.*`) so the
> applied `classes-15.*` pair is not clobbered.

## What this cluster actually is

Four unrelated groups share one cluster because the inventory buckets by
package and these are all small leftovers:

1. **`com.moonsworth.lunar.forge.mixin` — Lunar's Forge-version shim mixins.**
   The module ships two parallel sets of `@Mixin` patches against Forge itself,
   one for the 1.8 Forge runtime and one for the 1.12 Forge runtime. The
   restructure map (`tools/mappings-snapshot/restructure/mixin-renames.tsv`) and
   `package-renames.tsv` pin the provenance precisely:

   | obf package | config | flat deobf | current prefix |
   |---|---|---|---|
   | `forge.RCIOICOHRIOIIRRRROCRHCIICRROHO/HORH…` | `mixins.ichor.forge.v1_8.json` | `forge/mixin/mixin/*` | **no suffix (v1_8)** |
   | `forge.ICRHORIIHOHROHOHOCOOHOOCOORRHO/HORH…` | `mixins.ichor.forge.v1_12.json` | `forge/mixin2/mixin/*` | **`…2` / `…22` / `…3` (v1_12)** |

   Resolving each config's (obfuscated) mixin list through
   `inferred-renames.tsv` → `package-renames.tsv` → `mixin-renames.tsv`
   (`/tmp/opencode/resolve_mixins.py`) gives the exact v1_8 vs v1_12 split. The
   un-suffixed names in the tree (`EventBusMixin`, `FMLCommonHandlerMixin`, …)
   are the **v1_8** members and are *not* in this cluster, so they keep their
   names; every `*Mixin2` / `*Mixin22` / `*Mixin3` row here is the **v1_12**
   twin and is disambiguated with the tree's existing `…V1_12Mixin` convention
   (cf. the applied ReplayMod `…V1_8Mixin` names).

2. **`net.minecraft.world.gen.structure` — not lazy at all.** All 12 inner
   classes the generator flagged (`Corridor2…5`, `Crossing2/3`, `Field1/2`,
   `House1/2/3`, `Stairs2`) are the **canonical MCP 1.8.9 names**. The three
   `.java` files are byte-identical to the 509 reference clients
   (`.../sources/Actinium/...`, `diff` = 0/0/4 lines) and `mappings-vanilla.tiny`
   lists `StructureNetherBridgePieces$Corridor2`, `StructureVillagePieces$Field1`,
   `StructureStrongholdPieces$Stairs2`, … as identity mappings. `Field1/2`,
   `House1/2/3`, `Corridor2…`, `Crossing2/3` are MCP's own numeric names, not a
   decompiler suffix. **Rows skipped on purpose**, nothing to rename.

3. **`com.moonsworth.lunar.network.mixin` — an OpenAPI-generated Java client,
   not mixins.** `MixinHelper` builds a `User-Agent` of
   `OpenAPI-Generator/0.1.0-SNAPSHOT/java`, keeps `basePath` +
   `analytics.lunarclientprod.com` server list, `KeyManager[]`,
   `connectTimeoutMillis`/`readTimeoutMillis`, `serialize`/`deserialize`,
   `buildCall`/`buildRequest` — every member matches the
   openapi-generator `Java` client template class-for-class (see table). The
   real names win over the `MixinHelperN` placeholders.

4. **`com.moonsworth.lunar.client.fov` — a flattened grab-bag, dominated by the
   GeckoLib cosmetic/emote model renderer.** The package name is a leftover from
   the same class of scrambling as `fishing.holograms` (cluster 12): the 46
   quarantined companions (`tools/work/quarantine/src/.../client/fov/`) plus the
   `mixin/` subpackage show it merges at least four subsystems — GeckoLib model
   rendering (`Fov6_3`, `Fov8`, `Fov9`, `Fov10`, `Fov11`, `FovHandler` (emotes),
   `mixin/FovType` = HAT/CLOAK/BODYWEAR/WINGS/GECKOLIB), the colour-saturation
   post-process (`Fov3`, `FovIterator`, `Fov5_2`, `Fov7_2`), the conversation /
   chat embeds (`Fov2_3`, `Fov2_4`, `Fov6Impl`, `Fov4_2`), and GLSL compute
   shaders (`Fov_4`, `Fov4_3`). The `$`-named types here (`Fov2$Data2`,
   `Fov4$Data`, …) are **separate top-level identifiers**, not companions — the
   applier's exact-stem rule (`apply_class_renames.py:149-162`) correctly leaves
   them alone.

## Renames (33 map rows, 21 applied)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `EventBusMixin2` | `GuavaEventBusMixin` | v1_8; `@Mixin(forge.lib.guava.eventbus.EventBus)`, `@WrapMethod post` swallows `Throwable` |
| 2 | `EventBusMixin22` | `GuavaEventBusV1_12Mixin` | v1_12 twin of #1 (`mixins.ichor.forge.v1_12`) |
| 3 | `EventBusMixin3` | `EventBusV1_12Mixin` | v1_12 forge `EventBus`: overwrite `post` + redirect `Logger;error` (v1_8 twin = `EventBusMixin`) |
| 4 | `FMLCommonHandlerMixin2` | `FMLCommonHandlerV1_12Mixin` | v1_12; redirects `setPrivateValue(…,String)` (v1_8 base uses `String[]`) |
| 5 | `FMLNetworkHandlerMixin2` | `FMLNetworkHandlerV1_12Mixin` | v1_12; cancels `fmlClientHandshake`/`fmlServerHandshake` |
| 6 | `FMLSanityCheckerMixin2` | `FMLSanityCheckerV1_12Mixin` | v1_12; sets `fmlLocation` from the Ichor pipeline |
| 7 | `ForgeEventFactoryMixin2` | `ForgeEventFactoryV1_12Mixin` | v1_12; redirects `getPrivateValue(…,String)` to `SaveHandler.playersDirectory` |
| 8 | `ForgeVersionMixin2` | `ForgeVersionV1_12Mixin` | v1_12; `%d.%d.%d.%d+ichor` version string + skip `startVersionCheck` |
| 9 | `LoaderMixin2` | `LoaderV1_12Mixin` | v1_12; redirect `ModDiscoverer.findClasspathMods`, inject mods in `Loader.instance` |
| 10 | `ModDiscovererMixin2` | `ModDiscovererV1_12Mixin` | v1_12; overwrite `findClasspathMods` (v1_8 also overwrites `findModDirMods`) |
| 11 | `ModelLoaderMixin2` | `ModelLoaderV1_12Mixin` | v1_12; swap the `setupModelRegistry` `loadSprites` lambda |
| 12 | `SplashProgressMixin2` | `SplashProgressV1_12Mixin` | v1_12; overwrite `checkThreadState`/`start` |
| 13 | `MixinHelper10` | `Authentication` | openapi-generator `auth.Authentication`: `applyToParams(query,header,cookie,payload,method,uri)` |
| 14 | `MixinHelper102` | `ApiKeyAuth` | openapi `auth.ApiKeyAuth`: `location`/`paramName`/`apiKey`/`apiKeyPrefix` |
| 15 | `MixinHelper103` | `HttpBasicAuth` | openapi `auth.HttpBasicAuth`: `username`/`password` → `Credentials.basic` |
| 16 | `MixinHelper104` | `HttpBearerAuth` | openapi `auth.HttpBearerAuth`: `scheme` + bearer-token `Supplier` |
| 17 | `MixinHelper2` | `StringUtil` | openapi `StringUtil`: `containsIgnoreCase`/`join`/`collectionToString` |
| 18 | `MixinHelper3` | `ApiCallback` | openapi `ApiCallback<T>`: `onFailure`/`onSuccess`/`onUploadProgress`/`onDownloadProgress` |
| 19 | `MixinHelper4` | `ApiResponse` | openapi `ApiResponse<T>`: `statusCode`/`headers`/`data` |
| 20 | `MixinHelper5` | `ServerConfiguration` | openapi `ServerConfiguration`: `URL`/`description`/`variables`; `{var}` substitution |
| 21 | `MixinHelper6` | `ServerVariable` | openapi `ServerVariable`: `description`/`defaultValue`/`allowableValues` |
| 22 | `MixinHelper8` | `Pair` | openapi `Pair`: `name`/`value` query-param holder |
| 23 | `MixinHelper9` | `Configuration` | openapi `Configuration`: `VERSION = "0.1.0-SNAPSHOT"`, `defaultApiClient` |
| 24 | `Fov2` | `BipedModelRenderer` | interface `render(Bridge5_11, ModelBipedBridge, float)`; biped model render hook |
| 25 | `Fov4` | `ShaderPackHelper` | static DFB bind/`restoreViewport` + shader-error line fixup (OptiFine integration) |
| 26 | `Fov8` | `ModelRenderConfig` | GeckoLib render config: `AnimationProcessor`, texture, model, `renderType`, pass, colour, glow, order + `Fov8.Data` builder |
| 27 | `Fov10` | `RenderContext` | entity + molang `Evaluator` + `FovType2` flags + render type; passed to `Condition.applies` / `Transform.transform` |
| 28 | `FovType2` | `RenderContextType` | enum `IN_WORLD`/`IN_PLAYER_MODEL`/`IN_GUI`/`IN_FIRST_PERSON` (the `RenderContext` flag set) |
| 29 | `FovType3` | `RenderPass` | enum `NORMAL`/`EMISSIVE`/`NORMAL_GLINT`; the `pass` field of `ModelRenderConfig` |
| 30 | `Fov_2` | `ShaderStateHelper` | `LunarModelViewMat`/`LunarNormalMat`/`LunarProjectionMat`/`LunarLegacyUISize` uniforms + `areShadersEnabled` |
| 31 | `Fov_4` | `ModelVertexShader` | the four GLSL `#version 430` compute-shader sources for model vertex/transform baking |
| 32 | `Fov_5` | `EmoteModelRenderer` | same render signature as #24; implemented by `FovHandler` (emote model, `gui.emotes`) |
| 33 | `Fov_6` | `RenderEntityHandle` | immutable pair `(Gui2Handler3 entity, boolean)` mirroring `RenderContext`'s entity slot |

## Skipped (12 rows) — already canonical MCP names

No map row is emitted for these; renaming them would *break* the vanilla
correspondence:

| file | inner classes | MCP mapping proof |
|---|---|---|
| `StructureNetherBridgePieces.java` | `Corridor2`, `Corridor3`, `Corridor4`, `Corridor5`, `Crossing2`, `Crossing3` | `mappings-vanilla.tiny` identity rows; file byte-identical to reference clients |
| `StructureVillagePieces.java` | `Field1`, `Field2`, `House1`, `House2`, `House3` | ditto (`StructureVillagePieces$Field1`/`$House1`/…) |
| `StructureStrongholdPieces.java` | `Stairs2` | ditto (`StructureStrongholdPieces$Stairs2`) |

## Collision rows — deferred to the package-aware rewriter

The simple-name applier skips a row when the **old** name is declared in more
than one package (README rule 4; same as `classes-12.md`). These are not map
errors — the *new* names are fine — they simply need an importer-aware rewriter
so unrelated same-named classes are not merged. The status is *tree-state
dependent*: a collision clears as soon as the neighbour is renamed by its own
cluster. On first run 12 rows collided, including three `client.fov` rows
(`Fov2`/`Fov4`/`FovType2` vs the then-unrenamed `client/fov/mixin` bones); once
that subpackage was renamed (`Fov2`→`PhysicsPoint`, `Fov4`→`DistanceConstraint`,
`FovType2`→`Direction2D`) those cleared and the rows now apply. The remaining
**9 network rows** collide with unrelated classes and stay deferred:

| skipped old | also declared in (besides `network.mixin`) |
|---|---|
| `MixinHelper2` | `bridge.mixin`, `client.util.mixin`, `ichor.mixin`, `legacy`, `network`, `replaymod.forge.v1_12.mixin`, `replaymod.forge.v1_8.mixin` |
| `MixinHelper3` | `ichor.mixin`, `legacy`, `replaymod.forge.v1_12.mixin`, `replaymod.forge.v1_8.mixin` |
| `MixinHelper4` | `ichor.mixin`, `legacy`, `network` |
| `MixinHelper5` | `ichor.mixin` |
| `MixinHelper6` | `bridge`, `ichor.mixin`, `network` |
| `MixinHelper8` | `bridge`, `network` |
| `MixinHelper9` | `bridge`, `network` |
| `MixinHelper10` | `network` |
| `MixinHelper102` | `client.inactive` |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
token in `com.moonsworth.lunar.network.MixinHelper2` (a different class) and
`client.inactive.MixinHelper102`, merging distinct types.

## Caveats / follow-ups

* The forge module's **base (v1_8) names are outside this cluster** and stay as
  `EventBusMixin`, `FMLCommonHandlerMixin`, …; only the v1_12 twins are named
  here. If a later pass renames the bases, keep the `…V1_12Mixin` pairing.
* `com.moonsworth.lunar.network.mixin` also contains un-listed `MixinHelper`
  (the generated `ApiClient` itself), `MixinHelper7` (the generated `JSON`
  helper) and `MixinHelperException` (`ApiException`). They are referenced by
  the renamed rows; whoever claims them should use `ApiClient`, `JSON`,
  `ApiException`. `InterceptorImpl`/`RequestBodyImpl`/`ResponseBodyImpl` are the
  custom `ProgressRequestBody`/`ProgressResponseBody` glue.
* The `client.fov` names are role-based inferences: there is no Lunar
  ground-truth name for any of them, and the strongest anchors are content
  (`Fov10`/`Fov8`/`FovType3`/`Fov_4` and `Fov_5` via `FovHandler`). `Fov2`/`Fov_5`
  are byte-identical interfaces with no in-tree implementor for `Fov2`; `Fov_6`
  (and `Fov10.field8`'s `Gui2Handler3`) has no in-tree user at all. `Fov4`'s
  nested `Fov4$Data` (GL objects) actually belongs to the quarantined
  compute-shader class `Fov4_3`, not to the top-level `Fov4` — the exact-stem
  applier keeps them separate.
* `client/fov`'s remaining `FovN`/`Fov_N` classes (not in this cluster) carry
  the same placeholder scheme. The `client/fov/mixin` subpackage was renamed
  concurrently by a neighbouring cluster while this map was being written
  (`Fov2`→`PhysicsPoint`, `Fov4`→`DistanceConstraint`, `FovType2`→`Direction2D`),
  which is why the three `client.fov` collision rows above now apply.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-forgemixin.tsv
# first run  → applied=21 skipped=12 files_touched=37 files_renamed=21 mode=dry-run
# latest run → applied=24 skipped=9  files_touched=40 files_renamed=24 mode=dry-run
#   (skips = the cross-package old-name collisions above; they shrink as the
#    neighbouring clusters land. 12 structure rows are intentionally absent.)
```

New names were re-checked against the applier's declaration index:
`grep -rE "\b(class|interface|enum) NewName\b" src/main/java` → no hits for any
of the 24 rendered new names.
