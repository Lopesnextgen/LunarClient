package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class ScaledTextRenderer extends GuiComponent {
   public ScaledTextRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   public void method1(String text1, int number2, int number3, boolean flag4, float value5) {
      if (!(value5 < 0.001)) {
         if (this.method1().field7) {
            this.method6().method17();
            float value6 = this.getStringWidth(text1) * value5;
            number2 = (int)((number2 - value6 / 2.0F + 1.0F) / value5);
            number3 = (int)((number3 - 5) / value5);
            int number7 = number2;
            int number8 = number3;
            Runnable runnable9 = () -> {
               this.method16().push();
               this.method16().method40(value5, value5);
               this.method16().method18(Ref.method10(), text1, number7, number8, -1, flag4);
               this.method16().pop();
            };
            if (Ref.MC_VERSION <= 29 && Ref.MC_VERSION >= 6) {
               IntRectangle threadmoduledump7010 = IntRectangle.method3(
                  (int)(number2 * value5), (int)(number3 * value5), (int)Math.ceil(value6), (int)Math.ceil(Ref.method10().method19() * value5)
               );
               this.method14().method2(threadmoduledump7010, runnable9);
               this.method14().method1(threadmoduledump7010, 0.0F);
            } else {
               runnable9.run();
            }
         }
      }
   }

   public void method2(String text1, int number2, int number3, boolean flag4, float value5) {
      if (!(value5 < 0.001)) {
         if (this.method1().field7) {
            this.method6().method17();
            Runnable runnable6 = () -> {
               this.method16().push();
               this.method16().method40(value5, value5);
               this.method16().method19(Ref.method10(), text1, number2 / value5, number3 / value5, -1, flag4);
               this.method16().pop();
            };
            if (Ref.MC_VERSION <= 29 && Ref.MC_VERSION >= 6) {
               float value7 = this.getStringWidth(text1) * value5;
               IntRectangle threadmoduledump708 = IntRectangle.method3(
                  number2, number3, (int)Math.ceil(value7), (int)Math.ceil(Ref.method10().method19() * value5)
               );
               this.method14().method2(threadmoduledump708, runnable6);
               this.method14().method1(threadmoduledump708, 0.0F);
            } else {
               runnable6.run();
            }
         }
      }
   }

   public int getStringWidth(String text1) {
      return (int)Ref.method10().bridge$getStringWidth(text1);
   }

   public float method3(String text1, int number2, int number3) {
      float value4 = (float)number2 / this.getStringWidth(text1);
      float value5 = number3 / 10.0F;
      return Math.max(0.0F, Math.min(value4, value5));
   }
}
