package com.moonsworth.lunar.client.config.option.trait;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface TraitListener<T> {
   void onChange(@NotNull TraitListener.Type type1, @Nullable T value2, @Nullable T value3);

   default void invoke(@NotNull TraitListener.Type type1, @Nullable Object obj2, @Nullable Object obj3) {
      this.onChange(type1, (T)obj2, (T)obj3);
   }

   enum Type {
      TRAIT_REMOVED,
      TRAIT_REMOVED_CONDITIONALLY,
      TRAIT_SET;

      Type() {
      }
   }
}
