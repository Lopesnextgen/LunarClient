package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class ZipperKeystrokeRenderer implements KeystrokeRenderer {
   public ZipperKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      float value5 = mixincore9extension1.getWidth();
      float value6 = mixincore9extension1.getHeight();
      boolean flag7 = value5 / 1.5 > value6;
      float value8 = flag7 ? value5 : value6;
      float value9 = 0.0F;
      float value10 = 0.0F;
      int number11 = (int)(8.0F + (flag7 ? value5 / value6 : value6 / value5));
      float value12 = value8 / number11;
      float value13 = 1.0F / number11;

      for (int index14 = 0; index14 < number11; index14++) {
         if (flag7) {
            float value15 = value13 * (index14 + 1);
            float value16 = MathUtils.method1(value2 / value15, 0.0F, 1.0F);
            float value17 = value6 * value16 / 2.0F;
            this.method1(mixinhelper_44, value9, value10, Math.min(value12, value8), value17, number3);
            this.method1(mixinhelper_44, value9, mixincore9extension1.getHeight() - value17, Math.min(value12, value8), value17, number3);
            value9 += value12;
            value8 -= value12;
         } else {
            float value18 = value13 * (index14 + 1);
            float value19 = MathUtils.method1(value2 / value18, 0.0F, 1.0F);
            float value20 = value5 * value19 / 2.0F;
            this.method1(mixinhelper_44, value9, value10, value20, Math.min(value12, value8), number3);
            this.method1(mixinhelper_44, mixincore9extension1.getWidth() - value20, value10, value20, Math.min(value12, value8), number3);
            value10 += value12;
            value8 -= value12;
         }
      }
   }
}
