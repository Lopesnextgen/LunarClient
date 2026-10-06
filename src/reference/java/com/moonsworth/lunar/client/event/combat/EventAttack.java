package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventAttack extends LunarEvent {
   private final MovingObjectPositionBridge field1;

   @Generated
   public MovingObjectPositionBridge method1() {
      return this.field1;
   }

   @Generated
   public EventAttack(MovingObjectPositionBridge horsestats211) {
      this.field1 = horsestats211;
   }
}
