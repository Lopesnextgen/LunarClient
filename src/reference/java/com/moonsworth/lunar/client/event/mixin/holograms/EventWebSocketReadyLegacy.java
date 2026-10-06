package com.moonsworth.lunar.client.event.mixin.holograms;

import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.waypoints.WebSocketClientIterator;
import lombok.Generated;

public class EventWebSocketReadyLegacy extends Highlight {
   private final WebSocketClientIterator field1;

   @Generated
   public WebSocketClientIterator method1() {
      return this.field1;
   }

   @Generated
   public EventWebSocketReadyLegacy(WebSocketClientIterator iterator) {
      this.field1 = iterator;
   }
}
