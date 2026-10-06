package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class EventRenderChat extends com.moonsworth.lunar.client.event.ResultEvent {
   private final float field2;

   @Generated
   public EventRenderChat(float value) {
      this.field2 = value;
   }

   @Generated
   public float method2() {
      return this.field2;
   }
}
