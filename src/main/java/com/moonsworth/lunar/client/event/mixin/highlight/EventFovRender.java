package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class EventFovRender extends com.moonsworth.lunar.client.event.CancellableEvent {
   private float field1;

   @Generated
   public EventFovRender(float value1) {
      this.field1 = value1;
   }

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   public void method2(float value1) {
      this.field1 = value1;
   }
}
