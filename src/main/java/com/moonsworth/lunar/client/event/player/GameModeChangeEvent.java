package com.moonsworth.lunar.client.event.player;

import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import lombok.Generated;

public class GameModeChangeEvent extends PlayerStateEvent {
   private final ItemcounterType2 field1;
   private final ItemcounterType2 field2;

   public GameModeChangeEvent(ItemcounterType2 itemcounterType2, ItemcounterType2 itemcounterType22) {
      this.field1 = itemcounterType2;
      this.field2 = itemcounterType22;
   }

   @Generated
   public ItemcounterType2 method1() {
      return this.field1;
   }

   @Generated
   public ItemcounterType2 method2() {
      return this.field2;
   }
}
