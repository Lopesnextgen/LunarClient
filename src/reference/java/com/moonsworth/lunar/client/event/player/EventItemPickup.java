package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import lombok.Generated;

public class EventItemPickup extends EventPlayerState {
   private final Bridge6_10 field1;
   private final EntityItemBridge field2;

   public EventItemPickup(Bridge6_10 bridge6_101, EntityItemBridge entity) {
      this.field1 = bridge6_101;
      this.field2 = entity;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public EntityItemBridge method2() {
      return this.field2;
   }
}
