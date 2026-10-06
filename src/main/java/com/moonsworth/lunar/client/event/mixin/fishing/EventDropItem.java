package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventDropItem extends LunarEvent {
   private boolean field1;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public EventDropItem(boolean flag) {
      this.field1 = flag;
   }
}
