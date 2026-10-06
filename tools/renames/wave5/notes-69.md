# Cluster 69 — `com.moonsworth.lunar.client.fps` (28 rows)

Map: `tools/renames/wave5/classes-69.tsv` — **6 rename rows** (2 top-level + 4
nested; 3 of the rows address cluster rows, 3 more rename nested types of the
same classes that the lazy-name inventory never lists), **25 skipped** as
stale-jar duplicates (8 top-level + 17 `Fps4` tokens). No `net.minecraft.*`
rows, no shaded third-party classes in this package.

Dry run (`tools/apply_class_renames_aware.py --map tools/renames/wave5/classes-69.tsv --verbose`):

```
[aware-renames] 6 rows (4 nested); 9808 java files
  com.moonsworth.lunar.client.fps: Fps6$Data -> MolangStmtEmitter (2 files)
  com.moonsworth.lunar.client.fps: Fps6$Data2 -> MolangStmtContext (2 files)
  com.moonsworth.lunar.client.fps: Fps9$Data -> MolangValueEmitter (4 files)
  com.moonsworth.lunar.client.fps: Fps9$Type -> MolangValueType (4 files)
  com.moonsworth.lunar.client.fps: Fps6 -> MolangStmtCompiler (78 hits)
  com.moonsworth.lunar.client.fps: Fps9 -> MolangExprCompiler (164 hits)
[aware-renames] rows=6 skipped=0 files_touched=11 files_renamed=2 mode=dry-run
```

Applied once to a scratch copy (`/tmp/opencode/c69-check`) and inspected: no
leftover `Fps6.`/`Fps9.` compounds, `Fps9.Data`/`Fps9.Type` in
`cosmetics/molang/MolangBuiltin` became `MolangExprCompiler.MolangValueEmitter`/
`MolangValueType`, and `ConstantExpression`/`MolangCustomFunction`/
`MolangRuntime`/`Highlight`/`FogIterator`/`Fps6Handler`/`Fps7Extension17` all
compile against the new names.

## 1. What this package is

Lunar's **Molang JIT compiler** (turns `com.eliotlash.molang` AST into ASM
bytecode for a generated class literally named `"MolangJit"`), not the FPS
counter — that is `client.mod.hud.Fps`. Established by `classes-19.md`; also
confirmed here: `MolangJit` `ClassWriter`s, `MethodVisitor` emission,
`Fps.epsilonEquals`, `math.*` builtin registry, `"Not jiting stmt: "`.

`/tmp/opencode/reference/molangkit` (LunarClient/molangkit @ `16ced00`) is only
the **upstream AST/evaluator** (`ast/`, `variables/`, `functions/`, `lexer/`,
`CompilerConstants`); it contains **no JIT/compiler classes**, so it offers no
real names for this package. The upstream analogue of the compiled program is
`ast/Evaluator` + `variables/ExecutionContext`, which do not map 1:1.

## 2. The duplication finding (why 25 rows are skipped)

The `fps` files in the tree are **rescued pre-rename copies**, not new work:

| step | commit | what happened |
|---|---|---|
| wave-1 rename | `9fb544127` (`classes-19`) | `fps` `Fps2/3/4/5/7/8/10/11` renamed to `MolangClassDefiner`, `VariablesMap`, `MolangJitSignature`, `MolangBuiltinFunctions`, `MolangSymbol`, `MolangClassBuilder`, `MolangCompileOptions`, `MolangScope` |
| audit06 | `31245fb24` | `Fps` -> `MolangVariablePath`, `Fps6Handler` -> `ConstantExpression`, `Fps7Extension` -> `MolangBuiltin`, `Fps7ExtensionN` -> `MolangMath<Op>`, `Fps7Handler` -> `MolangVariable`; **package moved** `client.fps` -> `client.molang` (later `cosmetics/molang` via bucket B `811b292d4`) |
| rescue | `7adc91305` + `47dd3dd48` | full-coverage sweep with `--include-renamed-twins` re-added the *old* `fps` files from `libs/lunar-renamed-classes.jar`, because rescued call sites (FogIterator, Highlight, FunctionImpl2..7, Holograms2Iterator, ...) still referenced the old names |

`Fps6` and `Fps9` were never in `src/main` (they lived in `src/reference/java/...`
at `50dba2ea9` and stayed jar-only until the rescue), so no rename map ever
touched them — they are the genuine leftovers the cluster note refers to.

Both families are live and compile today:

| fps class (skipped) | already-named twin in `client.cosmetics.molang` | refs fps / twin |
|---|---|---|
| `Fps2` | `MolangClassDefiner` | 1 / 0 |
| `Fps3` | `VariablesMap` | 3 / 2 |
| `Fps4` (+ 17 token classes) | `MolangJitSignature` (+ `MolangSig*`) | 2 / 0 |
| `Fps5` | `MolangBuiltinFunctions` | 1 / 1 |
| `Fps7` | `MolangSymbol` | 5 / 4 |
| `Fps8` | `MolangClassBuilder` | 3 / 1 |
| `Fps10` | `MolangCompileOptions` | 2 / 0 |
| `Fps11` | `MolangScope` | 8 / 5 |

Duplicate proof (all 8 pairs): body diff vs the twin is only the package line, an
added explicit constructor and already-cleaned local names; e.g. `Fps3` and
`VariablesMap` share the `toString()` literal
`"VariablesMap{assignableMap=" + field1 + ", nextVarId=" + field2 + "}"`.
The fps family is consumed by the legacy highlight/holograms animation code
(`client.inactive.mixin.*`, `client.fog.holograms.HologramEntityManager`), the
`cosmetics/molang` family by the modern cosmetics/GeckoLib path
(`cosmetics.inactive.mixin.highlight.*`, `Mod.misc.GeckolibDebugMod`).

Per `classes-59.md` §2 and the wave-5 precedents (`classes-39/42/43/46/47`)
these are **repair work, not naming work**: renaming a twin to the canonical
name is refused by the applier (`new name already declared`), and inventing a
second name for the same class would defeat the dedupe. Recommended repair:
pick one family per concept (the `cosmetics/molang` lineage is the named one,
the `fps` lineage is the complete one because it carries the two compilers),
repoint the call sites and delete the other copy. The 17 nested `Fps4` tokens
are constructed only by `Fps6`/`Fps9`, while the twin `MolangSig*` tokens are
`MolangJitSignature`-only declarations with **zero references anywhere** — the
`fps` side is the live signature-token set.

## 3. Renames (6 rows)

| # | old | new | why |
|---|---|---|---|
| 1 | `Fps6` | `MolangStmtCompiler` | Stmt(s) -> runnable program compiler facade **and** the compiled-program interface (`run()`); generated `"MolangJit"` class implements it; `Cache<Fps4,Constructor<?>>` keyed by the JIT signature. Jar-only leftover; `classes-19.md` recommendation; name free tree-wide |
| 2 | `Fps9` | `MolangExprCompiler` | expression compiler: `method1(Expr, Fps3, Fps11, Fps10, Fps6.Data2, Fps4)` emits DADD..DPOW / jump chains / `Math.pow` / `Fps.epsilonEquals`, `"Not jiting expr: "`; called by `MolangStmtCompiler` and `MolangBuiltin.method4..7`. Jar-only leftover; `classes-19.md` recommendation; name free |
| 3 | `Fps6$Data` | `MolangStmtEmitter` | per-statement emitter + class baker (`method2(MolangJitSignature)` builds `"MolangJit"` and returns a program); nested of row 1 |
| 4 | `Fps6$Data2` | `MolangStmtContext` | statement-compile context threaded through `Fps6.method6` (root/value flag `field2` decides keep-on-stack vs pop; `field1` never read); nested of row 1 |
| 5 | `Fps9$Data` | `MolangValueEmitter` | compiled expression fragment: `Consumer<Fps8>` emitter + value kind, created by `method12`; nested of row 2 |
| 6 | `Fps9$Type` | `MolangValueType` | `DOUBLE_PRIM`/`DOUBLE_OBJ`/`BOOLEAN` + `convertTo`/`pop`; nested of row 2 |

Rows 3-6 are `Owner$Inner` rows (`old` is the bare inner name, the evidence
starts `nested Owner$Inner: ...`, matching `classes-42.tsv`); the applier's
nested-first detection reorders them ahead of the owner rows automatically —
verified in the dry run (`4 nested`).

## 4. Skipped cluster rows (25) — stale duplicates

Top-level (8): `Fps2`, `Fps3`, `Fps4`, `Fps5`, `Fps7`, `Fps8`, `Fps10`,
`Fps11` -> twins in the table above.

Nested `Fps4` tokens (17), all declared inside `Fps4.java` and constructed only
by `Fps6`/`Fps9`; twin names already exist as `MolangJitSignature.MolangSig*`:
`Data2`->`MolangSigAccessRead`, `Data3`->`MolangSigLoop`,
`Data4`->`MolangSigAccessWrite`, `Data5`->`MolangSigElseIf`,
`Data6`->`MolangSigBinaryOperator`, `Data7`->`MolangSigNot`,
`Data8`->`MolangSigReturn`, `Data9`->`MolangSigTernary`, `Data10`->`MolangSigGroup`,
`Data11`->`MolangSigBlockEnd`, `Data12`->`MolangSigIf`,
`Data13`->`MolangSigFunctionCall`, `Data14`->`MolangSigConstant`,
`Data15`->`MolangSigBlockStart`, `Data16`->`MolangSigNegate`,
`Data17`->`MolangSigExpressionStatement`, `Data18`->`MolangSigVariableRead`.

## 5. Out-of-scope leftovers in the same package (for the repair pass)

These files are in `client.fps` but are not cluster rows (no digit suffix), and
all are duplicates of applied names: `Fps` -> `MolangVariablePath`,
`Fps6Handler` -> `ConstantExpression`, `Fps7` -> `MolangSymbol`,
`Fps7Extension` -> `MolangBuiltin`, `Fps7Extension2..33` -> `MolangMath*`,
`Fps7Handler` -> `MolangVariable`, `mixin/EvaluatableImpl` ->
`cosmetics/molang/ast/ConstantEvaluatable`. Delete/unify them together with the
25 skipped rows; the two renamed compilers are the only pieces with no twin.
