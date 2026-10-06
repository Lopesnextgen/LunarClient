package com.moonsworth.lunar.client.util;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector2d;

public class ThreadModuleDump81<T> {
   @NotNull
   private final Vector2d position;
   @NotNull
   private final T value;

   public ThreadModuleDump81(@NotNull Vector2d var1, @NotNull T var2) {
      this.position = var1;
      this.value = (T)var2;
   }

   @NotNull
   public Vector2d getPosition() {
      return this.position;
   }

   @NotNull
   public T getValue() {
      return this.value;
   }
}
