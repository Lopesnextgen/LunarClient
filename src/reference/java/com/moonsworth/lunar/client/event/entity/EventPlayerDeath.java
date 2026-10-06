package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import lombok.Generated;

public class EventPlayerDeath extends com.moonsworth.lunar.client.event.CancellableEvent {
   public EntityLivingBridge field1;

   @Generated
   public EventPlayerDeath(EntityLivingBridge bridgeextension2_51) {
      this.field1 = bridgeextension2_51;
   }

   @Generated
   public EntityLivingBridge method1() {
      return this.field1;
   }
}
