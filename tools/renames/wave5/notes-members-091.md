# Member renames — cluster 091 (directionhud + f3display + debug.shaderdebugmod)

Owners: 25 files (cluster table `tools/renames/wave5/member-cluster-091.tsv`,
1,280 lazy rows: 953 method/field + 327 params/locals).

**Map:** `tools/renames/wave5/members-091.tsv` — **305 rows** (methods + fields).
Params and locals are out of scope for this map format (`M|F` only); the local
buckets (`varN`, `numberN`, `valueN`, `textN`, `flagN` misclassified as `F`) are
left to `tools/clean_locals.py`.

The map was applied end-to-end in a throwaway git worktree with the patched
applier (see finding 1): 281 rows survive the multi-decl filter, 854 declaration
and 2,505 access renames, and the resulting tree is self-consistent (no dangling
accesses, no corrupted identifiers; verified with
`tools/apply_member_renames.py` dry-run + a rename-aware scan).

## Finding 1 — REQUIRED applier fix before applying this map

`access_worker` in `tools/apply_member_renames.py` corrupts identifiers when one
old member name is a substring/prefix of a longer one:

* the `_CAST_ACCESS` branch appends only the bare `name` (`hits.append((m.group('name'), hit[1]))`),
* the replacement loop then does a plain `text.replace(old_s, new_s)`.

Reproduced on this cluster: `((ProfilerResultBridge)list.get(i)).method1()` makes
the loop replace the bare token `method1`, so `Ref.method10()` becomes
`Ref.percentage0()`, `this.field6.method13()` becomes `this.module.percentage3`,
and `var1.method15()` becomes `var1.writeLine5` (F3Display.java,
F3DebugRenderer.java, F3display2.java, F3display2_2.java).

Fix (validated in the worktree, gives zero corrupted identifiers):

```python
# 1) cast hits keep the full match, like the _ACCESS branch
full, name = m.group(0), m.group('name')
hits.append((full, full[:-len(name)] + hit[1]))

# 2) replacement is longest-first and word-boundary safe
for old_s, new_s in sorted(hits, key=lambda h: len(h[0]), reverse=True):
    if old_s != new_s:
        text = re.sub(re.escape(old_s) + r'(?![\w$])', new_s, text)
```

## What was renamed (highlights)

* `directionhud` — the four `DirectionHud.Extension` renderers: compass
  textures, `drawWaypointMarker` / `drawPlayerMarker` / `drawMapMarker` /
  `drawTeammateMarker`, `computeMarkerOffset`, `normalizeHeading`,
  `DIRECTION_NAMES`. Cross-checked against Laby v6 `directionhud/DirectionHUD`
  and Moonsense `DirectionHUDModule`.
* `f3display` — both copies of the F3 stack. Variant A (`F3Debug*`): writer
  interface (`writeLine` / `writeLabeledLine` / `writeSegments` / `writePieChart`
  / `writeChart` / `isReducedInfo` + `rendererWriter` / `measurer` /
  `conditionalWriter` / `notAvailable` factories), the three writer impls,
  info providers (`getFps`, `getBiome`, `getCoordinates`, `getChunkRegion`,
  `getLightLevel`, `getGpuUtilization`, `writeMemoryUsage`, `getBandwidth`, …),
  renderer layout (`reducedInfo`, `drawLine`, `drawPieChart`, `advance`,
  `getContentWidth/Height`). Variant B (`F3display*`) mirrored, with the
  documented exceptions below.
* `Shadermod` — all JS bridge methods take their `@CallbackJS(...)` values
  (`updateResolution`, `updateStaticShader`, `requestFeedAction`, …); the main
  class gets `vertexCustom`/`fragmentCustom`, `vertexShader`/`fragmentShader`,
  `selectedUniforms`/`hiddenUniforms`/`samplers`, `limitTo20Fps`,
  `autoHotReload`, `hotReload`, `applyDefinition`, `flushReport`, `parseMissingUniform`,
  `renderPreview`, watchers, `@Generated` getters, etc.
* `ProfilerResultBridge` (unclustered bridge, included because F3PieSlice /
  F3display3 override it) — ground truth from `@BridgeTargetMapping`:
  `method1 → percentage` (`usePercentage` / `field_76332_a`),
  `method2 → globalPercentage` (`totalUsePercentage` / `field_76330_b`).

## Skipped / uncertain (kept lazy, with the name we would use)

1. `DirectionHud.Extension.method1` (+ `Directionhud`, `Directionhud2`,
   `Directionhud3`, `Directionhud3Impl` overrides) → would be `render`.
   Nested-interface call site `var9.renderer.method1(...)` in
   `mod/hud/DirectionHud.java` cannot be resolved by the applier because
   `qualify()` maps dotted type names to a package-less key
   (`DirectionHud.Extension → DirectionHud/Extension`). Needs a manual pass
   together with cluster 187.
2. `F3display_3.method2/method4/method5/method7` (and the corresponding
   `F3display$Data2/Data3/Data4` overrides) → `writeLabeledLine`, `writePieChart`,
   `writeChart`, `isReducedInfo`. `mod/hud/F3Display.java` (cluster 187) calls
   them on inferred lambda params (`(var0x, var1) -> var0x.method2(...)`, 27
   sites); the access resolver cannot infer lambda types (same reason
   `F3DebugWriter`'s twins stayed lazy). Variant A *is* fully renamed because
   its only equivalent caller is `F3DataTask`'s anonymous class, which is
   already broken (see 5).
3. Writer `method6` (`F3DebugWriter` / `F3display_3`) — `F3DebugMeasurer` and
   `F3display$Data4` declare two `method6` overloads, so the applier's
   multi-decl filter drops the row; a signature-aware pass is needed.
4. `Shaderdebugmod.method7/8/9/10/11/13/15/16/24/25/26` — only reachable through
   `Shaderdebugmod5` / `ShaderDebugJsBridge` lambdas with inferred params
   (`var2x -> var2x.method8(...)`). Proposed: `toggleUniform`,
   `toggleHiddenUniform`, `updateResolution`, `updateShaderType`,
   `updateStaticShader`, `requestCustomShader`, `updateMiscToggle`,
   `requestFeedAction`, `renameSampler`, `chooseSamplerFile`, `deleteSampler`.
5. Fields whose declaring file uses them unqualified (the applier only rewrites
   declarations and `this.`/`super.` accesses): `Directionhud.field1` /
   `Directionhud3.field9` → `COMPASS_TEXTURE`, `Directionhud3Impl.field1` →
   `DIRECTION_NAMES`, `F3DebugInfo`/`F3display.field3/field4` →
   `lastAllocationSampleAt` / `lastAllocatedBytes`, `F3DebugRenderer`/
   `F3display2.field3` → `PIE_CHART_COLORS`.
6. Auto-dropped by the multi-decl filter (documented in-map, harmless):
   `Directionhud2.field1/2`, `F3DebugInfo`/`F3display.field2/field5`,
   `Shaderdebugmod.field1-4`. Note the filter only skips
   `rename_declarations`; the parallel access workers rebuild the map without
   the filter, so **methods** in this list are still renamed via the
   `_BARE`/access passes (verified: `saveIfPending`, `isSaveDue`,
   `formatDecimal`, …) while **fields** are not touched at all.
7. `F3DataTask` / `Fpsdebugmod4` anonymous `F3DebugWriter` / `F3display_3`
   implementors (clusters 088/089): their `method3`/`method4` collide with the
   outer class's own methods, so neither the interface rows here nor their own
   maps can rename them. Both files already fail to compile
   (`HCHHRHHCRIIORRRICOOCCOCHIRRRRR`), and they need a manual follow-up to
   `writeSegments` / `writePieChart` / `writeChart` / `isReducedInfo`.

## Hierarchy / cross-owner notes

* The source tree carries **two copies of the F3 debug stack** (one from
  `lunar.jar`, one from a legacy jar): the live `F3Debug*`/`TargetInfo*` names
  and the older `F3display*` duplicates. The map deliberately gives both copies
  the same member names (except the exceptions above).
* Cross-cluster owner rows added because the overrides must stay consistent:
  `F3display_3` (cluster 092), `F3display3` (092), `TargetInfoProvider` (092),
  `ProfilerResultBridge` (unclustered). If cluster 092 picks different names for
  `F3display_3` / `F3display3`, the maps conflict — the shared names here are
  `writeLine/writeSegments/rendererWriter/measurer/conditionalWriter/notAvailable`
  and `percentage/globalPercentage`.
* `mod/hud/F3Display` + `F3ModuleChildMod` (cluster 187) consume the renamed
  writer API; all their resolved call sites were updated automatically
  (`F3display_3.rendererWriter/measurer/conditionalWriter`, `writeLabeledLine`,
  `isReducedInfo`, `writePieChart`, `writeChart`).
* `Shaderdebugmod5` / `ShaderDebugJsBridge`: JS callbacks are registered by the
  `@CallbackJS` **annotation value** (`FunctionBus.subscribe` unreflects the
  MethodHandle), so renaming the Java methods is safe; the bridge bodies call
  the still-lazy `Shaderdebugmod.method7..26` and stay consistent.
* No reflection or string-keyed member usage was found among the renamed
  members (mixin configs reference classes, not members).

## Uncertain

* `Directionhud3.field1-8` (`TEXTURE_U_0..5`, `TEXTURE_V_0/1`) are unused
  private constants; named from the 23px tile geometry (texture 1186x122) in
  `method1`.
* `F3display$Data2.field1` / `F3display$Data3.field1` (`renderer`) and
  `F3display$Data4.field1` (`reducedInfo`) are safe, but the twins'
  `method6` accumulation helper is still `method6`.
* `Directionhud2` inner `Data.field1/field2` (x/y of a marker offset) cannot be
  renamed because the outer class uses the same old names for its textures.
