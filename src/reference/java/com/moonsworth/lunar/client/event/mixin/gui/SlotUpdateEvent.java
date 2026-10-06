package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class SlotUpdateEvent extends Highlight {
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
   public SlotUpdateEvent(int value, ItemStackBridge itemStackBridge, ItemStackBridge itemStackBridge2) {
      this.slot = value;
      this.field1 = itemStackBridge;
      this.field2 = itemStackBridge2;
   }
}
