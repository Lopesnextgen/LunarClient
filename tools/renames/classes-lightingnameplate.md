# Cluster 13 — `lighting.nameplate` + neighbours (42 rows; 38 mapped, 4 skipped)

Source revision: `tools/renames/cluster-13.txt` md5 `811f8626a345057615de8415ac347d55`
(42 rows). Map: `tools/renames/classes-lightingnameplate.tsv` md5
`fbd8bcab92d6bebc0e42c050283ffcfe` (38 rows; the four dead bridge stubs are
listed under "Skipped").

> The cluster name is only its first package. The 42 rows are a **cross-section
> of five unrelated subsystems** that the decompiler's package scrambling left
> next to each other: the option/settings trait layer (`lighting.nameplate`),
> the Apollo safety prompts (`animations`), the hosted-world relay
> (`coordinates`), the event system (`highlight.mixin.holograms`), the Feather
> profile importer (`horsestats.mixin`), GeckoLib animation controllers
> (`inactive.mixin.gui.mixin`) and the web-UI data providers (`markers`).

## What each sub-package actually is

* **`lighting.nameplate`** — the **trait/attribute layer of the options
  framework** (the same framework `classes-lighting-modhud` named:
  `OptionCombiner`, `SettingsBuilder`, `OptionTreeNode`, `OptionProvider`,
  `AbstractOptionBuilder`, `OptionBaker`, …). The class `Nameplate` (not in this
  cluster) is a registry of `Lightoverlay9<T>` trait keys whose id strings are
  `updates`, `display`, `badges`, `children`, `intercept`, `is_global`,
  `number_rules`, `feature_link`, `feature_id`, `lang_id`, `data_provider`,
  `custom_component`, `debug_object`, `hide_from_api`. The cluster renames the
  *values* of four of those traits plus the default JSON provider and the
  numeric-range implementations.
* **`animations`** — **Apollo button safety/confirmation prompts** (not
  animations). `animations/Animations` holds the active prompt, the abstract
  `animations/mixin/Animations` is the prompt, and the `Impl` subclasses are the
  concrete prompts keyed by lang path (`gui.apollo.button.runCommandPrompt`,
  `gui.apollo.button.openUrlPrompt`, `safety.maliciousUrl.prompt`,
  `safety.maliciousServer.prompt`). Driven by `ChatActionHandler` and
  `Itemcounter`.
* **`coordinates`** — **hosted-world / server-hosting glue** (the proto package
  `com.lunarclient.websocket.hostedworld.v1` and `LocalHostedWorldSettings`).
  `Coordinates3` is a TCP relay, `Coordinates5` the settings model and
  `Coordinates2` a holder; `FogIterator` (missing from the tree) is the manager.
  (A different `framework.feature.rewind.rewindhandlers.coordinates` package
  holds unrelated timeline classes with the same simple names.)
* **`highlight.mixin.holograms`** — part of the **event system** (see
  `classes-23.md`); `Highlight` is the event base. These four events are only
  used by the asset server (`AssetServerClient`), Radio and the ReplayMod
  integration, and are distinct from the same-named events in
  `highlight.mixin.gui` / `highlight.mixin.nameplate`.
* **`horsestats.mixin`** — the **Feather profile importer** path, parallel to
  the already-named Badlion path in `client.horsestats`
  (`BadlionProfileConfig`, `BadlionProfileConverter`, `BadlionProfileImporter`,
  `ConvertedProfile`, `ExternalProfileLocator`).
* **`inactive.mixin.gui.mixin`** — **GeckoLib (Bedrock) animation controllers**;
  the registry map in `inactive/mixin/gui/Gui2.field1` names each one
  (`simple_movement`, `single`, `attacking`, `occasional_blended`, `layered`,
  `molang`). These are *not* Mixin classes despite the package name.
* **`markers`** — the **web-UI option/widget layer** (`MarkersType` is the
  widget enum button/range/slider/…). `markers/gui/*` are `@CallbackJS`
  data-provider handlers; `markers/mixin/gui` is the JSON component model.

## Renames (38 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `AlertExtension2` | `OptionChildren` | impl of nameplate `AlertExtension`: list-backed children container (trait `children`) |
| 2 | `GuiExtension2` | `OptionJsonProvider` | default `GuiExtension` (`data_provider`): serialises id/value/default/description/children/badges/name |
| 3 | `Nameplate2` | `OptionUpdateListeners` | `updates` trait: Consumer update-listener registry, static factory -> `Nameplate2Task` |
| 4 | `Nameplate3` | `OptionFeatureLink` | `feature_link` trait: `getFeature()` returns the owning `Framework7Extension` |
| 5 | `Nameplate4` | `OptionDisplay` | `display` trait: icon/name/action-button/hidden metadata, built by `Nameplate4Task` |
| 6 | `NameplateImpl2` | `FloatNumberRange` | `mixin.Nameplate<Float>` range, value_type `"Float"` |
| 7 | `NameplateImpl3` | `LongNumberRange` | `mixin.Nameplate<Long>` range, value_type `"Long"` |
| 8 | `NameplateImpl4` | `ShortNumberRange` | `mixin.Nameplate<Short>` range, value_type `"Short"` |
| 9 | `NameplateImpl5` | `IntegerNumberRange` | `mixin.Nameplate<Integer>` range, value_type `"Integer"` |
| 10 | `NameplateImpl6` | `ByteNumberRange` | `mixin.Nameplate<Byte>` range, value_type `"Byte"` |
| 11 | `AnimationsImpl2` | `BlockedActionPrompt` | no-op prompt for the BLOCK safety action (`Itemcounter.method7`) |
| 12 | `AnimationsImpl3` | `OpenUrlPrompt` | `gui.apollo.button.openUrlPrompt`, saveChoice/saveChoiceAll |
| 13 | `AnimationsImpl5` | `MaliciousUrlPrompt` | `safety.maliciousUrl.prompt`, adds to the method86() allow-list |
| 14 | `AnimationsImpl6` | `MaliciousServerPrompt` | `safety.maliciousServer.prompt`, adds to the method85() allow-list |
| 15 | `Coordinates2` | `HostedWorldSettingsHolder` | wraps a `LocalHostedWorldSettings`; `FogIterator.field3` |
| 16 | `Coordinates3` | `HostedWorldRelay` | TCP relay `(sessionId, address, port, publishedPort)` from `JoinHostedWorldPush` |
| 17 | `Coordinates5` | `LocalHostedWorldSettings` | `@SerializedName` allowCheats/port/gamemode/difficulty/worldPrivacy/…; toString says `LocalHostedWorldSettings` |
| 18 | `Data5` (nested `Coordinates3$Data5`) | `HostedWorldRelayHandler` | `@Sharable` channel relay handler |
| 19 | `Data12` (nested `HighlightBase$Data12`) | `EventOptionsReload` | empty event re-applying options (`SettingsOverrideDebug`, `SoundChanger`) |
| 20 | `HighlightImpl2` | `EventAssetServerConnected` | posted in `AssetServerClient.onOpen` after the assets handshake |
| 21 | `HighlightImpl3` | `EventWebSocketReady` | carries the `WebSocketClientIterator`; subscribed by `Radio` |
| 22 | `HighlightImpl4` | `EventFeatureToggle` | `Framework7Extension` + boolean; ReplayMod auto-recording gate |
| 23 | `Horsestats2` | `FeatherConvertedProfile` | 4 JSON files + mapped/skipped lists (parallel to `ConvertedProfile`) |
| 24 | `Horsestats3` | `FeatherProfileConfig` | parsed Feather profile: `**ArbitraryData**` + flat settings map |
| 25 | `Horsestats4` | `FeatherProfileConverter` | Feather config -> four Lunar JSON files (~50 per-mod converters) |
| 26 | `Horsestats5` | `FeatherProfileImporter` | Feather import entry + shared option pruner `method7` |
| 27 | `Gui2` (inactive) | `AttackingAnimationController` | GeckoLib controller `"attacking"` (idle/entering/leaving attack) |
| 28 | `Gui2Impl2` (inactive) | `SingleAnimationController` | GeckoLib controller `"single"` |
| 29 | `Gui2Impl3` (inactive) | `OccasionalAnimationController` | GeckoLib controller `"occasional_blended"` |
| 30 | `Gui3` (inactive) | `MolangAnimationController` | GeckoLib controller `"molang"` |
| 31 | `GuiExtension2` (markers) | `FeatureDataProvider` | resolves a feature and provides its `MixinCore9Extension` JSON |
| 32 | `GuiExtension3` (markers) | `MarkersDataProvider` | wraps a `GuiIterator` and provides its JSON |
| 33 | `GuiExtension_2` (markers) | `AnalyticsDataProvider` | `@CallbackJS("submit")` analytics event submit |
| 34 | `GuiExtension_3` (markers) | `AbstractDataProvider` | abstract base: feature lookup by id |
| 35 | `Data2` (nested `Gui2$Data2`) | `CachedJsonProvider` | caches the wrapped `provide()` JsonElement |
| 36 | `Gui2` (markers) | `JsonProvider` | base UI-data interface (`provide()` + cached copy) |
| 37 | `Gui3` (markers) | `JsonSetSerializer` | serialises a `Set<Object>` to a JsonArray |
| 38 | `Type2` (nested `Gui2Task$Type2`) | `ModLoader` | LUNAR/VANILLA/EXTERNAL/FABRIC/FORGE/NEOFORGE/QUILT |

Nested rows 18, 19, 35 and 38 carry the owner in the evidence column
(`Coordinates3$Data5`, `HighlightBase$Data12`, `Gui2$Data2`,
`Gui2Task$Type2`) and the declaring file in the 5th column.

## Skipped (4 rows — unresolvable dead stubs)

`Horsestats9`, `Horsestats17`, `Horsestats24`, `Horsestats27`
(`com.moonsworth.lunar.bridge.horsestats`) are **not** renamed. They are the
same four stubs already investigated by `classes-16.md` §Skipped: `Horsestats9`
is `float method1(BridgeExtension2_5, BridgeExtension2_5)` (living-entity pair)
and the other three are empty 103-byte interfaces with **zero references** in
`src/main/java`, in the remapped runtime jar and in the whole combined staging
jar (`tools/work/staging/lunar-all-final.jar`), and no `@Annotation` metadata.
No target can be derived, so naming them would be invention. They are listed
here only because cluster-13/22/35 overlap on the `bridge.horsestats` rows.

## Collisions and applier behaviour

`tools/apply_class_renames.py` (v1) refuses rows whose old simple name is
declared in more than one package, and cannot touch nested types. On this map
the v1 dry run was:

```
applied=24 skipped=14 files_touched=287 files_renamed=23 mode=dry-run
```

The 14 skips were **old-name collisions across packages**, not map errors:

| skipped old name | also declared in |
|---|---|
| `Coordinates2`, `Coordinates3` | `framework.feature.rewind.rewindhandlers.coordinates` |
| `HighlightImpl2`, `HighlightImpl3`, `HighlightImpl4` | `highlight.mixin.gui` |
| `Gui2`, `Gui3` | `inactive.mixin.gui.mixin` / `markers.mixin.gui` (both directions) |
| `GuiExtension2` | `lighting.nameplate` / `markers.gui` (both directions) |
| `Nameplate2`, `Nameplate3`, `Nameplate4` | `fishing.holograms`, `fog.holograms`, `markers.holograms`, … |
| `Data5`, `Data12` | nested types, not top-level declarations |

**Do not run this map with `--allow-collisions`.** A global word-boundary
replace would rewrite the unrelated `framework.feature.rewind.rewindhandlers.
coordinates`, `highlight.mixin.gui` and `markers.holograms` classes. Use
`tools/apply_class_renames_aware.py`, which resolves each reference from the
file's package/imports and scopes the 5th-column `file` to the declaration; it
applies all 38 rows (including the nested ones). After the aware pass every old
name is `not declared in <package>` and the map is idempotent.

## Verification

```
python3 tools/apply_class_renames.py --map tools/renames/classes-lightingnameplate.tsv
# pre-apply: applied=24 skipped=14 files_touched=287 files_renamed=23 mode=dry-run
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-lightingnameplate.tsv
# post-apply: rows=38 skipped=38 files_touched=0 files_renamed=0 mode=dry-run
```

All 38 new names were checked against the applier's declaration index and
against `src/main/java` directly (`grep -rE "\b(class|interface|enum|record)
NewName\b"`): none is declared anywhere else in the tree.