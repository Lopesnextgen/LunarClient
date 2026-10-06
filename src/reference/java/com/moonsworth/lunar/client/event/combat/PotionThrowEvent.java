package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class PotionThrowEvent extends Highlight {
   private BridgeExtension2_5 field1;
   private ItemStackBridge field2;

   @Generated
   public BridgeExtension2_5 method1() {
      return this.field1;
   }

   @Generated
   public ItemStackBridge method2() {
      return this.field2;
   }

   @Generated
   public PotionThrowEvent(BridgeExtension2_5 bridgeExtension2_5, ItemStackBridge itemStackBridge) {
      this.field1 = bridgeExtension2_5;
      this.field2 = itemStackBridge;
   }
}
