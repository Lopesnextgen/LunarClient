package com.moonsworth.lunar.client.network.apollo;

import com.moonsworth.lunar.client.highlight.Highlight;
import java.util.function.Consumer;

class ApolloModuleHandler$ListenerEntry<T extends Highlight> {
   private final Class<T> field1;
   private final Consumer<T> consumer;
   private final int field2;

   private ApolloModuleHandler$ListenerEntry(Class<T> type, Consumer<T> consumer2, int value) {
      this.field1 = type;
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
