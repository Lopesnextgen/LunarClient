# Wave 5 member renames — cluster 201 (`com.moonsworth.lunar.client.mod.render`)

Scope: the 25 files listed in `member-cluster-201.tsv`
(owner = first class in file). Map: `members-201.tsv` — **680 rows**
(266 methods, 414 fields) over 24 owners. `ServerCustomNameplate` contributed
no rows (see "dropped" below).

## Method

* Evidence per row: declaring source + all call sites grepped tree-wide,
  option keys (`"borderColor"` etc.), Lombok `@Generated` getter pairs,
  `lunar-client-names.tsv` (`ModMinimap`, `ModLightOverlay`, Apollo
  `NickHider*/Nametag*/Saturation*`), and the 509 reference clients
  (`Apple 2.67`/`Athena` MotionBlur `Blur Amount`/`getAccumulationValue`,
  Moonsense/Glass/Melon `NickHiderModule`, `ScrollableTooltipsModule`).
* Option-tied names copy the literal option id (`mapWidth`, `hideFoliage`,
  `fishingLineThickness`, ...); Lombok getters are named `get<FieldName>`,
  boolean getters `is<FieldName>Enabled` where the body ANDs a toggle.
* Family base names (outside this cluster) were **not** added, see below.

## Verification

* `tools/apply_member_renames.py --map members-201.tsv` dry run: 680 rows,
  2 multi-decl skips (MobSize.method2, Saturation.method9), 0 unknown owners.
* Applied to a scratch copy of `src/main/java` (`/tmp/opencode/m201/mod_tree`)
  and compiled with ECJ next to an identical unmodified copy:
  **0 NEW failing files** (`tools/ecj-diff` criterion).
* Arity audit of every `this.<old>(`/`super.<old>(`/bare `<old>(` call in the
  declaring files against the declaration signature: 0 mismatches.
* Validator: 0 format/duplicate/style problems; old name declared exactly once
  per file for every emitted row.

## Deliberately skipped (family / applier limitations)

`apply_member_renames.py` renames (a) declarations, (b) `this.<old>`/`super.<old>`
textually, (c) accesses whose receiver *type* it can resolve. The following were
left as `methodN`/`fieldN` because renaming them alone breaks the build:

* **Tree-wide overrides of other clusters' base methods** (need one family-wide
  pass, base owner not in this cluster): `method2(RootSettingsAssembler|Builder)`
  (Framework7Extension — recommend `registerOptions`), `method20()` (AbstractFeature
  — `getModDetails`), `method1(boolean)` (AbstractFeature — `setEnabled`),
  `method3(boolean)`/`method5()`/`method6()`/`method8()`/`method9(String,Object...)`/
  `method10()`/`method12()` (AbstractFeature/Framework7Extension), `method23(String)`
  and `method24(String)` (AbstractFeature, OverlayMod/OneSevenVisuals),
  `method4(boolean)`/`method3(HighlightImpl,...)`/`method31()` (MixinCore9Extension,
  implemented by `Minimap$Data`/`MinimapMod$Data`), `method14(MixinNameplate2)`.
* **Rows dropped after the ECJ gate caught NEW failing files** — their call sites
  are chained (`Ref.method4()...methodXX().methodYY()`) or use inherited fields,
  which the tool cannot resolve. Add these later with a type-aware pass:
  | owner | old | intended name |
  |---|---|---|
  | Markers | method14 / method15 | getGuiIterator / getMarkerKeybind |
  | NickHider | method13 | clearNickname |
  | NickHider | method29 | forEachNickname |
  | NickHider | method39 | getNicknameListener |
  | Lighting | method13 | shouldForceFullBright |
  | OverlayMod | method45 / method48 / method71 | shouldRenderStuckArrows / isBarrierOutlinesEnabled / getGuiHandler |
  | ScrollableTooltips | method1 / method14 | onTooltipPre / getHoveredItemStack |
  | Minimap+MinimapMod | method13 | isInsideMap (also clashes with inherited `method13(Framework, Data)` in the ctor) |
  | Minimap+MinimapMod | method24 / method26 | getEntityMarkerType / getEntityMarkerSize (accessed via `field1` inherited from `Minimap_2`) |
  | ServerCustomNameplate | method3 | onRenderNameTag (file also calls inherited `method3(Framework, ModEnabledState)`) |
  | Markers | field9 | DEBUG (public static, read cross-package as `...render.Markers.field9`; FQN field access is not renamed) |
* **Names declared more than once in the file** (auto-skipped by the applier,
  documented instead): `MobSize.method2` (event handler + settings override),
  `Saturation.method9` (render + `method9()` override). Also skipped for the
  same reason: the outer/inner shared names of `Minimap`/`MinimapMod`
  (`method4`..`method9` used by both the mod and its `Data` HUD element).
* **`MenuBlur$Data`** (inner class, no `MenuBlur$Data.java` companion): the tool
  cannot resolve the owner or `field11.method1()` receivers, so the 8 members
  stay lazy. Intended: `field1/2/3` → `toggle`/`background`/`screenClasses`;
  `method1` → `getBackgroundColor`; `method2` → `matchesScreen`;
  `method3/4/5` → `getToggle`/`getBackground`/`getScreenClasses`.
* **`Minimap$Data`/`MinimapMod$Data`**: same tool limitation plus shared old
  names. Intended: `method3` → `renderMinimap`, `method4(MixinHelper_4,..)` →
  `renderInfoLines`, `method5` → `getInfoLinesHeight`, `method6` →
  `renderWaypointBeacons`, `method7` → `renderCompass`, `method8` →
  `renderCompassLetter`, `method9` → `renderCompassLetterCircular`
  (`method3`, `method4(boolean)`, `method31` are MixinCore9Extension overrides
  and must stay family-consistent anyway).
* **OverlayMod.field82 + method72/method73**: no call sites anywhere in the tree
  and the semantics are unclear — left untouched rather than guessed.

## Class-level follow-ups (not this cluster's map)

Inner enums are still lazy; recommended class renames when a class pass runs:
`Markers$Type` → `Visibility`, `Markers$Type2` → `DescriptionDisplay`,
`Markers$Type3` → `OwnerDisplay`; `Minimap$Type` → `MapShape`,
`Minimap$Type2` → `EntityMarkerType`, `Minimap$Type3` → `PlayerMarkerType`
(the `MinimapMod` twins already carry the latter two names);
`LightOverlay$Type` → `OverlayMode`; `PotionEffects$Type` → `DisplayMode`,
`PotionEffects$Type2` → `BarPosition`; `OverlayMod$Type` → `GlintMode`;
`MotionBlur$Type` → `BlurVersion`; `MenuBlur$Data` → `BlurScreen`.

## Uncertain

* `OverlayMod.field8` = `BARRIER_TEXTURE_SIZE` (public constant 64, no in-tree
  readers); `OverlayMod.field10` = `POWDER_SNOW_OUTLINE_COLOR` (unused, matches
  the literal in the powdered-snow updater).
* `Minimap.field9` = `MAX_RENDER_DISTANCE` (14, only read by the scale formula).
* `OneSevenItemsLegacy.method5` = `isSpecialHeldItem` (ItemsBridge items 1-2
  only get the 1.7 first-person path when `firstPersonFishingRod` is on).
* `MotionBlur.field8` = `blurAmount` follows the reference clients' label
  ("Blur Amount") even though the option id is `"value"`.

No renamed member is used through reflection or by string name in this cluster
(the string-keyed members are option ids, which were kept).
