package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;

public class ItemDropEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final ItemStackBridge field1;

   @Generated
   public ItemStackBridge method1() {
      return this.field1;
   }

   @Generated
   public ItemDropEvent(ItemStackBridge itemStackBridge) {
      this.field1 = itemStackBridge;
   }
}
