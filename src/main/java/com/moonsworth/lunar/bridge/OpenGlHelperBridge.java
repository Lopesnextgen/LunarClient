package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public interface OpenGlHelperBridge {
   boolean method1();

   default boolean method2() {
      return true;
   }

   boolean method3();

   void method4(int var1, float var2, float var3);

   int method5();

   int method6();

   float method7();

   float method8();

   String method9();

   void method10();

   void method11();

   default void method12() {
   }

   void method13(int var1, ByteBuffer var2);

   void method14(int var1, IntBuffer var2);

   void method15(int var1, FloatBuffer var2);

   void method16(int var1, DoubleBuffer var2);

   boolean method17();

   boolean method18();

   boolean method19();

   boolean method20();

   boolean method21();

   boolean method22();

   boolean method23();

   boolean method24();

   boolean method25();

   List<String> method26();

   OpenGlHelperBridge.Type method27();

   void method28(int var1, String var2);

   enum Type {
      BASE,
      ARB,
      EXT;
   }
}
