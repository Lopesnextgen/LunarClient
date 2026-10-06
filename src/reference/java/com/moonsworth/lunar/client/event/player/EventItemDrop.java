package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;

public class EventItemDrop extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final ItemStackBridge field1;

   @Generated
   public ItemStackBridge method1() {
      return this.field1;
   }

   @Generated
   public EventItemDrop(ItemStackBridge bridgeextension_41) {
      this.field1 = bridgeextension_41;
   }
}
