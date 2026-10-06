package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventEntityRemove extends LunarEvent {
   private final BridgeExtension field1;

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public EventEntityRemove(BridgeExtension bridge) {
      this.field1 = bridge;
   }
}
