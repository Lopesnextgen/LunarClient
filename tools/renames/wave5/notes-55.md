# Cluster 55 — `com.moonsworth.lunar.client.fog.holograms` (top level)

**Status:** map written (43 rows). No sources edited.

## Summary

The package is not actually about fog or holograms: it is a grab-bag of Lunar
client services/stores that the earlier package-rename pass dumped under
`fog/holograms` (the same original package also owns `chest/`, `click/`,
`gui/`, `highlight/`, `nameplate/`, `rewindhandlers/`, `fishing/`,
`colorsaturation/`, `mixin*/`, `coordinates/`). The 43 top-level classes are:

| area | classes |
|---|---|
| streamer mode | `Holograms2` → `StreamerMode` |
| cosmetics family | `Holograms12` → `CosmeticManager`, `Holograms14` → `EmoteManager`, `Holograms16` → `SprayManager`, `Holograms17` → `SavedSkinManager`, `Holograms13` → `BadgeManager`, `FogHandler26` → `OutfitManager`, `FogHandler29` → `MorphManager`, `Holograms4` → `CosmeticPreviewManager`, `FogIterator_2` → `CosmeticCompatibility`, `FogImpl2` → `FontRegistry` |
| account / social | `Holograms11` → `AccountManager`, `FogHandler27` → `ConversationManager`, `FogHandler23` → `TeamMemberManager`, `FogIterator2` → `TabLogoManager`, `Holograms15` → `BlogPostManager` |
| server list/net | `FogHandler211` → `PinnedServerManager`, `FogHandler252` → `ServerDiscoveryManager`, `FogHandler254` → `ServerPinger`, `Holograms3` → `ServerIconManager` |
| settings files (JSON) | `FogLoader22` → `GeneralSettings` (`general.json`), `FogLoader24` → `PerformanceSettings` (`performance.json`), `FogLoader2` → `ControlsSettings` (`controls.json`), `FogLoader23` → `InternalSettings` (`internal.json`), `FogLoader25` → `GlobalOptionsSettings` (`global_options.json`), `FogLoader3` → `ModsSettings` (`mods.json`), `Holograms10` → `ServerFeaturesSettings` (`features.json`), `Holograms6` → `SettingsManager` (owns the scopes) |
| Apollo modules | `FogHandler25` → `BorderManager` (border), `FogHandler2` → `NametagOverrideStore` (nametag), `FogHandler253` → `HologramEntityManager` (hologram entities), `FogHandler259` → `EventChestRegistry` (AprilFools/anniversary chests) |
| misc | `FogHandler255` → `ProfanityFilter`, `FogHandler256` → `LiveExperienceManager`, `FogHandler257` → `PerformanceReporter`, `FogHandler210` → `AnalyticsCounters`, `FogHandler212` → `UiStateStore`, `FogHandler22` → `LinkRegistry`, `FogIterator_3` → `TurboEngineManager`, `Holograms5` → `WaypointManager` |

No mixin classes are in this cluster, so no `<Target><Purpose>Mixin` rows.

## Skipped rows

| cluster row | why |
|---|---|
| `FogHandler254.java` / `Data2` | Stale inventory row. `FogHandler254.java` currently declares `class FogHandler254` (mapped above); `Data2` survives only as the nested `FogHandler254.Data2` ping-entry class (nested-aware pass, not this wave). |
| `Holograms2.java` / `Data4` | Same: `Holograms2.java` declares `class Holograms2` (mapped above); `Data4` is the nested process-name list holder (`Holograms2.Data4`). |

## Shaded third-party libraries

None in this cluster — every row is `com.moonsworth.lunar.*` client code.
Third-party types appear only as dependencies (protobuf, kyori adventure,
Guava, gson, fastutil, jgrapht, joml, `mchorse.emoticons` from the Emoticons
mod, `com.eliotlash.molang`); no classes were skipped for this reason.

## Naming checks

* Every new simple name was grepped tree-wide for references/declarations and
  against `tools/renames/*.tsv` + `tools/renames/wave5/*.tsv` — no collisions.
* `LanguageManager` was rejected for `FogHandler28` because
  `net/minecraft/client/resources/LanguageManager.java` already exists in the
  tree; `TranslationManager` used instead.
* `ServerPinger` was accepted for `FogHandler254` because the vanilla 1.8.9
  class in this tree is `net/minecraft/client/network/OldServerPinger.java`
  and the existing `ServerPinger*Mixin` classes target
  `net.minecraft.client.network.ServerPinger$1` via string literals only.

## Ambiguities / judgement calls

* **`FogHandler2` → `NametagOverrideStore`.** The class itself is only a
  `UUID → List<Component>` ConcurrentHashMap cleared on the
  `highlight.mixin.gui.HighlightImpl10` world-unload event; the purpose comes
  from its only writer, `Highlight3Iterator17` (Apollo nametag module), which
  stores `OverrideNametagMessage.adventureJsonLines` through `Client.method58()`
  and reads them back as nametag render lines.
* **`FogHandler23` → `TeamMemberManager`.** It stores `memory.Memory` records
  (name/colour/location) and is driven by the Apollo team module
  (`UpdateTeamMembersMessage` / `TeamMember`), not by the friends list (which
  uses `util.memory.Memory` / `Client.method50()`).
* **`FogHandler22` → `LinkRegistry`.** Only observable use is
  `Client.method64().IORHHH…().getOrDefault("store"|"wrapped", …)` in the two
  home-screen bridges, so it is a named URL registry. The map getter still has
  an obfuscated member name in call sites (member-rename phase).
* **`FogHandler253` → `HologramEntityManager`.** Named for what it does (spawns
  no-physics fake entities with decrementing ids and binds them to
  `Holograms3_3` handlers) rather than for one consumer; it drives Companion
  cosmetics, the AprilFools chest hologram and Waila.
* **`FogHandler259` → `EventChestRegistry`.** Date-gated `chest.Chest` booleans
  (April 1 / April 6) pick `Chest2Handler` / `Chest2Iterator2`; both build on
  `FogHandler253`, which is why the two rows reference each other.
* **`FogIterator_3` → `TurboEngineManager`.** `FeatureFlag.TURBO_ENGINE` and
  `TURBO_ENTITIES`/`TURBO_BLOCK_ENTITIES` toggle it; the actual batched render
  engine is `HighlightTask`/`HighlightHandler` (`Highlight_2`), which the class
  owns. Not renamed `TurboEngine` to avoid clashing with the future engine
  class.
* **Revision mixing in the tree.** Two Lunar revisions are merged, so several
  external call sites are typed against the *other* copy (e.g.
  `Nameplate2Impl2` imports `com.moonsworth.lunar.client.FogHandler2` while
  calling `Client.method84()`). Evidence was taken from each class's own source
  plus named consumers, never from those mismatched call sites.
