# Cluster 67 — `com.moonsworth.lunar.network` (33 rows)

Source: `tools/renames/cluster-67.txt` (33 rows, all files exist; no
`net.minecraft.*`, no missing paths). Map: `tools/renames/wave5/classes-67.tsv`
(**14 rename rows**, 19 skipped as stale rescue duplicates — see §2).
No sources were edited by this subagent; the applier dry run is in §4.

## What this cluster is

`com.moonsworth.lunar.network` is Lunar's **generated OpenAPI okhttp-gson
client** for the analytics/game-event endpoint (`POST /game/event/batch`), not a
mixins package. Earlier naming waves already named the core here
(`classes-networkgui.tsv` applied in `1d4af8830`, `classes-finalnetwork.tsv`
applied in `38ce5b49c`). The 2026-09-15 rescue sweep `7adc91305` then re-added
the **pre-rename copies** of 19 of those classes (each listed as `A` in that
commit), so the tree holds two copies per class: the named lineage and the
placeholder zombie. That gives this cluster two very different halves:

* **14 genuinely unnamed classes** — the models whose `$Data` adapter had already
  been extracted/named but whose outer class was never renamed, plus the six
  event models consumed by `Network2` and the `Rewind` module. These are named
  here.
* **19 zombie duplicates** — skipped (the target name is already declared;
  `apply_class_renames_aware.py` refuses such rows, probe in §4). They are
  repair/dedupe work, per the `classes-59`/`43`/`46` convention.

## §1 Rows written (14)

All 14 target names are unique tree-wide: an anchored declaration grep
(`^\s*(public |final |abstract )*(class|interface|enum|record) <Name>`) finds
**0 declarations** for every target, and the file does not exist yet.

| old | new | evidence (short) |
|---|---|---|
| `MixinHelper3` | `GameRewindExportAudio` | toString `class GameRewindExportAudio`; `channels` enum `MONO("mono")`/`STEREO("stereo")` + `frequency`; `GameRewindExportAudioTypeAdapterFactory.create()` checks `MixinHelper3.class`; created by `Rewind_5` (audio layer), field `audio` of `MixinHelper22` |
| `MixinHelper5` | `GameRewindRecordingLocation` | toString `class GameRewindRecordingLocation`; `type` + `name`/`ip`/`server_mappings_id`/`live_experience_id`; `GameRewindRecordingLocationTypeAdapterFactory`; `List<MixinHelper5> locations` of `MixinHelper26`, read by `RewindIterator2` |
| `MixinHelper24` | `GamePromotionInteractionEventData` | toString `class GamePromotionInteractionEventData`; `interaction_type` (`IMPRESSION`/`CLICK`) + `promotion_type`; its data adapter; `data` field of `NetworkIterator4` |
| `MixinHelper25` | `GameBlogPostInteractionEventData` | toString `class GameBlogPostInteractionEventData`; `post_id` + `interaction_type`; its data adapter; `data` of `NetworkIterator`; `Gui20` sends one with `Type.CLICK` |
| `MixinHelper27` | `GameRewindEditorSessionEventData` | toString `class GameRewindEditorSessionEventData`; `session_type` (`PROJECT`/`QUICK_VIEW`)/`duration_ms`/`timelines List<MixinHelper8>`/`fast_rewind`; its data adapter; `data` of `NetworkIterator5` |
| `MixinHelper162` | `GameRewindLayerGameplay` | toString `class GameRewindLayerGameplay`; `extends MixinHelper16`, `type` enum `GAMEPLAY("gameplay")`; `GameRewindLayerGameplayTypeAdapterFactory`; key `"GameRewindLayerGameplay"` in `GameRewindLayer.field4`; instantiated in `Rewind_5` |
| `MixinHelper163` | `GameRewindLayerAudio` | toString `class GameRewindLayerAudio`; `type` enum `AUDIO("audio")` + `name`; `GameRewindLayerAudioTypeAdapterFactory`; `GameRewindLayer.field4` key; `Rewind_5` |
| `MixinHelper164` | `GameRewindLayerEffect` | toString `class GameRewindLayerEffect`; `type` enum `EFFECT("effect")` + `sub_type`; `GameRewindLayerEffectTypeAdapterFactory`; `GameRewindLayer.field4` key; `Rewind_5` |
| `NetworkIterator2` | `GameRewindLayerAddEvent` | toString `class GameRewindLayerAddEvent`; `implements Network`; `Type REWIND_LAYER_ADD("rewind.layer.add")` + `data MixinHelper23`; `GameRewindLayerAddEventTypeAdapterFactory.create()` checks `NetworkIterator2.class`; anyOf variant of `GameBatchEvent` |
| `NetworkIterator3` | `GameRewindProjectExportEvent` | toString `class GameRewindProjectExportEvent`; `Type REWIND_PROJECT_EXPORT("rewind.project.export")` + `data MixinHelper22`; its event adapter; anyOf variant of `GameBatchEvent` |
| `NetworkIterator4` | `GamePromotionInteractionEvent` | toString `class GamePromotionInteractionEvent`; `Type PROMOTION_INTERACTION("promotion.interaction")` + `data MixinHelper24`; its event adapter; anyOf variant |
| `NetworkIterator5` | `GameRewindEditorSessionEvent` | toString `class GameRewindEditorSessionEvent`; `Type REWIND_EDITOR_SESSION("rewind.editor.session")` + `data MixinHelper27`; its event adapter; anyOf variant |
| `NetworkIterator6` | `GameRewindRecordingEvent` | toString `class GameRewindRecordingEvent`; `Type REWIND_RECORDING("rewind.recording")` + `data MixinHelper26`; its event adapter; `RewindIterator2` enqueues one; anyOf variant |
| `Network2` | `AnalyticsBatcher` | analytics batcher: `ConcurrentLinkedQueue<Network2.Data>` + 15 s `ScheduledExecutorService` `"lunar-analytics-service-thread"`, batch ≤100 / retry <2, POSTs `MixinHelper11` via `MixinHelper_2` to `/game/event/batch`, `Slayer` tag `"Analytics"`; held by `Client.field77` (`method105()`) and built from `ServiceEndpoints.method9()` (`Network2.Type`) |

Naming notes: the 13 model names are not invented — `toString()` prints the
model name, and every one of them is the name of the already-extracted
`<Model>TypeAdapterFactory` in the same package (`GameRewindExportAudio…`,
`GameRewindRecordingLocation…`, `GameRewindLayerGameplay/Audio/Effect…`,
`GamePromotionInteractionEvent…`, `GameBlogPostInteractionEvent…`,
`GameRewindEditorSessionEvent…`, `GameRewindLayerAddEvent…`,
`GameRewindProjectExportEvent…`, `GameRewindRecordingEvent…`), so the applier
will now wire the factories and the anyOf/oneOf wrappers to them.
`AnalyticsBatcher` follows the previous wave's `AnalyticsEvent` /
`AnalyticsBatchEntry` / `AnalyticsEnvironment` family; `AnalyticsService` is
**taken** tree-wide by the protobuf
`com.lunarclient.websocket.analytics.v1.AnalyticsService` used in
`EntityRenderer4`/`AssetServerClient`, so it was avoided.

## §2 Skipped: zombie duplicates (19)

Each skipped row is the pre-rename copy of a class whose named twin is in the
same package. All 19 were renamed in `1d4af8830` (`classes-networkgui.tsv`,
19 rows, `R078`–`R091`) and re-added by the rescue sweep `7adc91305` (each
listed as `A` there). Applying the twin name would collide (verified:

```
SKIP MixinHelper2 -> BaseGameEvent: new name already declared
SKIP MixinHelper62 -> GameBatchEvent: new name already declared
```

Duplicate proof (script-checked, not heuristic): package/import/comment-stripped,
self-name-blanked token comparison shows the bodies differ only by (a) local
variable names — the named lineage was cleaned (`var1` → `obj1`/`text0`), (b) the
default constructor the rename pass injected, (c) references to *other* twins in
this same graph, and (d) the inline `Data implements TypeAdapterFactory` (whose
extracted twin is the `<Model>TypeAdapterFactory` file). `@SerializedName` sets
are identical for every pair. Token-shape ratios 0.83–0.98 for the plain models
(0.43–0.49 for `MixinHelper62`/`63`/`15` because the zombie still carries the
inline `Data` class that the twin has as a separate file).

Ref counts are import-aware (same-package bare name + `import
com.moonsworth.lunar.network.X`/FQN), own file excluded:

| zombie | named twin | refs zombie / twin (in-pkg + ext) |
|---|---|---|
| `MixinHelper2` | `BaseGameEvent` | 14+0 / 5+0 |
| `MixinHelper4` | `GameFailedParseEvent` | 2+0 / 5+0 |
| `MixinHelper6` | `AbstractOpenApiSchema` | 2+0 / 2+0 |
| `MixinHelper7` | `BaseGameEventGeoLocation` | 49+0 / 3+0 |
| `MixinHelper8` | `GameRewindEditorTimeline` | 1+1 / 2+0 |
| `MixinHelper9` | `BaseGameEventInboundLocation` | 3+0 / 3+0 |
| `MixinHelper10` | `BaseGameEventLocation` | 2+0 / 3+0 |
| `MixinHelper11` | `GameEventBatchPostRequest` | 2+0 / 3+0 |
| `MixinHelper12` | `BaseGameEventLocationHostedWorld` | 2+0 / 3+0 |
| `MixinHelper13` | `BaseGameEventLocationPublicServer` | 2+0 / 3+0 |
| `MixinHelper14` | `BaseGameEventInstalledModsInner` | 2+0 / 3+0 |
| `MixinHelper15` | `GameFailedParseEventData` | 1+0 / 3+0 |
| `MixinHelper16` | `GameRewindBaseLayer` | 3+1 / 1+0 |
| `MixinHelper22` | `GameRewindProjectExportEventData` | 1+1 / 2+0 |
| `MixinHelper23` | `GameRewindLayerAddEventData` | 1+1 / 2+0 |
| `MixinHelper26` | `GameRewindRecordingEventData` | 1+1 / 2+0 |
| `MixinHelper62` | `GameBatchEvent` | 2+1 / 4+1 |
| `MixinHelper63` | `GameRewindLayer` | 2+1 / 4+0 |
| `MixinHelper_2` | `GameEventApi` | 1+0 / 0+0 |

**Merge recommendation.** Keep the named/twin lineage; the zombies are the
*pre-cleanup* copies (the only reason they look "live" is that `7adc91305` also
restored callers — `Network2`, `Rewind_5`, `RewindIterator2`, `Gui20`,
`GuiExtension_2`, `Client`, `ServiceEndpoints` — onto them). A dedupe pass should
repoint those callers to the twin names and delete the zombie files. Note that
after the §1 map is applied the graph is already mostly rewired: the
`<Model>TypeAdapterFactory` files, `GameBatchEvent`/`GameRewindLayer` and the
renamed `Rewind*`/`Gui20` call sites will reference the new names, so the only
remaining zombie-internal wiring is `Network2` (owns `Data`/`Type` nested twins
of `AnalyticsBatchEntry`/`AnalyticsEnvironment`), `Client`/`ServiceEndpoints`
and the zombie models referencing each other.

## §3 Skipped for other reasons

* Missing paths / `net.minecraft.*`: none.
* Shaded third-party code: none in this cluster's rows. `com.moonsworth.lunar.network`
  itself is Lunar's own generated client (the openapi **runtime** that is
  third-party — `ApiClient`, `ApiException`, `Configuration`, Gzip interceptor,
  progress bodies — lives in `com.moonsworth.lunar.network.mixin` and is outside
  this cluster; it was already named by `38ce5b49c`). The `AbstractOpenApiSchema`
  twin is the generator's own base class and is left as is.

## §4 Applier dry run

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-67.tsv --verbose
[aware-renames] 14 rows (0 nested); 9808 java files
  com.moonsworth.lunar.network: MixinHelper3 -> GameRewindExportAudio (45 hits)
  com.moonsworth.lunar.network: MixinHelper5 -> GameRewindRecordingLocation (51 hits)
  com.moonsworth.lunar.network: MixinHelper24 -> GamePromotionInteractionEventData (35 hits)
  com.moonsworth.lunar.network: MixinHelper25 -> GameBlogPostInteractionEventData (38 hits)
  com.moonsworth.lunar.network: MixinHelper27 -> GameRewindEditorSessionEventData (43 hits)
  com.moonsworth.lunar.network: MixinHelper162 -> GameRewindLayerGameplay (47 hits)
  com.moonsworth.lunar.network: MixinHelper163 -> GameRewindLayerAudio (49 hits)
  com.moonsworth.lunar.network: MixinHelper164 -> GameRewindLayerEffect (48 hits)
  com.moonsworth.lunar.network: NetworkIterator2 -> GameRewindLayerAddEvent (48 hits)
  com.moonsworth.lunar.network: NetworkIterator3 -> GameRewindProjectExportEvent (48 hits)
  com.moonsworth.lunar.network: NetworkIterator4 -> GamePromotionInteractionEvent (46 hits)
  com.moonsworth.lunar.network: NetworkIterator5 -> GameRewindEditorSessionEvent (48 hits)
  com.moonsworth.lunar.network: NetworkIterator6 -> GameRewindRecordingEvent (48 hits)
  com.moonsworth.lunar.network: Network2 -> AnalyticsBatcher (13 hits)
[aware-renames] rows=14 skipped=0 files_touched=44 files_renamed=14 mode=dry-run
```

No `src/main/resources` file references any of the 33 simple names (grepped), so
the map only affects Java sources.

## §5 Follow-ups for the main agent

1. **`NetworkIterator` is not in this cluster** (the `GameBlogPostInteractionEvent`
   model, `toString` says so, `GameBlogPostInteractionEventTypeAdapterFactory`
   checks it). It needs the same treatment in its own cluster.
2. **`Network` is not in this cluster**: it is the zombie of the already-named
   `AnalyticsEvent` (interface `BaseGameEvent method1()`); after this map the six
   renamed event classes still `implements Network` and must be unified onto
   `AnalyticsEvent` by the merge pass.
3. The `Rewind*`/`Gui20`/`GuiExtension_2` call sites already use the *renamed*
   zombie models (`MixinHelper3/5/8/16/22/23/162/163/164/25/62`), so the applier's
   import-aware rewriting will bring them onto the new names automatically.
