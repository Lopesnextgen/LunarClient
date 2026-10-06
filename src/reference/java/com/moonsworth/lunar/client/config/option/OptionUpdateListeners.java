package com.moonsworth.lunar.client.config.option;

import java.util.function.Consumer;

public interface OptionUpdateListeners<T> {
   void forEach(Consumer<Consumer<? super T>> consumer1);

   void method1(Consumer<? super T> consumer1);

   void method2(Consumer<? super T> consumer1);

   void method3(Runnable runnable1);

   void method4(Runnable runnable1);

   default void method5(T t) {
      this.forEach(arg1x -> arg1x.accept((T)t));
   }

   static <T> OptionUpdateListeners<T> method6() {
      return new ListenerSet<>();
   }
}
