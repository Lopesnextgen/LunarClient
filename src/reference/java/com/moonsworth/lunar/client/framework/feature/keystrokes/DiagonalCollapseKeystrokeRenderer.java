package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class DiagonalCollapseKeystrokeRenderer implements KeystrokeRenderer {
   public DiagonalCollapseKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = value2 * mixincore9extension1.getWidth();
      float value6 = value2 * mixincore9extension1.getHeight();
      this.method1(mixinhelper_44, 0.0F, 0.0F, 0.0F, value6, value5, 0.0F, number3);
      this.method1(mixinhelper_44, mixincore9extension1.getWidth(), mixincore9extension1.getHeight() - value6, mixincore9extension1.getWidth() - value5, mixincore9extension1.getHeight(), mixincore9extension1.getWidth(), mixincore9extension1.getHeight(), number3);
   }
}
