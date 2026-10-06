# Cluster 18 — `framework.feature.keystrokes` + `framework.feature.mod.fishing.gui` (45 rows)

Source revision: `tools/renames/cluster-18.txt` md5 `3ec053fa83d934c7751662a6117e1afc`
(45 rows: 23 in `keystrokes`, 22 in `fishing/gui`; 3 of them nested).
Map: `tools/renames/classes-keystrokes.tsv` md5 `dd143c3a9cce77674db8496104e7b5cb`.

> Brief mismatch: the task text said this cluster was `keystrokes` +
> `armorstatus` with a `Framework7Extension3`. The regenerated
> `cluster-18.txt` actually pairs `keystrokes` with
> `framework.feature.mod.fishing.gui` (no `Framework7Extension3` anywhere).
> The file is authoritative and matches the stated 45 rows, so it is what this
> map covers. `armorstatus` lives in clusters 36/42/48/54/56/57.

## What these two packages actually are

### `framework.feature.keystrokes` — the Keystrokes HUD module

The real Lunar Keystrokes mod (`com.moonsworth.lunar.client.mod.hud.Keystrokes`,
option keys `keystrokesMode`, `keyStrokesClicks`, `leftCPS`, `rightCPS`,
`keyStrokesMovement`, `keyStrokesSpacebar`, `useArrows`, `animate`,
`animationType`, `timerType`, `animation`, `timingFunction`, …). This package is
the per-key rendering half:

* one abstract key base (`Framework7Extension2`, options `boxSize`,
  `borderThickness`, `keyFadeDelay`, `animationType`, `timerType`, `animation`,
  `timingFunction`) with a concrete subclass per key (W/A/S/D/SPACE/LMB/RMB) in
  the sibling `keystrokes.mixin` package;
* a **timer** interface (`Keystrokes2`) with HALF/FULL implementations
  (`Keystrokes2Handler2` / `Keystrokes2Handler`) selected by `timerType`;
* a **renderer** interface (`Keystrokes3`) plus one class per animation style;
* the option enums (`Gui2Extension2` = cubic-bezier timing function,
  `Gui2Extension3` = animation style, `Gui2Extension_2` = timer type);
* a small wrapper (`Keystrokes`, not in this cluster) that pairs timer +
  renderer + easing to animate one key press.

The animation names come straight from the `Gui2Extension3` enum values:
`FILL`, `SMOOTH_FILL`, `RIPPLE`, `COLLAPSE`, `ZIPPER`, `TRIANGULATE`, `SPIRAL`,
`SAND`, `CIRCULAR_FILL`, `MULTI_SQUARE_FILL`, `HEAD_FILL`, `CROSS_COLLAPSE`,
`CROSS_GROW`, `HORIZONTAL_COLLAPSE`, `HORIZONTAL_GROW`, `VERTICAL_COLLAPSE`,
`VERTICAL_GROW`, `DIAGONAL_COLLAPSE`, `DIAGONAL_GROW`. Each maps to a renderer
class, so every concrete class is named after its enum value.

### `framework.feature.mod.fishing.gui` — SkyBlock item registry + shop prices

Despite the `fishing/gui` package name (another scrambled leftover), this is the
SkyBlock **item data / shop-price** stack used by `SkyblockChocolateShopHelper`,
`SkyblockPriceInLore`, `SkyblockVanillaItemModels`, `SkyblockChestProfit`, the
HUDs and the alert/calculator mods:

* `Gui2` — static item registry built from `SkyBlockItemsUtil.getItemsSync()`
  (`ItemsResponse` + id→Item and name→id maps) that resolves an id/name to an
  `ItemStack`;
* `Gui3` — static SkyBlock item helpers (read `skyblock id` from NBT, base64
  NBT decode, `ItemStack` from NBT, dye-colour item builder);
* `Gui4` — codec for `MATERIAL=item_id` bindings (`skyblock-vanilla-items.json`);
* `Gui5` — a required item (id + amount);
* `Gui` (not in this cluster) — `ItemValueResponse` (`success`/`value`/`failureReason`);
* `Gui_2` + `GuiHandler2..11` — the `ItemValueParser` strategy chain that turns
  an item display name into an `ItemValueResponse` (rune, enchanted book, dye,
  essence, shard, shiny, potion, starred, …);
* `Gui2_2` — root model of `item-shop-prices.json` (7 category maps: bits,
  farmingEssentials, farmingTools, barnSkins, greenhouseSkins, pests, chocolate),
  loaded by `Module.method1()` into `Module.field24`; `Gui3_2` = a shop entry,
  `Gui4_2` = a rendered price row, `Gui_3` = a skin, `JsonDeserializerIterator_2`
  = its Gson adapter.

Provenance (`tools/work/mappings/normalize-renames.tsv`): the `_2`/`_3` suffixed
files were flattened from `fishing/gui/mixinCore/mixin/*` and
`fishing/gui/mixinExtra/*`; the digit-free `Gui`, `Gui2`, `Gui3`, `Gui4` are the
same-package originals.

## Renames (45 rows)

### `keystrokes` (23)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `Framework7Extension2` | `KeystrokeKey` | abstract base for one keystroke key; box/border/animation options; concrete subclass per key |
| 2 | `Data4` (nested) | `KeystrokeKeyElement` | `Framework7Extension2$Data4`, `MixinCore9` HUD element that draws the key box/label/CPS/spacebar bar |
| 3 | `Gui2Extension2` | `EasingFunction` | option `timingFunction`: cubic-bezier `EASE`/`LINEAR`/`EASE_IN`/`EASE_OUT`/`EASE_IN_OUT`, `compute(t)` |
| 4 | `Gui2Extension3` | `KeystrokeAnimationStyle` | option `animation`: `FILL`…`DIAGONAL_GROW`, factory of `KeystrokeRenderer` |
| 5 | `Gui2Extension_2` | `KeystrokeTimerType` | option `timerType`: `HALF`/`FULL` timer factories |
| 6 | `Keystrokes2` | `KeystrokeTimer` | timer interface: `progress()`/`rawProgress()` 0..2, `press()`/`release()`, `isDone()` |
| 7 | `Keystrokes3` | `KeystrokeRenderer` | renderer interface: `render(ctx,progress,color,helper)` + `fill`/rounded-fill defaults |
| 8 | `Keystrokes4` | `HorizontalGrowKeystrokeRenderer` | enum `HORIZONTAL_GROW` |
| 9 | `Keystrokes5` | `CollapseKeystrokeRenderer` | enum `COLLAPSE` |
| 10 | `Keystrokes6` | `SandKeystrokeRenderer` | enum `SAND` (8x8 `BitSet` cellular automaton) |
| 11 | `Keystrokes7` | `CircularFillKeystrokeRenderer` | enum `CIRCULAR_FILL` |
| 12 | `Keystrokes8` | `CrossCollapseKeystrokeRenderer` | enum `CROSS_COLLAPSE` |
| 13 | `Keystrokes9` | `HorizontalCollapseKeystrokeRenderer` | enum `HORIZONTAL_COLLAPSE` |
| 14 | `Keystrokes10` | `CrossGrowKeystrokeRenderer` | enum `CROSS_GROW` |
| 15 | `Keystrokes11` | `SpiralKeystrokeRenderer` | enum `SPIRAL` |
| 16 | `Keystrokes12` | `TriangulateKeystrokeRenderer` | enum `TRIANGULATE` |
| 17 | `Keystrokes13` | `VerticalGrowKeystrokeRenderer` | enum `VERTICAL_GROW` |
| 18 | `Keystrokes14` | `MultiSquareFillKeystrokeRenderer` | enum `MULTI_SQUARE_FILL` |
| 19 | `Keystrokes15` | `DiagonalGrowKeystrokeRenderer` | enum `DIAGONAL_GROW` |
| 20 | `Keystrokes16` | `RippleKeystrokeRenderer` | enum `RIPPLE` (`Bridge7Iterator.method117`) |
| 21 | `Keystrokes17` | `FillKeystrokeRenderer` | enum `FILL` |
| 22 | `Keystrokes18` | `HeadFillKeystrokeRenderer` | enum `HEAD_FILL` (player skin head) |
| 23 | `Keystrokes19` | `DiagonalCollapseKeystrokeRenderer` | enum `DIAGONAL_COLLAPSE` |

### `fishing/gui` (22)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 24 | `Gui2` | `SkyblockItemRegistry` | static item registry: `ItemsResponse` + id/name maps → `ItemStack` |
| 25 | `Gui2_2` | `ItemShopPrices` | root of `item-shop-prices.json`, 7 category maps |
| 26 | `Gui3` | `SkyblockItemUtil` | static item helpers: skyblock id, base64 NBT, NBT→`ItemStack`, dye builder |
| 27 | `Gui3_2` | `ShopItem` | shop entry: id/skyblockId/cost/type/displayName/itemModern/skin/requiredItems |
| 28 | `Gui4` | `ItemIdCodec` | parses `MATERIAL=item_id` bindings + validation/map helpers |
| 29 | `Gui4_2` | `ShopPriceLine` | rendered shop price row (name + price + icon) |
| 30 | `Gui5` | `RequiredItem` | required item: id + amount |
| 31 | `Gui_2` | `ItemValueParser` | parser interface: match/parse/cleanup → `ItemValueResponse` |
| 32 | `Gui_3` | `ItemSkin` | skin: id + texture + signature |
| 33 | `JsonDeserializerIterator_2` | `ItemShopPricesDeserializer` | Gson adapter for `item-shop-prices.json` |
| 34 | `GuiHandler2` | `EnchantedBookItemValueParser` | `Enchanted Book (name tier)` → `ENCHANTMENT_…` |
| 35 | `GuiHandler3` | `StarCountItemValueParser` | trailing ✪{1,5} → delegate to `GuiHandler8` |
| 36 | `GuiHandler4` | `DyeItemValueParser` | `name Dye` → `DYE_…` |
| 37 | `GuiHandler5` | `ApiItemValueParser` | direct API lookup by display name |
| 38 | `GuiHandler6` | `EssenceItemValueParser` | `name Essence` → `ESSENCE_…` |
| 39 | `GuiHandler7` | `ShardItemValueParser` | `name Shard` → `SHARD_…` |
| 40 | `GuiHandler8` | `NamedItemValueParser` | exact display-name → id lookup |
| 41 | `GuiHandler9` | `ShinyItemValueParser` | `Shiny (Necron's Handle|Wither …)` |
| 42 | `GuiHandler10` | `PotionItemValueParser` | `name tier Potion` → `POTION;…` |
| 43 | `GuiHandler11` | `StarredItemValueParser` | leading star glyph U+E068 / `STARRED_` prefix |
| 44 | `Data2` (nested) | `ItemIdPair` | `Gui4$Data2`, material-key + item-id pair, `encode()`/`itemId()` |
| 45 | `Type2` (nested) | `DyeColor` | `Gui3$Type2`, 16 dye colours `WHITE`…`BLACK`, `fromIndex` |

All 45 new names were verified unique tree-wide
(`grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java` → 0).

## Applying

Use the **import-aware** applier — the v1 applier skips 16 of the 45 rows
because the old simple names collide across packages:

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-keystrokes.tsv
# → rows=45 skipped=0 files_touched=130 files_renamed=42 mode=dry-run
```

The 3 nested rows (`Data4`, `Data2`, `Type2`) are detected from the
`Owner$Old` evidence and carry the owner's relative file in the 5th column; the
aware applier rewrites `Owner.Inner` qualifiers tree-wide and the bare token only
inside the owner file.

v1 comparison:

```
python3 tools/apply_class_renames.py --map tools/renames/classes-keystrokes.tsv
# → applied=29 skipped=16 files_touched=36 files_renamed=29 mode=dry-run
```

The 16 v1 skips are old-name collisions, not map errors:

| skipped old name | also declared in |
|---|---|
| `Framework7Extension2` | `armorstatus.nameplate.mixin`, `keystrokes.mixin`, `mod.impl.chest.dungeon.practice`, `mod.impl.guiCore`, `mod.impl.ultrasequencer`, `pvpinfo.pvp` |
| `Gui2Extension2` | 13 packages (`blockoutline`, `crosshair.mixin`, `mod.highlight`, `lighting`, …) |
| `Gui2Extension3` | 7 packages |
| `Keystrokes2` / `Keystrokes3` | `com.moonsworth.lunar.client.keystrokes` |
| `Data4` | 5 packages |
| `Gui2` | 10 packages |
| `Gui2_2` | `rewind.gui` |
| `Gui3` | 8 packages |
| `Gui4` | 4 packages |
| `Gui5` | 4 packages |
| `GuiHandler2` | `waypoints`, `inactive.gui` |
| `Gui_2` | `mod.gui`, `rewind.gui` |
| `JsonDeserializerIterator_2` | `mod.fishing` |
| `Data2` | 12 packages |
| `Type2` | 6 packages |

**Do not** run the v1 map with `--allow-collisions`: the bare tokens `Gui2`,
`Gui3`, `Gui4`, `Gui5`, `Data2`, `Type2`, `Framework7Extension2` are declared in
many unrelated packages and a global replace would merge distinct types.

## Caveats / follow-ups

* **Sibling packages not in this cluster.** The concrete per-key class
  `keystrokes/mixin/Framework7Extension2.java` and
  `keystrokes/mixin/Keystrokes.java` are listed in clusters 42/48/54/57; the
  `fishing/gui/mixin/*` models (`Gui`, `Gui2`, `Gui4`, `Gui5`, `GuiType*`) are in
  cluster 34. The aware applier resolves the shared simple names by import, so
  this map only renames the declarations listed in `cluster-18.txt`. Whoever
  owns the `keystrokes.mixin` file should name the concrete subclass something
  like `KeystrokeKeyImpl` (the abstract base here is `KeystrokeKey`, its nested
  HUD component is `KeystrokeKeyElement`).
* `Gui3$Type` (the shop-item kind enum `ITEM/DYE/…`) is a separate file
  `Gui3$Type.java` and is **not** in `cluster-18.txt`; it keeps its name for now
  and is referenced as `Gui3.Type` by `Gui3_2`/`JsonDeserializerIterator_2`.
* The `fishing/gui` package name is a decompiler leftover; the real feature is
  SkyBlock item data + shop prices. A future package move (e.g.
  `framework.feature.mod.skyblock.items`) would be more accurate than the
  `fishing.gui` path, but that is out of scope for a class-rename map.
* `Gui` (top-level, `ItemValueResponse`) and its nested `Gui.Type` keep their
  names (not lazy / not listed); the parser names above deliberately reference
  "ItemValue" to match that class.

## Verification

```
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-keystrokes.tsv
# → rows=45 skipped=0 files_touched=130 files_renamed=42 mode=dry-run
python3 tools/apply_class_renames.py --map tools/renames/classes-keystrokes.tsv
# → applied=29 skipped=16 files_touched=36 files_renamed=29 mode=dry-run
```