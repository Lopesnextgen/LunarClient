package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventItemRightClick extends Highlight {
   private Bridge6_10 field1;
   private ItemStackBridge field2;

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public ItemStackBridge method2() {
      return this.field2;
   }

   @Generated
   public EventItemRightClick(Bridge6_10 bridge6_10, ItemStackBridge itemStackBridge) {
      this.field1 = bridge6_10;
      this.field2 = itemStackBridge;
   }
}
