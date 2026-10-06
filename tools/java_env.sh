#!/usr/bin/env bash
# ============================================================================
# Resolve a JDK/Maven without hardcoding any user path.
#
# Order: $JAVA_HOME -> $HOME/.sdkman/candidates/java/{current,*} -> java on PATH.
# Source this file and call lunar_java_home / lunar_maven_bin.
# ============================================================================

lunar_java_home() {
  if [ -n "${JAVA_HOME:-}" ] && [ -x "${JAVA_HOME}/bin/java" ]; then
    printf '%s\n' "$JAVA_HOME"
    return 0
  fi
  # Preferred sdkman versions first (callers may pass their own list).
  for pref in "$@" 21.0.12-amzn 17.0.20.fx-zulu 17.0.19.fx-zulu current; do
    cand="$HOME/.sdkman/candidates/java/$pref"
    if [ -x "${cand}/bin/java" ]; then
      printf '%s\n' "$cand"
      return 0
    fi
  done
  if [ -d "$HOME/.sdkman/candidates/java" ]; then
    for c in "$HOME"/.sdkman/candidates/java/*; do
      if [ -x "${c}/bin/java" ]; then
        printf '%s\n' "$c"
        return 0
      fi
    done
  fi
  if command -v java >/dev/null 2>&1; then
    j="$(command -v java)"
    j="$(readlink -f -- "$j" 2>/dev/null || printf '%s' "$j")"
    printf '%s\n' "$(dirname "$(dirname "$j")")"
    return 0
  fi
  echo "[java_env] no JDK found; set JAVA_HOME" >&2
  return 1
}

lunar_maven_bin() {
  if command -v mvn >/dev/null 2>&1; then
    command -v mvn
    return 0
  fi
  if [ -x "$HOME/.sdkman/candidates/maven/current/bin/mvn" ]; then
    printf '%s\n' "$HOME/.sdkman/candidates/maven/current/bin/mvn"
    return 0
  fi
  echo "[java_env] no Maven found; set MAVEN_HOME or put mvn on PATH" >&2
  return 1
}
