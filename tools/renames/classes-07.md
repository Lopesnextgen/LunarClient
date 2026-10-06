# Cluster 07 notes — `com.moonsworth.lunar.bridge` (50 classes)

All 50 files listed in `cluster-07.txt` still exist, none were quarantined in the
meantime. 44 classes got a new name (`classes-07.tsv`), 6 are listed below under
"Needs follow-up".

## How the names were derived

The package is Lunar's MoonBridge/ichor API. Two kinds of classes live in it:

* **MC-facing bridge interfaces** (`BridgeExtension*`, `Bridge14/15/17/19/20/21`,
  root annotations). Their `bridge$...` method names and their `@Mixin`
  implementors (in `com/moonsworth/lunar/legacy/mixin` and
  `.../legacy/wrapper`) identify the wrapped Minecraft type, so they were named
  `<McTarget>Bridge` (`EntityItemFrameBridge`, `RenderTypeBridge`, ...).
* **ichor bytecode-framework internals** (`Bridge3`, `BridgeHandler*`,
  `BridgeIterator*`). These classes emit ASM instruction lists for the runtime
  remapper (`BridgeHandler2` = NEW+DUP, `BridgeHandler6` = RETURN, ...), so they
  were named as `<what it emits>Emitter`; `BridgeIterator2/3/4` as
  `InvocationEmitter`/`PathEmitter`/`CompositeEmitter`. These are descriptive,
  not recovered real names (the real names are not present in any Lunar jar,
  `.kin` file, or reference client).

Method/annotations evidence used per row is in the `evidence` column. Runtime
classes were reverse-mapped with
`tools/work/mappings/{normalize-renames,restructure-merged,package-renames}.tsv`
and cross-checked with `javap` where the decompiled source could hide a member.

## Notable renames

| old | new | why it matters |
|---|---|---|
| Bridge5 | `TextBridge` | high-traffic Adventure Component utility (stripColor/asAdventure/getTextContent/...); used in 130+ files |
| Bridge11 | `RendererLivingEntityBridge` | implemented by `RendererLivingEntityImpl` (extends `RendererLivingEntity`) |
| Bridge14/Bridge15 | `ResourcePackBridge` / `ResourceBridge` | `IResourcePack` / `IResource`; mixins `AbstractResourcePackMixin`, `DefaultResourcePackMixin`, `SimpleResourceMixin` |
| Bridge17/Bridge19/Bridge20 | `MultiBufferSourceBridge` / `RenderStateShardBridge` / `RenderTypeBridge` | modern render pipeline API (getBuffer(RenderType), setupState/clearState, pipeline+outline+shader uniforms) |
| BridgeExtension52 | `EntityItemBridge` | `EntityItemMixin` (item state/render count/render seed/baked model) |
| BridgeExtension_10 | `ModelBipedBridge` | `ModelBipedMixin`; NOT an entity despite the `BridgeExtension` prefix |
| Annotation2/3/4 | `BridgeParameterTarget` / `BridgeVersionTarget` / `BridgeTarget` | annotation family used by the runtime `@Annotation*` remapper; `BridgeExtension` (quarantined) references them as `method1()` members |

## Collisions avoided in the new names

* `ShaderType` is already taken by `net.minecraft.client.shader.ShaderLoader.ShaderType`,
  so `BridgeType2` became `ShaderStageType`.
* `BridgeExtension_12` -> `EntityRenderStateProvider` (instead of a generic
  `EntityRenderStateBridge`) to leave `Bridge_61`/`MixinHelper_12`-style names
  free for other clusters.
* All 44 new simple names were checked against
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java` and
  `src/reference` — no hits.

## Lower-confidence rows (still evidence-based)

* `Bridge8 -> BlockTripWireBridge` and `Bridge9 -> ServerListEntryNormalBridge`
  are empty marker interfaces; their only evidence is the sole implementor
  (`@Mixin BlockTripWire` / `@Mixin ServerListEntryNormal`). They have no
  methods and no other usages in the tree, so the names could also be feature
  markers rather than per-class bridges.
* `Bridge16 -> BufferSourceWrapper` is a decorator interface with only
  `unwrap() -> Bridge17`; "wrapper" describes its role, the concrete MC class
  behind it is not present in this build.
* `BridgeExtension_6 -> GlHandleBridge` is a `@FunctionalInterface` exposing
  `int lunar$getHandle()` with a `create(int)` factory; the pixel-readback
  utility accepts either a texture or a framebuffer, so the name is deliberately
  generic.
* `BridgeExtension_12 -> EntityRenderStateProvider` is an entity extension that
  only adds `bridge$getRenderState()` returning the cached render state
  (`MixinHelper_12`, min 17); the concrete render-state owner is not visible here.
* `BridgeExtension_11 -> SulfurCubeTntBridge` (min 39) is separate from
  `BridgeExtension4` (`EntityTNTPrimed`/`PrimedTnt`), and `TntCountdown` keys its
  instances `"sulfur-cube-tnt-..."`; if the entity has a different upstream name
  this is the row most likely to need a follow-up correction.

## Applier warnings (dry run of `tools/apply_class_renames.py`)

`python3 tools/apply_class_renames.py --map tools/renames/classes-07.tsv`
reports `applied=40 skipped=4`. The four skipped rows have **old names that are
declared in more than one package**, so the naive word-boundary rewriter refuses
them:

| row | also declared in |
|---|---|
| `Annotation2 -> BridgeParameterTarget` | client.guiRewindhandlers, client.util, genesis, ichor, ichor.util |
| `Annotation3 -> BridgeVersionTarget` | annotations, client.guiRewindhandlers, client.util, genesis, ichor, ichor.util |
| `Annotation4 -> BridgeTarget` | client.util, genesis, ichor, ichor.util |
| `BridgeExtension2 -> EntityFallingBlockBridge` | client.util.click (`BridgeExtension2 extends BridgeExtension2_11`) |

**Do not apply those four with `--allow-collisions`** — the tool would
substitute the simple name tree-wide and rename the unrelated
`com.moonsworth.lunar.ichor.Annotation2` (and friends) as well. They need a
package-scoped/manual rename (declaration in `com/moonsworth/lunar/bridge` plus
references whose type resolves to that package; e.g. only files that import
`com.moonsworth.lunar.bridge.Annotation2` or live in the same package, and
never the fully-qualified `ichor`/`genesis`/`click` ones). There are 543 files
mentioning the token `Annotation2`, so the main agent should use a qualified
rewrite.

Two more companion-file notes for the applier:

* `Bridge3$Extension.java` is not an inner type of this cluster's `Bridge3`
  (it extends `Bridge3_13`); and `Bridge5$Data.java` is not an inner type of
  `Bridge5` (its constructor takes `Bridge5_16`). The tool's
  `Outer$Inner.java` companion rule would still rename them to
  `MethodParameter$Extension.java` / `TextBridge$Data.java`. That compiles
  (top-level `$` identifiers are legal and all references are rewritten
  consistently) but is semantically wrong; consider excluding those two files
  from the file rename if a scoped applier is used.
* `Bridge4$Data.java` is likewise **not** the inner type of `Bridge4` (it is
  `Bridge4_8`'s nested `Data`, see follow-up table); because `Bridge4` is not
  in the map, that file is untouched.

## Needs follow-up (6 classes, deliberately left unnamed)

| class | why not named |
|---|---|
| `Bridge4` | truly empty marker (javap: 0 methods, 0 interfaces, no annotations); no implementor or usage anywhere. **Hazard:** the top-level file `Bridge4$Data.java` (literal `$` in the identifier) is not its inner class — it corresponds to `Bridge4_8` (TextureAtlasSprite bridge, references it as `Bridge4.Data`). Do not let a `Bridge4` rename drag `Bridge4$Data` along. |
| `Bridge6` | truly empty marker; no implementor, no usage in src, quarantine or staging. |
| `Bridge7` | single method `bridge$lightOverlayTracker()`; implementors absent (the classes that expose that method implement `Bridge9_7` instead). Could be the same role as `Bridge9_7` (render chunk/container). |
| `Bridge13` | single method `bridge$setShaderUniforms(Bridge6_8)`; no implementor/usage in any jar (scanned all class files in the runtime jars for the literal method name). Not enough to decide between shader/pipeline roles. |
| `BridgeExtension3` | empty interface extends `BridgeExtension`; no implementor, only an `instanceof` exclusion in `GuiRewindhandlers2`. |
| `BridgeExtension_8` | abstract `setScale/getScale` on a `Bridge_61` render state; no implementor/usage anywhere. |

## Notes for the applier

* The base interface `com.moonsworth.lunar.bridge.BridgeExtension`, the supertype
  of most `BridgeExtension_*` classes, is currently quarantined
  (`tools/work/quarantine/src/com/moonsworth/lunar/bridge/BridgeExtension.java`).
  Its rename (another cluster) will affect the same hierarchy; the names chosen
  here only replace the `BridgeExtension_*` simple names, not `BridgeExtension`.
* `BridgeExtension$Type` and `BridgeExtension2_*`-style classes are in other
  clusters and were not touched.
* `Bridge3_5`, `Bridge4_2`, `Bridge4_6`, `Bridge6_6`, `Bridge6_8`, `Bridge_13`,
  `Bridge_15`, `Bridge_45`, `Bridge_61`, `Bridge_63`, `Bridge_68`, `Bridge9_7`
  and `MixinHelper_*` are referenced by the renamed classes but belong to other
  clusters; no rows were emitted for them here.
