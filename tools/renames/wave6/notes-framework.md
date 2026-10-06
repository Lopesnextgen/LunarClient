# Wave 6 — framework/** structure audit (notes)

Scope: `src/main/java/com/moonsworth/lunar/client/framework/**` (1,972 files):
112 loose classes + the non-feature subtrees `build/ combat/ crash/ holograms/
hud/ listener/ loading/ mixin/ nameplate/ transform/` (109 classes).
`feature/**` (1,751 files, the mod tree) is left untouched — it is being
rewritten by the feature-tree wave (main classes already live under
`client/mod/{combat,hud,misc,movement,player,render}/` and still reference
old-generation framework types).

Map: `moves-framework.tsv` — 188 rows (112 loose + 76 subtree), two renames
(`listener/mixin/GuiRewindhandlers` → `AutoReconnectListener`, loose
`Gui2Extension` → `HudPlacement`), everything else move-only. Validated: every
mapped source exists, every loose class is covered, and no destination package
gets a duplicate simple name (counted against the classes already present there
and against the sibling wave-6 maps — the only clash was `Gui2Extension`, see
below). Dry-run through `tools/apply_class_moves.py`: `rows=188 skipped=0
files_moved=188 files_touched=919 mode=dry-run`.

## Destination summary (rows)

| target | rows | holds |
|---|---|---|
| `client/framework/mod/` | 68 | mod/feature API (Framework*/Mod*/register traits, `AbstractFeature`, category registry) + `nameplate/` implementations of those traits |
| `client/ui/hud/` | 55 | HUD framework: `Hud*`, `MixinCore5*` components, `MixinCore9*` renderers, `hud/` rows |
| `client/command/` | 34 | command tree/nodes/executors/argument parsers + suggestion helpers (incl. the `MixinNameplate*`/`MixinHelper*` old generation) |
| `client/framework/listener/` | 15 | flattened `listener/{mixin,nameplate,rewindhandlers}` services + the dynamic-listener predicate |
| `client/framework/loading/` | 4 | `LoadableHandler`, `ItemMapHandler`, `ItemSetHandler`, `Tickable` (init/close + `LoadingStage` registration) |
| `client/config/option/` | 4 | `EnabledOption`/`FixedBooleanOption`/`CompoundBooleanOption`/`DefaultedBooleanOption` (option-backed `ModEnabledState` strategies) |
| `client/event/` | 2 | `DynamicListenerEvent`, `ThreadedEvent` markers (implemented by event classes; checked by `LunarEventBus`) |
| `client/framework/bootstrap/` | 2 | `InitialScreenGuard`, `Highlight3Handler` (startup/screen guards; the latter is built in `Client.init`) |
| `client/config/` / `config/override/` | 2 | `JsonFileConfig`; `SettingOverrideInterceptor` |
| `client/mod/combat/` | 1 | `PlayerCombatState` (category-level shared combat state) |
| `client/mod/render/serverholograms/` | 1 | `Serverholograms` model, co-located with the `ServerHolograms` mod (per `moves-mods.tsv`) |

Left in place (already correct packages): `framework/build/` (`LunarBuildData`),
`framework/crash/` (6), `framework/transform/` (6), `framework/loading/`
(`LoadableResource`, `LoadingStage`), and the 18 top-level
`framework/listener/*.java` services.

## Why these homes (evidence pattern)

* Consumer packages are decisive: `client/mod/**` (436 already-renamed mods)
  imports `AbstractFeature`, `ModTraits`, `ModDetails`, `ModCategories`,
  `HudAnchor`, `HudSize`, `TypedHudRenderer` … so the mod/HUD API must land in
  `framework/mod` and `ui/hud`, not stay loose.
* `nameplate/` was the mod-framework *implementation* package: `FeatureLifecycleManager`
  (lifecycle), `FeatureDetails`/`Nameplate2/5` (`ModDetails`), `FeatureChildren`/`Nameplate3`
  (`ModChildren`/`AlertExtension`), `Nameplate4*` (`ModEnabledState`),
  `Nameplate6` (`OptionContainer`), `Nameplate7` (`PageState`),
  `Nameplate8` (`ModRestriction`), `Nameplate10` (`PanelPosition`),
  `Nameplate9` (`ModSearchIndex`), `Nameplate11`/`KeystrokeSupport` (`ModSupport`),
  `ModDisplayData`/`Nameplate4Impl22` (`ModDisplay`), `ModCategorySet`.
* `CommandCompleter`/`CommandSuggestion*`/`Nameplate_2`/`Nameplate2_2`/`Nameplate3_2`/
  `Nameplate3Handler` build suggestions for the command tree → `client/command/`.
* `MixinCore*` are not all the same thing: `MixinCore`/`MixinCoreImpl` are
  argument parsers (→ `command`), while `MixinCore2/3/5*/6/7/9*` are HUD
  layout/value types (their own `toString`/methods say `HudSize`/`HudLine`) →
  `ui/hud`. `MixinCore$Data` is a `HudConditionSet` builder.
* `client/ui/` is the plan's home for the HUD framework (screens currently live
  in `client/gui/`, which is a different wave's scope); `client/render/` has no
  candidates here — all "render" classes in this scope render HUD widgets.

## Duplicate generations (both mapped, both live today)

The tree contains two API generations side by side; each pair stays under the
same new home so a later cleanup can delete the old one:

`Framework`→`ModTraits` (trait registries, keys identical), `Calculator2Handler`→`ModCategory`,
`Framework2`→`ModCategories`, `Framework4`→`ChildModBinding`, `Framework5`→`OptionContainer`,
`Framework6`→`PageState`, `Framework10`→`ModDisplay`, `Framework10Extension`→`ModLifecycle`,
`Framework11`→`DynamicCondition`, `Framework12`→`PanelPosition`, `FrameworkType`→`ModLoadState`,
`AlertExtension`→`ModChildren`, `MixinCore`→`CommandArgumentParser`,
`MixinCore2`→`HudSize`, `MixinCore3`→`HudLine`, `MixinCore6`→`HudComponentValue`,
`HudComponentStyle`→`MixinCore7`, and the old command tree
(`MixinNameplate*`/`MixinHelper*`/`MixinCoreImpl`) vs the new one
(`CommandNode*`/`BoundArguments`/`CommandArguments`).
Recommend deleting old-generation twins only after `framework/feature/**` is gone.

## Collision / rename decisions

* `listener/GuiRewindhandlers` (ref-counted listener interface) vs
  `listener/mixin/GuiRewindhandlers` (auto-reconnect service). Flattening would
  collide, so the service is renamed `AutoReconnectListener` (name unused
  tree-wide); the interface keeps its name.
* `nameplate/Nameplate` (option-backed enabled-state wrapper) and
  `nameplate/mixin/Nameplate` (dynamic-listener `isEnabled` predicate) cannot
  share `framework/mod`. The predicate goes to `framework/listener/` — it is
  annotated `@Annotation2(DYNAMICLISTENER_ISENABLED)` and consumed by the
  listener/restriction gating.
* Cross-map clash: `framework.Gui2Extension` (TOP/BOTTOM/MIDDLE/LEFT/RIGHT) and
  `client.hitbox.Gui2Extension` (LEFT/CENTER/RIGHT) were both mapped to
  `client/ui/hud/Gui2Extension`. The hitbox enum is a wave-6 sibling's call, so
  this map renames the framework enum to `HudPlacement` (free tree-wide).
  Note the sibling map also brings `client/hitbox/Gui2Extension`, which is a
  duplicate of `framework/hud/HudRowAlignment`; both land in `client/ui/hud`
  under different names — a deletion candidate for the naming/cleanup pass.
* All other flattening (listener subpackages, `hud/`, `nameplate/`) is
  name-collision-free.

## Explicitly not done (out of scope / follow-ups)

* `feature/**` untouched. Its root files `HudElement.java`, `HudElementBounds.java`,
  `Module.java`, `Module2.java`, `ModuleHandler.java`, `ModOptionOverrides.java`,
  `Staffxray.java`, `StaffXrayState.java` are framework-level, not features:
  recommend the feature-tree wave place `HudElement`/`Module*` with `client/ui/hud`
  and `Staffxray(State)`/`ModOptionOverrides` with `client/framework/mod`.
* `client/framework/security/` has no classes: the security prompts
  (`ActivePrompt`, `MaliciousServerPrompt`, `MaliciousUrlPrompt`, `RunCommandPrompt`, …)
  live in `client/gui/prompt/` — propose `client/ui/security/` from the gui wave.
* `Tickable` has zero references (dead); kept in `framework/loading/` rather
  than deleted, since this wave only moves classes.
