# cluster-26 notes — `com.moonsworth.lunar.client.highlight`

## What this package actually is

**Not** a block/entity highlighting feature. It is the client's **event bus framework plus
the generic (non-category) event types**. The package name "highlight" is an artifact: the
obfuscated package `com/moonsworth/lunar/client/ORCHOHHCOHCORRICRIHCHHRORHHCHH` was named
after one of its own classes in the earlier restructure pass
(`package-renames.tsv`), and every `*/highlight` subpackage in the tree was derived from it.

Evidence (strings preserved in the decompiled code):

* `Highlight2Iterator` ctor: `throw new IllegalAccessError("DebuggingEventBus is being used in production environment!");`
* `Highlight2` exception paths: `"EventBus [" + clazz + "]"`.
* `Highlight2` thread check: `"Event [" + clazz1.getSimpleName() + "] was fired on the wrong thread! If this is intentional, make it implement ThreadedEvent"`.
* `Highlight2.method11`: `"Event " + clazz1.getName() + " has a @TriggeredBy annotation, but does not extend DynamicListenerEvent"`.
* `Highlight2Iterator.method6` names events by `getSimpleName()` when it `contains("Event")` — the codebase's own convention is `<Something>Event` / `Event<Something>`.
* Real name in the code: `"EventRenderTooltip.Pre"` (in `HighlightImpl17.Data2`) and `"EventPreAttackEntity"` (in `highlight/fishing`).

## Framework mapping

| old | new | why |
|---|---|---|
| `Highlight2` | `LunarEventBus` | client event bus; plain `EventBus` is already declared twice in the tree (`forge/mixin/EventBus`, `forge/lib/guava/eventbus/EventBus`) |
| `Highlight2Iterator` | `DebuggingEventBus` | literal class name in its own error message |
| `Highlight3` | `EventBusAccess` | default `handle(...)` registration interface (21 implementors) |
| `Highlight4` | `EventListener` | priority + `Consumer` registration entry |
| `Highlight2$Data2` | `EventBusOperation` | queued (un)register op drained on dispatch-depth 0 |
| `HighlightImpl` (out of inventory) | `CancellableEvent` | `cancelled` + `cancel()/isCancelled()` |
| `Highlight` (out of inventory) | `Event` | empty base class of every posted event |

`Highlight` and `HighlightImpl` are **not** in this cluster's inventory (they were named by the
restructure pass, not by a "DataN/MixinN" placeholder) and both simple names are declared in
~10 packages. They are follow-ups: a tree-wide word-boundary rename would be unsafe.

## Event handlers used as evidence

* `HighlightImpl2_2` — handled by `client/Highlight3Iterator` (`TntCountdownModule.TNT_TICKS`), i.e. Apollo option update.
* `HighlightImpl4` — fired in `legacy/GuiPlayerTabOverlayTabMixin` with `(NetworkPlayerInfo, Component)`; `TotemCounter.method10` rewrites the tab-list name.
* `HighlightImpl5` — handled by ~20 Skyblock container solvers; getters `getSlotId`, `method6`, `method7` (item).
* `HighlightImpl9` — fired in `rewindhandlersCore/RewindHandlers3Impl5` with `(currentScreen, nameplate text)`; handled by `Zoom`, HUD mods.
* `HighlightImpl10` — `(getScaledWidth, getScaledHeight)`; `Bossbar` / `SkyblockWitherHealthHud` reflow on it.
* `HighlightImpl11` — fired in `legacy/wrapper/GuiScreenImpl.keyTyped` on ESC, cancelled by `GuiRewindhandlers3`.
* `HighlightImpl13` — `char`/`keyCode`/`modifiers` + `HighlightType2{PRESS,RELEASE,CHAR}`.
* `HighlightImpl14` — `Markers.Data4` + `HighlightType3{CLICK,RELEASE,DRAG,SCROLL}`.
* `HighlightImpl16` — mutable screen; `TitleModule.CLEAR_TITLE_ON_SERVER_SWITCH` handler, `ServerAddress`, `ToggleSneak`, `PvpInfo`.
* `HighlightImpl17` — tooltip event (real name), stages `Pre` (measures lines/width) and `Post` (bounds cache).

## Confidence

High: `LunarEventBus`, `DebuggingEventBus`, `EventBusAccess`, `EventListener`, `ResultEvent`,
`EventRenderTooltip` (+`Pre`/`Post`), `EventRenderNameTag`, `ApolloOptionUpdateEvent`,
`EventKeyInput`, `EventRenderSlot`, `EventRenderButton`, `EventRenderTabListEntry`,
`EventRenderItemDurability`, `EventRenderParticle`, `EventRenderHologram*`.
Medium (payload-based): `EventResolutionChange`, `EventMarkerInput`, `EventScreenChange`,
`EventScreenClose`, `EventResourcePackUpdate`, `EventRenderScreenItem`, `EventRenderScale`,
`EventRenderContainerSlot`, `ApolloPacketEvent`.
Low (role/handler inference, verify before trusting): `EventScreenOpen` (`HighlightImpl9`) and
`EventScreenUpdate` (`HighlightImpl16`) — both carry a mutable screen and may be two stages of
one screen-lifecycle event; `EventRenderItemColor` (`HighlightImpl15` in cluster-23).

## Needs follow-up (no TSV row — evidence not strong enough)

| class | status / recommendation |
|---|---|
| `HighlightImpl8` | empty event, no firer/consumer found in tree (firer likely quarantined). Do not guess. |
| `HighlightImpl18` | empty event, no user found. |
| `HighlightImpl19` | extends `mixin.nameplate.HighlightImpl` (pose+screen+markers+boolean) but has no firer/consumer in the tree (`Worldeditcui` uses `mixin.gui.HighlightImpl19`, a different class). |
| `HighlightBase2$Data4`, `HighlightBase2$Data5` | identical screen-only variants; users `SkyblockGoldenRabbit` (Data4) / `SkyblockFirstLetter`, `SkyblockSelectColor`, `GuiRewindhandlers3` (Data5). Stage semantics unknown. |
| `HighlightImpl15$Data`, `$Data2`, `$Data3`, `$Data4` | marker/GUI stage variants (`SkyblockRarityBackground`, `SkyblockSuperpairs`, `SkyblockUltrasequencer`, `SkyblockInventoryButtons`, `SlotBinding`, `fishing/click/Click`, `RewindHandlers3Impl5`). Cannot tell the stages apart without the real mapping. |
| `HighlightImpl2_2` naming | payload is `(Options, Option, value)`; called `ApolloOptionUpdateEvent` here — matches `ApolloUpdateOptionEvent` in `lunar-client-names.tsv` but the client-side class is not necessarily the same type. |

## Applier caveat

Several old names in this map are declared in other packages too
(`HighlightImpl2`..`HighlightImpl19`, `HighlightBase`, `HighlightBase2`, `HighlightImpl_2`).
Without `--allow-collisions` those rows are skipped; **do not enable `--allow-collisions`** —
the applier would rewrite every `HighlightImplN` token tree-wide. The nested-class rows are
written as `Outer$Inner` on purpose so the applier skips them instead of mass-renaming `DataN`.

## Dry-run result (important)

`python3 tools/apply_class_renames.py --map tools/renames/classes-26.tsv`:
`30 rows`, only **3 applied** (`Highlight2Iterator`, `HighlightImpl2_3`, `HighlightImpl_3`),
27 skipped (collisions + nested `Outer$Inner`). An import/package-aware applier is required
before this map can be applied.

Extra rows not present in `cluster-26.txt`: `HighlightImpl2_2` and `HighlightImpl2_3`
(in-package duplicates created by the normalization pass), included for completeness.

