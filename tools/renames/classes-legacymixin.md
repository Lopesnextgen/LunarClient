# Cluster 17 — `com.moonsworth.lunar.legacy.mixin` leftovers (24 classes)

Source revision: `tools/renames/cluster-17.txt` md5 `b2f9a1618c42618f7cbe164c2cbe4127`
(24 rows, all present in `src/main/java`; nothing skipped as missing).
Map: `tools/renames/classes-legacymixin.tsv` (md5 `9cec011bbf94c18f35fd79a26fe48672`).

These are the **digit-suffixed leftovers** of the legacy mixin set: classes whose
`old` simple name was either a lazy `...MixinN` / `...N` placeholder or was
skipped by the v1 applier because the same simple name is declared in another
package. Every row is a real Sponge `@Mixin`; the new name is
`<Target><Purpose>Mixin`, purpose chosen from the injected methods and the
Lunar feature they implement.

## What these mixins are

The package is Lunar's 1.8.9/1.7.10 legacy mixin layer. The base (un-numbered)
mixin for each target is usually the *bridge* (`implements BridgeN`, only
`bridge$…` methods); the numbered leftovers carry the actual injections:

* **Lighting** (`ModLighting`) — `ChunkMixin2`, `WorldMixin3`.
* **Faster loading** (`FlawlessFrames` / `lunar$flawless$fasterLoading`) —
  `EntityRendererMixin3`.
* **Glow/outline** (`GlowModule`) — `RendererLivingEntityMixin2`.
* **Hit colour** (`ModHitColor`) — `RendererLivingEntityMixin3`.
* **3D skins** (`Mod3dSkins`, `Skins3d`) — `RenderPlayerMixin3`,
  `TileEntitySkullRendererMixin2` (and the arm half of `RenderPlayerMixin2`).
* **Rewind** (`ModRewind`) — `ShaderGroupMixin2`, `TimerMixin2`,
  `SkinManagerMixin2` (sync skin loading).
* **Nametag** (`ModNametag`) — `ScoreboardMixin2`, `StatFileWriter3`.
* **Adventure interop** — `StatFileWriter2` (`ResourceLocation implements Key`).
* **Bridge-only** — `RenderItemMixin3`, `ThreadDownloadImageDataMixin2`.
* **Accessors** — `ScoreboardMixin3`, `ServerListEntryNormalMixin3`,
  `WorldMixin4`.

Reference material cross-checked: the sibling base mixins in the same package,
`tools/mappings-snapshot/restructure/mixin-renames.tsv` (reverse-mapped each
leftover to its original subtree, e.g. `legacy/optifine/mixin` or
`legacy/mixin2/mixin/…`), `tools/renames/APPLIED.md` / `Prompt.md` (applied
legacy-mixin names such as `ChunkLightingMixin`, `RenderGlobalFlawlessMixin`,
`EntityRendererFasterLoadingMixin`), and
`tools/mappings-snapshot/lunar-client-names.tsv` for the real Apollo feature
names (`Mod3dSkins`, `ModHitColor`, `ModLighting`, `ModRewind`, `ModScoreboard`,
`ModWeatherChanger`, `GlowModule`).

## Renames (24 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `ChunkMixin2` | `ChunkLightingMixin` | skips skylight/relight updates while `ModLighting` is on; fires chunk load/unload/block-change/entity-enter events |
| 2 | `EntityRendererMixin3` | `EntityRendererFasterLoadingMixin` | `@ModifyConstant loadAllVisibleChunks$v1_8` 100 → 1 in `lunar$flawless$fasterLoading` |
| 3 | `RenderItemMixin3` | `RenderItemBridgeMixin` | pure `Bridge5_19` bridge: `bridge$getModelLocation` / `bridge$getModel` |
| 4 | `RenderPlayerMixin2` | `RenderPlayerEventMixin` | fires render-player events (`HighlightImpl9`/`HighlightImpl13`), rewind `isUser`, plus 3D-skin arms |
| 5 | `RenderPlayerMixin3` | `RenderPlayer3dSkinsMixin` | only 3D-skin arm/sleeve rendering (`Skins3d` + `Pkg3`) |
| 6 | `RendererLivingEntityMixin2` | `RendererLivingEntityGlowMixin` | entity glow/outline (`GlowModule`, outline pass, team colour) |
| 7 | `RendererLivingEntityMixin3` | `RendererLivingEntityHitColorMixin` | redirects `Shaders.setEntityColor` to `ModHitColor` values |
| 8 | `ResourcePackRepositoryMixin2` | `ResourcePackRepositoryServerPackMixin` | events on server resource-pack apply/remove |
| 9 | `ScoreboardMixin2` | `ScoreboardNameTagMixin` | invalidates name tags on team changes; blocks duplicate teams |
| 10 | `ScoreboardMixin3` | `ScoreboardAccessorMixin` | `@Accessor` for `objectiveDisplaySlots` / `entitiesScoreObjectives` |
| 11 | `ServerListEntryNormalMixin2` | `ServerListEntryNormalPingMixin` | per-entry overlay draw + 3s ping timeout around `EXECUTOR.submit` |
| 12 | `ServerListEntryNormalMixin3` | `ServerListEntryNormalAccessorMixin` | `@Mutable @Accessor("EXECUTOR")` |
| 13 | `ServerPingerMixin2` | `ServerPingerLunarServerMixin` | copies the Lunar-server flag from the status response into `ServerData` |
| 14 | `ShaderGroupMixin2` | `ShaderGroupRewindMixin` | substitutes the rewind framebuffer for `mainFramebuffer` |
| 15 | `ShaderMixin2` | `ShaderCustomResourceMixin` | swaps the `IResourceManager` for `lunar-shaders/` paths |
| 16 | `SkinManagerMixin2` | `SkinManagerSkinLoadingMixin` | unsecure hosted-world skins + synchronous skin loading during rewind |
| 17 | `StatFileWriter2` | `ResourceLocationAdventureMixin` | `@Implements(@Interface(iface = Key.class, prefix = "adventure$"))` |
| 18 | `StatFileWriter3` | `NetworkPlayerInfoSkinMixin` | `NetworkPlayerInfo$1.skinAvailable` → skin-loaded event |
| 19 | `TextureMapMixin3` | `TextureMapTransparencyMixin` | strips alpha after atlas image read; no-ops OptiFine `scaleImage` |
| 20 | `ThreadDownloadImageDataMixin2` | `ThreadDownloadImageDataImageFoundMixin` | `Bridge20Extension` override of `bridge$setImageFound` |
| 21 | `TileEntitySkullRendererMixin2` | `TileEntitySkullRendererSkinsMixin` | 3D-skin player-head skull hooks (`Skins3d` / `Pkg6`) |
| 22 | `TimerMixin2` | `TimerRewindMixin` | rewind clock replaces `System.nanoTime`; partial-tick recompute |
| 23 | `WorldMixin3` | `WorldEventMixin` | world events (collisions/entity join/remove/particles/horizon) + lighting skip + rain |
| 24 | `WorldMixin4` | `WorldAccessorMixin` | `@Mutable @Accessor("isRemote")` |

## Applier notes

`python3 tools/apply_class_renames.py --map tools/renames/classes-legacymixin.tsv`
(dry run):

```
applied=21 skipped=3 files_touched=21 files_renamed=21 mode=dry-run
```

The 3 skips are **old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `ChunkMixin2` | `com.moonsworth.lunar.mixin.mixin` (a different bridge) |
| `EntityRendererMixin3` | `com.moonsworth.lunar.client.mixin` (lightmap/GUI mixin) |
| `TextureMapMixin3` | `com.moonsworth.lunar.client.mixin` |

The package/import-aware applier handles all of them:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-legacymixin.tsv
# → rows=24 skipped=0 files_touched=24 files_renamed=24 mode=dry-run
```

Use the **aware** applier for this map (or split the 3 collision rows out). Do
not run the v1 applier with `--allow-collisions`: it would rewrite the
unrelated `com.moonsworth.lunar.mixin.mixin.ChunkMixin2` and
`com.moonsworth.lunar.client.mixin.{EntityRendererMixin3,TextureMapMixin3}`.

All 24 new simple names were checked tree-wide with the applier's declaration
scan and a manual `grep -rE "\b(class|interface|enum) NewName\b" src/main/java`
— no collisions.

## Caveats / follow-ups

* **`StatFileWriter2` / `StatFileWriter3` are misnamed.** They target
  `ResourceLocation` and `NetworkPlayerInfo$1` respectively, not
  `net.minecraft.stats.StatFileWriter` (that is `StatFileWriterMixin`). The new
  names reflect the real targets.
* **`RenderPlayerMixin2` vs `RenderPlayerMixin3` overlap.** Both inject the same
  3D-skin sleeve logic on `renderRightArm`/`renderLeftArm`; `Mixin2` is a
  superset (adds the render events, `renderModel` override and rewind hook).
  They are named by their distinguishing purpose.
* **`WorldMixin3` is multi-purpose** (events + lighting + weather). It is named
  `WorldEventMixin` for the dominant event surface; `WorldMixin4` is the
  accessor for the same target.
* **`ChunkMixin2` / `EntityRendererMixin3` names match the applied legacy-mixin
  ledger** (`APPLIED.md`, `Prompt.md`); the current tree has no other class
  holding those names, so they are free.

## Verification

```
md5sum tools/renames/cluster-17.txt            # b2f9a1618c42618f7cbe164c2cbe4127
md5sum tools/renames/classes-legacymixin.tsv   # 9cec011bbf94c18f35fd79a26fe48672
python3 tools/apply_class_renames.py --map tools/renames/classes-legacymixin.tsv
# → applied=21 skipped=3 files_touched=21 files_renamed=21 mode=dry-run
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-legacymixin.tsv
# → rows=24 skipped=0 files_touched=24 files_renamed=24 mode=dry-run
```