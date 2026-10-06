# Module restructure map

`module-renames.tsv` moves the Lunar client's feature/module classes (and their inner
classes) into a clean, Badlion-like module tree:

```
com/moonsworth/lunar/client/mod/<category>/<SimpleName>
```

Categories: `combat`, `render`, `movement`, `player`, `hud`, `misc`.

## Result

| | count |
|---|---:|
| module classes moved (outers) | **424** |
| inner classes moved with their outers | **340** |
| total pairs in `module-renames.tsv` | **764** |

Every target is unique, every old name exists in `tools/work/staging/lunar-all-final.jar`
and no target FQCN exists in that jar yet.

### Category counts

| category | modules | inner classes | total pairs |
|---|---:|---:|---:|
| `combat` | 23 | 17 | 40 |
| `render` | 104 | 90 | 194 |
| `movement` | 6 | 0 | 6 |
| `player` | 23 | 9 | 32 |
| `hud` | 101 | 131 | 232 |
| `misc` | 167 | 93 | 260 |
| **total** | **424** | **340** | **764** |

## Method

1. **Module classes renamed from a mod id** — 413 classes under
   `com/moonsworth/lunar/client/**` whose `getName()`/`getId()` returns an ALL_CAPS
   mod id (`ATTACK_INDICATOR` -> `AttackIndicator`). The class must be a descendant of
   the module base class `com.moonsworth.lunar.client.framework.HORHROIOIOICIRHIOCOICHHHIHCIIO_`
   and must not be a mixin (`@Mixin`, `*Mixin` name or `mixin*` package segment).
2. **Hidden child-mods** — 11 more descendants of the module base whose id is built
   dynamically but whose name appears in a `"<Name> must be created using <Name>.create()!"`
   exception string: `CoordinatesChildHudMod`, `CrosshairChildMod`, `F3ModuleChildMod`,
   `FragmentDebugChildMod`, `HeightLimitChildMod`, `ItemCounterElementChildMod`,
   `KillSoundChildMod`, `ParticleChildMod`, `SkyBlockTabWidgetHud`,
   `StopwatchChildMod`, `TimerChildMod`.
3. **Obfuscated module names fixed** — three modules still carried hash names; they were
   renamed from their mod id: `2D_ITEMS` -> `Items2d`, `3D_SKINS` -> `Skins3d`,
   `KEYSTROKE_KEY_` -> `CustomKeystrokeKey`.
4. **Categorisation** — from the class name, the mod id, and (for split children) the
   settings keys/strings; e.g. `*Hud`/CPS/FPS/Ping/Coordinates/scoreboards -> `hud`,
   `*Highlight`/`*Overlay`/`*Waypoints`/nametags/animations -> `render`,
   inventory/slot/hide-player features -> `player`, PvP feedback -> `combat`,
   camera/zoom/freelook/sneak-toggle -> `movement`, everything else -> `misc`
   (most Skyblock solvers/trackers).
5. **Duplicates** — the five `RewindHandlers` classes were disambiguated so targets stay
   unique: `RewindHandlers`, `RewindHandlers2`, `RewindHandlers3`, `RewindHandlers4`,
   `RewindHandlers5` (main implementation keeps the plain name).
6. **Helpers** — no class outside the module set has a readable name that references a
   module name (the only readable non-module, non-mixin classes under `client/**` are
   `util/ThreadModuleDump` and `util/Wasapi`), so no extra helper classes were moved.
7. **Inner classes** — every `Outer$...` class of every moved outer was enumerated from
   the jar and remapped to `NewOuter$...` (340 entries, including anonymous classes).

## 16 before/after examples

| old | new |
|---|---|
| `com/moonsworth/lunar/client/framework/feature/attackindicator/AttackIndicator` | `com/moonsworth/lunar/client/mod/combat/AttackIndicator` |
| `com/moonsworth/lunar/client/framework/feature/damagetint/DamageTint` | `com/moonsworth/lunar/client/mod/combat/DamageTint` |
| `com/moonsworth/lunar/client/framework/feature/killsounds/HORHROIOIOICIRHIOCOICHHHIHCIIO_` | `com/moonsworth/lunar/client/mod/combat/KillSoundChildMod` |
| `com/moonsworth/lunar/client/framework/feature/zoom/Zoom` | `com/moonsworth/lunar/client/mod/movement/Zoom` |
| `com/moonsworth/lunar/client/framework/feature/freelook/Freelook` | `com/moonsworth/lunar/client/mod/movement/Freelook` |
| `com/moonsworth/lunar/client/framework/feature/toggle/ToggleSneak` | `com/moonsworth/lunar/client/mod/movement/ToggleSneak` |
| `com/moonsworth/lunar/client/framework/feature/armorstatus/Armorstatus` | `com/moonsworth/lunar/client/mod/render/Armorstatus` |
| `com/moonsworth/lunar/client/framework/feature/armorstatus/armorstatusbarschild/ArmorstatusBarsChild` | `com/moonsworth/lunar/client/mod/render/ArmorstatusBarsChild` |
| `com/moonsworth/lunar/client/framework/feature/mod/impl/fishing/SkyblockFishingCircleOverlay` | `com/moonsworth/lunar/client/mod/render/SkyblockFishingCircleOverlay` |
| `com/moonsworth/lunar/client/framework/feature/pkg46/HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/client/mod/render/Items2d` |
| `com/moonsworth/lunar/client/framework/feature/keystrokes/Keystrokes` | `com/moonsworth/lunar/client/mod/hud/Keystrokes` |
| `com/moonsworth/lunar/client/framework/feature/coordinates/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `com/moonsworth/lunar/client/mod/hud/CoordinatesChildHudMod` |
| `com/moonsworth/lunar/client/framework/feature/mod/impl/gui2/SkyblockVisitorHud` | `com/moonsworth/lunar/client/mod/hud/SkyblockVisitorHud` |
| `com/moonsworth/lunar/client/framework/feature/inventorymod/InventoryMod` | `com/moonsworth/lunar/client/mod/player/InventoryMod` |
| `com/moonsworth/lunar/client/framework/feature/mod/impl/chest/dungeon/SkyblockTerminalSolvers` | `com/moonsworth/lunar/client/mod/misc/SkyblockTerminalSolvers` |
| `com/moonsworth/lunar/client/framework/feature/attackindicator/AttackIndicator$HORHROIOIOICIRHIOCOICHHHIHCIIO` | `com/moonsworth/lunar/client/mod/combat/AttackIndicator$HORHROIOIOICIRHIOCOICHHHIHCIIO` |

## Collision validation

Checks run on the final TSV (all passed):

* 2 columns on every line, 764 rows, sorted by old name, no duplicate old names.
* **0 duplicate targets** (`len(set(new)) == 764`); old and new name sets are disjoint.
* every old class exists in `lunar-all-final.jar` and every new FQCN does **not**.
* every target is under `com/moonsworth/lunar/client/mod/<valid category>/`.
* no mixin is moved: no moved class has `@Mixin`, a `*Mixin` name or a `mixin*`
  package segment.
* every moved inner class corresponds to a moved outer (`Outer$...` -> `NewOuter$...`),
  340 / 340 inner classes covered.

## Deliberately not moved

* 2 descendants living in `mixin*` packages (incl. `ArmorStatusElementChildMod`, which is
  named like a module but lives in `.../armorstatus/nameplate/mixin/`) — mixins are
  handled by `mixin-renames.tsv`.
* 21 other descendants of the module base that are not modules: abstract per-feature
  bases (debug, keystrokes, item counter, calculator, practice, gui, gui3,
  ultrasequencer, pvp) and the 11 `.../rewindhandlers2/` handler/interface classes.
  They keep their current names and are left to `remaining-renames.tsv`.
* Any class outside `com/moonsworth/lunar/client/**`.
