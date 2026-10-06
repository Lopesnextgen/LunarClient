package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;

@GwtCompatible(serializable = true)
final class Comparator4<T> extends Ordering<T> implements Serializable {
   final ImmutableMap<T, Integer> field3;
   private static final long field4 = 0L;

   Comparator4(List<T> var1) {
      this(Maps.method50(var1));
   }

   Comparator4(ImmutableMap<T, Integer> var1) {
      this.field3 = var1;
   }

   @Override
   public int compare(T var1, T var2) {
      return this.rank((T)var1) - this.rank((T)var2);
   }

   private int rank(T var1) {
      Integer var2 = this.field3.get(var1);
      if (var2 == null) {
         throw new Ordering.Data3(var1);
      } else {
         return var2;
      }
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof Comparator4) {
         Comparator4 var2 = (Comparator4)var1;
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
      return "Ordering.explicit(" + this.field3.method14() + ")";
   }
}
