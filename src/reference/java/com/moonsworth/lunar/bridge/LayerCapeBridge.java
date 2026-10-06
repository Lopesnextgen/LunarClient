package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface LayerCapeBridge {
   void bridge$render(EntityPlayerBridge bridgeextension2221, float value2, float value3, float value4, float value5, float value6, float value7, float value8);

   @VersionGate(min = 6)
   default void bridge$renderModern(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge entity, float value3, float value4, float value5, float value6, float value7) {
   }
}
