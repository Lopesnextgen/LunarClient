package com.moonsworth.lunar.client.chat.translation;

import java.util.function.Function;
import lombok.Generated;

public class ConstantReplacement implements CachedReplacement {
   private final String field1;

   @Override
   public int method1() {
      return 0;
   }

   @Override
   public String method2(Object... items1) {
      return this.field1;
   }

   @SafeVarargs
   @Override
   public final <T> String method3(Function<T, String> function1, T... items2) {
      return this.field1;
   }

   @Generated
   ConstantReplacement(String text) {
      this.field1 = text;
   }
}
