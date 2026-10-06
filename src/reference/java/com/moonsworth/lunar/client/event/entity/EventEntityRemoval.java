package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventEntityRemoval extends Highlight {
   private final BridgeExtension field1;

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public EventEntityRemoval(BridgeExtension bridge) {
      this.field1 = bridge;
   }
}
