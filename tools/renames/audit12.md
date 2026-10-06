# Cluster 12 package audit — itemtracker / keystrokes / killsounds / knockbacktrainer / lightoverlay / markers

Source revision: `tools/renames/audit-cluster-12.txt` (8 package names; all have
`.java` files). This is an **accuracy audit** of the current tree, after the
wave-2/3 rename batches. Earlier waves renamed the *lazy* names that a simple
inventory flagged; this pass looks for the other failure mode: plausible names
that survive because they look right, plus whole packages whose name does not
describe their contents.

Ground truth used: class bodies, the feature option enums
(`KeystrokeAnimationStyle`, `KeystrokeTimerType`, `KillsoundsType`), the callers
(`mod/hud/Keystrokes`, `mod/combat/KillSounds`, `mod/render/*`,
`guiRewindhandlers/KnockbackListener`), `tools/mappings-snapshot/normalize-renames.tsv`
and `restructure/{remaining,module}-renames.tsv`, and the real Lunar/Apollo names
table (`ModItemTracker`, `ModKeystrokes`, `ModKillSounds`, `ModKnockbackTrainer`,
`ModLightOverlay`, `ModMarkers`).

## What each package really is

| package | real subsystem | verdict |
|---|---|---|
| `...feature.itemtracker` | the ItemTracker HUD's slot-count accumulator | package name OK, class name lazy |
| `...feature.keystrokes` | Keystrokes HUD: key model, options, animation engine | package OK, 8 stale names |
| `...feature.keystrokes.mixin` | *(misnomer)* keystroke layout + default key | **package misplaced** |
| `...feature.killsounds` | KillSounds HUD: per-kill-sound settings + type enum | package OK, 2 stale names |
| `...feature.killsounds.mixin` | *(misnomer)* kill-detection engine, chat parsers, filter | **package misplaced** |
| `...feature.knockbacktrainer.mixin` | *(misnomer)* the KnockbackTrainer event payload | **package misplaced**, class wrong |
| `...feature.lightoverlay` | LightOverlay render strategies (cross / box) | package OK, 2 stale names |
| `...feature.markers` | marker icon registry (item/entity↔icon mappings) + marker manager | package OK, 15 stale names |
| `...feature.markers.mixin` | *(misnomer)* marker team/source detection | **package misplaced** |

## (a) Misleading / wrong class names

### itemtracker (1)
* `Itemtracker` → **`ItemTrackerData`**. It is *not* the ItemTracker HUD (that is
  `mod.misc.ItemTracker`); it is the per-name slot-count accumulator
  (`Object2ObjectMap<String,List<Data>>`, `sum`/`sum-excluding-slots`/`add`/`remove`).
  Note the lowercase `t` also makes it look like a package name.

### keystrokes (8)
The previously applied batch renamed `Keystrokes2→KeystrokeTimer`,
`Keystrokes3→KeystrokeRenderer`, `Keystrokes4..19→<Style>KeystrokeRenderer`, etc.,
but skipped the classes that sit between those numbers. The current
`KeystrokeAnimationStyle`/`KeystrokeTimerType` enums pin down their identities:

* `Keystrokes` → **`KeystrokeAnimation`** — a per-press animation instance
  bundling timer + renderer + easing (created in `KeystrokeKey.KeystrokeKeyElement`).
* `Keystrokes19Impl` → **`SmoothFillKeystrokeRenderer`** (`SMOOTH_FILL`).
* `Keystrokes19Impl2` → **`VerticalCollapseKeystrokeRenderer`** (`VERTICAL_COLLAPSE`).
* `Keystrokes19Impl3` → **`ZipperKeystrokeRenderer`** (`ZIPPER`).
* `Keystrokes2Handler` → **`FullKeystrokeTimer`** (`FULL`).
* `Keystrokes2Handler2` → **`HalfKeystrokeTimer`** (`HALF`).
* `Keystrokes2Handler3` → **`SyncedKeystrokeTimer`** (used when `SYNCED`).
* `Gui2Extension` → **`KeystrokeAnimationType`** (option `animationType`,
  `SYNCED`/`STACKED`).

### killsounds (5)
* `Killsounds` (root) → **`KillSoundSettings`** — its own `toString()` already
  says `"KillSoundSettings"`. Plausible-but-wrong because the root package name
  is reused as a class name for a settings model.
* `KillsoundsType` → **`KillSoundType`**.
* `Killsounds` (in `.mixin`) → **`KillSoundTracker`** — the event-driven kill
  detector (projectile correlation + 15s `KillEvent` cache).
* `Killsounds4Loader` → **`ChatPatternKillMessageParser`** (loads
  `kill-sound-chat-patterns.json`).
* `Killsounds4Loader2` → **`DeathMessageKillParser`** (translatable `death.*`).

### knockbacktrainer (1)
* `Knockbacktrainer` → **`KnockbackEvent`** — not a trainer or a mixin but a
  `Highlight` event (boolean + `DamageSourceBridge`) published by
  `KnockbackListener` on the `LunarEventBus`.

### lightoverlay (2)
* `Lightoverlay` (interface) → **`LightOverlayRenderer`** — a strategy interface
  (`render`/`end`), and it collides by simple name with
  `client.lightoverlay.Lightoverlay` (the TraitType/options framework).
* `LightoverlayHandler` → **`LightOverlayCrossRenderer`** — it draws the X/cross
  variant; the sibling `LightOverlayBoxRenderer` is already correct.

### markers (15)
* `Markers` → **`MarkerManager`** — the live marker store/broadcaster.
* `Markers2_2` → **`WoodTypeIconMapping`** (planks/sapling/slab wood species).
* `Markers3_3` → **`ItemBoatIconMapping`** — item boats; distinct from the
  entity `BoatIconMapping`.
* `Markers9Impl` → **`StoneBrickIconMapping`**.
* `Markers12Base` → **`SuffixIconMapping`** (abstract, keyed by registry suffix).
* `Markers12Base2` → **`LeavesIconMapping`**, `Markers12Base3` → **`LogIconMapping`**.
* `Markers12Impl`..`Markers12Impl6` → **`TerracottaIconMapping`**, **`ScuteIconMapping`**,
  **`GrassIconMapping`**, **`SignIconMapping`**, **`SlabIconMapping`**, **`TrapdoorIconMapping`**.
* `SExtension` → **`HorseIconMapping`** (registered in `EntityIconRegistry`).
* `SIterator` → **`IconRegistry`** (base of `EntityIconRegistry`/`ItemIconRegistry`).
* `Gui2Extension` (in `.mixin`) → **`MarkerTeam`** (`HYPIXEL_PARTY`/`LUNAR_FRIENDS`/
  `APOLLO`/`SCOREBOARD`/`NAME_COLOR`).

The already-applied names in `markers` (`BannerIconMapping`, `BlockVariantIconMapping`,
`BoatIconMapping`, `DyeColorIconMapping`, `DynamicMapping`, `EntityIconRegistry`,
`FenceIconMapping`, `IconMapping`, `ItemIconRegistry`, `LegacyItemAliasMapping`,
`NetherBrickIconMapping`, `RedstoneComponentIconMapping`, `SkullIconMapping`,
`SpawnEggIconMapping`, `WoodenBlockIconMapping`) were checked and are accurate —
left unchanged. `KeystrokeKey`, `KeystrokeRenderer`, `KeystrokeTimer`,
`KeystrokeTimerType`, `KeystrokeAnimationStyle`, `EasingFunction`, `KillEvent`,
`KillMessageParser`, `KillSoundFilter` are likewise correct.

## (b) Wrong / misplaced package names

All four finds are `.mixin` buckets with **zero `@Mixin` classes** — the suffix is
a decompiler artifact of the `…/mixinCore/mixin/…` jar folders, not a real mixin
package. See `packages-audit12.tsv` for the machine-readable map.

| old | new | contents |
|---|---|---|
| `…feature.keystrokes.mixin` | `…feature.keystrokes` | `KeystrokeLayout`, `DefaultKeystrokeKey` |
| `…feature.killsounds.mixin` | `…feature.killsounds` | `KillSoundTracker`, parsers, `KillSoundFilter`, `KillEvent` |
| `…feature.knockbacktrainer.mixin` | `…feature.knockbacktrainer` | `KnockbackEvent` |
| `…feature.markers.mixin` | `…feature.markers` | `MarkerTeam`, `MarkerSource`, `MarkerDetectionFunction` |

`…feature.itemtracker`, `…feature.keystrokes`, `…feature.killsounds`,
`…feature.lightoverlay`, `…feature.markers` are correctly named and stay put.

## Applier notes / ordering

* Use the **import-aware** applier for the class map
  (`tools/apply_class_renames_aware.py --map tools/renames/classes-audit12.tsv`):
  the old simple names `Gui2Extension` (keystrokes + markers.mixin and 14 other
  packages), `Keystrokes` (keystrokes + keystrokes.mixin), and `Killsounds`
  (killsounds + killsounds.mixin) are declared in more than one package; the v1
  applier would skip them.
* **Apply the class map before the package map.** `keystrokes.mixin/Keystrokes.java`
  and `killsounds.mixin/Killsounds.java` would otherwise `git mv -f` onto the
  existing `keystrokes/Keystrokes.java` and `killsounds/Killsounds.java`.
* All 34 new simple names were checked tree-wide with
  `grep -rE "\b(class|interface|enum|record) <New>\b" src/main/java`; none is
  already declared.
* Stale/partial-tree references (`Markers2`, `Markers2_3`, `Markers3_2`,
  `markers.mixin.Markers`, missing `mod.render.Markers`/`mod.combat.KillSounds`
  siblings) exist in the tree; they are unchanged dangling tokens, not new
  collisions. The aware applier will not rewrite same-package-only bare names in
  other packages unless the import resolves to the renamed FQN.

## Verification

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/classes-audit12.tsv
[aware-renames] 34 rows (2 nested); 6594 java files
[aware-renames] rows=34 skipped=0 files_touched=50 files_renamed=34 mode=dry-run

$ python3 tools/apply_package_renames.py --map tools/renames/packages-audit12.tsv
[pkg-renames] rows=4 files_moved=12 files_touched=19 mode=dry-run
```

Run the class map first (see ordering note above). The package dry-run lists
`keystrokes/mixin/Keystrokes.java -> keystrokes/Keystrokes.java` and
`killsounds/mixin/Killsounds.java -> killsounds/Killsounds.java`; after the class
map those files are `KeystrokeLayout.java` / `KillSoundTracker.java`, so the
moves no longer collide.
