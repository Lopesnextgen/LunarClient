package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class CrossCollapseKeystrokeRenderer implements KeystrokeRenderer {
   public CrossCollapseKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      if (value2 == 1.0F) {
         this.method1(mixinhelper_44, 0.0F, 0.0F, mixincore9extension1.getWidth(), mixincore9extension1.getHeight(), number3);
      } else {
         float value5 = mixincore9extension1.getWidth() * value2 / 2.0F;
         float value6 = mixincore9extension1.getHeight() * value2 / 2.0F;
         this.method1(mixinhelper_44, 0.0F, 0.0F, value5, value6, number3);
         this.method1(mixinhelper_44, mixincore9extension1.getWidth() - value5, 0.0F, value5, value6, number3);
         this.method1(mixinhelper_44, 0.0F, mixincore9extension1.getHeight() - value6, value5, value6, number3);
         this.method1(mixinhelper_44, mixincore9extension1.getWidth() - value5, mixincore9extension1.getHeight() - value6, value5, value6, number3);
      }
   }
}
