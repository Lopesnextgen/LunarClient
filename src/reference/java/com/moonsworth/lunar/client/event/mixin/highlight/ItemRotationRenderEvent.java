package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.BridgeExtension3_2;
import com.moonsworth.lunar.bridge.Bridge_8;
import lombok.Generated;

public class ItemRotationRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Bridge_8 field1;
   private final BridgeExtension3_2 field2;
   private final float field3;

   @Generated
   public ItemRotationRenderEvent(Bridge_8 bridge_8, BridgeExtension3_2 bridgeExtension3_2, float value) {
      this.field1 = bridge_8;
      this.field2 = bridgeExtension3_2;
      this.field3 = value;
   }

   @Generated
   public Bridge_8 method1() {
      return this.field1;
   }

   @Generated
   public BridgeExtension3_2 method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }
}
