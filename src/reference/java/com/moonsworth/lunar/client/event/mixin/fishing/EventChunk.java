package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventChunk extends LunarEvent {
   private final ChunkBridge field1;

   @Generated
   public EventChunk(ChunkBridge itemcounter21) {
      this.field1 = itemcounter21;
   }

   @Generated
   public ChunkBridge method1() {
      return this.field1;
   }

   public static class EventChunkUnload extends EventChunk {
      public EventChunkUnload(ChunkBridge itemcounter21) {
         super(itemcounter21);
      }
   }

   public static class EventChunkLoad extends EventChunk {
      public EventChunkLoad(ChunkBridge itemcounter21) {
         super(itemcounter21);
      }
   }
}
