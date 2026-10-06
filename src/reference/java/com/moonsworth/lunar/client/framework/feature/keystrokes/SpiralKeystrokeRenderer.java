package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class SpiralKeystrokeRenderer implements KeystrokeRenderer {
   public SpiralKeystrokeRenderer() {
   }

   @Override
   public void method1(MixinCore9Extension mixincore9extension1, float value2, int number3, MixinHelper_4 mixinhelper_44) {
      mixinhelper_44.push();
      float value5 = 5 * Math.round(mixincore9extension1.getWidth() / mixincore9extension1.getHeight());
      float value6 = 5.0F;
      mixinhelper_44.scale(mixincore9extension1.getWidth() / value5, mixincore9extension1.getHeight() / value6, 1.0F);
      int index7 = (int)value5;
      int index8 = (int)value6;
      int number9 = index7 * index8;
      int number10 = (int)Math.ceil(number9 * value2);
      int number11 = number10;
      int index12 = 0;
      int index13 = 0;

      for (int index14 = 0; number10 > 0; index14++) {
         int number15 = number11 - number10;
         switch (index14 % 4) {
            case 0:
               float value21 = (float)index7 / number9;
               float value24 = (float)number15 / number9;
               float value27 = MathUtils.method1((value2 - value24) / value21, 0.0F, 1.0F);
               this.method1(mixinhelper_44, index13, index12, index7 * value27, 1.0F, number3);
               index12++;
               index8--;
               number10 -= index7;
               break;
            case 1:
               float value20 = (float)index8 / number9;
               float value23 = (float)number15 / number9;
               float value26 = MathUtils.method1((value2 - value23) / value20, 0.0F, 1.0F);
               this.method1(mixinhelper_44, index13 + index7 - 1, index12, 1.0F, index8 * value26, number3);
               index7--;
               number10 -= index8;
               break;
            case 2:
               float value19 = (float)index7 / number9;
               float value22 = (float)number15 / number9;
               float value25 = MathUtils.method1((value2 - value22) / value19, 0.0F, 1.0F);
               this.method1(mixinhelper_44, index13, index12 + index8 - 1, index7 * value25, 1.0F, number3);
               index8--;
               number10 -= index7;
               break;
            case 3:
               float value16 = (float)index8 / number9;
               float value17 = (float)number15 / number9;
               float value18 = MathUtils.method1((value2 - value17) / value16, 0.0F, 1.0F);
               this.method1(mixinhelper_44, index13, index12, 1.0F, index8 * value18, number3);
               index13++;
               index7--;
               number10 -= index8;
         }
      }

      mixinhelper_44.pop();
   }
}
