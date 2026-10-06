package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.EntityItemExtensionBridge;
import com.moonsworth.lunar.bridge.PoseStackBridge;
import lombok.Generated;

public class EventRenderItemRotation extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final PoseStackBridge field1;
   private final EntityItemExtensionBridge field2;
   private final float field3;

   @Generated
   public EventRenderItemRotation(PoseStackBridge bridge_81, EntityItemExtensionBridge bridgeextension3_22, float value) {
      this.field1 = bridge_81;
      this.field2 = bridgeextension3_22;
      this.field3 = value;
   }

   @Generated
   public PoseStackBridge method1() {
      return this.field1;
   }

   @Generated
   public EntityItemExtensionBridge method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }
}
