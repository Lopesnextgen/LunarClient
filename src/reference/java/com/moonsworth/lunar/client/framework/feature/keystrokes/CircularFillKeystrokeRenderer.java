package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class CircularFillKeystrokeRenderer implements KeystrokeRenderer {
   public CircularFillKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = mixincore9extension1.getWidth() / 2.0F;
      float value6 = mixincore9extension1.getHeight() / 2.0F;
      float value7 = MathUtils.method1(mixincore9extension1.getWidth() * value2 * 4.0F, 0.0F, mixincore9extension1.getWidth());
      this.method1(mixinhelper_44, value5, value6, value7, 0.0F, 0.0F, 0.0F, number3);
      if (value2 > 0.25F) {
         float value8 = MathUtils.method1(mixincore9extension1.getHeight() * (value2 - 0.25F) * 4.0F, 0.0F, mixincore9extension1.getHeight());
         this.method1(mixinhelper_44, value5, value6, mixincore9extension1.getWidth(), value8, mixincore9extension1.getWidth(), 0.0F, number3);
      }

      if (value2 > 0.5F) {
         float value9 = mixincore9extension1.getWidth() - MathUtils.method1(mixincore9extension1.getWidth() * (value2 - 0.5F) * 4.0F, 0.0F, mixincore9extension1.getWidth());
         this.method1(mixinhelper_44, value5, value6, value9, mixincore9extension1.getHeight(), mixincore9extension1.getWidth(), mixincore9extension1.getHeight(), number3);
      }

      if (value2 > 0.75F) {
         float value10 = mixincore9extension1.getHeight() - MathUtils.method1(mixincore9extension1.getHeight() * (value2 - 0.75F) * 4.0F, 0.0F, mixincore9extension1.getHeight());
         this.method1(mixinhelper_44, value5, value6, 0.0F, value10, 0.0F, mixincore9extension1.getHeight(), number3);
      }
   }
}
