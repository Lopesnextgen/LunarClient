# Cluster 13 — `rewind.gui` + `rewindhandlersNameplateCore.mixin` + `client.highlight` (36 rows)

Source revision: `tools/renames/cluster-13.txt` md5 `0d1bbcbf026791d9aadcba27a40ca2e3`
(36 rows: 25 top-level types + 9 nested `DataN` + 2 more nested `DataN`).
Map: `tools/renames/classes-rewindgui.tsv` md5 `ad0fea3147166d46becf170073ac5c16`.

## What this cluster actually is

Three unrelated packages got lumped together by the inventory generator. The
"gui" in the package name is a decompiler artefact — **none of these are
screens or widgets**:

1. **`framework.feature.rewind.gui` — the replay-editor timeline model.**
   `Rewind` is Lunar's ReplayMod integration; `gui` here is the project/timeline
   data model that is serialised to `project.json`. Ground truth is the JSON
   emitted by `rewindhandlers/chest/Chest.java` and
   `rewindhandlers/holograms/Holograms.java`:
   `timeline { id, name, tracks[], framerate, frameTime, viewportWidth, … }` and
   `track { id, type, layers[], properties[], showKeyframes, enabled }`.
   So `Highlight_3` is the **Timeline**, `Gui2_2` the **TrackCollection**
   (`@SerializedName("tracks")`), `Gui_2` a **Track** and `Highlight<T>` a
   **Layer** of keyframe segments. The three track subclasses carry the type
   strings `"effect"`, `"audio"`, `"gameplay"`. `Gui.java` (the `rewind/`
   directory constants) is a sibling that is *not* in this cluster.

2. **`rewindhandlersNameplateCore.mixin` — replay packet recorders.**
   Despite the `mixin` package name these classes carry **no `@Mixin`
   annotation**. `RewindHandlers5` holds a `List<RewindhandlersNameplateCore>`
   of them and dispatches the 19 `method1..method19` event hooks; each `ImplN`
   overrides a subset and writes serialisable replay packets
   (`Nameplate2Impl*`) through the `Rewind_4` writer. They are per-category
   **recorders**, so they are named `<Category>Recorder`, not `…Mixin`.

3. **`client.highlight` — the event system** (see `classes-26.md`). This cluster
   only touches the leftover lazy nested `DataN` payloads of five event classes
   plus three unnamed empty/leaf events (`HighlightImpl8/18/19`).

## Renames (36 rows)

### `framework.feature.rewind.gui` — timeline model (12)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Gui2` | `AngleUtils` | `method1` wraps an angle across the 360° boundary, `method2` normalises to [0,360) |
| 2 | `Gui2_2` | `TrackCollection` | the `@SerializedName("tracks")` object: `Iterable<Gui_2<?>>` holding effect/gameplay/audio track sets + shared layer map |
| 3 | `Gui3` | `ProgressListener` | `onStart/method1(done,total,percent)/method2()/method3(msg,ex)/onMessage`; used by `ExportSettingsPanel` and the FFmpeg downloader |
| 4 | `Gui5` | `PathUtils` | `method1(Path,Path)`/`method2(File,File)` produce forward-slash relative paths for resource-pack/media JSON |
| 5 | `Gui6` | `EntityPositionApplier` | default `method1(Nameplate3,x,y,z,onGround)` sets previous pos + `lerpTo` + `setOnGround`; implemented by `Nameplate2Impl16/17` |
| 6 | `Gui7` | `ReplayLocation` | `(ReplayEnvironment, String value)` pair; `Nameplate4.toString()` calls the field `currentLocation` |
| 7 | `GuiImpl2` | `SoundTrack` | `Gui_2` subclass with `type()=="audio"` |
| 8 | `GuiImpl3` | `GameplayTrack` | `Gui_2` subclass with `type()=="gameplay"` |
| 9 | `GuiType2` | `AudioSampleFormat` | OpenAL `MONO8/MONO16/STEREO8/STEREO16` enum (`alFormat`, channels, bytesPerSample) |
| 10 | `GuiType3` | `KeybindAction` | `TAB/ZOOM/F1`; `Map<KeybindAction,Boolean>` "keybinds" in `Nameplate4` |
| 11 | `GuiType4` | `ReplayEnvironment` | `LIVE_EXPERIENCE/HOSTED_WORLD/SINGLEPLAYER/MULTIPLAYER` with ids `liveExperience/…` |
| 12 | `Gui_2` | `Track` | abstract track: `id`, `layers` (`Highlight<T>`), `type`, `showKeyframes`, `enabled` |

`GuiImpl` (the `"effect"` track) is **not** in `cluster-13.txt`; it is the
obvious `EffectTrack` and is left for whoever owns that row.

### `rewindhandlersNameplateCore.mixin` — replay recorders (12)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 13 | `RewindhandlersNameplateCoreImpl2` | `KeyframeRecorder` | starts a new segment (`RewindIterator_2.method6`) at the `Gui2Extension` keyframe interval + 5-min auto-save |
| 14 | `RewindhandlersNameplateCoreImpl3` | `GuiStateRecorder` | records screen change/open, container-slot stage, marker + key input |
| 15 | `RewindhandlersNameplateCoreImpl4` | `CosmeticsRecorder` | pushes local cosmetics (`Client.method109().method53().method25()`) while recording |
| 16 | `RewindhandlersNameplateCoreImpl5` | `ScoreboardRecorder` | records the sidebar JSON (`GuiRewindhandlersHandler23.field7`) |
| 17 | `RewindhandlersNameplateCoreImpl6` | `PlayerStateRecorder` | held item, pause, swing, sprint, third-person, item use, attack strength, item swap |
| 18 | `RewindhandlersNameplateCoreImpl7` | `KeybindRecorder` | records TAB/ZOOM/F1 toggle state |
| 19 | `RewindhandlersNameplateCoreImpl8` | `WorldEffectRecorder` | block-break animation + effect packets (`Bridge.method59().method11()/method12()`) |
| 20 | `RewindhandlersNameplateCoreImpl9` | `HudStateRecorder` | HUD component enable/active state (`Nameplate2Iterator*`) |
| 21 | `RewindhandlersNameplateCoreImpl10` | `ResourcePackRecorder` | server/client resource-pack lists (relative paths via `PathUtils`) |
| 22 | `RewindhandlersNameplateCoreImpl11` | `MessageSignatureRecorder` | 1.19+ chat message-signature cache |
| 23 | `RewindhandlersNameplateCoreImpl12` | `LocationRecorder` | records the current `ReplayLocation` |
| 24 | `RewindhandlersNameplateCoreImpl13` | `PacketRecorder` | raw clientbound PLAY/CONFIGURATION packets (`Nameplate2Impl3`) |

### `client.highlight` — nested event payloads + leaf events (12)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 25 | `Data2` (`EventRenderContainerSlot`) | `EventRenderContainerSlotAfterItems` | fired after item stacks are drawn (`GuiContainerMixin2 lunar$renderGuiAfterItems`) |
| 26 | `Data2` (`EventRenderTooltip`) | `EventRenderTooltipPre` | tooltip pre-render stage; logs `"EventRenderTooltip.Pre"` |
| 27 | `Data2` (`LunarEventBus`) | `EventBusOperation` | queued (un)register op drained at dispatch depth 0 |
| 28 | `Data3` (`EventRenderContainerSlot`) | `EventRenderContainerSlotPost` | post stage of `GuiScreen.drawScreen` (Forge `DrawScreenEvent.Post`) |
| 29 | `Data4` (`EventRenderContainerSlot`) | `EventRenderContainerSlotPre` | pre stage of `GuiScreen.drawScreen` (Forge `DrawScreenEvent.Pre`) |
| 30 | `Data4` (`EventRenderScreenOverlay`) | `EventScreenInitPre` | before `GuiScreen.setWorldAndResolution` clears the button list |
| 31 | `Data5` (`EventRenderHologram`) | `EventRenderHologramItem` | item hologram (`Bridge7Iterator.method127`) |
| 32 | `Data5` (`EventRenderScreenOverlay`) | `EventScreenInitPost` | after `setWorldAndResolution` completes |
| 33 | `Data6` (`EventRenderHologram`) | `EventRenderHologramText` | text hologram (`Bridge7Iterator.method128..131`) |
| 34 | `HighlightImpl8` | `EventOptionsSaved` | empty event at TAIL of `GameSettings.saveOptions` |
| 35 | `HighlightImpl18` | `EventResourcesReload` | empty event at TAIL of `Minecraft.refreshResources`; `FogIterator` drops its texture cache |
| 36 | `HighlightImpl19` | `EventRenderPlayerStats` | screen+font+marker + `foodRendered`; fired at TAIL of `GuiIngame.renderPlayerStats`, read by `Saturation` |

Rows 25–33 are **nested** (`Owner$Old` in the evidence + 5th `file` column), so
the aware applier resolves them inside the owner file instead of mass-renaming
`DataN`. Rows 34–36 are top-level but their simple names are declared in several
packages, so they also carry the 5th `file` column to scope the declaration.

## Provenance / evidence used

* `tools/work/mappings/normalize-renames.tsv` — the `gui` files are the flattened
  `rewind/mixinRewindhandlers/gui/*` subtree; the recorder package is
  `rewind/rewindhandlers3/*`.
* `tools/mappings-snapshot/restructure/remaining-renames.tsv` lines 2976–3010 —
  obfuscated origin of the recorders.
* Runtime firers recovered from the staging jar
  (`tools/work/staging/lunar-all-normalized.jar`, `GuiContainerMixin2`,
  `GuiScreenMixin2`, `ForgeHooksClientMixin`, `GuiIngameMixin3`) and
  `tools/work/quarantine/src` — these name the `DataN` stages.
* `classes-26.md`/`.tsv` — the outer `highlight` event classes were named there;
  this map supplies the nested rows it deferred (`EventBusOperation`,
  `EventRenderTooltipPre`, `EventRenderHologramItem/Text`).

## Applier dry run

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-rewindgui.tsv
# → rows=36 skipped=0 files_touched=138 files_renamed=27 mode=dry-run
```

The v1 `apply_class_renames.py` cannot apply this map (27 of the 36 old simple
names — `Gui2`, `Gui3`, `Gui5`…`GuiType4`, `Gui_2`, `HighlightImpl8/18/19` — are
declared in multiple packages) and would need `--allow-collisions`, which is
**unsafe**. Use the aware applier.

## Caveats / follow-ups

* **`Gui2$Data`** (the Gson deserializer for `TrackCollection`) is a separate
  `$`-named top-level file and is *not* in this cluster; once `Gui2_2` becomes
  `TrackCollection` it should be `TrackCollectionDeserializer`.
* **`HighlightImpl19`** extends `highlight.mixin.nameplate.HighlightImpl`, not
  the `client.highlight` cancellable base; the `Worldeditcui` user of the same
  simple name is a different class in `highlight.mixin.gui`.
* The `EventRenderScreenOverlay` outer name (from `classes-26`) is a little off:
  its two variants are the pre/post of `GuiScreen.setWorldAndResolution`, so the
  nested names here are `EventScreenInitPre/Post`; a future pass may want to
  rename the outer to `EventScreenInit`.
* New names were checked against the applier's declaration index; no row was
  skipped for "new name already declared".