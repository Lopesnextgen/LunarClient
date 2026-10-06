# Cluster 68 — `com.moonsworth.lunar.client.markers.mixin.gui.mixin` (32 rows)

Source: `tools/renames/cluster-68.txt` (32 rows; all files present on disk).
Map: `tools/renames/wave5/classes-68.tsv` — **6 rows written, 26 rows skipped**
(they are stale-jar twins of already-named classes; §3).

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-68.tsv --verbose
[aware-renames] 6 rows (1 nested); 9808 java files
  com.moonsworth.lunar.client.markers.mixin.gui.mixin: Gui10$$Data2 -> PreviewCosmeticsPayload (1 files)
  com.moonsworth.lunar.client.markers.mixin.gui.mixin: Gui10 -> CosmeticsBridge (12 hits)
  com.moonsworth.lunar.client.markers.mixin.gui.mixin: Gui12 -> SpraysBridge (7 hits)
  com.moonsworth.lunar.client.markers.mixin.gui.mixin: Gui13 -> EmotesBridge (7 hits)
  com.moonsworth.lunar.client.markers.mixin.gui.mixin: Gui20 -> BlogPostsBridge (7 hits)
  com.moonsworth.lunar.client.markers.mixin.gui.mixin: GuiExtension2 -> BugReportBridge (1 hits)
[aware-renames] rows=6 skipped=0 files_touched=9 files_renamed=5 mode=dry-run
```

No `net.minecraft.*` rows, no missing paths; nothing skipped as shaded
third-party code (this whole package is Lunar's own WebOSR/JS bridge).

## 1. What this cluster is

The per-route providers of Lunar's **WebOSR JavaScript bridge**: every class
implements `GuiExtension_2` + `GuiIterator.Extension`, carries static
`@CallbackJS("...")` methods called from the browser UI, and returns the route's
JSON tree through `provide()`/`method128()`. The route name of almost every
class is pinned by the two registries, which list the identical key set:

* `com.moonsworth.lunar.client.markers.Markers4` — the **live** registry
  (instantiated by `markers.mixin.Highlight3Iterator` field14);
* `com.moonsworth.lunar.client.driver.DriverBridge` — the newer-generation copy
  of the same registry (0 users in the tree).

Most of this package was already renamed by the `classes-markersgui` map
(`tools/renames/classes-markersgui.tsv`, applied `c446f88c1`), and the files
were then moved to `com.moonsworth.lunar.client.driver.core.gui.mixin`
(`moves-misc`, `8902f000b`). The rescue sweep `b384aca20` restored the original
placeholder copies from the reference jar **after** the move, so today both
generations exist side by side and are referenced from different files. That is
why 26 of the 32 rows cannot be renamed again.

The 6 rows in this map are the classes that have **no already-named twin**:
`Gui10`, `Gui12`, `Gui13`, `Gui20` (routes that the newer driver generation
still references by their old location) and `GuiExtension2` (dead bug-report
provider, not in any registry).

## 2. Renamed (6)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Gui10` | `CosmeticsBridge` | route **"cosmetics"** in both registries; `@CallbackJS` getCosmeticData / updateCosmeticOption / sync / updateContext / requestData / markAllAsSeen / markAsSeen / unequipAll / showCosmeticLocker / toggleCosmetic / previewCosmetics / setCosmeticFavorite; reads/writes `CosmeticManager` and `OutfitManager`, the option toggles `showClothCloaksForOthers` / `flipArm` / `showHatAboveHelmet` / `showOverChestplate` / `showOverLeggings` / `showOverBoots` / `cosmeticHeight`, and sends `FavoriteCosmeticRequest`/`UnfavoriteCosmeticRequest`. `PromotionBridge` (and its markers twin `Gui11`) equip a claimed reward through `Gui10.method18(id)` = `toggleCosmetic`. |
| 2 | `Gui12` | `SpraysBridge` | route **"sprays"**: spray / selectSpray / unequipAll / remove / showSprayLocker / add; slot management over `SprayManager` + `EquippedSpray` protobuf builders ("Removed Spray" / "Added Spray" toasts) and the `NameplateType.SPRAYS` locker (`Markers2Handler2.field13`). |
| 3 | `Gui13` | `EmotesBridge` | route **"emotes"**: emote / selectEmote / showEmoteLocker / unequipAll / remove / add; `EmoteManager` slot list of `Gui2Handler` entries with `FovHandler` metadata, "Removed Emote" / "Added Emote" toasts, 500 ms emote throttle, `NameplateType.EMOTES` locker, Escape handler gated on `Markers2Handler2.field14`. |
| 4 | `Gui20` | `BlogPostsBridge` | route **"blogPosts"**: single `@CallbackJS("open")` that queues a `NetworkIterator` CLICK entry (`MixinHelper25`) and opens the post URL through the WebOSR dispatcher (`Markers4.method11` → `BrowserUtils.method7`) with `Initiator.INITIATOR_BLOG`. |
| 5 | `GuiExtension2` | `BugReportBridge` | bug-report form data provider (`GuiExtension_2, Calculator2`, 0 refs): `provide()` emits `"title"` = *reportABug*, `"shortDescription"` = *shortDescriptionChars*, `"description"` and `"categories"` from `click.Bridge7Iterator$Type2` (MOD/GUI/COSMETIC/STORE/VOICE/LAUNCHER/OTHER, mirrored by `gui.BugReportCategory`); `getLanguagePath()` = `"gui.bug"`. |
| 6 | `Gui10.java` `Data2` | `PreviewCosmeticsPayload` | nested `Gui10$Data2`: Gson body of `@CallbackJS("previewCosmetics")` — `method19(Gui10.Data2,boolean)` — with `@SerializedName("active") Holograms[]` + `@SerializedName("preview") Holograms`, resolved through `CosmeticManager.method48` preview/compatibility data. (The sibling `Gui10.Data` seen-list payload stays a lazy placeholder; it is not a cluster row.) |

All six names were grepped tree-wide: 0 declarations and 0 `.java` stems.

## 3. Skipped rows (26) — stale/rescue twins of already-applied classes

Each placeholder file is the pre-move copy of a class already renamed by
`classes-markersgui.tsv` and moved to `driver/core/gui/mixin`; the rescue sweep
restored the placeholder file afterwards. Every pair has the **identical
`@CallbackJS` set** (verified programmatically) and the same route key in the
registries. The applier would refuse the rows (`new name already declared`).

| cluster row | already-applied name (driver twin) | live users of the placeholder | live users of the twin |
|---|---|---|---|
| `Gui2` | `StyngrBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui3` | `UiStateBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui4` | `SkyblockBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui5` | `OutfitBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui6` | `BadgeBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui7` | `ColorBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui8` | `LunarPlusBridge` | 0 (only `Markers4`) | 0 (only `DriverBridge`) |
| `Gui9` | `SettingsBridge` | 1 — `markers.mixin.Highlight3Iterator` reads `Gui9.field1` (editing keybind) | 0 |
| `Gui11` | `PromotionBridge` | 0 (only `Markers4`; also calls `Gui10.method18`) | 0 |
| `Gui14` | `HostedWorldBridge` | 0 (only `Markers4`) | 0 |
| `Gui15` | `LanguagePageBridge` | 0 (only `Markers4`) | 0 |
| `Gui16` | `CosmeticPreviewBridge` | 0 (only `Markers4`) | 0 |
| `Gui17` | `SavedSkinBridge` | 0 (only `Markers4`) | 0 |
| `Gui18` | `AlertBridge` | 0 (only `Markers4`) | 0 |
| `Gui19` | `AccountBridge` | 4 — `Client`, `click.lotusfish.mixin.Bridge7Iterator`, `fog.holograms.CosmeticPreviewManager`, `guiRewindhandlers.mixin.GuiRewindhandlers3` (all `Gui19.method2()`) | 1 — `framework.listener.mixin.ScreenInteractionHandler` (`AccountBridge.method2()`) |
| `Gui21` | `SocialMediaBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension3` | `ProfileBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension4` | `ServerDiscoveryBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension5` | `MetadataBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension6` | `NotificationBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension7` | `PlayerBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension8` | `ServerPingBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension9` | `HomeButtonBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension10` | `HomeRadioBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension11` | `HomeThemeBridge` | 0 (only `Markers4`) | 0 |
| `GuiExtension12` | `HomeNavigationBridge` | 0 (only `Markers4`) | 0 |

**Recommendation for the dedupe pass.** The live call sites (Markers4 →
`Highlight3Iterator`, plus the five files above) use the **placeholder
generation**, while the already-applied names sit on the newer `DriverBridge`
generation, which has **no users at all**. The same rule as `notes-54.md` §3 /
`classes-40.md` §2 applies: pick one survivor per pair, repoint the other side,
then rename the survivor to the applied name. In practice that means keeping the
markers side (repointing `AccountBridge` in `ScreenInteractionHandler` first)
and deleting the driver twin files, **except** that the driver package must not
be deleted wholesale — 71 files outside it import other driver classes
(`DriverRouteRegistry`, `DriverGuiExtension`, `JsonProvider`, `DriverDataProvider`,
`JsonSection`, `AccountBridge`, `ScreenshotUploadBridge`, the
`driver/core/gui/rewindhandlers` + `driver/gui` providers, ...).

## 4. Ready-to-copy rows for after the dedupe

These are the applied names, re-expressed against the surviving markers copies.
They will apply once the duplicate declarations in `driver/core/gui/mixin` are
gone (the applier skips rows whose target simple name is already declared):

```
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui2	StyngrBridge	WebOSR "styngr" route provider; @CallbackJS addJam/removeJam/previewJam/stopPreview preview a Styngr song through the sound handler
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui3	UiStateBridge	WebOSR "uiState" route provider; @CallbackJS setWindowState/setSortState/setQuickNavigationState forward to Client.method100()
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui4	SkyblockBridge	WebOSR "skyblock" route provider (no callbacks); method15() returns the SkyBlock mod-list screen (`method40().method82()`)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui5	OutfitBridge	WebOSR "outfits" route provider; @CallbackJS add/delete/setDefault/setFavorite/rename outfit presets
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui6	BadgeBridge	WebOSR "badges" route provider; @CallbackJS equip sends EquipBadgeRequest
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui7	ColorBridge	WebOSR "colors" route provider; @CallbackJS add/remove chroma entries (hue/brightness/saturation/opacity/hex)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui8	LunarPlusBridge	WebOSR "lunarPlus" route provider; @CallbackJS setColor sends UpdateLunarPlusColorRequest
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui9	SettingsBridge	WebOSR "settings" route provider; @CallbackJS setAdvancedMode/update/reset/invokeAction/setEditingKeybind/stopEditingKeybind drive the option tree (extends Markers5)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui11	PromotionBridge	WebOSR "promotion" route provider; @CallbackJS playPromotion/refresh/openMedalPromotion/claimReward (ClaimPendingRewardRequest; equips the cosmetic via CosmeticsBridge.method18)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui14	HostedWorldBridge	WebOSR "hostedWorlds" route provider; @CallbackJS inviteFriend/kick/promote/demote/onModalCallback + hosted-world Type enum
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui15	LanguagePageBridge	WebOSR "language" route provider; @CallbackJS selectLanguage/setForceUnicode
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui16	CosmeticPreviewBridge	WebOSR "cosmeticPreview" route provider; @CallbackJS exitPreview/openStore/openPreviewModal/checkout/joinWorld
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui17	SavedSkinBridge	WebOSR "savedSkins" route provider; @CallbackJS updateSkinType/setFavorite/apply/delete/add/rename/save skin presets
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui18	AlertBridge	WebOSR "alerts" route provider; @CallbackJS dismiss(index) clears a notification
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui19	AccountBridge	WebOSR "accounts" route provider; @CallbackJS addAccount/removeAccount/selectAccount + static method2() used by Client/Bridge7Iterator/CosmeticPreviewManager/GuiRewindhandlers3
com.moonsworth.lunar.client.markers.mixin.gui.mixin	Gui21	SocialMediaBridge	WebOSR "socialMedia" route provider; @CallbackJS openSocialMedia/unlinkSocial (UnlinkSocialRequest, BrowserUtils/ClipboardUtils)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension3	ProfileBridge	WebOSR "profiles" route provider; provide() delegates to Client.method61().provide()
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension4	ServerDiscoveryBridge	WebOSR "serverDiscovery" route provider; @CallbackJS discoverServers/searchServers/loadAutocompleteSuggestions/loadServerModal/joinServer/saveServer/trackSectionNotInterested (PromiseJS)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension5	MetadataBridge	WebOSR "metadata" provider; provide() emits lunarInfo (branch/fullGitHash/production/minecraftVersion/version/scale/uiBranch/uiGitHash/modern/legacy)
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension6	NotificationBridge	WebOSR "notification" provider; method128() delegates to Client.method69().method128()
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension7	PlayerBridge	WebOSR "player" provider; method128() emits local-player x/y/z/world + dimension/dimensionKey
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension8	ServerPingBridge	WebOSR "serverPing" route provider; @CallbackJS ping(address) resolves the server-list entry and emits serverPing:result
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension9	HomeButtonBridge	WebOSR "homeButtons" provider; builds singleplayer/multiplayer/discover/store buttons (Gui2Task) with MarkersType icons
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension10	HomeRadioBridge	WebOSR "homeRadio" route provider; @CallbackJS mute/setVolume/close + music-player event handlers
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension11	HomeThemeBridge	WebOSR "homeTheme" route provider; @CallbackJS selectTheme/selectDefaultTheme/playSound/openWrapped/openAdvent/setBalloonHighscore
com.moonsworth.lunar.client.markers.mixin.gui.mixin	GuiExtension12	HomeNavigationBridge	WebOSR "homeNavigation" provider; builds btnMenu/btnLocker/btnSatellite/btnXboxFriends/btnOptions/btnLanguage/btnRealms/btnRewindEditor/btnReplayViewer/btnFlashback/btnFabricModMenu
```

## 5. Caveats / follow-ups

* **Parent-package twins (not in this cluster) have the same problem.** The
  `markers/mixin/gui` support classes are also duplicated by driver-side
  classes: `Gui`↔`driver/core/gui/DriverDataProvider`, `Gui2`↔`JsonProvider`,
  `Gui3`↔`JsonSetSerializer`, `Gui2Task`↔`ButtonProvider`,
  `GuiExtension`↔`ButtonProviderGui`, `Annotation`↔`JsonSection` (all renamed
  from the same sources; the `driver/core/gui/JsonSetSerializer` still imports
  the markers `GuiIterator`, which has **no** driver twin). They need the same
  keep-one-survivor decision before the ready-to-copy rows in §4 can apply.
* **Both generations are referenced for accounts.** `Gui19.method2()` has 4
  live callers and `AccountBridge.method2()` has 1, so that pair is a true
  merge; the other 25 pairs only need the dead driver copies dropped.
* **The four route providers were never ported in the newer build.** The
  newer `DriverBridge` still registers `Gui10` (cosmetics), `Gui12` (sprays),
  `Gui13` (emotes) and `Gui20` (blogPosts) by their old markers names, so these
  four are genuinely single-copy and are renamed here (§2 rows 1-4). If a newer
  driver-side copy is ever rescued from a fresh jar, prefer the `*Bridge` names
  chosen here for it and drop the markers copies instead.
* **`GuiExtension2` is dead** (no import anywhere) and is renamed purely so the
  package has no placeholder left; if the dedupe pass deletes it, nothing
  breaks.
* Naming was kept to the established conventions: route key + `Bridge` (matches
  the applied `classes-markersgui.tsv` and `ShaderDebugJsBridge`), nested DTO
  named after its `@CallbackJS` payload (`PreviewCosmeticsPayload`).
* No package moves (wave-5 rule): all rows stay in
  `com.moonsworth.lunar.client.markers.mixin.gui.mixin`.

## 6. Evidence sources

* class bodies of all 32 cluster files + `markers/Markers4`, `driver/DriverBridge`,
  `driver/core/gui/*`, `markers/mixin/Highlight3Iterator`,
  `click/chest/Chest`, `audio/music/StyngrSong`, `gui/BugReportCategory`;
* prior maps/notes: `tools/renames/classes-markersgui.{tsv,md}`,
  `tools/renames/wave5/{classes-42.tsv,notes-54.md,notes-51.md}` (dedupe
  convention), `APPLIED.md`;
* git history: `c446f88c1` (markersgui renames), `8902f000b` (markers→driver
  move), `b384aca20` (rescue restore of the placeholder copies);
* `tools/mappings-snapshot/restructure/remaining-renames.tsv` (obf → current
  names) and a programmatic `@CallbackJS` set comparison for all 26 twin pairs.
