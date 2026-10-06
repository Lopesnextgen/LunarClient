package com.moonsworth.lunar.client.util.concurrent;

import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerExtension<T> extends Consumer<T> {
   void acceptWithException(T value1);

   @Override
   default void accept(T value1) {
      try {
         this.acceptWithException((T)value1);
      } catch (Exception exception3) {
         exception3.printStackTrace();
      }
   }
}
