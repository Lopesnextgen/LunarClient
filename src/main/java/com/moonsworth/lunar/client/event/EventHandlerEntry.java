package com.moonsworth.lunar.client.event;

import com.moonsworth.lunar.client.event.LunarEvent;
import java.util.function.Consumer;

class EventHandlerEntry<T extends LunarEvent> {
   private final Class<T> field1;
   private final Consumer<T> consumer;
   private final int field2;

   private EventHandlerEntry(Class<T> clazz1, Consumer<T> consumer2, int value) {
      this.field1 = clazz1;
      this.consumer = consumer2;
      this.field2 = value;
   }

   public Class<T> method1() {
      return this.field1;
   }

   public Consumer<T> method2() {
      return this.consumer;
   }

   public int priority() {
      return this.field2;
   }
}
