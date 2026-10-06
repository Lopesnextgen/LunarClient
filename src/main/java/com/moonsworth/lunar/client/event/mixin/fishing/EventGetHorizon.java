package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventGetHorizon extends LunarEvent {
   private double value;

   public EventGetHorizon() {
   }

   @Generated
   public double getValue() {
      return this.value;
   }

   @Generated
   public void setValue(double value2) {
      this.value = value2;
   }
}
