package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface LegacyLayerRendererBridge<T extends EntityLivingBridge> {
   @VersionGate(max = 5)
   void method1(BridgeExtension3_5 bridgeextension3_51, T value2, float value3, float value4, float value5, float value6, float value7, float value8, float value9);

   default void method2(
      AbstractRenderContext bridgeextension_91,
      EntityLivingBridge bridgeextension2_52,
      VertexConsumerBridge bridge4_63,
      Bridge5_16 bridge5_164,
      float value5,
      float value6,
      float value7,
      float value8,
      float value9,
      float value,
      float value2
   ) {
   }
}
