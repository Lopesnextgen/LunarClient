# Cluster 17 — `alert.excavation.mixin` + neighbours (44 classes)

Source revision: `tools/renames/cluster-17.txt` md5 `5a78514449527b07b442f66884309fc7`
(44 rows: 38 top-level types + the nested `Framework7Extension2.Data2`/`.Data3`,
`Highlight.Data6`/`.Data7`, `GuiHandler2.Data2` and `FogIterator.Data7`).
Map: `tools/renames/classes-excavation.tsv` md5 `8863a732281255508fad1ca212e7051c`.

## What this cluster actually is

The headline package is the Hypixel SkyBlock **Fossil Excavator** solver plus the
Diana **burrow** helper; the remaining 36 rows are the usual decompiler package
scramble (a grab-bag of unrelated helper classes that happened to sit in the
clusters' tree neighbourhood):

* **`alert.excavation`** — the Fossil Excavator minigame solver
  (`SkyblockFossilExcavationSolver`, `getId()` `SKYBLOCK_FOSSIL_EXCAVATION_SOLVER`).
  `ExcavationType2` is the enum of fossil grid shapes, `ExcavationType3` the
  per-slot `EMPTY/UNKNOWN/FOSSIL` cell state, with `Excavation`/`Excavation2`/
  `ExcavationIterator`/`ExcavationType` (rotation transforms) as neighbours.
* **`burrow`** — the Diana mythological-burrow locator
  (`SkyblockBurrowLocating`, `SkyblockDianaMobHud`). `BurrowType2` is the burrow
  kind (MOB/TREASURE/START), `BurrowType3` the warp-point list; `BurrowType`
  (mob list) is the neighbour.
* **GUI/HUD**: `impl.guiCore.Framework7Extension2` is the BetterMap dungeon-map
  HUD base (BettermapPrimary/Secondary); `pvpinfo.pvp.Framework7Extension2` is
  the PvP-info stat-line HUD base.
* **Replay/Rewind**: `rewindhandlers.highlight.Highlight2` (built-in music
  track), `Highlight_2` (project media exporter), `Rewindhandlers2` (audio
  waveform renderer), `RewindhandlersImpl2` (replay camera).
* **Feature helpers**: nickhider (`Nickhider2`/`Nickhider3`), screenshot
  (`Screenshot2`/`ScreenshotThread2`), scrollable tooltips
  (`ScrollabletooltipsHandler2/3`), WorldEditCUI (`Worldeditcui2`/`Worldeditcui_2`).
* **`glintcolorizer`** — actually the particle-effect engine
  (`ModGlintColorizer` / emote particles): `Highlight.Data6/Data7` are particle
  initial-velocity modes, `nameplate.Nameplate2` a particle-effect JSON loader,
  `Nameplate_2` a rotation-matrix helper.
* **`inactive`** — the GeckoLib/Bedrock cosmetic model system (`Gui2`/`Gui3`
  animation controllers, `GuiHandler2` conditional selector, `Gui2Impl2` item
  cosmetic definition, `Holograms2/3` animated-model base + state,
  `Nameplate2/3` animation cache + geometry loader, `FogIterator$Data7` a parsed
  molang function, `colorsaturation.Colorsaturation2/3` tick/vector converters).
* **`inventorymod`** — is **not** an inventory mod: it is Lunar's **Sentry crash
  reporter** (`Inventorymod2`) + exception sanitiser (`Inventorymod3`).
* **`itemphysics.mixin`** — is **not** item physics: the Gson model of
  `lunar:profanity/profanity_filter.json` (`regex` + `words`, normal/high).
* **`keystrokes`** — is **not** the keystroke HUD (that is
  `framework.feature.keystrokes`): `Keystrokes2` is a pinned server entry,
  `Keystrokes3` a server-icon entry.
* **`markers.holograms`** — `Holograms2` is the hovered-cosmetic model state and
  `Holograms_2` the marker-model renderer interface.

Real Lunar/Apollo mod names are not in `lunar-client-names.tsv` for these
internal classes, but the Apollo module ids confirm the feature identities:
`ModNickHider`, `ModScrollableTooltips`, `ModWorldeditCui`,
`ModGlintColorizer`, `ModItemPhysics`, `ModKeystrokes`, `ModColorSaturation`,
`ModScreenshot`, `ModPvpInfo`.

## Renames (44 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `ExcavationType2` | `FossilPattern` | fossil grid shapes SPINE/HELIX/…/UGLY with `boolean[][]` + getWidth/getHeight/matches |
| 2 | `ExcavationType3` | `FossilCellState` | 9x6 slot state EMPTY/UNKNOWN/FOSSIL; `from()` Dirt→UNKNOWN, "Fossil"→FOSSIL |
| 3 | `BurrowType2` | `BurrowKind` | Diana burrow kind MOB/TREASURE/START + `getColor(SkyblockBurrowLocating)` |
| 4 | `BurrowType3` | `BurrowWarp` | warp destinations SPAWN/MUSEUM/DARK_AUCTION/CASTLE/CRYPTS/STONKS/WIZARD |
| 5 | `Data2` (nested `Framework7Extension2`) | `BettermapHudComponent` | MixinCore9 element drawing the BetterMap map |
| 6 | `Framework7Extension2` (guiCore) | `BettermapHud` | BetterMap dungeon-map HUD base for BettermapPrimary/Secondary |
| 7 | `Nickhider2` | `NickHiderBridge` | `Optional<Nickhider2>` on Client; invalidate + transform, set by Apollo NickHider |
| 8 | `Nickhider3` | `NicknameTransformerCache` | abstract `LoadingCache<F,T>` → `NickhiderImpl` |
| 9 | `Data3` (nested `Framework7Extension2`) | `PvpInfoStatComponent` | MixinCore9Base2 18x18 stat line |
| 10 | `Framework7Extension2` (pvpinfo) | `PvpInfoStatHud` | PvP-info stat HUD options (heading/stat/number/card colours, alignment, …) |
| 11 | `Highlight2` | `MusicTrack` | built-in rewind music: name + SHA-1 hash + icon (`sparkling`/`sword`/…) |
| 12 | `Highlight_2` | `MediaExporter` | writes the project "media"/"provided" JSON arrays |
| 13 | `Rewindhandlers2` | `AudioWaveformRenderer` | waveform PNG cache, `lunar-waveform-thread` |
| 14 | `RewindhandlersImpl2` | `CameraRewindHandler` | replay camera (render view, yaw/pitch, third-person) |
| 15 | `Screenshot2` | `ScreenshotCapture` | framebuffer → pixels → `ScreenshotThread` |
| 16 | `ScreenshotThread2` | `ScreenshotShareThread` | copy/upload/tweet the screenshot |
| 17 | `ScrollabletooltipsHandler2` | `ScaleTooltipScroll` | applies while the scale AnimatedValue animates |
| 18 | `ScrollabletooltipsHandler3` | `BasicTooltipScroll` | applies before first scroll / when free-scroll off |
| 19 | `Worldeditcui2` | `WorldeditSelection` | WorldEditCUI selection shape interface |
| 20 | `Worldeditcui_2` | `WorldeditSelectionVersion` | long protocol attribute (set from `p` message field 5) |
| 21 | `Data6` (nested `Highlight`) | `FixedVelocity` | particle initial velocity from a fixed (x,y,z) |
| 22 | `Data7` (nested `Highlight`) | `RadialVelocity` | radial particle velocity, "inwards"/"outwards" |
| 23 | `Nameplate2` (glintcolorizer) | `ParticleEffectLoader` | loads a particle-effect JSON → `Glintcolorizer3_2` |
| 24 | `Nameplate_2` (glintcolorizer) | `ParticleMath` | rotation Matrix3f → direction vector (vortex) |
| 25 | `Data2` (nested `GuiHandler2`) | `ConditionalAnimationState` | `GuiHandler` with a required molang condition |
| 26 | `GuiHandler2` (inactive) | `ConditionalAnimationSelector` | returns first state whose condition == 1.0 |
| 27 | `Data7` (nested `FogIterator`) | `MolangFunction` | parsed molang function: id, body, arguments |
| 28 | `Gui2Impl2` (inactive/mixin) | `ItemCosmeticDefinition` | state_machine/attached_bone/item_transformation/… |
| 29 | `Colorsaturation2` (inactive) | `TickConverter` | seconds↔ticks (×20) for keyframes |
| 30 | `Colorsaturation3` (inactive) | `VectorConverter` | double[]/float[]/Vector3d → Vector3d/Vector3f |
| 31 | `Gui2` (inactive/mixin/gui) | `AnimationControllerDefinition` | type-key → impl registry, registers controllers |
| 32 | `Gui3` (inactive/mixin/gui) | `AnimationStateConfig` | id/condition/tasks/animation/cooldown state |
| 33 | `Holograms2` (inactive) | `BedrockAnimatedModel` | GeckoLib animated-model base |
| 34 | `Holograms3` (inactive) | `ModelRenderState` | unused holder: 2 flags + 2 floats |
| 35 | `Nameplate2` (inactive) | `AnimationCache` | `HashMap<String, Animation>` |
| 36 | `Nameplate3` (inactive) | `ModelGeometryLoader` | geometry JSON loader + vertex footprint |
| 37 | `Inventorymod2` | `CrashReporter` | Sentry bootstrap + `method5(Throwable,String)` error reporter |
| 38 | `Inventorymod3` | `ExceptionSanitizer` | strips obfuscated names, rewrites mixin frames |
| 39 | `Itemphysics2` | `ProfanityFilterConfig` | `profanity_filter.json`: `regex` + `words` |
| 40 | `Itemphysics3` | `ProfanityWordLists` | `normal`/`high` word lists |
| 41 | `Keystrokes2` | `PinnedServer` | pinned server entry (name/ip/expiry/flags) |
| 42 | `Keystrokes3` | `ServerIconEntry` | domain glob + icon resource for the server list |
| 43 | `Holograms2` (markers) | `HoverModel` | hovered-cosmetic state, sends `model:hover` |
| 44 | `Holograms_2` (markers) | `MarkerModelRenderer` | init/render interface for marker models |

## Applier dry run, and collision rows

`python3 tools/apply_class_renames_aware.py --map tools/renames/classes-excavation.tsv`:

```
[aware-renames] 44 rows (6 nested); 6594 java files
[aware-renames] rows=44 skipped=0 files_touched=153 files_renamed=38 mode=dry-run
```

All 44 rows apply with **zero skips** because the import-aware applier resolves
the colliding simple names by their package/imports. The collisions that the
older v1 applier would skip are:

| old | also declared in |
|---|---|
| `Framework7Extension2` | armorstatus, chest/dungeon/practice, keystrokes/mixin, ultrasequencer, `framework` base (15 hits each in guiCore/pvpinfo are the `Owner.this` self-references inside their own files) |
| `Gui2`, `Gui3` | `markers.mixin.gui`, `inactive.mixin.gui.mixin`, fishing/gui, … |
| `Holograms2`, `Holograms3` | `fog.holograms`, `markers.mixin.holograms.mixin`, `click.holograms`, … |
| `Nameplate2` | `armorstatus.nameplate`, `glintcolorizer`, `inactive.mixin.nameplate`, `fog.holograms.nameplate`, … |
| `Colorsaturation2/3` | `inactive.mixin.colorsaturation.mixin`, `fog.holograms.colorsaturation`, … |
| `Highlight2`, `Highlight_2` | `rewind.highlight`, `inactive.mixin.highlight`, `markers.mixin.holograms`, … |
| `Keystrokes2/3` | `framework.feature.keystrokes` (already renamed to `KeystrokeTimer`/`KeystrokeRenderer` in `classes-keystrokes`) |
| `Itemphysics2/3`, `Inventorymod2/3`, `GuiHandler2`, `Rewindhandlers2` | one-off same-name siblings in other packages |

The 6 nested rows (`Data2`, `Data3`, `Data6`, `Data7`, `Data2`, `Data7`) carry
the owner in the evidence (`Owner$Old`) and the owning file in the 5th column,
matching the format used by `classes-keystrokes.tsv` and `classes-fov.tsv`.

## Caveats / follow-ups

* Two deliberately vague rows are data holders that are **unused in the current
  tree** (their only callers are quarantined): `inactive.mixin.holograms.mixin.Holograms3`
  (`ModelRenderState`) and `inactive.mixin.nameplate.Nameplate2` (`AnimationCache`).
  Rename them freely if a later restore shows a more specific role.
* `WorldeditSelectionVersion` (`Worldeditcui_2`) names the long protocol field 5
  of the `p|…` message; it is only written, never read in-tree
  (`Worldeditcui2Base2.method2(long)`). If the WorldEditCUI protocol reference
  shows it is a selection id, prefer `WorldeditSelectionId`.
* `ScrollabletooltipsHandler2/3` are two of three strategies (the third,
  `mixin.ScrollabletooltipsHandler`, is a neighbour not in this cluster). The
  names reflect their predicates (`scale.isAnimating()` vs
  `!scrolled || !freeScroll`); `ScrollableTooltips` is the owning mod.
* `Nickhider2` is the Apollo `NickHiderModule` bridge installed on the Client
  (`Client.method111(Optional<Nickhider2>)`); it is named after its role in this
  tree, not after the Apollo module.
* Do **not** merge `Inventorymod2` with the `inventorymod.Inventorymod`
  neighbour or the `framework.feature.inventorymod` package — they are unrelated.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-excavation.tsv
# → rows=44 skipped=0 files_touched=153 files_renamed=38 mode=dry-run
python3 tools/apply_class_renames.py --map tools/renames/classes-excavation.tsv
# v1 applier would skip the ~20 colliding old names; use the aware applier.
```
