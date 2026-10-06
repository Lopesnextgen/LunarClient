package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;

public enum RoomState {
   FAILED,
   ADJACENT,
   OPENED,
   CLEARED,
   COMPLETED;

   RoomState() {
   }

   public ChatFormatting asColor() {
      return switch (this) {
         case FAILED -> ChatFormatting.RED;
         case CLEARED -> ChatFormatting.WHITE;
         case COMPLETED -> ChatFormatting.GREEN;
         default -> ChatFormatting.DARK_GRAY;
      };
   }
}
