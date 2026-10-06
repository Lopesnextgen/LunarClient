package com.moonsworth.lunar.client.framework.feature.keystrokes;

public class SyncedKeystrokeTimer implements KeystrokeTimer {
   private final long field1;
   private long start;
   private double field2;
   private boolean pressed;

   public SyncedKeystrokeTimer(double value1) {
      this.field1 = (long)(value1 * 1.0E9);
   }

   @Override
   public void method3() {
      this.field2 = this.method1();
      this.pressed = true;
      this.start = System.nanoTime();
   }

   @Override
   public void method4() {
      this.field2 = 1.0 + (1.0F - this.method1());
      this.pressed = false;
      this.start = System.nanoTime();
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
      value3 += this.field2;
      if (value3 > 1.0 && this.pressed) {
         return 1.0F;
      } else {
         return value3 > 2.0 ? 2.0F : (float)value3;
      }
   }
}
