package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

class Suppliers$ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
   final Supplier<T> field1;
   private static final long field2 = 0L;

   Suppliers$ThreadSafeSupplier(Supplier<T> supplierextension1) {
      this.field1 = (Supplier<T>)Preconditions.checkNotNull(supplierextension1);
   }

   public T get() {
      synchronized (this.field1) {
         return (T)this.field1.get();
      }
   }

   @Override
   public String toString() {
      return "Suppliers.synchronizedSupplier(" + this.field1 + ")";
   }
}
