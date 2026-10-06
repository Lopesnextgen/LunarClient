# Cluster 20 — `com.moonsworth.lunar.client.framework` (top level)

25 classes. This is the **feature/mod framework**: per-mod interfaces stored under
reserved keys on `Framework7Extension`, the HUD component model, and the client
command system. Names follow the role, with real Lunar names used where the
decompiled code still leaks them (`HudSize`, `HudComponentValue`).

## Names

| old | new | role |
|---|---|---|
| `MixinCore2` | `HudSize` | immutable HUD min/max size (real name in `toString`) |
| `MixinCore3` | `HudLine` | one HUD line: optional item/icon texture + adventure text |
| `MixinCore5` | `HudComponent` | renderable HUD unit (`clearCache/getWidth/getHeight/render`) |
| `MixinCore5Iterator` | `HudComponentGroup` | horizontal/vertical group with alignment, spacing, filler |
| `MixinCore5Handler_2` | `HudComponentWrapper` | generic self-typed wrapper base + fluent `setComponent` |
| `MixinCore5Handler2` | `HudComponentDecorator` | abstract delegating base with protected delegate |
| `MixinCore5Handler2_2` | `TextureHudComponent` | fixed-size texture component |
| `MixinCore5Handler2_3` | `ToggleHudComponent` | two-state component (boolean/Supplier switch) |
| `MixinCore5Handler4` | `ItemStackHudComponent` | item stack icon + count overlay + scale |
| `MixinCore5Impl2` | `BackgroundHudComponent` | paints colour background behind wrapped component |
| `MixinCore5Impl3` | `PlaceholderHudComponent` | empty wrapper subclass (unused) |
| `MixinCore6` | `HudComponentValue` | supplier-backed cached value (real name in exception string) |
| `MixinCore7` | `HudComponentStyle` | fluent 5-property style interface for components |
| `Framework2` | `ModCategories` | key `label`: `Set<RewindhandlersType>` categories |
| `Framework4` | `ChildModBinding` | key `child`: parent feature + visible-when condition |
| `Framework5` | `OptionContainer` | key `options`: option set + load/save |
| `Framework6` | `PageState` | key `page`: mutable boolean with fluent setter |
| `Framework7` | `ModRestriction` | key `restriction`: nameplate/server restriction condition |
| `Framework10` | `ModDisplay` | key `display`: mod-menu thumbnails, seen/favorite, render |
| `Framework11` | `DynamicCondition` | key `dynamic`: runtime visibility condition |
| `Framework12` | `PanelPosition` | key `panel`: x/y + panelIndex + reset |
| `MixinHelper2` | `CommandArguments` | typed command-argument accessor |
| `MixinHelper22` | `BoundArguments` | Map-backed `CommandArguments` impl |
| `MixinHelper3` | `CommandParser` | walks the command tree and dispatches |
| `MixinNameplate2` | `ClientCommand` | registered client command (name/execute/suggestions) |

Key evidence: `Framework`'s static init maps `field2..field21` to the reserved key
strings above; `Framework7Extension2` subclasses register each value with
`method2/3/…(Framework.fieldN, …)`. `Nameplate6` implements `Framework5`
(`options`) and `Nameplate10` implements `Framework12` (`panelIndex` JSON), so the
`Framework*` names come from behavior, not spelling.

## Apply notes

* `MixinCore2`, `MixinCore3`, `MixinHelper2`, `MixinHelper22`, `MixinHelper3` are
  also declared in other packages (unrelated classes with the same placeholder
  name). `apply_class_renames.py` will **skip those rows** while the collision
  exists. Most colliding `MixinHelper*` copies are owned by other clusters
  (03/25/26/33/35/38/45/47/51/54/56); `MixinCore2` is also in cluster-38.
  `com.moonsworth.lunar.forge.lib.MixinCore3` is **not in any cluster**, so do not
  run this map with `--allow-collisions` until that class is out of the way
  (otherwise every `MixinCore3` reference, including forge-lib ones, becomes
  `HudLine`).
* Nested types (`MixinCore5Iterator.Data`, `.Data2`, `.Type`) are not touched by
  the class applier; a follow-up nested/member pass can name them
  (`AlignedComponent`, `Spacer`, `Alignment`).
* Siblings left unnamed by any cluster (`MixinCore`, `MixinCore5Impl`,
  `MixinCore5Handler`, `MixinHelper`, `MixinNameplate`, `MixinNameplateImpl`,
  `MixinNameplateIterator`, `Framework`, `FrameworkType`, `Framework8/9/13`,
  `AlertExtension`, `Framework10Extension`) keep their tokens; the chosen names
  intentionally do not collide with the likely roles of those
  (`HideableHudComponent`, `EmptyHudComponent`, `CommandNode`, command bases).
