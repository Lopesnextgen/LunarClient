package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventEntityHealthUpdate extends LunarEvent {
   private EntityLivingBridge field1;
   private float field2;
   private float field3;

   @Generated
   public EntityLivingBridge method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   public EventEntityHealthUpdate(EntityLivingBridge bridgeextension2_51, float value, float value2) {
      this.field1 = bridgeextension2_51;
      this.field2 = value;
      this.field3 = value2;
   }
}
