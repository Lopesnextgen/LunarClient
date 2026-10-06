package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.EntityItemExtensionBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.EntityRendererBridge;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventRenderEntityItem extends com.moonsworth.lunar.client.event.CancellableEvent {
   private AbstractRenderContext field1;
   @Nullable
   EntityRendererBridge field2;
   private EntityItemExtensionBridge field3;
   private double x;
   private double y;
   private double z;
   private boolean field4 = false;
   private float field5 = 1.0F;

   @Generated
   public EventRenderEntityItem() {
   }

   @Generated
   public EventRenderEntityItem(
      AbstractRenderContext bridgeextension_91, @Nullable EntityRendererBridge bridge_622, EntityItemExtensionBridge bridgeextension3_23, double value, double value2, double value3, boolean flag, float value4
   ) {
      this.field1 = bridgeextension_91;
      this.field2 = bridge_622;
      this.field3 = bridgeextension3_23;
      this.x = value;
      this.y = value2;
      this.z = value3;
      this.field4 = flag;
      this.field5 = value4;
   }

   @Generated
   public AbstractRenderContext method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public EntityRendererBridge method2() {
      return this.field2;
   }

   @Generated
   public EntityItemExtensionBridge method3() {
      return this.field3;
   }

   @Generated
   public double getX() {
      return this.x;
   }

   @Generated
   public double getY() {
      return this.y;
   }

   @Generated
   public double getZ() {
      return this.z;
   }

   @Generated
   public boolean method4() {
      return this.field4;
   }

   @Generated
   public float method5() {
      return this.field5;
   }
}
