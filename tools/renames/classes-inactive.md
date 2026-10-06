# Cluster inactive — `com.moonsworth.lunar.client.hitbox` + lazy `com.moonsworth.lunar.client.inactive`

Map: `tools/renames/classes-inactive.tsv` (9 rows).

## What these clusters actually are

* `client.hitbox` is **not** the combat Hitbox mod — it is the **stat-HUD row
  layout system** used by `PvpInfoStatHud`, `HypixelBedwarsResourceCounterChild`,
  `HypixelBedwarsStatsChild` and the SkyBlock HUDs: `HudRow` = one renderable
  row (render/width/height), `Hitbox` = vertical layout engine + row factories.
* `client.inactive` is the **GeckoLib-style cosmetic animation engine**:
  bedrock model deserializers (`rewindhandlers/`), Molang support
  (`MolangDeserializer`, `MolangResourceProvider`), pet AI tasks
  (`mixin/fishing/`). `Inactive3` (pet entity) and `inactive.mixin.Gui2Handler`
  (cosmetic definition model) have no decompiled source; only their
  deserializer companions survived.

## Renames (9 rows)

| # | old | new | why |
|---|---|---|---|
| 1 | `Hitbox2Handler` | `TextHudRow` | string + color, font draw, width = stringWidth |
| 2 | `Hitbox2Handler2` | `SpacerHudRow` | renders nothing, fixed width; 4px icon/count gap |
| 3 | `Hitbox2Handler3` | `HorizontalHudRow` | left-to-right composite, width = sum + padding |
| 4 | `Hitbox2Handler4` | `ItemIconHudRow` | itemstack icon 16x15 via `MixinHelper_4.method37` |
| 5 | `Gui2Extension` | `HudRowAlignment` | LEFT/CENTER/RIGHT, `alignLeft/Center/Right` ids |
| 6 | `Inactive2_2` | `WanderPositionResolver` | random-offset wander target, height/path validated |
| 7 | `Inactive3_2` | `CosmeticDefinitionMapper` | Jackson mapper, `geckolib-cosmetic-definition.json` |
| 8 | `Inactive3$Data2` | `AnimationBuilderDeserializer` | `{name, loop}` → GeckoLib `AnimationBuilder` |
| 9 | `Inactive3$Data5` | `OffsetDateTimeDeserializer` | multi-format date parser via `Inactive3_2.field1` |

## Skipped (not lazy, left as-is)

* `Hitbox` (factory + vertical layout engine) and `HudRow` (row interface)
  have accurate digit-free names; renaming them would churn every stat HUD.
