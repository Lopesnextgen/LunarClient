# Cluster 28 — `framework.feature.mod.fishing` + neighbours (40 classes)

Source revision: `tools/renames/cluster-28.txt` md5 `a6159b0b0020c1705ae08089433cd787`
(40 rows: 39 top-level types + the nested `Rewindhandlers2.Data4`).
Map: `tools/renames/classes-modfishing.tsv` md5 `48db34628958eadeffb6730a1fdcb0db`.

## What this cluster actually is

The cluster spans three unrelated packages that the decompiler dumped next to
each other; the package names are misleading.

* **`framework.feature.mod.fishing`** is **not** a fishing feature. It is the
  Hypixel **SkyBlock utility grab-bag** that survived the `mod.fishing` package
  scramble: SkyBlock commissions, the SkyBlock chat-command system, the
  fairy-soul and metal-detector location data, and their Gson adapters. The
  real names are recoverable from Lombok `toString` leaks and the JSON keys
  (`sbCommandKeyBinds`, `chatCommand<Name>Enabled`, `fairySouls`,
  `metal-detector-locations.json`).
* **`framework.feature.rewind.rewindhandlers`** is Lunar's **Rewind (replay)
  audio + export-GUI layer**: the OpenAL audio-stream player, the export/render
  settings panel (options `bitrate`, `frequency`, `renderRange`, …) and the
  JS-bridge panels that feed the rewind web UI.
* **`framework.feature.rewind.rewindhandlersNameplateCore`** is the **Rewind
  recorder**: per-tick packet-capture tasks plus the audio helpers (sample
  conversion, `RewindAudioMetadata`, audio track writer).
* **`framework.nameplate`** is the **feature/mod framework implementations**:
  the concrete classes behind the interfaces renamed in `classes-20`
  (`Framework3/5/6/8/9/12/13`, `AlertExtension`). `Nameplate5`'s `toString`
  leaks `FeatureDetailsImpl`; the rest follow the reserved keys in
  `Framework` (`enabled`/`options`/`page`/`details`/`index`/`panel`/`support`/
  `children`).

Readable ground truth used: `mod/misc/Skyblock.java`,
`SkyblockGlaciteCommissions.java`, `SkyblockFairySouls.java`,
`SkyblockMetalDetectorDataGen.java`, the quarantined
`mod/misc/SkyblockChatCommands.java`, `framework/feature/mod/Module.java`,
`mod/misc/RewindHandlers.java`, `mod/misc/Rewind.java`,
`mod/misc/RewindHandlers5.java`, `bridge/Bridge_16.java`, and the ReplayMod
reference jar (`ReplayMod-v1_8-2.6.24.jar`, `RenderSettings`).

## Renames (40 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Fishing3` | `SkyBlockCommission` | Lombok `toString` leaks "SkyBlockCommission": goal type SLAY/COLLECT/PARTICIPATE/CRYSTAL/MISC + glacite resource + strings |
| 2 | `Fishing4` | `SkyBlockCommandKeybinds` | keybind store: `sbCommandKeyBinds`/`sbCommandFavorites`/`sbUserDefinedSubCommands`; `Skyblock.field211` |
| 3 | `Fishing5` | `SkyBlockCommandMigration` | migration context passed to `Fishing3_2` steps; moves/renames keybinds + subcommands |
| 4 | `Fishing6` | `ChatCommandSettings` | per-command enabled + allowSelfCommand + allowed chat types (`chatCommand<Name>*`) |
| 5 | `Fishing_2` | `SkyBlockCommand` | builder `toString` leaks "SkyBlockCommand.SkyBlockCommandBuilder"; `commands.json` |
| 6 | `Fishing_3` | `ChatMessageQueue` | static `ArrayDeque<String>`; enqueue + poll-and-send chat |
| 7 | `Fishing_4` | `FairySoulLocations` | `@SerializedName("fairySouls")` Map<island, Set<Vector3i>> |
| 8 | `JsonDeserializerIterator2` | `MetalDetectorLocationsDeserializer` | `JsonDeserializer<mixin/Fishing>` for `metal-detector-locations.json` |
| 9 | `JsonDeserializerIterator_2` | `FairySoulLocationsDeserializer` | `JsonDeserializer<Fishing_4>` for `fairy-souls.json` |
| 10 | `JsonDeserializerIterator_3` | `SkyBlockCommandDeserializer` | `JsonDeserializer<JsonDeserializerIterator$Data>` for `commands.json` |
| 11 | `Data4` (nested) | `AudioStreamAdapter` | `Rewindhandlers2$Data4`: `TypeAdapter<Rewindhandlers2<?>>` |
| 12 | `Gui2Extension2` | `BitrateMode` | option `bitrate`: AUTO_QUALITY/AUTO_SIZE/CUSTOM |
| 13 | `Gui2Extension3` | `AudioSampleRate` | option `frequency`: hz22050…hz96000 |
| 14 | `Gui2Extension4` | `RenderRange` | option `renderRange`: ENTIRE/REGION |
| 15 | `Rewindhandlers2` | `AudioStream` | abstract OpenAL audio stream (sources/buffers/volume/rate) |
| 16 | `Rewindhandlers_2` | `InputTimelinePanel` | serializes `inputTicks`/`inputMetadata` for the rewind UI |
| 17 | `Rewindhandlers_3` | `ExportSettingsPanel` | video/audio/export settings (`renderSettings`/`videoSettings`/`audioSettings`) |
| 18 | `Rewindhandlers_4` | `EntityOptionOverrides` | Map<entityId, Map<optionId, Rewindhandlers2_5>> override registry |
| 19 | `Rewindhandlers_5` | `EffectsPanel` | serializes highlight `effects` for the rewind UI |
| 20 | `Rewindhandlers_6` | `RewindFileCallbacks` | `@CallbackJS` create/open/openLocation/delete/getMissingMods |
| 21 | `RewindhandlersNameplateCore2` | `AudioSampleConverter` | float→short downmix + in-memory resample |
| 22 | `RewindhandlersNameplateCore3` | `RewindAudioMetadata` | `toString` leaks "RewindAudioMetadata"; mic/loopback metadata |
| 23 | `RewindhandlersNameplateCoreHandler2` | `PlayerStateCapture` | abilities/held-item/XP/window-items packets |
| 24 | `RewindhandlersNameplateCoreIterator2` | `ApolloSettingsCapture` | packs `OverrideConfigurableSettingsMessage` |
| 25 | `RewindhandlersNameplateCoreIterator3` | `ScoreboardCapture` | boss-info + scoreboard + tab header/footer |
| 26 | `RewindhandlersNameplateCoreIterator4` | `WorldStateCapture` | difficulty/border/time/chunk/map packets |
| 27 | `RewindhandlersNameplateCoreIterator5` | `EntityStateCapture` | entity/particle/world-event packets |
| 28 | `RewindhandlersNameplateCoreIterator6` | `PlayerListCapture` | player-list (tab) packets |
| 29 | `RewindhandlersNameplateCore_2` | `RecorderCapture` | interface of per-tick capture tasks |
| 30 | `RewindhandlersNameplateCore_3` | `AudioTrack` | recorded mic/loopback track (id + file + stream) |
| 31 | `Nameplate10` | `PanelPositionImpl` | implements `PanelPosition` ("panel") |
| 32 | `Nameplate11` | `KeystrokeSupport` | implements `Framework13` ("support") + `KeystrokesType` |
| 33 | `Nameplate2` | `DynamicFeatureDetails` | `FeatureDetailsImpl` with Supplier name/description |
| 34 | `Nameplate3` | `FeatureChildren` | implements `AlertExtension` ("children") |
| 35 | `Nameplate4` | `EnabledOption` | implements `Framework3` ("enabled") |
| 36 | `Nameplate5` | `FeatureDetailsImpl` | `toString` leaks "FeatureDetailsImpl" |
| 37 | `Nameplate6` | `OptionContainerImpl` | implements `OptionContainer` ("options") |
| 38 | `Nameplate7` | `PageStateImpl` | implements `PageState` ("page") |
| 39 | `Nameplate9` | `FeatureIndex` | implements `Framework9` ("index"), prefix search |
| 40 | `Nameplate_2` | `CommandCompleter` | tab-completion over the `MixinNameplate` command tree |

## Applier dry run, and the collision rows

`python3 tools/apply_class_renames.py --map tools/renames/classes-modfishing.tsv`:

```
applied=21 skipped=19 files_touched=51 files_renamed=21 mode=dry-run
```

The 19 skips are **old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Fishing3` | `fishing.mixin`, `rewind.fishing` |
| `Fishing4`, `Fishing5`, `JsonDeserializerIterator_2` | `fishing.mixin` / `fishing.gui` |
| `Fishing_2` | `com.moonsworth.lunar.client.fishing` |
| `Data4` | keystrokes, holograms.rewindhandlers, rewind.mixin.nameplate, coordinates, forge.lib |
| `Gui2Extension2`, `Gui2Extension3`, `Gui2Extension4` | 13 / 7 / 3 packages (crosshair, keystrokes, armorstatus, lighting, …) |
| `Rewindhandlers2` | 10 packages (click/fov/fishing/mod/guiRewindhandlers/inactive/…) |
| `Rewindhandlers_2`, `Rewindhandlers_3` | `mod.rewindhandlers`, `inactive.rewindhandlers` |
| `Nameplate2`…`Nameplate7`, `Nameplate_2` | 4–14 packages (armorstatus, markers, lighting, rewind, glintcolorizer, …) |

**Do not run this map with `--allow-collisions`.** It would rewrite the same
simple name in unrelated packages (e.g. every `Nameplate3`, every
`Rewindhandlers2`, every `Gui2Extension2`), merging distinct types. These rows
need the planned import/package-aware rewriter (same follow-up as
`classes-07.md`, `classes-12.md`, `classes-15.md`, `classes-23.md`).

## Caveats / follow-ups

* **Nested row 11** (`Rewindhandlers2.Data4`) is a real nested type inside
  `Rewindhandlers2.java`; the applier's simple-name scan cannot target it while
  `Data4` is declared in five other packages, so it is skipped by design. It
  needs the nested-aware pass together with the sibling nested types.
* **Partial tree.** Several referenced classes are absent from the active tree
  (`RewindhandlersNameplateCore4`, `RewindhandlersNameplateCore4Task`,
  `Framework3/8/9/13`, `alert.AlertExtension`, `mod/misc/SkyblockChatCommands`),
  so some consumers live in `tools/work/quarantine` / `tools/work/staging`.
  `Fishing6` looks unreferenced in `src/main/java` for that reason.
* New names were checked against the tree-wide declaration scan; none of the 40
  new simple names is already declared in `src/main/java`.