package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public class BlockHighlightLayerEvent extends BlockHighlightRenderEvent {
   public final int field5;
   public final float farPlaneDistance;

   public BlockHighlightLayerEvent(Bridge3_17 bridge3_17, BridgeExtension bridge, Bridge3_23 bridge3_23, double value, int value2, float value3) {
      super(bridge3_17, bridge, bridge3_23, value);
      this.field5 = value2;
      this.farPlaneDistance = value3;
   }

   @Generated
   public int method4() {
      return this.field5;
   }

   @Generated
   public float method5() {
      return this.farPlaneDistance;
   }
}
