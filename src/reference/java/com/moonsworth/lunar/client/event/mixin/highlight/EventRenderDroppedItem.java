package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.EntityItemExtensionBridge;
import lombok.Generated;

public class EventRenderDroppedItem extends com.moonsworth.lunar.client.event.LunarEvent {
   private final EntityItemExtensionBridge field1;
   private float field2;

   @Generated
   public EventRenderDroppedItem(EntityItemExtensionBridge bridgeextension3_21, float value) {
      this.field1 = bridgeextension3_21;
      this.field2 = value;
   }

   @Generated
   public EntityItemExtensionBridge method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public void method3(float value) {
      this.field2 = value;
   }
}
