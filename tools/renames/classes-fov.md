# Cluster 23 — `fov.mixin` + `armorstatus.nameplate` (and neighbours), 44 classes

Source revision: `tools/renames/cluster-23.txt` md5 `a653339522b16b398ee6a73b297facfe`
(44 rows, all paths present).
Map: `tools/renames/classes-fov.tsv` (md5 `5c3916ba8a05fe3dd38e199fc636ada5`).

## Accuracy fix: these are **not** FOV mixins

The brief guessed `fov.mixin` was an FOV/zoom/dynamic-FOV mixin package.
It is not:

* **No `@Mixin` anywhere.** None of the 44 classes carries a `@Mixin`
  annotation and none appears in any `mixins.*.json` (checked all 21 configs).
  The `mixin` in the path is a leftover of the decompiler's subtree flattening
  (`fov/mixin/mixin/*` → `fov/mixin/*`, see `remaining-renames.tsv`).
* `fov.mixin` is the **cosmetics/emote/outfit support package**:
  `fov.mixin.FovType` = `HAT/CLOAK/BODYWEAR/WINGS/GECKOLIB`; the sibling
  `fov.FovHandler` is the emote handler (`emotes/icons/*.webp`,
  `mchorse.emoticons...Emote`); `fov.mixin.rewindhandlers.Rewindhandlers` is
  the `EquipCondition` outfit abstraction.
* `fov.mixin.Fov2`/`Fov4` are a **Verlet point + distance constraint** pair
  (position/previous/normal/locked, box clamp, `pos += (pos-prev)*factor`),
  used by the cosmetic model solver `Fov3` — not FOV camera code.
* `fov.mixin.FovType2` is a generic 2D direction enum (`LEFT/UP/RIGHT/DOWN`)
  consumed by `ScrollabletooltipsHandler2`, `AnimatedValue`, `SkyblockArrowAlign`.
* `fov.mixin.Gui2Handler2` is the JSON payload of an **emote gift**
  (uuid/username/message/anonymous), built in `EntityRenderer4` from
  `Emote.getGiftInfo()`.

So the `<Target><Purpose>Mixin` rule does not apply; the classes are named by
role like the other accuracy-fixed clusters. The rest of `cluster-23.txt` is a
grab-bag of neighbouring lazy classes which are handled here too.

## What the packages actually are

| package | what it is |
|---|---|
| `fov.mixin` | cosmetics/emote support: physics points, 2D direction enum, emote gift info |
| `armorstatus.nameplate` | the ArmorStatus HUD: per-slot element builder, durability/count renderer, panel renderer, HUD component |
| `chat` | emoji transformer, message stacker, chat filter, hover image preview (`Chat` itself is player-name lookup, not in cluster) |
| `debug.shaderdebugmod` | shader-cloak exporter, directory watcher, framebuffer preview, JS bridge |
| `gui` | connected-texture animation cache (`Gui$Data4`), HUD colour override, glass/ore texture processors |
| `itemcounter` | item-counter entries (base + potion variants) and the label-side enum |
| `mod.fishing.highlight` | the Highlight **inventory-button** feature: config migrations, migration context, serializer, button model |
| `mod.fishing.holograms.nameplate` | generic render helpers (line batch, text), animated world position, route pickaxe tier |
| `...dungeon.dungeonwaypoints` | Dungeon Waypoints manager / raycast target / share-code codec + decoder |
| `rewind.mixin.nameplate` | a ZIP reader (`Nameplate`); the four nested rows are its ZIP64 records |
| `guiRewindhandlers.rewindhandlers` | Hypixel party tracker, TPS tracker, Hypixel location model, party state |

Readable ground truth used: `fov/FovHandler.java`, `fov/Fov2_4.java`,
`fov/mixin/rewindhandlers/Rewindhandlers.java`, `armorstatus/nameplate/*`,
`markers/mixin/gui/Gui2.java`, `mod/gui/Bridge7Iterator2.java`,
`mod/gui/Bridge7Iterator222.java` (`"inventoryButton-"`),
`Holograms6_2.getPickaxeTier()`, `Rewindhandlers2.toString()` ("HypixelLocation"),
and the runtime/real-name tables (`ModArmorstatus`, `ModItemCounter`, `ModChat`,
`ModWaypoints` in `lunar-client-names.tsv`).

## Renames (44 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Fov2` | `PhysicsPoint` | Verlet point: pos/prev/normal + locked, box clamp, normal from two points |
| 2 | `Fov4` | `DistanceConstraint` | soft distance constraint over two PhysicsPoints |
| 3 | `FovType2` | `Direction2D` | LEFT/UP/RIGHT/DOWN + getAdjacent/getOpposite; scroll/arrow direction |
| 4 | `Gui2Handler2` | `EmoteGiftInfo` | Gui2 JSON provider: gift uuid/username/message/anonymous |
| 5 | `Nameplate2` | `ArmorStatusElementProvider` | builds Map<ArmorstatusType,Nameplate> from armor/held stacks |
| 6 | `Nameplate3` | `ArmorStatusDurabilityRenderer` | durability bar + stack count for one slot |
| 7 | `Nameplate4` | `ArmorStatusPanelRenderer` | hotbar-style panel layout/background + item grid |
| 8 | `Nameplate5` | `ArmorStatusHud` | MixinCore9 HUD component for ArmorStatus |
| 9 | `Chat2` | `ChatEmojiTransformer` | shortcodes.json `:emoji:` ↔ unicode + emoji font extraction |
| 10 | `Chat3` | `ChatMessageStacker` | dedups chat and appends ` [xN]` |
| 11 | `Chat4` | `ChatFilter` | regex block/obfuscate + own-name decoration |
| 12 | `Chat5` | `ChatImagePreview` | hover link → downloaded image preview |
| 13 | `Shaderdebugmod2` | `ShaderCloakExporter` | exports shader cloak zips (export_/dev_) |
| 14 | `Shaderdebugmod3` | `ShaderDirectoryWatcher` | WatchService ENTRY_MODIFY thread |
| 15 | `Shaderdebugmod4` | `ShaderPreviewRenderer` | 704x544 shader preview framebuffer |
| 16 | `Shaderdebugmod5` | `ShaderDebugJsBridge` | `@CallbackJS` bridge to the web shader editor |
| 17 | `Data4` (nested `Gui$Data4`) | `AnimationCacheEntry` | original-UV + generation cache for CTM animation |
| 18 | `Gui2` | `HudColorOverride` | static HUD colour/text-colour override state |
| 19 | `GuiUpdater2` | `GlassTextureProcessor` | glass/stained-glass texture processor |
| 20 | `GuiUpdater3` | `OreTextureProcessor` | ore texture processor (Perlin noise) |
| 21 | `Itemcounter2` | `PotionItemCounterEntry` | potion entry matched by damage |
| 22 | `Itemcounter3` | `PotionVariantItemCounterEntry` | potion entry by name (splash/lingering/long/strong) |
| 23 | `Itemcounter_2` | `ItemCounterEntry` | base item-counter entry |
| 24 | `Type3` (nested `Itemcounter$Type3`) | `ItemCounterTextSide` | NONE/TOP/BOTTOM/LEFT/RIGHT label side |
| 25 | `Highlight2` | `HighlightConfigMigrations` | @VersionGate(33) migration-handler registry |
| 26 | `Highlight3` | `HighlightMigrationContext` | json + screen w/h passed to migrations |
| 27 | `Highlight4` | `HighlightSerializer` | HighlightButton ↔ JSON |
| 28 | `Highlight5` | `HighlightButton` | the highlight/inventory button model |
| 29 | `Nameplate2` | `LineBatchRenderer` | batched line/box renderer |
| 30 | `Nameplate3` | `TextRenderHelper` | string/Component list text renderers |
| 31 | `Nameplate4` | `WorldPosition` | animated block position + map progress |
| 32 | `NameplateType2` | `PickaxeTier` | NONE/DUNGEONBREAKER route requirement |
| 33 | `Dungeonwaypoints3` | `DungeonWaypointManager` | rooms/boss/presets repository |
| 34 | `Dungeonwaypoints4` | `DungeonWaypointRaycast` | aim/raycast target tracker |
| 35 | `Dungeonwaypoints5` | `DungeonWaypointCodec` | `lcdwp1:` share code + zip import/export |
| 36 | `Dungeonwaypoints_2` | `DungeonWaypointDecoder` | `decode(String) -> Data` |
| 37 | `Data2` (nested `Nameplate$Data2`) | `Zip64EndOfCentralDirectory` | ZIP64 EOCD record |
| 38 | `Data3` (nested `Nameplate$Data3`) | `ZipLocalFileHeader` | local file header |
| 39 | `Data4` (nested `Nameplate$Data4`) | `ZipEntryLocation` | located stored entry |
| 40 | `Data5` (nested `Nameplate$Data5`) | `Zip64ExtraField` | ZIP64 extra-field values |
| 41 | `GuiRewindhandlersHandler2` | `HypixelPartyTracker` | parses Hypixel party chat into PartyState |
| 42 | `GuiRewindhandlersHandler22` | `TpsTracker` | rolling TPS estimate |
| 43 | `Rewindhandlers2` | `HypixelLocation` | @SerializedName server/gametype/mode/map/lobbyname |
| 44 | `Rewindhandlers3` | `PartyState` | party members + leader |

## Applier dry run, and the collision rows

`python3 tools/apply_class_renames.py --map tools/renames/classes-fov.tsv`:

```
applied=16 skipped=28 files_touched=43 files_renamed=15 mode=dry-run
```

Applied (16): `Chat2..5`, `Shaderdebugmod2..5`, `GuiUpdater2/3`,
`Itemcounter_2`, `Itemcounter$Type3`, `Highlight4`, `Highlight5`,
`NameplateType2`, `Dungeonwaypoints_2`.

The 28 skips are **old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Fov2`, `Fov4`, `FovType2` | `client.fov` (the real FOV mod) |
| `Gui2Handler2` | `client` |
| `Nameplate2..5` | 3–13 packages (`fishing.holograms.nameplate`, `markers.nameplate`, `lighting.nameplate`, …) |
| `Data4` | `mod.holograms.rewindhandlers`, `rewind.rewindhandlers.coordinates` |
| `Gui2` | 9 packages (`fog.gui`, `markers.mixin.gui`, …) |
| `Itemcounter2`, `Itemcounter3` | `bridge.itemcounter.mixin` |
| `Highlight2`, `Highlight3` | `rewind.highlight`, `inactive.mixin.highlight` |
| `Dungeonwaypoints3/4/5` | `...dungeonwaypoints.mixin` |
| `Data2`, `Data3` | 7 / 6 packages |
| `GuiRewindhandlersHandler2/22`, `Rewindhandlers2/3` | 4–7 packages each |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
simple name in unrelated `client.fov`, `markers.nameplate`, `fog.gui`,
`bridge.itemcounter`, `rewind.highlight` classes and merge distinct types.
These rows need the planned import/package-aware rewriter (same follow-up as
`classes-07.md`, `classes-12.md`, `classes-15.md`).

## Caveats / follow-ups

* **Nested rows 17, 24, 37–40** are real nested types (`Gui$Data4`,
  `Itemcounter$Type3`, `Nameplate$Data2..5`). The simple applier matches
  declarations by simple name, and its `DECL_RE` does not even see
  `private`/`protected` nested classes, so they are skipped by design. They
  need a nested-aware pass; the 5th `file` column scopes each row.
* `Nameplate$Data5` was additionally reported as "not declared" because the
  owner declares its nested classes `private`; the nested pass must use the
  `file` column rather than the declaration scan.
* New names were checked with the applier's own declaration scan and a manual
  `grep -rE "\b(class|interface|enum|record) NewName\b"` over `src/main/java`
  and `tools/work/quarantine/src`; none collides.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-fov.tsv
# → applied=16 skipped=28 files_touched=43 files_renamed=15 mode=dry-run
```