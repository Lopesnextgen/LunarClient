package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge5_11;
import lombok.Generated;

public class PlayerJoinWorldLegacyEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private Bridge5_11 field1;

   @Generated
   public PlayerJoinWorldLegacyEvent(Bridge5_11 bridge5_11) {
      this.field1 = bridge5_11;
   }

   @Generated
   public Bridge5_11 method1() {
      return this.field1;
   }
}
