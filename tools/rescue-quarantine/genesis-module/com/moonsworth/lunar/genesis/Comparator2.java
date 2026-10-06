package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class Comparator2<T> extends Ordering<T> implements Serializable {
   final Ordering<? super T> field3;
   private static final long field4 = 0L;

   Comparator2(Ordering<? super T> var1) {
      this.field3 = Preconditions.checkNotNull(var1);
   }

   @Override
   public int compare(T var1, T var2) {
      return this.field3.compare((T)var2, (T)var1);
   }

   @Override
   public <S extends T> Ordering<S> method9() {
      return this.field3;
   }

   @Override
   public <E extends T> E min(E var1, E var2) {
      return this.field3.max((E)var1, (E)var2);
   }

   @Override
   public <E extends T> E min(E var1, E var2, E var3, E... var4) {
      return this.field3.max((E)var1, (E)var2, (E)var3, (E[])var4);
   }

   @Override
   public <E extends T> E min(Iterator<E> var1) {
      return this.field3.max(var1);
   }

   @Override
   public <E extends T> E min(Iterable<E> var1) {
      return this.field3.max(var1);
   }

   @Override
   public <E extends T> E max(E var1, E var2) {
      return this.field3.min((E)var1, (E)var2);
   }

   @Override
   public <E extends T> E max(E var1, E var2, E var3, E... var4) {
      return this.field3.min((E)var1, (E)var2, (E)var3, (E[])var4);
   }

   @Override
   public <E extends T> E max(Iterator<E> var1) {
      return this.field3.min(var1);
   }

   @Override
   public <E extends T> E max(Iterable<E> var1) {
      return this.field3.min(var1);
   }

   @Override
   public int hashCode() {
      return -this.field3.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof Comparator2) {
         Comparator2 var2 = (Comparator2)var1;
         return this.field3.equals(var2.field3);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.field3 + ".reverse()";
   }
}
