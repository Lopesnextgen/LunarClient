package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

enum MixinHelper6$Type implements Function<Object, String> {
   INSTANCE;

   MixinHelper6$Type() {
   }

   public String apply(Object obj1) {
      Preconditions.checkNotNull(obj1);
      return obj1.toString();
   }

   @Override
   public String toString() {
      return "Functions.toStringFunction()";
   }
}
