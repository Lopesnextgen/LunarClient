# Cluster 02 — `com.moonsworth.lunar.client.util` (50 rows)

Source: `tools/renames/cluster-02.txt` (50 rows, all `ThreadModuleDumpN`). All 50
files exist; no `net.minecraft.*` rows; no shaded third-party classes in this
cluster (every row is a first-party Lunar class; the obf jar token for each is
listed in the table below from `mappings-snapshot/restructure/remaining-renames.tsv`).

Map: `tools/renames/wave7/classes-02.tsv` — **50 MERGE rows, 0 pure renames**.

## 1. Verdict: every row is a stale rescue twin

All 50 placeholders are the second generation of a class that was already
renamed by the 2026-09-14 renamer commits and then re-added by the 2026-09-15
rescue sweep:

* `8d6088f02` (`renamer: apply clientutil3 map`) renamed 39 of the 50 to their
  current names;
* `99deb1921` (`renamer: apply clientutil2 map`) renamed the other 11
  (`ThreadModuleDump{7,66,67,68,69,70,71,72,73,75,76}`);
* `3d38608ff` (`rescue: legacy/replaymod + client leftovers`) added this copy
  back as `src/main/java/...` (git shows every row as `A`, 104
  `client/util/ThreadModuleDump*` files in that commit);
* `24425f129` (wave6 restructure) then moved the named copies to their current
  packages; the wave6 dedupe (`d54a9a3fe`) missed these because the move map
  source FQN (`client.util.<Name>`) no longer existed when `make_merge_map.py`
  ran, so no merge row was derived for them.

A rename is impossible: each target simple name is already declared exactly
once tree-wide, so `apply_class_renames_aware.py` skips every row:

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave7/classes-02.tsv
[aware-renames] 50 rows (0 nested); 9392 java files
  SKIP ThreadModuleDump29 -> DesktopNotifier: new name already declared
  ... (all 50 rows, same reason) ...
  SKIP ThreadModuleDump76 -> ResourcePackUtils: new name already declared
[aware-renames] rows=50 skipped=50 files_touched=0 files_renamed=0 mode=dry-run
```

I did **not** invent alternative names for the placeholders: that would keep
the duplicate class alive under a second name and defeat the wave-6 dedupe.
`tools/renames/wave7/classes-02.tsv` therefore keeps the canonical name in the
`new` column and marks each row `MERGE:` (same convention as wave7
`classes-06.tsv`), so the map doubles as the merge work-list.

## 2. Verification (per pair)

1. **Structural comparison** of placeholder vs canonical: regex profiles of
   public/protected/private methods and fields give identical counts for all
   50 pairs (column `struct` below); a normalised (modifier, return type,
   arity) method-shape multiset is identical for 28 pairs, and the remaining
   22 differ only by the Lombok `@Generated` private constructor dropped by
   the rescue decompile, self-return types (`SELF` vs old simple name), or
   member names that the canonical generation evolved further (`Ref`:
   `ClientPacketListenerBridge` -> `NetHandlerPlayClientBridge`,
   `KeyBindingClashEntry` -> `KeyBindingEntry`).
2. **Literal intersection**: the shared string sets match (examples in each
   `classes-02.tsv` row); literal-free classes (`TickQueue`, `TraitBuilder`,
   `MarkerPredicate`, `UnorderedPair`, `GuiResolution`, `RectangleQuadtree`,
   `Vector2f`, `DummyPlayer`) are covered by the structural comparison and the
   consumer sets.
3. **Single declaration**: every `new` name is declared exactly once tree-wide;
   every `old` name is declared exactly once (the placeholder file itself).
4. **Reference counts** (grep of `src/main/java`, excluding the declaring file)
   are listed per row; they show both generations are live, so the repair is
   "repoint then delete", not "delete".

## 3. Rows

| old | new (canonical simple) | canonical FQN | obf token | struct | refs ph/can | renamed by |
|---|---|---|---|---|---|---|
| `ThreadModuleDump29` | `DesktopNotifier` | `com.moonsworth.lunar.client.ui.notification.DesktopNotifier` | `HORHIHCCOCCORIIIOHRRRROROCHCIC` | 3m/5f | 0/3 | `8d6088f02` |
| `ThreadModuleDump3` | `ServerUtils` | `com.moonsworth.lunar.client.util.net.ServerUtils` | `CCOIRCORCORHORHHIORHOIHOHRCICH` | 6m/2f | 5/6 | `8d6088f02` |
| `ThreadModuleDump30` | `KeyVersionPair` | `com.moonsworth.lunar.client.config.option.KeyVersionPair` | `HORHIRROCIOIICIOHCOCCOOHIRCCRI` | 10m/3f | 0/2 | `8d6088f02` |
| `ThreadModuleDump31` | `ReflectionUtils` | `com.moonsworth.lunar.client.framework.listener.ReflectionUtils` | `HORHROIOIOICIRHIOCOICHHHIHCIIO_` | 7m/0f | 1/1 | `8d6088f02` |
| `ThreadModuleDump32` | `DevFeatureList` | `com.moonsworth.lunar.client.framework.DevFeatureList` | `HRICOROOOCCOCOROCRHHCRRIRCOICO_` | 2m/0f | 0/0 | `8d6088f02` |
| `ThreadModuleDump33` | `PotionUtils` | `com.moonsworth.lunar.client.util.game.PotionUtils` | `HROCOOROCRHIRHOHIRHIRIHOOCHRIH` | 7m/2f | 3/2 | `8d6088f02` |
| `ThreadModuleDump34` | `DateUtils` | `com.moonsworth.lunar.client.util.text.DateUtils` | `HRORICORIHHHRICRIRCIIOHCRIRRHI` | 7m/2f | 7/5 | `8d6088f02` |
| `ThreadModuleDump36` | `UuidUtils` | `com.moonsworth.lunar.client.util.text.UuidUtils` | `ICHHHOIOHOIRIOCIHOOOOOCOIROHHI` | 6m/0f | 5/4 | `8d6088f02` |
| `ThreadModuleDump37` | `BackgroundExecutor` | `com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor` | `ICIHHHCHHOIHHRROOCHOICRHOCHCOI` | 22m/9f | 39/30 | `8d6088f02` |
| `ThreadModuleDump38` | `FastMath` | `com.moonsworth.lunar.client.util.math.FastMath` | `ICOOHRIORIOOIIRRIHHOOIOHHCORIR` | 3m/4f | 7/4 | `8d6088f02` |
| `ThreadModuleDump4` | `TickQueue` | `com.moonsworth.lunar.client.util.collection.TickQueue` | `CCORCOICIRIROICHRORRORHCROCOOR` | 8m/3f | 0/1 | `8d6088f02` |
| `ThreadModuleDump40` | `NumberUtils` | `com.moonsworth.lunar.client.util.math.NumberUtils` | `IHCORIOHOHHOIORHCCOOIIIHOCROOI` | 12m/2f | 10/28 | `8d6088f02` |
| `ThreadModuleDump41` | `RingBuffer` | `com.moonsworth.lunar.client.util.collection.RingBuffer` | `IHHCOCHOHOCRCRRHCIHHHIHCRIOIHR` | 3m/5f | 0/1 | `8d6088f02` |
| `ThreadModuleDump42` | `CheckoutUtils` | `com.moonsworth.lunar.client.network.ipc.CheckoutUtils` | `IHHIOOICIOOICRICCIRCOHCHRIROIC` | 2m/0f | 3/3 | `8d6088f02` |
| `ThreadModuleDump43` | `ConfigRangeBuilder` | `com.moonsworth.lunar.client.config.option.ConfigRangeBuilder` | `IHRHHRIHICHOOICIRIOOHOICHIRHOI_` | interface | 1/7 | `8d6088f02` |
| `ThreadModuleDump44` | `TraitBuilder` | `com.moonsworth.lunar.client.config.option.TraitBuilder` | `IHRHIHROHOCHORROCIHHOHIHOCROOR` | interface | 7/2 | `8d6088f02` |
| `ThreadModuleDump45` | `HudTimer` | `com.moonsworth.lunar.client.framework.hud.HudTimer` | `IICOCHRHIRHIHIIRHOORCHHIIOIOIR` | 15m/16f | 7/18 | `8d6088f02` |
| `ThreadModuleDump46` | `TextUtils` | `com.moonsworth.lunar.client.util.text.TextUtils` | `IIHCOIRRRHRCIRHOCIHHIOOOOOOCCC` | 7m/0f | 9/13 | `8d6088f02` |
| `ThreadModuleDump47` | `WeightedQuadtree` | `com.moonsworth.lunar.client.util.math.WeightedQuadtree` | `IIHOIORIOCHCOORROROHIRIRIOHOHH` | 9m/10f | 1/1 | `8d6088f02` |
| `ThreadModuleDump48` | `LunarConstants` | `com.moonsworth.lunar.client.framework.LunarConstants` | `IIORCIOOIHRRRICOHIRCIHOOCCOHRO` | 2m/32f | 90/82 | `8d6088f02` |
| `ThreadModuleDump49` | `CursorManager` | `com.moonsworth.lunar.client.ui.CursorManager` | `IRCHCHOHRRIOORHRCHRIHIOHRCIHRH` | 5m/6f | 6/3 | `8d6088f02` |
| `ThreadModuleDump5` | `UnorderedPair` | `com.moonsworth.lunar.client.util.collection.UnorderedPair` | `CCRRHIHOCIOOCHORHRORCORCIOCHOH` | 4m/2f | 1/2 | `8d6088f02` |
| `ThreadModuleDump50` | `OpaqueTextureFix` | `com.moonsworth.lunar.client.render.texture.OpaqueTextureFix` | `IRIHHIROOHRHOIHHCIORRHIOHRCOCH` | 3m/1f | 3/3 | `8d6088f02` |
| `ThreadModuleDump51` | `MarkerPredicate` | `com.moonsworth.lunar.client.driver.MarkerPredicate` | `OCCCOOOICCCOCIROCHCCOOCHHIORCO` | interface | 9/1 | `8d6088f02` |
| `ThreadModuleDump52` | `CompressionUtils` | `com.moonsworth.lunar.client.util.io.CompressionUtils` | `OCHOCIIRCRIHHHRCRCHOHOHRCCCCOH` | 2m/0f | 2/4 | `8d6088f02` |
| `ThreadModuleDump53` | `ListUtils` | `com.moonsworth.lunar.client.util.collection.ListUtils` | `OCOCRCHOHOIICRHIRCCHCIHICHRCHH` | 1m/0f | 1/1 | `8d6088f02` |
| `ThreadModuleDump54` | `DummyPlayer` | `com.moonsworth.lunar.client.cosmetics.DummyPlayer` | `OCRORCCHCRIOOIRHOHRRHCRRHRCIHO` | interface | 10/7 | `8d6088f02` |
| `ThreadModuleDump55` | `MimeTypeUtils` | `com.moonsworth.lunar.client.util.io.MimeTypeUtils` | `OCRRICRIORICCCRHIOHORCICIHHICO` | 3m/0f | 2/1 | `8d6088f02` |
| `ThreadModuleDump56` | `TranslationFormatter` | `com.moonsworth.lunar.client.translation.TranslationFormatter` | `OHCRHOCRIOOHCCCRRHHRRRCRCHOIIC` | 4m/1f | 9/1 | `8d6088f02` |
| `ThreadModuleDump57` | `KeyBindingOrder` | `com.moonsworth.lunar.client.config.KeyBindingOrder` | `OHICCIICIRHHHOIORCRORRRCHHROCH` | 2m/1f | 0/0 | `8d6088f02` |
| `ThreadModuleDump58` | `TextSanitizer` | `com.moonsworth.lunar.client.util.text.TextSanitizer` | `OHOOORICRHIIIIRHCICICOCHROICRC_` | 2m/1f | 2/1 | `8d6088f02` |
| `ThreadModuleDump59` | `ImageUtils` | `com.moonsworth.lunar.client.util.io.ImageUtils` | `OHRCHRORCORHICIHCOHICIHHHIRORC` | 4m/0f | 11/7 | `8d6088f02` |
| `ThreadModuleDump6` | `ValueHolder` | `com.moonsworth.lunar.client.util.collection.ValueHolder` | `CHIRIORIIRHHHHCIIHOHCHIIORCOHH` | 2m/1f | 23/24 | `8d6088f02` |
| `ThreadModuleDump60` | `Vector2f` | `com.moonsworth.lunar.client.util.math.Vector2f` | `OHRHCOCHIHRCCHIIRHICRHOHCRRHOH` | 2m/2f | 4/6 | `8d6088f02` |
| `ThreadModuleDump61` | `BrowserUtils` | `com.moonsworth.lunar.client.util.net.BrowserUtils` | `OHROCHICOIOICHOCRROORRCIIICIHO_` | 12m/2f | 20/16 | `8d6088f02` |
| `ThreadModuleDump62` | `GuiClipState` | `com.moonsworth.lunar.client.ui.GuiClipState` | `OICRROHCCIRICRIHCOIRCOORHRHRHC` | 1m/3f | 2/2 | `8d6088f02` |
| `ThreadModuleDump63` | `Ref` | `com.moonsworth.lunar.client.framework.Ref` | `OIHCCIIRIOORHHOOICRCIORCOICOIR` | 48m/9f | 983/998 | `8d6088f02` |
| `ThreadModuleDump64` | `ScreenProjection` | `com.moonsworth.lunar.client.render.ScreenProjection` | `OIIHHHCRHIHHRICHIIICHIHHRRIROI` | 7m/14f | 3/3 | `8d6088f02` |
| `ThreadModuleDump65` | `CollectionUtils` | `com.moonsworth.lunar.client.util.collection.CollectionUtils` | `OIOCHOHCIICHHIORHCICRCIOOHHHRH` | 16m/0f | 7/4 | `8d6088f02` |
| `ThreadModuleDump66` | `ProtoConverter` | `com.moonsworth.lunar.client.network.apollo.ProtoConverter` | `OIOHHHHCICORIHOHOHIRHRROOORRCO` | 22m/0f | 36/21 | `99deb1921` |
| `ThreadModuleDump67` | `MathUtils` | `com.moonsworth.lunar.client.util.math.MathUtils` | `OIRHOOIICOCIOOHICRRRICORIHHIHC` | 19m/0f | 43/54 | `99deb1921` |
| `ThreadModuleDump68` | `ClipboardUtils` | `com.moonsworth.lunar.client.util.io.ClipboardUtils` | `OIRIOIIIRICICCHORICRRIRROHCRIC_` | 8m/1f | 14/20 | `99deb1921` |
| `ThreadModuleDump69` | `HeadTextureCache` | `com.moonsworth.lunar.client.render.texture.HeadTextureCache` | `OOCIOHRCCCRHHIIIIOOCIIHHHRIORO` | 4m/0f | 3/2 | `99deb1921` |
| `ThreadModuleDump7` | `RectangleQuadtree` | `com.moonsworth.lunar.client.util.math.RectangleQuadtree` | `CHOHRRHCHHCHCOOCICOCORRHRIICIH` | 6m/6f | 0/0 | `99deb1921` |
| `ThreadModuleDump70` | `IntRectangle` | `com.moonsworth.lunar.client.util.math.IntRectangle` | `OOIIRCOCCICIRCRHHOHCIRIRHRROHO` | 13m/4f | 12/11 | `99deb1921` |
| `ThreadModuleDump71` | `GuiResolution` | `com.moonsworth.lunar.client.ui.GuiResolution` | `OOOHIHRHRHIIICICCCRRICOOICOIRR` | 7m/7f | 38/24 | `99deb1921` |
| `ThreadModuleDump72` | `DownloadedImageCache` | `com.moonsworth.lunar.client.render.texture.DownloadedImageCache` | `OORCHROOICIRHOHIIOOHHOOCIRRHRI` | 6m/2f | 1/3 | `99deb1921` |
| `ThreadModuleDump73` | `EntityLookup` | `com.moonsworth.lunar.client.util.game.EntityLookup` | `OORHIHOOORHHIRORRICRRORCHRRIIC` | 2m/0f | 2/3 | `99deb1921` |
| `ThreadModuleDump75` | `RomanNumeralUtils` | `com.moonsworth.lunar.client.util.text.RomanNumeralUtils` | `ORHCCCIHICRHROHROHHCIIOCCICHRC` | 3m/5f | 0/0 | `99deb1921` |
| `ThreadModuleDump76` | `ResourcePackUtils` | `com.moonsworth.lunar.client.util.io.ResourcePackUtils` | `ORICCCROCROHCRRIRHICRHCRRCOOHI` | 8m/4f | 1/5 | `99deb1921` |

## 4. Zero-reference placeholders (delete immediately, no repoint needed)

These have no references outside their own file, so they can simply be deleted:

`ThreadModuleDump29`, `ThreadModuleDump30`, `ThreadModuleDump32`, `ThreadModuleDump4`, `ThreadModuleDump41`, `ThreadModuleDump57`, `ThreadModuleDump7`, `ThreadModuleDump75`

All other rows need the reference files repointed before deletion.

## 5. Ready-made merge map (wave6 `merges.tsv` format)

Feed this to `tools/repoint_external.py --map <file>` (it deletes the twin and
rewrites references to the canonical FQN). One row per placeholder:

```tsv
# old.package<TAB>Old<TAB>canonical.fqn<TAB>evidence
com.moonsworth.lunar.client.util	ThreadModuleDump29	com.moonsworth.lunar.client.ui.notification.DesktopNotifier	merge: stale rescue twin; canonical com.moonsworth.lunar.client.ui.notification.DesktopNotifier already exists -- DesktopNotifier: 3m/5f match, obf HORHIHCCOCCORIIIOHRRRROROCHCIC; renamed by 8d6088f02, restored by 3d38608ff; refs ph=0 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump3	com.moonsworth.lunar.client.util.net.ServerUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.net.ServerUtils already exists -- ServerUtils: 6m/2f match, obf CCOIRCORCORHORHHIORHOIHOHRCICH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=5 can=6
com.moonsworth.lunar.client.util	ThreadModuleDump30	com.moonsworth.lunar.client.config.option.KeyVersionPair	merge: stale rescue twin; canonical com.moonsworth.lunar.client.config.option.KeyVersionPair already exists -- KeyVersionPair: 10m/3f match, obf HORHIRROCIOIICIOHCOCCOOHIRCCRI; renamed by 8d6088f02, restored by 3d38608ff; refs ph=0 can=2
com.moonsworth.lunar.client.util	ThreadModuleDump31	com.moonsworth.lunar.client.framework.listener.ReflectionUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.framework.listener.ReflectionUtils already exists -- ReflectionUtils: 7m/0f match, obf HORHROIOIOICIRHIOCOICHHHIHCIIO_; renamed by 8d6088f02, restored by 3d38608ff; refs ph=1 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump32	com.moonsworth.lunar.client.framework.DevFeatureList	merge: stale rescue twin; canonical com.moonsworth.lunar.client.framework.DevFeatureList already exists -- DevFeatureList: 2m/0f match, obf HRICOROOOCCOCOROCRHHCRRIRCOICO_; renamed by 8d6088f02, restored by 3d38608ff; refs ph=0 can=0
com.moonsworth.lunar.client.util	ThreadModuleDump33	com.moonsworth.lunar.client.util.game.PotionUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.game.PotionUtils already exists -- PotionUtils: 7m/2f match, obf HROCOOROCRHIRHOHIRHIRIHOOCHRIH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=3 can=2
com.moonsworth.lunar.client.util	ThreadModuleDump34	com.moonsworth.lunar.client.util.text.DateUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.text.DateUtils already exists -- DateUtils: 7m/2f match, obf HRORICORIHHHRICRIRCIIOHCRIRRHI; renamed by 8d6088f02, restored by 3d38608ff; refs ph=7 can=5
com.moonsworth.lunar.client.util	ThreadModuleDump36	com.moonsworth.lunar.client.util.text.UuidUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.text.UuidUtils already exists -- UuidUtils: 6m/0f match, obf ICHHHOIOHOIRIOCIHOOOOOCOIROHHI; renamed by 8d6088f02, restored by 3d38608ff; refs ph=5 can=4
com.moonsworth.lunar.client.util	ThreadModuleDump37	com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor already exists -- BackgroundExecutor: 22m/9f match, obf ICIHHHCHHOIHHRROOCHOICRHOCHCOI; renamed by 8d6088f02, restored by 3d38608ff; refs ph=39 can=30
com.moonsworth.lunar.client.util	ThreadModuleDump38	com.moonsworth.lunar.client.util.math.FastMath	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.FastMath already exists -- FastMath: 3m/4f match, obf ICOOHRIORIOOIIRRIHHOOIOHHCORIR; renamed by 8d6088f02, restored by 3d38608ff; refs ph=7 can=4
com.moonsworth.lunar.client.util	ThreadModuleDump4	com.moonsworth.lunar.client.util.collection.TickQueue	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.collection.TickQueue already exists -- TickQueue: 8m/3f match, obf CCORCOICIRIROICHRORRORHCROCOOR; renamed by 8d6088f02, restored by 3d38608ff; refs ph=0 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump40	com.moonsworth.lunar.client.util.math.NumberUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.NumberUtils already exists -- NumberUtils: 12m/2f match, obf IHCORIOHOHHOIORHCCOOIIIHOCROOI; renamed by 8d6088f02, restored by 3d38608ff; refs ph=10 can=28
com.moonsworth.lunar.client.util	ThreadModuleDump41	com.moonsworth.lunar.client.util.collection.RingBuffer	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.collection.RingBuffer already exists -- RingBuffer: 3m/5f match, obf IHHCOCHOHOCRCRRHCIHHHIHCRIOIHR; renamed by 8d6088f02, restored by 3d38608ff; refs ph=0 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump42	com.moonsworth.lunar.client.network.ipc.CheckoutUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.network.ipc.CheckoutUtils already exists -- CheckoutUtils: 2m/0f match, obf IHHIOOICIOOICRICCIRCOHCHRIROIC; renamed by 8d6088f02, restored by 3d38608ff; refs ph=3 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump43	com.moonsworth.lunar.client.config.option.ConfigRangeBuilder	merge: stale rescue twin; canonical com.moonsworth.lunar.client.config.option.ConfigRangeBuilder already exists -- ConfigRangeBuilder: interface match, obf IHRHHRIHICHOOICIRIOOHOICHIRHOI_; renamed by 8d6088f02, restored by 3d38608ff; refs ph=1 can=7
com.moonsworth.lunar.client.util	ThreadModuleDump44	com.moonsworth.lunar.client.config.option.TraitBuilder	merge: stale rescue twin; canonical com.moonsworth.lunar.client.config.option.TraitBuilder already exists -- TraitBuilder: interface match, obf IHRHIHROHOCHORROCIHHOHIHOCROOR; renamed by 8d6088f02, restored by 3d38608ff; refs ph=7 can=2
com.moonsworth.lunar.client.util	ThreadModuleDump45	com.moonsworth.lunar.client.framework.hud.HudTimer	merge: stale rescue twin; canonical com.moonsworth.lunar.client.framework.hud.HudTimer already exists -- HudTimer: 15m/16f match, obf IICOCHRHIRHIHIIRHOORCHHIIOIOIR; renamed by 8d6088f02, restored by 3d38608ff; refs ph=7 can=18
com.moonsworth.lunar.client.util	ThreadModuleDump46	com.moonsworth.lunar.client.util.text.TextUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.text.TextUtils already exists -- TextUtils: 7m/0f match, obf IIHCOIRRRHRCIRHOCIHHIOOOOOOCCC; renamed by 8d6088f02, restored by 3d38608ff; refs ph=9 can=13
com.moonsworth.lunar.client.util	ThreadModuleDump47	com.moonsworth.lunar.client.util.math.WeightedQuadtree	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.WeightedQuadtree already exists -- WeightedQuadtree: 9m/10f match, obf IIHOIORIOCHCOORROROHIRIRIOHOHH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=1 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump48	com.moonsworth.lunar.client.framework.LunarConstants	merge: stale rescue twin; canonical com.moonsworth.lunar.client.framework.LunarConstants already exists -- LunarConstants: 2m/32f match, obf IIORCIOOIHRRRICOHIRCIHOOCCOHRO; renamed by 8d6088f02, restored by 3d38608ff; refs ph=90 can=82
com.moonsworth.lunar.client.util	ThreadModuleDump49	com.moonsworth.lunar.client.ui.CursorManager	merge: stale rescue twin; canonical com.moonsworth.lunar.client.ui.CursorManager already exists -- CursorManager: 5m/6f match, obf IRCHCHOHRRIOORHRCHRIHIOHRCIHRH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=6 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump5	com.moonsworth.lunar.client.util.collection.UnorderedPair	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.collection.UnorderedPair already exists -- UnorderedPair: 4m/2f match, obf CCRRHIHOCIOOCHORHRORCORCIOCHOH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=1 can=2
com.moonsworth.lunar.client.util	ThreadModuleDump50	com.moonsworth.lunar.client.render.texture.OpaqueTextureFix	merge: stale rescue twin; canonical com.moonsworth.lunar.client.render.texture.OpaqueTextureFix already exists -- OpaqueTextureFix: 3m/1f match, obf IRIHHIROOHRHOIHHCIORRHIOHRCOCH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=3 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump51	com.moonsworth.lunar.client.driver.MarkerPredicate	merge: stale rescue twin; canonical com.moonsworth.lunar.client.driver.MarkerPredicate already exists -- MarkerPredicate: interface match, obf OCCCOOOICCCOCIROCHCCOOCHHIORCO; renamed by 8d6088f02, restored by 3d38608ff; refs ph=9 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump52	com.moonsworth.lunar.client.util.io.CompressionUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.io.CompressionUtils already exists -- CompressionUtils: 2m/0f match, obf OCHOCIIRCRIHHHRCRCHOHOHRCCCCOH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=2 can=4
com.moonsworth.lunar.client.util	ThreadModuleDump53	com.moonsworth.lunar.client.util.collection.ListUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.collection.ListUtils already exists -- ListUtils: 1m/0f match, obf OCOCRCHOHOIICRHIRCCHCIHICHRCHH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=1 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump54	com.moonsworth.lunar.client.cosmetics.DummyPlayer	merge: stale rescue twin; canonical com.moonsworth.lunar.client.cosmetics.DummyPlayer already exists -- DummyPlayer: interface match, obf OCRORCCHCRIOOIRHOHRRHCRRHRCIHO; renamed by 8d6088f02, restored by 3d38608ff; refs ph=10 can=7
com.moonsworth.lunar.client.util	ThreadModuleDump55	com.moonsworth.lunar.client.util.io.MimeTypeUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.io.MimeTypeUtils already exists -- MimeTypeUtils: 3m/0f match, obf OCRRICRIORICCCRHIOHORCICIHHICO; renamed by 8d6088f02, restored by 3d38608ff; refs ph=2 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump56	com.moonsworth.lunar.client.translation.TranslationFormatter	merge: stale rescue twin; canonical com.moonsworth.lunar.client.translation.TranslationFormatter already exists -- TranslationFormatter: 4m/1f match, obf OHCRHOCRIOOHCCCRRHHRRRCRCHOIIC; renamed by 8d6088f02, restored by 3d38608ff; refs ph=9 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump57	com.moonsworth.lunar.client.config.KeyBindingOrder	merge: stale rescue twin; canonical com.moonsworth.lunar.client.config.KeyBindingOrder already exists -- KeyBindingOrder: 2m/1f match, obf OHICCIICIRHHHOIORCRORRRCHHROCH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=0 can=0
com.moonsworth.lunar.client.util	ThreadModuleDump58	com.moonsworth.lunar.client.util.text.TextSanitizer	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.text.TextSanitizer already exists -- TextSanitizer: 2m/1f match, obf OHOOORICRHIIIIRHCICICOCHROICRC_; renamed by 8d6088f02, restored by 3d38608ff; refs ph=2 can=1
com.moonsworth.lunar.client.util	ThreadModuleDump59	com.moonsworth.lunar.client.util.io.ImageUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.io.ImageUtils already exists -- ImageUtils: 4m/0f match, obf OHRCHRORCORHICIHCOHICIHHHIRORC; renamed by 8d6088f02, restored by 3d38608ff; refs ph=11 can=7
com.moonsworth.lunar.client.util	ThreadModuleDump6	com.moonsworth.lunar.client.util.collection.ValueHolder	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.collection.ValueHolder already exists -- ValueHolder: 2m/1f match, obf CHIRIORIIRHHHHCIIHOHCHIIORCOHH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=23 can=24
com.moonsworth.lunar.client.util	ThreadModuleDump60	com.moonsworth.lunar.client.util.math.Vector2f	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.Vector2f already exists -- Vector2f: 2m/2f match, obf OHRHCOCHIHRCCHIIRHICRHOHCRRHOH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=4 can=6
com.moonsworth.lunar.client.util	ThreadModuleDump61	com.moonsworth.lunar.client.util.net.BrowserUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.net.BrowserUtils already exists -- BrowserUtils: 12m/2f match, obf OHROCHICOIOICHOCRROORRCIIICIHO_; renamed by 8d6088f02, restored by 3d38608ff; refs ph=20 can=16
com.moonsworth.lunar.client.util	ThreadModuleDump62	com.moonsworth.lunar.client.ui.GuiClipState	merge: stale rescue twin; canonical com.moonsworth.lunar.client.ui.GuiClipState already exists -- GuiClipState: 1m/3f match, obf OICRROHCCIRICRIHCOIRCOORHRHRHC; renamed by 8d6088f02, restored by 3d38608ff; refs ph=2 can=2
com.moonsworth.lunar.client.util	ThreadModuleDump63	com.moonsworth.lunar.client.framework.Ref	merge: stale rescue twin; canonical com.moonsworth.lunar.client.framework.Ref already exists -- Ref: 48m/9f match, obf OIHCCIIRIOORHHOOICRCIORCOICOIR; renamed by 8d6088f02, restored by 3d38608ff; refs ph=983 can=998
com.moonsworth.lunar.client.util	ThreadModuleDump64	com.moonsworth.lunar.client.render.ScreenProjection	merge: stale rescue twin; canonical com.moonsworth.lunar.client.render.ScreenProjection already exists -- ScreenProjection: 7m/14f match, obf OIIHHHCRHIHHRICHIIICHIHHRRIROI; renamed by 8d6088f02, restored by 3d38608ff; refs ph=3 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump65	com.moonsworth.lunar.client.util.collection.CollectionUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.collection.CollectionUtils already exists -- CollectionUtils: 16m/0f match, obf OIOCHOHCIICHHIORHCICRCIOOHHHRH; renamed by 8d6088f02, restored by 3d38608ff; refs ph=7 can=4
com.moonsworth.lunar.client.util	ThreadModuleDump66	com.moonsworth.lunar.client.network.apollo.ProtoConverter	merge: stale rescue twin; canonical com.moonsworth.lunar.client.network.apollo.ProtoConverter already exists -- ProtoConverter: 22m/0f match, obf OIOHHHHCICORIHOHOHIRHRROOORRCO; renamed by 99deb1921, restored by 3d38608ff; refs ph=36 can=21
com.moonsworth.lunar.client.util	ThreadModuleDump67	com.moonsworth.lunar.client.util.math.MathUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.MathUtils already exists -- MathUtils: 19m/0f match, obf OIRHOOIICOCIOOHICRRRICORIHHIHC; renamed by 99deb1921, restored by 3d38608ff; refs ph=43 can=54
com.moonsworth.lunar.client.util	ThreadModuleDump68	com.moonsworth.lunar.client.util.io.ClipboardUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.io.ClipboardUtils already exists -- ClipboardUtils: 8m/1f match, obf OIRIOIIIRICICCHORICRRIRROHCRIC_; renamed by 99deb1921, restored by 3d38608ff; refs ph=14 can=20
com.moonsworth.lunar.client.util	ThreadModuleDump69	com.moonsworth.lunar.client.render.texture.HeadTextureCache	merge: stale rescue twin; canonical com.moonsworth.lunar.client.render.texture.HeadTextureCache already exists -- HeadTextureCache: 4m/0f match, obf OOCIOHRCCCRHHIIIIOOCIIHHHRIORO; renamed by 99deb1921, restored by 3d38608ff; refs ph=3 can=2
com.moonsworth.lunar.client.util	ThreadModuleDump7	com.moonsworth.lunar.client.util.math.RectangleQuadtree	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.RectangleQuadtree already exists -- RectangleQuadtree: 6m/6f match, obf CHOHRRHCHHCHCOOCICOCORRHRIICIH; renamed by 99deb1921, restored by 3d38608ff; refs ph=0 can=0
com.moonsworth.lunar.client.util	ThreadModuleDump70	com.moonsworth.lunar.client.util.math.IntRectangle	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.math.IntRectangle already exists -- IntRectangle: 13m/4f match, obf OOIIRCOCCICIRCRHHOHCIRIRHRROHO; renamed by 99deb1921, restored by 3d38608ff; refs ph=12 can=11
com.moonsworth.lunar.client.util	ThreadModuleDump71	com.moonsworth.lunar.client.ui.GuiResolution	merge: stale rescue twin; canonical com.moonsworth.lunar.client.ui.GuiResolution already exists -- GuiResolution: 7m/7f match, obf OOOHIHRHRHIIICICCCRRICOOICOIRR; renamed by 99deb1921, restored by 3d38608ff; refs ph=38 can=24
com.moonsworth.lunar.client.util	ThreadModuleDump72	com.moonsworth.lunar.client.render.texture.DownloadedImageCache	merge: stale rescue twin; canonical com.moonsworth.lunar.client.render.texture.DownloadedImageCache already exists -- DownloadedImageCache: 6m/2f match, obf OORCHROOICIRHOHIIOOHHOOCIRRHRI; renamed by 99deb1921, restored by 3d38608ff; refs ph=1 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump73	com.moonsworth.lunar.client.util.game.EntityLookup	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.game.EntityLookup already exists -- EntityLookup: 2m/0f match, obf OORHIHOOORHHIRORRICRRORCHRRIIC; renamed by 99deb1921, restored by 3d38608ff; refs ph=2 can=3
com.moonsworth.lunar.client.util	ThreadModuleDump75	com.moonsworth.lunar.client.util.text.RomanNumeralUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.text.RomanNumeralUtils already exists -- RomanNumeralUtils: 3m/5f match, obf ORHCCCIHICRHROHROHHCIIOCCICHRC; renamed by 99deb1921, restored by 3d38608ff; refs ph=0 can=0
com.moonsworth.lunar.client.util	ThreadModuleDump76	com.moonsworth.lunar.client.util.io.ResourcePackUtils	merge: stale rescue twin; canonical com.moonsworth.lunar.client.util.io.ResourcePackUtils already exists -- ResourcePackUtils: 8m/4f match, obf ORICCCROCROHCRRIRHICRHCRRCOOHI; renamed by 99deb1921, restored by 3d38608ff; refs ph=1 can=5
```

`repoint_external.py` is dry-run by default and deletes the declaring file +
repoints FQN/import/bare references on `--apply`. The canonical FQNs in the
map above all exist (verified by `find`), so the rows are directly consumable.
After repointing, re-run the dry run of `classes-02.tsv`: it must report the
same 50 SKIPs (canonical still exists), then delete the 50 files.

## 6. Notes / ambiguities

* **`ThreadModuleDump63` (`Ref`)** is the big one: 983 placeholder references
  vs 998 canonical references, i.e. roughly half of each usage kind is on
  either side; `Ref` is a framework-wide accessor, so repoint it first and
  gate with `ecj_diff.py` / `error_diff.py`.
* **Half-renamed broken copies**: several rescue copies already received member
  renames without their bodies being updated, e.g. `ThreadModuleDump69`
  declares `headTextures`/`wrappedTextures` but uses `field1.*/field2.*`, and
  `ThreadModuleDump75` declares `TO_ROMAN_CACHE`/`FROM_ROMAN_CACHE` but uses
  `field1/field2`. They cannot be "kept" even if one wanted to; the canonical
  copies are the consistent generation.
* **Nested rows owned by sibling clusters**: the placeholders declare nested
  types that appear in sibling cluster lists (`ThreadModuleDump29.Type`/
  `Extension`, `ThreadModuleDump4.Data`, `ThreadModuleDump47.Data2`,
  `ThreadModuleDump54.Type`, `ThreadModuleDump63.Data`, ...). If the repair pass
  deletes these files, those sibling rows become moot; note it in their maps.
* **`ThreadModuleDump73Type`** (`OIRIHIOOOCHRCHICICHCHCIOOOOOII`, cluster 01/45)
  is a different class (`InterpolationMode`) and is not part of cluster 02.
* **Not shaded**: no Jackson/Guava/Mixin/other third-party row in this cluster;
  none of the 50 should go through `match_libs.py`.
* The canonical packages follow the wave6 target layout (`util/{net,text,io,
  collection,math,concurrent,game}`, `ui`, `framework`, `render/texture`,
  `network/{apollo,ipc}`, `config/option`, `cosmetics`, `driver`,
  `translation`), which is further evidence that the canonical generation is
  the maintained one.
