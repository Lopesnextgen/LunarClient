package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Suppliers$SupplierComposition<F, T> implements Supplier<T>, Serializable {
   final Function<? super F, T> field1;
   final Supplier<F> field2;
   private static final long field3 = 0L;

   Suppliers$SupplierComposition(Function<? super F, T> mixinhelper24_21, Supplier<F> supplierextension2) {
      this.field1 = (Function<? super F, T>)Preconditions.checkNotNull(mixinhelper24_21);
      this.field2 = (Supplier<F>)Preconditions.checkNotNull(supplierextension2);
   }

   public T get() {
      return (T)this.field1.apply(this.field2.get());
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Suppliers$SupplierComposition)) {
         return false;
      }

      Suppliers$SupplierComposition mixinhelper25$data62 = (Suppliers$SupplierComposition)obj1;
      return this.field1.equals(mixinhelper25$data62.field1) && this.field2.equals(mixinhelper25$data62.field2);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1, this.field2});
   }

   @Override
   public String toString() {
      return "Suppliers.compose(" + this.field1 + ", " + this.field2 + ")";
   }
}
