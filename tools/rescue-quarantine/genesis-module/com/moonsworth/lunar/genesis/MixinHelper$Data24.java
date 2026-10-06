package com.moonsworth.lunar.genesis;

import java.math.BigInteger;
import com.google.common.primitives.UnsignedLongs;

final class MixinHelper$Data24 {
   static final long[] field1 = new long[37];
   static final int[] field2 = new int[37];
   static final int[] field3 = new int[37];

   private MixinHelper$Data24() {
   }

   static boolean overflowInParse(long var0, int var2, int var3) {
      if (var0 >= 0L) {
         if (var0 < field1[var3]) {
            return false;
         } else {
            return var0 > field1[var3] ? true : var2 > field2[var3];
         }
      } else {
         return true;
      }
   }

   static {
      BigInteger var0 = new BigInteger("10000000000000000", 16);

      for (int var1 = 2; var1 <= 36; var1++) {
         field1[var1] = UnsignedLongs.divide(-1L, var1);
         field2[var1] = (int)UnsignedLongs.remainder(-1L, var1);
         field3[var1] = var0.toString(var1).length() - 1;
      }
   }
}
