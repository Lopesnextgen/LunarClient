# Wave 6 — client/ structure audit (moves)

Each subagent audits one subsystem and writes a per-class move map:

    old.package<TAB>Old<TAB>new.package<TAB>New<TAB>evidence

(New == Old for move-only; applied by tools/apply_class_moves.py, which rewrites
references tree-wide.)

Target layout (from tools/renames/PLAN-restructure.md):
* `client/mod/<combat|render|movement|player|hud|misc|skyblock>/<feature>/` —
  every mod/feature; feature helpers live with the feature.
* `client/event/` event bus + event types; `client/ui/` screens/HUD framework;
  `client/config/` options/settings; `client/render/` pipeline/shaders/textures/
  fonts/particles; `client/cosmetics/` gecko/emote/skin/molang; `client/replay/`
  Rewind; `client/network/` server/ipc/websocket; `client/account/` accounts;
  `client/chat/`, `client/audio/`; `client/framework/` bootstrap/mod framework/
  listener/bytecode/crash/security; `client/util/{math,io,text,collection}/`
  real utilities only; `client/mixin/` shared mixins.
* No junk-drawer package names (`fog`, `fov`, `click`, `fps`, `pkg`, ...).
* No package with <5 classes unless it is a named subsystem.

## Map status (2026-09-16 late)

| map | rows | status |
|---|---|---|
| moves-mods.tsv | 444 | applying (background mover) |
| moves-framework.tsv | 188 | ready |
| moves-util.tsv | 154 | ready |
| moves-misc.tsv | 62 | ready |
| moves-cosmetics.tsv | 132 | ready |
| moves-render.tsv | 295 (151 stale twins) | ready |
| moves-events.tsv | 468 | ready |
| moves-replay.tsv | 427 | ready |
| moves-junkA.tsv | 752 (333 stale twins) | ready |

Cross-map check: 0 duplicate source rows; 51 duplicate destinations are the
two-generation twins (second row skips in the mover, dedupe later).

Apply order: mods -> framework -> util -> misc -> cosmetics -> render ->
events -> replay -> junkA, then `clean_params.py` on the moved packages and a
fresh ECJ gate after each map. Dedupe/twin merge is the follow-up pass.

## Dedupe pass (2026-09-16 late)

`tools/make_merge_map.py` derived **416 merge rows** from the wave-6 maps
(skipped "target already declared" rows = stale rescue twins). Applied with
`tools/repoint_external.py --map tools/renames/wave6/merges.tsv`:
416 files deleted, 268 reference files rewritten, 1,534 edit actions.
ECJ: 3,379 -> 3,325 failing (net -1,066 vs the 4,391 baseline).

Remaining junk-named packages hold the twins whose canonical class lived in a
package the map did not cover; re-run `make_merge_map.py` after the next class
wave to catch them.
