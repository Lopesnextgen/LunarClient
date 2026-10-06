package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.base.Function;

class Functions$ConstantFunction<E> implements Function<Object, E>, Serializable {
   private final @Nullable E field1;
   private static final long field2 = 0L;

   public Functions$ConstantFunction(@Nullable E value1) {
      this.field1 = (E)value1;
   }

   public E apply(@Nullable Object obj1) {
      return this.field1;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Functions$ConstantFunction) {
         Functions$ConstantFunction mixinhelper6$data212 = (Functions$ConstantFunction)obj1;
         return Objects.equal(this.field1, mixinhelper6$data212.field1);
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
