package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
final class Comparator10<T> extends Ordering<T> implements Serializable {
   final Ordering<? super T> field3;
   private static final long field4 = 0L;

   Comparator10(Ordering<? super T> var1) {
      this.field3 = var1;
   }

   @Override
   public int compare(@Nullable T var1, @Nullable T var2) {
      if (var1 == var2) {
         return 0;
      } else if (var1 == null) {
         return 1;
      } else {
         return var2 == null ? -1 : this.field3.compare((T)var1, (T)var2);
      }
   }

   @Override
   public <S extends T> Ordering<S> method9() {
      return this.field3.method9().method10();
   }

   @Override
   public <S extends T> Ordering<S> method10() {
      return this.field3.method10();
   }

   @Override
   public <S extends T> Ordering<S> method11() {
      return this;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof Comparator10) {
         Comparator10 var2 = (Comparator10)var1;
         return this.field3.equals(var2.field3);
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
