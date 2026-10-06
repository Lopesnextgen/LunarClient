package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;
import java.util.List;

public interface AlcBridge {
   Object method1(String text1, int number2, int number3, int number4);

   void method2(Object obj1);

   int method3(Object obj1, int number2);

   String method4(Object obj1, int number2);

   List<String> method5(Object obj1, int number2);

   void method6(Object obj1, ByteBuffer buffer2, int number3);

   void method7(Object obj1);

   void method8(Object obj1);

   Object method9(Object obj1, int[] items2);

   void method10(Object obj1);

   Object method11(String text1);

   boolean method12(Object obj1, int number2, int number3, int number4);

   void method13(Object obj1, ByteBuffer buffer2, int number3);

   boolean method14(Object obj1, String text2);
}
