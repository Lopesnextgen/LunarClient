# Cluster: `framework.feature.tiertagger` core (7 rows)

Map: `tools/renames/classes-tiertagger.tsv`. Source files read in full; reference
clients (`Badlion 2.0.0-v-beta`, ABDM sources) absent on this machine, so evidence
is source + `tools/mappings-snapshot` only. All 7 new names verified unique
tree-wide (`grep src/main/java tools/renames` — zero hits). No trailing digits.

## Roles (how the cluster fits together)

* `Tiertagger` (out of scope, already named): nametag helper with Caffeine
  UUID->Data cache; handles `EventRenderNameTag` / `EventRenderEntityLabelLines`,
  tier providers, `TierFallbackMode.HIGHEST_ALWAYS` fallback.
* `Gui2Extension` (tiertagger package enum, out of scope): tier source selector
  PVPTIERS_COM / MCTIERS_COM / SUBTIERS / TIERTESTS / PVPHQ, each holding a
  `Tiertagger2_2` provider. `mixin/Gui2Extension` TIER/RANK and `TierFallbackMode`
  SELECTED_ONLY/HIGHEST_FALLBACK/HIGHEST_ALWAYS are its option enums.
* `Tiertagger6` subclasses (out of scope): `Tiertagger6Impl` (MCTiers, .com/.io
  fallbacks), `Tiertagger6Impl_2` (PvpHQ), `nameplate/Tiertagger6Impl` (SubTiers),
  `rewindhandlers/Tiertagger6Iterator` (TierTests + `mcTiersFormat` display names).

## Per-class evidence

* `Tiertagger2` -> `TierLookupHandler`: `method1(String)` runs on
  `TierTagger.field8` executor; resolves `GameProfile` from tab list (`method3`)
  / world entities or Mojang `api.mojang.com` (`method5`); fetches profile via
  `getTierProvider().method5(uuid)`; prints `[TierTagger] <name>'s tiers
  (<provider>):`, `Overall: #n (points)` + region, then per-gamemode lines with
  `(retired)` suffix (`method2`); errors logged via `LunarLogger` + red chat.
  Chat-command lookup handler, not a renderer.
* `Tiertagger3` -> `TierBadgeFormatter`: `method1(tier,pos)` returns a colored
  `Component` (TierTests custom display name via `Tiertagger6Iterator.method7()`
  when provider `isTierTests()`, else `method3` text); `method2` picks legacy 1.8
  `NamedTextColor` switch vs modern `LightingExtension4222` color map;
  `method3` renders slot prefix H (0) / M (2) / L (default) + `T` + tier number.
* `Tiertagger4` -> `TierPlayerProfile`: immutable DTO
  `(Optional<TiertaggerType> region, OptionalInt overall, OptionalInt points,
  Map<TierGameMode, TierGameModeRanking>)`; constructed from profile JSON
  (`region`/`overall`/`points`/`rankings`) in `Tiertagger2Iterator_3.method2`.
* `Tiertagger5` -> `TierPlacement`: `(tier, pos)` int pair, `tier()`/`method3()`
  accessors, `method1/method2` ranking score `tier*3+mappedSlot` (pos 0->0, 2->1,
  else 2), constants 0/1/2 = H/L/M slots. Used for current + peak placements.
* `Tiertagger6` -> `TierMetadataRegistry`: abstract base, `init()` reads
  `tier-tagger.json`, `method2/method3` lowercase icon/color lookups, `method4`
  loads `gameModeData` (missing-icon/color warnings), `method5` builds gamemode
  list, `method6` version-gated icon (`minVersion`/`orElse`), `method7` parses
  color via `ColorUtils`. Subclassed by all four provider metadata singletons.
* `Tiertagger_2` -> `TierGameMode`: `(apiName, niceName, icon, color)` with
  `apiName()/niceName()/method1()=icon/method2()=color`, `equals/hashCode` on
  apiName only, `toString`=apiName, `field5` NONE sentinel. Map key everywhere.
* `Tiertagger2$Data` -> `TierProviderData`: file name marks it as inner `Data`
  of `Tiertagger2_2`, the abstract provider base whose declaring file is missing
  from the tree. Shape `(String, List<TierPlacement>, boolean, boolean)` with
  `method1()` = any placement with `pos==2`. Zero references tree-wide:
  recommend the main agent verify post-apply and delete if still unreferenced.

## Context gaps (not blockers)

* `mod/render/TierTagger.java` (the mod class) does not exist in this tree
  despite `module-renames.tsv:737` mapping it there; mod context was reconstructed
  from field usages (`field10` provider enum, `field11` fallback mode,
  `field12` TIER/RANK display, `field13/14` selected gamemodes, `field17`
  separate-line, `method13` prefix/suffix swap, `field8` executor).
* `Tiertagger2_2` (abstract provider: `method10(url,async)` fetch,
  `method5(uuid)` profile, `method3()` gamemodes, `method8()` name,
  `method9()` provider niceName?) also has no declaring file; several sibling
  provider files referenced by `Gui2Extension` are likewise absent
  (`Tiertagger2Iterator_2`, `Tiertagger2Iterator2`, bare `Tiertagger2Iterator`,
  `nameplate/Tiertagger2Iterator`).
* Applier safety: boundaries treat `_`/`$` as identifier chars, so
  `Tiertagger2`->`TierLookupHandler` cannot touch `Tiertagger2_2`,
  `Tiertagger2$Data`, or `Tiertagger2Iterator_3`; same for `Tiertagger3` vs
  `Tiertagger3_2` and `Tiertagger6` vs `Tiertagger6Impl*`/`Tiertagger6Iterator`.
* Suggested (out-of-scope, for owning agents): `Tiertagger3_2` =
  per-gamemode ranking (gamemode + current + peak + retired) ->
  `TierGameModeRanking`; `Tiertagger2Iterator_3` = HTTP JSON provider base
  (tierlists/profile fetch) -> `HttpTierProvider`; `TiertaggerType` = region
  enum (NA/EU/AS/...) -> `TierRegion`.
