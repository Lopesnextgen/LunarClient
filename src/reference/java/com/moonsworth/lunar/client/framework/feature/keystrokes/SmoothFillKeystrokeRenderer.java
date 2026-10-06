package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class SmoothFillKeystrokeRenderer implements KeystrokeRenderer {
   public SmoothFillKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixinCore9, float value, int value2, MixinHelper_4 mixinhelper_44) {
      float value5 = mixinCore9.getWidth() * value;
      float value6 = mixinCore9.getHeight() * value;
      float value7 = (mixinCore9.getWidth() - value5) / 2.0F;
      float value8 = (mixinCore9.getHeight() - value6) / 2.0F;
      float value9 = Math.min(value5, value6);
      float value10;
      if (value > 0.5) {
         value10 = value9 / 2.0F * (1.0F - value) / 0.5F;
      } else {
         value10 = value9 / 2.0F;
      }

      LcuiScreen.method117(mixinhelper_44, value7, value8, value5, value6, value10, value2);
   }
}
