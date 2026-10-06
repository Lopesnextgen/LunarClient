package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventEntityMove extends Highlight {
   private final BridgeExtension field1;
   private final boolean field2;

   @Generated
   public EventEntityMove(BridgeExtension bridge, boolean flag) {
      this.field1 = bridge;
      this.field2 = flag;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }
}
