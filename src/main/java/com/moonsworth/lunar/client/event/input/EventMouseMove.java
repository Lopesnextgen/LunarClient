package com.moonsworth.lunar.client.event.input;

import lombok.Generated;

public class EventMouseMove extends com.moonsworth.lunar.client.event.CancellableEvent {
   private float field1;
   private float field2;

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public void method3(float value1) {
      this.field1 = value1;
   }

   @Generated
   public void method4(float value1) {
      this.field2 = value1;
   }

   @Generated
   public EventMouseMove(float value1, float value) {
      this.field1 = value1;
      this.field2 = value;
   }
}
