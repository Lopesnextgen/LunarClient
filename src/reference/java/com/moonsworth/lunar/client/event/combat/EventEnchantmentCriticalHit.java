package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public class EventEnchantmentCriticalHit extends com.moonsworth.lunar.client.event.CancellableEvent {
   private BridgeExtension field1;

   @Generated
   public EventEnchantmentCriticalHit(BridgeExtension bridge) {
      this.field1 = bridge;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }
}
