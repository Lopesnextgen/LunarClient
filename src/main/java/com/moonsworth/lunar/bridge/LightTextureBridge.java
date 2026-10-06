package com.moonsworth.lunar.bridge;

public class LightTextureBridge {
   public static final int field1 = 15728880;

   public LightTextureBridge() {
   }

   public static int pack(int number0, int value) {
      return value << 16 | number0;
   }

   public static int method1(int number0) {
      return number0 >> 16;
   }

   public static int method2(int number0) {
      return number0 & 65535;
   }
}
