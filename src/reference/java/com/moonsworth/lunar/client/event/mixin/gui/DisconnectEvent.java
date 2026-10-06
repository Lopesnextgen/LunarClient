package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import lombok.Generated;

public class DisconnectEvent extends Highlight {
   private static int field1 = -1;
   private boolean field2 = false;

   public static void method1() {
      method2(false);
   }

   public static void method2(boolean flag) {
      if (field1 < com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.field1 - 1) {
         field1 = com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.field1;
         ClientEventBus.method29().method12(DisconnectEvent.class, () -> new DisconnectEvent(flag));
      }
   }

   @Generated
   private DisconnectEvent(boolean flag) {
      this.field2 = flag;
   }

   @Generated
   private DisconnectEvent() {
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }
}
