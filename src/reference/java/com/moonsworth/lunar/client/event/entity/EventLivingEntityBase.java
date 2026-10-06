package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class EventLivingEntityBase extends LunarEvent {
   public EntityLivingBridge field1;

   @Generated
   public EntityLivingBridge method1() {
      return this.field1;
   }

   @Generated
   public EventLivingEntityBase(EntityLivingBridge bridgeextension2_51) {
      this.field1 = bridgeextension2_51;
   }

   public static class EventLivingEntity extends EventLivingEntityBase {
      public EventLivingEntity(EntityLivingBridge bridgeextension2_51) {
         super(bridgeextension2_51);
      }
   }

   public static class EventEntityScale extends EventLivingEntityBase {
      private float scale;

      public EventEntityScale(EntityLivingBridge bridgeextension2_51, float value2) {
         super(bridgeextension2_51);
         this.scale = value2;
         this.CHCICRCHHRCCHRRRCHHHCIRIHOICIC.bridge$setLunarScale(1.0F);
      }

      public void setScale(float value1) {
         this.scale = value1;
         this.CHCICRCHHRCCHRRRCHHHCIRIHOICIC.bridge$setLunarScale(value1);
      }

      public void method1(float value1) {
         this.scale *= value1;
         this.CHCICRCHHRCCHRRRCHHHCIRIHOICIC.bridge$setLunarScale(this.CHCICRCHHRCCHRRRCHHHCIRIHOICIC.bridge$getLunarScale() * value1);
      }

      @Generated
      public float getScale() {
         return this.scale;
      }
   }
}
