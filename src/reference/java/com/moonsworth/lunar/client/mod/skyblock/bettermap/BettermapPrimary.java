package com.moonsworth.lunar.client.mod.skyblock.bettermap;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapVariant;
import com.moonsworth.lunar.client.framework.feature.mod.impl.guiCore.BettermapHud;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class BettermapPrimary extends BettermapHud {
   public BettermapPrimary(Skyblock skyblock1) {
      super(skyblock1, true, BettermapVariant.PRIMARY);
   }

   public String getId() {
      return "BETTERMAP_PRIMARY";
   }
}
