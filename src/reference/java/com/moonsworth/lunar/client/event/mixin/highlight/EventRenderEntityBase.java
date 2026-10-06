package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public abstract class EventRenderEntityBase {
   public EventRenderEntityBase() {
   }

   public static class EventRenderEntity extends com.moonsworth.lunar.client.event.CancellableEvent {
      private final BridgeExtension field1;

      @Generated
      public BridgeExtension method1() {
         return this.field1;
      }

      @Generated
      public EventRenderEntity(BridgeExtension bridge) {
         this.field1 = bridge;
      }
   }
}
