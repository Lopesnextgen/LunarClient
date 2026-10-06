package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class Comparator7<F, T> extends Ordering<F> implements Serializable {
   final MixinHelper24_2<F, ? extends T> field3;
   final Ordering<T> field4;
   private static final long field5 = 0L;

   Comparator7(MixinHelper24_2<F, ? extends T> var1, Ordering<T> var2) {
      this.field3 = Preconditions.checkNotNull(var1);
      this.field4 = Preconditions.checkNotNull(var2);
   }

   @Override
   public int compare(F var1, F var2) {
      return this.field4.compare((T)this.field3.apply((F)var1), (T)this.field3.apply((F)var2));
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof Comparator7)) {
         return false;
      }

      Comparator7 var2 = (Comparator7)var1;
      return this.field3.equals(var2.field3) && this.field4.equals(var2.field4);
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field3, this.field4);
   }

   @Override
   public String toString() {
      return this.field4 + ".onResultOf(" + this.field3 + ")";
   }
}
