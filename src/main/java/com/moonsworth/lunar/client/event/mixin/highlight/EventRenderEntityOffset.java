package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class EventRenderEntityOffset extends com.moonsworth.lunar.client.event.LunarEvent {
   private final boolean field1;
   private float field2 = 0.0F;
   private float field3 = 0.0F;
   private float field4 = 0.0F;
   private float field5 = 0.0F;
   private float field6 = 0.0F;
   private float field7 = 0.0F;
   private float scale = 1.0F;

   public void method1(float value1, float value2, float value3) {
      this.field2 = value1;
      this.field3 = value2;
      this.field4 = value3;
   }

   public void method2(float value1, float value2, float value3) {
      this.field5 = value1;
      this.field6 = value2;
      this.field7 = value3;
   }

   @Generated
   public boolean method3() {
      return this.field1;
   }

   @Generated
   public float getXOffset() {
      return this.field2;
   }

   @Generated
   public float getYOffset() {
      return this.field3;
   }

   @Generated
   public float method4() {
      return this.field4;
   }

   @Generated
   public float method5() {
      return this.field5;
   }

   @Generated
   public float method6() {
      return this.field6;
   }

   @Generated
   public float method7() {
      return this.field7;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public EventRenderEntityOffset(boolean flag) {
      this.field1 = flag;
   }

   @Generated
   public void setScale(float value1) {
      this.scale = value1;
   }
}
