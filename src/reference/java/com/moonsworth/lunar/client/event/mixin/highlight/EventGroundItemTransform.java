package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.PoseStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import lombok.Generated;

public class EventGroundItemTransform extends com.moonsworth.lunar.client.event.LunarEvent {
   private final ItemStackRenderStateBridge field1;
   private final boolean field2;
   private final boolean field3;
   private final PoseStackBridge field4;
   private float scale = 1.0F;

   public EventGroundItemTransform(PoseStackBridge bridge_81, ItemStackRenderStateBridge mixinhelper_142, boolean flag3, boolean flag) {
      this.field1 = mixinhelper_142;
      this.field2 = flag3;
      this.field3 = flag;
      this.field4 = bridge_81;
   }

   @Generated
   public ItemStackRenderStateBridge method1() {
      return this.field1;
   }

   @Generated
   public boolean method2() {
      return this.field2;
   }

   @Generated
   public boolean method3() {
      return this.field3;
   }

   @Generated
   public PoseStackBridge method4() {
      return this.field4;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public EventGroundItemTransform(ItemStackRenderStateBridge mixinhelper_141, boolean flag, boolean flag3, PoseStackBridge bridge_84, float value) {
      this.field1 = mixinhelper_141;
      this.field2 = flag;
      this.field3 = flag3;
      this.field4 = bridge_84;
      this.scale = value;
   }

   @Generated
   public void setScale(float value) {
      this.scale = value;
   }
}
