package com.moonsworth.lunar.genesis;

import java.nio.ByteOrder;

final class MixinHelper2_13 {
   private static final MixinHelper2$Extension field1;

   static long load64(byte[] var0, int var1) {
      assert var0.length >= var1 + 8;
      return field1.getLongLittleEndian(var0, var1);
   }

   static long load64Safely(byte[] var0, int var1, int var2) {
      long var3 = 0L;
      int var5 = Math.min(var2, 8);

      for (int var6 = 0; var6 < var5; var6++) {
         var3 |= (var0[var1 + var6] & 255L) << var6 * 8;
      }

      return var3;
   }

   static void store64(byte[] var0, int var1, long var2) {
      assert var1 >= 0 && var1 + 8 <= var0.length;
      field1.putLongLittleEndian(var0, var1, var2);
   }

   static int load32(byte[] var0, int var1) {
      return var0[var1] & 0xFF | (var0[var1 + 1] & 0xFF) << 8 | (var0[var1 + 2] & 0xFF) << 16 | (var0[var1 + 3] & 0xFF) << 24;
   }

   static boolean usingUnsafe() {
      return field1 instanceof MixinHelper2$Type8;
   }

   private MixinHelper2_13() {
   }

   static {
      Enum var0 = MixinHelper2$Type7.INSTANCE;

      try {
         String var1 = System.getProperty("os.arch");
         if ("amd64".equals(var1)) {
            var0 = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) ? MixinHelper2$Type8.UNSAFE_LITTLE_ENDIAN : MixinHelper2$Type8.UNSAFE_BIG_ENDIAN;
         }
      } catch (Throwable var2) {
      }

      field1 = var0;
   }
}
