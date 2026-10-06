package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.eventbus.Subscribe;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

@Subscribe
class Suppliers$MemoizingSupplier<T> implements Supplier<T>, Serializable {
   final Supplier<T> field1;
   transient volatile boolean initialized;
   transient @Nullable T value;
   private static final long field2 = 0L;

   Suppliers$MemoizingSupplier(Supplier<T> supplierextension1) {
      this.field1 = (Supplier<T>)Preconditions.checkNotNull(supplierextension1);
   }

   public T get() {
      if (!this.initialized) {
         synchronized (this) {
            if (!this.initialized) {
               Object obj2 = this.field1.get();
               this.value = (T)obj2;
               this.initialized = true;
               return (T)obj2;
            }
         }
      }

      return this.value;
   }

   @Override
   public String toString() {
      return "Suppliers.memoize(" + (this.initialized ? "<supplier that returned " + this.value + ">" : this.field1) + ")";
   }
}
