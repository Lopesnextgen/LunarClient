package com.moonsworth.lunar.genesis;
import com.google.common.reflect.TypeToken;
import com.google.common.collect.ImmutableSet;

final class SubscriberRegistry$2 extends MixinHelper8_4<Class<?>, ImmutableSet<Class<?>>> {
   SubscriberRegistry$2() {
   }

   public ImmutableSet<Class<?>> method1(Class<?> clazz1) {
      return ImmutableSet.method10(TypeToken.method1(clazz1).method13().rawTypes());
   }
}
