# Prompt.md — Lunar Client 1.8.9 Deobfuscation: Project Handoff (updated)

> **READ THIS WHOLE FILE BEFORE TOUCHING ANYTHING.**
> It is written for an AI assistant continuing this work. Assume you know nothing.
> Everything here is verified against the actual files on disk as of this handoff.
> The project lives in `~/Lunar-Client-Reversal/`.
>
> **Big picture: the client now launches to the full Lunar main menu.**
> The remaining headline task is the account/alt manager (Singleplayer and
> Multiplayer should work without Lunar's launcher), plus the source-tree
> cleanup/renaming that is still queued.

---

## 0. THE ORIGINAL USER REQUEST (VERBATIM — DO NOT "INTERPRET" IT AWAY)

The user's request for this project, word for word:

> "~/Downloads/ABDM/Compressed/mc-client-sources-main/sources '~/Projects/Badlion 2.0.0-v-beta' rename classes and structure the client properly there seems to be weirdness all around some braindead AI didn't CHECK classes and folders before renaming them look at git history and files in ~/Projects/ to understand this project. Your main working directory is ~/Lunar-Client-Reversal/ git commit good stuff you do. Don't stop till it's better named than badlion! assure it also has the relevant assets? in resources/
>
> this may be useful? https://github.com/Lunar-Mapping-Project/mappings.git https://github.com/Lunar-Mapping-Project/mappings/releases/tag/genesis%2F2.22.25-2632
> eh it's old though
> But! https://github.com/orgs/LunarClient/repositories?type=all lunar client DOES have some opensource stuff that could help with namings.
>
> I remember that I started this project with this initial prompt:
>
> "Your job: Make a lunar client deobf. I installed too lunar clients, one e with forge and one without. I don't think it matters too much but maybe the forge one is less obfuscated or it has extra mappings so that Minecraft mods actually work properly and you could probably locate the mappings 1.8.9 This directory has EVERY possible thing you could want!  <repository>
>   <id>forge-maven</id>
>   <name>Forge Maven</name>
>   <url>https://maven.minecraftforge.net/<repository></url>
> </repository>  there also exists minecraft {
>     version = "1.8.9-11.15.1.1722"
>     runDir = "run"
>     mappings = "stable_20"
> } or stable_22 but we have those downloaded somehwere here in the directory I believe regardless! here in ~/Documents/Project/Deobf so your job is simple: deobf lunar client and maybe use mappings to get the net/minecraft shit? ~/Lunar-Client-Reversal/src you can replace or hack it on to the src/ in here! EVERYTHING you could possibly want is here ~/.var/app/com.lunarclient.LunarClient/ LOOK at how previous shit in this directory worked note some of this may be OLD code, use the stuff in development kit which has deobfuscators and decompilers, you may make the pipeline tools in MavenMCP-1.8.9/ for deobf and note everything you have done in a pipeline-work.md we need a full deobf! it should be nice and clean! should it help you another PVP client's sourcecode is there for reference '~/Projects/Badlion 2.0.0-v-beta'  final lunar client directory may be com/lunarclient ? assure the necessary libs eg replay mod are there too and assure it compiles and works perfectly and MavenMCP-1.8.9/ is the final destination. assure assets are bundled inside there too! sdkman is installed on this system if you need a java version assure it's like java fx zulu though you can search for javas using sdkman "
>
> PLEASE learn how to properly name stuff from ~/Projects/Badlion 2.0.0-v-beta/ and this VERY useful project: ~/Downloads/ABDM/Compressed/mc-client-sources-main/sources/
>
> Assure you TRULY understand this project! The other AI did NOT. It made some whacky choices. You can look at /tmp/opencode for some of the cache of the shit it did? Classes don't even correspond to the correct feature nor do the mapped things. It didn't even launch subagents to UNDERSTAND the original lunar code + our current decompiled lunar code to understand which classes do what and to not have a million unclean lazily named shit like Mixin1 Mixin2 ALSO
>
> we need a subagent for the not-named shit like obj1 2 3 (etc) and field1 2 3 (etc) method 1 2 3 (etc) number 1 2 3 (etc) and similar not well named class vriables
>
> Class names should be clean and not lazy, features deserve to be correct like the reference clients badlion as well as the OTHER stuff in mc-client-sources-main/ read the .md files to understand pipeline work"

Later user messages that changed priorities (verbatim):

> "your main job before renaming: get decompiled lunar fixed to work on maven mcp base working and java compiled and get the game LAUNCHING! hopefully we can salvage the other AI's work? rememver we won't launch from lunar's launcher we need to get it all working and launching from ~/Lunar-Client-Reversal/ before renaming even begins so that we can make renaming feasible I mean you may not need to decompile lunar stuff if we already have the stuff on disk but if it's all fucked up then yeah you gotta like redo some stuff. but yeah continue and understand this base"

> "i just launched a lunar version but yeah continue I just closed it. anyway yeah continue"

> "it may have updated the lunar client because I launched it but maybe not continue
>
> there is a file extractor I think attributed to YOU using a lot of memory please be weary of it"

> "there is a baloo_file_extractor taking a fuck ton of memory on my system is that one of your old processes? Look into it"

> "two lunar versions* which may be sperate or like all in one jar?"

> "Lunar's internals are weird... doesn't lunar-libraries.jar have like a FULL deobfuscation of the client?? what the hell is this company doing? why is one part obfuscated with weird names but this is a complete deobf?"

> "Sending event to Sentry: 42fefe6f4e4a454ab80f141a85bfb71c are we like sending info about our launch to lunar? LOL maybe uh not?"

> "~/Lunar-Client-Reversal/src/reference this seems weird and unnecessary? ~/Lunar-Client-Reversal/src/main I assume you are gonna make the fixed code be here and the client is going to be FULLY launchable and developable from eg Intellij and that the code is all in main/ right? anyway continue! (I would like this to launch and then cleaning can begin!) anyway continue you seem to be working things out! may all the references and things mentioned prior to this prompt help you and for us to get this working!"

> "perhaps commit changes first and then continue"
>
> "go for it you have comitted incase you need to revert"

> "how much longer till you get a launch?"

> "gradle/maven dual compatibility possible? I want FAST builds" ... "Add Gradle alongside Maven"

> "are you sure the merging references/ thing was the best way to do this?"

> "ADD debug don't 'enable' existing not very good solutions FUCKING ADD IT"

> "I want it built in"

> "add the token login things awesome alt manager has in common/"

**Priority order now:**
1. Singleplayer + Multiplayer must work without Lunar's launcher, and the
   top-left account area must become a **built-in alt manager** (cracked and
   premium accounts; user explicitly wants ColdPlay + awesome-alt-management +
   ksyzov/AccountManager merged "to be the very best").
   **The buttons are currently gated:** clicking **Singleplayer**,
   **Multiplayer** or **Discover** fails/whines because Lunar requires its
   launcher/account state (the "You must have the launcher open to sign in to
   an account" path). Removing that gate so those screens open and work with
   an offline/cracked or locally-added account is part of this task.
2. Keep the launch green and committed.
3. **THE RENAMER PHASE IS STILL ENTIRELY LEFT.** Launching and the account
   layer are prerequisites, not the deliverable. The user's original ask is a
   clean, fully renamed source tree (no `MixinN`/`methodN`/`fieldN`/`objN`,
   real feature names, subagents used, Badlion + 509 clients as references).
   See §4 for the concrete plan, current counts and tooling. Do not consider
   the project finished because the game launches.

> **HONEST STATE OF THE WIP (read this):** the fake-launcher and alt-manager
> work described in §3 was written at the end of the session but **is not
> functional yet**. The game does not connect to the fake launcher (handshake
> is null), FML does not discover the alt-manager mod (the whole mod idea is a
> dead end as tried), RSHIFT does nothing, and **Singleplayer / Multiplayer /
> Discover are still gated by the account/launcher check** so they whine
> instead of opening. The sources/build tools are valuable starting points,
> not working features. The *launch* itself (stock Lunar to the full main
> menu) **is** working and committed.

---

## 0.5 LATEST SESSION UPDATE — READ THIS BEFORE §1/§3 (2026-09-13, evening)

The account gate is **solved and the alt manager is implemented**; the notes in
§1–§3 below describing it as unsolved are historical. What actually happened:

### The exact gate (found in `multiver/lunar.jar`, not the local tree)

* The React main-menu buttons call `window.lunar.setScreen("SINGLEPLAYER"|...)`.
  Java handler: `com.moonsworth.lunar.client.HIRIHCROOIRIORCCOIRRCRHOHCCRRO.
  HHRROIIHRRICIIHIIHICRHHRHOHHOO.ORICHRORRORHORHOIHCRHOORCRRHOI.
  HORHROIOIOICIRHIOCOICHHHIHCIIO.HHRROIIHRRICIIHIIHICRHHRHOHHOO.
  RCIOICOHRIOIIRRRROCRHCIICRROHO.setScreen()`. It blocks when
  `screen.isRequiresAuthentication() && !AccountManager.IHROORHHHHICIIIHHICRIHIOHIIHRO()`.
* `IHROORHHHHICIIIHHICRIHIOHIIHRO()` returns **`true unconditionally when the
  build is not production** (`ICRHORIIHOHROHOHOCOOHOOCOORRHO.HORHROIOIOICIRHIOCOICHHHIHCIIO.HCRRCCCCHRHHRCCOCRCOOORIHIRHOH == false`).

### The fix (committed, working)

* `libs/lunar-localpatches.jar` (built by `tools/build_localpatches.sh`, first
  on the game `-cp` **and** first in `--ichorClassPath`) carries
  `lunarBuildData.txt` with `production=false` → the client runs in its own
  dev/gradle mode. Singleplayer, Multiplayer and Discover open now.
* Dev mode tries to load `com.moonsworth.lunar.ichor.ProfileData` (missing in
  this runtime) → stub shipped in the patch jar. The stub also starts the alt
  manager's asset-connect warm-up thread (first code of ours guaranteed to run).
* Dev mode builds a real IPC handshake from
  `<data>/launcher-cache/installation-id`, so the game now connects to the fake
  launcher: log line `[LC IPC] Connection established` and real RPCs land in
  `run/logs/fake-launcher.log`. (Do not add `--launchId`; it is unnecessary.)
* Dev mode honours `serviceOverrideAuthenticator` / `serviceOverrideAssetServer`
  / `serviceOverrideApi` **system properties**, which is how the local backend
  below is wired in.

### The alt manager (what exists now)

* `tools/localpatches/tools/AccountsPatcher.java` bytecode-patches Lunar's
  "accounts" UI service (a source override is impossible: the runtime jar has a
  class/package name clash there, javac refuses the package). Added callbacks:
  `listAccounts`, `addOfflineAccount(name)`, `startPremiumAccount`,
  `getPremiumStatus`; `selectAccount` is rewritten to switch session locally.
* `tools/localpatches/src/com/moonsworth/lunar/altmanager/`:
  `LocalAccounts` (accounts.json schema), `AccountOps` (reflection bridge +
  asset reconnect trigger), `MicrosoftAuth` (real device-code → Xbox Live →
  Minecraft services login).
* `tools/ui/altmanager.js` + `tools/patch_ui.py`: local copy of the WebOSR UI
  with an "ALTS" panel (add cracked/premium, select, remove). The stock
  "Add account" button is redirected to it.
* `tools/fake-launcher/FakeBackend.java`: local **Authenticator** and
  **AssetServer** websockets (ports 28191/28192) — the AssetServer going READY
  is what removes the "Connecting..." chip; its login answers set
  `hasAllCosmetics/Emotes/Sprays/Badges` so the client owns everything.
  `FakeLauncher` now matches the real CamelCase RPC names
  (`AuthService.AddAccount` used to get an empty response → "unknown error").
* Reference for the old protocol: https://github.com/PringlePot/Lunar-Websocket
  (archived, different wire format, but confirms the "local websocket server
  that grants all cosmetics" approach).

### Renamer phase — wave 1 running (2026-09-14)

* **Fragmentation warning (read this):** the compiling tree is partial. The
  previous AI's green-trunk workaround quarantined 3,060 files
  (`tools/work/quarantine/`, ledger there), and **2,275 remaining tree files
  reference classes that now only exist in quarantine** — mostly
  `forge/`, `ichor/`, `genesis/`, `framework/`. Running `qa_loop.py` further
  will keep hollowing the tree out (1-2 files per recompile). The chosen
  strategy is: **rename the compiling tree now, rescue the quarantine later**
  as a separate workstream. Do not run qa_loop to convergence unless you
  intend to rescue.
* Two maps already applied and committed: `classes-07` (bridge, 40 renames)
  and `classes-15` (legacy mixins, 48 renames). Deferred collision rows are
  listed in the respective `.md` files.
* `tools/name_inventory.py` finds lazy class declarations (2,544 now; was
  2,850 before wave 1); `tools/make_clusters.py` slices them for subagents.
* `tools/apply_class_renames.py` applies a map tree-wide (word-boundary with
  `$` treated as an identifier char, exact-stem file renames, mixin configs,
  dry-run by default, refuses duplicate new names / cross-package collisions).
* Wave 1b subagents: bridge#part2/#part3, legacy.wrapper, replaymod.mixin,
  client.util#part1, highlight. Maps land in `tools/renames/classes-<id>.tsv`.
  Apply -> compile-check -> commit per cluster.

### Screenshot-upload token — investigation notes (follow-up)

* The uploader (`...RHOROICRIHRHCOCOORIHHIRORICRCR.OHROCHICOIOICHOCRROORRCIIICIHO`)
  asks the **launcher IPC** for a token with initiator
  `GAME_SCREENSHOT_UPLOAD` and then POSTs
  `api.lunarclientprod.com/game/screenshot/upload` with it. The fake
  launcher's empty response => HTTP 401.
* The launcher-side handler is not in `app.asar` or the Electron binary, so
  it is likely the official Authenticator minting a scoped JWT from the
  `X-Initiator` header. Replicating it for premium accounts means doing the
  Authenticator encryption handshake from the fake launcher (RSA/AES +
  Mojang joinServer with the stored access token). Guest/cracked accounts
  have no valid Mojang session, so the API token cannot be minted for them;
  a local-save fallback in the uploader would be the alternative.

### Known issues / next steps on this front

* Verified working: `[LC IPC] Connection established` then
  `[LC Assets] Connection established as <account> (Online)` per switched
  account; the fake backend answers the cosmetic/subscription/social RPCs and
  grants every cosmetic/emote/spray/badge via the has-all login flags.
* Account lookups initially failed because `AccountOps` called
  `CCOICIOICCCHRIIIRIROORORRHCRO` (two O's) while the runtime method is
  `CCOICIOICCCHRIIIRIROOORORRHCRO` (three). If anything account-related looks
  dead, print the client's method list from `AccountOps.manager()` first.
* Premium login uses Lunar's own launcher client id
  `4358653d-21f6-4697-96bb-7963ff974196` (device code). The old public id
  `00000000402b5328` is gone and returns AADSTS700016.
* Premium accounts are **proxied to Lunar's official backend** by
  `FakeBackend` (frame-by-frame relay of both the Authenticator and the
  AssetServer websockets). So with a premium account selected, tab-list
  icons, other players' cosmetics and friends come from the real servers.
  Cracked accounts stay fully local. If the official relay fails, the backend
  falls back to local behaviour for that session.
  Watch `run/logs/fake-backend.log` for
  `premium -> official authenticator` / `official session -> Lunar backend`.
* `AccountsPatcher` also patches the game IPC client's `onOpen` to call
  `AccountOps.triggerAssetConnect()`, so the Authenticator/AssetServer
  connection starts as soon as the launcher IPC is up.
* The ALTS panel is main-menu-only (`getGameContext` +
  `AccountOps.isMainMenu()`); it auto-hides in the multiplayer list and
  in-game, and has a close button.
* `-Dlunar.altmanager.selftest=1` adds/lists/selects a cracked account from
  the warm-up thread for headless testing.
* "Falling through the world" in singleplayer was reported — likely unrelated
  to accounts (chunk/teleport timing); check with a fresh world and vanilla-ish
  settings before blaming the patch.
* The stock `addAccount` UI button is patched to open the panel; if the UI
  bundle hash changes, rerun `tools/patch_ui.py --force`.
* The GitHub snapshot repo is `RealCrystalNight/Lunar-Client-Reversal`
  (single-commit snapshot; the local repo keeps full history because the
  full-history push exceeded the link's stability).

---

## 0.6 RENAMER HANDOFF (2026-09-14) — READ THIS IF YOU ARE THE NEXT AI

This session did the functional work in §0.5 (alt manager working, gated
screens removed, official proxy) and then **started the renamer phase**. The
renamer phase is NOT finished; it is mid-flight and must be continued by the
next AI. Nothing in this file was deleted — this section is the state of play.

### What was finished (functional, verified)

* Singleplayer / Multiplayer / Discover ungated via the production=false
  patch jar; see §0.5 for the exact gate and mechanism.
* Built-in **alt manager** working from the React panel: cracked accounts
  (local), premium accounts (Microsoft device-code with Lunar's own client id
  `4358653d-21f6-4697-96bb-7963ff974196`), account list/select/remove.
* Fake launcher IPC + local Authenticator/AssetServer; cracked sessions get
  local assets + all cosmetics; **premium sessions are proxied to Lunar's
  official backend** so tab icons/other players' cosmetics still work.
* Known limitation: screenshot uploads need a token minted by Lunar's
  launcher over IPC (`GAME_SCREENSHOT_UPLOAD`); the fake launcher cannot mint
  it, so uploads return HTTP 401. A local-save fallback is the alternative.

### Renamer phase progress

Measured at handoff: the compiling tree has ~7.7k Java files. Lazy class
declarations went **2,850 → 2,112** (inventory count; see below). Every applied
batch passed the compile gate (no NEW failing files) and was committed.

Applied batches (maps live in `tools/renames/classes-*.tsv`, full ledger in
`tools/renames/APPLIED.md`):

| Batch | Highlights |
|---|---|
| bridge #1-3 | ResourcePackBridge, FrustumBridge, 22 `<Packet>PacketBridge`, BakedModelBridge, EntityRenderStateBridge |
| legacy mixins + wrappers | ChunkLightingMixin, RenderGlobalEntityOutlineMixin, RewindPacketBuilder family, LegacyWorldBorder |
| event system (misnamed `highlight`) | LunarEventBus, EventRenderEntityBase, EventRenderTooltip, ApolloPacketEvent |
| replaymod mixins | `<Target><Purpose>V1_8Mixin` flavour names |
| client.util | SourceEmitter/MethodSpec/FieldSpec (JavaPoet-like generator), Easing curves, NotNullSerialized |
| framework core | ModCategories, OptionContainer, HudComponent + decorators, HudSize, CommandParser, ClientCommand |
| Molang JIT (misnamed `fps`) | MolangClassDefiner, VariablesMap, MolangClassBuilder + 17 AST tokens |
| SkyBlock utilities (misnamed `fishing.click`) | IslandUtils, CalculatorParser, TextComponentFactory, SkyblockCalendar |
| BetterMap/dungeons (misnamed `fishing.holograms`) | DungeonRoute, RouteRenderer, RouteTracker, WaterBoardSolutions, MapRoomType |
| bridge.horsestats (misnamed) | Vec3iBridge, EnumFacingBridge, DamageSourceBridge, ChatComponentFactoryBridge |
| ichor | TransformClass/Field/Resource/Method, ProvideRemapper, PipelineHook, IchorOptions/Stage/Loader |

**Big recurring finding: package names from the old restructure lie.** Always
read the code first (`highlight` = event system, `fps` = Molang compiler,
`fishing.holograms` = BetterMap, `fishing.click` = SkyBlock utils,
`bridge.horsestats` = generic bridges). The next AI should keep doing this.

### Tooling (use these, do not reinvent)

| Tool | Purpose |
|---|---|
| `tools/name_inventory.py` | lists lazy class declarations per package -> `tools/renames/inventory.tsv` |
| `tools/make_clusters.py --size 45` | slices inventory into subagent clusters -> `tools/renames/clusters.tsv`, `cluster-NN.txt` |
| `tools/apply_class_renames_aware.py` | **preferred applier**: package/import-aware bare-name resolution, nested `Owner$Inner` rows, duplicate guards, declaration forcing, dry-run by default |
| `tools/apply_class_renames.py` | v1 applier, only for globally-unique simple names |
| `tools/error_diff.py` | compile + diff failing files against `tools/work/compile-baseline.txt`; "no NEW failing files" is the gate. `--save-baseline` resets it |
| `tools/member_inventory.py` | 256,937 placeholder member tokens across 5,458 files -> `tools/renames/members.tsv` (phase 2, not started) |
| `tools/renames/README.md` | subagent handbook (naming rules, evidence sources, map formats) |
| `tools/renames/APPLIED.md` | batch ledger — update it after each batch |

### Exact workflow to continue

```bash
cd ~/Lunar-Client-Reversal
python3 tools/name_inventory.py
python3 tools/make_clusters.py --size 45
# launch one rename-research subagent per cluster (general agent, background),
# map-only output; prompt template at the end of this section
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-NN.tsv        # dry-run
python3 tools/apply_class_renames_aware.py --map tools/renames/classes-NN.tsv --apply
python3 tools/error_diff.py                                                          # must not list NEW files
git add -A && git commit -m "renamer: apply <cluster> map (...)"
# update tools/renames/APPLIED.md in the commit, or a follow-up commit
```

Subagent prompt template (kept short on purpose):

> Rename researcher for the Lunar Client 1.8.9 deobf project at
> ~/Lunar-Client-Reversal. Read
> `tools/renames/README.md` first. YOUR CLUSTER: `tools/renames/cluster-NN.txt`
> (skip missing paths). For every lazy class produce an ACCURATE new name.
> Identify each class from its source (fields/methods/strings/annotations), the
> real names in `tools/mappings-snapshot/lunar-client-names.tsv`, the
> obf->named tables in `tools/mappings-snapshot/restructure/*.tsv`, runtime
> decompiles (`java -jar tools/bin/cfr-0.153.jar ...`), and the reference
> clients (`~/Projects/Badlion 2.0.0-v-beta`,
> `~/Downloads/ABDM/Compressed/mc-client-sources-main/sources/`).
> Rules: unique tree-wide names, no trailing digits, mixins
> `<Target><Purpose>Mixin`, **do not edit sources** — write only
> `tools/renames/classes-NN.tsv` (`package<TAB>old<TAB>new<TAB>evidence`) and
> `tools/renames/classes-NN.md`. Reply with a 10-line summary.

### Pitfalls learned the hard way (do not repeat)

1. **`git mv` stages renames.** `git checkout -- <path>` restores from the
   *index*, so it does NOT undo an applied batch; and a later `git add -A`
   commit can sweep half-applied renames into an unrelated commit. Commit each
   batch immediately and explicitly.
2. **`git reset --hard` reverts tracked tool edits.** Commit changes to
   `tools/apply_class_renames_aware.py` (or any tracked tool) *before* running
   a reset to fix the tree.
3. `\b` treats `$` as a boundary: `\bBridge3\b` would corrupt
   `Bridge3$Extension`. The aware applier uses `(?<![\w$.])` for bare names and
   an explicit FQN pattern; keep that behaviour.
4. Bare replacement must never match an FQN *segment* (the `.` lookbehind) —
   the first version corrupted FQNs exactly this way.
5. Nested classes appear both as `Owner$Inner` top-level names and as real
   nested declarations. Rows may be `Data2` with evidence `Owner$Data2` or
   `Owner.Data2`, or full `Owner$Inner`. The aware applier handles all three
   and orders nested rows before owner rows (an owner rename would otherwise
   destroy the nested pattern).
6. **Never use `--allow-collisions`** (260 simple names collide across
   packages; it rewrites unrelated classes). Use the aware applier.
7. `libs/lunar-renamed-classes.jar` on the qa classpath is stale with respect
   to renames and can produce phantom errors (`MixinCore_2`, `Horsestats20`
   etc.). Check whether an error references an old compiled name before
   blaming the rename.
8. Always regenerate the inventory/clusters after a wave — cluster numbers
   shift, and two sessions can otherwise write the same `classes-NN.tsv`.

### Fragmentation / quarantine (deferred workstream)

The tree is partial. `tools/work/quarantine/` holds 3,060 files moved out by
the previous AI's green-trunk workaround, and ~2,275 remaining tree files
reference classes that only exist in quarantine (mostly
`forge/`, `ichor/`, `genesis/`, `framework/`). The chosen strategy was
**rename the compiling tree now, rescue the quarantine later** — do not run
`tools/qa_loop.py` to convergence, it hollows the tree out (1-2 files per
recompile). Rescue plan sketch: restore the quarantined files in dependency
batches, fix the missing-symbol cascades with the rename tables, then delete
only the genuinely shaded third-party files (`com/moonsworth/lunar/forge/lib`,
419 files, excluded by `qa_loop.py`).

### Phase 2 — members (not started)

`tools/member_inventory.py`: **256,937 placeholder member tokens** across
5,458 files (`methodN` 75k, `fieldN` 66k, `numberN` 41k, `valueN` 29k,
`textN` 20k, `flagN` 9.5k, `objN` 7.4k, ...). There is no member applier yet.
Recommended order: (a) automate locals/params with type inference, (b) use
subagents for semantic method/field names per feature cluster, (c) write
`tools/apply_member_renames.py` with per-class scoping and verify with
`error_diff.py`. Member-map rules are already in `tools/renames/README.md`.

### Remaining class clusters (suggested order)

* `genesis` (7 parts, ~270 names), `client.util#part2`,
  `client.framework.feature.*` (keystrokes, rewindhandlers, mod.*, holograms),
  `markers`, `mod.hud`, `guiRewindhandlers`, `lighting`, `fov`,
  `legacy.mixin` leftovers, `replaymod` v1_12 twins.
* Package renames once classes are done: `highlight` -> `client.event`,
  `fps` -> `client.molang`, `fishing.holograms` -> `skub.bettermap`,
  `bridge.horsestats` -> `bridge.<target>`. Update mixin configs only if they
  reference tree names (the shipped configs reference runtime obf names, so
  tree renames do not break them).
* Delete `com/moonsworth/lunar/forge/lib` (shaded third-party) rather than
  renaming; it is already excluded from the compile loop.

### Handoff state at a glance (commit `5c29972c`, 2026-09-14)

* **All rename subagents were cancelled at handoff — nothing is in flight.**
  The three wave-3 maps that existed on disk (`classes-12` BetterMap,
  `classes-16` bridge.horsestats, `classes-15` ichor) had already been applied
  under their semantic copies (`classes-bettermap.tsv`,
  `classes-bridgehorsestats.tsv`, `classes-ichor.tsv`). Genesis and
  keystrokes agents were cancelled before writing anything. So every
  `classes-*.tsv` on disk is either applied or needs a fresh dry-run — check
  `tools/renames/APPLIED.md` first.
* Lazy class inventory: **2,112** declarations. Member tokens: **256,937**.
* `tools/work/compile-baseline.txt` is gitignored; if `error_diff.py` reports
  a missing baseline, run `python3 tools/error_diff.py --save-baseline` once.
* The tree is clean and committed; `git status` should be empty before
  starting a new wave.

### Functional build/run commands (the shipped product)

```bash
export JAVA_HOME=~/.sdkman/candidates/java/17.0.20.fx-zulu
bash tools/build_localpatches.sh      # production=false patch jar + account service patch
bash tools/build_fakelauncher.sh      # FakeLauncher + FakeBackend (auth/asset proxy)
python3 tools/patch_ui.py --force     # local UI copy + ALTS panel + stock button redirect
bash tools/run-lunar.sh               # launch the client
```

Alt manager smoke test: main menu → **ALTS**; add cracked; add premium
(device code at microsoft.com/link); select each. Expected log lines:
`[LC IPC] Connection established`, `[LC Assets] Connection established as …`
in the game log; `cracked -> local session` or
`premium -> official authenticator` + `auth(official): authSuccess` +
`official session -> Lunar backend` in `run/logs/fake-backend.log`.

Workspace builds: `mvn -Dmaven.test.skip=true package` and `gradle build`.
If `libs/lunar-libraries.jar` is a 134-byte LFS pointer, run
`git lfs checkout` or `bash tools/setup_libs.sh` first (same for
`libs/multiver-full/lunar.jar`).

GitHub mirror: `RealCrystalNight/Lunar-Client-Reversal` (single-commit
snapshots; use the `github_realcrystalnight` SSH key). Local full history is
pushed only via snapshots because the full pack is ~900 MB and unstable on
the link — the snapshot recipe used this session is:

```bash
tar -cf - --exclude=.git --exclude=run --exclude=target --exclude=.gradle \
  --exclude=.ichor --exclude=default --exclude=config --exclude=test_run \
  --exclude=test_natives --exclude=tools/work \
  --exclude=libs/lunar-libraries.jar --exclude=libs/multiver-full/lunar.jar \
  . | tar -xf - -C /tmp/opencode/lcr-push
cd /tmp/opencode/lcr-push && git init -q -b master && git add -A && \
  git commit -q -m "snapshot" && \
  GIT_SSH_COMMAND="ssh -i ~/.ssh/github_realcrystalnight -o IdentitiesOnly=yes" \
  git push -f git@github_rcn:RealCrystalNight/Lunar-Client-Reversal.git master:master
```

### §8 definition-of-done mapping (concrete status)

| DoD item | Status / action |
|---|---|
| Launch to main menu | DONE (`bash tools/run-lunar.sh`) |
| Singleplayer/Multiplayer/Discover ungated | DONE (production=false patch jar) |
| Built-in alt manager (cracked + premium) | DONE; screenshot upload is a known 401 limitation |
| Premium sees official Lunar data | DONE via FakeBackend proxy (test with a premium account) |
| Maven + Gradle green | Verify with the commands above; `git lfs checkout` if jars are pointers |
| All sources compile or documented | **NOT done** — 3,060 quarantined files, 2,275 dependents; rescue workstream §0.6 |
| Renamer phase complete | **IN PROGRESS** — 2,112 lazy classes + 256,937 member tokens; workflow §0.6 |
| Assets intact under `src/main/resources` | Yes; do not move/delete them during renames (the applier updates mixin configs, not assets) |

### If the environment looks strange

* `/tmp/opencode` is ephemeral (wiped on restarts): re-extract `lunar.jar`
  and re-decompile with `tools/bin/cfr-0.153.jar` when a subagent needs it.
* Running the game writes into `run/` and `.ichor/` (both gitignored); crash
  reports live in `run/game/crash-reports/`.
* Do not run `tools/qa_loop.py` to convergence on the current tree — it
  quarantines the remaining tree one file per recompile (fragmentation note
  above).

---

## 0.7 JAR-FREE CLASSPATH + TRUSTWORTHY QA (2026-09-16)

### Jar dependency: severed

* `pom.xml` no longer lists the Lunar reference jars; the Maven classpath
  regenerated from the pom contains **zero** `lunar-libraries.jar` /
  `lunar-renamed-classes.jar` entries, and `tools/qa_loop.py` compiles
  jar-free by default (`QA_LUNAR_JAR=1` restores A/B mode).
* Every `com/moonsworth/lunar` class has a source twin (7,349/7,349); the
  1,228 quarantined classes whose path was missing were restored into src.
* The third-party libraries the reference jar bundled (Mixin, kyori
  adventure, protobuf, kotlin, jctools, caffeine, ...) now come from
  `libs/lunar-bundled-libs.jar` (14,422 entries, no Lunar classes),
  extracted by `tools/extract_bundled_deps.py`. Replace packages with Maven
  coordinates over time; the classpath order keeps Maven versions first.
* Lunar's protocol API (`com/lunarclient/**`, 5,346 classes of generated
  protobuf + Apollo interfaces) is a compiled library in
  `libs/lunarclient-protocol.jar`, matching STATUS.md ("bundled compiled
  rather than decompiled"). The decompiled variant produced ~1,800 failing
  files with protobuf decompiler artifacts; regenerate canonically from the
  embedded descriptors with protoc if they are ever wanted in src.

### Trustworthy QA (this is the important part)

* javac 21/22 aborts on this tree with internal errors (Attr NPE,
  Annotate/TransTypes asserts) while entering/attributing broken code. The
  abort truncates the error list, and the reported failing set moves between
  runs; `-XDshould-stop.ifError=GENERATE` aborts after ~6 errors, and the
  default INIT policy stops after the first error phase (a single parse error
  once made the tree look like "1 failing file" when it was thousands).
* **Use ECJ for measurements while the tree is broken**:
  `tools/ecj_check.py` compiles everything in one pass (~19s) and writes
  `/tmp/opencode/ecj-errors.txt`. `tools/error_diff.py` + javac remain the
  final gate for when the crash triggers are gone.
* Crash-trigger tooling: `tools/find_crash_triggers.py` +
  `tools/qa-crash-excludes.txt` (empty now; the earlier 80 exclusions were
  mis-attributed and caused cascades, e.g. excluding `Uuid.java` broke 317
  references). `tools/dedupe_nested_classes.py` quarantines flat
  `Outer$Inner.java` files that duplicate inline nested declarations.

### Honest state of the tree

* ECJ reports **4,391 failing files** (was 4,733 before the token fixes) out
  of 12,582 compiled; javac still aborts, so its numbers are not meaningful
  yet. `src/main/java` is 13,684 files; `tools/rescue-quarantine/` holds the
  staged duplicates.
* Member-aligner bugs fixed: `fix_dangling_members.py` ignored every token
  preceded by `.` (i.e. every `expr.TOKEN()` call) — fixing that plus the
  map-based `fix_obf_tokens.py` (72,875-entry member table) rewrote 14,452
  dangling member tokens.
* Biggest remaining families: `methodN ... is not visible` (~3.5k, protected
  members called from outside their hierarchy — the call sites come from a
  different decompile generation than the declarations), `Unhandled exception
  IOException` (~1.2k), abstract-method mismatches (~313), annotation
  attribute mismatches (~400), and a long tail of undefined symbols where
  receiver-type inference failed.
* These are semantic mismatches, not tooling artifacts: the repair loop is
  now scriptable and measurable. Zyvori dev-kit decompilers (cfr/procyon/
  fernflower/krak2) are available for re-decompiling stubborn classes.

---


## 1. WHAT WORKS RIGHT NOW (verified this session)

* **The client launches from this directory to the full Lunar main menu.**
  `bash tools/run-lunar.sh` → loading screens → `LUNARCLIENT_STATUS_STARTED`
  → Singleplayer / Multiplayer / Discover / Store buttons, account chip,
  settings icons, background panorama. Screenshots exist in the session.
* Sentry is fully neutralized (no telemetry leaves the machine).
* Tooling is in place for the alt manager and for the source-tree cleanup.
* Maven + Gradle dual build exist; `mvn` package is expected green (verify).

### What the client actually runs

**Stock Lunar runtime** = official Minecraft 1.8.9 jar (`1.8.9.jar`) + the
installed Lunar module jars + Ichor mapping + the official 1.8.9 libraries.
The workspace MCP source tree is **not** on the runtime classpath.

Why: our MCP-compiled game classes are already MCP-named, and Ichor's runtime
remapper is built to take the *obfuscated* official classes to MCP. Feeding it
our classes corrupts some transforms (e.g. a malformed `nb/minecraft/...`
class) and the boot dies with `FatalIchorError: Failed to transform
net.minecraft.server.MinecraftServer`. The experimental
`tools/run-mcp-classes.sh` + `tools/PatchExclude.java` investigate that path;
they are **not** the default and should stay parked unless you want to reopen
that can of worms.

---

## 2. HOW LAUNCH WORKS (the contract you must not break)

`tools/run-lunar.sh` is the working harness. Key pieces:

| Piece | Value / reason |
|---|---|
| Java to run | `~/.sdkman/candidates/java/17.0.20.fx-zulu` (official launcher uses 17) |
| Java to build | `~/.sdkman/candidates/java/21.0.12-amzn` |
| Game jar | `<Lunar install>/.minecraft/versions/1.8.9/1.8.9.jar` first on CP |
| Modules | `libs/multiver/genesis-patched.jar`, `forge-patched.jar`, then the original multiver jars |
| Libs | official `.minecraft/libraries/**` jars (authlib, log4j 2.22.1, lwjgl, netty, ...) |
| `libs/sentry-off.jar` | **first** on the classpath; neuters `io.sentry` capture/transport |
| `--uiDir` | **the UI bundle hash dir** (contains `index.html`), not `ui/` — see below |
| `--webosrDir` | the natives dir that contains `resources/icudt67l.dat` |
| `--texturesDir` / `--jitDir` | `run/lunarclient/textures`, `run/lunarclient/jit` |
| assets | `run/game/assets` → symlink to `test_run/assets` |
| natives | `run/game/natives` → symlink to the installed multiver `natives` dir |

### The three fixes that made the menu appear (do not regress them)

1. **`--uiDir` must be `<data>/ui/<sha1>/`** (the directory containing
   `index.html` and `static/index.js`), *not* `<data>/ui/`.
   Lunar's launcher does `uiDir: join(<data>/ui, sourceSha1)`. WebOSR's
   `OHROCHICOIOICHOCRROORRCIIICIHO.onRequest()` resolves every `file:///`
   request as `<root>/<path>`, so with the wrong root `index.html`,
   `/static/index.js` and `/assets/index.css` all 404 and the React UI renders
   an empty page (this was the "blank menu with no buttons" bug).
   `run-lunar.sh` now auto-detects the hash dir into `$UI_BUNDLE`.
2. **`--webosrDir` must contain `resources/icudt67l.dat`** (the installed
   `offline/multiver/natives` dir has it). Do not create a `web/` subdir and
   do not point it at one.
3. **Lunar's read-only data must be linked into the run data dir**:
   `run/lunarclient/{ui,shared,textures}` symlink to the installed
   `.lunarclient/{ui,shared,textures}`. Without `textures`, cosmetics/badges/
   emotes/jit_index fail to load; without `ui`, the launcher UI is blank.

### Debugging you can use

* `LUNAR_DEBUG=1 bash tools/run-lunar.sh` adds `lunar.webosr.debug` + LWJGL
  debug. The WebOSR debug lines show `Routing to /home`, `Resized Browser`,
  component dumps.
* `libs/webosr-debug.jar` (built from `/tmp/opencode/WebOsrPatch2.java`,
  gated behind `LUNAR_DEBUG=1` if present) logs every browser callback but it
  is **invasive** and has crashed the native engine; keep it off unless needed.
* Console + `run/logs/ichor-boot.log` + `run/game/crash-reports/`.
* `tools/classify_errors.py`, `tools/qa_loop.py` for the source tree.

---

## 3. THE ACTIVE TASK: ACCOUNT / ALT MANAGER

### What is broken right now (the user's actual complaints)

* **Singleplayer, Multiplayer and Discover are gated.** Clicking them does
  not open the expected screens/actions; Lunar's account/launcher requirement
  intercepts and shows "You must have the launcher open to sign in to an
  account" (the `AccountError.LAUNCHER_NOT_OPEN` path in
  `com/moonsworth/lunar/client/util/ICRHORIIHOHROHOHOCOOHOOCOORRHO`).
* **The top-left account chip says "Connecting..."** forever because the game
  is not connected to Lunar's launcher IPC (no `--ipcPort` peer), and there is
  no local account flow to replace it.
* Result: the menu is fully rendered, but the user cannot start singleplayer,
  join multiplayer or browse Discover without first satisfying a launcher
  account check they cannot complete. **Un-gating those three is the core of
  this task.**

### What the user wants

* Singleplayer and Multiplayer must stop whining about accounts and work.
* Discover should open and work too (it is gated the same way).
* The top-left account chip ("Add account", "Connecting...") should become /
  act like an **alt manager**: add cracked (offline) accounts and premium
  accounts, pick one, play.
* Premium auth feature set to merge (references cloned to `/tmp/opencode/`):
  * `ColdPlay` (Apache-2.0) — `src/coldplay/account/*`, `gui/account/*`:
    cracked + premium + refresh-token flows, proxy config, direct connect UI.
  * `awesome-alt-management` / AuthMe (MIT) — `common/.../authme/api/...`:
    offline, device-code, add-token screens, `KNOWN_CLIENTS` refresh-token
    auto-detection, `SessionUtils`.
  * `ksyzov/AccountManager` (LGPL) — reference only: `SessionManager`
    reflection, account list/persistence, Microsoft refresh flow.
* The user said **"I want it built in"** — an external Forge mod that must sit
  in a mods folder is not the preferred answer; anything that works as part of
  the normal launch is.

### What already exists (written this session)

`src/main/java/com/moonsworth/lunar/altmanager/`:

| File | Purpose |
|---|---|
| `AltAccount.java` | username / uuid / accessToken / refreshToken / cracked / lastUsed |
| `AltStore.java` | JSON list in `<gameDir>/lunar/altmanager.json`, name validation, random names, offline UUIDs |
| `MicrosoftAuth.java` | three flows — refresh token (with **launcher-client auto-detection** ported from AuthMe: Mojang/Prism/Lunar/LabyMod/… and `d=`/`t=` ticket prefixes), device code, access token; optional proxy |
| `SessionSwitcher.java` | reflection swap of `Minecraft.session` (field-name plus type-scan fallback, same trick as AccountManager) |
| `GuiAltManager.java` | account list, add cracked, random, refresh-login, access-token login, device login, join server, login/delete/copy/done, direct join via `GuiConnecting` |
| `GuiDeviceLogin.java` | device-code screen: shows code + link, copy/open, polls, logs in |
| `AltManagerMod.java` | `@Mod` + client tick: RSHIFT opens the manager |

Build: `bash tools/build_altmod.sh` → `libs/lunar-altmanager.jar` (mcmod.info
in `tools/altmanager-resources/`).

### Why it does not run yet, and the options

FML in this Ichor runtime reported only 4 mods (mcp, FML, Forge, replaymod);
it did **not** discover `libs/lunar-altmanager.jar` from `run/game/mods`, and
RSHIFT did nothing. Pick the path that works:

1. **Figure out FML discovery in this runtime.** Check why the mods dir is
   ignored (Ichor may install a custom `ModDiscoverer`/mod-dir, or FML scans
   before our jar is copied). Look at `RuntimeForgeIchorModule` (not in the
   decompiled tree — grep the original jars) and the FML log lines around
   "Mods directory". If fixed, the whole mod loads as-is.
2. **Inject the tick hook via ASM** into a class that is certainly loaded
   (e.g. `net.minecraft.client.Minecraft` constructor or `Client` init) in a
   patched copy of the vanilla jar; it only needs to register our tick
   handler that opens `GuiAltManager` on RSHIFT. This is the "built in" route.
3. **Patch the React UI.** The main menu is a React bundle at
   `<data>/ui/<sha1>/static/index.js`. The account chip and buttons call Java
   through `window.lunarInternalInvoke("<method>", ...)`. You can patch the JS
   so the account button invokes a bridge method that opens our GUI (or so the
   account actions work offline). Remember to keep a pristine copy.
4. **Ichor module** — package the alt manager as an `Ichor5` module jar added
   to `--ichorClassPath`; then Ichor gives you a proper lifecycle hook and you
   can register events/keybinds. More work but the most "native" option.

### Failed attempts (do NOT repeat these — they wasted a lot of time)

These are the things tried this session that **did not work**; the next AI
should start from the options above instead.

1. **External Forge mod in `run/game/mods/` (the "mod idea").** The alt
   manager was compiled and packaged exactly like a normal 1.8.9 Forge mod
   (`@Mod`, `mcmod.info`, `tools/build_altmod.sh` → `libs/lunar-altmanager.jar`,
   copied to `run/game/mods/lunar-altmanager.jar`). FML still reported
   `Forge Mod Loader has successfully loaded 4 mods` and never mentioned the
   mod; RSHIFT did nothing and no error was logged. The Ichor Forge module
   appears to control/ignore mod discovery (the launcher log says "Mods
   directory does not exist, skipping validation"), so the standard mods-dir
   route is dead unless you first work out Ichor's `ModDiscoverer` behaviour.
   Do not spend more time re-copying the jar or re-checking `mcmod.info`.
2. **Classpath mod instead of mods dir.** Not tried, but FML discovery is the
   unknown; don't assume it will work before checking the log line above.
3. **Invasive WebOSR debug shadow jar** (`/tmp/opencode/WebOsrPatch*.java`,
   `libs/webosr-debug.jar`). The first version crashed with a `VerifyError`
   from a wrong local-variable slot in the injected logging; the fixed version
   produced **zero events** and made the native engine crash with
   `SIGSEGV in webosr::itc::pollEvents`. The browser-listener bridge is not a
   safe place to instrument. Keep `LUNAR_DEBUG=1` (existing logs only) and the
   fake launcher log instead.
4. **`PatchExclude` (excluding `net.minecraft` from Ichor's remapper).** It
   does make the game boot (no more corrupt class transform) but it also
   bypasses the Mixin transformer, so the game is vanilla Minecraft with no
   Lunar features. It is not an acceptable runtime.
5. **Feeding the MCP-compiled game classes to Ichor**
   (`tools/run-mcp-classes.sh`). Dies with
   `FatalIchorError: Failed to transform net.minecraft.server.MinecraftServer`
   after Ichor synthesizes a corrupt `nb/minecraft/...` class. Parked.
6. **Adding `--launchId` to connect the fake launcher** was written but
   reverted at the user's request before it was ever tested. It is still the
   most promising first thing to try (see the launcher IPC section below).

### The launcher IPC (needed so the UI stops saying "Connecting...")

The game talks to Lunar's launcher over a websocket:
`ws://127.0.0.1:<ipcPort>`, client sends `ServerboundWebSocketMessage`
(`request_id`, `service`, `method`, `input` protobuf bytes), server answers
with `GameboundIPCMessage` containing `IPCRpcResponse` (`request_id`,
`output`). All classes are in the installed `lunar.jar`.

A **fake launcher** is already written and wired into `run-lunar.sh`:

* `tools/fake-launcher/FakeLauncher.java` (java-websocket server from the
  Lunar jars) — logs every RPC to `run/logs/fake-launcher.log` and answers
  AuthService `addAccount`/`refreshAccount` with success, Microsoft popup with
  "closed, no URL", `notifySwitchAccount` default, everything else with an
  empty (protobuf default) response.
* Build: `bash tools/build_fakelauncher.sh` → `libs/fake-launcher.jar`.
* `run-lunar.sh` starts it on `$LUNAR_IPC_PORT` (default 28190) and passes
  `--ipcPort`.

**Current blocker:** the game does not even connect. `Client.method8()`
logs `[LC IPC] Unable to construct IPC handshake.` because
`Client.method9()` returns null: it needs `ThreadModuleDump80.field4`
(`--launchId`) to be supplied, and if it is `"not supplied"` it checks
`Nameplate.field4`, which is true on this setup, and bails.
`MainMixin` accepts `--launchId`, `--launcherVersion`, `--installationId`,
`--sentryTraceId`, `--launcherFeatureFlags`, `--canaryToken`,
`--ipcPort`, … Pass a synthetic `--launchId <uuid>` (and `--launcherVersion`)
from `run-lunar.sh`; then the client should connect and the fake launcher log
will fill with the real RPCs. If that still fails, patch `Client.method9`'s
runtime class or make `Nameplate.field4` false.

Once connected, **watch `run/logs/fake-launcher.log`** while clicking the UI
buttons; that log tells you exactly what to respond to next. The AuthService
protobuf classes live in `com.lunarclient.gameipc.auth.v1.*`; pushes the game
understands are registered in `WebSocketClientIterator.onOpen`
(`PlayServerPush`, `ExitGamePush`, `FocusGamePush`,
`PendingPromotionUpdatePush`, `StartStorePreviewSessionPush`,
`TebexJsCheckoutClosed`). Lots more push types exist under
`com/lunarclient/websocket/**/v1/*Push` if needed.

### Where "You must have the launcher open to sign in" comes from

`com/moonsworth/lunar/client/util/ICRHORIIHOHROHOHOCOOHOOCOORRHO` (only in the
installed `lunar.jar`, not in our source tree) is the account bridge: it
exposes `addAccount`/`refreshAccount` over the launcher IPC with
`AccountError` values such as `LAUNCHER_NOT_OPEN`, `MISSING_REFRESH_TOKEN`,
`NOT_PURCHASED_MINECRAFT`. The toast the user saw is the
`LAUNCHER_NOT_OPEN` path. Two ways out: satisfy it with the fake launcher, or
patch that class to handle offline accounts locally (it is a good ASM-patch
candidate if the IPC route stalls).

---

## 4. THE RENAMER PHASE — STILL ENTIRELY LEFT (the original main ask)

This is the point of the project and it has **not been done yet**. Launching +
the account layer were the user's stated prerequisites ("get the game
LAUNCHING … before renaming even begins"), but they are not a substitute.
Plan for the largest remaining workstream:

**Current numbers (measured this session):**

| Item | Count |
|---|---|
| Java files in `src/main/java` (compiling tree) | ~7,000 |
| Files held out in `tools/work/quarantine/` (gitignored, ledger TSV) | ~2,900 |
| Files still containing `methodN`/`fieldN`/`objN`/`numberN`/`textN` tokens | ~8,100 |
| Class basenames still matching lazy patterns (`MixinN`, `DataN`, `FrameworkN`, …) | ~168 |
| Classes named `*Mixin<N>` | ~161 |
| Obfuscated member tokens already fixed by `fix_obf_tokens.py` | ~2,250 |

**Method:**

1. Do not resurrect `src/reference`. Rescue quarantined files in verified
   batches: move back into `src/main/java`, compile, commit. Delete shaded
   third-party files (Jackson/Guava internals under `com.moonsworth.lunar` —
   their classes already live in `libs/lunar-renamed-classes.jar`) with the
   reason recorded in the commit/ledger.
2. `tools/qa_loop.py` compiles and quarantines failures; it converges one file
   at a time through error cascades, so run it in the background or fix the
   chain roots first with `tools/fix_obf_tokens.py`.
3. `tools/fix_obf_tokens.py` resolves leftover obfuscated member tokens from
   the rename tables (~2,250 done; more remain). `tools/mappings-snapshot/`
   has every table. `tools/mappings-snapshot/lunar-client-names.tsv` holds the
   **real Lunar/Apollo names** recovered from the shipped `lunar.jar`
   (`com/lunarclient/apollo/mods/impl/ModOverlayMod`, option constants, …) —
   use those as ground truth for mod/framework naming.
4. Classify classes from bytecode, not names: `@Mixin` targets, mod ids in
   constructors, string literals, superclasses. The old AI's `/tmp/opencode/`
   cache (`records.json`, `obf_recs.json`, `naming.json`, rename tables) helps
   but must be verified against bytecode.
5. Mixins: name `XxxMixinN` after the target + purpose (if two mixins target
   the same class, disambiguate by feature). Modules: name after the mod id /
   feature. Members: build a usage index, then rename semantically — use
   subagents per package cluster (the user explicitly asked for subagent use)
   and keep a TSV ledger for each batch.
6. Naming references: `~/Projects/Badlion 2.0.0-v-beta`
   (full sources) and the 509 clients in
   `~/Downloads/ABDM/Compressed/mc-client-sources-main/sources/`; read
   their `.md`/pipeline docs for conventions.
7. Recompile after every rename batch; commit each green state with a
   descriptive message. The user checks the ledger and the names.

**Bar for done:** zero `MixinN`/`methodN`/`fieldN`/`objN`/`numberN`, zero
empty/generic names, meaningful packages and feature grouping — "better named
than Badlion".

---

## 5. PATHS, TOOLS, CACHE

```
MavenMCP-1.8.9/
├── src/main/java/{net/minecraft,com/moonsworth}   the workspace source tree
├── src/main/java/com/moonsworth/lunar/altmanager/ alt manager sources (new)
├── src/main/resources/                            assets, mixin configs, services
├── libs/
│   ├── multiver-full/  (official module jars + symlink to the installed natives)
│   ├── multiver/       (patched genesis/forge/legacy/optifine)
│   ├── lunar-libraries.jar, lunar-renamed-classes.jar
│   ├── sentry-off.jar, fake-launcher.jar, lunar-altmanager.jar, webosr-debug.jar
│   └── forge-1.8.9-compile.jar, ReplayMod, OptiFine, natives
├── tools/
│   ├── run-lunar.sh            WORKING stock launcher
│   ├── run-mcp-classes.sh      experimental MCP-source launcher (parked)
│   ├── fake-launcher/, build_fakelauncher.sh
│   ├── altmanager-resources/, build_altmod.sh
│   ├── SentryOff.java, PatchExclude.java
│   ├── qa_loop.py, fix_obf_tokens.py, classify_errors.py, mappings-snapshot/
│   └── work/quarantine/        held-out sources + ledger.tsv
├── run/                        gitignored runtime dir (game, data, logs, mods)
└── Prompt.md                   this file
```

Installed Lunar data (read-only reference, makes the game run):
`~/.var/app/com.lunarclient.LunarClient/.lunarclient/` and
`.minecraft/`. The official launcher log that documents the exact launch
arguments is
`.lunarclient/logs/launcher/main.log` — **read it** when in doubt.

Previous-AI cache: `/tmp/opencode/` (records.json, rename tables output,
stage jars, decompiler output, plus this session's debug tools:
`WebOsrPatch2.java`, `FindImpl.java`, `SentryOff.java` derivatives, etc.).

---

## 6. COMMANDS

```bash
# Launch (this works)
export JAVA_HOME=~/.sdkman/candidates/java/17.0.20.fx-zulu
bash tools/run-lunar.sh

# Debug launch
LUNAR_DEBUG=1 bash tools/run-lunar.sh

# Build the alt-manager mod jar / fake launcher
bash tools/build_altmod.sh
bash tools/build_fakelauncher.sh

# Build the workspace
export JAVA_HOME=~/.sdkman/candidates/java/21.0.12-amzn
export PATH=$JAVA_HOME/bin:~/.sdkman/candidates/maven/current/bin:$PATH
mvn -Dmaven.test.skip=true clean package
gradle build

# Source-tree compile loop / token fixing
python3 tools/qa_loop.py --dry-run
python3 tools/fix_obf_tokens.py --check --tree src/main/java/com/moonsworth
```

---

## 7. PITFALLS / THINGS THAT WILL BITE YOU

1. **Do not feed the MCP-compiled game classes to Ichor** (see §1). Use the
   stock launcher. The `PatchExclude` path excludes `net.minecraft` from
   Ichor's remap and works, but it bypasses the mixin transformer (the game
   runs vanilla-ish) — not what the user wants.
2. **Do not change `--uiDir` back to the parent**; the UI goes blank.
3. **Keep `libs/sentry-off.jar` first on the classpath.** Sentry must not
   send anything.
4. **FML does not see external mods here** (only 4 mods). Verify any mod route
   in the log ("Forge Mod Loader has successfully loaded N mods").
5. **WebOSR debug shadow jars can crash the native engine.** Gate them.
6. **The client needs the installed `ui`, `shared`, `textures`, natives and
   libraries.** The launcher script symlinks them; a fresh clone does not
   have them.
7. **Ichor is load-bearing**: the obfuscated module jars are transformed at
   runtime; mixin configs and mappings must keep matching
   (`ipcPort`/`launchId` aside).
8. **Commit at every green state** — the user cares about a good history.
9. The user is (rightly) impatient with guessing. When stuck: read the actual
   code (`lunar.jar` classes, the launcher's `app.asar` JS, the UI bundle),
   add real debug/logging, and use the fake launcher log.

---

## 8. DEFINITION OF DONE (updated)

* [x] Client **launches from this directory** to the full Lunar main menu.
* [x] Sentry neutralized (no telemetry).
* [ ] Singleplayer and Multiplayer work without Lunar's launcher.
* [ ] Built-in alt manager (cracked + premium via refresh token / device code /
      access token), reachable from the game; top-left account flow usable.
* [ ] Fake launcher connected and every RPC the UI needs answered.
* [ ] `mvn -Dmaven.test.skip=true clean package` and `gradle build` green.
* [ ] All non-compiling sources either rescued into `src/main/java` or deleted
      with a documented reason; `src/reference` stays gone.
* [ ] **THE RENAMER PHASE — entirely left, and it is the original point of the
      project.** No `MixinN`/`methodN`/`fieldN`/`objN`/`numberN` anywhere; real
      feature/class names; subagents used per cluster; Badlion + 509 clients
      as references; a rename ledger per batch; commits at every green state.
      See §4.
* [ ] Assets under `src/main/resources` intact.

Good luck — you are very close. The hard part (launching real Lunar from this
workspace) is done; the account layer is the last functional blocker before
renaming.
