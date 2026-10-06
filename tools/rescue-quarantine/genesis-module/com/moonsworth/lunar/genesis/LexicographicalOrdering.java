package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;

@GwtCompatible(serializable = true)
final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
   final java.util.Comparator<? super T> field3;
   private static final long field4 = 0L;

   LexicographicalOrdering(java.util.Comparator<? super T> comparator1) {
      this.field3 = comparator1;
   }

   public int compare(Iterable<T> list1, Iterable<T> list2) {
      Iterator iterator3 = list1.iterator();
      Iterator iterator4 = list2.iterator();

      while (iterator3.hasNext()) {
         if (!iterator4.hasNext()) {
            return 1;
         }

         int number5 = this.field3.compare((T)iterator3.next(), (T)iterator4.next());
         if (number5 != 0) {
            return number5;
         }
      }

      return iterator4.hasNext() ? -1 : 0;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (obj1 instanceof LexicographicalOrdering) {
         LexicographicalOrdering comparator32 = (LexicographicalOrdering)obj1;
         return this.field3.equals(comparator32.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode() ^ 2075626741;
   }

   @Override
   public String toString() {
      return this.field3 + ".lexicographical()";
   }
}
