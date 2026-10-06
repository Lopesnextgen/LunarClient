package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Arrays;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Iterables;

@GwtCompatible(serializable = true)
final class Comparator5<T> extends Ordering<T> implements Serializable {
   final java.util.Comparator<? super T>[] field3;
   private static final long field4 = 0L;

   Comparator5(java.util.Comparator<? super T> var1, java.util.Comparator<? super T> var2) {
      this.field3 = new java.util.Comparator[]{var1, var2};
   }

   Comparator5(Iterable<? extends java.util.Comparator<? super T>> var1) {
      this.field3 = Iterables.toArray(var1, new java.util.Comparator[0]);
   }

   @Override
   public int compare(T var1, T var2) {
      for (int var3 = 0; var3 < this.field3.length; var3++) {
         int var4 = this.field3[var3].compare((T)var1, (T)var2);
         if (var4 != 0) {
            return var4;
         }
      }

      return 0;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof Comparator5) {
         Comparator5 var2 = (Comparator5)var1;
         return Arrays.equals(this.field3, var2.field3);
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
