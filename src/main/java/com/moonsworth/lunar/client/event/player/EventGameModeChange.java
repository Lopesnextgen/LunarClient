package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.world.GameTypeBridge;
import lombok.Generated;

public class EventGameModeChange extends EventPlayerState {
   private final GameTypeBridge field1;
   private final GameTypeBridge field2;

   public EventGameModeChange(GameTypeBridge gameTypeBridge, GameTypeBridge gameTypeBridge2) {
      this.field1 = gameTypeBridge;
      this.field2 = gameTypeBridge2;
   }

   @Generated
   public GameTypeBridge method1() {
      return this.field1;
   }

   @Generated
   public GameTypeBridge method2() {
      return this.field2;
   }
}
