package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multiset;

abstract class MixinHelper33$Data3<E> implements Multiset.Extension<E> {
   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof Multiset.Extension)) {
         return false;
      }

      Multiset.Extension var2 = (Multiset.Extension)var1;
      return this.getCount() == var2.getCount() && MixinHelper72.equal(this.getElement(), var2.getElement());
   }

   @Override
   public int hashCode() {
      Object var1 = this.getElement();
      return (var1 == null ? 0 : var1.hashCode()) ^ this.getCount();
   }

   @Override
   public String toString() {
      String var1 = String.valueOf(this.getElement());
      int var2 = this.getCount();
      return var2 == 1 ? var1 : var1 + " x " + var2;
   }
}
