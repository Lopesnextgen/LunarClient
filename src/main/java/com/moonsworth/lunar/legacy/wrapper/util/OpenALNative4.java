package com.moonsworth.lunar.legacy.wrapper.util;

import java.util.function.Function;

public class OpenALNative4<T, R> implements Function<T, R> {
   private com.google.common.base.Function<T, R> field1;

   public OpenALNative4(com.google.common.base.Function<T, R> var1) {
      this.field1 = var1;
   }

   @Override
   public R apply(T var1) {
      return (R)this.field1.apply(var1);
   }

   @Override
   public boolean equals(Object var1) {
      return this.field1.equals(var1);
   }
}
