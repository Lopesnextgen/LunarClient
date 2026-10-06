package com.moonsworth.lunar.client.mod.skyblock.bettermap;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapVariant;
import com.moonsworth.lunar.client.framework.feature.mod.impl.guiCore.BettermapHud;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class BettermapSecondary extends BettermapHud {
   public BettermapSecondary(Skyblock skyblock1) {
      super(skyblock1, BettermapVariant.SECONDARY);
   }

   public String getId() {
      return "BETTERMAP_SECONDARY";
   }
}
