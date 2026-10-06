package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

@GwtCompatible(serializable = true)
final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
   final Function<F, ? extends T> field3;
   final Ordering<T> field4;
   private static final long field5 = 0L;

   ByFunctionOrdering(Function<F, ? extends T> mixinhelper24_21, Ordering<T> comparator2) {
      this.field3 = (Function<F, ? extends T>)Preconditions.checkNotNull(mixinhelper24_21);
      this.field4 = (Ordering<T>)Preconditions.checkNotNull(comparator2);
   }

   public int compare(F f1, F f2) {
      return this.field4.compare(this.field3.apply(f1), this.field3.apply(f2));
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (!(obj1 instanceof ByFunctionOrdering)) {
         return false;
      }

      ByFunctionOrdering comparator72 = (ByFunctionOrdering)obj1;
      return this.field3.equals(comparator72.field3) && this.field4.equals(comparator72.field4);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field3, this.field4});
   }

   @Override
   public String toString() {
      return this.field4 + ".onResultOf(" + this.field3 + ")";
   }
}
