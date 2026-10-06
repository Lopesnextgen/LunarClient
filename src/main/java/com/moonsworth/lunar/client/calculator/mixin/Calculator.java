package com.moonsworth.lunar.client.calculator.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;

public interface Calculator {
   int method1();

   String method2(Object... var1);

   <T> String method3(Function<T, String> var1, T... var2);

   static Calculator method4(String text) {
      return new CalculatorHandler3(text);
   }

   static Calculator.Data method5() {
      return new Calculator.Data();
   }

   class Data {
      private final List<Object> field1 = new ArrayList<>();
      private List<Object> field2 = null;
      private String field3 = "";
      private boolean field4 = false;

      public Calculator.Data method1(String var1) {
         this.field3 = this.field3 + var1;
         return this;
      }

      public Calculator.Data method2(Supplier<?> var1) {
         if (var1 == null) {
            return this;
         }

         if (this.field2 == null) {
            this.field2 = new ArrayList<>();
         }

         if (!this.field3.isEmpty()) {
            this.field2.add(this.field3);
            this.field3 = "";
         }

         this.field4 = true;
         this.field2.add(var1);
         return this;
      }

      public Calculator.Data method3() {
         if (this.field2 != null) {
            this.method4();
         } else {
            this.field1.add(this.field3);
         }

         this.field3 = "";
         return this;
      }

      private void method4() {
         if (!this.field3.isEmpty()) {
            this.field2.add(this.field3);
         }

         Object[] var1 = this.field2.toArray();
         this.field1.add((Supplier<String>)() -> {
            StringBuilder var1x = new StringBuilder();

            for (Object var5 : var1) {
               if (var5 instanceof Supplier supplier) {
                  var1x.append(supplier.get().toString());
               } else {
                  var1x.append(var5.toString());
               }
            }

            return var1x.toString();
         });
         this.field2 = null;
      }

      public Calculator method5() {
         if (!this.field1.isEmpty() || this.field2 != null && !this.field2.isEmpty()) {
            if (this.field2 != null) {
               this.method4();
            } else if (!this.field3.isEmpty()) {
               this.field1.add(this.field3);
            }

            return this.field4 ? new CalculatorHandler(this.field1.toArray()) : new CalculatorHandler2(this.field1.toArray(new String[0]));
         } else if (this.field3.isEmpty()) {
            throw new RuntimeException("CachedReplacement.Builder created with nothing set!");
         } else {
            return new CalculatorHandler3(this.field3);
         }
      }

      @Generated
      private Data() {
      }
   }
}
