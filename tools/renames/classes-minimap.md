# Cluster 15 — `framework.feature.minimap.mixin` + neighbours (45 rows)

Source revision: `tools/renames/cluster-15.txt` md5 `4130f2fdd742a071c6ed0dd1204d2f13`
(45 rows: 35 top-level types + 10 nested `DataN`/`Type4`).
Map: `tools/renames/classes-minimap.tsv` md5 `6f8e005b886c3734d5655aa3c306a19b`.

The inventory generator lumped the minimap engine together with a long tail of
unrelated packages that happen to sit next to it in the tree. The package names
are decompiler artefacts throughout (see `classes-12.md`): the `mixin` suffix
does **not** mean the class is a mixin, and `fishing`/`holograms`/`nameplate`/
`itemcounter` are reused placeholder names, not the features they sound like.
Every renamed class was read in full; no source was edited.

## What each package actually is

| package | really is |
|---|---|
| `…feature.minimap.mixin` | the **Lunar minimap engine**'s data helpers (chunk/region key packing, per-column surface sample, per-chunk colour map). Siblings `Minimap`/`Minimap2`/`Minimap5`/`MinimapImpl*` are the manager, the 512×512 region-PNG store, the render task and the marker/entity/waypoint layers. Ground truth: `mod/render/Minimap` option keys (`mapShape`, `mapZoom`, `mapWidth`, `rotateWithPlayer`, `showCoordinates`, `entityMarkerType`, `waypointsOptions`, …), executor `lunar-map-manager-thread` / `lunar-map-manager-texture-save-thread`, texture `minimap-mod-texture`. |
| `…feature.mod.gui.nameplate` | the **nameplate HUD components** (`MixinCore9` subclasses): a player-name/text component and a stat-line+bar component. |
| `…feature.rewind.fishing` | **Lunar Rewind** (the ReplayMod integration) value helpers: the range-slice interface, a number lerp, and the settings-path key constants. |
| `…feature.rewind.holograms` | Rewind **type registries** (entity-override loaders, segment classes, timeline track classes) and the big settings registry `Holograms` (out of cluster). |
| `…feature.rewind.nameplate` | the Rewind **replay packet base**, a lazy settings category, and a settings leaf option. |
| `…feature.saturation` | the **saturation outline** renderer drawn over the hunger bar (`saturation_outline*` textures). |
| `…feature.tiertagger.mixin` | the **TierTagger** option types (tier-source selection + fallback mode). |
| `…feature.waypoints` | the **waypoint model** and importers (Badlion/JourneyMap). |
| `…guiRewindhandlers.nameplate` | the event-bus **marker interfaces** (`DynamicListenerEvent`, `ThreadedEvent`) and a subscription record. |
| `…highlight.mixin.nameplate` | three **event classes** of the HUD/marker render system (the base HUD render event, its focused pass, and the tab-list render event). |
| `…inactive.mixin.highlight` | the **Molang query context** used by the GeckoLib animation system. |
| `…itemcounter` | the **malicious server/URL safety checker** (misnamed: it fetches `/game/safety`, not an item counter). |
| `…markers.mixin.gui.mixin.nameplate` | the **friends/conversation JS bridge** (conversation image uploader, friends list, friends API). |
| `…markers.mixin.gui.mixin.rewindhandlers` | the **minimap/waypoint JS bridges**. |
| `…markers.mixin.holograms` | the **marker hologram renderer** and its JSON models. |

## Renames (45 rows)

| # | package | old | new | evidence (short) |
|---|---------|-----|-----|------------------|
| 1 | minimap.mixin | `Minimap2` | `MapCoord` | chunk/region key packing: `method2(x,z)` packs two ints, `method3` >>4 chunk, `method4` >>5 region, `method5/6` unpack |
| 2 | minimap.mixin | `Minimap3` | `TerrainColumn` | per-column sample: height / fluid depth / fluid block, returned by `ChunkColorMap.method2` |
| 3 | minimap.mixin | `Minimap4` | `ChunkColorMap` | 16×16 chunk colour map (`int[256]` + `built`); builds from `Itemcounter2`, samples a `TerrainColumn` |
| 4 | mod.gui.nameplate | `Data2` | `StatBarOptions` | `Nameplate3$Data2` option holder: showValue/showMax/showSuffix/showBar/barBackground/alignment/combine |
| 5 | mod.gui.nameplate | `Nameplate2` | `NameplateComponent` | HUD component drawing a player name or `Supplier<List<TextComponent>>`; ALWAYS/RECENT/NEVER |
| 6 | mod.gui.nameplate | `Nameplate3` | `StatBarComponent` | HUD component drawing stat lines + a 100×8 progress bar |
| 7 | rewind.fishing | `Fishing2` | `Sliceable` | functional `T method1(Nameplate2, Range<K>, Range<K>)`; bound `V extends Sliceable<Integer,V> & Fishing<UUID>` |
| 8 | rewind.fishing | `Fishing3` | `NumberInterpolator` | static lerp for Double/Float/Integer/Long via `Easing` |
| 9 | rewind.fishing | `Fishing4` | `RewindSettingKeys` | settings path constants (gameplay/audio/effect/camera/entityOverrides/…) + `/` `#` join helpers |
| 10 | rewind.holograms | `Holograms2` | `EntityOverrideRegistry` | hide/name/skin → `Supplier<Fishing2Loader>` + allowed entity types |
| 11 | rewind.holograms | `Holograms3` | `SegmentRegistry` | type-string → `RewindIterator` class; used by the `Highlight` layer deserializer |
| 12 | rewind.holograms | `Holograms4` | `TrackRegistry` | type-string → `Track` class; used by the `Gui2$Data` TrackCollection deserializer |
| 13 | rewind.nameplate | `Nameplate2` | `ReplayPacket` | abstract packet: `method1/2(ByteBufLoader)` read/write, `method3` handler, `name()/data()` |
| 14 | rewind.nameplate | `Nameplate3` | `DynamicCategory` | `Fishing2Iterator3` whose children are computed lazily from `Function<RewindHandlers,List<SettingOption>>` |
| 15 | rewind.nameplate | `Nameplate_2` | `SettingOption` | (type, display name, optional UUID) leaf option (e.g. `player#<uuid>`) |
| 16 | saturation | `Data2` | `OutlineTexture` | `Saturation2$Data2`: cached outline texture + resolution + static flag |
| 17 | saturation | `Data3` | `OutlinePoint` | `Saturation2$Data3`: (x,y) pixel of an outline edge |
| 18 | saturation | `Saturation2` | `SaturationRenderer` | builds/draws the saturation outline (`saturation_outline`/`_hunger`) |
| 19 | tiertagger.mixin | `Gui2Extension2` | `TierFallbackMode` | enum SELECTED_ONLY/HIGHEST_FALLBACK/HIGHEST_ALWAYS |
| 20 | tiertagger.mixin | `LightingExtension4952` | `TierSourceOption` | selects a `Tiertagger_2` provider (apiName/niceName) |
| 21 | tiertagger.mixin | `LightingExtension49522` | `TierSourceModeOption` | tier-source option with `modeBySource`/`secondaryModeBySource` maps |
| 22 | waypoints | `Gui2Loader_2` | `JourneyMapWaypointImporter` | imports `journeymap/data/**/waypoints/*.json` |
| 23 | waypoints | `GuiHandler2` | `Waypoint` | waypoint model + builder (name/pos/world/dimension/colour/enabled) |
| 24 | waypoints | `Waypoints2` | `WaypointStringPool` | decodes the compressed BLCWP string table and slices substrings |
| 25 | guiRewindhandlers.nameplate | `Data9` | `EventSubscription` | `Nameplate$Data9`: (Consumer, priority, event class) registered on `LunarEventBus` |
| 26 | guiRewindhandlers.nameplate | `Nameplate2` | `DynamicListenerEvent` | marker for `@TriggeredBy` events (bus error text "does not extend DynamicListenerEvent") |
| 27 | guiRewindhandlers.nameplate | `Nameplate3` | `ThreadedEvent` | marker for off-thread events (bus error text "make it implement ThreadedEvent") |
| 28 | highlight.mixin.nameplate | `Data4` | `EventRenderHudFocused` | `HighlightImpl$Data4`: focused/selected HUD render pass (`MixinCore9` instanceof; `ChatImagePreview`) |
| 29 | highlight.mixin.nameplate | `HighlightImpl3` | `EventRenderTabList` | cancellable event before `GuiPlayerTabOverlay.renderPlayerlist` (`GuiIngameForgeMixin`) |
| 30 | highlight.mixin.nameplate | `HighlightImpl5` | `EventRenderHud` | HUD render event (entity+pose+marker) consumed by `MixinCore9.method20` |
| 31 | inactive.mixin.highlight | `Data5` | `BoneQuery` | `Highlight$Data5`: per-`IBone` position/rotation/scale query handles |
| 32 | inactive.mixin.highlight | `Highlight2` | `QueryDefaults` | static default `query.*` value map |
| 33 | inactive.mixin.highlight | `Highlight3` | `QueryTransition` | eased boolean query transition over a time window |
| 34 | itemcounter | `Data2` | `MaliciousServer` | `Itemcounter$Data2`: `maliciousServers` entry (pattern + `SafetyAction`) |
| 35 | itemcounter | `Data3` | `MaliciousUrl` | `Itemcounter$Data3`: `maliciousUrls` entry (compiled `Pattern` + `SafetyAction`) |
| 36 | itemcounter | `Type4` | `SafetyAction` | `Itemcounter$Type4` enum PROMPT/BLOCK |
| 37 | markers…nameplate | `Data7` | `PendingImage` | `Nameplate$Data7`: pending conversation icon/chat image (bytes + contentType) |
| 38 | markers…nameplate | `Nameplate3` | `FriendsGuiExtension` | `GuiExtension_2` returning the friends-list `GuiIterator` |
| 39 | markers…nameplate | `Nameplate4` | `FriendApi` | `@CallbackJS` friends API (add/remove/accept/deny/visibility/join/pin) |
| 40 | markers…rewindhandlers | `GuiExtension3` | `MinimapJsApi` | `@CallbackJS` minimap API (setMapDimensions/mapDrag/mapZoomBy/showMap) |
| 41 | markers…rewindhandlers | `GuiExtension5` | `WaypointJsApi` | `@CallbackJS` requestColor/showAddWaypoint |
| 42 | markers…rewindhandlers | `GuiExtension6` | `WaypointImportJsApi` | `@CallbackJS` searchForWaypoints/importWaypoints |
| 43 | markers.mixin.holograms | `Holograms3` | `HologramElement` | JSON model: position (`HologramBounds`), id, scrollAmount |
| 44 | markers.mixin.holograms | `Holograms4` | `HologramRenderer` | renders/queues marker holograms (`processQueue`, `HologramsIterator2`) |
| 45 | markers.mixin.holograms | `Holograms5` | `HologramBounds` | `@SerializedName` x/y/width/height rect + transient float; hit test vs `Markers.Data5` |

Nested rows (4, 16, 17, 25, 28, 31, 34, 35, 36, 37) carry the owner as
`Owner$Old` in the evidence plus the 5th `file` column, so the aware applier
renames them inside the owner file instead of mass-renaming `DataN`/`Type4`.

## Applier dry run

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-minimap.tsv
# → rows=45 skipped=0 files_touched=179 files_renamed=35 mode=dry-run
```

The v1 `apply_class_renames.py` cannot apply this map: most old simple names
(`Minimap2/3/4`, `Nameplate2/3`, `Holograms2/3/4`, `Fishing2/3/4`,
`Highlight2/3`, `Data2/3`, `Type4`, …) are declared in many packages and would
need `--allow-collisions`, which is **unsafe**. Use the aware applier.

## Evidence / provenance used

* Source of every class in the cluster (read in full), plus the sibling
  `Minimap`/`Minimap2`/`Minimap5`/`Minimap_2`/`Minimap2_2`/`MinimapImpl*`
  minimap classes and `mod/render/Minimap` option keys.
* `tools/work/mappings/normalize-renames.tsv` — original jar subtrees
  (`…/minimap/mixinCore/*`, `…/rewind/mixinRewindhandlers/*`,
  `…/markers/mixin/gui/mixin/mixinExtra/*`, …).
* `tools/mappings-snapshot/restructure/remaining-renames.tsv` lines 3660-3666 —
  obf origin of the `highlight/mixin/nameplate` events.
* `tools/work/quarantine/src/…/framework/MixinCore9.java` and
  `…/v1_8/mixin/GuiIngameForgeMixin.java` — the HUD render dispatch and the
  tab-list event firing, which name rows 28-30.
* `tools/renames/classes-rewindgui.md` — the Rewind timeline model (`Track`,
  `TrackCollection`, `Layer`, `Timeline`) and recorder naming, reused here for
  the Rewind type registries and packet base.
* `tools/mappings-snapshot/restructure/mixin-renames.tsv` — confirms none of
  these classes is a real mixin.

## Caveats / follow-ups

* **Not mixins.** Despite `mixin` in the package path, none of the renamed
  classes carries `@Mixin`/`@Inject`; they are plain data/registry/event types,
  so they are named by role, not `<Target><Purpose>Mixin`.
* **Out-of-cluster siblings** that belong to the same families and should be
  named by whoever owns them: the minimap engine (`Minimap` manager,
  `Minimap2` region-PNG store, `Minimap5` render task, `Minimap_2`/`Minimap2_2`
  layer/element bases, `MinimapImpl*`/`Minimap2Impl*`), the Rewind root
  (`Fishing`, `Fishing2Loader*`, `Rewind*`, `RewindIterator*`), the `Holograms`
  settings registry, the `markers` `GuiExtension*`/`Gui*` JS bridges, and the
  missing `highlight/mixin/nameplate/HighlightImpl2/4` (referenced by many mods).
* **Missing base types.** `lighting.LightingExtension4952`/`LightingExtension495`
  (base of rows 20-21) and `framework.MixinCore9` live in
  `tools/work/quarantine/src`; the tiertagger option classes therefore cannot
  compile in the partial tree today, but the rename is still correct.
* Names were checked against the applier's declaration index and are declared
  nowhere else in `src/main/java` (all 45 new names FREE); none appears in
  another `tools/renames/classes-*.tsv` map.