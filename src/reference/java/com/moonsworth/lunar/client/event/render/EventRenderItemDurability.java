package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

@VersionGate(min = 26)
public class EventRenderItemDurability extends LunarEvent {
   private final ItemStackBridge field1;
   private double field2;

   @Generated
   public EventRenderItemDurability(ItemStackBridge bridgeextension_41, double value) {
      this.field1 = bridgeextension_41;
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
   public void method2(double value) {
      this.field2 = value;
   }
}
