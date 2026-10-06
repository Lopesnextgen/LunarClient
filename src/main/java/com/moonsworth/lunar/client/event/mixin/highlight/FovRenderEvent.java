package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class FovRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private float field1;

   @Generated
   public FovRenderEvent(float var1) {
      this.field1 = var1;
   }

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   public void method2(float var1) {
      this.field1 = var1;
   }
}
