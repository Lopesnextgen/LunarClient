package com.moonsworth.lunar.client.calculator.mixin;

import java.util.function.Function;
import lombok.Generated;

public class CalculatorHandler2 implements Calculator {
   private final String[] field1;

   @Override
   public int method1() {
      return this.field1.length - 1;
   }

   @Override
   public String method2(Object... var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < this.field1.length; var3++) {
         var2.append(this.field1[var3]);
         if (var3 < var1.length) {
            var2.append(var1[var3].toString());
         }
      }

      return var2.toString();
   }

   @SafeVarargs
   @Override
   public final <T> String method3(Function<T, String> var1, T... var2) {
      StringBuilder var3 = new StringBuilder();

      for (int var4 = 0; var4 < this.field1.length; var4++) {
         var3.append(this.field1[var4]);
         if (var4 < var2.length) {
            var3.append((String)var1.apply(var2[var4]));
         }
      }

      return var3.toString();
   }

   @Generated
   CalculatorHandler2(String[] var1) {
      this.field1 = var1;
   }
}
