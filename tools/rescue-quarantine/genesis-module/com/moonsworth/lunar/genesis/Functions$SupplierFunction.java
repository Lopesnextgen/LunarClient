package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Functions$SupplierFunction<T> implements Function<Object, T>, Serializable {
   private final Supplier<T> field1;
   private static final long field2 = 0L;

   private Functions$SupplierFunction(Supplier<T> supplierextension1) {
      this.field1 = (Supplier<T>)Preconditions.checkNotNull(supplierextension1);
   }

   public T apply(@Nullable Object obj1) {
      return (T)this.field1.get();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Functions$SupplierFunction) {
         Functions$SupplierFunction mixinhelper6$data252 = (Functions$SupplierFunction)obj1;
         return this.field1.equals(mixinhelper6$data252.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public String toString() {
      return "Functions.forSupplier(" + this.field1 + ")";
   }
}
