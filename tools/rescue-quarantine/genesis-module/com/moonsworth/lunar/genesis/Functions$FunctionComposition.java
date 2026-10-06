package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Functions$FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
   private final Function<B, C> field1;
   private final Function<A, ? extends B> field2;
   private static final long field3 = 0L;

   public Functions$FunctionComposition(Function<B, C> mixinhelper24_21, Function<A, ? extends B> mixinhelper24_22) {
      this.field1 = (Function<B, C>)Preconditions.checkNotNull(mixinhelper24_21);
      this.field2 = (Function<A, ? extends B>)Preconditions.checkNotNull(mixinhelper24_22);
   }

   public C apply(@Nullable A value1) {
      return (C)this.field1.apply(this.field2.apply(value1));
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Functions$FunctionComposition)) {
         return false;
      }

      Functions$FunctionComposition mixinhelper6$data222 = (Functions$FunctionComposition)obj1;
      return this.field2.equals(mixinhelper6$data222.field2) && this.field1.equals(mixinhelper6$data222.field1);
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
