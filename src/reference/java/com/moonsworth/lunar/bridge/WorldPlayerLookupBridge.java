package com.moonsworth.lunar.bridge;

import java.util.Optional;
import java.util.UUID;

public interface WorldPlayerLookupBridge {
   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getPlayerEntityByUUID")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getPlayerByUUID"))
      }
   )
   Optional<Bridge6_10> bridge$getPlayerByUniqueId(UUID var1);
}
