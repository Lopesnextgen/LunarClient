package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;

public class EventEntityItem extends com.moonsworth.lunar.client.event.CancellableEvent {
   public ItemStackBridge field1;
   public Bridge6_10 field2;

   @Generated
   public EventEntityItem(ItemStackBridge bridgeextension_41, Bridge6_10 bridge6_102) {
      this.field1 = bridgeextension_41;
      this.field2 = bridge6_102;
   }
}
