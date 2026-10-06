package com.moonsworth.lunar.client.util.collection;

import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface ImmutableListView<T> extends List<T> {
   static <T> ImmutableListView<T> method1(List<T> list) {
      return new ImmutableListViewImpl<>(list);
   }

   @Override
   default boolean add(T t) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean addAll(@NotNull Collection<? extends T> list1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean remove(Object object) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean removeAll(@NotNull Collection<?> list1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean retainAll(@NotNull Collection<?> list1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default void clear() {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default T set(int number1, T value2) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default void add(int number1, T value2) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default T remove(int number1) {
      throw new UnsupportedOperationException("List is immutable!");
   }

   @Override
   default boolean addAll(int number1, @NotNull Collection<? extends T> list2) {
      throw new UnsupportedOperationException("List is immutable!");
   }
}
