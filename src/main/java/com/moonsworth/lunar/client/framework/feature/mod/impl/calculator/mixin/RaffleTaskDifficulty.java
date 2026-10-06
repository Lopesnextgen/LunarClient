package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin;

import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import javax.annotation.Nullable;
import lombok.Generated;

public enum RaffleTaskDifficulty {
   EASY("Easy", ChatFormatting.GREEN),
   MEDIUM("Medium", ChatFormatting.YELLOW),
   HARD("Hard", ChatFormatting.RED);

   public static final int TASKS_PER_TIER = 7;
   private final String label;
   private final ChatFormatting color;

   RaffleTaskDifficulty(String text, ChatFormatting chatFormatting) {
      this.label = text;
      this.color = chatFormatting;
   }

   @Nullable
   public static RaffleTaskDifficulty fromLoreLine(String text) {
      for (RaffleTaskDifficulty calculatortype24 : values()) {
         if (text.equals(calculatortype24.label + " Task")) {
            return calculatortype24;
         }
      }

      return null;
   }

   @Generated
   public String getLabel() {
      return this.label;
   }

   @Generated
   public ChatFormatting getColor() {
      return this.color;
   }
}
