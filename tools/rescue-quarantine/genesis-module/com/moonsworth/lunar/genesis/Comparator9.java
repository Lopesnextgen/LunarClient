package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class Comparator9<T> extends Ordering<T> implements Serializable {
   final java.util.Comparator<T> field3;
   private static final long field4 = 0L;

   Comparator9(java.util.Comparator<T> var1) {
      this.field3 = Preconditions.checkNotNull(var1);
   }

   @Override
   public int compare(T var1, T var2) {
      return this.field3.compare((T)var1, (T)var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof Comparator9) {
         Comparator9 var2 = (Comparator9)var1;
         return this.field3.equals(var2.field3);
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
