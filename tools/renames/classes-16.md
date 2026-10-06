# Cluster 16 — `com.moonsworth.lunar.bridge.horsestats` (29 classes)

All 29 paths in `cluster-16.txt` exist; nothing missing. Map: `classes-16.tsv`
(25 rows; columns package / old / new / evidence). Four classes are unresolvable
stubs and are listed under "Skipped" below.

> **The package name is a red herring.** This is **not** the Horse Stats HUD.
> `horsestats` is a rename-pass artifact: the obfuscated subpackage
> `.../HRICOROOOCCOCOROCRHHCRRIRCOICO/CCHOHROIOHHCCCOCIIOIRCHRROHHRC` got the
> same `horsestats` name that was assigned to the unrelated client feature
> package `client/.../horsestats` (see `package-renames.tsv` line 12 and
> `package-renames.md`). In reality this is Lunar's **generic bridge API
> package**: duck interfaces injected into Minecraft classes by
> `com.moonsworth.lunar.legacy.mixin.*`, plus a handful of platform-neutral
> value enums and helpers used everywhere in the client. The real Horse Stats
> HUD lives in `com.moonsworth.lunar.client.horsestats` (a different cluster).

## How the identities were established

1. **Implementing mixins / wrappers** in `src/main/java` and in the remapped
   runtime jar (`tools/work/staging/remapped/source`, the renamed `lunar.jar`):
   `Vec3iMixin`, `BlockPosMixin`, `EnumFacingMixin`, `EnumChatFormattingMixin`,
   `DamageSourceMixin`, `ModelRotationMixin`, `GuiResourcePackListMixin`,
   `MovingObjectPositionMixin`, `IChatComponent*Mixin`, and the
   `legacy/wrapper`+`legacy/wrapper/util` handlers. `javap` on the jar classes
   gave the exact vanilla types (`Horsestats7Handler` holds a `FaceBakery`,
   `Horsestats16Task` the box geometry, `Horsestats23Handler` the clipboard,
   `Horsestats26` the `GameProfile` property merge, `Horsestats13Handler` the
   chat-component flatten/factory).
2. **Delegates used by the interfaces**: `Bridge_10` = `NativeImage`
   (`bridge$getPixels/getFormat/writeToFile`) for `Horsestats11`;
   `BridgeExtension2_5` = living entity for `Horsestats9`;
   `Bridge7_6.bridge$getLanguages` for `Horsestats10`; `Bridge2.method53` for
   `Horsestats16`; `Bridge.method47/57/69/70` singletons for
   `Horsestats23/13/26`.
3. **Call sites in `src/main/java`** for the heavily used enums:
   `HorsestatsType8` (~85 files, `GRAY + "[..."`, `getTextWithoutFormattingCodes`,
   Adventure conversion), `HorsestatsType4` (Waila/killsounds hit tests),
   `HorsestatsType5` (key-bind clash report), `HorsestatsType2`
   (`bridge$getEquipmentInSlot`).
4. **Obf name → restructure map** (`remaining-renames.tsv`, lines 25-75) was
   only used to confirm that the files in this cluster are the top-level classes
   of the package and to find the corresponding jar classes; it carries no
   semantic information by itself.

The suffix convention follows the tree (`BedPartTypeBridge`, `BlocksBridge`,
`DirectImageBridge`, ...): a **duck interface implemented by a Minecraft
class** gets `<McpName>Bridge`, a **standalone enum/record mirroring a
Minecraft concept** keeps the concept name (or `<Concept>Bridge` where the plain
name is taken by the duck, as with `EnumFacing`).

## Mapping table

| old | new | target / concept |
|---|---|---|
| `Horsestats20` | `Vec3iBridge` | `net.minecraft.util.Vec3i` (implemented by `Vec3iMixin`) |
| `Horsestats25` | `EnumFacingBridge` | `net.minecraft.util.EnumFacing` (`EnumFacingMixin`) |
| `Horsestats21` | `MovingObjectPositionBridge` | `net.minecraft.util.MovingObjectPosition` |
| `Horsestats19` | `DamageSourceBridge` | `net.minecraft.util.DamageSource` |
| `Horsestats22` | `EnumChatFormattingBridge` | `net.minecraft.util.EnumChatFormatting` |
| `Horsestats2` | `ModelRotationBridge` | `ModelRotation.rotateFace` (`ModelRotationMixin`) |
| `Horsestats7` | `FaceBakeryBridge` | `FaceBakery` + `SimpleBakedModel$Builder` + `BlockPartFace` |
| `Horsestats4` | `GuiResourcePackListBridge` | `GuiResourcePackList` backing list setter |
| `Horsestats5` | `IChatComponentMarker` | empty marker both `IChatComponent` mixin interfaces extend |
| `Horsestats10` | `LanguageBridge` | language/locale entry (`LanguageManagerMixin`) |
| `Horsestats11` | `ImageConverterBridge` | `NativeImage` (`Bridge_10`) ⇄ `BufferedImage` |
| `Horsestats13` | `ChatComponentFactoryBridge` | chat component → text / translated-component factory |
| `Horsestats16` | `TexturedBoxBridge` | cached textured box used by holograms/nameplates |
| `Horsestats18` | `KeyBindingEntry` | id + source + binding for the key-bind clash UI |
| `Horsestats23` | `ClipboardBridge` | system clipboard get/set |
| `Horsestats26` | `GameProfilePropertyMerger` | `GameProfile` properties merge hook |
| `Horsestats29` | `LunarSymbols` | Unicode chat/HUD glyphs (❤ ⚔ ☠ ⚡ ☀ ✿ ...) |
| `HorsestatsType2` | `EntityEquipmentSlotBridge` | `EntityEquipmentSlot` (MAINHAND/OFFHAND/armour) |
| `HorsestatsType8` | `ChatFormatting` | Lunar's `EnumChatFormatting` (Adventure-aware) |
| `HorsestatsType3` | `EnumFacingValue` | standalone 1.8 `EnumFacing` copy |
| `HorsestatsType4` | `MovingObjectTypeBridge` | `MovingObjectPosition.MovingObjectType` |
| `HorsestatsType5` | `KeyBindingSource` | MINECRAFT vs LUNAR binding origin |
| `HorsestatsType6` | `DyeColorBridge` | 16 `DyeColor` constants |
| `HorsestatsType7` | `ClickEventActionBridge` | `ClickEvent.Action` subset |
| `Horsestats_2` | `RotationOperator` | joml `Quaternionf` rotation functional |

## Skipped (no evidence anywhere in the tree)

These four have **no members beyond the signature**, **zero references** in
`src/main/java`, in the remapped runtime jar, in the quarantine/decompiled
staging trees, and in any jar's constant pools (only their own class files
match). They carry no `@Annotation(version→class)` metadata either, so no
target can be derived:

| old | content | status |
|---|---|---|
| `Horsestats9` | `float method1(BridgeExtension2_5, BridgeExtension2_5)` | unresolvable; dead in this build (do **not** guess a name) |
| `Horsestats17` | empty | unresolvable, dead |
| `Horsestats24` | empty | unresolvable, dead |
| `Horsestats27` | empty | unresolvable, dead |

In the runtime jar `Horsestats17/24/27` are 103-byte empty interfaces
(`javap -v`: no interfaces, fields, methods, attributes). They look like
forward-compat stubs for other platform versions; renaming them would be
invention, so the map leaves them alone.

## Collisions and applier behaviour

`tools/apply_class_renames.py` (v1) refuses rows whose old simple name is
declared in more than one package. Five rows hit that:

| old | also declared in |
|---|---|
| `Horsestats2` | `client.horsestats`, `client.horsestats.mixin` |
| `Horsestats4` | `client.horsestats`, `client.horsestats.mixin` |
| `Horsestats5` | `client.horsestats`, `client.horsestats.mixin` |
| `Horsestats_2` | `client.horsestats` |
| `HorsestatsType2` | `bridge.horsestats.mixin` (the `EnumParticleTypes` enum) |

**Do not run those five with `--allow-collisions`** — a global word-boundary
replace would corrupt the unrelated `client.horsestats` classes. Use
`tools/apply_class_renames_aware.py`, which resolves each reference from the
file's package/imports, or apply them after the `client.horsestats` cluster.

Dry runs on the current file:

* v1 applier: `applied=20 skipped=5 files_renamed=20` (the skipped ones are
  exactly the five collisions above). `files_touched` moves as other subagents
  edit the tree concurrently (191 → 196 during this session), so only the
  applied/skipped counts are meaningful.
* All 25 new names are declared nowhere else in `src/main/java` (checked with
  the applier's own declaration index) and appear in no other
  `tools/renames/classes-*.tsv` map.

## Follow-ups

1. `Horsestats20Extension2` (`BlockPos` duck, implemented by `BlockPosMixin` and
   `MutableBlockPosMixin`, plus the joml `Vector3iMixin`) sits in this package
   but is **not in `cluster-16.txt` nor in `inventory.tsv`**. It should be
   `BlockPosBridge`; left untouched here to keep the cluster scope exact.
2. Related out-of-cluster types that belong to the same API family and should be
   renamed in their own clusters: `Horsestats20Extension` (annotated →
   `net/minecraft/core/SectionPos`), `Horsestats19Extension`,
   `HorsestatsType` (`SHOW_TEXT`), `Horsestats$Type`
   (MISS/BLOCK/LIGHT/... — hit/effect type), `Horsestats_3`/`_4`,
   `Horsestats6/8/12/14/15/28/30` (quarantined), and the `mixin`/`mixinCore`/
   `mixinExtra` subpackages.
3. The `bridge/horsestats` package name itself is wrong (it is the generic
   bridge API bucket). A package rename is out of scope for class maps, but the
   package should eventually be merged back into `com.moonsworth.lunar.bridge`
   or renamed `com.moonsworth.lunar.bridge.minecraft`.
