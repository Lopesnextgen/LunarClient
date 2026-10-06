# members-obf3 — obf/placeholder member renames (bridge, ichor, legacy, network, files, forge, mixin, replaymod, v1_*, loader, altmanager, webosr)

Subagent: member-rename researcher (obf members). Output: `tools/renames/wave5/members-obf3.tsv`
(940 rows: 544 methods / 396 fields, 64 owners). No sources were edited.

## 1. What was produced

| package | rows | basis |
|---|---|---|
| `com/moonsworth/lunar/network` (incl. `network.mixin` models) | 849 | OpenAPI-generator models: `@SerializedName("json_key")` + generated body shapes |
| `com/moonsworth/lunar/files` | 88 | `@SerializedName` models (AssetIndex / VersionInfo / VersionManifest) + Lorenz real sources |
| `com/moonsworth/lunar/bridge` (annotations) | 3 | version-map annotation elements |

Every row was re-validated against the worktree: the owner file exists, the old
name is declared exactly once in it (the applier's own `drop_multi_decl_rows`
rule) and the new name does not collide with a member already declared in that
owner. 76 further rows produced by the generators were **removed** because the
declaration count was not 1 (files that declare a nested class with the same
simple `methodN` slot); see section 2.

Evidence per row is in the last column and names the exact JSON key, the
generated body (`this.x = arg; return this;` fluent setter / `return this.x;`
getter / `this.x.add(...)` item-adder / `getGson().fromJson|toJson`) or the
annotation declaration.

### network traffic-event models (the bulk)

`network/*.java` contains ~46 OpenAPI-generated analytics models
(`BaseGameEvent`, `GameBatchEvent`, `GameRewind*EventData`, `MixinHelper*`
copies…). Naming is deterministic from the generated code:

* `public static final String fieldN = "json_key"` -> `SERIALIZED_NAME_JSON_KEY`
* `@SerializedName("json_key") private T fieldN` -> `jsonKey` (lowerCamelCase of the key)
* generated accessors -> `getX`, `setX`, fluent setter `x` (returns the owner),
  list item-adder `addXItem`, plus the generator helpers `toIndentedString`
  (body `return obj == null ? "null" : obj.toString().replace("\n", "\n    ")`),
  `toJson` / `fromJson` (`MixinHelper7.getGson().toJson/fromJson`).
* the `private static final long field1 = 1L` serialVersionUID was left
  (`field1` -> not emitted) — the applier would need `serialVersionUID` via a
  single row per class; skipped on purpose.

## 2. How to apply

```
tools/apply_member_renames.py --map tools/renames/wave5/members-obf3.tsv          # dry run
tools/apply_member_renames.py --map tools/renames/wave5/members-obf3.tsv --apply
```

Expected applier behaviour (by design of the tool):

* rows whose member is declared more than once in the owner file are dropped
  by `drop_multi_decl_rows`; **76 such rows were already filtered out of the
  map** — files that declare a nested class using the same `fieldN`/`methodN`
  slot as the outer class. Affected owners (count in brackets):
  `network.MixinHelper22/26` (5 each), `network.GameRewindProjectExportEventData`
  and `network.GameRewindRecordingEventData` (4 each), `network.MixinHelper27`
  (4), `network.MixinHelper164` (3), and 28 further `network.MixinHelper*` /
  `network.NetworkIterator*` / `network.MixinHelper` owners (2-3 each), plus
  `files.mixin.Files`, `files.mixin.ResolvedVersion` (2 each) and
  `files.PredefinedMappingBundle.field4` (0 declarations — the field is only
  assigned, never declared, so the source itself is broken).
  A signature-aware pass (or per-`$Inner` owner keys) is needed for those.

## 3. Obf rows in scope — classification (members-obf.tsv)

Every scope row from `member-spree-inventory` was checked against the current
tree. **22 of 25 are false positives** (genuine UPPER_SNAKE constants/MCP
fields, not obfuscation) and are deliberately *not* renamed:

| row | verdict |
|---|---|
| `ActiveRenderInfo.OBJECTCOORDS`, `ItemStack.DECIMALFORMAT`, `TileEntityBannerRenderer.BANNERTEXTURES`, `WorldGenDungeons.CHESTCONTENT/SPAWNERTYPES` | genuine MCP 1.8 names |
| `BridgeType2_2.CONFIGURATION`, `ConnectionProtocol.CONFIGURATION`, `ConnectionState.DISCONNECTED`, `DriverScreen.SINGLEPLAYER`, `FogLoader22.ALPHABETICAL`, `MarkersType_3.SINGLEPLAYER`, `SkyblockTicTacToe.UNDETERMINED`, `HorsestatsType8.STRIKETHROUGH`, `ChatFormatting.STRIKETHROUGH`, `EnumChatFormatting.STRIKETHROUGH` | genuine enum/constant names |
| `Horsestats3.IHCHIIICCCHCCIROHOHROHOOHRCHRC` | annotation *element use site*, not a field: fixed via the annotation rows below |
| `HorsestatsHandler[2-8].HORHROIOIOICIRHIOCOICHHHIHCIIO` / `OHOOORICRHIIIIRHCICICOCHROICRC` / `HHRROIIHRRICIIHIIHICRHHRHOHHOO` / `ICRHORIIHOHROHOHOCOOHOOCOORRHO` | call sites of *overloaded* static hit-result factories; the applier drops multi-declaration rows, so these need a signature-aware pass (below) |

### Handoff: the `bridge/horsestats` hit-result family

While this map was being prepared, class-rename wave `0d0cca2ea` renamed the
whole family (`HorsestatsHandler` -> `MissResult`, `HorsestatsHandler2` ->
`BiomeHitResult`, `HorsestatsHandler3` -> `ChunkHitResult`,
`HorsestatsHandler4` -> `SprayHitResult`, `HorsestatsHandler5` ->
`ClientEntityHitResult`, `HorsestatsHandler6` -> `EntityHitResult`,
`HorsestatsHandler8` -> `CosmeticHitResult`, `Horsestats7` -> `LightHitResult`,
`HorsestatsHandler`+`7` merged into `MissResult`, `Horsestats15` -> `Vec3Bridge`,
`Horsestats20` -> `Vector3iBridge`, `HorsestatsType4` -> `MovingObjectHitType`,
`Horsestats14` -> `ResourceLocationBridge`, `Horsestats3` -> `CryptManagerBridge`,
`HorsestatsType8` -> `AdventureChatFormatting`). Members there are still lazy;
the intended names (members were drafted, then dropped because the owners moved
under another agent) are:

* `Horsestats_3` (still unrenamed) = the hit-result *interface*:
  `method1` -> `getType`, `method3` -> `getSpecialized`, `method4` -> `isMiss`,
  `method5` -> `ifPresent`, `method6` -> `ifPresentOrElse`
  (`isSuccessful()` is already real and pins the semantics).
* `MovingObjectPositionHitResult`/`MissResult` (was `HorsestatsHandler`):
  `method5` -> `getLocation` (Vec3), `method6` -> `getBlockPos`,
  `method7` -> `getDirection`, `method8` -> `isInside`,
  `field1` -> `ZERO_LOCATION`, `field2` -> `ZERO_BLOCK_POS`;
  `Data.method5(BridgeExtension)` -> `distanceTo` (squared distance to an
  entity, same contract as vanilla `HitResult.distanceTo(Entity)`).
  The static factories `method7/method8/method9` (and the obf tokens named
  above) are overloaded and must be renamed with a signature-aware pass.
* `Vec3Bridge` (= Vec3) and `Vector3iBridge` (= BlockPos) keep MCP names
  (`getX`/`add`/`distanceTo`/...); ~62 + ~87 lazy members remain there.

### Dangling obf token

`client/click/lotusfish/Bridge7Iterator.java:68` calls
`IHIIRCRIIHICHHRIOOHIROHRHHRCHI()`; `member-renames.tsv` maps that token to
`.../SkyblockArrowAlign$X.method1()V`, and the tree's own
`client/mod/misc/SkyblockArrowAlign$ArrowFrame.method1()` is the only matching
declaration — but the call is in an unrelated subclass, so the receiver chain
cannot be resolved from the source alone. Left for a manual lookup (the
`click/holograms/Bridge7Iterator` superclass is owned by the client-lotusfish
cluster).

## 4. Bridge annotation elements (needed for compilation, needs a token pass)

The 48 files under `com/moonsworth/lunar/bridge/**` still write the obfuscated
element names while the annotations declare `method1()`:

```
@Annotation(OIRHICCHORIRORCCOOCRRRHRRCRCCI = { @BridgeVersionMapping(version = 0,
    IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("worldObj")) })
```

Map rows rename the *declarations*
(`Annotation.method1` -> `mappings`, `BridgeVersionMapping.method1` -> `targets`,
`BridgeVersionTarget.method1` -> `targets`). `apply_member_renames.py` cannot
rewrite annotation element names (no receiver), so the use sites need either

```
tools/fix_obf_tokens.py --apply            # resolves TOKEN against member-renames.tsv -> method1
```
followed by re-running this map, or a tree-wide token rewrite:
`IHCHIIICCCHCCIROHOHROHOOHRCHRC` -> `targets` (247 uses),
`OIRHICCHORIRORCCOCIRCRHRHOHRCI...` -> `mappings` (113 uses, exact token
`OIRHICCHORIRORCCOOCRRRHRRCRCCI`). Both tokens only occur in `bridge/**`.

## 5. Skipped / not covered (remaining work in scope)

Genuine *class-level* `fieldN`/`methodN` still present (counted on the
`member-spree-inventory` data, nested-class locals excluded):

| package | remaining |
|---|---|
| `bridge` | ~2539 (incl. `Vec3Bridge`/`Vector3iBridge`/`Horsestats20Extension`/`Horsestats12` families) |
| `legacy` (`mixin`, `wrapper`, `optifine`) | ~1108 (`@Shadow` targets in mixins — rename with the MC member name only) |
| `ichor` | ~864 (Ichor config/data library; not enough reference material to name safely in this pass) |
| `files` | ~270 (Lorenz-derived: the real sources are in `/tmp/opencode/reference/Lorenz` — e.g. `BinaryMappingsFormat` is `KinConstants`, `BinaryMappingsReader` is `KinReader`, `BinaryMappingsWriter` is `KinWriter`, `InheritanceProvider` is `bombe`'s ASM inheritance provider; the `@SerializedName` models in `files/mixin` are done) |
| `forge` | ~179 |
| `replaymod` | ~150 |
| `client/network`, `client/mod`, … | not in this map's scope (other clusters) |

`mixin`, `v1_7`, `v1_8`, `v1_12`, `altmanager`, `webosr` contain **no genuine
class-level lazy members** — the `fieldN`-shaped rows in `members-lazy.tsv` for
those packages are locals (`flagN`/`numberN`/`varN` mis-classified by the
inventory's field regex) or already renamed; nothing to do.

## 6. Hierarchy / convention notes

* **`files/**` is a merged Lorenz/mapping-bundle library.** `BinaryMappingsFormat`
  and its twin `Files2` are Lorenz `org.cadixdev.lorenz.io.kin.KinConstants`
  (magic 99151942, `VERSION_ONE`, `STANDARD_EXTENSION = "kin"`,
  `toHexString(int)`), the tree classes extend the real
  `org.cadixdev.lorenz.io.*` base classes, so the remaining member names there
  can be copied straight from `/tmp/opencode/reference/Lorenz`
  (`KinReader.readClass`, `KinWriter.writeClass`, `getSortedAndFilteredList`,
  `MappingFormat.createReader/createWriter/getStandardFileExtension`, ...).
  `MappingArtifacts` is the artifact table (`lunar_named_b5_${mcVer}.kin`,
  `mcp_searge_${mcVer}.kin`, `parchment_${mcVer}.json`); the literal file names
  are the evidence for the constant names used here. `MappingArtifacts.field3`
  (`"5"`) was left alone - its meaning is not recoverable from the source.
* No network model overrides a Minecraft/Java method, so no base-name
  constraints apply; `equals`/`hashCode`/`toString` were never touched.
* Constants use UPPER_SNAKE (`SERIALIZED_NAME_*`), matching the OpenAPI
  generator convention; methods lowerCamelCase with `get`/`set`/`is` per the
  shared rules.
* Repeated generated classes exist as copies across packages
  (`network/BaseGameEvent.java` vs `network/mixin/MixinHelper7.java`); the map
  keys owners by FQCN so both copies get the same names — verify after applying
  that the duplicate pair did not already diverge.
* Rows were validated with a resolver that re-reads each owner file: no target
  collides with an existing same-kind member, and every owner file exists in the
  current worktree.
