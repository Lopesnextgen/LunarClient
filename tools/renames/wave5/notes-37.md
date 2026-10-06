# Cluster 37 — `com.moonsworth.lunar.bridge` (45 rows)

Source: `tools/renames/cluster-37.txt` (45 lines). Map: `tools/renames/wave5/classes-37.tsv`.

Dry run (`tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-37.tsv`):

```
[aware-renames] 44 rows (0 nested); 12545 java files
[aware-renames] rows=44 skipped=0 files_touched=759 files_renamed=44 mode=dry-run
```

44 renamed, **1 skipped** (`Bridge_18`, see §3). All 45 files exist; the package is
not `net.minecraft.*` and contains no shaded third-party code — every class is
Lunar's own *MoonBridge / Ichor bridge-generation* subsystem (ASM emitters that
compile `@Annotation2`-gated bridge methods into the target classes at runtime).

## What this slice of the package actually is

`normalize-renames.tsv` shows each class came from a **different copy of the bridge
engine** before the package flattening: `mixinMore/mixinExtra` (handlers/iterators),
`mixinInternal`, `mixinShared`, `mixinSupport`, `mixinMain`, `mixinCommon`,
`mixinSide`, `mixinGeneral`, `mixinMore/mixinMisc`. The 2026-09-16 rescue sweep
then restored the pre-rename copies of the same logical classes next to the ones
previous naming waves had already renamed, so **37 of the 44 rows are the
duplicate snapshot of a class that already carries a name in the same package**
(see §2). The other 7 have no named twin in the tree (unique types, plus the two
reverted/no-twin entries `Bridge_10` and `Bridge_15`).

Evidence used: class bodies (ASM opcodes emitted, annotations, field types,
string literals), the already-named twins, `Bridge.java`'s typed static bridge
registry, and the real Lunar API visible in `ReplayModMixins`
(`BridgeManager.getGlHelper()`, `RenderContextLegacy.ofLegacy()`,
`bridge$getOpenContainer()`, `PacketFactory`).

## §1 Notable renames

| old | new | why it matters |
|---|---|---|
| `BridgeExtension_9` | `AbstractRenderContext` | 1,214 references in 337 files: the abstract render context (`translate/scale/rotate/push/pop/color`), base of `BridgeExtension2_11` (modern) and `BridgeExtension3_5` (`RenderContextLegacyTransform`'s parent, i.e. `RenderContextLegacy.ofLegacy()`). No twin — this one is unique. |
| `BridgeIterator5` | `GroupingEmitter` | resolves a signature-compatible "grouping" through the static helper methods of the upstream `GroupingUtil` ("Could not find a valid grouping to convert from %s to %s!"). No twin. |
| `BridgeType_8` | `KeyCode` | 250-reference keyboard/mouse key enum (`KEY_0..KEY_MOUSE16`, VK ids, `getShortName`, `fromMouseButton`) that drives `KeyboardBridge`/`KeyboardBridgeImpl`; no twin. |
| `BridgeType_15` | `GlslUniformType` | 23-reference GLSL uniform type enum (`vec/mat/dvec`, `asGlslType()`/`asMojangType()`); no twin. |
| `BridgeExtension_9`'s family | emitter names | `ObjectAllocationEmitter` (NEW+DUP), `FieldRead/WriteEmitter`, `ParameterDefaultEmitter` (`@Default` lens), `MethodReturnEmitter`, `ReceiverEmitter`, `SignatureEmitter`, `ChainEmitter`, `SequenceEmitter` — descriptive names, the real upstream names are not recoverable (Ichor is closed source; crash logs only expose `com.moonsworth.lunar.ichor.*`). |

## §2 Duplicate copies (ambiguous rows)

These rows are the **pre-rename snapshot** of a class that already carries a name
in the same package (the rescue sweep restored the stale-jar copies). Applying the
exact twin name would collide (`apply_class_renames_aware.py` would produce two
declarations in `com.moonsworth.lunar.bridge`), so the map keeps the tree unique
with a distinct but equivalent name. Most pairs are **both live** — different
call sites use different copies — so the real fix is a merge, not a rename; the
"intended final name" is the twin name.

| cluster class | new name here | already-named twin | refs here / refs twin |
|---|---|---|---|
| `BridgeHandler2` | `ObjectAllocationEmitter` | `NewInstanceEmitter` | 1 / 1 |
| `BridgeHandler3` | `FieldReadEmitter` | `FieldGetEmitter` | 1 / 1 |
| `BridgeHandler4` | `FieldWriteEmitter` | `FieldSetEmitter` | 1 / 1 |
| `BridgeHandler5` | `ParameterDefaultEmitter` | `ParameterConversionEmitter` | 1 / 1 |
| `BridgeHandler6` | `MethodReturnEmitter` | `ReturnEmitter` | 1 / 1 |
| `BridgeHandler_2` | `ReceiverEmitter` | `ThisEmitter` | 3 / 1 |
| `BridgeIterator2` | `SignatureEmitter` | `InvocationEmitter` | 1 / 0 (twin orphan) |
| `BridgeIterator3` | `ChainEmitter` | `PathEmitter` | 4 / 0 (twin orphan) |
| `BridgeIterator4` | `SequenceEmitter` | `CompositeEmitter` | 2 / 0 (twin orphan) |
| `BridgeType2` | `ShaderStage` | `ShaderStageType` | 0 / 0 (both orphan) |
| `BridgeType3` | `PolygonDrawMode` | `PolygonMode` | 5 / 3 |
| `BridgeType4` | `ShadingModel` | `ShadeModel` | 6 / 5 |
| `BridgeType5` | `TextureSlot` | `TextureUnit` | 0 / 0 (both orphan) |
| `BridgeType6` | `VertexAttributeType` | `VertexElementType` | 0 / 0 (both orphan) |
| `BridgeType_10` | `DepthComparison` | `DepthFunction` | 7 / 6 |
| `BridgeType_11` | `TexturePixelFormat` | `TextureFormat` | 11 / 7 |
| `BridgeType_12` | `DepthTestMode` | `DepthTestFunction` | 9 / 4 |
| `BridgeType_13` | `ContainerClickType` | `ClickTypeBridge` | 7 / 9 |
| `BridgeType_14` | `BufferUsageHint` | `BufferUsage` | 0 / 0 (both orphan) |
| `BridgeType_16` | `BufferBindingTarget` | `BufferTarget` | 2 / 1 |
| `BridgeType_17` | `BufferBuildMode` | `BufferMode` | 33 / 27 |
| `BridgeType_2` | `PackFormatVersion` | `ResourcePackFormat` | 0 / 2 |
| `BridgeType_3` | `SoundAttenuationType` | `AttenuationTypeBridge` | 4 / 1 |
| `BridgeType_4` | `PacketDirection` | `PacketDirectionBridge` | 10 / 6 |
| `BridgeType_5` | `ItemTransformType` | `ItemTransformTypeBridge` | 2 / 1 |
| `BridgeType_7` | `TransparencyMode` | `TransparencyType` | 8 / 3 |
| `BridgeType_9` | `ColorChannelOrder` | `PixelFormat` | 9 / 6 |
| `Bridge_10` | `NativeImageBridge` | (none; `Bridge8Extension33.bridge$getNativeImage()` returns it) | 6 / — |
| `Bridge_11` | `GLSyncBridge` | `GlFenceSyncBridge` | 2 / 3 |
| `Bridge_12` | `ContainerMarker` | `ContainerBridge` | 11 / 9 |
| `Bridge_13` | `TargetPathMember` | `MixinTargetMember` | 1 / 2 |
| `Bridge_14` | `WitherSkeletonBridge` | `EntitySkeletonBridge` | 0 / 1 |
| `Bridge_15` | `BytecodeEmitter` | (none; name was applied in `classes-bridge3` and reverted as jar-coupled) | 23 / — |
| `Bridge_16` | `PacketFactoryBridge` | `PacketFactory` | 8 / 8 |
| `Bridge_17` | `TileEntityRendererBridge` | `BlockEntityRendererBridge` | 2 / 1 |
| `Bridge_19` | `LevelRendererBridge` | `LevelRendererVisibilityBridge` | 0 / 0 (both orphan) |
| `Bridge_2` | `CustomBufferSourceProvider` | `CustomBufferSourceHolder` | 0 / 0 (both orphan) |
| `Bridge_20` | `NetworkConnectionBridge` | `NetworkManagerBridge` | 10 / 4 |
| `Bridge_21` | `MovementStateBridge` | `MovementInputBridge` | 9 / 3 |

Notes:

* Token/identifier-set diffs confirm each pair is the same decompiled class; the
  snapshot copy is recognisable by `@com.moonsworth.lunar.ichor.Annotation2`
  (renamed `VersionGate` later), old `MixinHelper*` type references and numbered
  locals, the named twin by real types (`ClassTypeSignature`, `MixinHelper*` →
  real builders) and `@VersionGate`.
* `BridgeType_13` avoids `ClickType` on purpose: `net.minecraft.inventory.ClickType`
  is imported by the container mixins, so the bridge copy is named
  `ContainerClickType` to prevent a simple-name shadowing question.
* If the merge pass deletes the twins instead of the copies, keep these names;
  the map already leaves every referenced symbol resolvable.

## §3 Skipped (1) — no evidence

| class | why |
|---|---|
| `Bridge_18` | Truly empty marker (`javap`: zero members, no interfaces, no annotations) with **zero references in src** and no implementor found in the stale jar either. Same call as cluster 07's `Bridge4`/`Bridge6`: naming an empty marker with no usage would be invention, not evidence. Revisit after the merge pass deletes the duplicate copies. |

Not skipped as shaded third-party: none. All rows are first-party
`com.moonsworth.lunar.bridge` / Ichor code (the shaded Jackson/Mixin/Guava trees
live in other package clusters).

## §4 Ambiguities / lower-confidence rows

* `BridgeExtension_9` — no twin and no single carrier: it is the abstract base of
  both the modern and the legacy render contexts. `AbstractRenderContext` is
  descriptive; the real API name for the concrete legacy class
  (`BridgeExtension3_5`) is `RenderContextLegacy` (proven by
  `ReplayModMixins/.../AbstractGuiTimelineTimeMixin_v1_8`), so if that cluster
  names `BridgeExtension3_5` `RenderContextLegacy`, the base could later be
  folded to `RenderContextBridge`.
* `Bridge_14` / `EntitySkeletonBridge` — the interface only adds
  `bridge$isWitherSkeleton()` (`max = 1`); `EntitySkeletonMixin` is the only
  implementor. `WitherSkeletonBridge` names its discriminator, not the entity.
* `Bridge_10` — no twin; the name follows the `bridge$getNativeImage()` call site
  and the `NativeImage` API shape (`getPixels`/`getFormat`/`writeToFile`).
* `BridgeType_2` — both copies are orphaned; `ResourcePackFormat` is the live name
  for other users, so this row is only kept for tree uniqueness.
* `Bridge_15`/`BridgeHandler*`/`BridgeIterator*` — all of the emitter family is
  referenced by the jar-coupled `Bridge_67`/`Bridge2_35` chain; previous waves
  reverted `Bridge_15` → `BytecodeEmitter` for stale-jar signature coupling.
  `ecj_diff.py` will confirm whether this time the rename can land; the applier
  updates every source reference, so only the jar-only classes can complain.

## §5 Findings worth keeping

1. **This is the Ichor bytecode-generation engine, not the MC bridge API.**
   `Bridge_15` is an emitter SPI; handlers emit NEW+DUP, GET/PUT field, `@Default`
   null-checked lens conversions, POP/POP2 and RETURN. `BridgeIterator5` is the
   only one that resolves "groupings" via `GroupingUtil` static methods.
2. **The named twins came from `Bridge.java`'s typed static registry** — e.g.
   `GlFenceSyncBridge` (field), and `Bridge.method59()` returns `PacketFactory`
   (`Bridge_16`). `Chest.java` even embeds the real call shape as a debug string:
   `"BridgeManager.getGlHelper().bridge$glGet"`.
3. **The `Bridge_*` rows are the public MoonBridge API recovered from real Lunar
   sources**: `RenderContextLegacy.ofLegacy()` (`BridgeExtension3_5`),
   `bridge$getOpenContainer()` → `ContainerMarker`/`ContainerBridge`, movement
   input (`MovementStateBridge`/`MovementInputBridge`), network manager
   (`NetworkConnectionBridge`/`NetworkManagerBridge`), level-renderer visibility
   and packet factory.
