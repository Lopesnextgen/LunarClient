package com.moonsworth.lunar.bridge.horsestats;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/Session")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/client/User"))
   }
)
public interface Horsestats {
   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("getPlayerID")),
            @BridgeVersionMapping(version = 6, targets = {})
      }
   )
   @Nullable
   String bridge$getPlayerID();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("getUsername")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("getName")),
            @BridgeVersionMapping(version = 19, targets = {})
      }
   )
   String bridge$getUsername();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("getToken")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("getAccessToken")),
            @BridgeVersionMapping(version = 19, targets = {})
      }
   )
   String bridge$getToken();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("getProfile")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("getGameProfile")),
            @BridgeVersionMapping(version = 19, targets = {})
      }
   )
   GameProfile bridge$getProfile();

   default UUID bridge$getId() {
      return this.bridge$getProfile().getId();
   }

   default boolean bridge$isValidSession() {
      if (StringUtils.isBlank(this.bridge$getUsername())) {
         return false;
      }

      GameProfile var1 = this.bridge$getProfile();
      return var1 != null && var1.getId() != null;
   }
}
