package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class EventChunkLifecycle extends Highlight {
   private final Itemcounter2 field1;

   @Generated
   public EventChunkLifecycle(Itemcounter2 var1) {
      this.field1 = var1;
   }

   @Generated
   public Itemcounter2 method1() {
      return this.field1;
   }

   public static class EventChunkUnloaded extends EventChunkLifecycle {
      public EventChunkUnloaded(Itemcounter2 var1) {
         super(var1);
      }
   }

   public static class EventChunkLoaded extends EventChunkLifecycle {
      public EventChunkLoaded(Itemcounter2 var1) {
         super(var1);
      }
   }
}
