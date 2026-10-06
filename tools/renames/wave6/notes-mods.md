# Wave 6 — `client/mod/**` structure audit (notes)

Map: `tools/renames/wave6/moves-mods.tsv` (444 rows, all move-only, `New == Old`).
Scope: `client/mod/**` (436 files) + the mod leftovers outside it
(`client/keystrokes`, `client/itemcounter`, `client/calculator`,
`client/heightlimit`, `client/hitbox`, `client/fishing`, `client/inventorymod`;
`client/inventorysearch` no longer exists — already resolved into
`client/framework/feature/inventorymod/slot/inventorysearch`).

## Scheme

* `client/mod/<category>/<feature>/` — `<category>` ∈ combat | render |
  movement | player | hud | misc | skyblock; `<feature>` = lowercase
  concatenated mod name (matches the existing `framework/feature/<name>`
  dirs where one exists: `onesevenvisuals`, `autotextactions`,
  `hypixelbedwars`, `f3display`, …).
* **One folder per mod.** Child mods / helpers / data classes live in their
  parent's folder: `*Child`, `*ChildMod`, `*ChildHudMod`, `*Hud` variants,
  `*IndicatorChild`, nested `$Data` files.
* **Skyblock category created** (242 rows): every class constructed by the
  `Skyblock` umbrella feature (`client/mod/misc/Skyblock.java` keeps ~180
  `new X(this)` registrations) plus every `Skyblock*` class moves to
  `mod/skyblock/**`; the umbrella itself → `skyblock/core/`.
* Named subsystems allowed inside a category: `misc/debug` (28), `skyblock/debug`
  (20), `misc/rewind` (12), `skyblock/dungeonpuzzles`, `…/chocolatefactory`,
  `…/terminalsolvers`, `…/experimentsolvers`, `…/dungeonscorealert`,
  `…/hoppityegghud`, `…/kuudra`.
* Move-only: no class is renamed by this map; class naming stays as-is
  (naming pass is complete, structural pass must not touch names).

## How features were assigned (evidence per row)

1. `getId()` string — single strongest signal; also unifies stale/renamed
   pairs (`Coordinates`/`CoordinatesHud` → `coordinates`, `Minimap`/
   `MinimapMod` → `minimap`, `Waila`/`WailaHud` → `waila`).
2. Own constructor parameter type (`Ctor(Sibling ...)`) proves child mods:
   `KillSoundChildMod(KillSounds)`, `StopwatchChildMod(Stopwatch)`,
   `HypixelBedwars*(HypixelBedwars)`, `PvpInfo*` etc.
3. `new X(this)` sites in the umbrella features (`Skyblock`, `HypixelBedwars`,
   `ItemCustomizer`, `OneSevenVisuals`, `ParticleChanger`, `OverlayMod`,
   `TurboRenderingDebugMod`, `SkyblockDebugMod`, `SkyblockChocolateFactory`,
   `SkyblockTerminalSolvers`, `SkyblockExperimentSolvers`,
   `SkyblockDungeonPuzzles`, `SkyblockDungeonScoreAlert`, `SkyblockKuudra`,
   `SkyblockHoppityEggHud`).
4. Reference naming: `framework/feature/<feature>` package names (kept),
   Apollo module list (`/tmp/opencode/reference/Apollo/api/.../module/*`) for
   `cooldown`, `tntcountdown`, `heightlimit`, `nickhider`, `nametag`,
   `saturation`, `glint`, `stopwatch`, `waypoint`, `marker`, `team`, `title`,
   `staffmod`, `border`, `hologram`, `vignette` — appended to row evidence.

## Result

| category | rows | notable features |
|---|---|---|
| skyblock | 242 | dungeonpuzzles (11), chocolatefactory (10), terminalsolvers (8), debug (20), 1 per mod elsewhere |
| misc | 65 | debug (28), rewind (12), hypixelbedwars (8), chat, autotextactions |
| render | 58 | onesevenvisuals (5), itemcustomizer (4), crosshair/armorstatus/particlechanger (3) |
| hud | 41 | coordinates/ping/stopwatch/itemcounter/f3display/keystrokes/waila families |
| combat | 22 | knockbacktrainer (4), pvpinfo (4), killsounds/totemcounter (2) |
| player | 8 | inventorymod, inventorysearch, hotbarkeyoverlay, itemdropprotection, mumblelink, slotbinding, slotlocking, teamview |
| movement | 6 | togglesneak (2) |
| outside | 9 | see below |

Totals: 444 move rows, 295 feature folders (255 single-mod features), 0
destination-class collisions, 0 declaration mismatches
(`/tmp/opencode/mod-audit/validate.py` re-implements the applier checks).

Rows: 435 of the 436 `client/mod/**` files (the one exception is the
Vineflower stub below) + 7 `client/hitbox` + `keystrokes.KeystrokesType` +
`fishing.highlight.Highlight`.

## Leftovers moved into buckets

| class | destination | why |
|---|---|---|
| `client/hitbox/{Hitbox,Hitbox2,Hitbox2Handler{,2,3,4},Gui2Extension}` | `client.ui.hud.row` | not a mod: HUD row-layout engine (row iface + 4 measurers + LEFT/CENTER/RIGHT alignment enum), used by `framework.TypedHudRenderer`/`HudRowElement`. `row/` keeps it out of the way of the framework agent's `framework → client.ui.hud` moves (their `Gui2Extension` is the position enum) |
| `client.keystrokes.KeystrokesType` | `client.network.server` | live server-brand enum (`HYPIXEL("Hypixel BungeeCord")`, `TEST`), consumed by `network.server.ServerBrandWatcher`; its 4 package siblings are stale twins (below) |
| `client.fishing.highlight.Highlight` | `client.replay.modsettings` | ReplayMod settings-registry adapter (`SettingKeys*` mixins implement it; `getOption()`/`method1` ClientOption factory) — not a mod and not external-link |

## Leftovers NOT moved (stale twin generations / deferred)

`client/calculator` is the translation subsystem's old generation
(`client/translation` is the renamed home: `Calculator2`≈`Translatable`,
`CalculatorType`≈`ClientLanguage`, `CalculatorType2`≈`TranslationVariable`,
`mixin/Calculator*`≈`CachedReplacement`/replacement impls). Similarly
`client/inventorymod` is **crash-handling**, not inventory
(`client/framework/crash/*`), `client/itemcounter` is the safety service
(`client/network/safety/MaliciousListFetcher`), and `client/fishing` is the
external-link system (`client/gui/external/*`). Those files are referenced by
old-generation call sites that also have renamed twins, so a plain move would
leave two live copies — they need a repoint+delete pass, not a move.
`client/heightlimit/*` is jar-coupled (`PLAN` deferred rename
`HeightLimitOverride`, Apollo manager `method14`) and is left until the jar
remap.

| excluded file | twin / reason |
|---|---|
| `calculator.Calculator` | translation: `client.translation` generation (not a mod) |
| `calculator.Calculator2` | `translation.Translatable` |
| `calculator.CalculatorType` | `translation.ClientLanguage` |
| `calculator.CalculatorType2` | `translation.TranslationVariable` |
| `calculator.mixin.Calculator{,Handler,Handler2,Handler3}` | `translation.CachedReplacement` + replacement impls |
| `fishing.Fishing` | `gui.external.ExternalLinkRegistry` |
| `fishing.Fishing2` | `gui.external.ExternalLink` |
| `fishing.Fishing_2` | `gui.external.FancyMenuCompat` |
| `fishing.Nameplate` | `gui.external.ModMenuCompat` |
| `fishing.Fishing2Extension` (top, gui.mixin, highlight, holograms, mixin, rewindhandlers) | `gui.external` `ExternalLink`/`GuiExternalLink`/`RecordingExternalLink`/`HologramExternalLink`/`MainMenuExternalLink`/`TurboExternalLink` |
| `heightlimit.Heightlimit{,2}` | jar-coupled, keep until jar remap |
| `itemcounter.Itemcounter` | `network.safety.MaliciousListFetcher` |
| `inventorymod.{Inventorymod,Inventorymod2,Inventorymod3,InventorymodError,JsonSerializer}` | `framework.crash.{IchorStackTraceFilter,CrashReporter,ExceptionSanitizer,CrashReportError,ThrowableJsonSerializer}` |
| `keystrokes.{Highlight3Iterator,Keystrokes,Keystrokes2,Keystrokes3}` | `network.server.{ServerBrandWatcher,PluginChannelRegistry,PinnedServer,ServerIconEntry}` |
| `mod.player.EnchantmentSpriteTextDecoration` | Vineflower failure stub, no class in file |

## Duplicates that stay visible inside `client/mod` (same feature folder)

`Coordinates`/`CoordinatesHud` (both getId `COORDINATES`), `Waila`/`WailaHud`
(`WAILA`), `Minimap`/`MinimapMod` (`MINIMAP`), `Rewind`/`RewindMod` (`REWIND`)
and the 8 `REWIND_HANDLERS` classes. The move map coalesces each pair into one
feature folder; a later delete pass should keep the newer member (the one whose
imports use `ModTraits`/`ModChildren`/`event.*` naming).

## Caveats

* This map intentionally overlaps no sibling subsystem except three outside
  destinations: `client.ui.hud.row`, `client.network.server`, `client.replay.modsettings`
  (none of these packages currently declares the moved names; a cross-check of
  the five wave6 maps in this directory shows 0 shared `(package, class)` targets).
* `apply_class_moves.py` cannot delete; run it first, then the stale twins can
  be deleted with the twin table above.
* `Skyblock`-owned render classes (`DungeonWaypoints`, `BettermapPrimary/Secondary`,
  `StorageOverlay`, `TeamCakeHighlight`, …) were classified `skyblock` from the
  umbrella construction evidence even without a `Skyblock` name prefix.
