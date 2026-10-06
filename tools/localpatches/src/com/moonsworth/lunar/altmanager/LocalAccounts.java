package com.moonsworth.lunar.altmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

/**
 * Reads/writes the client's own account store,
 * {@code <lunar.dataDir>/settings/game/accounts.json}.
 *
 * The game's AccountManager loads this file at startup and after every
 * {@code IH HCOCOCIOHIICIRCOHCIROOCIRCCC()} (reload) call, so writing an entry
 * here and reloading makes it appear in the React account switcher and makes
 * it selectable.
 *
 * The schema mirrors the game's writer:
 * <pre>
 * {
 *   "activeAccountLocalId": "&lt;localId&gt;",
 *   "accounts": {
 *     "&lt;localId&gt;": {
 *       "accessToken": "...", "accessTokenExpiresAt": "2027-...Z",
 *       "eligibleForMigration": false, "hasMultipleProfiles": false,
 *       "legacy": false, "persistent": true, "userProperites": [],
 *       "minecraftProfile": {"id": "&lt;uuidNoDash&gt;", "name": "&lt;name&gt;"},
 *       "localId": "&lt;uuidNoDash&gt;", "refreshToken": "...",
 *       "remoteId": "&lt;uuidNoDash&gt;", "type": "Xbox", "username": "&lt;name&gt;"
 *     }
 *   }
 * }
 * </pre>
 */
public final class LocalAccounts {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private LocalAccounts() {
    }

    public static Path storePath() {
        String dataDir = System.getProperty("lunar.dataDir",
                System.getProperty("user.home") + "/.lunarclient");
        return Paths.get(dataDir, "settings", "game", "accounts.json");
    }

    public static synchronized JsonObject readStore() {
        Path path = storePath();
        if (!Files.isRegularFile(path)) {
            JsonObject empty = new JsonObject();
            empty.add("accounts", new JsonObject());
            return empty;
        }
        try {
            String text = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            JsonObject root = new JsonParser().parse(text).getAsJsonObject();
            if (!root.has("accounts") || !root.get("accounts").isJsonObject()) {
                root.add("accounts", new JsonObject());
            }
            return root;
        } catch (Exception exception) {
            System.err.println("[altmanager] failed to read " + path + ": " + exception);
            JsonObject empty = new JsonObject();
            empty.add("accounts", new JsonObject());
            return empty;
        }
    }

    public static synchronized void writeStore(JsonObject root) {
        Path path = storePath();
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, GSON.toJson(root).getBytes(StandardCharsets.UTF_8));
        } catch (IOException exception) {
            throw new RuntimeException("failed to write " + path, exception);
        }
    }

    /** Adds a cracked/offline account and returns its 32-char uuid. */
    public static synchronized String addOffline(String username) {
        if (username == null || !username.matches("[A-Za-z0-9_]{3,16}")) {
            throw new IllegalArgumentException("username must be 3-16 chars of [A-Za-z0-9_]");
        }
        UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8));
        String uuid32 = uuid.toString().replace("-", "");
        JsonObject account = accountEntry(username, uuid32, uuid32,
                offlineToken(username), "local-offline-" + uuid32, "Xbox");
        return put(account, uuid32);
    }

    /** Adds an already-authenticated (premium) account. */
    public static synchronized String addPremium(String username, String uuid32,
                                                 String accessToken, String refreshToken) {
        JsonObject account = accountEntry(username, uuid32, uuid32, accessToken, refreshToken, "Xbox");
        return put(account, uuid32);
    }

    private static String put(JsonObject account, String localId) {
        JsonObject root = readStore();
        JsonObject accounts = root.getAsJsonObject("accounts");
        accounts.add(localId, account);
        root.addProperty("activeAccountLocalId", localId);
        writeStore(root);
        return account.get("remoteId").getAsString();
    }

    private static JsonObject accountEntry(String username, String uuid32, String localId,
                                           String accessToken, String refreshToken, String type) {
        JsonObject account = new JsonObject();
        account.addProperty("accessToken", accessToken);
        account.addProperty("accessTokenExpiresAt",
                Instant.now().plus(7, ChronoUnit.DAYS).toString());
        // account compliance block (base Account.load reads these unconditionally)
        account.addProperty("eligibleForMigration", false);
        account.addProperty("hasMultipleProfiles", false);
        account.addProperty("legacy", false);
        account.addProperty("persistent", true);
        account.add("userProperites", new com.google.gson.JsonArray());
        JsonObject profile = new JsonObject();
        profile.addProperty("id", uuid32);
        profile.addProperty("name", username);
        account.add("minecraftProfile", profile);
        account.addProperty("localId", localId);
        account.addProperty("refreshToken", refreshToken);
        account.addProperty("remoteId", uuid32);
        account.addProperty("type", type);
        account.addProperty("username", username);
        return account;
    }

    /**
     * A syntactically valid JWT with iat/exp so the client's token-age parsing
     * (used for the "last used" sort) works for offline accounts.
     */
    private static String offlineToken(String username) {
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String header = encoder.encodeToString(
                "{\"alg\":\"none\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        long iat = System.currentTimeMillis() / 1000L;
        long exp = iat + 7L * 24L * 3600L;
        String payload = encoder.encodeToString(
                ("{\"sub\":\"" + username + "\",\"iat\":" + iat + ",\"exp\":" + exp + "}")
                        .getBytes(StandardCharsets.UTF_8));
        return header + "." + payload + ".offline";
    }
}
