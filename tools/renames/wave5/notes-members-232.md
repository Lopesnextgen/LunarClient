# members-232 notes — `com.moonsworth.lunar.client.util` cluster (alert / chest / click / util basics)

Cluster: `tools/renames/wave5/member-cluster-232.tsv` — 25 owner files.
Output: `tools/renames/wave5/members-232.tsv` — **242 rows** (89 fields, 153 methods) over
**30 owner FQNs** (25 top-level + the nested `SExtension$Data*`, `WeightedQuadtree$Data`,
`WeightedQuadtree$QuadTreeEntry`, `Alert4$Data`, `GuiRewindhandlersHandler2$Data5/$Data6/$Data6$Data`).

Sources cross-checked: the 509 reference clients (no hits for these Lunar-internal utils),
Lunar OSS in `/tmp/opencode/reference` (no direct hits — the twins in this tree are the real
evidence), the class-rename evidence in `tools/renames/classes-*.tsv`, and the live twin
packages (`util/raytrace`, `cosmetics`, `render/shader`, `framework/feature/overlay`).

---

## 1. What the cluster actually is

| owner(s) | real identity | key evidence |
|---|---|---|
| `Vector2dPair`, `Vector2f`, `WeightedValue`, `WeightedQuadtree` | small generic utils | class maps `classes-clientutil2/3.tsv` (`normalize` returns this; WeightedValue = Dijkstra (value,cost) pair; WeightedQuadtree = quadtree over IntRectangle) |
| `Wasapi` | JNA Windows WASAPI bindings | native GUID/UPPER_SNAKE constants already real; only `P` locals left |
| `alert/Alert` | async-texture idle/downgrade tracker | Util_2.method4/5/7: residentSize, 60s/15s idle thresholds, downgrade sort factor |
| `alert/Alert2` | **CloakTextureSlicer** (dead duplicate of `client/cosmetics/CloakTextureSlicer`) | class map markersnameplate; SimpleTextureMixin `lunar$sliceCloakTexture_v1_7/v1_8` |
| `alert/Alert3` | **CosmeticTextureLoader** (live copy) | Util2Handler.method4 calls `Alert3.method2(location, FULL)` |
| `alert/Alert4` | **TextureLoadTask** + `TextureLoadTaskBuilder` | its own `toString()` prints `TextureLoadTask.TextureLoadTaskBuilder(resourceManager=…, textureLocation=…, format=…, quality=…, isSlicedCloak=…)` — not a guess |
| `alert/Alert5` | **AnimatedTexture** (`getAnimation`/`restartAnimation`) | class map markersnameplate; HologramsIterator casts and calls method12 to restart |
| `alert/Alert5Handler/Impl/Extension` | no-op `TextureBridge` implementations | see §3 |
| `alert/Alert6` | **ShaderDefinition** | class map markersnameplate + Fov3/FovIterator/FogIterator/ShaderPass/ThreadModuleDump94 call sites (JSON keys fragment/vertex/uniforms/samplers/renderOnTick/renderWidth/renderHeight; method12/13 = loaded VSH/FSH, set by method14/15) |
| `alert/GuiRewindhandlersHandler2` | **ConnectedTexturesListener** (OptiFine CTM) | class map markersnameplate; live twin `framework/feature/overlay/ConnectedTexturesListener`; GuiUpdater.method10 call site |
| `chest/Chest` | **RaySegment** pair | twin `util/raytrace/RaySegment` (same field1/field2/method3 factory) |
| `chest/Chest2` | raycast utility (block/entity/chunk/cosmetic/spray/client-entity + generic voxel walk) | SImpl.Type.BLOCK/ENTITY/… call it; twin `util/raytrace/Raycaster`; `RayContext`/`Ray already has positions assigned!` error strings |
| `chest/SExtension` | **Ray** interface + `RayBuilder` (Data2) + `Data` | twin `util/raytrace/Ray` + `Ray.RayBuilder` + `Ray.Data` (same layout, same strings) |
| `chest/SImpl` | **Raycaster** config (Type + factories) | twin `util/raytrace/Raycaster` (BLOCK…CLIENT_ENTITY static configs) |
| `chest/SBase`, `SBase2` | **DynamicRay** (supplier-backed) / **FixedRay** | twin `util/raytrace/DynamicRay`/`FixedRay`; SBase2 = class-map "FixedRay" |
| `chest/mixin/ChestHandler/2/3` | **CosmeticRaycastContext / BlockRaycastContext / EntityRaycastContext** | class maps markersholograms; twins `util/raytrace/CosmeticRaycastContext` etc.; calls in Markers/Waila/RewindHandlers3Impl2 |
| `click/Click` | render pass (texture + consumer + renderType + enabled) | Click12 = **RenderPipeline** holds `List<Click> passes` (class map clickstruct) |
| `click/BridgeExtension2/3` | **RenderContextModernTransform / RenderContextLegacyTransform** | class maps clickstruct + the RuntimeException strings "…on RenderContextModernTransform/LegacyTransform" |

The chest package is the "S" generation of the same engine that lives renamed as
`com.moonsworth.lunar.client.util.raytrace.*` (other clusters 234/235). Member names were chosen
to mirror those twins (Ray/RayBuilder/DynamicRay/FixedRay/RaySegment/RaycastContext / Raycaster),
so both copies read the same when cluster 234/235 land.

## 2. Naming highlights (full list in the map)

* `SExtension`: refresh / getConfig / getContext / getStart / getEnd / getResultFunction /
  getMissSupplier / trace / builder; `SExtension$Data2` (= RayBuilder): context / positions /
  ray / blockPositions / fromEntity / fromEntityWithOffset / segmentSupplier /
  entitySegmentSupplier / entityOffsetSegmentSupplier / predicate / resultFunction / eachHit /
  trace / requireNoPositions / requireNoResultFunction.
* `SImpl`: static configs `BLOCK, BLOCK_OR_MISS, BLOCK_AT, BLOCK_AT_OR_MISS, PACKED_LIGHT,
  BLOCK_LIGHT, SKY_LIGHT, BIOME, ENTITY, CHUNK, COSMETIC, SPRAY, CLIENT_ENTITY`;
  type / missSupplier / valueFunction / resultFactory; create ×2, getType,
  getMissSupplier, getValueFunction, getResultFactory.
* `Chest2`: traverseBlocks, rayTraceBlocks, traverseChunks, rayTraceChunks,
  rayTraceEntities0/Entities, expandBoundingBox, rayTraceCosmetics0/Cosmetics,
  rayTraceSprays0/Sprays, rayTraceClientEntities0/Entities, getEntityMatrix,
  rayTraceCosmeticModel, rayTraceHologram, rayTraceSpray, length, normalize, extrapolate,
  intersectRayBox ×3, intersectRayModel ×2, traverseGrid, traverseGrid2d, CHUNK_SIZE.
* `Alert`: MAX/MIN_IDLE_MILLIS/NANOS, residentSize, lastUseNanos, reset, isIdle, getIdleFactor,
  set/getResidentSize (this one was already re-applied by members-align4 with the same `reset`).
* `Alert4`/`Alert4$Data`: resourceManager / textureLocation / format / quality / isSlicedCloak /
  call / getScaleFactor / processImage / processAnimationMetadata / getTextureHeight /
  createDecodedTexture / builder; Data: resourceManager / textureLocation / format / quality /
  isSlicedCloak setters + build.
* `Alert6`: texture, fragmentShader, vertexShader, uniforms, samplers, renderOnTick,
  renderWidth, renderHeight, vertexSource, fragmentSource; isPerPlayer, getPipeline,
  declareUniforms, getters 4–13, setVertexSource/setFragmentSource.
* `GuiRewindhandlersHandler2`: connectedTextures, getConnectedTexture (state/String overloads),
  addConnectedProperties, addTextureNames; Data5 tileNames/matchKeys; Data6 connectedTextures,
  getMethod/contains/getIndex/getNames; Data6$Data method/index.

## 3. Skipped / deliberately not renamed

1. **All 347 `P` inventory rows** (params/locals) and the many `varN` rows that
   `members-lazy.tsv` misclassified as fields (the inventory's FIELD regex also matches
   method-local declarations). Params/locals cannot be expressed in the owner-level M|F map.
2. **`Wasapi`** — 37 rows are all params of already-named JNA methods; the class-level fields are
   the real WASAPI constants (`EDataFlow_eRender`, `CLSID_MMDeviceEnumerator`, …). Nothing to map.
3. **`click/BridgeExtension2` (0 rows) and the 19 method declarations of `BridgeExtension3`** —
   every one overrides `AbstractRenderContext` / `BridgeExtension2_11` / `BridgeExtension3_5` /
   `Click8.method53`, and those base owners live in member clusters 009/010. Renaming the
   overrides alone would silently stop the override (the methods carry no `@Override`), so they
   are left untouched. Recommended family names if the base clusters land:
   method29()=createGlStateManager, method43=getBatchingBufferSource, method46=getSortingIndex,
   method6(Integer)=setSortingIndex, method10=createTessellationBuilder,
   method12=createLineTessellationBuilder, method13=rotate(Quaternionf),
   method4=rotateDegrees(angle,x,y,z), method5=rotateDegrees(x,y,z), method36()=loadIdentity,
   method29(4f)=setProjectionMatrix, method33=endBatch, method34=clearDepth,
   method22(6d)=setOrthoMatrix, method23=uploadVertexData, method25=setShaderColor,
   method26(2f)=setShaderTexture, method27=setScissor, method35=setCameraMatrix,
   method36(4f)=setColorMask, method40=resetProjectionMatrix, method41=restoreProjectionMatrix,
   method44=pushTracker, method47=popTracker, method48=endBatch, method49=getPartialTicks,
   method50=builder, method51=getPoseStack, method26(PoseStack)=setPoseStack,
   method28=getPartialTicks, method38=isModern.
   Only `BridgeExtension3.field6 → poseStack` is in the map.
4. **`alert/Alert5Handler.method1/method22`** — no-op overrides of the bridge `TextureBridge`
   (`Bridge3_4.method1` = loadTexture, `method22` = deleteGlTexture, per its class map and the
   `TextureBridge.java` source twin). Skipped so they cannot diverge from the bridge cluster's
   naming. `Alert5Extension`/`Alert5Impl` declare nothing.
5. `Alert2` is a **dead duplicate** (no importers; the live cloak slicer is
   `client/cosmetics/CloakTextureSlicer`), `Alert3` is the **live** copy (its cosmetics twin is
   unused), `Alert6`/`GuiRewindhandlersHandler2` have live twins (`render/shader/ShaderDefinition`,
   `framework/feature/overlay/ConnectedTexturesListener`). Rows were written for the cluster's
   owners only; when the twins are touched, reuse the same names.

## 4. ⚠ Nested-class application hazard (verified against the live tree)

`apply_member_renames.py` renames **every declaration line** matching an old name **in the owner
file**, not just the declaration of the owner class. For files with nested classes that reuse
`methodN`/`fieldN`, the outer-class rows therefore rewrote the nested declarations too, and the
later per-nested-owner rows were no-ops (old names had already disappeared). A scope-aware pass
(signature + enclosing-class) is required for these four files:

| file | class | intended names | what the applier produced | callers to repair |
|---|---|---|---|---|
| `chest/SExtension.java` | `SExtension$Data2` | method1 context, method2 positions, method3 positions, method4/5 ray, method6/7 blockPositions, method8 fromEntity, method9/10 fromEntityWithOffset | refresh(E), getConfig(Vec3,Vec3), getContext(Vector3dc,Vector3dc), getStart(3 args), getEnd(3 args), getResultFunction(2 args), getMissSupplier(3 args), trace(3 args), builder(4 args) | chain call sites still say `.method1/.method8/.method15/.method18` (F3display2_2, Markers, Holograms, Dungeonwaypoints4, RewindhandlersImpl, RewindHandlers3Impl2, Waila, Holograms3Handler2, SprayManager) |
| `chest/SExtension.java` | `SExtension$Data` | method11/12/13 segmentSupplier family, method18 trace, method19 requireNoSegmentSupplier, field7 segmentSupplier | unchanged (rows dropped: same old names in Data and Data2) | chain call sites |
| `alert/Alert4.java` | `Alert4$Data` | method1..5 resourceManager/textureLocation/format/quality/isSlicedCloak, method6 build | call, getScaleFactor, processImage, processAnimationMetadata, getTextureHeight, createDecodedTexture (bleed from outer Alert4) | `NameplateUpdater.java:56-64` still chains `Alert4.builder().method1(…).method2(…).method3(…).method4(…).method5(…).method6()` |
| `alert/GuiRewindhandlersHandler2.java` | `Data6` | method1 getMethod, method2 contains, method3 getIndex, method4 getNames | getConnectedTexture(String) **twice with identical signature (compile error)**, getConnectedTexture(String), addConnectedProperties(String), addTextureNames() | `GuiUpdater.java:178-181` calls `var4.method2/method1/method3` |
| `alert/GuiRewindhandlersHandler2.java` | `Data6$Data` | method1 getMethod, method2 getIndex | getConnectedTexture() **twice (compile error)** | internal |
| `util/WeightedQuadtree.java` | `Data` | method1 add, method2 getWeight | getWeight(IntRectangle,float), insert(IntRectangle) | `ScrollAnimator.method1/2` calls `this.field2.method2(rect)` / `.method1(rect, v)` — declarations no longer match; rename them to `getWeight`/`add` |
| `util/WeightedQuadtree.java` | `QuadTreeEntry` | method1 getBounds | getWeight() | — |

The same mechanism also produced **wrong access rewrites** in `Chest2.java` (receivers typed
`SExtension` were resolved to the `ChestHandler*` context types):

* lines 173, 233, 336, 481, 522, 576 — `var1.isExpandZeroWidth();` → should be `var1.refresh();`
* lines 174, 337, 482 — `(ChestHandler2/3/ChestHandler)var1.getPartialTicks()` → should be
  `…var1.getContext()`
* `SExtension.java:246,252` — `this.getConfig0();` (dangling) → `this.requireNoResultFunction();`
  (declaration still `method20` at line 282)

These were caused by the access pass' receiver-type heuristic (position-based `varN` typing),
not by the map rows themselves; the map contents above are the spec to restore.

## 5. Interactions with other maps / main agent

* `members-align3.tsv` mapped `com.moonsworth.lunar.client.util.chest.Chest2.method15 →
  getProvider` claiming it implements `GuiIterator$Extension.getProvider`. This class has no
  interfaces and no such family; the map now carries `Chest2 M getProvider rayTraceCosmeticModel`
  to correct it (re-apply needed).
* `members-align4.tsv` already applied `Alert.method1 → reset` with the identical name.
* Cluster 234/235 (raytrace twins) will want the same member names for `Ray`, `Raycaster`,
  `DynamicRay`, `FixedRay`, `RaySegment`, `RaycastContext`, `BlockRaycastContext`,
  `EntityRaycastContext`, `CosmeticRaycastContext` — all names in this map mirror them.
* `SExtension` is also the simple name of unrelated classes in `bridge`/`markers`; the owner
  keys here are fully qualified, so no ambiguity, but grep by simple name is noisy.
