# Rewind highlight cluster (`framework.feature.rewind.highlight`)

Family roles (neighbours `Highlight` = Range->segment layer map owned by each
`Track`, `Highlight2_2` = pending thumbnail request, `HighlightImpl` =
`ReplayTimeline` + savePath/file) were kept; the six lazy names map as below.
Reference clients / runtime jars are absent on this machine, so all evidence
is source + `RewindProject`/`ExportSettingsPanel` serialized vocabulary.

| Old | New | Why |
|---|---|---|
| `Highlight2` | `SegmentLinkManager` | Owns the `"links"` map, groups/ungroups segments with undo; `TrackCollection.method6` |
| `Highlight3` | `ThumbnailManager` | `thumbnails/<uuid>/<frame>.png` cache + `ScreenshotCapture` queue |
| `Highlight_2` | `TimelineMarker` | **Low confidence** — orphan `(int,int,int,String)` DTO, zero refs; shape suggests in/out/color+label marker |
| `Highlight_3` | `ReplayTimeline` | id/name/tracks/playhead doc; `RewindProject` `"selectedTimeline"`, exported as `"timelines"` |
| `Highlight_4` | `MediaPool` | `RewindProject` `@SerializedName("mediaPool")`; media `BiMap` + folders/parents/names |
| `Highlight.java$Data2` | `HighlightAdapter` | Gson adapter for `Highlight`; `AudioStream.AudioStreamAdapter` precedent |

All new simple names verified unique tree-wide (`grep -rn \b<name>\b
src/main/java` empty, no hits in `tools/renames/*.tsv`); bare `Timeline`
was rejected (collides with `replaymod` mixins). `Data2` row carries the
declaring-file column per the `classes-debugprofiler.tsv` precedent
(`Nameplate2$Data2` precedent). No source edits made.
