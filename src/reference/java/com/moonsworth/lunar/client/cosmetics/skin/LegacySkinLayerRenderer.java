package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(max = 5)
public class LegacySkinLayerRenderer {
   public static boolean field1 = false;
   private static final HatLayerRenderer field2 = new HatLayerRenderer();
   private static final BodyOverlayLayerRenderer field3 = new BodyOverlayLayerRenderer();

   public LegacySkinLayerRenderer() {
   }

   public static void method1(Bridge5_11 bridge5_110, ModelPlayerBridge bridgeextension2_71, float value2) {
      field2.method5(AbstractRenderContext.method32(), bridge5_110, bridgeextension2_71, 0.0F, 0.0F, 0.0F, bridge5_110.method2(), 0.0F, 0.0F, value2);
      field3.method5(AbstractRenderContext.method32(), bridge5_110, bridgeextension2_71, 0.0F, 0.0F, 0.0F, bridge5_110.method2(), 0.0F, 0.0F, value2);
   }
}
