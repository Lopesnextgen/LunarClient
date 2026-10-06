package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;

public interface LayerRendererBridge<L extends EntityLivingBridge, M extends EntityLivingStateBridge> {
   @VersionGate(max = 5)
   default void method1(
      BridgeExtension3_5 bridgeextension3_51, L l2, ModelPlayerBridge bridgeextension2_73, float value, float value2, float value3, float value4, float value5, float value6, float value7
   ) {
   }

   @VersionGate(min = 6)
   default Optional<ResourceLocationBridge> method2(M m1, int value) {
      return Optional.empty();
   }

   @VersionGate(min = 6)
   default int method3(M m1) {
      return 1;
   }

   @VersionGate(min = 6)
   default void method4(BridgeExtension2_11 bridgeextension2_111, M m2, ModelPlayerBridge bridgeextension2_73, int number4) {
      this.method5(bridgeextension2_111, (M)m2, bridgeextension2_73, number4, -1);
   }

   @VersionGate(min = 6)
   default void method5(BridgeExtension2_11 bridgeextension2_111, M m2, ModelPlayerBridge bridgeextension2_73, int number4, int value) {
   }

   @VersionGate(min = 6)
   default Optional<RenderTypeBridge> method6(ResourceLocationBridge horsestats141) {
      return Optional.empty();
   }

   default boolean method7() {
      return false;
   }

   default boolean method8() {
      return false;
   }

   default boolean method9(EntityPlayerBridge entity) {
      return true;
   }

   default void method10(M m1, ModelPlayerBridge bridgeextension2_72, int value) {
   }
}
