package com.moonsworth.lunar.client.framework.feature.f3display.chart;

import com.moonsworth.lunar.client.framework.Ref;

public class PingChart extends F3Chart {
   public PingChart() {
      super(method3(), "Ping");
      this.ROHRHIOHRHRHIOIOCROCHRRHHCHHOO = 250;
      this.method3(19, "1.20.2");
   }

   @Override
   public String method11(int number1) {
      return number1 + "ms";
   }

   @Override
   protected int[] method8() {
      int number1 = this.method9();
      if (number1 > 4000) {
         return new int[]{number1 / 2};
      } else if (number1 > 2000) {
         return new int[]{1000};
      } else if (number1 > 1300) {
         return new int[]{500, 1000};
      } else {
         return number1 > 600 ? new int[]{500} : new int[]{100};
      }
   }

   public static int method3() {
      return Ref.MC_VERSION >= 19 ? 240 : 12;
   }
}
