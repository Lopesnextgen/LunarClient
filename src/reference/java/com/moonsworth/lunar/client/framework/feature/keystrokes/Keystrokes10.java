package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class Keystrokes10 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4) {
      float var5 = var1.getWidth() * var2;
      float var6 = var1.getHeight() * var2;
      float var7 = var1.getWidth() - var5;
      float var8 = var1.getHeight() - var6;
      this.method1(var4, var7 / 2.0F, 0.0F, var5, var1.getHeight(), var3);
      if (var2 != 1.0F) {
         this.method1(var4, 0.0F, var8 / 2.0F, var7 / 2.0F, var6, var3);
         this.method1(var4, var1.getWidth() - var7 / 2.0F, var8 / 2.0F, var7 / 2.0F, var6, var3);
      }
   }
}
