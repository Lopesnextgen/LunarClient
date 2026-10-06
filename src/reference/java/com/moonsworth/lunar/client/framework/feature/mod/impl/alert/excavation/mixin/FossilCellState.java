package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;

public enum FossilCellState {
   EMPTY,
   UNKNOWN,
   FOSSIL;

   FossilCellState() {
   }

   public static FossilCellState from(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 == null) {
         return UNKNOWN;
      }

      if (bridgeextension_40.bridge$isEmpty()) {
         return EMPTY;
      }

      if (bridgeextension_40.bridge$hasDisplayName()) {
         String text1 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_40.bridge$getDisplayName());
         if (text1.startsWith("Dirt")) {
            return UNKNOWN;
         } else {
            return text1.equals("Fossil") ? FOSSIL : EMPTY;
         }
      } else {
         return UNKNOWN;
      }
   }
}
