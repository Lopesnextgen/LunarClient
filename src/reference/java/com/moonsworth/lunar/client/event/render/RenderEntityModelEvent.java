package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import lombok.Generated;

public class RenderEntityModelEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final EntityPlayerBridge field1;

   public RenderEntityModelEvent(EntityPlayerBridge entity) {
      this.field1 = entity;
   }

   @Generated
   public EntityPlayerBridge method1() {
      return this.field1;
   }
}
