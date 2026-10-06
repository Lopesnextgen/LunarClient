package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

final class Converter$ConverterComposition<A, B, C> extends MixinHelper242<A, C> implements Serializable {
   final MixinHelper242<A, B> field3;
   final MixinHelper242<B, C> field4;
   private static final long field5 = 0L;

   Converter$ConverterComposition(MixinHelper242<A, B> mixinhelper2421, MixinHelper242<B, C> mixinhelper2422) {
      this.field3 = mixinhelper2421;
      this.field4 = mixinhelper2422;
   }

   protected C doForward(A value1) {
      throw new AssertionError();
   }

   protected A doBackward(C value1) {
      throw new AssertionError();
   }

   @Nullable C correctedDoForward(@Nullable A value1) {
      return (C)this.field4.correctedDoForward(this.field3.correctedDoForward(value1));
   }

   @Nullable A correctedDoBackward(@Nullable C value1) {
      return (A)this.field3.correctedDoBackward(this.field4.correctedDoBackward(value1));
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Converter$ConverterComposition)) {
         return false;
      }

      Converter$ConverterComposition mixinhelper242$data22 = (Converter$ConverterComposition)obj1;
      return this.field3.equals(mixinhelper242$data22.field3) && this.field4.equals(mixinhelper242$data22.field4);
   }

   @Override
   public int hashCode() {
      return 31 * this.field3.hashCode() + this.field4.hashCode();
   }

   @Override
   public String toString() {
      return this.field3 + ".andThen(" + this.field4 + ")";
   }
}
