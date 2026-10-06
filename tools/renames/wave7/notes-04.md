# Cluster 04 — `com.moonsworth.lunar.bridge` (50 classes)

Source: `tools/renames/cluster-04.txt` (50 rows, all present under
`src/main/java/com/moonsworth/lunar/bridge/`). No missing paths, no
`net.minecraft.*` rows, no shaded third-party code (the shaded
Jackson/Mixin/Guava trees live in other clusters). Every row is first-party
Lunar bridge/ichor code.

Map: `tools/renames/wave7/classes-04.tsv` (50 rows).

## Verdict: this cluster is the 2026-09-15 rescue generation, not new work

All 50 classes are **stale rescue copies** of classes that already carry a
canonical name in the same package — with one deliberate exception
(`Bridge_52`, section 5). Provenance is uniform and verifiable:

* the canonical file was created by one of the earlier map waves —
  `bridge3` **72c44452a**, `bridge part2/part3` **8a891edde**, `bridge4`
  **740bb7f2**;
* `7adc91305` ("rescue: batch 7 … + quarantine recovery") restored the
  pre-rename file at the old `Bridge_N.java` path (all 50 copies are additions
  in that commit);
* the copies carry the older generation markers (`@com.moonsworth.lunar.ichor.Annotation2`,
  `var1`/`var2` decompiler locals, pre-rename type references such as
  `Bridge2_2`, `PolygonDrawMode`, `Bridge_63`), while the canonical files carry
  `@VersionGate`, renamed parameters and the post-rename type names.

Member-level equivalence was checked per pair: declaration kind, method-name
sets and field sets are identical between the copy and its twin in **all 50
cases** (e.g. `Bridge_56`/`BlocksBridge` both declare exactly `method1..method113`;
`Bridge_45`/`RenderPipelineBridge` both declare the same 25 members and the same
`"Shader supplier is not available on the current version"` string). The copies
are also still live: 34 `implements/extends Bridge_N` sites remain in the tree
(`InventoryPlayerMixin implements Bridge_24`, `RenderPipelineBridgeAdapter
implements Bridge_45`, `BlocksBridgeAdapter implements Bridge_56`,
`BridgeType2_6 implements Bridge_63`, `MarkerModel.Data4 implements Bridge_65`,
`legacy/mixin/BridgeHandler implements Bridge_7`, …), plus plain reference
users. The intended end state is a merge, not a second name.

This matches the wave-7 sibling convention already used by
`tools/renames/wave7/classes-06.tsv` (`MERGE: <commit> renamed to <New>,
<commit> restored this copy …`). Running the standard applier is therefore
expected to skip 49 rows by design:

```
tools/apply_class_renames_aware.py --map tools/renames/wave7/classes-04.tsv
[aware-renames] rows=50 skipped=49 files_touched=12 files_renamed=1 mode=dry-run
```

## Applying the merges

The 49 `MERGE` rows are directly convertible to the `repoint_external.py`
format (`old.package<TAB>Old<TAB>canonical.fqn<TAB>evidence`); the canonical
FQN is just `com.moonsworth.lunar.bridge.<new>`. A derived map was dry-run to
size the work:

```
# 49 MERGE rows from classes-04.tsv (Bridge_52 excluded - see section 5)
python3 tools/repoint_external.py --map <derived> # (dry run)
[repoint] deletes: 49; files rewritten: 165; edit actions: 530
[repoint] 1 bare-name rewrites skipped (collisions):
   bridge/EntityRenderStateBridge.java: com.moonsworth.lunar.bridge.Bridge_61
   -- declares its own EntityRenderStateBridge
```

The one skipped rewrite is the **`Bridge_61` shim** built by `dbfca2d11`:
`EntityRenderStateBridge` (the canonical file) currently declares
`public interface EntityRenderStateBridge extends Bridge_61`. Deleting
`Bridge_61` therefore needs a manual fix-up in that file: **remove the
`extends Bridge_61` clause** before/after the repoint, otherwise the repoint
either leaves a dangling reference or produces a self-extension. No other
canonical file references its own copy (checked all 50 pairs).

Order for the main agent: (1) apply `Bridge_52` rename, (2) apply the merge
map with the 49 rows, (3) edit `EntityRenderStateBridge.java` to drop the shim
`extends`, (4) ECJ gate. `Bridge_52` is intentionally excluded from the merge
map because `FramebufferBuilder` is not declared anywhere today.

## Rows

| old | new | action | live refs |
|---|---|---|---|
| `Bridge_18` | `GuiChestBridge` | merge (delete) | 0 |
| `Bridge_22` | `EntityRegistryBridge` | merge | 4 / 2 files |
| `Bridge_23` | `GlEnum` | merge | 6 / 6 files |
| `Bridge_24` | `InventoryPlayerBridge` | merge | 10 / 5 files |
| `Bridge_25` | `MiscMarkerBridge` | merge (delete) | 0 |
| `Bridge_26` | `INetHandlerBridge` | merge | 19 / 8 files |
| `Bridge_27` | `VertexFormats` | merge | 77 / 7 files |
| `Bridge_28` | `BufferBuilderBridge` | merge | 59 / 19 files |
| `Bridge_29` | `ComputeProgramBridge` | merge | 6 / 2 files |
| `Bridge_3` | `FakeTickBridge` | merge (delete) | 0 |
| `Bridge_30` | `NetworkPlayerInfoBridge` | merge | 13 / 4 files |
| `Bridge_32` | `PropertyMapBridge` | merge | 3 / 2 files |
| `Bridge_33` | `AbstractTextureMultiTexBridge` | merge | 4 / 2 files |
| `Bridge_34` | `LightTextureBridge` | merge | 9 / 4 files |
| `Bridge_35` | `GlObjectBridge` | merge | 19 / 6 files |
| `Bridge_36` | `StyleBridge` | merge | 5 / 2 files |
| `Bridge_37` | `ItemEntityRendererBridge` | merge | 2 / 1 file |
| `Bridge_38` | `TurboBlockBridge` | merge | 12 / 5 files |
| `Bridge_39` | `CommandSenderBridge` | merge | 1 / 1 file |
| `Bridge_4` | `ProtectionEnchantmentsBridge` | merge | 2 / 1 file |
| `Bridge_40` | `ParticleEngineBridge` | merge | 6 / 3 files |
| `Bridge_42` | `ModelBaseBridge` | merge | 1 / 1 file |
| `Bridge_43` | `AuxCoreMarkerBridge` | merge (delete) | 0 |
| `Bridge_44` | `ShaderInstanceBridge` | merge | 2 / 1 file |
| `Bridge_45` | `RenderPipelineBridge` | merge | 88 / 12 files |
| `Bridge_46` | `NameTagRendererBridge` | merge | 8 / 4 files |
| `Bridge_47` | `LightingMarkerBridge` | merge (delete) | 0 |
| `Bridge_48` | `SizeExtendableBridge` | merge (delete) | 0 |
| `Bridge_49` | `InputConstants` | merge (delete) | 0 |
| `Bridge_5` | `WeightedBufferSourceBridge` | merge (delete) | 0 |
| `Bridge_50` | `TiersBridge` | merge | 2 / 1 file |
| `Bridge_51` | `SoundMarkerBridge` | merge (delete) | 0 |
| `Bridge_52` | `FramebufferBuilder` | **rename (re-apply)** | 21 / 11 files |
| `Bridge_53` | `MatrixStackBridge` | merge | 20 / 7 files |
| `Bridge_54` | `EntityDragonBridge` | merge (delete) | 0 |
| `Bridge_55` | `EntityAnimalBridge` | merge (delete) | 0 |
| `Bridge_56` | `BlocksBridge` | merge | 15 / 6 files |
| `Bridge_57` | `CompoundTagBridge` | merge | 92 / 20 files |
| `Bridge_58` | `SpriteAnimationBridge` | merge | 21 / 7 files |
| `Bridge_59` | `FluidStateBridge` | merge | 4 / 2 files |
| `Bridge_6` | `EntityMinecartBridge` | merge (delete) | 0 |
| `Bridge_61` | `EntityRenderStateBridge` | merge + shim fix | 36 / 13 files |
| `Bridge_62` | `EntityRendererBridge` | merge | 8 / 4 files |
| `Bridge_63` | `VertexFormatBridge` | merge | 59 / 20 files |
| `Bridge_64` | `TextureOverrideBridge` | merge | 0 |
| `Bridge_65` | `MarkerPositionBridge` | merge | 36 / 10 files |
| `Bridge_66` | `DisplayListBridge` | merge | 10 / 3 files |
| `Bridge_68` | `GlStateQueryBridge` | merge | 1 / 1 file |
| `Bridge_69` | `EntityListBridge` | merge | 4 / 2 files |
| `Bridge_7` | `KeyEventBridge` | merge | 24 / 11 files |

## 5. The `Bridge_52` exception (not a merge)

`Bridge_52` is a real class with no live twin: the builder that
`Minimap`/`MinimapMap`/`MotionBlur`/`LunarPostEffect`/`ShaderCloakRenderer`
use as `Bridge_52.method2().method1(w,h).method4(texture).method8(true).method3()`
to create a `Bridge3_24` framebuffer. `8a891edde` renamed it to
`FramebufferBuilder`, then `dbfca2d11` reverted it together with the other
stale-jar-coupled renames (`FramebufferBridge`, `BridgeMethodTarget`,
`BytecodeEmitter`, …). Two of those were later re-applied without trouble
(`BytecodeEmitter` in wave 5), and the coupling source is gone: `pom.xml`
(2026-09-16) states the bundled Lunar reference jars are **no longer on the
build classpath** — "every Lunar class now resolves from `src/main/java`" —
and the staging `lunar-all-final.jar` that baked `bridge.Bridge_52` into its
signatures no longer exists (`tools/work/staging/` has no such file). The row
is therefore marked `RENAME (re-apply)` so the applier actually lands it
(12 files touched, 1 file renamed in the dry run).

## Skipped / ambiguous

* Nothing skipped for missing paths or shaded third-party code.
* `tools/mappings-snapshot/lunar-client-names.tsv` contains no bridge entries
  (0 `moonsworth` rows: the readable real-Lunar names are all
  `com.lunarclient.*`, mostly Apollo/protobuf), and
  `tools/mappings-snapshot/restructure/*.tsv` only records the pre-flattening
  origin packages (`normalize-renames.tsv`, e.g. `bridge/mixinShared/mixinSupport/Bridge`
  → `Bridge_52`), so the evidence for the names is the earlier wave maps + the
  class bodies + the live implementors listed per row.
* `libs/multiver-full/lunar.jar` keeps its obfuscated `com/moonsworth/lunar/...`
  names (no `bridge/` entries), so it cannot confirm or refute bridge names; it
  was only used to check for shaded-library duplication (none in this cluster).
* Empty markers whose name is only evidence-backed through their twin and the
  original pre-flattening package (`Bridge_18`→`GuiChestBridge`,
  `Bridge_25`→`MiscMarkerBridge`, `Bridge_43`→`AuxCoreMarkerBridge`,
  `Bridge_47`→`LightingMarkerBridge`, `Bridge_48`→`SizeExtendableBridge`,
  `Bridge_51`→`SoundMarkerBridge`, `Bridge_54`/`Bridge_55`/`Bridge_6`→entity
  markers, `Bridge_5`): all are merges/deletes, so wave-5's objection
  ("naming an empty marker with no usage is invention") no longer applies —
  the target name already exists and is used by the corresponding mixin.
* The `bridge4` rename log pairs identical empty markers differently in
  `git show` (`Bridge_43` appears as `AuxMixinMarker`, `Bridge_47` as
  `SoundMarkerBridge`) because git rename detection cannot distinguish
  empty interfaces; the TSV mapping (and the original pre-flattening package
  quoted in each row's evidence) is authoritative and was used here.
