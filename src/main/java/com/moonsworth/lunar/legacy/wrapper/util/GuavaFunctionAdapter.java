package com.moonsworth.lunar.legacy.wrapper.util;

import java.util.function.Function;

public class GuavaFunctionAdapter<T, R> implements Function<T, R> {
   private com.google.common.base.Function<T, R> field1;

   public GuavaFunctionAdapter(com.google.common.base.Function<T, R> function1) {
      this.field1 = function1;
   }

   @Override
   public R apply(T t) {
      return (R)this.field1.apply(t);
   }

   @Override
   public boolean equals(Object object) {
      return this.field1.equals(object);
   }
}
