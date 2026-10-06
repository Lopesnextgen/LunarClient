package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.MixinHelper2;
import lombok.Generated;

public class ItemClumpEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final MixinHelper2 field1;
   private long field2;
   private float field3;

   @Generated
   public ItemClumpEvent(MixinHelper2 var1, long value, float value2) {
      this.field1 = var1;
      this.field2 = value;
      this.field3 = value2;
   }

   @Generated
   public MixinHelper2 method1() {
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
   public void method4(long var1) {
      this.field2 = var1;
   }

   @Generated
   public void method5(float var1) {
      this.field3 = var1;
   }
}
