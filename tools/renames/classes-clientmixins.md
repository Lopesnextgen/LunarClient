# Cluster 07 — `com.moonsworth.lunar.client.mixin` + `com.moonsworth.lunar.ichor.mixin` + neighbours (38 rows)

Source revision: `tools/renames/cluster-07.txt` (38 rows: 36 top-level types +
2 nested `SignatureVisitorImpl.Data2` / `.Data3`).
Map: `tools/renames/classes-clientmixins.tsv`.

## What this cluster actually is

The cluster is a grab-bag of four unrelated things that the decompiler left in
three packages:

1. **`client.mixin` — real mixins + the AssetServer websocket stack.** The
   `mixin-renames.tsv` restructure merged the `menublur` / `minimap` / `mobsize`
   mixin trees into `com.moonsworth.lunar.client.mixin` and suffixed the
   duplicates. Only the `minimap`/`mobsize` copies survive in the tree
   (`EntityRendererMixin2/3`, `EntityFXMixin2`, `TextureMapMixin2/3`); the
   `menublur` `EntityRendererMixin`/`TextureMapMixin` are missing. The same
   package also holds the non-mixin websocket client (`EntityRenderer4`), its
   reconnect timer (`EntityRenderer3`), the auth-service factory
   (`EntityRenderer2`), the connection-state enum (`EntityRendererType2`) and a
   friend chat-line model (`EntityRenderer5`).
2. **`ichor.mixin` — Ichor's generic-signature parser.** A Java generic
   signature → descriptor converter built on ASM's `SignatureVisitor` and
   `org.cadixdev.bombe.type.BaseType` (`SignatureVisitorImpl` + the
   `MixinHelper` descriptor family). Not mixins at all.
3. **`framework.feature.rewind` — the replay/rewind stack.** A ReplayMod-style
   replay editor: project model, keybind handler, timeline clips and the replay
   handler interface/impl. The `.gui` sub-package and the
   `rewindhandlersNameplateCore.mixin` recorders were already named by
   `classes-rewindgui`; these are the core classes they reference.
4. **`inactive.mixin.fishing` — a cosmetic-entity task system.** Not fishing and
   not mixins: tasks that drive a companion/pet entity (`AttachToOwnerTask`,
   `LookAtOwnerTask`, `MoveToOwnerTask`, `TeleportToOwnerTask`, …). The `toString`
   of each concrete class is the ground truth for its name.

## Renames (38 rows)

### `com.moonsworth.lunar.client.mixin`

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `EntityFXMixin2` | `EntityFXMovementMixin` | `@Mixin(EntityFX.class)` (mobsize); overrides `moveEntity` to skip vanilla collision physics while the particle-movement option is off |
| 2 | `EntityRenderer2` | `YggdrasilAuthServiceFactory` | reflective factory for `YggdrasilAuthenticationService`; used by `WebSocketClientHandler` |
| 3 | `EntityRenderer3` | `ReconnectBackoff` | websocket reconnect timer (15s/1s, doubling to a max) + reconnect-JWT expiry check |
| 4 | `EntityRenderer4` | `AssetServerClient` | the Lunar AssetServer `WebSocketClient` (`.../game`, log tag "Assets"); all service stubs + push dispatch |
| 5 | `EntityRenderer5` | `FriendChatMessage` | friend chat-line model (`Gui2.provide()` → `{user,time,message}`) |
| 6 | `EntityRendererMixin2` | `EntityRendererReachMixin` | `@Mixin(EntityRenderer.class)` (minimap); HUD overlay redirect + the unique Apollo reach tracking on `getMouseOver` |
| 7 | `EntityRendererMixin3` | `EntityRendererOverlayMixin` | `@Mixin(EntityRenderer.class)` (mobsize); HUD overlay redirect (`renderGameOverlay(F)`), no reach |
| 8 | `EntityRendererType2` | `ConnectionState` | `DISCONNECTED`/`READY` websocket connection-state enum |
| 9 | `TextureMapMixin2` | `TextureMapAtlasLoadMixin` | `@Mixin(TextureMap.class)` `@MixinCondition(absent="optifine")` (minimap); fires the atlas-loaded event + opaque fix |
| 10 | `TextureMapMixin3` | `TextureMapOpaqueFixMixin` | `@Mixin(TextureMap.class)` (mobsize); opaque-texture (alpha-strip) fix |

### `com.moonsworth.lunar.ichor.mixin` (Ichor signature parser)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 11 | `Data2` (nested) | `TypeVariableRef` | `SignatureVisitorImpl$Data2`: type-variable reference node, resolved to its formal parameter |
| 12 | `Data3` (nested) | `TypeVariableName` | `SignatureVisitorImpl$Data3`: `T<name>;` descriptor |
| 13 | `MixinHelper2` | `MethodSignature` | parameter list + return type → `(params)return` |
| 14 | `MixinHelper3` | `TypeSignature` | parsed type node (base descriptor + type arguments) |
| 15 | `MixinHelper32` | `WildcardTypeSignature` | unbounded wildcard `*` |
| 16 | `MixinHelper33` | `ArrayTypeSignature` | array node (component + depth) |
| 17 | `MixinHelper4` | `ClassSignature` | formal type params + superclass + interfaces |
| 18 | `MixinHelper5` | `PrimitiveTypeSignature` | primitive/void over `bombe` `BaseType` |
| 19 | `MixinHelper6` | `ArrayElementSignature` | `"["*depth + component` descriptor |
| 20 | `MixinHelper7` | `ClassTypeSignature` | `L<internalName>;` descriptor |

### `com.moonsworth.lunar.client.framework.feature.rewind`

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 21 | `Rewind2` | `KeybindCombination` | key + ctrl/shift/alt, the key of `Rewind4`'s action map |
| 22 | `Rewind3` | `RewindProject` | `toString` "RewindProject": name/versions/timeline/mediaPool/repository/externalMods |
| 23 | `Rewind4` | `ReplayKeybindHandler` | keybind → replay-action dispatcher; owned by `RewindHandlers.field35` |
| 24 | `RewindImpl2` | `TextSegment` | timeline clip `type()=="text"` |
| 25 | `RewindIterator22` | `AudioSegment` | timeline clip `type()=="audio"` (owns an `AudioStream`) |
| 26 | `RewindIterator23` | `GameplaySegment` | timeline clip `type()=="gameplay"` |
| 27 | `RewindIterator_2` | `ReplayHandlerImpl` | implements `Rewind_4`; manages the replay-segment deque |
| 28 | `Rewind_2` | `EffectSegment` | timeline clip `type()=="effect"` |
| 29 | `Rewind_4` | `ReplayHandler` | replay-handler interface (getReplayFileList/isPaused/pause/start/end/recording/mark/tick/handleReplayPacket/onReplayClosed/endReplay) |

### `com.moonsworth.lunar.client.inactive.mixin.fishing` (cosmetic tasks)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 30 | `FishingHandler3` | `AttachToOwnerTask` | `toString` "AttachToOwnerTask{bone=…}" |
| 31 | `FishingHandler4` | `AbstractTask` | abstract base with the `continuous` flag |
| 32 | `FishingHandler42` | `LookAtOwnerTask` | `toString` "LookAtOwnerTask" |
| 33 | `FishingHandler43` | `LookAtTargetTask` | `toString` "LookAtTargetTask" |
| 34 | `FishingHandler44` | `LookAtTask` | `toString` "LookAtTask" |
| 35 | `FishingHandler45` | `LookAtBlockTask` | `toString` "LookAtBlockTask" |
| 36 | `FishingHandler5` | `MoveToOwnerTask` | `toString` "MoveToOwnerTask{distance=…}" |
| 37 | `FishingHandler6` | `AbstractTimedTask` | abstract base leaving `canContinue` abstract |
| 38 | `FishingHandler62` | `TeleportToOwnerTask` | `toString` "TeleportToOwnerTask{min_radius,max_radius,duration}" |

## Naming notes

* **Mixin disambiguation.** `EntityRendererMixin2` and `EntityRendererMixin3`
  are near-identical HUD-overlay patches from the `minimap` and `mobsize`
  modules; the only structural difference is that `EntityRendererMixin2` also
  records the player reach for Apollo's `PacketEnrichmentModule`
  (`apollo$saveReachVariable` / `apollo$reachCheck$v1_7`) and uses the v1_7
  `renderGameOverlay(FZII)` signature, so it takes the `Reach` purpose and
  `EntityRendererMixin3` takes the plain `Overlay` purpose. Likewise
  `TextureMapMixin2` (fires the atlas-loaded event) vs `TextureMapMixin3`
  (opaque fix only). The legacy package already owns
  `TextureMapTransparencyMixin`, so the opaque variant is named
  `TextureMapOpaqueFixMixin` to stay unique.
* **Non-mixins in the mixin package.** `EntityRenderer2/3/4/5` and
  `EntityRendererType2` carry the decompiler's `EntityRenderer` prefix but are
  the websocket stack; they are named by role, not `<Target><Purpose>Mixin`.
* **`ichor.mixin` is not mixin infra.** It is a generic-signature parser; the
  `MixinHelper` base interface (not in this cluster) is the descriptor contract,
  and the renamed classes follow the standard `MethodSignature` /
  `ClassSignature` / `TypeSignature` vocabulary.
* **Same simple names elsewhere.** `framework.feature.rewind.mixin.Rewind2/3`
  are *different* classes (metadata.json models) and are not touched: the
  aware applier resolves references by package/import, and the dry run confirms
  only the parent-package copies (`Rewind2` 10 hits, `Rewind3` 10 hits) are
  rewritten. `MixinHelper2/5` also exist in other packages; only the
  `ichor.mixin` declarations move.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-clientmixins.tsv
# → rows=38 skipped=0 files_touched=105 files_renamed=36 mode=dry-run
#   (2 nested rows rename inside SignatureVisitorImpl.java; 36 top-level file renames)
```

Every new name was checked to be undeclared tree-wide:

```
grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java   # no hits
```

Cross-checks used: `tools/mappings-snapshot/restructure/mixin-renames.tsv`
(module provenance of the mixins), `normalize-renames.tsv` (original jar
subtrees of the rewind classes), the `ReplayMod-v1_8-2.6.24.jar` runtime
reference for the rewind handler/segment vocabulary, and the class `toString`
strings for the cosmetic tasks.