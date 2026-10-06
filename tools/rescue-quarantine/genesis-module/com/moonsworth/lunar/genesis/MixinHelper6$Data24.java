package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper6$Data24<T> implements MixinHelper24_2<T, Boolean>, Serializable {
   private final PredicateExtension<T> field1;
   private static final long field2 = 0L;

   private MixinHelper6$Data24(PredicateExtension<T> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   public Boolean apply(@Nullable T var1) {
      return this.field1.apply((T)var1);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper6$Data24) {
         MixinHelper6$Data24 var2 = (MixinHelper6$Data24)var1;
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
      return "Functions.forPredicate(" + this.field1 + ")";
   }
}
