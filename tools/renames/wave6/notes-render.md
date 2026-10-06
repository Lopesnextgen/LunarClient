# Wave 6 — render subsystem notes (`client/render`, `lighting`, `glintcolorizer`, `animations`, `lightoverlay`)

Deliverable: `tools/renames/wave6/moves-render.tsv` (295 rows: **144 real moves, 151
duplicates that apply_class_moves will SKIP**). Map applied with:

    tools/apply_class_moves.py --map tools/renames/wave6/moves-render.tsv --apply

Result summary (per audited package):

| source | files | real moves | stale dupes (SKIP) | destination |
|---|---|---|---|---|
| `client/render` (root, 19) | 19 | 19 | 0 | `render/pipeline` (18) + `cosmetics` (`EntityRenderLayer`) |
| `client/render/particle/**` | 36 non-root | 36 | 0 | flat `render/particle` (35) + `render/texture/GifTexture` |
| `client/lighting/**` | 106 | 82 | 24 | `config/option` |
| `client/lightoverlay/**` | 18 | 5 | 13 | `config/option/trait` |
| `client/glintcolorizer/**` | 108 | 1 | 107 | `render/particle` |
| `client/animations/**` | 8 | 1 | 7 | `gui/prompt` |

Evidence used: (a) `git log --name-status --diff-filter=R` rename chains for every
class (which class was already moved/renamed and to what), (b) the wave 2/5 ledgers
(`moves-misc.tsv`, `classes-51.tsv`, `classes-markersgui.tsv`, `pkgs-buckets{A,B}.tsv`,
`APPLIED.md`), (c) content comparison (declaration + method sets + body diff) against
the candidate twin, (d) reference counts tree-wide.

---

## 1. `client/render` root → `render/pipeline/`

The 19 root classes were all moved here by `moves-namesA` ("name the util.* junk
drawers": they came from `client/util/click/**`, which is now a separate stale-duplicate
problem owned by the util subagent). The whole set is the deferred-render pipeline plus
GPU state helpers, so they move one level down into a named `render/pipeline` package:
`RenderPipeline`, `PipelinePass`, `RenderStage`, `RenderStageException`, `RenderSubmission`,
`RenderSubmissionManager` (collect-on-EventRenderTick + per-stage draw), `PoseMatrixProvider`,
`RenderContextLegacyTransform` / `RenderContextModernTransform` (1.8 / 1.9+ pose paths),
`StencilEmulator`, `ThreadedPresentSupport`, `GpuResourceTracker`, `GlProgramCache`,
`ByteBufferCache`, `FloatArrayBuilder`, `RenderCache`, `RenderCacheProvider`,
`SkinRenderCache` (per-instance render/skin caches, ducked as `lunar$getCache()`).

The only render-root class that is not pipeline infrastructure is
**`EntityRenderLayer` → `client/cosmetics`**: it is the player layer registry with
`HOLOGRAMS`/`COSMETICS`/`EMOTES`/`CAPES`/`COMPANIONS`/`SELF_DUMMY` instances (used by the
cosmetic/emote/hologram renderers, no other callers in source).

Kept as-is (already in the right named subpackage, no rows): `render/color` (6:
AnimatedColor/ColorAnimation/ColorMutator/MutableColor/SolidColor — render colour model,
`ColorAnimation` only *implements* the option enum interface), `render/font` (3),
`render/shader` (5), `render/texture` (6). `render/pipeline` is new (18 classes).

## 2. `client/render/particle/**` — dissolve the obfuscator subpackages, one texture leftover

`render/particle` (107 files) is the vendored Blockbuster/Bedrock particle engine. Its
subpackage names (`nameplate`, `holograms`, `highlight`, `rewindhandlers`, `mixin`) are
obfuscator dictionary labels (PLAN §"No junk-drawer package names"); they carry no
meaning — e.g. `particle/mixin/MolangParser` and `CosDegrees` are Molang built-ins, not
mixins, and `particle/holograms` = the spawn-rate components. All 35 non-root classes
move up into flat `render/particle/`; no simple-name collisions (verified).

`particle/nameplate/GifTexture` is **not** particle code: an animated GIF texture
(`Bridge8Extension3`, frame delays, `TextureManagerBridge` lookup) → `render/texture`
(the "client/texture-like leftovers" item in the brief).

## 3. `client/lighting/**` = options/settings framework → `config/option/`

82 genuine moves (move-only except two renames): `RewindhandlersType → SettingsPage`
(settings-page enum; name aligned with the sibling wave6 misc map, which keeps
`Calculator2Type` move-only), and `nameplate/Alert2Handler → OptionAlertHandler`
(the only un-duplicated class in the junk `nameplate` subpackage).

**Overlap:** the sibling subagent's `tools/renames/wave6/moves-misc.tsv` already contains
81 of these rows (same old→new) — my map covers them as well so it is self-contained;
applying both is safe (the second run SKIPs rows whose file has already moved). Do not
double-rename: I aligned `Calculator2Type`/`RewindhandlersType` with that map.

**24 stale rescue duplicates (rows SKIP — see §6):** the canonical copies already exist in
`config/option`. 22 of the 23 `lighting/nameplate/**` files are second-generation copies:
`Nameplate→OptionTraits`, `Nameplate2→OptionUpdateListeners`, `Nameplate2Task→ListenerSet`,
`Nameplate3→OptionFeatureLink`, `Nameplate3Handler→FeatureLinkRef`,
`Nameplate4→OptionDisplay`, `Nameplate4Task→DisplaySpec`, `GuiExtension→OptionDataProvider`,
`GuiExtension2→OptionJsonProvider`, `AlertExtension→AlertExtension`,
`AlertExtension2→OptionChildren`, `ThreadModuleDump43Extension*→SettingsSection*`,
`mixin/Nameplate→NumberRule`, `mixin/NameplateHandler→AbstractNumberRule`,
`mixin/NameplateImpl→DoubleNumberRule`, `mixin/NameplateImpl{2..6}→{Float,Long,Short,Integer,Byte}NumberRange`;
plus `mixin/LightingExtension44→CrosshairDrawOption` and `OptionSection→OptionCategory`
(identical enum, no rename record — found by content). The 23rd nameplate file is
`Alert2Handler`, moved above.

**Second-generation twins (23 more) — move, then merge:** these are the same classes as
existing `config/option` ones under different names; both generations are internally
consistent and live, so `apply_class_moves` moves them under their own names and the
merge is a follow-up refactor (repoint + delete):

| `lighting` (moved) | `config/option` twin | note |
|---|---|---|
| `OptionHierarchyNode` | `OptionTreeNode` | identical interface |
| `OptionTreeMapper` | `SettingsTreeMapper` | identical mapper defaults |
| `OptionTreePruner` | `PruningOptionBaker` | identical abstract pruner |
| `OptionBuilderBase` | `AbstractOptionBuilder` | identical abstract builder |
| `FeatureOptionBaker` | `OptionBaker` | identical baker |
| `DevelopmentOptionBaker` | `DevOptionBaker` | identical dev baker |
| `SettingsTreeAssembler` | `SettingsTreeBuilder` | identical assembler |
| `RootSettingsAssembler` | `RootSettingsBuilder` | identical root assembler |
| `DefaultValueBuilder` | `DefaultedOptionBuilder` | identical builder base |
| `OptionGraphNode` | `SettingsNode` | identical graph node |
| `OptionConfigException` | `OptionException` | identical exception |
| `DefaultedBoolean` | `BooleanOption` | identical enum |
| `AdvancedOptionFlag` | `OptionFlag` | lighting copy has a decompile bug (duplicate `ADVANCED`) |
| `OverrideTriState` | `TriState` | same tri-state enum |
| `JsonPersistable` | `JsonConfigurable` | identical persist interface |
| `NumberRangeConfigurator` / `SteppedNumberRangeConfigurator` | `NumberRangeBuilder` / `SteppedNumberRangeBuilder` | identical range interfaces |
| `OptionSupplier` | `OptionProvider` | same supplier interface (`OptionSupplier.method2` static) |
| `OptionBakerProvider` | `OptionBakerFactory` | same factory |
| `SettingsGroupCreator` / `SettingsParentCreator` | `SettingsGroupFactory` / `SettingsParentFactory` | same functional interfaces |
| `OptionRegistrant` | `SettingsRegistrant` | same registrant |
| `SettingsComposer` | `SettingsBuilder` | identical composer/builder interface |

Not twins (keep both): `ClientOption`/`ToggleOption`/`OptionFactory` + the `*Option` types
and builder chain are the live option API (322/368/219 external imports), plus
`SettingsParent`, `SettingsTreeContainer` (imported by `config/option/SettingsTreeBuilder`),
`CategorizedSettingsBuilder`, the settings-composer helpers, `Calculator2Type`,
`KeyCombo` and `AutoTextHotkeyOption`. `OptionSection`/`JsonPersistable` are handled
above (duplicate / twin).

## 4. `client/lightoverlay/**` = the *trait* registry of the options framework → `config/option/trait`

Not the light-overlay feature (that is `mod/render/LightOverlay` + `framework/feature/lightoverlay`).
Bucket batch A already moved this package to `client/option/trait` (now `config/option/trait`),
so 13 of 18 files are stale duplicates (rows SKIP): `Lightoverlay→Lightoverlay`,
`Lightoverlay2→TraitReader`, `Lightoverlay2Extension→TraitHost`,
`Lightoverlay3Extension→MutableTraitHost`, `Lightoverlay4→TraitSnapshot`,
`Lightoverlay5→TraitListener`, `Lightoverlay7→TraitDebugFormatter`, `Lightoverlay8→Trait`,
`Lightoverlay9→TraitType`, `Lightoverlay9Impl→BuilderTraitType`,
`Exception/Handler/Type→LightoverlayException/Handler/Type`.

The other **5 have no canonical copy and are required by `config/option/trait` itself**
(`MutableTraitHost`, `TraitHost`, `TraitDebugFormatter`, `ConditionalOption`,
`OptionCombiner`, `OptionTraits`, `framework.ModTraits`, `framework.AbstractFeature`,
`framework.Framework` already import these old FQNs) — they move for real and get names
consistent with the canonical trait vocabulary:
`Lightoverlay2Extension2→TraitContainer`, `Lightoverlay3→TraitMutator`,
`Lightoverlay3Extension2→MutableTraitContainer`, `Lightoverlay3Extension22→DebugTraitContainer`,
`Lightoverlay6→TraitRegistry`. After that `config/option/trait` is self-contained; the 13
duplicate files can be deleted (references above then resolve to the canonical names).

## 5. `client/glintcolorizer/**` and `client/animations/**` are not what their name says

* `client/glintcolorizer` (108 files) is a **rescue resurrection of the vendored
  Blockbuster/McLib particle engine** that already lives, renamed, in `render/particle`
  (the glint colouriser itself is `mod/render/GlintColorizer`, which never touches this
  package). `c446f88c` renamed `glintcolorizer/Glintcolorizer2 → BedrockLibrary` and
  `811b292d4` moved it to `render/particle`; rescue batch 5 then re-added the pre-rename
  package. 107 rows are therefore duplicates (rows SKIP) mapping each old class to its
  canonical `render/particle` name (the mapping was taken from git rename history; the
  5 with no chain record were matched 1:1 by content:
  `Glintcolorizer2Impl_2→BedrockComponentAppearanceLighting`,
  `GlintcolorizerExtension→IComponentEmitterInitialize`,
  `holograms/Glintcolorizer2Base→holograms/BedrockComponentRate`,
  `mixin/GlintcolorizerException→mixin/MolangException`,
  `rewindhandlers/Glintcolorizer2Base→rewindhandlers/BedrockComponentMotion`).
  The one class with **no** `render/particle` twin is
  `nameplate/mixin/Glintcolorizer5Iterator` (a `BedrockEmitter` implementing
  `holograms.Holograms8`; 10 source reference sites incl. `cosmetics/holograms/MorphRenderer`,
  `holograms/Holograms8`, `markers/holograms/HologramsIterator2`) → real move to
  `render/particle/HologramParticleEmitter`.

* `client/animations` (8 files) is the **chat/safety prompt-button model**, not animations:
  `Animations→ActivePrompt`, `mixin/Animations→PromptAction`, `Impl→RunCommandPrompt`,
  `Impl2→BlockedActionPrompt`, `Impl3→OpenUrlPrompt`, `Impl5→MaliciousUrlPrompt`,
  `Impl6→MaliciousServerPrompt` are duplicates of the canonical `client/gui/prompt`
  package (7 files, created from the earlier `client/prompt` package). `Impl4` (runs the
  server "yes"/"no" command and remembers the server IP; `rememberChoice`) has no twin →
  real move to `gui/prompt/RememberServerPrompt`.

## 6. Required follow-up: dedupe the 151 SKIP files

`apply_class_moves` skips any row whose target is already declared, so the duplicate files
stay in place. To finish the restructure, for each group: rewrite the listed references to
the canonical FQN (plain token rewrite, the destinations exist), then `git rm` the files.

| group | files | canonical home | source reference sites |
|---|---|---|---|
| `glintcolorizer/**` (minus the emitter) | 107 | `render/particle` | 25 files / 6 classes (`fog/holograms/MorphManager`, `holograms/Holograms{6,8,8Handler}`, `markers/holograms/HologramsIterator2`, `cosmetics/holograms/{MorphRenderer,EmoteAnimator}`, `render/particle/nameplate/mixin/Nameplate*`, `framework/feature/{chat/Chat5,potioneffects,titles,saturation}`, `fps/Fps7Extension6`, `util/ThreadModuleDump17`, `legacy/wrapper/AudioStreamLoader`, …) |
| `lighting/nameplate/**` + `lighting/mixin/LightingExtension44` + `lighting/OptionSection` | 24 | `config/option` | 102 files mention `client.lighting.nameplate.*` (options UI/engine: `click/holograms/*`, `fog/holograms/*`, `framework/feature/**`, `mod/**`); `OptionSection` has 7 users |
| `lightoverlay/**` (13) | 13 | `config/option/trait` | 19 files already import the old FQNs incl. `config/option/{OptionTraits,ConditionalOption,OptionCombiner}`, `config/option/trait/*`, `framework/{Framework,ModTraits,AbstractFeature,AlertExtension,Framework7Extension}`, `alert/AlertExtension` |
| `animations/**` (7) | 7 | `gui/prompt` | 4 files: `client/Client`, `client/itemcounter/Itemcounter`, `client/rewindhandlers/Rewindhandlers2`, `legacy/mixin/GuiYesNoMixin2` |

Notes/risks:
* Rows for classes with the same simple name in different sources are ordered so that
  every real move target is unique per destination (validator checked: 0 duplicate
  real-move targets, 0 missing old files).
* The 151 SKIP rows are intentionally kept in the ledger: they are the authoritative
  old→canonical name mapping for the dedupe pass.
* Renames/moves change FQNs referenced from `libs/lunar-renamed-classes.jar` bytecode;
  run `tools/error_diff.py` after applying, as with every wave. (The jar-side duplicates
  are exactly why the rescue re-created these packages in the first place.)
* `framework/ModTraits`/`OptionTraits` reference the `lightoverlay` FQNs at map-apply
  time; the 13 duplicate trait rows SKIP, so those references are exactly the §6 repoint
  list for `config/option/trait`.
