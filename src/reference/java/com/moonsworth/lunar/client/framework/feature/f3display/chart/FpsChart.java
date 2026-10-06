package com.moonsworth.lunar.client.framework.feature.f3display.chart;

public class FpsChart extends F3Chart {
   private static final long field8 = 50L;
   private int frames = 0;
   private long field9 = 0L;

   public FpsChart() {
      super(240, "FPS");
      this.ROHRHIOHRHRHIOIOCROCHRRHHCHHOO = 60;
   }

   @Override
   protected int[] method8() {
      int number1 = this.method9();
      if (number1 > 1100) {
         return new int[]{1000};
      } else if (number1 > 600) {
         return new int[]{500};
      } else if (number1 > 300) {
         return new int[]{240};
      } else if (number1 > 240) {
         return new int[]{120};
      } else if (number1 > 120) {
         return new int[]{60};
      } else if (number1 > 90) {
         return new int[]{30, 60};
      } else if (number1 > 45) {
         return new int[]{30};
      } else if (number1 > 30) {
         return new int[]{15};
      } else {
         return number1 > 15 ? new int[]{10} : null;
      }
   }

   @Override
   public String method11(int number1) {
      return number1 + " FPS";
   }

   public void method3() {
      this.field9 = 0L;
      this.frames = 0;
   }

   public void method4() {
      long number1 = System.currentTimeMillis();
      if (this.field9 == 0L) {
         this.field9 = number1;
      }

      this.frames++;
      long number3 = number1 - this.field9;
      if (number3 >= 50L) {
         float value5 = 1000.0F / (float)number3;
         int number6 = (int)(this.frames * value5);
         this.frames = 0;
         this.field9 = number1;
         this.method2(number6);
      }
   }
}
