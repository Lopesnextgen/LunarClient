package com.moonsworth.lunar.client.event.mixin.holograms;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventFeatureToggle extends LunarEvent {
   private final Framework7Extension field1;
   private final boolean field2;

   @Generated
   public EventFeatureToggle(Framework7Extension framework7, boolean flag) {
      this.field1 = framework7;
      this.field2 = flag;
   }

   @Generated
   public Framework7Extension method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }
}
