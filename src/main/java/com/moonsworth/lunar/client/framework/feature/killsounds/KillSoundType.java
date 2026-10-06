package com.moonsworth.lunar.client.framework.feature.killsounds;

import lombok.Generated;

public enum KillSoundType {
   PLAYER_KILL,
   MELEE_KILL,
   BOW_KILL,
   ROD_KILL,
   THROWABLE_KILL,
   BEDWARS_WIN;

   public static KillSoundType defaultType() {
      return PLAYER_KILL;
   }

   @Generated
   KillSoundType() {
   }
}
