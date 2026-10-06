package com.moonsworth.lunar.bridge;

public interface Bridge2_39 {
   boolean method1(KeyCode var1);

   String method2(KeyCode var1);

   void method3(boolean var1);

   int method4(KeyCode var1);

   default boolean method5(String var1) {
      if (!var1.startsWith("KEY_")) {
         var1 = "KEY_" + var1;
      }

      return this.method1(KeyCode.valueOf(var1));
   }

   default KeyCode method6(String var1) {
      if (!var1.startsWith("KEY_")) {
         var1 = "KEY_" + var1;
      }

      return KeyCode.valueOf(var1);
   }
}
