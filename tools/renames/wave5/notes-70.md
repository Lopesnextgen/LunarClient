# Cluster 70 — `com.moonsworth.lunar.client.highlight.mixin.gui` (26 rows + 4 adjacent)

Map: `tools/renames/wave5/classes-70.tsv` — **30 rows (4 nested), 0 skipped.**

```
[aware-renames] 30 rows (4 nested); 9808 java files
[aware-renames] rows=30 skipped=0 files_touched=100 files_renamed=26 mode=dry-run
```

The package is **not mixins**: it is the payload-class bucket of Lunar's event
bus. Every class in it is a **rescued pre-rename copy** (stale-jar twin) of a
class that already carries its final name in
`com.moonsworth.lunar.client.event.mixin.gui`. Because the applier refuses a
`new` name that is already declared, each duplicate gets a **unique, non-lazy
variant name** — the `classes-40.md` §2 / `classes-51.md` §2 / cluster-66
practice. The canonical twin of every row is recorded below, so the map is also
an inverse map if the dedupe pass keeps this generation instead (§5).

| | count |
|---|---|
| cluster rows | 26 (22 top-level + 4 nested `Data13..Data16`) |
| rows emitted | 30 (26 cluster + 4 adjacent non-digit placeholders in the same package) |
| skipped | 0 |
| missing paths / `net.minecraft.*` / shaded third-party | 0 |

The subagent made no source edits. The main agent picked the map up and applied it
to the working tree while these notes were being written; the 26 renamed files
now carry the variant names in the tree (nested renames included), pending the
commit. The canonical-twin pairings in §2 are still the merge reference.

## 1. What the package is

`com.moonsworth.lunar.client.highlight.Highlight` is the jar-bound event base
(see its javadoc); the source-side event system is `client.event`
(`LunarEvent`, `LunarEventBus`). The 26 cluster entries are immutable payload
holders that extend `Highlight` and are constructed on the event bus.

Lineage (verified with `git log --follow`):

1. `531c6f7d` renamed all 26 in place (`tools/renames/classes-highlightmixgui.tsv`,
   26 rows with evidence).
2. `moves-namesC.tsv` added `HighlightImpl → EventSlotUpdate` and
   `HighlightBase → EventTeleportBase`/nested pre/post (lines 6, 15).
3. `d6a452378` (bucket batch A, `client.highlight` → `client.event`) moved the
   renamed classes to `client.event.mixin.gui`
   (`git show --stat d6a452378`: `event/mixin/gui/EventServerTick.java +8`,
   `highlight/mixin/gui/EventServerTick.java −8`).
4. `b384aca20` (rescue) re-added the pre-rename copies here
   (`git log --diff-filter=A -- …/HighlightImpl2.java` → `b384aca20`).

## 2. Names (30 rows)

`dup of` = the already-applied canonical class in `client.event.mixin.gui`
(if the merge keeps *this* generation, the canonical column is the final name).
`refs` = files referencing this copy / the canonical twin (FQN-import
resolution, own file excluded). Roles/firers/consumers per row are in the
`evidence` column of the TSV.

| # | old | new (variant) | dup of | refs h/e |
|---|---|---|---|---|
| 1 | `HighlightImpl` | `SlotUpdateEvent` | `EventSlotUpdate` | 70 / 25 |
| 2 | `HighlightImpl2` | `ScoreboardUpdateEvent` | `EventScoreboardUpdate` | 17 / 11 |
| 3 | `HighlightImpl3` | `TabListUpdateEvent` | `EventTabListUpdate` | 5 / 12 |
| 4 | `HighlightImpl4` | `TitleEvent` | `EventTitle` | 3 / 4 |
| 5 | `HighlightImpl5` | `ServerPingEvent` | `EventServerPing` | 7 / 4 |
| 6 | `HighlightImpl6` | `DisconnectReasonEvent` | `EventDisconnectReason` | 2 / 1 |
| 7 | `HighlightImpl7` | `ServerResourcePackRemoveEvent` | `EventServerResourcePackRemove` | 4 / 4 |
| 8 | `HighlightImpl8` | `PluginMessageEvent` | `EventPluginMessage` | 3 / 3 |
| 9 | `HighlightImpl9` | `ServerTickEvent` | `EventServerTick` | 6 / 8 |
| 10 | `HighlightImpl10` | `ServerChangeEvent` | `EventServerChange` | 14 / 9 |
| 11 | `HighlightImpl11` | `DisconnectEvent` | `EventDisconnect` | 25 / 21 |
| 12 | `HighlightImpl12` | `ServerBrandEvent` | `EventServerBrand` | 3 / 3 |
| 13 | `HighlightImpl13` | `PacketEvent` | `EventPacket` | 7 / 4 |
| 14 | `HighlightImpl14` | `ScreenActionEvent` | `EventScreenAction` | 2 / 3 |
| 15 | `HighlightImpl15` | `BossBarUpdateEvent` | `EventBossBarUpdate` | 1 / 1 |
| 16 | `HighlightImpl16` | `ServerJoinEvent` | `EventServerJoin` | 11 / 12 |
| 17 | `HighlightImpl17` | `SignUpdateEvent` | `EventSignUpdate` | 1 / 2 |
| 18 | `HighlightImpl18` | `GuiScreenEvent` | `EventGuiScreen` | 0 / 0 |
| 19 | `HighlightImpl19` | `WorldEditSelectionEvent` | `EventWorldEditSelection` | 0 / 1 |
| 20 | `HighlightImpl20` | `LocationChangeEvent` | `EventLocationChange` | 4 / 4 |
| 21 | `HighlightImpl21` | `NetworkEncryptionEvent` | `EventNetworkEncryption` | 0 / 0 |
| 22 | `HighlightImpl22` | `ServerResourcePackUpdateEvent` | `EventServerResourcePackUpdate` | 4 / 5 |
| 23 | `HighlightBase2` | `PlayerListEntryEvent` | `EventPlayerListEntry` | 1 / 1 |
| 24 | `Data13` (`HighlightBase2$Data13`) | `PlayerListRemoveEvent` | `EventPlayerListRemove` | nested |
| 25 | `Data14` (`HighlightBase2$Data14`) | `PlayerListAddEvent` | `EventPlayerListAdd` | nested |
| 26 | `HighlightBase` | `TeleportEvent` | `EventTeleportBase` | 5 / 0 |
| 27 | `Data15` (`HighlightBase$Data15`) | `TeleportPreEvent` | `EventTeleportPre` | 1 / 1 |
| 28 | `Data16` (`HighlightBase$Data16`) | `TeleportPostEvent` | `EventTeleportPost` | 4 / 5 |
| 29 | `Gui` **adjacent** | `BridgePayload` | `EventBridgePayload` | 0 / 0 |
| 30 | `HighlightIterator` **adjacent** | `SkinLoadedEvent` | *(twin unnamed)* | 2 / 3 |

Rows 29–30 (and 1/26) are **outside the literal cluster file**: the wave-5
inventory only lists names that end in digits, so `HighlightImpl` (the parent
of `HighlightImpl2..22`), `HighlightBase`, `Gui` and `HighlightIterator` were
never allocated. They are the same kind of rescued duplicate and are included
so the package has no placeholders left; drop them if strict scope is wanted.
Row 30's canonical twin (`client.event.mixin.gui.HighlightIterator`) is still
**unnamed everywhere** — the recommended final name for it is
`EventSkinLoaded` (see §5).

Nested notes: `HighlightImpl4$Type` (nested enum) keeps its name here; the twin
renamed it to `TitleSource`. Per `classes-59.md` §3.5 nested types are left to
a later nested/member pass, so no row was emitted for it.

## 3. Duplication proof (why every row is the same class as its twin)

Structural comparison of each copy against its named twin (script-checked, not
heuristic): **field names, field-type sequence, method set and all string
constants are identical**; the only differences are

* the class's own name (and nested-class names),
* the already-applied **type renames** on the named side —
  `Lighting4→ScoreboardBridge`, `Bridge3_19→ServerDataBridge`,
  `Bridge3_21→PacketBridge`, `PacketDirection→PacketDirectionBridge`,
  `BridgeType2_2→ConnectionProtocol`, `Worldeditcui2→WorldeditSelection`,
  `Rewindhandlers2→HypixelLocation`,
  `NetworkConnectionBridge→NetworkManagerBridge`,
  `HighlightImpl4.Type→EventTitle.TitleSource`, and
* the base class (`extends Highlight` here, `extends LunarEvent`/
  `CancellableEvent` there — `LunarEvent extends Highlight`, same tree).

Obf provenance: `tools/mappings-snapshot/restructure/remaining-renames.tsv`
maps an original jar simple name (`CCROIHHH…`, `RIIICIRHR…`) to each placeholder;
the `highlight/mixin/gui/` package is real in `lunar.jar` and the
`event.mixin.gui` package exists only in the restructured source, i.e. these are
the same jar class. Git shows `R` rename for every pair in
`531c6f7d`/`moves-namesC` and `A` of the placeholder copy in `b384aca20`.

## 4. Split-brain: both generations are live today

The named generation is what the renamed listeners subscribe to (109 files /
152 FQN references: `CommissionListener`, `SkillXpListener`, `SkyblockFarmingHud`,
`SkyblockScoreboardParser`, `KillSounds`, `TabListListener`,
`ScoreboardListener`, `PacketRecorder`, …), while most **firers** were restored
by the rescue and still construct this generation, e.g.:

| event | this-generation firer (rescue copy) | canonical firer (renamed copy) |
|---|---|---|
| `SlotUpdateEvent` | `legacy/mixin/NetHandlerPlayClientEventMixin` | — |
| `TitleEvent` | `Highlight3Iterator14`, `NetHandlerPlayClientEventMixin` | `TitleApolloHandler` |
| `ServerResourcePackRemoveEvent` | `legacy/mixin/ResourcePackRepositoryMixin2` | `ResourcePackRepositoryServerPackMixin` |
| `ServerResourcePackUpdateEvent` | `ResourcePackRepositoryMixin2` | `ResourcePackRepositoryServerPackMixin`, `client/mixin/ResourcePackRepositoryMixin` |
| `SignUpdateEvent` | `legacy/mixin/GuiEditSignMixin2` | `GuiEditSignFinishMixin` |
| `ServerJoinEvent` | `legacy/mixin/NetHandlerPlayClientMixin` | — |
| `PacketEvent` | `NetworkManagerRewindMixin`, `NetworkManager_v1_7Mixin2`, `NetworkManager_v1_12Mixin2` | `PacketRecorder`, `ClientboundPacketEventFactory` |
| `LocationChangeEvent` | `client/guiRewindhandlers/GuiRewindhandlersHandler23` | `HypixelLocationListener` |
| `ServerPingEvent` | `GuiConnectingConnectMixin` | — |

## 5. Merge / dedupe recipe (if preferred over the variant names)

Unify on the canonical generation: delete
`src/main/java/com/moonsworth/lunar/client/highlight/mixin/gui/**` and repoint
the 74 files / 129 FQN references to
`com.moonsworth.lunar.client.event.mixin.gui.*`, together with the rescue
mixin copies that fire them. If instead this generation is kept, the inverse map
is `new → dup of` from §2 (e.g. `SlotUpdateEvent → EventSlotUpdate`), and the
remaining unnamed classes are:

| class | recommendation |
|---|---|
| `client.event.mixin.gui.HighlightIterator` (unnamed twin) | `EventSkinLoaded` — fired at TAIL of `NetworkPlayerInfo$1.skinAvailable` (`NetworkPlayerInfoSkinMixin`) and `AbstractClientPlayer.onSkinAvailable`, carries the loaded skin `ResourceLocation`, `method1()` resolves the player entity with that skin; consumers `SkyblockMetalDetector`/`SkyblockMetalDetectorDataGen` match `getSkinLocation().bridge$getPath()` against metal-detector treasure skins |
| `Gui` twin content | `EventBridgePayload` (applied `moves-namesD` row 32) is low-confidence: no firer, no consumer, 0 refs on both sides |

## 6. Validation / evidence sources

* Dry run: 30 rows, 4 nested, **0 SKIP / 0 WARN**, 100 files touched,
  26 file renames. New names: no declaration and no occurrence tree-wide
  (`grep -rE "\b<Name>\b"` = 0 for all; `GuiScreenEvent` only occurs in
  `net.minecraftforge.client.event.GuiScreenEvent` imports, which the applier
  does not touch — the row's 1 hit is its own declaration).
* Structural pair check: fields/methods/strings equal to the twin (all 28
  pairs, §3).
* Canonical names + roles: `tools/renames/classes-highlightmixgui.tsv` (26 rows,
  applied `531c6f7d`), `moves-namesC.tsv` lines 6/15,
  `moves-namesD.tsv` row 32, `tools/renames/APPLIED.md` line 87.
* Obf provenance: `restructure/remaining-renames.tsv` (31 rows for
  `highlight/mixin/gui/`, all placeholders present).
* Firers/consumers: `NetHandlerPlayClientEventMixin`, `NetHandlerPlayClientMixin`,
  `NetworkManagerRewindMixin`, `ResourcePackRepositoryMixin2`,
  `GuiEditSignMixin2`, `ClientboundPacketEventFactory`, `PacketRecorder`,
  `HypixelLocationListener`, `ScoreboardListener`, `TabListListener`,
  `TitleApolloHandler`, `SkyblockTpMaze`, `RewindRecorder`.
* Not present: no `net.minecraft.*` rows, no shaded third-party code, no
  missing paths.
