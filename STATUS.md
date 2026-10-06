# STATUS — Lunar Client 1.8.9 deobfuscation

Saved state: **the Maven build is green and the release artifacts are packaged.**
The compiling source core plus the quarantined WIP sources and the full rename
pipeline are committed; docs, license and notice are in place.

## What works right now

```bash
export JAVA_HOME=~/.sdkman/candidates/java/21.0.12-amzn
export PATH=~/.sdkman/candidates/maven/current/bin:$PATH
mvn -Dmaven.test.skip=true clean package     # SUCCESS in ~37s
bash tools/verify.sh                          # build + artifact + debt report
```

Artifacts:

* `target/lunar-client-deobf-1.8.9.jar` (21 MB)
* `target/lunar-client-deobf-1.8.9-jar-with-dependencies.jar` (111 MB):
  2,500 `net/minecraft`, 2,172 `com/moonsworth`, 5,346 `com/lunarclient`,
  491 Lunar asset entries.

Runtime harness (documented in `README.md`): `tools/run-lunar.sh` launches the
client from `libs/` with local fake launcher/authenticator/asset-server. It runs
offline/local (all cosmetics, a default outfit, Discover populated from Lunar's
public server catalogue) or, after `tools/import_lunar_account.py`, as a premium
relay to Lunar's official backend so real Lunar users are visible.

## Current tree

| Path | Contents |
|---|---|
| `src/main/java/net/minecraft` | MCP 1.8.9 sources, 1,612 files, compiling (Guava 29 patched) |
| `src/main/java/com/moonsworth` | renamed + restructured Lunar sources, 2,002 files compiling |
| `src/reference/java` | 5,778 quarantined WIP sources — readable, deliberately not compiled |
| `src/main/resources` | 64 MB: MCP assets (3,090 files), Lunar textures (491), 34 language files |
| `libs/` | vendored runtime: Lunar modules, Forge, OptiFine, ReplayMod, natives, 407 MB (trimmed) |
| `tools/` | complete pipeline (quarantine loop, renamers/appliers, verify, harness) |
| `tools/renames/` | rename maps + `APPLIED.md` ledger and per-batch notes |

## Build model

`tools/fast_green.sh` compiles MCP with javac, then compiles the Lunar tree with
ECJ in one pass per iteration and moves files that cannot compile to
`src/reference/java` until the tree is green (reachable in 6 iterations on this
tree). `mvn package` then builds the code in `src/main/java`; quarantined classes
stay available to the runtime through `libs/`.

Rescue work continues by fixing a quarantined file (or a family of them) and
moving it back, one green state per commit — maps and notes for the remaining
batches live in `tools/renames/` (`wave7/` holds the stale-duplicate maps).

## Restructure / rename result

* 0 obfuscated class names left in **filenames**; 8 files still mention an
  obfuscated token and 507 `com/moonsworth` classes still carry digit-suffixed
  or placeholder names (`ThreadModuleDump*` is down to 40) — see
  `tools/renames/APPLIED.md` for what has been applied.
* Modules live under `client/mod/{combat,render,movement,player,hud,misc,skyblock}/`;
  the latest batch removed the mechanical `Child`/`ChildMod`/`Mod` suffixes
  (32 + 7 classes).
* The annotation subsystem was repaired (obfuscated element names rewritten to
  the declared elements, `lunar/annotations` declarations match their files).
* Mixins are named after their target; locals/parameters were cleaned earlier.

## Known limitations

* `src/reference/java` is readable but not compiled; a file moves back to
  `src/main/java` only when it compiles (see the rescue workflow above).
* Original Lunar identifiers were destroyed by the obfuscator; clean names are
  inferred. Some placeholder member names remain (`methodN`, `Data2`, …).
* `com/lunarclient/**` is bundled compiled (generated protobuf + relocated
  libraries) rather than decompiled.
* The runtime harness uses the vendored Lunar jars, not the deobf sources.

## Licensing

`LICENSE` covers the original tooling/documentation (GPL-3.0). Third-party code and
assets remain the property of their owners — see `NOTICE.md`. This repository is
a research/archival deobfuscation, not affiliated with Mojang or Moonsworth.
