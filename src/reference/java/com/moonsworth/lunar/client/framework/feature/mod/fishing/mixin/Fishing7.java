package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.ItemStackBridge;

public class Fishing7 {
   private final Bridge3_18 field1;
   private final ItemStackBridge field2;
   private final long field3;
   private final double field4;

   public Fishing7(Bridge3_18 bridge3_18, ItemStackBridge itemStackBridge, long value, double value2) {
      this.field1 = bridge3_18;
      this.field2 = itemStackBridge;
      this.field3 = value;
      this.field4 = value2;
   }

   public double method1() {
      return this.field4 <= 0.0 ? Double.MAX_VALUE : this.field3 / this.field4;
   }

   public Bridge3_18 method2() {
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
