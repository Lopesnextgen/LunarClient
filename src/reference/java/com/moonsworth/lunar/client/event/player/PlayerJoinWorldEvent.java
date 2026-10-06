package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.Bridge5_11;
import lombok.Generated;

public class PlayerJoinWorldEvent extends com.moonsworth.lunar.client.event.CancellableEvent {
   private Bridge5_11 field1;

   @Generated
   public PlayerJoinWorldEvent(Bridge5_11 bridge5_111) {
      this.field1 = bridge5_111;
   }

   @Generated
   public Bridge5_11 method1() {
      return this.field1;
   }
}
