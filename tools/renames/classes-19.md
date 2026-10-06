# cluster-19 notes — `com.moonsworth.lunar.client.fps`

## What this package actually is

**Not** the FPS-counter feature. The whole `com.moonsworth.lunar.client.fps` package is
Lunar's **Molang JIT compiler** — the layer that turns `com.eliotlash.molang` AST
(`Expr`, `Stmt`, `Operator`, `Evaluatable`) into generated JVM bytecode (ASM). Evidence:

* `Fps6` (`HROHOIOCHIRIHICOORIHOHCIOIRIIH`, missing from the tree, present in the jar)
  builds a class literally named `"MolangJit"` in a `ClassWriter` and caches
  `Cache<Fps4, Constructor<?>>` (Guava, 10 min expiry); error strings
  `"Not jiting stmt: " + stmt.getClass()` / `"Not jiting expr: " + expr`.
* `Fps9` (`ORCHOHHCOHCORRICRIHCHHRORHHCHH`, also missing from the tree) is the Expr
  compiler: it emits `visitVarInsn/visitFieldInsn/visitJumpInsn/visitInsn` with
  AST-derived opcodes (`Expr.BinOp` → DADD/DSUB/DMUL/DDIV/DREM/DPOW, `Expr.Not` → jumps,
  `Expr.Call` → `Fps7Extension.call(double...)`).
* `Fps5` registers the built-in `math.*` functions (`"math.min"`, `"math.cos"`,
  `"math.lerp"`, `"math.dice_roll"`, …) into a scope; `Fps7Extension` declares
  `call(double...)`.
* `Fps3`'s `toString()` is `"VariablesMap{assignableMap=..., nextVarId=...}"`.
* `EvaluatableImpl` (fps/mixin) implements `com.eliotlash.molang.ast.Evaluatable`.

The *actual* FPS-counter mod is a different class: `com.moonsworth.lunar.client.mod.hud.Fps`
(formerly `com/moonsworth/lunar/client/framework/feature/fps/Fps`, see
`tools/mappings-snapshot/restructure/module-renames.tsv:117`). The earlier pipeline guessed
the package name `fps` from that neighbour and it stuck; this package needs a package
rename to `...client.molang` (out of scope here — class maps cannot rename packages).

One name is taken directly from a class's own string literal: `Fps3` → **`VariablesMap`**
(the `toString` invented name in the original source).

## Renames (8 top-level + 17 `Fps4` tokens)

| old | new | job |
|---|---|---|
| `Fps2` | `MolangClassDefiner` | `ClassLoader.defineClass` + `getDeclaredConstructor` for generated classes |
| `Fps3` | `VariablesMap` | `Assignable` → `run()` local-slot allocation (2 slots for booleans) |
| `Fps4` | `MolangJitSignature` | ordered token list used as the JIT class cache key (`equals/hashCode`) |
| `Fps5` | `MolangBuiltinFunctions` | static `math.*` registry, registers 31 functions into a scope |
| `Fps7` | `MolangSymbol` | marker supertype for scope values (functions + variable cells) |
| `Fps8` | `MolangClassBuilder` | ASM inputs for one generated class: code emitters, field types, constants |
| `Fps10` | `MolangCompileOptions` | 4 boolean compiler flags + diagnostics list |
| `Fps11` | `MolangScope` | name → symbol map with parent fallback (`Data` child scope) |
| `Fps4$Data2` | `MolangSigAccessRead` | `Expr.Access` read (int 1 = DLOAD local, 2 = GETFIELD cell) |
| `Fps4$Data3` | `MolangSigLoop` | `Stmt.Loop` |
| `Fps4$Data4` | `MolangSigAccessWrite` | `Expr.Assignment` store (int 0 = PUTFIELD cell, 1 = DSTORE local) |
| `Fps4$Data5` | `MolangSigElseIf` | `Stmt.If` elif branch |
| `Fps4$Data6` | `MolangSigBinaryOperator` | `Expr.BinOp`, carries `Operator` |
| `Fps4$Data7` | `MolangSigNot` | `Expr.Not` |
| `Fps4$Data8` | `MolangSigReturn` | `Stmt.Return` |
| `Fps4$Data9` | `MolangSigTernary` | `Expr.Ternary` |
| `Fps4$Data10` | `MolangSigGroup` | `Expr.Group` |
| `Fps4$Data11` | `MolangSigBlockEnd` | appended after a block's final expression |
| `Fps4$Data12` | `MolangSigIf` | `Stmt.If` |
| `Fps4$Data13` | `MolangSigFunctionCall` | `Expr.Call`, carries `Class<? extends Fps7Extension>` |
| `Fps4$Data14` | `MolangSigConstant` | `Expr.Constant`, carries the double |
| `Fps4$Data15` | `MolangSigBlockStart` | `Expr.Block` |
| `Fps4$Data16` | `MolangSigNegate` | `Expr.Negate` |
| `Fps4$Data17` | `MolangSigExpressionStatement` | `Stmt.Expression` |
| `Fps4$Data18` | `MolangSigVariableRead` | bare `Expr.Variable` resolving to a scope cell |

Creation sites are the two compilers, both missing from the tree but present in
`lunar.jar`: `Fps6` (statements; `HROHOIOCHIRIHICOORIHOHCIOIRIIH`) and `Fps9`
(expressions; `ORCHOHHCOHCORRICRIHCHHRORHHCHH`). The token classes themselves are
payload-only records — they exist to make the JIT signature unique per generated
code shape.

## Applying this map

`python3 tools/apply_class_renames.py --map tools/renames/classes-19.tsv` (dry run):
**8 rows apply cleanly** (Fps2/3/4/5/7/8/10/11; 15 files touched), **17 `Fps4$DataN`
rows are skipped** by the current applier (it matches simple names only, so
`Outer$Inner` rows cannot match). Do **not** retry with `--allow-collisions`: bare
`Data2`/`Data4`/… are declared in many packages (nameplate, forge lib, fishing, …) and
would be rewritten tree-wide.

For the nested rows, apply inside
`src/main/java/com/moonsworth/lunar/client/fps/Fps4.java` directly, then rename the
declarations via an import-aware applier:

```
Data2 -> MolangSigAccessRead        Data10 -> MolangSigGroup
Data3 -> MolangSigLoop              Data11 -> MolangSigBlockEnd
Data4 -> MolangSigAccessWrite       Data12 -> MolangSigIf
Data5 -> MolangSigElseIf            Data13 -> MolangSigFunctionCall
Data6 -> MolangSigBinaryOperator    Data14 -> MolangSigConstant
Data7 -> MolangSigNot               Data15 -> MolangSigBlockStart
Data8 -> MolangSigReturn            Data16 -> MolangSigNegate
Data9 -> MolangSigTernary           Data17 -> MolangSigExpressionStatement
                                    Data18 -> MolangSigVariableRead
```

(`Fps4$Data` = else-branch token and `Fps4$Extension` = the token interface are **not**
in cluster-19.txt; they are `MolangSigElse` / `MolangSigToken` when someone takes them.)

## Confidence and follow-ups

High: `VariablesMap` (toString literal), `MolangClassDefiner`, `MolangJitSignature`,
`MolangBuiltinFunctions`, `MolangCompileOptions`, `MolangScope`, `MolangSigConstant`,
`MolangSigBinaryOperator`, `MolangSigFunctionCall`, all statement tokens (`Data3/5/8/12/17`).
Medium: the int-payload read/write and access/variable splits (`Data2/4/18`), `Data7/9/10/16`
(op→AST node mapping is direct), `Data11` (block boundary marker — no payload, mapped from
the only append site in `Expr.Block`).

Not in this cluster (missing from the tree, present in `lunar.jar`; recommended names for
whoever gets them): `HROHOIOCHIRIHICOORIHOHCIOIRIIH` → `MolangStmtCompiler` (interface
`CompiledExpression` with `run()` also works), `ORCHOHHCOHCORRICRIHCHHRORHHCHH` →
`MolangExprCompiler` (+ nested `Data` → `MolangValue`, `Type` → `MolangValueType`).
Siblings in the tree but outside the cluster: `Fps` (variable-path formatter),
`Fps6Handler`, `Fps7Extension*`, `Fps7Handler` (scope symbol/variable cell).

## Dry-run result

```
[class-renames] 25 rows; 7696 java files
  8 applied (Fps2..Fps11 top-level), 17 skipped (nested Fps4$DataN rows)
  files_touched=15 files_renamed=8 mode=dry-run
```
