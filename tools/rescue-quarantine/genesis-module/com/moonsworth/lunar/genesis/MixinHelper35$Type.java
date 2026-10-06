package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.List;

enum MixinHelper35$Type {
   ANY_PRESENT,
   LAST_PRESENT,
   FIRST_PRESENT,
   FIRST_AFTER,
   LAST_BEFORE;

   MixinHelper35$Type() {
   }

   abstract <E> int resultIndex(Comparator<? super E> comparator1, E value2, List<? extends E> list3, int number4);
}
