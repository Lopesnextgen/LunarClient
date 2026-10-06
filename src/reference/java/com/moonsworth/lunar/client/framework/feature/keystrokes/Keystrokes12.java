package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class Keystrokes12 implements Keystrokes3 {
   @Override
   public void method1(MixinCore9Extension var1, float var2, int var3, MixinHelper_4 var4) {
      var4.push();
      var4.method38(var1.getWidth() / 2.0F, var1.getHeight() / 2.0F, 0.0F);
      this.method1(var4, 0.0F, 0.0F, 0.0F, -var1.getHeight() / 2.0F, -var1.getWidth() / 2.0F, -var1.getHeight() / 2.0F, var3);
      if (var2 > 0.125F) {
         this.method1(var4, 0.0F, 0.0F, var1.getWidth() / 2.0F, -var1.getHeight() / 2.0F, 0.0F, -var1.getHeight() / 2.0F, var3);
      }

      if (var2 > 0.25F) {
         this.method1(var4, 0.0F, 0.0F, var1.getWidth() / 2.0F, 0.0F, var1.getWidth() / 2.0F, -var1.getHeight() / 2.0F, var3);
      }

      if (var2 > 0.375F) {
         this.method1(var4, 0.0F, 0.0F, var1.getWidth() / 2.0F, var1.getHeight() / 2.0F, var1.getWidth() / 2.0F, 0.0F, var3);
      }

      if (var2 > 0.5F) {
         this.method1(var4, 0.0F, 0.0F, 0.0F, var1.getHeight() / 2.0F, var1.getWidth() / 2.0F, var1.getHeight() / 2.0F, var3);
      }

      if (var2 > 0.675F) {
         this.method1(var4, 0.0F, 0.0F, -var1.getWidth() / 2.0F, var1.getHeight() / 2.0F, 0.0F, var1.getHeight() / 2.0F, var3);
      }

      if (var2 > 0.75F) {
         this.method1(var4, 0.0F, 0.0F, -var1.getWidth() / 2.0F, 0.0F, -var1.getWidth() / 2.0F, var1.getHeight() / 2.0F, var3);
      }

      if (var2 > 0.875F) {
         this.method1(var4, 0.0F, 0.0F, -var1.getWidth() / 2.0F, -var1.getHeight() / 2.0F, -var1.getWidth() / 2.0F, 0.0F, var3);
      }

      var4.pop();
   }
}
