package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventEntityStatusUpdate extends Highlight {
   private BridgeExtension field1;
   private byte field2;

   @Generated
   public EventEntityStatusUpdate(BridgeExtension bridge, byte value) {
      this.field1 = bridge;
      this.field2 = value;
   }

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public byte method2() {
      return this.field2;
   }
}
