package com.moonsworth.lunar.client.framework.feature.f3display.chart;

public class BandwidthChart extends F3Chart {
   private static final long field8 = 50L;
   public long field9 = 0L;
   private long field10 = 0L;

   public BandwidthChart() {
      super(240, "");
      this.ROHRHIOHRHRHIOIOCROCHRRHHCHHOO = 2000;
   }

   @Override
   public String method11(int number1) {
      switch (number1) {
         case 600:
            return "64 B/s";
         case 1000:
            return "1 KiB/s";
         case 1400:
            return "16 KiB/s";
         case 2000:
            return "1 MiB/s";
         default:
            int number2 = (int)Math.pow(2.0, number1 / 100.0);
            if (number2 < 1024) {
               return number2 + " B/s";
            } else if (number2 >> 10 < 1024) {
               double value8 = number2 / 1024.0;
               double value10 = (int)(value8 * 10.0) / 10.0;
               return value10 + " KiB/s";
            } else if (number2 >> 20 < 1024) {
               double value7 = number2 / 1048576.0;
               double value9 = (int)(value7 * 10.0) / 10.0;
               return value9 + " MiB/s";
            } else {
               double value3 = number2 / 1.0737418E9F;
               double value5 = (int)(value3 * 10.0) / 10.0;
               return value5 + " GiB/s";
            }
      }
   }

   @Override
   protected int[] method8() {
      int number1 = this.method9();
      return number1 >= 2300 ? new int[]{2000} : new int[]{600, 1000, 1400};
   }

   public void method3() {
      long number1 = System.currentTimeMillis();
      if (this.field9 == 0L) {
         this.field9 = number1;
      } else {
         long number3 = number1 - this.field9;
         if (number3 > 50L) {
            double value5 = 1000.0 / number3;
            double value7 = this.field10 * value5;
            this.field10 = 0L;
            this.field9 = number1;
            double value9 = Math.log(value7) * 1.44269504089;
            this.method2((int)(value9 * 100.0));
         }
      }
   }

   public void method5(int number1) {
      this.field10 += number1;
   }

   public int method6(int number1) {
      for (int index2 = 1; index2 < 5; index2++) {
         if ((number1 & -1 << index2 * 7) == 0) {
            return index2;
         }
      }

      return 5;
   }
}
