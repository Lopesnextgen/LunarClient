package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventEntityMovement extends LunarEvent {
   private final BridgeExtension field1;
   private final boolean field2;

   @Generated
   public EventEntityMovement(BridgeExtension bridge, boolean flag) {
      this.field1 = bridge;
      this.field2 = flag;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }
}
