package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;

public interface Bridge4_21 {
   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default void method1(BridgeExtension3_5 var1, float var2, float var3, ResourceLocationBridge var4) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void method2(BridgeExtension2_11 var1, float var2, float var3, ResourceLocationBridge var4) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void method3(BridgeExtension2_11 var1, float var2, float var3, ResourceLocationBridge var4, boolean flag, int value) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Optional<RenderLayerBridge> method4(ResourceLocationBridge var1) {
      return Optional.empty();
   }
}
