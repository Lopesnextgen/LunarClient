package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class CrossGrowKeystrokeRenderer implements KeystrokeRenderer {
   public CrossGrowKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = mixincore9extension1.getWidth() * value2;
      float value6 = mixincore9extension1.getHeight() * value2;
      float value7 = mixincore9extension1.getWidth() - value5;
      float value8 = mixincore9extension1.getHeight() - value6;
      this.method1(mixinhelper_44, value7 / 2.0F, 0.0F, value5, mixincore9extension1.getHeight(), number3);
      if (value2 != 1.0F) {
         this.method1(mixinhelper_44, 0.0F, value8 / 2.0F, value7 / 2.0F, value6, number3);
         this.method1(mixinhelper_44, mixincore9extension1.getWidth() - value7 / 2.0F, value8 / 2.0F, value7 / 2.0F, value6, number3);
      }
   }
}
