# Lunar Client 1.8.9 deobfuscated source

A deobfuscated source tree for Lunar Client 1.8.9, merged into an MCP 1.8.9
workspace. This was an original project and a lot of time and effort went into
it.

The goal is to let people survey the codebase: how the client is put together,
how the modules and mixins are organised, how it talks to the launcher and the
UI layer, and how it sits on top of Minecraft 1.8.9.

I am not responsible for anything anyone does with this source code. Use it at
your own risk.

It was a fun project.

## What is in the tree

| Path | Contents |
|---|---|
| `src/main/java/net/minecraft` | MCP 1.8.9 game sources, compiling |
| `src/main/java/com/moonsworth` | deobfuscated and restructured Lunar sources, compiling |
| `src/reference/java` | sources that do not compile yet. Readable, left out of the build |
| `src/main/resources` | game assets plus Lunar textures, icons and language files |
| `libs/` | Lunar modules, Forge, OptiFine, ReplayMod, natives and libraries |
| `tools/` | deobfuscation pipeline, rename maps, build and launch scripts |

## Building

```bash
export JAVA_HOME=~/.sdkman/candidates/java/21.0.12-amzn
export PATH=~/.sdkman/candidates/maven/current/bin:$PATH
mvn -Dmaven.test.skip=true clean package
gradle build
bash tools/verify.sh
```

`mvn package` and `gradle build` both produce the jars in `target/`. Sources in
`src/reference/java` are kept readable but are not compiled; they are moved back
into `src/main/java` once they build, one batch at a time.

## Running the client

`tools/run-lunar.sh` launches the client from the vendored runtime in `libs/`.
It starts local stand-ins for Lunar's launcher and backend, so the game can run
without the official launcher. With an imported account it can also relay to
Lunar's real backend for multiplayer. See `README` notes in `tools/` and
`STATUS.md` for the details.

## License

The repository is licensed under the GNU General Public License v3.0, see
`LICENSE`.

Third party code and assets keep their own licenses and are not covered by that
license. That includes Minecraft (Mojang), Lunar Client (Moonsworth), OptiFine,
Minecraft Forge, ReplayMod and the libraries under `libs/`. See `NOTICE.md`.
