package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class HorizontalGrowKeystrokeRenderer implements KeystrokeRenderer {
   public HorizontalGrowKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = mixincore9extension1.getWidth() * value2;
      float value6 = mixincore9extension1.getWidth() - value5;
      this.method1(mixinhelper_44, value6 / 2.0F, 0.0F, value5, mixincore9extension1.getHeight(), number3);
   }
}
