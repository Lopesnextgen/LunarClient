package com.moonsworth.lunar.bridge;

public interface Bridge2_23 {
   default void method1() {
      this.method2(7);
   }

   void method2(int var1);

   void method3();

   boolean bridge$isDrawing();
}
