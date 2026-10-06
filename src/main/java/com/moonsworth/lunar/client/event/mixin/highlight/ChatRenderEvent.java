package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class ChatRenderEvent extends com.moonsworth.lunar.client.event.OutcomeEvent {
   private final float field2;

   @Generated
   public ChatRenderEvent(float value) {
      this.field2 = value;
   }

   @Generated
   public float method2() {
      return this.field2;
   }
}
