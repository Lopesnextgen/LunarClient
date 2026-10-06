package com.moonsworth.lunar.ichor;

/**
 * Local stub for the dev-mode "ProfileData" class that the stock runtime does
 * not ship.
 *
 * When the client runs with production=false (see lunarBuildData.txt in this
 * jar) its constructor calls:
 *
 *   this.getClass().getClassLoader().getClass().getClassLoader()
 *       .loadClass("com.moonsworth.lunar.ichor.ProfileData")
 *       .getDeclaredMethod("log").invoke(null);
 *
 * Without this class the client dies during startup with
 * ClassNotFoundException. The real implementation logs boot profile data; the
 * stub also starts the local alt manager's asset-connect warm-up thread, which
 * is the first code of ours guaranteed to run in the game JVM.
 */
public final class ProfileData {

    private ProfileData() {
    }

    /** Called once at the end of client construction in dev builds. */
    public static void log() {
        com.moonsworth.lunar.altmanager.AccountOps.startWarmup();
    }
}
