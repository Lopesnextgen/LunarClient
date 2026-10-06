package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Converter;

final class MixinHelper19$Data23<A, B> extends Converter<A, B> implements Serializable {
   private final MapExtension<A, B> field3;
   private static final long field4 = 0L;

   MixinHelper19$Data23(MapExtension<A, B> var1) {
      this.field3 = Preconditions.checkNotNull(var1);
   }

   @Override
   protected B doForward(A var1) {
      return method1(this.field3, (A)var1);
   }

   @Override
   protected A doBackward(B var1) {
      return method1(this.field3.method2(), (B)var1);
   }

   private static <X, Y> Y method1(MapExtension<X, Y> var0, X var1) {
      Object var2 = var0.get(var1);
      Preconditions.checkArgument(var2 != null, "No non-null mapping present for input: %s", var1);
      return (Y)var2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper19$Data23) {
         MixinHelper19$Data23 var2 = (MixinHelper19$Data23)var1;
         return this.field3.equals(var2.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode();
   }

   @Override
   public String toString() {
      return "Maps.asConverter(" + this.field3 + ")";
   }
}
