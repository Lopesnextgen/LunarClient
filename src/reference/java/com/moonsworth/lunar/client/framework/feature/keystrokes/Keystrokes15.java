package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class Keystrokes15 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4) {
      float var5 = (1.0F - var2) * var1.getWidth();
      float var6 = (1.0F - var2) * var1.getHeight();
      this.method1(var4, var5, 0.0F, var1.getWidth(), var1.getHeight() - var6, var1.getWidth(), 0.0F, var3);
      this.method1(var4, 0.0F, var1.getHeight(), var1.getWidth() - var5, var1.getHeight(), 0.0F, var6, var3);
      if (var2 != 1.0F) {
         this.method1(var4, var5, 0.0F, 0.0F, var6, var1.getWidth(), var1.getHeight() - var6, var3);
         this.method1(var4, 0.0F, var6, var1.getWidth() - var5, var1.getHeight(), var1.getWidth(), var1.getHeight() - var6, var3);
      }
   }
}
