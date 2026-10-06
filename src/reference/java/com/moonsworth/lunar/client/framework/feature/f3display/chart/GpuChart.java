package com.moonsworth.lunar.client.framework.feature.f3display.chart;

import com.moonsworth.lunar.client.framework.Ref;

public class GpuChart extends F3Chart {
   private static final long field8 = 100L;
   public long field9 = 0L;

   public GpuChart() {
      super(240, "GPU");
      this.ROHRHIOHRHRHIOIOCROCHRRHHCHHOO = 100;
      this.method3(12, "1.19");
   }

   @Override
   protected int[] method8() {
      int number1 = this.method9();
      return number1 >= 120 ? new int[]{100} : new int[]{25, 50};
   }

   @Override
   public String method11(int number1) {
      return number1 + "%";
   }

   public void method3() {
      long number1 = System.currentTimeMillis();
      if (number1 - this.field9 >= 100L) {
         this.field9 = number1;
         this.method2((int)Ref.method3().bridge$getGpuUtilization());
      }
   }
}
