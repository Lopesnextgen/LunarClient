# Member application notes (2026-09-16)

The member maps (`members-options.tsv`, `members-settings.tsv`, `members-bridge.tsv`)
and the applier (`tools/apply_member_renames.py`) are committed. The first full
application was **not committed** because it introduced new ECJ failures; the
tree was reverted to the gate-clean state.

## Measured attempts (ECJ failing files, baseline 4,391)

| attempt | rows applied | failing after | new failures |
|---|---|---|---|
| all three maps | 650 | 4,435 | 44 |
| options + settings only | 343 | 4,416 | 25 |

Zero files were fixed by either attempt (the errors move, not disappear), so the
gate correctly rejected both.

## Applier improvements already in (all committed)

* source-only hierarchy index (a javap JVM per ancestor lookup made the first
  run ~10 min; now the full pass is ~4 s)
* generic bounds are no longer mistaken for `extends` (`<T extends X>` parsed
  as inheritance caused whole-subtree mis-renames)
* position-aware receiver typing (decompiled `varN` names are reused with
  different types per method; the old per-file map picked the wrong one)
* multi-declaration guard: rows whose member is declared more than once in the
  owner file are skipped (renaming both overloads breaks resolution)

## Remaining resolver gaps (the pairs that broke)

* `MixinCore9Extension.method23` (9 errors + `case LEFT/RIGHT/CENTER` cascades):
  declaration renamed but call sites kept the placeholder (receiver typed as a
  nested `Data`).
* Factory-name collisions across owners: `LightingExtension.enumOption`,
  `LightingExtension443.{enumOption,text,triState,shortOption}`,
  `OptionCombiner.dropdown`, `ColorOption.button`, `ModifierKeybindOption.toggle`
  — a call was renamed from another owner's row (owner-chain over-reach) while
  the declaration kept its placeholder.
* `FogLoader22.method24/25`, `AbstractFeature.method2`, `ThreadModuleDump63.drawString`,
  `Client.scaleXY`, `Holograms2.getEnchantmentLevel`: same family.
* Lambda receivers (`arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO`, 265 sites) still
  cannot be typed; they need the section handle type carried into the lambda.

## Next pass

1. Build a receiver-type pass that understands nested types (`Outer.Inner`),
   generic method return types, and lambda parameters (functional interface
   signature), or
2. Apply per-owner groups with an ECJ gate after each group and keep the clean
   ones (60 owners x ~25 s gate = ~25 min), or
3. Use the correction-loop: for each new failure, look up the right target from
   the map and add a correction row.
