package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventUseItem extends LunarEvent {
   private final int field1;

   @Generated
   public int method1() {
      return this.field1;
   }

   @Generated
   public EventUseItem(int value) {
      this.field1 = value;
   }
}
