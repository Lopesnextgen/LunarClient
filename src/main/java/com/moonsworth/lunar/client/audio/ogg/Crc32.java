package com.moonsworth.lunar.client.audio.ogg;

public class Crc32 {
   protected static final int field1 = 79764919;
   private static final int[] field2 = new int[256];

   public Crc32() {
   }

   public static int method1(byte[] items0) {
      int index1 = 0;

      for (byte index5 : items0) {
         int number6 = index1 << 8;
         int number7 = field2[index1 >>> 24 & 0xFF ^ index5 & 0xFF];
         index1 = number6 ^ number7;
      }

      return index1;
   }

   static {
      for (int index0 = 0; index0 < 256; index0++) {
         int number1 = index0 << 24;

         for (int index2 = 0; index2 < 8; index2++) {
            if ((number1 & -2147483648) != 0) {
               number1 = number1 << 1 ^ 79764919;
            } else {
               number1 <<= 1;
            }
         }

         field2[index0] = number1;
      }
   }
}
