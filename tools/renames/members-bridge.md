# Members — bridge render-context cluster (`MixinHelper_4`, fonts, render types)

Member-rename map for the render/pose/font/texture owners reached from
`client/mod/combat/AttackIndicator` and `client/click/holograms/Bridge7Iterator`.

* Map: `tools/renames/members-bridge.tsv` (298 rows, 54 owners)
* Apply: `python3 tools/apply_member_renames.py --map tools/renames/members-bridge.tsv [--apply]`
* No sources were modified by this pass; every declaration was checked against
  the live source and `libs/lunar-renamed-classes.jar` (`javap -p`).
  Validation done here: all 54 owners resolve to a source file, all 298 rows
  match a declaration (exception/notes below), no duplicate old names and no
  duplicate target names per owner. The full applier **dry-run was not
  completed** in this session (killed under concurrent load), so run
  `apply_member_renames.py` without `--apply` once before applying.

## Owners covered

| owner | rows | what it is |
|---|---|---|
| `bridge.MixinHelper_4` | 50 | abstract GUI/pose render context (push/pop/translate/scale/rotate, fill, drawString, blit, item render) |
| `bridge.MixinHelper5` | 26 | classic/legacy implementation over `BridgeExtension_9` (overrides) |
| `bridge.MixinHelper6_5` | 30 | modern implementation over `Bridge8_2`/`Bridge5_16` (overrides + scissor helpers) |
| `bridge.MixinHelper5_6` | 99 | static render-type registry (64 texture->RenderType caches + 35 init/factory methods) |
| `bridge.Bridge10_2` | 20 | font-renderer bridge (draw/measure/centering) |
| `bridge.horsestats.Horsestats14` | 5 | `ResourceLocation` bridge |
| `bridge.RenderHelperBridge` + `Bridge3_35` + `legacy.wrapper.Bridge3Iterator` | 21 | global `Bridge.method14()` helper (item lighting, nameplates, menu panorama) and its lazy twin/implementation |
| `bridge.Bridge` | 2 | `method14`/`method15` get/set accessors |
| `bridge.BridgeExtension_4` | 1 | ItemStack enchantment level |
| `bridge.GuiScreenBridge` + `Bridge5Extension6` | 2 | instance check incl. wrapped driver screen (new + lazy twin) |
| `bridge.Bridge5Extension612` + `GuiBridge` family | 40 | driver-screen title override (`getScreenTitle`) |
| `client.click.holograms.Bridge7Iterator` | 2 | `method46`/`method94` GUI helpers |

### Naming families used

* pose: `push`/`pop`/`translate`/`translateXY`/`scale`/`scaleXY`/`rotate`/`rotateDegrees`
* fills: `fill`/`fillFloat`/`fillRect`/`fillRectFloat` + `fillGradient*`
* text: `drawString*` (Component), `drawText*` (`Bridge2_42`), `drawString*Raw*` (String),
  `*NoBidi` (modern impl uses `bridge$drawStringNoBidi$v1_20_0`), `drawCentered*`
* font: `bridge$getStringWidth` kept, `method19` -> `getFontHeight`
* textures: `blit` (whole texture), `blitRegion` (u/v + texture-size scale), `blitRegionGlint`
* render contexts: `drawCustom` (render type + vertex-sink consumer)
* modern/legacy views: `withLegacyGraphics`/`withModernGraphics`, `asLegacyGraphics`/`asModernGraphics`,
  `getGuiGraphics`/`getModernGuiGraphics`, `isLegacyGraphics`

## Horsestats14 class rename (required note — NOT in the tsv)

`com.moonsworth.lunar.bridge.horsestats.Horsestats14` -> **`ResourceLocationBridge`**

Evidence:

* class-level `@Annotation` maps it to `net/minecraft/util/ResourceLocation` (v0),
  `net/minecraft/resources/ResourceLocation` (v6) and `net/minecraft/resources/Identifier` (v35);
* API: `create(String,String)`, `create(String)`, `createWithDefaultNamespace`, `bridge$getDomain`,
  `bridge$getPath`, `isValidPath`, `validPathChar`, `validNamespaceChar`, `isValidResourceLocation`;
* package siblings already follow the `<Vanilla>Bridge` convention (`Vec3iBridge`,
  `EnumFacingBridge`, `DamageSourceBridge`, `ChatComponentFactoryBridge`, `KeyBindingEntry`);
* no `ResourceLocationBridge` exists anywhere in the tree today (checked), so the class applier
  should be collision-free. Apply the class map first; this map's rows use the old FQN as owner.

Members mapped: `method1` -> `createWithDefaultNamespace`, `method2` -> `isValidResourceChar`,
`method3` -> `isValidNamespace`, `method4` -> `isValidResourceLocation`, `method5` -> `split`.

## Notes for whoever applies the map

1. **`::` method references in `MixinHelper5_6`.** The static factory methods (`method5`..`method34`)
   are referenced *only* through `MixinHelper5_6::methodN` method references (29 occurrences in that
   file), and `apply_member_renames.py` only rewrites `receiver.method` accesses, not `::`. Either
   add `::` to the access regex before applying, or hand-rewrite those 29 refs to the new names
   listed in the tsv (`method5` -> `createVignette`, ... `method34` -> `createSprays`).
2. **`BridgeExtension_4.method1` declaration.** Its source line is
   `default @Range(from = 0L, to = 255L) int method1(Bridge2_26 var1) {` — the applier's declaration
   regex does not allow an annotation between modifier and return type, so the call sites would be
   renamed while the declaration is not. Hand-edit that one line to `getEnchantmentLevel` when
   applying (or add `@\w+(\.\w+)*(\(...\))?` after the modifiers).
3. **Duplicate class files for the title family.** The `Gui*Bridge` renames were applied to copies,
   so both `Bridge5Extension10.java` and `GuiScreenResourcePacksBridge.java` (etc.) declare
   `method1()`. Rows exist for both spellings; after the stale `BridgeN*` files are dropped the
   duplicate rows are harmless (the applier skips missing sources only if files are gone).
4. **Override rows.** Per the "every declaring class" rule, the `MixinHelper_4` names are repeated
   for `MixinHelper5` (26 overrides) and `MixinHelper6_5` (26 overrides + 4 scissor methods).
   `FontRendererMixin`, `FontRendererMixin3` and `v1_7.FontRendererMixin` implement `Bridge10_2`
   but do not redeclare any of its placeholder methods (they only provide `bridge$*` methods), so
   no rows were needed there.
5. **`RenderTypeBridge` and `Bridge5Extension612` need no member rows.** `RenderTypeBridge`'s
   surface is already `bridge$*`-named; `Bridge5Extension612` only adds named `bridge$getInitialText`
   / `bridge$isSuggestionOverlayVisible` / `bridge$isBedChat` (its `method1()` title override is
   covered by the family rows).
6. **Cast receivers are not rewritten by the current applier.** The six call sites
   `((Bridge10_2)fontX).method6(...)` in `legacy/mixin/RendererLivingEntityMixin` and
   `mixin/mixin/RendererLivingEntityMixin` must be hand-rewritten to `drawTextComponent` (the
   applier's access regex only handles `name.method`/`this.field.method`). Check ECJ for any other
   renamed member reached through a cast or chained call; the `::` refs from note 1 are the same
   class of gap.

## Ambiguities / known quirks (do not “fix” silently)

* **`MixinHelper_4.method18` and `method22` have identical descriptors**
  (`(Bridge10_2,String,IIZ)V`) in the compiled jar and source — impossible in javac, a remap
  collision. They were given different names because the modern implementation distinguishes them:
  `method18` goes through `Component.text` (bidi), `method22` calls
  `bridge$drawStringNoBidi$v1_20_0` -> `drawStringRaw` vs `drawStringNoBidi`. If the duplicate is
  removed later, keep both names available.
* **`MixinHelper5` private `method14` overload.** `private void method14(MixinHelper6_3,
  Horsestats14, ...)` (texture draw helper) shares its old name with the public
  `method14(Bridge10_2, Component, ...)` override. The map can only rename one old name per owner,
  so applying it renames both to `drawStringStyled`; if you want the private helper to read
  `drawTexture`, edit that declaration by hand afterwards.
* **`MixinHelper6_5` generated aliases.** `method31()`/`method32()` (Generated getters returning
  `Bridge8_2`/`Bridge5_16`) share names with renamed overrides and will become `drawLine()` /
  `drawLines()`. Different descriptors, so it compiles; deleting the generated aliases is cleaner.
* **`AttackIndicator.java:377` dangling field.** Batch 0 (`0ce302152`) renamed the local hud field
  `field12` -> `alwaysShow` and also rewrote `MixinHelper5_6.field12` -> `MixinHelper5_6.alwaysShow`,
  but `MixinHelper5_6` has no such field. The field is the `"lunar_crosshair"` render-type cache
  (`Crosshairelytra3`/`CrosshairTextureRenderer` use it as the crosshair RT), so after applying this
  map that line should read `MixinHelper5_6.crosshair`.
* **`MixinHelper5_6.field8` vs `field9`** are two caches for the same render-type name
  `"lunar_entity_cutout_no_cull"` (256 vs 1536 buffer and different pipelines) -> named
  `entityCutoutNoCull` / `entityCutoutNoCullLarge`.
* **`RenderHelperBridge.method8/9/10` left unnamed.** `method1/2/3` are documented in
  `classes-bridge34.tsv` as `RenderHelper` lighting, `method4/5` are world-space nameplates and
  `method6/7` the main-menu panorama; for `method8(pose, player)`, `method9()` and `method10()`
  the call sites (player layers, color-saturation pipelines) did not pin the semantics down, so
  they were deliberately left out instead of guessed. The same three methods in the lazy twin
  `Bridge3_35`/`Bridge3Iterator` stay `method8/9/10` for the same reason.
* **`MixinHelper_4.method51/method52`** exist only in `libs/lunar-renamed-classes.jar` (private
  static helpers) and have no source declaration — skipped.
* **`Bridge.method8`/`method9`** (resource manager / Minecraft singleton) are still placeholder
  names in evidence strings because they are outside this batch's scope.
* **Out-of-scope class-name suggestions** (report only): `MixinHelper_4` -> `GuiGraphicsBridge`,
  `MixinHelper5` -> `LegacyGuiGraphics`, `MixinHelper6_5` -> `ModernGuiGraphics`,
  `Bridge10_2` -> `FontRendererBridge`, `MixinHelper5_6` -> `RenderTypeCache`,
  `Bridge5Extension612` -> `GuiChatBridge` (it is the `@Mixin GuiChat`/`ChatScreen` duck).

## Source/evidence commands

```
javap -p -classpath libs/lunar-renamed-classes.jar com.moonsworth.lunar.bridge.MixinHelper_4 \
      com.moonsworth.lunar.bridge.MixinHelper5 com.moonsworth.lunar.bridge.MixinHelper6_5 \
      com.moonsworth.lunar.bridge.Bridge10_2 com.moonsworth.lunar.bridge.horsestats.Horsestats14 \
      com.moonsworth.lunar.bridge.MixinHelper5_6 com.moonsworth.lunar.bridge.BridgeExtension_4
grep -rn "MixinHelper5_6\.field" src/main/java   # 40+ call sites, used to pick field names
sed -n '1,250p' src/main/java/com/moonsworth/lunar/bridge/MixinHelper5.java   # concrete pose/texture impl
```
