package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventWorld extends LunarEvent {
   private final Itemcounter6 field1;

   @Generated
   public Itemcounter6 method1() {
      return this.field1;
   }

   @Generated
   private EventWorld(Itemcounter6 itemcounter61) {
      this.field1 = itemcounter61;
   }

   public static class EventWorldChange extends EventWorld {
      public EventWorldChange(Itemcounter6 itemcounter61) {
         super(itemcounter61);
      }
   }

   public static class EventWorldLoad extends EventWorld {
      public EventWorldLoad(Itemcounter6 itemcounter61) {
         super(itemcounter61);
      }
   }
}
