package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public class EventBridgePayload {
   private BridgeExtension field1;

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public EventBridgePayload(BridgeExtension bridge) {
      this.field1 = bridge;
   }
}
