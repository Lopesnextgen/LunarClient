#!/usr/bin/env python3
"""Print the first free TCP port at or after BASE on 127.0.0.1.

Used by tools/run-lunar.sh so the local fake launcher/backend avoid ports that
are already taken - most importantly 28190, which the official Lunar launcher
also listens on for game IPC while it is running.
"""

import socket
import sys


def main() -> int:
    if len(sys.argv) < 2:
        print("usage: pick_port.py BASE", file=sys.stderr)
        return 2
    base = int(sys.argv[1])
    for port in range(base, base + 50):
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
            try:
                sock.bind(("127.0.0.1", port))
            except OSError:
                continue
            print(port)
            return 0
    print(f"no free port in {base}..{base + 49}", file=sys.stderr)
    return 1


if __name__ == "__main__":
    raise SystemExit(main())
