package com.moonsworth.lunar.bridge;

@FunctionalInterface
public interface GlObjectHandleBridge extends Bridge_35 {
   int lunar$getHandle();

   static GlObjectHandleBridge create(int value) {
      return () -> value;
   }
}
