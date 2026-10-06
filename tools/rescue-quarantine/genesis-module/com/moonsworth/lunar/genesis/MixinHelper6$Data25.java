package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper6$Data25<T> implements MixinHelper24_2<Object, T>, Serializable {
   private final SupplierExtension<T> field1;
   private static final long field2 = 0L;

   private MixinHelper6$Data25(SupplierExtension<T> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public T apply(@Nullable Object var1) {
      return this.field1.get();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper6$Data25) {
         MixinHelper6$Data25 var2 = (MixinHelper6$Data25)var1;
         return this.field1.equals(var2.field1);
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
