package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventHorizonQuery extends Highlight {
   private double value;

   @Generated
   public double getValue() {
      return this.value;
   }

   @Generated
   public void setValue(double value2) {
      this.value = value2;
   }
}
