# Member cluster 206 — profile + render helpers (wave 5)

Owners: 25 top-level classes in `com.moonsworth.lunar.client.profile{,.importer}`
and `com.moonsworth.lunar.client.render`.
Map: `tools/renames/wave5/members-206.tsv` — 362 rows (M/F), plus 4 nested owners
(`BadlionProfileConverter$ModConverter`, `BadlionProfileConverter$CanvasCrosshair`,
`FeatherProfileConfig$Data`, `FeatherProfileConverter$Data`).

Dry-run with `tools/apply_member_renames.py` (default multi-decl filter):
**421 declarations + 11,072 access sites** would be renamed; 68 rows skipped by the
conservative filter (all expected, see below).

## Method

Two evidence sources per row: (a) the declaring source (JSON keys, strings, mod-id
tables, renderer wiring) and (b) a reference implementation. Reference used where a
counterpart exists:

* Badlion 2.0.0-v-beta (`Documentation/references/.../Badlion 2.0.0-v-beta`):
  `net/badlion/client/mods/ModProfile`, `manager/ModProfileManager`,
  `gui/mainmenu/GuiModProfiles`, and the per-mod classes (`Keystroke`, `ShowCPS`,
  `Coordinates`, `ArmorStatus`, `Scoreboard`, …) — the Badlion converter's mod ids
  are exactly those modules, and `ModProfileManager.getModProfiles`,
  `modProfiles`, `activate*` names are copied from it.
* Lunar Apollo (`/tmp/opencode/reference/Apollo/api/.../common/location/HudPosition`)
  confirmed the HUD position concept/name.
* `libs/multiver-full/legacy-…-nomappings.jar` decompiled with CFR: the 1.8
  `NametagRenderer` wrapper (`.../HIOROOCORHICHIRIHIRCCORCIIICCH/…`) is the only
  consumer of the `RenderCache` fields (`cachedWidth` int -1 sentinel,
  `cachedState` bool, `cachedText` Component) — used for those four names.

## Skipped / uncertain

1. **Inherited overrides in the two render contexts** (17 methods in
   `RenderContextLegacyTransform`, 14 in `RenderContextModernTransform`) are
   deliberately *not* mapped: they override placeholder members of
   `com.moonsworth.lunar.bridge.BridgeExtension3_5` / `BridgeExtension2_11`,
   which belong to the bridge clusters (36–41). They must be renamed in one
   coordinated pass with those base classes, or compilation breaks. Only
   `field6 -> matrixStack` and `method53 -> poseMatrix` (own field / interface
   implemented here) are mapped. The exception strings in the overrides give the
   real names for the follow-up: `recordDisplayList` (method1),
   `createTessellationBuilder` (method10), `createLineTessellationBuilder`
   (method12); the rest are no-ops (method2/3/25/26/27/29/34/35/36/22/23) or
   rotate helpers (`method4/5/13`).
2. **`EntityRenderLayer`**: `field1..field6` are the six static layers (mapped);
   `method1 -> render` has 7 declarations in the file (abstract + 6 anonymous
   overrides) so the default filter skips it — needs `--allow-multi-decl`
   (no external call sites exist, so that is safe here). The anonymous classes'
   `field7` managers (4 decls, one per anonymous class) cannot be addressed by
   the current per-old-name map format — left as-is.
3. **Same-file nested classes**: the applier resolves `Outer$Inner` owner rows to
   `no source` (`MemResolver.owner_file` only strips `$` in the wrong direction),
   so every `$ModConverter` / `$Data` / `$CanvasCrosshair` row is currently inert.
   Fix `owner_file()` to try `outer + '$' + rest` (joining remaining parts with
   `$`) and make `drop_multi_decl_rows` / `rename_declarations` scope by the
   innermost class block before applying those rows. With the default filter all
   colliding outer tokens are dropped safely, so the map is inert but harmless.
4. **Overloads sharing one placeholder token** can only take one name
   (`ModProfileManager.method3`: chose `switchProfile`; the no-arg
   `getDefaultProfile()` overload would get the same name — compiles, but a
   signature-aware pass should split them; same for
   `BadlionProfileImporter.method1/4/5`, `FeatherProfileImporter.method1/5/6`).
5. **Applier regex gaps noticed in the dry run**: generic method declarations
   don't match `MEMBER_DECL` (`BadlionProfileConverter.method42` = 0 matches);
   top-level field assignments and `return methodX(...)` lines are counted as
   declarations, which makes several useful rows skip by default
   (`ExternalProfileLocator.field1`, `GpuResourceTracker.field2`,
   `ExternalProfileLocator.method7/10/11`, `BadlionProfileImporter.method5`,
   `FeatherProfileImporter.method6`, `ModProfileManager.method3`, all converter
   tokens shared with the nested classes).
6. **Duplicate classes**: the live cache types are
   `com.moonsworth.lunar.client.util.click.Click4` / `Click11` (used by
   `legacy/mixin/EntityHooksMixin` and `Click4Impl`); `render/RenderCache.java`
   and `render/RenderCacheProvider.java` are orphan copies. The `RenderCache`
   rows should be copied onto `Click4`/`Click11` (or the orphan copies deleted)
   when that duplication is resolved.
7. `HudPosition.field8 -> scale` coexists with the existing `scale()` getter
   (legal Java; rename to `scaleFactor` if tooling objects).
   `ModProfile.field1 -> icon` coexists with `getIcon()` (same pattern).
8. `BadlionProfileConverter.field2 -> DEFAULT_SCALE` is dead (unused 2.0F
   constant) and `FeatherProfileConverter.field1/field2` key-code tables are
   rebuilt in static init — names are descriptive, not referenced by string.
9. No reflection/string lookups of any renamed member were found (the converter
   `mods.json` keys are Lunar mod ids, not Java member names).

## Notable findings

* `BadlionProfileConverter`/`FeatherProfileConverter` are the same design: a
  `ModConverter`/`Data` builder assembling `mods.json` plus a `general.json`
  (betterframes/overlay options), with controls/performance as versioned empty
  sections; the mapped-vs-skipped ids drive the import log.
* `BadlionProfileConfig` excludes a fixed set of non-mod top-level keys
  (`NON_MOD_KEYS`) and keeps per-mod `graphicModeSettings`/`textModeSettings`
  that override the global `boxes` positions — mirrored by Feather's
  `**ArbitraryData**` + flat settings map.
* `version` in the converted JSONs comes from `ConfigMigrator` (not a literal),
  so rename `newSection` helpers accordingly.
