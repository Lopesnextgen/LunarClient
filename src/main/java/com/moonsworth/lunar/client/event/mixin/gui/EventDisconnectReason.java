package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventDisconnectReason extends LunarEvent {
   private final String reason;

   @Generated
   public EventDisconnectReason(String text) {
      this.reason = text;
   }

   @Generated
   public String getReason() {
      return this.reason;
   }
}
