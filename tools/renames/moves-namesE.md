# Cluster E — mass-named leftovers (77 rows)

Map: `tools/renames/moves-namesE.tsv` (77 rows, rename-only: `newpkg == oldpkg`).

Scope: the mass-renamed (`Glintcolorizer*`, `Fishing`, `Colorsaturation*`, `Coordinates`,
`Chest`, `Click`, `Waila`, `Minimap`, `Rewind`, `MixinHelper`, `Annotation`,
`Bridge7Iterator`, `JsonDeserializerImpl`) leftovers assigned to this cluster. Every row
was resolved from the class body, the registration site (Gson/component/driver-route
registry) or the upstream library source; all `new` simple names are unique across
`src/main/java` (checked with `tools/renames/namecheck.py`-style tree grep).

The tree moved under this cluster while it was being analysed: the concurrent `namesA`
wave already committed `util/chest -> util/raytrace` (`Ray`, `RaySegment`, `Raycaster`,
`RaycastContext`, `DynamicRay`), `util/click/Click -> render/PipelinePass`,
`util/colorsaturation/Colorsaturation -> cosmetics/emote/BOBJMesh`,
`util/mixin/MixinHelper -> util/collection/ElementAdder`, and `util/Coordinates`’ old
neighbours. Those files are gone from the tree, so they are **not** in this map; the
remaining `Chest`/`Click`/`Colorsaturation`/`MixinHelper` rows below are the files that
still exist at the old names.

## Verification

```
python3 tools/apply_class_moves.py --map tools/renames/moves-namesE.tsv
# -> [class-moves] rows=77 skipped=0 files_moved=77 files_touched=277 mode=dry-run
# (applied by the coordinator afterwards: mode=APPLY, same rows/skipped/files counts)
```

The applied coordinator build adjusted one row: `nameplate.Glintcolorizer2Base44` became
`RandomFunction` (not `Random`) because the declaring file also uses `java.util.Random`;
the follow-up is recorded in `tools/renames/classes-randomfix.tsv`. This map has been
updated to the final name.

## Vendored McLib/Blockbuster Bedrock particle engine (`render/particle/**`)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Glintcolorizer2Base` | `BedrockComponentAppearanceBillboard` | `com.moonsworth.lunar.client.render.particle` | Upstream Blockbuster Bedrock component: size/facing_camera_mode/uv+flipbook, calculateUVs w/h; registered by JsonDeserializerIterator under minecraft:particle_appearance_billboard |
| 2 | `Glintcolorizer2Base2` | `BedrockComponentAppearanceTinting` | `com.moonsworth.lunar.client.render.particle` | Upstream Blockbuster component: Tint color from "color"; registered under minecraft:particle_appearance_tinting, sorting index -10 |
| 3 | `Glintcolorizer2Base3` | `SSFunction` | `com.moonsworth.lunar.client.render.particle` | mclib.math.functions.SSFunction: string-in/string-out function base ("cannot receive number arguments", stringValue(), booleanValue = equalsIgnoreCase("true")) |
| 4 | `Glintcolorizer2Base4` | `Cos` | `com.moonsworth.lunar.client.render.particle` | mclib.math.functions.trig.Cos: Math.cos(arg0); MathBuilder registers it as function "cos" |
| 5 | `Glintcolorizer2Base42` | `Abs` | `com.moonsworth.lunar.client.render.particle` | mclib.math.functions.classic.Abs: Math.abs(arg0); MathBuilder registers it as "abs" |
| 6 | `Glintcolorizer2Base43` | `Exp` | `com.moonsworth.lunar.client.render.particle` | mclib.math.functions.classic.Exp: Math.exp(arg0); MathBuilder registers it as "exp" |
| 7 | `Glintcolorizer2Base44` | `Min` | `com.moonsworth.lunar.client.render.particle` | mclib.math.functions.limit.Min: Math.min(arg0,arg1); MathBuilder registers it as "min" |
| 8 | `Glintcolorizer2Impl` | `BedrockComponentParticleLifetime` | `com.moonsworth.lunar.client.render.particle` | Upstream Blockbuster component: expiration_expression/max_lifetime -> particle.lifetime; registered under minecraft:particle_lifetime_expression, sorting index 1000 |
| 9 | `Glintcolorizer2Impl2` | `BedrockComponentKillPlane` | `com.moonsworth.lunar.client.render.particle` | Upstream Blockbuster component: exactly the 4-float plane a,b,c,d and plane-crossing particle.dead test; registered under minecraft:particle_kill_plane, sorting index 100 |
| 10 | `Glintcolorizer2Base` | `BedrockComponentShapeBase` | `com.moonsworth.lunar.client.render.particle.highlight` | Upstream Blockbuster shape base: offset/direction(inwards|vector)/surface_only; applied at particle spawn (IComponentParticleInitialize) |
| 11 | `Glintcolorizer2Base2` | `BedrockComponentShapeEntityAABB` | `com.moonsworth.lunar.client.render.particle.highlight` | Upstream Blockbuster shape: random point in the target entity AABB (width/eye-height) with 6-way surface roll |
| 12 | `Glintcolorizer2Base3` | `BedrockComponentShapeSphere` | `com.moonsworth.lunar.client.render.particle.highlight` | Upstream Blockbuster shape: random unit vector scaled by "radius" (half radius when not surface_only) |
| 13 | `Glintcolorizer2Base4` | `BedrockComponentShapePoint` | `com.moonsworth.lunar.client.render.particle.highlight` | Upstream Blockbuster shape: position = offset, fixed direction applied only for ShapeDirection.Vector |
| 14 | `Glintcolorizer2Base` | `BedrockComponentRate` | `com.moonsworth.lunar.client.render.particle.holograms` | Upstream Blockbuster abstract rate base with the single MolangExpression particles field |
| 15 | `Glintcolorizer2Base2` | `BedrockComponentRateSteady` | `com.moonsworth.lunar.client.render.particle.holograms` | Upstream Blockbuster component: spawn_rate/max_particles (default 50), per-emitter-tick emission with fraction carry |
| 16 | `Glintcolorizer2Base3` | `BedrockComponentRateInstant` | `com.moonsworth.lunar.client.render.particle.holograms` | Upstream Blockbuster component: num_particles burst on the first emitter tick (default 10) |
| 17 | `Glintcolorizer2Base4` | `SinDegrees` | `com.moonsworth.lunar.client.render.particle.mixin` | mclib.math.molang.functions.SinDegrees: Math.sin(deg/180*PI); MolangParser (Glintcolorizer3Iterator) registers it as "sin" |
| 18 | `Glintcolorizer2Base42` | `CosDegrees` | `com.moonsworth.lunar.client.render.particle.mixin` | mclib.math.molang.functions.CosDegrees: Math.cos(deg/180*PI); MolangParser registers it as "cos" (distinct from the root Cos) |
| 19 | `Glintcolorizer2Base4` | `Lerp` | `com.moonsworth.lunar.client.render.particle.nameplate` | mclib.math.functions.utility.Lerp: Interpolations.lerp(a,b,t); registered as "lerp" in MathBuilder |
| 20 | `Glintcolorizer2Base42` | `HermiteBlend` | `com.moonsworth.lunar.client.render.particle.nameplate` | mclib.math.functions.utility.HermiteBlend: 3x^2-2x^3; registered as "hermite_blend" |
| 21 | `Glintcolorizer2Base43` | `LerpRotate` | `com.moonsworth.lunar.client.render.particle.nameplate` | mclib.math.functions.utility.LerpRotate: Interpolations.lerpYaw(a,b,t); registered as "lerprotate" |
| 22 | `Glintcolorizer2Base44` | `RandomFunction` | `com.moonsworth.lunar.client.render.particle.nameplate` | mclib.math.functions.utility.Random: seeded/range random; registered as "random"; suffixed to avoid the java.util.Random clash in the declaring file |
| 23 | `Glintcolorizer2Impl` | `BedrockComponentLocalSpace` | `com.moonsworth.lunar.client.render.particle.nameplate` | Upstream Blockbuster component: position/rotation/scale/scale_billboard/direction/acceleration/gravity + linear/angular_velocity flags, sorting index 1000 |
| 24 | `Glintcolorizer2Impl2` | `BedrockComponentInitialization` | `com.moonsworth.lunar.client.render.particle.nameplate` | Upstream Blockbuster meta component: creation_expression/per_update_expression on the emitter (IComponentEmitterInitialize+Update) |
| 25 | `Glintcolorizer2Base` | `NameplateBillboardComponent` | `com.moonsworth.lunar.client.render.particle.nameplate.mixin` | Nameplate-engine appearance billboard: extends the root billboard component and renders nameplate quads through Bridge2_32; registered for minecraft:particle_appearance_billboard by nameplate/JsonDeserializerIterator |
| 26 | `Glintcolorizer2Base2` | `NameplateTintingComponent` | `com.moonsworth.lunar.client.render.particle.nameplate.mixin` | Nameplate-engine tinting: extends the root tinting component, renderOnScreen applies the Tint; registered for minecraft:particle_appearance_tinting |
| 27 | `Glintcolorizer2Impl` | `NameplateCollisionTintingComponent` | `com.moonsworth.lunar.client.render.particle.nameplate.mixin` | Upstream BedrockComponentCollisionTinting for the nameplate engine: enabled+color, renders collision-tinting particles only; registered for blockbuster:particle_collision_tinting |
| 28 | `Glintcolorizer2Impl2` | `NameplateCollisionAppearanceComponent` | `com.moonsworth.lunar.client.render.particle.nameplate.mixin` | Upstream BedrockComponentCollisionAppearance for the nameplate engine: enabled/lit/material/texture + billboard UV/flipbook and the full nameplate quad render; registered for blockbuster:particle_collision_appearance |
| 29 | `Glintcolorizer2Base` | `BedrockComponentMotion` | `com.moonsworth.lunar.client.render.particle.rewindhandlers` | Upstream Blockbuster abstract motion base (empty body) |
| 30 | `Glintcolorizer2Base2` | `BedrockComponentMotionParametric` | `com.moonsworth.lunar.client.render.particle.rewindhandlers` | Upstream Blockbuster component: relative_position + rotation applied to the particle matrix |
| 31 | `Glintcolorizer2Base3` | `BedrockComponentMotionDynamic` | `com.moonsworth.lunar.client.render.particle.rewindhandlers` | Upstream Blockbuster component: linear_acceleration / linear_drag_coefficient / rotation_acceleration / rotation_drag_coefficient |
| 32 | `Glintcolorizer2Impl` | `BedrockComponentInitialSpin` | `com.moonsworth.lunar.client.render.particle.rewindhandlers` | Upstream Blockbuster component: "rotation" + "rotation_rate" -> particle initialRotation/rotationVelocity (rate/20) |
| 33 | `Glintcolorizer2Impl2` | `BedrockComponentInitialSpeed` | `com.moonsworth.lunar.client.render.particle.rewindhandlers` | Upstream Blockbuster component: JSON array direction[3] or primitive speed (default 1) scaling the particle direction |

## Fishing (6)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Fishing` | `InactiveTask` | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing` | Task interface of the inactive-cosmetic animation system (isCancellable/start/stop/duration/tick over Inactive3+Holograms3Handler); implemented by AbstractTask/AbstractTimedTask/MoveToOwnerTask/FishingHandler and held by FishingType.taskClass |
| 2 | `Fishing` | `GlaciteTunnelGraph` | `com.moonsworth.lunar.client.framework.feature.mod.fishing` | glacite-tunnels.json: UndirectedGraph<Fishing2> plus precomputed Map<UnorderedPair,Float> edge weights and getWeight(a,b); loaded by Module.method10() for Glacite Tunnels pathing |
| 3 | `Fishing` | `MetalDetectorTreasureMap` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin` | Map<MetalDetectorTreasureType,Set<Vector3ic>> of relative treasure offsets; built by MetalDetectorLocationsDeserializer, consumed by SkyblockMetalDetector |
| 4 | `Fishing` | `GiftRecord` | `com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.mixin` | Winter-gift record cached per armor stand by GiftTracker: gift stand, From:/To: stands+names, fromSelf/toSelf flags; method1() = !fromSelf && !toSelf |
| 5 | `Fishing` | `UuidProvider` | `com.moonsworth.lunar.client.replay.fishing` | Single-method interface (T get()) always instantiated as Fishing<UUID>: RewindIterator implements it and Highlight/HighlightTreeMap key entries by the returned UUID |
| 6 | `Fishing` | `LineSegment` | `com.moonsworth.lunar.client.util` | Immutable (Vector3dc,Vector3dc) pair: produced by LineFitter edge fitting, drawn by fishing.click.Click.method18, emitted as replay highlight quad edges by RewindHandlers3Updater |

## Colorsaturation (4) + ColorsaturationExtension (3)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Colorsaturation` | `ModelMatrixStack` | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.colorsaturation` | Push/pop stack of Matrix4f+Matrix3f with translate/scale/rotate helpers for IBoneSerializer and CubeMesh; the model-baking transform stack used by Rewindhandlers2_2/Fov11 |
| 2 | `Colorsaturation` | `AnimationKeyframeParser` | `com.moonsworth.lunar.client.cosmetics.inactive.mixin.colorsaturation.mixin` | Parses Bedrock/Gecko animation keyframe maps into GeckoLib VectorKeyFrameList (easing/easingArgs, radians for rotation) and Molang Evaluatables; used by AnimationJsonParser |
| 3 | `ColorsaturationExtension` | `CosmeticHighlightShader` | `com.moonsworth.lunar.client.driver.holograms` | ShaderDefinition implementation id "cosmetic_highlight": uniforms HighlightColor/Resolution, samplers CosmeticDepth/SceneDepth, alpha-cutout 0.0F |
| 4 | `ColorsaturationExtension` | `MenuBlurShader` | `com.moonsworth.lunar.client.framework.feature.menublur` | ShaderDefinition implementation id "menu_blur": BlurDir/Radius/OneTexel/Progress uniforms |
| 5 | `Colorsaturation` | `MinionXpData` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation` | minions.json data: categories (minion name -> category key) and xp (item id -> xp per item); used by SkyblockEnhancedMinionMenu to compute minion XP/levels |
| 6 | `ColorsaturationExtension` | `MotionBlurShader` | `com.moonsworth.lunar.client.framework.feature.motionblur` | ShaderDefinition implementation id "motion_blur": PrevSampler sampler + Phosphor vec3 uniform |
| 7 | `Colorsaturation` | `RewindPropertiesPanel` | `com.moonsworth.lunar.client.replay.rewindhandlers.colorsaturation` | GuiIterator panel of the rewindProperties route (RewindPropertiesBridge.method15 -> RewindHandlers.method35): emits availableProperties/showMods/hideKeyframeControls/properties/canUndo/canRedo |

## Coordinates (5)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Coordinates` | `SkillLevelCalculator` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates` | Static XP->level math for skills/runecrafting/social/pet100/pet200/catacombs from the max-levels.json arrays; used by SkillXpListener and SkyblockPartyFinder |
| 2 | `Coordinates` | `MaxLevels` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin` | max-levels.json holder: Object2IntOpenHashMap<CoordinatesType> + Object2IntOpenHashMap<String>; returned by Module.method24() and queried by SkillLevelCalculator |
| 3 | `Coordinates` | `CoordinatesHud` | `com.moonsworth.lunar.client.mod.hud` | The coordinates HUD mod (getId "COORDINATES"): X/Y/Z/C/biome/direction child mods, background/border/copy-coords settings |
| 4 | `Coordinates` | `RewindEditorContext` | `com.moonsworth.lunar.client.replay.rewindhandlers.coordinates` | Static rewind-editor context (selected layers/keyframes, playhead, zoom, timeline lookup helpers) and base class of the rewind route providers (RewindTimelineBridge/RewindPropertiesBridge/RewindEditorBridge/...) |
| 5 | `Coordinates` | `LunarSoundPlayer` | `com.moonsworth.lunar.client.util` | Custom sound player: plays registered sounds or mp3/wav/ogg files from .minecraft/lunar/sounds, owns the sound-name list, file-size check and 10s missing-sound throttle |

## Chest (2 remaining)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Chest` | `NpcLocations` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.chest` | important-npc-locations.json: island name -> List<Vector3d> NPC positions; consumed by SkyblockHidePlayersNearNpc |
| 2 | `Chest` | `RewindTimelinePanel` | `com.moonsworth.lunar.client.replay.rewindhandlers.chest` | GuiIterator panel of the rewindTimeline route (RewindTimelineBridge.method15 -> RewindHandlers.method29): emits selectedLayer/additionalSelectedLayers/links/timeline tracks+layers+keyframes |

## Click (2 remaining)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Click` | `WorldRenderUtils` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.click` | Static world-render toolbox (~60 methods): coloured boxes/outlines/beams/lines/billboards/labels; used by Calculator, Burrow, RouteRenderer, pathfinding debug and rewind handlers |
| 2 | `Click` | `RewindTimelinesListPanel` | `com.moonsworth.lunar.client.replay.rewindhandlers.click` | GuiIterator panel of the rewindTimelinesList route (RewindTimelinesListBridge.method15 -> RewindHandlers.method34): emits the list of replay timelines (id+name) |

## Waila (3)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Waila` | `WailaComponent` | `com.moonsworth.lunar.client.framework.feature.waila` | Element interface (getWidth/getHeight/render) implemented by every *WailaComponent, VerticalWailaGroup, WailaHandler and WailaIterator |
| 2 | `Waila` | `WailaRow` | `com.moonsworth.lunar.client.framework.feature.waila.mixin` | Horizontal WAILA row: list of elements plus scale, lays them out left to right; toString "WAILARow(elements=..., scale=...)" |
| 3 | `Waila` | `WailaHud` | `com.moonsworth.lunar.client.mod.hud` | The WAILA (What Am I Looking At) HUD mod: block coords/correct tool/break time/light level/entities/cosmetics settings and render context |

## Minimap (3)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Minimap` | `MinimapMap` | `com.moonsworth.lunar.client.framework.feature.minimap` | Minimap map object: chunk colour-map cache + region files, GL texture/framebuffer and update queue; exposed by the mod as method29() and passed to every Minimap*Provider |
| 2 | `Minimap` | `BoneTransform` | `com.moonsworth.lunar.client.framework.feature.minimap.mixin` | Record whose bootstrap component names are rotation;scale;rootOnly (Vector3f/float/boolean); orphan class (no source or jar references) so named from its shape |
| 3 | `Minimap` | `MinimapMod` | `com.moonsworth.lunar.client.mod.render` | The minimap HUD mod (shape/markers/waypoints/entity-marker settings, Ref...method94()), owns the MinimapMap and the render/marker providers |

## Rewind (3)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Rewind` | `RewindMod` | `com.moonsworth.lunar.client.mod.misc` | The Rewind recording/replay mod (getId "REWIND"): record/pause/marker keys, shadow rewind, mic+system audio, validates and opens RewindHandlers |
| 2 | `Rewind` | `ReplayAction` | `com.moonsworth.lunar.client.replay` | @FunctionalInterface run(Rewind3_3) used as the keybind action in ReplayKeybindHandler option/combination maps and invoked with the active replay session |
| 3 | `Rewind` | `RewindFileCache` | `com.moonsworth.lunar.client.replay.mixin` | Replay file library: Map<UUID,RewindFileReader>, scans .rewind files with MediaPool+ThumbnailManager, opens/caches/closes readers by UUID |

## MixinHelper (2 remaining)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `MixinHelper` | `GuiRenderer` | `com.moonsworth.lunar.client.framework.feature.mod` | GUI render framework singleton (MixinHelper.field1): start/end frame, render-state stack, WidgetStateCache/ScissorStack/scaled text/item/slot/box/panel renderers, MouseInput/KeyboardInput/ScrollAnimator |
| 2 | `MixinHelper` | `SpecBuilder` | `com.moonsworth.lunar.client.util` | Abstract fluent builder base of the source-generation spec library (JavadocSpec+AnnotationSpec sets, typed self-returning methods); extended by FieldSpec.Data and MethodSpec.Data |

## Annotation (4)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Annotation` | `JsonSection` | `com.moonsworth.lunar.client.driver.core.gui` | RUNTIME method annotation whose value() is a JSON section key ("settings","categories","friends","accounts"); applied to methods returning JsonProvider/JsonElement/toString for the driver GUI |
| 2 | `Annotation` | `RequiresDynamicListener` | `com.moonsworth.lunar.client.framework.listener` | Method annotation carrying DynamicListenerIsEnabled.Type; applied to option getters (LightingExtension4, GuiRewindhandlers2Extension2) whose value is only meaningful while that dynamic listener is enabled |
| 3 | `Annotation` | `TranslationKey` | `com.moonsworth.lunar.client.util` | Annotation whose Type enum holds translation-key prefixes (features.$MOD$.info, settings, settings.labels/buttons, gui.components...); the IntelliJ @Pattern is a translation-key pattern; 100 jar refs |
| 4 | `Annotation` | `TransformMethod` | `com.moonsworth.lunar.ichor` | Ichor transformer method-hook annotation: String value is the target method name/pattern (default ".*"), e.g. @Annotation("<init>") on (ClassNode,MethodNode) hooks; sibling of @TransformClass |

## Bridge7Iterator (4)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `Bridge7Iterator` | `InventoryButtonsEditor` | `com.moonsworth.lunar.client.framework.feature.mod.gui` | Skyblock inventory-buttons editor screen: edits HighlightButton size/scale/shape/colors/command/hover/item with live preview; language path .skyblockInventoryButtonsEditor |
| 2 | `Bridge7Iterator` | `DungeonWaypointShareScreen` | `com.moonsworth.lunar.client.framework.feature.mod.gui.mixin` | Dungeon waypoint share screen: room/boss-floor selection, export/import string and zip, language path .dungeonWaypointShare |
| 3 | `Bridge7Iterator` | `QuickplayOverlay` | `com.moonsworth.lunar.client.framework.feature.quickplay.mixin` | Quickplay options overlay: favorite toggle + keybind binding + done; method133 returns "Quickplay Overlay" |
| 4 | `Bridge7Iterator` | `RewindLoadingScreen` | `com.moonsworth.lunar.client.replay.rewindhandlers.holograms` | Screen shown while a rewind recording loads: "Loading Rewind..." banner and a cancel button that closes the rewind mod |

## JsonDeserializerImpl (3)

| # | old | new | package | evidence |
|---|-----|-----|---------|----------|
| 1 | `JsonDeserializerImpl` | `Vec3iDeserializer` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.click` | Gson adapter for Vec3iBridge from "x,y,z" strings; registered in Module for Vec3iBridge.class |
| 2 | `JsonDeserializerImpl` | `VisitorTrackerDeserializer` | `com.moonsworth.lunar.client.framework.feature.mod.fishing.nameplate` | Gson adapter for SkyblockVisitorTrackerHud.Data from the visitor JSON (total/accepted/denied/gardenXP/farmingXP/copper/bits/rarities); registered by the HUD class |
| 3 | `JsonDeserializerImpl` | `LegacyDungeonWaypointDeserializer` | `com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints` | Gson adapter for the legacy Dungeonwaypoints format ("pos" string + color/showThroughWalls); registered for Dungeonwaypoints.class in Module |

## Notes / caveats

### The particle engine is upstream Blockbuster/McLib

`client/render/particle` is the vendored Blockbuster 1.12 Bedrock-particle engine
(branch `1.12` of `mchorse/blockbuster`) plus McLib’s math library (branch `1.12` of
`mchorse/mclib`). Each body was matched against those sources: the Bedrock components
have the same JSON keys, the same `apply/update` logic and the same `getSortingIndex`
values; the math functions have the same `getRequiredArguments`/`doubleValue`. The
numbering leftovers are simply the upstream classes:

* `Glintcolorizer2Base{,2,3,4,42,43,44}` -> `BedrockComponentAppearanceBillboard`,
  `BedrockComponentAppearanceTinting`, `SSFunction`, `Cos`, `Abs`, `Exp`, `Min`;
* the `highlight`/`holograms`/`rewindhandlers`/`nameplate` subpackages hold the
  Blockbuster `shape`/`rate`/`motion`/`local-space` components and are named
  accordingly; the per-feature package names are wrong-package leftovers but moving
  them is out of scope for this map;
* `nameplate/mixin/*` are Lunar’s nameplate re-implementations of the collision
  appearance/tinting components (the nameplate `JsonDeserializerIterator` registers
  them for `minecraft:particle_appearance_*` and `blockbuster:particle_collision_*`),
  named after the already-applied `NameplateGlintRenderer` interface;
* `mixin/Glintcolorizer3Iterator` (MolangParser) registers `mixin.Glintcolorizer2Base4`
  as `"sin"` and `mixin.Glintcolorizer2Base42` as `"cos"`, while `MathBuilder` registers
  the root `Glintcolorizer2Base4/42/43/44` as `"cos"/"abs"/"exp"/"min"` — hence
  `SinDegrees`/`CosDegrees` in `mixin` and `Cos`/`Abs`/`Exp`/`Min` in the root package.

**Jar-only name collision (documented, accepted):** the nine math functions collide with
the bundled `com.eliotlash.molang.functions.*` jar classes of the same name
(`classic.Cos`, `classic.Abs`, `classic.Exp`, `limit.Min`, `utility.Lerp`,
`utility.LerpRotate`, `utility.Random`, `classic.SinDegrees`, `classic.CosDegrees`).
They are unique in `src/main/java`, no source file imports those eliotlash function
classes (only `Function`/`FunctionDefinition` are imported, without wildcards), and the
already-applied `Interpolations` in this same package has exactly the same jar collision,
so upstream names are kept. If the owner prefers jar-clean names, `McLibMathCos`-style
prefixes are the fallback.

### Medium-confidence rows

* `framework/feature/minimap/mixin/Minimap -> BoneTransform` — an orphan record. Its
  bootstrap component names are `rotation;scale;rootOnly` (Vector3f/float/boolean) and
  there are no source *or* jar references to it, so it is named from its shape only.
* `driver/core/gui/Annotation -> JsonSection` — a RUNTIME method annotation whose value is
  the JSON key of the section the annotated method provides (`"settings"`, `"categories"`,
  `"friends"`, `"accounts"`); the reader is a jar-side driver class, so the name comes
  from the usage sites.
* `framework/listener/Annotation -> RequiresDynamicListener` — method annotation carrying
  `DynamicListenerIsEnabled.Type`; applied in the jar to option `get()`s
  (`LightingExtension4`, `GuiRewindhandlers2Extension2`) whose value depends on the
  dynamic listener. The twin annotation `DynamicListenerIsEnabled` was already named.
* `client/util/Annotation -> TranslationKey` — the annotation itself is only referenced
  from the jar (100 classes) plus `client/util/TranslationEntry.Annotation.Type`; the
  nested `Type` enum values are translation-key prefixes.
* `ichor/Annotation -> TransformMethod` — has real usages: `@Annotation("<init>")` on
  `(ClassNode, MethodNode)` transformer hooks, next to `@TransformClass`; the default
  `".*"` selects the target method name.

### Deliberately not renamed

* `ichor/util/Annotation` (the 5th `Annotation`) — an empty marker annotation with zero
  source references; in the jar it is applied next to `@Deprecated` on launchwrapper
  classes (`Launch`, `LaunchClassLoader`, `IClassTransformer`, `RemappingClassAdapter`),
  which is not enough to recover a confidence-worthy name.
* The ten `MixinHelper` files outside `com.moonsworth.lunar.client.*` (`bridge`, `forge`,
  `genesis.mixin`, `legacy`, `loader.mixin`, `network[.mixin]`, `replaymod.*`) — the
  mass-name list only covers the three client ones, and the third
  (`client/util/mixin/MixinHelper`) was already renamed to `ElementAdder` by `namesA`.

### Applier notes

* Every row is rename-only, so `newpkg == oldpkg`; the 6th `file` column is present on
  every row (the `old` names are duplicated across packages, so scoping keeps the map
  unambiguous even though no two rows share `oldpkg`).
* Import-aware shadowing is safe for the generic names: `ichor/Smuggle.java` uses bare
  `Annotation` for `java.lang.annotation.Annotation` but single-imports it, so the
  applier’s shadow guard skips it; `client/util/ReflectionUtils.java` uses the FQN
  `java.lang.annotation.Annotation`, which the FQN patterns do not touch.
* `mod/render/Minimap` -> `MinimapMod` also updates the nested-type imports
  `mod.render.Minimap.EntityMarkerType` used by `MinimapPlayerProvider`/`Minimap2Impl2`.

### Neighbour leftovers in the same packages (not this cluster)

`Glintcolorizer2Base4_2/3/4`, `Glintcolorizer2Base42_2/3`, `Glintcolorizer2Base43_2/3`,
`Glintcolorizer2Base44_2/3`, `Glintcolorizer2Base45..48`, `Glintcolorizer2Base_2/3`,
`Glintcolorizer2Base2_2/3`, `Glintcolorizer2Base3_2`, `Glintcolorizer2Handler*`,
`Glintcolorizer2_2/3/4`, `Glintcolorizer3_2/3`, `Glintcolorizer4_2`, `Glintcolorizer5_2`,
`Glintcolorizer{Extension,Impl,Iterator,Type}`, `highlight.Glintcolorizer2Base5/32`,
`rewindhandlers.Glintcolorizer2Iterator`, `mixin.Glintcolorizer3Iterator` and the various
`JsonDeserializerIterator`/`GuiIterator` copies were not in this cluster’s file list.
For consistency, if they are ever taken: `Glintcolorizer2Base4_2` is McLib `NNFunction`,
`Glintcolorizer2Base45` `Pow`, `Glintcolorizer2Base46` `Sin`, `Glintcolorizer2Base47`
`Sqrt`, `Glintcolorizer2Base48` `Mod`, `Glintcolorizer2Base4_3` `Floor`,
`Glintcolorizer2Base42_3` `Ceil`, `Glintcolorizer2Base43_3` `Round`,
`Glintcolorizer2Base44_2` `Trunc`, `Glintcolorizer2Base43_2` `Clamp`,
`Glintcolorizer2Base42_2` `Max`, `Glintcolorizer2Base44_3` `Ln`, and
`highlight.Glintcolorizer2Base5`/`Glintcolorizer2Base32` are the remaining Blockbuster
shape components `BedrockComponentShapeBox` (`half_dimensions`) and
`BedrockComponentShapeDisc` (`plane_normal`).
