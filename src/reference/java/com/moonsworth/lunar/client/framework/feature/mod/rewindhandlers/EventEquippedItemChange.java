package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(EquippedItemListener.class)
public class EventEquippedItemChange extends LunarEvent implements DynamicListenerEvent {
   private final ItemStackBridge field1;
   private final String field2;
   private final String field3;

   @Generated
   public ItemStackBridge method1() {
      return this.field1;
   }

   @Generated
   public String getId() {
      return this.field2;
   }

   @Generated
   public String method2() {
      return this.field3;
   }

   @Generated
   public EventEquippedItemChange(ItemStackBridge bridgeextension_41, String text, String text2) {
      this.field1 = bridgeextension_41;
      this.field2 = text;
      this.field3 = text2;
   }
}
