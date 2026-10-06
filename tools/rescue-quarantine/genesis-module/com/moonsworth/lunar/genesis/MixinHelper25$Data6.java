package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper25$Data6<F, T> implements SupplierExtension<T>, Serializable {
   final MixinHelper24_2<? super F, T> field1;
   final SupplierExtension<F> field2;
   private static final long field3 = 0L;

   MixinHelper25$Data6(MixinHelper24_2<? super F, T> var1, SupplierExtension<F> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   @Override
   public T get() {
      return this.field1.apply(this.field2.get());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper25$Data6)) {
         return false;
      }

      MixinHelper25$Data6 var2 = (MixinHelper25$Data6)var1;
      return this.field1.equals(var2.field1) && this.field2.equals(var2.field2);
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1, this.field2);
   }

   @Override
   public String toString() {
      return "Suppliers.compose(" + this.field1 + ", " + this.field2 + ")";
   }
}
