package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

final class Converter$FunctionBasedConverter<A, B> extends MixinHelper242<A, B> implements Serializable {
   private final Function<? super A, ? extends B> field3;
   private final Function<? super B, ? extends A> field4;

   private Converter$FunctionBasedConverter(Function<? super A, ? extends B> mixinhelper24_21, Function<? super B, ? extends A> mixinhelper24_22) {
      this.field3 = (Function<? super A, ? extends B>)Preconditions.checkNotNull(mixinhelper24_21);
      this.field4 = (Function<? super B, ? extends A>)Preconditions.checkNotNull(mixinhelper24_22);
   }

   protected B doForward(A value1) {
      return (B)this.field3.apply(value1);
   }

   protected A doBackward(B value1) {
      return (A)this.field4.apply(value1);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Converter$FunctionBasedConverter)) {
         return false;
      }

      Converter$FunctionBasedConverter mixinhelper242$data2 = (Converter$FunctionBasedConverter)obj1;
      return this.field3.equals(mixinhelper242$data2.field3) && this.field4.equals(mixinhelper242$data2.field4);
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode() * 31 + this.field4.hashCode();
   }

   @Override
   public String toString() {
      return "Converter.from(" + this.field3 + ", " + this.field4 + ")";
   }
}
