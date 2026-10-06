package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper25$Data7<T> implements SupplierExtension<T>, Serializable {
   final @Nullable T field1;
   private static final long field2 = 0L;

   MixinHelper25$Data7(@Nullable T var1) {
      this.field1 = (T)var1;
   }

   @Override
   public T get() {
      return this.field1;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper25$Data7) {
         MixinHelper25$Data7 var2 = (MixinHelper25$Data7)var1;
         return MixinHelper72.equal(this.field1, var2.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1);
   }

   @Override
   public String toString() {
      return "Suppliers.ofInstance(" + this.field1 + ")";
   }
}
