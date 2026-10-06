package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.eventbus.Subscribe;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

@Subscribe
class Suppliers$NonSerializableMemoizingSupplier<T> implements Supplier<T> {
   volatile Supplier<T> field1;
   volatile boolean initialized;
   @Nullable T value;

   Suppliers$NonSerializableMemoizingSupplier(Supplier<T> supplierextension1) {
      this.field1 = (Supplier<T>)Preconditions.checkNotNull(supplierextension1);
   }

   public T get() {
      if (!this.initialized) {
         synchronized (this) {
            if (!this.initialized) {
               Object obj2 = this.field1.get();
               this.value = (T)obj2;
               this.initialized = true;
               this.field1 = null;
               return (T)obj2;
            }
         }
      }

      return this.value;
   }

   @Override
   public String toString() {
      Object obj1 = this.field1;
      return "Suppliers.memoize(" + (obj1 == null ? "<supplier that returned " + this.value + ">" : obj1) + ")";
   }
}
