package com.moonsworth.lunar.client.util.gui;

import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface ListExtension2<T> extends List<T> {
   static <T> ListExtension2<T> method1(List<T> list) {
      return new ListExtension22<>(list);
   }

   @Override
   default boolean add(T var1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean addAll(@NotNull Collection<? extends T> var1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean remove(Object var1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean removeAll(@NotNull Collection<?> var1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean retainAll(@NotNull Collection<?> var1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default void clear() {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default T set(int var1, T var2) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default void add(int var1, T var2) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default T remove(int var1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean addAll(int var1, @NotNull Collection<? extends T> var2) {
      throw new UnsupportedOperationException("List is immutable!");
   }
}
