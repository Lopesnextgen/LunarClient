package com.moonsworth.lunar.genesis;

import java.math.BigInteger;

final class UnsignedLongs$ParseOverflowDetection {
   static final long[] field1 = new long[37];
   static final int[] field2 = new int[37];
   static final int[] field3 = new int[37];

   private UnsignedLongs$ParseOverflowDetection() {
   }

   static boolean overflowInParse(long number0, int number2, int index3) {
      if (number0 >= 0L) {
         if (number0 < field1[index3]) {
            return false;
         } else {
            return number0 > field1[index3] ? true : number2 > field2[index3];
         }
      } else {
         return true;
      }
   }

   static {
      BigInteger number0 = new BigInteger("10000000000000000", 16);

      for (int index1 = 2; index1 <= 36; index1++) {
         field1[index1] = MixinHelper_9.divide(-1L, index1);
         field2[index1] = (int)MixinHelper_9.remainder(-1L, index1);
         field3[index1] = number0.toString(index1).length() - 1;
      }
   }
}
