package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventEntityChunkBase extends LunarEvent {
   public BridgeExtension field1;

   @Generated
   public EventEntityChunkBase(BridgeExtension bridgeextension1) {
      this.field1 = bridgeextension1;
   }

   public static class EventEntityChunk extends EventEntityChunkBase {
      public int field2;
      public int field3;

      public EventEntityChunk(BridgeExtension bridgeextension1, int value, int value2) {
         super(bridgeextension1);
         this.field2 = value;
         this.field3 = value2;
      }
   }
}
