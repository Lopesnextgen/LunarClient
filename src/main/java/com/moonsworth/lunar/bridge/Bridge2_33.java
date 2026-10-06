package com.moonsworth.lunar.bridge;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.UUID;
import net.kyori.adventure.text.Component;

public interface Bridge2_33 {
   GameProfile bridge$getGameProfile();

   UUID bridge$getProfileTextureId();

   int bridge$getLatency();

   Component bridge$getDisplayName();

   boolean bridge$hasMismatchedId();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   ResourceLocationBridge bridge$getLocationSkin();

   Component bridge$formatName();
}
