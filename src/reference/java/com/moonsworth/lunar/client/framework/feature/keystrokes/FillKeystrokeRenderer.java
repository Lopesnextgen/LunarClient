package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class FillKeystrokeRenderer implements KeystrokeRenderer {
   public FillKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = mixincore9extension1.getWidth() * value2;
      float value6 = mixincore9extension1.getHeight() * value2;
      float value7 = (mixincore9extension1.getWidth() - value5) / 2.0F;
      float value8 = (mixincore9extension1.getHeight() - value6) / 2.0F;
      this.method1(mixinhelper_44, value7, value8, value5, value6, number3);
   }
}
