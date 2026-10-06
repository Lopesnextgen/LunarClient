package com.moonsworth.lunar.bridge;

@FunctionalInterface
public interface GlHandleBridge extends GlObjectBridge {
   int lunar$getHandle();

   static GlHandleBridge create(int value) {
      return () -> value;
   }
}
