package com.moonsworth.lunar.client.event.fishing;

import com.moonsworth.lunar.bridge.GameRendererBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;
import com.moonsworth.lunar.client.event.render.EventBlockHighlight;

public class EventBlockHighlightColor extends EventBlockHighlight {
   private float red;
   private float green;
   private float blue;

   public EventBlockHighlightColor(GameRendererBridge bridge3_171, BridgeExtension bridge, Bridge3_23 bridge3_233, double value, float value2, float value3, float value4) {
      super(bridge3_171, bridge, bridge3_233, value);
      this.red = value2;
      this.green = value3;
      this.blue = value4;
   }

   @Generated
   public float method4() {
      return this.red;
   }

   @Generated
   public float method5() {
      return this.green;
   }

   @Generated
   public float method6() {
      return this.blue;
   }

   @Generated
   public void method4(float value1) {
      this.red = value1;
   }

   @Generated
   public void method5(float value1) {
      this.green = value1;
   }

   @Generated
   public void method6(float value1) {
      this.blue = value1;
   }
}
