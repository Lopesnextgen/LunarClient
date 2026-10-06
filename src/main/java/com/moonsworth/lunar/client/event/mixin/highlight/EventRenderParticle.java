package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.EntityFXBridge;
import lombok.Generated;

public class EventRenderParticle extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EntityFXBridge field1;

   @Generated
   public EntityFXBridge method1() {
      return this.field1;
   }

   @Generated
   public EventRenderParticle(EntityFXBridge bridge4_121) {
      this.field1 = bridge4_121;
   }
}
