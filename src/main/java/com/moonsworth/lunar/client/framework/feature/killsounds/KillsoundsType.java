package com.moonsworth.lunar.client.framework.feature.killsounds;

import lombok.Generated;

public enum KillsoundsType {
   PLAYER_KILL,
   MELEE_KILL,
   BOW_KILL,
   ROD_KILL,
   THROWABLE_KILL,
   BEDWARS_WIN;

   public static KillsoundsType defaultType() {
      return PLAYER_KILL;
   }
}
