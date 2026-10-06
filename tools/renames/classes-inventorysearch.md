# Cluster 19 — `inventorymod.slot.inventorysearch` + 44 neighbours (45 classes)

Source revision: `tools/renames/cluster-19.txt` md5 `f9beb449d97dc793ce66fe0e027f5f61`
(45 rows: 40 top-level types + 5 nested `Owner$Data2`).
Map: `tools/renames/classes-inventorysearch.tsv` (md5 `f553a9ba70b8b95df4ffdd6d53d74d06`).

> Unlike the package-tree clusters, this worksheet is a **cross-tree bucket**:
> only the first row is the lead package
> (`framework.feature.inventorymod.slot.inventorysearch`), the other 44 rows are
> unrelated lazy classes scattered over `framework.feature.*`, `glintcolorizer`,
> `heightlimit`, `highlight`, `hitbox`, `inactive`, `lighting`, `markers` and
> `mod.combat`. Every row is renamed on its own evidence.

## What the rows actually are

* **Inventory search** (`inventorysearch.ModuleBase2`) — the search text-input
  overlay used by `mod/player/InventorySearch` (`"InvSearchOverlay"`).
* **Keystrokes** — the built-in `KeystrokeKey` for the game-bound keys
  (W/A/S/D/space/LMB/RMB), the counterpart of `mod/hud/CustomKeystrokeKey`.
* **LightOverlay** — the box-outline renderer (the sibling `LightoverlayHandler`
  draws the X cross).
* **Fishing** — the fishing/sea-creature registry (`sea-creatures.json`), the
  Kuudra waypoint model (`kuudra-waypoints.json`), the skill-XP source enum and
  the inventory-button shape enum.
* **Rewind editor (ReplayMod-style)** — the four `@CallbackJS` bridges that the
  web editor registers (`"rewind"`, `"rewindTimeline"`, `"rewindTimelinesList"`,
  `"rewindProperties"`, see `markers/Markers4`), the camera-mode/video-orientation
  enums and the PBO frame-capture pool.
* **GeckoLib/Bedrock stack** (`inactive.mixin.*`) — the animation-JSON parser,
  the animatable-model interpolation state and the bone-hierarchy builder.
* **Misc mods** — scrollable tooltips controller, sound-changer entry, team-view
  matcher, title renderer, bedwars level formats, WAILA layout, ColeWeight
  waypoint importer, crosshair option, marker icon renderer, `PositionQuad`,
  `ConfigureWaypointProps`, knockback graph HUD, threaded-present flag.

### Accuracy fixes found while reading

* `framework.feature.mod.impl.calculator` is **not a calculator**: it is the
  SkyBlock **raffle-task** system (`CalculatorType2` = task difficulty,
  `Calculator` = a raffle task) plus an unrelated abstract block-highlight base
  (`Calculator2`). Named accordingly.
* `highlight.mixin.fishing.HighlightImpl12` is **`EventPickBlock`**, not
  `EventEntityRemove`. It is fired from
  `legacy/mixin/MinecraftMixin2.lunar$pickBlockEvent`
  (`@Inject(method = "middleClickMouse", at = @At("HEAD"))`) and recorded by
  `RewindHandlers5.method18` → `PlayerStateRecorder.method18`, which writes
  `holograms/Nameplate2Impl6` (`Ref.bridge$pickBlock()`). The earlier
  `classes-highlightmixinfishing.tsv` row (`HighlightImpl12 → EventEntityRemove`)
  was wrong and was skipped by the applier anyway (`EventEntityRemove` is already
  declared in `highlight.fishing`); this map gives it the correct unique name.
* `markers.mixin.Markers4` is a **marker icon renderer**, not the JS bridge
  registry (that is the top-level `markers/Markers4`).
* `hitbox` is a misnomer: `Hitbox2` is a **PvP-info HUD row element** interface
  (text/spacer/group/item-icon), laid out by `Hitbox`.

## Renames (45 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `ModuleBase2` (inventorysearch) | `InventorySearchOverlay` | OverlayModule used by `InventorySearch`: renders `"InvSearchOverlay"` input + calculator result, open/close/isOpen |
| 2 | `Framework7Extension2` (keystrokes.mixin) | `DefaultKeystrokeKey` | built-in game-key `KeystrokeKey` (W/A/S/D/space/LMB/RMB), `getId` = `"KEYSTROKE_KEY_"+name`; counterpart of `CustomKeystrokeKey` |
| 3 | `LightoverlayHandler3` | `LightOverlayBoxRenderer` | implements `lightoverlay.Lightoverlay`; draws the 4-line box outline; sibling draws the X cross |
| 4 | `ModuleBase2` (testcustomguioverlay) | `TestOverlayModule` | debug `"testoverlay"`: text input + `"Text:"` button, used by `TestCustomGuiOverlay` |
| 5 | `CoordinatesType2` | `SkillXpSource` | enum ACTION_BAR/TAB_LIST/SKILLS_MENU; source field of `SkillXpUpdateEvent` |
| 6 | `Gui2Extension2` (fishing.highlight.mixin) | `HighlightButtonShape` | SQUARE/ROUNDED_SQUARE/CIRCLE + `skyblock/inventorybuttons/*.png` textures |
| 7 | `Holograms2` (fishing.holograms.mixin) | `KuudraWaypoints` | `kuudra-waypoints.json`: pearl map + stun list; used by `SkyblockKuudraWaypoints` |
| 8 | `Rewindhandlers2` (fishing.rewindhandlers) | `SeaCreatureRegistry` | `fishing/sea-creatures.json`: id → `Rewindhandlers` (name/spawnMessage/rarity/requirements) |
| 9 | `Data2` (nested `Gui`) | `SettingsLabel` | nested `Gui$Data2`: `HudComponent` label wrapping a `MixinCore5Handler3`; width = widest label + 8 |
| 10 | `GuiRewindhandlersHandler2` (holograms.gui) | `WormScathaSpawnListener` | scans entities for `[Lv5] Worm`/`[Lv10] Scatha`, publishes `WormScathaSpawnEvent` |
| 11 | `GuiRewindhandlersHandler2` (holograms.nameplate) | `CropTracker` | tracks the current crop `HighlightType3` from block place/break; read by farming HUDs |
| 12 | `Excavation2` | `ExcavationGrid` | 9×6 grid of `ExcavationType3` + candidate `Excavation` placements (`SkyblockFossilExcavationSolver`) |
| 13 | `Calculator2` (impl.calculator) | `TrackedBlockHighlight` | abstract base for particle-detected block highlights (`SkyblockEndNodeHighlight`, `SkyblockGlowingMushroomHighlight`) |
| 14 | `CalculatorType2` (calculator.mixin) | `RaffleTaskDifficulty` | EASY/MEDIUM/HARD + `TASKS_PER_TIER=7`, parsed from `"Easy Task"` lore |
| 15 | `Framework7Extension2` (chest.dungeon.practice) | `TerminalSolverModule` | abstract base for the terminal solvers (ArrowAlign/ClickInOrder/FirstLetter/MatchColors/SelectColor/SimonSays) |
| 16 | `Gui2Extension2` (storageoverlay.mixin) | `ChestsPerRow` | enum COUNT_2..COUNT_5, id `"chestsPerRowN"` |
| 17 | `Framework7Extension2` (ultrasequencer) | `ExperimentSolverModule` | abstract base for Chronomatron/Superpairs/Ultrasequencer |
| 18 | `Data2` (nested `Modern`) | `ItemTransform` | nested `Modern$Data2`: position/rotation/scale glint transform for `EventRenderGlintTransform` |
| 19 | `Rewind3` (rewind.mixin) | `RewindFileReader` | reads rewind `.zip` (`packets.dat`), ZSTD/LZ4/ZLIB decompression, yields `Nameplate2` |
| 20 | `Chest2` | `RewindTimelineBridge` | `@CallbackJS "rewindTimeline"`: cut/addTrack/addLayer/selectLayer(s)/moveLayer(s)/trimLayer/keyframes |
| 21 | `Click2` | `RewindTimelinesListBridge` | `@CallbackJS "rewindTimelinesList"`: select/edit/duplicate/delete/new timeline |
| 22 | `Colorsaturation2` (rewindhandlers.colorsaturation) | `RewindPropertiesBridge` | `@CallbackJS "rewindProperties"`: add/remove/set property, keyframe navigation |
| 23 | `Gui2Extension2` (rewindhandlers.fishing) | `VideoOrientation` | HORIZONTAL/VERTICAL; the `"videoOrientation"` export option |
| 24 | `Holograms2` (rewindhandlers.holograms) | `RewindEditorBridge` | `@CallbackJS "rewind"`: preview/exit/pause/save/undo/redo/forward/backward/playhead/zoom |
| 25 | `Gui2Extension2` (rewindhandlersCore.mixin) | `CameraMode` | POV/FREE_CAMERA/FOLLOW/FORCE_FIRST/THIRD_PERSON_* with view helpers |
| 26 | `RewindhandlersNameplateImpl2` | `PersistentPixelBufferPool` | PBO + persistent mapped buffers / GL fence sync frame capture; chosen when `Ref.method43()` |
| 27 | `Scrollabletooltips2` | `ScrollableTooltipsController` | core controller: `tooltipFreeScroll`/`lineShiftMode`, `ScrollableTooltips` state, 3 transformers |
| 28 | `Soundchanger2` | `SoundChangerEntry` | per-sound entry (location/volume/prettyName/path); map value in `SoundChanger` |
| 29 | `Data2` (nested `Teamview`) | `TeamViewMatcher` | nested `Teamview$Data2`: pattern list + regex flag for gametype/mode/map |
| 30 | `Titles2` | `TitleRenderer` | draws title/subtitle with fade/stay/fadeOut alpha + `Titles` colour providers |
| 31 | `Tps2` | `BedwarsLevelFormats` | `hypixel/bedwars_levels_format.json`: name/colors/symbol level tags (`HypixelMod`) |
| 32 | `Waila2` | `WailaLayout` | `List<Waila>` rows + width/height; `toString` = `"WAILALayout"` |
| 33 | `Gui2Loader2` (waypoints.mixin) | `ColeWeightWaypointImporter` | `Gui2Loader` subclass importing ColeWeight waypoints |
| 34 | `GlintcolorizerExtension3` (nameplate.mixin) | `NameplateGlintRenderer` | extends the base glint renderer with nameplate render methods |
| 35 | `Heightlimit2` | `ThreadedPresentSupport` | singleton `"Threaded Present Supported %b"` flag |
| 36 | `HighlightImpl12` (highlight.mixin.fishing) | `EventPickBlock` | fired at `middleClickMouse` HEAD; recorded as `Nameplate2Impl6` (`bridge$pickBlock`) |
| 37 | `Hitbox2` | `HudRow` | HUD row element (render/width/height) implemented by `Hitbox2Handler*`; laid out by `Hitbox` |
| 38 | `Colorsaturation2` (inactive…colorsaturation.mixin) | `AnimationJsonParser` | parses Bedrock/GeckoLib animation JSON into `software.bernie` `Animation` |
| 39 | `Data2` (nested `Holograms2Iterator`) | `InterpolationState` | nested `Holograms2Iterator$Data2`: previous/current `BoneSnapshot` lists + evaluator |
| 40 | `Rewindhandlers2` (inactive…rewindhandlers.mixin) | `BoneHierarchyBuilder` | jgrapht graph + topological sort → `Map<String,Rewindhandlers>` bone tree |
| 41 | `LightingExtension44` (lighting.mixin) | `CrosshairDrawOption` | `"crosshairDraw"` BOOL option with a 200 ms fade timer (`CrosshairChildMod`) |
| 42 | `Markers4` (markers.mixin) | `MarkerIconRenderer` | renders a marker icon texture with `RenderTypeBridge`, rate-throttled |
| 43 | `Nameplate2` (markers.nameplate) | `PositionQuad` | 4-float position quad; `toString` = `"PositionQuad"` |
| 44 | `MarkersImpl2` (markers.rewindhandlers) | `ConfigureWaypointProps` | waypoint (server/world/name + x/y/z) serializer with builder; `toString` = `"ConfigureWaypointProps.ConfigureWaypointPropsBuilder"` |
| 45 | `Data2` (nested `KnockbackGroundGraph`) | `KnockbackGraphHud` | nested `KnockbackGroundGraph$Data2` extends `MixinCore9`; draws the ground graph |

## Nested rows

Rows 9, 18, 29, 39 and 45 are **nested** types (`Owner$Data2`) that the
decompiler left inside their owner file. They are written with the owner in the
evidence (`Gui$Data2`, `Modern$Data2`, `Teamview$Data2`,
`Holograms2Iterator$Data2`, `KnockbackGroundGraph$Data2`) plus the 5th `file`
column, and are handled by `apply_class_renames_aware.py`'s nested pass (which
scopes the bare-name rename to the owner file and to files importing
`pkg.Owner.Data2`).

## Applier dry runs

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-inventorysearch.tsv
# → [aware-renames] 45 rows (5 nested); 6594 java files
#   rows=45 skipped=0 files_touched=135 files_renamed=40 mode=dry-run
```

The v1 applier cannot apply this map (26 rows are old-name collisions across
packages — `Framework7Extension2`, `Data2`, `Gui2Extension2`, `Holograms2`,
`Colorsaturation2`, `Nameplate2`, `HighlightImpl12`, `Heightlimit2`,
`GlintcolorizerExtension3`, `Rewindhandlers2`, ...):

```
python3 tools/apply_class_renames.py --map tools/renames/classes-inventorysearch.tsv
# → applied=19 skipped=26 files_touched=68 files_renamed=19 mode=dry-run
```

**Do not run this map with `--allow-collisions`.** Use the aware applier.

## Caveats / follow-ups

* **Out-of-cluster companions** (not listed in `cluster-19.txt`, so not in the
  map; whoever gets them should claim them): `fishing.rewindhandlers.Rewindhandlers`
  (the sea-creature entry → `SeaCreature`), `fishing.holograms.mixin.Holograms`
  and `mixin/JsonDeserializerIterator` (Kuudra pearl entry + its deserializer),
  `inactive.rewindhandlers`/`inactive.mixin.colorsaturation` siblings
  (`Colorsaturation` matrix stack, `Colorsaturation3` vector helpers,
  `mixin/Colorsaturation` keyframe builder), `markers.rewindhandlers.MarkersImpl2.Data`
  (`ConfigureWaypointPropsBuilder`), the `rewind.nameplate.mixin.*.Nameplate2Impl*`
  packet classes, and the base `rewindhandlersNameplate.mixin.RewindhandlersNameplate`
  (`PixelBufferPool`).
* **New names were checked tree-wide** for existing declarations; the only
  collision found was `SoundEntry` (Minecraft's `SoundList.SoundEntry`), so the
  sound entry was named `SoundChangerEntry` instead. Manual re-check:
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java`.
