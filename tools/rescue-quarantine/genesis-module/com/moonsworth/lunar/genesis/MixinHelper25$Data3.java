package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

@Annotation4
class MixinHelper25$Data3<T> implements SupplierExtension<T>, Serializable {
   final SupplierExtension<T> field1;
   transient volatile boolean initialized;
   transient @Nullable T value;
   private static final long field2 = 0L;

   MixinHelper25$Data3(SupplierExtension<T> var1) {
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
               return (T)var2;
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
