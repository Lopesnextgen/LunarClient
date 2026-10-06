package com.moonsworth.lunar.bridge;

public class Bridge_34 {
   public static final int field1 = 15728880;

   public static int pack(int var0, int value) {
      return value << 16 | var0;
   }

   public static int method1(int var0) {
      return var0 >> 16;
   }

   public static int method2(int var0) {
      return var0 & 65535;
   }
}
