package com.moonsworth.lunar.client.util.math;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector2d;

public class Vector2dPair<T> {
   @NotNull
   private final Vector2d position;
   @NotNull
   private final T value;

   public Vector2dPair(@NotNull Vector2d vector2d1, @NotNull T value2) {
      this.position = vector2d1;
      this.value = (T)value2;
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
