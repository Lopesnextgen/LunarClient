# Cluster 3 — `com.moonsworth.lunar.client.util` (remaining, 45 classes)

Source revision: `tools/renames/cluster-03.txt` md5 `2d931b152a6540fd7eb9885165aadd2f`
(45 rows, all top-level `ThreadModuleDumpN` types).
Map: `tools/renames/classes-clientutil3.tsv` (md5 `3104f5cfb3c803d72e5aebf349dd9b7b`).

This is the **leftover half of `client.util`** after `classes-clientutil2.tsv`
(`99deb192`) took the `ThreadModuleDump66…95` block. It is not one feature: it
is the package's generic toolbox plus a few globals. The single most important
row is `ThreadModuleDump63` → **`Ref`**, the static client-reference class whose
own error string names it (`"…To check use Ref.isMcLoaded()"`); it is referenced
5,218 times tree-wide.

## What this cluster actually is

* **`Ref`** (`ThreadModuleDump63`) — `MC_VERSION`, `hasModule`, bridge getters
  (`method3()` = the `Minecraft` bridge, throwing "Minecraft client is not
  available"), keybind-clash bookkeeping, resource/JSON loading, `method14/15`
  time helpers. Its `Data` nested type is the keybind-clash key.
* **Colour / math / text toolbox** — `ColorUtils` (ARGB/HSV/hex/blend),
  `FastMath` (lookup-table sin/cos, prints `[LC Fast Math]`), `NumberUtils`,
  `TextUtils`, `TextSanitizer`, `TranslationFormatter`, `DateUtils`.
* **Collections / concurrency** — `TickQueue`, `UnorderedPair`, `ValueHolder`,
  `MutableValue`, `RingBuffer`, `BackgroundExecutor` (`lunar-background-task-%d`
  pool + delayed-Future tick queue), `ListUtils`, `CollectionUtils`,
  `ExpiringWeightedCache`, `WeightedQuadtree`, `BinarySearchState`.
* **Client/OS glue** — `ServerUtils` (Hypixel gamemode), `FileExplorer`,
  `DesktopNotifier`, `BrowserUtils`, `CheckoutUtils` (Tebex/PayNow),
  `CursorManager`, `ImageUtils`, `OpaqueTextureFix`, `MimeTypeUtils`,
  `CompressionUtils` (gzip+base64), `UuidUtils`, `PotionUtils`,
  `ReflectionUtils`, `ScreenProjection`, `Vector2f`, `StringCursor`.
* **Interfaces** — `TraitBuilder` (`T build()`, the builder of `Lightoverlay9`),
  `ConfigRangeBuilder` (fluent `Config`-range settings builder),
  `DummyPlayer` (SELF/MANNEQUIN), `MarkerPredicate`, `KeyVersionPair`,
  `HudTimer`, `GuiClipState`, `DevFeatureList` (unreferenced stub).

Provenance: `tools/mappings-snapshot/restructure/remaining-renames.tsv` maps each
placeholder back to its obf name (e.g. `ThreadModuleDump63` =
`OIHCCIIRIOORHHOOICRCIORCOICOIR`), and `tools/mappings-snapshot/package-renames.tsv`
shows the obf package the util classes were flattened out of. The runtime
`lunar.jar` still ships these classes obfuscated, so names come from the source
plus the reference clients.

## Renames (45 rows)

| # | old | new | evidence (short) |
|---|-----|-----|------------------|
| 1 | `ThreadModuleDump3` | `ServerUtils` | `server` field + `getServer()`, `method4("skyblock")`, client-brand `startsWith` |
| 2 | `ThreadModuleDump4` | `TickQueue` | `ArrayDeque<(value,tick)>`, evicts older than N ticks; KnockbackTrainer |
| 3 | `ThreadModuleDump5` | `UnorderedPair` | symmetric `equals()` (`a,b == b,a`) |
| 4 | `ThreadModuleDump6` | `ValueHolder` | private value + `get()/set()`, passed as mutable ref in rewind code |
| 5 | `ThreadModuleDump23` | `ColorUtils` | ARGB pack/unpack, HSV/HSB, hex parse/format, blend/lerp, NamedTextColor |
| 6 | `ThreadModuleDump24` | `ExpiringWeightedCache` | weight `Function<V,Long>` + time/total-weight eviction, `onRemove` |
| 7 | `ThreadModuleDump25` | `BinarySearchState` | min/max/current half-step with undo/redo stacks, per-name instances |
| 8 | `ThreadModuleDump26` | `MutableValue` | public mutable `value`; increment/decrement counter in Shields |
| 9 | `ThreadModuleDump27` | `StringCursor` | `peek/skip/skipWhitespace/rest`; command parser |
| 10 | `ThreadModuleDump28` | `FileExplorer` | `explorer.exe` / `open` / `xdg-open` a directory |
| 11 | `ThreadModuleDump29` | `DesktopNotifier` | `notify-send` / `SystemTray` / `osascript` + capability enum |
| 12 | `ThreadModuleDump30` | `KeyVersionPair` | `toString()` = `"KeyVersionPair(keyCode=…, key=…, legacyKeyCode=…)"` |
| 13 | `ThreadModuleDump31` | `ReflectionUtils` | hierarchy walk + annotation scans, EventHandler parameter check |
| 14 | `ThreadModuleDump32` | `DevFeatureList` | stub `List<String>` provider, no-op gated on production; no callers |
| 15 | `ThreadModuleDump33` | `PotionUtils` | potion damage id → registry name; ambient/extended/upgradable bits |
| 16 | `ThreadModuleDump34` | `DateUtils` | today/tomorrow/inDays/daysAgo + localized `DateTimeFormatter` |
| 17 | `ThreadModuleDump36` | `UuidUtils` | UUID ↔ dashed string / `int[4]` / two longs |
| 18 | `ThreadModuleDump37` | `BackgroundExecutor` | `lunar-background-task-%d` pool + scheduler + delayed Futures |
| 19 | `ThreadModuleDump38` | `FastMath` | lookup-table sin/cos; `[LC Fast Math]` self-test |
| 20 | `ThreadModuleDump40` | `NumberUtils` | parse/optional/roman/compact/`b`/`k`/`m` + grouped formatting |
| 21 | `ThreadModuleDump41` | `RingBuffer` | circular byte buffer; `"RingBuffer overflow! Dropping N bytes."` |
| 22 | `ThreadModuleDump42` | `CheckoutUtils` | `openTebexJsCheckout` / `openPayNowJsCheckout` via launcher IPC |
| 23 | `ThreadModuleDump43` | `ConfigRangeBuilder` | self-typed fluent builder of `Config` ranges/ordinals/strings |
| 24 | `ThreadModuleDump44` | `TraitBuilder` | `T build()`; the `Builder` type of `Lightoverlay9` traits |
| 25 | `ThreadModuleDump45` | `HudTimer` | pausable/steppable elapsed timer for HUD mods; `"%.2fs"` |
| 26 | `ThreadModuleDump46` | `TextUtils` | word-wrap by width, username check, UPPER_SNAKE/CamelCase/kebab |
| 27 | `ThreadModuleDump47` | `WeightedQuadtree` | quadtree over `IntRectangle` with max-weight query; `Data` facade |
| 28 | `ThreadModuleDump48` | `LunarConstants` | `.lunarclient` dir tree, Gson instances, master/dev/beta, `bootstrap()` |
| 29 | `ThreadModuleDump49` | `CursorManager` | `cursors/*.png`, HIDDEN/CROSSHAIR/IBEAM/HAND/HRESIZE/VRESIZE |
| 30 | `ThreadModuleDump50` | `OpaqueTextureFix` | forces opaque block textures opaque; `TextureMapMixin` |
| 31 | `ThreadModuleDump51` | `MarkerPredicate` | `@FunctionalInterface boolean accept(Markers.Data2, int)` |
| 32 | `ThreadModuleDump52` | `CompressionUtils` | GZIP + Base64 for `lcdwp1:` share strings |
| 33 | `ThreadModuleDump53` | `ListUtils` | remove-and-swap-with-last list element |
| 34 | `ThreadModuleDump54` | `DummyPlayer` | SELF/MANNEQUIN dummy player (ticks, nametag, game profile) |
| 35 | `ThreadModuleDump55` | `MimeTypeUtils` | extension/URL → MIME type |
| 36 | `ThreadModuleDump56` | `TranslationFormatter` | resolves `$1` / `${name}` / `$s` lang placeholders |
| 37 | `ThreadModuleDump57` | `KeyBindingOrder` | reorders keybind array so registered binds sort last |
| 38 | `ThreadModuleDump58` | `TextSanitizer` | keeps printable non-`§` chars; Apollo payload sanitizer |
| 39 | `ThreadModuleDump59` | `ImageUtils` | WebP decode + `BufferedImage` → packed `int[]`/ARGB |
| 40 | `ThreadModuleDump60` | `Vector2f` | mutable float x/y with `set()`/`normalize()` |
| 41 | `ThreadModuleDump61` | `BrowserUtils` | http/https/lunarclient scheme validation + embedded browser |
| 42 | `ThreadModuleDump62` | `GuiClipState` | static clip state (`IntRectangle` bounds + flags) + `reset()` |
| 43 | `ThreadModuleDump63` | `Ref` | global client reference; error text names `Ref.isMcLoaded()` |
| 44 | `ThreadModuleDump64` | `ScreenProjection` | world↔screen projection, screen ray, camera pos, scaled W/H |
| 45 | `ThreadModuleDump65` | `CollectionUtils` | `make/firstNonNull/isNullOrEmpty`, getLast/Next/Previous, `DataResult` validators |

## Applier dry run

```
python3 tools/apply_class_renames.py --map tools/renames/classes-clientutil3.tsv
# → applied=45 skipped=0 files_touched=1142 files_renamed=45 mode=dry-run
```

Every row is collision-free: no new simple name is already declared tree-wide
(checked with `grep -rE "\b(class|interface|enum|record) NewName\b" src/main/java`),
and none collides with an old name in another package.

## Caveats / follow-ups

* **`ThreadModuleDump32` → `DevFeatureList`** is the one low-confidence name. The
  class is an unreferenced stub: `List<String>` returning `List.of()` plus a
  no-op guarded by `Nameplate.field4` (production). It has no callers and no
  mixin/service references, so the name is inferred from the production gate.
* **`ThreadModuleDump62` → `GuiClipState`** is also inferred: the fields are only
  ever *written* in the decompiled tree (`field2` = panel `IntRectangle`), so the
  reader is presumably an ASM/`@Shadow` mixin outside the source tree.
* `Vector2f` (`ThreadModuleDump60`) shares its simple name with `org.joml.Vector2f`
  but no file that references it also imports the joml type, so no import clash.
* `ThreadModuleDump63` → `Ref` also renames its nested `Data` usages; the nested
  type itself (`Ref.Data`) is left as-is (not in this cluster).