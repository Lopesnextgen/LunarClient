# Cluster 50 — `com.moonsworth.lunar.legacy.wrapper` (33 rows)

Source: `tools/renames/cluster-50.txt` (33 lines). **All 33 files exist**; map:
`tools/renames/wave5/classes-50.tsv` (33 rows). No `net.minecraft.*` rows, no
shaded third-party classes in this cluster. Dry run:

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-50.tsv
[aware-renames] 33 rows (0 nested); 13684 java files
[aware-renames] rows=33 skipped=0 files_touched=74 files_renamed=33 mode=dry-run
```

(The java-file count moves while other wave-5 maps are applied concurrently;
the row/skip/rename numbers above are stable.)

## What this cluster actually is

This is the **legacy (1.7/1.8/1.12) runtime wrapper layer** of the rewind
packet recorder plus the legacy GL / Adventure / model-bakery adapters:

* the concrete packet builders registered by `legacy/wrapper/BridgeIterator`
  (implements `Bridge_16`, one field per clientbound packet; consumed by
  `client/framework/feature/rewind/rewindhandlersNameplateCore/*`),
* the legacy GL state facade (`Bridge12` impl) and its state snapshot,
* the 1.7 `WorldBorder`/server-border geometry,
* Adventure <-> vanilla style/chat conversion and the CIT item-model baker.

Cluster 49 (`classes-15.tsv`, applied 2026-09-14 in `37739947`) renamed the
first 45 files of this package. The 2026-09-16 rescue sweeps then restored the
pre-rename copies from `src/reference`, so **22 of these 33 rows are the
rescued twin of a class that already has a real name** (normalised-identifier
similarity 0.91–1.00; differences are only local names and already-renamed type
references) and **11 are genuinely unnamed** live classes from the original
decompile.

Naming references: `classes-15.tsv` (the applied twin map), `classes-10.tsv` /
`classes-bridge4.tsv` (bridge-side interface names), the class bodies and the
`legacy/Legacy2` wiring.

## §1 Genuinely unnamed (11 rows)

| # | old | new | evidence (short) |
|---|---|---|---|
| 1 | `MixinHelper31` | `EntityMovePacketBuilder` | only `S14PacketEntity` builder: negates posX/Y/Z + yaw/pitch into `S15PacketEntityRelMove`/`S17PacketEntityLookMove` (rewind inverse); `BridgeIterator.field25`; implements bridge `EntityMovePacketBridge` |
| 2 | `MixinHelper4` | `LegacyRenderPipelineBuilder` | concrete bridge `RenderPipelineBuilder` (`MixinHelper4_6`); `Legacy2.method80()` returns it; `method45()` wraps `ShaderBridgeImpl`, `method43()` returns `BridgeHandler$Data` |
| 3 | `MixinHelper4_2` | `ContainerItemsComponentAdapter` | wraps `NonNullList<ItemStack>` as `ContainerItemsComponentBridge.bridge$items()`; built by `Wrapper_6` for `BlockEntityTag` "Items" (shulker preview) |
| 4 | `MixinHelper6` | `WorldBorderPacketBuilder` | only `S44PacketWorldBorder(Action.INITIALIZE)` builder from `World.getWorldBorder()`; version-gated, throws "This packet is not available in 1.7"; implements bridge `WorldBorderPacketBridge` |
| 5 | `MixinHelper7` | `LegacyRenderTypeFactory` | concrete bridge `RenderTypeBuilder` (`MixinHelper7_4`); `Legacy2.method81()`; `method15` builds `Bridge20Iterator` from `ShaderBridgeImpl` + state shards; `field9` = the legacy GL snapshot |
| 6 | `MixinHelper9` | `SpawnPointPacketBuilder` | only `S05PacketSpawnPosition` builder; 1.7 `ChunkCoordinates` vs 1.8 `world.getSpawnPoint()`; `classes-bridge4` names it the sole impl of bridge `SpawnPositionPacketBuilder` |
| 7 | `Wrapper2_2` | `ItemModelBaker` | bakes CIT models from `ModelBlock` via `ItemModelGenerator.LAYERS` with 3.0E-4F/4.5E-4F offsets; called by `CustomItemPropertiesMixin` (via `ModelBakeryHolder.field1`) and `ModelBakeryMixin` |
| 8 | `Wrapper2_3` | `LegacyGlStateManagerBridge` | `Bridge12.Extension` impl installed by `Legacy2.enable()` via `Bridge.method41`; `GlStateManagerMixin` pushes/pops `LegacyGlStateManagerBridge.field1` (`Bridge_53` matrix stack) |
| 9 | `Wrapper_10` | `TextMeshBuilder` | builds glyph vertex runs (`List<Wrapper$Data3>` first/count/texture) from a `Component`; called by `BridgeIterator_2.method5` (font renderer bridge) |
| 10 | `Wrapper_11` | `AdventureStyleConverter` | Adventure <-> vanilla colour/click/hover conversion + `GsonComponentSerializer` with `LegacyHoverEventSerializer`; used by `ChatStyleMixin`/`StyleImplMixin` |
| 11 | `Wrapper_6` | `ItemDataComponentAccessors` | static init of bridge `DataComponentTypes` lambdas: `ExtraAttributes`, display Name/Lore, `SkullOwner` properties, `BlockEntityTag` Items; called by `Legacy2.method3` |

## §2 Duplicate copies (22 rows)

Same decompiled class as the listed already-named twin (`classes-15.tsv`); the
applier cannot reuse the twin name ("new name already declared"), so each row
here keeps the tree unique and non-lazy. Similarity = normalised-identifier
diff ratio (strings/numbers kept); refs = files under `src/main/java` in the
declaring package or importing/FQN-referencing the class.

| cluster class | new name here | already-named twin | sim | refs here/twin | final name if twin is kept |
|---|---|---|---|---|---|
| `MixinHelper26` | `EffectPacketFactory` | `EffectPacketBuilder` | 1.00 | 2 / 1 | `EffectPacketBuilder` |
| `MixinHelper27` | `SpawnObjectPacketFactory` | `SpawnObjectPacketBuilder` | 1.00 | 2 / 1 | `SpawnObjectPacketBuilder` |
| `MixinHelper28` | `WindowItemsPacketFactory` | `WindowItemsPacketBuilder` | 1.00 | 2 / 1 | `WindowItemsPacketBuilder` |
| `MixinHelper29` | `UnsupportedSnapshotPacketFactory` | `UnsupportedSnapshotPacketBuilder` | 1.00 | 2 / 1 | `UnsupportedSnapshotPacketBuilder` |
| `MixinHelper3` | `EntityHeadLookPacketFactory` | `EntityHeadLookPacketBuilder` | 1.00 | 2 / 1 | `EntityHeadLookPacketBuilder` |
| `MixinHelper30` | `ServerDifficultyPacketFactory` | `ServerDifficultyPacketBuilder` | 0.99 | 2 / 1 | `ServerDifficultyPacketBuilder` |
| `MixinHelper4_3` | `SpawnPaintingPacketFactory` | `SpawnPaintingPacketBuilder` | 1.00 | 2 / 1 | `SpawnPaintingPacketBuilder` |
| `MixinHelper5` | `UnsupportedPacketFactory` | `UnsupportedPacketBuilder` | 1.00 | 2 / 1 | `UnsupportedPacketBuilder` |
| `MixinHelper7_2` | `TimeUpdatePacketFactory` | `TimeUpdatePacketBuilder` | 1.00 | 2 / 1 | `TimeUpdatePacketBuilder` |
| `MixinHelper8` | `SpawnExperienceOrbPacketFactory` | `SpawnExperienceOrbPacketBuilder` | 1.00 | 2 / 1 | `SpawnExperienceOrbPacketBuilder` |
| `MixinHelper_2` | `AbstractRewindPacketBuilder` | `RewindPacketBuilder` | 1.00 | 31 / 25 | `RewindPacketBuilder` |
| `RendererLivingEntityImpl2` | `RendererLivingEntityBrightnessAdapter` | `RendererLivingEntityBrightnessImpl` | 1.00 | 2 / 1 | `RendererLivingEntityBrightnessImpl` |
| `Wrapper2` | `LegacyServerBorderState` | `LegacyServerBorder` | 1.00 | 2 / 1 | `LegacyServerBorder` |
| `Wrapper2_4` | `RenderBackgroundToggle` | `RenderBackgroundExtension` | 1.00 | 3 / 4 | `RenderBackgroundExtension` |
| `Wrapper_12` | `DummyNetworkManagerProvider` | `DummyNetworkManagerFactory` | 0.96 | 2 / 1 | `DummyNetworkManagerFactory` |
| `Wrapper_2` | `GameSettingsAccess` | `GameSettingsBridge` | 1.00 | 2 / 1 | `GameSettingsBridge` |
| `Wrapper_3` | `AudioStreamLoader` | `AudioStreamCache` | 1.00 | 4 / 2 | `AudioStreamCache` |
| `Wrapper_4` | `AncientDummyPlayerFactory` | `AncientDummyPlayer` | 0.99 | 3 / 3 | `AncientDummyPlayer` |
| `Wrapper_5` | `GlStateRecorder` | `GlStateSnapshot` | 0.99 | 3 / 3 | `GlStateSnapshot` |
| `Wrapper_7` | `LegacyWorldBorderBase` | `LegacyWorldBorder` | 1.00 | 2 / 2 | `LegacyWorldBorder` |
| `Wrapper_8` | `ModelBakeryReference` | `ModelBakeryHolder` | 0.91 | 1 / 3 | `ModelBakeryHolder` |
| `Wrapper_9` | `ShaderStateLifecycle` | `ShaderRenderState` | 1.00 | 2 / 3 | `ShaderRenderState` |

Notes:

* The low sim scores (`Wrapper_8` 0.91, `Wrapper_12` 0.96) are the two smallest
  classes: the rescued copy still carries `@Annotation2` where the twin already
  carries `@VersionGate` and old type refs — a one-line difference in an 8-line
  body, not a different class.
* **Both copies can be live.** The rescued copies are wired into
  `BridgeIterator` (all 22 packet builders, `field1..field31`) and `Legacy2`
  (`Wrapper_6.method1()`, `new Wrapper2(...)`, `new MixinHelper7()`, `new
  MixinHelper4()`, `new Wrapper2_3()`), but several renamed twins also keep
  call sites (`LegacyWorldBorder` ← `LegacyServerBorder`, `AudioStreamCache` ←
  `StaticMp3Codec`, `ModelBakeryHolder` ← `CustomItemPropertiesMixin`,
  `RenderBackgroundExtension` ← `GuiScreenImpl2`/GuiSlot mixins). The merge
  pass must repoint the remaining references before deleting either side.
* Merge recommendation: a dedupe/repair pass should keep one copy per concept,
  repoint the `BridgeIterator`/`Legacy2` references, then delete the other.
  Whichever copy survives should carry the twin name (last column) — those are
  the names already applied by `classes-15` and the more accurate ones. Applying
  this map first is safe: both families compile, no lazy names remain.
* Do not apply this map a second time after deleting the twins — the fresh
  names here (`*PacketFactory`, etc.) exist only to keep this map applicable
  while both copies are present.

## §3 Skipped rows

* None. All 33 paths exist and got a row (`skipped=0` in the dry run).
* No shaded third-party code: the package is pure `com.moonsworth` legacy
  wrapper. (Jackson/Guava/Mixin/Kotlin shaded trees live in the
  `genesis`/root clusters, not here.)
* No mixins in this package, so no `<Target><Purpose>Mixin` rows.

## §4 Ambiguities / caveats

* **`SpawnPointPacketBuilder` (MixinHelper9) is not the twin name.**
  `com.moonsworth.lunar.bridge.SpawnPositionPacketBuilder` already exists (the
  renamed bridge interface); the wrapper impl got the close synonym
  `SpawnPointPacketBuilder` to stay unique. If the bridge map later renames
  `bridge/SpawnPositionPacketBuilder` (canonical bridge name from
  `classes-bridge4`), keep the two distinct.
* **`LegacyGlStateManagerBridge` vs `GlStateManagerBridge`.** There is an
  unrelated modern `com.moonsworth.lunar.bridge.GlStateManagerBridge`
  (`GlStateQueryBridge`+`GlStateMutatorBridge`, used by the new render path).
  `Wrapper2_3` implements `Bridge12.Extension` (the pre-1.9 GL facade installed
  via `Bridge.method41`), hence the `Legacy` prefix.
* **`LegacyRenderTypeFactory` vs `LegacyRenderPipelineBuilder`.** Both follow
  the abstract bridge names created by cluster 40 (`RenderTypeBuilder`,
  `RenderPipelineBuilder`); `genesis/MixinHelper72` is the other
  `RenderTypeBuilder` impl and is owned by another cluster — do not let its
  final name collide with `LegacyRenderTypeFactory`.
* **`Wrapper2$Data`, `Wrapper2$Type`, `Wrapper$Data3..6`, `Wrapper$Type`** are
  separate top-level files in the same package that this cluster does not
  cover (they are not in `cluster-50.txt`); renaming `Wrapper2`/`Wrapper_12`
  does not touch their names (no `$`-prefixed nested rows here).
* `MixinHelper4`/`MixinHelper4_2` reference the *rescued* twins
  `BridgeHandler_5`/`MixinHelper4_4` in their bodies; those files belong to
  cluster 49 / cluster 40 maps. Apply order does not matter — both maps rename
  declarations and references independently.
