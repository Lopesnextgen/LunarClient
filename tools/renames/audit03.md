# Audit cluster 03 — `client.{alert, alert.mixin, animations, animations.mixin, calculator, calculator.mixin, click, click.chest}`

Slice: `tools/renames/audit-cluster-03.txt` (8 packages). Reconstructed from the
died audit-03 subagent's saved reasoning (opencode.db `ses_f5fa9de69...`, died
"Not Enough Credits" mid-investigation) plus independent verification of every
row against the tree.

Maps produced:

* `tools/renames/classes-audit03.tsv` — 20 class renames (all applied).
* `tools/renames/packages-audit03.tsv` — 4 package moves applied
  (`animations` → `prompt`, `alert.mixin` → `alert`,
  `calculator.mixin` → `calculator`); the 5th (`click.chest` → `click.notification`)
  was applied then **reverted** (see below).

## Per-package verdicts

* `alert` + `alert.mixin` — the mod-setting override/intercept system, not
  alerts. `AlertType` → `OverrideSource`, `Alert` → `SettingOverride`,
  `AlertCondition` → `SettingIntercept` (the "intercept" TraitType),
  `Alert2Handler` → `InterceptHandler`, base `Alert` → `ChildContainer`.
* `animations` + `animations.mixin` — the Apollo confirmation-prompt
  ("save choice") system, not animations. Abstract `Animations` →
  `PromptAction`, `AnimationsImpl` → `RunCommandPrompt` (matches siblings
  OpenUrlPrompt/BlockedActionPrompt/..., all extending the base),
  holder `Animations` → `ActivePrompt`.
* `calculator` + `calculator.mixin` — the i18n translation system, not a
  calculator. Mixin `Calculator` → `CachedReplacement` (exception string
  "CachedReplacement.Builder created with nothing set!"), `CalculatorHandler`
  → `SupplierReplacement`, `ConstantTranslation` →
  `ConstantReplacement`, `StringArrayTranslation` →
  `StringArrayReplacement` (family consistency), `Calculator` →
  `SharedInfo` (shared_info strings), `CalculatorType` → `ClientLanguage`
  (`Language`/`Locale` taken).
* `click` — LCUI screen base package (correct). `Bridge7Iterator` →
  `EmptyScreen` (no-op screen opened by CheckoutUtils), `Bridge7Iterator$Type2`
  → `BugReportCategory` (quarantined bug-report screen's categories; orphan).
* `click.chest` — notification popups. `Gui2Iterator` → `Notification`,
  `ChestType` → `NotificationType`, `Chest` → `AnchorFunction`,
  plus rescued `Gui2Extension` → `NotificationAnchor` (see below).

## chest→notification revert (documented, not dropped)

The `click.chest` → `click.notification` move was applied, then reverted:
`FriendNotification`/`Notification` reference quarantined same-package classes
(`Gui2Iterator2`, `Gui2Extension`) that resolve via the stale jar under the
`chest` path only. `Gui2Extension` was rescuable (single jar-resolved method,
like `DurabilityDisplayMode`) and was rescued directly as
`chest/NotificationAnchor` with its 4 consumer sites updated. `Gui2Iterator2`
(the hint-notification base) is NOT rescuable — it calls a `method4(String,
Object[])` overload missing from the live tree — so it stays quarantined and
the package move stays reverted until its rescue. Sibling renames
(`Notification`, `NotificationType`, `AnchorFunction`) were kept in `chest/`.
