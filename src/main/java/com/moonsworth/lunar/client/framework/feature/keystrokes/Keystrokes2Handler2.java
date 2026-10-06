package com.moonsworth.lunar.client.framework.feature.keystrokes;

import com.moonsworth.lunar.client.util.ThreadModuleDump67;

public class Keystrokes2Handler2 implements Keystrokes2 {
   private final long field1;
   private final long field2;

   public Keystrokes2Handler2(double value) {
      this.field1 = (long)(value * 1.0E9);
      this.field2 = System.nanoTime();
   }

   @Override
   public float method1() {
      return ThreadModuleDump67.method1((float)((double)(System.nanoTime() - this.field2) / this.field1), 0.0F, 1.0F);
   }

   @Override
   public float method2() {
      return this.method1();
   }

   @Override
   public void method3() {
   }

   @Override
   public void method4() {
   }

   @Override
   public boolean isDone() {
      return this.method1() == 1.0F;
   }
}
