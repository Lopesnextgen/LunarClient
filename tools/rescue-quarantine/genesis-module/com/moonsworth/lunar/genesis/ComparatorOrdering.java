package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class ComparatorOrdering<T> extends Ordering<T> implements Serializable {
   final java.util.Comparator<T> field3;
   private static final long field4 = 0L;

   ComparatorOrdering(java.util.Comparator<T> comparator1) {
      this.field3 = (java.util.Comparator<T>)Preconditions.checkNotNull(comparator1);
   }

   @Override
   public int compare(T value1, T value2) {
      return this.field3.compare((T)value1, (T)value2);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (obj1 instanceof ComparatorOrdering) {
         ComparatorOrdering comparator92 = (ComparatorOrdering)obj1;
         return this.field3.equals(comparator92.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode();
   }

   @Override
   public String toString() {
      return this.field3.toString();
   }
}
