#!/usr/bin/env bash
# ============================================================================
# Builds the jars this project generates itself (localpatches + fake launcher).
#
# The runtime libraries are vendored in libs/ (see libs/README.md): no Lunar
# Client install is needed to build or launch from this repository.
#
# Usage: bash tools/setup_libs.sh
# ============================================================================
set -euo pipefail
HERE="$(cd "$(dirname "$0")" && pwd)"

bash "$HERE/build_localpatches.sh"
bash "$HERE/build_fakelauncher.sh"

echo "[setup-libs] done"
