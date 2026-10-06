package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventPlayerRemoval extends Highlight {
   private Bridge6_10 field1;

   @Generated
   public EventPlayerRemoval(Bridge6_10 bridge6_10) {
      this.field1 = bridge6_10;
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }
}
