package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventSlotUpdate extends LunarEvent {
   private final int slot;
   private final ItemStackBridge field1;
   private final ItemStackBridge field2;

   @Generated
   public int getSlot() {
      return this.slot;
   }

   @Generated
   public ItemStackBridge method2() {
      return this.field1;
   }

   @Generated
   public ItemStackBridge method3() {
      return this.field2;
   }

   @Generated
   public EventSlotUpdate(int value, ItemStackBridge bridgeextension_42, ItemStackBridge bridgeextension_43) {
      this.slot = value;
      this.field1 = bridgeextension_42;
      this.field2 = bridgeextension_43;
   }
}
