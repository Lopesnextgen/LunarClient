package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class Hashing {
   private static final long field1 = -862048943L;
   private static final long field2 = 461845907L;
   private static final int field3 = 1073741824;

   private Hashing() {
   }

   static int smear(int number0) {
      return (int)(461845907L * Integer.rotateLeft((int)(number0 * -862048943L), 15));
   }

   static int smearedHash(@Nullable Object obj0) {
      return smear(obj0 == null ? 0 : obj0.hashCode());
   }

   static int closedTableSize(int number0, double value1) {
      number0 = Math.max(number0, 2);
      int number3 = Integer.highestOneBit(number0);
      if (number0 > (int)(value1 * number3)) {
         number3 <<= 1;
         return number3 > 0 ? number3 : 1073741824;
      } else {
         return number3;
      }
   }

   static boolean needsResizing(int number0, int number1, double value2) {
      return number0 > value2 * number1 && number1 < 1073741824;
   }
}
