package com.moonsworth.lunar.client.event.mixin.chat;

import lombok.Generated;

public class EventChatSendLegacy extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private String message;

   @Generated
   public void setMessage(String var1) {
      this.message = var1;
   }

   @Generated
   public String getMessage() {
      return this.message;
   }

   @Generated
   public EventChatSendLegacy(String var1) {
      this.message = var1;
   }
}
