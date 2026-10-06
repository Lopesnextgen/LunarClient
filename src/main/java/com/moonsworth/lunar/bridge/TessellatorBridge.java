package com.moonsworth.lunar.bridge;

public interface TessellatorBridge {
   default void method1() {
      this.method2(7);
   }

   void method2(int number1);

   void method3();

   boolean bridge$isDrawing();
}
