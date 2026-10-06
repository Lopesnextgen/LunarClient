package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class EventChunkBase extends Highlight {
   public BridgeExtension field1;

   @Generated
   public EventChunkBase(BridgeExtension var1) {
      this.field1 = var1;
   }

   public static class EventEntityChunkEnter extends EventChunkBase {
      public int field2;
      public int field3;

      public EventEntityChunkEnter(BridgeExtension var1, int value, int value2) {
         super(var1);
         this.field2 = value;
         this.field3 = value2;
      }
   }
}
