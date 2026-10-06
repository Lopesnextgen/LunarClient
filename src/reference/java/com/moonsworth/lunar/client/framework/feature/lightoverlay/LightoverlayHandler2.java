package com.moonsworth.lunar.client.framework.feature.lightoverlay;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.client.mod.render.lightoverlay.LightOverlay;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

class LightoverlayHandler2 implements Lightoverlay {
   private final LightOverlay field1;
   private final Bridge2_32 field2;
   private final LightOverlay.Type field3;
   private final int field4;
   private final double field5;

   protected LightoverlayHandler2(AbstractRenderContext var1, LightOverlay var2) {
      this.field1 = var2;
      this.field2 = var1.method10(LunarRenderTypes.field62);
      this.field2.method1();
      this.field3 = var2.lightOverlayMode.get();
      this.field4 = var2.getSkylightSubtract();
      this.field5 = Math.toRadians(ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher().bridge$playerViewY() + 180.0);
   }

   @Override
   public void method1(double var1, double var3, double var5, byte var7, byte var8) {
      int var9;
      if (this.field3 == LightOverlay.Type.NONE) {
         var9 = this.field1.method15(var7, var8, this.field4, (float)(var1 + var5));
      } else {
         var9 = this.field1.field22.method14((float)(var1 + var5));
      }

      int var10 = LightOverlay.method17(var7, var8, this.field4);
      if (var10 > 9) {
         this.method2(var1, var3, var5, 1, -0.38, var9);
         this.method2(var1, var3, var5, var10 - 10, 0.38, var9);
      } else {
         this.method2(var1, var3, var5, var10, 0.0, var9);
      }
   }

   @Override
   public void end() {
      this.field2.method17(BufferBuildMode.IMMEDIATE);
   }

   private void method2(double var1, double var3, double var5, int var7, double var8, int var10) {
      float var11 = var7 * 8.0F / 128.0F;
      float var12 = 0.1875F;
      float var13 = var11 + 0.0546875F;
      float var14 = 0.2421875F;
      this.method3(0.0 + var8, 0.0, 0.0, var11, var12, var10, var1, var3, var5);
      this.method3(0.0 + var8, 0.0, 1.0, var11, var14, var10, var1, var3, var5);
      this.method3(1.0 + var8, 0.0, 1.0, var13, var14, var10, var1, var3, var5);
      this.method3(1.0 + var8, 0.0, 0.0, var13, var12, var10, var1, var3, var5);
   }

   private void method3(double var1, double var3, double var5, float var7, float var8, int var9, double var10, double var12, double var14) {
      var1 *= 0.5;
      var5 *= 0.5;
      var1 -= 0.18;
      var5 -= 0.25;
      double var16 = MathHelperBridge.method2(this.field5);
      double var18 = MathHelperBridge.method1(this.field5);
      double var20 = var1 * var18 - var5 * var16;
      double var22 = var1 * var16 + var5 * var18;
      var1 = var20;
      var5 = var22;
      var1 += var10 + 0.5;
      var3 += var12;
      var5 += var14 + 0.5;
      this.field2.method2(var1, var3, var5).method10(var7, var8).method9(var9).method16();
   }
}
