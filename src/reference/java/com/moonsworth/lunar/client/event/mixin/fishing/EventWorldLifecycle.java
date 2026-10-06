package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class EventWorldLifecycle extends Highlight {
   private final Itemcounter6 field1;

   @Generated
   public Itemcounter6 method1() {
      return this.field1;
   }

   @Generated
   private EventWorldLifecycle(Itemcounter6 var1) {
      this.field1 = var1;
   }

   public static class EventWorldChanged extends EventWorldLifecycle {
      public EventWorldChanged(Itemcounter6 var1) {
         super(var1);
      }
   }

   public static class EventWorldLoaded extends EventWorldLifecycle {
      public EventWorldLoaded(Itemcounter6 var1) {
         super(var1);
      }
   }
}
