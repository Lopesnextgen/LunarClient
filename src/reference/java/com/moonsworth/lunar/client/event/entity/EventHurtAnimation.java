package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import lombok.Generated;

public class EventHurtAnimation extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final BridgeExtension2_5 field1;
   private float yaw;

   @Generated
   public BridgeExtension2_5 method1() {
      return this.field1;
   }

   @Generated
   public float getYaw() {
      return this.yaw;
   }

   @Generated
   public EventHurtAnimation(BridgeExtension2_5 bridgeExtension2_5, float value) {
      this.field1 = bridgeExtension2_5;
      this.yaw = value;
   }
}
