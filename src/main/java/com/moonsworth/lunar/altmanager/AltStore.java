package com.moonsworth.lunar.altmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/** JSON-backed account list stored in &lt;gameDir&gt;/lunar/altmanager.json. */
public final class AltStore {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9_]{3,16}$");
    private static final String[] ADJECTIVES = {
            "Swift", "Silent", "Frost", "Blaze", "Shadow", "Pixel", "Turbo", "Lunar", "Crimson", "Echo",
            "Nova", "Rapid", "Cosmic", "Mystic", "Iron", "Neon", "Storm", "Ghost", "Solar", "Arctic"
    };
    private static final String[] NOUNS = {
            "Panda", "Falcon", "Cobra", "Rider", "Ninja", "Wizard", "Sniper", "Gamer", "Knight", "Tiger",
            "Dragon", "Pilot", "Wolf", "Vortex", "Comet", "Rogue", "Hunter", "Phantom", "Blade", "Spark"
    };

    private final File file;
    private final List<AltAccount> accounts = new ArrayList<>();
    private String selectedName;
    private final Random random = new Random();

    public AltStore(File file) {
        this.file = file;
        this.load();
    }

    public synchronized List<AltAccount> getAccounts() {
        List<AltAccount> copy = new ArrayList<>(this.accounts);
        copy.sort(Comparator.comparingLong(AltAccount::getLastUsed).reversed());
        return copy;
    }

    public synchronized AltAccount getSelected() {
        if (this.selectedName == null) {
            return null;
        }
        for (AltAccount account : this.accounts) {
            if (account.getUsername().equalsIgnoreCase(this.selectedName)) {
                return account;
            }
        }
        return null;
    }

    public synchronized void select(AltAccount account) {
        if (account != null) {
            this.selectedName = account.getUsername();
            account.setLastUsed(System.currentTimeMillis());
            this.save();
        }
    }

    public static boolean isValidName(String username) {
        return username != null && VALID_NAME.matcher(username).matches();
    }

    /** Adds a cracked account (or refreshes an existing entry with the same name). */
    public synchronized AltAccount addCracked(String username) {
        String offlineUuid = offlineUuid(username);
        for (AltAccount account : this.accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                account.setUuid(offlineUuid);
                account.setCracked(true);
                account.setLastUsed(System.currentTimeMillis());
                this.save();
                return account;
            }
        }
        AltAccount account = new AltAccount(username, offlineUuid, "0", "", true);
        account.setLastUsed(System.currentTimeMillis());
        this.accounts.add(account);
        this.save();
        return account;
    }

    /** Adds or updates a premium account after a successful token refresh. */
    public synchronized AltAccount addPremium(String username, String uuid, String accessToken, String refreshToken) {
        for (AltAccount account : this.accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                account.setUuid(uuid);
                account.setAccessToken(accessToken);
                account.setRefreshToken(refreshToken);
                account.setCracked(false);
                account.setLastUsed(System.currentTimeMillis());
                this.save();
                return account;
            }
        }
        AltAccount account = new AltAccount(username, uuid, accessToken, refreshToken, false);
        account.setLastUsed(System.currentTimeMillis());
        this.accounts.add(account);
        this.save();
        return account;
    }

    public synchronized void remove(AltAccount account) {
        if (account == null) {
            return;
        }
        this.accounts.remove(account);
        if (account.getUsername().equalsIgnoreCase(this.selectedName)) {
            this.selectedName = null;
        }
        this.save();
    }

    public synchronized String randomName() {
        for (int attempt = 0; attempt < 50; attempt++) {
            String name = ADJECTIVES[this.random.nextInt(ADJECTIVES.length)]
                    + NOUNS[this.random.nextInt(NOUNS.length)]
                    + (this.random.nextInt(90) + 10);
            boolean taken = false;
            for (AltAccount account : this.accounts) {
                if (account.getUsername().equalsIgnoreCase(name)) {
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                return name;
            }
        }
        return "Player" + (this.random.nextInt(9000) + 1000);
    }

    public static String offlineUuid(String username) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8))
                .toString().replace("-", "");
    }

    public synchronized void save() {
        try {
            File parent = this.file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            try (Writer writer = Files.newBufferedWriter(this.file.toPath(), StandardCharsets.UTF_8)) {
                GSON.toJson(this.accounts, writer);
            }
        } catch (IOException ignored) {
        }
    }

    private void load() {
        if (!this.file.isFile()) {
            return;
        }
        try (Reader reader = Files.newBufferedReader(this.file.toPath(), StandardCharsets.UTF_8)) {
            List<AltAccount> parsed = GSON.fromJson(reader,
                    new TypeToken<List<AltAccount>>() { }.getType());
            if (parsed != null) {
                this.accounts.clear();
                this.accounts.addAll(parsed);
            }
        } catch (Exception ignored) {
        }
    }
}
