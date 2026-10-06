# Restructure plan (AFTER the naming pass is complete)

The user's directive: once every class/package has a real name, do a second
pass that restructures the code so it is cleaner and the pieces link together
properly. This is tracked here so it is not lost between sessions.

## P0 (user directive): src/ is the sole source of Lunar Client

**Goal:** `src/main/java` contains every `com.moonsworth.lunar.**` class; the
client builds and launches from source alone. No Lunar/obfuscated jar on the
classpath — `libs/` shrinks to genuine third-party libraries only (asm,
adventure, apollo, netty, gson, ...), and `libs/lunar-renamed-classes.jar` (+
the `lunar-libraries.jar` symlink) is deleted once empty.

**Backlog (measured 2026-09-15):** the jar holds 7,349 top-level Lunar classes;
5,348 have no source yet (excluding renamed twins and the intentionally deleted
shaded `forge/lib` Guava). Biggest holes:
`client/framework/feature/mod` (376), `client/framework/feature/rewind` (254),
`click/holograms/mixin` (54), `markers/mixin/gui` (52),
`framework/feature/{markers,debug,keystrokes,tiertagger}` (~120),
`highlight/mixin/**` (~90), `client/framework` core (66), `client/lighting`
core (77).

**Method (batched campaign):**
1. Decompile with `tools/rescue_classes.py <packages>` (Vineflower 1.12; skips
   classes that already have a renamed source twin unless
   `--include-renamed-twins`; outer classes must be included whenever a rescued
   class references their nested types).
2. **Rescue with quarantine** (`tools/rescue_with_quarantine.py`): add the batch,
   compile, and move any rescued file that does not compile to
   `tools/work/rescue-quarantine/` (out of the compile, tracked in git); repeat
   until the batch is green. Quarantined files are re-attempted once their
   dependencies are rescued. This is the monotonic-progress loop — the jar only
   ever shrinks while the compile stays green.
2. **Member alignment is required.** Freshly decompiled classes contain the
   jar's obfuscated member tokens (`OOHROIO...`), while the source tree's
   renamed twins use `methodN`/`fieldN`; mixing the two produced 674 errors in
   the module-core trial. Existing tooling:
   `tools/fix_obf_tokens.py` (resolves tokens through the receiver's type chain
   using `tools/mappings-snapshot/member-renames.tsv`) — it fixed only ~10% of
   the sites in the trial (52/202 in lighting, 58/794 in framework), so the
   snapshot's member table needs to be extended or the rescued classes must be
   decompiled together with their whole package cluster so the jar-internal
   naming stays self-consistent.
3. Per batch: add, run `fix_obf_tokens`, compile, fix, commit. A batch is never
   left half-added — decompiled outers change javac resolution for their nested
   consumers, so partial batches destabilise other packages (measured: 65).
4. Fix pattern for references to renamed twins: point the decompiled code at
   the renamed source class; for stale-jar-only signatures use the old FQN
   explicitly (the run of jar-coupling fixes is documented in
   `APPLIED.md`/`PLAN-restructure.md`).
5. When a package is fully in source, trim it from the jar (monotonically
   shrinking reference jar), and finally drop the jar from the build.
6. Milestone check: compile the tree with the Maven classpath only (no
   `libs/lunar-*`), then launch (Prompt.md §5).

**libs/ audit (measured):**

| jar | size | classification | action for P0 |
|---|---|---|---|
| `lunar-renamed-classes.jar` | 49M | Lunar (stale reference jar) | delete when empty (main goal) |
| `lunar-libraries.jar` | symlink → stale jar | Lunar | delete with the jar |
| `lunar-localpatches.jar` | 28K | Lunar patch classes incl. obfuscated packages | rescue then delete |
| `lunar-altmanager.jar` | 28K | classpath source for `com/moonsworth/lunar/altmanager` (11 classes) | rescue or keep until alt-manager work |
| `asm-9.7.1-all.jar` | 280K | third-party | keep (or Maven) |
| `forge-1.8.9-compile.jar` / `Forge_v1_8.jar` | 3.6M / 3.7M | third-party (Forge) | keep |
| `optifine-1.8.9-compile.jar` / `OptiFine_v1_8.jar` | 2.5M each | third-party (OptiFine) | keep |
| `ReplayMod-v1_8-2.6.24.jar` | 17M | third-party (ReplayMod) | keep |
| `sentry-off.jar` | 36K | third-party (Sentry no-op) | keep |
| `fake-launcher.jar` | 20K | project tooling (fake launcher/backend) | keep |

**Rescue status:** tooling done (`tools/rescue_classes.py` — Vineflower + optional
KinRemapper member pre-pass; `tools/build_jar_map.py` — jar↔source pairing from the
rename ledgers). Current blocker: the member table snapshot is keyed by the old
obfuscated class generation (0 rows for the module core), and regenerating it with
`make_member_renames.py` against the current tree paired only 1,031 of 6,592 source
files because ~1,500 classes were renamed since. Next step: fingerprint-based
jar↔source class pairing (method descriptors), then regenerate the member table and
run the rescue in package clusters.

### Rescue diagnosis (module-core trial, precise)

Rescuing `client/framework` + `client/lighting` core (148 files) produced 674
errors from exactly **16 distinct obfuscated members** (132+45+15+11+6+6+… hits).
Two flavours:

1. **Annotation element names**: the jar's bytecode uses `@Annotation4(OOHROIO… =
   …)` while the jar's own annotation classes declare `method1()/method2()/method3()`
   (`javap client.util.Annotation4`). The member-rename pass renamed the
   declarations but not the annotation element pairs, so decompiled annotations
   carry names that no longer exist. Fix is positional/type matching:
   `OOHROIO…`→`method1()` (enum), `RHCICCHIRIIRCHOHRCHCIIRRRORRRR`→`method2()`
   (String), `RORIIHROROHIOHCRRHCOICOHRCOIRI`→`method3()` (boolean).
2. **Token method calls on jar/source twins** (`this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()`
   in the rescued lighting classes): the same renaming left call sites behind, so
   the rescued code calls members that the (renamed) declarations no longer have.
   Owners/descriptors for all 16 tokens were extracted by constant-pool scan and
   are ready to drive a generated fix map.

**Unblocking task:** generate the current member map = for each jar class paired
with its source twin (via `tools/pair_classes.py`, 279 rename-proof pairs so far;
extend the fingerprint with method-descriptor multisets), then run
`tools/make_member_renames.py --class-map …` to emit token→current-member rows,
then apply them to rescued files (annotation elements by descriptor, method/field
calls by owner+descriptor). After that the rescue runs package-cluster by cluster.

**Rescue progress (2026-09-15, automated loop running):**

| batch | package(s) | decompiled | kept | quarantined |
|---|---|---|---|---|
| 1 | `client/framework` + `client/lighting` (top-level) | 148 | 87 | 61 |
| 2 | `client/lighting` (subtree) | 106 | 21 | 57¹ |
| 3 | `client/framework` (whole tree) | 1166 | 880 | 152 |
| 4 | `client/highlight` `click` `markers` `inactive` | 609 | 562 | 45 |
| 5 | `client/fog` `fov` `glintcolorizer` `mod` | 853 | 390 | 102 |
| 6 | `legacy` `replaymod` + client leftovers | 3084 | 437 | 497 |

Backlog went 5,348 → **1,702** missing classes (of which 555 are quarantined and awaiting repair). ¹ the quarantine directory was
reset before batch 2; later batches append to it. Quarantined files are staged
in `tools/rescue-quarantine/` and re-attempted by
`tools/repair_quarantine.py` (copy back → run member/annotation fixers →
compile → keep the green ones).

**Coverage snapshot (after batch 7):** 4,805 / 7,349 Lunar classes present
(65%); 1,754 still jar-only; 790+ decompiled and staged in quarantine.
`src/` is 10,850 files. Name debt: 2,531 digit-named classes, 134k `methodN`
references, 102k `fieldN`, 26.5k obfuscated tokens.

## Preconditions

- [ ] every obfuscation-style name gone from `src/main/java`
      (`tools/name_inventory.py` → only vanilla/algorithms remain)
- [ ] no mass-named classes (same simple name in >2 packages)
- [ ] `error_diff` clean (baseline may be 0 by then)

## Target layout (Badlion-class cleanliness)

Reference clients (all decompiled in
`Documentation/references/mc-client-sources/sources/`) converge on the same
small vocabulary:

| client | top-level own-code packages |
|---|---|
| Badlion 2.0.0-v-beta | `config events gui manager mods thread tweaker util` (283 files own code; `mods/{misc,movement,render}`) |
| Rise 5.99 | `anticheat antipiracy command config creative event font module notifications protection proxy script setting ui util` |
| Rise 6.9.5 | `anticheat auth bots command compat component creative keybind module packet packetlog protection script security ui util value` |
| Expensive 2.0 | `command config events friend managment modules notification proxy scripts ui util` |
| Expensive Ancient | `command config discord events functions scripts ui utils` |

Shared conventions: the feature tree is **`module(s)`** (Badlion's `mods`),
settings are **`config`/`setting`/`value`**, screens are **`ui`**, plus
`command`, `event(s)`, `notification`, `packet`/`proxy`, `auth`/`security`,
`script`, `util`. The final Lunar tree must read the same way: every top-level
folder is a word you can explain in one breath, and each feature is a
subfolder of the thing it belongs to. Target (`client/`):

| target | holds | reference analogue |
|---|---|---|
| `module/` | every mod/feature: `combat/ hud/ movement/ player/ render/ misc/ skyblock/` — `framework/feature/*` dissolves into these categories, each feature keeps its package name | `mods/` (Badlion), `module(s)/` (Rise, Expensive) |
| `event/` | `LunarEventBus` + the `Event*` bus types (feature events stay with their mod) | `events/event` |
| `ui/` | screens, HUD framework, prompts, notifications, blog, external links, hosted worlds | `ui/` + `gui/` |
| `config/` | options/settings framework, migrations, mod profiles, feature flags | `config/` + `setting/` + `value/` |
| `command/` | command framework + command suites | `command/` |
| `network/` | server, ipc, websocket, apollo, transfer/ping, mumble, friend status | `packet/` + `proxy/` |
| `account/` | account/session model, skins, alt-manager glue | `auth/` |
| `render/` | pipeline, shaders, textures, fonts, particles, block outlines | — |
| `cosmetics/` | Gecko library, emote/BOBJ, hologram models, Molang, skin layers | — |
| `replay/` | the Rewind engine (project/timeline/export/GUI) | — |
| `driver/` | the WebOSR driver stack | — |
| `chat/` | chat messages/embeds, translation | — |
| `audio/` | ogg/CRC audio, music | — |
| `framework/` | bootstrap, mod framework, listener framework, bytecode transforms, loading, build info, crash reporting, security/prompts | `manager/` + `tweaker/` + `security/` |
| `util/` | real utilities only, split into `math/ io/ text/ collection/` | `util(s)/` |

Rules: `framework/feature/` no longer exists as a dumping ground; `module/` is
the feature tree. No top-level folder with <5 classes unless it is a named
subsystem. `client.mixin` mixins are distributed to live next to what they mix
into where that reads better, otherwise `mixin/` stays one folder.

## Phase 2 finding: the stale jar caps further renaming

`error_diff` measures against `tools/work/compile-baseline.txt`; javac masks
dependents of a failing root, so the true error set only surfaces one fix at a
time. During the naming wave we resolved ~15 masked errors, and the baseline is
now the 2 remaining jar-coupling files.

**Coupling rule:** renaming a source class whose *old FQN* appears in
`libs/lunar-renamed-classes.jar` bytecode breaks any source site that mixes a
jar-typed value with the renamed twin. Records (e.g. `Bridge3_5`,
`Bridge6_6`) cannot extend their old twin, so their renames are impossible
until the jar side is rebuilt.

**Deferred names (reverted to the jar FQN, re-apply after the rescue):**

| class | good name (deferred) | coupling |
|---|---|---|
| `bridge.Bridge3_5` | `BridgeMethodTarget` | final record in `Bridge2_35` signatures |
| `bridge.Bridge6_6` | `MixinTargetMemberResolverBridge` | final record returned by `Bridge4_2.method3` |
| `bridge.Bridge3_24` | `FramebufferBridge` | `Bridge.method56/58` return it |
| `bridge.Bridge_52` | `FramebufferBuilder` | `Bridge.method56/58` builders |
| `bridge.Bridge_15` | `BytecodeEmitter` | `Bridge_67` method param |
| `bridge.Bridge3` | `MethodParameter` | `Bridge_15.method1` param |
| `ichor.mixin.MixinHelper3` | `TypeSignature` | `Bridge_15.method1` param |
| `client.MixinExtra` | `ApolloApprovedServer` | `Ref` chain returns it |
| `client.highlight.HighlightImpl_3` | `ApolloPacketEvent` | abstract `Highlight3Iterator_3.method3` param |
| `heightlimit.Heightlimit` | `HeightLimitOverride` | Apollo manager `method14` param |
| `bridge.Bridge5Extension62` | `GuiScreenWrapperBridge` | `Bridge.method18` return |

**Systematic fix (next milestone):** remap the stale reference jar with the
accumulated rename maps (ASM `ClassRemapper`; `libs/asm-9.7.1-all.jar` is
available), producing `libs/lunar-renamed-classes-remapped.jar` so the jar's
bytecode references the new FQNs. That removes the whole coupling class at
once, after which the deferred names can be applied and the baseline can go to
zero.



## Goals

1. **One home per subsystem.** `client/{event,replay,render,cosmetics,network,...}`
   own their code; no `framework.feature.*` vs `mod.*` overlap: a mod's HUD/event/
   render helpers live with the mod, shared engines live in the bucket.
2. **Flat junk out of `client.util`.** The root still holds ~140 top-level
   classes (Ref, FastMath, …). Split into `util/{math,io,text,collection,net,…}`
   only where the class is arguably a utility; anything feature-specific leaves.
3. **Rescue jar-only leftovers** so classes stop resolving from
   `libs/lunar-renamed-classes.jar`: `Bridge2` (+ the deferred
   `Horsestats20Extension2`/`HorsestatsType$Type{,3}`/`Vec3iBridge` renames),
   `Lazy*` stubs, `$VF` regressions. Then the deferred renames can land.
4. **Link together better:** replace remaining `BridgeN`-style loose couplings in
   source with the named bridge interfaces; make feature packages depend on
   buckets instead of on other feature packages (no cycles), and document the
   dependency direction.
5. **Runtime sanity:** the launch path (fake launcher, fake backend, account
   manager) must keep working after every structural batch —
   `error_diff` plus the VM smoke test (see Prompt.md §5).

## Method

Same discipline as the naming pass: one subsystem per batch, a map + notes
under `tools/renames/`, `error_diff` after each batch, commit per batch, and
the ledger in `APPLIED.md` updated. No big-bang restructure.

## Phase 3 (very last): member-level naming

User directive: only after the restructure is done, rename the remaining
machine names *inside* the classes — `method1`, `field3`, `arg0`, `type2`,
and the leftover obfuscated tokens (`OHROCHICOIOICHOCRROORRCIIICIHO`, …) — to
generic, meaningful names. This covers methods, fields, constructor
parameters, locals where it is inferable, and the constant/`@Override`
echoes.

Approach sketch (to be refined when we get there):

0. Scale at the start of phase 3: ~16,050 `methodN` declarations, ~10,982
   `fieldN` declarations, ~2,755 `argN` parameters and ~10,264
   obfuscated-token identifiers across 1,292 files (JVM remapping rules apply —
   1.8 has no private-nesting access, so only cross-file references need edits).
1. Build an index of every declaration + every reference site
   (`tools/analyze_refs.py` exists; extend it for members).
2. Name from evidence, in this order of strength: `@Override` target signature
   from the mapped Minecraft/Forge/Lunar class → interface method it
   implements → call-site argument types → internal use (e.g. a field only
   read as `this.field3.getX()` in a render loop is a "renderer/cache").
3. Apply with a refactor-aware tool (rename + all references, collision-checked
   per class), not blind token replacement: methods are overloaded and
   shadowed, so the tool must resolve receivers.
4. Batch by package/subsystem, `error_diff` after each, same commit discipline.
5. Keep the obfuscation maps (`tools/mappings-snapshot/`, `kin2*` tooling) as a
   cross-check where Lunar's own names are recoverable from the runtime jar.

This is a much bigger job than class naming (hundreds of thousands of member
references) — it starts only when phase 1+2 are green and committed.
