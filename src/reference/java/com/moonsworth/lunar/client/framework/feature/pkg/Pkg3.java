package com.moonsworth.lunar.client.framework.feature.pkg;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.ichor.Annotation2;

public class Pkg3 {
   private static com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method1(
      Bridge2_46 var0, boolean var1, EntityPlayerBridge var2, boolean var3, boolean var4, float var5
   ) {
      if (var1) {
         return null;
      }

      Skins3d var6 = Skins3d.method13();
      if (!var6.isEnabled()) {
         return null;
      }

      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] var7 = var6.method34().method4(var2, var3);
      if (var7 == null) {
         return null;
      }

      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var8;
      if (var4) {
         var8 = var7[2];
      } else {
         var8 = var7[3];
      }

      var8.method1(var0);
      if (!var3) {
         var8.x -= 0.4F;
      }

      var8.y += 0.4F;
      var8.x -= 0.6F;
      float var9 = var5 - 1.0F;
      if (var9 > 0.0F) {
         var8.y += 0.5F * var9 / 0.4F;
         var8.x += 2.0F * var9 / 0.4F;
      }

      return var8;
   }

   @Annotation2(min = 33)
   public static void method2(
      BridgeExtension2_11 var0, RenderLayerBridge var1, int var2, Bridge2_46 var3, boolean var4, EntityPlayerBridge var5, boolean var6, boolean var7
   ) {
      Skins3d var8 = Skins3d.method13();
      float var9 = var8.method3(var8.method26(), var8.method2(var5));
      float var10 = 1.0F;
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var11 = method1(var3, var4, var5, var6, var7, var9);
      if (var11 != null) {
         var0.push();
         var0.scale(var9, var10, var9);
         Bridge4_6 var12 = (Bridge4_6)var0.method2(var1).orElseThrow();
         Bridge5_16 var13 = var0.method51();
         var11.method4(var13, var12, var2, 655360);
         var0.method33(var1);
         var0.pop();
         var11.method2(0.0F, 0.0F, 0.0F);
         var11.method3(0.0F, 0.0F, 0.0F);
      }
   }

   @Annotation2(min = 6)
   public static void method3(Bridge5_16 var0, Bridge4_6 var1, int var2, Bridge2_46 var3, boolean var4, EntityPlayerBridge var5, boolean var6, boolean var7) {
      Skins3d var8 = Skins3d.method13();
      float var9 = var8.method3(var8.method26(), var8.method2(var5));
      float var10 = 1.0F;
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var11 = method1(var3, var4, var5, var6, var7, var9);
      if (var11 != null) {
         var0.bridge$pushPose();
         var0.bridge$scale(var9, var10, var9);
         var11.method4(var0, var1, var2, 655360);
         var0.bridge$popPose();
         var11.method2(0.0F, 0.0F, 0.0F);
         var11.method3(0.0F, 0.0F, 0.0F);
      }
   }

   @Annotation2(max = 5)
   public static void method4(BridgeExtension3_5 var0, float var1, Bridge2_46 var2, boolean var3, EntityPlayerBridge var4, boolean var5, boolean var6) {
      Skins3d var7 = Skins3d.method13();
      float var8 = var7.method3(var7.method26(), var7.method2(var4));
      float var9 = 1.0F;
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var10 = method1(var2, var3, var4, var5, var6, var8);
      if (var10 != null) {
         var0.push();
         var0.scale(var8, var9, var8);
         var10.method6(var0, var1, var4.bridge$getLocationSkin());
         var0.pop();
      }
   }
}
