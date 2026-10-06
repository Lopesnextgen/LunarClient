package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.SortedSet;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class SortedIterables {
   private SortedIterables() {
   }

   public static boolean hasSameComparator(Comparator<?> comparator0, Iterable<?> list1) {
      Preconditions.checkNotNull(comparator0);
      Preconditions.checkNotNull(list1);
      Comparator comparator2;
      if (list1 instanceof SortedSet) {
         comparator2 = comparator((SortedSet)list1);
      } else {
         if (!(list1 instanceof SortedIterable)) {
            return false;
         }

         comparator2 = ((SortedIterable)list1).comparator();
      }

      return comparator0.equals(comparator2);
   }

   public static <E> Comparator<? super E> comparator(SortedSet<E> sortedset0) {
      Object obj1 = sortedset0.comparator();
      if (obj1 == null) {
         obj1 = com.google.common.collect.Ordering.method1();
      }

      return (Comparator<? super E>)obj1;
   }
}
