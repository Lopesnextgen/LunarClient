package com.moonsworth.lunar.client.framework.feature.keystrokes;

public interface KeystrokeTimer {
   float method1();

   float method2();

   void method3();

   void method4();

   default boolean isDone() {
      return this.method2() == 2.0F;
   }
}
