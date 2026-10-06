package com.moonsworth.lunar.bridge;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.UUID;
import net.kyori.adventure.text.Component;

public interface PlayerInfoBridge {
   GameProfile bridge$getGameProfile();

   UUID bridge$getProfileTextureId();

   int bridge$getLatency();

   Component bridge$getDisplayName();

   boolean bridge$hasMismatchedId();

   @VersionGate(min = 1)
   ResourceLocationBridge bridge$getLocationSkin();

   Component bridge$formatName();
}
