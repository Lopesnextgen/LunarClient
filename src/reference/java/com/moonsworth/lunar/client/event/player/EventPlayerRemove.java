package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventPlayerRemove extends LunarEvent {
   private Bridge6_10 field1;

   @Generated
   public EventPlayerRemove(Bridge6_10 bridge6_101) {
      this.field1 = bridge6_101;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }
}
