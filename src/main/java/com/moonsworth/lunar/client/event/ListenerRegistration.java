package com.moonsworth.lunar.client.event;

import java.util.function.Consumer;

public class ListenerRegistration<T> {
   private final int field1;
   private final Consumer<T> field2;

   public ListenerRegistration(int value, Consumer<T> consumer) {
      this.field1 = value;
      this.field2 = consumer;
   }

   public int priority() {
      return this.field1;
   }

   public Consumer<T> method1() {
      return this.field2;
   }
}
