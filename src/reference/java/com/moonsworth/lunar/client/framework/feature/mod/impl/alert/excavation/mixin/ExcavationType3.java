package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;

public enum ExcavationType3 {
   EMPTY,
   UNKNOWN,
   FOSSIL;

   public static ExcavationType3 from(ItemStackBridge itemStackBridge) {
      if (itemStackBridge == null) {
         return UNKNOWN;
      }

      if (itemStackBridge.bridge$isEmpty()) {
         return EMPTY;
      }

      if (itemStackBridge.bridge$hasDisplayName()) {
         String var1 = AdventureChatFormatting.getTextWithoutFormattingCodes(itemStackBridge.bridge$getDisplayName());
         if (var1.startsWith("Dirt")) {
            return UNKNOWN;
         } else {
            return var1.equals("Fossil") ? FOSSIL : EMPTY;
         }
      } else {
         return UNKNOWN;
      }
   }
}
