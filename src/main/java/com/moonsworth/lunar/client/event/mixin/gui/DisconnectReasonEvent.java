package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class DisconnectReasonEvent extends Highlight {
   private final String reason;

   @Generated
   public DisconnectReasonEvent(String text) {
      this.reason = text;
   }

   @Generated
   public String getReason() {
      return this.reason;
   }
}
