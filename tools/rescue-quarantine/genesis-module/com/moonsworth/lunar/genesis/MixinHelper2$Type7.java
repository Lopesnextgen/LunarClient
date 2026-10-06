package com.moonsworth.lunar.genesis;
import com.google.common.primitives.Longs;

enum MixinHelper2$Type7 implements MixinHelper2$Extension {
   INSTANCE {
      @Override
      public long getLongLittleEndian(byte[] var1, int var2) {
         return Longs.fromBytes(
            var1[var2 + 7], var1[var2 + 6], var1[var2 + 5], var1[var2 + 4], var1[var2 + 3], var1[var2 + 2], var1[var2 + 1], var1[var2]
         );
      }

      @Override
      public void putLongLittleEndian(byte[] var1, int var2, long var3) {
         long var5 = 255L;

         for (int var7 = 0; var7 < 8; var7++) {
            var1[var2 + var7] = (byte)((var3 & var5) >> var7 * 8);
            var5 <<= 8;
         }
      }
   };

   MixinHelper2$Type7() {
   }
}
