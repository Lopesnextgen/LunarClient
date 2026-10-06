#!/usr/bin/env bash
# ============================================================================
# Lunar Client 1.8.9 — local launch harness
#
# Launches the deobfuscated client from this Maven/MCP workspace using the
# original Lunar bootstrap (genesis/ichor modules) with our compiled classes
# shadowing the original ones.
# ============================================================================
set -u

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"
LIB="$ROOT/libs"
MV="$LIB/multiver-full"
. "$(cd "$(dirname "$0")" && pwd)/java_env.sh"
JAVA_HOME="$(lunar_java_home)"
export JAVA_HOME
JAVA="$JAVA_HOME/bin/java"
MEM="${LUNAR_MEM:-3072M}"

GAME="${LUNAR_GAME:-$ROOT/run/game}"
DATA="${LUNAR_DATA:-$ROOT/run/lunarclient}"
LOGS="$ROOT/run/logs"
mkdir -p "$GAME" "$DATA" "$LOGS" "$DATA/profiles/1.8/mods"

# assets + natives from the vendored copies
[ -e "$GAME/assets" ]  || ln -sfn "$LIB/vanilla/assets" "$GAME/assets"
python3 "$ROOT/tools/extract_natives.py" --out "$GAME/natives" >/dev/null

# ---- classpath, in precedence order (first wins) ---------------------------
# 1. original loader/bootstrap classes must come first: the official launcher
#    ships these as-is, and our decompiled copies do not implement the exact
#    reflective contract the loader expects.
# 2. our compiled sources + fixed resources
# 3. libraries (renamed Lunar classes not compiled in src/main)
CP=""
# Sentry-neutralising shadow classes must win over every other jar copy.
[ -e "$LIB/sentry-off.jar" ] && CP="$LIB/sentry-off.jar:"
for j in genesis-patched.jar forge-patched.jar; do
  [ -e "$LIB/multiver/$j" ] && CP="$CP$LIB/multiver/$j:"
done
CP="$CP$ROOT/src/main/resources:$ROOT/target/classes:$LIB/lunar-renamed-classes.jar"
# modern ASM must shadow the older one bundled in genesis
[ -e "$LIB/asm-9.7.1-all.jar" ] && CP="$CP:$LIB/asm-9.7.1-all.jar"
# original Lunar jar as a fallback for any obfuscated names still referenced
[ -e "$MV/lunar.jar" ] && CP="$CP:$MV/lunar.jar"
[ -e "$MV/legacy-0.1.0-SNAPSHOT-all-nomappings.jar" ] && CP="$CP:$MV/legacy-0.1.0-SNAPSHOT-all-nomappings.jar"
for j in genesis-0.1.0-SNAPSHOT-all.jar common-0.1.0-SNAPSHOT-all-nomappings.jar \
         optifine-0.1.0-SNAPSHOT-all.jar \
         lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar forge-0.1.0-SNAPSHOT-all.jar \
         lunar-platform-mappings-v1_8.jar \
         Forge_v1_8.jar OptiFine_v1_8.jar ReplayMod-v1_8-2.6.24.jar lunar-lang.jar; do
  [ -e "$MV/$j" ] && CP="$CP:$MV/$j"
done
# legacy module (libs/multiver/legacy-patched.jar was an identical copy)
CP="$CP:$MV/legacy-0.1.0-SNAPSHOT-all-nomappings.jar"

# Official Minecraft 1.8.9 libraries (authlib, lwjgl, guava, ...). Appended
# last so Lunar's bundled copies win; these only fill gaps such as authlib,
# which Ichor needs while remapping game classes. Defaults to the packaged
# runtime (libs/vanilla); set LUNAR_MINECRAFT_DIR (or LUNAR_MC) to point at an
# installed .minecraft instead, e.g.:
#   LUNAR_MINECRAFT_DIR="$HOME/.var/app/com.lunarclient.LunarClient/.minecraft"
MINECRAFT_DIR="${LUNAR_MC:-${LUNAR_MINECRAFT_DIR:-$LIB/vanilla}}"
if [ -d "$MINECRAFT_DIR/libraries" ]; then
  while IFS= read -r rel; do
    [ -n "$rel" ] && [ -e "$MINECRAFT_DIR/libraries/$rel" ] && CP="$CP:$MINECRAFT_DIR/libraries/$rel"
  done <<'LIBS'
oshi-project/oshi-core/1.1/oshi-core-1.1.jar
com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar
net/sf/jopt-simple/jopt-simple/4.6/jopt-simple-4.6.jar
com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar
com/paulscode/codecwav/20101023/codecwav-20101023.jar
com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar
com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar
com/paulscode/soundsystem/20120107/soundsystem-20120107.jar
io/netty/netty-all/4.0.23.Final/netty-all-4.0.23.Final.jar
com/google/guava/guava/17.0/guava-17.0.jar
org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar
commons-io/commons-io/2.4/commons-io-2.4.jar
commons-codec/commons-codec/1.9/commons-codec-1.9.jar
net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar
net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar
com/google/code/gson/gson/2.2.4/gson-2.2.4.jar
com/mojang/authlib/1.5.21/authlib-1.5.21.jar
com/mojang/realms/1.7.59/realms-1.7.59.jar
org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar
org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar
commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar
org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar
org/lwjgl/lwjgl/lwjgl/2.9.4-nightly-20150209/lwjgl-2.9.4-nightly-20150209.jar
org/lwjgl/lwjgl/lwjgl_util/2.9.4-nightly-20150209/lwjgl_util-2.9.4-nightly-20150209.jar
org/lwjgl/lwjgl/lwjgl-platform/2.9.4-nightly-20150209/lwjgl-platform-2.9.4-nightly-20150209.jar
tv/twitch/twitch/6.5/twitch-6.5.jar
org/apache/logging/log4j/log4j-api/2.22.1/log4j-api-2.22.1.jar
org/apache/logging/log4j/log4j-core/2.22.1/log4j-core-2.22.1.jar
org/apache/logging/log4j/log4j-slf4j-impl/2.22.1/log4j-slf4j-impl-2.22.1.jar
LIBS
fi

SYS_PROPS=(
  "-Dlog4j2.formatMsgNoLookups=true"
  "-Dichor.filteredGenesisSentries=.*lcqt.*|.*Some of your mods are incompatible with the game or each other.*"
  "-Dlunar.dataDir=$DATA"
  "-Dichor.fabric.localModPath=$DATA/profiles/1.8/mods"
  "-Dichor.usingIsolatedProfiles=true"
  "-Dichor.logsFile=$LOGS/ichor-boot.log"
  "-Djava.library.path=$GAME/natives"
  "-Dichor.prebakeClasses=false"
  "-Dlog4j.configurationFile=$LOGS/config.xml"
)

[ -f "$LOGS/config.xml" ] || cat > "$LOGS/config.xml" <<'XML'
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
  <Appenders>
    <Console name="SysOut" target="SYSTEM_OUT">
      <PatternLayout pattern="[%d{HH:mm:ss}] [%t/%level]: %msg%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="info"><AppenderRef ref="SysOut"/></Root>
  </Loggers>
</Configuration>
XML

echo "=== launching (game dir: $GAME) ==="
exec "$JAVA" -Xmx"$MEM" "${SYS_PROPS[@]}" -cp "$CP" \
  com.moonsworth.lunar.genesis.Genesis \
  --version 1.8.9 \
  --classpathDir "$ROOT/libs/multiver-full" \
  --workingDirectory "$GAME" \
  --ichorClassPath "sentry-off.jar,lunar.jar,common-0.1.0-SNAPSHOT-all-nomappings.jar,legacy-0.1.0-SNAPSHOT-all-nomappings.jar,optifine-0.1.0-SNAPSHOT-all.jar,lunar-replaymod-forge-mixins-0.1.0-SNAPSHOT-all.jar,forge-0.1.0-SNAPSHOT-all.jar,genesis-0.1.0-SNAPSHOT-all.jar,lunar-lang.jar" \
  --ichorExternalFiles "Forge_v1_8.jar,ReplayMod-v1_8-2.6.24.jar,OptiFine_v1_8.jar,kill-sound-chat-patterns.json,vanilla_capes.json,waypoint-patterns.json,user-message-patterns.json,tier-tagger.json" \
  --installationId local-dev \
  --gameDir "$GAME" \
  --assetsDir "$GAME/assets" \
  --assetIndex 1.8 \
  --username Player \
  --uuid 00000000000000000000000000000000 \
  --accessToken 0 \
  --userType legacy \
  "$@"
