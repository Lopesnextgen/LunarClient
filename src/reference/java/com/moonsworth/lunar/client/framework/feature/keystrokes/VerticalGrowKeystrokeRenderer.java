package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class VerticalGrowKeystrokeRenderer implements KeystrokeRenderer {
   public VerticalGrowKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = mixincore9extension1.getHeight() * value2;
      float value6 = mixincore9extension1.getHeight() - value5;
      this.method1(mixinhelper_44, 0.0F, value6 / 2.0F, mixincore9extension1.getWidth(), value5, number3);
   }
}
