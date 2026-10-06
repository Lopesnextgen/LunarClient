# Lunar Client 1.8.9 — Deobfuscation Pipeline (final status)

A buildable, heavily renamed deobfuscation of **Lunar Client 1.8.9**, merged
into the MCP 1.8.9 workspace at `MavenMCP-1.8.9/`.

## Result at a glance

| Item | Value |
|---|---|
| Project artifact | `com.moonsworth:lunar-client-deobf:1.8.9` |
| Build | `mvn -Dmaven.test.skip=true clean package` → **SUCCESS** |
| Artifacts | `target/lunar-client-deobf-1.8.9.jar` (22 MB), `…-jar-with-dependencies.jar` (111 MB) |
| `net/minecraft` (MCP) sources | 1,612 files |
| Lunar sources compiled | 2,837 files (green) |
| Lunar sources in `src/reference/java` | 2,903 files (readable, not compiled; classes in `libs/`) |
| Bundled Lunar classes | `libs/lunar-libraries.jar` (32,174 classes) |
| Renamed mixin classes | 637 (named after their `@Mixin` targets) |
| Renamed module classes | 410 (named after mod ids) |
| Renamed packages | 1,262 prefix mappings (all gibberish segments gone) |
| Renamed methods/fields | 161,458 methods + 141,063 field references |
| Renamed locals/params | main: 8,320 locals + 21,378 params; reference: 46,422 + 53,939 |
| Assets | 491 Lunar entries, 3,090 Minecraft entries, 34 Lunar language files |
| Libraries | ReplayMod, Forge, OptiFine, Kotlin, protobuf, Mixin, ASM, … bundled |

Example of the final naming:

```
com/moonsworth/lunar/client/freelook/Freelook
com/moonsworth/lunar/client/framework/feature/mixin2/AttackIndicator
com/moonsworth/lunar/client/fog/handler/…
com/moonsworth/lunar/forge/mixin2/mixin/ModelLoaderMixin
com/moonsworth/lunar/genesis/…/ClientGameBootstrap
```

---

## 1. Inputs

Read from the installed Lunar Client data set
(`~/.var/app/com.lunarclient.LunarClient/`).

| Input | Notes |
|---|---|
| `lunar.jar` | Core client, 18,627 classes (6,597 `com/moonsworth`) |
| `genesis-0.1.0-SNAPSHOT-all.jar` | Client framework/loader |
| `forge-0.1.0-SNAPSHOT-all.jar` | Forge platform module (incl. Kotlin stdlib) |
| `legacy-…-nomappings.jar` | 1.8.9 multiver platform module (Java 17) |
| `optifine-…`, `common-…`, `lunar-replaymod-forge-mixins-…` | Supporting modules |
| `lunar-platform-mappings-v1_8.jar` | **The `.kin` mapping files** |
| MCP stable 1.8.9 | `../mappings/versions/1.8.9/` |
| MCP source tree | `src/main/java/net/minecraft` |
| `Forge_v1_8.jar`, `OptiFine_v1_8.jar`, `ReplayMod-v1_8-2.6.24.jar` | Bundled libraries |
| Lunar assets | `.lunarclient/textures/assets/lunar`, `lunar-lang.jar` |

---

## 2. Reverse-engineering the `.kin` mapping format

Recovered by decompiling the single class in `lunar.jar` that references
`.kin` and the reader in `genesis` (a
`org.cadixdev.lorenz.io.BinaryMappingsReader`). Big-endian Java
`DataOutputStream`:

```
int32   magic 99151942 (0x05E8F046)
int8    version 1
int32   string-pool count (ignored)
int32   top-level class count
per class:
    UTF obfuscated name
    UTF deobfuscated name
    int32 inner-class count (recurse)
    int32 field count  -> (obfName, descriptor, deobfName)
    int32 method count -> (obfName, descriptor, deobfName)
```

Five files are shipped: `lunar/lunar_named_b5_1.8.9.kin`, and the four
`v1_8_inflight_{vanilla,optifine,forge,optiforge}.kin` flavour mappings.
Parser tools: `tools/kin2tsrg.py`, `tools/kin2tiny.py`,
`tools/export_mappings.py`.

### Why the public Lunar mappings don't help

`Lunar-Mapping-Project/mappings` targets different jar hashes (2023/2025).
Measured overlap with this build: **0 obfuscated Lunar classes**. Lunar's
obfuscator regenerates every name per build and strips `SourceFile`,
`LocalVariableTable` and `MethodParameters`; a scan over all 6,597 core
classes confirmed no original identifiers survive. All readable names in this
tree were therefore *inferred* (see §5).

---

## 3. Core pipeline

```
python3 tools/deobf_pipeline.py mappings    # kin -> Tiny/TSV tables + MCP CSVs
python3 tools/deobf_pipeline.py extract     # union all Lunar jars (32,174 classes)
python3 tools/deobf_pipeline.py remap       # KinRemapper: Minecraft refs + clash renames
python3 tools/deobf_pipeline.py decompile   # chunked Vineflower (imports mode)
python3 tools/deobf_pipeline.py assemble    # sources/libs/assets into the project
python3 tools/fix_decompiled.py --apply     # mechanical decompiler-artifact fixes
bash    tools/fast_green.sh                 # ECJ-driven exclude loop (fast)
bash    tools/maven_finish.sh               # javac/Maven finishing loop
```

* **Extract** — 7 Lunar jars merged, dedup by FQCN+SHA-1; 2,420 identical
  duplicates, 0 conflicts.
* **Remap** (`tools/KinRemapper.java`, ASM) — class names `X_v1_n → X`
  (2,620), exact method/field renames (77,854 / 13,153), `$v1_*` suffix
  stripping guarded by known mapping bases, `func_*`/`field_*` via the MCP
  stable-20 CSVs, descriptor-aware lookups.
* **Class/package clash pass** (`tools/make_class_renames.py`) — 634 classes
  whose FQCN is also a package prefix get a trailing `_` (Java cannot express
  both); inner classes are propagated by `KinRemapper`.
* **Decompile** — Vineflower 1.12.0, chunked by package prefix (~1,100 classes
  per chunk, `-Xmx2g`). CFR fallback available but never needed.
* **Mechanical fixes** (`tools/fix_decompiled.py`) — empty-component records
  → classes (366), numeric anonymous references, empty enums, diamond casts.
* **Assemble** — sources → `src/main/java`, all Lunar classes →
  `libs/lunar-libraries.jar`, shipped libs → `libs/`, filtered compile jars for
  Forge/OptiFine, Lunar textures/lang → `src/main/resources`.
* **Compile loops** — ECJ reports all errors in one pass (3 iterations to
  green); Maven/javac finishes the last few files (4 iterations). Files javac
  cannot parse are moved to `src/reference/java`; their classes remain
  available from `libs/lunar-libraries.jar`.

---

## 4. Build

```bash
export JAVA_HOME=~/.sdkman/candidates/java/21.0.12-amzn
export PATH=~/.sdkman/candidates/maven/current/bin:$PATH
cd MavenMCP-1.8.9
mvn -Dmaven.test.skip=true clean package
```

Toolchain (installed with SDKMAN!):

| Tool | Version | Purpose |
|---|---|---|
| Zulu FX 8 | `8.0.502.fx-zulu` | original MCP workspace |
| Zulu FX 17 | `17.0.20.fx-zulu` | JavaFX-capable runtime / decompilers |
| Amazon Corretto 21 | `21.0.12-amzn` | **build JDK** |
| Maven | `3.9.16` | build |
| Vineflower | 1.12.0 | decompiler (`tools/bin/`) |
| CFR | 0.153 | fallback decompiler |
| ASM | 9.7.1 | `KinRemapper` |
| ECJ | 3.33.0 | fast error-complete compiler for the exclude loop |
| TinyRemapper | 0.10.4 | kept for reference (rejected, see below) |

Notes:
* Java 17 is the language level (`--release 17`); JDK 17's javac crashes with
  an internal NPE on one decompiled class, so the build uses JDK 21.
* MCP is compiled in a first execution, Lunar in a second one (a single javac
  run overflows the constant pool of several large vanilla classes).
* `-g:none` is used because debug attributes push those classes over the
  65,535 constant limit.

---

## 5. Renaming (the "Badlion-like" cleanup)

Original names are unrecoverable, so names were **inferred** with conservative
rules, applied at bytecode level and then re-decompiled so the generated
source *intrinsically* uses the new names.

| Pass | Tool | Map | Size | Examples |
|---|---|---|---|---|
| Classes | `tools/infer_renames.py` | `inferred-renames.tsv` | 1,057 | `ChunkMixin`, `ModelLoaderMixin`, `AttackIndicator`, `Freelook` |
| Packages | `tools/make_package_renames.py` | `package-renames.tsv` | 1,262 | `framework`, `feature`, `mixin`, `gui`, `fog`, `handler`, `network`, `config`, `annotations` |
| Members | `tools/make_member_renames.py` | `member-renames.tsv` | 72,875 | getters/setters from field access, systematic `methodN`/`fieldN` |
| Locals | `tools/clean_locals.py` | applied in place | 130k+ | type-aware `player`, `text`, `flag`, `index`, `list`, … |

* Mixins: a class annotated `@Mixin(Target.class)` / `@Mixin(targets="a.b.Target")`
  becomes `TargetMixin` (anonymous targets use the outer class name).
* Modules: classes whose `getName()`/`getId()`/`getModId()` returns an
  ALL_CAPS constant are named after it in PascalCase.
* Member renaming uses exact JVM descriptors; 0 collisions were produced.
* Local renaming is token-aware (strings/comments untouched); it has been
  applied to both the compiled and reference trees.

Apply/regenerate everything with:

```bash
bash tools/apply_renames.sh      # applies both maps, re-decompiles, rebuilds, cleans locals
```

All rename maps are preserved under `tools/work/mappings/`:

```
classes.tsv             2620   kin class mappings
members.tsv            91047   kin member mappings
srg-methods.tsv         6724   MCP SRG -> MCP
srg-fields.tsv          6585   MCP SRG -> MCP
class-renames.tsv        876   class/package clash renames
inferred-renames.tsv    1057   mixin/mod names
package-renames.tsv     1262   package names
member-renames.tsv     72875   method/field names
rename-fixes.tsv          11   invalid-identifier fixes
```

---

## 6. Verification

`tools/verify.sh` runs `mvn clean package` and checks:

* `target/lunar-client-deobf-1.8.9-jar-with-dependencies.jar`
* 491 `assets/lunar` entries, Lunar language files present
* compiled `com/moonsworth` + `net/minecraft` classes
* bundled Kotlin (2,913), protobuf (779) and `com/lunarclient` (5,346) classes
  (proof the system-scoped libraries were unpacked)

## 7. Layout

```
MavenMCP-1.8.9/
  pom.xml                      # com.moonsworth:lunar-client-deobf:1.8.9
  README.md                    # short project intro
  pipeline-work.md             # this document
  src/main/java/net/minecraft  # MCP 1.8.9 sources (1,612 files)
  src/main/java/com/moonsworth # renamed Lunar sources, compiled (2,837 files)
  src/reference/java           # renamed Lunar sources javac rejects (2,903 files)
  src/main/resources           # MCP assets + Lunar textures/lang + service files
  libs/                        # lunar-libraries.jar + ReplayMod/Forge/OptiFine/natives
  tools/                       # the whole pipeline (scripts + jars)
```

## 9. Restructure & normalization (final pass)

After the first renaming pass the tree still had two problems: fake
`mixin*` package names (`mixinCore`, `mixinExtra`, `mixin2`, …) that were not
mixins at all, and shaded library trees (`forge/lib`) sitting in the main
source root. Three inference maps plus a normalizer fixed that, applied at
bytecode level and followed by a full re-decompile:

| Map | Entries | Purpose |
|---|---:|---|
| `restructure/mixin-renames.tsv` | 690 | real `@Mixin` classes → `<module>/mixin/` |
| `restructure/module-renames.tsv` | 764 | mod classes → `client/mod/{combat,render,movement,player,hud,misc}/` |
| `restructure/remaining-renames.tsv` | 9,190 | every remaining obfuscated class (incl. inlined inner classes) |
| `normalize-renames.tsv` | 6,220 | strip fake `mixin*` segments, canonicalise real mixins |

`tools/apply_restructure.sh` merges the three maps (structure from
mixin/module maps, clean simple names from the remaining map), applies them,
re-decompiles, rebuilds, re-cleans locals, deletes empty directories and
packages. `tools/final_normalize.sh` does the same for the normalization pass
and moves `lunar/forge/lib/**` (shaded libraries) to `src/reference/java`.

Final source tree:

* `src/main/java/net/minecraft` — 1,612 MCP files
* `src/main/java/com/moonsworth` — 3,711 compiling Lunar files
* `src/reference/java` — 5,772 readable, non-compiling Lunar files
* zero obfuscated class names, zero fake `mixin*` packages, zero empty dirs

Reference clients used for the naming conventions: `../Badlion 2.0.0-v-beta`
and the 509 clients in
`~/Downloads/ABDM/Compressed/mc-client-sources-main/sources` (packages
`mod/module`, `event(s)`, `gui`, `config`, `manager`, `util`, and flat
`mixin`/`mixins` layouts).

## 10. Known limitations

* Original Lunar identifiers are unrecoverable; all clean names are inferred.
  Most methods without a readable override are systematically named
  `methodN`/`fieldN` — clean, but not always semantic.
* `legacy` is a multiver module supporting Minecraft 1.7/1.12/1.16 as well;
  it cannot compile against 1.8.9 and lives in `src/reference/java`.
* Files in `src/reference/java` are excluded from compilation but fully
  readable; their classes are inside `libs/lunar-libraries.jar`.
* `com/lunarclient/**` (generated protobuf + relocated libraries) is bundled
  compiled rather than decompiled.
* The fat jar filters `com/moonsworth/**` from `lunar-libraries.jar` to avoid
  duplicating the compiled sources.
