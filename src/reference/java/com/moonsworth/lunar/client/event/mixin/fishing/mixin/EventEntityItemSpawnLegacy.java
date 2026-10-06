package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventEntityItemSpawnLegacy extends Highlight {
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
   public EventEntityItemSpawnLegacy(ItemStackBridge itemStackBridge, BridgeExtension bridge) {
      this.field1 = itemStackBridge;
      this.field2 = bridge;
   }
}
