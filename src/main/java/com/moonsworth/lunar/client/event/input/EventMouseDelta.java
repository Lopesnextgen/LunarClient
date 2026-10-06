package com.moonsworth.lunar.client.event.input;

import lombok.Generated;

public class EventMouseDelta extends com.moonsworth.lunar.client.highlight.HighlightImpl {
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
   public void method3(float var1) {
      this.field1 = var1;
   }

   @Generated
   public void method4(float var1) {
      this.field2 = var1;
   }

   @Generated
   public EventMouseDelta(float var1, float value) {
      this.field1 = var1;
      this.field2 = value;
   }
}
