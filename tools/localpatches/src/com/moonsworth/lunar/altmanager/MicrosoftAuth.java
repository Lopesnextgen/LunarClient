package com.moonsworth.lunar.altmanager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Real Microsoft/Xbox/Minecraft login used by the built-in alt manager.
 *
 * Flow: OAuth device code (consumers tenant, the public Minecraft client id)
 * -> Xbox Live user authenticate -> XSTS authorize -> Minecraft services
 * login_with_xbox -> profile. The resulting Minecraft access token is written
 * to accounts.json through {@link LocalAccounts#addPremium}.
 *
 * Progress is exposed as JSON through {@link #statusJson()} so the account
 * panel can poll it.
 */
public final class MicrosoftAuth {

    /** Lunar's own public client id (from the launcher's app.asar). */
    private static final String CLIENT_ID = "4358653d-21f6-4697-96bb-7963ff974196";
    private static final String DEVICE_CODE_URL =
            "https://login.microsoftonline.com/consumers/oauth2/v2.0/devicecode";
    private static final String TOKEN_URL =
            "https://login.microsoftonline.com/consumers/oauth2/v2.0/token";
    private static final String XBL_URL = "https://user.auth.xboxlive.com/user/authenticate";
    private static final String XSTS_URL = "https://xsts.auth.xboxlive.com/xsts/authorize";
    private static final String MC_LOGIN_URL =
            "https://api.minecraftservices.com/authentication/login_with_xbox";
    private static final String MC_PROFILE_URL = "https://api.minecraftservices.com/minecraft/profile";

    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    private static final Object LOCK = new Object();
    private static JsonObject state = null;

    private MicrosoftAuth() {
    }

    public static String statusJson() {
        synchronized (LOCK) {
            return state == null ? "{}" : state.toString();
        }
    }

    private static void setState(String stateName, String code, String url, String username, String message) {
        JsonObject json = new JsonObject();
        json.addProperty("state", stateName);
        json.addProperty("code", code == null ? "" : code);
        json.addProperty("url", url == null ? "" : url);
        json.addProperty("username", username == null ? "" : username);
        json.addProperty("message", message == null ? "" : message);
        synchronized (LOCK) {
            state = json;
        }
    }

    public static void startDeviceLogin() {
        synchronized (LOCK) {
            if (state != null && "waiting".equals(state.get("state").getAsString())) {
                return;
            }
        }
        setState("starting", "", "", "", "Contacting Microsoft...");
        Thread thread = new Thread(MicrosoftAuth::runFlow, "lunar-altmanager-premium");
        thread.setDaemon(true);
        thread.start();
    }

    private static void runFlow() {
        try {
            String refreshToken = null;
            String deviceCode;
            String userCode;
            String verificationUri;
            long interval;
            long expiresIn;

            JsonObject device = postForm(DEVICE_CODE_URL, form(
                    "client_id", CLIENT_ID,
                    "scope", "XboxLive.signin offline_access"));
            deviceCode = device.get("device_code").getAsString();
            userCode = device.get("user_code").getAsString();
            verificationUri = device.get("verification_uri").getAsString();
            interval = device.has("interval") ? device.get("interval").getAsLong() : 5L;
            expiresIn = device.has("expires_in") ? device.get("expires_in").getAsLong() : 900L;
            setState("waiting", userCode, verificationUri, "", "Enter the code at " + verificationUri);

            long deadline = System.currentTimeMillis() + expiresIn * 1000L;
            String msAccessToken = null;
            while (System.currentTimeMillis() < deadline) {
                Thread.sleep(Math.max(1L, interval) * 1000L);
                JsonObject token = postForm(TOKEN_URL, form(
                        "grant_type", "urn:ietf:params:oauth:grant-type:device_code",
                        "client_id", CLIENT_ID,
                        "device_code", deviceCode));
                if (token.has("access_token")) {
                    msAccessToken = token.get("access_token").getAsString();
                    refreshToken = token.has("refresh_token") ? token.get("refresh_token").getAsString() : null;
                    break;
                }
                String error = token.has("error") ? token.get("error").getAsString() : "";
                if ("authorization_pending".equals(error)) {
                    continue;
                }
                if ("slow_down".equals(error)) {
                    interval += 5L;
                    continue;
                }
                throw new IllegalStateException("Microsoft auth failed: " + token);
            }
            if (msAccessToken == null) {
                throw new IllegalStateException("Device code expired before it was entered");
            }
            setState("waiting", userCode, verificationUri, "", "Microsoft login ok, signing into Xbox...");

            JsonObject xblProperties = new JsonObject();
            xblProperties.addProperty("AuthMethod", "RPS");
            xblProperties.addProperty("SiteName", "user.auth.xboxlive.com");
            xblProperties.addProperty("RpsTicket", "d=" + msAccessToken);
            JsonObject xblBody = new JsonObject();
            xblBody.add("Properties", xblProperties);
            xblBody.addProperty("RelyingParty", "http://auth.xboxlive.com");
            xblBody.addProperty("TokenType", "JWT");
            JsonObject xbl = postJson(XBL_URL, xblBody);
            String xblToken = xbl.get("Token").getAsString();
            String uhs = xbl.getAsJsonObject("DisplayClaims").getAsJsonArray("xui")
                    .get(0).getAsJsonObject().get("uhs").getAsString();

            JsonObject xstsProperties = new JsonObject();
            xstsProperties.addProperty("SandboxId", "RETAIL");
            com.google.gson.JsonArray userTokens = new com.google.gson.JsonArray();
            userTokens.add(xblToken);
            xstsProperties.add("UserTokens", userTokens);
            JsonObject xstsBody = new JsonObject();
            xstsBody.add("Properties", xstsProperties);
            xstsBody.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
            xstsBody.addProperty("TokenType", "JWT");
            JsonObject xsts = postJson(XSTS_URL, xstsBody);
            String xstsToken = xsts.get("Token").getAsString();

            JsonObject mcBody = new JsonObject();
            mcBody.addProperty("identityToken", "XBL3.0 x=" + uhs + ";" + xstsToken);
            JsonObject mc = postJson(MC_LOGIN_URL, mcBody);
            String mcAccessToken = mc.get("access_token").getAsString();

            JsonObject profile = getJson(MC_PROFILE_URL, mcAccessToken);
            if (!profile.has("name") || !profile.has("id")) {
                throw new IllegalStateException("No Minecraft profile on this account (does it own the game?)");
            }
            String username = profile.get("name").getAsString();
            String uuid32 = profile.get("id").getAsString();

            LocalAccounts.addPremium(username, uuid32, mcAccessToken, refreshToken);
            AccountOps.reloadAsync();
            setState("success", "", verificationUri, username, "Added " + username);
        } catch (Throwable throwable) {
            setState("error", "", "", "", String.valueOf(throwable.getMessage()));
        }
    }

    private static String form(String... keyValues) {
        return Stream.iterate(0, i -> i + 2)
                .limit(keyValues.length / 2)
                .map(i -> encode(keyValues[i]) + "=" + encode(keyValues[i + 1]))
                .collect(Collectors.joining("&"));
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private static JsonObject postForm(String url, String body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return send(request);
    }

    private static JsonObject postJson(String url, JsonObject body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();
        return send(request);
    }

    private static JsonObject getJson(String url, String bearer) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Authorization", "Bearer " + bearer)
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();
        return send(request);
    }

    private static JsonObject send(HttpRequest request) throws Exception {
        HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = new JsonParser().parse(response.body()).getAsJsonObject();
        if (response.statusCode() >= 400) {
            throw new IllegalStateException(json.has("error_description")
                    ? json.get("error_description").getAsString()
                    : ("HTTP " + response.statusCode() + ": " + response.body()));
        }
        return json;
    }
}
