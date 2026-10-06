package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public abstract class EventInteractEntity extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final BridgeExtension field1;

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public EventInteractEntity(BridgeExtension var1) {
      this.field1 = var1;
   }

   public static class Data extends EventInteractEntity {
      public Data(BridgeExtension var1) {
         super(var1);
      }
   }
}
