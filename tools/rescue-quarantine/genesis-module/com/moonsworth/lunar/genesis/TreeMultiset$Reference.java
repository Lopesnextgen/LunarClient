package com.moonsworth.lunar.genesis;

import java.util.ConcurrentModificationException;
import org.checkerframework.checker.nullness.qual.Nullable;

final class TreeMultiset$Reference<T> {
   private @Nullable T value;

   private TreeMultiset$Reference() {
   }

   public @Nullable T get() {
      return this.value;
   }

   public void checkAndSet(@Nullable T value1, T value2) {
      if (this.value != value1) {
         throw new ConcurrentModificationException();
      }

      this.value = (T)value2;
   }

   void clear() {
      this.value = null;
   }
}
