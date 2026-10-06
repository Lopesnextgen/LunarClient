package com.moonsworth.lunar.altmanager;

/**
 * One saved account.
 *
 * Cracked accounts only need a username (session UUID is derived offline).
 * Premium accounts store the Microsoft refresh token so the session can be
 * refreshed on demand.
 */
public class AltAccount {
    private String username;
    private String uuid;
    private String accessToken;
    private String refreshToken;
    private boolean cracked;
    private long lastUsed;

    public AltAccount() {
    }

    public AltAccount(String username, String uuid, String accessToken, String refreshToken, boolean cracked) {
        this.username = username;
        this.uuid = uuid;
        this.accessToken = accessToken == null ? "" : accessToken;
        this.refreshToken = refreshToken == null ? "" : refreshToken;
        this.cracked = cracked;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccessToken() {
        return this.accessToken == null ? "" : this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken == null ? "" : this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isCracked() {
        return this.cracked;
    }

    public void setCracked(boolean cracked) {
        this.cracked = cracked;
    }

    public long getLastUsed() {
        return this.lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }

    /** Session UUID: stored value, otherwise the offline UUID for the name. */
    public String sessionUuid() {
        if (this.uuid != null && !this.uuid.isEmpty()) {
            return this.uuid;
        }
        return AltStore.offlineUuid(this.username);
    }
}
