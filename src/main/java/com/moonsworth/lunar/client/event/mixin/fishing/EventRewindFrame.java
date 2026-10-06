package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventRewindFrame extends LunarEvent {
   private final float field1;

   @Generated
   public EventRewindFrame(float value) {
      this.field1 = value;
   }

   @Generated
   public float method1() {
      return this.field1;
   }
}
