package com.moonsworth.lunar.bridge;

@FunctionalInterface
public interface Bridge8Extension2 extends Bridge8Extension {
   int lunar$getHandle();

   static Bridge8Extension2 create(int value) {
      return () -> value;
   }
}
