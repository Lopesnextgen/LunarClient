package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;

public class Keystrokes7 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4) {
      float var5 = var1.getWidth() / 2.0F;
      float var6 = var1.getHeight() / 2.0F;
      float var7 = ThreadModuleDump67.method1(var1.getWidth() * var2 * 4.0F, 0.0F, var1.getWidth());
      this.method1(var4, var5, var6, var7, 0.0F, 0.0F, 0.0F, var3);
      if (var2 > 0.25F) {
         float var8 = ThreadModuleDump67.method1(var1.getHeight() * (var2 - 0.25F) * 4.0F, 0.0F, var1.getHeight());
         this.method1(var4, var5, var6, var1.getWidth(), var8, var1.getWidth(), 0.0F, var3);
      }

      if (var2 > 0.5F) {
         float var9 = var1.getWidth() - ThreadModuleDump67.method1(var1.getWidth() * (var2 - 0.5F) * 4.0F, 0.0F, var1.getWidth());
         this.method1(var4, var5, var6, var9, var1.getHeight(), var1.getWidth(), var1.getHeight(), var3);
      }

      if (var2 > 0.75F) {
         float var10 = var1.getHeight() - ThreadModuleDump67.method1(var1.getHeight() * (var2 - 0.75F) * 4.0F, 0.0F, var1.getHeight());
         this.method1(var4, var5, var6, 0.0F, var10, 0.0F, var1.getHeight(), var3);
      }
   }
}
