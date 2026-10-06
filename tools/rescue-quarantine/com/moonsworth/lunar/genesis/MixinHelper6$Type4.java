package com.moonsworth.lunar.genesis;

import java.util.Comparator;

enum MixinHelper6$Type4 implements Comparator<boolean[]> {
   INSTANCE;

   MixinHelper6$Type4() {
   }

   public int compare(boolean[] items1, boolean[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         int number5 = MixinHelper6.compare(items1[index4], items2[index4]);
         if (number5 != 0) {
            return number5;
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "Booleans.lexicographicalComparator()";
   }
}
