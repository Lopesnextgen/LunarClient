#!/usr/bin/env bash
# ============================================================================
# Lunar Client 1.8.9 — local launch harness (stock runtime)
#
# Launches Lunar Client from this workspace the way Lunar's own launcher does:
# official Minecraft 1.8.9 jar + bundled Lunar modules + Ichor mapping, with
# the official 1.8.9 libraries from the Lunar install. The workspace MCP
# source tree is not used at runtime; see tools/run-mcp-classes.sh for the
# experimental "run our compiled game classes" variant.
# ============================================================================
set -u
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"
LIB="$ROOT/libs"
# Vendored Lunar runtime: jars, modules, natives and the UI bundle all ship in
# this repo (libs/), so no Lunar Client install is needed anywhere.
MV="$LIB/multiver-full"
LUNAR_JAR="$MV/lunar.jar"
LUNAR_ASSETS="${LUNAR_ASSETS:-$LIB/lunar-assets}"
# Vanilla Minecraft side: packaged in libs/vanilla by default (fully offline).
# Set LUNAR_MC to a .minecraft directory to use an installed copy instead.
VANILLA="${LUNAR_VANILLA:-$LIB/vanilla}"
MC="${LUNAR_MC:-$VANILLA}"
if [ ! -d "$MC/versions/1.8.9" ]; then
  echo "[run-lunar] no Minecraft 1.8.9 runtime at $MC (set LUNAR_MC, or refresh the packaged copy with tools/vendor_from_install.py)" >&2
  exit 1
fi
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home 17.0.20.fx-zulu 17.0.19.fx-zulu)"
export JAVA_HOME
JAVA="$JAVA_HOME/bin/java"
MEM="${LUNAR_MEM:-2048M}"
GAME="${LUNAR_GAME:-$ROOT/run/game}"
DATA="${LUNAR_DATA:-$ROOT/run/lunarclient}"
LOGS="$ROOT/run/logs"
mkdir -p "$GAME" "$DATA" "$LOGS" "$DATA/profiles/1.8/mods"
[ -e "$GAME/assets" ] || ln -sfn "$MC/assets" "$GAME/assets"
# Natives: extract the vendored per-OS zips from libs/natives/ into the game dir.
python3 "$ROOT/tools/extract_natives.py" --out "$GAME/natives" >/dev/null
# Vendored Lunar UI bundle + textures (libs/lunar-assets/); writable state stays local.
for d in ui; do
  if [ -e "$LUNAR_ASSETS/$d" ] && [ ! -e "$DATA/$d" ]; then
    ln -sfn "$LUNAR_ASSETS/$d" "$DATA/$d"
  fi
done
# Older runs created a link to the (now removed) shared asset store; clean up
# dangling links so the client never sees a broken path.
if [ -L "$DATA/shared" ] && [ ! -e "$DATA/shared" ]; then
  rm -f "$DATA/shared"
fi
# The game identifies itself to the launcher with an installation id. Dev mode
# reads it from <dataDir>/launcher-cache/installation-id; generate one locally.
mkdir -p "$DATA/launcher-cache"
if [ ! -f "$DATA/launcher-cache/installation-id" ]; then
  python3 -c 'import uuid; print(uuid.uuid4())' > "$DATA/launcher-cache/installation-id"
fi

# Local patch jar: must come before every Lunar module jar so its resource and
# class overrides win. tools/build_localpatches.sh rebuilds it.
LOCALPATCH="$LIB/lunar-localpatches.jar"
[ -e "$LOCALPATCH" ] || bash "$ROOT/tools/build_localpatches.sh"

# Built-in fake launcher: the game speaks its IPC protocol to
# ws://127.0.0.1:<port>. Running it removes the "Connecting..." state and
# answers account/service RPCs locally.
#
# Ports must not collide with anything else that speaks this protocol: the
# official Lunar launcher also listens on 28190 while it runs, and a game that
# connects to it gets rejected ("Authentication required: Installation ID does
# not match") and can crash when opening UI screens. pick_port.py walks up from
# the default until it finds a free port; set LUNAR_IPC_PORT / LUNAR_ASSET_PORT
# / LUNAR_AUTH_PORT to force a specific one.
IPC_PORT="${LUNAR_IPC_PORT:-$(python3 "$ROOT/tools/pick_port.py" 28190)}"
ASSET_PORT="${LUNAR_ASSET_PORT:-$(python3 "$ROOT/tools/pick_port.py" $((IPC_PORT + 1)))}"
AUTH_PORT="${LUNAR_AUTH_PORT:-$(python3 "$ROOT/tools/pick_port.py" $((ASSET_PORT + 1)))}"
FAKE_PID=""
BACKEND_PID=""
GAME_PID=""
cleanup() {
  # Kill the game and the local fake services. Without this an interrupted run
  # leaves stale listeners behind, and the next launch either collides with
  # them or connects to a backend built from an old jar.
  kill "$GAME_PID" "$FAKE_PID" "$BACKEND_PID" 2>/dev/null || true
}
# EXIT alone is not enough: when timeout(1) or Ctrl-C sends SIGTERM the shell
# does not run it, so trap the signals as well.
trap cleanup EXIT
trap 'exit 143' TERM INT
if [ -f "$LIB/fake-launcher.jar" ]; then
  pkill -f 'FakeLauncher' 2>/dev/null || true
  pkill -f 'FakeBackend' 2>/dev/null || true
  sleep 0.5  # let the old sockets close before rebinding
  "$JAVA" -cp "$LIB/fake-launcher.jar:$MV/lunar.jar" FakeLauncher "$IPC_PORT" "$LOGS/fake-launcher.log" "$DATA/settings/game/accounts.json" \
      >/dev/null 2>&1 &
  FAKE_PID=$!
  # Local authenticator + asset server. The client is pointed at these with
  # the serviceOverride* system properties below; the asset connection going
  # READY is what stops the UI account chip from saying "Connecting...".
  "$JAVA" -cp "$LIB/fake-launcher.jar:$MV/lunar.jar" FakeBackend "$ASSET_PORT" "$AUTH_PORT" "$LOGS/fake-backend.log" "$DATA/settings/game/accounts.json" \
      "$ROOT/tools/fake-launcher/discovery.json" >/dev/null 2>&1 &
  BACKEND_PID=$!
  sleep 1
fi
# The launcher passes --uiDir as the UI bundle *hash* directory (the one that
# contains index.html), not its parent. WebOSR resolves file:/// requests by
# prefixing this root, so passing the parent makes every request 404.
#
# We prefer a local patched copy (tools/patch_ui.py) so the built-in alt
# manager panel is available; the read-only installed bundle is only a source.
if command -v python3 >/dev/null 2>&1 && [ -f "$ROOT/tools/patch_ui.py" ]; then
  python3 "$ROOT/tools/patch_ui.py" --out "$DATA/ui-local" >/dev/null 2>&1 || true
fi
UI_ROOT="$DATA/ui"
if [ -d "$DATA/ui-local" ]; then
  for d in "$DATA"/ui-local/*/; do
    if [ -f "${d}index.html" ]; then UI_ROOT="$DATA/ui-local"; break; fi
  done
fi
UI_BUNDLE=""
if [ -d "$UI_ROOT" ]; then
  for d in "$UI_ROOT"/*/; do
    if [ -f "${d}index.html" ]; then UI_BUNDLE="${d%/}"; break; fi
  done
fi
[ -n "$UI_BUNDLE" ] || UI_BUNDLE="$UI_ROOT"
# Lunar's textures (cosmetics, sprays, badges, jit index, ...): vendored copy.
if [ -d "$LUNAR_ASSETS/textures" ]; then
  if [ ! -e "$DATA/textures" ] || [ -z "$(find "$DATA/textures" -type f -print -quit 2>/dev/null)" ]; then
    rm -rf "$DATA/textures"
    ln -sfn "$LUNAR_ASSETS/textures" "$DATA/textures"
  fi
fi

# Forge: this harness used to always load Lunar's Forge module plus
# MinecraftForge 1.8.9, but Ichor never registers the forge module's mixin
# config (mixins.ichor.forge.v1_8.json) here, so Forge half-initialises
# (Loader.mccversion null) and creating a world dies in OptiFine's reflective
# call to FMLClientHandler.handleLoadingScreen ("this.client is null").
# Lunar's own vanilla 1.8.9 profile ships no Forge at all, so default to that
# and put Forge behind LUNAR_FORGE=1.
LUNAR_FORGE="${LUNAR_FORGE:-0}"

# The "patched" jars that used to live in libs/multiver/ were byte-identical
# copies of the multiver-full modules, so the module paths are used directly.
CP="$MC/versions/1.8.9/1.8.9.jar"
CP="$CP:$LOCALPATCH"
CP="$CP:$MV/genesis-0.1.0-SNAPSHOT-all.jar"
[ "$LUNAR_FORGE" = "1" ] && CP="$CP:$MV/forge-0.1.0-SNAPSHOT-all.jar"
# Debug-only shadow classes that log every WebOSR browser callback.
if [ "${LUNAR_DEBUG:-0}" = "1" ] && [ -e "$LIB/webosr-debug.jar" ]; then
  CP="$CP:$LIB/webosr-debug.jar"
fi
MODULE_JARS="lunar.jar common-0.1.0-SNAPSHOT-all-nomappings.jar legacy-0.1.0-SNAPSHOT-all-nomappings.jar \
            optifine-0.1.0-SNAPSHOT-all.jar \
            lunar-platform-mappings-v1_8.jar \
            OptiFine_v1_8.jar lunar-lang.jar"
if [ "$LUNAR_FORGE" = "1" ]; then
  MODULE_JARS="$MODULE_JARS lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar \
               forge-0.1.0-SNAPSHOT-all.jar Forge_v1_8.jar ReplayMod-v1_8-2.6.24.jar"
fi
for j in $MODULE_JARS; do
  [ -e "$MV/$j" ] && CP="$CP:$MV/$j"
done

for rel in \
  oshi-project/oshi-core/1.1/oshi-core-1.1.jar \
  com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar \
  net/sf/jopt-simple/jopt-simple/4.6/jopt-simple-4.6.jar \
  com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar \
  com/paulscode/codecwav/20101023/codecwav-20101023.jar \
  com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar \
  com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar \
  com/paulscode/soundsystem/20120107/soundsystem-20120107.jar \
  io/netty/netty-all/4.0.23.Final/netty-all-4.0.23.Final.jar \
  com/google/guava/guava/17.0/guava-17.0.jar \
  org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar \
  commons-io/commons-io/2.4/commons-io-2.4.jar \
  commons-codec/commons-codec/1.9/commons-codec-1.9.jar \
  net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar \
  net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar \
  com/google/code/gson/gson/2.2.4/gson-2.2.4.jar \
  com/mojang/authlib/1.5.21/authlib-1.5.21.jar \
  com/mojang/realms/1.7.59/realms-1.7.59.jar \
  org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar \
  org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar \
  commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar \
  org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar \
  org/lwjgl/lwjgl/lwjgl/2.9.4-nightly-20150209/lwjgl-2.9.4-nightly-20150209.jar \
  org/lwjgl/lwjgl/lwjgl_util/2.9.4-nightly-20150209/lwjgl_util-2.9.4-nightly-20150209.jar \
  org/lwjgl/lwjgl/lwjgl-platform/2.9.4-nightly-20150209/lwjgl-platform-2.9.4-nightly-20150209.jar \
  tv/twitch/twitch/6.5/twitch-6.5.jar \
  org/apache/logging/log4j/log4j-api/2.22.1/log4j-api-2.22.1.jar \
  org/apache/logging/log4j/log4j-core/2.22.1/log4j-core-2.22.1.jar \
  org/apache/logging/log4j/log4j-slf4j-impl/2.22.1/log4j-slf4j-impl-2.22.1.jar; do
  [ -e "$MC/libraries/$rel" ] && CP="$CP:$MC/libraries/$rel"
done
CP="$CP:$LIB/sentry-off.jar"

SYS_PROPS=(
  -Dlog4j2.formatMsgNoLookups=true
  "-Dichor.filteredGenesisSentries=.*lcqt.*|.*Some of your mods are incompatible with the game or each other.*"
  "-Dlunar.dataDir=$DATA"
  "-Dichor.fabric.localModPath=$DATA/profiles/1.8/mods"
  "-Dichor.usingIsolatedProfiles=true"
  "-Dichor.logsFile=$LOGS/ichor-boot.log"
  "-Djava.library.path=$GAME/natives"
  "-Dichor.prebakeClasses=false"
  "-Dlog4j.configurationFile=$LOGS/config.xml"
)
# LWJGL 2.9.4's XRandR display-mode path shells out to the `xrandr` CLI
# (org.lwjgl.opengl.XRandR.populate) and crashes the game with an
# ArrayIndexOutOfBoundsException when that binary is missing, because it then
# indexes an empty screen list (LinuxDisplay.getAvailableDisplayModes ->
# XRandR.getScreenNames()[0]). Wayland distros can ship XWayland without the
# xorg-xrandr package; in that case fall back to LWJGL's XF86VidMode extension,
# which XWayland advertises. With a real xrandr on PATH nothing changes.
if ! command -v xrandr >/dev/null 2>&1; then
  SYS_PROPS+=(-DLWJGL_DISABLE_XRANDR=true)
fi
# Point the removed Lunar backends at our local fakes. The client only reads
# these when it runs in dev build mode (production=false in lunarBuildData.txt).
SYS_PROPS+=(
  "-DserviceOverrideAuthenticator=ws://127.0.0.1:$AUTH_PORT"
  "-DserviceOverrideAssetServer=ws://127.0.0.1:$ASSET_PORT"
)
if [ "${LUNAR_DEBUG:-0}" = "1" ]; then
  SYS_PROPS+=(
    -Dlunar.webosr.debug=true
    -Dorg.lwjgl.util.Debug=true
    -Dorg.lwjgl.util.DebugLoader=true
  )
fi

# Account: a real (non-offline) account in the harness store makes the game
# launch with that Minecraft identity, so FakeBackend relays the Authenticator
# and AssetServer websockets to Lunar's official backend - that is what makes
# other Lunar users (tab icons, cosmetics, friends) visible. Import one from
# the official launcher's store with tools/import_lunar_account.py; otherwise
# the offline "Player" dev identity keeps everything local.
IFS=$'\t' read -r MC_USERNAME MC_UUID MC_TOKEN ACCOUNT_PREMIUM < <(python3 "$ROOT/tools/active_account.py" "$DATA")
[ -n "$MC_USERNAME" ] || MC_USERNAME=Player
[ -n "$MC_UUID" ] || MC_UUID=00000000000000000000000000000000
[ -n "$MC_TOKEN" ] || MC_TOKEN=0
ACCOUNT_PREMIUM="${ACCOUNT_PREMIUM:-0}"
if [ "$ACCOUNT_PREMIUM" = "1" ]; then
  echo "[run-lunar] account: $MC_USERNAME (premium) - auth/asset relay to Lunar's official backend"
else
  echo "[run-lunar] account: $MC_USERNAME (offline) - cosmetics/discovery served locally"
fi

# Modules Ichor loads for the game. Keep this in sync with MODULE_JARS above;
# Forge and ReplayMod only take part in a LUNAR_FORGE=1 launch.
ICHOR_CP="lunar-localpatches.jar,sentry-off.jar,lunar.jar,common-0.1.0-SNAPSHOT-all-nomappings.jar,legacy-0.1.0-SNAPSHOT-all-nomappings.jar,optifine-0.1.0-SNAPSHOT-all.jar"
if [ "$LUNAR_FORGE" = "1" ]; then
  ICHOR_CP="$ICHOR_CP,lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar,forge-0.1.0-SNAPSHOT-all.jar"
fi
ICHOR_CP="$ICHOR_CP,genesis-0.1.0-SNAPSHOT-all.jar,lunar-lang.jar"
ICHOR_EXTERNAL="OptiFine_v1_8.jar,kill-sound-chat-patterns.json,vanilla_capes.json,waypoint-patterns.json,user-message-patterns.json,tier-tagger.json"
if [ "$LUNAR_FORGE" = "1" ]; then
  ICHOR_EXTERNAL="Forge_v1_8.jar,ReplayMod-v1_8-2.6.24.jar,$ICHOR_EXTERNAL"
fi

"$JAVA" -Xmx"$MEM" "${SYS_PROPS[@]}" \
  -cp "$CP" com.moonsworth.lunar.genesis.Genesis \
  --version 1.8.9 \
  --classpathDir "$LIB/multiver-full" \
  --workingDirectory "$GAME" \
  --texturesDir "$DATA/textures" \
  --webosrDir "$GAME/natives" \
  --jitDir "$DATA/jit" \
  --uiDir "$UI_BUNDLE" \
  --ipcPort "$IPC_PORT" \
  --ichorClassPath "$ICHOR_CP" \
  --ichorExternalFiles "$ICHOR_EXTERNAL" \
  --installationId local-dev \
  --gameDir "$GAME" \
  --assetsDir "$GAME/assets" \
  --assetIndex 1.8 \
  --username "$MC_USERNAME" \
  --uuid "$MC_UUID" \
  --accessToken "$MC_TOKEN" \
  --userType legacy \
  "$@" &
GAME_PID=$!
wait "$GAME_PID"
STATUS=$?
exit $STATUS
