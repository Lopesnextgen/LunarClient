package com.moonsworth.lunar.genesis;

import java.util.Comparator;

enum MixinHelper16$Type2 implements Comparator<byte[]> {
   INSTANCE;

   MixinHelper16$Type2() {
   }

   public int compare(byte[] items1, byte[] items2) {
      int number3 = Math.min(items1.length, items2.length);

      for (int index4 = 0; index4 < number3; index4++) {
         int number5 = MixinHelper16_3.compare(items1[index4], items2[index4]);
         if (number5 != 0) {
            return number5;
         }
      }

      return items1.length - items2.length;
   }

   @Override
   public String toString() {
      return "SignedBytes.lexicographicalComparator()";
   }
}
