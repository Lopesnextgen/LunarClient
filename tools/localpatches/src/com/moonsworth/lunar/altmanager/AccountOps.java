package com.moonsworth.lunar.altmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Reflection bridge to the game's AccountManager.
 *
 * The runtime jar contains class/package name clashes (e.g. the client class
 * {@code com.moonsworth.lunar.client.HORHROIOIOICIRHIOCOICHHHIHCIIO} is both a
 * class and a package prefix), so javac cannot compile against those types.
 * Everything here goes through reflection using the runtime names instead.
 */
public final class AccountOps {

    private static final String BRIDGE_CLASS = "com.moonsworth.lunar.client.util.OIHCCIIRIOORHHOOICRCIORCOICOIR";
    private static final String CLIENT_CLASS = "com.moonsworth.lunar.client.HORHROIOIOICIRHIOCOICHHHIHCIIO";

    private AccountOps() {
    }

    /**
     * Starts a daemon that waits for the client to finish booting and then
     * connects the Authenticator/AssetServer for the launch session, so the
     * account chip leaves the "Connecting..." state without user interaction.
     */
    public static void startWarmup() {
        System.out.println("[altmanager] warmup thread started");
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 120; i++) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    return;
                }
                try {
                    Class<?> clientClass = Class.forName(CLIENT_CLASS);
                    Object client = clientClass.getMethod("OHHHIIIRCIORROIOIOHIHHCOHORIII").invoke(null);
                    if (client == null) {
                        continue;
                    }
                    // wait for the game to be in the menus (a session exists)
                    Object minecraft = minecraft();
                    Object session = minecraft.getClass().getMethod("bridge$getSession").invoke(minecraft);
                    if (session == null) {
                        continue;
                    }
                    Thread.sleep(3000L);
                    if ("1".equals(System.getProperty("lunar.altmanager.selftest"))) {
                        try {
                            String uuid = LocalAccounts.addOffline("SelfTestAlt");
                            reload();
                            System.out.println("[altmanager] selftest list=" + listJson());
                            System.out.println("[altmanager] selftest select=" + selectLocal(uuid));
                        } catch (Throwable selftestFailure) {
                            System.err.println("[altmanager] selftest failed: " + selftestFailure);
                        }
                    }
                    triggerAssetConnect();
                    return;
                } catch (Throwable ignored) {
                    // keep waiting; the client may still be constructing
                }
            }
        }, "lunar-altmanager-warmup");
        thread.setDaemon(true);
        thread.start();
    }

    private static Object bridge() throws Exception {
        return Class.forName(BRIDGE_CLASS);
    }

    /** The Minecraft bridge object (getSession/getWorld/getCurrentScreen/...). */
    private static Object minecraft() throws Exception {
        return Class.forName(BRIDGE_CLASS)
                .getMethod("IOIIHCIOOCRIIOHRIHCCRIIHOHIOIC").invoke(null);
    }

    private static Object client() throws Exception {
        Class<?> clientClass = Class.forName(CLIENT_CLASS);
        return clientClass.getMethod("OHHHIIIRCIORROIOIOHIHHCOHORIII").invoke(null);
    }

    private static Object manager() throws Exception {
        Object client = client();
        return client.getClass().getMethod("CCOICIOICCCHRIIIRIROOORORRHCRO").invoke(client);
    }

    /** True when the client is sitting on the main menu (no world, no GUI). */
    public static boolean isMainMenu() {
        try {
            Object minecraft = minecraft();
            Object world = minecraft.getClass().getMethod("bridge$getWorld").invoke(minecraft);
            if (world != null) {
                return false;
            }
            Object screen = minecraft.getClass().getMethod("bridge$getCurrentScreen").invoke(minecraft);
            if (screen == null) {
                return true;
            }
            String name = screen.getClass().getSimpleName();
            return name.contains("MainMenu") || name.contains("Gui2");
        } catch (Throwable throwable) {
            return false;
        }
    }

    /** AccountManager.IHHCOCOCIOHIICIRCOHCIROOCIRCCC() - reload accounts.json. */
    public static void reload() {
        try {
            Object manager = manager();
            manager.getClass().getMethod("IHHCOCOCIOHIICIRCOHCIROOCIRCCC").invoke(manager);
        } catch (Exception exception) {
            System.err.println("[altmanager] account reload failed: " + exception);
        }
    }

    /** Reload on the game thread (account UI state must be touched there). */
    public static void reloadAsync() {
        try {
            Object minecraft = minecraft();
            Method submit = minecraft.getClass().getMethod("bridge$submit", Runnable.class);
            submit.invoke(minecraft, (Runnable) AccountOps::reload);
        } catch (Exception exception) {
            reload();
        }
    }

    /** JSON: {mainMenu: bool} for the UI panel's visibility gate. */
    public static String gameContextJson() {
        JsonObject json = new JsonObject();
        json.addProperty("mainMenu", isMainMenu());
        return json.toString();
    }

    /** JSON array of {uuid, username, type, invalid} for the alt manager panel. */
    public static String listJson() {
        JsonArray array = new JsonArray();
        try {
            Map<?, ?> accounts = accounts();
            for (Object account : accounts.values()) {
                JsonObject json = new JsonObject();
                json.addProperty("uuid", profileId(account));
                json.addProperty("username", username(account));
                json.addProperty("type", accountType(account));
                json.addProperty("invalid", isInvalid(account));
                array.add(json);
            }
        } catch (Exception exception) {
            System.err.println("[altmanager] listAccounts failed: " + exception);
        }
        return array.toString();
    }

    /** Selects the account, applies its session locally and reconnects assets. */
    public static String selectLocal(String uuid) {
        try {
            Object account = find(uuid);
            if (account == null) {
                return null;
            }
            Object manager = manager();
            for (Method method : manager.getClass().getMethods()) {
                if (!method.getName().equals("HORHROIOIOICIRHIOCOICHHHIHCIIO")) {
                    continue;
                }
                if (method.getParameterCount() != 1) {
                    continue;
                }
                if (!method.getParameterTypes()[0].isAssignableFrom(account.getClass())) {
                    continue;
                }
                method.invoke(manager, account);
                triggerAssetConnect();
                return profileId(account);
            }
            return null;
        } catch (Exception exception) {
            System.err.println("[altmanager] selectAccount failed: " + exception);
            return null;
        }
    }

    /**
     * Kicks the client's Authenticator -> AssetServer connection for the
     * current session. The stock client only does this from its internal
     * session-change path, which never fires when the game is launched without
     * Lunar's launcher, so the UI would stay on "Connecting..." forever.
     */
    public static void triggerAssetConnect() {
        try {
            Class<?> clientClass = Class.forName(CLIENT_CLASS);
            Object client = clientClass.getMethod("OHHHIIIRCIORROIOIOHIHHCOHORIII").invoke(null);
            if (client == null) {
                return;
            }
            Object minecraft = minecraft();
            Object session = minecraft.getClass().getMethod("bridge$getSession").invoke(minecraft);
            if (session == null) {
                return;
            }
            for (Method method : clientClass.getMethods()) {
                if (!method.getName().equals("HORHROIOIOICIRHIOCOICHHHIHCIIO")) {
                    continue;
                }
                if (method.getParameterCount() != 1) {
                    continue;
                }
                if (!method.getParameterTypes()[0].getName()
                        .equals("com.moonsworth.lunar.client.HHRROIIHRRICIIHIIHICRHHRHOHHOO.RCIOICOHRIOIIRRRROCRHCIICRROHO")) {
                    continue;
                }
                method.invoke(client, (Object) null);
                System.out.println("[altmanager] triggered asset server connect");
                return;
            }
        } catch (Exception exception) {
            System.err.println("[altmanager] asset connect trigger failed: " + exception);
            try {
                Class<?> clientClass = Class.forName(CLIENT_CLASS);
                Object client = clientClass.getMethod("OHHHIIIRCIORROIOIOHIHHCOHORIII").invoke(null);
                System.err.println("[altmanager] client runtime class=" + client.getClass().getName());
                int shown = 0;
                for (Method method : client.getClass().getMethods()) {
                    if (method.getParameterCount() != 0 || shown >= 200) {
                        continue;
                    }
                    System.err.println("[altmanager]   " + method.getReturnType().getSimpleName()
                            + " " + method.getName() + "()");
                    shown++;
                }
            } catch (Throwable diagnosticFailure) {
                System.err.println("[altmanager] diagnostic failed: " + diagnosticFailure);
            }
        }
    }

    private static Map<?, ?> accounts() throws Exception {
        Object manager = manager();
        return (Map<?, ?>) manager.getClass().getMethod("IORHHHROCRRHORHRCHCCHHIHICCRCO").invoke(manager);
    }

    private static Object find(String uuid) throws Exception {
        String wanted = uuid == null ? "" : uuid.replaceAll("-", "");
        for (Object account : accounts().values()) {
            if (wanted.equalsIgnoreCase(profileId(account))) {
                return account;
            }
        }
        return null;
    }

    private static String username(Object account) {
        try {
            return (String) account.getClass().getMethod("getUsername").invoke(account);
        } catch (Exception exception) {
            return "";
        }
    }

    private static String profileId(Object account) {
        try {
            Object profile = account.getClass().getMethod("HOOOHHOHROCORRHCICHIOCIIOIOHIR").invoke(account);
            return (String) profile.getClass().getMethod("getId").invoke(profile);
        } catch (Exception exception) {
            return "";
        }
    }

    private static String accountType(Object account) {
        try {
            Object type = account.getClass().getMethod("IHROCOORHOCRRHRHRROCIOORIHIROC").invoke(account);
            return String.valueOf(type.getClass().getMethod("getFormatted").invoke(type));
        } catch (Exception exception) {
            return "";
        }
    }

    private static boolean isInvalid(Object account) {
        try {
            return (Boolean) account.getClass().getMethod("isInvalid").invoke(account);
        } catch (Exception exception) {
            return false;
        }
    }
}
