package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface ItemEntityRendererBridge {
   @VersionGate(max = 5)
   default void method1(EntityItemBridge entity, double value, double value4, double value6, float value8) {
   }

   @VersionGate(min = 6)
   default void method2(BridgeExtension2_11 bridgeextension2_111, EntityRendererBridge bridge_622, EntityItemExtensionBridge bridgeextension3_23, double value4, double value6, double value8) {
   }
}
