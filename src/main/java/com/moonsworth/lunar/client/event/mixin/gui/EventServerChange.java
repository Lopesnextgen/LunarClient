package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventServerChange extends LunarEvent {
   private final boolean field1;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public EventServerChange(boolean flag) {
      this.field1 = flag;
   }
}
