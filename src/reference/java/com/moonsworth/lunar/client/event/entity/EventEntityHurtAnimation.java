package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import lombok.Generated;

public class EventEntityHurtAnimation extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EntityLivingBridge field1;
   private float yaw;

   @Generated
   public EntityLivingBridge method1() {
      return this.field1;
   }

   @Generated
   public float getYaw() {
      return this.yaw;
   }

   @Generated
   public EventEntityHurtAnimation(EntityLivingBridge bridgeextension2_51, float value) {
      this.field1 = bridgeextension2_51;
      this.yaw = value;
   }
}
