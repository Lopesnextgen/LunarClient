package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

@Annotation2(min = 26)
public class ItemDurabilityRenderEvent extends Highlight {
   private final ItemStackBridge field1;
   private double field2;

   @Generated
   public ItemDurabilityRenderEvent(ItemStackBridge var1, double value) {
      this.field1 = var1;
      this.field2 = value;
   }

   @Generated
   public ItemStackBridge getItem() {
      return this.field1;
   }

   @Generated
   public double method1() {
      return this.field2;
   }

   @Generated
   public void method2(double var1) {
      this.field2 = var1;
   }
}
