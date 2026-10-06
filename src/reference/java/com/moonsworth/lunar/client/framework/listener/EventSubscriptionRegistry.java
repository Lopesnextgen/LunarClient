package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;

public class EventSubscriptionRegistry {
   private final List<EventSubscriptionRegistry.EventSubscription<? extends LunarEvent>> field1 = new ArrayList<>();

   public EventSubscriptionRegistry() {
   }

   public <T extends LunarEvent> void method1(Consumer<T> consumer1, int number2, Class<T> clazz3) {
      this.field1.add(new EventSubscriptionRegistry.EventSubscription<>(consumer1, number2, clazz3));
   }

   public void clear() {
      this.field1.clear();
   }

   public void method2() {
      for (EventSubscriptionRegistry.EventSubscription data92 : this.field1) {
         data92.register();
      }
   }

   public void method3() {
      for (EventSubscriptionRegistry.EventSubscription data92 : this.field1) {
         data92.method1();
      }
   }

   @Generated
   public List<EventSubscriptionRegistry.EventSubscription<? extends LunarEvent>> method4() {
      return this.field1;
   }

   private class EventSubscription<T extends LunarEvent> {
      private final Consumer<T> field1;
      private final int field2;
      private final Class<T> field3;

      private EventSubscription(Consumer<T> consumer1, int number2, Class<T> clazz3) {
         this.field1 = consumer1;
         this.field2 = number2;
         this.field3 = clazz3;
      }

      public void register() {
         LunarEventBus.method29().method4(this.field3, this.field1, this.field2);
      }

      public void method1() {
         LunarEventBus.method29().method6(this.field3, this.field1);
      }

      public Consumer<T> method2() {
         return this.field1;
      }

      public int priority() {
         return this.field2;
      }

      public Class<T> method3() {
         return this.field3;
      }
   }
}
