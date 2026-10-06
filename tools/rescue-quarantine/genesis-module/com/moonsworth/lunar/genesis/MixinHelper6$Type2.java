package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Function;

enum MixinHelper6$Type2 implements Function<Object, Object> {
   INSTANCE;

   MixinHelper6$Type2() {
   }

   public @Nullable Object apply(@Nullable Object obj1) {
      return obj1;
   }

   @Override
   public String toString() {
      return "Functions.identity()";
   }
}
