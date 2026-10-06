package com.moonsworth.lunar.client.event.resourcepack;

import com.moonsworth.lunar.bridge.ResourcePackBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public class EventResourcePackUpdate extends LunarEvent {
   private final ResourcePackBridge field1;

   @Generated
   public ResourcePackBridge method1() {
      return this.field1;
   }

   @Generated
   public EventResourcePackUpdate(ResourcePackBridge bridge141) {
      this.field1 = bridge141;
   }
}
