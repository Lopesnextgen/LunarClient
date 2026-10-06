package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.base.Supplier;

class Suppliers$SupplierOfInstance<T> implements Supplier<T>, Serializable {
   final @Nullable T field1;
   private static final long field2 = 0L;

   Suppliers$SupplierOfInstance(@Nullable T value1) {
      this.field1 = (T)value1;
   }

   public T get() {
      return this.field1;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Suppliers$SupplierOfInstance) {
         Suppliers$SupplierOfInstance mixinhelper25$data72 = (Suppliers$SupplierOfInstance)obj1;
         return Objects.equal(this.field1, mixinhelper25$data72.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1});
   }

   @Override
   public String toString() {
      return "Suppliers.ofInstance(" + this.field1 + ")";
   }
}
