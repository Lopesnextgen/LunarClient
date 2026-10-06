#!/usr/bin/env python3
"""Create a patched local copy of Lunar's WebOSR UI bundle.

The stock UI lives read-only in the installed Lunar client. This script copies
it under <data>/ui-local and applies two small changes:

 1. static/altmanager.js is added (the local alt manager panel)
 2. index.html loads it after the React bundle
 3. the minified accounts module's addAccount() is redirected to the panel so
    the stock "Add account" button opens our cracked/premium flows

Usage:
    python3 tools/patch_ui.py [--official DIR] [--out DIR] [--force]

Defaults: official = installed .lunarclient/ui, out = run/lunarclient/ui-local
"""
import argparse
import re
import shutil
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
DEFAULT_OFFICIAL = Path.home() / ".var/app/com.lunarclient.LunarClient/.lunarclient/ui"

STOCK_ADD = 'async addAccount(){await this.invoke("addAccount")}'
PATCHED_ADD = (
    'async addAccount(){if(window.lunarAltManager&&window.lunarAltManager.handleAdd)'
    '{window.lunarAltManager.handleAdd();return}await this.invoke("addAccount")}'
)


def bundle_dir(root: Path) -> Path:
    for child in sorted(root.iterdir()):
        if (child / "index.html").is_file() and (child / "static/index.js").is_file():
            return child
    raise SystemExit(f"no UI bundle with index.html under {root}")


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--official", default=str(DEFAULT_OFFICIAL))
    parser.add_argument("--out", default=str(ROOT / "run/lunarclient/ui-local"))
    parser.add_argument("--force", action="store_true")
    args = parser.parse_args()

    official = Path(args.official)
    if not official.is_dir():
        print(f"[patch-ui] official UI dir not found: {official}", file=sys.stderr)
        return 2
    src = bundle_dir(official)
    out_root = Path(args.out)
    dest = out_root / src.name

    already = dest.is_dir() and (dest / "static/altmanager.js").is_file()
    if already and not args.force:
        print(f"[patch-ui] already patched: {dest}")
        return 0

    if dest.exists():
        shutil.rmtree(dest)
    shutil.copytree(src, dest)

    # 1. alt manager script
    shutil.copyfile(ROOT / "tools/ui/altmanager.js", dest / "static/altmanager.js")

    # 2. index.html script tag
    index = dest / "index.html"
    html = index.read_text(encoding="utf-8")
    tag = '<script src="/static/altmanager.js"></script>'
    if tag not in html:
        if "</body>" in html:
            html = html.replace("</body>", f"    {tag}\n  </body>")
        else:
            html += "\n" + tag + "\n"
        index.write_text(html, encoding="utf-8")

    # 3. redirect the stock add-account button
    js_path = dest / "static/index.js"
    js = js_path.read_text(encoding="utf-8", errors="replace")
    if STOCK_ADD in js:
        js = js.replace(STOCK_ADD, PATCHED_ADD, 1)
        js_path.write_text(js, encoding="utf-8")
        print("[patch-ui] redirected stock addAccount() to the alt manager panel")
    else:
        print("[patch-ui] WARNING: stock addAccount() snippet not found; "
              "the chip button will use Lunar's launcher flow", file=sys.stderr)

    print(f"[patch-ui] patched UI bundle -> {dest}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
