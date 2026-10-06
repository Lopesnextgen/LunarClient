package com.moonsworth.lunar.client.util.lotusfish.mixin;

public class Lotusfish {
   protected static final int field1 = 79764919;
   private static final int[] field2 = new int[256];

   public static int method1(byte[] var0) {
      int var1 = 0;

      for (byte var5 : var0) {
         int var6 = var1 << 8;
         int var7 = field2[var1 >>> 24 & 0xFF ^ var5 & 0xFF];
         var1 = var6 ^ var7;
      }

      return var1;
   }

   static {
      for (int var0 = 0; var0 < 256; var0++) {
         int var1 = var0 << 24;

         for (int var2 = 0; var2 < 8; var2++) {
            if ((var1 & -2147483648) != 0) {
               var1 = var1 << 1 ^ 79764919;
            } else {
               var1 <<= 1;
            }
         }

         field2[var0] = var1;
      }
   }
}
