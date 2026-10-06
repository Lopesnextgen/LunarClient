# Cluster 06 — `client.util.click` + `net.minecraft.world.gen.structure` + `highlight.mixin` + `client.inactive` (44 rows)

Source revision: `tools/renames/cluster-06.txt` md5 `d2c19ed1e379e10d68f9a2886e0252ce`
(44 rows = 12 `com.moonsworth.lunar.client.util.click` + 12 vanilla structure
inner classes + 10 `com.moonsworth.lunar.client.highlight.mixin` + 10
`com.moonsworth.lunar.client.inactive`).
Map: `tools/renames/classes-clickstruct.tsv` md5 `7eea7ddbf63d78ebea7f92378182765f`
(32 rows; the 12 vanilla structure rows are intentionally absent — see below).

## What this cluster actually is

Four unrelated leftovers bucketed by package:

1. **`com.moonsworth.lunar.client.util.click` — not a clicker.** It is the
   render/GPU utility grab-bag behind Lunar's modern render pipeline. The
   decompiler even left the real class names in the exception strings:
   `BridgeExtension2`/`BridgeExtension3` throw
   `"Unable to use createTessellationBuilder on RenderContextModernTransform"`
   and `…RenderContextLegacyTransform`. The rest are a vertex-array builder
   (`Click2`), an entity render layer (`Click3`), a GPU texture/buffer tracker
   (`Click5`), a stencil emulator (`Click6`, texture `lunar:stencil_emulator`),
   a GL-program cache (`Click9`), a ByteBuffer cache (`Click10`) and the
   render-pipeline/pass plumbing (`Click`, `Click4`, `Click8`, `Click11`,
   `Click12`).

2. **`net.minecraft.world.gen.structure` — not lazy at all.** The 12 inner
   classes the generator flagged (`Corridor2…5`, `Crossing2/3`, `Field1/2`,
   `House1/2/3`, `Stairs2`) are the **canonical MCP 1.8.9 names**. The three
   `.java` files are byte-identical to the 509 reference clients
   (`…/sources/Actinium/…`, `diff` = 0/0/4 lines) and `mappings-snapshot/members.tsv`
   lists `StructureNetherBridgePieces$Corridor2`,
   `StructureVillagePieces$Field1`, `StructureStrongholdPieces$Stairs2`, … as
   identity mappings. This same conclusion was already reached by the
   `classes-forgemixin` cluster (see `classes-forgemixin.md` §2 / "Skipped (12
   rows)"); the rows are a duplicate. **Rows skipped on purpose, nothing to
   rename.**

3. **`com.moonsworth.lunar.client.highlight.mixin` — the chat / command /
   nameplate event category.** The whole `client.highlight` tree is the event
   bus (`LunarEventBus`, `Highlight`, `HighlightImpl`); this top-level `mixin`
   package holds the events fired by the chat/nameplate system:
   `HighlightImpl` (abstract message event + nested `Data`/`Data2`/`Data3`),
   `HighlightImpl3` (chat send), `HighlightImpl4` (client command),
   `HighlightImpl5` (nameplate extension), `HighlightImpl6` (tab-complete),
   `HighlightImpl7` (command registration) and the input-action enums
   `HighlightType2` (keyboard) / `HighlightType3` (mouse). Names follow the
   codebase's own `Event<Purpose>` convention used by clusters 23/26 (these are
   plain event data classes, **not** mixins).

4. **`com.moonsworth.lunar.client.inactive` — the GeckoLib / Bedrock cosmetic
   model stack.** `Inactive3_2` imports `software.bernie.geckolib3.core.builder.AnimationBuilder`
   and loads `geckolib-cosmetic-definition.json`; the sibling
   `inactive.rewindhandlers` package was already renamed to the Bedrock model
   format (`GeoModelSerializer`, `ModelCube`, `CubeMesh`, …) by
   `classes-holograms-bridge`. The top-level types here are the model config
   (`Inactive` = hitbox/physics/pathfinder), the animation state
   (`Inactive2`, whose `toString()` literally prints
   `AnimationState(animation=…, isDefault=…, timer=…)`), a molang resource
   provider (`Inactive4`), five option enums (`InactiveType2…6`), a ticker
   (`Inactive_2`) and two Jackson `JsonDeserializer`s (`MixinHelper102`,
   `MixinHelper1022`).

## Renames (32 rows, 21 applied)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | `util.click` | `BridgeExtension2` | `RenderContextModernTransform` | `RuntimeException("…on RenderContextModernTransform")`; joml pose from the current pose stack |
| 2 | `util.click` | `BridgeExtension3` | `RenderContextLegacyTransform` | `RuntimeException("…on RenderContextLegacyTransform")`; owns a joml `Matrix4fStack(10)` |
| 3 | `util.click` | `Click2` | `FloatArrayBuilder` | growable `float[]` + size cursor; `WorldRenderer.pos()/tex()` append vertex floats |
| 4 | `util.click` | `Click3` | `EntityRenderLayer` | abstract `method1(renderContext, entity, renderType, partialTicks, packedLight)`; 6 static layer instances |
| 5 | `util.click` | `Click4` | `RenderCache` | mutable holder (Object/int/boolean/Component); `Click4Impl` adds `skinType`; `Click11.lunar$getCache()` |
| 6 | `util.click` | `Click5` | `GpuResourceTracker` | tracks GL textures/buffers, `dump()` prints "gpu-objects"; used by `Fpsdebugmod4Impl4` |
| 7 | `util.click` | `Click6` | `StencilEmulator` | texture `Horsestats14.create("lunar","stencil_emulator")`; overrides the main render target |
| 8 | `util.click` | `Click8` | `PoseMatrixProvider` | interface `method53(): Matrix4f`, implemented by both RenderContext transforms |
| 9 | `util.click` | `Click9` | `GlProgramCache` | caches `GL_CURRENT_PROGRAM` behind `GECKO_CURRENT_PROGRAM_CACHE` |
| 10 | `util.click` | `Click10` | `ByteBufferCache` | reusable `BufferUtils.createByteBuffer`; used by `AbstractTextureMixin` |
| 11 | `util.click` | `Click11` | `RenderCacheProvider` | interface `lunar$getCache(): Click4` |
| 12 | `util.click` | `Click12` | `RenderPipeline` | `Bridge5_16` + `List<Click>` passes; payload of `EventPreRenderPlayer` |
| 13 | `highlight.mixin` | `Data2` (nested `HighlightImpl$Data2`) | `EventActionBarMessage` | Component message (no chat id) parsed by ActionBarStats/SkillXp/DungeonMap+Secret listeners |
| 14 | `highlight.mixin` | `Data3` (nested `HighlightImpl$Data3`) | `EventTypedMessage` | non-cancellable (extends `Highlight`), nullable Component + string + int type; `HypixelLocationListener` |
| 15 | `highlight.mixin` | `HighlightImpl2` | `EventComponentMessage` | `(Component, String)`; no in-tree user |
| 16 | `highlight.mixin` | `HighlightImpl3` | `EventChatSend` | cancellable chat-send, `getMessage()`; `ChatCommandAliasesChild` rewrites aliases |
| 17 | `highlight.mixin` | `HighlightImpl4` | `EventCommand` | `getCommand()`/name/args; `NameplateTask` executes registered `ClientCommand`s |
| 18 | `highlight.mixin` | `HighlightImpl5` | `EventNameplateExtension` | carries a holograms `Rewindhandlers.Extension`, implements `Nameplate2` |
| 19 | `highlight.mixin` | `HighlightImpl6` | `EventTabComplete` | `(text, cursor, String[])` fired by `GuiChatMixin.sendAutocompleteRequest` |
| 20 | `highlight.mixin` | `HighlightImpl7` | `EventCommandRegister` | `List<ClientCommand>` populated by `CommandCompleter` |
| 21 | `highlight.mixin` | `HighlightType2` | `KeyInputType` | PRESS/RELEASE/CHAR, action of `EventKeyInput` |
| 22 | `highlight.mixin` | `HighlightType3` | `MouseInputType` | CLICK/RELEASE/DRAG/SCROLL, action of `EventMarkerInput` |
| 23 | `inactive` | `Inactive2` | `AnimationState` | `toString()` = `"AnimationState(animation=…, isDefault=…, timer=…)"` |
| 24 | `inactive` | `Inactive4` | `MolangResourceProvider` | `List<(ResourceLocation, Evaluatable)>`, first condition == 1.0; model/texture/animation + `item_transformation` |
| 25 | `inactive` | `InactiveType2` | `ArmorSlot` | HELMET/CHESTPLATE/LEGGINGS/BOOTS → `ThreadModuleDump91` transform |
| 26 | `inactive` | `InactiveType3` | `ItemRenderMaterial` | ANY/WOOD/…/NETHERITE; `@Annotation27("item_render_material")` |
| 27 | `inactive` | `InactiveType4` | `AttachedBone` | HEAD/SHOULDER/…/RIGHT_LEG + bone name + `ModelBipedBridge` supplier; `@Annotation27("attached_bone")` |
| 28 | `inactive` | `InactiveType5` | `FirstPersonArmMode` | NONE/SINGLE_ARM/DOUBLE_ARM; `@Annotation27("render_first_person")` |
| 29 | `inactive` | `InactiveType6` | `PlayerModelType` | NONE/MINIME (Lunar MiniMe model) |
| 30 | `inactive` | `Inactive_2` | `AnimationTicker` | `double` time + `int` last tick; advanced from `EventTick.field1` in `Holograms2Iterator` |
| 31 | `inactive` | `MixinHelper102` | `TransformationListDeserializer` | Jackson `JsonDeserializer<List<ThreadModuleDump91>>`, parses via `Holograms12.method32` |
| 32 | `inactive` | `MixinHelper1022` | `MolangDeserializer` | Jackson `JsonDeserializer<Evaluatable>`, registered in `Inactive3_2` |

## Skipped (12 rows) — already canonical MCP names

No map row is emitted for these; renaming them would *break* the vanilla
correspondence (and duplicates the `classes-forgemixin` decision):

| file | inner classes | MCP mapping proof |
|---|---|---|
| `StructureNetherBridgePieces.java` | `Corridor2`, `Corridor3`, `Corridor4`, `Corridor5`, `Crossing2`, `Crossing3` | `mappings-snapshot/members.tsv` identity rows; file byte-identical to reference clients |
| `StructureVillagePieces.java` | `Field1`, `Field2`, `House1`, `House2`, `House3` | ditto (`StructureVillagePieces$Field1`/`$House1`/…) |
| `StructureStrongholdPieces.java` | `Stairs2` | ditto (`StructureStrongholdPieces$Stairs2`) |

## Applier dry run, and the collision rows

`python3 tools/apply_class_renames.py --map tools/renames/classes-clickstruct.tsv`:

```
[class-renames] 32 rows; 6594 java files
applied=21 skipped=11 files_touched=32 files_renamed=21 mode=dry-run
```

The 11 skips are **old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Click2` | `framework.feature.rewind.rewindhandlers.click` |
| `Data2`, `Data3` | 6 / 4 packages (nested `HighlightImpl$Data*`; the applier matches by simple name) |
| `HighlightImpl2`…`HighlightImpl7` | 4–6 event subpackages (`highlight.mixin.fishing.mixin`, `.gui`, `.holograms`, `.nameplate`, `.rewindhandlers`) |
| `HighlightType2`, `HighlightType3` | `framework.feature.mod.highlight` |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
simple name in the unrelated `highlight.mixin.*` event subpackages (each has its
own `HighlightImplN`/`DataN`) and in `feature.mod.highlight`, merging distinct
types. Those 11 rows need the planned import/package-aware rewriter (same
follow-up as `classes-12.md`, `classes-15.md`, `classes-23.md`).

## Caveats / follow-ups

* **Nested rows 13–14** (`HighlightImpl$Data2`, `HighlightImpl$Data3`) are real
  nested types inside `HighlightImpl.java`. The applier matches declarations by
  simple name and both names are declared in many other packages, so they are
  skipped by design. They need a nested-aware pass together with the sibling
  nested `Data` (not listed) and `Data3`'s `getType()` int.
* **`HighlightImpl2`** (row 15) has no in-tree user; the name is inferred from
  its `(Component, String)` shape and may be dead code.
* **`Click2`** collides with `framework.feature.rewind.rewindhandlers.click.Click2`
  (a different class, out of this cluster). Whoever claims that package should
  rename it first; `FloatArrayBuilder` is reserved here.
* **`Click4`/`Click11`** (rows 5/11) have no in-tree implementor/user either;
  `RenderCache` / `RenderCacheProvider` are role-based inferences from
  `Click11.lunar$getCache()` and `Click4Impl.skinType`.
* **`InactiveType6`** (row 29) has no in-tree user; `PlayerModelType` is inferred
  from the `NONE`/`MINIME` constants (MiniMe is a Lunar player-model feature).
* **`MixinHelper102`** (row 31) deserializes `List<ThreadModuleDump91>`; the
  element type (`client.util.ThreadModuleDump91`, a scale/rotate/translate model
  transformation) is out of this cluster and still carries a lazy name.
* Names added by this cluster were checked with
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java` — no hits
  for any of the 32 new names.

## Verification

```
md5sum tools/renames/cluster-06.txt          # d2c19ed1e379e10d68f9a2886e0252ce
md5sum tools/renames/classes-clickstruct.tsv # 7eea7ddbf63d78ebea7f92378182765f
python3 tools/apply_class_renames.py --map tools/renames/classes-clickstruct.tsv
# → applied=21 skipped=11 files_touched=32 files_renamed=21 mode=dry-run
```