package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class EventLivingBase extends Highlight {
   public BridgeExtension2_5 field1;

   @Generated
   public BridgeExtension2_5 method1() {
      return this.field1;
   }

   @Generated
   public EventLivingBase(BridgeExtension2_5 var1) {
      this.field1 = var1;
   }

   public static class EventLiving extends EventLivingBase {
      public EventLiving(BridgeExtension2_5 var1) {
         super(var1);
      }
   }

   public static class EventEntityScaling extends EventLivingBase {
      private float scale;

      public EventEntityScaling(BridgeExtension2_5 var1, float value) {
         super(var1);
         this.scale = value;
         this.field1.bridge$setLunarScale(1.0F);
      }

      public void setScale(float var1) {
         this.scale = var1;
         this.field1.bridge$setLunarScale(var1);
      }

      public void method1(float var1) {
         this.scale *= var1;
         this.field1.bridge$setLunarScale(this.field1.bridge$getLunarScale() * var1);
      }

      @Generated
      public float getScale() {
         return this.scale;
      }
   }
}
