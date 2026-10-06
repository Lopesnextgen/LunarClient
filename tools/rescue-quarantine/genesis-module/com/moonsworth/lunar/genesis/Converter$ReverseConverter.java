package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

final class Converter$ReverseConverter<A, B> extends MixinHelper242<B, A> implements Serializable {
   final MixinHelper242<A, B> field3;
   private static final long field4 = 0L;

   Converter$ReverseConverter(MixinHelper242<A, B> mixinhelper2421) {
      this.field3 = mixinhelper2421;
   }

   protected A doForward(B value1) {
      throw new AssertionError();
   }

   protected B doBackward(A value1) {
      throw new AssertionError();
   }

   @Nullable A correctedDoForward(@Nullable B value1) {
      return (A)this.field3.correctedDoBackward(value1);
   }

   @Nullable B correctedDoBackward(@Nullable A value1) {
      return (B)this.field3.correctedDoForward(value1);
   }

   public MixinHelper242<A, B> method2() {
      return this.field3;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Converter$ReverseConverter) {
         Converter$ReverseConverter mixinhelper242$data42 = (Converter$ReverseConverter)obj1;
         return this.field3.equals(mixinhelper242$data42.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return ~this.field3.hashCode();
   }

   @Override
   public String toString() {
      return this.field3 + ".reverse()";
   }
}
