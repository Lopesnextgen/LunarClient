# Notice and attribution

This repository is an independent **research and archival deobfuscation** of
Lunar Client 1.8.9, merged into an MCP 1.8.9 workspace. It exists to study how
the client is built and to keep the result readable and buildable. It is not
affiliated with, endorsed by, or supported by Mojang Studios, Microsoft or
Moonsworth, LLC.

## Third-party material

The following material is included for interoperability and research purposes
and remains the property of its owners. It is **not** covered by the
repository license in `LICENSE`:

| Material | Owner / license |
|---|---|
| Minecraft 1.8.9 sources and assets (`net/minecraft`, `assets/`) | (c) Mojang Studios / Microsoft |
| Lunar Client code, assets, textures, language files, mappings | (c) Moonsworth, LLC |
| OptiFine (`OptiFine_v1_8.jar`, optifine module) | (c) sp614x |
| Minecraft Forge (`Forge_v1_8.jar`, forge module) | (c) Forge Development LLC, LGPL-2.1 |
| ReplayMod (`ReplayMod-v1_8-2.6.24.jar`) | (c) ReplayMod contributors, GPL-3.0 |
| Guava, Gson, Netty, LWJGL, ASM, Mixin, Kotlin stdlib and others (`libs/`) | their respective licenses |

The bundled Lunar runtime (`libs/`) is kept because the client cannot start
without it; it is not redistributed as a product, and no support is offered
for it.

## Original work

The deobfuscation pipeline in `tools/`, the documentation in
`pipeline-work.md` / `STATUS.md` / `README.md` / `Prompt.md`, and the cleanup
and renaming of the source trees are original work, released under the GNU
General Public License v3.0 in `LICENSE`.

That license covers the original work only. It does not apply to the
third-party material listed above, which keeps its own terms and ownership.

## Removal requests

If you own rights to material included here and want it removed, open an issue
and it will be taken down.
