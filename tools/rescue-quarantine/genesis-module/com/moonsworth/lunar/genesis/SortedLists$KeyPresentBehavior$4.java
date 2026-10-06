package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.List;

enum SortedLists$KeyPresentBehavior$4 {
   ;
   SortedLists$KeyPresentBehavior$4() {
   }

   public <E> int resultIndex(Comparator<? super E> comparator1, E value2, List<? extends E> list3, int number4) {
      return LAST_PRESENT.resultIndex(comparator1, value2, list3, number4) + 1;
   }
}
