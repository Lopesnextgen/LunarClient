package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.VertexConsumerBridge;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.ichor.VersionGate;

public class SkinLayerRenderer {
   public SkinLayerRenderer() {
   }

   private static com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method1(
      ModelRendererBridge bridge2_460, boolean flag1, EntityPlayerBridge bridgeextension2222, boolean flag3, boolean flag4, float value5
   ) {
      if (flag1) {
         return null;
      }

      Skins3d skins3d6 = Skins3d.method13();
      if (!skins3d6.isEnabled()) {
         return null;
      }

      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] items7 = skins3d6.method34().method4(bridgeextension2222, flag3);
      if (items7 == null) {
         return null;
      }

      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg38;
      if (flag4) {
         pkg38 = items7[2];
      } else {
         pkg38 = items7[3];
      }

      pkg38.method1(bridge2_460);
      if (!flag3) {
         pkg38.x -= 0.4F;
      }

      pkg38.y += 0.4F;
      pkg38.x -= 0.6F;
      float value9 = value5 - 1.0F;
      if (value9 > 0.0F) {
         pkg38.y += 0.5F * value9 / 0.4F;
         pkg38.x += 2.0F * value9 / 0.4F;
      }

      return pkg38;
   }

   @VersionGate(min = 33)
   public static void method2(
      BridgeExtension2_11 bridgeextension2_110, RenderTypeBridge bridge201, int number2, ModelRendererBridge bridge2_463, boolean flag4, EntityPlayerBridge bridgeextension2225, boolean flag6, boolean flag7
   ) {
      Skins3d skins3d8 = Skins3d.method13();
      float value9 = skins3d8.method3(skins3d8.method26(), skins3d8.method2(bridgeextension2225));
      float value10 = 1.0F;
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg311 = method1(bridge2_463, flag4, bridgeextension2225, flag6, flag7, value9);
      if (pkg311 != null) {
         bridgeextension2_110.push();
         bridgeextension2_110.scale(value9, value10, value9);
         VertexConsumerBridge bridge4_612 = (VertexConsumerBridge)bridgeextension2_110.method2(bridge201).orElseThrow();
         Bridge5_16 bridge5_1613 = bridgeextension2_110.method51();
         pkg311.method4(bridge5_1613, bridge4_612, number2, 655360);
         bridgeextension2_110.method33(bridge201);
         bridgeextension2_110.pop();
         pkg311.method2(0.0F, 0.0F, 0.0F);
         pkg311.method3(0.0F, 0.0F, 0.0F);
      }
   }

   @VersionGate(min = 6)
   public static void method3(Bridge5_16 bridge5_160, VertexConsumerBridge bridge4_61, int number2, ModelRendererBridge bridge2_463, boolean flag4, EntityPlayerBridge bridgeextension2225, boolean flag6, boolean flag7) {
      Skins3d skins3d8 = Skins3d.method13();
      float value9 = skins3d8.method3(skins3d8.method26(), skins3d8.method2(bridgeextension2225));
      float value10 = 1.0F;
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg311 = method1(bridge2_463, flag4, bridgeextension2225, flag6, flag7, value9);
      if (pkg311 != null) {
         bridge5_160.bridge$pushPose();
         bridge5_160.bridge$scale(value9, value10, value9);
         pkg311.method4(bridge5_160, bridge4_61, number2, 655360);
         bridge5_160.bridge$popPose();
         pkg311.method2(0.0F, 0.0F, 0.0F);
         pkg311.method3(0.0F, 0.0F, 0.0F);
      }
   }

   @VersionGate(max = 5)
   public static void method4(BridgeExtension3_5 bridgeextension3_50, float value1, ModelRendererBridge bridge2_462, boolean flag3, EntityPlayerBridge bridgeextension2224, boolean flag5, boolean flag6) {
      Skins3d skins3d7 = Skins3d.method13();
      float value8 = skins3d7.method3(skins3d7.method26(), skins3d7.method2(bridgeextension2224));
      float value9 = 1.0F;
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg310 = method1(bridge2_462, flag3, bridgeextension2224, flag5, flag6, value8);
      if (pkg310 != null) {
         bridgeextension3_50.push();
         bridgeextension3_50.scale(value8, value9, value8);
         pkg310.method6(bridgeextension3_50, value1, bridgeextension2224.bridge$getLocationSkin());
         bridgeextension3_50.pop();
      }
   }
}
