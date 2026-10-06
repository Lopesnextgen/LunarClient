package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public class BlockHighlightRotationEvent extends BlockHighlightRenderEvent {
   private float yaw;
   private float pitch;
   private float roll;

   public BlockHighlightRotationEvent(Bridge3_17 var1, BridgeExtension bridge, Bridge3_23 bridge3_23, double value, float value2, float value3, float value4) {
      super(var1, bridge, bridge3_23, value);
      this.yaw = value2;
      this.pitch = value3;
      this.roll = value4;
   }

   @Generated
   public void method1(float var1) {
      this.yaw = var1;
   }

   @Generated
   public void method2(float var1) {
      this.pitch = var1;
   }

   @Generated
   public void method3(float var1) {
      this.roll = var1;
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
