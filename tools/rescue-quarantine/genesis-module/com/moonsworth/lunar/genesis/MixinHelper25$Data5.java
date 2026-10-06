package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

@Annotation4
class MixinHelper25$Data5<T> implements SupplierExtension<T> {
   volatile SupplierExtension<T> field1;
   volatile boolean initialized;
   @Nullable T value;

   MixinHelper25$Data5(SupplierExtension<T> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public T get() {
      if (!this.initialized) {
         synchronized (this) {
            if (!this.initialized) {
               Object var2 = this.field1.get();
               this.value = (T)var2;
               this.initialized = true;
               this.field1 = null;
               return (T)var2;
            }
         }
      }

      return this.value;
   }

   @Override
   public String toString() {
      Object var1 = this.field1;
      return "Suppliers.memoize(" + (var1 == null ? "<supplier that returned " + this.value + ">" : var1) + ")";
   }
}
