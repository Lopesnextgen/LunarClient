package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.client.util.ThreadModuleDump67;

public class Keystrokes2Handler implements Keystrokes2 {
   private final long start = System.nanoTime();
   private final long field1;

   public Keystrokes2Handler(double var1) {
      this.field1 = (long)(var1 * 1.0E9);
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
      return ThreadModuleDump67.method1((float)var3, 0.0F, 2.0F);
   }

   @Override
   public void method3() {
   }

   @Override
   public void method4() {
   }
}
