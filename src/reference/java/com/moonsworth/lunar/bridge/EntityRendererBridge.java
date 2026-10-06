package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface EntityRendererBridge {
   @VersionGate(min = 6)
   default int bridge$getPackedLightCoords(BridgeExtension bridge, float value) {
      return 0;
   }
}
