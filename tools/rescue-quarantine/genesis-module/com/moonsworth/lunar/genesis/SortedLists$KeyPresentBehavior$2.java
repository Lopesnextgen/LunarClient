package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.List;

enum SortedLists$KeyPresentBehavior$2 {
   ;
   SortedLists$KeyPresentBehavior$2() {
   }

   <E> int resultIndex(Comparator<? super E> comparator1, E value2, List<? extends E> list3, int number4) {
      int number5 = number4;
      int number6 = list3.size() - 1;

      while (number5 < number6) {
         int index7 = number5 + number6 + 1 >>> 1;
         int number8 = comparator1.compare(list3.get(index7), value2);
         if (number8 > 0) {
            number6 = index7 - 1;
         } else {
            number5 = index7;
         }
      }

      return number5;
   }
}
