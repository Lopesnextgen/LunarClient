# Cluster 04 — `com.moonsworth.lunar.network` + `com.moonsworth.lunar.client.guiRewindhandlers` (36 classes)

Source revision: `tools/renames/cluster-04.txt` md5 `df1fec0678b6bcf02550fe4bc04b449e`
(36 rows: 19 `network` + 17 `guiRewindhandlers`).
Map: `tools/renames/classes-networkgui.tsv` (md5 `907e595a42cf2a712b3c0c2369ed043d`).

## What this cluster actually is

Two accuracy fixes. Neither package is what its name suggests:

* **`com.moonsworth.lunar.network` is not the packet/channel layer.** It is the
  **generated OpenAPI HTTP client for Lunar's game-event/telemetry API**
  (openapi-generator output: `@SerializedName` models with
  `validateJsonElement`, `equals/hashCode/toString`, a `TypeAdapterFactory`,
  and the client runtime in `com.moonsworth.lunar.network.mixin` —
  `ApiClient`/`Configuration`/`ApiCallback`/`Pair`/`StringUtil`/…). The model
  classes are flattened from the original jar subtree
  `…/network/mixin/mixinExtra/*` (see
  `tools/mappings-snapshot/restructure/remaining-renames.tsv`); the previous
  placeholder names were `MixinHelperN`. The real model names are embedded in
  the generated `toString()` strings and error messages, so they are exact, not
  inferred. The API is Lunar's telemetry/replay event reporting: `BaseGameEvent`
  carries `lunar_client_git_commit`, `ichor_modules`, `wearer_cosmetic_ids`,
  `canary_token`, `overwolf_muid`, … and the endpoint is `POST /game/event/batch`.
* **`com.moonsworth.lunar.client.guiRewindhandlers` is the DynamicListener
  framework**, not a "GUI rewind handler" package. The base
  `GuiRewindhandlersHandler2` (quarantined, renamed by another cluster) is
  `DynamicListener` — its own error string says
  `"DynamicListener#addDependency() must be called before the listener is
  initialized!"`. The concrete `GuiRewindhandlersHandlerN` classes are the
  shared **listener/state-cache singletons** (CPS, tab list, scoreboard, biome,
  Hypixel location, persistent values, knockback, screen title) that the mods
  depend on. `Annotation3` is the real `@TriggeredBy` (the event bus throws
  `"… has a @TriggeredBy annotation"`), and `Annotation2` is the
  `isEnabled()` marker (`DYNAMICLISTENER_ISENABLED`).

Naming follows the already-applied convention in
`framework/feature/mod/rewindhandlers` (`ProfileIdListener`,
`DungeonMapListener`, `SlayerQuestListener`, `CommissionListener`, …): concrete
listeners are `<Purpose>Listener`. No class in this cluster is a mixin, so no
`<Target><Purpose>Mixin` names apply.

## Renames (36 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `MixinHelper2` | `BaseGameEvent` | telemetry event base, 28 fields (player_uuid, installation_id, …, geo_location); superclass of rows 14–16 |
| 2 | `MixinHelper4` | `GameFailedParseEvent` | `type` + `data` + `__failed_parse`; an anyOf `GameBatchEvent` variant |
| 3 | `MixinHelper6` | `AbstractOpenApiSchema` | generator base: `instance`/`isNullable`/`schemaType` + abstract `getSchemaType()` |
| 4 | `MixinHelper7` | `BaseGameEventGeoLocation` | city/continent/country/region/region_code/postal_code/metro_code/timezone |
| 5 | `MixinHelper8` | `GameRewindEditorTimeline` | `duration_ms` + `List<GameRewindLayer> layers` |
| 6 | `MixinHelper9` | `BaseGameEventInboundLocation` | `type` + `server_ip` |
| 7 | `MixinHelper10` | `BaseGameEventLocation` | `type` + `public_server` + `hosted_world` |
| 8 | `MixinHelper11` | `GameEventBatchPostRequest` | `events List<GameBatchEvent>`; POST body |
| 9 | `MixinHelper12` | `BaseGameEventLocationHostedWorld` | `host_uuid`/`host_username` |
| 10 | `MixinHelper13` | `BaseGameEventLocationPublicServer` | `server_mappings_id` + 7 `rich_status_*` |
| 11 | `MixinHelper14` | `BaseGameEventInstalledModsInner` | `id`/`name`/`version`/`type`; element of `installed_mods` |
| 12 | `MixinHelper15` | `GameFailedParseEventData` | `timestamp` + additionalProperties |
| 13 | `MixinHelper16` | `GameRewindBaseLayer` | `duration_ms` + `properties`; base of the `GameRewindLayer` oneOf |
| 14 | `MixinHelper22` | `GameRewindProjectExportEventData` | extends `BaseGameEvent`; length_ms/path/format/codec/encoder/resolution/framerate/bitrate/audio |
| 15 | `MixinHelper23` | `GameRewindLayerAddEventData` | extends `BaseGameEvent`; `layer` |
| 16 | `MixinHelper26` | `GameRewindRecordingEventData` | extends `BaseGameEvent`; duration_ms/locations/shadow_rewind/mic_recording/system_audio_recording |
| 17 | `MixinHelper62` | `GameBatchEvent` | anyOf wrapper; error lists the 7 event variants |
| 18 | `MixinHelper63` | `GameRewindLayer` | oneOf wrapper; audio/effect/gameplay |
| 19 | `MixinHelper_2` | `GameEventApi` | API service: `POST /game/event/batch`, sync/async |
| 20 | `Annotation2` | `DynamicListenerIsEnabled` | `@Target(METHOD)`, enum `Type{DYNAMICLISTENER_ISENABLED}` |
| 21 | `Annotation3` | `TriggeredBy` | `Class<? extends GuiRewindhandlersHandler2> value()`; event-bus `@TriggeredBy` |
| 22 | `Data2` (nested) | `PersistentValues` | `GuiRewindhandlersHandler26$Data2`: root persistent.json (sbProfiles + protectedItems) |
| 23 | `Data3` (nested) | `BiomeDisplay` | `GuiRewindhandlersHandler27$Data3`: (Biome, name) pair |
| 24 | `GuiRewindhandlers2` | `DependencyValue` | `add/removeListener(Runnable)`; implemented by lighting options |
| 25 | `GuiRewindhandlers3` | `DependencyTracker` | evaluates a supplier, tracks DependencyValue reads |
| 26 | `GuiRewindhandlersHandler22` | `CpsListener` | left/right click timestamps; Cps HUD + Keystrokes |
| 27 | `GuiRewindhandlersHandler23` | `HypixelLocationListener` | `/locraw` → HypixelLocation, fires LocationEvent |
| 28 | `GuiRewindhandlersHandler24` | `TabListListener` | caches tab-list display names |
| 29 | `GuiRewindhandlersHandler25` | `LocalPlayerNameListener` | local player's displayed tab-list name |
| 30 | `GuiRewindhandlersHandler26` | `PersistentValuesListener` | saves/loads `config/lunar/persistent.json` |
| 31 | `GuiRewindhandlersHandler27` | `BiomeListener` | current player biome |
| 32 | `GuiRewindhandlersHandler28` | `ScoreboardListener` | caches sidebar objective lines |
| 33 | `GuiRewindhandlersHandler29` | `KnockbackListener` | fires `Knockbacktrainer` event |
| 34 | `GuiRewindhandlersHandler2_2` | `ScreenTitleListener` | current container screen + title |
| 35 | `GuiRewindhandlersHandler3` | `RefCountedListener` | abstract ref-counted onEnable/onDisable |
| 36 | `GuiRewindhandlers_2` | `LambdaFactory` | LambdaMetafactory Consumer/Runnable/Supplier helper |

Out-of-cluster companions (not listed in `cluster-04.txt`, so not in the map):
`MixinHelper3` (audio), `MixinHelper5` (recording location),
`NetworkIterator`…`NetworkIterator6` (`GameBlogPostInteractionEvent`,
`GamePromotionInteractionEvent`, `GameRewindEditorSessionEvent`,
`GameRewindProjectExportEvent`, `GameRewindRecordingEvent`,
`GameRewindLayerAddEvent`) and `MixinHelper162/163/164`
(`GameRewindLayerGameplay/Audio/Effect`), plus `MixinHelper` →
`GameRewindExportResolution`. The `guiRewindhandlers` base
`GuiRewindhandlersHandler2` is the real `DynamicListener`, and
`GuiRewindhandlersHandler26$Data` / `GuiRewindhandlersHandler27$Data2` are
siblings of the two nested rows here.

## Applier dry runs

`tools/apply_class_renames_aware.py` (the import/package-aware applier) handles
the whole map:

```
[aware-renames] 36 rows (2 nested); 6594 java files
[aware-renames] rows=36 skipped=0 files_touched=154 files_renamed=34 mode=dry-run
```

34 file renames = 36 rows − 2 nested rows (nested types stay in their owner
files; their references are rewritten in place). The two nested rows carry the
5th `file` column and name the owner as `Owner$Old` in the evidence, which is
what the aware applier keys on.

The legacy `tools/apply_class_renames.py` (simple tree-wide rewriter) is **not**
usable for this cluster: it skips 18 of 36 rows as old-name collisions, because
`MixinHelperN`/`GuiRewindhandlersHandlerN`/`AnnotationN`/`DataN` are declared in
many unrelated packages:

```
[class-renames] applied=18 skipped=18 files_touched=96 files_renamed=19 mode=dry-run
```

Colliding old names: `MixinHelper2` (7 packages), `MixinHelper4/6/7` (3),
`MixinHelper8/9/13/22/_2` (2), `Annotation2` (2), `Annotation3` (3), `Data2`
(7), `Data3` (6), `GuiRewindhandlers2/3` (2), `GuiRewindhandlersHandler22/23`
(3), `GuiRewindhandlersHandler24` (2). **Do not run the simple applier with
`--allow-collisions`** — it would merge these into the unrelated
`bridge`/`ichor`/`legacy`/`holograms` classes.

## Caveats / follow-ups

* **`network.mixin` twin names.** `com.moonsworth.lunar.network.mixin.MixinHelper7`
  is the generator's `JSON` helper, *not* the model `BaseGameEventGeoLocation`
  (also named `MixinHelper7`). The aware applier resolves this correctly (the
  model files import the `mixin` one, so their bare `MixinHelper7` stays put,
  and the declaring file is renamed by stem). The `network.mixin` runtime
  (`MixinHelper`/`MixinHelper2`…`MixinHelper7`, `ApiCallback`, `Configuration`,
  `Pair`, `StringUtil`, `InterceptorImpl`, `RequestBodyImpl`,
  `ResponseBodyImpl`, `Authentication`, `ApiKeyAuth`, `HttpBasicAuth`,
  `HttpBearerAuth`, `ApiResponse`, `ServerConfiguration`, `ServerVariable`,
  `MixinHelperException`) belongs to another cluster.
* **`GuiRewindhandlersHandler25` semantics.** It returns the *local player's*
  tab-list name (entry UUID == own UUID, fallback `Memory.getName()`), used to
  tell "you" apart in gift/slayer/dungeon parsing. If a later pass finds the
  real Lunar name, prefer it.
* **`Annotation2` vs `Annotation`.** Both are `@Target(METHOD)` annotations with
  a member of type `Annotation2.Type` and the single value
  `DYNAMICLISTENER_ISENABLED`; `Annotation` is out of this cluster. They are
  near-twins (one marks `DynamicListener.isEnabled()`, the other marks option
  `get()`), so whichever cluster owns `Annotation` should pick a complementary
  name (e.g. `DynamicListenerEnabled`).
* **`@TriggeredBy` name reuse.** Renaming `Annotation3` → `TriggeredBy` makes the
  code match the existing `LunarEventBus` error strings; verify
  `com.moonsworth.lunar.annotations.Annotation3` (a different annotation) is not
  also intended to be `TriggeredBy` before applying.
* Names added here were checked against the applier's declaration scan; no row
  was skipped for "new name already declared".

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-networkgui.tsv
# → rows=36 skipped=0 files_touched=154 files_renamed=34 mode=dry-run
python3 tools/apply_class_renames.py --map tools/renames/classes-networkgui.tsv
# → applied=18 skipped=18 files_touched=96 files_renamed=19 mode=dry-run  (collisions; use the aware applier)
```