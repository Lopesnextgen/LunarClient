package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

final class MixinHelper$Data52 {
   private final String field1;
   private final List<Class<?>> field2;

   MixinHelper$Data52(Method var1) {
      this.field1 = var1.getName();
      this.field2 = Arrays.asList(var1.getParameterTypes());
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1, this.field2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper$Data52)) {
         return false;
      }

      MixinHelper$Data52 var2 = (MixinHelper$Data52)var1;
      return this.field1.equals(var2.field1) && this.field2.equals(var2.field2);
   }
}
