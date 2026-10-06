# Cluster 20 — `client.util.colorsaturation` + `client.waypoints` + neighbours (7 rows)

Source revision: `tools/renames/cluster-20.txt` md5 `4cf443631683f838996cae77a1d6e24c`
(7 rows). Map: `tools/renames/classes-waypoints.tsv` md5
`1ccade5b1dd1487cb783a911e47246a3` (6 renames + 1 "keep").

## What this cluster actually is

The cluster name is misleading. Two of the three package names are leftover
decompiler scrambling and do **not** describe the code:

* **`client.util.colorsaturation` is NOT colour-saturation post-processing.**
  It is Lunar's render wrapper around the bundled **Emoticons `BOBJLoader`**
  (Bedrock/BOBJ model format, `mchorse.emoticons.skin_n_bones.api.bobj`).
  `Colorsaturation` is a single renderable mesh (GL VBOs in
  `ColorsaturationHandler`, skinned buffers in the missing
  `ColorsaturationTask`), and `Colorsaturation2` is the **model**: a list of
  meshes plus a lazily-aggregated bounding box. (The real colour-saturation
  effect lives in `client/mod/render/ColorSaturation.java` and the
  `…/colorsaturation/ColorsaturationExtension` classes, which are unrelated.)
* **`client.waypoints` is NOT the waypoint HUD.** It is the **game-IPC
  websocket client**: `WebSocketClientIterator extends java_websocket
  WebSocketClient` (connects to `ws://127.0.0.1:<LaunchOptions port>`,
  handshake header `lc-handshake`) and `RpcChannelImpl implements
  com.google.protobuf.RpcChannel` for the launcher services (auth, launch,
  location, tebex, paynow, browser, promotion, styngr).
* **`config`** is Lunar's **MinecraftVersion** system: `Config` is the version
  class loaded from `versions.conf` (`Config$Data` = `MinecraftVersion.ModuleGroup`,
  `Config$Data2` = `MinecraftVersion.MinecraftVersionBuilder`), `ConfigType` is
  the maven-repository enum, and `Config2` is the ordinal table.
* **`legacy.wrapper.FaceEdgeMasks`** is a face/edge bit-mask table for cube
  culling (dead code, no references in the tree).
* **`replaymod.forge.Ichor5Loader`** is the ReplayMod `IchorLoader`; its nested
  handler registers the ReplayMod mixin configs.
* **`v1_8.optifine.wrapper.Wrapper2`** is the OptiFine `CustomItems` adapter
  implementing the `Slayer6` bridge.
* **`net.minecraft.entity.ai.EntityAIWatchClosest2`** is the stock MCP 1.8.9
  class (villager look-at-AI base for `EntityAIVillagerInteract`), not a lazy
  name — see caveats.

Provenance (from `tools/work/mappings/restructure-merged.tsv`):

| current file | original obf class |
|---|---|
| `client/util/colorsaturation/Colorsaturation2` | `client/util/colorsaturation/HORHROIOIOICIRHIOCOICHHHIHCIIO` |
| `client/waypoints/RpcChannelImpl$Data2` | `client/waypoints/HHRROIIHRRICIIHIIHICRHHRHOHHOO$HORHROIOIOICIRHIOCOICHHHIHCIIO` |
| `config/Config2` | `config/HRICOROOOCCOCOROCRHHCRRIRCOICO` |
| `replaymod/forge/Ichor5Loader$Data2` | `replaymod/forge/HHRROIIHRRICIIHIIHICRHHRHOHHOO$HORHROIOIOICIRHIOCOICHHHIHCIIO` |
| `v1_8/optifine/wrapper/Wrapper2` | `v1_8/optifine/wrapper/ICRHORIIHOHROHOHOCOOHOOCOORRHO` |
| `legacy/wrapper/FaceEdgeMasks$Data2` | (outer named by an earlier pass; not in restructure-merged) |

Runtime cross-checks (`java -jar tools/bin/cfr-0.153.jar`):

* `legacy-…jar` `v1_8/optifine/wrapper/ICRHORIIHOHROHOHOCOOHOOCOORRHO` decompiles
  to exactly the `Wrapper2` source (implements `Slayer6`, calls
  `net.optifine.CustomItems`).
* `lunar-replaymod-forge-mixins-…jar`
  `replaymod/forge/HHRROIIHRRICIIHIIHICRHHRHOHHOO` decompiles to the
  `Ichor5Loader` source with the nested mixin-registering handler.
* `lunar-all-named.jar` `client/util/colorsaturation/*` decompiles to the
  `Colorsaturation`/`Colorsaturation2`/`ColorsaturationTask`/`ColorsaturationHandler`
  sources (all four classes; `ColorsaturationTask` is absent from the tree).

## Renames (6)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | `client.util.colorsaturation` | `Colorsaturation2` | `BOBJModel` | model = `ImmutableList<Colorsaturation>` meshes built from `BOBJLoader.loadMeshes(BOBJData)`; `method3` sums mesh sizes, `method4/6` lazily aggregate the mesh AABBs (`Horsestats12`), `free()` deletes all meshes |
| 2 | `client.waypoints` | `Data2` (nested `RpcChannelImpl$Data2`) | `PendingRequest` | pending IPC request in the Guava cache keyed by `requestId`: service.method name + response prototype `Message` + `RpcCallback`; removal listener logs `"Timeout waiting for response to …"` |
| 3 | `config` | `Config2` | `MinecraftVersionOrdinal` | 40 int constants 0..39; `Config` (=`MinecraftVersion`) declares exactly 40 versions `v1_7`..`v26_2` with ordinals 0..39 in `versions.conf`; unreferenced helper |
| 4 | `legacy.wrapper` | `Data2` (nested `FaceEdgeMasks$Data2`) | `Line` | the 12 cube **edge** masks + ALL (`0x11 DOWN_WEST`, `0x12 UP_WEST`, `0x21 DOWN_EAST`, `0x22 UP_EAST`, `0x05 DOWN_NORTH`, `0x06 UP_NORTH`, `0x09 DOWN_SOUTH`, `0x0A UP_SOUTH`, `0x14 NORTH_WEST`, `0x24 NORTH_EAST`, `0x18 SOUTH_WEST`, `0x28 SOUTH_EAST`, `63`); matches reference-client `FaceMasks.Quad`/`FaceMasks.Line` tables |
| 5 | `replaymod.forge` | `Data2` (nested `Ichor5Loader$Data2`) | `ReplayModMixinHandler` | `MixinInternal3Handler` that registers `mixins.meta.ichor.replaymod.<id>.json` / `mixins.ichor.replaymod.<id>.json` at `META_MIXIN`/`MIXIN` |
| 6 | `v1_8.optifine.wrapper` | `Wrapper2` | `CustomItemsWrapper` | implements `Slayer6`, delegates to `net.optifine.CustomItems` (`isUseGlint`, `renderCustomEffect`, `getCustomItemModel`) |

Nested rows (2, 4, 5) carry the owner in the evidence as `Owner$Old` and the
5th `file` column, per the aware applier's nested handling.

## Row 7 is a false positive — no rename

`net.minecraft.entity.ai.EntityAIWatchClosest2` is the **real MCP 1.8.9 name**
(obf `rg`, `tools/mappings-snapshot/classes.tsv:2355`). Its source is
byte-for-byte the same as 509 reference clients
(`Actinium/net/minecraft/entity/ai/EntityAIWatchClosest2.java`):
`extends EntityAIWatchClosest`, `setMutexBits(3)`, used by
`EntityAIVillagerInteract` and `EntityVillager`. The "trailing digit" is part
of the canonical name, so it is intentionally **not** in the map.

## Caveats / follow-ups

* **`Colorsaturation2` collides across four packages**
  (`client.util.colorsaturation`, `…rewind.rewindhandlers.colorsaturation`,
  `client.inactive.mixin.colorsaturation`, `…inactive.mixin.colorsaturation.mixin`).
  The map carries the 5th `file` column so the aware applier
  (`tools/apply_class_renames_aware.py`) scopes the declaration; the v1 applier
  would skip it as a collision. Do **not** run with a global token replace.
* **`Data2` is declared in 34 files.** The three nested rows rely on the
  `Owner$Old` evidence form + `file` column. Run the aware applier, not v1.
* **`Config2` is dead code** (referenced only by itself in the whole
  `lunar-all-named.jar`). The ordinal-table reading is inferred from the exact
  40-vs-40 match with `versions.conf`; if a future pass finds a reference, the
  name may be refined.
* **Ideal names for the surrounding classes (not in this cluster):**
  `client.util.colorsaturation.Colorsaturation` → `BOBJMesh`,
  `ColorsaturationHandler`/`ColorsaturationTask` → `BOBJStaticMesh` /
  `BOBJSkinnedMesh`, and `legacy.wrapper.FaceEdgeMasks` → `FaceMasks` with
  `Data` → `Quad` (the reference-client pairing). Whoever owns those files
  should claim them.
* `EntityAIWatchClosest2` must be left untouched by any future map.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-waypoints.tsv --verbose
# [aware-renames] 6 rows (3 nested); 6594 java files
#   client.waypoints: RpcChannelImpl$$Data2 -> PendingRequest (1 files)
#   legacy.wrapper:   FaceEdgeMasks$$Data2 -> Line (1 files)
#   replaymod.forge:  Ichor5Loader$$Data2 -> ReplayModMixinHandler (1 files)
#   client.util.colorsaturation: Colorsaturation2 -> BOBJModel (2 hits)
#   config:           Config2 -> MinecraftVersionOrdinal (2 hits)
#   v1_8.optifine.wrapper: Wrapper2 -> CustomItemsWrapper (2 hits)
# [aware-renames] rows=6 skipped=0 files_touched=6 files_renamed=3 mode=dry-run
```