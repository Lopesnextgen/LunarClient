package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.MapDifference;

class MixinHelper19$Data9<V> implements MapDifference.Extension<V> {
   private final @Nullable V field1;
   private final @Nullable V field2;

   static <V> MapDifference.Extension<V> method1(@Nullable V var0, @Nullable V var1) {
      return new MixinHelper19$Data9<>((V)var0, (V)var1);
   }

   private MixinHelper19$Data9(@Nullable V var1, @Nullable V var2) {
      this.field1 = (V)var1;
      this.field2 = (V)var2;
   }

   @Override
   public V leftValue() {
      return this.field1;
   }

   @Override
   public V rightValue() {
      return this.field2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MapDifference.Extension)) {
         return false;
      }

      MapDifference.Extension var2 = (MapDifference.Extension)var1;
      return MixinHelper72.equal(this.field1, var2.leftValue()) && MixinHelper72.equal(this.field2, var2.rightValue());
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1, this.field2);
   }

   @Override
   public String toString() {
      return "(" + this.field1 + ", " + this.field2 + ")";
   }
}
