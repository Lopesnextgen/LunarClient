package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper6$Data22<A, B, C> implements MixinHelper24_2<A, C>, Serializable {
   private final MixinHelper24_2<B, C> field1;
   private final MixinHelper24_2<A, ? extends B> field2;
   private static final long field3 = 0L;

   public MixinHelper6$Data22(MixinHelper24_2<B, C> var1, MixinHelper24_2<A, ? extends B> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   @Override
   public C apply(@Nullable A var1) {
      return this.field1.apply((B)this.field2.apply((A)var1));
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper6$Data22)) {
         return false;
      }

      MixinHelper6$Data22 var2 = (MixinHelper6$Data22)var1;
      return this.field2.equals(var2.field2) && this.field1.equals(var2.field1);
   }

   @Override
   public int hashCode() {
      return this.field2.hashCode() ^ this.field1.hashCode();
   }

   @Override
   public String toString() {
      return this.field1 + "(" + this.field2 + ")";
   }
}
