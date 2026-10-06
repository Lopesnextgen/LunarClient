package com.moonsworth.lunar.bridge;

import com.mojang.authlib.GameProfile;
import java.util.UUID;

@Annotation("com/mojang/authlib/yggdrasil/YggdrasilMinecraftSessionService")
public interface Bridge3_28 {
   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"insecureProfiles", "refresh"})),
            @BridgeVersionMapping(version = 19, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {})
      }
   )
   default void bridge$refresh(GameProfile var1) {
      this.method1(var1.getId());
   }

   @Annotation(OIRHICCHORIRORCCOOCRRRHRRCRCCI = @BridgeVersionMapping(version = 19, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"insecureProfiles", "refresh"})))
   default void method1(UUID var1) {
      throw new UnsupportedOperationException("Not implemented below 1.20.2!");
   }
}
