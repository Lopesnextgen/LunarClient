# Cluster 02 — `com.moonsworth.lunar.client.highlight.mixin.gui` (26 classes)

Source revision: `tools/renames/cluster-02.txt` md5 `43939ff1407a32edcd3b99907965f4d0`
(26 rows: 21 top-level `HighlightImpl2`…`HighlightImpl22`, `HighlightBase2`, and
the nested `HighlightBase2$Data13/Data14` + `HighlightBase$Data15/Data16`).
Map: `tools/renames/classes-highlightmixgui.tsv` md5 `3fc1fea7ad0fd528498b7c775d5ae035`.

> Note: the tree was being renamed by other batches while this cluster was
> analysed (`@Annotation3` → `@TriggeredBy`, `GuiRewindhandlersHandlerNN` →
> `ScoreboardListener`/`TabListListener`/`HypixelLocationListener`). The lazy
> class names in this cluster are unaffected.

## What this package actually is

`com.moonsworth.lunar.client.highlight` is the client's **event system** (a
Forge-like bus, `LunarEventBus`), and this package is one category of event
data classes — the network/screen/container events fired from `NetHandlerPlayClient`,
`Minecraft`, `BossStatus`, `ServerPinger`, `ResourcePackRepository`, the GUI
mixins and the rewind packet recorder. None of them are mixins despite the
`mixin` path segment: every class extends `Highlight` (or the cancellable
`HighlightImpl`) and is posted with `LunarEventBus.method29().method12(Class,
Supplier)`.

Evidence sources:

* **Firers** recovered from `tools/work/quarantine/src` (the mixin sources that
  the current tree has quarantined) and from the staging decompile
  `tools/work/staging/decompiled` — `NetHandlerPlayClientMixin2`,
  `NetHandlerPlayClientMixin`, `MinecraftMixin`, `ServerPingerMixin3`,
  `S38PacketPlayerListItemMixin`, `BossStatusMixin`, `GuiIngameMenuMixin`,
  `GuiEditSignFinishMixin`, `ResourcePackRepository*Mixin`,
  `AbstractClientPlayerMixin2`, `WorldClientMixin`.
* **Consumers** in `src/main/java` (HUD modules, SkyBlock solvers, rewind
  recorders) and the `@TriggeredBy(Listener)` annotations on
  `HighlightImpl2/3/20`.
* **Provenance**: `tools/work/mappings/restructure-merged.tsv` /
  `tools/mappings-snapshot/normalize-renames.tsv` map the obfuscated originals
  (`CORRCOHCRHOHHOIHOIOICORROHOOOO` = `HighlightImpl2`, …) into this package.

## Renames (26 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `HighlightImpl2` | `EventScoreboardUpdate` | scoreboard sidebar (`Lighting4`) fired at TAIL of `handleTeams` (S3EPacketTeams action 2); `@TriggeredBy(ScoreboardListener)` |
| 2 | `HighlightImpl3` | `EventTabListUpdate` | empty; fired at RETURN of `handlePlayerListItem`; `@TriggeredBy(TabListListener)` reads `getPlayerInfoList` |
| 3 | `HighlightImpl4` | `EventTitle` | title/subtitle `(Component, Type SERVER/APOLLO, isSubtitle)`, cancellable; `handleTitle` (S45PacketTitle) + Apollo `Highlight3Iterator14` |
| 4 | `HighlightImpl5` | `EventServerPing` | `(host, port)` + current `ServerData`; fired from `ServerPinger.run` |
| 5 | `HighlightImpl6` | `EventDisconnectReason` | reason `String`; fired at HEAD of `handleDisconnect` (S40PacketDisconnect) |
| 6 | `HighlightImpl7` | `EventServerResourcePackRemove` | empty; `ResourcePackRepository.clearResourcePack`/`func_148529_f` |
| 7 | `HighlightImpl8` | `EventPluginMessage` | `(channel, byte[])`, cancellable; HEAD of `handleCustomPayload` |
| 8 | `HighlightImpl9` | `EventServerTick` | empty; RETURN of `handleConfirmTransaction`; `TpsTracker` counts ticks |
| 9 | `HighlightImpl10` | `EventServerChange` | boolean `method1()` (true=disconnect); fired by freelook `Highlight3Handler` on ping/disconnect; cache resets |
| 10 | `HighlightImpl11` | `EventDisconnect` | static `method1()/method2(boolean)`; `NetHandlerPlayClient.cleanup`/`onDisconnect` |
| 11 | `HighlightImpl12` | `EventServerBrand` | MC\| channel name; RETURN of `handleCustomPayload` for `MC|*`; brand tests |
| 12 | `HighlightImpl13` | `EventPacket` | `(packet, ByteBuf, direction, protocol state)`; rewind `PacketRecorder` |
| 13 | `HighlightImpl14` | `EventScreenAction` | cancellable `Runnable`; `GuiIngameMenuMixin` Multiplayer/Disconnect buttons |
| 14 | `HighlightImpl15` | `EventBossBarUpdate` | boss name `Component`; `BossStatus.setBossStatus` / `handleUpdateBossInfo` |
| 15 | `HighlightImpl16` | `EventServerJoin` | empty; RETURN of `handleJoinGame`; `CLEAR_TITLE_ON_SERVER_SWITCH` |
| 16 | `HighlightImpl17` | `EventSignUpdate` | `String[]` sign lines; `GuiEditSign.finishEditing` |
| 17 | `HighlightImpl18` | `EventGuiScreen` | **empty, no firer/consumer anywhere** — best-effort generic name, low confidence |
| 18 | `HighlightImpl19` | `EventWorldEditSelection` | `Worldeditcui2` selection; `Worldeditcui.method3` (`s`/`p` commands) |
| 19 | `HighlightImpl20` | `EventLocationChange` | `(from, to) HypixelLocation`; `@TriggeredBy(HypixelLocationListener)` |
| 20 | `HighlightImpl21` | `EventNetworkEncryption` | `(NetworkManagerBridge, Channel, SecretKey)`; unused in tree — named from payload |
| 21 | `HighlightImpl22` | `EventServerResourcePackUpdate` | `List<File>` server packs; `setResourcePackInstance`/`onDownloadComplete` |
| 22 | `HighlightBase2` | `EventPlayerListEntry` | abstract base holding the `GameProfile` |
| 23 | `Data13` (nested) | `EventPlayerListRemove` | `HighlightBase2$Data13`: REMOVE_PLAYER entry (S38 player-list packet) |
| 24 | `Data14` (nested) | `EventPlayerListAdd` | `HighlightBase2$Data14`: ADD_PLAYER entry; `NickHider` updates the nick map |
| 25 | `Data15` (nested) | `EventTeleportPre` | `HighlightBase$Data15`: teleport target from S08PacketPlayerPosLook HEAD |
| 26 | `Data16` (nested) | `EventTeleportPost` | `HighlightBase$Data16`: player position at TAIL of `handlePlayerPosLook` |

Rows 23–26 carry the 5th `file` column and name the owner in the evidence
(`HighlightBase2$Data13`, `HighlightBase$Data15`, …) so a nested-aware applier
renames them inside the owner file instead of mass-renaming `DataN`.

## Firer summary

| event | hook |
|---|---|
| `EventScoreboardUpdate` | `NetHandlerPlayClient.handleTeams` TAIL (action 2) |
| `EventTabListUpdate` | `NetHandlerPlayClient.handlePlayerListItem` RETURN |
| `EventTitle` | `handleTitle` (S45PacketTitle) + Apollo title packet |
| `EventServerPing` | `ServerPinger.run` (connection opened) |
| `EventDisconnectReason` | `handleDisconnect` HEAD (S40PacketDisconnect) |
| `EventServerResourcePackRemove` | `ResourcePackRepository.clearResourcePack` |
| `EventPluginMessage` | `handleCustomPayload` HEAD |
| `EventServerTick` | `handleConfirmTransaction` RETURN |
| `EventServerChange` | freelook `Highlight3Handler` (ping/disconnect) |
| `EventDisconnect` | `NetHandlerPlayClient.cleanup` / `onDisconnect` |
| `EventServerBrand` | `handleCustomPayload` RETURN (`MC|*`) |
| `EventPacket` | rewind `PacketRecorder` / `Nameplate` |
| `EventScreenAction` | `GuiIngameMenuMixin` actionPerformed |
| `EventBossBarUpdate` | `BossStatus.setBossStatus`, `handleUpdateBossInfo` |
| `EventServerJoin` | `handleJoinGame` RETURN |
| `EventSignUpdate` | `GuiEditSign.finishEditing` |
| `EventWorldEditSelection` | `Worldeditcui.method3` |
| `EventLocationChange` | `GuiRewindhandlersHandler23.method8` |
| `EventServerResourcePackUpdate` | `ResourcePackRepository.setResourcePackInstance` / `onDownloadComplete` |
| `EventPlayerListAdd`/`Remove` | `S38PacketPlayerListItemMixin.readPacketData` |
| `EventTeleportPre`/`Post` | `handlePlayerPosLook` HEAD / TAIL |

## Applier dry run

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-highlightmixgui.tsv
# → [aware-renames] 26 rows (4 nested); rows=26 skipped=0 files_touched=110 files_renamed=22 mode=dry-run

python3 tools/apply_class_renames.py --map tools/renames/classes-highlightmixgui.tsv
# → applied=22 skipped=4 files_touched=155 files_renamed=22 mode=dry-run
#   (the 4 nested Data13/14/15/16 rows need the aware applier)
```

Use the **aware** applier: the v1 applier cannot touch the nested rows and its
word-boundary rewrite would also hit the many same-named `HighlightImplN`
classes in `highlight`, `highlight.mixin.*`, `legacy.*` etc. Do **not** run
either with `--allow-collisions`.

## Caveats / follow-ups

* **`HighlightImpl18` is a genuine orphan.** It is empty and has no reference in
  `src/main/java`, `tools/work/quarantine/src` or any runtime jar
  (`lunar.jar`, `legacy-*`, `forge-*`, `genesis-*`, `common-*`, `optifine-*`,
  `lunar-replaymod-*`). The name `EventGuiScreen` is a best-effort generic
  screen-event label; if a firer is recovered (likely a screen/container mixin
  that is still missing) it should be renamed. It is **not** the same class as
  the root `com.moonsworth.lunar.client.highlight.HighlightImpl18`
  (= `EventResourcesReload`, from `highlight/mixin/mixinExtra`) or
  `highlight.mixin.highlight.HighlightImpl18` (= `EventGroundItemTransform`).
* **`HighlightImpl21`** is also unused; its payload
  (`NetworkManagerBridge` + `Channel` + `SecretKey`) identifies the vanilla
  `NetworkManager.enableEncryption` step, so `EventNetworkEncryption` is
  payload-derived, not firer-derived.
* **`HighlightImpl10` boolean** is `true` on disconnect and `false` on
  server-ping/plugin-message (freelook `Highlight3Handler`); `EventServerChange`
  reflects that connection-state semantics. `HighlightImpl16` is the actual
  JoinGame event (`EventServerJoin`), kept distinct.
* **`HighlightBase`** (the teleport base) and `HighlightImpl`, `HighlightIterator`
  and `Gui` in the same package are **not** in this cluster and are left for
  whoever claims them (`HighlightBase` → e.g. `EventTeleport`).
* Names were checked with the applier's declaration index (no "new name already
  declared" skips) and manually against the tree-wide event list; none collide
  with the existing `Event*` classes (`EventScreenOpen`, `EventScreenChange`,
  `EventResourcePackUpdate`, `EventResourcesReload`, `EventTick`, …).