package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper6$Data21<E> implements MixinHelper24_2<Object, E>, Serializable {
   private final @Nullable E field1;
   private static final long field2 = 0L;

   public MixinHelper6$Data21(@Nullable E var1) {
      this.field1 = (E)var1;
   }

   @Override
   public E apply(@Nullable Object var1) {
      return this.field1;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper6$Data21) {
         MixinHelper6$Data21 var2 = (MixinHelper6$Data21)var1;
         return MixinHelper72.equal(this.field1, var2.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field1 == null ? 0 : this.field1.hashCode();
   }

   @Override
   public String toString() {
      return "Functions.constant(" + this.field1 + ")";
   }
}
