# Cluster 09 notes — `com.moonsworth.lunar.bridge` (45 classes)

All 45 files listed in `cluster-09.txt` exist. 38 classes got a new name
(`classes-09.tsv`); 7 are in "Needs follow-up" below.

## How the names were derived

This package is Lunar's MoonBridge API. The classes here are of three kinds
and were named the same way cluster-07 did:

* **MC-facing bridge interfaces** — `bridge$...` method sets plus the
  `@Mixin` implementor / call site identify the wrapped Minecraft type
  (`Bridge_63` = `VertexFormatBridge` because `VertexFormatMixin`
  `@Mixin(VertexFormat)` implements it; `Bridge_56` = `BlocksBridge` because
  `BridgeHandler_3` returns `Blocks.air/stone/...`; `Bridge_57` =
  `CompoundTagBridge` because `NBTTagCompoundMixin` implements it).
* **Lunar render/tooling wrappers** that are not MC classes — named for the
  role they play in Lunar's renderer (`FramebufferBuilder`, `RenderPipelineBridge`,
  `DisplayListBridge`, `SpriteAnimationBridge`, `TurboBlockBridge`).
* **Packet-bridge interfaces** (`MixinHelper10/11/12`) — the legacy wrapper
  class that implements each one names the actual packet
  (`S47PacketPlayerListHeaderFooter`, `S0FPacketSpawnMob`, `S25PacketBlockBreakAnim`),
  so they are named `<Packet>Bridge`.

Evidence per row is in the tsv; the runtime path -> current-name chain came
from `tools/mappings-snapshot/restructure/remaining-renames.tsv` +
`tools/mappings-snapshot/normalize-renames.tsv`, and the 1.8.9 wrapper
implementations in `com/moonsworth/lunar/legacy/{mixin,wrapper}`.

## Notable renames

| old | new | why it matters |
|---|---|---|
| `Bridge_45` | `RenderPipelineBridge` | `Bridge20Iterator` ctor parameter is literally named `renderPipeline` (Lombok `@Generated` ctor); `RenderTypeBridge.bridge$getRenderPipeline()`; matches 1.21.5 `RenderPipeline` (blendFunction/depthTestFunction/polygonMode/writeColor...). `Bridge8_2.bridge$blit$v1_21_6(Bridge_45,...)` confirms. |
| `Bridge_46` | `NameTagRendererBridge` | Implemented by quarantined `legacy/wrapper/BridgeIterator_2` — a full nameplate renderer (NickHider, GL display-list cache, `free()`), returned by `Legacy2.method85()`. |
| `Bridge_56` | `BlocksBridge` | 113 getters returning `Bridge3_23` (Block: quarantined `Bridge3_23` has `@Annotation4("net/minecraft/block/Block")`); impl `BridgeHandler_3` returns `Blocks.*`. |
| `Bridge_61` | `EntityRenderStateBridge` | cluster-07 deliberately named `BridgeExtension_12` `EntityRenderStateProvider` to leave this name free for this class; it is the entity render-state view (pos/age/size/passengers/extractRenderStates/outlineColor). |
| `Bridge_44` | `ShaderInstanceBridge` | the `Supplier<Bridge_44>` shader hook of `RenderPipelineBridge` on 1.16-1.20.4 (`@Annotation2(min=8,max=25)`); MC `ShaderInstance` supplier. |
| `Bridge_7` | `KeyEventBridge` | key-press descriptor (char + GLFW code + repeat) produced by `legacy/mixin/BridgeHandler` and passed to every mod's `handleKey`; `MixinHelper22` tracks one list per screen. |
| `Bridge_65` | `MarkerPositionBridge` | Implemented by `Markers.Data4` (markers/waypoints mod); `new Markers.Data4(0.0,0.0)` is the empty fallback passed into `Bridge7_8` render callbacks. |
| `Bridge_69` | `EntityListBridge` | impl `BridgeIterator_3` wraps `net.minecraft.entity.EntityList` (`stringToClassMapping`, `stringToIDMapping`, `getEntityNameList`). |

## Collisions / applier warnings

* **`MixinHelper10`, `MixinHelper11`, `MixinHelper12` collide tree-wide**
  (declared in `com.moonsworth.lunar.bridge`, `.../forge/lib`,
  `.../client/framework/feature/mod/mixin`, `.../genesis`,
  `.../legacy/wrapper`, `.../network`, `.../network/mixin`). The applier will
  `SKIP collision` them by default — do **not** pass `--allow-collisions`
  for this map: it would rewrite the unrelated `MixinHelper10/11/12`
  classes in the other packages as well. They need a package-scoped/manual
  rename of `src/main/java/com/moonsworth/lunar/bridge/MixinHelper1[012].java`
  plus every reference whose type resolves to `com.moonsworth.lunar.bridge`
  (i.e. `Bridge_16.java` only — the three interfaces are referenced nowhere
  else; the `legacy/wrapper` classes implement them via fully-qualified
  `com.moonsworth.lunar.bridge.MixinHelper10/11/12`).
* No other old name in this map is declared in more than one package.
* `Bridge_4` vs `Bridge_40` etc. are safe: the applier uses a word-boundary
  regex and exact-stem file renames, so `Bridge_4` never matches `Bridge_40`.
* `Bridge_33` reads `bridge$setMultiTextureBase` but `Bridge_3` (other
  cluster) is a different interface; no shared tokens.

## Companion-file notes

* None of the renamed old names owns a `$`-companion file. The literal-`$`
  files in this package (`Bridge$Data`, `Bridge12$Extension`,
  `Bridge12$Extension2`, `Bridge$Extension`, ...) belong to other clusters
  and are untouched by this map.
* `Bridge_45` is referenced by quarantined `Bridge$Extension.java`
  (`...Bridge_45$Extension` originally flattened to `Bridge$Extension`); the
  applier will rewrite the `Bridge_45` token there correctly, but the type
  relationship is historical, not an inner-class relationship.

## Lower-confidence rows (still evidence-based)

* `Bridge_52` -> `FramebufferBuilder`: there is no single MC class behind it
  (Lunar's builder over `net.minecraft.client.shader.Framebuffer` /
  `Bridge3_24`); the name describes the role.
* `Bridge_64` -> `TextureOverrideBridge` (`min 26` only): the two methods
  (`setTextureReplacementFunction`, `setNextOverlayTexture`) are clear but the
  concrete modern sprite/atlas class could not be recovered locally (no
  implementor in any runtime jar at hand); rename if the modern module says
  otherwise.
* `Bridge_5` -> `WeightedBufferSourceBridge`: mirrors
  `MultiBufferSourceBridge.bridge$contributeWeightedOrderings` but adds
  `bridge$begin(RenderType)`; no implementor/usage in the 1.8.9 tree.
* `Bridge_50` -> `TiersBridge` (1.8.9 impl is `Item.ToolMaterial`; modern
  holder is `Tiers`); `Bridge_34` -> `LightTextureBridge` (1.8.9 has no such
  class, but the three static helpers match `LightTexture.pack/block/sky`
  exactly, including 15728880).
* `Bridge_42` -> `ModelBaseBridge`: empty supertype of `ModelBipedBridge`;
  only indirect evidence (`ModelBipedMixin extends ModelBase`).

## Needs follow-up (7 classes, deliberately left unnamed)

| class | why not named |
|---|---|
| `Bridge_43` | truly empty; zero references in src, quarantine, staging and all runtime jars. No implementor, no annotation. |
| `Bridge_47` | truly empty; zero references anywhere. |
| `Bridge_48` | single `bridge$extendSize(int)`; zero references/implementors, no annotation to pin the target. |
| `Bridge_49` | utility holder of int constants `1, 3, 22, 24` (`@Generated` private ctor); zero references; values match neither GL, keycode nor packet-id tables conclusively (`22/24` would fit `S22PacketMultiBlockChange`/`S24PacketBlockAction`, but nothing in the tree consumes them). |
| `Bridge_51` | truly empty; zero references anywhere. |
| `Bridge_70` | single `bridge$first():Bridge4_6` (VertexConsumer), `@Annotation2(min=6,max=38)`; no implementor/usage. Related package (`mixin5/mixin4`) also holds a `bridge$pose()` interface, so it is render-state/consumer plumbing, but the exact MC type (e.g. a `VertexMultiConsumer`-style union) is unverifiable here. |
| `MixinHelper13` | empty packet-factory interface (`extends MixinHelper_19`, no methods); unlike `MixinHelper10/11/12` no legacy wrapper implements it and the jar has no implementor, so the packet it belongs to cannot be identified. |

## Notes for the applier

* Applying this map does **not** require `--allow-collisions`; the default
  dry run is expected to show `applied=35 skipped=3` (the three
  `MixinHelper1x` collisions). See the warning above before forcing them.
* All 38 new simple names were checked with
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java` — no hits.
