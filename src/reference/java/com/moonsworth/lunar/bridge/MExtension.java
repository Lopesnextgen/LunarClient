package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Optional;

public interface MExtension<L extends BridgeExtension2_5, M extends BridgeExtension2_2> {
   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default void method1(
      BridgeExtension3_5 var1, L var2, BridgeExtension2_7 var3, float var4, float var5, float value, float value2, float value3, float value4, float value5
   ) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Optional<ResourceLocationBridge> method2(M var1, int var2) {
      return Optional.empty();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default int method3(M var1) {
      return 1;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void method4(BridgeExtension2_11 var1, M var2, BridgeExtension2_7 var3, int var4) {
      this.method5(var1, (M)var2, var3, var4, -1);
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void method5(BridgeExtension2_11 var1, M var2, BridgeExtension2_7 var3, int var4, int var5) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Optional<RenderLayerBridge> method6(ResourceLocationBridge var1) {
      return Optional.empty();
   }

   default boolean method7() {
      return false;
   }

   default boolean method8() {
      return false;
   }

   default boolean method9(EntityPlayerBridge var1) {
      return true;
   }

   default void method10(M var1, BridgeExtension2_7 var2, int var3) {
   }
}
