package com.moonsworth.lunar.genesis;

enum MixinHelper20$Type implements Predicate<Object> {
   ALWAYS_TRUE,
   ALWAYS_FALSE,
   IS_NULL,
   NOT_NULL;

   MixinHelper20$Type() {
   }

   <T> Predicate<T> withNarrowedType() {
      return this;
   }
}
