package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge6_10;
import lombok.Generated;

public class HologramUpdateEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final Bridge6_10 field1;

   @Generated
   public HologramUpdateEvent(Bridge6_10 bridge6_10) {
      this.field1 = bridge6_10;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }
}
