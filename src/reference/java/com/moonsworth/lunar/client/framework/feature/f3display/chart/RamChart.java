package com.moonsworth.lunar.client.framework.feature.f3display.chart;

public class RamChart extends F3Chart {
   public long field8 = 0L;

   public RamChart() {
      super(240, "RAM");
      this.method4(false);
   }

   @Override
   protected int[] method8() {
      int number1 = this.method9();
      int number2 = number1 / 1024;
      if (number2 == 0) {
         if (number1 >= 800) {
            return new int[]{512};
         }

         if (number1 >= 512) {
            return new int[]{256};
         }
      }

      int number3 = 0;
      if (number2 >= 16) {
         number3 = number2 / 2;
      } else if (number2 >= 12) {
         number3 = 8;
      } else if (number2 >= 8) {
         number3 = 6;
      } else if (number2 >= 6) {
         number3 = 4;
      } else if (number2 >= 4) {
         number3 = 2;
      } else if (number2 >= 2) {
         number3 = 1;
      }

      return number3 == 0 ? null : new int[]{number3 * 1024};
   }

   @Override
   public String method11(int number1) {
      if (number1 < 1024) {
         return number1 + " MB";
      }

      double value2 = number1 / 1024.0;
      double value4 = (int)(value2 * 10.0) / 10.0;
      return value4 + " GB";
   }

   public void method3() {
      long number1 = System.currentTimeMillis();
      if (number1 - this.field8 >= 100L) {
         this.field8 = number1;
         Runtime runtime3 = Runtime.getRuntime();
         long number4 = runtime3.totalMemory() - runtime3.freeMemory();
         int number6 = Math.toIntExact(number4 / 1048576L);
         this.method2(number6);
      }
   }

   public void method4(boolean flag1) {
      if (flag1) {
         this.ROHRHIOHRHRHIOIOCROCHRRHHCHHOO = Math.toIntExact(Runtime.getRuntime().maxMemory() / 1048576L);
      } else {
         this.ROHRHIOHRHRHIOIOCROCHRRHHCHHOO = 256;
      }
   }
}
