# Cluster — `rewindhandlers/coordinates` + `rewindhandlersNameplate` (12 rows)

Map: `tools/renames/classes-coordsnameplate.tsv` (9 top-level + 3 nested `DataN`).

## What this cluster actually is

Two halves of the Rewind export/timeline-editing path:

1. **`rewindhandlers/coordinates` — timeline-editing state.** `Coordinates` is the
   static selection/timeline context (`method41/42` = selected + multiselect);
   the lazy classes around it are the export-panel sections and the JS-bridge
   DTOs. `Coordinates2` is a `(type, List<LightingExtension>)` settings section
   (`ExportSettingsPanel` builds exactly four: `renderSettings`, `videoSettings`,
   `audioSettings`, `exportSettings`; `Rewindhandlers2_4.updatePropertyValue` /
   `resetPropertyValue` iterate `field27` matching `type()`). `Coordinates3` is
   an empty namespace for the Gson DTOs consumed only by
   `RewindTimelineBridge` `@CallbackJS` methods; `Coordinates4` is the
   `(Fishing2Iterator, Fishing2Loader, frame)` selected keyframe.
2. **`rewindhandlersNameplate` — the FFmpeg export pipeline.** Despite the
   `Nameplate` package name nothing here is a nametag: it is the render-settings
   POJO plus the container/codec/encoder enums and the downloader, renderer,
   audio recorder and encoder probe that `RewindRenderQueue` drives.

## Renames (12 rows)

### `rewindhandlers.coordinates` (6)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Coordinates2` | `ExportSettingsSection` | 4 instances in `ExportSettingsPanel` (`renderSettings/…`); matched by `type()` in `Rewindhandlers2_4` |
| 2 | `Coordinates3` | `TimelineBridgePayloads` | empty namespace; only `RewindTimelineBridge` JS callbacks consume its DTOs |
| 3 | `Data2` (`Coordinates3`) | `KeyframeMove` | `moveKeyframes` payload (`currentFrame`→`newFrame`), applied via `Data4` |
| 4 | `Data3` (`Coordinates3`) | `LayerMove` | `moveLayers` payload (`layerId,trackId,frame`), applied by `method10` |
| 5 | `Data4` (`Coordinates3`) | `PendingKeyframeMove` | non-static `(loader,newFrame,data)` staged in `list3` then `put` into `method27` |
| 6 | `Coordinates4` | `SelectedKeyframe` | `(category,loader,frame)`; `Coordinates.method41/42` selected + multiselect |

### `rewindhandlersNameplate` (6)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 7 | `Gui2Extension2` | `VideoCodec` | `H264/HEVC/VP9/AV1/WEBP` + encoder list; `@SerializedName("codec")` |
| 8 | `Gui2Extension3` | `VideoEncoder` | ffmpeg args provider + `hardware`/`macOS` flags; `@SerializedName("encoder")` |
| 9 | `RewindhandlersNameplate2` | `FFmpegDownloader` | downloads ffmpeg 7.1.1 zip from `ffmpeg.lunarclientcdn.com`; `CrashReporter` tag `FFmpegDownloader` |
| 10 | `RewindhandlersNameplate_2` | `LoopbackAudioRecorder` | OpenAL loopback capture to s16le file; `RewindRenderQueue` field13 |
| 11 | `RewindhandlersNameplate_3` | `FFmpegRenderer` | owns the `ffmpeg` rawvideo-stdin process, IO threads, 5-slot queue; `RewindRenderQueue` field12 |
| 12 | `RewindhandlersNameplate_4` | `EncoderProbe` | `ffmpeg -encoders` parse + per-encoder testsrc probe on `RewindEncoderProbeThread`; tag `RewindEncoders` |

Rows 3–5 are **nested** (`Coordinates3$DataN`), so they carry the 5th `file`
column and must go through the aware applier, which resolves them inside
`Coordinates3.java` instead of mass-renaming tree-wide `DataN`.

## Provenance / evidence used

* `rewindhandlers/coordinates/Coordinates{,2,3,4}.java`, `GuiIterator.java` —
  shapes of the section, DTO namespace and selected keyframe.
* `rewindhandlers/ExportSettingsPanel.java` (4 `Coordinates2` instances,
  `method4` encoder-choice filtering) and `rewindhandlers/Rewindhandlers2_4.java`
  (`updatePropertyValue`/`resetPropertyValue`/`downloadFfmpeg`) — section + codec
  + downloader wiring.
* `rewindhandlers/chest/RewindTimelineBridge.java` (`moveLayers`/`selectKeyframes`/
  `moveKeyframe`/`moveKeyframes`) — all four DTO usages.
* `rewindhandlersNameplate/{Gui2Extension,Gui2Extension2,Gui2Extension3,
  RewindhandlersNameplate,RewindhandlersNameplate2,_2,_3,_4,Nameplate}.java`,
  `rewindhandlersNameplate/mixin/RewindhandlersNameplate.java`,
  `mod/misc/RewindRenderQueue.java` (`method10` builds `Nameplate(buffer,height,
  width)`; `field12/13` own the renderer/audio recorder) — export pipeline.
* `tools/mappings-snapshot/restructure/remaining-renames.tsv` lines 2833–2841
  (coordinates obfuscated origins); the `rewindhandlersNameplate` top-level
  siblings have no remaining-renames entry (already flattened before the snapshot).
* New names were grepped against the tree (`class <Name>` plus bare-token
  search): zero declarations and, except for the two log tags that motivate
  rows 9/12, zero references.

## Applier dry run

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-coordsnameplate.tsv
# → rows=12 skipped=0 files_touched=19 files_renamed=9 mode=dry-run
```

Use the aware applier, not v1: the three nested `DataN` rows collide
tree-wide and need the `file`-column scoping. (Verified by dry run on the written map.)

## Caveats / follow-ups

* **Do not move the package in this map.** `rewindhandlersNameplate` (missing
  underscore) is mangled; the correct home is
  `…rewindhandlers.export` (it mirrors `ExportSettingsPanel` and the `export*`
  JS callbacks), but that move belongs in `moves-misc.tsv`, not here.
* **Neighbour renames deliberately excluded** (same-package but out of
  inventory rows 14–25): `RewindhandlersNameplate.toString()` returns
  `"RewindRenderSettings(…)"`, so it wants to be `RewindRenderSettings`;
  `Gui2Extension` (MP4/MKV/MOV/… + `supportsAudio`) wants `VideoContainer`;
  `Nameplate(buffer,height,width)` — only `buffer()` is read, by the ffmpeg
  stdin writer — wants `RenderedFrame`; plain `Coordinates3$Data`
  (`selectKeyframes` payload) wants `KeyframeSelection`. Whoever owns those
  rows should take them together.
* `Gui2Extension2/3` collide as simple names with unrelated enums (e.g.
  `framework.Gui2Extension2`, `armorstatus.Gui2Extension2/3`); the aware
  applier scopes by package, so no `--allow-collisions` on v1.
