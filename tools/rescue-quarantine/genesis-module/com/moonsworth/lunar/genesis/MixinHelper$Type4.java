package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import com.google.common.primitives.UnsignedLongs;

enum MixinHelper$Type4 implements Comparator<long[]> {
   INSTANCE;

   MixinHelper$Type4() {
   }

   public int compare(long[] items1, long[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         if (items1[index4] != items2[index4]) {
            return UnsignedLongs.compare(items1[index4], items2[index4]);
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "UnsignedLongs.lexicographicalComparator()";
   }
}
