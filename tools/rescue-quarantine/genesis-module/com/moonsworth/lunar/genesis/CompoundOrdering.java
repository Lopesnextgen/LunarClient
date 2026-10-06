package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Arrays;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.collect.Iterables;

@GwtCompatible(serializable = true)
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
   final java.util.Comparator<? super T>[] field3;
   private static final long field4 = 0L;

   CompoundOrdering(java.util.Comparator<? super T> comparator1, java.util.Comparator<? super T> comparator2) {
      this.field3 = new java.util.Comparator[]{comparator1, comparator2};
   }

   CompoundOrdering(Iterable<? extends java.util.Comparator<? super T>> list1) {
      this.field3 = Iterables.toArray(list1, new java.util.Comparator[0]);
   }

   @Override
   public int compare(T value1, T value2) {
      for (int index3 = 0; index3 < this.field3.length; index3++) {
         int number4 = this.field3[index3].compare((T)value1, (T)value2);
         if (number4 != 0) {
            return number4;
         }
      }

      return 0;
   }

   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (obj1 instanceof CompoundOrdering) {
         CompoundOrdering comparator52 = (CompoundOrdering)obj1;
         return Arrays.equals(this.field3, comparator52.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(this.field3);
   }

   @Override
   public String toString() {
      return "Ordering.compound(" + Arrays.toString(this.field3) + ")";
   }
}
