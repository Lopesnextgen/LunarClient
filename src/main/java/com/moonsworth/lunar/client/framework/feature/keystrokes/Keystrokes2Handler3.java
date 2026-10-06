package com.moonsworth.lunar.client.framework.feature.keystrokes;

public class Keystrokes2Handler3 implements Keystrokes2 {
   private final long field1;
   private long start;
   private double field2;
   private boolean pressed;

   public Keystrokes2Handler3(double var1) {
      this.field1 = (long)(var1 * 1.0E9);
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
      float var1 = this.method2();
      if (var1 > 1.0F) {
         var1 = 2.0F - var1;
      }

      return var1;
   }

   @Override
   public float method2() {
      long var1 = System.nanoTime() - this.start;
      double var3 = (double)var1 / this.field1;
      var3 *= 2.0;
      var3 += this.field2;
      if (var3 > 1.0 && this.pressed) {
         return 1.0F;
      } else {
         return var3 > 2.0 ? 2.0F : (float)var3;
      }
   }
}
