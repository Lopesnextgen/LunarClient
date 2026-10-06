package com.moonsworth.lunar.genesis;

import java.util.Comparator;

enum MixinHelper122$Type implements Comparator<int[]> {
   INSTANCE;

   MixinHelper122$Type() {
   }

   public int compare(int[] items1, int[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         int number5 = MixinHelper122.compare(items1[index4], items2[index4]);
         if (number5 != 0) {
            return number5;
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "Ints.lexicographicalComparator()";
   }
}
