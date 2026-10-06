#!/usr/bin/env python3
"""Import an account from the official Lunar launcher into the harness store.

The harness game only sees accounts in its own data dir
(run/lunarclient/settings/game/accounts.json). Import a real account there and
tools/run-lunar.sh launches the game with that account's Minecraft identity, so
FakeBackend can relay the Authenticator + AssetServer websockets to Lunar's
official backend (that is what makes other Lunar users visible: tab icons,
their cosmetics/emotes, friends, ...).

Usage:
    python3 tools/import_lunar_account.py                     # active official account
    python3 tools/import_lunar_account.py --account <username>
    python3 tools/import_lunar_account.py --list              # show candidates
    python3 tools/import_lunar_account.py --clear             # back to offline "Player"

Minecraft access tokens expire (~24h). Run the official Lunar launcher to
refresh, then re-import. The harness's alt manager can also add accounts
(Microsoft device-code login) directly.
"""

from __future__ import annotations

import argparse
import json
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
DEFAULT_SOURCE = Path.home() / ".lunarclient" / "settings" / "game" / "accounts.json"
DEFAULT_TARGET = ROOT / "run" / "lunarclient" / "settings" / "game" / "accounts.json"


def load(path: Path) -> dict:
    if not path.is_file():
        return {"accounts": {}}
    try:
        root = json.loads(path.read_text(encoding="utf-8"))
    except (ValueError, OSError):
        return {"accounts": {}}
    if not isinstance(root.get("accounts"), dict):
        root["accounts"] = {}
    return root


def save(path: Path, root: dict) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(root, separators=(",", ":")), encoding="utf-8")


def is_premium(account: dict) -> bool:
    token = account.get("accessToken") or ""
    refresh = account.get("refreshToken") or ""
    return not token.endswith(".offline") and not refresh.startswith("local-offline-")


def describe(account: dict) -> str:
    profile = account.get("minecraftProfile") or {}
    name = account.get("username") or profile.get("name") or "?"
    expires = account.get("accessTokenExpiresAt")
    expiry = ""
    if expires:
        try:
            when = time.strftime("%Y-%m-%d %H:%M", time.strptime(expires[:19], "%Y-%m-%dT%H:%M:%S"))
            expiry = f" token-until {when}"
            if expires[:19] < time.strftime("%Y-%m-%dT%H:%M:%S"):
                expiry += " (EXPIRED)"
        except ValueError:
            pass
    return f"{name}{' [premium]' if is_premium(account) else ' [offline]'}{expiry}"


def offline_player() -> tuple[str, dict]:
    import uuid as uuidlib

    local_id = uuidlib.uuid5(uuidlib.NAMESPACE_OID, "lunar-harness-player").hex
    return local_id, {
        "accessToken": None,  # replaced below
        "localId": local_id,
        "type": "Xbox",
        "username": "Player",
        "minecraftProfile": {"id": local_id, "name": "Player"},
        "refreshToken": "local-offline-" + local_id,
        "persistent": True,
    }


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--source", default=str(DEFAULT_SOURCE))
    parser.add_argument("--target", default=str(DEFAULT_TARGET))
    parser.add_argument("--account", help="username to import (default: the active one)")
    parser.add_argument("--list", action="store_true", help="list accounts and exit")
    parser.add_argument("--clear", action="store_true", help="reset the harness store to offline Player")
    args = parser.parse_args()

    target = Path(args.target)

    if args.list:
        source = load(Path(args.source))
        active = source.get("activeAccountLocalId")
        for local_id, account in source.get("accounts", {}).items():
            marker = " (active)" if local_id == active else ""
            print(f"{local_id}: {describe(account)}{marker}")
        return 0

    if args.clear:
        local_id, account = offline_player()
        import base64

        header = base64.urlsafe_b64encode(b'{"alg":"none","typ":"JWT"}').rstrip(b"=").decode()
        payload = base64.urlsafe_b64encode(
            ('{"sub":"Player","exp":%d}' % (time.time() + 30 * 24 * 3600)).encode()
        ).rstrip(b"=").decode()
        account["accessToken"] = f"{header}.{payload}.offline"
        root = {"activeAccountLocalId": local_id, "accounts": {local_id: account}}
        save(target, root)
        print(f"[import] reset {target} to the offline dev account")
        return 0

    source = load(Path(args.source))
    accounts = source.get("accounts", {})
    if not accounts:
        print(f"[import] no accounts in {args.source}", file=sys.stderr)
        return 1

    chosen_id = None
    if args.account:
        for local_id, account in accounts.items():
            profile = account.get("minecraftProfile") or {}
            if account.get("username") == args.account or profile.get("name") == args.account:
                chosen_id = local_id
                break
        if chosen_id is None:
            print(f"[import] account '{args.account}' not found; use --list", file=sys.stderr)
            return 1
    else:
        chosen_id = source.get("activeAccountLocalId")
        if chosen_id not in accounts:
            chosen_id = next(iter(accounts))

    account = accounts[chosen_id]
    root = load(target)
    root.setdefault("accounts", {})
    # Replace any other account with the same username / profile id.
    profile = account.get("minecraftProfile") or {}
    for local_id in list(root["accounts"]):
        other = root["accounts"][local_id]
        other_profile = other.get("minecraftProfile") or {}
        if (other.get("username") and other.get("username") == account.get("username")) or (
            other_profile.get("id") and other_profile.get("id") == profile.get("id")
        ):
            del root["accounts"][local_id]
    root["accounts"][chosen_id] = account
    root["activeAccountLocalId"] = chosen_id
    save(target, root)

    print(f"[import] {describe(account)} -> {target}")
    print(f"[import] {len(root['accounts'])} account(s) in the harness store; "
          f"run tools/run-lunar.sh to use it")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
