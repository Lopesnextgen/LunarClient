package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public abstract class EventEntityInteract extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final BridgeExtension field1;

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public EventEntityInteract(BridgeExtension bridgeextension1) {
      this.field1 = bridgeextension1;
   }

   public static class Interact extends EventEntityInteract {
      public Interact(BridgeExtension bridgeextension1) {
         super(bridgeextension1);
      }
   }
}
