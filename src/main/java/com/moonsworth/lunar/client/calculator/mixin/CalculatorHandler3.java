package com.moonsworth.lunar.client.calculator.mixin;

import java.util.function.Function;
import lombok.Generated;

public class CalculatorHandler3 implements Calculator {
   private final String field1;

   @Override
   public int method1() {
      return 0;
   }

   @Override
   public String method2(Object... var1) {
      return this.field1;
   }

   @SafeVarargs
   @Override
   public final <T> String method3(Function<T, String> var1, T... items) {
      return this.field1;
   }

   @Generated
   CalculatorHandler3(String var1) {
      this.field1 = var1;
   }
}
