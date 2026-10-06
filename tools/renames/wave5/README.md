# Wave 5 — class rename campaign (2026-09-16)

This directory holds the maps produced by the wave-5 rename subagents. The
authoritative rules and map format live in `tools/renames/README.md`; this file
only adds the wave-specific details.

## Scope

Wave 5 renames classes **in place**. Package moves are a separate wave handled
by `tools/apply_class_moves.py` / `tools/apply_package_renames.py`.

Clusters: `tools/renames/cluster-NN.txt` (regenerated 2026-09-16, 4,024 rows,
103 clusters). Index with row counts: `wave5/clusters.md`.

Already claimed by other workstreams — do **not** rename these in wave 5:

| Area | Clusters | Handled by |
|---|---|---|
| `com.moonsworth.lunar` root (flattened shaded libs: Jackson/Mixin/...) | 20-35 | `tools/match_libs.py` + `apply_class_moves.py` |
| `com.moonsworth.lunar.genesis` (relocated Guava) | 01-19 | Guava matcher (same tooling) |

Everything else is fair game, including bridge, client.util, legacy, ichor,
lighting, fog.holograms, and the feature/mixin clusters.

## Outputs (one set per cluster)

* `tools/renames/wave5/classes-NN.tsv` — `package<TAB>old<TAB>new<TAB>evidence`
* `tools/renames/wave5/notes-NN.md` — summary + skipped/ambiguous rows

Subagents never edit sources. The main agent applies maps with
`tools/apply_class_renames_aware.py`, gates with `tools/ecj_diff.py`
(no NEW failing files) and commits per batch.

## Evidence shortcuts

* real Lunar names: `tools/mappings-snapshot/lunar-client-names.tsv`
* prior obf→named tables: `tools/mappings-snapshot/restructure/*.tsv`
* runtime jars: `libs/multiver-full/*.jar` (decompile with
  `java -jar tools/bin/cfr-0.153.jar <jar> --outputdir /tmp/opencode/cfr --jarfilter 'com/moonsworth/**'`)
* real-named Lunar open source (cloned 2026-09-16): `/tmp/opencode/reference/`
  (`Mercury`, `Atlas`, `Bombe`, `Lorenz`, `LegacyLauncher`, `molangkit`,
  `geckolib-core`, `Apollo`, `Configurate`, `jGui`, `ReplayModMixins`, `Mixin`)
* reference clients: `Documentation/references/mc-client-sources/sources/`
  (509 clients) and `~/Downloads/Badlion 2.0.0-v-beta SRC.zip`
* FabricMC projects are worth checking if a class looks like loader/mixin
  tooling (`FabricMC/mapping-io`, `FabricMC/tiny-remapper`, `FabricMC/Mixin`).

## Status ledger

| cluster | rows | map | applied commit | notes |
|---|---|---|---|---|
| | | | | |

---

## Session status (2026-09-16, evening)

**Done and pushed**

* Root shaded-Jackson block (963 files) deleted; consumers quarantined
  (`tools/rescue-quarantine/root-jackson/`), `Annotation27` -> real Jackson
  `@JsonProperty` (jackson-annotations 2.15.0), glue classes relocated.
* Genesis (1,753 embedded-Guava files) removed to
  `tools/rescue-quarantine/genesis-module/`; consumers repointed to real Guava
  (guava 29.0-jre) where public.
* `forge/lib` (1,102 shaded files) deleted.
* Class renames applied: clusters 36-57, 61, 63-70, annotations (`-350` renamed).
* Member spree wave 1 applied: `members-{107,108,141,188,191,206,230,232}`,
  `members-obf{1,2,3}` (+ `members-align[3-6]` alignment rows). The user's
  example token `CCCHIHCOIHIHRICIRCRIICHHHRHIIH` -> `ignoreRecentKeyPress`.
* `clean_params.py`: parameter renames on compiling files (2,274 files).
* ECJ failing files: **4,391 -> 3,354 (-1,037)**.

**Next (in order)**

1. Apply the remaining member maps as their subagents finish
   (`tools/renames/wave5/members-*.tsv`), then run
   `tools/member_align.py` and re-gate.
2. Remaining class clusters: 59, 71-103 (feature packages; most are rescue
   duplicates — subagent notes list the merge candidates).
3. Package restructure wave (`moves-*`, `packages-*` appliers) for the
   junk-drawer packages.
4. Jackson/Guava port for the inactive-area files listed in the
   `genesis-notes.md` outcome section (they need member translation).
5. `clean_params.py` on the remaining failing files once they compile.
