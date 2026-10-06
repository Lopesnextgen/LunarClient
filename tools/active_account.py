#!/usr/bin/env python3
"""Print the harness launch identity for the active account, tab-separated:

    username <TAB> uuid-without-dashes <TAB> accessToken <TAB> premium(0|1)

tools/run-lunar.sh feeds this into --username/--uuid/--accessToken. When the
store holds a real (non-offline) account the game launches with that Minecraft
identity, which lets FakeBackend relay the Lunar Authenticator + AssetServer to
the official backend (other Lunar users become visible). Otherwise the offline
dev identity "Player" is used and everything stays local.

Usage: active_account.py [DATA_DIR]
"""

from __future__ import annotations

import json
import sys
import time
from pathlib import Path

FALLBACK = ("Player", "0" * 32, "0", "0")


def main() -> int:
    data_dir = Path(sys.argv[1]) if len(sys.argv) > 1 else Path("run/lunarclient")
    store = data_dir / "settings" / "game" / "accounts.json"
    if not store.is_file():
        print("\t".join(FALLBACK))
        return 0
    try:
        root = json.loads(store.read_text(encoding="utf-8"))
    except (ValueError, OSError):
        print("\t".join(FALLBACK))
        return 0

    accounts = root.get("accounts") or {}
    account = accounts.get(root.get("activeAccountLocalId"))
    if not isinstance(account, dict) or not account:
        account = next((a for a in accounts.values() if isinstance(a, dict) and a), None)
    if not isinstance(account, dict) or not account:
        print("\t".join(FALLBACK))
        return 0

    profile = account.get("minecraftProfile") or {}
    username = account.get("username") or profile.get("name") or FALLBACK[0]
    uuid = (profile.get("id") or account.get("localId") or account.get("remoteId") or FALLBACK[1])
    uuid = uuid.replace("-", "")
    token = account.get("accessToken") or FALLBACK[2]
    premium = not token.endswith(".offline") and not (account.get("refreshToken") or "").startswith("local-offline-")

    expires = account.get("accessTokenExpiresAt")
    if premium and expires and expires[:19] < time.strftime("%Y-%m-%dT%H:%M:%S"):
        print(f"[active_account] WARNING: {username}'s access token expired {expires} - "
              f"refresh it via the official Lunar launcher and re-run "
              f"tools/import_lunar_account.py", file=sys.stderr)

    print(f"{username}\t{uuid}\t{token}\t{'1' if premium else '0'}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
