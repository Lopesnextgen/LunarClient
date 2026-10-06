package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState;
import lombok.Generated;

public enum WaypointVisibility implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   CLEAR("clear"),
   SECRETS("secrets"),
   BOTH("both");

   private final String id;

   public boolean rendersAt(RoomState hologramstype21) {
      return switch (this) {
         case BOTH -> true;
         case CLEAR -> hologramstype21 != RoomState.CLEARED && hologramstype21 != RoomState.COMPLETED;
         case SECRETS -> hologramstype21 == RoomState.CLEARED;
      };
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   WaypointVisibility(String text3) {
      this.id = text3;
   }
}
