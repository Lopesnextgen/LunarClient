package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class CameraOffsetEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final float field1;
   private float field2;
   private float field3;
   private float field4;

   public void method1(float var1, float value, float value2) {
      this.field2 += var1;
      this.field3 += value;
      this.field4 += value2;
   }

   @Generated
   public float method2() {
      return this.field1;
   }

   @Generated
   public float method3() {
      return this.field2;
   }

   @Generated
   public float method4() {
      return this.field3;
   }

   @Generated
   public float method5() {
      return this.field4;
   }

   @Generated
   public CameraOffsetEvent(float var1) {
      this.field1 = var1;
   }
}
