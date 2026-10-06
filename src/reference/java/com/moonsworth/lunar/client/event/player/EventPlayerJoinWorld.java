package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventPlayerJoinWorld extends LunarEvent {
   private Bridge5_11 field1;

   @Generated
   public EventPlayerJoinWorld(Bridge5_11 bridge5_111) {
      this.field1 = bridge5_111;
   }

   @Generated
   public Bridge5_11 method1() {
      return this.field1;
   }
}
