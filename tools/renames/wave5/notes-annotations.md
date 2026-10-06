# Wave 5 — `com.moonsworth.lunar.annotations` (all 6 classes)

Map: `tools/renames/wave5/classes-annotations.tsv` (4 renames + 2 KEEP rows).
Dry run: `rows=6 skipped=2 files_touched=5 files_renamed=4`.

```
$ python3 tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-annotations.tsv --verbose
  com.moonsworth.lunar.annotations: Annotation -> ThreadConstraints (2 hits)
  com.moonsworth.lunar.annotations: Annotation2 -> ThreadRestriction (3 hits)
  com.moonsworth.lunar.annotations: Annotation3 -> SourceTag (1 hits)
  com.moonsworth.lunar.annotations: Annotation4 -> ThreadRequirement (5 hits)
[aware-renames] rows=6 skipped=2 files_touched=5 files_renamed=4 mode=dry-run
```

## 1. What this package is

Lunar's **source-retention thread-affinity annotation family**: a repeatable
"this member must (not) run on &lt;thread&gt;" restriction, its `@Repeatable`
container, the older pre-repeatable form of the same restriction, and one empty
SOURCE marker. The whole package is **dead in this tree** — `grep -rn
'moonsworth\.lunar\.annotations' src/` hits only the 6 package declarations, and
that is expected: the annotations are `RetentionPolicy.SOURCE`, so the
obfuscated client jar keeps the annotation *interfaces* but no uses of them
survive in bytecode (hence no reference evidence was available, for this or for
any earlier pass).

## 2. Provenance — the real package in `libs/multiver-full/lunar.jar`

The six tree files correspond to the real package
`com/moonsworth/lunar/IRCIIHHICIHRCOCRROCOICRIHHCCHH/` in the runtime jar. It
holds **exactly four** top-level classes, and `CRRRICCRROCOHHOHIICIHORCOORRRH$…`
is the only class in the whole jar whose constant pool contains the literal
`$NAME$ must be called from the main thread`:

| real class (obf) | shape (javap) | tree file |
|---|---|---|
| `IRCIIHHICIHRCOCRROCOICRIHHCCHH` | annotation, member `RCIRROCCCIIHCIHCCRHHCCHOHHHCHH[] IIHROHOIOHICCRRHRICHCIHRCROOIO()` | `Annotation.java` |
| `RCIRROCCCIIHCIHCCRHHCCHOHHHCHH` | `@Repeatable` element: `…$IRCIIHHICIHRCOCRROCOICRIHHCCHH IICRCOCCHCORCRHHHOIHROCHIROIHH()` + `boolean invert()` (member **not** obfuscated) | `Annotation2.java` |
| `CRRRICCRROCOHHOHIICIHORCOORRRH` | annotation, same member, **owns** nested `Type { THREAD_MAIN(Type2.THREAD, "$NAME$ must be called from the main thread") }` and `Type2 { THREAD }` | `Annotation4.java` |
| `HHCCIRHCCCIIRHCROHIORHIRHHIORH` | empty `@interface`, `@Retention(SOURCE)`, `@Target({PARAMETER, METHOD})` | `Annotation3.java` |

The remaining two files are **byte-identical rescue copies**, not a second
generation of anything: `diff Annotation.java ThreadRestrictions.java` and
`diff Annotation3.java SourceMarker.java` differ only in the interface name, and
`git log` shows `7adc91305` copied the named stem back to the placeholder stem
(`ThreadRestrictions.java -> Annotation.java`, C091; `SourceMarker.java ->
Annotation3.java`, C089). So the "Annotation2/Annotation vs Annotation4"
pairing is the only real generation split (repeatable + `invert` vs
pre-repeatable), and the container/marker duplication is a merge artifact.

Un-obfuscated evidence worth keeping: the member name `invert` (default
`false`), the enum constants `THREAD_MAIN`/`THREAD`, the message template
`"$NAME$ must be called from the main thread"`, and the `@Target`s
(`{METHOD, CONSTRUCTOR, PARAMETER}` for the restrictions, `{PARAMETER, METHOD}`
for the marker). The annotation-member method names really are obfuscated
(`IIHROHOIOHICCRRHRICHCIHRCROOIO`, `IICRCOCCHCORCRHHHOIHROCHIROIHH`), so the
real container/element names cannot be recovered from the jar.

## 3. Decision table (all 6 classes)

| file | final simple name | role / evidence |
|---|---|---|
| `Annotation.java` | **`ThreadConstraints`** | container (`Annotation2[] method1()`), but a byte-identical twin exists; the copy takes a synonym — `ThreadConstraints` is a direct synonym of "thread restrictions". Also the copy `Annotation2`'s `@Repeatable(...)` resolves to, hence the reference rewrite below |
| `ThreadRestrictions.java` | `ThreadRestrictions` (KEEP) | twin container; canonical plural name for a `@Repeatable` holder (already applied by `classes-audit01.tsv`). KEEP row is skipped by the applier |
| `Annotation2.java` | **`ThreadRestriction`** | repeatable element (newer generation): `@Repeatable(Annotation.class)`, `Annotation4.Type method1()`, `boolean invert()`; `invert` is the real member name |
| `Annotation4.java` | **`ThreadRequirement`** | older pre-repeatable form, no `@Repeatable`/`invert`; owns the shared `Type`/`Type2` enums the element reuses → previous generation of the same concept; synonym keeps it distinct from `ThreadRestriction` |
| `Annotation3.java` | **`SourceTag`** | empty SOURCE marker, unreferenced duplicate of the `SourceMarker` stem → synonym |
| `SourceMarker.java` | `SourceMarker` (KEEP) | the other marker copy, already carrying the name applied by `classes-markersholograms.tsv` |

Why the KEEP rows are in the map: the applier refuses to hand a name to a
second declaration (`SKIP … new name already declared` — it checks
`decl_packages` built at load time), so one copy per pair must take a synonym,
and the only operation left for the correctly-named twins is a documented
no-op row (the applier counts them as `skipped=2`; wave-5 maps routinely carry
skipped rows).

## 4. Application notes / follow-up

* **Intended reference rewrites** are inside the package only: row 1 also
  rewrites `@Repeatable(Annotation.class)` in `Annotation2.java` to
  `@Repeatable(ThreadConstraints.class)` (same package, no shadowing import),
  and row 4 repoints the `Annotation4.Type method1()` reference in
  `Annotation2.java` to `ThreadRequirement.Type`. Nothing outside the package
  imports any of the six names.
* **Consolidation (optional, after the dedupe pass).** The two twin pairs
  should be de-duplicated; once one copy per pair is deleted the surviving
  declaration can be given the canonical name with a second small map —
  `ThreadConstraints -> ThreadRestrictions` (and `SourceTag -> SourceMarker` if
  the marker copy survives) — that name is free by then, so the applier accepts
  it. Target end state: `ThreadRestrictions` (container), `ThreadRestriction`
  (repeatable element), `ThreadRequirement` (older element), `SourceMarker`
  (marker) — four files, no synonyms, one class per concept.
* Final simple names were grepped tree-wide before writing: `ThreadRestriction`,
  `ThreadRequirement`, `ThreadConstraints`, `SourceTag` have **0** other
  occurrences in `src/main/java`.

## 5. Ambiguities / caveats

1. **These are structural names, not recovered real names.** No mapping exists
   for the client jar: `tools/mappings-snapshot/lunar-client-names.tsv` only
   covers `com.lunarclient.*` (Apollo), `lunar_named_b5_1.8.9.kin` only
   `net/minecraft/**`, and solstice/uranometrical only ships vpatcher-prod.
   Lunar's obfuscator stripped `SourceFile` and renamed the members, and SOURCE
   retention leaves no usages, so the container's and the marker's real names
   are unrecoverable from any local artifact. `ThreadRestrictions` /
   `SourceMarker` are the most defensible descriptions (and the ones earlier
   waves already applied).
2. **`ThreadRequirement` is a synonym by construction.** It is the same concept
   as `ThreadRestriction` in its older, non-repeatable shape; if the real Lunar
   name is ever recovered, revisit this row and the container pair together.
3. **The marker's purpose is unproven.** It is empty, SOURCE-only and targets
   `{PARAMETER, METHOD}` (it does **not** allow `CONSTRUCTOR`, unlike the
   restrictions), so `SourceMarker`/`SourceTag` describe what it *is*, not what
   reads it (no annotation processor or build tool for it exists in this tree).
4. **Out of scope (nested, not in the 6 files):** `Annotation4.Type` and
   `Annotation4.Type2` keep placeholder nested names; after this map they read
   `ThreadRequirement.Type` / `ThreadRequirement.Type2`. A later nested pass
   could name them from the semantics (`Type` = the concrete requirement, e.g.
   `RequiredThread`; `Type2` = the restricted subject, e.g. `ThreadKind`), but
   with only `THREAD_MAIN`/`THREAD` retained there is no evidence for a fuller
   set.
