package com.moonsworth.lunar.client.event;

import java.util.function.Consumer;

public interface EventBusAccess {
   default <T extends LunarEvent> void handle(Class<T> clazz1, Consumer<T> consumer2) {
      LunarEventBus.method29().method2(clazz1, consumer2);
   }

   default <T extends LunarEvent> void method1(Class<T> clazz1, Runnable runnable2) {
      this.handle(clazz1, arg1x -> runnable2.run());
   }

   default <T extends LunarEvent> void method2(Class<T> clazz1, Consumer<T> consumer2, int number3) {
      LunarEventBus.method29().method4(clazz1, consumer2, number3);
   }

   default <T extends LunarEvent> void method3(Class<T> clazz1, Runnable runnable2, int number3) {
      this.method2(clazz1, arg1x -> runnable2.run(), number3);
   }
}
