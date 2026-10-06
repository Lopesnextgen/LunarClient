package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public abstract class EntityRenderBaseEvent {
   public static class EntityRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
      private final BridgeExtension field1;

      @Generated
      public BridgeExtension method1() {
         return this.field1;
      }

      @Generated
      public EntityRenderEvent(BridgeExtension bridge) {
         this.field1 = bridge;
      }
   }
}
