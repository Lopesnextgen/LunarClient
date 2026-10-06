# Cluster 03 — `markers.mixin.gui.mixin` + `client.glintcolorizer` (45 classes)

Source revision: `tools/renames/cluster-03.txt` md5 `674b0400d8273e64c4221467543691b6`
(45 rows: 26 `markers.mixin.gui.mixin` types + 19 `glintcolorizer` types; all top-level).
Map: `tools/renames/classes-markersgui.tsv` md5 `e634d149dfa915a6b578dae5f88aab67`.

## What this cluster actually is

Two unrelated packages the clusterer merged.

### 1. `markers.mixin.gui.mixin` — the WebOSR "Driver" GUI providers (26 classes)

**Not mixins.** These are the per-route providers of Lunar's **WebOSR Driver** UI
(the browser-driven overlay/route system). The parent packages were renamed by
cluster 05 (`classes-ichorutil.tsv`, commit `62b5319b`): the interface
`GuiExtension_2` is now **`DriverGuiExtension`**, `Markers4` is **`DriverBridge`**
(the `@CallbackJS` dispatcher + provider registry), `Markers2Handler2` is
**`DriverScreen`**/`DriverRoute`, `Markers5` is **`DriverSettingExtension`**, etc.

Each class in this package is one registered route provider:

* implements `DriverGuiExtension` + `GuiIterator.Extension`,
* exposes static `@CallbackJS` methods that the WebOSR UI calls,
* returns the route's JSON through `provide()`/`method128()`.

The route name for each provider comes straight from the registry in
`DriverBridge.method1()`; that is the primary evidence column below. The
per-route WebOSR providers in `framework.feature.rewind.rewindhandlers.*` were
already named **`<Purpose>Bridge`** by cluster `classes-inventorysearch`
(`RewindEditorBridge`, `RewindTimelineBridge`, `RewindPropertiesBridge`, …), and
`ShaderDebugJsBridge` set the same precedent, so these follow that convention.
Only `Gui15` ("language") needs the disambiguator `LanguagePageBridge` because
`bridge.horsestats.LanguageBridge` already exists.

Classes in this package that are **not** in `cluster-03.txt` and stay lazy:
plain `Gui` ("mods"/"markers"), `GuiExtension` ("profileImport"), `Gui10`
("cosmetics"), `Gui12` ("sprays"), `Gui13` ("emotes"), `Gui20` ("blogPosts"),
and the `nameplate/` + `rewindhandlers/` subpackages.

### 2. `client.glintcolorizer` — **the item-glint package name is a decoy** (19 classes)

**Accuracy fix.** Despite the package name, this is **not** Lunar's item-glint
(enchantment-shimmer) feature. It is a vendored **McLib + Blockbuster Bedrock
particle engine**:

* `BedrockLibrary` loads `assets/blockbuster/particles/*.json`
  (`default_fire`/`default_magic`/`default_rain`/`default_snow`);
* the schema is a **Bedrock particle JSON** (`particle_effect`, `components`,
  `curves`, `minecraft:emitter_*`, `minecraft:particle_*`, `Molang` expressions);
* the parser is **McLib's math/molang** (`mchorse.mclib.math.*`,
  `mchorse.mclib.math.molang.*`, `mclib.interpolations.*`);
* consumers are Lunar cosmetics/emotes — `holograms/Holograms6.java` and
  `holograms/Holograms8.java` feed `Glintcolorizer3_2` (the `BedrockScheme`) to
  emote particle effects via `Ref.method4().method70()`.

The real **GlintColorizer** is `com.moonsworth.lunar.client.mod.render.GlintColorizer`
(apollo `ModGlintColorizer` / `GlintModule`), which is a different file. Every
name below was matched to its upstream Blockbuster/McLib identity (same field
names, same methods, same string literals).

The cluster only lists 19 of the package's ~70 types; the rest are other
clusters. Cluster members match as follows (upstream → new):

| cluster class | upstream (Blockbuster / McLib) |
|---|---|
| `Glintcolorizer2` | `BedrockLibrary` |
| `Glintcolorizer3` | `mclib.math.MathBuilder` |
| `Glintcolorizer4` | `mclib.utils.Timer` → `CooldownTimer` (name taken) |
| `Glintcolorizer5` | `mclib.utils.MathUtils` → `ClampUtils` (name taken) |
| `GlintcolorizerExtension` (not in cluster) | `IComponentEmitterInitialize` |
| `GlintcolorizerExtension2` | `IComponentEmitterUpdate` |
| `GlintcolorizerExtension3` | `IComponentParticleRender` |
| `GlintcolorizerExtension4` | `IComponentParticleUpdate` |
| `GlintcolorizerExtension5` | `IComponentParticleInitialize` |
| `GlintcolorizerImpl2` | `mclib.math.molang.expressions.MolangValue` |
| `GlintcolorizerType2` | `BedrockMaterial` |
| `GlintcolorizerType_2` | `CameraFacing` |
| `GlintcolorizerType_3` | `mclib.utils.Interpolation` |
| `GlintcolorizerType_4` | `BedrockCurveType` |
| `Glintcolorizer_2` | `mclib.utils.Interpolations` |
| `Glintcolorizer_3` | `BedrockCurve` |
| `Glintcolorizer_5` | `mclib.math.molang.expressions.MolangExpression` |
| `Glintcolorizer_7` | `Tint` (`Tint.Solid` = `Glintcolorizer$Data`) |

## Renames (45 rows)

### `com.moonsworth.lunar.client.markers.mixin.gui.mixin` (26)

| # | old | new | route / evidence |
|---|-----|-----|------------------|
| 1 | `Gui2` | `StyngrBridge` | "styngr": addJam/removeJam/previewJam/stopPreview (Chest2 sound preview) |
| 2 | `Gui3` | `UiStateBridge` | "uiState": setWindowState/setSortState/setQuickNavigationState |
| 3 | `Gui4` | `SkyblockBridge` | "skyblock": mod-list screen |
| 4 | `Gui5` | `OutfitBridge` | "outfits": add/delete/setDefault/setFavorite/rename |
| 5 | `Gui6` | `BadgeBridge` | "badges": equip → EquipBadgeRequest |
| 6 | `Gui7` | `ColorBridge` | "colors": add/remove chroma (LightingExtension4222) |
| 7 | `Gui8` | `LunarPlusBridge` | "lunarPlus": setColor → UpdateLunarPlusColorRequest |
| 8 | `Gui9` | `SettingsBridge` | "settings": setAdvancedMode/update/reset/invokeAction/setEditingKeybind (extends DriverSettingExtension) |
| 9 | `Gui11` | `PromotionBridge` | "promotion": playPromotion/refresh/openMedalPromotion/claimReward |
| 10 | `Gui14` | `HostedWorldBridge` | "hostedWorlds": inviteFriend/kick/promote/demote (+ internal `Type` enum) |
| 11 | `Gui15` | `LanguagePageBridge` | "language": selectLanguage/setForceUnicode (`LanguageBridge` taken) |
| 12 | `Gui16` | `CosmeticPreviewBridge` | "cosmeticPreview": exitPreview/openStore/openPreviewModal/checkout/joinWorld |
| 13 | `Gui17` | `SavedSkinBridge` | "savedSkins": updateSkinType/setFavorite/apply/delete/add/rename/save |
| 14 | `Gui18` | `AlertBridge` | "alerts": dismiss(index) |
| 15 | `Gui19` | `AccountBridge` | "accounts": addAccount/removeAccount/selectAccount |
| 16 | `Gui21` | `SocialMediaBridge` | "socialMedia": openSocialMedia/unlinkSocial |
| 17 | `GuiExtension3` | `ProfileBridge` | "profiles": provide → Client.method61() |
| 18 | `GuiExtension4` | `ServerDiscoveryBridge` | "serverDiscovery": discover/search/loadAutocomplete/loadModal/join/save |
| 19 | `GuiExtension5` | `MetadataBridge` | "metadata": lunarInfo (branch/gitHash/versions/scale) |
| 20 | `GuiExtension6` | `NotificationBridge` | "notification": Client.method69().method128() |
| 21 | `GuiExtension7` | `PlayerBridge` | "player": location + dimension/dimensionKey |
| 22 | `GuiExtension8` | `ServerPingBridge` | "serverPing": ping → serverPing:result |
| 23 | `GuiExtension9` | `HomeButtonBridge` | "homeButtons": singleplayer/multiplayer/discover/store |
| 24 | `GuiExtension10` | `HomeRadioBridge` | "homeRadio": mute/setVolume/close + music event handlers |
| 25 | `GuiExtension11` | `HomeThemeBridge` | "homeTheme": selectTheme/playSound/openWrapped/openAdvent/balloon |
| 26 | `GuiExtension12` | `HomeNavigationBridge` | "homeNavigation": btnMenu/btnLocker/btnSatellite/btnOptions/btnReplay… |

### `com.moonsworth.lunar.client.glintcolorizer` (19)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 27 | `Glintcolorizer2` | `BedrockLibrary` | presets/factory, reload/load/save, `assets/blockbuster/particles/*.json` |
| 28 | `Glintcolorizer3` | `MathBuilder` | McLib expression builder (breakdown/parseSymbols/createFunction/…) |
| 29 | `Glintcolorizer4` | `CooldownTimer` | enabled/time/duration + mark/check/checkRepeat |
| 30 | `Glintcolorizer5` | `ClampUtils` | static clamp/cycler (int/float/double) |
| 31 | `GlintcolorizerExtension2` | `IComponentEmitterUpdate` | method2(emitter) per emitter update |
| 32 | `GlintcolorizerExtension3` | `IComponentParticleRender` | method1/method2(emitter,float) = pre/post render |
| 33 | `GlintcolorizerExtension4` | `IComponentParticleUpdate` | method1(emitter,particle) per particle update |
| 34 | `GlintcolorizerExtension5` | `IComponentParticleInitialize` | method2(emitter,particle) at spawn |
| 35 | `GlintcolorizerImpl2` | `MolangValue` | wraps an IValue + `return` flag |
| 36 | `GlintcolorizerType2` | `BedrockMaterial` | particles_opaque/alpha/blend |
| 37 | `GlintcolorizerType_2` | `CameraFacing` | rotate_xyz/lookat_xyz/direction_* |
| 38 | `GlintcolorizerType_3` | `Interpolation` | `mclib.interpolations.*` easing enum |
| 39 | `GlintcolorizerType_4` | `BedrockCurveType` | linear/catmull_rom |
| 40 | `Glintcolorizer_2` | `Interpolations` | lerp/bezier/cubicHermite/envelope/normalizeYaw |
| 41 | `Glintcolorizer_3` | `BedrockCurve` | curve nodes + input/horizontal_range |
| 42 | `Glintcolorizer_4` | `TruePredicate` | unused single boolean method returning true (see caveats) |
| 43 | `Glintcolorizer_5` | `MolangExpression` | isZero/isOne/isConstant + get()/toJson() |
| 44 | `Glintcolorizer_6` | `HologramSorting` | static toggle gating hologram distance sorting |
| 45 | `Glintcolorizer_7` | `Tint` | compute(particle)/toJson + parseColor/parseGradient |

## Caveats / follow-ups

* **Two weak rows (no upstream match found).**
  * `Glintcolorizer_4` is an unused trivial class with a single
    `boolean method() { return true; }`. It was recovered from the runtime jar
    (lunar.jar, obf `HORHROIOIOICIRHIOCOICHHHIHCIIO` in the particle obf package)
    but has no McLib/Blockbuster counterpart I could pin down and no in-tree
    reference at all. `TruePredicate` is a placeholder role name pending a
    better read.
  * `Glintcolorizer_6` is a static boolean (default true) with a getter/setter,
    referenced only by `glintcolorizer.nameplate.Nameplate.method1` (hologram
    distance sorting), so it is named after that sole use.
* **Name collisions avoided.** `Timer` (`mclib.utils.Timer`) and `MathUtils`
  (`mclib.utils.MathUtils`) are already declared in the tree
  (`client.util.MathUtils`, a `Timer`), hence `CooldownTimer` / `ClampUtils`.
  `Interpolation`/`Interpolations`/`Molang*`/`Bedrock*`/`Tint`/`CameraFacing`
  were confirmed free tree-wide.
* **Nested rows.** None — every row in `cluster-03.txt` is a top-level file, so
  no 5th `file` column / `Owner$Old` rows are needed.
* **Stale references.** `glintcolorizer.highlight|holograms|nameplate|
  rewindhandlers.*` and `glintcolorizer.Glintcolorizer2Base*` / `2Handler*` /
  `3_2` / `5_2` / `2_2`…`2_4` are other clusters and stay lazy; the applied
  rows will rewrite their references to the new names (the applier matches by
  simple name + import).

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-markersgui.tsv   # dry run
```