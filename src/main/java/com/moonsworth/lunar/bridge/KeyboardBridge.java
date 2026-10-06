package com.moonsworth.lunar.bridge;

public interface KeyboardBridge {
   boolean method1(KeyCode bridgetype_81);

   String method2(KeyCode bridgetype_81);

   void method3(boolean flag1);

   int method4(KeyCode bridgetype_81);

   default boolean method5(String text1) {
      if (!text1.startsWith("KEY_")) {
         text1 = "KEY_" + text1;
      }

      return this.method1(KeyCode.valueOf(text1));
   }

   default KeyCode method6(String text1) {
      if (!text1.startsWith("KEY_")) {
         text1 = "KEY_" + text1;
      }

      return KeyCode.valueOf(text1);
   }
}
