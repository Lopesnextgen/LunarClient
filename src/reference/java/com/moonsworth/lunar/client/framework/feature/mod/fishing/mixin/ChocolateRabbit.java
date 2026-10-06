package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;

public class ChocolateRabbit {
   private final SlotBridge field1;
   private final ItemStackBridge field2;
   private final long field3;
   private final double field4;

   public ChocolateRabbit(SlotBridge bridge3_181, ItemStackBridge bridgeextension_42, long value, double value2) {
      this.field1 = bridge3_181;
      this.field2 = bridgeextension_42;
      this.field3 = value;
      this.field4 = value2;
   }

   public double method1() {
      return this.field4 <= 0.0 ? Double.MAX_VALUE : this.field3 / this.field4;
   }

   public SlotBridge method2() {
      return this.field1;
   }

   public ItemStackBridge method3() {
      return this.field2;
   }

   public long method4() {
      return this.field3;
   }

   public double method5() {
      return this.field4;
   }
}
