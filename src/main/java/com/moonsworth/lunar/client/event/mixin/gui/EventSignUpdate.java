package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventSignUpdate extends LunarEvent {
   private String[] field1;

   @Generated
   public EventSignUpdate(String[] items1) {
      this.field1 = items1;
   }

   @Generated
   public String[] method1() {
      return this.field1;
   }
}
