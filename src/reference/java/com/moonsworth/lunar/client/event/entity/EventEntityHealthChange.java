package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventEntityHealthChange extends Highlight {
   private BridgeExtension2_5 field1;
   private float field2;
   private float field3;

   @Generated
   public BridgeExtension2_5 method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   public EventEntityHealthChange(BridgeExtension2_5 bridgeExtension2_5, float value, float value2) {
      this.field1 = bridgeExtension2_5;
      this.field2 = value;
      this.field3 = value2;
   }
}
