package com.moonsworth.lunar.client.util.math;

import org.jetbrains.annotations.NotNull;

public class WeightedValue<T> implements Comparable<WeightedValue<T>> {
   private final T value;
   private final float cost;

   public WeightedValue(T value1, float value2) {
      this.value = (T)value1;
      this.cost = value2;
   }

   public int compareTo(@NotNull WeightedValue<T> threadmoduledump841) {
      return Float.compare(this.cost, threadmoduledump841.field2);
   }

   public T getValue() {
      return this.value;
   }

   public float value() {
      return this.cost;
   }
}
