package com.moonsworth.lunar.client.util;

import org.jetbrains.annotations.NotNull;

public class ThreadModuleDump84<T> implements Comparable<ThreadModuleDump84<T>> {
   private final T element;
   private final float weight;

   public ThreadModuleDump84(T var1, float var2) {
      this.element = (T)var1;
      this.weight = var2;
   }

   public int compareTo(@NotNull ThreadModuleDump84<T> var1) {
      return Float.compare(this.weight, var1.field2);
   }

   public T getElement() {
      return this.element;
   }

   public float value() {
      return this.weight;
   }
}
