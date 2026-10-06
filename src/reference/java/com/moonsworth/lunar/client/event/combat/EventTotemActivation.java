package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventTotemActivation extends Highlight {
   private BridgeExtension field1;

   @Generated
   public EventTotemActivation(BridgeExtension bridge) {
      this.field1 = bridge;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }
}
