package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import com.google.common.primitives.Longs;

enum MixinHelper5$Type2 implements Comparator<long[]> {
   INSTANCE;

   MixinHelper5$Type2() {
   }

   public int compare(long[] items1, long[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         int number5 = Longs.compare(items1[index4], items2[index4]);
         if (number5 != 0) {
            return number5;
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "Longs.lexicographicalComparator()";
   }
}
