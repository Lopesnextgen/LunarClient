package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge4_12;
import lombok.Generated;

public class ParticleRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Bridge4_12 field1;

   @Generated
   public Bridge4_12 method1() {
      return this.field1;
   }

   @Generated
   public ParticleRenderEvent(Bridge4_12 bridge4_12) {
      this.field1 = bridge4_12;
   }
}
