package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventEntityItemSpawn extends LunarEvent {
   private final ItemStackBridge field1;
   private final BridgeExtension field2;

   @Generated
   public ItemStackBridge getItem() {
      return this.field1;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field2;
   }

   @Generated
   public EventEntityItemSpawn(ItemStackBridge bridgeextension_41, BridgeExtension bridge) {
      this.field1 = bridgeextension_41;
      this.field2 = bridge;
   }
}
