import com.google.protobuf.ByteString;
import com.lunarclient.gameipc.protocol.v1.GameboundIPCMessage;
import com.lunarclient.gameipc.protocol.v1.IPCRpcResponse;
import com.lunarclient.websocket.protocol.v1.ServerboundWebSocketMessage;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

/**
 * Minimal local "launcher" speaking Lunar's game IPC protocol.
 *
 * The game connects as a websocket client to ws://127.0.0.1:<ipcPort> and
 * sends ServerboundWebSocketMessage protobuf RPCs; we answer with
 * GameboundIPCMessage / IPCRpcResponse envelopes. This removes the
 * "Connecting..." state and lets the UI work without Lunar's launcher.
 *
 * Account RPCs (AuthService):
 *   AddAccount       - creates a cracked account in accounts.json and reports
 *                      success, mirroring what the real launcher does (the game
 *                      then reloads its account list)
 *   RefreshAccount   - reported successful (offline sessions do not expire)
 *   NotifySwitchAccount / OpenMicrosoftPopup - harmless defaults
 *
 * Every call is logged so behaviour can be inspected.
 */
public final class FakeLauncher extends WebSocketServer {
    private final PrintWriter log;
    private final Path accountsFile;
    private final Random random = new Random();

    public FakeLauncher(int port, Path logFile, Path accountsFile) throws IOException {
        super(new InetSocketAddress("127.0.0.1", port));
        Files.createDirectories(logFile.getParent());
        this.log = new PrintWriter(new FileWriter(logFile.toFile(), true), true);
        this.accountsFile = accountsFile;
    }

    private void line(String text) {
        String msg = "[" + LocalTime.now().withNano(0) + "] " + text;
        System.out.println(msg);
        this.log.println(msg);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String handshakeHeader = handshake.getFieldValue("lc-handshake");
        line("connection from " + conn.getRemoteSocketAddress() + " handshake=" + (handshakeHeader == null ? "<none>" : handshakeHeader));
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        line("closed: " + code + " " + reason + " remote=" + remote);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        line("text message: " + message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer buffer) {
        ServerboundWebSocketMessage request;
        try {
            request = ServerboundWebSocketMessage.parseFrom(buffer);
        } catch (Exception exception) {
            line("UNPARSEABLE (" + buffer.remaining() + " bytes): " + exception);
            return;
        }
        String service = request.getService();
        String method = request.getMethod();
        byte[] input = request.getInput().toByteArray();
        line("RPC " + service + "." + method + " id=" + request.getRequestId().toStringUtf8()
                + " in=" + input.length + "B " + preview(input));

        ByteString output = respond(service, method, request.getInput());
        IPCRpcResponse response = IPCRpcResponse.newBuilder()
                .setRequestId(request.getRequestId())
                .setOutput(output)
                .build();
        GameboundIPCMessage envelope = GameboundIPCMessage.newBuilder().setRpcResponse(response).build();
        conn.send(envelope.toByteArray());
    }

    private ByteString respond(String service, String method, ByteString input) {
        try {
            boolean auth = service != null && service.contains("auth");
            if (method.equalsIgnoreCase("addAccount")) {
                String username = createCrackedAccount();
                if (username != null) {
                    line("addAccount -> created cracked account " + username);
                }
                return com.lunarclient.gameipc.auth.v1.AddAccountResponse.newBuilder()
                        .setSuccess(true).build().toByteString();
            }
            if (method.equalsIgnoreCase("refreshAccount")) {
                return com.lunarclient.gameipc.auth.v1.RefreshAccountResponse.newBuilder()
                        .setSuccess(true).build().toByteString();
            }
            if (method.equalsIgnoreCase("openMicrosoftPopup")) {
                return com.lunarclient.gameipc.auth.v1.OpenMicrosoftPopupResponse.newBuilder()
                        .setStatus(com.lunarclient.gameipc.auth.v1.OpenMicrosoftPopupResponse.Status.STATUS_CLOSED_WITH_NO_URL)
                        .setUrl("").build().toByteString();
            }
            if (method.equalsIgnoreCase("notifySwitchAccount")) {
                return com.lunarclient.gameipc.auth.v1.NotifySwitchAccountResponse.getDefaultInstance().toByteString();
            }
            if (auth) {
                // unknown auth method: empty (protobuf default) response
                return ByteString.EMPTY;
            }
        } catch (Throwable throwable) {
            line("response error for " + service + "." + method + ": " + throwable);
        }
        return ByteString.EMPTY;
    }

    /**
     * Creates one offline account when the store is empty. Without an account
     * the game never connects to the authenticator/asset server (the account
     * chip stays on "Connecting...", cosmetics and Discover stay empty), so
     * seed "Player" - the same name the launcher passes on the command line.
     */
    void ensureAccount() {
        if (accountsFile == null) {
            return;
        }
        try {
            if (readJson().getAsJsonObject("accounts").size() > 0) {
                return;
            }
            String username = createCrackedAccount("Player");
            if (username != null) {
                line("seeded offline account " + username + " (account store was empty)");
            }
        } catch (Throwable throwable) {
            line("failed to seed offline account: " + throwable);
        }
    }

    /**
     * Fallback for the account chip's stock "Add account" button: writes a
     * random cracked account to accounts.json. Returns the username or null.
     */
    private String createCrackedAccount() {
        return createCrackedAccount("Alt" + String.format("%06X", random.nextInt(0x1000000)));
    }

    private String createCrackedAccount(String username) {
        if (accountsFile == null) {
            return null;
        }
        try {
            UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8));
            String uuid32 = uuid.toString().replace("-", "");
            String localId = uuid32;

            com.google.gson.JsonObject root = readJson();
            com.google.gson.JsonObject accounts = root.getAsJsonObject("accounts");
            com.google.gson.JsonObject account = new com.google.gson.JsonObject();
            account.addProperty("accessToken", offlineToken(username));
            account.addProperty("accessTokenExpiresAt", java.time.Instant.now().plus(7, java.time.temporal.ChronoUnit.DAYS).toString());
            account.addProperty("eligibleForMigration", false);
            account.addProperty("hasMultipleProfiles", false);
            account.addProperty("legacy", false);
            account.addProperty("persistent", true);
            account.add("userProperites", new com.google.gson.JsonArray());
            com.google.gson.JsonObject profile = new com.google.gson.JsonObject();
            profile.addProperty("id", uuid32);
            profile.addProperty("name", username);
            account.add("minecraftProfile", profile);
            account.addProperty("localId", localId);
            account.addProperty("refreshToken", "local-offline-" + uuid32);
            account.addProperty("remoteId", uuid32);
            account.addProperty("type", "Xbox");
            account.addProperty("username", username);
            accounts.add(localId, account);
            root.addProperty("activeAccountLocalId", localId);
            writeJson(root);
            return username;
        } catch (Throwable throwable) {
            line("failed to create cracked account: " + throwable);
            return null;
        }
    }

    private com.google.gson.JsonObject readJson() throws IOException {
        if (!Files.isRegularFile(accountsFile)) {
            return emptyStore();
        }
        String text = new String(Files.readAllBytes(accountsFile), StandardCharsets.UTF_8);
        if (text.isBlank()) {
            // The game creates a 0-byte accounts.json before the first account
            // exists; treat it as an empty store instead of failing to parse.
            return emptyStore();
        }
        com.google.gson.JsonObject root;
        try {
            root = new com.google.gson.JsonParser().parse(text).getAsJsonObject();
        } catch (Throwable throwable) {
            line("accounts.json is not a JSON object, starting a fresh store: " + throwable);
            return emptyStore();
        }
        if (!root.has("accounts") || !root.get("accounts").isJsonObject()) {
            root.add("accounts", new com.google.gson.JsonObject());
        }
        return root;
    }

    private static com.google.gson.JsonObject emptyStore() {
        com.google.gson.JsonObject empty = new com.google.gson.JsonObject();
        empty.add("accounts", new com.google.gson.JsonObject());
        return empty;
    }

    private void writeJson(com.google.gson.JsonObject root) throws IOException {
        Files.createDirectories(accountsFile.getParent());
        Files.write(accountsFile, root.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String offlineToken(String username) {
        java.util.Base64.Encoder encoder = java.util.Base64.getUrlEncoder().withoutPadding();
        String header = encoder.encodeToString("{\"alg\":\"none\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        long iat = System.currentTimeMillis() / 1000L;
        long exp = iat + 7L * 24L * 3600L;
        String payload = encoder.encodeToString(
                ("{\"sub\":\"" + username + "\",\"iat\":" + iat + ",\"exp\":" + exp + "}").getBytes(StandardCharsets.UTF_8));
        return header + "." + payload + ".offline";
    }

    private static String preview(byte[] data) {
        if (data.length == 0) {
            return "";
        }
        String text = new String(data, StandardCharsets.ISO_8859_1);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < Math.min(60, text.length()); i++) {
            char c = text.charAt(i);
            out.append(c >= 32 && c < 127 ? c : '.');
        }
        return out.toString();
    }

    @Override
    public void onError(WebSocket conn, Exception exception) {
        line("error: " + exception);
        exception.printStackTrace();
    }

    @Override
    public void onStart() {
        line("fake launcher listening on port " + getPort());
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 28190;
        Path logFile = Paths.get(args.length > 1 ? args[1] : "run/logs/fake-launcher.log");
        Path accountsFile = args.length > 2 ? Paths.get(args[2]) : null;
        FakeLauncher launcher = new FakeLauncher(port, logFile, accountsFile);
        launcher.start();
        launcher.ensureAccount();
        Thread.sleep(Long.MAX_VALUE);
    }
}
