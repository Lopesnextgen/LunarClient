package com.moonsworth.lunar.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.lang.reflect.Field;

/**
 * Swaps the running Minecraft session.
 *
 * The runtime classes are Ichor-remapped copies of the official jar; the
 * session field is normally called "session", but we fall back to a type
 * scan so this keeps working if the mapping changes.
 */
public final class SessionSwitcher {
    private SessionSwitcher() {
    }

    public static void apply(AltAccount account) {
        String uuid = account.sessionUuid();
        Session session = account.isCracked()
                ? new Session(account.getUsername(), uuid, "0", "legacy")
                : new Session(account.getUsername(), uuid, account.getAccessToken(), "mojang");
        applySession(session);
    }

    public static void applySession(Session session) {
        Minecraft mc = Minecraft.getMinecraft();
        try {
            Field field = Minecraft.class.getDeclaredField("session");
            field.setAccessible(true);
            field.set(mc, session);
            return;
        } catch (ReflectiveOperationException ignored) {
        }
        for (Field field : Minecraft.class.getDeclaredFields()) {
            if (Session.class.isAssignableFrom(field.getType())) {
                try {
                    field.setAccessible(true);
                    field.set(mc, session);
                    return;
                } catch (ReflectiveOperationException ignored) {
                }
            }
        }
    }
}
