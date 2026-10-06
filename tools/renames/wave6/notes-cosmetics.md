# Wave 6 — cosmetics / gui / chat / account / profile audit

Scope audited: `client/cosmetics/**` (178), `client/gui/**` (29), `client/chat/**` (22),
`client/translation/**` (8), `client/account/**` (8), `client/profile/**` (15),
`client/altmanager` leftovers (none in `client/`).

Map: `tools/renames/wave6/moves-cosmetics.tsv` — 131 moves / 14 exclusions (below).
Validation: `python3 tools/apply_class_moves.py --map tools/renames/wave6/moves-cosmetics.tsv`
dry-run reports **0 skips, 131 files moved, 228 files touched** (the single reported
"SKIP Old" is the header line, same as `wave5/moves-rootfinal.tsv`).

## Resulting layout

| target | from | n | reason / reference |
|---|---|---|---|
| `client/cosmetics/` (root) | emote `CosmeticType` | 1 | core cosmetic domain (`Cosmetic`, `CosmeticSlot`, `CosmeticCategory`, `CosmeticSettings`) stays; `CosmeticType` (HAT/CLOAK/BODYWEAR/WINGS/GECKOLIB) belongs with it |
| `client/cosmetics/gecko/` | emote (10) + inactive (42) | 52 | GeckoLib integration + Bedrock geo/animation engine. Evidence: `software.bernie.geckolib3.core.*` imports throughout; `GeckoRenderMode.RENDERED_GECKO_COMPUTE`; shaded `software/bernie/geckolib3/core/{builder,controller,easing,event,keyframe,manager,processor,snapshot,util}` in `lunar.jar` and `/tmp/opencode/reference/geckolib-core` |
| `client/cosmetics/emote/` | holograms (11) + inactive (2) + stays (17) | 30 | emote domain + BOBJ + McHorse/snowstorm morph display. Evidence: `mchorse.emoticons.skin_n_bones.api.bobj.*` (BOBJModel/LegacyBOBJMesh), `mchorse.emoticons.morph.*` (Morph*), `Emote`/`EmoteGift`/`RenderContext`; reference `Silent 2.2.1 .../client/emotes/{bobj,animation,ui}` |
| `client/cosmetics/molang/` | inactive (12) | 57 | Molang runtime/JIT + builtins on `com.eliotlash.molang`; shaded `com/eliotlash/molang/**` in `lunar.jar` |
| `client/cosmetics/skin/` | — (16 stay) | 16 | 3D skin layers (`SolidPixelWrapper` log tag `SkinLayers3D`), hat/overlay/skull layer renderers |
| `client/ui/` (+ `blog`, `external`, `notification`, `prompt`) | gui (29) | 29 | screens/HUD framework per PLAN; `Rise-6.9.5-main com/alan/clients/ui/**`, Badlion `client/gui/{mainmenu,slideout}` |
| `client/chat/translation/` | translation (8) | 8 | PLAN: `chat/` holds "chat messages/embeds, translation" |
| `client/config/profile/` (+ `importer`) | profile (15) | 15 | PLAN: `config/` holds "… mod profiles"; profiles are mods.json/general.json/controls.json + Badlion/Feather import |
| `client/util/math/` | emote `Direction2D` | 1 | generic direction enum imported by fishing/scrollabletooltips/SkyblockArrowAlign — not a cosmetic |

`client/chat/**` (22) and `client/account/**` (9) already sit on their target packages:
no rows (chat messages/embeds stay flat in the 30-class named subsystem; account/session/skin
stay `client/account[/skin]`). `com.moonsworth.lunar.altmanager` (7 classes) untouched as instructed.

## Why these boundaries

* **gecko vs emote.** GeckoLib's own layering (builder/controller/keyframe/processor) has no
  "emote" concept; the emote concept in this tree is `Emote`/`EmoteGift`/outfit conditions +
  BOBJ + morph. So the engine (compute shader `ModelVertexShader`, `MeshPassRunner`,
  `Transform`/`TransformStack`, `VertexSink`/`VertexBuilder`, `GlResources`, `ModelRenderConfig`,
  `RenderPass`, controller/event classes, animation parsers, all Bedrock**/Model**/Mesh**/
  Bone** geometry and loaders) goes to `gecko`, while `RenderContext*`,
  `RenderEntityHandle`, BOBJ, physics (`PhysicsPoint`/`DistanceConstraint`/`PositionHistory`),
  `EmoteModel`/`MolangResourceModel` and the `holograms` morph classes go to `emote`
  (`RenderContext` is the animation-selection input; `AnimationSelector` picks by context).
* **`holograms` is not a subsystem.** Its 11 classes are the emote-morph display path
  (`Morph*`, `Emote*Animator`, `EmoteDefinition`, `EmoteGiftProvider`, `IBoneRenderer`);
  dissolved into `emote`.
* **`inactive` is not a subsystem either.** It is the legacy playback half of the emote/gecko
  engine and got split by role (gecko/molang/emote). Note the parallel `client/inactive/**`
  tree (77 files, the modern/renamed generation: `FishingHandler2..`, `RewindhandlersN`, …)
  is out of this scope and still interops with these classes (`BedrockBone` imports
  `client.inactive.rewindhandlers.Rewindhandlers12`).
* **No `spray/` or `hat/` packages.** There are no spray classes in scope (spray lives in
  `client/fog/holograms/SprayManager`, `client/markers/.../SpraysBridge`, `client/util/chest`),
  and `HatLayerRenderer` is the only hat class — a 1-class package would violate the >=5 rule,
  so it stays in `skin` with the overlay/skull layer renderers it shares cubes with.

## Excluded — recommend DELETE, not move (14)

| class(es) | why |
|---|---|
| `cosmetics/inactive/mixin/fishing/{AbstractTask,AbstractTimedTask,AttachToOwnerTask,FishingHandler,FishingType,InactiveTask,LookAtBlockTask,LookAtOwnerTask,LookAtTargetTask,LookAtTask,MoveToOwnerTask,TeleportToOwnerTask}` | older duplicate generation of `client/inactive/mixin/fishing/*`; the live flow (`client/fog/holograms/Holograms3Handler`) instantiates `Fishing`/`FishingHandler2`, and nothing in src references this copy (their renamed twins `FishingHandler3/42/43/45/5/62` live in `client/inactive`) |
| `cosmetics/inactive/WanderPositionResolver` | only caller is the dead `FishingHandler` above |
| `cosmetics/inactive/mixin/InactiveException` | duplicate of `client/inactive/mixin/InactiveException` (the one imported by `Holograms3Handler`); no importer |

Also flag (not moved):
* `cosmetics/inactive/mixin/GeckolibCosmeticManager` and `MolangResourceProvider` are
  referenced by the moved files but are **not in src** — they resolve from the stale jar and
  are staged in `tools/rescue-quarantine/root-jackson/...`; rescue them with the cluster when
  the jar coupling is fixed.
* `BipedModelRenderer` and `EmoteModelRenderer` are byte-identical interfaces (name only);
  candidate to fold in the naming pass.
* `inactive/gui/GuiHandler` is a placeholder name (it is an animation-selection entry:
  `anim` + `transition_ticks`/`transition_easing`) — rename in the naming pass, not here.
* `gui/IllegalStateException` is an unreferenced empty `java.lang.IllegalStateException`
  subclass; moved to `client/ui` for now (shadowing risk in-package is nil today).

## Cross-map coordination (other wave6 maps, checked 2026-09-16)

No other map contains a row for a class owned by this one (0 duplicate source rows), so the
rows themselves are order-independent. Two neighbouring maps target packages this map empties
— retarget them before applying both:

* `moves-render.tsv` (lines 295-302) merges `client/animations/**` twins into
  **`client/gui/prompt`**. After this map, `gui/prompt` is gone (moved to `ui/prompt`), and the
  twin merge would recreate it with duplicate simple names. Point those rows at
  `client/ui/prompt` (adds `RememberServerPrompt`, merges the 7 twins).
* `moves-util.tsv` (lines 127-129) moves `TranslationKey`/`TranslationEntry`/
  `TranslationFormatter` into **`client/translation`**. This map moves the other 8 translation
  classes to `client/chat/translation`; point those three at the same package to keep one
  translation home.

Additions into packages this map targets are otherwise collision-free: `moves-misc` adds
`HorizontalAlignment` + `NotificationLink` to `ui[.notification]`, `RenderScope` +
`CosmeticRenderFilter` to `cosmetics`, `Badge` + `OwnedBadge` to `account`; `moves-util` adds
`CursorManager`/`GuiClipState`/`GuiResolution`/`MousePosition`/`Click2Base` to `ui`, 19 classes
to `util/math` (none named `Direction2D`); `moves-replay` adds `AbstractCosmetic`,
`CosmeticModelRenderer`, `CosmeticLayerExtension`, `DummyPlayer`, `ThreadModuleDump91`,
`EntityRenderLayer` to `cosmetics`.

## Apply order

1. `tools/apply_class_moves.py --map tools/renames/wave6/moves-cosmetics.tsv --apply`
2. `tools/error_diff.py` — expect no new failing files (2 known jar-coupling roots stay).
3. Delete the 14 excluded files (or move them to git history) before the `fog/holograms` /
   `inactive` batches, so the same simple names do not reappear in `cosmetics/gecko`.
