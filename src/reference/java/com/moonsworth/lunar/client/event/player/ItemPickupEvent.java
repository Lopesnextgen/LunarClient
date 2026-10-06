package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import lombok.Generated;

public class ItemPickupEvent extends PlayerStateEvent {
   private final Bridge6_10 field1;
   private final ItemEntityBridge field2;

   public ItemPickupEvent(Bridge6_10 bridge6_10, ItemEntityBridge entity) {
      this.field1 = bridge6_10;
      this.field2 = entity;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public ItemEntityBridge method2() {
      return this.field2;
   }
}
