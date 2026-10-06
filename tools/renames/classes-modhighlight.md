# Cluster 09 — `mod.highlight` + neighbours (42 classes)

Source revision: `tools/renames/cluster-09.txt` md5 `79c4e7c758f4d8aef537b5d4dffb1a10`
(42 rows). Map: `tools/renames/classes-modhighlight.tsv` md5
`c24964fb8048d01c5ddcb3e4f0eed9c0` (42 rows + header).

## What this cluster actually is

The cluster title says "highlight/outline mod internals", but the 42 rows are a
mixed bag of six unrelated subtrees that the restructure pass happened to group
together. Each subtree is named by its real role:

* **`framework.feature.mod.highlight`** — a SkyBlock **data-enum** package, not
  the event system. `HighlightType` (dungeon boss floors), `HighlightType2`
  (Kuudra tiers), `HighlightType3` (crops), `HighlightType4` (calendar seasons),
  `HighlightType5` (weather), `HighlightType6` (glowing mushroom cave) plus the
  island enum `Gui2Extension3` and the visibility enum `Gui2Extension2`.
  (`Gui2Extension` and `HighlightType` themselves are not in this cluster.)
* **`framework.feature.mod.holograms.mixin`** — the **Griffin-burrow locator**
  (Diana's mythological ritual) and the two Kuudra listeners. It finds burrows
  from particle trails and validates them against a HUB ground-block scan; the
  `Holograms2`/`Holograms3` names are decompiler noise.
* **`framework.feature.waila`** — the **WAILA HUD component** implementations
  (cosmetic preview, text, entity preview, spacer, item icon, texture icon,
  vertical group). The base interface is `Waila`; the real Lunar base is
  `client/feature/mod/waila/component/WailaComponent`.
* **`client.holograms`** — the **Emoticons/morph cosmetic renderer** stack
  (mchorse.emoticons `Morph`/`EmoteController`/`Emote`). The real package is
  `client/morph` (see `IBoneRenderer` below).
* **`client.horsestats`** — the **Badlion/Feather profile importer**
  (locate → read zip → convert config → write Lunar profile). `Horsestats`
  itself stringifies as `Profile(...)`; `FogIterator` is the profile manager.
* **`client.lightoverlay`** — a generic **trait system** (`TraitContainer`,
  `TraitType`, `TraitBuilder`, `[DEBUG_TRAIT]`). The package name is a
  decompiler scramble; the real concept is traits.

Readable ground truth used: `mod/misc/SkyblockBurrowLocating.java`,
`mod/render/SkyblockGlowingMushroomHighlight.java`,
`framework/feature/mod/impl/burrow/Burrow.java`,
`mod/hud/Waila.java`, `framework/feature/waila/mixin/{Waila,Waila2}.java`,
`mixin/EntityRenderer4.java`, `framework/feature/screenshot/Screenshot.java`,
`client/util/TraitBuilder.java`, and the quarantined trait interfaces
(`tools/work/quarantine/src/.../lightoverlay/Lightoverlay2Extension2.java`,
`Lightoverlay3.java`, `Lightoverlay6.java`).

### Real-name cross-check

`tools/work/lmp/sacred-mappings.tiny` (a different obfuscation scheme, so not
directly keyed to this jar) contains real Lunar names for the same features.
The one unambiguous match is the morph interface: our `client.holograms.Holograms2`
declares exactly `applyBoneTransform(Matrix4f, String)`, and the real
`com/moonsworth/lunar/client/morph/IBoneRenderer` declares exactly that method —
so it is renamed to the real `IBoneRenderer`. The real morph package also lists
`BlockMorph`, `SnowstormMorph`, `CosmeticMorph`, `CosmeticMorphManager`,
`EmoteEffect`, `MorphHelper`, `MorphType`; the remaining morph rows keep
descriptive role names because the scheme-B ↔ scheme-A crosswalk is not
available. The real profile package (`client/profile/Profile`,
`client/profile/ProfileManager`) and WAILA base (`WailaComponent`) confirm the
role names chosen here.

## Renames (42 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Gui2Extension2` | `SkyblockVisibility` | option ALWAYS/SKYBLOCK_ONLY/NEVER, `id()` humanises the name |
| 2 | `Gui2Extension3` | `SkyblockIsland` | island enum HUB/GOLD_MINE/…/KUUDRA; `getByMapValue`, `isMiningIsland`, `containsGemstones` |
| 3 | `HighlightType2` | `KuudraTier` | NONE/T1 Basic…T5 Infernal, parsed from `⏣ Kuudra's Hollow (T1..T5)` |
| 4 | `HighlightType3` | `CropType` | WHEAT…MOONFLOWER with `apiName`/`localName` + `isCrop` predicate |
| 5 | `HighlightType4` | `SkyblockSeason` | EARLY_SPRING…LATE_WINTER, used by `SkyblockCalendar` |
| 6 | `HighlightType5` | `SkyblockWeather` | DAY/NIGHT/RAIN/THUNDER icons, used by `SkyblockTimeListener` |
| 7 | `HighlightType6` | `SkyblockCave` | GLOWING_MUSHROOM_CAVE, `fromScoreboard` |
| 8 | `Data3` (nested) | `LocatedBurrow` | `Holograms2$Data3`: a Vector3ic burrow position found from a trail |
| 9 | `GuiRewindhandlersHandler2` | `KuudraTierListener` | fires `KuudraEnterEvent` from the Kuudra scoreboard |
| 10 | `GuiRewindhandlersHandler22` | `KuudraBossHealth` | tracks the Kuudra boss entity, `getHealth()`/max health |
| 11 | `GuiRewindhandlersHandler23` | `BurrowLocatingListener` | main Griffin-burrow listener, exposes state to `SkyblockBurrowLocating` |
| 12 | `GuiRewindhandlersHandler24` | `BurrowGroundScanner` | HUB chunk scan y70..100 for valid burrow ground blocks |
| 13 | `Holograms2` | `BurrowTrailLocator` | fits bezier particle trails, validates candidates via the scanner |
| 14 | `Holograms3` | `BurrowTracker` | `Map<Vector3i,Burrow>` of confirmed burrows + expiry |
| 15 | `WailaHandler2` | `CosmeticWailaComponent` | renders a cosmetic preview (CLOAK/SUITS/…/COMPANION) |
| 16 | `WailaHandler3` | `StringWailaComponent` | renders a plain String via `LightingExtension4222` |
| 17 | `WailaHandler4` | `EntityWailaComponent` | renders a full entity model preview |
| 18 | `WailaHandler5` | `SpacerWailaComponent` | fixed width/height, no render |
| 19 | `WailaHandler6` | `ItemWailaComponent` | 16×16 item icon with glint handling |
| 20 | `WailaHandler7` | `TextureWailaComponent` | square resource-location texture |
| 21 | `WailaIterator2` | `VerticalWailaGroup` | stacks children vertically (max width, summed height) |
| 22 | `Holograms2` | `IBoneRenderer` | interface `applyBoneTransform(Matrix4f,String)` = real `client/morph/IBoneRenderer` |
| 23 | `Holograms3` | `MorphTimeline` | plays one `MorphEntry`'s morphs over time |
| 24 | `Holograms4` | `MorphTracker` | per-player `Map<Gui2Handler,MorphTimeline>` from the cosmetic manager |
| 25 | `Holograms5` | `EmoteDefinition` | (id, display name, `Fov2_4` grant) looked up by emote id |
| 26 | `Holograms6` | `EmoteAnimator` | drives `EmoteController`'s emote: morph entry + particle effect |
| 27 | `Holograms7` | `MorphTransform` | `Morph` translate/rotation/scale → `Matrix4f` |
| 28 | `Holograms8` | `MorphRenderer` | live morph interface; factory builds Snowstorm/Block morphs |
| 29 | `Data2` (nested) | `ModConverter` | `Horsestats3$Data2`: Badlion mod options → Lunar mods.json |
| 30 | `Data3` (nested) | `CanvasCrosshair` | `Horsestats3$Data3`: size + int[] ARGB canvas crosshair |
| 31 | `Horsestats2` | `ExternalProfileLocator` | finds BLClient/Feather profile zips/json |
| 32 | `Horsestats3` | `BadlionProfileConverter` | converts a Badlion config into the four Lunar JSON files |
| 33 | `Horsestats4` | `BadlionProfileImporter` | reads a Badlion profile zip and imports it |
| 34 | `Horsestats5` | `ConvertedProfile` | 4 JsonObjects + mapped/skipped mod lists |
| 35 | `Horsestats_2` | `BadlionProfileConfig` | parsed Badlion JsonObject: mods, boxes, mode settings |
| 36 | `Data2` (nested) | `TraitConflictException` | `LightoverlayException$Data2`: conflicting trait types |
| 37 | `Lightoverlay2` | `TraitReader` | read-only trait access (get/has/optional/require/getOrDefault) |
| 38 | `Lightoverlay4` | `TraitSnapshot` | immutable `TraitType→Optional` snapshot + builder |
| 39 | `Lightoverlay5` | `TraitListener` | change listener TRAIT_SET/REMOVED/REMOVED_CONDITIONALLY |
| 40 | `Lightoverlay7` | `TraitDebugFormatter` | static debug dumps of containers/types/diffs |
| 41 | `Lightoverlay8` | `Trait` | one trait instance = TraitType id + value |
| 42 | `Lightoverlay9` | `TraitType` | typed trait key (int id, generic value type) |

Nested rows (8, 29, 30, 36) carry the owner in the evidence as `Owner$Old` and
the exact declaring file in the 5th TSV column.

## Applier dry run

`python3 tools/apply_class_renames.py --map tools/renames/classes-modhighlight.tsv`:

```
[class-renames] 42 rows; 6594 java files
applied=23 skipped=19 files_touched=57 files_renamed=23 mode=dry-run
```

23 rows apply with the simple tree-wide rewriter. The 19 skips are **old-name
collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Gui2Extension2` | crosshair.mixin, fishing.highlight.mixin, dungeonwaypoints.mixin, storageoverlay.mixin, rewindhandlers.fishing, rewindhandlersCore.mixin, rewindhandlersNameplate, tiertagger.mixin (9 packages) |
| `Gui2Extension3` | crosshair.mixin, dungeonwaypoints.mixin, rewindhandlersNameplate |
| `HighlightType2`, `HighlightType3` | `client.highlight.mixin` (a different enum) |
| `Data2` | gui.nameplate, onesevenvisuals.modern, highlight.mixin.highlight, markers.mixin.gui, ichor.mixin (5 packages) |
| `Data3` | mod.holograms.mixin, horsestats, pkg, ichor.mixin (4 packages) |
| `GuiRewindhandlersHandler2` | holograms.gui, holograms.nameplate, util.alert |
| `Holograms2` | fishing.holograms.mixin, rewind.holograms, rewind.rewindhandlers.holograms, inactive.mixin.holograms.mixin, markers.holograms (7 packages) |
| `Holograms3` | rewind.holograms, inactive.mixin.holograms.mixin, markers.mixin.holograms |
| `Holograms4` | rewind.holograms, markers.mixin.holograms |
| `Holograms5` | markers.mixin.holograms |
| `Horsestats2`…`Horsestats5` | `client.horsestats.mixin` (the mixin twins) |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
simple name in unrelated `crosshair`/`armorstatus`/`keystrokes` `Gui2Extension*`
enums, the various `holograms` packages, and the `horsestats.mixin` twins,
merging distinct types. The collision rows need the planned import/package-aware
rewriter (same follow-up as `classes-07.md`, `classes-12.md`, `classes-15.md`).

## Caveats / follow-ups

* **Nested rows 8, 29, 30, 36** (`Holograms2.Data3`, `Horsestats3.Data2`,
  `Horsestats3.Data3`, `LightoverlayException.Data2`) are real nested types. The
  applier matches declarations by simple name and all four names are declared in
  many other packages, so they are skipped by design; they need a nested-aware
  pass that renames inside the declaring file.
* **`Horsestats2`…`Horsestats5` collide with `client.horsestats.mixin`.** The
  mixin twins are a different cluster; whoever handles `client.horsestats.mixin`
  should coordinate so the two families get distinct names.
* **`IBoneRenderer` is a real Lunar name** recovered from
  `tools/work/lmp/sacred-mappings.tiny` by exact method match; it is the only
  row where the real name was recoverable. The remaining morph/profile/trait
  rows use role names because the sacred mapping uses a different obfuscation
  scheme with no available crosswalk.
* Names added by this cluster were checked with the applier's own declaration
  scan; no row was skipped for "new name already declared". Manual re-check:
  `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java`.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-modhighlight.tsv
# → applied=23 skipped=19 files_touched=57 files_renamed=23 mode=dry-run
```