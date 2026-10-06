package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.BridgeExtension3_2;
import lombok.Generated;

public class DroppedItemRenderEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final BridgeExtension3_2 field1;
   private float field2;

   @Generated
   public DroppedItemRenderEvent(BridgeExtension3_2 var1, float value) {
      this.field1 = var1;
      this.field2 = value;
   }

   @Generated
   public BridgeExtension3_2 method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public void method3(float var1) {
      this.field2 = var1;
   }
}
