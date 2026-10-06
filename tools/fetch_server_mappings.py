#!/usr/bin/env python3
"""Build tools/fake-launcher/discovery.json from Lunar's official server list.

The local FakeBackend answers the client's ServerDiscoveryService RPCs from
this file, so the in-game Discover page shows real servers (real logos and
backgrounds are served straight from Lunar's CDN by the UI).

Source of truth:
    https://servermappings.lunarclientcdn.com/servers.json

Usage:
    python3 tools/fetch_server_mappings.py                       # download + build
    python3 tools/fetch_server_mappings.py --source servers.json # use a local copy
    python3 tools/fetch_server_mappings.py --out /tmp/d.json

The generated file only keeps the fields the client renders, plus the curated
section layout below. Re-run it whenever Lunar adds or removes servers.
"""

from __future__ import annotations

import argparse
import json
import sys
import urllib.request
from pathlib import Path

SOURCE_URL = "https://servermappings.lunarclientcdn.com/servers.json"
ROOT = Path(__file__).resolve().parent.parent
DEFAULT_OUT = ROOT / "tools" / "fake-launcher" / "discovery.json"

# Phosphor icon names the client knows (PhosphorIconLegacy). Unknown names
# render as "no icon", which is fine too.
TYPE_ICONS = {
    "PvP": "PI_FOCUS_TARGET_STROKE",
    "Practice": "PI_TROPHY_SOLID",
    "Competitive": "PI_AWARD_MEDAL_SOLID",
    "Minigames": "PI_PLUGIN_ADDON_PUZZLE_SOLID",
    "Survival": "PI_CAMP_FIRE_SOLID",
    "Skyblock": "PI_ROCKET_SHIP_SOLID",
    "Prison": "PI_ARCHIVE_LOCKED_SOLID",
    "Creative": "PI_PAINT_BRUSH_SOLID",
    "MMORPG": "PI_CROWN_WINNER_KING_SOLID",
    "Adventure": "PI_MAGNETIC_COMPASS_SOLID",
    "Casual": "PI_FACE_SMILE_SOLID",
    "Open World": "PI_EARTH_GLOBE_SOLID",
    "HCF": "PI_SHIELD_CHECK_SOLID",
    "UHC": "PI_DANGER_SKULL_SOLID",
}

# (id, display name, description, card size, icon, curated server ids, fallback game type)
SECTIONS = [
    ("featured", "Featured", "Highlighted servers with something for everyone", "large",
     "PI_ANNOTATION_STAR_SOLID",
     ["hypixel", "minemenclub", "gommehd", "mineplex", "manacube", "complex"], "Minigames"),
    ("competitive", "Competitive", "Practice, duels and ranked PvP", "medium",
     "PI_TROPHY_SOLID",
     ["minemenclub", "pvplounge", "bedwarspractice", "pvpgym", "heeph", "kaizenmc", "elevatemc", "octc"],
     "Competitive"),
    ("pvp", "PvP", "Legacy PvP, factions and hardcore servers", "medium",
     "PI_FOCUS_TARGET_STROKE",
     ["pvplounge", "zonix", "veltpvp", "veltrixmc", "dynamicpvp", "invadedlands"], "PvP"),
    ("minigames", "Minigames", "Bedwars, party games and more", "medium",
     "PI_PLUGIN_ADDON_PUZZLE_SOLID",
     ["hypixel", "mineplex", "gommehd", "minehut", "jartexnetwork", "riftmystic"], "Minigames"),
    ("survival", "Survival", "SMPs and long-term survival worlds", "medium",
     "PI_CAMP_FIRE_SOLID",
     ["mccentral", "invadedlands", "coralmc", "veltryxnetwork"], "Survival"),
    ("skyblock", "Skyblock", "Islands, economies and grinds", "medium",
     "PI_ROCKET_SHIP_SOLID",
     ["complex", "manacube", "pvpwars", "hypixel", "mcprison"], "Skyblock"),
    ("prison", "Prison", "Mine, rank up and prestige", "medium",
     "PI_ARCHIVE_LOCKED_SOLID",
     ["purpleprison", "mysticpvp"], "Prison"),
    ("creative", "Creative", "Build and freebuild plots", "medium",
     "PI_PAINT_BRUSH_SOLID",
     ["gommehd", "griefergames"], "Creative"),
]

MIN_CARDS = 6
MAX_CARDS = 8


def legacy_compatible(server: dict) -> bool:
    """True when the server accepts 1.7/1.8 clients (this harness is 1.8.9)."""
    return any(v.startswith(("1.7", "1.8")) for v in server.get("minecraftVersions", []))


def normalize(server: dict) -> dict:
    images = server.get("images", {})
    return {
        "name": server["name"],
        "description": server.get("description", ""),
        "primaryAddress": server.get("primaryAddress", ""),
        "addresses": server.get("addresses", []),
        "primaryColor": server.get("primaryColor", ""),
        "gameTypes": server.get("gameTypes", []),
        "primaryGameType": server.get("primaryGameType", ""),
        "regions": server.get("regions", []),
        "socials": server.get("socials", {}),
        "website": server.get("website"),
        "store": server.get("store"),
        "logoUrl": images.get("logo-128") or images.get("logo"),
        "backgroundUrl": images.get("background-720") or images.get("background"),
    }


def fill(section_cards: list[str], servers: dict[str, dict], game_type: str, limit: int) -> list[str]:
    """Append extra servers of `game_type` until the section has `limit` cards."""
    for server in servers.values():
        if len(section_cards) >= limit:
            break
        if server["id"] in section_cards:
            continue
        if server["inactive"] or not legacy_compatible(server):
            continue
        if game_type not in server.get("gameTypes", []):
            continue
        section_cards.append(server["id"])
    return section_cards


def build(servers: list[dict], source: str) -> dict:
    by_id = {s["id"]: s for s in servers}
    active = [s for s in servers if not s["inactive"]]

    known = {s["id"] for s in active if legacy_compatible(s)}
    enriched = {s["id"] for s in active if s.get("enriched") and s["id"] in known}
    # Prefer "enriched" (fuller) servers when auto-filling, then the rest.
    ordered = sorted(
        (s for s in active if s["id"] in known and s["id"] in enriched),
        key=lambda s: (not s.get("website"), s["name"].lower()),
    ) + sorted(
        (s for s in active if s["id"] in known and s["id"] not in enriched),
        key=lambda s: (not s.get("website"), s["name"].lower()),
    )
    pool = {s["id"]: s for s in ordered}

    sections = []
    used: dict[str, int] = {}
    for section_id, name, description, size, emoji, curated, fallback_type in SECTIONS:
        cards = []
        for server_id in curated:
            if server_id in by_id and not by_id[server_id]["inactive"]:
                cards.append(server_id)
            else:
                print(f"  ! {section_id}: '{server_id}' missing/inactive, skipped", file=sys.stderr)
        cards = fill(cards, pool, fallback_type, min(MAX_CARDS, max(MIN_CARDS, len(cards))))
        for server_id in cards:
            used[server_id] = used.get(server_id, 0) + 1
        sections.append({
            "id": section_id,
            "name": name,
            "description": description,
            "cardSize": size,
            "emoji": emoji,
            "serverIds": cards,
        })

    # Search across every curated card (a server listed in two sections appears once).
    datasets = {server_id: normalize(by_id[server_id]) for server_id in used}

    counts: dict[str, int] = {}
    for server in datasets.values():
        for game_type in server["gameTypes"]:
            counts[game_type] = counts.get(game_type, 0) + 1
    game_types = [name for name, _ in sorted(counts.items(), key=lambda kv: (-kv[1], kv[0]))]

    return {
        "source": source,
        "totalServers": len(active),
        "gameTypes": [{"name": name, "emoji": TYPE_ICONS.get(name, "")} for name in game_types],
        "sections": sections,
        "servers": datasets,
    }


def load(source: str) -> tuple[list[dict], str]:
    if source.startswith(("http://", "https://")):
        print(f"[discovery] downloading {source}", file=sys.stderr)
        with urllib.request.urlopen(source, timeout=120) as response:
            return json.load(response), source
    print(f"[discovery] reading {source}", file=sys.stderr)
    return json.loads(Path(source).read_text(encoding="utf-8")), source


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--source", default=SOURCE_URL,
                        help="official servers.json URL or a local copy")
    parser.add_argument("--out", default=str(DEFAULT_OUT), help="output path")
    args = parser.parse_args()

    servers, source = load(args.source)
    if not isinstance(servers, list):
        print("expected a JSON list from the server mappings source", file=sys.stderr)
        return 1

    discovery = build(servers, source)
    out = Path(args.out)
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(discovery, separators=(",", ":"), ensure_ascii=False), encoding="utf-8")
    cards = sum(len(s["serverIds"]) for s in discovery["sections"])
    print(f"[discovery] wrote {out} "
          f"({out.stat().st_size // 1024} KiB, {len(discovery['servers'])} servers, "
          f"{len(discovery['sections'])} sections, {cards} cards)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
