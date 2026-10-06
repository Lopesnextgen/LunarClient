package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;

public class Keystrokes19Impl3 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4) {
      float var5 = var1.getWidth();
      float var6 = var1.getHeight();
      boolean var7 = var5 / 1.5 > var6;
      float var8 = var7 ? var5 : var6;
      float var9 = 0.0F;
      float var10 = 0.0F;
      int var11 = (int)(8.0F + (var7 ? var5 / var6 : var6 / var5));
      float var12 = var8 / var11;
      float var13 = 1.0F / var11;

      for (int var14 = 0; var14 < var11; var14++) {
         if (var7) {
            float var15 = var13 * (var14 + 1);
            float var16 = ThreadModuleDump67.method1(var2 / var15, 0.0F, 1.0F);
            float var17 = var6 * var16 / 2.0F;
            this.method1(var4, var9, var10, Math.min(var12, var8), var17, var3);
            this.method1(var4, var9, var1.getHeight() - var17, Math.min(var12, var8), var17, var3);
            var9 += var12;
            var8 -= var12;
         } else {
            float var18 = var13 * (var14 + 1);
            float var19 = ThreadModuleDump67.method1(var2 / var18, 0.0F, 1.0F);
            float var20 = var5 * var19 / 2.0F;
            this.method1(var4, var9, var10, var20, Math.min(var12, var8), var3);
            this.method1(var4, var1.getWidth() - var20, var10, var20, Math.min(var12, var8), var3);
            var10 += var12;
            var8 -= var12;
         }
      }
   }
}
