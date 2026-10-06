package com.moonsworth.lunar.client.chat.translation;

import java.util.function.Function;
import lombok.Generated;

public class StringArrayReplacement implements CachedReplacement {
   private final String[] field1;

   @Override
   public int method1() {
      return this.field1.length - 1;
   }

   @Override
   public String method2(Object... items1) {
      StringBuilder builder2 = new StringBuilder();

      for (int index3 = 0; index3 < this.field1.length; index3++) {
         builder2.append(this.field1[index3]);
         if (index3 < items1.length) {
            builder2.append(items1[index3].toString());
         }
      }

      return builder2.toString();
   }

   @SafeVarargs
   @Override
   public final <T> String method3(Function<T, String> function1, T... items2) {
      StringBuilder builder3 = new StringBuilder();

      for (int index4 = 0; index4 < this.field1.length; index4++) {
         builder3.append(this.field1[index4]);
         if (index4 < items2.length) {
            builder3.append((String)function1.apply(items2[index4]));
         }
      }

      return builder3.toString();
   }

   @Generated
   StringArrayReplacement(String[] items1) {
      this.field1 = items1;
   }
}
