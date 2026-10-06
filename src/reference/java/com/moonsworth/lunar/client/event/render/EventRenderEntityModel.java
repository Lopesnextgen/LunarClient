package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import lombok.Generated;

public class EventRenderEntityModel extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EntityPlayerBridge field1;

   public EventRenderEntityModel(EntityPlayerBridge entity) {
      this.field1 = entity;
   }

   @Generated
   public EntityPlayerBridge method1() {
      return this.field1;
   }
}
