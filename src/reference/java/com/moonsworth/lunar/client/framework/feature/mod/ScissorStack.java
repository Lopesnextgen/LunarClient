package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class ScissorStack extends GuiComponent {
   private static final IntRectangle field2 = new IntRectangle(-5000, -5000, 0, 0);
   private boolean field3 = false;

   public ScissorStack(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   public void method1(int number1, int number2, int number3, int number4) {
      if (this.method1().field4 == null) {
         this.method2(number1, number2, number3, number4);
      } else {
         IntRectangle threadmoduledump705 = this.method1().field4;
         this.method1().field4 = threadmoduledump705.method8(IntRectangle.method3(number1, number2, number3, number4));
         if (this.method1().field4 == null) {
            this.method1().field4 = field2;
         }

         this.method17();
      }
   }

   public void method2(int number1, int number2, int number3, int number4) {
      this.method1().field4 = IntRectangle.method3(number1, number2, number3, number4);
      this.method17();
   }

   private void method17() {
      this.method6().method17();
      if (this.field3) {
         this.method16().method44(arg0 -> LcuiScreen.method114(arg0.method29()));
         this.method16().method45(arg0 -> arg0.method28());
         this.field3 = false;
      }

      if (this.method2().method18() <= 0 && this.method1().field4 != null) {
         IntRectangle threadmoduledump701 = this.method1().field4;
         this.method16()
            .method44(
               arg2 -> LcuiScreen.method113(
                  arg2.method29(),
                  (int)(threadmoduledump701.method10() * this.method1().field9),
                  (int)(threadmoduledump701.method11() * this.method1().field10),
                  (int)(threadmoduledump701.method12() * this.method1().field9),
                  (int)(threadmoduledump701.method13() * this.method1().field10),
                  LcuiScreen.method151().method3(),
                  LcuiScreen.method151().getScaledHeight()
               )
            );
         this.method16().method45(arg1x -> arg1x.method27(threadmoduledump701.method10(), threadmoduledump701.method11(), threadmoduledump701.method12(), threadmoduledump701.method13()));
         this.field3 = true;
      }
   }

   public void update() {
      this.method17();
   }

   public boolean method4(int number1, int number2) {
      return this.method1().field4 == null ? true : this.method1().field4.method9(number1, number2);
   }

   public boolean method5(int number1, int number2, int number3, int number4) {
      if (!this.method1().field8) {
         return false;
      }

      int number5 = 0;
      int number6 = 0;
      int number7 = (int)(LcuiScreen.method151().getScaledWidth() / this.method1().field9);
      int number8 = (int)(LcuiScreen.method151().getScaledHeight() / this.method1().field10);
      if (this.method1().field4 != null) {
         number5 = this.method1().field4.method10();
         number6 = this.method1().field4.method11();
         number7 = this.method1().field4.method1();
         number8 = this.method1().field4.method2();
      }

      return number1 + number3 < number5 || number2 + number4 < number6 || number1 > number5 + number7 || number2 > number6 + number8;
   }

   public IntRectangle method6(IntRectangle threadmoduledump701) {
      return this.method1().field4 == null ? threadmoduledump701 : threadmoduledump701.method8(this.method1().field4);
   }
}
