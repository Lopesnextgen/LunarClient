# Launch debugging (2026-09-16)

## Fixed: stale mixin configs inside the module jars

The authored configs in `src/main/resources/mixins.*.json` were fixed on
2026-09-13 (commit 6e31975fd: `required:false`, pruned mixin lists,
`injectors.defaultRequire:0`) but a later packaging step put the OLD configs
back into the jars. The launch crashed in
`mixins.legacy_optifine_combined.json` with "No refMap loaded / target init
not found".

Fix: sync `src/main/resources/mixins.*.json` into the jars that are actually
loaded (`multiver-full/legacy-*-nomappings.jar`, `multiver/legacy-patched.jar`,
`multiver/forge-patched.jar`; 28 configs). The mixin crash is gone.

## Next blocker (unmasked): Ichor module lookup

After the fix the launch advances past PREINIT into `net.minecraft.client.main.Main.main`
and dies with:

    ClassNotFoundException: IchorPipeline can't find class in Genesis:
    com.moonsworth.lunar.HRICOROOOCCOCOROCRHHCRRIRCOICO.HRORICORIHHHRICRIRCIIOHCRIRRHI.HRICOROOOCCOCOROCRHHCRRIRCOICO

That class EXISTS in `libs/multiver/legacy-patched.jar` and
`libs/multiver-full/legacy-0.1.0-SNAPSHOT-all-nomappings.jar`, so this is an
Ichor classloader/module-mapping mismatch, not a missing jar. Likely causes:
* the packaged module jars are a different Lunar build generation than
  `lunar-platform-mappings-v1_8.jar` (Ichor resolves the mapped name through
  the mappings and fails);
* `libs/lunar-libraries.jar` is a 134-byte LFS pointer (never real content here)
  and is no longer on the classpath (the old pre-migration harness line had it).

Candidate next steps:
1. Rebuild/refresh the runtime jars from the local Lunar install
   (`tools/vendor_from_install.py`) so modules and mappings match.
2. Or restore the `libs/multiver*` jars + `run-lunar.sh` from the commit where
   the launch was last verified (2026-09-14, `b921c0948` era) and re-apply the
   config sync above.
3. Or add the missing module to Ichor's lookup: run with the legacy module
   first on the classpath and `--ichorClassPath` pointing at the same file.

## ROOT BLOCKER (final finding): missing lunar-libraries.jar

Every jar we have was searched: the mixin classes named by the installed
Lunar module configs (`com.moonsworth.lunar.legacy.optifine.mixin.HHRROII...`,
`com.moonsworth.lunar.client.IICOCH...`) are **not present in any local jar**.
They live in `libs/lunar-libraries.jar` (32,174 classes), whose only local copy
is a 134-byte LFS pointer; the file is not in the install tree either
(`~/.var/app/com.lunarclient.LunarClient/.lunarclient/offline/multiver/` holds
only the 14 module jars). The old pre-migration harness had it on the
classpath (`CP=...:$LIB/lunar-libraries.jar`, commit b66fd59ad:tools/run-lunar.sh).

Without that jar the launch cannot boot past PREINIT:
* packaged-jar runtime -> Ichor cannot resolve the remapped class;
* install-jar runtime  -> mixin classes missing entirely.

To restore the launch:
1. obtain `lunar-libraries.jar` (git LFS from the repo's original remote,
   Lunar's version CDN, or another machine that has run the client), place it
   at `libs/lunar-libraries.jar`;
2. add it to the classpath in `tools/run-lunar.sh` (as in b66fd59ad) and to
   `--ichorClassPath` if present there;
3. keep the mixin-config sync that is already committed for the packaged jars.

## Update: runtime refresh got furthest (Forge/OptiFine init)

Refreshing ALL modules from the installed client (consistent generation, own
configs, no authored overlay) advances the launch past the mixin phase into
Minecraft/Forge startup. Remaining crashes:

1. `net.optifine.Config.getDisplayModes` -> LWJGL2
   `LinuxDisplay.getAvailableDisplayModes` throws
   `ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0`
   (the X server reports zero display modes; happens on Wayland/XWayland AND
   on Xvfb). OptiFine's `Config.class` lives at the ROOT of
   `libs/multiver-full/OptiFine_v1_8.jar` (no package) and is remapped to
   `net.optifine.Config` at runtime.
2. Follow-on: `Forge Loader.mccversion` is null (likely a consequence of (1)
   failing during class init).

Next fixes, in order of cost:
* run an X11 session where XRandR reports real outputs (GNOME on Xorg) - zero
  code changes;
* or patch `Config.class#getDisplayModes` with ASM (template:
  `tools/localpatches/tools/AccountsPatcher.java`) to catch the LWJGL failure
  and return an empty mode list, shipped via `lunar-localpatches.jar`;
* or install/verify `libxrandr` so LWJGL's XRandR path works.
