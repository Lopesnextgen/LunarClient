# Cluster 38 — `com.moonsworth.lunar.bridge` part 3 (`Bridge_3` … `Bridge_62`)

Source: `tools/renames/cluster-38.txt` (45 rows). All 45 files exist on disk. Map:
`tools/renames/wave5/classes-38.tsv` — **3 rows**. The other **42 rows are skipped on
purpose**: 41 are rescue zombies of already-applied class names, and `Bridge_52` is
the surviving half of a deliberately reverted jar-coupled pair. None of them can be
renamed without colliding with a live declaration, and inventing a second name for a
byte-equivalent copy would only make the repair pass harder.

## 1. The three genuine renames

| old | new | evidence |
|---|---|---|
| `Bridge_31` | `VanillaStyleBridge` | Empty marker for the *vanilla* style object: `@Annotation` targets `net/minecraft/util/ChatStyle` (v0) / `util/text/Style` (v5) / `network/chat/Style` (v6). `StyleBridge.moonBridge$asBridgeStyle()` returns it, `StyleImplMixin` returns `(Bridge_31) new ChatStyle()`, and `AbstractComponentMixin` casts it back to `ChatStyle`. The name `ChatStyleBridge` is already the *reverse* (ChatStyle -> Adventure) interface on `ChatStyleMixin`, hence the `Vanilla` qualifier. |
| `Bridge_41` | `BlockPortalBridge` | Empty marker; `@Annotation` targets `net/minecraft/block/BlockPortal` (v0) / `world/level/block/NetherPortalBlock` (v6); no references in `src` or in `libs/lunar-renamed-classes.jar` (orphan). Flattened from `bridge/mixinAux/Bridge`; name follows the existing `BlockTripWireBridge` / `BlockDoublePlantBridge` / `BlockButtonBridge` convention. |
| `Bridge_60` | `WorldPlayerLookupBridge` | Single method `Optional<Bridge6_10> bridge$getPlayerByUniqueId(UUID)` with `@Annotation` v0 `World.getPlayerEntityByUUID` / v6 `Level.getPlayerByUUID`; returned by `Bridge2_12.method1()` whose own annotation maps `Entity.worldObj`/`world`/`level`, i.e. the World facet of an entity. No implementor (bridge-gen facet; `Itemcounter6`/World declares the same method). |

The three are unique tree-wide (`grep -rn <name> src/` is empty) and have no
resource/mixin-config references. Applying the map rewrites
`StyleBridge`, `TextBridge`, `StyleImplMixin`, `Bridge5`, `Bridge_36`, `Bridge2_12`.

## 2. Skipped rows (42)

41 of the skipped files (`Bridge_22`…`Bridge_62`, `Bridge_3`…`Bridge_6` minus the
three renamed above) are rescue zombies: second decompiles of classes that earlier
applied maps already named (`classes-bridge3`, `classes-09`, `classes-bridge4`).
`Bridge_52` is the exception (see §2a). Evidence:

* **Member-identical bodies.** A normalised member/string comparison of all 42 pairs
  (methods + arity + fields + string literals) reports no member-set differences;
  the only diffs are renamed type references and local-variable names.
* **Git origin.** Each skipped placeholder was (re-)added by the rescue sweep
  `7adc91305` ("rescue: batch 7") while its twin was added by a renamer commit
  (`8a891edde`, `72c44452a`, `758db25a`, `3b15d6560`); `Bridge_52` instead comes from
  the `dbfca2d11` jar-coupling revert. The three renamed classes were restored by
  `47dd3dd48` (and had been present since `335cea10d`).
* **Both generations are still referenced** (the placeholder names by the old-gen
  call sites, the named twins by the renamed ones), so they cannot be deleted blindly
  either. `apply_class_renames_aware.py` would answer every one of these rows with
  `SKIP ... new name already declared`.

Recommended follow-up is the same as `classes-52`/`classes-59`: pick a survivor per
pair, repoint the remaining call sites, delete the other copy. In several pairs the
placeholder is a supertype of old-gen interfaces (`GlEnum`, `INetHandlerBridge`,
`BufferBuilderBridge`, `GlObjectBridge`, `ModelBaseBridge`, `EntityRenderStateBridge`),
so the repoint has to walk the hierarchy before deleting.

| # | placeholder | already-applied twin | refs placeholder | refs twin | naming map | remarks |
|---|---|---|---|---|---|---|
| 1 | `Bridge_22` | `EntityRegistryBridge` | 2 | 3 | classes-bridge3 |  |
| 2 | `Bridge_23` | `GlEnum` | 6 | 6 | classes-bridge3 |  |
| 3 | `Bridge_24` | `InventoryPlayerBridge` | 5 | 4 | classes-bridge3 |  |
| 4 | `Bridge_25` | `MiscMarkerBridge` | 0 | 0 | classes-bridge3 |  |
| 5 | `Bridge_26` | `INetHandlerBridge` | 8 | 7 | classes-bridge3 |  |
| 6 | `Bridge_27` | `VertexFormats` | 8 | 3 | classes-bridge3 |  |
| 7 | `Bridge_28` | `BufferBuilderBridge` | 19 | 29 | classes-bridge3 |  |
| 8 | `Bridge_29` | `ComputeProgramBridge` | 2 | 0 | classes-bridge3 |  |
| 9 | `Bridge_3` | `FakeTickBridge` | 0 | 0 | classes-bridge3 |  |
| 10 | `Bridge_30` | `NetworkPlayerInfoBridge` | 4 | 4 | classes-bridge3 |  |
| 11 | `Bridge_32` | `PropertyMapBridge` | 2 | 2 | classes-bridge3 |  |
| 12 | `Bridge_33` | `AbstractTextureMultiTexBridge` | 2 | 2 | classes-09 |  |
| 13 | `Bridge_34` | `LightTextureBridge` | 4 | 3 | classes-09 |  |
| 14 | `Bridge_35` | `GlObjectBridge` | 7 | 3 | classes-09 |  |
| 15 | `Bridge_36` | `StyleBridge` | 2 | 2 | classes-09 |  |
| 16 | `Bridge_37` | `ItemEntityRendererBridge` | 1 | 2 | classes-09 |  |
| 17 | `Bridge_38` | `TurboBlockBridge` | 6 | 2 | classes-09 |  |
| 18 | `Bridge_39` | `CommandSenderBridge` | 1 | 2 | classes-09 |  |
| 19 | `Bridge_4` | `ProtectionEnchantmentsBridge` | 1 | 1 | classes-09 |  |
| 20 | `Bridge_40` | `ParticleEngineBridge` | 3 | 1 | classes-09 |  |
| 21 | `Bridge_42` | `ModelBaseBridge` | 1 | 1 | classes-09 |  |
| 22 | `Bridge_43` | `AuxCoreMarkerBridge` | 0 | 0 | classes-bridge4 |  |
| 23 | `Bridge_44` | `ShaderInstanceBridge` | 1 | 1 | classes-09 |  |
| 24 | `Bridge_45` | `RenderPipelineBridge` | 12 | 5 | classes-09 |  |
| 25 | `Bridge_46` | `NameTagRendererBridge` | 4 | 0 | classes-09 |  |
| 26 | `Bridge_47` | `LightingMarkerBridge` | 0 | 0 | classes-bridge4 |  |
| 27 | `Bridge_48` | `SizeExtendableBridge` | 0 | 0 | classes-bridge4 |  |
| 28 | `Bridge_49` | `InputConstants` | 0 | 0 | classes-bridge4 |  |
| 29 | `Bridge_5` | `WeightedBufferSourceBridge` | 0 | 0 | classes-09 |  |
| 30 | `Bridge_50` | `TiersBridge` | 1 | 2 | classes-09 |  |
| 31 | `Bridge_51` | `SoundMarkerBridge` | 0 | 0 | classes-bridge4 |  |
| 32 | `Bridge_52` | `FramebufferBuilder` | 11 | twin reverted | classes-09 | twin **reverted on purpose** by `classes-fbbuilder-revert.tsv` (jar-coupled with `Bridge3_24`/`Bridge.method56/58`) |
| 33 | `Bridge_53` | `MatrixStackBridge` | 7 | 4 | classes-09 |  |
| 34 | `Bridge_54` | `EntityDragonBridge` | 0 | 2 | classes-09 |  |
| 35 | `Bridge_55` | `EntityAnimalBridge` | 0 | 2 | classes-09 |  |
| 36 | `Bridge_56` | `BlocksBridge` | 6 | 15 | classes-09 |  |
| 37 | `Bridge_57` | `CompoundTagBridge` | 20 | 27 | classes-09 | **jar-coupled**: jar-only `mchorse/emoticons` classes reference `bridge.Bridge_57` in their signatures |
| 38 | `Bridge_58` | `SpriteAnimationBridge` | 7 | 2 | classes-09 | **jar-coupled**: jar-only `mchorse/emoticons` classes reference `bridge.Bridge_58` in their signatures |
| 39 | `Bridge_59` | `FluidStateBridge` | 2 | 1 | classes-09 |  |
| 40 | `Bridge_6` | `EntityMinecartBridge` | 0 | 1 | classes-09 | **jar-coupled**: jar-only `mchorse/emoticons` classes reference `bridge.Bridge_6` in their signatures |
| 41 | `Bridge_61` | `EntityRenderStateBridge` | 13 | 7 | classes-09 |  |
| 42 | `Bridge_62` | `EntityRendererBridge` | 4 | 3 | classes-09 |  |

### 2a. Jar-coupling caveats (important for the repair pass)

* `Bridge_52` — its rename to `FramebufferBuilder` was **deliberately reverted** in
  `classes-fbbuilder-revert.tsv`/`dbfca2d11` ("jar-coupled pair with `Bridge3_24`:
  `Bridge.method56/58` and `Bridge_53` builders type against `bridge.Bridge_52`").
  Keep the placeholder name until `Bridge3_24`/`Bridge` are rescued.
* `Bridge_57`, `Bridge_58`, `Bridge_6` — jar-only shaded `mchorse/emoticons` classes
  reference these placeholder FQNs in real signatures (`Morph`/`MorphDeserializer`
  take `Lcom/moonsworth/lunar/bridge/Bridge_57;`, `AnimatorController` calls
  `Bridge_58.updateAnimation()` and does `instanceof Bridge_6`). Renaming either side
  breaks the stale jar until those classes are rescued.
* `Bridge_61` — already reconciled in `dbfca2d11` as a compat shim: the named
  `EntityRenderStateBridge` now `extends Bridge_61` and redeclares its members, and the
  old-gen interfaces (`BridgeExtension`, `BridgeExtension2_2`, `BridgeExtension_8`)
  also extend it. Do not delete `Bridge_61` without touching those headers.

## 3. Ambiguities / decisions

* `Bridge_31` — `VanillaStyleBridge` was chosen over `ChatStyleObjectBridge` /
  `NativeStyleBridge`; `ChatStyleBridge` is taken by the reverse-direction interface
  implemented by `ChatStyleMixin`. If the family later renames that one,
  `ChatStyleBridge` remains the natural name for `Bridge_31`.
* `Bridge_60` — `WorldPlayerLookupBridge` is the concrete reading (the World facet
  reached through `Entity.worldObj` that only exposes player lookup). Alternative:
  `EntityWorldBridge`; rejected because the interface is typed as the world, not the
  entity. `Itemcounter6` in `bridge/itemcounter` is the full World bridge and must keep
  a distinct name.
* `Bridge_41` — orphan marker with zero references; `BlockPortalBridge` is inferred
  purely from the two `@Annotation` targets.
* No shaded third-party classes are in this cluster (all 45 rows are
  `com.moonsworth.lunar.bridge` types). The `mchorse/emoticons` classes mentioned in
  §2a are jar-only and therefore not cluster rows.
* Prior maps that covered this cluster's rows: `classes-bridge3` (11 of them),
  `classes-09` (26), `classes-bridge4` (5); `Bridge_52` additionally appears in
  `classes-fbbuilder-revert.tsv`.

## 4. Dry-run result (measured)

```
[aware-renames] 3 rows (0 nested); 13684 java files
  com.moonsworth.lunar.bridge: Bridge_31 -> VanillaStyleBridge (11 hits)
  com.moonsworth.lunar.bridge: Bridge_41 -> BlockPortalBridge (1 hits)
  com.moonsworth.lunar.bridge: Bridge_60 -> WorldPlayerLookupBridge (2 hits)
[aware-renames] rows=3 skipped=0 files_touched=9 files_renamed=3 mode=dry-run
```

`Bridge_31` rewrites `StyleBridge`, `TextBridge`, `StyleImplMixin`, `Bridge5` and the
zombie `Bridge_36`; `Bridge_60` rewrites `Bridge2_12` (also a zombie owned by another
cluster). No `src/main/resources` hits.
