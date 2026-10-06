package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import lombok.Generated;

public class EventOtherPlayerDamage extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EntityLivingBridge field1;
   private DamageSourceBridge field2;
   private float field3;

   @Generated
   public EntityLivingBridge method1() {
      return this.field1;
   }

   @Generated
   public DamageSourceBridge method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   public EventOtherPlayerDamage(EntityLivingBridge bridgeextension2_51, DamageSourceBridge horsestats192, float value) {
      this.field1 = bridgeextension2_51;
      this.field2 = horsestats192;
      this.field3 = value;
   }
}
