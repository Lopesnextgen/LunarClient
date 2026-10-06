package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge_8;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import lombok.Generated;

public class GroundItemTransformEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final ItemStackRenderStateBridge field1;
   private final boolean field2;
   private final boolean field3;
   private final Bridge_8 field4;
   private float scale = 1.0F;

   public GroundItemTransformEvent(Bridge_8 var1, ItemStackRenderStateBridge var2, boolean var3, boolean var4) {
      this.field1 = var2;
      this.field2 = var3;
      this.field3 = var4;
      this.field4 = var1;
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
   public Bridge_8 method4() {
      return this.field4;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public GroundItemTransformEvent(ItemStackRenderStateBridge var1, boolean var2, boolean var3, Bridge_8 var4, float value) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.scale = value;
   }

   @Generated
   public void setScale(float var1) {
      this.scale = var1;
   }
}
