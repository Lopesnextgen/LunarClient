package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge6_10;
import lombok.Generated;

public class EventHologramUpdate extends com.moonsworth.lunar.client.event.LunarEvent {
   private final Bridge6_10 field1;

   @Generated
   public EventHologramUpdate(Bridge6_10 bridge6_101) {
      this.field1 = bridge6_101;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }
}
