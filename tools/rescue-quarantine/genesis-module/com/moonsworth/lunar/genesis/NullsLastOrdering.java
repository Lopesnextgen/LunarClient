package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;

@GwtCompatible(serializable = true)
final class NullsLastOrdering<T> extends Ordering<T> implements Serializable {
   final Ordering<? super T> field3;
   private static final long field4 = 0L;

   NullsLastOrdering(Ordering<? super T> comparator1) {
      this.field3 = comparator1;
   }

   public int compare(@Nullable T value1, @Nullable T value2) {
      if (value1 == value2) {
         return 0;
      } else if (value1 == null) {
         return 1;
      } else {
         return value2 == null ? -1 : this.field3.compare(value1, value2);
      }
   }

   public <S extends T> Ordering<S> method9() {
      return this.field3.method9().method10();
   }

   public <S extends T> Ordering<S> method10() {
      return this.field3.method10();
   }

   public <S extends T> Ordering<S> method11() {
      return this;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (obj1 instanceof NullsLastOrdering) {
         NullsLastOrdering comparator102 = (NullsLastOrdering)obj1;
         return this.field3.equals(comparator102.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode() ^ -921210296;
   }

   @Override
   public String toString() {
      return this.field3 + ".nullsLast()";
   }
}
