package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventTotemPop extends LunarEvent {
   private BridgeExtension field1;

   @Generated
   public EventTotemPop(BridgeExtension bridge) {
      this.field1 = bridge;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }
}
