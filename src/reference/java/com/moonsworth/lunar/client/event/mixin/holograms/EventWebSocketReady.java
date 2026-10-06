package com.moonsworth.lunar.client.event.mixin.holograms;

import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.network.ipc.WebSocketClientIterator;
import lombok.Generated;

public class EventWebSocketReady extends LunarEvent {
   private final WebSocketClientIterator field1;

   @Generated
   public WebSocketClientIterator method1() {
      return this.field1;
   }

   @Generated
   public EventWebSocketReady(WebSocketClientIterator iterator) {
      this.field1 = iterator;
   }
}
