package com.moonsworth.lunar.client.event.fishing;

import com.moonsworth.lunar.bridge.GameRendererBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;
import com.moonsworth.lunar.client.event.render.EventBlockHighlight;

public class EventBlockHighlightRotation extends EventBlockHighlight {
   private float yaw;
   private float pitch;
   private float roll;

   public EventBlockHighlightRotation(GameRendererBridge bridge3_171, BridgeExtension bridge, Bridge3_23 bridge3_233, double value, float value2, float value3, float value4) {
      super(bridge3_171, bridge, bridge3_233, value);
      this.yaw = value2;
      this.pitch = value3;
      this.roll = value4;
   }

   @Generated
   public void method1(float value1) {
      this.yaw = value1;
   }

   @Generated
   public void method2(float value1) {
      this.pitch = value1;
   }

   @Generated
   public void method3(float value1) {
      this.roll = value1;
   }

   @Generated
   public float getYaw() {
      return this.yaw;
   }

   @Generated
   public float getPitch() {
      return this.pitch;
   }

   @Generated
   public float method4() {
      return this.roll;
   }
}
