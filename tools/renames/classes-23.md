# cluster-23 notes — `com.moonsworth.lunar.client.highlight.mixin.highlight`

## What this package actually is

**Not** a block/entity "highlight" feature. The whole `com.moonsworth.lunar.client.highlight`
tree is the client's **event system** (a Forge-like event bus plus its event types), and
`mixin.highlight` is one category of event classes (events fired by rendering mixins).
Evidence:

* `Highlight2` is a listener registry (`register/unregister/post`, `ReentrantLock`,
  listener priorities, PRE/POST/BOTH dispatch, `"EventBus [...]"` diagnostics).
* `Highlight2Iterator`'s ctor throws `IllegalAccessError("DebuggingEventBus is being used in production environment!")`.
* Error strings: `"...does not extend DynamicListenerEvent"`, `"...make it implement ThreadedEvent"`,
  and the bus checks `clazz1.getSimpleName().contains("Event")` when naming listeners.
* The codebase's own string constants show the real naming convention:
  `EventRenderTooltip.Pre` (`HighlightImpl17`), `EventPreAttackEntity` (`highlight/fishing`).
* Every class here extends the event base (`Highlight` / `HighlightImpl` / `HighlightImpl2` /
  `ResultEvent`) and is constructed inside a `Highlight2.method12(Class, Supplier)` post call
  in a mixin, then consumed via `handle(Class, Consumer)`.

So names below **do not use the mixin convention** (`XxxTargetMixin`) — these are plain event
data classes, named after the codebase's own `Event<Purpose>` convention where possible.

## Key type identifications used as evidence

| type | identification |
|---|---|
| `BridgeExtension222` (`bridge$getUniqueID`, `getVehicle`, `isTextDisplay`) | `Entity` |
| `BridgeExtension2_2` / `BridgeExtension2_5` (sleeping/deathTime/hurtTime/headItem) | `EntityLivingBase` |
| `BridgeExtension_4` (`bridge$getStackSize`) | `ItemStack` |
| `BridgeExtension_9` (`translate`/`scale`/`color`, `method32()` = new pose) | `GlStateManager` |
| `Bridge_8` (translate/scale/rotate stack) | pose/matrix stack |
| `BridgeExtension3_2` (`getEntityId`, `getRotationYaw`, `isOnGround`) | `Entity` (item entity parent) |
| `Bridge2_20` (`GuiButtonMixin implements Bridge2_20`) | `GuiButton` |
| `Bridge7_8` (`ThreadModuleDump63.method31(screen)` resolves slots/screens) | `GuiScreen` |
| `Bridge4_12` (rgb/alpha/particle type/firework) | `EntityFX` (particle) |
| `Bridge11_5` (empty marker) | `EntityGuardian` |
| `Bridge3_24` (`framebufferWidth/Texture/blitToScreen`) | `Framebuffer` |
| `Bridge3_18` / `Bridge5Extension_3` | font renderer / draw context of GUI screens |
| `Bridge5Extension6` | screen (GUI) |
| `Bridge6_10` | unresolved (hologram/packet payload, see follow-ups) |
| `MixinHelper_4` (bridge package, quarantined here) | screen (GUI) |
| `MixinHelper_14` | item/baked model-ish (only used inside drop-item transforms) |
| `Bridge5_16`, `Bridge17`, `Bridge4_12`, `Bridge10_2`, `MixinHelper_13` | quarantined/missing in tree |

## Firer summary (event -> hook)

| event | fired from |
|---|---|
| `EventSetupTerrain` (`HighlightImpl7`) | `RenderGlobal.setupTerrain` end (`RenderGlobalEntityOutlineMixin`) |
| `EventChunkReload` (`HighlightImpl8`) | `RenderGlobal.loadRenderers` return |
| `EventRenderEntities` (`HighlightImpl24`) | `RenderGlobal.renderEntities` "entities" marker |
| `EventRenderNameTag` (`HighlightImpl11`) | `RendererLivingEntity.renderName` / `passSpecialRender` |
| `EventRenderItemGlint` (`HighlightImpl3`) | `RendererLivingEntity.doRender` glint constant |
| `EventRenderDroppedItem`/`EventRenderItemRotation`/`EventGroundItemTransform`/`EventRenderItemClump` | legacy `RenderEntityItemMixin` / `RenderEntityItemPhysicsMixin` |
| `EventRenderBossBar` (`HighlightImpl10`) | boss-bar render (handlers cancel) |
| `EventPostProcess` (`HighlightImpl27`) | post-processing shader path (MenuBlur/MotionBlur/ColorSaturation) |

## Confidence

High: `EventRenderNameTag`, `EventRenderBossBar`, `EventRenderItemClump`, `EventRenderItemGlint`,
`EventSetupTerrain`, `EventChunkReload`, `EventRenderEntities`, `EventRenderGuardian`,
`EventRenderDroppedItem`, `EventRenderItemRotation`, `EventGroundItemTransform`,
`EventPostProcess`, `EventRenderCrosshair`, `EventFovModifier`, `EventCameraOffset`,
`EventRenderArmor`, `EventRenderPlayer`, `EventRenderItemStackSize`.
Medium (payload-based): `EventRenderVanillaHud`, `EventRenderEntityLabel*`, `EventHologramUpdate`,
`EventAlertUpdate`, `EventRenderEntityOffset`, `EventRenderItemColor`, `EventRenderModel`,
`EventRenderInventoryScreen`, `EventRenderEntity*`.
Low (inference only, verify): `EventFovModifierPre/Post` stage ordering (`HighlightImpl20$Data*`),
`EventRenderItemColor` (`HighlightImpl15`).

## Needs follow-up (no TSV row — evidence not strong enough)

Nested stage/variant classes. The applier cannot rename `Outer$Inner` rows safely anyway
(its regex matches simple names only), so they are listed here for manual handling:

| class | status / recommendation |
|---|---|
| `HighlightBase2$Data10`, `HighlightBase2$Data11`, `HighlightBase2$Data12` | identical (screen+x+y) stage variants; users split across `ProtectItem`, `SlotLocking`, `HotbarKeyOverlay`, `ItemDropProtection` (Data10) and `RarityBackground` (Data11). Need the real mapping or dynamic tracing to tell Pre/Post apart. |
| `HighlightImpl15$Data5` | empty subclass of main `HighlightImpl`, no users found. |
| `HighlightImpl23` (`extends HighlightImpl2` + float) | no users in tree; likely a result-gated render event (probably crosshair-related) but unproven. |
| `HighlightImpl25` (`extends HighlightImpl2`) | no users in tree; unproven. |
| `HighlightImpl20$Data`/`Data2`, `HighlightImpl5$Data2`/`Data3` | included in the map with best-evidence names; `EventFovModifierPre/Post` ordering is inferred from handler behaviour, verify. |

Other packages use the same class names (`highlight/fishing`, `highlight/mixin/gui`, …). The
applier skips rows whose simple name is declared in more than one package unless
`--allow-collisions` is given; **do not use `--allow-collisions` for this map** — it would
rewrite every `HighlightImplN` occurrence tree-wide.

## Dry-run result (important)

`python3 tools/apply_class_renames.py --map tools/renames/classes-23.tsv`:
`37 rows`, only **5 applied** (`HighlightImpl24/26/27/28/29` — the only uniquely-declared
names), 32 skipped (31 simple-name collisions + 6 nested `Outer$Inner` rows). To actually apply
this map the applier needs package/import-aware replacement (e.g. rename occurrences that
resolve to the declaring package, not every word-boundary token).

Extra row not present in `cluster-23.txt`: `HighlightImpl` (the abstract dropped-item event) —
it is a lazy top-level class in the same package, included for completeness.

