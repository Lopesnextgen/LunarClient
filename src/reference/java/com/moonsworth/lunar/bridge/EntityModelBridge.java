package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;

public interface EntityModelBridge {
   @VersionGate(max = 5)
   default void method1(BridgeExtension3_5 bridgeextension3_51, float value2, float value3, ResourceLocationBridge horsestats144) {
   }

   @VersionGate(min = 6)
   default void method2(BridgeExtension2_11 bridgeextension2_111, float value2, float value3, ResourceLocationBridge horsestats144) {
   }

   @VersionGate(min = 6)
   default void method3(BridgeExtension2_11 bridgeextension2_111, float value2, float value3, ResourceLocationBridge horsestats144, boolean flag, int value) {
   }

   @VersionGate(min = 6)
   default Optional<RenderTypeBridge> method4(ResourceLocationBridge horsestats141) {
      return Optional.empty();
   }
}
