package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.client.util.math.MathUtils;

public class FullKeystrokeTimer implements KeystrokeTimer {
   private final long start = System.nanoTime();
   private final long field1;

   public FullKeystrokeTimer(double value1) {
      this.field1 = (long)(value1 * 1.0E9);
   }

   @Override
   public float method1() {
      float value1 = this.method2();
      if (value1 > 1.0F) {
         value1 = 2.0F - value1;
      }

      return value1;
   }

   @Override
   public float method2() {
      long number1 = System.nanoTime() - this.start;
      double value3 = (double)number1 / this.field1;
      value3 *= 2.0;
      return MathUtils.method1((float)value3, 0.0F, 2.0F);
   }

   @Override
   public void method3() {
   }

   @Override
   public void method4() {
   }
}
