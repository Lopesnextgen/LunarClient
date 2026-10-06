package com.moonsworth.lunar.client.chat.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;

public interface CachedReplacement {
   int method1();

   String method2(Object... items1);

   <T> String method3(Function<T, String> function1, T... items2);

   static CachedReplacement method4(String text) {
      return new ConstantReplacement(text);
   }

   static CachedReplacement.Data method5() {
      return new CachedReplacement.Data();
   }

   class Data {
      private final List<Object> field1 = new ArrayList<>();
      private List<Object> field2 = null;
      private String field3 = "";
      private boolean field4 = false;

      public CachedReplacement.Data method1(String text) {
         this.field3 = this.field3 + text;
         return this;
      }

      public CachedReplacement.Data method2(Supplier<?> supplier1) {
         if (supplier1 == null) {
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
         this.field2.add(supplier1);
         return this;
      }

      public CachedReplacement.Data method3() {
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

         Object[] items1 = this.field2.toArray();
         this.field1.add((Supplier<String>)() -> {
            StringBuilder builder1x = new StringBuilder();

            for (Object obj5 : items1) {
               if (obj5 instanceof Supplier supplier6) {
                  builder1x.append(supplier6.get().toString());
               } else {
                  builder1x.append(obj5.toString());
               }
            }

            return builder1x.toString();
         });
         this.field2 = null;
      }

      public CachedReplacement method5() {
         if (!this.field1.isEmpty() || this.field2 != null && !this.field2.isEmpty()) {
            if (this.field2 != null) {
               this.method4();
            } else if (!this.field3.isEmpty()) {
               this.field1.add(this.field3);
            }

            return this.field4 ? new SupplierReplacement(this.field1.toArray()) : new StringArrayReplacement(this.field1.toArray(new String[0]));
         } else if (this.field3.isEmpty()) {
            throw new RuntimeException("CachedReplacement.Builder created with nothing set!");
         } else {
            return new ConstantReplacement(this.field3);
         }
      }

      @Generated
      private Data() {
      }
   }
}
