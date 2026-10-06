package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public class BlockHighlightAlphaEvent extends BlockHighlightRenderEvent {
   private float field5;

   public BlockHighlightAlphaEvent(Bridge3_17 var1, BridgeExtension bridge, Bridge3_23 bridge3_23, double value, float value2) {
      super(var1, bridge, bridge3_23, value);
      this.field5 = value2;
   }

   @Generated
   public float method4() {
      return this.field5;
   }

   @Generated
   public void method2(float var1) {
      this.field5 = var1;
   }
}
