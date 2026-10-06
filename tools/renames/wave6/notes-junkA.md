# Wave 6 — junk-drawer audit A (`client/{fog,fov,click,fps,killsounds,lightoverlay,lotusfish,horsestats,holograms,nameplate,glintcolorizer}`)

Map: `moves-junkA.tsv` — **752 rows for 752 classes** (one row per file, incl. the
flattened `Outer$Inner` classes). Format `oldpkg Old newpkg New evidence`.

## Method

1. Chained *every* map under `tools/renames/*.tsv` (class renames, class moves and
   package moves) in commit order and replayed it on each scope FQN. 331 classes
   resolve to a canonical class that already exists in the tree — those rows carry
   the evidence of the map that produced the final hop and target the canonical FQN.
2. Everything else was read and placed by hand: superclass,
   `@Mixin`/annotation, field types, string literals and the 509 decompiled
   reference clients (`Documentation/references/mc-client-sources/sources`).

## The central finding: most of these packages are *stale rescue twins*

The whole scope is largely a second decompiled copy of code that already had a
renamed/moved twin before `rescue: batch 4-7` re-created the old FQNs from the
stale jar. **333 of 752 rows target a class that already exists** — they are
MERGE rows: the file should be deleted and its (few) users re-pointed at the
canonical class, not moved. `apply_class_moves.py` skips them by design
(“target already declared”), so a dedupe pass is still required. (Duplicate
`(newpkg, New)` pairs occur only among these merge rows — e.g. both
`click/fishing/Fishing` and `fog/holograms/fishing/Fishing` collapse onto
`framework.loading.LoadingStage`.) Examples:

| stale copy | canonical twin |
|---|---|
| `fog.holograms.{Fog,FogHandler,FogHandler2}` | `framework.{LoadableHandler,ItemSetHandler,ItemMapHandler}` |
| `fov.Fov3$Data5` | `chat.PlainTextContents` (etc.: all `fov` message models) |
| `fov.mixin.Fov2` | `cosmetics.emote.PhysicsPoint` |
| `holograms.Holograms2..8` | `cosmetics.holograms.IBoneRenderer/Morph*` |
| `killsounds.Killsounds*` (all impls) | `config.migration.*Migration` |
| `fps.Fps*` (all 45) | `cosmetics.molang.*` (Molang JIT twins) |
| `glintcolorizer.Glintcolorizer*` (102) | `render.particle.*` (Bedrock particle engine) |
| `horsestats.*` | `profile.*` + `profile.importer.{Badlion,Feather}*` |
| `nameplate.*` | `network.transfer.*` + `framework.build.LunarBuildData` |
| `lightoverlay.*` | `config.option.trait.*` |
| `fog.holograms.chest.Chest` | `audio.music.StyngrSong` |

For the one in-family duplicate target that does **not** yet exist the map uses
`FavoriteColorsConfigRescued` (variant name next to the canonical
`config.FavoriteColorsConfig`) for the `fog/holograms` twin of `fog.FogLoader`.

## What each junk package really holds

* `fog/**` — five unrelated subsystems:
  * `fog/holograms` root = **client managers/services** (Account/Badge/Blog/Border/
    Cosmetic/Emote/Outfit/SavedSkin/Server*/Spray/Streamer/Translation/Waypoint…),
    the **settings containers** (General/Performance/Controls/Internal/Mods/
    GlobalOptions/ServerFeatures + `FogLoader2` → `config.SettingsContainer`), and
    the **Turbo render-batching engine** (`TurboEngineManager`, `HologramEntityManager`,
    `Holograms*`, `Highlight3*`, path finder `mixin.Holograms4..7Impl`) → `client/render/turbo`.
  * `fog/holograms/nameplate` = **JIT asset download/cache** (`lunar-jit`,
    `textures.lunarclientcdn.com`, `.mcmeta`, `[LC JIT] Cache Size`) → `client/render/jit`.
  * `fog/holograms/colorsaturation` = **shader/post-effect system**
    (`lunar/shader-inject`, vsh/fsh, `LunarPostEffect`) → `client/render/shader`.
  * `fog/highlight` = **remote metadata consumers** (`metadata_fallback.json`,
    blogPosts/alert/modSettings/clientSettings/pinnedServers/featureFlag/links/…)
    → `client/framework/metadata`.
  * `fog/**` leftovers: `Fog`/`FogHandler`/… are already renamed (Loadable/Item*
    handlers); `fog/click` = texture/model updaters; `fog/colorsaturation` =
    social links (Twitter/Discord/…); `fog/chest` = Styngr songs + Lunar Jams;
    `fog/fishing` = skin upload + profile lookup; `fog/gui` = mod metadata,
    option overrides and Apollo mod-stub generation.
* `fov/**` — the **cosmetics subsystem**: cosmetic index (`mixin.Fov`), owned
  cosmetics, cloth-cloak physics (`mixin.Fov3/Fov3Task`), outfit/equip-condition
  trees, emote models, shader/Elytra/gecko compute pipelines, plus the
  conversation/message models and the **server-discovery card models**
  (`fov/nameplate` → `network.server.{ServerMapping,ServerAnnouncement,…}`).
* `click/**` — the **LCUI GUI system** (+ a few odds):
  * `click/holograms` = LCUI screen + widget toolkit (`Calculator2*`),
    `click/lotusfish` = HUD layout editor + mod menu, `click/holograms/nameplate`
    = main menu + seasonal themes, `click/gui` = dialogs, `click/colorsaturation`
    = minimap screen, `click/coordinates` = profile editor, `click/highlight` =
    cosmetics screen, `click/fishing` = loading screen, `click/chest` =
    notifications, `click/rewindhandlers` = text render caches.
  * `click.Alert/AlertTask` = framebuffer capture, `click.Nameplate` =
    reduced-debug-info notifier, `click.FogIterator` = LCUI screen stack.
* `fps/**` — Molang JIT; **all** classes are twins of `cosmetics.molang.*`
  (the two real ones, `MolangExprCompiler`/`MolangStmtCompiler`, are move-only
  into `cosmetics.molang` because the cosmetics code references them).
* `killsounds/**` — config migrations (canonical `config/migration`), including
  `mixin/Killsounds*` = the vanilla `options.txt` reader (`VanillaOptionsFile`).
* `lightoverlay/**` — the **options/settings trait framework**
  (`config.option.trait`), not a light overlay.
* `lotusfish/**` — the **Ichor class-transform/mixin framework**
  (`framework/transform`: LWJGL function provider, OmniMixin validation, Lunar→
  intermediary mapper, GL access transform).
* `horsestats/**` — Badlion/Feather profile import (`profile.importer`).
* `nameplate/**` — Apollo transfer/ping services + build-data parsing (network).
* `holograms/**` — emote/morph renderer models (cosmetics.holograms).
* `glintcolorizer/**` — the **Bedrock/Molang particle engine** (`render.particle`),
  not a glint colorizer; `glintcolorizer/nameplate` = hologram particle renderer.

## Caveats / follow-up

* **Merge rows**: 333 rows point at an existing FQN. Apply with a merge-aware
  step (delete source + rewrite refs), otherwise they are skipped.
* **Naming confidence**: `fog/holograms` managers, the trait/particle/migration/
  profile twin families and the click screen classes are verified from their
  bodies. The widget names inside `client/ui/widget` and `client/ui/menu`, the
  Turbo path-finder internals (`PathFinder/PathNode/…`) and the JIT class names
  (`JitResource*`) are read from superclass/fields/strings but were not
  individually exercised — treat them as medium confidence and adjust during
  the dedupe/merge pass.
* No `@Mixin` classes exist in any `mixin/**` subpackage of the scope; those
  directory names are decompiler artefacts. Rows therefore move each class
  individually to its real subsystem.
* New packages introduced by this map: `client/render/{turbo,jit}`,
  `client/ui/**` (root + `widget/menu/hud/mainmenu`), `client/framework/metadata`;
  `client/render/shader` and `client/framework/transform` are existing packages
  that grow. Everything else lands in existing packages.
