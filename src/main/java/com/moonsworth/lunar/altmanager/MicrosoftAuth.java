package com.moonsworth.lunar.altmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Microsoft / Minecraft authentication for premium accounts.
 *
 * Flows:
 *   - refresh token -> login()          re-login for a saved account; the
 *                                       minting client (Mojang, Lunar, Prism,
 *                                       LabyMod, ...) is auto-detected
 *   - device code   -> requestDeviceCode() + pollDeviceCode()   fresh login
 *   - access token  -> tokenLogin()     validate an existing services token
 *
 * The client list and auto-detection follow AuthMe
 * (SinisterBluds/awesome-alt-management, MIT) which tracks the OAuth clients
 * observed to mint Minecraft refresh tokens.
 */
public final class MicrosoftAuth {
    private static final String V1_TOKEN_URL = "https://login.live.com/oauth20_token.srf";
    private static final String V2_TOKEN_URL = "https://login.microsoftonline.com/consumers/oauth2/v2.0/token";

    /** A known OAuth client that Minecraft refresh tokens originate from. */
    private static final class Client {
        final String name;
        final String clientId;
        final String scope;
        final String ticketPrefix; // "d" for Azure apps, "t" for legacy Mojang Live

        Client(String name, String clientId, String scope, String ticketPrefix) {
            this.name = name;
            this.clientId = clientId;
            this.scope = scope;
            this.ticketPrefix = ticketPrefix;
        }
    }

    private static final List<Client> KNOWN_CLIENTS = new ArrayList<>();

    static {
        KNOWN_CLIENTS.add(new Client("Mojang", "00000000402b5328", "service::user.auth.xboxlive.com::MBI_SSL", "t"));
        KNOWN_CLIENTS.add(new Client("Prism", "c36a9fb6-4f2a-41ff-90bd-ae7cc92031eb", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("Lunar", "4358653d-21f6-4697-96bb-7963ff974196", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("LabyMod", "27843883-6e3b-42cb-9e51-4f55a700601e", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("PolyMC", "6b329578-bfec-42a3-b503-303ab3f2ac96", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("Technic", "8dfabc1d-38a9-42d8-bc08-677dbc60fe65", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("IAS", "54fd49e4-2103-4044-9603-2b028c814ec3", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("Rise", "ba89e6e0-8490-4a26-8746-f389a0d3ccc7", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("Essentials", "e39cc675-eb52-4475-b5f8-82aaae14eeba", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("HMCL", "6a3728d6-27a3-4180-99bb-479895b8f88e", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("LiquidBounce", "0add8caf-2cc6-4546-b798-c3d171217dd9", "XboxLive.signin offline_access", "d"));
        KNOWN_CLIENTS.add(new Client("KSYZ", "42a60a84-599d-44b2-a7c6-b00cdef1d6a2", "XboxLive.signin offline_access", "d"));
    }

    private static volatile String proxyHost;
    private static volatile int proxyPort;

    private MicrosoftAuth() {
    }

    /** Optional HTTP proxy for auth requests, e.g. "127.0.0.1:8080". */
    public static void setProxy(String hostPort) {
        if (hostPort == null || hostPort.isEmpty()) {
            proxyHost = null;
            proxyPort = 0;
            return;
        }
        String[] parts = hostPort.split(":", 2);
        proxyHost = parts[0];
        try {
            proxyPort = parts.length > 1 ? Integer.parseInt(parts[1]) : 8080;
        } catch (NumberFormatException exception) {
            proxyPort = 8080;
        }
    }

    private static CloseableHttpClient client() {
        RequestConfig.Builder config = RequestConfig.custom()
                .setConnectTimeout(15000)
                .setSocketTimeout(20000);
        if (proxyHost != null) {
            config.setProxy(new HttpHost(proxyHost, proxyPort));
        }
        return HttpClients.custom().setDefaultRequestConfig(config.build()).build();
    }

    /** Re-login from a refresh token, auto-detecting the launcher that minted it. */
    public static AltAccount login(String rawRefreshToken) throws Exception {
        if (rawRefreshToken == null || rawRefreshToken.trim().isEmpty()) {
            throw new IllegalArgumentException("No refresh token given");
        }
        String refreshToken = normalizeToken(rawRefreshToken);
        StringBuilder errors = new StringBuilder();
        try (CloseableHttpClient http = client()) {
            for (Client candidate : KNOWN_CLIENTS) {
                for (String tokenUrl : new String[]{V2_TOKEN_URL, V1_TOKEN_URL}) {
                    try {
                        String[] ms = refreshWith(http, tokenUrl, candidate, refreshToken);
                        return completeLogin(http, ms[0], ms[1], candidate.ticketPrefix);
                    } catch (Exception exception) {
                        if (errors.length() < 240) {
                            errors.append(candidate.name).append(": ").append(exception.getMessage()).append("; ");
                        }
                    }
                }
            }
        }
        throw new IllegalStateException("No known launcher accepted this refresh token. "
                + "It may be revoked or from an unsupported client. " + errors);
    }

    /** Premium login from an existing Minecraft services access token. */
    public static AltAccount tokenLogin(String accessToken) throws Exception {
        if (accessToken == null || accessToken.trim().isEmpty()) {
            throw new IllegalArgumentException("No access token given");
        }
        try (CloseableHttpClient http = client()) {
            String[] profile = profile(http, accessToken.trim());
            return new AltAccount(profile[0], profile[1], accessToken.trim(), "", false);
        }
    }

    /** Starts the device-code flow; show the code to the user and poll. */
    public static DeviceCode requestDeviceCode() throws Exception {
        try (CloseableHttpClient http = client()) {
            List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair("client_id", KNOWN_CLIENTS.get(0).clientId));
            form.add(new BasicNameValuePair("scope", KNOWN_CLIENTS.get(0).scope));
            form.add(new BasicNameValuePair("response_type", "device_code"));
            HttpPost post = new HttpPost("https://login.live.com/oauth20_connect.srf");
            post.setEntity(new UrlEncodedFormEntity(form, StandardCharsets.UTF_8));
            JsonObject json = execute(http, post);
            return new DeviceCode(
                    required(json, "device_code", "Device login failed"),
                    required(json, "user_code", "Device login failed"),
                    json.has("verification_uri") ? json.get("verification_uri").getAsString()
                            : "https://microsoft.com/link",
                    json.has("interval") ? json.get("interval").getAsInt() : 5,
                    json.has("expires_in") ? json.get("expires_in").getAsInt() : 900);
        }
    }

    /** Polls the device-code flow until approved/expired. Blocking. */
    public static AltAccount pollDeviceCode(DeviceCode code) throws Exception {
        long deadline = System.currentTimeMillis() + code.expiresIn * 1000L;
        try (CloseableHttpClient http = client()) {
            while (System.currentTimeMillis() < deadline) {
                Thread.sleep(Math.max(1, code.interval) * 1000L);
                List<NameValuePair> form = new ArrayList<>();
                form.add(new BasicNameValuePair("client_id", KNOWN_CLIENTS.get(0).clientId));
                form.add(new BasicNameValuePair("grant_type", "urn:ietf:params:oauth:grant-type:device_code"));
                form.add(new BasicNameValuePair("device_code", code.deviceCode));
                try {
                    HttpPost post = new HttpPost(V1_TOKEN_URL);
                    post.setEntity(new UrlEncodedFormEntity(form, StandardCharsets.UTF_8));
                    JsonObject json = execute(http, post);
                    String access = required(json, "access_token", "Device login failed");
                    String refresh = json.has("refresh_token") ? json.get("refresh_token").getAsString() : "";
                    return completeLogin(http, access, refresh, KNOWN_CLIENTS.get(0).ticketPrefix);
                } catch (IllegalStateException exception) {
                    String message = exception.getMessage() == null ? "" : exception.getMessage();
                    if (message.contains("authorization_pending") || message.contains("slow_down")) {
                        continue;
                    }
                    throw exception;
                }
            }
        }
        throw new IllegalStateException("Device login expired; try again");
    }

    private static String[] refreshWith(CloseableHttpClient http, String tokenUrl, Client candidate,
                                        String refreshToken) throws Exception {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("client_id", candidate.clientId));
        form.add(new BasicNameValuePair("grant_type", "refresh_token"));
        form.add(new BasicNameValuePair("refresh_token", refreshToken));
        form.add(new BasicNameValuePair("scope", candidate.scope));
        HttpPost post = new HttpPost(tokenUrl);
        post.setEntity(new UrlEncodedFormEntity(form, StandardCharsets.UTF_8));
        JsonObject json = execute(http, post);
        String access = required(json, "access_token", "refresh failed");
        String rotated = json.has("refresh_token") ? json.get("refresh_token").getAsString() : refreshToken;
        return new String[]{access, rotated};
    }

    private static AltAccount completeLogin(CloseableHttpClient http, String msAccessToken, String refreshToken,
                                            String ticketPrefix) throws Exception {
        String[] xbl = xboxLive(http, msAccessToken, ticketPrefix);
        String[] xsts = xsts(http, xbl[0]);
        String mcToken = minecraftLogin(http, xsts[0], xsts[1]);
        String[] profile = profile(http, mcToken);
        return new AltAccount(profile[0], profile[1], mcToken, refreshToken, false);
    }

    /** Strips "user:token"-style prefixes handed out by some alt sources. */
    public static String normalizeToken(String value) {
        if (value == null) {
            return "";
        }
        value = value.trim();
        int colon = value.indexOf(':');
        if (colon > 0 && colon < value.length() - 1) {
            String prefix = value.substring(0, colon);
            if (!prefix.contains(".") && prefix.length() <= 32) {
                value = value.substring(colon + 1).trim();
            }
        }
        return value.trim();
    }

    private static String[] xboxLive(CloseableHttpClient http, String msAccessToken, String ticketPrefix)
            throws Exception {
        JsonObject properties = new JsonObject();
        properties.addProperty("AuthMethod", "RPS");
        properties.addProperty("SiteName", "user.auth.xboxlive.com");
        properties.addProperty("RpsTicket", ticketPrefix + "=" + msAccessToken);
        JsonObject body = new JsonObject();
        body.add("Properties", properties);
        body.addProperty("RelyingParty", "http://auth.xboxlive.com");
        body.addProperty("TokenType", "JWT");
        JsonObject json = execute(http, jsonPost("https://user.auth.xboxlive.com/user/authenticate", body));
        String token = required(json, "Token", "Xbox Live authentication failed");
        return new String[]{token, userHash(json)};
    }

    private static String[] xsts(CloseableHttpClient http, String xblToken) throws Exception {
        JsonObject properties = new JsonObject();
        properties.addProperty("SandboxId", "RETAIL");
        JsonArray tokens = new JsonArray();
        tokens.add(new com.google.gson.JsonPrimitive(xblToken));
        properties.add("UserTokens", tokens);
        JsonObject body = new JsonObject();
        body.add("Properties", properties);
        body.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
        body.addProperty("TokenType", "JWT");
        JsonObject json = execute(http, jsonPost("https://xsts.auth.xboxlive.com/xsts/authorize", body));
        String token = required(json, "Token", "XSTS authorization failed");
        return new String[]{token, userHash(json)};
    }

    private static String minecraftLogin(CloseableHttpClient http, String xstsToken, String userHash) throws Exception {
        JsonObject body = new JsonObject();
        body.addProperty("identityToken", "XBL3.0 x=" + userHash + ";" + xstsToken);
        JsonObject json = execute(http,
                jsonPost("https://api.minecraftservices.com/authentication/login_with_xbox", body));
        return required(json, "access_token", "Minecraft login failed");
    }

    private static String[] profile(CloseableHttpClient http, String mcAccessToken) throws Exception {
        HttpGet get = new HttpGet("https://api.minecraftservices.com/minecraft/profile");
        get.setHeader("Authorization", "Bearer " + mcAccessToken);
        JsonObject json = execute(http, get);
        String name = required(json, "name", "Could not read Minecraft profile");
        String id = required(json, "id", "Could not read Minecraft profile");
        return new String[]{name, id};
    }

    private static HttpPost jsonPost(String url, JsonObject body) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(body.toString(), StandardCharsets.UTF_8));
        return post;
    }

    private static JsonObject execute(CloseableHttpClient http, HttpUriRequest request) throws Exception {
        try (CloseableHttpResponse response = http.execute(request)) {
            String body = response.getEntity() == null
                    ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            int status = response.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300) {
                String detail = body;
                try {
                    JsonObject json = new JsonParser().parse(body).getAsJsonObject();
                    if (json.has("error")) {
                        detail = json.get("error").getAsString();
                    }
                } catch (Exception ignored) {
                }
                throw new IllegalStateException(status + " " + detail);
            }
            return new JsonParser().parse(body).getAsJsonObject();
        }
    }

    private static String userHash(JsonObject json) throws Exception {
        try {
            return json.getAsJsonObject("DisplayClaims").getAsJsonArray("xui")
                    .get(0).getAsJsonObject().get("uhs").getAsString();
        } catch (Exception exception) {
            throw new IllegalStateException("Login response was missing the user hash");
        }
    }

    private static String required(JsonObject json, String key, String message) {
        if (json.has(key) && !json.get(key).isJsonNull()) {
            return json.get(key).getAsString();
        }
        throw new IllegalStateException(message);
    }

    /** Device-code session data. */
    public static final class DeviceCode {
        public final String deviceCode;
        public final String userCode;
        public final String verificationUri;
        public final int interval;
        public final int expiresIn;

        DeviceCode(String deviceCode, String userCode, String verificationUri, int interval, int expiresIn) {
            this.deviceCode = deviceCode;
            this.userCode = userCode;
            this.verificationUri = verificationUri;
            this.interval = interval;
            this.expiresIn = expiresIn;
        }
    }
}
