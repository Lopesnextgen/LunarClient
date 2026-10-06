package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;

public enum HologramsType2 {
   FAILED,
   ADJACENT,
   OPENED,
   CLEARED,
   COMPLETED;

   public AdventureChatFormatting asColor() {
      return switch (this) {
         case FAILED -> AdventureChatFormatting.RED;
         case CLEARED -> AdventureChatFormatting.WHITE;
         case COMPLETED -> AdventureChatFormatting.GREEN;
         default -> AdventureChatFormatting.DARK_GRAY;
      };
   }
}
