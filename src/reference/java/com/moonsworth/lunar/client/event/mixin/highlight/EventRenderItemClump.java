package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.EntityItemStateBridge;
import lombok.Generated;

public class EventRenderItemClump extends com.moonsworth.lunar.client.event.LunarEvent {
   private final EntityItemStateBridge field1;
   private long field2;
   private float field3;

   @Generated
   public EventRenderItemClump(EntityItemStateBridge mixinhelper21, long value, float value2) {
      this.field1 = mixinhelper21;
      this.field2 = value;
      this.field3 = value2;
   }

   @Generated
   public EntityItemStateBridge method1() {
      return this.field1;
   }

   @Generated
   public long method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   public void method4(long value) {
      this.field2 = value;
   }

   @Generated
   public void method5(float value) {
      this.field3 = value;
   }
}
