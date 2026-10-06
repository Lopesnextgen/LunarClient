package com.moonsworth.lunar.client.event.render;

import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public class EventRenderScale extends LunarEvent {
   private float scale = 1.0F;

   public EventRenderScale() {
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public void setScale(float value) {
      this.scale = value;
   }
}
