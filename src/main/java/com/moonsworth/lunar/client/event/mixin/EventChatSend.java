package com.moonsworth.lunar.client.event.mixin;

import lombok.Generated;

public class EventChatSend extends com.moonsworth.lunar.client.event.CancellableEvent {
   private String message;

   @Generated
   public void setMessage(String text1) {
      this.message = text1;
   }

   @Generated
   public String getMessage() {
      return this.message;
   }

   @Generated
   public EventChatSend(String text1) {
      this.message = text1;
   }
}
