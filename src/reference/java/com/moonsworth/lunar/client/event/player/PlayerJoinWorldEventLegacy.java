package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class PlayerJoinWorldEventLegacy extends Highlight {
   private Bridge5_11 field1;

   @Generated
   public PlayerJoinWorldEventLegacy(Bridge5_11 bridge5_11) {
      this.field1 = bridge5_11;
   }

   @Generated
   public Bridge5_11 method1() {
      return this.field1;
   }
}
