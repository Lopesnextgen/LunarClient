package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
   final Ordering<? super T> field3;
   private static final long field4 = 0L;

   ReverseOrdering(Ordering<? super T> comparator1) {
      this.field3 = (Ordering<? super T>)Preconditions.checkNotNull(comparator1);
   }

   public int compare(T value1, T value2) {
      return this.field3.compare(value2, value1);
   }

   public <S extends T> Ordering<S> method9() {
      return this.field3;
   }

   public <E extends T> E min(E value1, E value2) {
      return (E)this.field3.max(value1, value2);
   }

   public <E extends T> E min(E value1, E value2, E value3, E... items4) {
      return (E)this.field3.max(value1, value2, value3, items4);
   }

   public <E extends T> E min(Iterator<E> iterator1) {
      return (E)this.field3.max(iterator1);
   }

   public <E extends T> E min(Iterable<E> list1) {
      return (E)this.field3.max(list1);
   }

   public <E extends T> E max(E value1, E value2) {
      return (E)this.field3.min(value1, value2);
   }

   public <E extends T> E max(E value1, E value2, E value3, E... items4) {
      return (E)this.field3.min(value1, value2, value3, items4);
   }

   public <E extends T> E max(Iterator<E> iterator1) {
      return (E)this.field3.min(iterator1);
   }

   public <E extends T> E max(Iterable<E> list1) {
      return (E)this.field3.min(list1);
   }

   @Override
   public int hashCode() {
      return -this.field3.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (obj1 instanceof ReverseOrdering) {
         ReverseOrdering comparator22 = (ReverseOrdering)obj1;
         return this.field3.equals(comparator22.field3);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.field3 + ".reverse()";
   }
}
