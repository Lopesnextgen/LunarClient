package com.moonsworth.lunar.genesis;

import java.util.Comparator;

enum MixinHelper132$Type implements Comparator<float[]> {
   INSTANCE;

   MixinHelper132$Type() {
   }

   public int compare(float[] items1, float[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         int number5 = Float.compare(items1[index4], items2[index4]);
         if (number5 != 0) {
            return number5;
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "Floats.lexicographicalComparator()";
   }
}
