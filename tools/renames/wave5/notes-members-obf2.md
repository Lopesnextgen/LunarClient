# notes-members-obf2 -- settings/framework/option obf members

Owner: settings/framework/option cluster (wave 5). Map: `members-obf2.tsv` (24 rows).
No source files were edited.

## 1. What this map covers

The wave-5 subagent brief asked for the obfuscated member rows of
`members-obf.tsv` whose files sit under `client/{framework,config,lighting,option,util,event,feature}`,
plus the `CCCHIHCOIHIHRICIRCRIICHHHRHIIH` boolean setter on the option
builders, plus lazy members of the same owners.

During this pass the tree moved twice (commits `0d0cca2ea` "16 class-rename
maps" and `4227f6adb` "member spree wave 1"; a further class/move wave was
still landing). Result of the re-check against the current tree:

* the rewind-handler obf tokens that appear in the requested directories
  (`Chest2`, `Click2`, `Colorsaturation2`, `Highlight2_2`, `Holograms2`,
  `Nameplate2_2`, `Rewindhandlers2_4`) and their declaring base
  `...rewindhandlers.coordinates.Coordinates` were already named **and applied**
  by `members-obf1.tsv` (rewind events), e.g. `HHCCRRIIOCOROOHRCCCCOHRCHIOROC`
  -> `refreshProperties`, `RORCORIHORORRCCCCCRRICIORROIHR` -> `refreshTimeline`,
  `CIROCIIRIRCRCICHCIHOHCRIRHCHOO` -> `getAdditionalSelectedLayers`. Nothing in
  this map overwrites or renames those again.
* the option framework (`com.moonsworth.lunar.client.lighting`) is still lazy and
  is where the briefed `CCCHI...` token lives, so this map is concentrated there
  (plus the other two declaring owners of that raw token string).

Rows by family:

| family | rows | notes |
|---|---|---|
| `CCCHI...` keybind-builder boolean setter | 4 | `ignoreRecentKeyPress` on `AbstractKeybindOption.KeybindOptionBuilder` (M `method5` + token + F `field15`) and the option-side `field9` |
| companion `OCIRRCIOIORIIRCOORRIROOROOHCOI` setter | 2 | `disabledInGui` (M `method4` + token) |
| option-framework fields | 12 | `AbstractKeybindOption` field7..field14, `SimpleKeybindOption` field15..field19, `ToggleOption` field8 |
| other declaring owners of the `CCCHI...` raw token | 4 | `rewind.highlight.HighlightImpl.method1` -> `setSavePath`, `util.ThreadModuleDump23.method6` -> `greenFloat` (each with its token alias row) |

## 2. Naming rationale (CCCHI -> ignoreRecentKeyPress)

Two independent evidences:

1. **Declaration + control flow.** `AbstractKeybindOption.KeybindOptionBuilder`
   declares `public B method5(boolean var1) { this.field15 = var1; }`
   (the token row in `members-renames.tsv` is
   `.../OIHOHCCCRCORHCRIRHIHOOCIOHRCHR$HORHROIOIOICIRHIOCOICHHHIHCIIO M CCCHI...(Z)... -> method5`;
   verified against the bytecode of `lunar-renamed-classes.jar`:
   `LightingExtension4913$Data2.method5(boolean)` does `putfield field15`).
   The flag is copied into `AbstractKeybindOption.field9` (5th ctor arg) and read
   by `ModifierKeybindOption.method19()`:
   `return this.method12() && GuiRewindhandlers3.method4() ? false : this.isKeyDown();`
   and by the dispatcher `GuiRewindhandlers6Task.method5()`:
   `if (!option.method12() || !GuiRewindhandlers3.method4()) { ... }`.
   `GuiRewindhandlers3.method4()` is a 500 ms window since the last key event
   (`field2 = System.nanoTime()`), so the flag means "do not fire a keybind right
   after another key press" -- named `ignoreRecentKeyPress`.
2. **Call sites.** All 50 `.CCCHI...(true)` sites in 16 files
   (`Snaplook`, `Freelook`, `Radio`, `Waypoints`, `Nametag`, `Coordinates`,
   `CoordinatesHud`, `Quickplay`, `ShaderDebugMod`, `GeckolibDebugMod`,
   `InventorySearch`, `Fishing4`, `SkyBlockCommandKeybinds`,
   `FeatureToggleKeybind`, `FogLoader2`, `Holograms13`) sit on
   `SimpleKeybindOption.Data` / `ModifierKeybindOption.Data` builder chains and
   set the flag true.

The sibling `OCIRRCIOIORIIRCOORRIROOROOHCOI` (raw -> `method4`, field14,
**default true**) is always called with `false` on keybinds that must keep
working while a screen is open (Tab player-list key, debug hotkeys), so it is
named `disabledInGui`.

`HighlightImpl.method1(String)` and `ThreadModuleDump23.method6(int):float`
carry the same raw token **string** in `member-renames.tsv` but are unrelated
methods (per-owner obfuscation names are not global): the former sets the
`@SerializedName("savePath")` field, the latter returns the green channel of an
ARGB int as 0..1. Both are included because the brief asked for every declaring
owner of the token; they are safe single-declaration members.

## 3. Application guidance / tooling constraints

* `apply_member_renames.py` builds its owner index from **file paths**, so the
  nested owner `...AbstractKeybindOption$KeybindOptionBuilder` is not resolvable
  (`owner_file()` returns None -> "!! no source" and the row is skipped). The
  token rows are therefore intended to be applied as a **token-wide textual
  replacement**: `CCCHIHCOIHIHRICIRCRIICHHHRHIIH` and
  `OCIRRCIOIORIIRCOORRIROOROOHCOI` occur nowhere else in `src/main/java`
  (16 and 6 files respectively, all call sites), so replacing
  `.CCCHI...(` -> `.ignoreRecentKeyPress(` and `.OCIRR...(` -> `.disabledInGui(`
  is unambiguous. `fix_obf_tokens.py` cannot do this itself -- its
  `MEMBER_ACCESS` regex does not handle chained calls whose receiver is `)`.
* The declarations must be renamed **signature-aware** at the same time:
  * `AbstractKeybindOption.KeybindOptionBuilder.method5(boolean)` -> `ignoreRecentKeyPress`
  * `AbstractKeybindOption.KeybindOptionBuilder.method4(boolean)` -> `disabledInGui`
    (`method4()` is also the `Function<O, GuiExtension>` override from
    `OptionBuilderBase`; a plain name rename would merge them)
  * `AbstractKeybindOption.java` declares `field14` twice (outer option list
    `List<BooleanConsumer>` and builder flag) -- rename by owner, not by name.
  * 5 rows carry an explicit `[SIGNATURE-AWARE: ...]` tag in the evidence column
    (`KeybindOptionBuilder.field14/method4`, `AbstractKeybindOption.field14`,
    `SimpleKeybindOption.field16/field17`): the owner file declares the same
    simple name twice (outer + nested option/builder class). The default
    multi-decl filter in `apply_member_renames.py` drops those rows, which is
    the safe outcome; apply them by owner+signature, not by name.
* The option `methodN` methods were deliberately **not** named in this map.
  `AbstractOption` declares `method1..method27` with other signatures, and the
  applier's lookup is name-only (it renames every `recv.methodN(...)` whose
  receiver resolves to the owner's hierarchy), so renaming e.g.
  `AbstractKeybindOption.method3(Runnable)` or `method9()/method10()` would
  also rewrite calls to the inherited `AbstractOption.methodN` overloads. They
  need a signature-aware pass (same "SPLIT" class of problem flagged in
  `members-settings.tsv`).
* `client.lighting` -> `client.option` package move was still pending while
  this map was written; owner FQNs use the paths as of 2026-09-16 17:50
  (`com.moonsworth.lunar.client.lighting.*`).

## 4. Skipped / uncertain

**False positives in `members-obf.tsv`** (legitimate names, not obfuscated
tokens -- verified in source; left untouched):
`CoordinatesType.RUNECRAFTING`, `GuiType.INVISIBILITY`/`REGENERATION`,
`GuiType4.SINGLEPLAYER`, `PickaxeTier.DUNGEONBREAKER`,
`ThreadModuleDump11.MILLISECONDS` and `TimeFormatting.MILLISECONDS`
(enum constants of the time-format `Type` enums, display string "12345ms"),
`Wasapi.WAVEFORMATEX` (JNA `Structure` subclass mirroring the Win32 struct),
`ItemStack.DECIMALFORMAT`, `EnumChatFormatting.STRIKETHROUGH`,
`ActiveRenderInfo.OBJECTCOORDS`, `WorldGenDungeons.CHESTCONTENT`/`SPAWNERTYPES`
(all vanilla-canonical names). Out-of-scope files in the same rows
(`bridge/*`, `driver/*`, `markers/*`, `replay/*`, `network/*`) were left to
their owning clusters.

**Rewind-handler leftovers** (out of this map's lane; already tracked by
obf1): the overload/inherited-call collisions that obf1 could not rename with
the current applier remain lazy, e.g. `Chest2.method7(UUID)`/`method7(UUID,UUID[])`
(`selectLayer`/`selectLayers`), `Chest2.method15(int)` (`deleteTrack`, collides
with the `method15()` override), `method20()`/`method21()` pairs
(`unselectLayer`/`unselectKeyframe` vs `moveKeyframes`/`linkLayers`),
`Highlight2_2.method7` (`newFolder`/`rename`), `Nameplate2_2.method9`,
`Colorsaturation2.method3`/`method20`, `Holograms2.method6`/`method16`/`method17`/`method18`/`method19`,
`Click2.method2`, `Rewindhandlers2_4.method17`/`method19`. All names are known
from their `@CallbackJS("...")`/`@CallbackJS` strings and from the obf1 map.

**Not resolvable in this pass**: `Rewindhandlers2_4` line 192 calls
`OIRHOOIICOCIOOHICRRRICORRIHHIHC(var0.getParent())` on a GUI component builder;
its declaration lives in the GUI/framework bucket (out of scope), so the token
is left unresolved here.

**No solid evidence / left lazy**:
* `ModifierKeybindOption.field16` + `method21()` (boolean default true, setter
  `Data.method3(boolean)`, cleared by `Data.method11()`). It is passed as the
  6th ctor arg but no reader was found in the tree, so no name was guessed.
* `ModifierKeybindOption.Data.method3(boolean)` / `method11()` -- same flag.
* `AbstractKeybindOption.method11()`/`method12()` getters and
  `method13()`/`method11(long)`, `method14()`/`method14(int)`,
  `method12(boolean)` setters are overload pairs; names are given in the map as
  field names (`disabledInGui`, `ignoreRecentKeyPress`, `lastPressTime`,
  `down`, `holdThreshold`) but the overloaded accessors themselves need the
  signature-aware pass.
* `AbstractKeybindOption.method2(BooleanConsumer)`/`method3(Runnable)`/
  `method15()`/`method16()` (press/hold listener API) -- safe names would be
  `onHold`/`addPressAction`/`getPressActions`/`getHoldListeners`, but `method2`,
  `method3`, `method15`, `method16` all exist on `AbstractOption` with other
  signatures, so they were not mapped to keep the map apply-safe.

**Twins outside the requested paths.** `client/replay/rewindhandlers/*`
(`RewindTimelineBridge`, `RewindTimelinePanel`, `RewindPropertiesBridge`,
`RewindEditorBridge`, `RewindInteractionJsApi`, `MediaExporterJsApi`,
`RewindTimelinesListBridge`, `ExportSettingsJsApi`) are the same classes as the
old `client/framework/feature/rewind/rewindhandlers/*` handlers. They share the
tokens named by obf1; they should receive the identical names when the dedupe
pass picks one home. They were not mapped here because the brief's path filter
(`client/framework`, ...) does not include `client/replay`.

## 5. Evidence files consulted

* `tools/renames/wave5/members-obf.tsv`, `members-lazy.tsv`, `members-obf1.tsv`
* `tools/mappings-snapshot/member-renames.tsv` (raw -> lazy per owner)
* `libs/lunar-renamed-classes.jar` (`javap` of `LightingExtension4913$Data2`,
  `LightingExtension49132$Data`, `LightingExtension4913$Data`,
  `LightingExtension49133`)
* `src/main/java/com/moonsworth/lunar/client/lighting/{AbstractKeybindOption,
  SimpleKeybindOption,ModifierKeybindOption,ToggleOption,BooleanOptionBuilder,
  DefaultValueBuilder,OptionBuilderBase}.java`
* `src/main/java/com/moonsworth/lunar/client/guiRewindhandlers/mixin/GuiRewindhandlers6Task.java`,
  `.../guiRewindhandlers/mixin/GuiRewindhandlers3.java` (500 ms key window)
* `src/main/java/com/moonsworth/lunar/client/framework/FeatureToggleKeybind.java`,
  `client/mod/movement/{Snaplook,Freelook}.java` (call-site shapes)
* `libs/multiver-full/lunar-lang.jar` `lang/lunar/en_US.json`
  (option display strings; no description exists for the two internal flags)
