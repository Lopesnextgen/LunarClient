package com.moonsworth.lunar.client.event.fishing;

import com.moonsworth.lunar.bridge.GameRendererBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;
import com.moonsworth.lunar.client.event.render.EventBlockHighlight;

public class EventBlockHighlightLayer extends EventBlockHighlight {
   public final int field5;
   public final float farPlaneDistance;

   public EventBlockHighlightLayer(GameRendererBridge bridge3_171, BridgeExtension bridge, Bridge3_23 bridge3_233, double value, int value2, float value3) {
      super(bridge3_171, bridge, bridge3_233, value);
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
