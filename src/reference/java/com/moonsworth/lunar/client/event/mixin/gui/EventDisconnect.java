package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import lombok.Generated;

public class EventDisconnect extends LunarEvent {
   private static int field1 = -1;
   private boolean field2 = false;

   public static void method1() {
      method2(false);
   }

   public static void method2(boolean flag) {
      if (field1 < com.moonsworth.lunar.client.event.mixin.fishing.EventTick.field1 - 1) {
         field1 = com.moonsworth.lunar.client.event.mixin.fishing.EventTick.field1;
         LunarEventBus.method29().method12(EventDisconnect.class, () -> new EventDisconnect(flag));
      }
   }

   @Generated
   private EventDisconnect(boolean flag) {
      this.field2 = flag;
   }

   @Generated
   private EventDisconnect() {
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }
}
