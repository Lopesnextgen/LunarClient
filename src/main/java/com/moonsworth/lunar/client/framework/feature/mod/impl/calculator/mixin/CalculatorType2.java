package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import javax.annotation.Nullable;
import lombok.Generated;

public enum CalculatorType2 {
   EASY("Easy", AdventureChatFormatting.GREEN),
   MEDIUM("Medium", AdventureChatFormatting.YELLOW),
   HARD("Hard", AdventureChatFormatting.RED);

   public static final int TASKS_PER_TIER = 7;
   private final String label;
   private final AdventureChatFormatting color;

   CalculatorType2(String text, AdventureChatFormatting var4) {
      this.label = text;
      this.color = var4;
   }

   @Nullable
   public static CalculatorType2 fromLoreLine(String text) {
      for (CalculatorType2 var4 : values()) {
         if (text.equals(var4.label + " Task")) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   public String getLabel() {
      return this.label;
   }

   @Generated
   public AdventureChatFormatting getColor() {
      return this.color;
   }
}
