package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

@VersionGate(min = 8)
public class EventEntityPosition extends LunarEvent {
   private Horsestats20Extension field1;

   @Generated
   public Horsestats20Extension method1() {
      return this.field1;
   }

   @Generated
   public EventEntityPosition(Horsestats20Extension horsestats20) {
      this.field1 = horsestats20;
   }
}
