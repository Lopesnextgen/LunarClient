package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import com.google.common.primitives.UnsignedInts;

enum MixinHelper3$Type3 implements Comparator<int[]> {
   INSTANCE;

   MixinHelper3$Type3() {
   }

   public int compare(int[] items1, int[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         if (items1[index4] != items2[index4]) {
            return UnsignedInts.compare(items1[index4], items2[index4]);
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "UnsignedInts.lexicographicalComparator()";
   }
}
