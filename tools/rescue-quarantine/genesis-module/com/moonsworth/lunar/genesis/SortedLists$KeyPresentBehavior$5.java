package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.List;

enum SortedLists$KeyPresentBehavior$5 {
   ;
   SortedLists$KeyPresentBehavior$5() {
   }

   public <E> int resultIndex(Comparator<? super E> comparator1, E value2, List<? extends E> list3, int number4) {
      return FIRST_PRESENT.resultIndex(comparator1, value2, list3, number4) - 1;
   }
}
