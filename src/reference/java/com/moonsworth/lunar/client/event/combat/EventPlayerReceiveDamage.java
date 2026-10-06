package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventPlayerReceiveDamage extends LunarEvent {
   private final Bridge6_10 field1;
   private final DamageSourceBridge field2;

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public DamageSourceBridge method2() {
      return this.field2;
   }

   @Generated
   public EventPlayerReceiveDamage(Bridge6_10 bridge6_101, DamageSourceBridge horsestats192) {
      this.field1 = bridge6_101;
      this.field2 = horsestats192;
   }
}
