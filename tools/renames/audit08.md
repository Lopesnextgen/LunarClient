# Audit cluster 08 — `framework.feature.{attackindicator.mixin, autotextactions, blockoutline, chat, colorsaturation, cooldowns, cooldowns.mixin, coordinates}`

Source slice: `tools/renames/audit-cluster-08.txt` (8 packages, all contain `.java`).

This wave looks for **plausible-but-wrong** names that the lazy-name inventory
never flagged (`n_lazy = 0` in `package-inventory.tsv` for every package here).
Maps produced:

* `tools/renames/classes-audit08.tsv` — 21 class rows
* `tools/renames/packages-audit08.tsv` — 2 package rows

Dry runs:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-audit08.tsv
#  rows=21 skipped=1 files_touched=29 files_renamed=20 mode=dry-run
#  (the one skip is the deliberate BlockOutlineMode swap, see below)
python3 tools/apply_package_renames.py --map tools/renames/packages-audit08.tsv
#  rows=2 files_moved=15 files_touched=18 mode=dry-run
```

## What each package really is

### 1. `framework.feature.attackindicator.mixin` (13 classes)
The **attack-indicator provider subsystem**, not mixins. `AttackIndicatorProvider`
(package-private interface) + `AttackIndicatorState` (immutable snapshot) are
already correctly named (renamed earlier from `Attackindicator2`/`Attackindicator3`).
The remaining 11 `Attackindicator2Handler*` classes are the concrete providers.
The quarantined registry `tools/work/quarantine/.../mixin/Attackindicator.java`
shows the exact categories each provider is registered under
(`sleep`, `blockBreaking`, `swords`/`axes`/`pickaxes`/`shovel`, `enderPearl`/
`chorusFruit`/`shields`/`windCharge`, `bows`, `tridents`, `spears`,
`consumables`, `storage`, `nonWeapons`), which pins every new name:

| old | new | category |
|---|---|---|
| `Attackindicator2Handler` | `SleepAttackIndicator` | sleep |
| `Attackindicator2Handler2` | `AbstractItemAttackIndicator` | abstract base |
| `Attackindicator2Handler22` | `BowAttackIndicator` | bows |
| `Attackindicator2Handler23` | `ConsumableAttackIndicator` | consumables |
| `Attackindicator2Handler24` | `ContainerAttackIndicator` | storage |
| `Attackindicator2Handler25` | `CooldownAttackIndicator` | enderPearl/chorusFruit/shields/windCharge |
| `Attackindicator2Handler26` | `TridentAttackIndicator` | tridents |
| `Attackindicator2Handler262` | `SpearAttackIndicator` | spears (KineticWeaponPhases) |
| `Attackindicator2Handler3` | `BlockBreakAttackIndicator` | blockBreaking |
| `Attackindicator2Handler4` | `NonWeaponAttackIndicator` | nonWeapons |
| `Attackindicator2Handler5` | `MeleeAttackIndicator` | swords/axes/pickaxes/shovel |

**Package is wrong:** the classes are providers, not mixins (no `@Mixin`, no
mixin config). `attackindicator.mixin` → `attackindicator` (merge into the
parent). The `.mixin` segment is a restructure artifact: `package-renames.tsv`
maps the obf package `…/HRICOROOOCCOCOROCRHHCRRIRCOICO/HORHROIOIOICIRHIOCOICHHHIHCIIO`
to `…/feature/attackindicator/mixin`.

### 2. `framework.feature.autotextactions` (1 class)
`Lighting3Loader` is a **misleading leftover name** (looks like a lighting
loader). It is one configured **AutoTextActions trigger**: option keys
`active/triggerKey/regex/contains/caseSensitive/hideMessage/showTitleAction/
titleText/desktopNotification/ingameNotification/playSound`, `matches(String)`,
lang keys `gui.autoTextTriggers.*`; held in `mod/misc/AutoTextActions.actions`.
→ `AutoTextAction`. Package name is correct.

### 3. `framework.feature.blockoutline` (2 classes)
The two enums are **swapped**: the option `blockOutlineMode` uses
`Gui2Extension` (STATIC/RAINBOW/BLEND) and `blockOverlayMode` uses
`BlockOutlineMode` (STATIC/RAINBOW/BLEND/INVERTED/DARKEN, supplies
`renderType()`); see `mod/render/BlockOutline.field10`/`field18`.

* `Gui2Extension` → `BlockOutlineMode`
* `BlockOutlineMode` → `BlockOverlayMode`

⚠️ This is a **swap**, so the aware applier cannot do it in one pass (it sees
`BlockOutlineMode` still declared and skips that row). Apply in two passes:
first `BlockOutlineMode → BlockOverlayMode`, then `Gui2Extension → BlockOutlineMode`.

### 4. `framework.feature.chat` (6 classes)
Package name is correct; `ChatEmojiTransformer`, `ChatFilter`,
`ChatImagePreview`, `ChatMessageStacker` are all accurate. Two names are wrong:

* `Chat` (generic, and shadowed by the real mod `mod.misc.Chat` in the
  quarantined tree) is the **chat player tracker**: it maps lunar message ids
  to the sender `Bridge2_33` and resolves names/skins for the `chatHeads`
  feature (`GuiNewChatMessageMixin.lunar$chatHeadSkin`,
  `mod.misc.Chat.method83()`). → `ChatPlayerTracker`.
* `Gui2Extension` is the **profanity filter mode** enum OFF/NORMAL/HIGH/CUSTOM
  bound to option `"profanity"` (`mod.misc.Chat.field30`), consumed by
  `ChatFilter.method4`. → `ProfanityFilterMode`.

### 5. `framework.feature.colorsaturation` (1 class)
`ColorsaturationExtension` implements the post-process shader interface
`fog.holograms.colorsaturation.mixin.ColorsaturationExtension` and defines the
`"color_saturation"` shader (uniforms Hue/Brightness/Contrast/Saturation),
registered by `mod/render/ColorSaturation`. → `ColorSaturationShader`.
(The same misnomer is used in `feature.menublur` = `"menu_blur"` and
`feature.motionblur` = `"motion_blur"`; those packages are outside this slice.)

### 6. `framework.feature.cooldowns` (2 classes)
* `Cooldowns` is the abstract **single** cooldown entry (name/length/style/
  start-time), base of the two implementations; distinct from the HUD mod
  `mod.combat.Cooldowns`. → `Cooldown`.
* `Gui2Extension` is the **text position** enum LEFT/RIGHT/ABOVE/BELOW bound to
  option `cooldownTextPosition` (`mod.combat.Cooldowns.field11`). →
  `CooldownTextPosition`.

### 7. `framework.feature.cooldowns.mixin` (2 classes)
Concrete `Cooldown` implementations, **not mixins**:
* `CooldownsImpl` renders an item icon + progress arc → `ItemCooldown`.
* `IconCooldown` already correct (Apollo `Icon` cooldown) — left as is.

**Package is wrong:** `cooldowns.mixin` → `cooldowns` (merge).

### 8. `framework.feature.coordinates` (2 classes)
* `CoordinatesDirectionChildMod` is correct (the "Direction" child,
  `cardinalDirection`/`directionAffect` options).
* `CoordinatesChildHudModImpl` is a generic `Impl` name; it is the **"Biome"**
  child (`Coordinates.field24 = CoordinatesChildHudModImpl.method3(this,
  "Biome", "Plains")`, option `presetBiomeColor`). → `CoordinatesBiomeChildMod`.

## Names deliberately NOT renamed (already correct)
`AttackIndicatorProvider`, `AttackIndicatorState`, `ChatEmojiTransformer`,
`ChatFilter`, `ChatImagePreview`, `ChatMessageStacker`,
`CoordinatesDirectionChildMod`, `IconCooldown`, and the package names
`autotextactions`, `blockoutline`, `chat`, `colorsaturation`, `cooldowns`,
`coordinates`.

## Caveats / follow-ups
* **Apply order:** run `classes-audit08.tsv` **before** `packages-audit08.tsv`
  (the class rows use `file` paths under `…/attackindicator/mixin/` and
  `…/cooldowns/mixin/`; the package move rewrites those directories).
* Many old names here are declared in several packages
  (`Gui2Extension` ×32, `ColorsaturationExtension` ×4, `Cooldowns` ×2,
  `Lighting3Loader` ×2), so the rows carry the 5th `file` column and must be
  applied with `apply_class_renames_aware.py`, not the simple applier.
* The provider **registry** `…/attackindicator/mixin/Attackindicator.java` is
  quarantined (absent from `src`); `mod/combat/AttackIndicator.java` still
  imports/instantiates it, so that file is already broken. When the registry is
  restored it must adopt the new package (`…feature.attackindicator`).
* Adjacent (out-of-slice) fix worth doing in the same pass:
  `framework.feature.attackindicator.Gui2Extension` (option
  `attackIndicatorDisplayMode`, VANILLA/VANILLA_ICON/INDICATOR/INDICATOR_DOT/
  PROGRESS) should become `AttackIndicatorDisplayMode`; it is the last
  `Gui2Extension` in the attack-indicator tree.