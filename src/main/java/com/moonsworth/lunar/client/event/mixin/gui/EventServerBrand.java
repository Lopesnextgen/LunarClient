package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventServerBrand extends LunarEvent {
   private final String field1;

   @Generated
   public EventServerBrand(String text) {
      this.field1 = text;
   }

   @Generated
   public String method1() {
      return this.field1;
   }
}
