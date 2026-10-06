package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;

class MixinHelper25$Data8<T> implements SupplierExtension<T>, Serializable {
   final SupplierExtension<T> field1;
   private static final long field2 = 0L;

   MixinHelper25$Data8(SupplierExtension<T> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public T get() {
      synchronized (this.field1) {
         return this.field1.get();
      }
   }

   @Override
   public String toString() {
      return "Suppliers.synchronizedSupplier(" + this.field1 + ")";
   }
}
