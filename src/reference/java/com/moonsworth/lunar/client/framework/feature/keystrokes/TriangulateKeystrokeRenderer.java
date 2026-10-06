package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class TriangulateKeystrokeRenderer implements KeystrokeRenderer {
   public TriangulateKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      mixinhelper_44.push();
      mixinhelper_44.method38(mixincore9extension1.getWidth() / 2.0F, mixincore9extension1.getHeight() / 2.0F, 0.0F);
      this.method1(mixinhelper_44, 0.0F, 0.0F, 0.0F, -mixincore9extension1.getHeight() / 2.0F, -mixincore9extension1.getWidth() / 2.0F, -mixincore9extension1.getHeight() / 2.0F, number3);
      if (value2 > 0.125F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, mixincore9extension1.getWidth() / 2.0F, -mixincore9extension1.getHeight() / 2.0F, 0.0F, -mixincore9extension1.getHeight() / 2.0F, number3);
      }

      if (value2 > 0.25F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, mixincore9extension1.getWidth() / 2.0F, 0.0F, mixincore9extension1.getWidth() / 2.0F, -mixincore9extension1.getHeight() / 2.0F, number3);
      }

      if (value2 > 0.375F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, mixincore9extension1.getWidth() / 2.0F, mixincore9extension1.getHeight() / 2.0F, mixincore9extension1.getWidth() / 2.0F, 0.0F, number3);
      }

      if (value2 > 0.5F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, 0.0F, mixincore9extension1.getHeight() / 2.0F, mixincore9extension1.getWidth() / 2.0F, mixincore9extension1.getHeight() / 2.0F, number3);
      }

      if (value2 > 0.675F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, -mixincore9extension1.getWidth() / 2.0F, mixincore9extension1.getHeight() / 2.0F, 0.0F, mixincore9extension1.getHeight() / 2.0F, number3);
      }

      if (value2 > 0.75F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, -mixincore9extension1.getWidth() / 2.0F, 0.0F, -mixincore9extension1.getWidth() / 2.0F, mixincore9extension1.getHeight() / 2.0F, number3);
      }

      if (value2 > 0.875F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, -mixincore9extension1.getWidth() / 2.0F, -mixincore9extension1.getHeight() / 2.0F, -mixincore9extension1.getWidth() / 2.0F, 0.0F, number3);
      }

      mixinhelper_44.pop();
   }
}
