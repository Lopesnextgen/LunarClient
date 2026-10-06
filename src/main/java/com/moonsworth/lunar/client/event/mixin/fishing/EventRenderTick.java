package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventRenderTick extends LunarEvent {
   private final float field1;

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   private EventRenderTick(float value1) {
      this.field1 = value1;
   }

   public static class EventRenderTickStart extends EventRenderTick {
      public EventRenderTickStart(float value1) {
         super(value1);
      }
   }

   public static class EventRenderTickEnd extends EventRenderTick {
      public EventRenderTickEnd(float value1) {
         super(value1);
      }
   }
}
