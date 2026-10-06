package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class MultiSquareFillKeystrokeRenderer implements KeystrokeRenderer {
   public MultiSquareFillKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      this.method1(mixinhelper_44, 0.0F, 0.0F, mixincore9extension1.getWidth() / 2.0F * value2, mixincore9extension1.getHeight() / 2.0F, number3);
      this.method1(mixinhelper_44, mixincore9extension1.getWidth() / 2.0F, 0.0F, mixincore9extension1.getWidth() / 2.0F, mixincore9extension1.getHeight() / 2.0F * value2, number3);
      float value5 = mixincore9extension1.getWidth() / 2.0F * value2;
      this.method1(mixinhelper_44, mixincore9extension1.getWidth() - value5, mixincore9extension1.getHeight() / 2.0F, value5, mixincore9extension1.getHeight() / 2.0F, number3);
      float value6 = mixincore9extension1.getHeight() / 2.0F * value2;
      this.method1(mixinhelper_44, 0.0F, mixincore9extension1.getHeight() - value6, mixincore9extension1.getWidth() / 2.0F, value6, number3);
   }
}
