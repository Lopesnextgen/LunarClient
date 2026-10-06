package com.moonsworth.lunar.client.event.fishing;

import com.moonsworth.lunar.bridge.GameRendererBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;
import com.moonsworth.lunar.client.event.render.EventBlockHighlight;

public class EventBlockHighlightAlpha extends EventBlockHighlight {
   private float field5;

   public EventBlockHighlightAlpha(GameRendererBridge bridge3_171, BridgeExtension bridge, Bridge3_23 bridge3_233, double value, float value2) {
      super(bridge3_171, bridge, bridge3_233, value);
      this.field5 = value2;
   }

   @Generated
   public float method4() {
      return this.field5;
   }

   @Generated
   public void method2(float value) {
      this.field5 = value;
   }
}
