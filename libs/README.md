# libs/ — packaged runtime + build dependencies

**Everything the build, the QA tooling and `tools/run-lunar.sh` need is
committed in this repository.** A fresh clone works offline: no Lunar Client
installation, no LADDER/LFS fetch, no symlinks into external directories.

`tools/vendor_from_install.py` exists only to *optionally* refresh these
packaged copies from a local install (it copies, never links; dry-run by
default).

The tree was trimmed for size: byte-identical duplicate jars, three copies of
the UI bundle, an unused 192 MB shared asset store, an unused native zip and
the 1.8.9-unused modern libraries were removed. See the notes at the bottom for
how to restore any of them.

## Build classpath (Maven system deps, see pom.xml)

| File | Used for |
|---|---|
| `lunar-bundled-libs.jar` | third-party libraries the reference jar bundled (Mixin, kyori, protobuf, kotlin, jctools, caffeine, ...) |
| `lunarclient-protocol.jar` | Lunar's bundled protocol API (generated protobuf + Apollo interfaces), kept compiled per STATUS.md |
| `multiver-full/ReplayMod-v1_8-2.6.24.jar`, `forge-1.8.9-compile.jar`, `optifine-1.8.9-compile.jar` | compile deps |
| `asm-9.7.1-all.jar` | bytecode tooling |

## Launch runtime (`tools/run-lunar.sh`) — all packaged

| Path | Used for |
|---|---|
| `multiver-full/lunar.jar` | stock Lunar client jar (62 MB) |
| `multiver-full/*-SNAPSHOT-all*.jar`, `lunar-platform-mappings-v1_8.jar`, `lunar-lang.jar`, `Forge_v1_8.jar`, `OptiFine_v1_8.jar`, `ReplayMod-*.jar` | Lunar module jars + Ichor mappings |
| `lunar-assets/ui` | Lunar UI bundle (one version kept; patched into `run/lunarclient/ui-local` by `tools/patch_ui.py`) |
| `lunar-assets/textures` | Lunar cosmetics/spray/badge textures |
| `natives/*.zip`, `natives/resources/` | per-OS natives (jinput/lwjgl/openal, Ultralight/WebCore, WebOSR binding) + `icudt67l.dat`/`cacert.pem`; extracted to `run/game/natives` by `tools/extract_natives.py` |
| `vanilla/versions/1.8.9/`, `vanilla/libraries/`, `vanilla/assets/` | the vanilla Minecraft 1.8.9 runtime (Mojang), packaged, libraries trimmed to the set the 1.8.9 launch actually loads |
| `sentry-off.jar`, `fake-launcher.jar`, `lunar-localpatches.jar`, `lunar-altmanager.jar` | Sentry neutering, fake launcher, dev-mode patches, alt manager |

## Notes

* `LUNAR_MC=/path/to/.minecraft` makes the launcher use an installed vanilla
  runtime instead of `libs/vanilla` (for testing against other installs).
* Runtime links live only under the gitignored `run/` directory and always
  point back into this repository (assets/natives/UI), never outside it.
* `lunar-renamed-classes.jar` (the renamed reference jar the older fixer tools
  read) is not shipped; a copy lives next to the old git backup and the
  pipeline can regenerate it into `tools/work/staging/lunar-all-final.jar`.
* `lunar-assets/shared` (the shared 1.8/Mojang asset store) is not shipped
  either. The client does not read it in this harness; if you want it back for
  another profile, `python3 tools/vendor_from_install.py --apply` restores it
  from a local Lunar install.
* Legacy pipeline scripts under `tools/` that still mention
  `libs/lunar-libraries.jar` predate the jar-free work and are not part of any
  current workflow; that LFS-tracked pointer was never real content and was
  dropped.
