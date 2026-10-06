package com.moonsworth.lunar.client.event.render;

import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class RenderScaleEvent extends Highlight {
   private float scale = 1.0F;

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public void setScale(float value) {
      this.scale = value;
   }
}
